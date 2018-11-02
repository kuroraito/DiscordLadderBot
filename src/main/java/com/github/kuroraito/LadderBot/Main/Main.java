package com.github.kuroraito.LadderBot.Main;

import com.github.kuroraito.LadderBot.Commands.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;

public class Main extends ListenerAdapter {
    public static void main(String[] arguments) throws Exception {
        LadderBot bot = new LadderBot();

    }
}
