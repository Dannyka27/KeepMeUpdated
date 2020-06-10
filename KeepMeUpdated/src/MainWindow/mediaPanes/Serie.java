package MainWindow.mediaPanes;

public class Serie extends Medium
{
	private String season;
	private String altersgruppe;
	private String franchise;
	private String medium;

	public Serie(int ID, String titel, String untertitel, String season, String zusatzinformationen,
			String altersgruppe, String standort, String franchise, String medium, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.season = season;
		this.altersgruppe = altersgruppe;
		this.franchise = franchise;
		this.medium = medium;

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Season", "" + season);
		addInfo("Altersgruppe", altersgruppe);
		addInfo("Franchise", franchise);
		addInfo("Medium", medium);
	}

}
