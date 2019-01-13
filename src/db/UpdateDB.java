package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import logic.Klant;
import logic.Leverancier;
import logic.Order;

public class UpdateDB {
	String JDBC_DRIVER =  "org.h2.Driver";
	String DB_URL = "jdbc:h2:~/ShowCaseDB";
	//DB credentials
	 final String USER = "sa";
	 final String PASS = "";
	
	private Connection conn = null;
	private Statement stmt = null;
	private String insertData;	
	public UpdateDB() {}
	
	public void createInsertKlant(Klant obj) {
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection succesful!");
			
			//create a prepared statement, consisting data from obj
			insertData = "INSERT INTO GEBRUIKER( naam, adres, plaats, emailadres, telefoonnummer, contactpersoon, website, opmerkingen, typeGebruiker)"+  
					"VALUES('"+obj.getKlantNaam()+ "', "+"'"+obj.getKlantAdres()+"', "+" '"+obj.getKlantPlaats()+"', "+" '"+obj.getKlantEmailadres()+"', "+" '"+obj.getKlantTelefoonnummer()+"', "+" '', "+" '', "+" '"+obj.getKlantOpmerking()+"', "+" 'Klant' )";
			
			PreparedStatement ps = conn.prepareStatement(insertData);
			//execute statement
			ps.executeUpdate();
			System.out.println("Insertion successfull!");
			//clean up everything
			//stmt.close();
			//conn.close();
			
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
	
	public void createInsertLev(Leverancier newLev) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection succesful!");
			
			//create a prepared statement, consisting data from obj
			insertData = "INSERT INTO GEBRUIKER(naam, adres, plaats, emailadres, telefoonnummer, contactpersoon, website, opmerkingen, typeGebruiker, land)"+  
					"VALUES('"+newLev.getLeverancierNaam()+ "', "+" '"+newLev.getLeverancierAdres()+"', "+" '"+newLev.getLeverancierPlaats()+"', "+" '"+newLev.getLeverancierEmailadres()+"', "+" '"+newLev.getLeverancierTelNr()+"', "+" '"+newLev.getContactPersoon()+"', "+" '"+newLev.getWebsite()+"', "+" '"+newLev.getLeverancierOpmerking()+"', "+" 'Leverancier', "+"'"+newLev.getLand()+"')";
			
			PreparedStatement ps = conn.prepareStatement(insertData);
			
			//execute statement
			ps.executeUpdate();
			System.out.println("Insertion successfull!");
			
			//clean up everything
			//stmt.close();
			//conn.close();
			
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

	public void createInsertOrder(Order newOrder) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection succesful!");
			
			//create a prepared statement, consisting data from obj
			insertData = "INSERT INTO OrderTable(artikel, omschrijving, datum, prijs, typeBetaling, orderStatus, gebgebruikerNummer)"+  
					"VALUES('"+newOrder.getArtikel()+ "', "+"'"+newOrder.getOmschrijving()+"', "+"'"+newOrder.getDatum()+"', "+"'"+newOrder.getPrijs()+"', "+"'"+newOrder.getTypeBetaling()+"', "+"'"+newOrder.getOrderStatus()+"',"+"'"+newOrder.getGebruikerNummer()+"',"+")";
			
			PreparedStatement ps = conn.prepareStatement(insertData);
			
			//execute statement
			ps.executeUpdate();
			System.out.println("Insertion successfull!");
			
			//clean up everything
			//stmt.close();
			//conn.close();
			
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
		
		
			
			
