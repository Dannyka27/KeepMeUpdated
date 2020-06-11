package Hinzufuegen;

import MainWindow.Main;
import MainWindow.mediaPanes.Medium;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller
{
	@FXML
	private AnchorPane hAnchorPane;
	@FXML
	private Label hStandortLabel;
	@FXML
	private Label hMediumLabel;
	@FXML
	public ChoiceBox<String> hMediumChoiceBox;
	@FXML
	private ChoiceBox<String> hStandortChoiceBox;
	@FXML
	public TextField hTitelTextField;
	@FXML
	private TextField hUntertitelTextField;
	@FXML
	private TextField hZusatzinfoTextField;
	@FXML
	private Button hAbbruchButton;
	@FXML
	private Button hSpeichernButton;

	final ObservableList<String> hMediumChoiceBoxList = FXCollections.observableArrayList("Filme", "Serien", "Musik",
			"Hörspiele", "Games", "Bücher", "Zeitschriften");
	final ObservableList<String> hStandortChoiceBoxList = FXCollections.observableArrayList("Wohnzimmer", "Hanna",
			"Jan");
	
	protected Medium medium;
	protected String tabellenName = null;
	
	@FXML
	private void boxenFuellen()
	{
		hMediumChoiceBox.setItems(hMediumChoiceBoxList);
		hStandortChoiceBox.setValue("Wohnzimmer");
		hStandortChoiceBox.setItems(hStandortChoiceBoxList);
	}

	@FXML
	void initialize()
	{
		boxenFuellen();
		hMediumChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String altesMedium, String medium)
			{
				if (medium.equals("Filme"))
				{
					try
					{
						fensteroeffnen(485, "/Hinzufuegen/HinzufuegenFilm.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Serien"))
				{
					try
					{
						fensteroeffnen(525, "/Hinzufuegen/HinzufuegenSerie.fxml");

					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Musik"))
				{
					try
					{
						fensteroeffnen(445, "/Hinzufuegen/HinzufuegenMusik.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Hörspiele"))
				{
					try
					{
						fensteroeffnen(449, "/Hinzufuegen/HinzufuegenHoerspiel.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Games"))
				{
					try
					{
						fensteroeffnen(485, "/Hinzufuegen/HinzufuegenGame.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Bücher"))
				{
					try
					{
						fensteroeffnen(530, "/Hinzufuegen/HinzufuegenBuch.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Zeitschriften"))
				{
					try
					{
						fensteroeffnen(490, "/Hinzufuegen/HinzufuegenZeitschrift.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		hTitelTextField.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
			{
				// Platzhalter für Code, wenn Enter gedrueckt wird, schreibt er den Inhalt des
				// TextFields in die Konsole
				System.out.println(hTitelTextField.getText());
			}
		});
		hUntertitelTextField.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
			{
				// Platzhalter für Code, wenn Enter gedrueckt wird, schreibt er den Inhalt des
				// TextFields in die Konsole
				System.out.println(hUntertitelTextField.getText());
			}
		});
		hZusatzinfoTextField.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
			{
				// Platzhalter für Code, wenn Enter gedrueckt wird, schreibt er den Inhalt des
				// TextFields in die Konsole
				System.out.println(hZusatzinfoTextField.getText());
			}
		});
	}
	
	public void promptMedium(Medium medium)
	{
		hTitelTextField.setPromptText(medium.getTitel());
		hUntertitelTextField.setPromptText(medium.getUntertitel());
		hZusatzinfoTextField.setPromptText(medium.getZusatzinformationen());
		hStandortChoiceBox.setValue(medium.getStandort());
		
		hMediumChoiceBox.setValue(medium.getClass().getSimpleName());
		hMediumChoiceBox.setDisable(true);
	}

	private void fensteroeffnen(int height, String pfad) throws Exception
	{
		Stage app_stage = (Stage) hAnchorPane.getScene().getWindow();
		app_stage.setHeight(height);
		AnchorPane pane = FXMLLoader.load(getClass().getResource(pfad));
		app_stage.setScene(new Scene(pane));
	}

	public void hSpeichernOnAction(ActionEvent actionEvent)
	{ 
		// Medium Klasse aktualisieren und dann die Speichermethode aufrufen um das 
		// Medium in die Datenbank zu schreiben
			
		medium.setTitel(hTitelTextField.getText());
		medium.setUntertitel(hUntertitelTextField.getText());
		medium.setZusatzinformationen(hZusatzinfoTextField.getText());
		medium.setStandort(hStandortChoiceBox.getValue());
		medium.setLink(""); //TODO Wo isser?
		
		Main.db.mediumSpeichern(medium, tabellenName);
		System.out.println("ControllerMedium Gespeichert!");
		
		//Lade Accordion neu wenn Update Methode vorhanden
		medium.updateParentAccordion();
		
		Stage akutelleStage = (Stage) hAbbruchButton.getScene().getWindow();
		akutelleStage.close();
	}

	public void hAbbruchOnAction(ActionEvent actionEvent)
	{
		// stage = fenster der Szene wo der Button sitzt
		Stage akutelleStage = (Stage) hAbbruchButton.getScene().getWindow();
		akutelleStage.close();
	}
}
