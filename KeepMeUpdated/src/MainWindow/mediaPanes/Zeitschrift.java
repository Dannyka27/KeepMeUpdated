package MainWindow.mediaPanes;

@SuppressWarnings("unused")
public class Zeitschrift extends Medium
{
	private String ausgabe;
	private String herausgeber;
	private String genre;
	public Zeitschrift(int ID, 
			String titel, 
			String untertitel,
			String herausgeber,
			String ausgabe,
			String zusatzinformationen,
			String genre,
			String standort,
			String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.ausgabe = ausgabe;
		this.herausgeber = herausgeber;
		this.genre = genre;
		
		setzeInfos(String.format("Ausgabe: %s%n" + 
				"Herausgeber: %s%n" + 
				"Genre: %s", ausgabe, herausgeber, genre));
	}

}