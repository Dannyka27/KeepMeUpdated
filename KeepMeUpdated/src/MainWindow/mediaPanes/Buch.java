package MainWindow.mediaPanes;

import java.io.IOException;
import java.util.LinkedHashMap;

import Hinzufuegen.HinzufuegenBuchController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

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

	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Bücher");
		lhm.put("Genre", getGenre());
		lhm.put("Autor", getAutor());
		lhm.put("Franchise", getFranchise());
		lhm.put("Altersgruppe", getAltersgruppe());
		return lhm;
	}
	
	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getAutor()
	{
		return autor;
	}

	public void setAutor(String autor)
	{
		this.autor = autor;
	}

	public String getFranchise()
	{
		return franchise;
	}

	public void setFranchise(String franchise)
	{
		this.franchise = franchise;
	}

	public String getAltersgruppe()
	{
		return altersgruppe;
	}

	public void setAltersgruppe(String altersgruppe)
	{
		this.altersgruppe = altersgruppe;
	}
	
	@Override
	public void onEdit(ActionEvent value)
	{
		try 
		{
			//Die passende FXML wird geladen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenBuch.fxml"));
			root = loader.load();
			HinzufuegenBuchController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
			
			//Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
			hinzufuegenController.promptMedium(this);
			super.onEdit(value);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public String getTabellenTitel()
	{
		return "Bücher";
	}
}
