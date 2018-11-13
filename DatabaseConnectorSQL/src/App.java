import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class App {

	private JFrame frame;
	String connectionString = "jdbc:mysql://localhost:3306/test";
	String user = "test";
	String password = "test";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnConnect = new JButton("Get connection");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = DBConnection.getConnection(connectionString, user, password);
				if (connection == null) {
					JOptionPane.showMessageDialog(null, "B³¹d po³¹czenia z baz¹ danych", "B³¹d sterownika",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					String query = "Select * from osoby";
					Statement stm = connection.createStatement();
					ResultSet rs = stm.executeQuery(query);
					while (rs.next()) {
						String id = rs.getString("id_osoby");
						String imie = rs.getString("imie");
						String nazwisko = rs.getString("nazwisko");

						System.out.println(id + " " + imie + " " + nazwisko);
					}
				} catch (SQLException ex2) {
					JOptionPane.showMessageDialog(null, "B³¹d przy przetwarzaniu danych", "B³¹d pobierania danych",
							JOptionPane.ERROR_MESSAGE);
				}

				try {
					connection.close();
				} catch (SQLException ex2) {
					JOptionPane.showMessageDialog(null, "B³¹d zamkniêcia po³¹czenia z baz¹ danych",
							"B³¹d zamykania po³¹czenia", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConnect.setBounds(10, 11, 422, 49);
		frame.getContentPane().add(btnConnect);
	}
}
