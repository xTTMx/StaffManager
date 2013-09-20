package com.github.SkyBirdSoar.Conversations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.PlayerNamePrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class PROMPT_2 extends PlayerNamePrompt{

    private Main m;
    public PROMPT_2(Main s){
        super(s.sm);
        m = s;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext cc, Player player) {
        FileConfiguration fc = m.sm.getConfig(player.getName(), true);
        fc.set("application.ign", player.getName());
        m.sm.saveConfig(player.getName(), fc, true);
        return m.getNextPrompt(2);
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return message();
    }
    private String message(){
        String message = "&aWhat is your IGN?";
        message = m.sm.parseColor(message);
        return message;
    }
}