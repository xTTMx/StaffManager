package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class PROMPT_6 extends StringPrompt{
    private Main m;
    public PROMPT_6(Main s){
        m = s;
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aWhat is your current rank?");
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.getSender().getName(), true);
        fc.set("application.rank.current", string);
        m.sm.saveConfig(m.getSender().getName(), fc, true);
        return m.getNextPrompt(6);
    }
}