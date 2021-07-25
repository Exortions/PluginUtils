package com.exortions.pluginutils.database;

import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author MorkaZ, Exortions
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
	 * @param query the query.
	 */
	void updateAsync(String query);

	/**
	 * <p>Send query through connection with synchronization with main thread.
	 * It will affect TPS depends of connection quality.</p>
	 * @param query the query.
	 */
	Boolean updateSync(String query);

	/**
	 * <p>Data from ResultSet can be get using ResultSetManager from this plugin.</p>
	 * @param query the query.
	 */
	ResultSet getResult(String query);

	/**
	 * <p>data values are different depend on type of SQL connection.</p>
	 * @param data The data for the SQL conn.
	 */
	Connection createConnection(String... data);

	/**
	 * Gets the SQL conn.
	 * @return The SQL conn.
	 */
	Connection getConnection();

	/**
	 * @return If the SQL db is connected
	 */
	Boolean isConnected();

	/**
	 * Closes the SQL conn.
	 */
	void closeConnection();

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code removeObjectSync("SQLTable", "nick", "Steve", "money");}</p>
	 * @param table The table.
	 * @param keyColumn The column
	 * @param keyValue The value
	 * @param objectColumn The object
	 */
	void removeObjectSync(String table, String keyColumn, Object keyValue, String objectColumn);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code removeObjectAsync("SQLTable", "nick", "Steve", "money");}</p>
	 * @param table The table.
	 * @param keyColumn The column
	 * @param keyValue The value
	 * @param objectColumn The object
	 */
	void removeObjectAsync(String table, String keyColumn, Object keyValue, String objectColumn);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code setObjectSync("SQLTable", "nick", "Steve", "money", 3000);}</p>
	 * @param table The table.
	 * @param keyColumn The column
	 * @param keyValue The value
	 * @param objectColumn The object
	 * @param objectValue The value
	 * @param hasTablePrimaryKey If it is a PK
	 */
	void setObjectSync(String table, String keyColumn, Object keyValue, String objectColumn, Object objectValue, Boolean hasTablePrimaryKey);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code setObjectAsync("SQLTable", "nick", "Steve", "money", 3000);}</p>
	 * @param table The table.
	 * @param keyColumn The column
	 * @param keyValue The value
	 * @param objectColumn The object
	 * @param objectValue The value
	 * @param hasTablePrimaryKey If it is a PK
	 */
	void setObjectAsync(String table, String keyColumn, Object keyValue, String objectColumn, Object objectValue, Boolean hasTablePrimaryKey);

	/**
	 * <p>Apache commons lib is required for Pair class.</p>
	 * <p>Left value is column, Right value is value object of column.
	 * @param table The table.
	 * @param pairs The pairs for str and obj
	 * @param hasTablePrimaryKey If it is a PK
	 */
	void setObjectsSync(String table, List<Pair<String, Object>> pairs, Boolean hasTablePrimaryKey);

	/**
	 * <p>Apache commons lib is required for Pair class.</p>
	 * <p>Left value is column, Right value is value object of column.
	 * @param table The table.
	 * @param pairs The pairs for str and obj
	 * @param hasTablePrimaryKey If it is a PK
	 */
	void setObjectsAsync(String table, List<Pair<String, Object>> pairs, Boolean hasTablePrimaryKey);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code getRow("Players", "nick", "Steve");}</p>
	 * @param table The table.
	 * @param keyColumn The column.
	 * @param keyValue The value.
	 */
	ResultSet getRow(String table, String keyColumn, String keyValue);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code getRow("Players", "premium", true, 5);}</p>
	 * @param table The table.
	 * @param keyColumn The column.
	 * @param keyValue The value.
	 * @param limit The limit
	 */
	ResultSet getRows(String table, String keyColumn, String keyValue, int limit);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code deleteRow("Players", "nick", "Steve");}</p>
	 * @param table The table.
	 * @param keyColumn The column.
	 * @param keyValue The value.
	 */
	void deleteRowSync(String table, String keyColumn, String keyValue);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code deleteRow("Players", "nick", "Steve");}</p>
	 * @param table The table.
	 * @param keyColumn The column.
	 * @param keyValue The value.
	 */
	void deleteRowAsync(String table, String keyColumn, String keyValue);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code deleteRow("Players", "premium", true, 5);}</p>
	 * @param table The table.
	 * @param keyColumn The column.
	 * @param keyValue The value.
	 * @param limit The limit
	 */
	void deleteRowsSync(String table, String keyColumn, String keyValue, int limit);

	/**
	 * <p>Keys are used to find correct row.</p>
	 * <p>{@code deleteRow("Players", "premium", true, 5);}</p>
	 * @param table The table.
	 * @param keyColumn The column.
	 * @param keyValue The value.
	 * @param limit The limit
	 */
	void deleteRowsAsync(String table, String keyColumn, String keyValue, int limit);

}
