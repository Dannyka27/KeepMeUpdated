package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Spiel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Controller für WishlistGame.fxml
 * @author Hanna
 *
 * wSpeichernOnAction
 * @author Anika
 */

public class WishlistGameController extends WControllerFranchise {
    @FXML
    private Label wgPlattformLabel;
    @FXML
    private ChoiceBox<String> wgPlattformChoiceBox;

    final ObservableList<String> wgPlattformChoiceBoxList = FXCollections.observableArrayList("PS4", "PC");

    @FXML
    void initialize() {
        wMediumChoiceBox.setValue("Games");
        super.initialize();
        wgPlattformChoiceBox.setValue("PS4");
        wgPlattformChoiceBox.setItems(wgPlattformChoiceBoxList);

    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptPlattformBox(String plattform)
    {
        wgPlattformChoiceBox.setValue(plattform);
    }

    /*----------------------------------------------------*/
    @Override
    public void wSpeichernOnAction(ActionEvent actionEvent)
    {
        Spiel spiel = null;
        if (medium == null)
            spiel = new Spiel(-10, "", "", "", "", "", "", "", "");
        else if (medium instanceof Spiel)
            spiel = (Spiel) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenGameController ist kein Spiel!");

        spiel.setAltersgruppe(wAlterChoiceBox.getValue());
        spiel.setFranchise(wFranchiseTextField.getText());
        spiel.setPlattform(wgPlattformChoiceBox.getValue());

        medium = spiel;
        super.wSpeichernOnAction(actionEvent);

        MainController.instanz.wgamelist.clear();
        MainController.instanz.wishlistFuellen("Spiele", "Spiele");
    }
}
