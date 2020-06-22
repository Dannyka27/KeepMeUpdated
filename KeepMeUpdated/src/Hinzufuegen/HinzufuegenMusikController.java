package Hinzufuegen;

import MainWindow.MainController;
import MainWindow.mediaPanes.Musik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Newslist.Newslist;

/**
 * Controller für HinzufuegenMusik.fxml
 * @author Hanna
 *
 * hSpeichernOnAction
 * @author Anika
 */

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

    /*----------------------------------------------------
    PROMPT*/
    public void promptGenreBox(String genre)
    {
        hmGenreChoiceBox.setValue(genre);
    }
    public void promptFranchise(String franchise) {
        if(franchise != null)
        {hFranchiseTextField.setPromptText(franchise);}
    }

    /*----------------------------------------------------*/
    @Override
    public void hSpeichernOnAction(ActionEvent actionEvent)
    {
        Musik musik = null;
        if (medium == null)
            musik = new Musik(-10, "", "", "", "", "", "", "");
        else if (medium instanceof Musik)
            musik = (Musik) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenMusikController ist keine Musik!");

        musik.setFranchise(hFranchiseTextField.getText());
        musik.setGenre(hmGenreChoiceBox.getValue());

        medium = musik;
        super.hSpeichernOnAction(actionEvent);

        MainController.instanz.audioSortieren("");
        Newslist.newslist("Musik");
    }
}
