package com.github.SkyBirdSoar.Events;

import com.github.SkyBirdSoar.StaffManager.StaffManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class Event_Join {
    private StaffManager sm;
    public Event_Join(StaffManager pl){
        sm = pl;
    }
    public void onPlayerJoin(PlayerJoinEvent e){
        File file = sm.getFile(e.getPlayer().getName(), true);
        FileConfiguration fc = sm.getConfig(e.getPlayer().getName(), true);
        FileConfiguration cc = sm.getConfig("config", false);
        if(!file.exists()){
            Calendar d = Calendar.getInstance();
            int date = d.get(Calendar.DAY_OF_MONTH);
            int month = d.get(Calendar.MONTH);
            int year = d.get(Calendar.YEAR);
            List<Integer> time = Arrays.asList(date, month, year);
            defaultPlayerConfig(e.getPlayer().getName(), fc, time);
        }
        if(file.exists()){
            final String reminder = "&5[&aREMINDER&5] ";
            final String notice = "&5[&9NOTICE&5] ";
            if(fc.getBoolean("player.app")){
                sendMessage(e.getPlayer(), reminder + "&aYou are applying for staff!");
            }
            if(fc.getBoolean("player.banned")){
                sendMessage(e.getPlayer(), reminder + "&cYou are not allowed to apply for staff.");
            }
            if(!cc.getBoolean("applyOn")){
                sendMessage(e.getPlayer(), reminder + "&bRecruitment&c for staff is &4&lCLOSED&c.");
            }
            if(fc.getInt("player.voteUp.number") > 10){
                if(fc.getBoolean("alert.10.up")){
                    sendMessage(e.getPlayer(), notice + "&aCongratulations, you have gotten at least 10 votes!");
                    fc.set("alert.10.up", false);
                    sm.saveConfig(e.getPlayer().getName(), fc, true);
                }
            }
            if(fc.getInt("player.voteDown.number") > 10){
                if(fc.getBoolean("alert.10.down")){
                    sendMessage(e.getPlayer(), notice + "&cYou have at least 10 downvotes!");
                    fc.set("alert.10.down", false);
                    sm.saveConfig(e.getPlayer().getName(), fc, true);
                }
            }
            
        }
    }
    private void defaultPlayerConfig(String IGN, FileConfiguration fc, List<Integer> time){
        fc.set("player.app", false);
        fc.set("player.voteUp.number", 0);
        fc.set("player.voteUp.list", "");
        fc.set("player.voteDown.number", 0);
        fc.set("player.voteDown.list", 0);
        fc.set("player.banned", false);
        fc.set("player.dateJoined", time);
        fc.set("alert.10.up", true);
        fc.set("alert.10.down", true);
        sm.saveConfig(IGN, fc, true);
    }
    private void sendMessage(Player p, String m){
        p.sendMessage(sm.parseColor(m));
    }
}
