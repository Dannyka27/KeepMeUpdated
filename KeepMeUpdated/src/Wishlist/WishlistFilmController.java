package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Controller für WishlistFilm.fxml
 * @author Hanna
 *
 * wSpeichernOnAction
 * @author Anika
 */

public class WishlistFilmController extends WControllerFranchise{
    @FXML
    private Label wTypLabel;
    @FXML
    protected ChoiceBox<String> wTypChoiceBox;

    final ObservableList<String> wTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Blu-ray");

    @FXML
    void initialize() {
        wMediumChoiceBox.setValue("Filme");
        super.initialize();
        wTypChoiceBox.setValue("Normal");
        wTypChoiceBox.setItems(wTypChoiceBoxList);
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptTypBox(String typ)
    {
        wTypChoiceBox.setValue(typ);
    }

    /*----------------------------------------------------*/
    @Override
    public void wSpeichernOnAction(ActionEvent actionEvent)
    {
        Film film = null;
        if(medium == null)
            film = new Film(-10, "", "", "", "", "", "", "", "");
        else if(medium instanceof Film)
            film = (Film) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenFilmController ist kein Film!");

        film.setMedium(wTypChoiceBox.getValue());
        film.setFranchise(wFranchiseTextField.getText());
        film.setAltersgruppe(wAlterChoiceBox.getValue());

        medium = film;
        super.wSpeichernOnAction(actionEvent);

        MainController.instanz.wfilmlist.clear();
        MainController.instanz.wishlistFuellen("Filme", "Filme");
    }
}
