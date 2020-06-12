package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Film;
import MainWindow.mediaPanes.Medium;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class WishlistFilmController extends WControllerFranchise{
    @FXML
    private Label wTypLabel;
    @FXML
    private ChoiceBox<String> wTypChoiceBox;

    final ObservableList<String> wTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Blu-ray");

    @FXML
    void initialize() {
        wMediumChoiceBox.setValue("Filme");
        super.initialize();
        wTypChoiceBox.setValue("Normal");
        wTypChoiceBox.setItems(wTypChoiceBoxList);
    }

    @Override
    public void promptMedium(Medium medium)
    {
    	Film f = (Film) medium;
    	
    	wTypChoiceBox.setValue(f.getMedium());
    	wFranchiseTextField.setText(f.getFranchise());
		wAlterChoiceBox.setValue(f.getAltersgruppe());
    	
    	super.promptMedium(medium);
    }
    
    @Override
    public void wSpeichernOnAction(ActionEvent actionEvent)
    {
		Film film = null;
    	if(medium == null)
    		film = new Film(-10, "", "", "", "", "", "", "", "");
    	else if(medium instanceof Film)
    		film = (Film) medium;
    	else
    		throw new RuntimeException("Das Medium in HinzufuegenFilmController ist kein Film!");
    	
    	film.setMedium(wTypChoiceBox.getValue());
    	film.setFranchise(wFranchiseTextField.getText());
    	film.setAltersgruppe(wAlterChoiceBox.getValue());
    	
    	medium = film;
       	super.wSpeichernOnAction(actionEvent);
       	
       	MainController.instanz.videoSortieren("");
    }
}
