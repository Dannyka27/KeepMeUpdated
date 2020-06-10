package MainWindow.mediaPanes;

public class Buch extends Medium
{
	private String genre;
	private String autor;
	private String franchise;
	private String altersgruppe;

	public Buch(int ID, String titel, String untertitel, String genre, String standort, String autor,
			String zusatzinformationen, String franchise, String altersgruppe, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.genre = genre;
		this.autor = autor;
		this.franchise = franchise;
		this.altersgruppe = altersgruppe;

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Genre", genre);
		addInfo("Autor", autor);
		addInfo("Franchise", franchise);
		addInfo("Altersgruppe", altersgruppe);
	}

}
