package Wishlist;

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
 * Oberklasse der .fxml Controller (Wishlist)
 * @author Hanna
 *
 * wSpeichernOnAction & setMedium
 * @author Anika
 */

public class WController {
    @FXML
    private AnchorPane wAnchorPane;
    @FXML
    private Label wStandortLabel;
    @FXML
    private Label wMediumLabel;
    @FXML
    public ChoiceBox<String> wMediumChoiceBox;
    @FXML
    private ChoiceBox<String> wStandortChoiceBox;
    @FXML
    private TextField wTitelTextField;
    @FXML
    private TextField wLinkTextField;
    @FXML
    private TextField wUntertitelTextField;
    @FXML
    private TextField wZusatzinfoTextField;
    @FXML
    private Button wAbbruchButton;
    @FXML
    private Button wSpeichernButton;

    protected Medium medium;

    final ObservableList<String> wMediumChoiceBoxList = FXCollections.observableArrayList("Filme", "Serien", "Musik", "Hörspiele", "Games", "Bücher", "Zeitschriften");
    final ObservableList<String> wStandortChoiceBoxList = FXCollections.observableArrayList("Wohnzimmer", "Hanna", "Jan");

    @FXML
    void initialize() {
        boxenFuellen();
        wMediumChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String altesMedium, String medium) {
                switch (medium) {
                    case "Filme":
                        try {
                            fensterOeffnen(530, "/Wishlist/WishlistFilm.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Serien":
                        try {
                            fensterOeffnen(570, "/Wishlist/WishlistSerie.fxml");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Musik":
                        try {
                            fensterOeffnen(490, "/Wishlist/WishlistMusik.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Hörspiele":
                        try {
                            fensterOeffnen(490, "/Wishlist/WishlistHoerspiel.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Games":
                        try {
                            fensterOeffnen(530, "/Wishlist/WishlistGame.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Bücher":
                        try {
                            fensterOeffnen(575, "/Wishlist/WishlistBuch.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Zeitschriften":
                        try {
                            fensterOeffnen(535, "/Wishlist/WishlistZeitschrift.fxml");
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
    private void boxenFuellen() {
        wMediumChoiceBox.setItems(wMediumChoiceBoxList);
        wStandortChoiceBox.setValue("Wohnzimmer");
        wStandortChoiceBox.setItems(wStandortChoiceBoxList);
    }

    /*----------------------------------------------------
    SETZEN DER PROMPT WERTE*/

    public void promptStandortBox(String standort) {
        wStandortChoiceBox.setValue(standort);
    }
    public void promptTitel(String titel) {
        wTitelTextField.setPromptText(titel);
    }
    public void promptLink(String link) {
        wLinkTextField.setPromptText(link);
    }
    /**
     * Bei Untertitel und Zusatzinfo wird als PromptText das gesetzt, was in der Datenbank
     * hinterlegt ist. Ist nichts hinterlegt, ist der PromptText der Default Text aus der FXML.
     * Diese Unterscheidung gibt es bei Titel und Link nicht, da diese immer vorhanden sind.
     */
    public void promptUntertitel(String untertitel) {
        if(untertitel != null)
        {wUntertitelTextField.setPromptText(untertitel);}
    }
    public void promptZusatzinfo(String zusatzinfo) {
        if(zusatzinfo != null)
        {wZusatzinfoTextField.setPromptText(zusatzinfo);}
    }

    /**
     * Um je nach Auswahl in der ChoiceBox die passende FXML in passender Höhe zu laden.
     * Es wird die aktuelle Stage ermittelt, die Höhe angepasst und die FXML ersetzt, um immer
     * nur eine gleichzeitig zu haben.
     * @param height Die Höhe, die die FXML benötigt, um nicht verzerrt zu sein.
     * @param pfad Pfad der FXML.
     */
    private void fensterOeffnen(int height, String pfad) throws Exception {
        Stage app_stage = (Stage) wAnchorPane.getScene().getWindow();
        app_stage.setHeight(height);
        AnchorPane pane = FXMLLoader.load(getClass().getResource(pfad));
        app_stage.setScene(new Scene(pane));
    }

    /**
     * Die aktuelle Stage wird durch den wAbbruchButton ermittelt und geschlossen
     */
    public void wAbbruchOnAction(ActionEvent actionEvent) {
        Stage akutelleStage = (Stage) wAbbruchButton.getScene().getWindow();
        akutelleStage.close();
    }

    /*----------------------------------------------------*/
    public void wSpeichernOnAction(ActionEvent actionEvent) {
        // Medium Klasse aktualisieren und dann die Speichermethode aufrufen um das
        // Medium in die Datenbank zu schreiben

        medium.setTitel(wTitelTextField.getText());
        medium.setUntertitel(wUntertitelTextField.getText());
        medium.setZusatzinformationen(wZusatzinfoTextField.getText());
        medium.setStandort(wStandortChoiceBox.getValue());
        medium.setLink(wLinkTextField.getText());

        Main.db.mediumSpeichern(medium, medium.getTabellenTitel());
        System.out.println("ControllerMedium Gespeichert!");

        Stage akutelleStage = (Stage) wAbbruchButton.getScene().getWindow();
        akutelleStage.close();
    }
    public void setMedium(Medium medium)
    {
        this.medium = medium;
    }
}