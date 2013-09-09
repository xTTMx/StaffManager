package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.API.ListAPI;
import com.github.SkyBirdSoar.Main.StaffManager;
import com.github.SkyBirdSoar.Main.CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Command_APPLICANTS extends ListAPI{

    public Command_APPLICANTS(StaffManager sm, CommandHandler ch){
        super(sm, ch, "data", "applicants.txt", "&bApplicants");
    }
    @Override
    public void list(CommandSender sender, Command cmd, String label, String[] args) {
        this.getPage(sender, cmd, args);
    }
    
}
