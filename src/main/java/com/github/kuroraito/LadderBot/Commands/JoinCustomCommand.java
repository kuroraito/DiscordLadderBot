package com.github.kuroraito.LadderBot.Commands;

import com.github.kuroraito.LadderBot.Database.MySQL;
import com.github.kuroraito.LadderBot.Main.Info;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.net.URI;
import java.sql.*;
import java.util.Properties;

public class JoinCustomCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(e.getMessage().getContentDisplay().startsWith(Info.PREFIX)) {
            String[] args = e.getMessage().getContentDisplay().split(" ");
            if (args[0] == Info.PREFIX + "joinCustom") {
                if (args[0] == Info.PREFIX + "join") {
                    String listTitle = args[1];
                    String player = args[2];
                    String playerHandle = args[3];
                    int elo = Integer.parseInt(args[4]);
                    String guildID = e.getGuild().getId();

                    String query = "INSERT INTO " + guildID + "_" + listTitle + "(Discord ID, Name, Point) VALUES(" + player + ", " + playerHandle + ", " + elo + ");";
                    MySQL.queryStatement(query);

                    e.getChannel().sendMessage("Player" + playerHandle + "joined! Your point is " + elo).queue();
                }
            }
        }
    }
}
