package com.github.SkyBirdSoar.Conversations;

//CREDITS

import com.github.SkyBirdSoar.Main.StaffManager;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.PluginNameConversationPrefix;
import org.bukkit.entity.Player;

//https://docs.google.com/document/d/1ofTMA6dv7Vk3v4kKhLTC1sUooHgTq71ti44AI09OsNM/edit?pli=1

public class Main {
    protected StaffManager sm;
    protected Player p;
    public Main(StaffManager pl){
        sm = pl;
    }
    public void converse(Player player){
        ConversationFactory cf = new ConversationFactory(sm)
           .withModality(true)
           .withEscapeSequence("end")
           .thatExcludesNonPlayersWithMessage("Sorry, only players can apply")
           .withPrefix(new PluginNameConversationPrefix(sm))
           .withLocalEcho(false)
           .withTimeout(1200)
           .withFirstPrompt(new WelcomePrompt(sm, this));
        cf.buildConversation(player).begin();
        p = player;
    }
}
