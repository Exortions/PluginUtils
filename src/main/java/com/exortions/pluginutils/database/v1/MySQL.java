package com.exortions.pluginutils.database.v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQL extends SQLDatabase {

    public MySQL(String host, String port, String database, String username, String password, boolean useSSL) {
        this.host = host;
        this.port = port;
        this.database = database;

        this.username = username;
        this.password = password;

        this.useSSL = useSSL;
    }

    @Override
    public boolean isConnected() {
        return (connection != null);
    }

    @Override
    public void connect() throws SQLException, AlreadyConnectedException {
        if (!isConnected()) connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL, username, password); else throw new AlreadyConnectedException("Already connected to " + "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL + "!");
    }

    @Override
    public void disconnect() throws NotConnectedException {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else throw new NotConnectedException("Not connected to " + "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL + "!");
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void createTable(String table, List<String> chars, String primaryKey) throws SQLException {
        String statement = "CREATE TABLE IF NOT EXISTS " + table + " (";
        for (String s : chars) {
            statement = statement.concat(s + ",");
        }
        statement = statement.concat("PRIMARY KEY (" + primaryKey + "))");
        PreparedStatement ps = connection.prepareStatement(statement);
        ps.executeUpdate();
    }
}
