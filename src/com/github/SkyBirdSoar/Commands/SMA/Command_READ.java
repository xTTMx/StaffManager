package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Command_READ{
    private StaffManager sm;
    private CommandHandler ch;
    public Command_READ(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void read(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 1){
            ch.sendMessage(sender, sm.parseColor("&cNot enough arguments!"));
            ch.sendMessage(sender, sm.parseColor("&c/sma read <IGN>"));
        }
    }
}