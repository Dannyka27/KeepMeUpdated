package MainWindow.mediaPanes;

import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

public class Medium extends TitledPane
{
	protected int ID;
	protected String titel;
	protected String untertitel;
	protected String zusatzinformationen;
	protected String standort;
	protected String link;
	public Medium(int ID, String titel, String untertitel, String zusatzinformationen, String standort, String link)
	{
		this.ID = ID;
		this.titel = titel;
		this.untertitel = untertitel;
		this.zusatzinformationen = zusatzinformationen;
		this.standort = standort;
		this.link = link;
		
		setText(titel);
	}
	
	public void setzeInfos(String infos)
	{
		TextArea l = new TextArea(String.format(
				"Untertitel: %s%n" + 
				"%s%n" + 
				"Standort: %s%n" + 
				"Zusatzinformationen: %s%n" +
				"Link: %s",
                untertitel, infos, standort, zusatzinformationen, link
        ));
		setContent(l);
		l.setEditable(false);
	}
	
	public int getID()
	{
		return ID;
	}
}
