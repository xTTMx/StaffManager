/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.SkyBirdSoar.StaffManager;

import com.github.SkyBirdSoar.Commands.SM.Command_SM;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author skybirdsoar
 */
public class CommandHandler implements CommandExecutor{
    private StaffManager sm;
    CommandHandler(StaffManager pl){
        sm = pl;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("sm")){
            Command_SM csm = new Command_SM(sm);
            boolean successful = csm.commandSM(sender, cmd, label, args);
            return successful;
        }
        if(cmd.getName().equals("sma")){
            if(args.length == 0){
                sender.sendMessage(sm.parseColor("&cSorry, the command you asked for is not implemented yet."));
            }
            if(args.length > 0){
                boolean correctCommand = false;
                String[] commands = {"help", "addstaff", "demote", "broadcast", "close", "resetvotes", "app", "purge", "ban", "unban", "banlist"};
                for(int a = 0; a < commands.length; a++){
                    if(args[0].equals(commands[a])){
                        correctCommand = true;
                    }
                }
                if(correctCommand){
                    switch(args[0]){
                        default:
                            sender.sendMessage(sm.parseColor("&cSorry, the command you asked for is not implemented yet."));
                            break;
                    }
                }
                if(!correctCommand){
                    sender.sendMessage(sm.parseColor("&cUnknown argument: &d" + args[0]));
                    sender.sendMessage(sm.parseColor("&cPlease check &b/sm help &cfor help."));
                }
            }
        }
        return true;
    }
}
