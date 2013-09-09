package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Command_HELP {
    private StaffManager sm;
    private Command_SM csm;
    public Command_HELP(StaffManager pl, Command_SM pl2){
        sm = pl;
        csm = pl2;
    }
    public void help(CommandSender sender, Command cmd, String label, String[] args){
        if(sender.hasPermission("sm.help")){
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
        String[] commands = csm.commands;
        String prefix = "&a - ";
        String notAvailablePrefix = "&c - &m";
        commands[4] = prefix + commands[4] + "&b: Shows the default help for StaffManager.";
        commands[5] = notAvailablePrefix + commands[5] + "&b: Tells people you are applying for staff.";
        commands[6] = prefix + commands[6] + "&b: Votes for a particular person.";
        commands[7] = prefix + commands[7] + "&b: Shows the amount of votes and the status of the IGN's application.";
        commands[8] = prefix + commands[8] + "&b: Shows the names of current applicants.";
        commands[9] = prefix + commands[9] + "&b: Shows the current online staff or the list of current staff. Do /sm staff <pageNo.|all> to view the staff list.";
        commands[10] = prefix + commands[10] + "&b: Shows the applicant with the top amount of votes";
        sender.sendMessage(sm.parseColor("&aThe commands available are: "));
        for(int a = 4; a < commands.length; a++){
            sender.sendMessage(sm.parseColor(commands[a]));
        }
        sender.sendMessage(sm.parseColor("&f&lNOTE: Those in &cRED&f&l are not available."));
    }
}
