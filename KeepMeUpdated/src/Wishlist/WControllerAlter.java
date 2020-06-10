package Wishlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class WControllerAlter extends WController{
    @FXML
    private Label wAlterLabel;
    @FXML
    private ChoiceBox<String> wAlterChoiceBox;
    final ObservableList<String> wAlterChoiceBoxList = FXCollections.observableArrayList("Kinder", "Familie", "Eltern");

    @FXML
    void initialize()
    {
        super.initialize();
        wAlterChoiceBox.setValue("Familie");
        wAlterChoiceBox.setItems(wAlterChoiceBoxList);
    }
    public void promptAlterBox(String alter)
    {
        wAlterChoiceBox.setValue(alter);
    }
}
