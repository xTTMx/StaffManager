package com.SkyBirdSoar.StaffManager.Util;

import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerConfig {
    private StaffManager m;
    private Player p;
    private FileConfiguration f;
    private File fi;
    public PlayerConfig(StaffManager m, Player e){
        this.m = m;
        p = e;
        fi = new File(m.getPlayerFolder(), p.getName() + ".yml");
        f = m.getConfig(fi);
    }
    
    public FileConfiguration generateConfig(){
        FileConfiguration config = null;
        InputStream defConfigStream = m.getResource("playerconfig.yml");
        if (defConfigStream != null) {
            m.saveResource("data/" + p.getName() + ".yml", false);
            config = YamlConfiguration.loadConfiguration(defConfigStream);
        }
        return config;
    }
    
    public int getVotes(String s){
        boolean valid = s.equals("up") || s.equals("down") ? true : false;
        if(valid){
            if(s.equals("up")){
                return f.getInt("application.vote.up");
            }
            else{
                return f.getInt("application.vote.down");
            }
        }
        else{
            throw new IllegalArgumentException("Vote can only be either up or down");
        }
    }
    
    public boolean getApplyingStatus(){
        return f.getBoolean("player.isApplicant");
    }
    
    public String getStaffStatus(){
        if(getApplyingStatus()){
            return "applicant";
        }
        else{
            return f.getBoolean("player.isStaff") + "";
        }
    }
    
    public boolean getBlockedStatus(){
        return f.getBoolean("player.isBlocked");
    }
    
    public boolean getAlertStatus(int a, String s){
        boolean valid = a == 10 && s.equals("up") || s.equals("down") ? true : false;
        if(valid){
            String b = "alert. " + a + "." + s;
            return f.getBoolean(b);
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    
    public String getDateJoined(){
        return f.getString("player.dateJoined");
    }
    
    public boolean getOnAlertStatus(){
        return f.getBoolean("player.isOnVoteAlertOn");
    }
    
    public void setVotes(int a, String s) throws IOException{
        boolean valid = (a > 0) && (s.equals("up") || s.equals("down")) ? true : false;
        if(valid){
            if(s.equals("up")){
                f.set("application.vote.up", a);
                f.save(fi);
            }
            else{
                f.set("application.vote.down", a);
                f.save(fi);
            }
        }
        else{
            throw new IllegalArgumentException("Either invalid integer detected or invalid operation.");
        }
    }
    
    public void setApplyingStatus(boolean a) throws IOException{
        f.set("player.isApplicant", a);
        f.save(fi);
    }
    
    public void setStaffStatus(boolean a) throws IOException{
        f.set("player.isStaff", a);
        f.save(fi);
    }
    
    public void setBlockedStatus(boolean a) throws IOException{
        f.set("player.isBlocked", a);
        f.save(fi);
    }
    
    public void setAlertStatus(int a, String s, boolean b) throws IOException{
        boolean valid = a == 10 && (s.equals("up") || s.equals("down")) ? true : false;
        if(valid){
            String c = "alert." + a + "." + s;
            f.set(c, b);
            f.save(fi);
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    
    public void setDateJoined(String a) throws IOException{
        f.set("player.dateJoined", a);
        f.save(fi);
    }
    
    public void setOnAlertStatus(boolean a) throws IOException{
        f.set("player.isOnVoteAlertOn", a);
        f.save(fi);
    }
}