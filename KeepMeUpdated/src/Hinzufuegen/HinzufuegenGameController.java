package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class HinzufuegenGameController extends ControllerFranchise{

    @FXML
    private Label hgPlattformLabel;
    @FXML
    private ChoiceBox<String> hgPlattformChoiceBox;

    final ObservableList<String> hgPlattformChoiceBoxList = FXCollections.observableArrayList("PS4", "PC");

    @FXML
    void initialize() {
        hMediumChoiceBox.setValue("Games");
        super.initialize();
        hgPlattformChoiceBox.setValue("PS4");
        hgPlattformChoiceBox.setItems(hgPlattformChoiceBoxList);
    }

    public void promptPlattformBox(String plattform)
    {
        hgPlattformChoiceBox.setValue(plattform);
    }
}
