package com.github.kuroraito.LadderBot.Commands;

import com.github.kuroraito.LadderBot.Database.MySQL;
import com.github.kuroraito.LadderBot.Main.Info;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JoinCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(e.getMessage().getContentDisplay().startsWith(Info.PREFIX)) {
            String[] args = e.getMessage().getContentDisplay().split(" ");
            if (args[0] == Info.PREFIX + "join") {
                String listTitle = args[1];
                String player = args[2];
                String playerHandle = args[3];
                String guildID = e.getGuild().getId();

                String query = "INSERT INTO " + guildID + "_" + listTitle + "(Discord ID, Name, Point) VALUES('" + player + "', '" + playerHandle + "', 1500);";
                MySQL.queryStatement(query);

                e.getChannel().sendMessage("Player" + playerHandle + "joined!").queue();
            }
        }
    }
}
