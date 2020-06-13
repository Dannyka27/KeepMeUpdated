package Suche;

import MainWindow.Main;
import MainWindow.mediaPanes.Film;
import MainWindow.mediaPanes.Medium;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Queue;
import java.util.ResourceBundle;

public class SucheController implements Initializable {
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
    private ListView<String> sVorschlaegeListView;
    @FXML
    private Button sSchließenButton;


    ObservableList<String> sMediumChoiceBoxList = FXCollections.observableArrayList("Alle", "Filme", "Serien", "Musik", "Hörspiele", "Games", "Bücher", "Zeitschriften");
    HashMap<Integer, Medium> newslist = new HashMap<Integer, Medium>();

    @FXML
    private void initialize()
    {
        sMediumChoiceBox.setValue("Alle");
        sMediumChoiceBox.setItems(sMediumChoiceBoxList);
        //sVorschlaegeListView.setItems(sMediumChoiceBoxList);
    }

    @FXML
    private void sSchließenAction(ActionEvent actionEvent) {
        //stage = window der szene wo der Button sitzt
        Stage stage = (Stage) sSchließenButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
