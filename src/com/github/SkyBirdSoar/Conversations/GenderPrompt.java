package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.Prompt;

public class GenderPrompt extends FixedSetPrompt{
    private Main m;
    public GenderPrompt(Main p){
        super("Male", "Female");
        m = p;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.p.getName(), true);
        fc.set("application.gender", string);
        m.sm.saveConfig(m.p.getName(), fc, true);
        return new AgePrompt(m);
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aWhat is your gender?");
    }
    @Override
    protected String getFailedValidationText(ConversationContext cc, String string){
        return "&c&lERROR:&c Invalid Gender";
    }
}
