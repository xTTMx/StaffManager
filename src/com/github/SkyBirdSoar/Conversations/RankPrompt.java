package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class RankPrompt extends StringPrompt{

    private Main m;
    public RankPrompt(Main p){
        m = p;
    }
    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor(("&aWhat rank are you applying for?"));
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.p.getName(), true);
        fc.set("application.rank", string);
        m.sm.saveConfig(m.p.getName(), fc, true);
        return new EndPrompt(m);
    }
}