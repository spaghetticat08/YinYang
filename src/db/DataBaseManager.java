package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLEngineResult.Status;

import interfacefolder.DataBaseInterface;
import logic.BetalingsMiddel;
import logic.Klant;
import logic.Leverancier;
import logic.Order;

public class DataBaseManager implements DataBaseInterface {
	
	 final String JDBC_DRIVER	= "org.h2.Driver";
	 final String DB_URL = "jdbc:h2:~/ShowCaseDB";
	//DB credentials
	 final String USER = "sa";
	 final String PASS = "";
	
	Connection conn = null;
	Statement stmt = null;
	
	
	public DataBaseManager() {	
	}
	
	public void createDatabase() {
		try {
			CreateDB newDB = new CreateDB();
			newDB.createNewDB(newDB);
			
		} catch (Exception e) {
		}
	}

	public void insertKlant(Klant newKlant) {
		UpdateDB insertKlantDb = new UpdateDB();
		insertKlantDb.createInsertKlant(newKlant);
	}
	
	public void insertLeverancier(Leverancier newLev) {
		UpdateDB insertLevDb = new UpdateDB();
		insertLevDb.createInsertLev(newLev);
	}
	
	public void insertOrder(Order newOrder) {
		UpdateDB insertOrder = new UpdateDB();
		insertOrder.createInsertOrder(newOrder);
	}
	
	public ResultSet getOrders() {
		ResultSet rsOrders = null;
		ReadDB orderQuery = new ReadDB();
		rsOrders = orderQuery.getAllOrders();
		
		return rsOrders;
		
	}
	public ResultSet getLeveranciers() {
		ResultSet rsLeveranciers = null;
		ReadDB LeverancierQuery = new ReadDB();
		rsLeveranciers = LeverancierQuery.getAllLeveranciers();
		
		return rsLeveranciers;
	}
	
	public ResultSet getCustomers() {
		ResultSet rsCustomers = null;
		ReadDB getCustomerQuery = new ReadDB();
		rsCustomers= getCustomerQuery.getAllCustomers();
		return rsCustomers;	
	}

	@Override
	public void deleteKlant(Klant deletedKlant) {
		DeleteDB deleteKlantDB = new DeleteDB();
		deleteKlantDB.deleteCustomer(deletedKlant);
	}

	@Override
	public void deleteLeverancier(Leverancier deletedLev) {
		DeleteDB deleteLevDB = new DeleteDB();
		deleteLevDB.deleteSupplier(deletedLev);
	}

	@Override
	public void deleteOrder(Order deletedOrder) {
		DeleteDB deleteOrderDB = new DeleteDB();
		deleteOrderDB.deleteOrder(deletedOrder);
	}

	
	}
		
	
	
	
	
	
	


