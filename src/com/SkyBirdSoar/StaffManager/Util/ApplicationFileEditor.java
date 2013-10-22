/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.SkyBirdSoar.StaffManager.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationFileEditor implements FileEditor{
    
    private SMPlugin m;
    public ApplicationFileEditor(SMPlugin m){
        this.m = m;
    }

    @Override
    public File getFile() {
        return new File(getFolder(), "applicants.txt");
    }
    
    public File getFolder() {
        return new File(m.getPluginFolder(), "data");
    }

    @Override
    public List getList() throws FileNotFoundException{
        if(getFile().exists()){
            Scanner scan = new Scanner(getFile());
            List<String> l = new ArrayList<>();
            while(scan.hasNextLine()){
                l.add(scan.nextLine());
            }
            return l;
        }
        throw new FileNotFoundException("FileNotFoundException: " + getFile().getAbsolutePath());
    }

    @Override
    public void addItem(Object o) {
        if(o instanceof String){
            try{
                List<String> l = getList();
                PrintWriter pw = new PrintWriter(getFile());
                l.add((String) o);
                for (int a = 0; a < l.size(); a++){
                    pw.print(l.get(a));
                }
                pw.close();
            }
            catch(FileNotFoundException e){
                m.consoleOut("&cError, FileNotFoundException: " + getFile().getAbsolutePath());
            }
        }
        else{
            throw new IllegalArgumentException("Object must be instance of String");
        }
    }

    @Override
    public void delItem(Object o) {
        if(o instanceof String){
            try{
                List<String> l = getList();
                PrintWriter pw = new PrintWriter(getFile());
                for(String s : l){
                    if(!s.equals(o)){
                        pw.print(s);
                    }
                }
                pw.close();
            }
            catch(FileNotFoundException e){
                m.consoleOut("&cError, FileNotFoundException: " + getFile().getAbsolutePath());
            }
        }
        else{
            throw new IllegalArgumentException("Object must be instance of String");
        }
    }

}
