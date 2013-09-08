package com.github.SkyBirdSoar.StaffManager;

import com.github.SkyBirdSoar.Events.Event_Join;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SMEventHandler implements Listener{
    private StaffManager sm;
    public SMEventHandler(StaffManager pl){
        sm = pl;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Event_Join ej = new Event_Join(sm);
        ej.onPlayerJoin(e);
    }
}
