package com.SkyBirdSoar.StaffManager.Main;

import com.SkyBirdSoar.StaffManager.Util.SMPlugin;
import java.io.File;
import java.io.InputStream;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

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

public class StaffManager extends JavaPlugin implements SMPlugin{

    private File FileOfConfig;
    private FileConfiguration FileConfig;
    public FileConfiguration Config;
    private CmdHandler ch;
    
    @Override
    public void onEnable(){
        //Saves config.yml into memory at startup.
        this.reloadConfig(this.getConfigFile());
        CmdHandler ch = new CmdHandler(this);
        this.getCommand("sm").setExecutor(ch);
        //this.getCommand("sma").setExecutor(ch);
        this.setCH(ch);
    }
    
    @Override
    public void onDisable(){
        
    }
    
    @Override
    public void reloadConfig(File config) {
        if(!getConfigFile().exists()){
            InputStream defConfigStream = this.getResource("config.yml");
            if (defConfigStream != null) {
                this.saveResource("config.yml", false);
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            }
        }
        FileOfConfig = config;
        FileConfig = YamlConfiguration.loadConfiguration(FileOfConfig);
    }

    @Override
    public FileConfiguration getConfig(File config) {
        this.reloadConfig(config);
        return FileConfig;
    }

    @Override
    public SMPlugin getInstance() {
        return this;
    }

    @Override
    public void consoleOut(String message) {
        this.getServer().getConsoleSender().sendMessage("[StaffManager] " + this.parse(message));
    }
    
    @Override
    public File getPluginFolder(){
        return getDataFolder();
    }
    
    private File getConfigFile(){
        File f = new File(getDataFolder(), "config.yml");
        return f;
    }
    
    public String parse(String message){
        String[] codes = {"a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "l", "m", "n", "o", "r", "k"};
        for(int b = 0; b < codes.length; b++){
            message = message.replace("&"+ codes[b], "§" + codes[b]);
        }
        return message;
    }
    
    public File getPlayerFolder(){
        return new File(getPluginFolder(), "players");
    }
    
    private void setCH(CmdHandler c){
        ch = c;
    }
    
    public CmdHandler getCH(){
        return ch;
    }
}
