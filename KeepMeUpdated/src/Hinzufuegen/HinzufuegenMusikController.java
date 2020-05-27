package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class HinzufuegenMusikController extends Controller{
    @FXML
    private Label hmGenreLabel;
    @FXML
    private ChoiceBox<String> hmGenreChoiceBox;

    final ObservableList<String> hmGenreChoiceBoxList = FXCollections.observableArrayList("Cello", "Electrical", "Hits", "Tanz", "Pop/Rock", "Musical", "Metal");

    @FXML
    void initialize() {
        hMediumChoiceBox.setValue("Musik");
        super.initialize();
        hmGenreChoiceBox.setValue("Metal");
        hmGenreChoiceBox.setItems(hmGenreChoiceBoxList);
    }
}
