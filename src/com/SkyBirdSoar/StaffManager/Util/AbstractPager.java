package com.SkyBirdSoar.StaffManager.Util;

import com.SkyBirdSoar.StaffManager.Util.SMPlugin;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

public abstract class AbstractPager extends AbstractSMCommand{
    
    private File Filex;
    private String title;
    private int maxPage = 0;
    private double temp = 0.0;
    private List l = null;
    
    public AbstractPager(String desc, String label, File file, String title){
        super(desc, label);
        Filex = file;
        this.title = title;
    }
    
    public AbstractPager(String desc, String label, SMPlugin sm, String file, String title){
        super(desc, label);
        Filex = new File(sm.getPluginFolder(), file);
        this.title = title;
    }
    
    public AbstractPager(String desc, String label, SMPlugin sm, String folder, String file, String title){
        super(desc, label);
        Filex = new File(sm.getPluginFolder(), folder);
        Filex = new File(Filex, file);
        this.title = title;
    }
    
    public AbstractPager(String desc, String label, List l, String title){
        super(desc, label);
        this.l = l;
        this.title = title;
    }

    @Override
    public abstract void execute(CommandSender sender, Command cmd, String[] args);

    @Override
    public abstract String usage();
    
    private boolean checkExist() throws FileNotFoundException{
        if(!Filex.exists()){
            throw new FileNotFoundException("File Not Found: " + Filex.getAbsolutePath());
        }
        return true;
    }
    
    private List<String> getList() throws FileNotFoundException{
        if(l != null){
            return l;
        }
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
    
    public void returnPage(CommandSender sender, int x) throws FileNotFoundException{
        List<String> l = getList();
        int a = l.size();
        if(x == 0){
            x = 1;
        }
        int max = x * 10;
        int min = max - 9;
        if(min <= a){
            String header = parse("&3------------ " + title + " &aPage " + x + "/" + maxPage + " &3------------");
            sender.sendMessage(header);
            for(int b = min; b <= max && b <= a; b++){
                String send = "&6"+ b + ". &f" + l.get(b-1);
                sender.sendMessage(parse(send));
            }
        }
        else{
            sender.sendMessage(parse("&c&lERROR&c: Page not found."));
        }
    }
    
    private String parse(String x){
        String[] codes = {"a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "l", "m", "n", "o", "r", "k"};
        for(int b = 0; b < codes.length; b++){
            x = x.replace("&"+ codes[b], "§" + codes[b]);
        }
        return x;
    }
    
    protected File getFile(){
        return Filex;
    }
}