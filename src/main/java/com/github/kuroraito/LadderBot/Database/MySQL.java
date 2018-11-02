package com.github.kuroraito.LadderBot.Database;

import net.dv8tion.jda.core.entities.Guild;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQL {

    public static Connection connect(){
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            return conn;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static void createGuildEntry(Guild g){
        String name = g.getName();
        String id = g.getId();
        String query = "CREATE TABLE " + id + "_" + "ADMIN( Role varchar(255) )";
        queryStatement(query);
        query = "INSERT INTO " + id + "_" + name + "_ADMIN(Role) VALUES(Admin)";
        queryStatement(query);
    }

    public static ResultSet queryStatement(String stmt){
        try {
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(stmt);
            ResultSet rs = ps.executeQuery();
            return rs;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
