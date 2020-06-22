package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Musik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller für WishlistMusik.fxml
 * @author Hanna
 *
 * wSpeichernOnAction
 * @author Anika
 */

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

    /*----------------------------------------------------
    PROMPT*/
    public void promptGenreBox(String genre)
    {
        wmGenreChoiceBox.setValue(genre);
    }
    public void promptFranchise(String franchise) {
        if(franchise != null)
        {wFranchiseTextField.setPromptText(franchise);}
    }

    /*----------------------------------------------------*/
    @Override
    public void wSpeichernOnAction(ActionEvent actionEvent)
    {
        Musik musik = null;
        if (medium == null)
            musik = new Musik(-10, "", "", "", "", "", "", "");
        else if (medium instanceof Musik)
            musik = (Musik) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenMusikController ist keine Musik!");

        musik.setFranchise(wFranchiseTextField.getText());
        musik.setGenre(wmGenreChoiceBox.getValue());

        medium = musik;
        super.wSpeichernOnAction(actionEvent);

        MainController.instanz.wmusiklist.clear();
        MainController.instanz.wishlistFuellen("Hörspiele", "Musik");
    }
}
