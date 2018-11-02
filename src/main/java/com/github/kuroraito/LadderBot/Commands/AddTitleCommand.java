package com.github.kuroraito.LadderBot.Commands;

import com.github.kuroraito.LadderBot.Main.Info;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import com.github.kuroraito.LadderBot.Database.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddTitleCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(e.getMessage().getContentDisplay().startsWith(Info.PREFIX)) {
            String[] args = e.getMessage().getContentDisplay().split(" ");
            if (args[0] == Info.PREFIX + "addTitle") {
                String title = args[1];
                String query = "INSERT INTO ADMIN(Role) VALUES('" + title + "');";
                ResultSet rs = MySQL.queryStatement(query);

                e.getChannel().sendMessage(title + " has been added as admin!").queue();

            }
        }
    }
}
