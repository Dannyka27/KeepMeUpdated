package Wishlist;

import Hinzufuegen.HinzufuegenBuchController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WishlistBuchController extends WControllerFranchise{
    @FXML
    private TextField wLinkTextField;
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








}
