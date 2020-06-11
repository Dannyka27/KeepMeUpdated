package MainWindow.mediaPanes;

import java.io.IOException;
import java.util.LinkedHashMap;

import Hinzufuegen.HinzufuegenHoerspielController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class Hoerspiel extends Medium
{
	private int folge;
	private String altersgruppe;

	public Hoerspiel(int ID, String titel, String untertitel, int folge, String zusatzinformationen,
			String altersgruppe, String standort, String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.folge = folge;
		this.altersgruppe = altersgruppe;
		updateRahmenInfos();
	}

	@Override
	protected void updateInfos()
	{
		super.updateInfos();
		addInfo("Folge", "" + folge);
		addInfo("Altersgruppe", altersgruppe);
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
		lhm.put("Typ", "Hörspiele");
		lhm.put("Folge", "" +  getFolge());
		lhm.put("Altersgruppe", getAltersgruppe());
		return lhm;
	}

	public int getFolge()
	{
		return folge;
	}

	public void setFolge(int folge)
	{
		this.folge = folge;
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenHoerspiel.fxml"));
			root = loader.load();
			HinzufuegenHoerspielController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
			
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
