import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateQueries {

  public UpdateQueries() {}

  public void performUpdateQuery(String updateQuery) {
    if (updateQuery == null) {
      throw new NullPointerException("query must not be null!");
    } else if (updateQuery.isEmpty()) {
      throw new IllegalArgumentException("query must not be empty!");
    }
    Connection conn = DBConnection.getDBConnection();
    try (PreparedStatement prStmt = conn.prepareStatement(updateQuery);) {
      int rowsUpdated = prStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
