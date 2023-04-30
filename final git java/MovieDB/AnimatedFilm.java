
import java.util.ArrayList;

public class AnimatedFilm extends Movie<ReviewNumbers, Animator> {
	
	private int preferredAge;

	public int getPreferredAge() {
		return preferredAge;
	}

	public void setPreferredAge(int preferredAge) {
		this.preferredAge = preferredAge;
	}

	public AnimatedFilm() {
		super();
		reviews = new ArrayList<ReviewNumbers>();
		persons = new ArrayList<Animator>();
    }		

	public AnimatedFilm(String title, String director, int released, int preferredAge) {
		super(title,director, released);
		reviews = new ArrayList<ReviewNumbers>();
		persons= new ArrayList<Animator>();
		this.setPreferredAge(preferredAge);
	}		

	public AnimatedFilm(String title, String director, int released, int preferredAge, ArrayList<ReviewNumbers> reviews) {
		super(title,director, released);
		this.reviews = new ArrayList<ReviewNumbers>();
		this.persons = new ArrayList<Animator>();
		this.reviews = reviews;
		this.setPreferredAge(preferredAge);
	}

	public AnimatedFilm(String title, String director, int released, int preferredAge, ArrayList<Animator> persons, ArrayList<ReviewNumbers> reviews) {
		super(title,director, released);
		this.reviews = new ArrayList<ReviewNumbers>();
		this.persons = new ArrayList<Animator>();
		this.reviews = reviews;
		this.persons = persons;
		this.setPreferredAge(preferredAge);
	}

	@Override
	public String displayMovie() {
		String header;
		header = String.format("Title: %s, Directed by: %s, Released: %d, PG-%d",getTitle(), getDirector(), getReleased(), getPreferredAge());
        String animatorsInfo;
        animatorsInfo = this.displayPersons();
        String revInfo;
        revInfo = this.displayReviews();
        return String.format("Film Info:\n %s \nAnimators:\n %s\nReviews:\n %s \n",header, animatorsInfo, revInfo);
	}
}
