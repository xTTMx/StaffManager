package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Command_HELP {
    private StaffManager sm;
    private Command_SMA csma;
    public Command_HELP(StaffManager pl, Command_SMA pl2){
        sm = pl;
        csma = pl2;
    }
    public void help(CommandSender sender, Command cmd, String label, String[] args){
        if(sender.hasPermission("sma.help")){
            if(args.length == 1){
                addDescription(sender);
            }
            if(args.length > 1){
                sender.sendMessage(sm.parseColor("&cToo many arguments!"));
                sender.sendMessage(sm.parseColor("&c/sm help [page no]"));
            }
        }
        else{
            sender.sendMessage(sm.parseColor("&cYou do not have permission to do so."));
        }
    }
    private void addDescription(CommandSender sender){
        String[] commands = csma.commands;
        String prefix = "&a - ";
        //String notAvailablePrefix = "&c - &m";
        commands[0] = prefix + commands[0] + "&b: Shows the default help for StaffManager.";
        commands[1] = prefix + commands[1] + "&b: Adds a player to the staff list.";
        commands[2] = prefix + commands[2] + "&b: Removes a player from the staff list.";
        commands[3] = prefix + commands[3] + "&b: Broadcasts a message with the server name as a prefix.";
        commands[4] = prefix + commands[4] + "&b: Resets a player's votes.";
        commands[5] = prefix + commands[5] + "&b: Application Manager, /sma app <IGN> <accept|deny>";
        commands[6] = prefix + commands[6] + "&b: Deletes data.";
        commands[7] = prefix + commands[7] + "&b: Prevents a player from applying.";
        commands[8] = prefix + commands[8] + "&b: Unbans a player.";
        commands[9] = prefix + commands[9] + "&b: Shows the list of players which are banned";
        commands[10] = prefix + commands[10] + "&b: Shows the version of StaffManager.";
        sender.sendMessage(sm.parseColor("&aThe commands available are: "));
        for(int a = 0; a < commands.length; a++){
            sender.sendMessage(sm.parseColor(commands[a]));
        }
    }
}
