package MainWindow.mediaPanes;

public class Spiel extends Medium
{

	private String altersgruppe;
	private String franchise;
	private String plattform;

	public Spiel(int ID, String titel, String untertitel, String altersgruppe, String zusatzinformationen,
			String standort, String franchise, String plattform, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.altersgruppe = altersgruppe;
		this.franchise = franchise;
		this.plattform = plattform;

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Altersgruppe", altersgruppe);
		addInfo("Franchise", franchise);
		addInfo("Plattform", plattform);
	}

}
