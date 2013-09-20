package com.github.SkyBirdSoar.Conversations;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

public class PROMPT_END extends MessagePrompt{

    private Main m;
    public PROMPT_END(Main p){
        m = p;
    }
    @Override
    protected Prompt getNextPrompt(ConversationContext cc) {
        return Prompt.END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aThank you for submitting your application! It will be reviewed as soon as possible :)");
    }
    
}