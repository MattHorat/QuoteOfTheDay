package Database;

import DataObjects.Topic;

// Seed the database with data
public class DatabaseSeeder {

	public static void seed(DatabaseConnection databaseConnection) {
		databaseConnection.executeInsert("DROP TABLE Quotes");
		databaseConnection.executeInsert("CREATE TABLE Quotes (" +
												"quote varchar(255)," +
												"author varchar(255)," +
												"type varchar(255))");
		insertRecord("How beautiful it is to excel, and the goodness of giving from your heart.", "Robert Mondavi", Topic.Love, databaseConnection);
		insertRecord("Love is friendship set on fire.", "Jeremy Taylor", Topic.Love, databaseConnection);
		insertRecord("Artists are, above all, men who want to become inhuman.", "Guillaume Apollinaire", Topic.Philosophy, databaseConnection);
		insertRecord("Communism is like one big phone company.", "Lenny Bruce", Topic.Politics, databaseConnection);
		insertRecord("Work isnt to make money, you work to justify life.", "Marc Chagall", Topic.Philosophy, databaseConnection);
		insertRecord("I am... a mushroom, On whom the dew of heaven drops now and then.", "John Ford", Topic.Other, databaseConnection);
		insertRecord("Between two evils, I always pick the one I never tried before.", "Mae West", Topic.Other, databaseConnection);
		insertRecord("Some cause happiness wherever they go; others whenever they go.", "Oscar Wilde", Topic.Psychology, databaseConnection);
	}
	
	private static void insertRecord(String quote, String author, Topic topic, DatabaseConnection databaseConnection) {
		databaseConnection.executeInsert("INSERT INTO Quotes VALUES (" +
				"'" + quote + "'," +
				"'" + author + "'," +
				"'" + topic + "')");
	}

}
