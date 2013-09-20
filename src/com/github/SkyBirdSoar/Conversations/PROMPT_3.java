package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;

public class PROMPT_3 extends NumericPrompt{
    private Main m;
    public PROMPT_3(Main p){
        m = p;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext cc, Number number) {
        FileConfiguration fc = m.sm.getConfig(m.getSender().getName(), true);
        fc.set("application.age", number);
        m.sm.saveConfig(m.getSender().getName(), fc, true);
        return m.getNextPrompt(3);
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aPlease enter your age.");
    }
    
    @Override
    protected String getInputNotNumericText(ConversationContext cc, String invalidInput){
        return m.sm.parseColor("&cInvalid number: " + invalidInput);
    }
}