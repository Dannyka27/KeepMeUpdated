package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Zeitschrift extends Medium
{
	private String ausgabe;
	private String herausgeber;
	private String genre;

	public Zeitschrift(int ID, String titel, String untertitel, String herausgeber, String ausgabe,
			String zusatzinformationen, String genre, String standort, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.ausgabe = ausgabe;
		this.herausgeber = herausgeber;
		this.genre = genre;
		
		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Ausgabe", ausgabe);
		addInfo("Herausgeber", herausgeber);
		addInfo("Genre", genre);
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Zeitschriften");
		lhm.put("Ausgabe", getAusgabe());
		lhm.put("Herausgeber", getHerausgeber());
		lhm.put("Genre", getGenre());
		return lhm;
	}

	public String getAusgabe()
	{
		return ausgabe;
	}

	public void setAusgabe(String ausgabe)
	{
		this.ausgabe = ausgabe;
	}

	public String getHerausgeber()
	{
		return herausgeber;
	}

	public void setHerausgeber(String herausgeber)
	{
		this.herausgeber = herausgeber;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}
}
