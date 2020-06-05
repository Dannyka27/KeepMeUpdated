package MainWindow.mediaPanes;

@SuppressWarnings("unused")
public class Serie extends Medium
{
	private String season;
	private String altersgruppe;
	private String franchise;
	private String medium;
	public Serie(int ID, 
			String titel, 
			String untertitel, 
			String season,
			String zusatzinformationen,
			String altersgruppe,
			String standort, 
			String franchise,
			String medium,
			String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.season = season;
		this.altersgruppe = altersgruppe;
		this.franchise = franchise;
		this.medium = medium;
		
		setzeInfos(String.format("Season: %s%n" +
		"Altersgruppe: %s%n" + 
				"Franchise: %s%n" + 
		"Medium: %s", season, altersgruppe, franchise, medium));
	
	}

	
}