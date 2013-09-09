package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Command_DEMOTE {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_DEMOTE(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void demote(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 1){
            ch.sendMessage(sender, "&c/sma demote <IGN>");
        }
        if(args.length == 2){
            File file = getStaffFile();
            PrintWriter pw = null;
            try{
                Scanner scan = new Scanner(file);
                List<String> list = new ArrayList<>();
                int lines = 0;
                while(scan.hasNextLine()){
                    list.add(scan.nextLine());
                    lines++;
                }
                String[] toWrite = new String[lines];
                toWrite = list.toArray(toWrite);
                pw = new PrintWriter(new FileWriter(file));
                boolean successful = false;
                for(int a = 0; a < toWrite.length; a++){
                    if(!toWrite[a].equals(args[1])){
                        pw.println(toWrite[a]);
                    }
                    if(toWrite[a].equals(args[1])){
                        successful = true;
                    }
                }
                if(successful){
                    ch.sendMessage(sender, "&cSuccessfully removed!");
                }
                else{
                    ch.sendMessage(sender, "&cERROR: User does not exist.");
                }
            } 
            catch (IOException ex) {
                Logger.getLogger(Command_ADDSTAFF.class.getName()).log(Level.SEVERE, null, ex);
                ch.sendMessage(sender, "&cFailed to remove player.");
            }
            finally{
                pw.close();
            }
        }
        else{
            ch.sendMessage(sender, "&cToo many arguments!");
            ch.sendMessage(sender, "&c/sma demote <IGN>");
        }
        
    }
    private File getStaffFile(){
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
