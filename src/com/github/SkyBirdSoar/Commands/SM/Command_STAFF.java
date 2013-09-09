package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.API.ListAPI;
import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_STAFF extends ListAPI{
    public Command_STAFF(StaffManager pl, CommandHandler pl2){
        super(pl, pl2, "data", "staff.txt", "&bStaff");
    }

    @Override
    public void list(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 1){
            try {
                ch.sendMessage(sender, "&c===== &bStaff Online&c =====");
                int noOfStaffOnline = 0;
                String staffOnline = "";
                String[] array = this.getList();
                Player[] player = sm.getServer().getOnlinePlayers();
                for(int a = 0; a < array.length && a < 10; a++){
                    for(int b = 0; b < player.length; b++){
                        if(player[b].getName().equals(array[a])){
                            staffOnline += array[a] + ", ";
                            noOfStaffOnline++;
                        }
                    }
                }
                ch.sendMessage(sender, "&6Total Staff Online: " + noOfStaffOnline);
                ch.sendMessage(sender, "&6Staff Online: &b" + staffOnline);
            } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(Command_STAFF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(args.length > 1){
            this.getPage(sender, cmd, args);
        }
    }
}
