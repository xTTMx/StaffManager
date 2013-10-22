/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.SkyBirdSoar.StaffManager.CommandSM;

import com.SkyBirdSoar.StaffManager.Util.AbstractPager;
import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SM_staff extends AbstractPager{

    private StaffManager m;
    private SM_main sm;
    private File Filex;
    private int count;
    public SM_staff(StaffManager m, SM_main sm){
        super("Displays current online staff/staff list", "/sm staff", m, "data", "staff.txt", "Staff List");
        this.m = m;
        this.sm = sm;
        Filex = this.getFile();
    }
    @Override
    public void execute(CommandSender sender, Command cmd, String[] args) {
        if(args.length == 1){
            try {
                returnOnline(sender);
            } catch (FileNotFoundException ex) {
                m.getCH().out(sender, ERROR.ERROR_NO_STAFF);
                m.consoleOut("&cFileNotFoundException: data/staff.txt - To fix, accept a player's application or create one yourself.");
                m.getLogger().log(Level.FINE, null, ex);
            }
        }
        else{
            try {
                int x = 1;
                if (args.length > 1) {
                    x = Integer.parseInt(args[1]);
                }
                if (x == 0) {
                    x = 1;
                }
                this.returnPage(sender, x);
            } catch (FileNotFoundException ex) {
                m.getCH().out(sender, ERROR.ERROR_NO_STAFF);
                m.consoleOut("&cFileNotFoundException: data/staff.txt - To fix, accept a player's application or create one yourself.");
                m.getLogger().log(Level.FINE, null, ex);
            } catch (NumberFormatException ex) {
                m.getCH().out(sender, ERROR.ERROR_NOT_NUMERICAL);
            }
        }
    }

    @Override
    public String usage() {
        return "/sm staff [page]";
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
                count++;
            }
            return l;
        }
        throw new FileNotFoundException("FileNotFoundException: " + Filex.getAbsolutePath());
    }

    private List<String> getStaffOnlineList() throws FileNotFoundException{
        List<String> l = getList();
        String[] s = new String[count];
        s = l.toArray(s);
        Player[] p = m.getServer().getOnlinePlayers();
        List<String> c = new ArrayList<>();
        count = 0;
        for(int a = 0; a < s.length; a++){
            for(int b = 0; b < p.length; b++){
                if(p[b].getName().equals(s[a])){
                    c.add(p[b].getName());
                    count++;
                }
            }
        }
        return c;
    }
    
    private void returnOnline(CommandSender sender) throws FileNotFoundException{
        List<String> l = getStaffOnlineList();
        String header = m.parse("&3===== &aStaff Online &3=====");
        sender.sendMessage(header);
        String f = "";
        boolean alt = false;
        for(int a = 0; a < l.size(); a++){
            if(alt){
                if(a == l.size()-1){
                    f += "&a" + l.get(a);
                }
                else{
                    f += "&a" + l.get(a) + ", ";
                }
            }
            else{
                if(a == l.size()-1){
                    f += "&b" + l.get(a);
                }
                else{
                    f += "&b" + l.get(a) + ", ";
                }
            }
            alt = !alt;
        }
        if(l.isEmpty()){
            m.getCH().out(sender, "&cNo staff online.");
        }
        else{
            m.getCH().out(sender, "&6" + count + " Staff Online.");
            m.getCH().out(sender, f);
        }
    }
    
    @Override
    public void checkPerm(CommandSender sender, Command cmd, String[] args) {
        if(sender.hasPermission("staffmanager.sm.staff")){
            execute(sender, cmd, args);
        }
        else{
            m.getCH().out(sender, ERROR.PERMISSION_MESSAGE);
        }
    }
}
