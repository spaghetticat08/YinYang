package gui;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import interfacefolder.DataBaseInterface;
import db.DataBaseManager;
import logic.ApplicatieLogica;
import logic.Leverancier;
import logic.Stichting;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

public class SupplierScreen {
	Stichting newStichting;
	DataBaseInterface db;
	ApplicatieLogica newLogic = new ApplicatieLogica();
	
	private Text textNaam;
	private Text textAdres;
	private Text textPlaats;
	private Text textEmail;
	private Text textTelefoonNr;
	private Text textOpmerking;
	
	private List supplierList;
	private Text textContact;
	private Text textWebsite;
	private Text textLand;
	private Text textGebNr;
	private Text textSearch;
	
	String filter = null;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public SupplierScreen(Stichting newStichting, DataBaseInterface db) {
		this.newStichting = newStichting;
		this.db = db;
		
		Display display = Display.getDefault();
		Shell supplierShell = new Shell();
		supplierShell.setSize(845, 603);
		supplierShell.setText("SWT Application");
		supplierShell.setLayout(null);
		
		Menu menu = new Menu(supplierShell, SWT.BAR);
		supplierShell.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("Navigeer naar...");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmAdministratieoverzicht = new MenuItem(menu_1, SWT.RADIO);
		mntmAdministratieoverzicht.setText("Order-overzicht");
		mntmAdministratieoverzicht.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				supplierShell.dispose();
				OrderScreen startOrderMenu = new OrderScreen(newStichting, db);
			}
		});
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton.setSelection(true);
		mntmNewRadiobutton.setText("Leverancieroverzicht");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmNewRadiobutton_1 = new MenuItem(menu_1, SWT.RADIO);
		mntmNewRadiobutton_1.setText("Klantenoverzicht");
		mntmNewRadiobutton_1.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				supplierShell.dispose();
				CustomerScreen startCustomerMenu = new CustomerScreen(newStichting, db);
				
			}
		});

		//Create a list with all supplier names and display these in the list
		
		supplierList = new List(supplierShell, SWT.BORDER);		
		supplierList.setBounds(10, 36, 246, 478);
				
		String[] listOfSupplierNames = getSupplierNames(null);
		supplierList.setItems(listOfSupplierNames);
		supplierList.addListener(SWT.Selection, event -> onListItemSelect(supplierList, filter));
		
		Label label = new Label(supplierShell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(317, 13, 15, 534);
		
		Label lblAlleVeldenGemarkeerd = new Label(supplierShell, SWT.NONE);
		lblAlleVeldenGemarkeerd.setBounds(338, 13, 321, 15);
		lblAlleVeldenGemarkeerd.setText("Alle velden gemarkeerd met een * zijn verplicht in te vullen");
		
		textNaam = new Text(supplierShell, SWT.BORDER);
		textNaam.setBounds(454, 37, 144, 21);
		
		textContact = new Text(supplierShell, SWT.BORDER);
		textContact.setBounds(454, 66, 144, 21);
		
		textAdres = new Text(supplierShell, SWT.BORDER);
		textAdres.setBounds(454, 93, 233, 21);
		
		textPlaats = new Text(supplierShell, SWT.BORDER);
		textPlaats.setBounds(454, 120, 144, 21);
		
		textLand = new Text(supplierShell, SWT.BORDER);
		textLand.setBounds(454, 147, 144, 21);
		
		textEmail = new Text(supplierShell, SWT.BORDER);
		textEmail.setBounds(454, 188, 205, 21);
		
		textTelefoonNr = new Text(supplierShell, SWT.BORDER);
		textTelefoonNr.setBounds(454, 213, 144, 21);
		
		textWebsite = new Text(supplierShell, SWT.BORDER);
		textWebsite.setBounds(454, 240, 144, 21);
		
		textOpmerking = new Text(supplierShell, SWT.BORDER);
		textOpmerking.setBounds(454, 309, 205, 160);
		
		textGebNr = new Text(supplierShell, SWT.BORDER);
		textGebNr.setEditable(false);
		textGebNr.setBounds(454, 267, 144, 21);
		
		Label lblNewLabel = new Label(supplierShell, SWT.NONE);
		lblNewLabel.setBounds(338, 40, 55, 15);
		lblNewLabel.setText("Naam*");
		
		Label lblContactpersoon = new Label(supplierShell, SWT.NONE);
		lblContactpersoon.setText("Contactpersoon");
		lblContactpersoon.setBounds(338, 69, 110, 15);
		
		Label lblNewLabel_1 = new Label(supplierShell, SWT.NONE);
		lblNewLabel_1.setBounds(338, 96, 55, 15);
		lblNewLabel_1.setText("Adres");
		
		Label lblNewLabel_2 = new Label(supplierShell, SWT.NONE);
		lblNewLabel_2.setBounds(338, 123, 55, 15);
		lblNewLabel_2.setText("Plaats");
		
		Label lblLand = new Label(supplierShell, SWT.NONE);
		lblLand.setText("Land (Invullen \r\nindien niet NL)");
		lblLand.setBounds(338, 147, 88, 38);
		
		Label lblEmailadres = new Label(supplierShell, SWT.NONE);
		lblEmailadres.setBounds(338, 191, 76, 15);
		lblEmailadres.setText("E-mailadres");
		
		Label lblTelefoonnummer = new Label(supplierShell, SWT.NONE);
		lblTelefoonnummer.setBounds(338, 216, 110, 15);
		lblTelefoonnummer.setText("Telefoonnummer*");
		
		Label lblWebsite = new Label(supplierShell, SWT.NONE);
		lblWebsite.setText("Website");
		lblWebsite.setBounds(338, 243, 55, 15);
		
		Label lblGebruikernummer = new Label(supplierShell, SWT.NONE);
		lblGebruikernummer.setText("Gebruikernummer");
		lblGebruikernummer.setBounds(338, 270, 110, 15);
		
		Label lblOpmerkingen = new Label(supplierShell, SWT.NONE);
		lblOpmerkingen.setBounds(338, 312, 88, 15);
		lblOpmerkingen.setText("Opmerkingen");
		
		Button btnOpslaan = new Button(supplierShell, SWT.NONE);
		btnOpslaan.setBounds(445, 489, 101, 25);
		btnOpslaan.setText("Opslaan");
		
		Button btnToevoegen = new Button(supplierShell, SWT.NONE);
		btnToevoegen.setText("Toevoegen...");
		btnToevoegen.setBounds(338, 489, 101, 25);
		btnToevoegen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				boolean succes = addSupplier();
				if(succes == false) {
					MessageBox failed = new MessageBox(supplierShell, SWT.ICON_WARNING|SWT.OK);
					failed.setText("Incorrect fields");
					failed.setMessage("Een of meerdere velden zijn niet of incorrect ingevuld!");
					failed.open();
				}
			}
		});
		
		textSearch = new Text(supplierShell, SWT.BORDER);
		textSearch.setBounds(10, 10, 165, 21);
		
		Button btnSearch = new Button(supplierShell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filter = textSearch.getText();
				String[] resultOfSearch = getSupplierNames(filter);
				supplierList.setItems(resultOfSearch);
			}
		});
		btnSearch.setBounds(181, 10, 75, 22);
		btnSearch.setText("Zoeken...");
		
		Button btnClearSearch = new Button(supplierShell, SWT.NONE);
		btnClearSearch.setBounds(262, 10, 36, 21);
		btnClearSearch.setText("X");
		btnClearSearch.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
			textSearch.setText("");
			filter = null;
			String[] listOfSupplierNames = getSupplierNames(null);
			supplierList.setItems(listOfSupplierNames);
			}
		});
		
		Button btnVerwijderen = new Button(supplierShell, SWT.NONE);
		btnVerwijderen.setText("Verwijderen");
		btnVerwijderen.setBounds(552, 489, 101, 25);
		btnVerwijderen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				deleteSupplier();
			supplierShell.requestLayout();
			}
		});
		
		Button btnLeegmaken = new Button(supplierShell, SWT.NONE);
		btnLeegmaken.setText("Leegmaken");
		btnLeegmaken.setBounds(659, 489, 101, 25);
		btnLeegmaken.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textNaam.setText("");
				textContact.setText("");
				textContact.setText("");
				textAdres.setText("");
				textPlaats.setText("");
				textLand.setText("");
				textEmail.setText("");
				textTelefoonNr.setText("");
				textOpmerking.setText("");
				textGebNr.setText("");
			}
		});
		
		
		
		supplierShell.open();
		supplierShell.layout();
		while (!supplierShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * Calls the method in Logic layer to collect a list of suppliernames
	 * @param filter a string which every name in the list should contain. If this is empty the whole list is shown.
	 * @return a list of names which contain the string if filter is not null or empty
	 */
	public String[] getSupplierNames(String filter) {
		String[] listOfSupplierNames = newLogic.getSupplierNames(newStichting, db, filter);
		return listOfSupplierNames;
	}
	/**
	 * Collects the indexposition which has been clicked and shows the details in the text fields
	 * @param customerList the list containing the customernames
	 * @param filter String which all names in the list should contain
	 */
	public void onListItemSelect(List supplierList, String filter) {
		int indexNo = supplierList.getFocusIndex();
		Leverancier infoLev = newLogic.getLevInfo(indexNo, newStichting, db, filter);
		textNaam.setText(infoLev.getLeverancierNaam());
		textContact.setText(infoLev.getContactPersoon());
		textAdres.setText(infoLev.getLeverancierAdres());
		textPlaats.setText(infoLev.getLeverancierPlaats());
		textLand.setText(infoLev.getLand());
		textEmail.setText(infoLev.getLeverancierEmailadres());
		textTelefoonNr.setText(infoLev.getLeverancierTelNr());
		textWebsite.setText(infoLev.getWebsite());
		textOpmerking.setText(infoLev.getLeverancierOpmerking());
		textGebNr.setText(Integer.toString(infoLev.getGebruikerNummer()));
	}
	/**
	 * Collects the data in textfields and calls the method to insert data in database
	 * @return true if supplier succesfully has been inserted
	 * TODO: Exception handling if supplier could not be inserted due to db connection issues.
	 */
	public boolean addSupplier() {
		boolean succes = newLogic.insertLeverancier(db, textNaam.getText(), textContact.getText(), textAdres.getText(), textPlaats.getText(), textLand.getText(), textEmail.getText(), textTelefoonNr.getText(), textWebsite.getText(), textOpmerking.getText());
		String[] listOfSupplierNames = getSupplierNames(null);
		supplierList.setItems(listOfSupplierNames);
		return succes;
	}
	/**
	 * Collects the indexposition and sends this data to method which collects the supplier to be deleted
	 * TODO: Exception handling if supplier couldn't be deleted. Right now the application will crash.
	 */public void deleteSupplier() {
		int indexNo = supplierList.getFocusIndex();
		newLogic.prepLevForDelete(indexNo, newStichting, db);
		//refresh list with suppliernames
		String[] listOfSupplierNames = getSupplierNames(null);
		supplierList.setItems(listOfSupplierNames);
		
		
	}
}
