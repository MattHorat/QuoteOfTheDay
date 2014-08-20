package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// This is a singleton database connection. This allows for the web application to maintain only one connection to the database.
// It also means multiple requests can use that connection at the same time.
public class DatabaseConnection {
	// The singleton database connection object
	private static DatabaseConnection databaseConnection = null;
	// The actual JDBC database connection 
	private Connection connection = null;

	// The database initialization functionality
	private DatabaseConnection() throws ClassNotFoundException, SQLException {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName(driver);
		String url = "jdbc:derby:quoteDB;create=true";
		connection = DriverManager.getConnection(url);
	}

	// Used to request the database connection. If none has been created, it will create one.
	public static DatabaseConnection getConnection() {
		if (databaseConnection == null) {
			try {
				databaseConnection = new DatabaseConnection();
				DatabaseSeeder.seed(databaseConnection);
			} catch (ClassNotFoundException | SQLException exception) {
				exception.printStackTrace();
				// This should handle the exceptions in some way. Probably
				// beyond the purposes of this task, however.
			}
		}
		return databaseConnection;
	}

	// Common functionality for making an SQL Query statement, returning a resultset for the DAO to use
	public ResultSet executeQuery(String query) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return resultSet;
	}

	// Common functionality for making an SQL Update/Insert/Create statement
	public void executeInsert(String insert) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insert);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
