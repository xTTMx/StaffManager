package com.SkyBirdSoar.StaffManager.CommandSM;

import com.SkyBirdSoar.StaffManager.Util.AbstractSMCommand;
import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/*  StaffManager
    Copyright (C) 2013  SkyBirdSoar

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

// Does not extend ListAPI purely because you should not override returnPage ._.
public class SM_info extends AbstractSMCommand{

    private StaffManager m;
    private SM_main sm;
    public SM_info(StaffManager m, SM_main sm){
        super("Shows important information you must know!", "/sm info");
        this.m = m;
        this.sm = sm;
    }
    @Override
    public void execute(CommandSender sender, Command cmd, String[] args) {
        int x = 1;
        if(args.length > 1){
            try{
                x = Integer.parseInt(args[1]);
            }
            catch(NumberFormatException e){
                m.getCH().out(sender, ERROR.ERROR_NOT_NUMERICAL);
            }
        }
        if(x == 0){
            x = 1;
        }
        getPage(sender, x);
    }

    @Override
    public String usage() {
        return "/sm info [page]";
    }

    private boolean checkExist(){
        File file = new File(m.getPluginFolder(), "HELP");
        if(file.exists()){
            return true;
        }
        else{
            file.mkdir();
            return true;
        }
    }
    
    private void getPage(CommandSender sender, int x){
        if (checkExist()) {
            File[] file = new File(m.getPluginFolder(), "HELP").listFiles();
            if (file.length == 0) {
                if (sender instanceof Player) {
                    m.getCH().out(sender, "&cNo info has been provided by the owner.");
                }
                if (sender instanceof ConsoleCommandSender) {
                    m.consoleOut("&cNo valid files for output found.");
                }
            } else {
                if(checkValid(sender, file)){
                    returnPage(sender, file, x);
                }
                else{
                    m.getCH().out(sender, "&cNo info has been provided by the owner.");
                }
            }
        } else {
            m.getCH().out(sender, "&cNo info has been provided by the owner.");
        }
    }
    
    private boolean checkValid(CommandSender sender, File[] check){
        boolean hasFirst = false;
        String valid = "# 1";
        for(int a = 0; a < check.length; a++){
            try {
                File b = check[a];
                Scanner scan = new Scanner(b);
                String validator = scan.nextLine();
                if(validator.equals(valid)){
                    hasFirst = true;
                }
            } catch (FileNotFoundException ex) {
                m.consoleOut("FileNotFoundException occurred in folder HELP/");
                m.getCH().out(sender, "&cAn error occurred.");
                m.getLogger().log(Level.FINE, null, ex);
            }
        }
        return hasFirst;
    }
    
    private void returnPage(CommandSender sender, File[] file, int x){
        m.getCH().out(sender, "&6------------ &lINFO Page " + x + " &6------------");
        boolean valid = false;
        for(int a = 0; a < file.length; a++){
            File b = file[a];
            int c = 0;
            try {
                Scanner scan = new Scanner(b);
                String validator = scan.nextLine();
                String t = validator.charAt(2) + "";
                c = Integer.parseInt(t);
                if(c == x){
                    while(scan.hasNextLine()){
                        m.getCH().out(sender, scan.nextLine());
                    }
                    valid = true;
                }
            } catch (FileNotFoundException ex) {
                if(c == x){
                    m.getCH().out(sender, "&cAn error occurred.");
                }
                m.getLogger().log(Level.FINE, null, ex);
            } catch (NumberFormatException ex) {
                if(c == x){
                    m.getCH().out(sender, "&cAn error occurred.");
                }
                m.consoleOut("&cERROR! INVALID INFO FILE DETECTED: " + b.getAbsolutePath());
            }
        }
        if(!valid){
            m.getCH().out(sender, "&cFile Not Found.");
        }
    }

    @Override
    public void checkPerm(CommandSender sender, Command cmd, String[] args) {
        if(sender.hasPermission("staffmanager.sm.info")){
            execute(sender, cmd, args);
        }
        else{
            m.getCH().out(sender, ERROR.PERMISSION_MESSAGE);
        }
    }
}
