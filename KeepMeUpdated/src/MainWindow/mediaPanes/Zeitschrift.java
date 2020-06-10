package MainWindow.mediaPanes;

public class Zeitschrift extends Medium
{
	private String ausgabe;
	private String herausgeber;
	private String genre;

	public Zeitschrift(int ID, String titel, String untertitel, String herausgeber, String ausgabe,
			String zusatzinformationen, String genre, String standort, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.ausgabe = ausgabe;
		this.herausgeber = herausgeber;
		this.genre = genre;
		
		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Ausgabe", ausgabe);
		addInfo("Herausgeber", herausgeber);
		addInfo("Genre", genre);
	}
}
