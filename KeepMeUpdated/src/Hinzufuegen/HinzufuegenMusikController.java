package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HinzufuegenMusikController extends Controller{
    @FXML
    private TextField hFranchiseTextField;
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

    public void promptGenreBox(String genre)
    {
        hmGenreChoiceBox.setValue(genre);
    }
    public void promptFranchise(String franchise)
    {
        if(franchise != null)
        {hFranchiseTextField.setPromptText(franchise);}
    }
}
