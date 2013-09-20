package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class PROMPT_9 extends BooleanPrompt{
    private Main m;
    public PROMPT_9(Main s){
        m = s;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext cc, boolean bln) {
        if(bln){
            FileConfiguration fc = m.sm.getConfig(m.getSender().getName(), true);
            int a = fc.getInt("application.description.lines") + 1;
            fc.set("application.description.lines", a);
            m.sm.saveConfig(m.getSender().getName(), fc, true);
            return new PROMPT_8(m);
        }
        else{
            return m.getNextPrompt(9);
        }
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aDo you need an extra line?");
    }
}