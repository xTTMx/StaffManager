/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.SkyBirdSoar.StaffManager;

import com.github.SkyBirdSoar.Commands.SM.Command_SM;
import com.github.SkyBirdSoar.Commands.SMA.Command_SMA;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author skybirdsoar
 */
public class CommandHandler implements CommandExecutor{
    private StaffManager sm;
    public final String PERMISSION_MESSAGE = "&cSorry, you do not have permission to perform this command.";
    CommandHandler(StaffManager pl){
        sm = pl;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("sm")){
            Command_SM csm = new Command_SM(sm, this);
            csm.commandSM(sender, cmd, label, args);
        }
        if(cmd.getName().equals("sma")){
            Command_SMA csma = new Command_SMA(sm, this);
            csma.commandSMA(sender, cmd, label, args);
        }
        return true;
    }
    public void sendMessage(CommandSender sender, String message){
        sender.sendMessage(sm.parseColor(message));
    }
}
