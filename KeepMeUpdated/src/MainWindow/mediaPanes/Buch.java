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
		
		attribs += ",Genre,Autor,Franchise,Altersgruppe";
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

	@Override
	public boolean dbSpeichern(Datenbank db)
	{
		String[] werte = {"" + getID(), getTitel(), getUntertitel(), getZusatzinformationen(), getStandort(), getLink(),
				getGenre(), getAutor(), getFranchise(), getAltersgruppe()
		};
		return db.dbAusfuehren(String.format("SELECT OR INSERT INTO %s(%s) VALUES (%s)", attribs,
				Arrays.toString(werte).substring(1, werte.length-1)));
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getAutor()
	{
		return autor;
	}

	public void setAutor(String autor)
	{
		this.autor = autor;
	}

	public String getFranchise()
	{
		return franchise;
	}

	public void setFranchise(String franchise)
	{
		this.franchise = franchise;
	}

	public String getAltersgruppe()
	{
		return altersgruppe;
	}

	public void setAltersgruppe(String altersgruppe)
	{
		this.altersgruppe = altersgruppe;
	}
}
