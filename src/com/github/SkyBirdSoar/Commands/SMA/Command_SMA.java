package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.ERROR;
import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Command_SMA {
    private StaffManager sm;
    private CommandHandler ch;
    String[] commands = {"help", "addstaff", "demote", "broadcast", "resetvotes", "app", "purge", "ban", "unban", "banlist", "version"};
    String[] commandAliases = {"bc", "remove", "delete", "del", "rm"};
    public Command_SMA(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void commandSMA(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 0){
            if(sender.hasPermission("sma.help")){
                ch.sendMessage(sender, "&cType /sma help for help.");
            }
            else{
                ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
            }
        }
        if(args.length > 0){
            boolean correctCommand = false;
            for(int a = 0; a < commands.length; a++){
                if(args[0].equals(commands[a])){
                    correctCommand = true;
                }
            }
            for(int a = 0; a < commandAliases.length; a++){
                if(args[0].equals(commandAliases[a])){
                    correctCommand = true;
                }
            }
            if(correctCommand){
                switch(args[0]){
                    case "bc":
                    case "broadcast":
                        if(sender.hasPermission("sma.broadcast")){
                            String message = "";
                            for(int a = 1; a < args.length; a++){
                                message += args[a] + " ";
                                message = sm.parseColor(message);
                            }
                            if(!message.equals("")){
                                sm.broadcast(" " + message);
                            }
                            else{
                                ch.sendMessage(sender, "&c&lERROR:&c You cannot broadcast empty strings.");
                            }
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "version":
                        if(sender.hasPermission("sma.version")){
                            ch.sendMessage(sender, sm.getPluginName(true));
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "help":
                        if(sender.hasPermission("sma.help")){
                            Command_HELP cmdh = new Command_HELP(sm, this);
                            cmdh.help(sender, cmd, label, args);
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "addstaff":
                        if(sender.hasPermission("sma.addstaff")){
                            Command_ADDSTAFF cmdas = new Command_ADDSTAFF(sm, ch);
                            cmdas.addstaff(sender, cmd, label, args);
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "remove":
                    case "delete":
                    case "rm":
                    case "del":
                    case "demote":
                        if(sender.hasPermission("sma.demote")){
                            Command_DEMOTE cmdd = new Command_DEMOTE(sm, ch);
                            cmdd.demote(sender, cmd, label, args);
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "purge":
                        if(sender.hasPermission("sma.purge")){
                            Command_PURGE cmdp = new Command_PURGE(sm, ch);
                            cmdp.purge(sender, cmd, label, args);
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "resetvotes":
                        if(sender.hasPermission("sma.resetvotes")){
                            Command_RESETVOTES cmdrv = new Command_RESETVOTES(sm, ch);
                            cmdrv.resetVotes(sender, cmd, label, args);
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "app":
                        if(sender.hasPermission("sma.app")){
                            Command_APP cmda = new Command_APP(sm, ch);
                            cmda.app(sender, cmd, label, args);
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "ban":
                        if(sender.hasPermission("sma.ban")){
                            Command_BAN cmdb = new Command_BAN(sm, ch);
                            cmdb.ban(sender, cmd, label, args);
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "unban":
                        if(sender.hasPermission("sma.unban")){
                            Command_UNBAN cmdub = new Command_UNBAN(sm, ch);
                            cmdub.unban(sender, cmd, label, args);
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    case "banlist":
                        if(sender.hasPermission("sma.banlist")){
                            Command_BANLIST cmdbl = new Command_BANLIST(sm, ch);
                            cmdbl.list(sender, cmd, label, args);
                        }
                        else{
                            ch.sendMessage(sender, ERROR.PERMISSION_MESSAGE);
                        }
                        break;
                    default:
                        ch.sendMessage(sender, "&cSorry, the command you asked for is not implemented yet.");
                        break;
                }
            }
            if(!correctCommand){
                ch.sendMessage(sender, "&cUnknown argument: &d" + args[0]);
                ch.sendMessage(sender, "&cPlease check &b/sma help &cfor help.");
            }
        }
    }
}
