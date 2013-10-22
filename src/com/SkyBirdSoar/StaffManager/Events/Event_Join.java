package com.SkyBirdSoar.StaffManager.Events;

import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import com.SkyBirdSoar.StaffManager.Util.PlayerConfig;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

/*  StaffManager
    Copyright (C) 2013  SkyBirdSoar

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Event_Join {
    private StaffManager m;
    public Event_Join(StaffManager m){
        this.m = m;
    }
    public void onPlayerJoin(PlayerJoinEvent e){
        if(!checkExist(e.getPlayer())){
            String IGN = e.getPlayer().getName();
            try {
                PlayerConfig pc = new PlayerConfig(m, e.getPlayer());
                pc.generateConfig();
                pc.setDateJoined(time());
            } 
            catch (IOException ex) {
                m.consoleOut("&cUnable to save &6players/" + IGN + ".yml&c: An IOException occurred. Details saved in server.log");
                m.getCH().out((CommandSender) e.getPlayer(), ERROR.ERROR_UNABLE_TO_SAVE_CONFIG);
                m.getLogger().log(Level.FINE, null, ex);
            }
        }
        else{
            alert(e.getPlayer());
        }
    }
    
    private boolean checkExist(Player p){
        String IGN = p.getName();
        File f = new File(m.getPlayerFolder(), IGN + ".yml");
        return f.exists();
    }
    
    private void alert(Player p){
        String IGN = p.getName();
        File f = new File(m.getPlayerFolder(), IGN + ".yml");
        FileConfiguration fc = m.getConfig(f);
        wait(3);
        if(fc.getBoolean("alert.10.up") && fc.getInt("application.vote.up") >= 10 && fc.getBoolean("player.application")){
            try {
                m.getCH().out(p, "Congratulations on getting 10 votes!");
                fc.set("alert.10.up", false);
                fc.save(f);
            } 
            catch (IOException ex) {
                m.consoleOut("&cUnable to save &6players/" + IGN + ".yml&c: An IOException occurred. Details saved in server.log");
                m.getCH().out((CommandSender) p, ERROR.ERROR_UNABLE_TO_SAVE_CONFIG);
                m.getLogger().log(Level.FINE, null, ex);
            }
        }
        if(fc.getBoolean("alert.10.down") && fc.getInt("application.vote.down") >= 10 && fc.getBoolean("player.application")){
            try {
                m.getCH().out(p, "Unfortunately, you have 10 downvotes!");
                fc.set("alert.10.down", false);
                fc.save(f);
            } 
            catch (IOException ex) {
                m.consoleOut("&cUnable to save &6players/" + IGN + ".yml&c: An IOException occurred. Details saved in server.log");
                m.getCH().out((CommandSender) p, ERROR.ERROR_UNABLE_TO_SAVE_CONFIG);
                m.getLogger().log(Level.FINE, null, ex);
            }
        }
    }
    
    private String time(){
        Calendar d = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("d MMM yyyy");
        String time = sdf.format(d.getTime());
        return time;
    }
    
    private void wait (int n){
        long t0, t1;
        t0 =  System.currentTimeMillis();
        do{
            t1 = System.currentTimeMillis();
        } while ((t1 - t0) < (n * 1000));
    }
}