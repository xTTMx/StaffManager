package com.SkyBirdSoar.StaffManager.Util;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

/*  StaffManager
    Copyright (C) 2013  SkyBirdSoar

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public interface SMPlugin {
    /**
     * Sets #config as a FileConfiguration type.
     * @param config The file to set as a FileConfiguration.
     */
    abstract void reloadConfig(File config);
    /**
     * Returns #config as a FileConfiguration
     * @param config File to change to FileConfiguration.
     * @return FileConfiguration.
     */
    public abstract FileConfiguration getConfig(File config);
    /**
     * Returns a current instance of the plugin.
     * @return The Main plugin that implements this interface.
     */
    public abstract SMPlugin getInstance();
    /**
     * Logs to the console
     * @param message Message to log to the console.
     */
    public abstract void consoleOut(String message);
    /**
     * Gets the location of the plugin folder.
     * @return The location of the plugin folder.
     */
    public abstract File getPluginFolder();
}
