package MainWindow.mediaPanes;

import java.util.LinkedHashMap;
import java.util.function.Consumer;

import MainWindow.Main;
import datenhaltung.DatenbankEintrag;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Oberklasse für alle Medienobjekte, namentlich: Film, Serie, Buch, Zeitschrift, Musik, Hoerspiel, Spiel
 * @author Anika
 */
public abstract class Medium extends TitledPane implements DatenbankEintrag
{
	/** 
	 * Einmaliger Schlüssel, der das Addressieren in der Datenbank erlaubt. 
	 * Sollte kleiner 0 sein, wenn das Objekt noch nicht in der Datenbank gespeichert ist. 
	 */
	protected int ID;
	protected String titel;
	protected String untertitel;
	protected String zusatzinformationen;
	protected String standort;
	protected String link;

	private Button bearbeiten;
	private Button loeschen;

	/** Enhält, wenn ausgeklappt, alle wichtigen Infos über das Medium */
	private GridPane pnlInfo;
	/** Wird hochgezählt, damit jedes Element im Grid Pane seine eigene Spalte bekommt */
	private int infoCounter = 0;

	protected Parent root;
	
	private Consumer<String> updateMethod;
	
	/**
	 * Erstellt ein Medien Objekt
	 * @param ID Die ID des Mediums in der Datenbank, oder eine Zahl kleiner 0 wenn dieses noch nicht existiert.
	 * @param titel Der Titel des Mediums.
	 * @param untertitel Der Untertitel des Mediums, darf leer gelassen werden.
	 * @param zusatzinformationen Zusatzinformationen über das Medium, darf leer gelassen werden.
	 * @param standort Der Standort des Mediums
	 * @param link Der Link des Mediums, wenn es in der Wunschliste ist, oder leer/null wenn nicht.
	 */
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
		
		//Setze EventHandler für BEARBEITEN Button
		bearbeiten.setOnAction(this::onEdit);
		
		//Setze EventHandler für LÖSCHEN Button
		loeschen.setOnAction(e -> {
			Main.db.eintragLoeschen(this, getTabellenTitel());
			updateParentAccordion();
		});

		//Setze den Titel/Untertitel der TitledPane
		setText(titel + ((untertitel == null) ? "" : ": " + untertitel));
	}

	/**
	 * Füge dem ausgeklappten Panel Infos über das Medium hinzu.
	 * @param schluessel Beschreibung der Info, z.B. Untertitel.
	 * @param wert Der Wert der Info
	 */
	public void addInfo(String schluessel, String wert)
	{
		//Überspringe leere Infos
		if (schluessel == null || wert == null)
			return;
		if (schluessel.length() < 1 || wert.length() < 1)
			return;

		//Füge Info in den ausklappbaren Container ein
		pnlInfo.add(new Label(schluessel + ":"), 0, infoCounter);
		pnlInfo.add(new Label(wert), 1, infoCounter++);
	}

	/**
	 * Updated alle Infos, die im TitledPane und GridPane über das Medium stehen. Die Methode 
	 * ruft intern {@link #updateInfos()} auf, um die Unterklassenspezifischen Infos in der Mitte zu Updaten.
	 */
	public void updateRahmenInfos()
	{
		pnlInfo.getChildren().remove(0, pnlInfo.getChildren().size());
		
		//Zeige Datenbank IDs im Debug Modus an
		if(Main.DEBUG)
			addInfo("ID", "" + getID());
		
		addInfo("Zusatzinformationen", zusatzinformationen);
		updateInfos();
		addInfo("Standort", standort);
		addInfo("Link", link);
		pnlInfo.add(bearbeiten, 2, infoCounter);
		pnlInfo.add(loeschen, 3, infoCounter);
	}

	/**
	 * Wird in den Unterklassen überschrieben, um einzelne Infos z.B. vom Buch in der Mitte von den gemeinsamen
	 * Infos anzuzeigen
	 */
	protected abstract void updateInfos();
	
	/**
	 * Führe die Methode die mit {@link #setUpdateMethod(Consumer)} gesetzt wurde aus, 
	 * um änderungen an dem Medium in der GUI anzuzeigen.
	 * @return True wenn das Update erfolgreich war, false wenn die Update Methode nicht gesetzt wurde.
	 */
	public boolean updateParentAccordion()
	{
		if(updateMethod == null)
		{
			System.out.println("Hinweis: Konnte Acordion nicht updaten weil keine Update Methode spezifiziert wurde!");
			return false;
		}
		
		updateMethod.accept("");
		return true;
	}

	/**
	 * Setze die Methode, die ausgeführt werden soll um Änderungen (z.B. im Accordion) anzuzeigen. Wird u.a. von 
	 * dem Löschen Button verwendet.
	 * @param func
	 */
	public void setUpdateMethod(Consumer<String> func)
	{
		this.updateMethod = func;
	}
	
	/**
	 * Wird ausgeführt, wenn der Bearbeiten Button auf dem TitledPane geklickt wird. Öffnet den bearbeiten Dialog.
	 * @param value Wird vom Event gesetzt
	 */
	public void onEdit(ActionEvent value)
	{
		//Die Stage wird angelegt und die FXML hinzugefÃ¼gt
		Stage primaryStage = new Stage();
		primaryStage.setTitle(getClass().getSimpleName() + " bearbeiten");
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void setOnDelete(EventHandler<ActionEvent> value)
	{
		loeschen.setOnAction(value);
	}
	
	/**
	 * Mit dem Aufruf der Methode werden die benötigten Schlüssel-Werte zum Abspeichern in die Datenbank generiert.
	 * @return Alle Schlüssel-Werte, kann auch leere Strings und Null enthalten, als einziges die ID wird evtl leer gelassen, wenn sie kleiner 1 ist. Das Filtern von leeren Einträgen geschieht in der Datenbank.
	 */
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
	
	/**
	 * Gibt den Titel der Tabelle in der Datenbank an.
	 * @return Den Namen der zugehörigen Tabelle in der Datenbank (z.B. Bücher), oder null für Medium selber.
	 */
	public abstract String getTabellenTitel();
	
	@Override
	public String toString()
	{
		return String.format("%s %s: %s (%d)", getClass().getSimpleName(), getTitel(), getUntertitel(), getID());
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
}