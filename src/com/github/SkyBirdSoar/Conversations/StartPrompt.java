package com.github.SkyBirdSoar.Conversations;

import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.PlayerNamePrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class StartPrompt extends PlayerNamePrompt{
    private Main m;
    public StartPrompt(StaffManager pl, Main p){
        super(pl);
        m = p;
    }
    @Override
    protected Prompt acceptValidatedInput(ConversationContext cc, Player player) {
        cc.setSessionData("player", player.getName());
        return new GenderPrompt(m);
    }

    @Override
    public String getPromptText(ConversationContext cc) {
        return m.sm.parseColor("&aPlease input your IGN");
    }
    
}
