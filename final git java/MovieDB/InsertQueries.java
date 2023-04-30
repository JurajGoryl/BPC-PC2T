import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertQueries {

  public InsertQueries() {}
  public void performInsertQuery(String insertQuery) {
    if (insertQuery == null) {
      throw new NullPointerException("query must not be null!");
    } else if (insertQuery.isEmpty()) {
      throw new IllegalArgumentException("query must not be empty!");
    }
    Connection conn = DBConnection.getDBConnection();
    try (PreparedStatement prStmt = conn.prepareStatement(insertQuery);) {
      int rowsInserted = prStmt.executeUpdate();
      System.out.println("Byl vlozen uzivatel s emailem: " + "myname123@stud.feec.vutbr.cz");
    } catch (SQLException e) {
      System.out.println("Uzivatel s emailem: " + "myname123@stud.feec.vutbr.cz "
          + "jiz byl vlozen nemusite jej vkladat znovu");
    }
  }

  public void insertMovie(String title,String movieType, String director, int released, int preferedAge) {
    
    Connection conn = DBConnection.getDBConnection();

    String insertMovie = "insert into movies (title, movieType, director, released, preferdAge) values (?,?,?,?,?);";

    try (PreparedStatement prStmt = conn.prepareStatement(insertMovie)) {
      prStmt.setString(1, title);
      prStmt.setString(2, movieType);
      prStmt.setString(3, director);
      prStmt.setInt(4, released);
      prStmt.setInt(5, preferedAge);
      prStmt.executeUpdate();
      System.out.println("Novy film byl vlozen do databaze!");
    } catch (SQLException e) {
      System.out.println("film uz bol vlozeny");
    }
  }
  
  public void insertReview(String title, int points,    String comment) {
	    
	    Connection conn = DBConnection.getDBConnection();

	    String insertReview = "insert into reviews (title, points, comment) values (?,?,?);";

	    try (PreparedStatement prStmt = conn.prepareStatement(insertReview)) {
	      prStmt.setString(1, title);
	      prStmt.setInt(2, points);
	      prStmt.setString(3, comment);
	      
	      prStmt.executeUpdate();
	      System.out.println("Review saved!");
	    } catch (SQLException e) {
	      System.out.println("vynika");
	    }
	  }
  
  
  public void insertPerson(String title, String name) {
	    
	    Connection conn = DBConnection.getDBConnection();

	    String insertPerson= "insert into persons (title, name) values (?,?);";

	    try (PreparedStatement prStmt = conn.prepareStatement(insertPerson)) {
	      prStmt.setString(1, title);
	      prStmt.setString(2, name);
	      
	      prStmt.executeUpdate();
	      System.out.println("Person saved!");
	    } catch (SQLException e) {
	      System.out.println("vynika");
	    }
	}
}
