package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HinzufuegenZeitschriftController extends Controller{
    @FXML
    private TextField hzHerausgeberTextField;
    @FXML
    private Label hzGenreLabel;
    @FXML
    private ChoiceBox<String> hzGenreChoiceBox;

    final ObservableList<String> hzGenreList = FXCollections.observableArrayList("Informatik", "Fotografie", "Anmation");


    @FXML
    void initialize() {
        hMediumChoiceBox.setValue("Zeitschriften");
        super.initialize();
        hzGenreChoiceBox.setValue("Informatik");
        hzGenreChoiceBox.setItems(hzGenreList);
    }
}
