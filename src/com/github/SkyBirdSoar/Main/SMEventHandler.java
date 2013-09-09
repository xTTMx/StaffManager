package com.github.SkyBirdSoar.Main;

import com.github.SkyBirdSoar.Events.Event_Join;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SMEventHandler implements Listener{
    private StaffManager sm;
    private Event_Join ej;
    public SMEventHandler(StaffManager pl){
        sm = pl;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        ej = new Event_Join(sm);
        ej.onPlayerJoin(e);
    }
    public Event_Join getEventJoinHandler(){
        return ej;
    }
}
