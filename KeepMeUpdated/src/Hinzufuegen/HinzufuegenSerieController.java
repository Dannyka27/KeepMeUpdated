package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class HinzufuegenSerieController extends ControllerFranchise{
    @FXML
    private Label hTypLabel;
    @FXML
    private Label hSeasonLabel;
    @FXML
    private ChoiceBox<String> hSeasonChoiceBox;
    @FXML
    private ChoiceBox<String> hTypChoiceBox;

    final ObservableList<String> hTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Blu-ray");
    final ObservableList<String> hSeasonChoiceBoxList = FXCollections.observableArrayList("1", "2", "3","4","5","6","7","8","9","10");

    @FXML
    void initialize()
    {
        hMediumChoiceBox.setValue("Serien");
        super.initialize();
        hTypChoiceBox.setValue("Normal");
        hTypChoiceBox.setItems(hTypChoiceBoxList);
        hSeasonChoiceBox.setValue("1");
        hSeasonChoiceBox.setItems(hSeasonChoiceBoxList);
    }
}
