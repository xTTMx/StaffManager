package com.SkyBirdSoar.StaffManager.Main;

import com.SkyBirdSoar.StaffManager.CommandSM.SM_main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
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

public class CmdHandler implements CommandExecutor{
    private StaffManager m;
    
    public CmdHandler(StaffManager m){
        this.m = m;
    }
    
    public void sm(CommandSender sender, Command cmd, String label, String[] args){
        SM_main sm = new SM_main(m);
        sm.getCommand(sender, cmd, args);
    }
    
    public void sma(CommandSender sender, Command cmd, String label, String[] args){
        
    }

    public void out(CommandSender sender, String message){
        sender.sendMessage(m.parse(message));
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("sm")){
            this.sm(sender, cmd, label, args);
        }
        if(cmd.getName().equalsIgnoreCase("sma")){
            this.sma(sender, cmd, label, args);
        }
        return true;
    }
}
