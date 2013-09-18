package com.github.SkyBirdSoar.Conversations;

import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

public class WelcomePrompt extends MessagePrompt{

    private StaffManager sm;
    private Main m;
    public WelcomePrompt(StaffManager pl, Main p){
        sm = pl;
        m = p;
    }
    @Override
    protected Prompt getNextPrompt(ConversationContext cc) {
        return new StartPrompt(sm, m);
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aYou have initiated the application process.");
    }
    
}
