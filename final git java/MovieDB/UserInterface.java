import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
	public static int onlyInteger(Scanner sc){
		int number = 0;
		try	{
			number = sc.nextInt();
			sc.nextLine();
		}
		catch(Exception e){
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			number = onlyInteger(sc);
		}
		return number;
	}

	public static void main(String[] args) throws IOException {
		String title;
		String director;
		int released;
		int preferedAge;
		boolean prgInRun = true;
				
		Scanner sc = new Scanner(System.in);
		MovieHashMap movies = new MovieHashMap(); 
				
		SelectQueries selectQueries  = new SelectQueries();
        movies.setMovies(selectQueries.loadMovies());
        selectQueries.loadPersons(movies);
        selectQueries.loadReviews(movies);
        
		while(prgInRun) {
			
			System.out.println("0 UKONČENIE PROGRAMU");
			System.out.println("1 Pridanie nového filmu");
			System.out.println("2 Upravenie filmu");
			System.out.println("3 Zmazanie filmu");
			System.out.println("4 Pridanie hodnotenia pre vybraný film");
			System.out.println("5 Výpis všetkých filmov");
			System.out.println("6 Vyhľadanie konkrétneho filmu");
			System.out.println("7 Výpis hercov/animátorov, ktorý sa podielajú na viac ako jednom filme");
			System.out.println("8 Vyhladanie filmov podla oblúbeného herca/animátroa");
			System.out.println("9 Uloženie filmu do súboru");
			System.out.println("10 Načítanie filmu zo súboru");

			int caseNumber = onlyInteger(sc);
			switch(caseNumber){
				case 0:
					prgInRun = false;
					movies.saveToDatabase();
				break;
				case 1:
					System.out.println("Zvolte druh filmu");
					System.out.println("1 - Hrany film");
					System.out.println("2 - Animovany film");
					int AnimatedOrFeature = onlyInteger(sc);
					switch(AnimatedOrFeature) {
					case 1:
						System.out.println("Zadaj meno hraneho filmu");
						title=sc.nextLine();
				        System.out.println("Zadaj rezisera");
						director=sc.nextLine();
						System.out.println("Zadaj rok vydania");
						released=sc.nextInt();
						sc.nextLine();
						FeatureFilm ff = new FeatureFilm(title, director, released);
						movies.addMovie(ff);
			    		String actorName ="dummy";
						while ( actorName != "") {
							System.out.println("Zadaj meno a priezisko herca: <Pre ukoncenie stlac ENTER>");
							actorName=sc.nextLine();
							if (actorName!="") {
							  movies.getMovie(title).addPerson(new Actor(actorName));
							}
						}
												
						System.out.println(movies.getMovie(title).displayMovie());
						break;
				
					case 2:
						System.out.println("Zadaj meno animovaneho filmu");
						title = sc.nextLine();
						System.out.println("Zadaj rezisera");
						director = sc.nextLine();
						System.out.println("Zadaj rok vydania");
						released = sc.nextInt();
						sc.nextLine();
						System.out.println("Zadaj preferovany vek divaka");
						preferedAge = sc.nextInt();
						sc.nextLine();
						AnimatedFilm af = new AnimatedFilm(title, director, released, preferedAge);
						movies.addMovie(af);
						
						String animatorName ="dummy";
						while ( animatorName != "") {
							System.out.println("Zadaj meno a priezisko animatora: <Pre ukoncenie stlac ENTER>");
							animatorName=sc.nextLine();
							if (animatorName!="") {
							  movies.getMovie(title).addPerson(new Animator(animatorName));
							}
						}
						System.out.println(movies.getMovie(title).displayMovie());	
						break;
					}
				break;
				
				case 2:
					System.out.println("Zadaj nazov filmu ktory chces upravit");
					title = sc.nextLine();
					System.out.println(movies.displayMovies("movie",title));
					movies.getMovie(title);
					
					if(movies.getMovie(title)instanceof AnimatedFilm) {
						AnimatedFilm myFilm = (AnimatedFilm)movies.getMovie(title);
						String oldTitle = myFilm.getTitle();
						
						System.out.println("Zadaj novy nazov filmu (ak nemenis iba stlac Enter)");
						title = sc.nextLine();				
						
						if (title != "") {
							myFilm.setTitle(title);														
						}
						
						System.out.println("Zmen rezisera " + myFilm.getDirector() + "  (ak nemenis iba stlac Enter)");
						director = sc.nextLine();				
						
						if (director != "") {
							myFilm.setDirector(director);														
						}
						
						System.out.println("Zmen rok vydania " + myFilm.getReleased() + "  (ak nemenis iba stlac Enter)");
						String localValue = sc.nextLine();
						if (localValue != "") {
							myFilm.setReleased(Integer.parseInt(localValue));													
						}
						
						System.out.println("Zmen preferovany rok divaka " + myFilm.getPreferredAge() + "  (ak nemenis iba stlac Enter)");
						localValue = sc.nextLine();				
						
						if (localValue != "") {
							myFilm.setPreferredAge(Integer.parseInt(localValue));														
						}
						
						ArrayList<Animator> myPersons = new ArrayList<Animator>();
						
   						
			    		for (Animator person : myFilm.getPersons() ) {
			    			System.out.println("Zmena animatora  " + person.getName() + "  (ak nemenis iba stlac Enter, Delete - D)");
			    			localValue = sc.nextLine();
			    			
			        	   	switch (localValue) {
			        	   	case "":   
			        	   		myPersons.add(new Animator(person.getName()));
			        	   			break;
			        	   	case "D":  
	        	   			break;
			        	   	case "d":  
	        	   			break;
			        	   			
			        	   	default:  myPersons.add(new Animator(localValue));
			        	   	}
			        	}

						String animatorName ="dummy";
						while ( animatorName != "") {
							System.out.println("Zadaj meno a priezisko animatora: <Pre ukoncenie stlac ENTER>");
							animatorName=sc.nextLine();
							if (animatorName!="") {
							  myPersons.add(new Animator(animatorName));
							}
						}
						
			    		myFilm.setPersons(myPersons);

			    		movies.updateMovie(myFilm,  oldTitle);
						System.out.println("Film upraveny");
						System.out.println(movies.getMovie(myFilm.getTitle()).displayMovie());
												
					}
					else {
						FeatureFilm myFilm = (FeatureFilm)movies.getMovie(title);
						String oldTitle = myFilm.getTitle();
						
						System.out.println("Zadaj novy nazov filmu (ak nemenis iba stlac Enter)");
						title = sc.nextLine();				
						
						if (title != "") {
							myFilm.setTitle(title);														
						}
						
						System.out.println("Zmen rezisera " + myFilm.getDirector() + "  (ak nemenis iba stlac Enter)");
						director = sc.nextLine();				
						
						if (director != "") {
							myFilm.setDirector(director);														
						}
						
						System.out.println("Zmen rok vydania " + myFilm.getReleased() + "  (ak nemenis iba stlac Enter)");
						String localvalue = sc.nextLine();
						if (localvalue != "") {
							myFilm.setReleased(Integer.parseInt(localvalue));													
						}
						
						ArrayList<Actor> myPersons = new ArrayList<Actor>();
						
   						
			    		for (Actor person : myFilm.getPersons() ) {
			    			System.out.println("Zmena herca  " + person.getName() + "  (ak nemenis iba stlac Enter, Delete - D)");
			    			String locallValue = sc.nextLine();
			    			
   		        	   	switch (locallValue) {
			        	   	case "":
			        	   		myPersons.add(new Actor(person.getName()));
			        	   			break;
			        	   	case "D":  
	        	   			break;
			        	   	case "d":  
	        	   			break;
			        	   			
			        	   	default:  myPersons.add(new Actor(locallValue));
			        	   	}
			        	}

						String actorName ="dummy";
						while ( actorName != "") {
							System.out.println("Zadaj meno a priezisko herca: <Pre ukoncenie stlac ENTER>");
							actorName=sc.nextLine();
							if (actorName!="") {
							  myPersons.add(new Actor(actorName));
							}
						}
						
			    		myFilm.setPersons(myPersons);
						
						
						movies.updateMovie(myFilm,  oldTitle);
						System.out.println("Film upraveny");
						System.out.println(movies.getMovie(myFilm.getTitle()).displayMovie());	
					}		
				break;
				
				case 3:
					System.out.println("Zadaj nazov filmu ktory chces zmazat");
					title = sc.nextLine();
					System.out.println("Naozaj chces zmazat film: " + title + " ? Ak ano stlac ENTER");
					String localValue = sc.nextLine();
					if (localValue != "") {
						System.out.println("Zadaj nazov filmu ktory chces zmazat");
						title = sc.nextLine();
						
						movies.removeMovie(title);
						
					}
					
					movies.removeMovie(title);
				break;
				case 4:
					System.out.println("Zadaj nazov filmu ktoremu chces pridat hodnotenia");
					title = sc.nextLine();
					System.out.println(movies.displayMovies("movie", title));
					System.out.println("Zadaj pocet bodov ( af 1-10 / ff 1-5 ");
					int points = sc.nextInt();
					sc.nextLine();
					System.out.println("Zadaj komentar ak nechces stlac ENTER");
					String comment = sc.nextLine();
					if (movies.getMovie(title) instanceof AnimatedFilm) {	
						movies.getMovie(title).addReview(new ReviewNumbers(points, comment));
					} else {
						movies.getMovie(title).addReview(new ReviewStars(points, comment));
					} 
				break;
				case 5:
					System.out.println(movies.displayMovies("all",""));
				break;
				case 6:
					System.out.println("Zadaj nazov filmu");
					title = sc.nextLine();
					System.out.println(movies.displayMovies("movie", title));
				break;
				case 7:
					System.out.println(movies.displayPesonsWithMoreMovies("animator"));
					System.out.println(movies.displayPesonsWithMoreMovies("actor"));
				    
				break;	
				case 8:
					System.out.println("Zvolte druh hladania");
					System.out.println("1 - podla animatora");
					System.out.println("2 - podla herca");
					int typHladania = onlyInteger(sc);
					switch(typHladania) {
					case 1: 
						System.out.println("Zadaj animatora");
						title = sc.nextLine();
						System.out.println(movies.displayMovies("animator", title));
					break;
					case 2: 
						System.out.println("Zadaj herca");
						title = sc.nextLine();
						System.out.println(movies.displayMovies("actor", title));
					break;
					}
				break;
				case 9:
					System.out.println("Zadaj nazov filmu ktory chces ulozit do fajlu");
					title = sc.nextLine();
					Movie myMovie = movies.getMovie(title);
					System.out.println("Zadaj nazov suboru");
					String fileName = sc.nextLine();
                    movies.saveMovieToFile(fileName, myMovie) ;					
					
				break;				
				case 10:
					System.out.println("Zadaj nazov suboru");
					fileName = sc.nextLine();
                    movies.loadMovieFromFile(fileName);
					
				break;				
				default:
					System.out.println("Zadal si zlu volbu *(1-9)");
				break;

			}
		}
	}
}
