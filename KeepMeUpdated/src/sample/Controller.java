package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;


public class Controller {

    @FXML
    private Button whinzufuegenbutton;
    @FXML
    private Button vhinzufuegenbutton;
    @FXML
    private Button vsuchenbutton;
    @FXML
    private ChoiceBox<String> dvsortieren;
    @FXML
    private TableView vtabelle;
    @FXML
    private TableColumn vtabellelinks;
    @FXML
    private TableColumn vtabellerechts;
    @FXML
    private ListView wfilmeliste;
    @FXML
    private ListView whoerspieleliste;
    @FXML
    private ListView wbuecherliste;
    @FXML
    private ListView wserienliste;
    @FXML
    private ListView wmusikliste;
    @FXML
    private ListView wgamesliste;

    ObservableList<String> dvsortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Franchise", "Standort", "Altersgruppe", "Typ");
    @SuppressWarnings("unchecked")
	@FXML
    private void initialize()
    {
        dvsortieren.setValue("A-Z");
        dvsortieren.setItems(dvsortierenList);   
        
        //wfilmeliste.getI
        
        vtabellelinks.setCellValueFactory(c -> new SimpleStringProperty(((CellDataFeatures) c).getValue().toString()));
        //vtabellerechts.setCellValueFactory(c -> new SimpleStringProperty(((CellDataFeatures) c).getValue().toString()));
        vtabelle.getItems().addAll(DatenbankTest.db.holeSpalte("Filme", "Titel"));
        
    }
    @FXML
    public void suchen(ActionEvent actionEvent) {
    }

    @FXML
    public void hinzufuegen(ActionEvent actionEvent) {
    }
}
