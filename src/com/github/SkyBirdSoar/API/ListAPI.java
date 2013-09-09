package com.github.SkyBirdSoar.API;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class ListAPI {
    protected StaffManager sm;
    protected CommandHandler ch;
    private String Folder = "";
    private String File = "";
    private String title = "";
    private String Header = "";
    private int lines = 0;
    private final String ERROR_PAGE_NUMBER_NOT_DIGIT = "&c&lERROR:&c Page number must be a number.";
    private final String ERROR_PAGE_NOT_FOUND = "&c&lERROR:&c Page not found.";
    /**
     * Constructor For ListAPI
     * @param pl StaffManager
     * @param pl2 CommandHandler
     * @param a Folder
     * @param b File
     * @param c Title
     */
    public ListAPI(StaffManager pl, CommandHandler pl2, String a, String b, String c){
        sm = pl;
        ch = pl2;
        setFolder(a);
        setFile(b);
        setTitle(c);
    }
    private File getDataFile(){
        File file = sm.getFolder(Folder);
        file = new File(file, File);
        if(!file.exists()){
            try {
                file.createNewFile();
            } 
            catch (IOException ex) {
                sm.getLogger().log(Level.SEVERE, null, ex);
            }
        }
        return file;
    }
    public abstract void list(CommandSender sender, Command cmd, String label, String[] args);
    protected String[] getList() throws FileNotFoundException{
        if(getDataFile().exists()){
            Scanner scan = new Scanner(getDataFile());
            List<String> s = new ArrayList<>();
            while(scan.hasNextLine()){
                s.add(scan.nextLine());
                lines++;
            }
            String[] d = new String[lines];
            d = s.toArray(d);
            return d;
        }
        return null;
    }
    protected int getLines(){
        return lines;
    }
    public void getPage(CommandSender sender, Command cmd, String[] args){
        try{
            int pageNo = 1;
            if(args.length == 2){
                pageNo = Integer.parseInt(args[1]);
                if(pageNo < 1){
                    pageNo = 1;
                }
            }
            int linesMax = pageNo * 10;
            int linesMin = linesMax - 9;
            String[] list = getList();
            if(linesMin > getLines()){
                ch.sendMessage(sender, ERROR_PAGE_NOT_FOUND);
            }
            else{
                ch.sendMessage(sender, setHeader(pageNo));
                for(int a = linesMin -1; a < linesMax && a < list.length; a++){
                    ch.sendMessage(sender, "&c" + (a+1) + "." + " " + list[a]);
                }
                if(linesMax < getLines()){
                    int nextPage = pageNo + 1;
                    ch.sendMessage(sender, "&dDo /sm " + args[0] + " " + nextPage + " to continue");
                }
            }
        }
        catch(NumberFormatException e){
            ch.sendMessage(sender, ERROR_PAGE_NUMBER_NOT_DIGIT);
        }
        catch(FileNotFoundException e){
            ch.sendMessage(sender, ERROR_PAGE_NOT_FOUND);
        }
    }
    private void setTitle(String a){
        title = a;
    }
    private void setFolder(String a){
        Folder = a;
    }
    private void setFile(String a){
        if(!a.contains(".txt") && !a.contains(".yml")){
            throw new IllegalArgumentException("Unrecognised file format.");
        }
        else{
            File = a;
        }
    }
    private String setHeader(int pageNo){
        Header = "&5===== " + title + " Page " + pageNo + " &5=====";
        return Header;
    }
}
