package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class SkypePrompt extends StringPrompt{
    private Main m;
    public SkypePrompt(Main o){
        m = o;
    }
    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aWhat is your skype? &cNote: In no way is this data going to be released to the public.");
    }

    @Override
    public Prompt acceptInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.p.getName(), true);
        fc.set("application.skype", string);
        fc.set("application.desc.lines", 0);
        m.sm.saveConfig(m.p.getName(), fc, true);
        return new DescriptionPrompt(m);
    }
    
}
