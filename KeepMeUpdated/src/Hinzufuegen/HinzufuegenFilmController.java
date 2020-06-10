package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;


public class HinzufuegenFilmController extends ControllerFranchise{
    @FXML
    private Label hTypLabel;
    @FXML
    private ChoiceBox<String> hTypChoiceBox;

    final ObservableList<String> hTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Blu-ray");

    @FXML
    void initialize() {
        hMediumChoiceBox.setValue("Filme");
        super.initialize();
        hTypChoiceBox.setValue("Normal");
        hTypChoiceBox.setItems(hTypChoiceBoxList);
    }

    public void promptTyp(String typ)
    {
        hTypChoiceBox.setValue(typ);
    }
}
