package com.github.SkyBirdSoar.API;

import com.github.SkyBirdSoar.Main.CommandHandler;
import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class WriteAPI {
    protected StaffManager sm;
    protected CommandHandler ch;
    private String folder = "";
    private String file = "";
    private int lines = 0;
    public WriteAPI(StaffManager pl, CommandHandler pl2, String folder, String file){
        sm = pl;
        ch = pl2;
        setFolder(folder);
        setFile(file);
    }
    private void setFolder(String a){
        folder = a;
    }
    private void setFile(String a){
        if(!a.contains(".txt") && !a.contains(".yml")){
            throw new IllegalArgumentException("Unrecognised file format.");
        }
        file = a;
    }
    private File getDataFile(){
        File file = sm.getFolder(folder);
        file = new File(file, this.file);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                sm.getLogger().log(Level.SEVERE, null, ex);
            }
        }
        return file;
    }
    private String[] getText() throws FileNotFoundException{
        Scanner scan = new Scanner(getDataFile());
        List<String> list = new ArrayList<>();
        lines = 0;
        while(scan.hasNextLine()){
            list.add(scan.nextLine());
            lines++;
        }
        String[] g = new String[lines];
        g = list.toArray(g);
        return g;
    }
    protected void setText(String toPrintIn){
        try {
            String[] b = getText();
            PrintWriter pw = new PrintWriter(new FileWriter(getDataFile()));
            for(int a =  0; a < b.length; a++){
                pw.println(b[a]);
            }
            pw.println(toPrintIn);
            pw.close();
        } 
        catch (FileNotFoundException ex) {
            sm.getLogger().log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            sm.getLogger().log(Level.SEVERE, null, ex);
        }
    }
    protected void removeText(String toRemove){
        try {
            String[] b = getText();
            PrintWriter pw = new PrintWriter(new FileWriter(getDataFile()));
            for(int a =  0; a < b.length; a++){
                if(!b[a].equals(toRemove)){
                    pw.println(b[a]);
                }
            }
            pw.close();
        } 
        catch (FileNotFoundException ex) {
            sm.getLogger().log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            sm.getLogger().log(Level.SEVERE, null, ex);
        }
    }
    public abstract void command(CommandSender sender, Command cmd, String label, String[] args);
}
