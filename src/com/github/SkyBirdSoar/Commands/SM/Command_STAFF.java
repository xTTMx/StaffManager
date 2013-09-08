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
import org.bukkit.entity.Player;

public class Command_STAFF {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_STAFF(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void staff(CommandSender sender, Command cmd, String label, String[] args){
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
                        ch.sendMessage(sender, "&c=== &6Staff Online&c ===");
                        int noOfStaffOnline = 0;
                        String staffOnline = "";
                        Player[] player = sm.getServer().getOnlinePlayers();
                        for(int a = 0; a < array.length && a < 10; a++){
                            for(int b = 0; b < player.length; b++){
                                if(player[b].getName().equals(array[a])){
                                    staffOnline += array[a] + ", ";
                                    noOfStaffOnline++;
                                }
                            }
                        }
                        ch.sendMessage(sender, "&6Total Staff Online: " + noOfStaffOnline);
                        ch.sendMessage(sender, "&6Staff Online: &b" + staffOnline);
                    }
                    if(args.length == 2){
                        if(args[1].equals("all")){
                            boolean nextPage = false;
                            if(array.length > 9){
                                 nextPage = true;
                            }
                            ch.sendMessage(sender, "&c=== &6Staff (All)&c ===");
                            for(int a = 0; a < array.length && a < 10; a ++){
                                ch.sendMessage(sender, (a+1) + ". &a" + array[a]);
                            }
                            if(nextPage){
                                ch.sendMessage(sender, "&cDo /sm staff all 2 to continue.");
                            }
                        }
                        if(args.length > 2){
                            if(args[1].equals("all")){
                                if(array.length > 10){
                                    if(array.length > 10){
                                        try{
                                            int page = Integer.parseInt(args[2]);
                                            ch.sendMessage(sender, "&c=== &6Staff Page " + args[1] + "&c ===");
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
                                                    ch.sendMessage(sender, "&cDo /sm staff all " + (page+1) + " to continue.");
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
                                }
                            }
                        }
                        
                    }
                }
                else{
                    ch.sendMessage(sender, "&cNo staff online.");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Command_APPLICANTS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private File getDataFile(){
        File file = sm.getFolder("data");
        file = new File(file, "staff" + ".txt");
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
