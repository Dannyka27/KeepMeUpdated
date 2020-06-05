package MainWindow.mediaPanes;

@SuppressWarnings("unused")
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
		
		setzeInfos(String.format("Altersgruppe: %s%n" +
		"Franchise: %s%n" +
				"Plattform: %s", altersgruppe, franchise, plattform));
	}

}


