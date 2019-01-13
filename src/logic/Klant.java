package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DataBaseManager;
import gui.CustomerScreen;



public class Klant {
	private String naam;
	private String adres;
	private String plaats;
	private String emailadres;
	private String telefoonnummer;
	private String opmerking;
	protected  int gebruikerNummer;

	public Klant(String naam, String adres, String plaats, String emailadres, String telefoonnummer, String opmerking, int gebruikerNummer) {
		this.naam = naam;
		this.adres = adres;
		this.plaats = plaats;
		this.emailadres = emailadres;
		this.telefoonnummer = telefoonnummer;
		this.opmerking = opmerking;
		this.gebruikerNummer = gebruikerNummer;
		// TODO Auto-generated constructor stub
	}
	
	/*public void insertTestData(Klant testKlant) {
		DataBaseManager newdb = new DataBaseManager();
		newdb.insertData(testKlant);
	}*/
		
	public Klant(String naam, String adres, String plaats, String emailadres, String telefoonnummer,
			String opmerking) {
		this.naam = naam;
		this.adres = adres;
		this.plaats = plaats;
		this.emailadres = emailadres;
		this.telefoonnummer = telefoonnummer;
		this.opmerking = opmerking;
	}

	public String getKlantNaam() {
		return naam;
	}

	public void setKlantNaam(String naam) {
		this.naam = naam;
	}

	public String getKlantAdres() {
		return adres;
	}

	public void setKlantAdres(String adres) {
		this.adres = adres;
	}

	public String getKlantPlaats() {
		return plaats;
	}

	public void setKlantPlaats(String plaats) {
		this.plaats = plaats;
	}

	public String getKlantEmailadres() {
		return emailadres;
	}

	public void setKlantEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}

	public String getKlantTelefoonnummer() {
		return telefoonnummer;
	}

	public void setKlantTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public String getKlantOpmerking() {
		return opmerking;
	}

	public void setKlantOpmerking(String opmerking) {
		this.opmerking = opmerking;
	}

	public int getGebruikerNummer() {
		return gebruikerNummer;
	}

	public void setGebruikerNummer(int gebruikerNummer) {
		this.gebruikerNummer = gebruikerNummer;
	}
}
