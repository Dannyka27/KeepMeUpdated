package bearbeitung;

import Hinzufuegen.*;
import MainWindow.mediaPanes.*;
import Wishlist.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import keepMeUpdatedTools.KeepMeUpdatedTools;

/**
 * Externe Klasse, die das Anzeigen der Bearbeitung steuert.
 * @author Hanna
 */

public class Bearbeitung {
    /**
     * Zunächst wird die FXML, die beim Aufruf der Methode übergeben wurde, geladen.
     * Danach wird abgefragt, von welcher Kindesklasse das übergebene Objekt eine Instanz ist.
     * Das Objekt Medium wird auf das entsprechende Objekt der Kindesklasse gecastet, sodass auf die Methoden
     * der Kindesklasse zugegriffen werden kann. Zudem wird ein Objekt des Controllers der geladenen
     * FXML erzeugt, um auf die Methoden des Controllers zugreifen zu können.
     * Die promptTexte und DefaultValues werden gesetzt -> Unterscheidung was passieren soll, wenn TextFields leer sind,
     * steht in den jeweiligen Methode.
     * Die Stage wird angelegt und die FXML hinzugefügt.
     * Die Methode wird mit den folgenden Parametern aufgerufen:
     * @param m Medium ist die Oberklasse der aller Objekte (Film, Serie, Hoerspiel, usw.)
     * @param typ Der Typ des Objektes wird übergeben, benötigt um den Titel der Stage passend zum Objekt zu setzten.
     * @param fxml Hier wird die zu ladende FXML übergeben.
     * */
    public static void bearbeitenoeffnen(Medium m, String typ, String fxml) {
        try{
            FXMLLoader loader = new FXMLLoader(Bearbeitung.class.getResource(fxml));
            Parent root = loader.load();
            if(m instanceof Film)
            {
                Film f = (Film) m;
                HinzufuegenFilmController hfilmcontroller = loader.getController();

                hfilmcontroller.promptTitel(f.getTitel());
                hfilmcontroller.promptUntertitel(f.getUntertitel());
                hfilmcontroller.promptStandortBox(f.getStandort());
                hfilmcontroller.promptZusatzinfo(f.getZusatzinformationen());
                hfilmcontroller.promptAlterBox(f.getAltersgruppe());
                hfilmcontroller.promptFranchise(f.getFranchise());
                hfilmcontroller.promptTypBox(f.getMedium());
                hfilmcontroller.setMedium(f);
            }
            else if (m instanceof Serie)
            {
                Serie s = (Serie) m;
                HinzufuegenSerieController hseriecontroller = loader.getController();

                hseriecontroller.promptTitel(s.getTitel());
                hseriecontroller.promptUntertitel(s.getUntertitel());
                hseriecontroller.promptStandortBox(s.getStandort());
                hseriecontroller.promptZusatzinfo(s.getZusatzinformationen());
                hseriecontroller.promptAlterBox(s.getAltersgruppe());
                hseriecontroller.promptFranchise(s.getFranchise());
                hseriecontroller.promptSeasonBox(s.getSeason());
                hseriecontroller.promptTypBox(s.getMedium());
                hseriecontroller.setMedium(s);
            }
            else  if (m instanceof Hoerspiel)
            {
                Hoerspiel h = (Hoerspiel) m;
                HinzufuegenHoerspielController hhoerspielcontroller = loader.getController();

                hhoerspielcontroller.promptTitel(h.getTitel());
                hhoerspielcontroller.promptUntertitel(h.getUntertitel());
                hhoerspielcontroller.promptStandortBox(h.getStandort());
                hhoerspielcontroller.promptZusatzinfo(h.getZusatzinformationen());
                hhoerspielcontroller.promptAlterBox(h.getAltersgruppe());
                hhoerspielcontroller.promptFolge(h.getFolge());
                hhoerspielcontroller.setMedium(h);
            }
            else if (m instanceof Musik)
            {
                Musik a = (Musik) m;
                HinzufuegenMusikController hmusikcontroller = loader.getController();

                hmusikcontroller.promptTitel(a.getTitel());
                hmusikcontroller.promptUntertitel(a.getUntertitel());
                hmusikcontroller.promptStandortBox(a.getStandort());
                hmusikcontroller.promptZusatzinfo(a.getZusatzinformationen());
                hmusikcontroller.promptFranchise(a.getFranchise());
                hmusikcontroller.promptGenreBox(a.getGenre());
                hmusikcontroller.setMedium(a);
            }
            else if (m instanceof Buch)
            {
                Buch b = (Buch) m;
                HinzufuegenBuchController hbuchcontroller = loader.getController();

                hbuchcontroller.promptTitel(b.getTitel());
                hbuchcontroller.promptUntertitel(b.getUntertitel());
                hbuchcontroller.promptStandortBox(b.getStandort());
                hbuchcontroller.promptZusatzinfo(b.getZusatzinformationen());
                hbuchcontroller.promptAlterBox(b.getAltersgruppe());
                hbuchcontroller.promptFranchise(b.getFranchise());
                hbuchcontroller.promptGenreBox(b.getGenre());
                hbuchcontroller.promptAutor(b.getAutor());
                hbuchcontroller.setMedium(b);
            }
            else if (m instanceof Zeitschrift)
            {
                Zeitschrift z = (Zeitschrift) m;
                HinzufuegenZeitschriftController hzeitschriftcontroller = loader.getController();

                hzeitschriftcontroller.promptTitel(z.getTitel());
                hzeitschriftcontroller.promptUntertitel(z.getUntertitel());
                hzeitschriftcontroller.promptStandortBox(z.getStandort());
                hzeitschriftcontroller.promptZusatzinfo(z.getZusatzinformationen());
                hzeitschriftcontroller.promptGenreBox(z.getGenre());
                hzeitschriftcontroller.promptHerausgeber(z.getHerausgeber());
                hzeitschriftcontroller.promptAusgabe(z.getAusgabe());
                hzeitschriftcontroller.setMedium(z);
            }
            else if (m instanceof Spiel)
            {
                Spiel g = (Spiel) m;
                HinzufuegenGameController hgamecontroller = loader.getController();

                hgamecontroller.promptTitel(g.getTitel());
                hgamecontroller.promptUntertitel(g.getUntertitel());
                hgamecontroller.promptStandortBox(g.getStandort());
                hgamecontroller.promptZusatzinfo(g.getZusatzinformationen());
                hgamecontroller.promptAlterBox(g.getAltersgruppe());
                hgamecontroller.promptFranchise(g.getFranchise());
                hgamecontroller.promptPlattformBox(g.getPlattform());
                hgamecontroller.setMedium(g);
            }

            Stage primaryStage = new Stage();
            primaryStage.setTitle(typ + " bearbeiten");
            KeepMeUpdatedTools.stageIconSetzen(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void wbearbeitenoeffnen(Medium m, String typ, String fxml) {
        try{
            FXMLLoader loader = new FXMLLoader(Bearbeitung.class.getResource(fxml));
            Parent root = loader.load();
            if(m instanceof Film)
            {
                Film f = (Film) m;
                WishlistFilmController wfilmcontroller = loader.getController();

                wfilmcontroller.promptTitel(f.getTitel());
                wfilmcontroller.promptUntertitel(f.getUntertitel());
                wfilmcontroller.promptStandortBox(f.getStandort());
                wfilmcontroller.promptZusatzinfo(f.getZusatzinformationen());
                wfilmcontroller.promptAlterBox(f.getAltersgruppe());
                wfilmcontroller.promptFranchise(f.getFranchise());
                wfilmcontroller.promptTypBox(f.getMedium());
                wfilmcontroller.promptLink(f.getLink());
                wfilmcontroller.setMedium(f);
            }
            else if (m instanceof Serie)
            {
                Serie s = (Serie) m;
                WishlistSerieController wseriecontroller = loader.getController();

                wseriecontroller.promptTitel(s.getTitel());
                wseriecontroller.promptUntertitel(s.getUntertitel());
                wseriecontroller.promptStandortBox(s.getStandort());
                wseriecontroller.promptZusatzinfo(s.getZusatzinformationen());
                wseriecontroller.promptAlterBox(s.getAltersgruppe());
                wseriecontroller.promptFranchise(s.getFranchise());
                wseriecontroller.promptSeasonBox(s.getSeason());
                wseriecontroller.promptTypBox(s.getMedium());
                wseriecontroller.promptLink(s.getLink());
                wseriecontroller.setMedium(s);
            }
            else  if (m instanceof Hoerspiel)
            {
                Hoerspiel h = (Hoerspiel) m;
                WishlistHoerspielController whoerspielcontroller = loader.getController();

                whoerspielcontroller.promptTitel(h.getTitel());
                whoerspielcontroller.promptUntertitel(h.getUntertitel());
                whoerspielcontroller.promptStandortBox(h.getStandort());
                whoerspielcontroller.promptZusatzinfo(h.getZusatzinformationen());
                whoerspielcontroller.promptAlterBox(h.getAltersgruppe());
                whoerspielcontroller.promptFolge(h.getFolge());
                whoerspielcontroller.promptLink(h.getLink());
                whoerspielcontroller.setMedium(h);
            }
            else if (m instanceof Musik)
            {
                Musik a = (Musik) m;
                WishlistMusikController wmusikcontroller = loader.getController();

                wmusikcontroller.promptTitel(a.getTitel());
                wmusikcontroller.promptUntertitel(a.getUntertitel());
                wmusikcontroller.promptStandortBox(a.getStandort());
                wmusikcontroller.promptZusatzinfo(a.getZusatzinformationen());
                wmusikcontroller.promptFranchise(a.getFranchise());
                wmusikcontroller.promptGenreBox(a.getGenre());
                wmusikcontroller.promptLink(a.getLink());
                wmusikcontroller.setMedium(a);
            }
            else if (m instanceof Buch)
            {
                Buch b = (Buch) m;
                WishlistBuchController wbuchcontroller = loader.getController();

                wbuchcontroller.promptTitel(b.getTitel());
                wbuchcontroller.promptUntertitel(b.getUntertitel());
                wbuchcontroller.promptStandortBox(b.getStandort());
                wbuchcontroller.promptZusatzinfo(b.getZusatzinformationen());
                wbuchcontroller.promptAlterBox(b.getAltersgruppe());
                wbuchcontroller.promptFranchise(b.getFranchise());
                wbuchcontroller.promptGenreBox(b.getGenre());
                wbuchcontroller.promptAutor(b.getAutor());
                wbuchcontroller.promptLink(b.getLink());
                wbuchcontroller.setMedium(b);
            }
            else if (m instanceof Zeitschrift)
            {
                Zeitschrift z = (Zeitschrift) m;
                WishlistZeitschriftController wzeitschriftcontroller = loader.getController();

                wzeitschriftcontroller.promptTitel(z.getTitel());
                wzeitschriftcontroller.promptUntertitel(z.getUntertitel());
                wzeitschriftcontroller.promptStandortBox(z.getStandort());
                wzeitschriftcontroller.promptZusatzinfo(z.getZusatzinformationen());
                wzeitschriftcontroller.promptGenreBox(z.getGenre());
                wzeitschriftcontroller.promptHerausgeber(z.getHerausgeber());
                wzeitschriftcontroller.promptAusgabe(z.getAusgabe());
                wzeitschriftcontroller.promptLink(z.getLink());
                wzeitschriftcontroller.setMedium(z);
            }
            else if (m instanceof Spiel)
            {
                Spiel g = (Spiel) m;
                WishlistGameController wgamecontroller = loader.getController();

                wgamecontroller.promptTitel(g.getTitel());
                wgamecontroller.promptUntertitel(g.getUntertitel());
                wgamecontroller.promptStandortBox(g.getStandort());
                wgamecontroller.promptZusatzinfo(g.getZusatzinformationen());
                wgamecontroller.promptAlterBox(g.getAltersgruppe());
                wgamecontroller.promptFranchise(g.getFranchise());
                wgamecontroller.promptPlattformBox(g.getPlattform());
                wgamecontroller.promptLink(g.getLink());
                wgamecontroller.setMedium(g);
            }

            Stage primaryStage = new Stage();
            primaryStage.setTitle(typ + " bearbeiten");
            KeepMeUpdatedTools.stageIconSetzen(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
