package MainWindow.mediaPanes;

@SuppressWarnings("unused")
public class Musik extends Medium
{
	private String genre;
	public Musik(int ID, 
			String titel, 
			String untertitel,
			String genre,
			String zusatzinformationen, 
			String standort, 
			String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.genre = genre;
		
		setzeInfos(String.format("Genre: %s", genre));
	}

}

