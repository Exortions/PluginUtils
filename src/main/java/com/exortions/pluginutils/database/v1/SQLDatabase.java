package com.exortions.pluginutils.database.v1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class SQLDatabase {

    String host;
    String port;
    String database;

    String username;
    String password;

    boolean useSSL;

    Connection connection;

    abstract public boolean isConnected();
    abstract public void connect() throws SQLException, AlreadyConnectedException;
    abstract public void disconnect() throws NotConnectedException;
    abstract public Connection getConnection();

    abstract public void createTable(String var1, List<String> var2, String var3) throws SQLException;

}
