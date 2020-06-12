package MainWindow.mediaPanes;

import java.io.IOException;
import java.util.LinkedHashMap;

import Hinzufuegen.HinzufuegenGameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class Spiel extends Medium
{

	private String altersgruppe;
	private String franchise;
	private String plattform;

	public Spiel(int ID, String titel, String untertitel, String altersgruppe, String zusatzinformationen,
			String standort, String franchise, String plattform, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.altersgruppe = altersgruppe;
		this.franchise = franchise;
		this.plattform = plattform;

		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		addInfo("Altersgruppe", altersgruppe);
		addInfo("Franchise", franchise);
		addInfo("Plattform", plattform);
	}

	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Altersgruppe", getAltersgruppe());
		lhm.put("Franchise", getFranchise());
		lhm.put("Plattform", getPlattform());
		return lhm;
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

	public String getPlattform()
	{
		return plattform;
	}

	public void setPlattform(String plattform)
	{
		this.plattform = plattform;
	}
	
	@Override
	public void onEdit(ActionEvent value)
	{
		try 
		{
			//Die passende FXML wird geladen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenGame.fxml"));
			root = loader.load();
			HinzufuegenGameController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
			
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
		return "Spiele";
	}
}
