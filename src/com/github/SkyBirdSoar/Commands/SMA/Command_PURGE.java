package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
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
import org.bukkit.configuration.file.FileConfiguration;

public class Command_PURGE {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_PURGE(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void purge(CommandSender sender, Command cmd, String label, String[] args){
        FileConfiguration fc = sm.getConfig("config", false);
        if(fc.getBoolean("purgeProtect")){
            if(args.length == 2){
                if(fc.getString("purgePassword").equals(args[1])){
                    actionTaken(sender, cmd, label, args);
                }
            }
            else{
                ch.sendMessage(sender, "&cWrong Password! Usage: /sma purge <password>");
            }
        }
        else{
            this.actionTaken(sender, cmd, label, args);
        }
    }
    private File getHistoryFile(){
        File file = sm.getFolder("History");
        file = new File(file, "history.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } 
            catch (IOException ex) {
                Logger.getLogger(Command_PURGE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return file;
    }
    private void actionTaken(CommandSender sender, Command cmd, String label, String[] args){
        File file = getHistoryFile();
        File pf = sm.getFolder("players");
        File df = sm.getFolder("data");
        File hf = sm.getFolder("HELP_FILES");
        File[] pf2 = pf.listFiles();
        File[] df2 = df.listFiles();
        File[] hf2 = hf.listFiles();
        for(int a = 0; a < pf2.length; a++){
            pf2[a].delete();
        }
        for(int a = 0; a < df2.length; a++){
            df2[a].delete();
        }
        for(int a = 0; a < hf2.length; a++){
            hf2[a].delete();
        }
        boolean del1 = pf.delete();
        boolean del2 = df.delete();
        boolean del3 = hf.delete();
        if(!del1 && pf.exists()){
            ch.sendMessage(sender, "&cAn error occurred while deleting folder: players");
        }
        if(!del2 && df.exists()){
            ch.sendMessage(sender, "&cAn error occurred while deleting folder: data");
        }
        if(!del3 && hf.exists()){
            ch.sendMessage(sender, "&cAn error occurred while deleting folder: HELP_FILES");
        }
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
            for(int a = 0; a < toWrite.length; a++){
                pw.println(toWrite[a]);
            }
            pw.println(sender.getName() + ": Used /sma purge.");
            ch.sendMessage(sender, "&cSuccessfully purged!");
            sm.log(Level.SEVERE, sender.getName() + " has purged folders!");
            sm.broadcast("&c" + sender.getName() + " has deleted all data!");
            ch.sendMessage(sender, "&cKicking all players to regenerate config files...");
            sm.getServer().dispatchCommand(sm.getServer().getConsoleSender(), "kickall");
        }   
        catch (FileNotFoundException ex) {
            Logger.getLogger(Command_PURGE.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
             Logger.getLogger(Command_PURGE.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            pw.close();
        }
    }
}
