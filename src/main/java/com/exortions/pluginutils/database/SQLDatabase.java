package com.exortions.pluginutils.database;

import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * @since 0.4.24.23
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface SQLDatabase {

	SQLDatabaseType getDatabaseType();

	/**
	 * <p>Send query through connection asynchronously in background.
	 * It will not affect TPS, but it will work asynchronously.</p>
	 * <p>Also, database will be updated in delay and data will not be synchronous.
	 * It will be unable to get data from database instantly after update in another line of code.</p>
	 */
	void updateAsync(String query);

	/**
	 * <p>Send query through connection with synchronization with main thread.
	 * It will affect TPS depends of connection quality.</p>
	 */
	Boolean updateSync(String query);

	/**
	 * <p>Data from ResultSet can be get using ResultSetManager from this plugin.</p>
	 */
	ResultSet getResult(String query);

	/**
	 * <p>data values are different depend on type of SQL connection.</p>
	 */
	Connection createConnection(String... data);

	Connection getConnection();

	Boolean isConnected();

	void closeConnection();

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code removeObjectSync("SQLTable", "nick", "Steve", "money");}</p>
	 */
	void removeObjectSync(String table, String keyColumn, Object keyValue, String objectColumn);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code removeObjectAsync("SQLTable", "nick", "Steve", "money");}</p>
	 */
	void removeObjectAsync(String table, String keyColumn, Object keyValue, String objectColumn);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code setObjectSync("SQLTable", "nick", "Steve", "money", 3000);}</p>
	 */
	void setObjectSync(String table, String keyColumn, Object keyValue, String objectColumn, Object objectValue, Boolean hasTablePrimaryKey);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code setObjectAsync("SQLTable", "nick", "Steve", "money", 3000);}</p>
	 */
	void setObjectAsync(String table, String keyColumn, Object keyValue, String objectColumn, Object objectValue, Boolean hasTablePrimaryKey);

	/**
	 * <p>Apache commons lib is required for Pair class.</p>
	 * <p>Left value is column, Right value is value object of column.
	 */
	void setObjectsSync(String table, List<Pair<String, Object>> pairs, Boolean hasTablePrimaryKey);

	/**
	 * <p>Apache commons lib is required for Pair class.</p>
	 * <p>Left value is column, Right value is value object of column.
	 */
	void setObjectsAsync(String table, List<Pair<String, Object>> pairs, Boolean hasTablePrimaryKey);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code getRow("Players", "nick", "Steve");}</p>
	 */
	ResultSet getRow(String table, String keyColumn, String keyValue);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code getRow("Players", "premium", true, 5);}</p>
	 */
	ResultSet getRows(String table, String keyColumn, String keyValue, int limit);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code deleteRow("Players", "nick", "Steve");}</p>
	 */
	void deleteRowSync(String table, String keyColumn, String keyValue);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code deleteRow("Players", "nick", "Steve");}</p>
	 */
	void deleteRowAsync(String table, String keyColumn, String keyValue);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code deleteRow("Players", "premium", true, 5);}</p>
	 */
	void deleteRowsSync(String table, String keyColumn, String keyValue, int limit);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code deleteRow("Players", "premium", true, 5);}</p>
	 */
	void deleteRowsAsync(String table, String keyColumn, String keyValue, int limit);

}
