package logic;

public class Leverancier {
	private String leverancierNaam;
	private String contactPersoon;
	private String leverancierAdres;
	private String leverancierPlaats;
	private String land;
	

	private String leverancierEmailadres;
	private String leverancierTelNr;
	private String website;
	private String leverancierOpmerking;
	private int gebruikerNummer;

	public Leverancier(String naam, String contactPersoon, String Adres, String plaats, String land, String emailadres, String telefoonnummer, String website, String opmerking, int gebruikerNummer) {
		// TODO Auto-generated constructor stub
		this.leverancierNaam= naam;
		this.contactPersoon = contactPersoon;
		this.leverancierAdres = Adres;
		this.leverancierPlaats = plaats;
		this.land = land;
		this.leverancierEmailadres = emailadres;
		this.leverancierTelNr = telefoonnummer;
		this.website = website;
		this.leverancierOpmerking = opmerking;
		this.gebruikerNummer = gebruikerNummer;
	}
	
	public Leverancier(String naam, String contactPersoon, String Adres, String plaats, String land, String emailadres, String telefoonnummer, String website, String opmerking) {
		this.leverancierNaam= naam;
		this.contactPersoon = contactPersoon;
		this.leverancierAdres = Adres;
		this.leverancierPlaats = plaats;
		this.land = land;
		this.leverancierEmailadres = emailadres;
		this.leverancierTelNr = telefoonnummer;
		this.website = website;
		this.leverancierOpmerking = opmerking;
	}


	public String getLeverancierNaam() {
		return leverancierNaam;
	}

	public void setLeverancierNaam(String leverancierNaam) {
		this.leverancierNaam = leverancierNaam;
	}

	public String getContactPersoon() {
		return contactPersoon;
	}

	public void setContactPersoon(String contactPersoon) {
		this.contactPersoon = contactPersoon;
	}

	public String getLeverancierAdres() {
		return leverancierAdres;
	}

	public void setLeverancierAdres(String leverancierAdres) {
		this.leverancierAdres = leverancierAdres;
	}
	public String getLand() {
		return land;
	}
	
	public void setLand(String land) {
		this.land = land;
	}
	public String getLeverancierPlaats() {
		return leverancierPlaats;
	}

	public void setLeverancierPlaats(String leverancierPlaats) {
		this.leverancierPlaats = leverancierPlaats;
	}

	public String getLeverancierEmailadres() {
		return leverancierEmailadres;
	}

	public void setLeverancierEmailadres(String leverancierEmailadres) {
		this.leverancierEmailadres = leverancierEmailadres;
	}

	public String getLeverancierTelNr() {
		return leverancierTelNr;
	}

	public void setLeverancierTelNr(String leverancierTelNr) {
		this.leverancierTelNr = leverancierTelNr;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLeverancierOpmerking() {
		return leverancierOpmerking;
	}

	public void setLeverancierOpmerking(String leverancierOpmerking) {
		this.leverancierOpmerking = leverancierOpmerking;
	}

	public int getGebruikerNummer() {
		return gebruikerNummer;
	}

	public void setGebruikerNummer(int gebruikerNummer) {
		this.gebruikerNummer = gebruikerNummer;
	}
	
	

}
