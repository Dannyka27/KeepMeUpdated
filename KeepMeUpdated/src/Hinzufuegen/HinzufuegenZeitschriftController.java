package Hinzufuegen;

import MainWindow.MainController;
import MainWindow.mediaPanes.Medium;
import MainWindow.mediaPanes.Zeitschrift;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HinzufuegenZeitschriftController extends Controller
{
	@FXML
	private TextField hAusgabeTextField;
	@FXML
	private TextField hzHerausgeberTextField;
	@FXML
	private Label hzGenreLabel;
	@FXML
	private ChoiceBox<String> hzGenreChoiceBox;

	final ObservableList<String> hzGenreList = FXCollections.observableArrayList("Informatik", "Fotografie",
			"Animation");

	@FXML
	void initialize()
	{
		hMediumChoiceBox.setValue("Zeitschriften");
		super.initialize();
		hzGenreChoiceBox.setValue("Informatik");
		hzGenreChoiceBox.setItems(hzGenreList);
	}
	
	@Override
	public void promptMedium(Medium medium)
	{
		Zeitschrift z = (Zeitschrift) medium;
		
		if (z.getAusgabe() != null)
			hzHerausgeberTextField.setPromptText(z.getAusgabe());
		
		if (z.getHerausgeber() != null)
			hzHerausgeberTextField.setPromptText(z.getHerausgeber());
		
		hzGenreChoiceBox.setValue(z.getGenre());
		
		super.promptMedium(medium);
	}

	@Override
	public void hSpeichernOnAction(ActionEvent actionEvent)
	{
		tabellenName = "Bücher";
		
		Zeitschrift zeitung = null;
		if (medium == null)
			zeitung = new Zeitschrift(-10, "", "", "", "", "", "", "", "");
		else if (medium instanceof Zeitschrift)
			zeitung = (Zeitschrift) medium;
		else
			throw new RuntimeException("Das Medium in HinzufuegenZeitschriftController ist keine Zeitschrift!");

		zeitung.setAusgabe(hAusgabeTextField.getText());
		zeitung.setHerausgeber(hzHerausgeberTextField.getText());
		zeitung.setGenre(hzGenreChoiceBox.getValue());

		medium = zeitung;
		super.hSpeichernOnAction(actionEvent);
		
		MainController.instanz.biblioSortieren("");
	}
}