package com.github.kuroraito.LadderBot.Commands;

import com.github.kuroraito.LadderBot.Database.MySQL;
import com.github.kuroraito.LadderBot.Main.Info;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.sql.ResultSet;

public class AddList extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContentDisplay().startsWith(Info.PREFIX)) {
            String[] args = e.getMessage().getContentDisplay().split(" ");
            if (args[0] == Info.PREFIX + "addList") {
                String listTitle = args[1];
                String id = e.getGuild().getId();
                String query = "CREATE TABLE " + id + "_" + listTitle + "( Discord ID varchar(255), Name varchar(255), Point int);";
                ResultSet rs = MySQL.queryStatement(query);

                e.getChannel().sendMessage(listTitle + " Ladder is added!").queue();
            }
        }
    }
}
