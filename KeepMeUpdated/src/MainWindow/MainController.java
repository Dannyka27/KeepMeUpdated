package MainWindow;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import Hinzufuegen.*;
import MainWindow.mediaPanes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController
{
	@FXML
	private TableColumn<Film, String> filmeTitelC;
	@FXML
	private TableColumn<Film, String> filmeUntertitelC;
	@FXML
	private TableColumn<Film, String> filmeLinkC;
	@FXML
	private TableColumn<Serie, String> serienTitelC;
	@FXML
	private TableColumn<Serie, String> serienUntertitelC;
	@FXML
	private TableColumn<Serie, String> serienLinkC;
	@FXML
	private TableColumn<Spiel, String> gamesTitelC;
	@FXML
	private TableColumn<Spiel, String> gamesUntertitelC;
	@FXML
	private TableColumn<Spiel, String> gamesLinkC;
	@FXML
	private TableColumn<Hoerspiel, String> hoerspielTitelC;
	@FXML
	private TableColumn<Hoerspiel, String> hoerspielUntertitelC;
	@FXML
	private TableColumn<Hoerspiel, String> hoerspielLinkC;
	@FXML
	private TableColumn<Musik, String> musikTitelC;
	@FXML
	private TableColumn<Musik, String> musikUntertitelC;
	@FXML
	private TableColumn<Musik, String> musikLinkC;
	@FXML
	private TableColumn<Buch, String> buecherTitelC;
	@FXML
	private TableColumn<Buch, String> buecherUntertitelC;
	@FXML
	private TableColumn<Buch, String> buecherLinkC;
	@FXML
	private TableColumn<Zeitschrift, String> zeitschriftenTitelC;
	@FXML
	private TableColumn<Zeitschrift, String> zeitschriftenUntertitelC;
	@FXML
	private TableColumn<Zeitschrift, String> zeitschriftenLinkC;
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
	protected Accordion videothekAccordion;
	@FXML
	private Accordion audiothekAccordion;
	@FXML
	private Accordion bibliothekAccordion;
	@FXML
	private Accordion gamesAccordion;
	@FXML
	private TableView<Film> filmeTableView;
	@FXML
	private TableView<Hoerspiel> hoerspieleTableView;
	@FXML
	private TableView<Serie> serienTableView;
	@FXML
	private TableView<Musik> musikTableView;
	@FXML
	private TableView<Buch> buecherTableView;
	@FXML
	private TableView<Spiel> gamesTableView;
	@FXML
	private TableView<Zeitschrift> zeitschriftenTableView;
	@FXML
	private Button hinzufuegenButton;
	@FXML
	private Button suchenButton;

	ObservableList<String> videoSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Filme", "Serie",
			"Franchise", "Standort", "Altersgruppe", "Typ");
	ObservableList<String> audioSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Hörspiele", "Musik",
			"Franchise", "Folge", "Genre", "Standort", "Altersgruppe");
	ObservableList<String> biblioSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Bücher",
			"Zeitschriften", "Autor", "Genre", "Franchise", "Standort", "Altersgruppe", "Herausgeber");
	ObservableList<String> gamesSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Plattform",
			"Franchise", "Standort", "Altersgruppe");
	ObservableList<Film> wfilmlist = FXCollections.observableArrayList();
	ObservableList<Serie> wserielist = FXCollections.observableArrayList();
	ObservableList<Hoerspiel> whoerspiellist = FXCollections.observableArrayList();
	ObservableList<Musik> wmusiklist = FXCollections.observableArrayList();
	ObservableList<Buch> wbuchlist = FXCollections.observableArrayList();
	ObservableList<Zeitschrift> wzeitschriftlist = FXCollections.observableArrayList();
	ObservableList<Spiel> wgamelist = FXCollections.observableArrayList();

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

	@FXML
	private void initialize()
	{
		videoSortierenChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> oV, String oldValue, String newValue)
			{
				videoSortieren(newValue);
			}
		});
		audioSortierenChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> oV, String oldValue, String newValue)
			{
				audioSortieren(newValue);
			}
		});
		biblioSortierenChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> oV, String oldValue, String newValue)
			{
				biblioSortieren(newValue);
			}
		});
		gamesSortierenChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> oV, String oldValue, String newValue)
			{
				gamesSortieren(newValue);
			}
		});
		wishlistfuellen("Filme");
		wishlistfuellen("Hörspiele");
		wishlistfuellen("Bücher");
		wishlistfuellen("Spiele");

		videoSortierenChoiceBox.setValue("A-Z");
		videoSortierenChoiceBox.setItems(videoSortierenList);
		audioSortierenChoiceBox.setValue("A-Z");
		audioSortierenChoiceBox.setItems(audioSortierenList);
		biblioSortierenChoiceBox.setValue("A-Z");
		biblioSortierenChoiceBox.setItems(biblioSortierenList);
		gamesSortierenChoiceBox.setValue("A-Z");
		gamesSortierenChoiceBox.setItems(gamesSortierenList);
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

	public void loeschenOnAction(ActionEvent actionEvent)
	{

	}

	public void bearbeitenOnAction(ActionEvent actionEvent) 
	{
	    /*GeÃ¶ffnet wird, entsprechend dem ausgewÃ¤hlten Element, die passende FXML Datei, bei der die TextFields
		mit den Daten aus der Datenbank als PromptText gefÃ¼llt sind und die ChoiceBoxen als Default den Wert haben,
		der in der Datenbank hinterlegt ist*/
	
		// Die aufgeklappte Pane wird ermittelt. Da immer nur eine geöffnet sein kann, ist es die zu bearbeitende
		if(!(videothekAccordion.getExpandedPane() instanceof Medium))
			throw new RuntimeException("Das sollte nicht passieren, ein Panel im Accordion ist kein Medium!");
		
    	Medium selectedMedium = (Medium) videothekAccordion.getExpandedPane();
		
    	Parent root;
    	Stage primaryStage;
    	
    	try 
		{
    		if(selectedMedium instanceof Film)
    		{
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenFilm.fxml"));//Die passende FXML wird geladen
    			root = loader.load();
    			HinzufuegenFilmController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
    		
    			//Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
    			hinzufuegenController.promptMedium(selectedMedium);
    		
    			//Die Stage wird angelegt und die FXML hinzugefÃ¼gt
    			primaryStage = new Stage();
    			primaryStage.setTitle("Film bearbeiten");
    		}
    		
    		else if(selectedMedium instanceof Serie)
    		{
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenSerie.fxml"));//Die passende FXML wird geladen
    			root = loader.load();
    			HinzufuegenSerieController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
    		
    			//Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
    			hinzufuegenController.promptMedium(selectedMedium);
    		
    			//Die Stage wird angelegt und die FXML hinzugefÃ¼gt
    			primaryStage = new Stage();
    			primaryStage.setTitle("Serie bearbeiten");
    		}
    		
    		else if(selectedMedium instanceof Musik)
    		{
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenMusik.fxml"));//Die passende FXML wird geladen
    			root = loader.load();
    			HinzufuegenMusikController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
    		
    			//Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
    			hinzufuegenController.promptMedium(selectedMedium);
    		
    			//Die Stage wird angelegt und die FXML hinzugefÃ¼gt
    			primaryStage = new Stage();
    			primaryStage.setTitle("Musik bearbeiten");
    		}
    		
    		else if(selectedMedium instanceof Hoerspiel)
    		{
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenHoerspiel.fxml"));//Die passende FXML wird geladen
    			root = loader.load();
    			HinzufuegenHoerspielController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
    		
    			//Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
    			hinzufuegenController.promptMedium(selectedMedium);
    		
    			//Die Stage wird angelegt und die FXML hinzugefÃ¼gt
    			primaryStage = new Stage();
    			primaryStage.setTitle("Hörspiel bearbeiten");
    		}
    		
    		else if(selectedMedium instanceof Buch)
    		{
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenBuch.fxml"));//Die passende FXML wird geladen
    			root = loader.load();
    			HinzufuegenBuchController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
    		
    			//Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
    			hinzufuegenController.promptMedium(selectedMedium);
    		
    			//Die Stage wird angelegt und die FXML hinzugefÃ¼gt
    			primaryStage = new Stage();
    			primaryStage.setTitle("Buch bearbeiten");
    		}
    		
    		else if(selectedMedium instanceof Zeitschrift)
    		{
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenZeitschrift.fxml"));//Die passende FXML wird geladen
    			root = loader.load();
    			HinzufuegenZeitschriftController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
    		
    			//Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
    			hinzufuegenController.promptMedium(selectedMedium);
    		
    			//Die Stage wird angelegt und die FXML hinzugefÃ¼gt
    			primaryStage = new Stage();
    			primaryStage.setTitle("Zeitschrift bearbeiten");
    		}
    		
    		else if(selectedMedium instanceof Spiel)
    		{
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenGame.fxml"));//Die passende FXML wird geladen
    			root = loader.load();
    			HinzufuegenGameController hinzufuegenController = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
    		
    			//Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
    			hinzufuegenController.promptMedium(selectedMedium);
    		
    			//Die Stage wird angelegt und die FXML hinzugefÃ¼gt
    			primaryStage = new Stage();
    			primaryStage.setTitle("Spiel bearbeiten");
    		}
    		
    		else
    		{
    			System.err.println("Kein Plan welcher Tab ausgewählt ist...");
    			return;
    		}
    			
    		
    		Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
    	} catch (IOException e) 
    	{
			e.printStackTrace();
		}
    	
        
	}

	public void alertzeigen(Medium m)
    {
        if(m instanceof Film)
        {
            Film filmloeschen = filmeTableView.getSelectionModel().getSelectedItem();
            String filmloeschentitel = filmloeschen.getTitel();
            System.out.println(filmloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Film löschen");
            alert.setContentText("Film \"" + filmloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK) {
                //Rest
            } else {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Film wurde nicht gelöscht");
                abbruch.showAndWait();
            }
        }
    }

	public void wloeschenOnAction(ActionEvent actionEvent) {
        Medium m = null;
        if(filmeTableView.getSelectionModel().getSelectedItem() != null)
        {
            m = filmeTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        }
        else if(serienTableView.getSelectionModel().getSelectedItem() != null)
        {
            m = serienTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        }
        else if(hoerspieleTableView.getSelectionModel().getSelectedItem() != null)
        {
            m = hoerspieleTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        }
        else if(musikTableView.getSelectionModel().getSelectedItem() != null)
        {
            m = musikTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        }
        else if(buecherTableView.getSelectionModel().getSelectedItem() != null)
        {
            m = buecherTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        }
        else if(zeitschriftenTableView.getSelectionModel().getSelectedItem() != null)
        {
            m = zeitschriftenTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        }
        else if(gamesTableView.getSelectionModel().getSelectedItem() != null)
        {
            m = gamesTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        }
        alertzeigen(m);

        //es handelt sich um jeweils separate TableViews, daher werden sie nacheinander einfach abgearbeitet

        //Film löschen
        /*Film filmloeschen = filmeTableView.getSelectionModel().getSelectedItem();
        alertzeigen(filmloeschen);
            String filmloeschentitel = filmloeschen.getTitel();
            System.out.println(filmloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Film löschen");
            alert.setContentText("Film \"" + filmloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK) {
                //Rest
            } else {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Film wurde nicht gelöscht");
                abbruch.show();
            }*/
        //Serie löschen
       /* Serie serieleoschen = serienTableView.getSelectionModel().getSelectedItem();
            String serieloeschentitel = serieleoschen.getTitel();
            System.out.println(filmloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Film löschen");
            alert.setContentText("Film \"" + serieloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK) {
                //Rest
            } else {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Film wurde nicht gelöscht");
                abbruch.show();
            }*/
        Hoerspiel hoerspielloeschen = hoerspieleTableView.getSelectionModel().getSelectedItem();
        Musik musikloeschen = musikTableView.getSelectionModel().getSelectedItem();
        Buch buchloeschen = buecherTableView.getSelectionModel().getSelectedItem();
        Zeitschrift zeitschriftloeschen = zeitschriftenTableView.getSelectionModel().getSelectedItem();
        Spiel spielloeschen = gamesTableView.getSelectionModel().getSelectedItem();




    }

	public void wbearbeitenOnAction(ActionEvent actionEvent)
    {
        //ausgewählte Zeile rausfinden
        Film filmbearbeiten = filmeTableView.getSelectionModel().getSelectedItem();
        System.out.println("Bearbeiten!");
    }

	public void wwunschuebertragenOnAction(ActionEvent actionEvent) {
    }

	public void wishlistfuellen(String data)
    {
        ResultSet wl = Main.db.dbAbfrage("SELECT * FROM " + data + " ORDER BY Titel ASC");
        try {
            while (wl.next()) {
                if (wl.getString("LinK") != null) {
                    if(data.equals("Filme")) {
                        if(wl.getString("Typ").equals("Filme"))
                        {
                            wfilmlist.add(new Film(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                    wl.getString("Altersgruppe"), wl.getString("Typ"), wl.getString("Zusatzinformationen"),
                                    wl.getString("Standort"), wl.getString("Franchise"), wl.getString("Link")));

                            filmeTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                            filmeUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                            filmeLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                            filmeTableView.setItems(wfilmlist);
                        }
                        else
                        {
                            wserielist.add(new Serie(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                    wl.getString("Season"), wl.getString("Zusatzinformationen"), wl.getString("Altersgruppe"),
                                    wl.getString("Standort"), wl.getString("Franchise"), wl.getString("Medium"), wl.getString("Link")));

                            serienTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                            serienUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                            serienLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                            serienTableView.setItems(wserielist);
                        }
                    }
                    else if(data.equals("Hörspiele"))
                    {
                        if(wl.getString("Typ").equals("Hörspiele"))
                        {
                            whoerspiellist.add(new Hoerspiel(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                    wl.getInt("Folge"), wl.getString("Zusatzinformationen"), wl.getString("Altersgruppe"),
                                    wl.getString("Standort"), wl.getString("Link")));

                            hoerspielTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                            hoerspielUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                            hoerspielLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                            hoerspieleTableView.setItems(whoerspiellist);
                        }
                        else
                        {
                            wmusiklist.add(new Musik(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                    wl.getString("Genre"), wl.getString("Franchise"), wl.getString("Zusatzinformationen"),
                                    wl.getString("Standort"), wl.getString("Link")));

                            musikTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                            musikUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                            musikLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                            musikTableView.setItems(wmusiklist);
                        }
                    }
                    else if(data.equals("Bücher"))
                    {
                        if(wl.getString("Typ").equals("Bücher"))
                        {
                            wbuchlist.add(new Buch(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                    wl.getString("Genre"), wl.getString("Standort"), wl.getString("Autor"),
                                    wl.getString("Zusatzinformationen"), wl.getString("Franchise"), wl.getString("Altersgruppe"),
                                    wl.getString("Link")));

                            buecherTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                            buecherUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                            buecherLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                            buecherTableView.setItems(wbuchlist);
                        }
                        else
                        {
                            wzeitschriftlist.add(new Zeitschrift(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                    wl.getString("Herausgeber"), wl.getString("Ausgabe"), wl.getString("Zusatzinformationen"),
                                    wl.getString("Genre"), wl.getString("Standort"), wl.getString("Link")));

                            zeitschriftenTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                            zeitschriftenUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                            zeitschriftenLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                            zeitschriftenTableView.setItems(wzeitschriftlist);
                        }
                    }
                    else if(data.equals("Spiele"))
                    {
                        wgamelist.add(new Spiel(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                wl.getString("Altersgruppe"), wl.getString("Zusatzinformationen"), wl.getString("Standort"),
                                wl.getString("Franchise"), wl.getString("Plattform"), wl.getString("Link")));

                        gamesTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                        gamesUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                        gamesLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                        gamesTableView.setItems(wgamelist);
                    }
                    else
                    {
                        System.out.println("IWas ist falsch");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void videoSortieren(String s)
    {
        videothekAccordion.getPanes().clear();
        //Das Feld wird geleert, damit sich die Panes nicht jedes mal aufeinander stapeln, wenn man sortieren möchte

        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC"); //default
        switch(s) //Es wird geprüft, welche Sortierung in der ChoiceBox ausgewählt wurde, je nach dem ändert sich die SQL-Anweisung
        {
            case "A-Z" :
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC");
                break;
            case "Z-A" :
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel DESC");
                break;
            case "Filme" :
                rs = Main.db.dbAbfrage("SELECT * FROM Filme WHERE Typ = 'Filme' ORDER BY Titel ASC");
                break;
            case "Serie" :
                rs = Main.db.dbAbfrage("SELECT * FROM Filme WHERE Typ = 'Serien' ORDER BY Titel ASC");
                break;
            case "Franchise" :
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Franchise ASC, Titel ASC");
                break;
            case "Standort" :
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Standort ASC, Titel ASC");
                break;
            case "Altersgruppe" :
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Altersgruppe ASC, Titel ASC");
                break;
            case "Typ" :
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Typ ASC, Titel ASC");
                break;
        }
        try
        {
            while (rs.next())
            {
            	// Alles was nen Link hat, gehört in die Wishlist, 
            	// der Rest wird in die jeweilige Klasse verpackt und ins Accordion eingefügt
                if(rs.getString("Link") == null) 
                { 
                	if(rs.getString("Typ") == null)
                	{
                		System.err.println("Warnung: In einem Eintrag in der Videothek ist der Typ NULL!");
                		continue;
                	}
                	
                	if(rs.getString("Typ").equalsIgnoreCase("Filme"))
                	{
                		Film film = new Film(
                        		rs.getInt("ID"), 
                        		rs.getString("Titel"), 
                        		rs.getString("Untertitel"), 
                        		rs.getString("Altersgruppe"), 
                        		rs.getString("Medium"),
                        		rs.getString("Zusatzinformationen"),
                        		rs.getString("Standort"), 
                        		rs.getString("Franchise"),
                                rs.getString("Link")
                        );
                        videothekAccordion.getPanes().add(film);
                	}
                	else if(rs.getString("Typ").equalsIgnoreCase("Serien"))
                	{
                		Serie serie = new Serie(
                				rs.getInt("ID"),
                				rs.getString("Titel"), 
                				rs.getString("Untertitel"),
                				rs.getString("Season"), 
                				rs.getString("Zusatzinformationen"), 
                				rs.getString("Altersgruppe"),
                				rs.getString("Standort"),
                				rs.getString("Franchise"),
                				rs.getString("Medium"), 
                				rs.getString("Link")
        				);
                		videothekAccordion.getPanes().add(serie);
                	}
                	else
                		System.err.println("Warning: " + rs.getString("Typ")  + " ist kein Film/Serie!");
                }
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

	public void audioSortieren(String s)
    {
        audiothekAccordion.getPanes().clear();

        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC"); //default
        switch(s)
        {
            case "A-Z" :
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC");
                break;
            case "Z-A" :
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel DESC");
                break;
            case "Hörspiele" :
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele WHERE Typ = 'Hörspiele' ORDER BY Titel ASC");
                break;
            case "Musik" :
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele WHERE Typ = 'Musik' ORDER BY Titel ASC");
                break;
            case "Franchise" :
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Franchise ASC, Titel ASC");
                break;
            case "Folge" :
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC, Folge ASC");
                break;
            case "Genre" :
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Genre ASC, Titel ASC");
                break;
            case "Standort" :
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Standort ASC, Titel ASC");
                break;
            case "Altersgruppe" :
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Altersgruppe ASC, Titel ASC");
                break;
        }
        try
        {
            while (rs.next())
            {
                // Alles was nen Link hat, gehört in die Wishlist, 
            	// der Rest wird in die jeweilige Klasse verpackt und ins Accordion eingefügt
                if(rs.getString("Link") == null) 
                {
                	if(rs.getString("Typ") == null)
                	{
                		System.err.println("Warnung: In einem Eintrag in der Audiothek ist der Typ NULL!");
                		continue;
                	}
                	
					if(rs.getString("Typ").equalsIgnoreCase("Hörspiele"))
					{
						Hoerspiel hoerspiel = new Hoerspiel(
								rs.getInt("ID"), 
								rs.getString("Titel"), 
								rs.getString("Untertitel"),
								rs.getInt("Folge"), 
								rs.getString("Zusatzinformationen"),
								rs.getString("Altersgruppe"),
								rs.getString("Standort"),
								rs.getString("Link")
						);

	                    audiothekAccordion.getPanes().add(hoerspiel);
					}
					else if(rs.getString("Typ").equalsIgnoreCase("Musik"))
					{
						Musik musik = new Musik(rs.getInt("ID"),
								rs.getString("Titel"), 
								rs.getString("Untertitel"), 
								rs.getString("Genre"),
								rs.getString("Franchise"),
								rs.getString("Zusatzinformationen"), 
								rs.getString("Standort"), 
								rs.getString("Link")
						);
						audiothekAccordion.getPanes().add(musik);
					}
					else
						System.err.println("Warning: " + rs.getString("Typ")  + " ist kein Hörbuch/Musik!");
                }
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

	public void biblioSortieren(String s)
    {
        bibliothekAccordion.getPanes().clear();

        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC"); //default
        switch(s)
        {
            case "A-Z" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC");
                break;
            case "Z-A" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel DESC");
                break;
            case "Bücher" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher WHERE Typ = 'Bücher' ORDER BY Titel ASC");
                break;
            case "Zeitschriften" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher WHERE Typ = 'Zeitschriften' ORDER BY Titel ASC");
                break;
            case "Franchise" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Franchise ASC, Titel ASC");
                break;
            case "Autor" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Autor ASC, Titel ASC");
                break;
            case "Genre" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Genre ASC, Titel ASC");
                break;
            case "Standort" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Standort ASC, Titel ASC");
                break;
            case "Altersgruppe" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Altersgruppe ASC, Titel ASC");
                break;
            case "Herausgeber" :
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Herausgeber ASC, Titel ASC");
                break;
        }
        try
        {
            while (rs.next())
            {
            	// Alles was nen Link hat, gehört in die Wishlist, 
            	// der Rest wird in die jeweilige Klasse verpackt und ins Accordion eingefügt
                if(rs.getString("Link") == null) 
                {
                	if(rs.getString("Typ") == null)
                	{
                		System.err.println("Warnung: In einem Eintrag in der Bibliothek ist der Typ NULL!");
                		continue;
                	}
                	
                	if(rs.getString("Typ").equalsIgnoreCase("Zeitschriften"))
        			{
                		Zeitschrift zeitung = new Zeitschrift(
                				rs.getInt("ID"), 
                				rs.getString("Titel"),
                				rs.getString("Untertitel"),
                				rs.getString("Herausgeber"),
                				rs.getString("Ausgabe"), 
                				rs.getString("Zusatzinformationen"), 
                				rs.getString("Genre"), 
                				rs.getString("Standort"),
                				rs.getString("Link")
        				);
                		bibliothekAccordion.getPanes().add(zeitung);
                				
        			}
                	else if(rs.getString("Typ").equalsIgnoreCase("Bücher"))
                	{
                		Buch buch = new Buch(
                				rs.getInt("ID"), 
                				rs.getString("Titel"),
                				rs.getString("Untertitel"),
                				rs.getString("Genre"),
                				rs.getString("Standort"),
                				rs.getString("Autor"),
                				rs.getString("Zusatzinformationen"),
                				rs.getString("Franchise") ,
                				rs.getString("Altersgruppe"),
                				rs.getString("Link")
        				);
                		bibliothekAccordion.getPanes().add(buch);
                	}
                	else
                		System.err.println("Warning: " + rs.getString("Typ")  + " ist kein Buch/Zeitschrift!");
                }
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

	public void gamesSortieren(String s)
    {
        gamesAccordion.getPanes().clear();

        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC"); //default
        switch(s)
        {
            case "A-Z" :
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC");
                break;
            case "Z-A" :
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel DESC");
                break;
            case "Plattform" :
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Plattform ASC, Titel ASC");
                break;
            case "Franchise" :
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Franchise ASC, Titel ASC");
                break;
            case "Standort" :
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Standort ASC, Titel ASC");
                break;
            case "Altersgruppe" :
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Altersgruppe ASC, Titel ASC");
                break;
        }
        try
        {
        	// Alles was nen Link hat, gehört in die Wishlist, 
        	// der Rest kommt in die Klasse Game und ins Accordion
            while (rs.next())
            {
                if(rs.getString("Link") == null) 
                {
                	Spiel spiel = new Spiel(
                			rs.getInt("ID"),
                			rs.getString("Titel"),
                			rs.getString("Untertitel"), 
                			rs.getString("Altersgruppe"), 
                			rs.getString("Zusatzinformationen"),
                			rs.getString("Standort"),
                			rs.getString("Franchise"), 
                			rs.getString("Plattform"), 
                			rs.getString("Link")
        			);
            		gamesAccordion.getPanes().add(spiel);
                }
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
