import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SelectQueries {
  public SelectQueries() {}
  
  public void performPreparedStatementSelect(String selectQuery) {
    if (selectQuery == null) {
      throw new NullPointerException("query must not be null!");
    } else if (selectQuery.isEmpty()) {
      throw new IllegalArgumentException("query must not be empty!");
    }
    Connection conn = DBConnection.getDBConnection();
    try (PreparedStatement prStmt = conn.prepareStatement(selectQuery);
        ResultSet rs = prStmt.executeQuery()) {
      while (rs.next()) {
        System.out.println(rs.getString("id_user") + " : " + rs.getString("email") + ", "
            + rs.getString("name") + ", " + rs.getString("surname") + ", " + rs.getString("age")
            + ", " + rs.getString("salary"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
 
  public void performStatementSelect(String selectQuery) {
    if (selectQuery == null) {
      throw new NullPointerException("query must not be null!");
    } else if (selectQuery.isEmpty()) {
      throw new IllegalArgumentException("query must not be empty!");
    }

    Connection conn = DBConnection.getDBConnection();
    try (Statement prStmt = conn.createStatement();
        ResultSet rs = prStmt.executeQuery(selectQuery)) {
      while (rs.next()) {
        System.out.println(rs.getString("id_user") + " : " + rs.getString("email") + ", "
            + rs.getString("name") + ", " + rs.getString("surname") + ", " + rs.getString("age")
            + ", " + rs.getString("salary"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void printMovieWithName(String title) {
    Connection conn = DBConnection.getDBConnection();
    String movieWithActor = "SELECT * FROM movie WHERE title = ?";

    try (PreparedStatement prStmt = conn.prepareStatement(movieWithActor);) {
      prStmt.setString(1, title);
      ResultSet rs = prStmt.executeQuery();
      if (rs.next())
        System.out.println("film " + rs.getString("title") + " je typu: " + rs.getString("type"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public HashMap<String, Movie> loadMovies() {
	  HashMap<String, Movie> movies = new HashMap<String, Movie>(); 
	  
	  Connection conn = DBConnection.getDBConnection();
	  String selectMovie = "SELECT title, movieType, director, released, preferdAge FROM movies";
		    try (PreparedStatement prStmt = conn.prepareStatement(selectMovie )) {
		      ResultSet rs = prStmt.executeQuery();
		      while (rs.next()) {
		    	                                
                switch (rs.getString("movieType"))  {
                case "af" : 
                			AnimatedFilm af = new AnimatedFilm(rs.getString("title"), rs.getString("director"), rs.getInt("released"),rs.getInt("preferdAge"));
	    		            movies.put(af.getTitle(), af);
                	break; 
                case "ff" : 
				    		FeatureFilm ff = new FeatureFilm(rs.getString("title"), rs.getString("director"), rs.getInt("released"));
				    		movies.put(ff.getTitle(), ff);
                    break;
                }  
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
	 return movies;	    
  }
  
  public void loadPersons(MovieHashMap movies) {
	  Connection conn = DBConnection.getDBConnection();
	  String selectMovie = "SELECT name, title FROM persons";
		    try (PreparedStatement prStmt = conn.prepareStatement(selectMovie );) {
		      ResultSet rs = prStmt.executeQuery();
		      while (rs.next()) {
		    	String title = rs.getString("title"); 
		    	if (movies.getMovie(title) instanceof AnimatedFilm) {
		    		movies.getMovie(title).addPerson(new Animator(rs.getString("name")));
		    	} else {
		    		movies.getMovie(title).addPerson(new Actor(rs.getString("name")));
		    	}
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
  }

  public void loadReviews(MovieHashMap movies) {
	  Connection conn = DBConnection.getDBConnection();
	  String selectMovie = "SELECT title, comment, points FROM reviews";
		    try (PreparedStatement prStmt = conn.prepareStatement(selectMovie );) {
		      ResultSet rs = prStmt.executeQuery();
		      while (rs.next()) {
		    	String title = rs.getString("title"); 
		    	if (movies.getMovie(title) instanceof AnimatedFilm) {
		    		movies.getMovie(title).addReview(new ReviewNumbers(rs.getInt("points"), rs.getString("comment")));	
		    	} else {
		    		movies.getMovie(title).addReview(new ReviewStars(rs.getInt("points"), rs.getString("comment")));
		    	}
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
  }
  
}

