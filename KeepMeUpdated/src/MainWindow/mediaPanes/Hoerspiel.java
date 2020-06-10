package MainWindow.mediaPanes;

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
}
