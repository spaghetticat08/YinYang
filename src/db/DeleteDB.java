package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import logic.Klant;
import logic.Leverancier;
import logic.Order;

public class DeleteDB {
		String JDBC_DRIVER =  "org.h2.Driver";
		String DB_URL = "jdbc:h2:~/ShowCaseDB";
		//DB credentials
		 final String USER = "sa";
		 final String PASS = "";
		
		private Connection conn = null;
		private Statement stmt = null;
		private String deleteData;	
		
		public DeleteDB() {}
		
		public void deleteCustomer(Klant obj) {
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				System.out.println("Connection succesful!");
				
				deleteData= "DELETE FROM GEBRUIKER WHERE GebruikerNummer="+obj.getGebruikerNummer()+";";
				
				PreparedStatement ps = conn.prepareStatement(deleteData);
				
				ps.executeUpdate();
				System.out.println("Succesfull deleted!");
				
			} catch(SQLException se) {
				se.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(stmt !=null) stmt.close();
				} catch(SQLException se2) {
				} try {
					if(conn !=null) conn.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
				}
			}
		
	public void deleteSupplier(Leverancier obj) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection succesful!");
			
			deleteData= "DELETE FROM GEBRUIKER WHERE GebruikerNummer="+obj.getGebruikerNummer()+";";
			
			PreparedStatement ps = conn.prepareStatement(deleteData);
			
			ps.executeUpdate();
			System.out.println("Succesfull deleted!");
			
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt !=null) stmt.close();
			} catch(SQLException se2) {
			} try {
				if(conn !=null) conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
			}
		}
	public void deleteOrder(Order obj) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection succesful!");
			
			deleteData= "DELETE FROM OrderTable WHERE Ordernummer="+obj.getOrdernummer()+";";
			
			PreparedStatement ps = conn.prepareStatement(deleteData);
			
			ps.executeUpdate();
			System.out.println("Succesfull deleted!");
			
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt !=null) stmt.close();
			} catch(SQLException se2) {
			} try {
				if(conn !=null) conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
			}
		}
}	

