package Suche;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SucheController {
    @FXML
    private VBox sVBox;
    @FXML
    private Label sMediumLabel;
    @FXML
    private Label sTitelLabel;
    @FXML
    private ChoiceBox<String> sMediumChoiceBox;
    @FXML
    private TextField sTitelTextField;
    @FXML
    private ListView sVorschlaegeListView;
    @FXML
    private Button sSchließenButton;

    ObservableList<String> sMediumChoiceBoxList = FXCollections.observableArrayList("Alle", "Filme", "Serien", "Musik", "Hörspiele", "Games", "Bücher", "Zeitschriften");
    @FXML
    private void initialize()
    {
        sMediumChoiceBox.setValue("Alle");
        sMediumChoiceBox.setItems(sMediumChoiceBoxList);
    }

    @FXML
    private void sSchließenAction(ActionEvent actionEvent) {
        //stage = window der szene wo der Button sitzt
        Stage stage = (Stage) sSchließenButton.getScene().getWindow();
        stage.close();
    }
}
