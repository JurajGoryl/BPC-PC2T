import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteQueries {

  public DeleteQueries() {}
  public void performDeleteQuery(String deleteQuery) {
    if (deleteQuery == null) {
      throw new NullPointerException("query must not be null!");
    } else if (deleteQuery.isEmpty()) {
      throw new IllegalArgumentException("query must not be empty!");
    }
    Connection conn = DBConnection.getDBConnection();
    try (PreparedStatement prStmt = conn.prepareStatement(deleteQuery);) {
      int rowsDeleted = prStmt.executeUpdate();
      // System.out.println(rowsInserted);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void deleteMovies() {
	    Connection conn = DBConnection.getDBConnection();

	    String deleteAllMovies = "delete from movies";
	    
	    try (PreparedStatement prStmt = conn.prepareStatement(deleteAllMovies);) {
	        int rowsDeleted = prStmt.executeUpdate();
	        System.out.println("All movies deleted");
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
   }

  public void deleteReviews() {
	    Connection conn = DBConnection.getDBConnection();

	    String deleteAllMovies = "delete from reviews";
	    
	    try (PreparedStatement prStmt = conn.prepareStatement(deleteAllMovies);) {
	        int rowsDeleted = prStmt.executeUpdate();
	        System.out.println("All reviews deleted");
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
 }

  public void deletePersons() {
	    Connection conn = DBConnection.getDBConnection();

	    String deleteAllPersons = "delete from persons";
	    
	    try (PreparedStatement prStmt = conn.prepareStatement(deleteAllPersons);) {
	        int rowsDeleted = prStmt.executeUpdate();
	        System.out.println("All persons deleted");
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
  }  
}
