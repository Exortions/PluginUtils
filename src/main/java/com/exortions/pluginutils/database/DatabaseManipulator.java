package com.exortions.pluginutils.database;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public abstract class DatabaseManipulator {

    private String database;
    private String host;
    private String port;
    private boolean useSSL;

    private String username;
    private String password;

    private String url;

    private Connection connection;

    public DatabaseManipulator(String database, String host, String port, String username, String password, boolean useSSL) {
        this.database = database;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.useSSL = useSSL;

        this.url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL;
    }

    public DatabaseManipulator createConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        return this;
    }

    public ResultSet query(String sql) {
        try {
            return connection.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean execute(String sql) {
        try {
            return connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
