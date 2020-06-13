package MainWindow;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.function.Consumer;

import Hinzufuegen.*;
import MainWindow.mediaPanes.*;
import Wishlist.*;
import datenhaltung.DatenbankEintrag;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;


public class MainController {

    //CLUDGE
    public static MainController instanz = null;

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

    private Consumer<String> updateMethod;

    //WebView browser = new WebView();
    //WebEngine webEngine = browser.getEngine();

    ObservableList<String> videoSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Filme", "Serie", "Franchise", "Standort", "Altersgruppe", "Typ");
    ObservableList<String> audioSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Hörspiele", "Musik", "Franchise", "Folge", "Genre", "Standort", "Altersgruppe");
    ObservableList<String> biblioSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Bücher", "Zeitschriften", "Autor", "Genre", "Franchise", "Standort", "Altersgruppe", "Herausgeber");
    ObservableList<String> gamesSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Plattform", "Franchise", "Standort", "Altersgruppe");
    public ObservableList<Film> wfilmlist = FXCollections.observableArrayList();
    public ObservableList<Serie> wserielist = FXCollections.observableArrayList();
    public ObservableList<Hoerspiel> whoerspiellist = FXCollections.observableArrayList();
    public ObservableList<Musik> wmusiklist = FXCollections.observableArrayList();
    public ObservableList<Buch> wbuchlist = FXCollections.observableArrayList();
    public ObservableList<Zeitschrift> wzeitschriftlist = FXCollections.observableArrayList();
    public ObservableList<Spiel> wgamelist = FXCollections.observableArrayList();

    @FXML
    public void hinzufuegenOnAction(ActionEvent actionEvent) throws Exception {
        if (videothekTab.isSelected()) {
            fensteroeffnen("/Hinzufuegen/HinzufuegenFilm.fxml", "Hinzufügen");
        } else if (audiothekTab.isSelected()) {
            fensteroeffnen("/Hinzufuegen/HinzufuegenMusik.fxml", "Hinzufügen");
        } else if (bibliothekTab.isSelected()) {
            fensteroeffnen("/Hinzufuegen/HinzufuegenBuch.fxml", "Hinzufügen");
        } else if (gamesTab.isSelected()) {
            fensteroeffnen("/Hinzufuegen/HinzufuegenGame.fxml", "Hinzufügen");
        } else if (wishlistTab.isSelected()) {
            fensteroeffnen("/Wishlist/WishlistFilm.fxml", "Wunsch offenbaren");
        }
    }

    @FXML
    private void initialize() {
        videoSortierenChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> oV, String oldValue, String newValue) {
                videoSortieren(newValue);
            }
        });
        audioSortierenChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> oV, String oldValue, String newValue) {
                audioSortieren(newValue);
            }
        });
        biblioSortierenChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> oV, String oldValue, String newValue) {
                biblioSortieren(newValue);
            }
        });
        gamesSortierenChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> oV, String oldValue, String newValue) {
                gamesSortieren(newValue);
            }
        });
        wishlistfuellen("Filme", "Filme");
        wishlistfuellen("Filme", "Serien");
        wishlistfuellen("Hörspiele", "Hörspiele");
        wishlistfuellen("Hörspiele", "Musik");
        wishlistfuellen("Bücher", "Bücher");
        wishlistfuellen("Bücher", "Zeitschriften");
        wishlistfuellen("Spiele", "Spiele");

        videoSortierenChoiceBox.setValue("A-Z");
        videoSortierenChoiceBox.setItems(videoSortierenList);
        audioSortierenChoiceBox.setValue("A-Z");
        audioSortierenChoiceBox.setItems(audioSortierenList);
        biblioSortierenChoiceBox.setValue("A-Z");
        biblioSortierenChoiceBox.setItems(biblioSortierenList);
        gamesSortierenChoiceBox.setValue("A-Z");
        gamesSortierenChoiceBox.setItems(gamesSortierenList);

        instanz = this;
    }

    @FXML
    public void suchenOnAction(ActionEvent actionEvent) throws Exception {
        fensteroeffnen("/Suche/Suche.fxml", "Suche");
    }

    @FXML
    public void fensteroeffnen(String fxml, String titel) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage primaryStage = new Stage();
        primaryStage.setTitle(titel);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //läuft außer Folge muss angegeben werden
    public void bearbeitenOnAction(ActionEvent actionEvent) {
        /*GeÃ¶ffnet wird, entsprechend dem ausgewÃ¤hlten Element, die passende FXML Datei, bei der die TextFields
        mit den Daten aus der Datenbank als PromptText gefÃ¼llt sind und die ChoiceBoxen als Default den Wert haben,
        der in der Datenbank hinterlegt ist*/
        if (videothekTab.isSelected())  //Es wird geprÃ¼ft, welcher Tab ausgewÃ¤hlt ist
        {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC");  //passend dazu werden die Daten aus der Datenbank abgefragt
            //String text = videothekAccordion.getExpandedPane().getText();  /*Die aufgeklappte Pane wird ermittelt. Da immer nur eine geÃ¶ffnet sein kann, ist es die zu bearbeitende
             /*Das was im Header der TitledPane steht, wird in 'text' gespeichert*/
            try {
                while (rs.next()) //die Datenbank wird durchlaufen
                {
                    String identifikation = videothekAccordion.getExpandedPane().getId();
                    int id = Integer.parseInt(identifikation);
                    if (rs.getInt("ID") == id)
                    {
                        if (rs.getString("Typ").equals("Filme"))//es wird unterschieden nach Film und Serie(siehe else)
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenFilm.fxml"));//Die passende FXML wird geladen
                            Parent root = loader.load();
                            HinzufuegenFilmController hfilmcontroller = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu kÃ¶nnen
                            Film f = new Film(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                        rs.getString("Altersgruppe"), rs.getString("Medium"), rs.getString("Zusatzinformationen"),
                                        rs.getString("Standort"), rs.getString("Franchise"), rs.getString("Link"));
                            //Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
                            hfilmcontroller.promptTitel(rs.getString("Titel"));
                            hfilmcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hfilmcontroller.promptStandortBox(rs.getString("Standort"));
                            hfilmcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hfilmcontroller.promptAlterBox(rs.getString("Altersgruppe"));
                            hfilmcontroller.promptFranchise(rs.getString("Franchise"));
                            hfilmcontroller.promptMediumBox(rs.getString("Typ"));
                            hfilmcontroller.promptTyp(rs.getString("Medium"));
                            hfilmcontroller.setMedium(f);

                            //Die Stage wird angelegt und die FXML hinzugefÃ¼gt
                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Film bearbeiten");
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                            break;
                        }
                        else
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenSerie.fxml"));
                            Parent root = loader.load();
                            HinzufuegenSerieController hseriecontroller = loader.getController();
                            Serie s = new Serie(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Season"), rs.getString("Zusatzinformationen"), rs.getString("Altersgruppe"),
                                    rs.getString("Standort"), rs.getString("Franchise"), rs.getString("Medium"), rs.getString("Link"));

                            hseriecontroller.promptTitel(rs.getString("Titel"));
                            hseriecontroller.promptUntertitel(rs.getString("Untertitel"));
                            hseriecontroller.promptStandortBox(rs.getString("Standort"));
                            hseriecontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hseriecontroller.promptAlterBox(rs.getString("Altersgruppe"));
                            hseriecontroller.promptFranchise(rs.getString("Franchise"));
                            hseriecontroller.promptMediumBox(rs.getString("Typ"));
                            hseriecontroller.promptSeasonBox(rs.getString("Season"));
                            hseriecontroller.promptTypBox(rs.getString("Medium"));
                            hseriecontroller.setMedium(s);

                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Serie bearbeiten");
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                            break;
                        }
                    }
                }
            } catch (SQLException | IOException e) {
                System.err.println(e.getMessage());
            }
        }
        if (audiothekTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC");
            try {
                while (rs.next()) {
                    String identifikation = audiothekAccordion.getExpandedPane().getId();
                    int id = Integer.parseInt(identifikation);
                    if (rs.getInt("ID") == id)
                    {
                        if (rs.getString("Typ").equals("Hörspiele")) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenHoerspiel.fxml"));
                            Parent root = loader.load();
                            HinzufuegenHoerspielController hhoerspielcontroller = loader.getController();
                            Hoerspiel h = new Hoerspiel(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Folge"), rs.getString("Zusatzinformationen"), rs.getString("Altersgruppe"),
                                    rs.getString("Standort"), rs.getString("Link"));

                            hhoerspielcontroller.promptTitel(rs.getString("Titel"));
                            hhoerspielcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hhoerspielcontroller.promptStandortBox(rs.getString("Standort"));
                            hhoerspielcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hhoerspielcontroller.promptAlterBox(rs.getString("Altersgruppe"));
                            hhoerspielcontroller.promptFolge(rs.getString("Folge"));
                            hhoerspielcontroller.promptMediumBox(rs.getString("Typ"));
                            hhoerspielcontroller.setMedium(h);

                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Hörspiel bearbeiten");
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                            break;
                        } else {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenMusik.fxml"));
                            Parent root = loader.load();
                            HinzufuegenMusikController hmusikcontroller = loader.getController();
                            Musik m = new Musik(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Genre"), rs.getString("Franchise"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Standort"), rs.getString("Link"));

                            hmusikcontroller.promptTitel(rs.getString("Titel"));
                            hmusikcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hmusikcontroller.promptStandortBox(rs.getString("Standort"));
                            hmusikcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hmusikcontroller.promptGenreBox(rs.getString("Genre"));
                            hmusikcontroller.promptMediumBox(rs.getString("Typ"));
                            hmusikcontroller.promptFranchise(rs.getString("Franchise"));
                            hmusikcontroller.setMedium(m);

                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Musik bearbeiten");
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                            break;
                        }
                    }
                }
            } catch (SQLException | IOException e) {
                System.err.println(e.getMessage());
            }
        }
        if (bibliothekTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC");
            try {
                while (rs.next()) {
                    String identifikation = bibliothekAccordion.getExpandedPane().getId();
                    int id = Integer.parseInt(identifikation);
                    if (rs.getInt("ID") == id)
                    {
                        if (rs.getString("Typ").equals("Bücher")) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenBuch.fxml"));
                            Parent root = loader.load();
                            HinzufuegenBuchController hbuchcontroller = loader.getController();
                            Buch b = new Buch(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Genre"), rs.getString("Standort"), rs.getString("Autor"),
                                    rs.getString("Zusatzinformationen"), rs.getString("Franchise"), rs.getString("Altersgruppe"),
                                    rs.getString("Link"));

                            hbuchcontroller.promptTitel(rs.getString("Titel"));
                            hbuchcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hbuchcontroller.promptStandortBox(rs.getString("Standort"));
                            hbuchcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hbuchcontroller.promptAlterBox(rs.getString("Altersgruppe"));
                            hbuchcontroller.promptFranchise(rs.getString("Franchise"));
                            hbuchcontroller.promptGenreBox(rs.getString("Genre"));
                            hbuchcontroller.promptAutor(rs.getString("Autor"));
                            hbuchcontroller.promptMediumBox(rs.getString("Typ"));
                            hbuchcontroller.setMedium(b);

                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Buch bearbeiten");
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                            break;
                        } else {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenZeitschrift.fxml"));
                            Parent root = loader.load();
                            HinzufuegenZeitschriftController hzeitschriftcontroller = loader.getController();
                            Zeitschrift z = new Zeitschrift(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Herausgeber"), rs.getString("Ausgabe"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Genre"), rs.getString("Standort"), rs.getString("Link"));

                            hzeitschriftcontroller.promptTitel(rs.getString("Titel"));
                            hzeitschriftcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hzeitschriftcontroller.promptStandortBox(rs.getString("Standort"));
                            hzeitschriftcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hzeitschriftcontroller.promptGenreBox(rs.getString("Genre"));
                            hzeitschriftcontroller.promptHerausgeber(rs.getString("Herausgeber"));
                            hzeitschriftcontroller.promptAusgabe(rs.getString("Ausgabe"));
                            hzeitschriftcontroller.promptMediumBox(rs.getString("Typ"));
                            hzeitschriftcontroller.setMedium(z);

                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Zeitschrift bearbeiten");
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                            break;
                        }
                    }
                }
            } catch (SQLException | IOException e) {
                System.err.println(e.getMessage());
            }
        }
        if (gamesTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC");
            try {
                while (rs.next()) {
                    String identifikation = gamesAccordion.getExpandedPane().getId();
                    int id = Integer.parseInt(identifikation);
                    if (rs.getInt("ID") == id) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenGame.fxml"));
                        Parent root = loader.load();
                        HinzufuegenGameController hgamecontroller = loader.getController();
                        Spiel g = new Spiel(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                rs.getString("Altersgruppe"), rs.getString("Zusatzinformationen"), rs.getString("Standort"),
                                rs.getString("Franchise"), rs.getString("Plattform"), rs.getString("Link"));

                        hgamecontroller.promptTitel(rs.getString("Titel"));
                        hgamecontroller.promptUntertitel(rs.getString("Untertitel"));
                        hgamecontroller.promptStandortBox(rs.getString("Standort"));
                        hgamecontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                        hgamecontroller.promptAlterBox(rs.getString("Altersgruppe"));
                        hgamecontroller.promptFranchise(rs.getString("Franchise"));
                        hgamecontroller.promptPlattformBox(rs.getString("Plattform"));
                        hgamecontroller.promptMediumBox(rs.getString("Typ"));
                        hgamecontroller.setMedium(g);

                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Game bearbeiten");
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();

                        break;
                    }
                }
            } catch (SQLException | IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    //läuft
    public void wloeschenOnAction(ActionEvent actionEvent) {
        Medium m = null;
        if (filmeTableView.getSelectionModel().getSelectedItem() != null) {
            m = filmeTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        } else if (serienTableView.getSelectionModel().getSelectedItem() != null) {
            m = serienTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        } else if (hoerspieleTableView.getSelectionModel().getSelectedItem() != null) {
            m = hoerspieleTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        } else if (musikTableView.getSelectionModel().getSelectedItem() != null) {
            m = musikTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        } else if (buecherTableView.getSelectionModel().getSelectedItem() != null) {
            m = buecherTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        } else if (zeitschriftenTableView.getSelectionModel().getSelectedItem() != null) {
            m = zeitschriftenTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        } else if (gamesTableView.getSelectionModel().getSelectedItem() != null) {
            m = gamesTableView.getSelectionModel().getSelectedItem();
            System.out.println(m);
        }
        alertzeigen(m);
    }
    public void alertzeigen(Medium m) {
        if (m instanceof Film) {
            Film filmloeschen = filmeTableView.getSelectionModel().getSelectedItem();
            String filmloeschentitel = filmloeschen.getTitel();
            System.out.println(filmloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Film löschen");
            alert.setContentText("Film \"" + filmloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK)
            {
                Main.db.eintragLoeschen(filmloeschen.getID(), filmloeschen.getTitel(), "Filme");
                wfilmlist.remove(filmloeschen);
            }
            else
            {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Film wurde nicht gelöscht");
                abbruch.showAndWait();
            }
        }if (m instanceof Serie) {
            Serie serieloeschen =serienTableView.getSelectionModel().getSelectedItem();
            String serieloeschentitel = serieloeschen.getTitel();
            System.out.println(serieloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Serie löschen");
            alert.setContentText("Serie \"" + serieloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK)
            {
                Main.db.eintragLoeschen(serieloeschen.getID(), serieloeschen.getTitel(), "Filme");
                wserielist.remove(serieloeschen);
            }
            else
            {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Serie wurde nicht gelöscht");
                abbruch.showAndWait();
            }
        }if (m instanceof Hoerspiel) {
            Hoerspiel hoerspielloeschen = hoerspieleTableView.getSelectionModel().getSelectedItem();
            String hoerspielloeschentitel = hoerspielloeschen.getTitel();
            System.out.println(hoerspielloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hörspiel löschen");
            alert.setContentText("Hörspiel \"" + hoerspielloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK)
            {
                Main.db.eintragLoeschen(hoerspielloeschen.getID(), hoerspielloeschen.getTitel(), "Hörspiele");
                whoerspiellist.remove(hoerspielloeschen);
            }
            else
            {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Hörspiel wurde nicht gelöscht");
                abbruch.showAndWait();
            }
        }if (m instanceof Musik) {
            Musik musikloeschen = musikTableView.getSelectionModel().getSelectedItem();
            String musikloeschentitel = musikloeschen.getTitel();
            System.out.println(musikloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Musik löschen");
            alert.setContentText("Musik \"" + musikloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK)
            {
                Main.db.eintragLoeschen(musikloeschen.getID(), musikloeschen.getTitel(), "Hörspiele");
                wmusiklist.remove(musikloeschen);
            }
            else
            {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Musik wurde nicht gelöscht");
                abbruch.showAndWait();
            }
        }if (m instanceof Buch) {
            Buch buchloeschen = buecherTableView.getSelectionModel().getSelectedItem();
            String buchloeschentitel = buchloeschen.getTitel();
            System.out.println(buchloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Buch löschen");
            alert.setContentText("Buch \"" + buchloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK)
            {
                Main.db.eintragLoeschen(buchloeschen.getID(), buchloeschen.getTitel(), "Bücher");
                wbuchlist.remove(buchloeschen);
            }
            else
            {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Buch wurde nicht gelöscht");
                abbruch.showAndWait();
            }
        }if (m instanceof Zeitschrift) {
            Zeitschrift zeitschriftloeschen = zeitschriftenTableView.getSelectionModel().getSelectedItem();
            String zeitschriftenloeschentitel = zeitschriftloeschen.getTitel();
            System.out.println(zeitschriftloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Zeitschrift löschen");
            alert.setContentText("Zeitschrift \"" + zeitschriftenloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK)
            {
                Main.db.eintragLoeschen(zeitschriftloeschen.getID(), zeitschriftloeschen.getTitel(), "Bücher");
                wzeitschriftlist.remove(zeitschriftloeschen);
            }
            else
            {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Zeitschrift wurde nicht gelöscht");
                abbruch.showAndWait();
            }
        }if (m instanceof Spiel) {
            Spiel spielloeschen = gamesTableView.getSelectionModel().getSelectedItem();
            String spielloeschentitel = spielloeschen.getTitel();
            System.out.println(spielloeschen);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Spiel löschen");
            alert.setContentText("Spiel \"" + spielloeschentitel + "\" löschen?");
            Optional<ButtonType> antwort = alert.showAndWait();
            if (antwort.get() == ButtonType.OK)
            {
                Main.db.eintragLoeschen(spielloeschen.getID(), spielloeschen.getTitel(), "Spiele");
                wgamelist.remove(spielloeschen);
            }
            else
            {
                Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                abbruch.setTitle("Löschen abgebrochen");
                abbruch.setContentText("Spiel wurde nicht gelöscht");
                abbruch.showAndWait();
            }
        }
    }

    //läuft Folgennummer probleme
    public void wbearbeitenOnAction(ActionEvent actionEvent) {
        //Film filmbearbeiten = filmeTableView.getSelectionModel().getSelectedItem();
        {
            if (wishlistTab.isSelected())
            {
                Film f = filmeTableView.getSelectionModel().getSelectedItem();
                Serie s = serienTableView.getSelectionModel().getSelectedItem();
                Hoerspiel h = hoerspieleTableView.getSelectionModel().getSelectedItem();
                Musik m = musikTableView.getSelectionModel().getSelectedItem();
                Buch b = buecherTableView.getSelectionModel().getSelectedItem();
                Zeitschrift z = zeitschriftenTableView.getSelectionModel().getSelectedItem();
                Spiel g = gamesTableView.getSelectionModel().getSelectedItem();
                try {
                    if(filmeTableView.isFocused())
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Wishlist/WishlistFilm.fxml"));
                        Parent root = loader.load();
                        WishlistFilmController wfilmcontroller = loader.getController();

                        wfilmcontroller.promptTitel(f.getTitel());
                        wfilmcontroller.promptUntertitel(f.getUntertitel());
                        wfilmcontroller.promptStandortBox(f.getStandort());
                        wfilmcontroller.promptZusatzinfo(f.getZusatzinformationen());
                        wfilmcontroller.promptAlterBox(f.getAltersgruppe());
                        wfilmcontroller.promptFranchise(f.getFranchise());
                        wfilmcontroller.promptMediumBox("Filme");
                        wfilmcontroller.promptTyp(f.getMedium());
                        wfilmcontroller.promptLink(f.getLink());
                        wfilmcontroller.setMedium(f);

                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Wunsch bearbeiten");
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();

                    }
                    else if (serienTableView.isFocused())
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Wishlist/WishlistSerie.fxml"));
                        Parent root = loader.load();
                        WishlistSerieController wseriecontroller = loader.getController();

                        wseriecontroller.promptTitel(s.getTitel());
                        wseriecontroller.promptUntertitel(s.getUntertitel());
                        wseriecontroller.promptStandortBox(s.getStandort());
                        wseriecontroller.promptZusatzinfo(s.getZusatzinformationen());
                        wseriecontroller.promptAlterBox(s.getAltersgruppe());
                        wseriecontroller.promptFranchise(s.getFranchise());
                        wseriecontroller.promptMediumBox("Serien");
                        wseriecontroller.promptSeasonBox(s.getSeason());
                        wseriecontroller.promptTypBox(s.getMedium());
                        wseriecontroller.promptLink(s.getLink());
                        wseriecontroller.setMedium(s);

                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Wunsch bearbeiten");
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();

                    }
                    else if (hoerspieleTableView.isFocused())
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Wishlist/WishlistHoerspiel.fxml"));
                        Parent root = loader.load();
                        WishlistHoerspielController whoerspielcontroller = loader.getController();

                        whoerspielcontroller.promptTitel(h.getTitel());
                        whoerspielcontroller.promptUntertitel(h.getUntertitel());
                        whoerspielcontroller.promptStandortBox(h.getStandort());
                        whoerspielcontroller.promptZusatzinfo(h.getZusatzinformationen());
                        whoerspielcontroller.promptAlterBox(h.getAltersgruppe());
                        whoerspielcontroller.promptMediumBox("Hörspiele");
                        whoerspielcontroller.promptFolge(h.getFolge());
                        whoerspielcontroller.promptLink(h.getLink());
                        whoerspielcontroller.setMedium(h);

                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Wunsch bearbeiten");
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
                    }
                    else if (musikTableView.isFocused())
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Wishlist/WishlistMusik.fxml"));
                        Parent root = loader.load();
                        WishlistMusikController wmusikcontroller = loader.getController();

                        wmusikcontroller.promptTitel(m.getTitel());
                        wmusikcontroller.promptUntertitel(m.getUntertitel());
                        wmusikcontroller.promptStandortBox(m.getStandort());
                        wmusikcontroller.promptZusatzinfo(m.getZusatzinformationen());
                        wmusikcontroller.promptFranchise(m.getFranchise());
                        wmusikcontroller.promptMediumBox("Musik");
                        wmusikcontroller.promptGenreBox(m.getGenre());
                        wmusikcontroller.promptLink(m.getLink());
                        wmusikcontroller.setMedium(m);

                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Wunsch bearbeiten");
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
                    }
                    else if (buecherTableView.isFocused())
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Wishlist/WishlistBuch.fxml"));
                        Parent root = loader.load();
                        WishlistBuchController wbuchcontroller = loader.getController();

                        wbuchcontroller.promptTitel(b.getTitel());
                        wbuchcontroller.promptUntertitel(b.getUntertitel());
                        wbuchcontroller.promptStandortBox(b.getStandort());
                        wbuchcontroller.promptZusatzinfo(b.getZusatzinformationen());
                        wbuchcontroller.promptAlterBox(b.getAltersgruppe());
                        wbuchcontroller.promptFranchise(b.getFranchise());
                        wbuchcontroller.promptGenreBox(b.getGenre());
                        wbuchcontroller.promptAutor(b.getAutor());
                        wbuchcontroller.promptMediumBox("Bücher");
                        wbuchcontroller.promptLink(b.getLink());
                        wbuchcontroller.setMedium(b);

                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Wunsch bearbeiten");
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();

                    }
                    else if (zeitschriftenTableView.isFocused())
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Wishlist/WishlistZeitschrift.fxml"));
                        Parent root = loader.load();
                        WishlistZeitschriftController wzeitschriftcontroller = loader.getController();

                        wzeitschriftcontroller.promptTitel(z.getTitel());
                        wzeitschriftcontroller.promptUntertitel(z.getUntertitel());
                        wzeitschriftcontroller.promptStandortBox(z.getStandort());
                        wzeitschriftcontroller.promptZusatzinfo(z.getZusatzinformationen());
                        wzeitschriftcontroller.promptMediumBox("Zeitschriften");
                        wzeitschriftcontroller.promptAusgabe(z.getAusgabe());
                        wzeitschriftcontroller.promptHerausgeber(z.getHerausgeber());
                        wzeitschriftcontroller.promptGenreBox(z.getGenre());
                        wzeitschriftcontroller.promptLink(z.getLink());
                        wzeitschriftcontroller.setMedium(z);

                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Wunsch bearbeiten");
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();

                    }
                    else if (gamesTableView.isFocused())
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Wishlist/WishlistGame.fxml"));
                        Parent root = loader.load();
                        WishlistGameController wgamecontroller = loader.getController();

                        wgamecontroller.promptTitel(g.getTitel());
                        wgamecontroller.promptUntertitel(g.getUntertitel());
                        wgamecontroller.promptStandortBox(g.getStandort());
                        wgamecontroller.promptZusatzinfo(g.getZusatzinformationen());
                        wgamecontroller.promptAlterBox(g.getAltersgruppe());
                        wgamecontroller.promptFranchise(g.getFranchise());
                        wgamecontroller.promptMediumBox("Games");
                        wgamecontroller.promptLink(g.getLink());
                        wgamecontroller.setMedium(g);

                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Wunsch bearbeiten");
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
                    }
                }
                catch ( IOException e) {
                    System.err.println(e.getMessage());
                }
            }
    }
    }

    public void wwunschuebertragenOnAction(ActionEvent actionEvent) {

    }

    //läuft
    public void wishlistfuellen(String data, String typ) {
        ResultSet wl = Main.db.dbAbfrage("SELECT * FROM " + data + " ORDER BY Titel ASC");
        try {
            while (wl.next()) {
                if (wl.getString("Link") != null) {
                    if (data.equals("Filme")) {
                        if (wl.getString("Typ").equals("Filme") && typ.equals("Filme") ) {
                            wfilmlist.add(new Film(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                    wl.getString("Altersgruppe"), wl.getString("Medium"), wl.getString("Zusatzinformationen"),
                                    wl.getString("Standort"), wl.getString("Franchise"), wl.getString("Link")));

                            filmeTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                            filmeUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                            filmeLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                            filmeTableView.setItems(wfilmlist);
                        }
                        else if(wl.getString("Typ").equals("Serien") && typ.equals("Serien"))
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
                    else if (data.equals("Hörspiele")) {
                        if (wl.getString("Typ").equals("Hörspiele") && typ.equals("Hörspiele"))
                        {
                            whoerspiellist.add(new Hoerspiel(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                    wl.getString("Folge"), wl.getString("Zusatzinformationen"), wl.getString("Altersgruppe"),
                                    wl.getString("Standort"), wl.getString("Link")));

                            hoerspielTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                            hoerspielUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                            hoerspielLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                            hoerspieleTableView.setItems(whoerspiellist);
                        }
                        else if(wl.getString("Typ").equals("Musik") && typ.equals("Musik"))
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
                    else if (data.equals("Bücher")){
                        if (wl.getString("Typ").equals("Bücher") && typ.equals("Bücher"))
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
                        else if(wl.getString("Typ").equals("Zeitschriften") && typ.equals("Zeitschriften"))
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
                    else if (data.equals("Spiele") && typ.equals("Spiele")) {
                        wgamelist.add(new Spiel(wl.getInt("ID"), wl.getString("Titel"), wl.getString("Untertitel"),
                                wl.getString("Altersgruppe"), wl.getString("Zusatzinformationen"), wl.getString("Standort"),
                                wl.getString("Franchise"), wl.getString("Plattform"), wl.getString("Link")));

                        gamesTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                        gamesUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                        gamesLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                        gamesTableView.setItems(wgamelist);
                    } else {
                        System.out.println("IWas ist falsch");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //laufen
    public void videoSortieren(String s) {
        videothekAccordion.getPanes().clear();
        //Das Feld wird geleert, damit sich die Panes nicht jedes mal aufeinander stapeln, wenn man sortieren möchte
        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC"); //default
        switch (s) //Es wird geprüft, welche Sortierung in der ChoiceBox ausgewählt wurde, je nach dem ändert sich die SQL-Anweisung
        {
            case "A-Z":
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC");
                break;
            case "Z-A":
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel DESC");
                break;
            case "Filme":
                rs = Main.db.dbAbfrage("SELECT * FROM Filme WHERE Typ = 'Filme' ORDER BY Titel ASC");
                break;
            case "Serie":
                rs = Main.db.dbAbfrage("SELECT * FROM Filme WHERE Typ = 'Serien' ORDER BY Titel ASC");
                break;
            case "Franchise":
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Franchise ASC, Titel ASC");
                break;
            case "Standort":
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Standort ASC, Titel ASC");
                break;
            case "Altersgruppe":
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Altersgruppe ASC, Titel ASC");
                break;
            case "Typ":
                rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Typ ASC, Titel ASC");
                break;
        }
        try {
            //Anlegen der TitledPanes, die jeweils eine Gridpane mit den Informationen aus der Datenbank enthalten
            while (rs.next()) {
                /*Anlegen der GridPane mit Padding und Gaps;
                ZusÃ¤tzlich Initialisierung eines ZÃ¤hlers a, der hochgezÃ¤hlt wird, wenn in der Datenbank Daten verfÃ¼gbar sind,
                damit die GridPane lÃ¼ckenlos gefÃ¼llt wird*/
                GridPane l = new GridPane();
                l.setPadding(new Insets(10, 10, 10, 20));
                l.setVgap(10);
                l.setHgap(5);
                int a = 0;
                if (rs.getString("Link") == null) {
                    if (rs.getString("Franchise") != null) {
                        Label tmpBez = new Label("Franchise:");
                        Label tmp = new Label(rs.getString("Franchise"));
                        l.add(tmpBez, 0, a); //der ZÃ¤hler wird als Angabe der Zeilennummer genutzt
                        l.add(tmp, 1, a);
                        a++; //und anschlieÃŸend eins hochgezÃ¤hlt
                    }
                    if (rs.getString("Typ") != null) {
                        Label tmpBez = new Label("Typ:");
                        Label tmp = new Label(rs.getString("Typ"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Altersgruppe") != null) {
                        Label tmpBez = new Label("Altersgruppe:");
                        Label tmp = new Label(rs.getString("Altersgruppe"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Season") != null) {
                        Label tmpBez = new Label("Season:");
                        Label tmp = new Label(rs.getString("Season"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Zusatzinformationen") != null) {
                        Label tmpBez = new Label("Zusatzinformationen:");
                        Label tmp = new Label(rs.getString("Zusatzinformationen"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Standort") != null) {
                        Label tmpBez = new Label("Standort:");
                        Label tmp = new Label(rs.getString("Standort"));
                        /*Jedes Element der Datenbank hat einen Standort, daher werden hier auch die Button fÃ¼r
                        bearbeiten und lÃ¶schen angelegt, da sie so immer sichtbar sind, danach wird ihnen die OnAction-Methode
                        zugewiesen, die dieselbe ist wie aus dem ContextMenu heraus*/
                        Button bearbeiten = new Button("Bearbeiten");
                        bearbeiten.setOnAction(this::bearbeitenOnAction);
                        Button loeschen = new Button("Löschen");
                        loeschen.setOnAction(this::loeschenOnAction);
                        //Labels und Button werden in die GridPane eingefÃ¼gt
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        l.add(bearbeiten, 2, a);
                        l.add(loeschen, 3, a);
                    }
                     /*es wird geprÃ¼ft, ob ein Untertitel existiert, dafÃ¼r wird die Abfrage aus der Datenbank in einem
                     temporÃ¤ren String gespeichert. Wenn ein Untertitel existiert, wird der String mit ': ' vorne ergÃ¤nzt.
                     Wenn nicht wird der String auf '' gesetzt.
                     Dann wird der TitledPane der Titel aus der Datenbank, der temporÃ¤ren String und die Gridpane hinzugefÃ¼gt*/
                    String tempUntertitel = rs.getString("Untertitel");
                    if (tempUntertitel != null) {
                        tempUntertitel = ": " + tempUntertitel;
                    } else {
                        tempUntertitel = "";
                    }
                    TitledPane p = new TitledPane(rs.getString("Titel") + tempUntertitel, l);
                    p.setId(rs.getString("ID"));
                    videothekAccordion.getPanes().add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void audioSortieren(String s) {
        audiothekAccordion.getPanes().clear();
        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC"); //default
        switch (s) {
            case "A-Z":
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC");
                break;
            case "Z-A":
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel DESC");
                break;
            case "Hörspiele":
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele WHERE Typ = 'Hörspiele' ORDER BY Titel ASC");
                break;
            case "Musik":
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele WHERE Typ = 'Musik' ORDER BY Titel ASC");
                break;
            case "Franchise":
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Franchise ASC, Titel ASC");
                break;
            case "Folge":
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC, Folge ASC");
                break;
            case "Genre":
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Genre ASC, Titel ASC");
                break;
            case "Standort":
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Standort ASC, Titel ASC");
                break;
            case "Altersgruppe":
                rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Altersgruppe ASC, Titel ASC");
                break;
        }
        try {
            while (rs.next()) {
                GridPane l = new GridPane();
                l.setPadding(new Insets(10, 10, 10, 20));
                l.setVgap(10);
                l.setHgap(5);
                int a = 0;
                if (rs.getString("Link") == null) {
                    if (rs.getString("Franchise") != null) {
                        Label tmpBez = new Label("Franchise:");
                        Label tmp = new Label(rs.getString("Franchise"));
                        l.add(tmpBez, 0, a); //der ZÃ¤hler wird als Angabe der Zeilennummer genutzt
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Typ") != null) {
                        Label tmpBez = new Label("Typ:");
                        Label tmp = new Label(rs.getString("Typ"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Altersgruppe") != null) {
                        Label tmpBez = new Label("Altersgruppe:");
                        Label tmp = new Label(rs.getString("Altersgruppe"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Folge") != null) {
                        Label tmpBez = new Label("Folge:");
                        Label tmp = new Label(rs.getString("Folge"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Zusatzinformationen") != null) {
                        Label tmpBez = new Label("Zusatzinformationen:");
                        Label tmp = new Label(rs.getString("Zusatzinformationen"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Standort") != null) {
                        Label tmpBez = new Label("Standort:");
                        Label tmp = new Label(rs.getString("Standort"));
                        Button bearbeiten = new Button("Bearbeiten");
                        bearbeiten.setOnAction(this::bearbeitenOnAction);
                        Button loeschen = new Button("Löschen");
                        loeschen.setOnAction(this::loeschenOnAction);
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        l.add(bearbeiten, 2, a);
                        l.add(loeschen, 3, a);
                    }
                    String tempUntertitel = rs.getString("Untertitel");
                    if (tempUntertitel != null) {
                        tempUntertitel = ": " + tempUntertitel;
                    } else {
                        tempUntertitel = "";
                    }
                    TitledPane p = new TitledPane(rs.getString("Titel") + tempUntertitel, l);
                    p.setId(rs.getString("ID"));
                    audiothekAccordion.getPanes().add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void biblioSortieren(String s) {
        bibliothekAccordion.getPanes().clear();
        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC"); //default
        switch (s) {
            case "A-Z":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC");
                break;
            case "Z-A":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel DESC");
                break;
            case "Bücher":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher WHERE Typ = 'Bücher' ORDER BY Titel ASC");
                break;
            case "Zeitschriften":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher WHERE Typ = 'Zeitschriften' ORDER BY Titel ASC");
                break;
            case "Franchise":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Franchise ASC, Titel ASC");
                break;
            case "Autor":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Autor ASC, Titel ASC");
                break;
            case "Genre":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Genre ASC, Titel ASC");
                break;
            case "Standort":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Standort ASC, Titel ASC");
                break;
            case "Altersgruppe":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Altersgruppe ASC, Titel ASC");
                break;
            case "Herausgeber":
                rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Herausgeber ASC, Titel ASC");
                break;
        }
        try {
            while (rs.next()) {
                GridPane l = new GridPane();
                l.setPadding(new Insets(10, 10, 10, 20));
                l.setVgap(10);
                l.setHgap(5);
                int a = 0;
                if (rs.getString("Link") == null) {
                    if (rs.getString("Franchise") != null) {
                        Label tmpBez = new Label("Franchise:");
                        Label tmp = new Label(rs.getString("Franchise"));
                        l.add(tmpBez, 0, a); //der ZÃ¤hler wird als Angabe der Zeilennummer genutzt
                        l.add(tmp, 1, a);
                        a++; //und anschlieÃŸend eins hochgezÃ¤hlt
                    }
                    if (rs.getString("Genre") != null) {
                        Label tmpBez = new Label("Genre:");
                        Label tmp = new Label(rs.getString("Genre"));
                        l.add(tmpBez, 0, a); //der ZÃ¤hler wird als Angabe der Zeilennummer genutzt
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Autor") != null) {
                        Label tmpBez = new Label("Autor:");
                        Label tmp = new Label(rs.getString("Autor"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Herausgeber") != null) {
                        Label tmpBez = new Label("Herausgeber:");
                        Label tmp = new Label(rs.getString("Herausgeber"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Ausgabe") != null) {
                        Label tmpBez = new Label("Ausgabe:");
                        Label tmp = new Label(rs.getString("Ausgabe"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Typ") != null) {
                        Label tmpBez = new Label("Typ:");
                        Label tmp = new Label(rs.getString("Typ"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Altersgruppe") != null) {
                        Label tmpBez = new Label("Altersgruppe:");
                        Label tmp = new Label(rs.getString("Altersgruppe"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Zusatzinformationen") != null) {
                        Label tmpBez = new Label("Zusatzinformationen:");
                        Label tmp = new Label(rs.getString("Zusatzinformationen"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Standort") != null) {
                        Label tmpBez = new Label("Standort:");
                        Label tmp = new Label(rs.getString("Standort"));
                        Button bearbeiten = new Button("Bearbeiten");
                        bearbeiten.setOnAction(this::bearbeitenOnAction);
                        Button loeschen = new Button("Löschen");
                        loeschen.setOnAction(this::loeschenOnAction);
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        l.add(bearbeiten, 2, a);
                        l.add(loeschen, 3, a);

                    }
                    String tempUntertitel = rs.getString("Untertitel");
                    if (tempUntertitel != null) {
                        tempUntertitel = ": " + tempUntertitel;
                    } else {
                        tempUntertitel = "";
                    }
                    TitledPane p = new TitledPane(rs.getString("Titel") + tempUntertitel, l);
                    p.setId(rs.getString("ID"));
                    bibliothekAccordion.getPanes().add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void gamesSortieren(String s) {
        gamesAccordion.getPanes().clear();
        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC"); //default
        switch (s) {
            case "A-Z":
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC");
                break;
            case "Z-A":
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel DESC");
                break;
            case "Plattform":
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Plattform ASC, Titel ASC");
                break;
            case "Franchise":
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Franchise ASC, Titel ASC");
                break;
            case "Standort":
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Standort ASC, Titel ASC");
                break;
            case "Altersgruppe":
                rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Altersgruppe ASC, Titel ASC");
                break;
        }
        try {
            while (rs.next()) {
                GridPane l = new GridPane();
                l.setPadding(new Insets(10, 10, 10, 20));
                l.setVgap(10);
                l.setHgap(5);
                int a = 0;
                if (rs.getString("Link") == null) {
                    if (rs.getString("Franchise") != null) {
                        Label tmpBez = new Label("Franchise:");
                        Label tmp = new Label(rs.getString("Franchise"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Plattform") != null) {
                        Label tmpBez = new Label("Plattform:");
                        Label tmp = new Label(rs.getString("Plattform"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Altersgruppe") != null) {
                        Label tmpBez = new Label("Altersgruppe:");
                        Label tmp = new Label(rs.getString("Altersgruppe"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Zusatzinformationen") != null) {
                        Label tmpBez = new Label("Zusatzinformationen:");
                        Label tmp = new Label(rs.getString("Zusatzinformationen"));
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Standort") != null) {
                        Label tmpBez = new Label("Standort:");
                        Label tmp = new Label(rs.getString("Standort"));
                        Button bearbeiten = new Button("Bearbeiten");
                        bearbeiten.setOnAction(this::bearbeitenOnAction);
                        Button loeschen = new Button("Löschen");
                        loeschen.setOnAction(this::loeschenOnAction);
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        l.add(bearbeiten, 2, a);
                        l.add(loeschen, 3, a);

                    }
                    String tempUntertitel = rs.getString("Untertitel");
                    if (tempUntertitel != null) {
                        tempUntertitel = ": " + tempUntertitel;
                    } else {
                        tempUntertitel = "";
                    }
                    TitledPane p = new TitledPane(rs.getString("Titel") + tempUntertitel, l);
                    p.setId(rs.getString("ID"));
                    gamesAccordion.getPanes().add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //funkt nur mit Swing, nicht mit FX
    public void folgeLink(String link) {
        try {
            //webEngine.load(link);
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void wlinkfolgenOnAction(ActionEvent actionEvent) {
        Medium m;
        if (filmeTableView.getSelectionModel().getSelectedItem() != null) {
            m = filmeTableView.getSelectionModel().getSelectedItem();
            folgeLink(m.getLink());
            filmeTableView.getSelectionModel().clearSelection();
        } else if (serienTableView.getSelectionModel().getSelectedItem() != null) {
            m = serienTableView.getSelectionModel().getSelectedItem();
            folgeLink(m.getLink());
            serienTableView.getSelectionModel().clearSelection();
        } else if (hoerspieleTableView.getSelectionModel().getSelectedItem() != null) {
            m = hoerspieleTableView.getSelectionModel().getSelectedItem();
            folgeLink(m.getLink());
            hoerspieleTableView.getSelectionModel().clearSelection();
        } else if (musikTableView.getSelectionModel().getSelectedItem() != null) {
            m = musikTableView.getSelectionModel().getSelectedItem();
            folgeLink(m.getLink());
            musikTableView.getSelectionModel().clearSelection();
        } else if (buecherTableView.getSelectionModel().getSelectedItem() != null) {
            m = buecherTableView.getSelectionModel().getSelectedItem();
            folgeLink(m.getLink());
            buecherTableView.getSelectionModel().clearSelection();
        } else if (zeitschriftenTableView.getSelectionModel().getSelectedItem() != null) {
            m = zeitschriftenTableView.getSelectionModel().getSelectedItem();
            folgeLink(m.getLink());
            zeitschriftenTableView.getSelectionModel().clearSelection();
        } else if (gamesTableView.getSelectionModel().getSelectedItem() != null) {
            m = gamesTableView.getSelectionModel().getSelectedItem();
            folgeLink(m.getLink());
            gamesTableView.getSelectionModel().clearSelection();
        }
    }

    //läuft und aktualisiert
    public void loeschenOnAction(ActionEvent actionEvent) {
        if (videothekTab.isSelected())  //Es wird geprÃ¼ft, welcher Tab ausgewÃ¤hlt ist
        {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC");  //passend dazu werden die Daten aus der Datenbank abgefragt
            String identifikation = videothekAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next()) //die Datenbank wird durchlaufen
                {
                    if (rs.getInt("ID") == id) {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Film löschen");
                        alert.setContentText("Film \"" + rs.getString("Titel") + "\" löschen?");
                        Optional<ButtonType> antwort = alert.showAndWait();
                        if (antwort.get() == ButtonType.OK)
                        {
                            Main.db.eintragLoeschen(rs.getInt("ID"), rs.getString("Titel"), "Filme");
                            videoSortieren("");
                        }
                        else
                        {
                            Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                            abbruch.setTitle("Löschen abgebrochen");
                            abbruch.setContentText("Film wurde nicht gelöscht");
                            abbruch.showAndWait();
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (audiothekTab.isSelected())  //Es wird geprÃ¼ft, welcher Tab ausgewÃ¤hlt ist
        {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC");  //passend dazu werden die Daten aus der Datenbank abgefragt
            String identifikation = audiothekAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);
            try {
                while (rs.next()) //die Datenbank wird durchlaufen
                {
                    if (rs.getInt("ID") == id) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Hörspiel löschen");
                        alert.setContentText("Hörspiel \"" + rs.getString("Titel") + "\" löschen?");
                        Optional<ButtonType> antwort = alert.showAndWait();
                        if (antwort.get() == ButtonType.OK)
                        {
                            Main.db.eintragLoeschen(rs.getInt("ID"), rs.getString("Titel"), "Hörspiele");
                            audioSortieren("");
                        }
                        else
                        {
                            Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                            abbruch.setTitle("Löschen abgebrochen");
                            abbruch.setContentText("Hörspiel wurde nicht gelöscht");
                            abbruch.showAndWait();
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (bibliothekTab.isSelected())  //Es wird geprÃ¼ft, welcher Tab ausgewÃ¤hlt ist
        {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC");  //passend dazu werden die Daten aus der Datenbank abgefragt
            String identifikation = bibliothekAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next()) //die Datenbank wird durchlaufen
                {
                    if (rs.getInt("ID") == id) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Buch löschen");
                        alert.setContentText("Buch \"" + rs.getString("Titel") + "\" löschen?");
                        Optional<ButtonType> antwort = alert.showAndWait();
                        if (antwort.get() == ButtonType.OK)
                        {
                            Main.db.eintragLoeschen(rs.getInt("ID"), rs.getString("Titel"), "Bücher");
                            biblioSortieren("");
                        }
                        else
                        {
                            Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                            abbruch.setTitle("Löschen abgebrochen");
                            abbruch.setContentText("Buch wurde nicht gelöscht");
                            abbruch.showAndWait();
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (gamesTab.isSelected())  //Es wird geprÃ¼ft, welcher Tab ausgewÃ¤hlt ist
        {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC");  //passend dazu werden die Daten aus der Datenbank abgefragt
            String identifikation = gamesAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next()) //die Datenbank wird durchlaufen
                {
                    if (rs.getInt("ID") == id) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Spiel löschen");
                        alert.setContentText("Spiel \"" + rs.getString("Titel") + "\" löschen?");
                        Optional<ButtonType> antwort = alert.showAndWait();
                        if (antwort.get() == ButtonType.OK)
                        {
                            Main.db.eintragLoeschen(rs.getInt("ID"), rs.getString("Titel"), "Spiele");
                            gamesSortieren("");
                        }
                        else
                        {
                            Alert abbruch = new Alert(Alert.AlertType.INFORMATION);
                            abbruch.setTitle("Löschen abgebrochen");
                            abbruch.setContentText("Spiel wurde nicht gelöscht");
                            abbruch.showAndWait();
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
