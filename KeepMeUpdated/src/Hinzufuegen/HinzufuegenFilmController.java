package Hinzufuegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;


public class HinzufuegenFilmController extends ControllerFranchise implements EventHandler<ActionEvent>
{
    @FXML
    private Label hTypLabel;
    @FXML
    private ChoiceBox<String> hTypChoiceBox;
    @FXML
    private Button hSpeichernButton;
    
    final ObservableList<String> hTypChoiceBoxList = FXCollections.observableArrayList("Normal", "Blu-ray");

    @FXML
    void initialize() 
    {
        hMediumChoiceBox.setValue("Filme");
        super.initialize();
        hTypChoiceBox.setValue("Normal");
        hTypChoiceBox.setItems(hTypChoiceBoxList);
        
        hSpeichernButton.setOnAction(this);
    }

	@Override
	public void handle(ActionEvent event)
	{
		
	}
}
