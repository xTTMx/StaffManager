package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.Exceptions.UnknownPermissionsManagerException;
import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.ERROR;
import com.github.SkyBirdSoar.Main.SMEventHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Command_APP {
    private StaffManager sm;
    private CommandHandler ch;
    public Command_APP(StaffManager pl, CommandHandler pl2){
        sm = pl;
        ch = pl2;
    }
    public void app(CommandSender sender, Command command, String label, String[] args){
        if(args.length > 2){
            File file = sm.getFile(args[1], true);
            if(file.exists()){
                if(args[2].equalsIgnoreCase("accept")){
                    if(args.length == 3){
                        ch.sendMessage(sender, "&cField [Rank] is mandatory for operation accept.");
                        ch.sendMessage(sender, "&c/sma app " + args[1] + " accept " + "<Rank>");
                    }
                    else{
                        FileConfiguration fc = sm.getConfig("config", false);
                        if(!fc.getString("permMan").equals("PermissionsEx") && !fc.getString("permMan").equals("GroupManager") && !fc.getString("permMan").equals("None")){
                            ch.sendMessage(sender, "&cAn error occurred: UnknownPermissionsManagerException");
                            ch.sendMessage(sender, "&cPlease edit StaffManager/config.yml and change it to a recognised PermissionsManager.");
                            throw new UnknownPermissionsManagerException(fc.getString("permMan"));
                        }
                        else if(fc.getString("permMan").equals("PermissionsEx")){
                            if(sender.hasPermission("permissions.user.membership.*")){
                                FileConfiguration dc = sm.getConfig(args[1], true);
                                if(dc.getBoolean("player.app")){
                                    resetConfig(args[1], dc);
                                    sm.getServer().dispatchCommand(sender, "pex user " + args[1] + " group set " + args[3]);
                                    sm.broadcast("&aCongratulations " + args[1] + ", you have been promoted to: " + args[3]);
                                    try {
                                        Scanner scan = new Scanner(new File(sm.getFolder("data"), "staff.txt"));
                                        List<String> list = new ArrayList<>();
                                        int lines = 0;
                                        while(scan.hasNextLine()){
                                            list.add(scan.nextLine());
                                            lines++;
                                        }
                                        Scanner scan2 = new Scanner(new File(sm.getFolder("data"), "applicants.txt"));
                                        List<String> list2 = new ArrayList<>();
                                        int lines2 = 0;
                                        while(scan2.hasNextLine()){
                                            list2.add(scan2.nextLine());
                                            lines2++;
                                        }
                                        String[] alrThr = new String[lines];
                                        String[] alrThr2 = new String[lines2];
                                        alrThr = list.toArray(alrThr);
                                        alrThr2 = list2.toArray(alrThr2);
                                        boolean check = false;
                                        PrintWriter pw = new PrintWriter(new FileWriter(new File(sm.getFolder("data"), "staff.txt")));
                                        for(int a = 0; a < alrThr.length; a++){
                                            pw.println(alrThr[a]);
                                            if(alrThr[a].equals(args[1])){
                                                check = true;
                                            }
                                        }
                                        if(!check){
                                            pw.println(args[1]);
                                        }
                                        pw.close();
                                        PrintWriter pw2 = new PrintWriter(new FileWriter(new File(sm.getFolder("data"), "applicants.txt")));
                                        for(int a = 0; a < alrThr2.length; a++){
                                            if(!args[1].equals(alrThr2[a])){
                                                pw2.println(alrThr2[a]);
                                            }
                                        }
                                        pw2.close();
                                        ch.sendMessage(sender, "&cSuccess!");
                                        sm.getServer().dispatchCommand(sender, "email send " + args[1] + " Congratulations! Your application has been approved!");
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(Command_APP.class.getName()).log(Level.SEVERE, null, ex);
                                        ch.sendMessage(sender, "&cAn error occurred.");
                                    } catch (IOException ex) {
                                        Logger.getLogger(Command_APP.class.getName()).log(Level.SEVERE, null, ex);
                                        ch.sendMessage(sender, "&cAn error occurred.");
                                    }
                                }
                                else{
                                    ch.sendMessage(sender, "&cPlayer is not applying for staff.");
                                }
                            }
                            else{
                                ch.sendMessage(sender, ERROR.ERROR_PLAYER_DOES_NOT_HAVE_PERMISSION_TO_MANAGE_GROUPS);
                            }
                        }
                        else if(fc.getString("permMan").equals("GroupManager")){
                            if(sender.hasPermission("groupmanager.manuadd")){
                                FileConfiguration dc = sm.getConfig(args[1], true);
                                if(dc.getBoolean("player.app")){
                                    resetConfig(args[1], dc);
                                    sm.getServer().dispatchCommand(sender, "manuadd " + args[1] + " " + args[3]);
                                    sm.broadcast("&aCongratulations " + args[1] + ", you have been promoted to: " + args[3]);
                                    try {
                                        Scanner scan = new Scanner(new File(sm.getFolder("data"), "staff.txt"));
                                        List<String> list = new ArrayList<>();
                                        int lines = 0;
                                        while(scan.hasNextLine()){
                                            list.add(scan.nextLine());
                                            lines++;
                                        }
                                        Scanner scan2 = new Scanner(new File(sm.getFolder("data"), "applicants.txt"));
                                        List<String> list2 = new ArrayList<>();
                                        int lines2 = 0;
                                        while(scan2.hasNextLine()){
                                            list2.add(scan2.nextLine());
                                            lines2++;
                                        }
                                        String[] alrThr = new String[lines];
                                        String[] alrThr2 = new String[lines2];
                                        alrThr = list.toArray(alrThr);
                                        alrThr2 = list2.toArray(alrThr2);
                                        boolean check = false;
                                        PrintWriter pw = new PrintWriter(new FileWriter(new File(sm.getFolder("data"), "staff.txt")));
                                        for(int a = 0; a < alrThr.length; a++){
                                            pw.println(alrThr[a]);
                                            if(alrThr[a].equals(args[1])){
                                                check = true;
                                            }
                                        }
                                        if(!check){
                                            pw.println(args[1]);
                                        }
                                        pw.close();
                                        PrintWriter pw2 = new PrintWriter(new FileWriter(new File(sm.getFolder("data"), "applicants.txt")));
                                        for(int a = 0; a < alrThr2.length; a++){
                                            if(!args[1].equals(alrThr2[a])){
                                                pw2.println(alrThr2[a]);
                                            }
                                        }
                                        pw2.close();
                                        ch.sendMessage(sender, "&cSuccess!");
                                        sm.getServer().dispatchCommand(sender, "email send " + args[1] + " Congratulations! Your application has been approved!");
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(Command_APP.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Command_APP.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                else{
                                    ch.sendMessage(sender, "&cPlayer is not applying for staff.");
                                }
                            }
                        }
                        else if(fc.getString("permMan").equals("None")){
                            if(sender.hasPermission("sma.app")){
                                FileConfiguration dc = sm.getConfig(args[1], true);
                                if(dc.getBoolean("player.app")){
                                    resetConfig(args[1], dc);
                                    sm.broadcast("&aCongratulations " + args[1] + ", you have been promoted to: " + args[3]);
                                    try {
                                        Scanner scan = new Scanner(new File(sm.getFolder("data"), "staff.txt"));
                                        List<String> list = new ArrayList<>();
                                        int lines = 0;
                                        while(scan.hasNextLine()){
                                            list.add(scan.nextLine());
                                            lines++;
                                        }
                                        Scanner scan2 = new Scanner(new File(sm.getFolder("data"), "applicants.txt"));
                                        List<String> list2 = new ArrayList<>();
                                        int lines2 = 0;
                                        while(scan2.hasNextLine()){
                                            list2.add(scan2.nextLine());
                                            lines2++;
                                        }
                                        String[] alrThr = new String[lines];
                                        String[] alrThr2 = new String[lines2];
                                        alrThr = list.toArray(alrThr);
                                        alrThr2 = list2.toArray(alrThr2);
                                        boolean check = false;
                                        PrintWriter pw = new PrintWriter(new FileWriter(new File(sm.getFolder("data"), "staff.txt")));
                                        for(int a = 0; a < alrThr.length; a++){
                                            pw.println(alrThr[a]);
                                            if(alrThr[a].equals(args[1])){
                                                check = true;
                                            }
                                        }
                                        if(!check){
                                            pw.println(args[1]);
                                        }
                                        pw.close();
                                        PrintWriter pw2 = new PrintWriter(new FileWriter(new File(sm.getFolder("data"), "applicants.txt")));
                                        for(int a = 0; a < alrThr2.length; a++){
                                            if(!args[1].equals(alrThr2[a])){
                                                pw2.println(alrThr2[a]);
                                            }
                                        }
                                        pw2.close();
                                        ch.sendMessage(sender, "&cSuccess!");
                                        sm.getServer().dispatchCommand(sender, "email send " + args[1] + " Congratulations! Your application has been approved!");
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(Command_APP.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Command_APP.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                else{
                                    ch.sendMessage(sender, "&cPlayer is not applying for staff.");
                                }
                            }
                            else{
                                ch.sendMessage(sender, ERROR.ERROR_PLAYER_DOES_NOT_HAVE_PERMISSION_TO_MANAGE_GROUPS);
                            }
                        }
                    }
                }
                else if(args[2].equalsIgnoreCase("deny")){
                    if(args.length > 3){
                        ch.sendMessage(sender, "&cToo many arguments!");
                        ch.sendMessage(sender, "&c/sma app <IGN> deny");
                    }
                    else{
                        FileConfiguration dc = sm.getConfig(args[1], true);
                        if(dc.getBoolean("player.app")){
                            PrintWriter pw = null;
                            try {
                                pw = new PrintWriter(new FileWriter(sm.getFile(args[1], true)));
                                resetConfig(args[1], dc);
                                sm.getServer().dispatchCommand(sender, "email send " + args[1] + " Sorry, your application has been denied.");
                                Scanner scan = new Scanner(new File(sm.getFolder("data"), "applicants.txt"));
                                List<String> list = new ArrayList<>();
                                int lines = 0;
                                while(scan.hasNextLine()){
                                    list.add(scan.nextLine());
                                    lines++;
                                }
                                String[] alrThr = new String[lines];
                                alrThr = list.toArray(alrThr);
                                pw = new PrintWriter(new FileWriter(new File(sm.getFolder("data"), "applicants.txt")));
                                for(int a = 0; a < alrThr.length; a++){
                                    if(!alrThr[a].equals(args[1])){
                                        pw.println(alrThr[a]);
                                    }
                                }
                                pw.close();
                                ch.sendMessage(sender, "&cDenied the player's application.");
                            } catch (IOException ex) {
                                Logger.getLogger(Command_APP.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                        
                }
                else{
                    ch.sendMessage(sender, "&cUnknown argument: " + args[2]);
                    ch.sendMessage(sender, "&c/sma <IGN> <accept|deny> [Rank]");
                }
            }
            else{
                ch.sendMessage(sender, "&cERROR: Player not found.");
                ch.sendMessage(sender, "&cDid you get the arguments wrong?");
                ch.sendMessage(sender, "&c/sma app <IGN> <accept|deny> [Rank]");
            }
        }
        else{
            ch.sendMessage(sender, "&cNot enough arguments!");
            ch.sendMessage(sender, "&c/sma app <IGN> <accept|deny> [Rank]");
        }
    }
    public void resetConfig(String IGN, FileConfiguration fc){
        fc.set("player.app", false);
        fc.set("player.voteUp.number", 0);
        fc.set("player.voteUp.list", "");
        fc.set("player.voteDown.number", 0);
        fc.set("player.voteDown.list", "");
        fc.set("alert.10.up", true);
        fc.set("alert.10.down", true);
        sm.saveConfig(IGN, fc, true);
    }
}
