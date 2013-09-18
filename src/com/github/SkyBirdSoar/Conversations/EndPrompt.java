package com.github.SkyBirdSoar.Conversations;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

public class EndPrompt extends MessagePrompt{

    private Main m;
    public EndPrompt(Main p){
        m = p;
    }
    @Override
    protected Prompt getNextPrompt(ConversationContext cc) {
        return Prompt.END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aYou have successfully submitted your application. Thank you.");
    }
    
}