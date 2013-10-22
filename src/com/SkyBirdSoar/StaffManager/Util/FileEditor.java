package com.SkyBirdSoar.StaffManager.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileEditor {
    public File getFile();
    public List getList() throws FileNotFoundException;
    public void addItem(Object o);
    public void delItem(Object o);
}
