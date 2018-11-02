package com.github.kuroraito.LadderBot.Commands;

import com.github.kuroraito.LadderBot.Database.MySQL;
import com.github.kuroraito.LadderBot.Main.Info;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.net.URI;

public class WinCommand extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContentDisplay().startsWith(Info.PREFIX)) {
            String[] args = e.getMessage().getContentDisplay().split(" ");
            if (args[0] == Info.PREFIX + "win" && args[3] == "vs") {
                try {
                    String id = e.getGuild().getId();
                    String list = args[1];
                    String winPlayer = args[2];
                    String losePlayer = args[4];
                    String query = "SELECT * FROM " + id + "_" + list + "WHERE " +
                            "Name = " + winPlayer + ";";
                    ResultSet rs = MySQL.queryStatement(query);

                    int winELO = rs.getInt("Point");

                    query = "SELECT * FROM " + id + "_" + list + "WHERE " +
                            "Name = " + losePlayer + ";";
                    rs = MySQL.queryStatement(query);

                    int loseELO = rs.getInt("Point");

                    int temp = winELO;
                    int power = (winELO - loseELO)/400;
                    winELO += 16*(1 - 1/1+ Math.pow(10, power));

                    power = (loseELO - temp)/400;
                    loseELO += 16*(1 - 1/1+ Math.pow(10, power));
                    query = "UPDATE ONLY " + id + "_" + list + "WHERE Name = " + winPlayer +
                            " SET Point = " + winELO + ";";

                    rs = MySQL.queryStatement(query);

                    query = query = "UPDATE ONLY " + id + "_" + list + "WHERE Name = " + losePlayer +
                            " SET Point = " + loseELO + ";";

                    rs.close();;
                    e.getChannel().sendMessage("Recorded! " + winPlayer + ": " + winELO + ", " + losePlayer + ": " + loseELO).queue();
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}