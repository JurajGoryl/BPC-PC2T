import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MovieHashMap {
	private HashMap<String, Movie> movies;
    public HashMap<String, Movie> getMovies() {
		return movies;
	}

	public void setMovies(HashMap<String, Movie> movies) {
		this.movies = movies;
	}

	public MovieHashMap() {
        movies = new HashMap<String, Movie>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getTitle(), movie);
    }

    public void addMovie(AnimatedFilm movie) {
        movies.put(movie.getTitle(), movie);
    }

    public void addMovie(FeatureFilm movie) {
        movies.put(movie.getTitle(), movie);
    }

    
    public void updateMovie(Movie movie, String title) {
    	if (this.movies.containsKey(title)) {
    		this.movies.remove(title);  
    	    this.movies.put(movie.getTitle(), movie);
    	}
    }
    public void removeMovie(String title) {
    	if (this.movies.containsKey(title)){
    		this.movies.remove(title);  
    		System.out.println("Film( " + title +" ) sa vymazal");
    	}
    	else {
    		System.out.println("Film( " + title +" ) sa v databaze filmov nenachadza");	
    	}
    }
    
    public Movie getMovie(String title) {
        return movies.get(title);
    }
    
    public String displayPesonsWithMoreMovies(String value) {
    	StringBuilder moviesStr = new StringBuilder();
    	
    	HashMap<String,Integer> movieCounntsByPerson = new HashMap<String,Integer>();
    	
    	for (Movie movie : movies.values()) {
    		switch (value) {
    		case "actor" :
    			if (movie instanceof FeatureFilm) {
	        		for (Person person : (ArrayList<Person>)movie.getPersons() ) {
	        			if (movieCounntsByPerson.containsKey(person.getName())  ) {
	        				int count = movieCounntsByPerson.get(person.getName());
	        				count++;
	        				movieCounntsByPerson.put(person.getName(), count);
	        			} else {
	        				movieCounntsByPerson.put(person.getName(), 1);
	        			} 
	            	}
    			}
    		break;
    		case "animator" :
    			if (movie instanceof AnimatedFilm) {
	        		for (Person person : (ArrayList<Person>)movie.getPersons() ) {
	        			if (movieCounntsByPerson.containsKey(person.getName())  ) {
	        				int count = movieCounntsByPerson.get(person.getName());
	        				count++;
	        				movieCounntsByPerson.put(person.getName(), count);
	        			} else {
	        				movieCounntsByPerson.put(person.getName(), 1);
	        			}  
	            	}
    				
    			}
        		break;
    		}  
    	}
    	
    	//String filteredMoviesStr = "";
    	System.out.println(movieCounntsByPerson.toString());
    	for (String key : movieCounntsByPerson.keySet()) {
    	   if (movieCounntsByPerson.get(key) > 1 ) {
    		   moviesStr.append(value+ ": " + key + ">>>>>>>").append("\n\n");
    		   moviesStr.append(displayMovies(value, key)).append("\n\n");
    		  //filteredMoviesStr = displayMovies(value, key); 
    		   
    	   }
    	    	
    	}
    	return moviesStr.toString();
    }
    
    public String displayMovies(String type, String value) {
    	StringBuilder moviesStr = new StringBuilder();
    	for (Movie movie : movies.values()) {
    		switch (type) {
	        	case "all":
	        		moviesStr.append(movie.displayMovie()).append("\n\n");	
	        	break;
	        	case "actor": 
	        		for (Person person : (ArrayList<Person>)movie.getPersons() ) {
	            	   	if (value.equals(person.getName())) {
	            	   		moviesStr.append(movie.displayMovie()).append("\n\n");
	            	   	}
	            	}
	        	break;
	        	case "animator":  
	        		for (Person person : (ArrayList<Person>)movie.getPersons() ) {
	            	   	if (value.equals(person.getName())) {
	            	   		moviesStr.append(movie.displayMovie()).append("\n\n");
	            	   	}
	            	}	        	break;
	        	case "movie":  
	        		if (value.equals(movie.getTitle()))
	        			moviesStr.append(movie.displayMovie()).append("\n\n");
	            break;	
    		}
    	}
    	return moviesStr.toString();
    }
       
    public void saveToDatabase() {
		DeleteQueries deleteQueries = new DeleteQueries();
    	deleteQueries.deleteMovies();
    	deleteQueries.deleteReviews();
    	deleteQueries.deletePersons();
	
    	for (Movie movie : movies.values()) {
    		String movieType;

    		if (movie instanceof AnimatedFilm) {
    			movieType = "af";
    		} else {
    			movieType = "ff";
    		}
    		
			ArrayList<Review> myReviews = new ArrayList<Review>();
			myReviews = (ArrayList<Review>)movie.getReviews();
    		
			ArrayList<Person> myPersons = new ArrayList<Person>();
			myPersons = (ArrayList<Person>)movie.getPersons();
			
			int pg;
    		if (movie instanceof AnimatedFilm) {
    			pg = ((AnimatedFilm)movie).getPreferredAge();
    		} else {
    			pg=0;
    		}
    		
    		InsertQueries insertQueries = new InsertQueries();
    		insertQueries.insertMovie(movie.getTitle(), movieType, movie.getDirector(), movie.getReleased(),pg);
    		   			
    		//myReviews = movie.getReviews();
    		for (Review review : myReviews ) {
        	   	insertQueries.insertReview(movie.getTitle(), review.getPoints(), review.getComment());
        	}
    		
    		for (Person person : myPersons ) {
        	   	insertQueries.insertPerson(movie.getTitle(), person.getName());
        	}    		
    	}	
    }
    
	public void saveMovieToFile(String fileName, Movie movie) throws IOException{
		FileWriter fw = new FileWriter(fileName);
		String line;
		if (movie instanceof AnimatedFilm)  {
			line = String.format("af\n");
		} else {
			line = String.format("ff\n");
		}
		fw.write(line);
		fw.flush();
		
		line = String.format("%s\n%s\n%d\n", movie.getTitle(),movie.getDirector(),movie.getReleased());
		fw.write(line);
		fw.flush();
				
		if (movie instanceof AnimatedFilm)  {
			line = String.format("%d\n", ((AnimatedFilm)movie).getPreferredAge());
			fw.write(line);
			fw.flush();
		}
		
		line = String.format("%d\n",movie.getPersons().size());
		fw.write(line);
		
		fw.flush();

		for (Person person : (ArrayList<Person>)movie.getPersons() ) {
			line = String.format("%s\n",person.getName());
			fw.write(line);
			fw.flush();
    	}
		
		line = String.format("%d\n",movie.getReviews().size());
		fw.write(line);
		fw.flush();
		
		for (Review review : (ArrayList<Review>)movie.getReviews() ) {
			line = String.format("%d\n",review.getPoints());
			fw.write(line);
			fw.flush();
			line = String.format("%s\n",review.getComment());
			fw.write(line);
			fw.flush();
    	}
		
		fw.close();
	}
		
	public void loadMovieFromFile(String fileName) {
		//String celyText = "";
		FileReader fr = null;
		BufferedReader in = null;
		 try {
			fr = new FileReader(fileName);
			in = new BufferedReader(fr);
			
			String movieType = in.readLine();
		    String title = in.readLine();
		    String director = in.readLine();
		    int pg=0; 
		    int released = Integer.parseInt(in.readLine()) ;
			if (movieType.equals("af")) {
			  pg = Integer.parseInt(in.readLine()) ;
			}
			
			int personsCnt = Integer.parseInt(in.readLine()) ;
			
			ArrayList<Animator> myAnimators = new ArrayList<Animator>();
			ArrayList<Actor> myActors = new ArrayList<Actor>();
			for (int i=1; i<= personsCnt; i++ ) {
				String name = in.readLine();
				if (movieType.equals("af")) {
					myAnimators.add(new Animator(name));
				} else {
					myActors.add(new Actor(name));
				}	
			}
            			
			int reviewsCnt = Integer.parseInt(in.readLine()) ;
			
			ArrayList<ReviewNumbers> myReviewsN = new ArrayList<ReviewNumbers>();			
			ArrayList<ReviewStars> myReviewsS = new ArrayList<ReviewStars>();
			for (int i=1; i<= reviewsCnt; i++ ) {
				
				int points = Integer.parseInt(in.readLine()) ;
				String comment = in.readLine();
				
				if (movieType.equals("af")) {
					myReviewsN.add(new ReviewNumbers(points, comment));
				} else {
					myReviewsS.add(new ReviewStars(points, comment));
				}	
			}

			if (movieType.equals("af")) {
				movies.put(title, new AnimatedFilm(title, director, released, pg, myAnimators, myReviewsN));
			} else {
				movies.put(title, new FeatureFilm(title, director, released, myActors, myReviewsS));
			}		
		 }
		 catch (IOException e) {
		 }
	}
}
