package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class ControllerAlter extends Controller{
    @FXML
    private Label hAlterLabel;
    @FXML
    private ChoiceBox<String> hAlterChoiceBox;
    final ObservableList<String> hAlterChoiceBoxList = FXCollections.observableArrayList("Kinder", "Familie", "Eltern");

    @FXML
    void initialize()
    {
        super.initialize();
        hAlterChoiceBox.setValue("Familie");
        hAlterChoiceBox.setItems(hAlterChoiceBoxList);
    }
}