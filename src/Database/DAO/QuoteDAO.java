package Database.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.Quote;
import DataObjects.Topic;
import Database.DatabaseConnection;

// This would generally inherit from a BaseDAO class, which would handle connection management and other common functionality
// However we only have one DAO in this situation, so I have left it as is
public class QuoteDAO {
	private DatabaseConnection connection = DatabaseConnection.getConnection();

	public Quote getRandomQuote() {
		// This query selects random row from the table
		ResultSet results = connection.executeQuery("SELECT * FROM Quotes ORDER BY RANDOM() OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY");
		
		// In case we cannot select a row, we initialize to null
		String quote = null;
		String author = null;
		Topic topic = null;
		try {
			// Grab the quote, author and topic from the result
			results.next();
			quote = results.getString(1);
			author = results.getString(2);
			topic = Topic.valueOf(results.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Return the random quote
		return new Quote(quote, author, topic);
	}
}
