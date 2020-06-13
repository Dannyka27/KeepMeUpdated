package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Hoerspiel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.apache.commons.lang3.StringUtils;

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

    private boolean isInt(String f)
    {
            try
            {
                if (!StringUtils.isBlank(f)) {
                    int folge = Integer.parseInt(f);
                    System.out.println("Folge Nummer: " + folge);
                }
                return true;
            }
            catch(NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falsche Folgennummer");
                alert.setHeaderText("Eyy, gib gefälligst ne Zahl ein!");
                alert.setContentText("Es muss eine ganzzahlige Folgennummer eingegeben werden");
                alert.showAndWait();
                System.out.println("Error: " + f + " ist keine adquate Folgennummer");
                return false;
            }
    }

    public void promptFolge(String folge)
    {
        if(folge != null)
        {wFolgeTextField.setPromptText(folge);}
    }

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
            /*if(wFolgeTextField.getText().equals("0"))
            {
                hoerspiel.setFolge(null);
            }
            else
            {*/
                hoerspiel.setFolge(wFolgeTextField.getText());
            /*}*/

            medium = hoerspiel;
            super.wSpeichernOnAction(actionEvent);

            MainController.instanz.whoerspiellist.clear();
            MainController.instanz.wishlistfuellen("Hörspiele", "Hörspiele");
        }
    }
}
