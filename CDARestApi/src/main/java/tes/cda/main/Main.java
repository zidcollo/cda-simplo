package tes.cda.main;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class Main {
		private static Connection connection;
		
		static{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection ("jdbc:mysql://localhost:3306/cda_bdd?useSSL=false","root", "");
			} catch (Exception e) {  
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection() {
			return connection;
		}
		
		public static void closeConnection (Connection c) throws SQLException{
			c.close();
		}
	}
