package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Hoerspiel extends Medium
{
	private int folge;
	private String altersgruppe;

	public Hoerspiel(int ID, String titel, String untertitel, int folge, String zusatzinformationen,
			String altersgruppe, String standort, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.folge = folge;
		this.altersgruppe = altersgruppe;
		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Folge", "" + folge);
		addInfo("Altersgruppe", altersgruppe);
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Hörspiele");
		lhm.put("Folge", "" +  getFolge());
		lhm.put("Altersgruppe", getAltersgruppe());
		return lhm;
	}

	public int getFolge()
	{
		return folge;
	}

	public void setFolge(int folge)
	{
		this.folge = folge;
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
