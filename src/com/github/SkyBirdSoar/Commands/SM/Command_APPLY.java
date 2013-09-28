package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.Conversations.Main;
import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.ERROR;
import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Command_APPLY {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_APPLY(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void apply(CommandSender sender, Command cmd, String label, String[] args){
        FileConfiguration fc = sm.getConfig(sender.getName(), true);
        if(fc.getBoolean("player.app")){
            ch.sendMessage(sender, ERROR.ERROR_PLAYER_ALREADY_APPLIED);
        }
        else{
            if(fc.getBoolean("player.banned")){
                ch.sendMessage(sender, ERROR.ERROR_PLAYER_IS_BANNED);
            }
            else{
                FileConfiguration dc = sm.getConfig("config", false);
                if(!dc.getBoolean("applyOn")){
                    ch.sendMessage(sender, ERROR.ERROR_APPLY_NOT_ALLOWED);
                }
                else{
                    fc.set("player.app", true);
                    sm.saveConfig(sender.getName(), fc, true);
                    Main m = new Main(sm);
                    if(sender instanceof Player){
                        m.converse((Player) sender);
                    }
                    else{
                        m.converse((ConsoleCommandSender) sender);
                    }
                    sm.broadcast("&aPlayer &b" + sender.getName() + " &ahas applied for staff!");
                    ch.sendMessage(sender, "&aTo check your application status, do /sm status.");
                    ch.sendMessage(sender, "&aTo find out other StaffManager commands you can do, do /sm help.");
                    ch.sendMessage(sender, "&aTo view the help the owner has set, do /sm [page]");
                }
            }
        }
    }
}
