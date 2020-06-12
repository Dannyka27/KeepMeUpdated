package MainWindow.mediaPanes;

import java.io.IOException;
import java.util.LinkedHashMap;

import Hinzufuegen.HinzufuegenFilmController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class Film extends Medium
{
	private String medium;
	private String franchise;
	private String altersgruppe;

	public Film(int ID, String titel, String untertitel, String altersgruppe, String medium, String zusatzinformationen,
			String standort, String franchise, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.medium = medium;
		this.franchise = franchise;
		this.altersgruppe = altersgruppe;

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		addInfo("Medium", medium);
		addInfo("Franchise", franchise);
		addInfo("Altersgruppe", altersgruppe);
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Filme");
		lhm.put("Medium", getMedium());
		lhm.put("Franchise", getFranchise());
		lhm.put("Altersgruppe", getAltersgruppe());
		return lhm;
	}

	public String getMedium()
	{
		return medium;
	}

	public void setMedium(String medium)
	{
		this.medium = medium;
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenFilm.fxml"));
			root = loader.load();
			HinzufuegenFilmController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
			
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
		return "Filme";
	}
}
