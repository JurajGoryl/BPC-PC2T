import java.util.ArrayList;
import java.util.Collections;

public abstract class Movie<T extends Review, V extends Person> {
	
	private String title;
	private String director;
	private int released;
	protected ArrayList<T> reviews;
	protected ArrayList<V> persons;
	
	public Movie() {
		this.reviews = new ArrayList<>();	
	}
	
	public Movie(String title, String director, int released) {
		this.setTitle(title);
		this.setDirector(director);
		this.setReleased(released);
		this.reviews = new ArrayList<>();
		this.persons = new ArrayList<>();	
	}
	
	public int getReleased() {
		return released;
	}

	public void setReleased(int released) {
		this.released = released;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	public void addReview(T review) {
	   this.reviews.add(review);
	}	
	
	public void addPerson(V person) {
		this.persons.add(person);
	}	
	
	public String displayReviews() {
		Collections.sort(getReviews());
		return getReviews().toString();
	}
	
	public String displayPersons() {
		return getPersons().toString();
	}
	
	public ArrayList<T> getReviews() {
		return reviews;
	}
	public void setReviews(ArrayList<T> reviews) {
		this.reviews = reviews;    
	}
	
	public ArrayList<V> getPersons() {
		return persons;
	}
	
	public void setPersons(ArrayList<V> persons) {
		this.persons = persons;    
	}

	public String displayMovie() {
		return String.format("Title: %s, Directed by: %s, Released: %d",getTitle(), getDirector(), getReleased());	
	}
}
