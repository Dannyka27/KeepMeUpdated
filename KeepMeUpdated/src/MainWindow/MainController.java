package MainWindow;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainController
{
	@FXML
	private ChoiceBox<String> videoSortierenChoiceBox;
	@FXML
	private ChoiceBox<String> audioSortierenChoiceBox;
	@FXML
	private ChoiceBox<String> biblioSortierenChoiceBox;
	@FXML
	private ChoiceBox<String> gamesSortierenChoiceBox;
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private TabPane mainTabPane;
	@FXML
	private Tab videothekTab;
	@FXML
	private Tab audiothekTab;
	@FXML
	private Tab bibliothekTab;
	@FXML
	private Tab gamesTab;
	@FXML
	private Tab wishlistTab;
	@FXML
	private ScrollPane videothekScrollPane;
	@FXML
	private ScrollPane audiothekScrollPane;
	@FXML
	private ScrollPane bibliothekScrollPane;
	@FXML
	private ScrollPane gamesScrollPane;
	@FXML
	private ScrollPane wishlistScrollPane;
	@FXML
	private Accordion videothekAccordion;
	@FXML
	private Accordion audiothekAccordion;
	@FXML
	private Accordion bibliothekAccordion;
	@FXML
	private Accordion gamesAccordion;
	@FXML
	private TableView filmeTableView;
	@FXML
	private TableView hoerspieleTableView;
	@FXML
	private TableView serienTableView;
	@FXML
	private TableView musikTableView;
	@FXML
	private TableView buecherTableView;
	@FXML
	private TableView gamesTableView;
	@FXML
	private Button hinzufuegenButton;
	@FXML
	private Button suchenButton;

	ObservableList<String> videoSortierenList = FXCollections.observableArrayList("A-Z", "Z-A","Filme", "Serie", "Franchise", "Standort",
			"Altersgruppe", "Typ");
	ObservableList<String> audioSortierenList = FXCollections.observableArrayList("A-Z", "Z-A","Hörspiel", "Musik", "Folge", "Genre",
			"Standort", "Altersgruppe");
	ObservableList<String> biblioSortierenList = FXCollections.observableArrayList("A-Z", "Z-A","Bücher", "Zeitschriften", "Autor", "Genre",
			"Franchise", "Standort", "Altersgruppe", "Herausgeber");
	ObservableList<String> gamesSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Plattform",
			"Franchise", "Standort", "Altersgruppe");

	@FXML
	public void hinzufuegenOnAction(ActionEvent actionEvent) throws Exception
	{
		if (videothekTab.isSelected())
		{
			fensteroeffnen("/Hinzufuegen/HinzufuegenFilm.fxml", "Hinzufügen");
		} else if (audiothekTab.isSelected())
		{
			fensteroeffnen("/Hinzufuegen/HinzufuegenMusik.fxml", "Hinzufügen");
		} else if (bibliothekTab.isSelected())
		{
			fensteroeffnen("/Hinzufuegen/HinzufuegenBuch.fxml", "Hinzufügen");
		} else if (gamesTab.isSelected())
		{
			fensteroeffnen("/Hinzufuegen/HinzufuegenGame.fxml", "Hinzufügen");
		} else if (wishlistTab.isSelected())
		{
			fensteroeffnen("/Wishlist/WishlistFilm.fxml", "Wunsch offenbaren");
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize()
	{
		videoSortierenChoiceBox.setValue("A-Z");
		videoSortierenChoiceBox.setItems(videoSortierenList);
		audioSortierenChoiceBox.setValue("A-Z");
		audioSortierenChoiceBox.setItems(audioSortierenList);
		biblioSortierenChoiceBox.setValue("A-Z");
		biblioSortierenChoiceBox.setItems(biblioSortierenList);
		gamesSortierenChoiceBox.setValue("A-Z");
		gamesSortierenChoiceBox.setItems(gamesSortierenList);

		// CLUDGE
//		videothekAccordion.getPanes().clear();

		ResultSet rs = Main.db.dbAbfrage("SELECT * FROM " + "Filme" + " ORDER BY Titel ASC");

		try
		{
			while (rs.next())
			{
				TextArea l = new TextArea(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s", 
						rs.getString("Untertitel"),
						rs.getString("Medium"), 
						rs.getString("Altersgruppe"),
						rs.getString("Zusatzinformationen"),
						rs.getString("Standort"),
						rs.getString("Franchise"),
						rs.getString("Link")
						));
				l.setEditable(false);
				l.setWrapText(true);
				videothekAccordion.getPanes().add(new TitledPane(rs.getString("Titel"), l));
			}
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}

//		audiothekAccordion.getPanes().clear();

		rs = Main.db.dbAbfrage("SELECT * FROM " + "Musik" + " ORDER BY Titel ASC");

		try
		{
			while (rs.next())
			{
				TextArea l = new TextArea(String.format("%s%n%s%n%s%n%s%n%s", 
						rs.getString("Untertitel"), 
						rs.getString("Genre"),
						rs.getString("Standort"),
						rs.getString("Zusatzinformationen"),
						rs.getString("Link")
						));
				l.setEditable(false);
				audiothekAccordion.getPanes().add(new TitledPane(rs.getString("Titel"), l));
			}
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		
//		bibliothekAccordion.getPanes().clear();

		rs = Main.db.dbAbfrage("SELECT * FROM " + "Bücher" + " ORDER BY Titel ASC");

		try
		{
			while (rs.next())
			{
				TextArea l = new TextArea(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s", 
						rs.getString("Untertitel"), 
						rs.getString("Genre"),
						rs.getString("Standort"),
						rs.getString("Autor"),
						rs.getString("Zusatzinformationen"),
						rs.getString("Franchise"),
						rs.getString("Altersgruppe"),
						rs.getString("Link")
						));
				l.setEditable(false);
				bibliothekAccordion.getPanes().add(new TitledPane(rs.getString("Titel"), l));
			}
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		
//		gamesAccordion.getPanes().clear();

		rs = Main.db.dbAbfrage("SELECT * FROM " + "Spiele" + " ORDER BY Titel ASC");

		try
		{
			while (rs.next())
			{
				TextArea l = new TextArea(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s", 
						rs.getString("Untertitel"), 
						rs.getString("Plattform"),
						rs.getString("Standort"),
						rs.getString("Franchise"),
						rs.getString("Altersgruppe"),
						rs.getString("Zusatzinformationen"),
						rs.getString("Link")
						));
				l.setEditable(false);
				gamesAccordion.getPanes().add(new TitledPane(rs.getString("Titel"), l));
			}
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		
		
	}

	@FXML
	public void suchenOnAction(ActionEvent actionEvent) throws Exception
	{
		fensteroeffnen("/Suche/Suche.fxml", "Suche");
	}

	@FXML
	public void fensteroeffnen(String fxml, String titel) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource(fxml));
		Stage primaryStage = new Stage();
		primaryStage.setTitle(titel);
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public void videothekHinzufuegen(TitledPane pane)
	{
		videothekAccordion.getPanes().add(pane);
	}
}