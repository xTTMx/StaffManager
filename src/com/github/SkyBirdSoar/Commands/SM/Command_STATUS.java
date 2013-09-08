/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.StaffManager.CommandHandler;
import com.github.SkyBirdSoar.StaffManager.StaffManager;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author skybirdsoar
 */
public class Command_STATUS {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_STATUS(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void status(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 1){
            ch.sendMessage(sender, "&cNot enough arguments!");
            ch.sendMessage(sender, "&c/sm status <IGN>");
        }
        if(args.length > 1){
            String IGN = args[1];
            File file = sm.getFile(IGN, true);
            if(!file.exists()){
                ch.sendMessage(sender, "&cERROR: Player not found.");
            }
            if(file.exists()){
                FileConfiguration fc = sm.getConfig(IGN, true);
                ch.sendMessage(sender, "&c=== Player Info ===");
                String[] toGive = {"player.app", "player.banned", "player.dateJoined", "player.voteUp.number", "player.voteDown.number"};
                String[] head = {"Applicant: ", "Prevented from applying: ", "Join Date: ", "Number of votes: ", "Number of downvotes: "};
                for(int a = 0; a < 2; a++){
                    ch.sendMessage(sender, "&c" + head[a] + fc.getBoolean(toGive[a]));
                }
                ch.sendMessage(sender, "&c" + head[2] + fc.getString(toGive[2]));
                for(int a = 3; a < 5; a++){
                    ch.sendMessage(sender, "&c" + head[a] + fc.getInt(toGive[a]));
                }
            }
        }
    }
}
