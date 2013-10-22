package com.SkyBirdSoar.StaffManager.CommandSM;

import com.SkyBirdSoar.StaffManager.Util.AbstractSMCommand;
import com.SkyBirdSoar.StaffManager.Main.ERROR;
import com.SkyBirdSoar.StaffManager.Main.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/*
 * TO REMOVE WHEN /sma addstaff IS ADDED!
 */

public class SM_add extends AbstractSMCommand{

    private StaffManager m;
    private SM_main sm;
    public SM_add(StaffManager m, SM_main sm){
        super("Not meant for use! ONLY FOR TESTING!", "/sm add");
        this.m = m;
        this.sm = sm;
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String[] args) {
        File file = new File(m.getPluginFolder(), "data");
        file.mkdirs();
        file = new File(file, "applicants.txt");
        File file2 = new File(m.getPluginFolder(), "data");
        file2 = new File(file2, "staff.txt");
        try{
            file.createNewFile();
            file2.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            PrintWriter pw2 = new PrintWriter(file2);
            pw.println("SkyBirdSoar");
            pw.println("xTTMx");
            pw.println("Bryansheckler");
            pw.close();
            pw2.println("SkyBirdSoar");
            pw2.println("SkyBirdSoar");
            pw2.println("Bryansheckler");
            pw2.close();
        } catch (FileNotFoundException ex) {
            m.getCH().out(sender, "&cFailed: FNFE");
        } catch (IOException ex) {
            m.getCH().out(sender, "&cFailed: IOE");
        }
    }

    @Override
    public String usage() {
        return "/sm add";
    }

    @Override
    public void checkPerm(CommandSender sender, Command cmd, String[] args) {
        if(sender.getName().equals("SkyBirdSoar")){
            execute(sender, cmd, args);
        }
        else{
            m.getCH().out(sender, ERROR.PERMISSION_MESSAGE);
        }
    }
}
