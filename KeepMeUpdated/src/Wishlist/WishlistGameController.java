package Wishlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WishlistGameController extends WControllerFranchise {
    @FXML
    private Label wgPlattformLabel;
    @FXML
    private ChoiceBox<String> wgPlattformChoiceBox;

    final ObservableList<String> wgPlattformChoiceBoxList = FXCollections.observableArrayList("PS4", "PC");


    @FXML
    void initialize() {
        wMediumChoiceBox.setValue("Games");
        super.initialize();
        wgPlattformChoiceBox.setValue("PS4");
        wgPlattformChoiceBox.setItems(wgPlattformChoiceBoxList);

    }

    public void promptPlattformBox(String plattform)
    {
        wgPlattformChoiceBox.setValue(plattform);
    }
}
