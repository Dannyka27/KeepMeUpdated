package Hinzufuegen;

import MainWindow.MainController;
import MainWindow.mediaPanes.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import Newslist.Newslist;

/**
 * Controller für HinzufuegenFilm.fxml
 * @author Hanna
 *
 * hSpeichernOnAction
 * @author Anika
 */

public class HinzufuegenFilmController extends ControllerFranchise{
    @FXML
    private Label hTypLabel;
    @FXML
    private ChoiceBox<String> hTypChoiceBox;

    final ObservableList<String> hTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Bluray");

    @FXML
    void initialize() {
        hMediumChoiceBox.setValue("Filme");
        super.initialize();
        hTypChoiceBox.setValue("Normal");
        hTypChoiceBox.setItems(hTypChoiceBoxList);
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptTypBox(String typ)
    {
        hTypChoiceBox.setValue(typ);
    }

    /*----------------------------------------------------*/
    @Override
    public void hSpeichernOnAction(ActionEvent actionEvent)
    {
        Film film = null;
        if(medium == null)
            film = new Film(-10, "", "", "", "", "", "", "", "");
        else if(medium instanceof Film)
            film = (Film) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenFilmController ist kein Film!");

        film.setMedium(hTypChoiceBox.getValue());
        film.setFranchise(hFranchiseTextField.getText());
        film.setAltersgruppe(hAlterChoiceBox.getValue());

        medium = film;
        super.hSpeichernOnAction(actionEvent);

        MainController.instanz.videoSortieren("");
        Newslist.newslist("Filme");
    }
}