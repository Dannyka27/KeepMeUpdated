package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Hoerspiel;
import alert.AlertAufrufe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.apache.commons.lang3.StringUtils;

/**
 * Controller für WishlistHoerspiel.fxml
 * @author Hanna
 *
 * wSpeichernOnAction
 * @author Anika
 */

public class WishlistHoerspielController extends WControllerAlter {
    @FXML
    private TextField wFolgeTextField;

    @FXML
    void initialize()
    {
        wMediumChoiceBox.setValue("Hörspiele");
        super.initialize();
        wFolgeTextField.setOnKeyPressed(keyEvent ->
        {
            if(keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB)
            {
                isInt(wFolgeTextField.getText());
            }
        });
    }

    /**
     * Diese Methode wird aus initialize aufgerufen, wenn aus dem wFolgeTextField heraus 'Enter'
     * oder 'Tab' gedrückt wurde. Außerdem wurde sie unten als Bedingung für die Speichern Methode
     * eingebaut, damit nur ints gespeichert werden können. Außerdem soll es auch möglich sein,
     * keine Folennummer anzugeben, daher wird nur versucht einen int zu parsen, wenn das Feld nicht
     * leer ist. So wird keine Fehlermeldung ausgegeben, da sonst kein int von null geparst
     * werden kann. Der Fehler wird über die Methode 'zahlAlert' der Klasse 'AlertAufrufe' des
     * Packages 'alert' aufgerufen, übergeben wird der String f.
     * @param f Die Eingabe aus dem TextField
     * @return Wichtig für die Speichern Methode, die nur ausgeführt wird,
     * wenn der return Wert true ist.
     */
    private boolean isInt(String f)
    {
        try
        {
            if(!StringUtils.isBlank(f))
            {
                int folge = Integer.parseInt(f);
                System.out.println("Folge Nummer: " + folge);
            }
            return true;
        }
        catch(NumberFormatException e)
        {
            AlertAufrufe.zahlAlert(f);
            return false;
        }
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptFolge(String folge) {
        if(folge != null)
        {wFolgeTextField.setPromptText("Folge: " +folge+ " {Zum zurücksetzten \"0\" eingeben}");
        }
    }

    /*----------------------------------------------------*/
    @Override
    public void wSpeichernOnAction(ActionEvent actionEvent)
    {
        if(isInt(wFolgeTextField.getText())) {
            Hoerspiel hoerspiel = null;
            if (medium == null)
                hoerspiel = new Hoerspiel(-10, "", "", "", "", "", "", "");
            else if (medium instanceof Hoerspiel)
                hoerspiel = (Hoerspiel) medium;
            else
                throw new RuntimeException("Das Medium in HinzufuegenHoerspielController ist kein Hörspiel!");

            hoerspiel.setAltersgruppe(wAlterChoiceBox.getValue());
            if(wFolgeTextField.getText().equals("0"))
            {
                hoerspiel.setFolge(null);
            }
            else
            {
                hoerspiel.setFolge(wFolgeTextField.getText());
            }
            medium = hoerspiel;
            super.wSpeichernOnAction(actionEvent);

            MainController.instanz.whoerspiellist.clear();
            MainController.instanz.wishlistFuellen("Hörspiele", "Hörspiele");
        }
    }
}
