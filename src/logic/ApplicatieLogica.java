package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.swt.widgets.Text;

import interfacefolder.DataBaseInterface;
import db.DataBaseManager;

public class ApplicatieLogica {
	boolean descendingP = true;
	boolean descendingN = true;
	
	public boolean insertKlant(DataBaseInterface db, String klantNaam, String klantAdres, String klantPlaats, String klantEmail, String klantTelefoonNr, String klantOpmerking) {
		if (klantNaam.equals(null)||klantNaam.equals(" ") ||klantEmail.equals(null)||klantEmail.equals("")||klantTelefoonNr.equals(null)||klantTelefoonNr.equals("")){
			return false;
		}
		Klant newKlant = new Klant(klantNaam, klantAdres, klantPlaats, klantEmail, klantTelefoonNr, klantOpmerking);	
		db.insertKlant(newKlant);
		return true;
	}
	
	public boolean insertLeverancier(DataBaseInterface db, String levNaam, String contactPersoon, String levAdres, String levPlaats, String land, String levEmail, String LevTelefoonNr, String website, String levOpmerking) {
		if(levNaam.equals(null)||levNaam.equals("") ||LevTelefoonNr.equals(null)||LevTelefoonNr.equals("")) {
			return false;
		}
		Leverancier newLev = new Leverancier(levNaam, contactPersoon, levAdres, levPlaats, land, levEmail, LevTelefoonNr, website, levOpmerking);
		db.insertLeverancier(newLev);
		return true;
	}
	
	public void insertOrder(Stichting newStichting, DataBaseInterface db, String gebruiker, String artikel, String omschrijving, String datum, Double prijs,BetalingsMiddel typeBetaling, Status orderStatus ) {
		ArrayList<Leverancier> leveranciers = newStichting.getLeveranciers(newStichting, db);
		for (Leverancier l:leveranciers) {
			if (l.getLeverancierNaam().equals(gebruiker)) {
				Order newOrder = new Order(artikel, omschrijving, datum, prijs, typeBetaling, orderStatus, l, null, l.getGebruikerNummer());
				db.insertOrder(newOrder);
				return; 
			}
		}
		ArrayList<Klant> klanten = newStichting.getKlanten(newStichting, db);
		for (Klant k:klanten) {
			if (k.getKlantNaam().equals(gebruiker)) {
				Order newOrder = new Order(artikel, omschrijving, datum, prijs, typeBetaling, orderStatus, null, k, k.getGebruikerNummer());
				db.insertOrder(newOrder);
				return;
			}
		}
		
	}
	public void disposeAndCreateDB() {
		DataBaseInterface resetDB = new DataBaseManager();
		resetDB.createDatabase();
	}
	
	public Order getOrderInfo(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		Order infoOrder = orders.get(indexNo);
		return infoOrder;
	}

	public Klant getKlantInfo(int indexNo, Stichting newStichting, DataBaseInterface db, String filter) {
		ArrayList<Klant> klanten = newStichting.getKlanten(newStichting, db);
		if (filter == null || filter.equals(" ")) {
			Klant infoKlant = klanten.get(indexNo);	
			return infoKlant;
		}
		else {
			ArrayList<Klant> filteredKlanten = getFilteredSearchKlanten(newStichting, klanten, filter);
			Klant infoFilteredKlant = filteredKlanten.get(indexNo);
			return infoFilteredKlant;
		}
	}
	
	public Leverancier getLevInfo(int indexNo, Stichting newStichting, DataBaseInterface db, String filter) {
		ArrayList<Leverancier> leveranciers = newStichting.getLeveranciers(newStichting, db);
		if(filter == null || filter.equals(" ")) {
			Leverancier infoLev = leveranciers.get(indexNo);	
			return infoLev;
		}
		else {
			ArrayList<Leverancier> filteredLeveranciers = getFilteredSearchLeveranciers(newStichting, leveranciers, filter);
			Leverancier infoFilteredLev = filteredLeveranciers.get(indexNo);
			return infoFilteredLev;
		}
		}
	
	public void prepKlantForDelete(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Klant> klanten = newStichting.getKlanten(newStichting, db);
		Klant deletedKlant = klanten.get(indexNo);
		db.deleteKlant(deletedKlant);
	}
	
	public void prepLevForDelete(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Leverancier> leveranciers = newStichting.getLeveranciers(newStichting, db);
		Leverancier deletedLev = leveranciers.get(indexNo);
		db.deleteLeverancier(deletedLev);
	}
	
	public void prepOrderForDelete(int indexNo, Stichting newStichting, DataBaseInterface db) {
		ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		Order deletedOrder = orders.get(indexNo);
		db.deleteOrder(deletedOrder);
	}
	
	public String[] getGebruikers(Stichting newStichting, DataBaseInterface db) {
		String[] allCustomers = getCustomerNames(newStichting, db, null);
		String[] allSuppliers = getSupplierNames(newStichting, db, null);
		
		String[] both = new String[allCustomers.length+allSuppliers.length];
		int index = allCustomers.length;
		for (int i=0; i<allCustomers.length; i++) {
			both[i] = allCustomers[i];
		}
		for (int i=0; i<allSuppliers.length; i++) {
			both[i+ index]= allSuppliers[i];
		}
		return both;
	}
	
	
	
	public ArrayList<Order> sortPrices(Stichting newStichting, DataBaseInterface db){
		ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		if (descendingP == true){
		Collections.sort(orders, new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				return o1.getPrijs().compareTo(o2.getPrijs());
			}
		});
		descendingP = false;
		} else {
			Collections.sort(orders, new Comparator<Order>() {
				@Override
				public int compare(Order o1, Order o2) {
					return o2.getPrijs().compareTo(o1.getPrijs());
				}
			});
			descendingP = true;
		}
		return orders;
	}
	
	public ArrayList<Order> sortOrderNumbers(Stichting newStichting, DataBaseInterface db){
		ArrayList<Order> orders = newStichting.getOrders(newStichting, db);
		if(descendingN ==true) {
			Collections.sort(orders, new Comparator<Order>() {
				@Override
				public int compare(Order o1, Order o2) {
					int orderNo1 = o1.getOrdernummer();
					int orderNo2 = o2.getOrdernummer();
					return orderNo1 - orderNo2;
				}
			});
			descendingN = false;
		}else {
			Collections.sort(orders, new Comparator<Order>() {
				@Override
				public int compare(Order o1, Order o2) {
					int orderNo1 = o1.getOrdernummer();
					int orderNo2 = o2.getOrdernummer();
					return orderNo2 - orderNo1;
				}
			});
			descendingN = true;
		}
		return orders;
	}
	public ArrayList<Klant> getFilteredSearchKlanten(Stichting newStichting, ArrayList<Klant> klanten, String filter){
		ArrayList<Klant> searchedNames = new ArrayList<Klant>();
		for(Klant k:klanten) {
			if(k.getKlantNaam().toLowerCase().contains(filter.toLowerCase())) {
				searchedNames.add(k);
			}
		}
		return searchedNames;
	}
	
	public ArrayList<Leverancier> getFilteredSearchLeveranciers(Stichting newStichting, ArrayList<Leverancier> leveranciers, String filter){
		ArrayList<Leverancier> searchedNames = new ArrayList<Leverancier>();
		for(Leverancier l:leveranciers) {
			if(l.getLeverancierNaam().toLowerCase().contains(filter.toLowerCase())) {
				searchedNames.add(l);
			}
		}
		return searchedNames;
	}
	
	public String[] getCustomerNames(Stichting newStichting, DataBaseInterface db, String filter){
		ArrayList<Klant> klanten = newStichting.getKlanten(newStichting, db);
		if (filter == null || filter.equals(" ")) {
			String[] listOfCustomerNames = new String[klanten.size()];
			int i=0;
			for (Klant klant: klanten) {
				listOfCustomerNames[i] = klant.getKlantNaam();
				i++;
		}
			return listOfCustomerNames;
		}
		else {
			ArrayList<Klant> searchedKlanten = getFilteredSearchKlanten(newStichting, klanten, filter);
			String[] listOfSearchedNames = new String[searchedKlanten.size()];
			int i=0;
			for(Klant k:searchedKlanten) {
				listOfSearchedNames[i] = k.getKlantNaam();
				i++;
			}
			return listOfSearchedNames;
				}
		}
	
	public String[] getSupplierNames(Stichting newStichting, DataBaseInterface db, String filter){
		ArrayList<Leverancier> leveranciers = newStichting.getLeveranciers(newStichting, db);
		if (filter == null || filter.equals(" ")) {
			String[] listOfSupplierNames = new String[leveranciers.size()];
			int i=0;
			for (Leverancier l:leveranciers) {
				listOfSupplierNames[i] = l.getLeverancierNaam();
				i++;
		}
			return listOfSupplierNames;
		}
		else {
			ArrayList<Leverancier> searchedLeveranciers = getFilteredSearchLeveranciers(newStichting, leveranciers, filter);
			String[] listOfSearchedNames = new String[searchedLeveranciers.size()];
			int i=0;
			for(Leverancier l:searchedLeveranciers) {
				listOfSearchedNames[i] = l.getLeverancierNaam();
				i++;
			}
			return listOfSearchedNames;
				}
		}
	
	//This is a function to reset the xml containing the name and balance, in case it starts malfunctioning. 
	//The inital balance will be set to 0.00 and the name will be set to the default name which is "Reimagined Hapiness"
	public boolean resetXML(String location) {
		StichtingGegevens resetGegevens = new StichtingGegevens();
		String xml =location;
		resetGegevens.saveToXML(xml, "Reimagined Happiness", "0.00");
		
		return true;
	}
	}

	


