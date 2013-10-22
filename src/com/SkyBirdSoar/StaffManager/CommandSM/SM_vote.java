package com.SkyBirdSoar.StaffManager.CommandSM;

import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import com.SkyBirdSoar.StaffManager.Util.PlayerConfig;
import com.SkyBirdSoar.StaffManager.Util.AbstractSMCommand;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SM_vote extends AbstractSMCommand{

    private StaffManager m;
    private SM_main sm;
    
    public SM_vote(StaffManager m, SM_main sm){
        super("Votes for a player", "/sm vote");
        this.m = m;
        this.sm = sm;
    }
    @Override
    public void execute(CommandSender sender, Command cmd, String[] args) {
        if(args[1].equals("--alertOff")){
            try {
                PlayerConfig pc = new PlayerConfig(m, (Player) sender);
                pc.setOnAlertStatus(false);
                m.getCH().out(sender, "&aSuccessfully disabled alerts when people votes for you.");
            } catch (IOException ex) {
                m.consoleOut("&cFailed to save player config: " + ex);
                m.getLogger().log(Level.FINE, null, ex);
                m.getCH().out(sender, ERROR.ERROR_UNABLE_TO_SAVE_CONFIG);
            }
        }
        if(args[1].equals("--alertOn")){
            try{
                PlayerConfig pc = new PlayerConfig(m, (Player) sender);
                pc.setOnAlertStatus(true);
            }
            catch(IOException ex){
                m.consoleOut("&cFailed to save player config: " + ex);
                m.getLogger().log(Level.FINE, null, ex);
                m.getCH().out(sender, ERROR.ERROR_UNABLE_TO_SAVE_CONFIG);
            }
        }
        if(args[1].equals("up")){
            
        }
    }

    @Override
    public String usage() {
        return "/sm vote <up|down> <player> OR /sm vote --alertOff|--alertOn";
    }

    @Override
    public void checkPerm(CommandSender sender, Command cmd, String[] args) {
        if(sender.hasPermission("staffmanager.sm.vote")){
            execute(sender, cmd, args);
        }
        else{
            m.getCH().out(sender, ERROR.PERMISSION_MESSAGE);
        }
    }

}
