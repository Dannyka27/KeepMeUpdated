package MainWindow.mediaPanes;

import java.io.IOException;
import java.util.LinkedHashMap;

import Hinzufuegen.HinzufuegenSerieController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class Serie extends Medium
{
	private String season;
	private String altersgruppe;
	private String franchise;
	private String medium;
	
	public Serie(int ID, String titel, String untertitel, String season, String zusatzinformationen,
			String altersgruppe, String standort, String franchise, String medium, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.season = season;
		this.altersgruppe = altersgruppe;
		this.franchise = franchise;
		this.medium = medium;

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		addInfo("Season", "" + season);
		addInfo("Altersgruppe", altersgruppe);
		addInfo("Franchise", franchise);
		addInfo("Medium", medium);
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Serien");
		lhm.put("Season", getSeason());
		lhm.put("Altersgruppe", getAltersgruppe());
		lhm.put("Franchise", getFranchise());
		lhm.put("Medium", getMedium());
		return lhm;
	}

	public String getSeason()
	{
		return season;
	}

	public void setSeason(String season)
	{
		this.season = season;
	}

	public String getAltersgruppe()
	{
		return altersgruppe;
	}

	public void setAltersgruppe(String altersgruppe)
	{
		this.altersgruppe = altersgruppe;
	}

	public String getFranchise()
	{
		return franchise;
	}

	public void setFranchise(String franchise)
	{
		this.franchise = franchise;
	}

	public String getMedium()
	{
		return medium;
	}

	public void setMedium(String medium)
	{
		this.medium = medium;
	}
	
	@Override
	public void onEdit(ActionEvent value)
	{
		try 
		{
			//Die passende FXML wird geladen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenSerie.fxml"));
			root = loader.load();
			HinzufuegenSerieController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
			
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
