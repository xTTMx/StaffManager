package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class PROMPT_8 extends StringPrompt{
    private Main m;
    private int NO_OF_LINES;
    public PROMPT_8(Main s){
        m = s;
        NO_OF_LINES = m.sm.getConfig(m.getSender().getName(), true).getInt("application.description.lines");
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aGive us a description about yourself. &lLINE: " +  NO_OF_LINES);
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.getSender().getName(), true);
        fc.set("application.description." + NO_OF_LINES, string);
        m.sm.saveConfig(m.getSender().getName(), fc, true);
        return m.getNextPrompt(8);
    }
}