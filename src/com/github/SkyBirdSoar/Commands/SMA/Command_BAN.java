package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.API.WriteAndReadAPI;
import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.ERROR;
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

public class Command_BAN extends WriteAndReadAPI{
    private StaffManager sm;
    private CommandHandler ch;
    public Command_BAN(StaffManager pl, CommandHandler pl2){
        super(pl, pl2, "data", "banned.txt");
        sm = pl;
        ch = pl2;
    }
    public void ban(CommandSender sender, Command cmd, String label, String[] args){
        File file = sm.getFile(args[1], true);
        if(file.exists()){
            FileConfiguration fc = sm.getConfig(args[1], true);
            if(!fc.getBoolean("player.banned")){
                try {
                    Scanner scan = new Scanner(getDataFile());
                    List<String> list = new ArrayList<>();
                    int lines = 0;
                    while(scan.hasNextLine()){
                        list.add(scan.nextLine());
                        lines++;
                    }
                    String[] array = new String[lines];
                    array = list.toArray(array);
                    PrintWriter pw = new PrintWriter(new FileWriter(getDataFile()));
                    for(int a = 0; a < array.length; a++){
                        pw.println(array[a]);
                    }
                    pw.println(args[1]);
                    pw.close();
                    fc.set("player.banned", true);
                    sm.saveConfig(args[1], fc, true);
                    ch.sendMessage(sender, "&aSuccessfully prevented the player from using /sm apply");
                } 
                catch (FileNotFoundException ex) {
                    Logger.getLogger(Command_BAN.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (IOException ex) {
                    Logger.getLogger(Command_BAN.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
            else{
                ch.sendMessage(sender, ERROR.ERROR_PLAYER_ALREADY_BANNED);
            }
        }
        else{
            ch.sendMessage(sender, ERROR.ERROR_PLAYER_NOT_FOUND);
        }
    }  
    private File getDataFile(){
        File file = sm.getFolder("data");
        file = new File(file, "banned" + ".txt");
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

    @Override
    public void command(CommandSender sender, Command cmd, String label, String[] args) {
        this.setMessage(true, "&aSuccessfully banned player from using /sm apply.");
        this.setMessage(false, "&cFailed to perform command.");
        this.setText(args[1], sender);
    }
}
