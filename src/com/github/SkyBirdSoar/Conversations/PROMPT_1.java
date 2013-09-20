package com.github.SkyBirdSoar.Conversations;

import java.util.Set;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;
/**
 * First Prompt.
 * Tells the player a short description about what do they have to do.
 */
public class PROMPT_1 extends MessagePrompt{

    private Main m;
    private int NO_OF_PROMPTS;
    public PROMPT_1(Main s){
        m = s;
    }
    @Override
    protected Prompt getNextPrompt(ConversationContext cc) {
        return m.getNextPrompt(1);
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return message();
    }
    private String message(){
        String message = "&cYou do not need to submit an application form in game.";
        NO_OF_PROMPTS = m.howManyPrompts();
        if(NO_OF_PROMPTS > 0){
            message =  "&d&lSubmit Application Form. "
                + "&aJust follow the prompts given to you and write down accurate information. To stop this conversation, type &c\"end\"&a. "
                + "&aThere are a total of &l" + m.TOTAL_NO_OF_PROMPTS + " &aquestions. "
                + "&aYou are required to answer &l" + NO_OF_PROMPTS + " &aquestion(s). "
                + "&aPlease answer every question truthfully.";
        }
        message = m.sm.parseColor(message);
        return message;
    }
}