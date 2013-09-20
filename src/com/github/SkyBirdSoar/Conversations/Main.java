package com.github.SkyBirdSoar.Conversations;

//CREDITS

import com.github.SkyBirdSoar.Main.StaffManager;
import java.io.File;
import java.util.Set;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.PluginNameConversationPrefix;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

//https://docs.google.com/document/d/1ofTMA6dv7Vk3v4kKhLTC1sUooHgTq71ti44AI09OsNM/edit?pli=1

public class Main {
    protected StaffManager sm;
    protected Player p;
    protected ConsoleCommandSender c;
    protected final int TOTAL_NO_OF_PROMPTS = 10 - 1;
    public Main(StaffManager pl){
        sm = pl;
        generateOption();
    }
    public void converse(Player player){
        if(checkEnabled()){
            ConversationFactory cf = new ConversationFactory(sm)
               .withModality(true)
               .withEscapeSequence("end")
               .withPrefix(new PluginNameConversationPrefix(sm))
               .withLocalEcho(false)
               .withTimeout(1200)
               .withFirstPrompt(new PROMPT_1(this));
                cf.buildConversation(player).begin();
                p = player;
        }
        else{
            player.sendMessage(sm.parseColor("&cSubmitting applications is disabled at this point."));
        }
    }
    public void converse(ConsoleCommandSender s){
        if(checkEnabled()){
            ConversationFactory cf = new ConversationFactory(sm)
               .withModality(true)
               .withEscapeSequence("end")
               .withPrefix(new PluginNameConversationPrefix(sm))
               .withLocalEcho(false)
               .withTimeout(1200)
               .withFirstPrompt(new PROMPT_1(this));
            cf.buildConversation(s).begin();
            c = s;
        }
        else{
            s.sendMessage(sm.parseColor("&cSubmitting applications not allowed."));
        }
    }
    private void generateOption(){
        File file = sm.getFile("application", false);
        if(file.exists()){
            sm.getServer().getConsoleSender().sendMessage(sm.parseColor("&c[&bStaff&3Manager&c] &aLoading applications.yml!"));
        }
        else{
            FileConfiguration fc = sm.getConfig("application.yml", false);
            //IGN
            fc.set("PROMPT_2", false);
            //AGE
            fc.set("PROMPT_3", true);
            //GENDER
            fc.set("PROMPT_4", true);
            //SKYPE
            fc.set("PROMPT_5", true);
            //CURRENT RANK
            fc.set("PROMPT_6", true);
            //RANK APPLYING FOR
            fc.set("PROMPT_7", true);
            //BRIEF DESCRIPTION OF YOURSELF
            fc.set("PROMPT_8", true);
            //EXTRA LINE PROMPT
            fc.set("PROMPT_9", true);
            //STAFF EXPERIENCE PROMPT
            fc.set("PROMPT_10", true);
            sm.saveConfig("application", fc, false);
        }
    }
    public CommandSender getSender(){
        if(p != null){
            return p;
        }
        if(c != null){
            return c;
        }
        else{
            throw new IllegalStateException("Nobody is writing an application.");
        }
    }
    public int howManyPrompts(){
        FileConfiguration fc = sm.getConfig("application", false);
        Set<String> keys = fc.getKeys(false);
        int prompts = keys.size();
        int prompts1 = 0;
        String[] s = new String[prompts];
        s = keys.toArray(s);
        for(int a = 0; a < s.length; a++){
            boolean needed = fc.getBoolean(s[a]);
            if(needed){
                prompts1++;
            }
        }
        return prompts1;
    }
    public Prompt getNextPrompt(int i){
        i += 1;
        FileConfiguration fc = sm.getConfig("application", false);
        for(int a = 0; a <= TOTAL_NO_OF_PROMPTS + 1; a++){
            boolean temp = fc.getBoolean("PROMPT_" + a);
            if(temp){
                if (a >= i){
                    switch(a){
                        case 1:
                            return new PROMPT_1(this);
                        case 2:
                            return new PROMPT_2(this);
                        case 3:
                            return new PROMPT_3(this);
                        case 4:
                            return new PROMPT_4(this);
                        case 5:
                            return new PROMPT_5(this);
                        case 6:
                            return new PROMPT_6(this);
                        case 7:
                            return new PROMPT_7(this);
                        case 8:
                            return new PROMPT_8(this);
                        case 9:
                            return new PROMPT_9(this);
                        case 10:
                            return new PROMPT_10(this);
                    }
                }
            }
        }
        
        return new PROMPT_END(this);
    }
    private boolean checkEnabled(){
        return true;
    }
}
