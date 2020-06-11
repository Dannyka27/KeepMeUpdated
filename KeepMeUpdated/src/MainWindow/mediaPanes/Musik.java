package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Musik extends Medium
{
	private String genre;
	private String franchise;// von mir noch eingefügt

	public Musik(int ID, String titel, String untertitel, String genre, String franchise, // von mir noch eingefügt
			String zusatzinformationen, String standort, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.genre = genre;
		this.franchise = franchise;// von mir noch eingefügt

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Genre", genre);
		addInfo("Franchise", franchise);
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Musik");
		lhm.put("Genre", getGenre());
		lhm.put("Franchise", getFranchise());
		return lhm;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getFranchise()
	{
		return franchise;
	}

	public void setFranchise(String franchise)
	{
		this.franchise = franchise;
	}
	
}
