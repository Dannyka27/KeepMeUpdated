package Hinzufuegen;

import MainWindow.MainController;
import MainWindow.mediaPanes.Buch;
import MainWindow.mediaPanes.Medium;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HinzufuegenBuchController extends ControllerFranchise
{
	@FXML
	private Label hbGenreLabel;
	@FXML
	private ChoiceBox<String> hbGenreChoiceBox;
	@FXML
	private TextField hbAutorTextField;

	final ObservableList<String> hbGenreList = FXCollections.observableArrayList("Tatsachenroman", "Fantasy",
			"Urban Fantasy", "Romantikthriller", "Mystery Thriller", "Cyberpunk", "Abenteuerroman", "Exit-Krimi",
			"Krimi", "dystopisches Science-Fiction", "historischer Roman", "Thriller", "Jugendthriller", "Comedy",
			"Sachbuch", "Liebesroman", "Horror", "Animal Fantasy");

	@FXML
	void initialize()
	{
		hMediumChoiceBox.setValue("Bücher");
		super.initialize();
		hbGenreChoiceBox.setValue("Krimi");
		hbGenreChoiceBox.setItems(hbGenreList);
	}

	@Override
	public void promptMedium(Medium medium)
	{
		Buch b = (Buch) medium;

		hbGenreChoiceBox.setValue(b.getGenre());
		hbAutorTextField.setPromptText(b.getAutor());
		hFranchiseTextField.setText(b.getFranchise());
		hAlterChoiceBox.setValue(b.getAltersgruppe());

		super.promptMedium(medium);
	}

	@Override
	public void hSpeichernOnAction(ActionEvent actionEvent)
	{
		Buch buch = null;
		if (medium == null)
			buch = new Buch(-10, "", "", "", "", "", "", "", "", "");
		else if (medium instanceof Buch)
			buch = (Buch) medium;
		else
			throw new RuntimeException("Das Medium in HinzufuegenBuchController ist kein Buch!");

		buch.setGenre(hbGenreChoiceBox.getValue());
		buch.setAutor(hbAutorTextField.getText());
		buch.setFranchise(hFranchiseTextField.getText());
		buch.setAltersgruppe(hAlterChoiceBox.getValue());

		medium = buch;
		super.hSpeichernOnAction(actionEvent);

		MainController.instanz.biblioSortieren("");
	}
}
