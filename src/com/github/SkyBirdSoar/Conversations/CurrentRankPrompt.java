package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class CurrentRankPrompt extends StringPrompt{

    private Main m;
    public CurrentRankPrompt(Main p){
        m = p;
    }
    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aWhat is your current rank?");
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.p.getName(), true);
        fc.set("application.currentrank", string);
        m.sm.saveConfig(m.p.getName(), fc, true);
        return new RankPrompt(m);
    }
    
}