package logic;

import java.util.ArrayList;

import interfacefolder.DataBaseInterface;
import db.DataBaseManager;
import gui.Mainmenu;
import logic.Klant;

public class Main {
	
	public static void Main(String[] args) {
		StichtingGegevens xmlGegevens = new StichtingGegevens();
		String xml = "C:\\Users\\Britt\\eclipse-workspace\\ShowCase\\src\\src\\gegevens.xml";
		Stichting newStichting = xmlGegevens.readXML(xml);
		DataBaseInterface db = new DataBaseManager();
		Mainmenu startMainMenu = new Mainmenu(newStichting, db);
		
			
		/*DataBaseManager db = new DataBaseManager();
		Klant testKlant = new Klant("Bertha de Vrouw", "Stationsstraat 22 1071VL", "Amstelveen", "Berthaheeftmail@hotmail.com", "06-2769423", "Bertha is nog 50 euro verschuldigd", 2 );
		testKlant.insertTestData(testKlant);
		Stichting newStichting = new Stichting("Yoga4Life", 510.20);
		String[] newList = newStichting.customerNames(newStichting,db);
		System.out.println(newList[0]);
		*/
	}
	

}
