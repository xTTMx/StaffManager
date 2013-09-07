package com.github.SkyBirdSoar.Commands.SM;

import com.github.SkyBirdSoar.StaffManager.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Command_SM {
    private StaffManager sm;
    private final String defaultHelpFileName = "help_file_page_";
    public Command_SM(StaffManager pl){
        sm = pl;
    }
    public final String[] commands = {"2", "3", "4", "5", "help", "apply", "vote", "status", "applyers", "staff", "top"};
    public boolean commandSM(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 0){
                if(sender.hasPermission("sm.help.1")){
                    printHelpFile(1, sender);
                }
            }
            if(args.length > 0){
                boolean correctCommand = false;
                for(int a = 0; a < commands.length; a++){
                    if(args[0].equals(commands[a])){
                        correctCommand = true;
                    }
                }
                if(correctCommand){
                    switch(args[0]){
                        case "2":
                            if(sender.hasPermission("sm.help.2")){
                                printHelpFile(2, sender);
                            }
                            break;
                        case "3":
                            if(sender.hasPermission("sm.help.3")){
                                printHelpFile(3, sender);
                            }
                            break;
                        case "4":
                            if(sender.hasPermission("sm.help.4")){
                                printHelpFile(4, sender);
                            }
                            break;
                        case "5":
                            if(sender.hasPermission("sm.help.5")){
                                printHelpFile(5, sender);
                            }
                            break;
                        case "help":
                            Command_HELP cmdh = new Command_HELP(sm, this);
                            cmdh.help(sender, cmd, label, args);
                            break;
                        default:
                            sender.sendMessage(sm.parseColor("&cSorry, the command you asked for is not implemented yet."));
                            break;
                    }
                }
                if(!correctCommand){
                    sender.sendMessage(sm.parseColor("&cUnknown argument: &d" + args[0]));
                    sender.sendMessage(sm.parseColor("&cPlease check &b/sm help &cfor help."));
                }
            }
        return true;
    }
    private boolean isPageNeeded(int pageNumber){
        FileConfiguration fc = sm.getConfig("config", false);
        int pagesDefined = fc.getInt("pagesNeeded");
        boolean needed = false;
        if(pageNumber <= pagesDefined){
            needed = true;
        }
        return needed;
    }
    private void generateDefaultHelpFile(int pageNumber){
        File file = sm.getFolder("HELP_FILES");
        file = new File(file, defaultHelpFileName + pageNumber + ".txt");
        PrintWriter pw = null;
        try{
            file.createNewFile();
            pw = new PrintWriter(new FileWriter(file));
            String[] toWrite = new String[22];
            toWrite[0] = "&b=== &d&lHow to apply for staff &b===";
            toWrite[1] = "&11. &aRead through everything before doing anything!";
            toWrite[2] = "&1NOTE. &aThe easiest way to apply for staff is to go to http://remocraft.enjin.com/Recruitment";
            toWrite[3] = "&12. &aGo to &5/warp staff&a.";
            toWrite[4] = "&13. &aGrab a book and fill in the required fields:";
            toWrite[5] = " &d- IGN";
            toWrite[6] = " &d- GENDER";
            toWrite[7] = " &d- AGE";
            toWrite[8] = " &d- TIMEZONE";
            toWrite[9] = " &d- SKYPE";
            toWrite[10] = " &d- DESRIPTION OF YOURSELF";
            toWrite[11] = " &d- STAFF EXPERIENCE IN OTHER SERVERS";
            toWrite[12] = " &d- HOW LONG HAVE YOU BEEN ON THE SERVER";
            toWrite[13] = " &d- YOUR CURRENT RANK";
            toWrite[14] = " &d- WHAT RANK ARE YOU APPLYING FOR";
            toWrite[15] = " &d- WHAT WOULD YOU DO IF YOU FOUND A GLITCH";
            toWrite[16] = " &d- WHO IS THE OWNER OF THE SERVER";
            toWrite[17] = "&1NOTE: &cAll fields are mandatory";
            toWrite[18] = "&14. &aSign the book and be sure to drop it in the hopper!";
            toWrite[19] = "&15. &aDo &d/sm apply";
            toWrite[20] = "&16. &aGet people to vote for you!";
            toWrite[21] = "&17. &aDo &d/sm help &ato find out what other commands you can do";
            for(int a = 0; a < toWrite.length; a++){
                String toPrint = sm.parseColor(toWrite[a]);
                pw.println(toPrint);
            }
            sm.log(Level.INFO, "Successfully generated Help File " + pageNumber + "!");
        } 
        catch (IOException ex) {
            sm.getLogger().log(Level.SEVERE, null, ex);
        }
        finally{
            pw.close();
        }
    } 
    private File getHelpFile(int pageNumber, CommandSender sender){
        File file = sm.getFolder("HELP_FILES");
        file = new File(file, defaultHelpFileName + pageNumber + ".txt");
        if(!file.exists()){
            if(isPageNeeded(pageNumber)){
                generateDefaultHelpFile(pageNumber);
                file = sm.getFolder("HELP_FILES");
                file = new File(file, defaultHelpFileName + pageNumber + ".txt");
            }
            else{
                sm.log(Level.SEVERE, "Player tried to request for help file " +  pageNumber + ". If needed please edit the number of required help files in config.yml");
            }
        }
        return file;
    }
    private void printHelpFile(int pageNumber, CommandSender sender){
        try {
            File file = getHelpFile(pageNumber, sender);
            Scanner scan = new Scanner(file);
            String[] toPrint = new String[50];
            toPrint[0] = "&c###############";
            toPrint[1] = sm.SERVER_NAME;
            toPrint[2] = toPrint[0];
            toPrint[3] = sm.getPluginName(true);
            toPrint[4] = "&r";
            int a = 5;
            while(scan.hasNextLine()){
                toPrint[a] = scan.nextLine();
                a++;
            }
            for(int b = 0; b < toPrint.length; b++){
                if(toPrint[b] != null){
                    sender.sendMessage(sm.parseColor(toPrint[b]));
                }
            }
            if(isPageNeeded(pageNumber + 1)){
                sender.sendMessage(sm.parseColor(toPrint[0]));
                sender.sendMessage(sm.parseColor("&6Do /sm " + (pageNumber + 1) + " to continue"));
                sender.sendMessage(sm.parseColor(toPrint[0]));
            }
            else{
               sender.sendMessage(sm.parseColor(toPrint[0]));
               sender.sendMessage(sm.parseColor("      &cEND"));
               sender.sendMessage(sm.parseColor(toPrint[0]));
            }
        } catch (FileNotFoundException ex) {
            sender.sendMessage(sm.parseColor("&cUnable to find the specified help file."));
        }
    }
}
