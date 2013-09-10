/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.SkyBirdSoar.Main;

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
    public final String ERROR_PLAYER_NOT_FOUND = "&c&lERROR:&c Player not found.";
    public final String ERROR_PLAYER_ALREADY_BANNED = "&c&lERROR:&c Player is already banned";
    public final String ERROR_PLAYER_NOT_BANNED = "&c&lERROR:&c Player was not banned.";
    public final String ERROR_LIST_NOT_CHANGED = "&c&lERROR:&c An error occurred while processing your request.";
    public final String ERROR_PLAYER_ALREADY_VOTED = "&c&lERROR:&c You have already voted for this player.";
    public final String ERROR_UNKNOWN_ARGUMENT = "&c&lERROR:&c Unknown argument: ";
    public final String ERROR_PLAYER_ALREADY_APPLIED = "&c&lERROR:&c You have already applied. To check the status of your application, do /sm status";
    public final String ERROR_PLAYER_IS_BANNED = "&c&lERROR:&c You cannot apply as you are banned.";
    public final String ERROR_APPLY_NOT_ALLOWED = "&c&lERROR:&c You cannot apply at this point of time.";
    public final String ERROR_PLAYER_NOT_APPLYING = "&c&lERROR:&c Player did not apply, hence, vote is not allowed";
    public final String ERROR_PLAYER_DOES_NOT_HAVE_PERMISSION_TO_MANAGE_GROUPS = "&c&lERROR:&c You do not have permission to manage groups.";
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
