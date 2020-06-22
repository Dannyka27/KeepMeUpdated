package Hinzufuegen;

import MainWindow.Main;
import MainWindow.mediaPanes.Medium;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Oberklasse der .fxml Controller (Hinzufuegen)
 * @author Hanna
 *
 * hSpeichernOnAction & setMedium
 * @author Anika
 */

public class Controller {
    @FXML
    private AnchorPane hAnchorPane;
    @FXML
    private Label hStandortLabel;
    @FXML
    private Label hMediumLabel;
    @FXML
    public ChoiceBox<String> hMediumChoiceBox;
    @FXML
    private ChoiceBox<String> hStandortChoiceBox;
    @FXML
    private TextField hTitelTextField;
    @FXML
    protected TextField hUntertitelTextField;
    @FXML
    private TextField hZusatzinfoTextField;
    @FXML
    private Button hAbbruchButton;
    @FXML
    private Button hSpeichernButton;

    protected Medium medium;

    final ObservableList<String> hMediumChoiceBoxList = FXCollections.observableArrayList("Filme", "Serien", "Musik", "Hörspiele", "Games", "Bücher", "Zeitschriften");
    final ObservableList<String> hStandortChoiceBoxList = FXCollections.observableArrayList("Wohnzimmer", "Hanna", "Jan");

    @FXML
    void initialize() {
        boxenFuellen();
        hMediumChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String altesMedium, String medium) {
                switch (medium) {
                    case "Filme":
                        try {
                            fensterOeffnen(485, "/Hinzufuegen/HinzufuegenFilm.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Serien":
                        try {
                            fensterOeffnen(525, "/Hinzufuegen/HinzufuegenSerie.fxml");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Musik":
                        try {
                            fensterOeffnen(445, "/Hinzufuegen/HinzufuegenMusik.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Hörspiele":
                        try {
                            fensterOeffnen(449, "/Hinzufuegen/HinzufuegenHoerspiel.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Games":
                        try {
                            fensterOeffnen(485, "/Hinzufuegen/HinzufuegenGame.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Bücher":
                        try {
                            fensterOeffnen(530, "/Hinzufuegen/HinzufuegenBuch.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Zeitschriften":
                        try {
                            fensterOeffnen(490, "/Hinzufuegen/HinzufuegenZeitschrift.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });
    }

    /**
     * Setzen der Items und Default Werte
     */
    @FXML
    private void boxenFuellen(){
        hMediumChoiceBox.setItems(hMediumChoiceBoxList);
        hStandortChoiceBox.setValue("Wohnzimmer");
        hStandortChoiceBox.setItems(hStandortChoiceBoxList);
    }

    /*----------------------------------------------------
    SETZEN DER PROMPT WERTE*/
    public void promptStandortBox(String standort) {
        hStandortChoiceBox.setValue(standort);
    }
    public void promptTitel(String titel) {
        hTitelTextField.setPromptText(titel);
    }
    /**
     * Bei Untertitel und Zusatzinfo wird als PromptText das gesetzt, was in der Datenbank
     * hinterlegt ist. Ist nichts hinterlegt, ist der PromptText der Default Text aus der FXML.
     * Diese Unterscheidung gibt es bei Titel nicht, da dieser immer vorhanden ist.
     */
    public void promptUntertitel(String untertitel) {
        if(untertitel != null)
        {hUntertitelTextField.setPromptText(untertitel);}
    }
    public void promptZusatzinfo(String zusatzinfo) {
        if(zusatzinfo != null)
        {hZusatzinfoTextField.setPromptText(zusatzinfo);}
    }

    /**
     * Um je nach Auswahl in der ChoiceBox die passende FXML in passender Höhe zu laden.
     * Es wird die aktuelle Stage ermittelt, die Höhe angepasst und die FXML ersetzt, um immer
     * nur eine gleichzeitig zu haben.
     * @param height Die Höhe, die die FXML benötigt, um nicht verzerrt zu sein.
     * @param pfad Pfad der FXML.
     */
    private void fensterOeffnen(int height, String pfad) throws Exception {
        Stage app_stage = (Stage) hAnchorPane.getScene().getWindow();
        app_stage.setHeight(height);
        AnchorPane pane = FXMLLoader.load(getClass().getResource(pfad));
        app_stage.setScene(new Scene(pane));
    }

    /**
     * Die aktuelle Stage wird durch den hAbbruchButton ermittelt und geschlossen
     */
    public void hAbbruchOnAction(ActionEvent actionEvent) {
        Stage akutelleStage = (Stage) hAbbruchButton.getScene().getWindow();
        akutelleStage.close();
    }

    /*----------------------------------------------------*/
    public void hSpeichernOnAction(ActionEvent actionEvent) {
        // Medium Klasse aktualisieren und dann die Speichermethode aufrufen um das
        // Medium in die Datenbank zu schreiben

        medium.setTitel(hTitelTextField.getText());
        medium.setUntertitel(hUntertitelTextField.getText());
        medium.setZusatzinformationen(hZusatzinfoTextField.getText());
        medium.setStandort(hStandortChoiceBox.getValue());
        medium.setLink("");

        Main.db.mediumSpeichern(medium, medium.getTabellenTitel());
        System.out.println("ControllerMedium Gespeichert!");

        Stage akutelleStage = (Stage) hAbbruchButton.getScene().getWindow();
        akutelleStage.close();
    }
    public void setMedium(Medium medium)
    {
        this.medium = medium;
    }
}