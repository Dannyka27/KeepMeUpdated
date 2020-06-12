package MainWindow.mediaPanes;

import java.io.IOException;
import java.util.LinkedHashMap;

import Hinzufuegen.HinzufuegenZeitschriftController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

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
		addInfo("Ausgabe", ausgabe);
		addInfo("Herausgeber", herausgeber);
		addInfo("Genre", genre);
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Zeitschriften");
		lhm.put("Ausgabe", getAusgabe());
		lhm.put("Herausgeber", getHerausgeber());
		lhm.put("Genre", getGenre());
		return lhm;
	}

	public String getAusgabe()
	{
		return ausgabe;
	}

	public void setAusgabe(String ausgabe)
	{
		this.ausgabe = ausgabe;
	}

	public String getHerausgeber()
	{
		return herausgeber;
	}

	public void setHerausgeber(String herausgeber)
	{
		this.herausgeber = herausgeber;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}
	
	@Override
	public void onEdit(ActionEvent value)
	{
		try 
		{
			//Die passende FXML wird geladen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenZeitschrift.fxml"));
			root = loader.load();
			HinzufuegenZeitschriftController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
			
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
