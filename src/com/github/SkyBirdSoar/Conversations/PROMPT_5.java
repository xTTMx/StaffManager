package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class PROMPT_5 extends StringPrompt{
    private Main m;
    public PROMPT_5(Main s){
        m = s;
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aWhat is your skype?");
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.getSender().getName(), true);
        fc.set("application.skype", string);
        m.sm.saveConfig(m.getSender().getName(), fc, true);
        return m.getNextPrompt(5);
    }
}