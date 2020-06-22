package Suche;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import MainWindow.Main;
import MainWindow.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller für die Suche.fxml
 * @author Lennart
 */

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
    private ListView<String> sVorschlaegeListView;
    @FXML
    private Button sSchliessenButton;

    private AutoCompletionBinding<String> autoCompletionBinding;

    ObservableList<String> sMediumChoiceBoxList = FXCollections.observableArrayList("Filme", "Serien", "Musik", "Hörspiele", "Games", "Bücher", "Zeitschriften");

    /**
     * Das Füllen der sMediumChoiceBox braucht eine eigene Methode, da sie je nachdem, von wo aus die Suche betreten wird, mit einem
     * anderen Value initialisiert werden soll. (z.B.: öffnet man sie aus der Videothek, startet die Suche mit "Filme", betritt man aus
     * der Bibliothek, ist die Voreinstellung "Bücher").
     * @param typ Wird aus {@link MainWindow.MainController#suchenOnAction(ActionEvent)} übergeben, abhängig davon, welcher Tab bei Aufruf aktiv war.
     */
    @FXML
    public void boxenFuellen(String typ)
    {
        sMediumChoiceBox.setValue(typ);
        sMediumChoiceBox.setItems(sMediumChoiceBoxList);
    }

    @FXML
    private void initialize()
    {
        sMediumChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> oV, String altesmedium, String medium) {
                switch (medium)
                {
                    case "Filme": suchvorschlaege("Filme", "Filme");
                        break;
                    case "Serien": suchvorschlaege("Filme", "Serien");
                        break;
                    case "Hörspiele": suchvorschlaege("Hörspiele", "Hörspiele");
                        break;
                    case "Musik": suchvorschlaege("Hörspiele", "Musik");
                        break;
                    case "Bücher": suchvorschlaege("Bücher", "Bücher");
                        break;
                    case "Zeitschriften": suchvorschlaege("Bücher", "Zeitschriften");
                        break;
                    case "Games": suchvorschlaege("Spiele", "Games");
                        break;
                }
            }
        });
    }

    /**
     * Da immer nur ein Binding zur selben Zeit korrekt gehändelt werden kann, wird zuert geprüft, ob bereits eins existiert.
     * Wenn ja, wird es vorworfen. Danach wird ein neues Binding erzeugt, mit der Auswahl aus der ChoiceBox, und dem TextField in
     * das die Eingaben getätigt werden. Ein Binding benötigt ein TextField und ein String-Array; das String-Array wird mit der
     * Methode 'holeSpalte' der Datenbank erzeugt.
     * Der EventHandler greift, wenn ein Suchvorschlag angeklickt wurde. Über den sSchliessenButton wird die akutelle Stage
     * angesprochen und geschossen. Danach wird {@link MainWindow.MainController#zeigeSuchergebnis(String, String)} aufgerufen und ihr
     * der vollständige Titel des angeklickten Elements übergeben und zusätzlich die Tabelle der Datenbank, zu der das Element gehört.
     * @param medium Die Tabelle, die abgefragt werden muss.
     * @param typ Die Auswahl der ChoiceBox; das Medium, das durchsucht werden soll.
     */
    public void suchvorschlaege(String medium,String typ)
    {
        if(autoCompletionBinding != null)
        {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(sTitelTextField, Main.db.holeSpalte(medium, typ));
        autoCompletionBinding.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<String>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<String> stringAutoCompletionEvent) {
                System.out.println(sTitelTextField.getText()); //Auskommentierbar, diente der Kontrolle
                Stage stage = (Stage) sSchliessenButton.getScene().getWindow();
                stage.close();
                MainController.instanz.zeigeSuchergebnis(sTitelTextField.getText(), medium);
            }
        });
    }

    @FXML
    private void sSchliessenAction(ActionEvent actionEvent) {
        //stage = window der szene wo der Button sitzt
        Stage stage = (Stage) sSchliessenButton.getScene().getWindow();
        stage.close();
    }
}
