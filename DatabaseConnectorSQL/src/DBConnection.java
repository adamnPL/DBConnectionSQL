import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection(String connectionString, String user, String password) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

		}

		catch (ClassNotFoundException e) {
			System.out.println("Brak klasy!");
			return null;
		}
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
		} catch (SQLException e) {
			System.out.println("B³¹d po³¹czenia");
			return null;
		}
		return connection;
	}

}
