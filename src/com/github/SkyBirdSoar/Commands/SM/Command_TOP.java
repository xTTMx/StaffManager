package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.StaffManager.CommandHandler;
import com.github.SkyBirdSoar.StaffManager.StaffManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Command_TOP {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_TOP(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void top(CommandSender sender, Command cmd, String label, String[] args){
        File file = sm.getFolder("players");
        File[] configs = file.listFiles();
        List<String> names = new ArrayList<>();
        int b = 0;
        for(int a = 0; a < configs.length; a++){
            String temp = configs[a].getName();
            String[] fileAndExt = temp.split("\\.");
            names.add(fileAndExt[0]);
            b++;
        }
        String[] igns = new String[b];
        igns = names.toArray(igns);
        String top = "";
        String second = "";
        String third = "";
        int first = -1;
        int sec = -1;
        int thr = -1;
        for(int a = 0; a < igns.length; a++){
            FileConfiguration fc = sm.getConfig(igns[a], true);
            int temp = fc.getInt("player.voteUp.number");
            if(temp > thr){
                if(temp > sec){
                    if(temp > first){
                        top = igns[a];
                        first = temp;
                    }
                    else{
                        second = igns[a];
                        sec = temp;
                    }
                }
                else{
                    third = igns[a];
                    thr = temp;
                }
            }
        }
        ch.sendMessage(sender, "&e=== &b&lPLAYER TOP &e===");
        if(!top.equals("")){
            ch.sendMessage(sender, "&6Top Player: " + top + ", with " + first + " votes!");
        }
        if(!second.equals("")){
            ch.sendMessage(sender, "&6Second: " + second + ", with " + sec + " votes!");
        }
        if(!third.equals("")){
            ch.sendMessage(sender, "&6Third: " + third + ", with " + thr + " votes!");
        }
    }
}
