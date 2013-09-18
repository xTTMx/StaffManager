package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class StaffExperiencePrompt extends StringPrompt{

    private Main m;
    public StaffExperiencePrompt(Main o){
        m = o;
    }
    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aDo you have any staff experience? If so, tell us! eg. [Server]-[Rank], [Next Server]-[Rank]...");
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.p.getName(), true);
        fc.set("application.staffexperience", string);
        m.sm.saveConfig(m.p.getName(), fc, true);
        return new CurrentRankPrompt(m);
    }
    
}
