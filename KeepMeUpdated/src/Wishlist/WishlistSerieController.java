package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Controller für WishlistSerie.fxml
 * @author Hanna
 *
 * wSpeichernOnAction
 * @author Anika
 */

public class WishlistSerieController extends WControllerFranchise {
    @FXML
    private Label wTypLabel;
    @FXML
    private Label wSeasonLabel;
    @FXML
    private ChoiceBox<String> wSeasonChoiceBox;
    @FXML
    private ChoiceBox<String> wTypChoiceBox;

    final ObservableList<String> wTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Blu-ray");
    final ObservableList<String> wSeasonChoiceBoxList = FXCollections.observableArrayList("1", "2", "3","4","5","6","7","8","9","10");

    @FXML
    void initialize()
    {
        wMediumChoiceBox.setValue("Serien");
        super.initialize();
        wTypChoiceBox.setValue("Normal");
        wTypChoiceBox.setItems(wTypChoiceBoxList);
        wSeasonChoiceBox.setValue("1");
        wSeasonChoiceBox.setItems(wSeasonChoiceBoxList);
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptTypBox(String typ)
    {
        wTypChoiceBox.setValue(typ);
    }
    public void promptSeasonBox(String season)
    {
        wSeasonChoiceBox.setValue(season);
    }

    /*----------------------------------------------------*/
    @Override
    public void wSpeichernOnAction(ActionEvent actionEvent)
    {
        Serie serie = null;
        if (medium == null)
            serie = new Serie(-10, "", "", "", "", "", "", "", "", "");
        else if (medium instanceof Serie)
            serie = (Serie) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenSerieController ist keine Serie!");

        serie.setAltersgruppe(wAlterChoiceBox.getValue());
        serie.setFranchise(wFranchiseTextField.getText());
        serie.setMedium(wMediumChoiceBox.getValue());
        serie.setSeason(wSeasonChoiceBox.getValue());

        medium = serie;
        super.wSpeichernOnAction(actionEvent);

        MainController.instanz.wserielist.clear();
        MainController.instanz.wishlistFuellen("Filme", "Serien");
    }
}
