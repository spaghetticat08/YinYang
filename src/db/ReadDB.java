package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadDB {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs;
	
	String JDBC_DRIVER =  "org.h2.Driver";
	String DB_URL = "jdbc:h2:~/ShowCaseDB";
	//DB credentials
	final String USER = "sa";
	final String PASS = "";
	 
	
	public ReadDB() {}	
		
	public ResultSet getAllOrders() {
		ResultSet rs = null;
		try {
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection succesful!");
			stmt = conn.createStatement();
			
			//execute query
			String sqlCustomerQuery = "SELECT * FROM OrderTable";
			rs = stmt.executeQuery(sqlCustomerQuery);
			
			
			} catch(SQLException se) {
				se.printStackTrace();
			} 
			
			catch(Exception e) {
				e.printStackTrace();
			} 
			return rs;
	}
	
	public ResultSet getAllCustomers() {
		ResultSet rs = null;
		
		try {
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection succesful!");
			stmt = conn.createStatement();
			
			//execute query
			String sqlCustomerQuery = "SELECT * FROM Gebruiker WHERE typeGebruiker='Klant'";
			rs = stmt.executeQuery(sqlCustomerQuery);
			
			
		} catch(SQLException se) {
			se.printStackTrace();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		} 

		
		/*Clean-up environment
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return rs;  
		}
	public ResultSet getAllLeveranciers() {
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection succesful!");
			stmt = conn.createStatement();
			
			//execute query
			String sqlSupplierQuery = "SELECT * FROM Gebruiker WHERE typeGebruiker='Leverancier'";
			rs = stmt.executeQuery(sqlSupplierQuery);
			
			//Clean-up environment
			
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		return rs;
	}
	

		
}