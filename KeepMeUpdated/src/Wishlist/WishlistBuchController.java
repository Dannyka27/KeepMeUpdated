package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Buch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller für WishlistBuch.fxml
 * @author Hanna
 *
 * wSpeichernOnAction
 * @author Anika
 */

public class WishlistBuchController extends WControllerFranchise{
    @FXML
    private Label wbGenreLabel;
    @FXML
    private ChoiceBox<String> wbGenreChoiceBox;
    @FXML
    private TextField wbAutorTextField;

    final ObservableList<String> wbGenreList = FXCollections.observableArrayList("Tatsachenroman", "Fantasy", "Urban Fantasy", "Romantikthriller", "Mystery Thriller", "Cyberpunk", "Abenteuerroman", "Exit-Krimi", "Krimi", "dystopisches Science-Fiction", "historischer Roman", "Thriller", "Jugendthriller", "Comedy", "Sachbuch", "Liebesroman", "Horror", "Animal Fantasy");

    @FXML
    void initialize()
    {
        wMediumChoiceBox.setValue("Bücher");
        super.initialize();
        wbGenreChoiceBox.setValue("Krimi");
        wbGenreChoiceBox.setItems(wbGenreList);
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptGenreBox(String genre)
    {
        wbGenreChoiceBox.setValue(genre);
    }
    public void promptAutor(String autor)
    {
        wbAutorTextField.setPromptText(autor);
    }

    /*----------------------------------------------------*/
    @Override
    public void wSpeichernOnAction(ActionEvent actionEvent)
    {
        Buch buch = null;
        if (medium == null)
            buch = new Buch(-10, "", "", "", "", "", "", "", "", "");
        else if (medium instanceof Buch)
            buch = (Buch) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenBuchController ist kein Buch!");

        buch.setGenre(wbGenreChoiceBox.getValue());
        buch.setAutor(wbAutorTextField.getText());
        buch.setFranchise(wFranchiseTextField.getText());
        buch.setAltersgruppe(wAlterChoiceBox.getValue());

        medium = buch;
        super.wSpeichernOnAction(actionEvent);

        MainController.instanz.wbuchlist.clear();
        MainController.instanz.wishlistFuellen("Bücher", "Bücher");
    }
}