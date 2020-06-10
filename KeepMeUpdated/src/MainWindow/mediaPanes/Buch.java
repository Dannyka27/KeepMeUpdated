package MainWindow.mediaPanes;

import java.util.Arrays;

import datenhaltung.Datenbank;

public class Buch extends Medium
{
	private String genre;
	private String autor;
	private String franchise;
	private String altersgruppe;

	public Buch(int ID, String titel, String untertitel, String genre, String standort, String autor,
			String zusatzinformationen, String franchise, String altersgruppe, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.genre = genre;
		this.autor = autor;
		this.franchise = franchise;
		this.altersgruppe = altersgruppe;

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Genre", genre);
		addInfo("Autor", autor);
		addInfo("Franchise", franchise);
		addInfo("Altersgruppe", altersgruppe);
	}
	
	static final String attribs = "ID,Titel,Untertitel,Genre,Standort,Autor,Zusatzinformationen,Franchise,Altersgruppe,Link";
	
	@Override
	public boolean dbSpeichern(Datenbank db)
	{
		String[] werte = {};
		return db.dbAusfuehren(String.format("SELECT OR INSERT INTO %s(%s) VALUES (%s)", 
				attribs, Arrays.toString(werte).substring(1, werte.length))
		);	 
	}

}
