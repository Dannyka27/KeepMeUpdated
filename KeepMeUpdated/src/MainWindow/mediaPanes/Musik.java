package MainWindow.mediaPanes;

import java.io.IOException;
import java.util.LinkedHashMap;

import Hinzufuegen.HinzufuegenMusikController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

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
		addInfo("Genre", genre);
		addInfo("Franchise", franchise);
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Musik");
		lhm.put("Genre", getGenre());
		lhm.put("Franchise", getFranchise());
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

	public String getFranchise()
	{
		return franchise;
	}

	public void setFranchise(String franchise)
	{
		this.franchise = franchise;
	}
	
	@Override
	public void onEdit(ActionEvent value)
	{
		try 
		{
			//Die passende FXML wird geladen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenMusik.fxml"));
			root = loader.load();
			HinzufuegenMusikController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
			
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
		return "Hörspiele";
	}
}
