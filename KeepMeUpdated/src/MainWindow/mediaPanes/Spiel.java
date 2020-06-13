package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Spiel extends Medium
{
	private String altersgruppe;
    private String franchise;
    private String plattform;
    
    public Spiel(int ID,
			String titel,
			String untertitel, 
			String altersgruppe, 
			String zusatzinformationen, 
			String standort,
			String franchise,
			String plattform, 
			String link)
    {
        super(ID, titel, untertitel, zusatzinformationen, standort, link);
        this.altersgruppe = altersgruppe;
        this.franchise = franchise;
        this.plattform = plattform;
    }

	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Altersgruppe", getAltersgruppe());
		lhm.put("Franchise", getFranchise());
		lhm.put("Plattform", getPlattform());
		return lhm;
	}

	public String getAltersgruppe()
	{
		return altersgruppe;
	}

	public void setAltersgruppe(String altersgruppe)
	{
		this.altersgruppe = altersgruppe;
	}

	public String getFranchise()
	{
		return franchise;
	}

	public void setFranchise(String franchise)
	{
		this.franchise = franchise;
	}

	public String getPlattform()
	{
		return plattform;
	}

	public void setPlattform(String plattform)
	{
		this.plattform = plattform;
	}
	
	@Override
	public String getTabellenTitel()
	{
		return "Spiele";
	}
}
