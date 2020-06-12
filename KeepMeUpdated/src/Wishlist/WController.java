package Wishlist;

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

public class WController
{
	@FXML
	private AnchorPane wAnchorPane;
	@FXML
	private Label wStandortLabel;
	@FXML
	private Label wMediumLabel;
	@FXML
	public ChoiceBox<String> wMediumChoiceBox;
	@FXML
	private ChoiceBox<String> wStandortChoiceBox;
	@FXML
	private TextField wTitelTextField;
	@FXML
	private TextField wLinkTextField;
	@FXML
	private TextField wUntertitelTextField;
	@FXML
	private TextField wZusatzinfoTextField;
	@FXML
	private Button wAbbruchButton;
	@FXML
	private Button wSpeichernButton;

	final ObservableList<String> wMediumChoiceBoxList = FXCollections.observableArrayList("Filme", "Serien", "Musik",
			"Hörspiele", "Games", "Bücher", "Zeitschriften");
	final ObservableList<String> wStandortChoiceBoxList = FXCollections.observableArrayList("Wohnzimmer", "Hanna",
			"Jan");

	protected Medium medium;
	
	@FXML
	private void boxenFuellen()
	{
		wMediumChoiceBox.setItems(wMediumChoiceBoxList);
		wStandortChoiceBox.setValue("Wohnzimmer");
		wStandortChoiceBox.setItems(wStandortChoiceBoxList);
	}

	@FXML
	void initialize()
	{
		boxenFuellen();
		wMediumChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String altesMedium, String medium)
			{
				if (medium.equals("Filme"))
				{
					try
					{
						fensteroeffnen(530, "/Wishlist/WishlistFilm.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Serien"))
				{
					try
					{
						fensteroeffnen(570, "/Wishlist/WishlistSerie.fxml");

					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Musik"))
				{
					try
					{
						fensteroeffnen(490, "/Wishlist/WishlistMusik.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Hörspiele"))
				{
					try
					{
						fensteroeffnen(490, "/Wishlist/WishlistHoerspiel.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Games"))
				{
					try
					{
						fensteroeffnen(530, "/Wishlist/WishlistGame.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Bücher"))
				{
					try
					{
						fensteroeffnen(575, "/Wishlist/WishlistBuch.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} else if (medium.equals("Zeitschriften"))
				{
					try
					{
						fensteroeffnen(535, "/Wishlist/WishlistZeitschrift.fxml");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		wTitelTextField.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
			{
				// Platzhalter für Code, wenn Enter gedrueckt wird, schreibt er den Inhalt des
				// TextFields in die Konsole
				System.out.println(wTitelTextField.getText());
			}
		});
		wUntertitelTextField.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
			{
				// Platzhalter für Code, wenn Enter gedrueckt wird, schreibt er den Inhalt des
				// TextFields in die Konsole
				System.out.println(wUntertitelTextField.getText());
			}
		});
		wZusatzinfoTextField.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
			{
				// Platzhalter für Code, wenn Enter gedrueckt wird, schreibt er den Inhalt des
				// TextFields in die Konsole
				System.out.println(wZusatzinfoTextField.getText());
			}
		});
	}

	public void promptMedium(Medium medium)
	{
		this.medium = medium;
		
		wTitelTextField.setPromptText(medium.getTitel());
		wUntertitelTextField.setPromptText(medium.getUntertitel());
		wZusatzinfoTextField.setPromptText(medium.getZusatzinformationen());
		wStandortChoiceBox.setValue(medium.getStandort());
		wLinkTextField.setText(medium.getLink());
		wMediumChoiceBox.setValue(medium.getClass().getSimpleName());
		wMediumChoiceBox.setDisable(true);
	}
	
	private void fensteroeffnen(int height, String pfad) throws Exception
	{
		Stage app_stage = (Stage) wAnchorPane.getScene().getWindow();
		app_stage.setHeight(height);
		AnchorPane pane = FXMLLoader.load(getClass().getResource(pfad));
		app_stage.setScene(new Scene(pane));
	}

	public void wSpeichernOnAction(ActionEvent actionEvent)
	{ // TODO abschließen und Speichern des Eingabevorgangs
	}

	public void wAbbruchOnAction(ActionEvent actionEvent)
	{
		// stage = fenster der Szene wo der Button sitzt
		Stage akutelleStage = (Stage) wAbbruchButton.getScene().getWindow();
		akutelleStage.close();
	}
}
