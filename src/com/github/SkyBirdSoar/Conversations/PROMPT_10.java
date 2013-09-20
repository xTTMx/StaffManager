package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class PROMPT_10 extends StringPrompt{
    private Main m;
    public PROMPT_10(Main s){
        m = s;
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aDo you have any staff experience? If so, please elaborate. References are also helpful. EG: [Server: Rank], [Server: Rank]");
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.getSender().getName(), true);
        fc.set("application.staffexperience", string);
        m.sm.saveConfig(m.getSender().getName(), fc, true);
        return m.getNextPrompt(10);
    }
}