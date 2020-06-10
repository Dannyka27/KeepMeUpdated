package MainWindow.mediaPanes;

public class Musik extends Medium
{
	private String genre;
	private String franchise;// von mir noch eingefügt

	public Musik(int ID, String titel, String untertitel, String genre, String franchise, // von mir noch eingefügt
			String zusatzinformationen, String standort, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.genre = genre;
		this.franchise = franchise;// von mir noch eingefügt

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Genre", genre);
		addInfo("Franchise", franchise);
	}

}
