package com.github.kuroraito.LadderBot.Main;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import com.github.kuroraito.LadderBot.Commands.*;

public class LadderBot extends ListenerAdapter {
    public LadderBot() {
        JDABuilder jda = new JDABuilder(AccountType.BOT);
        jda.setToken("NTA1NTU0MjU4OTcyNjM5MjMy.DrVckA.gFkVDw_UNH0osWB3rbpmqs9aq48");
        jda.setAudioEnabled(false);


//        Info.readAdmin();
        jda.addEventListener(new AddTitleCommand());
//        jda.addEventListener(new deleteCommand());
        jda.addEventListener(new JoinCommand());
        jda.addEventListener(new WinCommand());
        jda.addEventListener(new AddList());
        jda.addEventListener(new JoinCustomCommand());
    }
}
