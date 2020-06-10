package MainWindow.mediaPanes;

public class Film extends Medium
{
	private String medium;
	private String franchise;
	private String altersgruppe;

	public Film(int ID, String titel, String untertitel, String altersgruppe, String medium, String zusatzinformationen,
			String standort, String franchise, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.medium = medium;
		this.franchise = franchise;
		this.altersgruppe = altersgruppe;

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Medium", medium);
		addInfo("Franchise", franchise);
		addInfo("Altersgruppe", altersgruppe);
	}
}
