package com.SkyBirdSoar.StaffManager.CommandSM;

import com.SkyBirdSoar.StaffManager.Util.AbstractSMCommand;
import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

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

public class SM_help extends AbstractSMCommand{
    private StaffManager m;
    private SM_main sm;
    
    public SM_help(StaffManager m, SM_main sm){
        super("Shows help for default users", "/sm help");
        this.m = m;
        this.sm = sm;
    }
    @Override
    public void execute(CommandSender sender, Command cmd, String[] args) {
        if(args.length == 1){
            showHelp(sender);
        }
        else{
            showHelp(sender, args);
        }
    }

    @Override
    public String usage() {
        return "/sm help [command]";
    }
    private void showHelp(CommandSender sender){
        String[] commands = sm.commands;
        m.getCH().out(sender, "&6------------ [StaffManager] /sm ------------");
        for (int a = 0; a < commands.length; a++) {
            try {
                Class<?> c = Class.forName("com.SkyBirdSoar.StaffManager.CommandSM.SM_" + commands[a]);
                Class<?>[] i = {StaffManager.class, SM_main.class};
                Constructor<?> d = c.getConstructor(i);
                Object instance = d.newInstance(m, sm);
                Method e = c.getMethod("getDescription");
                String invoke = (String) e.invoke(instance);
                String message = "&a" + commands[a] + " - &f" + invoke;
                m.getCH().out(sender, message);
            } catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                m.getCH().out(sender, "An error occurred: " + ex.toString());
                m.consoleOut("An error occured: " + ex.toString());
                m.getLogger().log(Level.FINE, null, ex);
            }
        }
    }
    private void showHelp(CommandSender sender, String[] args){
        String[] commands = sm.commands;
        boolean valid = false;
        for (int a = 0; a < commands.length; a++) {
            if (commands[a].equals(args[1])) {
                valid = true;
            }
        }
        if (valid) {
            m.getCH().out(sender, "&6------------ &a/sm " + args[1] + " &6------------");

            try {
                Class<?> c = Class.forName("com.SkyBirdSoar.StaffManager.CommandSM.SM_" + args[1]);
                Class<?>[] i = {StaffManager.class, SM_main.class};
                Constructor<?> d = c.getConstructor(i);
                Object instance = d.newInstance(m, sm);
                Method e = c.getMethod("usage");
                Method f = c.getMethod("getDescription");
                String invoke = (String) e.invoke(instance);
                String invokef = (String) f.invoke(instance);
                String message = "&6Usage: " + invoke;
                String desc = "&6Description: " + invokef;
                m.getCH().out(sender, desc);
                m.getCH().out(sender, message);
            } catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                m.getCH().out(sender, "An error occurred: " + ex.toString());
                m.consoleOut("An error occured: " + ex.toString());
                m.getLogger().log(Level.FINE, null, ex);

            }
        }
        else{
            m.getCH().out(sender, ERROR.ERROR_COMMAND_NOT_FOUND);
        }
    }
    
    @Override
    public void checkPerm(CommandSender sender, Command cmd, String[] args) {
        if(sender.hasPermission("staffmanager.sm.help")){
            execute(sender, cmd, args);
        }
        else{
            m.getCH().out(sender, ERROR.PERMISSION_MESSAGE);
        }
    }
}
