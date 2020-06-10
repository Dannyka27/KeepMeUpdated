package Wishlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    public void promptAusgabe(String ausgabe)
    {
        if(ausgabe != null)
        {wzAusgabeTextField.setPromptText(ausgabe);}
    }
    public void promptHerausgeber(String herausgeber)
    {
        if(herausgeber != null)
        {wzHerausgeberTextField.setPromptText(herausgeber);}
    }
    public void promptGenreBox(String genre)
    {
        wzGenreChoiceBox.setValue(genre);
    }
}
