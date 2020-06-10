package Wishlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WishlistMusikController extends WController {
    @FXML
    private TextField wFranchiseTextField;
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

    public void promptGenreBox(String genre)
    {
        wmGenreChoiceBox.setValue(genre);
    }
    public void promptFranchise(String franchise)
    {
        if(franchise != null)
        {wFranchiseTextField.setPromptText(franchise);}
    }
}
