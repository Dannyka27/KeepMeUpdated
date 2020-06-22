package MainWindow;

import java.awt.*;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import MainWindow.mediaPanes.*;
import Suche.SucheController;
import alert.AlertAufrufe;
import bearbeitung.Bearbeitung;
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
import javafx.stage.Stage;
import Newslist.Newslist;
import keepMeUpdatedTools.KeepMeUpdatedTools;

/**
 * Controller für Main.fxml
 * @author Hanna
 * Sortierung
 * @author Milana
 */

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

        wishlistFuellen("Filme", "Filme");
        wishlistFuellen("Filme", "Serien");
        wishlistFuellen("Hörspiele", "Hörspiele");
        wishlistFuellen("Hörspiele", "Musik");
        wishlistFuellen("Bücher", "Bücher");
        wishlistFuellen("Bücher", "Zeitschriften");
        wishlistFuellen("Spiele", "Spiele");

        videoSortierenChoiceBox.setValue("A-Z");
        videoSortierenChoiceBox.setItems(videoSortierenList);
        audioSortierenChoiceBox.setValue("A-Z");
        audioSortierenChoiceBox.setItems(audioSortierenList);
        biblioSortierenChoiceBox.setValue("A-Z");
        biblioSortierenChoiceBox.setItems(biblioSortierenList);
        gamesSortierenChoiceBox.setValue("A-Z");
        gamesSortierenChoiceBox.setItems(gamesSortierenList);

        instanz = this;

        Newslist.newslist("Filme");
        Newslist.newslist("Serien");
        Newslist.newslist("Hörspiele");
        Newslist.newslist("Musik");
        Newslist.newslist("Bücher");
        Newslist.newslist("Zeitschriften");
        Newslist.newslist("Spiele");
    }
    /*---------------------------------------------------------------------------
    METHODEN ALLGEMEIN DIREKT AUF DER GRIDPANE UNTEN*/
    /**
     * Öffnet das Hinzufügen Fenster abhängig davon,
     * welcher Tab ausgewählt ist.
     */
    @FXML
    public void hinzufuegenOnAction(ActionEvent actionEvent) throws Exception {
        if (videothekTab.isSelected()) {
            fensterOeffnen("/Hinzufuegen/HinzufuegenFilm.fxml", "Hinzufügen");}
        else if (audiothekTab.isSelected()) {
            fensterOeffnen("/Hinzufuegen/HinzufuegenMusik.fxml", "Hinzufügen");}
        else if (bibliothekTab.isSelected()) {
            fensterOeffnen("/Hinzufuegen/HinzufuegenBuch.fxml", "Hinzufügen");}
        else if (gamesTab.isSelected()) {
            fensterOeffnen("/Hinzufuegen/HinzufuegenGame.fxml", "Hinzufügen");}
        else if (wishlistTab.isSelected()) {
            fensterOeffnen("/Wishlist/WishlistFilm.fxml", "Wunsch offenbaren");}
    }
    /**
     * Template für das Öffnen der Hinzufügen bzw. Suchen Fenster
     * @param fxml Die zu ladende FXML
     * @param titel Hinzufügen bzw. Öffnen
     */
    @FXML
    public void fensterOeffnen(String fxml, String titel) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage primaryStage = new Stage();
        primaryStage.setTitle(titel);
        KeepMeUpdatedTools.stageIconSetzen(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Öffnet das "Suchen" Fenster
     * Über den Loader der FXML wird auf ihren Controller zugegriffen, um die dazugehörigen Methoden nutzen zu
     * können. Der String typ wird mit "Filme" initialisiert und abgängig vom aktiven Tab neu gesetzt.
     * Mit typ wird {@link Suche.SucheController#boxenFuellen(String)} aufgerufen, um den Wert der dortigen ChoiceBox
     * anhängig vom aktiven Tab zu setzen.
     * Damit auch die passenden Suchvorschläge kommen, wird mit typ außerdem {@link Suche.SucheController#suchvorschlaege(String, String)}
     * aufgerufen. Einzig für Games muss dabei eine kleine Unterscheidung gemacht werden, da in der ChoiceBox "Games" steht,
     * während die Datentabelle "Spiele" heißt. Diese Inkonsistenz wird auch in unserer Dokumentation unter Punkt 7 angesprochen.
     */
    @FXML
    public void suchenOnAction(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/Suche/Suche.fxml")));
        Parent root = loader.load();
        SucheController sucheController = loader.getController();
        String typ = "Filme";
        if(videothekTab.isSelected()) {
            typ = "Filme";
        }
        else if(audiothekTab.isSelected()) {
            typ = "Hörspiele";
        }
        else if(bibliothekTab.isSelected()) {
            typ = "Bücher";
        }
        else if(gamesTab.isSelected()) {
            typ = "Games";
        }

        sucheController.boxenFuellen(typ);
        if(typ.equals("Games"))
        {
            sucheController.suchvorschlaege("Spiele", "Games");
        }
        else
        {
            sucheController.suchvorschlaege(typ, typ);
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Suche");
        KeepMeUpdatedTools.stageIconSetzen(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    /**
     * Diese Methode sorgt dafür, dass sobald einer der Suchvorschläge angeklickt wurde, sich der zum Objekt passende
     * Tab öffnet, sich die TitledPane, die das Objekt anzeigt, ausklappt und ins Sichtfeld gescrollt wird.
     * Die Methode wird aus {@link Suche.SucheController#suchvorschlaege(String, String)} aufgerufen.
     * Zunächst wird ein ResultSet aus der angegebenen Tabelle angelegt; danach muss ermittelt werden, wie viele Elemente
     * sich in dem RS befinden, dafür wird zum letzten gesprungen, abgefragt und wieder vor das erste Element gesprungen,
     * damit danach von ganz vorne das RS durchlaufen werden kann. Als Parameter ergebnis wurde der vollständige Titel
     * übergeben, daher wird ein String name angelegt, der den vollständigen Titel des aktuellen Elements des RS enthält.
     * Stimmen name und ergebnis überein, wird die if-Schleife ausgeführt. Es wird die ID des gefundenen Elements abgefragt
     * und ein double anzahlpane mit 0 initialisiert. Danach wird ein Switch-Case um den Parameter tabelle ausgeführt.
     * Am Beispiel Film:
     * Zunächst wird im MainWindow der videothekTab ausgewählt, danach geht es in eine for-each Schleife.
     * Für jede TitledPane im Accordion wird die ID, die in videoSortieren übergeben wurde angefragt. Stimmt
     * diese mit der oben abgefragten ID überein, wird die if-Schleife ausgeführt.
     * Hier werden ein paar Berechnungen durchgeführt. Das Accordion liegt in einer ScrollPane. Damit die ausgeklappte
     * TitledPane ins Sichtfeld gescrollt wird, muss ihre relative Position, aus Höhe der zugeklappten TitledPane,
     * der Gesamtzahl der TitledPanes(numRows) und der Nummer der aktuellen Pane(anzahlpane) ausgerechnet und als Vvalue
     * für die vertical Scrollbar genutzt werden. -> die Berechnung ist noch nicht 100% akkurat, aber erfüllt ihren
     * Zweck, dennoch sollte hier in Zukunft noch eine genauere Formel gefunden werden.
     * Stimmen ID und titledpaneId nicht überein, so wird anzahlpane um 1.1 hochgezählt.
     * -> auch dieser Wert ist nur eine Näherung durch Probieren und sollte in Zukunft genauer berechnet werden.
     * @param ergebnis der volle Titel des Suchvorschlags, der angeklickt wurde.
     * @param tabelle welche Tabelle abgefragt werden muss.
     */
    public void zeigeSuchergebnis(String ergebnis, String tabelle){
        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM "+ tabelle +" ORDER BY Titel ASC");
        try {
            rs.last();
            int numRows = rs.getRow();
            rs.beforeFirst();
            while (rs.next())
            {
                String name = rs.getString("Titel") + ((rs.getString("Untertitel") == null) ? "" : ": " + rs.getString("Untertitel"));
                if(name.equals(ergebnis))
                {
                    String id = rs.getString("ID");
                    double anzahlpane = 0;
                    switch (tabelle) {
                        case "Filme":
                            mainTabPane.getSelectionModel().select(videothekTab);
                            for (TitledPane p : videothekAccordion.getPanes()) {
                                String titledpaneId = p.getId();
                                if (id.equals(titledpaneId)) {
                                    double geshoehe = numRows * 25.6;
                                    double h = 25.6 * anzahlpane / geshoehe;
                                    System.out.println(p.getHeight());
                                    videothekScrollPane.vvalueProperty().setValue(h);
                                    p.setExpanded(true);
                                }
                                anzahlpane+= 1.1;
                            }
                            break;
                        case "Hörspiele":
                            mainTabPane.getSelectionModel().select(audiothekTab);
                            for (TitledPane p : audiothekAccordion.getPanes()) {
                                String titledpaneId = p.getId();
                                if (id.equals(titledpaneId)) {
                                    double geshoehe = numRows * 25.6;
                                    double h = 25.6 * anzahlpane / geshoehe;
                                    audiothekScrollPane.vvalueProperty().setValue(h);
                                    p.setExpanded(true);
                                }
                                anzahlpane += 1.1;
                            }
                            break;
                        case "Bücher":
                            mainTabPane.getSelectionModel().select(bibliothekTab);
                            for (TitledPane p : bibliothekAccordion.getPanes()) {
                                String titledpaneId = p.getId();
                                if (id.equals(titledpaneId)) {
                                    double geshoehe = numRows * 25.6;
                                    double h = 25.6 * anzahlpane / geshoehe;
                                    bibliothekScrollPane.vvalueProperty().setValue(h);
                                    p.setExpanded(true);
                                }
                                anzahlpane += 1.1;
                            }
                            break;
                        case "Spiele":
                            mainTabPane.getSelectionModel().select(gamesTab);
                            for (TitledPane p : gamesAccordion.getPanes()) {
                                String titledpaneId = p.getId();
                                if (id.equals(titledpaneId)) {
                                    double geshoehe = numRows * 25.6;
                                    double h = 25.6 * anzahlpane / geshoehe;
                                    gamesScrollPane.vvalueProperty().setValue(h);
                                    p.setExpanded(true);
                                }
                                anzahlpane += 1.1;
                            }
                            break;
                    }
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /*TABS UND METHODEN
    ---------------------------------------------------------------------------*/
    /**
     * @author Milana
     * Zunächst wird das Feld geleert, damit sich die Panes nicht aufeinander stapeln, wenn man sortieren möchte.
     * Defaultmäßig wird das ResultSet mit der Sortierung alphabetisch aufsteigend angelegt.
     * Danach wird geprüft, welche Sortierung in der ChoiceBox ausgewählt wurde, je nachdem ändert sich die
     * SQL-Anweisung.
     * @author Hanna
     * Danach werden die TitledPanes angelegt, die jeweils eine Gridpane mit den Informationen aus der Datenbank
     * enthalten. Für besseres Aussehen werden Padding und Gaps verwendet.
     * Zusätzlich wird ein Zähler a initialisiert, der hochgezählt wird, wenn in der Datenbank Daten verfügbar sind,
     * damit die GridPane lückenlos gefüllt wird.
     * Da jedes Element der Datenbank hat einen Standort hat, werden hier die Button für bearbeiten und löschen
     * angelegt, da sie so immer sichtbar sind. Danach wird ihnen die Methoden {@link MainWindow.MainController#bearbeitenOnAction(ActionEvent)}
     * und {@link MainWindow.MainController#loeschenOnAction(ActionEvent)} zugewiesen.
     * Zuletzt wird geprüft, ob ein Untertitel existiert, und wenn ja, wird er hinten an den Titel angehängt.
     * Der vollständige Name wird als Name der TitledPane verwendet und ihr wird die Gridpane als Content hinzugefügt.
     * Danach wird die ID gesetzt und die Pane in das Accordion eingefügt.
     * Die folgenden drei Methoden analog.
     */
    public void videoSortieren(String s) {
        videothekAccordion.getPanes().clear();
        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC"); //default
        switch (s) {
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
                        Button bearbeiten = new Button("Bearbeiten");
                        bearbeiten.setOnAction(this::bearbeitenOnAction);
                        Button loeschen = new Button("Löschen");
                        loeschen.setOnAction(this::loeschenOnAction);
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        l.add(bearbeiten, 2, a);
                        l.add(loeschen, 3, a);
                    }
                    String name = rs.getString("Titel")
                            +((rs.getString("Untertitel") == null) ? "" : ": "
                            + rs.getString("Untertitel"));
                    TitledPane p = new TitledPane(name, l);
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
                    String name = rs.getString("Titel") + ((rs.getString("Untertitel") == null) ? "" : ": " + rs.getString("Untertitel"));
                    TitledPane p = new TitledPane(name, l);
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
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        a++;
                    }
                    if (rs.getString("Genre") != null) {
                        Label tmpBez = new Label("Genre:");
                        Label tmp = new Label(rs.getString("Genre"));
                        l.add(tmpBez, 0, a);
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
                    String name = rs.getString("Titel") + ((rs.getString("Untertitel") == null) ? "" : ": " + rs.getString("Untertitel"));
                    TitledPane p = new TitledPane(name, l);
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
                    String name = rs.getString("Titel") + ((rs.getString("Untertitel") == null) ? "" : ": " + rs.getString("Untertitel"));
                    TitledPane p = new TitledPane(name, l);
                    p.setId(rs.getString("ID"));
                    gamesAccordion.getPanes().add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /*--------button der titledpanes--------*/
    /**
     * Zunächst wird geprüft, welche Tab selektiert ist, dazu passend werden die Daten aus der Datenbank abgefragt.
     * Danach wird ermittelt, welche Pane aufgeklappt ist; da defaultmäßig immer nur eine Pane geöffnet sein kann,
     * ist es die zu Bearbeitende. In {@link MainWindow.MainController#videoSortieren(String)}, bzw. die Anderen analog,
     * wurde jeder TitledPane die ID gegeben, die in der Datenbank zu dem jeweiligen Element hinterlegt ist. Diese wird
     * nun aus der Aufgeklappten ausgelesen und die Datenbank wird durchlaufen. Wurde das Element gefunden, für das die
     * ID der Pane und die des Elements übereinstimmen, wird überprüft, ob es sich um einen Film oder eine Serie handelt.
     * Je nachdem wird mit dem gefundenen Element ein neuer Film / neue Serie angelegt und die Methode
     * {@link bearbeitung.Bearbeitung#bearbeitenoeffnen(Medium, String, String)} aufgerufen.
     * Übergeben werden der Film / Serie, der Typ und die zu ladende FXML.*/
    public void bearbeitenOnAction(ActionEvent actionEvent) {
        if (videothekTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC");
            String identifikation = videothekAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next())
                {
                    if (rs.getInt("ID") == id) {
                        if (rs.getString("Typ").equals("Filme"))
                        {
                            Film f = new Film(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Altersgruppe"), rs.getString("Medium"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Standort"), rs.getString("Franchise"), rs.getString("Link"));
                            Bearbeitung.bearbeitenoeffnen(f, "Film", "/Hinzufuegen/HinzufuegenFilm.fxml");
                        }
                        else {
                            Serie s = new Serie(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Season"), rs.getString("Zusatzinformationen"), rs.getString("Altersgruppe"),
                                    rs.getString("Standort"), rs.getString("Franchise"), rs.getString("Medium"), rs.getString("Link"));
                            Bearbeitung.bearbeitenoeffnen(s, "Serie", "/Hinzufuegen/HinzufuegenSerie.fxml");
                        }
                        break;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (audiothekTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC");
            String identifikation = audiothekAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next()) {
                    if (rs.getInt("ID") == id) {
                        if (rs.getString("Typ").equals("Hörspiele")) {
                            Hoerspiel h = new Hoerspiel(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Folge"), rs.getString("Zusatzinformationen"), rs.getString("Altersgruppe"),
                                    rs.getString("Standort"), rs.getString("Link"));
                            Bearbeitung.bearbeitenoeffnen(h, "Hörspiel", "/Hinzufuegen/HinzufuegenHoerspiel.fxml");

                        } else {
                            Musik m = new Musik(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Genre"), rs.getString("Franchise"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Standort"), rs.getString("Link"));
                            Bearbeitung.bearbeitenoeffnen(m, "Musik", "/Hinzufuegen/HinzufuegenMusik.fxml");
                        }
                        break;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (bibliothekTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC");
            String identifikation = bibliothekAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next()) {
                    if (rs.getInt("ID") == id) {
                        if (rs.getString("Typ").equals("Bücher")) {
                            Buch b = new Buch(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Genre"), rs.getString("Standort"), rs.getString("Autor"),
                                    rs.getString("Zusatzinformationen"), rs.getString("Franchise"), rs.getString("Altersgruppe"),
                                    rs.getString("Link"));
                            Bearbeitung.bearbeitenoeffnen(b, "Buch", "/Hinzufuegen/HinzufuegenBuch.fxml");

                        } else {
                            Zeitschrift z = new Zeitschrift(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Herausgeber"), rs.getString("Ausgabe"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Genre"), rs.getString("Standort"), rs.getString("Link"));
                            Bearbeitung.bearbeitenoeffnen(z, "Zeitschrift", "/Hinzufuegen/HinzufuegenZeitschrift.fxml");
                        }
                        break;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (gamesTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC");
            String identifikation = gamesAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next()) {
                    if (rs.getInt("ID") == id) {
                            Spiel g = new Spiel(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                rs.getString("Altersgruppe"), rs.getString("Zusatzinformationen"), rs.getString("Standort"),
                                rs.getString("Franchise"), rs.getString("Plattform"), rs.getString("Link"));
                            Bearbeitung.bearbeitenoeffnen(g, "Spiel", "/Hinzufuegen/HinzufuegenGame.fxml");
                        break;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    /**
     * Auch hier wird die ID der Pane abgefragt und das jeweilige RS durchlaufen, bis eine übereinstimmung gefunden
     * wurde. Es wird zwischen dem Typ unterschieden und ein entsprechendes Objekt mit den Daten aus der Datenbank angelegt.
     * {@link alert.AlertAufrufe#loeschenAlert(String, String, Medium)} wird aufgerufen, die einen boolean Wert zurückliefert.
     * Wird true zurückgegeben, wird {@link MainWindow.MainController#videoSortieren(String)} aufgerufen,
     * die die jeweilige -thek aktualisiert.
     */
    public void loeschenOnAction(ActionEvent actionEvent) {
        if (videothekTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC");
            String identifikation = videothekAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);
            try {
                while (rs.next())
                {
                    if (rs.getInt("ID") == id) {
                        if (rs.getString("Typ").equals("Filme"))
                        {
                            Film f = new Film(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Altersgruppe"), rs.getString("Medium"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Standort"), rs.getString("Franchise"), rs.getString("Link"));
                            if(AlertAufrufe.loeschenAlert("Film", "Filme", f))
                            {
                                videoSortieren("");
                            }
                        }
                        else {
                            Serie s = new Serie(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Season"), rs.getString("Zusatzinformationen"), rs.getString("Altersgruppe"),
                                    rs.getString("Standort"), rs.getString("Franchise"), rs.getString("Medium"), rs.getString("Link"));
                            if(AlertAufrufe.loeschenAlert("Serie", "Filme", s))
                            {
                                videoSortieren("");
                            }
                        }
                        break;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (audiothekTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC");
            String identifikation = audiothekAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next()) {
                    if (rs.getInt("ID") == id) {
                        if (rs.getString("Typ").equals("Hörspiele")) {
                            Hoerspiel h = new Hoerspiel(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Folge"), rs.getString("Zusatzinformationen"), rs.getString("Altersgruppe"),
                                    rs.getString("Standort"), rs.getString("Link"));
                            if(AlertAufrufe.loeschenAlert("Hörspiel", "Hörspiele", h))
                            {
                                audioSortieren("");
                            }
                        } else {
                            Musik m = new Musik(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Genre"), rs.getString("Franchise"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Standort"), rs.getString("Link"));
                            if(AlertAufrufe.loeschenAlert("Musik", "Hörspiele", m))
                            {
                                audioSortieren("");
                            }
                        }
                        break;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (bibliothekTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC");
            String identifikation = bibliothekAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next()) {
                    if (rs.getInt("ID") == id) {
                        if (rs.getString("Typ").equals("Bücher")) {
                            Buch b = new Buch(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Genre"), rs.getString("Standort"), rs.getString("Autor"),
                                    rs.getString("Zusatzinformationen"), rs.getString("Franchise"), rs.getString("Altersgruppe"),
                                    rs.getString("Link"));
                            if(AlertAufrufe.loeschenAlert("Buch", "Bücher", b))
                            {
                                biblioSortieren("");
                            }
                        } else {
                            Zeitschrift z = new Zeitschrift(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Herausgeber"), rs.getString("Ausgabe"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Genre"), rs.getString("Standort"), rs.getString("Link"));
                            if(AlertAufrufe.loeschenAlert("Zeitschrift", "Bücher", z))
                            {
                                biblioSortieren("");
                            }
                        }
                        break;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (gamesTab.isSelected()) {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC");
            String identifikation = gamesAccordion.getExpandedPane().getId();
            int id = Integer.parseInt(identifikation);

            try {
                while (rs.next()) {
                    if (rs.getInt("ID") == id) {
                        Spiel g = new Spiel(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                rs.getString("Altersgruppe"), rs.getString("Zusatzinformationen"), rs.getString("Standort"),
                                rs.getString("Franchise"), rs.getString("Plattform"), rs.getString("Link"));
                        if(AlertAufrufe.loeschenAlert("Spiel", "Spiele", g))
                        {
                            gamesSortieren("");
                        }
                        break;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /*WISHLIST UND METHODEN
    ---------------------------------------------------------------------------*/
    /**
     * wishlistFuellen wird bei Start des Programms aufgerufen, zudem jedes Mal, nachdem ein Objekt
     * bearbeitet / neu hinzugefügt und gespeichert wurde, bzw. ein Wunsch aus der Wishlist in den
     * Katalog übertragen wurde.
     * Es muss nicht jedes Mal die gesamte Wishlist neu gefüllt werden, da die Wishlisten
     * je Typ getrennt sind und immer nur eins auf einmal bearbeitet bzw. übertragen werden kann.
     * Außerdem wird geprüft, ob ein Link in der Datenbank vorhanden ist, da auf der Wishlist nur Objekte,
     * die einen Link haben angezeigt werden sollen.
     * Es werden Objekte des spezifischen Typs erzeugt und auf der dazugehörigen List gespeichert.
     * Danach werden die Spalten der Tabelle mit den erlangten Daten aus der List gefüllt.
     * Damit das Objekt aus der TableView verschwindet, wenn es über
     * {@link MainWindow.MainController#wLoeschenOnAction(ActionEvent)} -> {@link MainWindow.MainController#alertZeigen(Medium)}
     * von der Liste entfernt wird, müssen die ELemente mit setItems() anstatt getItems().setAll()
     * hinzugefügt werden.
     * @param data Gibt an aus welcher Tabelle die Daten geholt werden müssen.
     * @param typ Gibt genau an welche Tabelle und List aktualisiert werden soll.
     */
    public void wishlistFuellen(String data, String typ) {
        ResultSet wl = Main.db.dbAbfrage("SELECT * FROM " + data + " ORDER BY Titel ASC");
        try {
            while (wl.next()) {
                if (wl.getString("Link") != null) {
                    if (data.equals("Filme"))
                    {
                        if (wl.getString("Typ").equals("Filme") && typ.equals("Filme"))
                        {
                            wfilmlist.add(new Film(wl.getInt("ID"), wl.getString("Titel"),
                                    wl.getString("Untertitel"), wl.getString("Altersgruppe"),
                                    wl.getString("Medium"), wl.getString("Zusatzinformationen"),
                                    wl.getString("Standort"), wl.getString("Franchise"),
                                    wl.getString("Link")));

                            filmeTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                            filmeUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                            filmeLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                            filmeTableView.setItems(wfilmlist);
                        }
                        else if (wl.getString("Typ").equals("Serien") && typ.equals("Serien"))
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
                    else if (data.equals("Hörspiele"))
                    {
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
                        else if (wl.getString("Typ").equals("Musik") && typ.equals("Musik"))
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
                    else if (data.equals("Bücher"))
                    {
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
                        else if (wl.getString("Typ").equals("Zeitschriften") && typ.equals("Zeitschriften"))
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
                    else if (data.equals("Spiele") && typ.equals("Spiele"))
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

    /*------------contextmenu------------*/
    /**
     * Wird aus dem ContextMenu 'Bearbeiten' aus der Wishlist aufgerufen. Am Beispiel eines Films:
     * Die filmeTableView wird durch eine List gefüllt. In dieser List befinden sich ausschließlich Objekte
     * der Klasse Filme. Daher kann man das Objekt, das bearbeitet werden soll, mit getSelectionModel.getSelectedItem()
     * ansprechen.
     * Ist die filmeTableView fokussiert, wird das ausgewählte Objekt in dem Film f gespeichert und der Methode
     * {@link bearbeitung.Bearbeitung#wbearbeitenoeffnen(Medium, String, String)} übergeben, und zusätzlich der
     * Typ (hier: 'Film') und die zu ladende FXML.
     */
    public void wBearbeitenOnAction(ActionEvent actionEvent) {
                if (filmeTableView.isFocused()) {
                    Film f = filmeTableView.getSelectionModel().getSelectedItem();
                    Bearbeitung.wbearbeitenoeffnen(f, "Film", "/Wishlist/WishlistFilm.fxml");
                    filmeTableView.getSelectionModel().clearSelection();
                }
                else if (serienTableView.isFocused()) {
                    Serie s = serienTableView.getSelectionModel().getSelectedItem();
                    Bearbeitung.wbearbeitenoeffnen(s, "Serie", "/Wishlist/WishlistSerie.fxml");
                    serienTableView.getSelectionModel().clearSelection();
                }
                else if (hoerspieleTableView.isFocused()) {
                    Hoerspiel h = hoerspieleTableView.getSelectionModel().getSelectedItem();
                    Bearbeitung.wbearbeitenoeffnen(h, "Hörspiel", "/Wishlist/WishlistHoerspiel.fxml");
                    hoerspieleTableView.getSelectionModel().clearSelection();
                }
                else if (musikTableView.isFocused()) {
                    Musik m = musikTableView.getSelectionModel().getSelectedItem();
                    Bearbeitung.wbearbeitenoeffnen(m, "Musik", "/Wishlist/WishlistMusik.fxml");
                    musikTableView.getSelectionModel().clearSelection();
                }
                else if (buecherTableView.isFocused()) {
                    Buch b = buecherTableView.getSelectionModel().getSelectedItem();
                    Bearbeitung.wbearbeitenoeffnen(b, "Buch", "/Wishlist/WishlistBuch.fxml");
                    buecherTableView.getSelectionModel().clearSelection();
                }
                else if (zeitschriftenTableView.isFocused()) {
                    Zeitschrift z = zeitschriftenTableView.getSelectionModel().getSelectedItem();
                    Bearbeitung.wbearbeitenoeffnen(z, "Zeitschrift", "/Wishlist/WishlistZeitschrift.fxml");
                    zeitschriftenTableView.getSelectionModel().clearSelection();
                }
                else if (gamesTableView.isFocused()) {
                    Spiel g = gamesTableView.getSelectionModel().getSelectedItem();
                    Bearbeitung.wbearbeitenoeffnen(g, "Spiel", "/Wishlist/WishlistGame.fxml");
                    gamesTableView.getSelectionModel().clearSelection();
                }
    }
    /**
     * Wird aus dem ContextMenu 'Wunsch übertragen' aus der Wishlist aufgerufen.
     * Je nachdem welche TableView fokussiert ist, wird das ausgewählte Objekt abgefragt und an
     * {@link alert.AlertAufrufe#wunschUebertragenAlert(String, Medium)} übergeben, zusätzlich der Typ.
     * Diese Methode gibt einen boolean Wert zurück. Ist dieser true, wird der Link des Objekts auf null gesetzt, die Methode
     * {@link datenhaltung.Datenbank#mediumSpeichern(DatenbankEintrag, String)} aufgerufen und ihr das Objekt und der
     * Tabellenname übergeben. Danach wird ein {@link alert.AlertAufrufe#infoAlert(String, String, String, String)}
     * ausgegeben, der bestätigt, dass sich das Objekt nun in der jeweiligen -thek befindet. Schließlich wird das Objekt von
     * der zur Tabelle passenden List entfernt; Zudem wird die Newslist aktualisiert und die Auswahl geleert.
     */
    public void wWunschUebertragenOnAction(ActionEvent actionEvent) {
            try {
                if (filmeTableView.isFocused())
                {
                    Film f = filmeTableView.getSelectionModel().getSelectedItem();
                    if(AlertAufrufe.wunschUebertragenAlert("Film", f))
                    {
                        f.setLink(null);
                        Main.db.mediumSpeichern(f, "Filme");
                        AlertAufrufe.infoAlert(f.getTitel(), f.getUntertitel(), "Der Film", "der Videothek");
                        videoSortieren("");
                        wfilmlist.remove(f);
                        Newslist.newslist("Filme");
                        filmeTableView.getSelectionModel().clearSelection();
                    }
                }
                if (serienTableView.isFocused())
                {
                    Serie s = serienTableView.getSelectionModel().getSelectedItem();
                    if(AlertAufrufe.wunschUebertragenAlert("Serie", s)) {
                        s.setLink(null);
                        Main.db.mediumSpeichern(s, "Filme");
                        AlertAufrufe.infoAlert(s.getTitel(), s.getUntertitel(), "Die Serie", "der Videothek");
                        videoSortieren("");
                        wserielist.remove(s);
                        Newslist.newslist("Serien");
                        serienTableView.getSelectionModel().clearSelection();
                    }
                }
                if (gamesTableView.isFocused())
                {
                    Spiel g = gamesTableView.getSelectionModel().getSelectedItem();
                    if(AlertAufrufe.wunschUebertragenAlert("Spiel", g)) {
                        g.setLink(null);
                        Main.db.mediumSpeichern(g, "Spiele");
                        AlertAufrufe.infoAlert(g.getTitel(), g.getUntertitel(), "Das Spiel", "Games");
                        gamesSortieren("");
                        wgamelist.remove(g);
                        Newslist.newslist("Spiele");
                        gamesTableView.getSelectionModel().clearSelection();
                    }
                }
                if (hoerspieleTableView.isFocused())
                {
                    Hoerspiel h = hoerspieleTableView.getSelectionModel().getSelectedItem();
                    if(AlertAufrufe.wunschUebertragenAlert("Hörspiel", h)) {
                        h.setLink(null);
                        Main.db.mediumSpeichern(h, "Hörspiele");
                        AlertAufrufe.infoAlert(h.getTitel(), h.getUntertitel(), "Das Hörspiel", "der Audiothek");
                        audioSortieren("");
                        whoerspiellist.remove(h);
                        Newslist.newslist("Hörspiele");
                        hoerspieleTableView.getSelectionModel().clearSelection();
                    }
                }
                if (musikTableView.isFocused())
                {
                    Musik m = musikTableView.getSelectionModel().getSelectedItem();
                    if(AlertAufrufe.wunschUebertragenAlert("Musik", m)) {
                        m.setLink(null);
                        Main.db.mediumSpeichern(m, "Hörspiele");
                        AlertAufrufe.infoAlert(m.getTitel(), m.getUntertitel(), "Die Musik", "der Audiothek");
                        audioSortieren("");
                        wmusiklist.remove(m);
                        Newslist.newslist("Musik");
                        musikTableView.getSelectionModel().clearSelection();
                    }
                }
                if (buecherTableView.isFocused())
                {
                    Buch b = buecherTableView.getSelectionModel().getSelectedItem();
                    if(AlertAufrufe.wunschUebertragenAlert("Buch", b)) {
                        b.setLink(null);
                        Main.db.mediumSpeichern(b, "Bücher");
                        AlertAufrufe.infoAlert(b.getTitel(), b.getUntertitel(), "Das Buch", "der Bibliothek");
                        biblioSortieren("");
                        wbuchlist.remove(b);
                        Newslist.newslist("Bücher");
                        buecherTableView.getSelectionModel().clearSelection();
                    }
                }
                if (zeitschriftenTableView.isFocused())
                {
                    Zeitschrift z = zeitschriftenTableView.getSelectionModel().getSelectedItem();
                    if(AlertAufrufe.wunschUebertragenAlert("Zeitschrift", z)) {
                        z.setLink(null);
                        Main.db.mediumSpeichern(z, "Bücher");
                        AlertAufrufe.infoAlert(z.getTitel(), z.getUntertitel(), "Die Zeitschrift", "der Bibliothek");
                        biblioSortieren("");
                        wzeitschriftlist.remove(z);
                        Newslist.newslist("Zeitschriften");
                        zeitschriftenTableView.getSelectionModel().clearSelection();
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
    }

    /**
     * Wird aus dem ContextMenu 'Link folgen' aus der Wishlist aufgerufen.
     * Je nachdem welche TableView fokussiert ist, wird das ausgewählte Objekt abgefragt und dessen Link an die Methode
     * {@link MainWindow.MainController#folgeLink(String)} übergeben. Danach wird die Selektion der Tabelle geleert.
     */
    public void wLinkFolgenOnAction(ActionEvent actionEvent) {
        if (filmeTableView.isFocused()) {
            Film f = filmeTableView.getSelectionModel().getSelectedItem();
            folgeLink(f.getLink());
            filmeTableView.getSelectionModel().clearSelection();
        }
        else if (serienTableView.isFocused()) {
            Serie s = serienTableView.getSelectionModel().getSelectedItem();
            folgeLink(s.getLink());
            serienTableView.getSelectionModel().clearSelection();
        }
        else if (hoerspieleTableView.isFocused()) {
            Hoerspiel h = hoerspieleTableView.getSelectionModel().getSelectedItem();
            folgeLink(h.getLink());
            hoerspieleTableView.getSelectionModel().clearSelection();
        }
        else if (musikTableView.isFocused()) {
            Musik m = musikTableView.getSelectionModel().getSelectedItem();
            folgeLink(m.getLink());
            musikTableView.getSelectionModel().clearSelection();
        }
        else if (buecherTableView.isFocused()) {
            Buch b = buecherTableView.getSelectionModel().getSelectedItem();
            folgeLink(b.getLink());
            buecherTableView.getSelectionModel().clearSelection();
        }
        else if (zeitschriftenTableView.isFocused()) {
            Zeitschrift z = zeitschriftenTableView.getSelectionModel().getSelectedItem();
            folgeLink(z.getLink());
            zeitschriftenTableView.getSelectionModel().clearSelection();
        }
        else if (gamesTableView.isFocused()) {
            Spiel g = gamesTableView.getSelectionModel().getSelectedItem();
            folgeLink(g.getLink());
            gamesTableView.getSelectionModel().clearSelection();
        }
    }
    public void folgeLink(String link) {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Es wird das ausgewählte Objekt aus der TableView in m gespeichert
     * und an {@link MainWindow.MainController#alertZeigen(Medium)} übergeben.
     */
    public void wLoeschenOnAction(ActionEvent actionEvent) {
        Medium m = null;
        if (filmeTableView.getSelectionModel().getSelectedItem() != null) {
            m = filmeTableView.getSelectionModel().getSelectedItem();}
        else if (serienTableView.getSelectionModel().getSelectedItem() != null) {
            m = serienTableView.getSelectionModel().getSelectedItem();}
        else if (hoerspieleTableView.getSelectionModel().getSelectedItem() != null) {
            m = hoerspieleTableView.getSelectionModel().getSelectedItem();}
        else if (musikTableView.getSelectionModel().getSelectedItem() != null) {
            m = musikTableView.getSelectionModel().getSelectedItem();}
        else if (buecherTableView.getSelectionModel().getSelectedItem() != null) {
            m = buecherTableView.getSelectionModel().getSelectedItem();}
        else if (zeitschriftenTableView.getSelectionModel().getSelectedItem() != null) {
            m = zeitschriftenTableView.getSelectionModel().getSelectedItem();}
        else if (gamesTableView.getSelectionModel().getSelectedItem() != null) {
            m = gamesTableView.getSelectionModel().getSelectedItem();}
        alertZeigen(m);
    }
    /**
     * Es wird abgefragt, von welcher Kindesklasse das übergebene Objekt eine Instanz ist.
     * Entsprechend wird {@link alert.AlertAufrufe#loeschenAlert(String, String, Medium)}
     * aufgerufen. Übergeben werden der Typ und der Tabellenname, die zu dem Objekt passen.
     * Wenn die aufgerufene Methode 'true' zurückliefert, wird das Objekt von der Liste, die
     * die Wünsche enthält, entfernt und die Auswahl geleert.
     * @param m Das übergebene Objekt, das eine Instanz des ausgewählten Objekts in der TableView ist.
     */
    public void alertZeigen(Medium m) {
        if (m instanceof Film)
        {
            if(AlertAufrufe.loeschenAlert("Film", "Filme", m))
            {
                wfilmlist.remove(m);
            }
            filmeTableView.getSelectionModel().clearSelection();
        }
        if (m instanceof Serie)
        {
            if(AlertAufrufe.loeschenAlert("Serie", "Filme", m))
            {
                wserielist.remove(m);
            }
            serienTableView.getSelectionModel().clearSelection();
        }
        if (m instanceof Hoerspiel)
        {
            if(AlertAufrufe.loeschenAlert("Hörspiel", "Hörspiele", m))
            {
                whoerspiellist.remove(m);
            }
            hoerspieleTableView.getSelectionModel().clearSelection();
        }
        if (m instanceof Musik)
        {
            if(AlertAufrufe.loeschenAlert("Musik", "Hörspiele", m))
            {
                wmusiklist.remove(m);
            }
            musikTableView.getSelectionModel().clearSelection();
        }
        if (m instanceof Buch)
        {
            if(AlertAufrufe.loeschenAlert("Buch", "Bücher", m))
            {
                wbuchlist.remove(m);
            }
            buecherTableView.getSelectionModel().clearSelection();
        }
        if (m instanceof Zeitschrift)
        {
            if(AlertAufrufe.loeschenAlert("Zeitschrift", "Bücher", m))
            {
                wzeitschriftlist.remove(m);
            }
            zeitschriftenTableView.getSelectionModel().clearSelection();
        }
        if (m instanceof Spiel)
        {
            if(AlertAufrufe.loeschenAlert("Spiel", "Spiele", m))
            {
                wgamelist.remove(m);
            }
            gamesTableView.getSelectionModel().clearSelection();
        }
    }
}