package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class HinzufuegenBuchController extends ControllerFranchise{
    @FXML
    private Label hbGenreLabel;
    @FXML
    private ChoiceBox<String> hbGenreChoiceBox;
    @FXML
    private TextField hbAutorTextField;


    final ObservableList<String> hbGenreList = FXCollections.observableArrayList("Tatsachenroman", "Fantasy", "Urban Fantasy", "Romantikthriller", "Mystery Thriller", "Cyberpunk", "Abenteuerroman", "Exit-Krimi", "Krimi", "dystopisches Science-Fiction", "historischer Roman", "Thriller", "Jugendthriller", "Comedy", "Sachbuch", "Liebesroman", "Horror", "Animal Fantasy");


    @FXML
    void initialize()
    {
        hMediumChoiceBox.setValue("Bücher");
        super.initialize();
        hbGenreChoiceBox.setValue("Krimi");
        hbGenreChoiceBox.setItems(hbGenreList);
    }
}
