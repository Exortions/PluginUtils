package com.exortions.pluginutils.database.v2.mysql;

import java.sql.*;

/**
 * @author MorkaZ, Exortions
 * @since 0.4.24.23
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class MySQLConnection extends MySQLData {

	public MySQLConnection(String host, String port, String databaseName, String user, String password) {
		super(host, port, databaseName, user, password);
		try {
			super.connection = openConnection();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public Connection openConnection() throws SQLException, ClassNotFoundException {
		if (checkConnection()) {
			return connection;
		}

		String connectionURL = "jdbc:mysql://" + super.getHost() + ":" + super.getPort();
		if (getDatabaseName() != null) {
			connectionURL = connectionURL + "/" + this.getDatabaseName();
		}

		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(connectionURL, super.getUser(), super.getPassword());
		return connection;
	}


	public boolean checkConnection() throws SQLException {
		return connection != null && !connection.isClosed();
	}


	public Connection getConnection() {
		return connection;
	}


	public boolean closeConnection() throws SQLException {
		if (connection == null) {
			return false;
		}
		connection.close();
		return true;
	}


	public ResultSet querySQL(String query) throws SQLException,
			ClassNotFoundException {
		if (!checkConnection()) {
			openConnection();
		}
		Statement statement = connection.createStatement();
		return statement.executeQuery(query);
	}


	public int updateSQL(String query) throws SQLException,
			ClassNotFoundException {
		if (!checkConnection()) {
			openConnection();
		}
		Statement statement = connection.createStatement();
		return statement.executeUpdate(query);
	}

}



