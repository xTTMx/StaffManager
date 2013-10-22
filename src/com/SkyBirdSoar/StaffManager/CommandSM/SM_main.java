package com.SkyBirdSoar.StaffManager.CommandSM;

import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class SM_main {
    private StaffManager m;
    public String[] commands = {"help", "applicants", "staff", "add", "info"};
    public SM_main(StaffManager m){
        this.m = m;
    }
    
    public void getCommand(CommandSender sender, Command cmd, String[] args){
        if(args.length == 0 || args[0].equals("help")){
            SM_help sm = new SM_help(m, this);
            sm.execute(sender, cmd, args);
        }
        else{
            String label = args[0];
            boolean valid = false;
            for(int a = 0; a < commands.length; a++){
                if(commands[a].equals(label)){
                    valid = true;
                }
            }
            if(valid){
                try {
                    Class<?> c = Class.forName("com.SkyBirdSoar.StaffManager.CommandSM.SM_" + label);
                    Class[] i = {StaffManager.class, SM_main.class};
                    Constructor<?> d = c.getConstructor(i);
                    Object instance = d.newInstance(m, this);
                    Class<?>[] u = {CommandSender.class, Command.class, String[].class};
                    Method e = c.getMethod("checkPerm", u);
                    Object[] o = {sender, cmd, args};
                    e.invoke(instance, o);
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
    }
}
