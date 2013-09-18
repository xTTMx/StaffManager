package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;

public class AgePrompt extends NumericPrompt{

    private Main m;
    public AgePrompt(Main s){
        m = s;
    }
    @Override
    protected Prompt acceptValidatedInput(ConversationContext cc, Number number) {
        FileConfiguration fc = m.sm.getConfig(m.p.getName(), true);
        fc.set("application.age", number);
        m.sm.saveConfig(m.p.getName(), fc, true);
        return new TimeZonePrompt(m);
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aWhat is your age?");
    }
    
}
