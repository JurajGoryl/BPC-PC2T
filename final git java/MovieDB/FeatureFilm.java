import java.util.ArrayList;


public class FeatureFilm extends Movie<ReviewStars,Actor> {
	
	public FeatureFilm() {
		super();
		reviews = new ArrayList<ReviewStars>();
		persons = new ArrayList<Actor>();
	}
	
	public FeatureFilm(String title, String director, int released) {
		super(title,director, released);
		reviews = new ArrayList<ReviewStars>();
		persons = new ArrayList<Actor>();
	}	
	
	public FeatureFilm(String title, String director, int released, ArrayList<ReviewStars>reviews) {
		super(title,director, released);
		this.reviews = new ArrayList<ReviewStars>();
		this.persons = new ArrayList<Actor>();
		this.reviews = reviews;
	}
	
	public FeatureFilm(String title, String director, int released, ArrayList<Actor>persons, ArrayList<ReviewStars>reviews) {
		super(title,director, released);
		this.reviews = new ArrayList<ReviewStars>();
		this.persons = new ArrayList<Actor>();
		this.reviews = reviews;
		this.persons = persons;	
	}
	
	@Override
	public String displayMovie() {
		String header;
		header = String.format("Title: %s, Directed by: %s, Released: %d",getTitle(), getDirector(), getReleased());
		String actorsInfo;
        actorsInfo = this.displayPersons();
        String revInfo;
        revInfo = this.displayReviews();
        return String.format("Film Info:\n %s \nActors:\n %s\nReviews:\n %s \n",header, actorsInfo, revInfo);	
	}		
}


