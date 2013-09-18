package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.Conversations.Main;
import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_TEST{
    private StaffManager sm;
    public Command_TEST(StaffManager pl){
        sm = pl;
    }
    public void test(CommandSender sender, Command cmd, String label, String[] args){
        Main m = new Main(sm);
        String name = sender.getName();
        if(sender instanceof Player){
            Player p = sm.getServer().getPlayer(name);
            m.converse(p);
        }
        else{
            sender.sendMessage(sm.parseColor("&cSorry, only players can apply."));
        }
    }
}