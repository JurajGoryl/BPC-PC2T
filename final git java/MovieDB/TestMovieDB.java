import java.util.ArrayList;
import java.util.Scanner;
public class TestMovieDB  {
	
	public static void main(String[] args) {
		System.out.println("Test Review Star **************");
		ReviewStars rs1 = new ReviewStars(5,"1 Super film aj ked niektore sceny boli dlhe");
		System.out.println(rs1.getReview());
		ReviewStars rs2 = new ReviewStars(3,"2 priemerny film nic moc");
		System.out.println(rs2.getReview());
		ReviewStars rs3 = new ReviewStars(1,"prepadak, Rambo je lepsi ako Termos");
		System.out.println(rs3.getReview());
		ReviewStars rs4 = new ReviewStars(4,"4 Vynikajuci film tesim sa na pokracovanie");
		System.out.println(rs4.getReview());
		ReviewStars rs5 = new ReviewStars(5,"5 bomba mega spica");
		System.out.println(rs5.getReview());
		
		ReviewNumbers rn1 = new ReviewNumbers(5,"5 bomba mega spica 1");
		System.out.println(rn1.getReview());
		ReviewNumbers rn2 = new ReviewNumbers(3,"3 bomba mega spica 2");
		System.out.println(rn2.getReview());
		ReviewNumbers rn3 = new ReviewNumbers(4,"4 bomba mega spica 3");
		System.out.println(rn3.getReview());
		
		
		
		ArrayList<ReviewNumbers> reviewsAF = new ArrayList<>(); 
		reviewsAF.add(rn1);
		reviewsAF.add(rn2);
		reviewsAF.add(rn3);
	
		
		System.out.println("Test Aniated film *****  test preffered age***********");
		AnimatedFilm af = new AnimatedFilm("Krtko 1", "Zdenek Miler", 1957, 5);
		System.out.println("Test Aniated film ** VYPIS reviews **************");
		System.out.println(af.displayMovie());
		System.out.println("KONEC AF");
		
		/*
		System.out.println("Test Aniated film ****************");
		 af = new AnimatedFilm("Krtko 1", "Zdenek Miler", 1957, 5, reviewsAF);
		System.out.println("Test Aniated film ** VYPIS reviews **************");
		System.out.println(af.displayReviews());
		System.out.println("KONEC AF");
		
		*/
		
		
		System.out.println("Test featureFilm ****************");
		ArrayList<ReviewStars> reviewsFF = new ArrayList<>();
		reviewsFF.add(rs1);
		reviewsFF.add(rs2); 
		reviewsFF.add(rs3); 
		reviewsFF.add(rs4);
		reviewsFF.add(rs5);
		
		FeatureFilm ff = new FeatureFilm("Terminator 1", "Cameron", 1984, reviewsFF);
		System.out.println(ff.displayReviews());		  
		System.out.println("KONEC FF");
		
		
		
		System.out.println("Test VYPISY ****************");
		System.out.println("Test VYPISY ff****************");
		System.out.println(ff.displayMovie());
		System.out.println("Test VYPISY af****************");
		System.out.println(af.displayMovie());
		System.out.println("Test VYPISY ******KONEC**********");
		
		
		System.out.println("Test ACTOR  **START *************");
		Actor a1 = new Actor("Arnold Schwarzeneger");
		System.out.println(a1.getName());
		
		Actor a2 = new Actor("Silvester Stalone");
		System.out.println(a2.getName());
		
		ff.addPerson(a1);
		ff.addPerson(a2);
		System.out.println("Test FF  ** displayPersons *************");
		System.out.println(ff.displayPersons());
		System.out.println("Test ACTOR  ** displayMovie *************");
		System.out.println(ff.displayMovie());
		System.out.println("Test FF  **END *************");
		
		System.out.println("Test Animators  **START *************");
		Animator an1 = new Animator("Viktor Kubal");
		System.out.println(an1.getName());
		
		Animator an2 = new Animator("Maria Medvecka");
		System.out.println(an2.getName());
		
		af.addPerson(an1);
		af.addPerson(an2);
		
		
		af.addReview(new ReviewNumbers(5));
		
		System.out.println(af.displayMovie());
		System.out.println("Test Anime ******KONEC**********");
		
		
        MovieHashMap movies = new MovieHashMap(); 
		
		movies.addMovie(ff);
		movies.addMovie(af);
		
		System.out.println("Library  ******terminator 1**********");
		System.out.println(movies.getMovie("Terminator 1").displayMovie());
		System.out.println("Library  ******terminator 1******END****");
		
		movies.getMovie("Terminator 1").addReview(new ReviewStars(5,"pecka najlepsia"));
		
		System.out.println("Library  ******terminator 1** new review********");
		System.out.println(movies.getMovie("Terminator 1").displayMovie());
		System.out.println("Library  ******terminator 1******END****");

		
		System.out.println("Library  ****** display all movies********");
		System.out.println(movies.displayMovies("all", ""));
		System.out.println("Library  ******End diplay all movies ******END****");
		
		
		ff.setDirector("Juraj Goryl");
		movies.updateMovie(ff, "Terminator 1");
		
		System.out.println("Library  ****** display all Jurko reziser ********");
		System.out.println(movies.displayMovies("all", ""));
		System.out.println("Library  ******End diplay all movies ******END****");
		
		
		System.out.println("Library  ****zobrayuje hercov ********");
		System.out.println(movies.getMovie("Terminator 1").getPersons());
		System.out.println("Library  ******End diplay all movies ******END****");
		
		
		System.out.println("zobrayujem arnolda");
		System.out.println(movies.displayMovies("actor", "Arnold Schwarzeneger"));
		
	
		
		movies.saveToDatabase();

		
		Scanner sc = new Scanner(System.in);
		System.out.println("Stlac klavesu");
		String title = sc.nextLine();
		title = sc.nextLine();

		
		
		
        MovieHashMap moviesNew = new MovieHashMap(); 

        SelectQueries selectQueries  = new SelectQueries();
        //selectQueries.loadMovies(moviesNew);
//        selectQueries.loadPersons(moviesNew);
        //selectQueries.loadReviews(moviesNew);
        
        
		System.out.println("Library  ****** display all movies from DB nacitane********");
		System.out.println(moviesNew.displayMovies("all", ""));
		System.out.println("Library  ******End diplay all movies ******END****");
        
        
        
		
		
	}
}
