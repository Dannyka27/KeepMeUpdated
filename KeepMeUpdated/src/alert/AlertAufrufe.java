package alert;

import MainWindow.Main;
import MainWindow.mediaPanes.Medium;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import keepMeUpdatedTools.KeepMeUpdatedTools;

import java.util.Optional;

/**
 * Externe Klasse, die das Anzeigen der Alerts steuert.
 * @author Hanna
 */

public class AlertAufrufe {

    /**
     * Ein InfoAlert, wenn ein Wunsch übertragen wurde
     * @param titel Titel des Objekts
     * @param untertitel Untertitel des Objekts
     * @param typ Typ des Objekts
     * @param thek -thek in der das Objekt danach zu finden ist
     */
    public static void infoAlert(String titel, String untertitel, String typ, String thek) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wunsch übertragen");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        KeepMeUpdatedTools.stageIconSetzen(stage);
        String name = titel + ((untertitel == null) ? "" : ": " + untertitel);
        alert.setContentText(typ +" \"" + name + "\" \nist nun in "+ thek +"!");

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(AlertAufrufe.class.getResource("alertstyle.css").toExternalForm());

        alert.show();
    }

    /**
     * Ein loeschenAlert (Confirmation) wird erzeugt.
     * Wenn der Button 'OK' gedrückt wird, wird die Methode 'eintragLoeschen' aus
     * der Datenbank aufgerufen und ihr die ID, der Titel und der Tabellenname des Objektes übergeben. Zudem wird
     * 'true' zurückgegeben. Bei 'Abbruch' wird ein AbbruchAlert erzeugt und 'false' zurückgegeben.
     * @param typ Genutzt um den Titel und Content des Alerts zu setzen
     * @param tabelle Die Tabelle aus der bei 'OK' das Objekt gelöscht werden muss
     * @param m Das Objekt
     * @return Gebraucht dafür, ob das Objekt aus der Liste in Main entfernt werden muss
     */
    public static boolean loeschenAlert(String typ, String tabelle, Medium m) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(typ + " löschen");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        KeepMeUpdatedTools.stageIconSetzen(stage);
        alert.setContentText(typ + " \"" + m.getTitel() + "\" löschen?");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(AlertAufrufe.class.getResource("alertstyle.css").toExternalForm());
        Optional<ButtonType> antwort = alert.showAndWait();
        if (antwort.get() == ButtonType.OK)
        {
            Main.db.eintragLoeschen(m.getID(), m.getTitel(), tabelle);
            return true;
        }
        else
        {
            abbruchAlert(typ, "Löschen", "gelöscht");
            return false;
        }
    }

    /**
     * AbbruchAlert, wenn das Löschen bzw. Übertragen abgebrochen wurde.
     * @param typ Um auszudrücken, was abgebrochen wurde.
     * @param aktionS Substantiv, was abgebrochen wurde.
     * @param aktionV Verb, was abgebrochen wurde
     */
    public static void abbruchAlert(String typ, String aktionS, String aktionV) {
        Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane = abbruch.getDialogPane();
        dialogPane.getStylesheets().add(AlertAufrufe.class.getResource("alertstyle.css").toExternalForm());
        abbruch.setTitle(aktionS + " abgebrochen");
        Stage stage = (Stage) abbruch.getDialogPane().getScene().getWindow();
        KeepMeUpdatedTools.stageIconSetzen(stage);
        abbruch.setContentText(typ +" wurde nicht " + aktionV);
        abbruch.show();
    }

    /**
     * Ein wunschUebertragenAlert (Confirmation) wird erzeugt.
     * Wenn der Button 'OK' gedrückt wird, wird'true' zurückgegeben.
     * Bei 'Abbruch' wird ein abbruchAlert erzeugt und 'false' zurückgegeben.
     * @param typ Der Typ des Mediums, das übertragen werden soll.
     * @param m das Medium
     * @return Benöigt um in aufrufender Methode abhängig der Nutzereingabe zu reagieren.
     */
    public static boolean wunschUebertragenAlert(String typ, Medium m) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(typ + " übertragen?");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        KeepMeUpdatedTools.stageIconSetzen(stage);
        alert.setContentText(typ + " \"" + m.getTitel() + "\" übertragen?");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(AlertAufrufe.class.getResource("alertstyle.css").toExternalForm());
        Optional<ButtonType> antwort = alert.showAndWait();
        if (antwort.get() == ButtonType.OK)
        {
            return true;
        }
        else
        {
            abbruchAlert(typ, "Übertragung", "übertragen");
            return false;
        }
    }

    /**
     * Ein zahlAlert (Error) wird erzeugt.
     * Er wird ausgegeben, wenn in die Folge TextFields kein int oder null eingegeben wurde
     * @param folge Der String der eingegeben wurde.
     */
    public static void zahlAlert(String folge) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Falsche Folgennummer");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        KeepMeUpdatedTools.stageIconSetzen(stage);
        alert.setHeaderText("Bitte eine Zahl eingeben!");
        alert.setContentText("Es muss eine ganzzahlige\nFolgennummer eingegeben werden");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(AlertAufrufe.class.getResource("alertstyle.css").toExternalForm());
        alert.showAndWait();
        System.out.println("Error: " + folge + " ist keine adquate Folgennummer");
    }
}
