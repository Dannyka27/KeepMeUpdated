package Suche;

import java.sql.ResultSet;

import MainWindow.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.skin.TextFieldSkin;
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
    
    public void Suchvorschläge(String medium)
    {
        ResultSet asdf = Main.db.dbAbfrage("SELECT * FROM " + medium + " ORDER BY Titel ASC");

        if(medium.equals("Filme"))
        {
            TextField.bindAutoCompletion(sTitelTextField, null);
        }
        else if(medium.equals("Bücher"))
        {
            TextFields.bindAutoCompletion(sTitelTextField, holeSpalte(Bücher, asdf.getString("Titel")));
        }
        else if(medium.equals("Hörspiele"))
        {
            TextFields.bindAutoCompletion(sTitelTextField, holeSpalte(Hörspiele, asdf.getString("Titel")));
        }
        else if(medium.equals("Musik"))
        {
            TextFields.bindAutoCompletion(sTitelTextField, holeSpalte(Musik, asdf.getString("Titel")));
        }
        else if(medium.equals("Serien"))
        {
            TextFields.bindAutoCompletion(sTitelTextField, holeSpalte(Serien, asdf.getString("Titel")));
        }
        else if(medium.equals("Spiele"))
        {
            TextFields.bindAutoCompletion(sTitelTextField, holeSpalte(Spiele, asdf.getString("Titel")));
        }
        else if(medium.equals("Zeitschriften"))
        {
            TextFields.bindAutoCompletion(sTitelTextField, holeSpalte(Zeitschriften, asdf.getString("Titel")));
        }
    }
}
