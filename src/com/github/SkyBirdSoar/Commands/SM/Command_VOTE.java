package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.File;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Command_VOTE {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_VOTE(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void vote(CommandSender sender, Command cmd, String label, String[] args){
        File file = sm.getFile(args[1], true);
        if(file.exists()){
            FileConfiguration fc = sm.getConfig(args[0], true);
            List<String> list = fc.getStringList("player.voteUp.list");
            List<String> listd = fc.getStringList("player.voteDown.list");
            List<String> lista = list;
            boolean changed = lista.addAll(listd);
            if(changed){
                boolean hasVoted = list.contains(sender.getName());
                if(hasVoted){
                    ch.sendMessage(sender, ch.ERROR_PLAYER_ALREADY_VOTED);
                }
                else{
                    if(args.length == 2){
                        this.voteUp(sender, args, fc, list);
                    }
                    if(args.length == 3){
                        if(args[2].equalsIgnoreCase("up")){
                            this.voteUp(sender, args, fc, list);
                        }
                        else if(args[2].equalsIgnoreCase("down")){
                            this.voteDown(sender, args, fc, listd);
                        }
                        else{
                            ch.sendMessage(sender, ch.ERROR_UNKNOWN_ARGUMENT + args[2]);
                            ch.sendMessage(sender, "&c/sm vote <IGN> <up|down>");
                        }
                    }
                }
            }
            else{
                ch.sendMessage(sender, ch.ERROR_LIST_NOT_CHANGED);
            }
        }
        else{
            ch.sendMessage(sender, ch.ERROR_PLAYER_NOT_FOUND);
        }
    }
    private void voteUp(CommandSender sender, String[] args, FileConfiguration fc, List<String> list){
        list.add(sender.getName());
        fc.set("player.voteUp.list", list);
        int votes = fc.getInt("player.voteUp.number");
        votes += 1;
        fc.set("player.voteUp.number", votes);
        sm.saveConfig(args[1], fc, true);
    }
    private void voteDown(CommandSender sender, String[] args, FileConfiguration fc, List<String> list){
        list.add(sender.getName());
        fc.set("player.voteDown.list", list);
        int votes = fc.getInt("player.voteDown.number");
        votes += 1;
        fc.set("player.voteDown.number", votes);
        sm.saveConfig(args[1], fc, true);
    }
}
