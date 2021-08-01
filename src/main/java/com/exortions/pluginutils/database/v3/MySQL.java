package com.exortions.pluginutils.database.v3;

import lombok.Getter;

import java.sql.*;
import java.util.List;

@Getter
public class MySQL {

    private final String host;
    private final String port;
    private final String database;

    private final String username;
    private final String password;

    private final String url;

    private Connection connection;

    public MySQL(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;

        url = "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

    public boolean isConnected() {
        return (connection != null);
    }

    public boolean connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean createTable(String name, List<String> vars) {
        String sql = "CREATE TABLE IF NOT EXISTS " + name + "(";
        for (String var : vars) {
            sql = sql.concat(var + ",");
        }
        sql = sql.substring(0, sql.length()-1);
        sql = sql.concat("));");
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getResult(String table, String column, String input) {
        String sql = "SELECT * FROM " + table + " WHERE " + column + "=" + input;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, column);
            ResultSet res = stmt.executeQuery();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
