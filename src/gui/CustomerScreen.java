package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Text;
import interfacefolder.DataBaseInterface;
import logic.ApplicatieLogica;
import logic.Klant;
import logic.Stichting;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * @author Britt
 *
 */
public class CustomerScreen {
	private Text textNaam;
	private Text textAdres;
	private Text textPlaats;
	private Text textEmail;
	private Text textTelefoonNr;
	private Text textGebNr;
	private Text textOpmerkingen;
	String filter = null;
	
	private ArrayList<Klant> klantinfo;
	ApplicatieLogica newLogic = new ApplicatieLogica();
	
	Stichting newStichting;
	DataBaseInterface db;
	private List customerList;
	private Text textSearch;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public CustomerScreen(Stichting newStichting, DataBaseInterface db) {
		this.newStichting = newStichting;
		this.db = db;
		Display display = Display.getDefault();
		Shell customerShell2 = new Shell();
		customerShell2.setSize(845, 603);
		customerShell2.setText("SWT Application");
		customerShell2.setLayout(null);

		
		Menu menu = new Menu(customerShell2, SWT.BAR);
		customerShell2.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("Navigeer naar...");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmAdministratieoverzicht = new MenuItem(menu_1, SWT.RADIO);
		mntmAdministratieoverzicht.setText("Order-overzicht");
		mntmAdministratieoverzicht.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				customerShell2.dispose();
				OrderScreen startOrderMenu = new OrderScreen(newStichting, db);
			}
		});
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton.setText("Leverancieroverzicht");
		mntmNewRadiobutton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				customerShell2.dispose();
				SupplierScreen startSupplierMenu = new SupplierScreen(newStichting, db);
			}
		});
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton_1 = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton_1.setSelection(true);
		mntmNewRadiobutton_1.setText("Klantenoverzicht");
		
		//Make list
		
		customerList = new List(customerShell2, SWT.BORDER | SWT.V_SCROLL);
		customerList.setBounds(10, 36, 246, 478);
		
		String[] listOfCustomerNames = getCustomerNames(null);
		customerList.setItems(listOfCustomerNames);
		customerList.addListener(SWT.Selection, event -> onListItemSelect(customerList, filter));

		Label label = new Label(customerShell2, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(317, 13, 15, 534);
		
		Label lblAlleVeldenGemarkeerd = new Label(customerShell2, SWT.NONE);
		lblAlleVeldenGemarkeerd.setBounds(338, 13, 321, 15);
		lblAlleVeldenGemarkeerd.setText("Alle velden gemarkeerd met een * zijn verplicht in te vullen");
				
		Label lblNewLabel = new Label(customerShell2, SWT.NONE);
		lblNewLabel.setBounds(338, 40, 55, 15);
		lblNewLabel.setText("Naam*");
		
		textNaam = new Text(customerShell2, SWT.BORDER);
		textNaam.setBounds(454, 37, 144, 21);
		
		Label lblNewLabel_1 = new Label(customerShell2, SWT.NONE);
		lblNewLabel_1.setBounds(338, 67, 55, 15);
		lblNewLabel_1.setText("Adres");
		
		textAdres = new Text(customerShell2, SWT.BORDER);
		textAdres.setBounds(454, 64, 205, 21);
		
		Label lblNewLabel_2 = new Label(customerShell2, SWT.NONE);
		lblNewLabel_2.setBounds(338, 104, 55, 15);
		lblNewLabel_2.setText("Plaats");
		
		Label lblEmailadres = new Label(customerShell2, SWT.NONE);
		lblEmailadres.setBounds(338, 141, 76, 15);
		lblEmailadres.setText("E-mailadres*");
		
		textPlaats = new Text(customerShell2, SWT.BORDER);
		textPlaats.setBounds(454, 101, 76, 21);
		
		textEmail = new Text(customerShell2, SWT.BORDER);
		textEmail.setBounds(454, 138, 144, 21);
		
		Label lblTelefoonnummer = new Label(customerShell2, SWT.NONE);
		lblTelefoonnummer.setBounds(338, 184, 110, 15);
		lblTelefoonnummer.setText("Telefoonnummer*");
		
		textTelefoonNr = new Text(customerShell2, SWT.BORDER);
		textTelefoonNr.setBounds(454, 181, 144, 21);
		
		Label lblOpmerkingen = new Label(customerShell2, SWT.NONE);
		lblOpmerkingen.setBounds(338, 236, 88, 15);
		lblOpmerkingen.setText("Opmerkingen");
		
		textOpmerkingen = new Text(customerShell2, SWT.BORDER);
		textOpmerkingen.setBounds(454, 233, 205, 160);
		
		Button btnOpslaan = new Button(customerShell2, SWT.NONE);
		btnOpslaan.setBounds(442, 489, 101, 25);
		btnOpslaan.setText("Opslaan");
		btnOpslaan.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				//TODO: change selected information
			}
		});
		
		Button btnToevoegen = new Button(customerShell2, SWT.NONE);	
		btnToevoegen.setBounds(338, 489, 101, 25);
		btnToevoegen.setText("Toevoegen...");
		btnToevoegen.addListener(SWT.Selection, new Listener(){
			@Override
			public void handleEvent(Event e) {
				boolean succes = addCustomer();
				if (succes == false) {
					MessageBox failed = new MessageBox(customerShell2, SWT.ICON_WARNING|SWT.OK);
					failed.setText("Incorrect fields");
					failed.setMessage("Een of meerdere velden zijn niet of incorrect ingevuld!");
					failed.open();
				}
			}
		});
		
		Button btnVerwijderen = new Button(customerShell2, SWT.NONE);
		btnVerwijderen.setText("Verwijderen");
		btnVerwijderen.setBounds(549, 489, 101, 25);
		
		textGebNr = new Text(customerShell2, SWT.BORDER);
		textGebNr.setEditable(false);
		textGebNr.setBounds(454, 426, 144, 21);
		
		Label lblGebruikernummer = new Label(customerShell2, SWT.NONE);
		lblGebruikernummer.setText("Gebruikernummer");
		lblGebruikernummer.setBounds(338, 429, 101, 25);
		
		textSearch = new Text(customerShell2, SWT.BORDER);
		textSearch.setBounds(10, 10, 165, 21);
		
		Button btnSearch = new Button(customerShell2, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filter = textSearch.getText();
				String[] resultOfSearch = getCustomerNames(filter);
				customerList.setItems(resultOfSearch);
			}
		});
		btnSearch.setBounds(181, 10, 75, 22);
		btnSearch.setText("Zoeken...");
		
		Button btnClearSearch = new Button(customerShell2, SWT.NONE);
		btnClearSearch.setBounds(262, 10, 36, 21);
		btnClearSearch.setText("X");
		btnClearSearch.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
			textSearch.setText("");
			filter = null;
			String[] listOfCustomerNames = getCustomerNames(null);
			customerList.setItems(listOfCustomerNames);
			}
		});
		
		
		btnVerwijderen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				//TODO: make pop up for verification
				deleteCustomer();
				
			}
		});
		
		Button btnLeegmaken = new Button(customerShell2, SWT.NONE);
		btnLeegmaken.setText("Leegmaken");
		btnLeegmaken.setBounds(656, 489, 101, 25);
		btnLeegmaken.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				textNaam.setText("");
				textAdres.setText("");
				textPlaats.setText("");
				textEmail.setText("");
				textTelefoonNr.setText("");
				textOpmerkingen.setText("");
				textGebNr.setText("");
			}
		});
		
		customerShell2.open();
		customerShell2.layout();
		while (!customerShell2.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 *  Calls the method in Logic layer to collect a list of customerNames
	 * @param filter a string which every name in the list should contain. If this is empty the whole list is shown.
	 * @return a list of names which contain the string if filter is not null or empty
	 */
	public String[] getCustomerNames(String filter) {
		//create a query to get the whole list of customers and return only the names
		String[] listOfCustomerNames = newLogic.getCustomerNames(newStichting, db, filter);
		return listOfCustomerNames;
	}
	
	/**
	 * Collects the indexposition which has been clicked and  shows the details in the text fields
	 * @param customerList the list containing the customernames
	 * @param filter String which all names in the list should contain
	 */
	public void onListItemSelect(List customerList, String filter) {
		int indexNo = customerList.getFocusIndex();
		Klant infoKlant = newLogic.getKlantInfo(indexNo, newStichting, db, filter);
		textNaam.setText(infoKlant.getKlantNaam());
		textAdres.setText(infoKlant.getKlantAdres());
		textPlaats.setText(infoKlant.getKlantPlaats());
		textEmail.setText(infoKlant.getKlantEmailadres());
		textTelefoonNr.setText(infoKlant.getKlantTelefoonnummer());
		textOpmerkingen.setText(infoKlant.getKlantOpmerking());
		textGebNr.setText(Integer.toString(infoKlant.getGebruikerNummer()));
	}
	
	
	/**
	 * Collects the data in textfields and calls the method to insert data in database
	 * @return true if customer succesfully has been inserted
	 * TODO: Exception handling if customer could not be inserted due to db connection issues.
	 */
	public boolean addCustomer() {
		boolean succes = newLogic.insertKlant(db, textNaam.getText(), textAdres.getText(), textPlaats.getText(), textEmail.getText(), textTelefoonNr.getText(), textOpmerkingen.getText());
		String[] listOfCustomerNames = getCustomerNames(null);
		customerList.setItems(listOfCustomerNames);	
		return succes;
		}
	
	
	/**
	 * Collects the indexposition and sends this data to method which collects the customer to be deleted
	 * TODO: Exception handling if customer couldnt be deleted. Right now the application will crash.
	 */
	public void deleteCustomer() {
		int indexNo = customerList.getFocusIndex();
		newLogic.prepKlantForDelete(indexNo, newStichting, db);
		customerList.removeAll();
		//refresh list with customernames
		String[] listOfCustomerNames = getCustomerNames(null);
		customerList.setItems(listOfCustomerNames);	
	}
	}