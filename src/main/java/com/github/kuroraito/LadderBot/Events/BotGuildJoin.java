package com.github.kuroraito.LadderBot.Events;

import com.github.kuroraito.LadderBot.Database.MySQL;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class BotGuildJoin extends ListenerAdapter {
    public void OnGuildJoin(GuildJoinEvent e){
            Guild g = e.getGuild();
            MySQL.createGuildEntry(g);
    }
}
