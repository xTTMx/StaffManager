package com.SkyBirdSoar.StaffManager.CommandSM;

import com.SkyBirdSoar.StaffManager.Util.AbstractSMCommand;
import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

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

// Majority of the code from com.SkyBirdSoar.StaffManager.ListAPI

class SM_top extends AbstractSMCommand{

    private StaffManager m;
    private SM_main sm;
    private File Filex;
    private double temp = 0.0;
    private int maxPage = 0;
    
    public SM_top(StaffManager m, SM_main sm){
        super("Shows the current applicants ordered by votes", "/sm top");
        this.m = m;
        this.sm = sm;
        Filex = new File(m.getPluginFolder(), "data");
        Filex = new File(Filex, "applicants.txt");
    }
    @Override
    public void execute(CommandSender sender, Command cmd, String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String usage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      private boolean checkExist() throws FileNotFoundException{
        if(!Filex.exists()){
            throw new FileNotFoundException("File Not Found: " + Filex.getAbsolutePath());
        }
        return true;
    }
    
    private List<String> getList() throws FileNotFoundException{
        if(checkExist()){
            Scanner scan = new Scanner(Filex);
            List<String> l = new ArrayList<>();
            while(scan.hasNextLine()){
                l.add(scan.nextLine());
            }
            temp = Double.parseDouble(l.size() + "") / 10;
            maxPage = (int) temp;
            if(temp > maxPage){
                maxPage++;
            }
            return l;
        }
        throw new FileNotFoundException("FileNotFoundException: " + Filex.getAbsolutePath());
    }
    
    private List<String> getRealList() throws FileNotFoundException{
        List<String> l = getList();
        List<Integer> i = new ArrayList<>();
        for(int a = 0; a < l.size(); a++){
            String t = l.get(a);
            File b = new File(m.getPlayerFolder(), t + ".yml");
            FileConfiguration c = m.getConfig(b);
            if(c.getBoolean("player.application")){
                int vote = c.getInt("application.vote.up");
                Map<String, Integer> m = new HashMap<>();
                m.put(t, vote);
            }
        }
        return l;
    }
    
    public void returnPage(CommandSender sender, int x) throws FileNotFoundException{
        List<String> l = getList();
        int a = l.size();
        if(x == 0){
            x = 1;
        }
        int max = x * 10;
        int min = max - 9;
        if(min <= a){
            String header = m.parse("&3------------ TOP &aPage " + x + "/" + maxPage + " &3------------");
            sender.sendMessage(header);
            for(int b = min; b <= max && b <= a; b++){
                String send = "&6"+ b + ". &f" + l.get(b-1);
                sender.sendMessage(m.parse(send));
            }
        }
        else{
            sender.sendMessage(m.parse("&c&lERROR&c: Page not found."));
        }
    }
    
    protected File getFile(){
        return Filex;
    }
    
    @Override
    public void checkPerm(CommandSender sender, Command cmd, String[] args) {
        if(sender.hasPermission("staffmanager.sm.special.top")){
            execute(sender, cmd, args);
        }
        else{
            m.getCH().out(sender, ERROR.PERMISSION_MESSAGE);
        }
    }
}
