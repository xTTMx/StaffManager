package com.github.SkyBirdSoar.Conversations;

import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class MoreLinePrompt extends BooleanPrompt{

    private Main m;
    public MoreLinePrompt(Main p){
        m = p;
    }
    @Override
    protected Prompt acceptValidatedInput(ConversationContext cc, boolean bln) {
        if(bln){
            m.sm.getConfig(this.m.p.getName(), true).set("application.desc.lines", m.sm.getConfig(this.m.p.getName(), true).getInt("application.lines") + 1);
            return new DescriptionPrompt(m);
        }
        return new StaffExperiencePrompt(m);
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aDo you need more lines? [True/false]");
    }
    
}
