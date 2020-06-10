package MainWindow;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Hinzufuegen.*;
import MainWindow.mediaPanes.*;
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

public class MainController {
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
    ObservableList<String> audioSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Hörspiel", "Musik", "Folge", "Genre", "Standort", "Altersgruppe");
    ObservableList<String> biblioSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Bücher", "Zeitschriften", "Autor", "Genre", "Franchise", "Standort", "Altersgruppe", "Herausgeber");
    ObservableList<String> gamesSortierenList = FXCollections.observableArrayList("A-Z", "Z-A", "Plattform", "Franchise", "Standort", "Altersgruppe");
    ObservableList<Film> wfilmlist = FXCollections.observableArrayList();
    ObservableList<Film> whoerspiellist = FXCollections.observableArrayList();
    ObservableList<Film> wbuchlist = FXCollections.observableArrayList();
    ObservableList<Film> wgamelist = FXCollections.observableArrayList();

    boolean debug = true;
    
    @FXML
    public void hinzufuegenOnAction(ActionEvent actionEvent) throws Exception {
            if(videothekTab.isSelected())
            {
                fensteroeffnen("/Hinzufuegen/HinzufuegenFilm.fxml", "Hinzufügen");
            }
             else if(audiothekTab.isSelected())
            {
                fensteroeffnen("/Hinzufuegen/HinzufuegenMusik.fxml", "Hinzufügen");
            }
             else if(bibliothekTab.isSelected())
            {
                fensteroeffnen("/Hinzufuegen/HinzufuegenBuch.fxml", "Hinzufügen");
            }
             else if(gamesTab.isSelected())
            {
                fensteroeffnen("/Hinzufuegen/HinzufuegenGame.fxml", "Hinzufügen");
            }
             else if(wishlistTab.isSelected()) {
                fensteroeffnen("/Wishlist/WishlistFilm.fxml", "Wunsch offenbaren");
            }
    }

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
        ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC");
        try
        {
            //Anlegen der TitledPanes, die jeweils eine Gridpane mit den Informationen aus der Datenbank enthalten
            while (rs.next())
            {
                /*Anlegen der GridPane mit Padding und Gaps;
                Zusätzlich Initialisierung eines Zählers a, der hochgezählt wird, wenn in der Datenbank Daten verfügbar sind,
                damit die GridPane lückenlos gefüllt wird*/
                GridPane l = new GridPane();
                l.setPadding(new Insets(10,10,10,20));
                l.setVgap(10);
                l.setHgap(5);
                int a = 0;
                if(rs.getString("Link") == null) {
                    if (rs.getString("Franchise") != null) {
                        Label tmpBez = new Label("Franchise:");
                        Label tmp = new Label(rs.getString("Franchise"));
                        l.add(tmpBez, 0, a); //der Zähler wird als Angabe der Zeilennummer genutzt
                        l.add(tmp, 1, a);
                        a++; //und anschließend eins hochgezählt
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
                        /*Jedes Element der Datenbank hat einen Standort, daher werden hier auch die Button für
                        bearbeiten und löschen angelegt, da sie so immer sichtbar sind, danach wird ihnen die OnAction-Methode
                        zugewiesen, die dieselbe ist wie aus dem ContextMenu heraus*/
						Button bearbeiten = new Button("Bearbeiten");
						bearbeiten.setOnAction(this::bearbeitenOnAction);
						Button loeschen = new Button("Löschen");
                        loeschen.setOnAction(this::loeschenOnAction);
                        //Labels und Button werden in die GridPane eingefügt
                        l.add(tmpBez, 0, a);
                        l.add(tmp, 1, a);
                        l.add(bearbeiten, 2, a);
                        l.add(loeschen, 3, a);
                    }
                     /*es wird geprüft, ob ein Untertitel existiert, dafür wird die Abfrage aus der Datenbank in einem
                     temporären String gespeichert. Wenn ein Untertitel existiert, wird der String mit ': ' vorne ergänzt.
                     Wenn nicht wird der String auf '' gesetzt.
                     Dann wird der TitledPane der Titel aus der Datenbank, der temporären String und die Gridpane hinzugefügt*/
                    String tempUntertitel = rs.getString("Untertitel");
                    if (tempUntertitel != null) {
                        tempUntertitel = ": " + tempUntertitel;
                    } else {
                        tempUntertitel = "";
                    }
                    videothekAccordion.getPanes().add(new TitledPane(rs.getString("Titel") + tempUntertitel, l));
                }
                else
                {
                    wfilmlist.add(new Film(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                            rs.getString("Altersgruppe"), rs.getString("Typ"), rs.getString("Zusatzinformationen"),
                            rs.getString("Standort"), rs.getString("Franchise"), rs.getString("Link")));

                    filmeTitelC.setCellValueFactory(new PropertyValueFactory<>("Titel"));
                    filmeUntertitelC.setCellValueFactory(new PropertyValueFactory<>("Untertitel"));
                    filmeLinkC.setCellValueFactory(new PropertyValueFactory<>("Link"));

                    filmeTableView.setItems(wfilmlist);
                }
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            if(debug) e.printStackTrace();
        }

        rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC");
        try
        {
            while (rs.next())
            {
                GridPane l = new GridPane();
                l.setPadding(new Insets(10,10,10,20));
                l.setVgap(10);
                l.setHgap(5);
                int a = 0;
                if(rs.getString("Link") == null) {
                    if (rs.getString("Franchise") != null) {
                        Label tmpBez = new Label("Franchise:");
                        Label tmp = new Label(rs.getString("Franchise"));
                        l.add(tmpBez, 0, a); //der Zähler wird als Angabe der Zeilennummer genutzt
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
                    if (tempUntertitel != null){
                        tempUntertitel = ": " + tempUntertitel;
                    } else {
                        tempUntertitel = "";
                    }
                    audiothekAccordion.getPanes().add(new TitledPane(rs.getString("Titel") + tempUntertitel, l));
                }
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            if(debug) e.printStackTrace();
        }

        rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC");
        try
        {
            while (rs.next())
            {
                GridPane l = new GridPane();
                l.setPadding(new Insets(10,10,10,20));
                l.setVgap(10);
                l.setHgap(5);
                int a = 0;
                if(rs.getString("Link") == null) {
                    if (rs.getString("Franchise") != null) {
                        Label tmpBez = new Label("Franchise:");
                        Label tmp = new Label(rs.getString("Franchise"));
                        l.add(tmpBez, 0, a); //der Zähler wird als Angabe der Zeilennummer genutzt
                        l.add(tmp, 1, a);
                        a++; //und anschließend eins hochgezählt
                    }
                    if (rs.getString("Genre") != null) {
                        Label tmpBez = new Label("Genre:");
                        Label tmp = new Label(rs.getString("Genre"));
                        l.add(tmpBez, 0, a); //der Zähler wird als Angabe der Zeilennummer genutzt
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

                    bibliothekAccordion.getPanes().add(new TitledPane(rs.getString("Titel") + tempUntertitel, l));
                }
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            if(debug) e.printStackTrace();
        }


        rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC");
        try
        {
            while (rs.next())
            {
                GridPane l = new GridPane();
                l.setPadding(new Insets(10,10,10,20));
                l.setVgap(10);
                l.setHgap(5);
                int a = 0;
                if(rs.getString("Link") == null) {
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
                    gamesAccordion.getPanes().add(new TitledPane(rs.getString("Titel") + tempUntertitel, l));
                }
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
           if(debug) e.printStackTrace();
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

    public void loeschenOnAction(ActionEvent actionEvent) {
    }

    public void bearbeitenOnAction(ActionEvent actionEvent) {
        /*Geöffnet wird, entsprechend dem ausgewählten Element, die passende FXML Datei, bei der die TextFields
        mit den Daten aus der Datenbank als PromptText gefüllt sind und die ChoiceBoxen als Default den Wert haben,
        der in der Datenbank hinterlegt ist*/
        if(videothekTab.isSelected())  //Es wird geprüft, welcher Tab ausgewählt ist
        {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY Titel ASC");  //passend dazu werden die Daten aus der Datenbank abgefragt
            String text = videothekAccordion.getExpandedPane().getText();  /*Die aufgeklappte Pane wird ermittelt. Da immer nur eine geöffnet sein kann, ist es die zu bearbeitende
            *Das was im Header der TitledPane steht, wird in 'text' gespeichert*/
            try {
                while (rs.next()) //die Datenbank wird durchlaufen
                {
                    String tempUntertitel = rs.getString("Untertitel");
                    /*Zu jedem Element wird ein temporärer String für den Untertitel angelegt, genau wie in der initialize Methode*/
                    if (tempUntertitel != null)
                        {
                        tempUntertitel = ": " + tempUntertitel;
                        }
                    else
                        {
                        tempUntertitel = "";
                        }

                    if (text.equals(rs.getString("Titel") + tempUntertitel))
                        /*Es wird überprüft, ob 'text' aus dem Header mit dem Titel + temporären String übereinstimmt,
                        wenn ja ist das Element in der Datenbank gefunden*/
                        {
                            if(rs.getString("Typ").equals("Filme"))//es wird unterschieden nach Film und Serie(siehe else)
                            {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenFilm.fxml"));//Die passende FXML wird geladen
                            Parent root = loader.load();
                            HinzufuegenFilmController hfilmcontroller = loader.getController(); //Ein Objekt des entsprechenden Controllers wird erzeugt, um auf die Methoden zugreifen zu können

                                //Die promptTexte und DefaultValues werden gesetzt -> unterscheidung, was passiere soll, wenn TextFields leer sind steht in der Methode
                            hfilmcontroller.promptTitel(rs.getString("Titel"));
                            hfilmcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hfilmcontroller.promptStandortBox(rs.getString("Standort"));
                            hfilmcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hfilmcontroller.promptAlterBox(rs.getString("Altersgruppe"));
                            hfilmcontroller.promptFranchise(rs.getString("Franchise"));
                            hfilmcontroller.promptMediumBox(rs.getString("Typ"));
                            hfilmcontroller.promptTyp(rs.getString("Medium"));

                            //Die Stage wird angelegt und die FXML hinzugefügt
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

                            hseriecontroller.promptTitel(rs.getString("Titel"));
                            hseriecontroller.promptUntertitel(rs.getString("Untertitel"));
                            hseriecontroller.promptStandortBox(rs.getString("Standort"));
                            hseriecontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hseriecontroller.promptAlterBox(rs.getString("Altersgruppe"));
                            hseriecontroller.promptFranchise(rs.getString("Franchise"));
                            hseriecontroller.promptMediumBox(rs.getString("Typ"));
                            hseriecontroller.promptSeasonBox(rs.getString("Season"));
                            hseriecontroller.promptTypBox(rs.getString("Medium"));

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
                }
            catch (SQLException | IOException e)
            {
            	System.err.println(e.getMessage());
            	if(debug) e.printStackTrace();
            }
        }
        if(audiothekTab.isSelected())
        {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY Titel ASC");
            String text = audiothekAccordion.getExpandedPane().getText();
            try {
                while (rs.next())
                {
                    String tempUntertitel = rs.getString("Untertitel");
                    if (tempUntertitel != null)
                    {
                        tempUntertitel = ": " + tempUntertitel;
                    }
                    else
                    {
                        tempUntertitel = "";
                    }

                    if (text.equals(rs.getString("Titel") + tempUntertitel))
                    {
                        if(rs.getString("Typ").equals("Hörspiele"))
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenHoerspiel.fxml"));
                            Parent root = loader.load();
                            HinzufuegenHoerspielController hhoerspielcontroller = loader.getController();

                            hhoerspielcontroller.promptTitel(rs.getString("Titel"));
                            hhoerspielcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hhoerspielcontroller.promptStandortBox(rs.getString("Standort"));
                            hhoerspielcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hhoerspielcontroller.promptAlterBox(rs.getString("Altersgruppe"));
                            hhoerspielcontroller.promptFolge(rs.getString("Folge"));
                            hhoerspielcontroller.promptMediumBox(rs.getString("Typ"));

                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Hörspiel bearbeiten");
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                            break;
                        }
                        else
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenMusik.fxml"));
                            Parent root = loader.load();
                            HinzufuegenMusikController hmusikcontroller = loader.getController();

                            hmusikcontroller.promptTitel(rs.getString("Titel"));
                            hmusikcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hmusikcontroller.promptStandortBox(rs.getString("Standort"));
                            hmusikcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hmusikcontroller.promptGenreBox(rs.getString("Genre"));
                            hmusikcontroller.promptMediumBox(rs.getString("Typ"));
                            hmusikcontroller.promptFranchise(rs.getString("Franchise"));

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
                }
            catch (SQLException | IOException e)
            {
            	System.err.println(e.getMessage());
            	if(debug) e.printStackTrace();
            }
        }
        if(bibliothekTab.isSelected())
        {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY Titel ASC");
            String text = bibliothekAccordion.getExpandedPane().getText();
            try {
                while (rs.next())
                {
                    String tempUntertitel = rs.getString("Untertitel");
                    if (tempUntertitel != null)
                    {
                        tempUntertitel = ": " + tempUntertitel;
                    }
                    else
                    {
                        tempUntertitel = "";
                    }

                    if (text.equals(rs.getString("Titel") + tempUntertitel))
                    {
                        if (rs.getString("Typ").equals("Bücher"))
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenBuch.fxml"));
                            Parent root = loader.load();
                            HinzufuegenBuchController hbuchcontroller = loader.getController();

                            hbuchcontroller.promptTitel(rs.getString("Titel"));
                            hbuchcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hbuchcontroller.promptStandortBox(rs.getString("Standort"));
                            hbuchcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hbuchcontroller.promptAlterBox(rs.getString("Altersgruppe"));
                            hbuchcontroller.promptFranchise(rs.getString("Franchise"));
                            hbuchcontroller.promptGenreBox(rs.getString("Genre"));
                            hbuchcontroller.promptAutor(rs.getString("Autor"));
                            hbuchcontroller.promptMediumBox(rs.getString("Typ"));

                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Buch bearbeiten");
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                            break;
                        }
                        else
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenZeitschrift.fxml"));
                            Parent root = loader.load();
                            HinzufuegenZeitschriftController hzeitschriftcontroller = loader.getController();

                            hzeitschriftcontroller.promptTitel(rs.getString("Titel"));
                            hzeitschriftcontroller.promptUntertitel(rs.getString("Untertitel"));
                            hzeitschriftcontroller.promptStandortBox(rs.getString("Standort"));
                            hzeitschriftcontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hzeitschriftcontroller.promptGenreBox(rs.getString("Genre"));
                            hzeitschriftcontroller.promptHerausgeber(rs.getString("Herausgeber"));
                            hzeitschriftcontroller.promptAusgabe(rs.getString("Ausgabe"));
                            hzeitschriftcontroller.promptMediumBox(rs.getString("Typ"));

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
                }
            catch (SQLException | IOException e)
            {
                System.err.println(e.getMessage());
                if(debug) e.printStackTrace();
            }
        }
        if(gamesTab.isSelected())
        {
            ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY Titel ASC");
            String text = gamesAccordion.getExpandedPane().getText();
            try {
                while (rs.next())
                {
                    String tempUntertitel = rs.getString("Untertitel");
                    if (tempUntertitel != null)
                    {
                        tempUntertitel = ": " + tempUntertitel;
                    }
                    else
                    {
                        tempUntertitel = "";
                    }

                    if (text.equals(rs.getString("Titel") + tempUntertitel))
                    {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Hinzufuegen/HinzufuegenGame.fxml"));
                            Parent root = loader.load();
                            HinzufuegenGameController hgamecontroller = loader.getController();

                            hgamecontroller.promptTitel(rs.getString("Titel"));
                            hgamecontroller.promptUntertitel(rs.getString("Untertitel"));
                            hgamecontroller.promptStandortBox(rs.getString("Standort"));
                            hgamecontroller.promptZusatzinfo(rs.getString("Zusatzinformationen"));
                            hgamecontroller.promptAlterBox(rs.getString("Altersgruppe"));
                            hgamecontroller.promptFranchise(rs.getString("Franchise"));
                            hgamecontroller.promptPlattformBox(rs.getString("Plattform"));
                            hgamecontroller.promptMediumBox(rs.getString("Typ"));

                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Game bearbeiten");
                            Scene scene = new Scene(root);
                            primaryStage.setScene(scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                            break;
                    }
                }
                }
                catch (SQLException | IOException e)
                {
                    System.err.println(e.getMessage());
                    if(debug) e.printStackTrace();
                }
        }
        
    }

    public void wloeschenOnAction(ActionEvent actionEvent) {
    }

    public void wbearbeitenOnAction(ActionEvent actionEvent) {
    }

    public void wwunschuebertragenOnAction(ActionEvent actionEvent) {
    }
}
