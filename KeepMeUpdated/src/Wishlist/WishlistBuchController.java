package Wishlist;

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

public class WishlistBuchController extends WControllerFranchise{
    @FXML
    private Label wbGenreLabel;
    @FXML
    private ChoiceBox<String> wbGenreChoiceBox;
    @FXML
    private TextField wbAutorTextField;

    final ObservableList<String> wbGenreList = FXCollections.observableArrayList("Tatsachenroman", "Fantasy", "Urban Fantasy", "Romantikthriller", "Mystery Thriller", "Cyberpunk", "Abenteuerroman", "Exit-Krimi", "Krimi", "dystopisches Science-Fiction", "historischer Roman", "Thriller", "Jugendthriller", "Comedy", "Sachbuch", "Liebesroman", "Horror", "Animal Fantasy");

    @FXML
    void initialize()
    {
        wMediumChoiceBox.setValue("Bücher");
        super.initialize();
        wbGenreChoiceBox.setValue("Krimi");
        wbGenreChoiceBox.setItems(wbGenreList);
    }
    
    @Override
	public void promptMedium(Medium medium)
	{
		Buch b = (Buch) medium;

		wbGenreChoiceBox.setValue(b.getGenre());
		wbAutorTextField.setPromptText(b.getAutor());
		wFranchiseTextField.setText(b.getFranchise());
		wAlterChoiceBox.setValue(b.getAltersgruppe());

		super.promptMedium(medium);
	}
    
    @Override
	public void wSpeichernOnAction(ActionEvent actionEvent)
	{
		Buch buch = null;
		if (medium == null)
			buch = new Buch(-10, "", "", "", "", "", "", "", "", "");
		else if (medium instanceof Buch)
			buch = (Buch) medium;
		else
			throw new RuntimeException("Das Medium in HinzufuegenBuchController ist kein Buch!");

		buch.setGenre(wbGenreChoiceBox.getValue());
		buch.setAutor(wbAutorTextField.getText());
		buch.setFranchise(wFranchiseTextField.getText());
		buch.setAltersgruppe(wAlterChoiceBox.getValue());

		medium = buch;
		super.wSpeichernOnAction(actionEvent);

		MainController.instanz.biblioSortieren("");
	}
}
