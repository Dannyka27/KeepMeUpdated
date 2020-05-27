package Wishlist;

import Hinzufuegen.HinzufuegenMusikController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WishlistMusikController extends WController {
    @FXML
    private TextField wLinkTextField;
    @FXML
    private Label wmGenreLabel;
    @FXML
    private ChoiceBox<String> wmGenreChoiceBox;

    final ObservableList<String> wmGenreChoiceBoxList = FXCollections.observableArrayList("Cello", "Electrical", "Hits", "Tanz", "Pop/Rock", "Musical", "Metal");

    @FXML
    void initialize() {
        wMediumChoiceBox.setValue("Musik");
        super.initialize();
        wmGenreChoiceBox.setValue("Metal");
        wmGenreChoiceBox.setItems(wmGenreChoiceBoxList);
    }
}
