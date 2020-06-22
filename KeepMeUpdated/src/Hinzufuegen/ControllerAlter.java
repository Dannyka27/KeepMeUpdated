package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Oberklasse der .fxml Controller, die eine Altersangabe haben. (Hinzufuegen)
 * Erbt von Controller
 * @author Hanna
 */

public class ControllerAlter extends Controller{
    @FXML
    private Label hAlterLabel;
    @FXML
    protected ChoiceBox<String> hAlterChoiceBox;

    final ObservableList<String> hAlterChoiceBoxList = FXCollections.observableArrayList("Kinder", "Familie", "Eltern");

    @FXML
    void initialize()
    {
        super.initialize();
        hAlterChoiceBox.setValue("Familie");
        hAlterChoiceBox.setItems(hAlterChoiceBoxList);
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptAlterBox(String alter)
    {
        hAlterChoiceBox.setValue(alter);
    }
}
