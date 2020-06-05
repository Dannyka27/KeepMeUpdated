package MainWindow.mediaPanes;

@SuppressWarnings("unused")
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
		
		
		setzeInfos(String.format("Medium: %s%n" +
				"Franchise: %s%n" + 
				"Altersgruppe: %s", medium, franchise, altersgruppe));
	}

}

