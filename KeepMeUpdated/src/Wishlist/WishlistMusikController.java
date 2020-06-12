package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Medium;
import MainWindow.mediaPanes.Musik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WishlistMusikController extends WController {
    @FXML
    private TextField wFranchiseTextField;
    @FXML
    private Label wmGenreLabel;
    @FXML
    private ChoiceBox<String> wmGenreChoiceBox;

    final ObservableList<String> wmGenreChoiceBoxList = FXCollections.observableArrayList("Cello", "Electrical", "Hits", "Tanz", "Pop/Rock", "Musical", "Metal");

    @FXML
    void initialize() {
        wMediumChoiceBox.setValue("Musik");
        super.initialize();
        wmGenreChoiceBox.setValue("Metal");
        wmGenreChoiceBox.setItems(wmGenreChoiceBoxList);
    }

    @Override
	public void promptMedium(Medium medium)
	{
		Musik m = (Musik) medium;		
		
		if (m.getFranchise() != null)
		{
			wFranchiseTextField.setPromptText(m.getFranchise());
		}
		
		wmGenreChoiceBox.setValue(m.getGenre());
		
		super.promptMedium(medium);
	}

	@Override
	public void wSpeichernOnAction(ActionEvent actionEvent)
	{
		Musik musik = null;
		if (medium == null)
			musik = new Musik(-10, "", "", "", "", "", "", "");
		else if (medium instanceof Musik)
			musik = (Musik) medium;
		else
			throw new RuntimeException("Das Medium in HinzufuegenMusikController ist keine Musik!");

		musik.setFranchise(wFranchiseTextField.getText());
		musik.setGenre(wmGenreChoiceBox.getValue());

		medium = musik;
		super.wSpeichernOnAction(actionEvent);
		
		MainController.instanz.audioSortieren("");
	}
}
