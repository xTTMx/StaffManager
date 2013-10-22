package com.SkyBirdSoar.StaffManager.CommandSM;

import com.SkyBirdSoar.StaffManager.Util.AbstractPager;
import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * NOT TO BE USED IN ACTUAL PLUGIN! USE ONLY FOR REFERENCE.
 * 
 */
class SM_list extends AbstractPager{
    private StaffManager m;
    private SM_main sm;
    public SM_list(StaffManager m, SM_main sm){
        super("shows a list", "/sm list", m, "test", "test.txt", "List");
        this.m = m;
        this.sm = sm;
    }
    
    @Override
    public void execute(CommandSender sender, Command cmd, String[] args) {
        try {
            createList();
            int x = 1;
            if(args.length > 1){
                x = Integer.parseInt(args[1]);
            }
            if(x == 0){
                x = 1;
            }
            this.returnPage(sender, x);
        } catch (FileNotFoundException ex) {
            m.consoleOut("&cFailed To Read List! FileNotFoundException occurred.");
            m.getLogger().log(Level.FINE, null, ex);
        } catch (NumberFormatException ex) {
            m.consoleOut("&cNumberFormatException occurred.");
            m.getLogger().log(Level.FINE, null, ex);
        }
    }

    @Override
    public String usage() {
        return "/sm list";
    }
    
    private void createList(){
        try {
            if(!this.getFile().exists()){
                this.getFile().getParentFile().mkdirs();
                this.getFile().createNewFile();
            }
            PrintWriter pw = new PrintWriter(this.getFile());
            for(int v = 0; v < 51; v++){
                pw.println("dddd");
            }
            pw.close();
        } catch (FileNotFoundException ex) {
            m.consoleOut("&cFailed To Create List! FileNotFoundException occurred.");
            m.getLogger().log(Level.FINE, null, ex);
        } catch (IOException ex) {
            m.consoleOut("&cFailed To Create List! IOException occurred.");
            m.getLogger().log(Level.FINE, null, ex);
        }
    }
    
    @Override
    public void checkPerm(CommandSender sender, Command cmd, String[] args) {
        if(sender.hasPermission("staffmanager.sm.special.list")){
            execute(sender, cmd, args);
        }
        else{
            m.getCH().out(sender, ERROR.PERMISSION_MESSAGE);
        }
    }
}
