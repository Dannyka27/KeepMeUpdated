package Wishlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WishlistFilmController extends WControllerFranchise{
    @FXML
    private Label wTypLabel;
    @FXML
    private ChoiceBox<String> wTypChoiceBox;

    final ObservableList<String> wTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Blu-ray");

    @FXML
    void initialize() {
        wMediumChoiceBox.setValue("Filme");
        super.initialize();
        wTypChoiceBox.setValue("Normal");
        wTypChoiceBox.setItems(wTypChoiceBoxList);
    }

    public void promptTyp(String typ)
    {
        wTypChoiceBox.setValue(typ);
    }
}
