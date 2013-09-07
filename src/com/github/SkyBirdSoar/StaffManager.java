package com.github.SkyBirdSoar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class StaffManager extends JavaPlugin{

    FileConfiguration pFileConfig;
    File pConfig;
    public static final String EXT = ".yml";
    public String SERVER_NAME;
    public static final String VERSION = "REWRITE";
    
    @Override
    public void onEnable(){
        //Add CommandHandlers here
        getCommand("sm").setExecutor(new CommandHandler(this));
        SERVER_NAME = this.getConfig("config", false).getString("serverName");
        SERVER_NAME = parseColor(SERVER_NAME);
        this.checkConfig();
        generateHelp();
    }
    @Override
    public void onDisable(){
        
    }
    
    /**
     * Searches for a config and loads it as pFileConfig.
     *
     * @param configName The name of the configuration file you want without the extension.
     * @param player If true, checks ~/StaffManager/players. If false, checks ~/StaffManager for the configuration file.
     * 
     */
    public void reloadConfig(String configName, boolean player) {   
        pConfig = new File(getDataFolder(), configName + EXT);
        pFileConfig = YamlConfiguration.loadConfiguration(pConfig);
        if(player){
            pConfig = new File(getDataFolder(), "players");
            if(!pConfig.isDirectory()){
                pConfig.mkdir();
            }
            pConfig = new File(pConfig, configName + EXT);
            pFileConfig = YamlConfiguration.loadConfiguration(pConfig);
            if(!pConfig.exists()){
                defaultPlayerConfig(configName, pFileConfig);
            }
        }
        // Look for defaults in the jar
        
        if (configName.equals("config") && !(pConfig.exists()) && player == false){
            InputStream defConfigStream = this.getResource(configName + EXT);
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                pFileConfig.setDefaults(defConfig);
                this.saveResource(configName + EXT, false);
            }
        }
    }
    /**
     * Calls reloadConfig to set pFileConfig and then returns pFileConfig,
     * @param configName A parameter to pass on to reloadConfig
     * @param player A parameter to pass on to reloadConfig
     * @return The specified FileConfiguration object with the name configName.yml
     */
    public FileConfiguration getConfig(String configName, boolean player) {
        reloadConfig(configName, player);
        return pFileConfig;
    }
    /**
     * Saves the configuration
     * @param configName Name of the configuration (Without the extension)
     * @param fc The FileConfiguration you used to set the keys and values.
     * @param player Checks if you want to save a player config(true) or a plugin config(false).
     */
    public void saveConfig(String configName, FileConfiguration fc, boolean player) {
        try {
            if(player){
                pConfig = new File(getDataFolder(), "players");
                pConfig = new File(pConfig, configName + EXT);
            }
            if(!player){
                pConfig = new File(getDataFolder(), configName + EXT);
            }
            fc.save(pConfig);
        } 
        catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Could not save config to " + pConfig.getName(), ex);
        }
    }
    /**
     * Generates the default player configuration for IGN.
     * @param IGN The IGN of the player.
     */
    public void defaultPlayerConfig(String IGN, FileConfiguration fc){
        fc.set("player.app", false);
        fc.set("player.voteUps", 0);
        fc.set("player.voteDowns", 0);
        fc.set("player.banned", false);
        fc.set("voters.up", "");
        fc.set("voters.down", "");
        fc.set("deletable", true);
        this.saveConfig(IGN, fc, true);
    }
    /**
     * Checks config.yml for invalid data and outputs valid data to the server administrator.
     */
    public void checkConfig(){
        File config = new File(getDataFolder(), "config" + EXT);
        if (!(config.exists())){
            this.reloadConfig("config", false);
            getLogger().log(Level.INFO, "No config found, generating one for you...");
        }
        if (config.exists()){
            FileConfiguration f = getConfig("config", false);
            log(Level.INFO, "Reading data...");
            if (f.getBoolean("applyOn")==true){
                log(Level.INFO, "Recruitment for staff is OPEN. To change this, please adjust the applyOn value of config.yml");
            }
            if(f.getBoolean("applyOn")==false){
                log(Level.INFO, "Recruitment for staff is CLOSED. To change this, please adjust the applyOn value of config.yml");
            }
            log(Level.INFO, "The server name set in the config is: " + SERVER_NAME);
            if((f.getBoolean("applyOn") != true && f.getBoolean("applyOn") != false) || f.getInt("pagesNeeded") < 1 && f.getInt("pagesNeeded") > 6){
                log(Level.SEVERE, "Invalid config.yml, regenerating...");
                Boolean delete = config.delete();
                if (delete == false){
                    log(Level.SEVERE, "Unable to delete config.yml");
                }
                this.reloadConfig("config", false);
            }
        }
    }
    public void generateHelp(){
        File file = new File(getDataFolder(), "README.txt");
        if(!file.exists()){
            InputStream defConfigStream = this.getResource("README.txt");
            if (defConfigStream != null){
                this.saveResource("README.txt", false);
            }
        }
    }
    public String parseColor(String a){
        String[] codes = {"a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for(int b = 0; b < a.length(); b++){
            a = a.replace("&"+ codes[b], "§" + codes[b]);
        }
        return a;
    }
    public void log(Level a, String b){
        getLogger().log(a, b);        
    }
    public void broadcast(String message){
        message = parseColor(message);
        this.getServer().broadcastMessage(SERVER_NAME + message);
    }
}