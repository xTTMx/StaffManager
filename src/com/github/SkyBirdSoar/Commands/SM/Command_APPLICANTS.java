package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.StaffManager.CommandHandler;
import com.github.SkyBirdSoar.StaffManager.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Command_APPLICANTS {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_APPLICANTS(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void applicants(CommandSender sender, Command cmd, String label, String[] args){
        File file = this.getDataFile();
        if(file.exists()){
            try {
                Scanner scan = new Scanner(file);
                List<String> list = new ArrayList<>();
                int lines = 0;
                while(scan.hasNextLine()){
                    list.add(scan.nextLine());
                    lines++;
                }
                if(lines != 0){
                    String[] array = new String[lines];
                    array = list.toArray(array);
                    if(args.length == 1){
                        boolean nextPage = false;
                        if(array.length > 9){
                            nextPage = true;
                        }
                        ch.sendMessage(sender, "&c=== &6Applicants&c ===");
                        for(int a = 0; a < array.length && a < 10; a ++){
                            ch.sendMessage(sender, (a+1) + ". &a" + array[a]);
                        }
                        if(nextPage){
                            ch.sendMessage(sender, "&cDo /sm applicants 2 to continue.");
                        }
                    }
                    if(args.length > 1){
                        if(array.length > 10){
                            try{
                                int page = Integer.parseInt(args[1]);
                                ch.sendMessage(sender, "&c=== &6Applicants Page " + args[1] + "&c ===");
                                int pageMax = page * 10;
                                int pageMin = pageMax - 10 + 1;
                                if(lines > pageMin){
                                    for(int a = pageMin; a <= pageMax && a < array.length; a++){
                                        ch.sendMessage(sender, a + ". &a" + array[a]);
                                    }
                                    boolean nextPage = false;
                                    if(array.length > pageMax){
                                        nextPage = true;
                                    }
                                    if(nextPage){
                                        ch.sendMessage(sender, "&cDo /sm applicants " + (page+1) + " to continue.");
                                    }
                                }
                                else{
                                    ch.sendMessage(sender, "&cERROR: Page does not exist.");
                                }
                            }
                            catch(NumberFormatException e){
                                ch.sendMessage(sender, "&cPage must be all digits!");
                            }
                        }
                        else{
                            ch.sendMessage(sender, "&cNo results for page " + args[2]);
                        }
                    }
                }
                else{
                    ch.sendMessage(sender, "&cNo results to display.");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Command_APPLICANTS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public File getDataFile(){
        File file = sm.getFolder("data");
        file = new File(file, "applicants" + ".txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } 
            catch (IOException ex) {
                sm.getLogger().log(Level.SEVERE, null, ex);
            }
        }
        return file;
    }
}
