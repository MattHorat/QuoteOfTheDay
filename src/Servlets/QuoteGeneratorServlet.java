package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataObjects.Quote;
import Services.QuoteService;

// The Servlet called upon entering the home page of the application
public class QuoteGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 8108038122848807074L;
	// We store the number of views so that we can change the text every 10th visit
	public static int views = 0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Increment the views and set the context for the output
		views++;
		response.setContentType("text/html");

		// Grab the response writer so we can output text
		PrintWriter output = response.getWriter();

		// If we are the lucky 10th visitor, we get an extra special message
		// Otherwise we request a random quote from the quote service
		if (views % 10 == 0) {
			views = 0;
			printAlternateText(output);
		} else {
			QuoteService quoteService = new QuoteService();
			Quote randomQuote = quoteService.getRandomQuote();
			// Output the random quote to the page
			output.println("<html>");
			output.println(randomQuote.quote);
			output.println("<br>Written By: " + randomQuote.author);
			output.println("<br>Filed under: " + randomQuote.topic);
			output.println("</html>");
		}
	}

	// Because it is long and unwieldy, I have moved the quote to here. Normally I would read it
	// from a text file/the database, but I thought that was a little unnecessary for this task.
	private void printAlternateText(PrintWriter output) {
		output.println("<p>Dear Sir:</p>");
		output.println("<p>I have been requested by the Nigerian National Petroleum Company to contact you"
				+ " for assistance in resolving a matter. The Nigerian National Petroleum Company has recently"
				+ " concluded a large number of contracts for oil exploration in the sub-Sahara region. The contracts "
				+ "have immediately produced moneys equaling US$40,000,000. The Nigerian National Petroleum Company is "
				+ "desirous of oil exploration in other parts of the world, however, because of certain regulations of "
				+ "the Nigerian Government, it is unable to move these funds to another region.</p>");
		output.println("<p>You assistance is requested as a non-Nigerian citizen to assist the Nigerian National"
				+ " Petroleum Company, and also the Central Bank of Nigeria, in moving these funds out of Nigeria. If"
				+ " the funds can be transferred to your name, in your United States account, then you can forward the"
				+ " funds as directed by the Nigerian National Petroleum Company. In exchange for your accommodating"
				+ " services, the Nigerian National Petroleum Company would agree to allow you to retain 10%, or US$4 million"
				+ " of this amount.</p>");
		output.println("<p>However, to be a legitimate transferee of these moneys according to Nigerian law, you must presently"
				+ " be a depositor of at least US$100,000 in a Nigerian bank which is regulated by the Central Bank of Nigeria.</p>");
		output.println("<p>If it will be possible for you to assist us, we would be most grateful. We suggest that you meet with us"
				+ " in person in Lagos, and that during your visit I introduce you to the representatives of the Nigerian National"
				+ " Petroleum Company, as well as with certain officials of the Central Bank of Nigeria.</p>");
		output.println("<p>Please call me at your earliest convenience at 18-467-4975. Time is of the essence in this matter;"
				+ " very quickly the Nigerian Government will realize that the Central Bank is maintaining this amount on deposit,"
				+ " and attempt to levy certain depository taxes on it.</p>");
		output.println("<p>Yours truly,</p>");
		output.println("<p>Prince Alyusi Islassis</p>");
	}
}
