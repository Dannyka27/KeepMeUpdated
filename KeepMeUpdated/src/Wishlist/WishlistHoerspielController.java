package Wishlist;

import MainWindow.MainController;
import MainWindow.mediaPanes.Hoerspiel;
import MainWindow.mediaPanes.Medium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class WishlistHoerspielController extends WControllerAlter {
    @FXML
    private TextField wFolgeTextField;

    @FXML
    void initialize() {
        wMediumChoiceBox.setValue("Hörspiele");
        super.initialize();

        wFolgeTextField.setOnKeyPressed(keyEvent ->
        {
            if(keyEvent.getCode() == KeyCode.ENTER)
            {
                isInt(wFolgeTextField);
                //Platzhalter für Code, wenn Enter gedrueckt wird, schreibt er den Inhalt des TextFields in die Konsole
            }
        });
    }

    private boolean isInt(TextField hFolgeTextField)
    {
        try{
            int folge = Integer.parseInt(hFolgeTextField.getText());
            System.out.println("Folge Nummer: " + folge);
            return true;
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falsche Folgennummer");
            alert.setHeaderText("Eyy, gib gefälligst ne Zahl ein!");
            alert.setContentText("Es muss eine ganzzahlige Folgennummer eingegeben werden");
            alert.showAndWait();
            System.out.println("Error: " + hFolgeTextField.getText() + " ist keine adquate Folgennummer");
            return false;

        }
    }

    @Override
    public void promptMedium(Medium medium)
    {
    	Hoerspiel h = (Hoerspiel) medium;
    	
    	wFolgeTextField.setPromptText("" + h.getFolge());
		wAlterChoiceBox.setValue(h.getAltersgruppe());
    	
    	super.promptMedium(medium);
    }
    
    @Override
	public void wSpeichernOnAction(ActionEvent actionEvent)
	{
    	Hoerspiel hoerspiel = null;
		if (medium == null)
			hoerspiel = new Hoerspiel(-10, "", "", -3, "", "", "", "");
		else if (medium instanceof Hoerspiel)
			hoerspiel = (Hoerspiel) medium;
		else
			throw new RuntimeException("Das Medium in HinzufuegenHoerspielController ist kein Hörspiel!");

		hoerspiel.setAltersgruppe(wAlterChoiceBox.getValue());
		hoerspiel.setFolge(Integer.parseInt(wFolgeTextField.getText()));

		medium = hoerspiel;
		super.wSpeichernOnAction(actionEvent);
		
		MainController.instanz.audioSortieren("");
	}
}
