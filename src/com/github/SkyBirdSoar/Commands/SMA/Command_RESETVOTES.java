package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Command_RESETVOTES {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_RESETVOTES(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void resetVotes(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 2){
            if(args[1].equalsIgnoreCase("all")){
                File file = sm.getFolder("players");
                File[] list = file.listFiles();
                for(int a = 0; a < list.length; a++){
                    String temp = list[a].getName();
                    String fileName = temp.split("\\.")[0];
                    FileConfiguration fc = sm.getConfig(fileName, true);
                    fc.set("player.voteUp.number", 0);
                    fc.set("player.voteUp.list", "");
                    fc.set("player.voteDown.number", 0);
                    fc.set("player.voteDown.list", "");
                    fc.set("alert.10.up", true);
                    fc.set("alert.10.down", true);
                    sm.saveConfig(fileName, fc, true);
                }
                sm.broadcast("&cAll votes have been reset!");
                ch.sendMessage(sender, "&aResetted votes for everyone.");
            }
            else{
                String IGN = args[1];
                File file = sm.getFile(IGN, true);
                if(!file.exists()){
                    ch.sendMessage(sender, "&cERROR: Player not found.");
                }
                else{
                    FileConfiguration fc = sm.getConfig(IGN, true);
                    fc.set("player.voteUp.number", 0);
                    fc.set("player.voteUp.list", "");
                    fc.set("player.voteDown.number", 0);
                    fc.set("player.voteDown.list", "");
                    fc.set("alert.10.up", true);
                    fc.set("alert.10.down", true);
                    sm.saveConfig(IGN, fc, true);
                    ch.sendMessage(sender, "&aResetted votes for that player.");
                }
            }
        }
        else{
            ch.sendMessage(sender, "&c/sma resetvotes <IGN|all>");
        }
    }
}
