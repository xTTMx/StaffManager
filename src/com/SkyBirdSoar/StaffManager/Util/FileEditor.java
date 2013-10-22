package com.SkyBirdSoar.StaffManager.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileEditor {
    public File getFile();
    public List<String> getList() throws FileNotFoundException;
    public void addItem(String s);
    public void delItem(String s);
}
