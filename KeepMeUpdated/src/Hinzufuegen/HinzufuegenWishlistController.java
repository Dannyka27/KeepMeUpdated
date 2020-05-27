package Hinzufuegen;

import Hinzufuegen.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HinzufuegenWishlistController{
    @FXML
    private Button hwSpeichernButton;
    @FXML
    private Button hwAbbruchButton;
    @FXML
    private Label hwMediumLabel;
    @FXML
    private ChoiceBox<String> hwMediumChoiceBox;
    @FXML
    private TextField hwTitelTextField;
    @FXML
    private TextField hwUntertitelTextField;
    @FXML
    private TextField hwLinkTextField;

    ObservableList<String> hwMediumChoiceBoxList = FXCollections.observableArrayList("Filme", "Serien", "Musik", "Hörspiele", "Games", "Bücher");
    @FXML
    private void initialize() {
        hwMediumChoiceBox.setValue("Filme");
        hwMediumChoiceBox.setItems(hwMediumChoiceBoxList);
    }

    @FXML
    public void hwSpeichernOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void hwAbbruchOnAction(ActionEvent actionEvent) {
        //stage = window der szene wo der Button sitzt
        Stage stage = (Stage) hwAbbruchButton.getScene().getWindow();
        stage.close();
    }
}
