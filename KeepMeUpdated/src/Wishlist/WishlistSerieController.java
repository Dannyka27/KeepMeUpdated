package Wishlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WishlistSerieController extends WControllerFranchise {
    @FXML
    private TextField wLinkTextField;
    @FXML
    private Label wTypLabel;
    @FXML
    private Label wSeasonLabel;
    @FXML
    private ChoiceBox<String> wSeasonChoiceBox;
    @FXML
    private ChoiceBox<String> wTypChoiceBox;

    final ObservableList<String> wTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Blu-ray");


    final ObservableList<String> wSeasonChoiceBoxList = FXCollections.observableArrayList("1", "2", "3","4","5","6","7","8","9","10");

    @FXML
    void initialize()
    {
        wMediumChoiceBox.setValue("Serien");
        super.initialize();
        wTypChoiceBox.setValue("Normal");
        wTypChoiceBox.setItems(wTypChoiceBoxList);
        wSeasonChoiceBox.setValue("1");
        wSeasonChoiceBox.setItems(wSeasonChoiceBoxList);
    }
}
