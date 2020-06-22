package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Zeitschrift;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller für WishlistZeitschrift.fxml
 * @author Hanna
 *
 * wSpeichernOnAction
 * @author Anika
 */

public class WishlistZeitschriftController extends WController{
    @FXML
    private TextField wzAusgabeTextField;
    @FXML
    private TextField wzHerausgeberTextField;
    @FXML
    private Label wzGenreLabel;
    @FXML
    private ChoiceBox<String> wzGenreChoiceBox;

    final ObservableList<String> wzGenreList = FXCollections.observableArrayList("Informatik", "Fotografie", "Anmation");

    @FXML
    void initialize() {
        wMediumChoiceBox.setValue("Zeitschriften");
        super.initialize();
        wzGenreChoiceBox.setValue("Informatik");
        wzGenreChoiceBox.setItems(wzGenreList);
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptAusgabe(String ausgabe) {
        if(ausgabe != null)
        {wzAusgabeTextField.setPromptText(ausgabe);}
    }
    public void promptHerausgeber(String herausgeber) {
        if(herausgeber != null)
        {wzHerausgeberTextField.setPromptText(herausgeber);}
    }
    public void promptGenreBox(String genre)
    {
        wzGenreChoiceBox.setValue(genre);
    }

    /*----------------------------------------------------*/
    @Override
    public void wSpeichernOnAction(ActionEvent actionEvent)
    {
        Zeitschrift zeitung = null;
        if (medium == null)
            zeitung = new Zeitschrift(-10, "", "", "", "", "", "", "", "");
        else if (medium instanceof Zeitschrift)
            zeitung = (Zeitschrift) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenZeitschriftController ist keine Zeitschrift!");

        zeitung.setAusgabe(wzAusgabeTextField.getText());
        zeitung.setHerausgeber(wzHerausgeberTextField.getText());
        zeitung.setGenre(wzGenreChoiceBox.getValue());

        medium = zeitung;
        super.wSpeichernOnAction(actionEvent);

        MainController.instanz.wzeitschriftlist.clear();
        MainController.instanz.wishlistFuellen("Bücher", "Zeitschriften");
    }
}