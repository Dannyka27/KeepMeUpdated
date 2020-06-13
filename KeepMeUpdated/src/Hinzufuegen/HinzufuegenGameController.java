package Hinzufuegen;

import MainWindow.MainController;
import MainWindow.mediaPanes.Medium;
import MainWindow.mediaPanes.Spiel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class HinzufuegenGameController extends ControllerFranchise{

    @FXML
    private Label hgPlattformLabel;
    @FXML
    private ChoiceBox<String> hgPlattformChoiceBox;

    final ObservableList<String> hgPlattformChoiceBoxList = FXCollections.observableArrayList("PS4", "PC");

    @FXML
    void initialize() {
        hMediumChoiceBox.setValue("Games");
        super.initialize();
        hgPlattformChoiceBox.setValue("PS4");
        hgPlattformChoiceBox.setItems(hgPlattformChoiceBoxList);
    }

    public void promptPlattformBox(String plattform)
    {
        hgPlattformChoiceBox.setValue(plattform);
    }

    @Override
    public void hSpeichernOnAction(ActionEvent actionEvent)
    {
        Spiel spiel = null;
        if (medium == null)
            spiel = new Spiel(-10, "", "", "", "", "", "", "", "");
        else if (medium instanceof Spiel)
            spiel = (Spiel) medium;
        else
            throw new RuntimeException("Das Medium in HinzufuegenGameController ist kein Spiel!");

        spiel.setAltersgruppe(hAlterChoiceBox.getValue());
        spiel.setFranchise(hFranchiseTextField.getText());
        spiel.setPlattform(hgPlattformChoiceBox.getValue());

        medium = spiel;
        super.hSpeichernOnAction(actionEvent);

        MainController.instanz.gamesSortieren("");
    }
}
