package Hinzufuegen;

import MainWindow.mediaPanes.Buch;
import MainWindow.mediaPanes.Film;
import MainWindow.mediaPanes.Medium;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;


public class HinzufuegenFilmController extends ControllerFranchise{
    @FXML
    private Label hTypLabel;
    @FXML
    private ChoiceBox<String> hTypChoiceBox;

    final ObservableList<String> hTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Blu-ray");

    @FXML
    void initialize() {
        hMediumChoiceBox.setValue("Filme");
        super.initialize();
        hTypChoiceBox.setValue("Normal");
        hTypChoiceBox.setItems(hTypChoiceBoxList);
    }
    
    @Override
    public void promptMedium(Medium medium)
    {
    	Film f = (Film) medium;
    	
    	hTypChoiceBox.setValue(f.getMedium());
    	hFranchiseTextField.setText(f.getFranchise());
		hAlterChoiceBox.setValue(f.getAltersgruppe());
    	
    	super.promptMedium(medium);
    }
    
    @Override
    public void hSpeichernOnAction(ActionEvent actionEvent)
    {
    	tabellenName = "Filme";
    	
		Film film = null;
    	if(medium == null)
    		film = new Film(-10, "", "", "", "", "", "", "", "");
    	else if(medium instanceof Buch)
    		film = (Film) medium;
    	else
    		throw new RuntimeException("Das Medium in HinzufuegenFilmController ist kein Film!");
    	
    	film.setMedium(hTypChoiceBox.getValue());
    	film.setFranchise(hFranchiseTextField.getText());
    	film.setAltersgruppe(hAlterChoiceBox.getValue());
    	
    	medium = film;
       	super.hSpeichernOnAction(actionEvent);
    }
}
