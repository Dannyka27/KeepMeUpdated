package MainWindow.mediaPanes;

@SuppressWarnings("unused")
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
		
		setzeInfos(String.format("Genre: %s%n" +
				"Autor: %s%n" +
				"Franchise: %s%n" +
				"Altersgruppe: %s", genre, autor, franchise, altersgruppe));
	}
	
	
}
