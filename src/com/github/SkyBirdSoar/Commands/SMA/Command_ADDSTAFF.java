package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.Commands.SM.Command_STAFF;
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
import org.bukkit.entity.Player;

public class Command_ADDSTAFF {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_ADDSTAFF(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void addstaff(CommandSender sender, Command command, String label, String[] args){
        if(args.length == 1){
            ch.sendMessage(sender, "&c/sma addstaff <IGN>");
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
                boolean check = false;
                pw = new PrintWriter(new FileWriter(file));
                for(int a = 0; a < toWrite.length; a++){
                    pw.println(toWrite[a]);
                    if(toWrite[a].equals(args[1])){
                        check = true;
                    }
                }
                if(!check){
                    pw.println(args[1]);
                    ch.sendMessage(sender, "&cSuccessfully added!");
                }
                if(check){
                    ch.sendMessage(sender, "&cERROR: Player was already a staff.");
                }
            } 
            catch (IOException ex) {
                Logger.getLogger(Command_ADDSTAFF.class.getName()).log(Level.SEVERE, null, ex);
                ch.sendMessage(sender, "&cFailed to add player.");
            }
            finally{
                pw.close();
            }
        }
        else{
            ch.sendMessage(sender, "&cToo many arguments!");
            ch.sendMessage(sender, "&c/sma addstaff <IGN>");
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
