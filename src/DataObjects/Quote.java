package DataObjects;

public class Quote {
	public String quote;
	public String author;
	public Topic topic;

	public Quote(String quote, String author, Topic topic) {
		this.quote = quote;
		this.author = author;
		this.topic = topic;
	}
}
