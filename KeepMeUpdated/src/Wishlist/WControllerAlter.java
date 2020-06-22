package Wishlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Oberklasse der .fxml Controller, die eine Altersangabe haben. (Wishlist)
 * Erbt von WController.
 * @author Hanna
 */

public class WControllerAlter extends WController{
    @FXML
    private Label wAlterLabel;
    @FXML
    protected ChoiceBox<String> wAlterChoiceBox;

    final ObservableList<String> wAlterChoiceBoxList = FXCollections.observableArrayList("Kinder", "Familie", "Eltern");

    @FXML
    void initialize()
    {
        super.initialize();
        wAlterChoiceBox.setValue("Familie");
        wAlterChoiceBox.setItems(wAlterChoiceBoxList);
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptAlterBox(String alter)
    {
        wAlterChoiceBox.setValue(alter);
    }
}
