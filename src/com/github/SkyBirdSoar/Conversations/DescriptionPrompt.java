package com.github.SkyBirdSoar.Conversations;

import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class DescriptionPrompt extends StringPrompt{
    private StaffManager sm;
    private Main p;
    public DescriptionPrompt(Main m){
        sm = m.sm;
        p = m;
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return p.sm.parseColor("&aGive us a description of yourself. &lNOTE: Don't worry, we will give you more lines if needed.");
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = p.sm.getConfig(p.p.getName(), true);
        int line = fc.getInt("application.desc.lines");
        fc.set("application.desc." + line, string);
        p.sm.saveConfig(p.p.getName(), fc, true);
        return new MoreLinePrompt(p);
    }
    
}
