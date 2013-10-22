package com.SkyBirdSoar.StaffManager.Main;

import com.SkyBirdSoar.StaffManager.Events.Event_Join;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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

public class EvtHandler implements Listener{
    private StaffManager m;
    public EvtHandler(StaffManager m){
        this.m = m;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Event_Join ej = new Event_Join(m);
        ej.onPlayerJoin(e);
    }
}
