package com.SkyBirdSoar.StaffManager.CommandSM;

import com.SkyBirdSoar.StaffManager.Util.AbstractPager;
import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import java.io.FileNotFoundException;
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

public class SM_applicants extends AbstractPager{

    private StaffManager m;
    private SM_main sm;
    public SM_applicants(StaffManager m, SM_main sm){
        super("Displays the current list of applicants applying for staff", "/sm applicants", m, "data", "applicants.txt", "Applicants");
        this.m = m;
        this.sm = sm;
    }
    @Override
    public void execute(CommandSender sender, Command cmd, String[] args) {
        try {
            int x = 1;
            if(args.length > 1){
                x = Integer.parseInt(args[1]);
            }
            if(x == 0){
                x = 1;
            }
            this.returnPage(sender, x);
        } catch (FileNotFoundException ex) {
            m.getCH().out(sender, ERROR.ERROR_NO_APPLICANTS);
            m.consoleOut("&cFileNotFoundException: data/applicants.txt - To fix, get someone to apply for staff or create one yourself.");
            m.getLogger().log(Level.FINE, null, ex);
        } catch (NumberFormatException ex) {
            m.getCH().out(sender, ERROR.ERROR_NOT_NUMERICAL);
        }
    }

    @Override
    public String usage() {
        return "/sm applicants [page]";
    }

    @Override
    public void checkPerm(CommandSender sender, Command cmd, String[] args) {
        if(sender.hasPermission("staffmanager.sm.applicants")){
            execute(sender, cmd, args);
        }
        else{
            m.getCH().out(sender, ERROR.PERMISSION_MESSAGE);
        }
    }
    
}
