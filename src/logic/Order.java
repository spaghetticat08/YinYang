package logic;

import logic.BetalingsMiddel;
import logic.Klant;
import logic.Leverancier;
import logic.Status;

public class Order {
	private String artikel;
	private int ordernummer;
	private String omschrijving;
	private String datum;
	private  Double prijs;
	private BetalingsMiddel typeBetaling;
	private Status orderStatus;
	private Leverancier leverancierNaam;
	private Klant klantNaam;
	private int gebruikerNummer;
	

	public Order(String artikel, String omschrijving, String datum, Double prijs,BetalingsMiddel typeBetaling, Status orderStatus, Leverancier leverancierNaam, Klant klantNaam, int gebruikerNummer ) {
		this.artikel = artikel;
		this.omschrijving = omschrijving;
		this.datum = datum;
		this.prijs = prijs;
		this.typeBetaling = typeBetaling;
		this.orderStatus = orderStatus;
		this.leverancierNaam = leverancierNaam;
		this.klantNaam = klantNaam;
		this.gebruikerNummer = gebruikerNummer;
	}
		
	public Order(String artikel, int ordernummer, String omschrijving, String datum, Double prijs,BetalingsMiddel typeBetaling, Status orderStatus, int gebruikerNummer) {
		this.artikel = artikel;
		this.ordernummer = ordernummer;
		this.omschrijving = omschrijving;
		this.datum = datum;
		this.prijs = prijs;
		this.typeBetaling = typeBetaling;
		this.orderStatus = orderStatus;
		this.gebruikerNummer = gebruikerNummer;
	}
	
	public Order(String artikel, int ordernummer, String omschrijving, String datum, Double prijs,BetalingsMiddel typeBetaling, Status orderStatus, Leverancier leverancierNaam, Klant klantNaam, int gebruikerNummer ) {
		this.artikel = artikel;
		this.omschrijving = omschrijving;
		this.ordernummer = ordernummer;
		this.datum = datum;
		this.prijs = prijs;
		this.typeBetaling = typeBetaling;
		this.orderStatus = orderStatus;
		this.leverancierNaam = leverancierNaam;
		this.klantNaam = klantNaam;
		this.gebruikerNummer = gebruikerNummer;
	}
	
	public void setLeverancierNaam(Leverancier leverancierNaam) {
		this.leverancierNaam = leverancierNaam;
	}

	public void setKlantNaam(Klant klantNaam) {
		this.klantNaam = klantNaam;
	}

	public int getGebruikerNummer() {
		return gebruikerNummer;
	}

	public void setGebruikerNummer(int gebruikerNummer) {
		this.gebruikerNummer = gebruikerNummer;
	}

	public BetalingsMiddel getTypeBetaling() {
		return typeBetaling;
	}

	public void setTypeBetaling(BetalingsMiddel typeBetaling) {
		this.typeBetaling = typeBetaling;
	}

	public Status getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Status orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Leverancier getLeverancierNaam() {
		return leverancierNaam;
	}

	public Klant getKlantNaam() {
		
		return klantNaam;
	}

	public int getOrdernummer() {
		return ordernummer;
	}
	
	public String getArtikel() {
		return artikel;
	}
	public String getOmschrijving() {
		return omschrijving;
	}
	public String getDatum() {
		return datum;
	}
	public Double getPrijs() {
		return prijs;
	}

	
}
