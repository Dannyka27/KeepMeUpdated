package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

import datenhaltung.DatenbankEintrag;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

public class Medium extends TitledPane implements DatenbankEintrag
{
	protected int ID;
	protected String titel;
	protected String untertitel;
	protected String zusatzinformationen;
	protected String standort;
	protected String link;

	private Button bearbeiten;
	private Button loeschen;

	private GridPane pnlInfo;
	private int infoCounter = 0;

	public Medium(int ID, String titel, String untertitel, String zusatzinformationen, String standort, String link)
	{
		this.ID = ID;
		this.titel = titel;
		this.untertitel = untertitel;
		this.zusatzinformationen = zusatzinformationen;
		this.standort = standort;
		this.link = link;

		pnlInfo = new GridPane();
		pnlInfo.setPadding(new Insets(10, 10, 10, 20));
		pnlInfo.setVgap(10);
		pnlInfo.setHgap(5);
		setContent(pnlInfo);
		
		bearbeiten = new Button("Bearbeiten");
		loeschen = new Button("Löschen");

		setText(titel + ": " + untertitel);
	}

	public void addInfo(String schluessel, String wert)
	{
		if (schluessel == null || wert == null)
			return;
		if (schluessel.length() < 1 || wert.length() < 1)
			return;

		pnlInfo.add(new Label(schluessel + ":"), 0, infoCounter);
		pnlInfo.add(new Label(wert), 1, infoCounter++);
	}

	public void updateRahmenInfos()
	{
		pnlInfo.getChildren().remove(0, pnlInfo.getChildren().size());
		addInfo("Zusatzinformationen", zusatzinformationen);
		updateInfos();
		addInfo("Standort", standort);
		addInfo("Link", link);
		pnlInfo.add(bearbeiten, 2, infoCounter);
		pnlInfo.add(loeschen, 3, infoCounter);
	}

	protected void updateInfos()
	{

	}

	public int getID()
	{
		return ID;
	}

	public String getTitel()
	{
		return titel;
	}

	public void setTitel(String titel)
	{
		this.titel = titel;
	}

	public String getUntertitel()
	{
		return untertitel;
	}

	public void setUntertitel(String untertitel)
	{
		this.untertitel = untertitel;
	}

	public String getZusatzinformationen()
	{
		return zusatzinformationen;
	}

	public void setZusatzinformationen(String zusatzinformationen)
	{
		this.zusatzinformationen = zusatzinformationen;
	}

	public String getStandort()
	{
		return standort;
	}

	public void setStandort(String standort)
	{
		this.standort = standort;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}
	
	public void setOnEdit(EventHandler<ActionEvent> value)
	{
		bearbeiten.setOnAction(value);
	}
	
	public void setOnDelete(EventHandler<ActionEvent> value)
	{
		loeschen.setOnAction(value);
	}
	
	@Override
	public LinkedHashMap<String, String> dbSchluesselWerte()
	{
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		if(ID > 0) 
			map.put("ID", "" + getID());
		
		map.put("Titel", getTitel());
		map.put("Untertitel", getUntertitel());
		map.put("Zusatzinformationen", getZusatzinformationen());
		map.put("Standort", getStandort());
		map.put("Link", getLink());
		
		return map;
	}
}