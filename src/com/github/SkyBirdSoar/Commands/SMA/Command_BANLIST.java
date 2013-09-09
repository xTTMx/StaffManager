package com.github.SkyBirdSoar.Commands.SMA;

import com.github.SkyBirdSoar.API.ListAPI;
import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Command_BANLIST extends ListAPI{
    public Command_BANLIST(StaffManager sm, CommandHandler ch){
        super(sm, ch, "data", "banned.txt", "&bBan List");
    }

    @Override
    public void list(CommandSender sender, Command cmd, String label, String[] args) {
        this.getPage(sender, cmd, args);
    }
}
