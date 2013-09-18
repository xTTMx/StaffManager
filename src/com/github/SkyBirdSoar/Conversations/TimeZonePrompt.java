package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class TimeZonePrompt extends StringPrompt{

    private Main m;
    public TimeZonePrompt(Main s){
        m = s;
    }
    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aWhat is your timezone? Eg. GMT+1, UTC-7, etc.");
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.p.getName(), true);
        fc.set("application.timezone", string);
        m.sm.saveConfig(m.p.getName(), fc, true);
        return new SkypePrompt(m);
    }
    
}
