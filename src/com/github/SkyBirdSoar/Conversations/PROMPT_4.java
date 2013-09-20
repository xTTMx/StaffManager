package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.Prompt;

public class PROMPT_4 extends FixedSetPrompt{
    private Main m;
    public PROMPT_4(Main s){
        super("male", "female", "boy", "girl", "man", "woman");
        m = s;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext cc, String string) {
        FileConfiguration fc = m.sm.getConfig(m.getSender().getName(), true);
        if(string.equals("boy") || string.equals("man") || string.equals("male")){
            string = "Male";
        }
        else{
            string = "Female";
        }
        fc.set("application.gender", string);
        m.sm.saveConfig(m.getSender().getName(), fc, true);
        return m.getNextPrompt(4);
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aWhat is your gender?");
    }
}