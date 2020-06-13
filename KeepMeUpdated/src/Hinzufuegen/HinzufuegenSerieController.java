package Hinzufuegen;

import MainWindow.MainController;
import MainWindow.mediaPanes.Medium;
import MainWindow.mediaPanes.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;


public class HinzufuegenSerieController extends ControllerFranchise{
    @FXML
    private Label hTypLabel;
    @FXML
    private Label hSeasonLabel;
    @FXML
    private ChoiceBox<String> hSeasonChoiceBox;
    @FXML
    private ChoiceBox<String> hTypChoiceBox;

    final ObservableList<String> hTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Bluray");
    final ObservableList<String> hSeasonChoiceBoxList = FXCollections.observableArrayList("1", "2", "3","4","5","6","7","8","9","10");

    @FXML
    void initialize()
    {
        hMediumChoiceBox.setValue("Serien");
        super.initialize();
        hTypChoiceBox.setValue("Normal");
        hTypChoiceBox.setItems(hTypChoiceBoxList);
        hSeasonChoiceBox.setValue("1");
        hSeasonChoiceBox.setItems(hSeasonChoiceBoxList);
    }

    public void promptTypBox(String typ)
    {
        hTypChoiceBox.setValue(typ);
    }
    public void promptSeasonBox(String season)
    {
        hSeasonChoiceBox.setValue(season);
    }

    @Override
    public void hSpeichernOnAction(ActionEvent actionEvent)
    {
        Serie serie = null;
        if (medium == null)
            serie = new Serie(-10, "", "", "", "", "", "", "", "", "");
        else if (medium instanceof Serie)
            serie = (Serie) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenSerieController ist keine Serie!");

        serie.setAltersgruppe(hAlterChoiceBox.getValue());
        serie.setFranchise(hFranchiseTextField.getText());
        serie.setMedium(hMediumChoiceBox.getValue());
        serie.setSeason(hSeasonChoiceBox.getValue());

        medium = serie;
        super.hSpeichernOnAction(actionEvent);

        MainController.instanz.videoSortieren("");
    }
}
