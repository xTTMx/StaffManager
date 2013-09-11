package com.github.SkyBirdSoar.Util;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.File;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;

public class configconverter {
    private StaffManager sm;
    public configconverter(StaffManager pl){
        sm = pl;
    }
    private String getConfigVersion(){
        FileConfiguration fc = sm.getConfig("config", false);
        String getVersion = fc.getString("version");
        return getVersion;
    }
    public void convertConfig(String pluginVersion,  boolean change){
        if(getConfigVersion().equals(pluginVersion)){
            sm.getLogger().log(Level.INFO, "config.yml is good to use for this version!");
        }
        else if(change){
            sm.getLogger().log(Level.WARNING, "Current config.yml is outdated with current plugin!");
            sm.getLogger().log(Level.INFO, "Please delete config.yml and call a reload and then re-edit every value!");
            sm.getLogger().log(Level.INFO, "Updating readme.txt...");
            deleteReadMe();
            sm.generateHelp();
        }
        else{
            sm.getLogger().log(Level.INFO, "config.yml is good to use for this version!");
        }
    }
    private void deleteReadMe(){
        File file = sm.getFile("README", false, "txt");
        file.delete();
    }
}
