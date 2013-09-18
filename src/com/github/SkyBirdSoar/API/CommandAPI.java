package com.github.SkyBirdSoar.API;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CommandAPI{
    private StaffManager sm;
    private CommandHandler ch;
    private static String description;
    private static String label;
    public CommandAPI(StaffManager p, CommandHandler f){
        sm = p;
        ch = f;
    }
    public abstract void execute(CommandSender sender, Command cmd, String label, String[] args);
    protected void setDescription(String a){
        description = a;
    }
    public static String getDescription(){
        return description;
    }
    protected void setLabel(String a){
        label = a;
    }
    public static String getLabel(){
        return label;
    }
}