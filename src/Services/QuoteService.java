package Services;

import DataObjects.Quote;
import Database.DAO.QuoteDAO;

// The service layer implementation for obtaining a quote. Requests a random quote from the DAO.
public class QuoteService {
	public Quote getRandomQuote() {
		QuoteDAO quoteDAO = new QuoteDAO();
		return quoteDAO.getRandomQuote();
	}
}
