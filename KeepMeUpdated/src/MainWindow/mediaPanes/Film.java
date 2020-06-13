package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Film extends Medium
{
private String medium;
private String franchise;
private String altersgruppe;

	public Film(int ID,
			String titel,
			String untertitel,
			String altersgruppe,
			String medium,
			String zusatzinformationen,
			String standort,
			String franchise,
			String link) 
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.medium = medium;
		this.franchise = franchise;
		this.altersgruppe = altersgruppe;
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Filme");
		lhm.put("Medium", getMedium());
		lhm.put("Franchise", getFranchise());
		lhm.put("Altersgruppe", getAltersgruppe());
		return lhm;
	}

	public String getMedium()
	{
		return medium;
	}

	public void setMedium(String medium)
	{
		this.medium = medium;
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
	
	@Override
	public String getTabellenTitel()
	{
		return "Filme";
	}
}
