package Newslist;

import MainWindow.Main;
import MainWindow.mediaPanes.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Externe Klasse, die immer jeweils die 10 neusten Objekte jedes Types in einer
 * HashMap speichert.
 * NOTE
 * Sie war dazu gedacht, die neusten Objekte farblich anders anzuzeigen im jeweiligen Katalog, als Ersatz für das Icon
 * "Neu". Die Newslist funktioniert und wird immer, wenn sich etwas im Katalog ändert aktualisiert, allerdings hat die
 * Zeit für die Anbindung an die CSS nicht mehr gereicht.
 * Sie ist in der Dokumentation unter Punkt 4 nicht umgesetzte funktionale Anforderungen aufgeführt.
 * @author Lennart
 */

public class Newslist {

    /**
     * Die Newslist wird zu Beginn in MainController initialize aufgerufen und wenn ein Objekt in den Katalog hinzugefügt wurde.
     * Der Switch-Case unterscheidet, welche newslist angelegt/geupdated werden muss.
     * Am Beispiel Filme:
     * Ein Zähler f wird mit 0 initialisiert, und aus der Datenbank wird ein ResultSet der Tabelle Filme aus der Spalte ID in
     * absteigender Reihenfolge erzeugt. Danach wird eine HashMap mit einem Integer als Schlüssel und einem Film als Value angelegt.
     * Das Folgende wird solange gemacht, bis es nichts Weiteres im RS gibt, oder f >= 10 ist.
     * Wenn das Objekt der Datenbank ein Film ist (Typ-Spalte = Filme) und in der Link-Spalte nichts drin ist, wird ein neuer Film
     * angelegt, der dann mit dem aktuellen Schlüssel f in die HashMap gespeichert wird. Danach wird f eins hochgezählt.
     * Die Newslist wird ausgegeben, aber auskommentierbar.
     * @param str Um welchen Typ es sich handelt, sodass die Newslist nicht immer vollständig neu gefüllt werden muss, sondern
     *            selektiv danach, von welchem Typ etwas hinzugefügt wurde.
     */
    public static void newslist(String str) {
        switch (str) {
            case "Filme": {
                int f = 0;
                ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY ID DESC");
                HashMap<Integer, Film> newslistfilme = new HashMap<>();
                try {
                    while (rs.next() && f < 10) {
                        if (rs.getString("Typ").equals("Filme") && rs.getString("Link") == null)
                        {
                            Film film = new Film
                                    (
                                    rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Altersgruppe"), rs.getString("Medium"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Standort"), rs.getString("Franchise"), rs.getString("Link")
                                    );

                            newslistfilme.put(f, film);
                            f++;
                        }
                    }
                    System.out.println(newslistfilme);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            }
            case "Serien": {
                int f = 0;
                ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Filme ORDER BY ID DESC");
                HashMap<Integer, Serie> newslistserien = new HashMap<>();
                try {
                    while (rs.next() && f < 10) {
                        if (rs.getString("Typ").equals("Serien") && rs.getString("Link") == null) {
                            Serie serie = new Serie(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Season"), rs.getString("Zusatzinformationen"), rs.getString("Altersgruppe"),
                                    rs.getString("Standort"), rs.getString("Franchise"), rs.getString("Medium"), rs.getString("Link"));

                            newslistserien.put(f, serie);
                            f++;
                        }
                    }

                    System.out.println(newslistserien);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Hörspiele": {
                int f = 0;
                ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY ID DESC");
                HashMap<Integer, Hoerspiel> newslisthoerspiele = new HashMap<>();
                try {
                    while (rs.next() && f < 10) {
                        if (rs.getString("Typ").equals("Hörspiele") && rs.getString("Link") == null) {
                            Hoerspiel hoerspiel = new Hoerspiel(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Folge"), rs.getString("Zusatzinformationen"), rs.getString("Altersgruppe"),
                                    rs.getString("Standort"), rs.getString("Link"));

                            newslisthoerspiele.put(f, hoerspiel);
                            f++;
                        }
                    }
                    System.out.println(newslisthoerspiele);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Musik": {
                int f = 0;
                ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Hörspiele ORDER BY ID DESC");
                HashMap<Integer, Musik> newslistmusik = new HashMap<>();
                try {
                    while (rs.next() && f < 10) {
                        if (rs.getString("Typ").equals("Musik") && rs.getString("Link") == null) {
                            Musik musik = new Musik(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Genre"), rs.getString("Franchise"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Standort"), rs.getString("Link"));

                            newslistmusik.put(f, musik);
                            f++;
                        }
                    }
                    System.out.println(newslistmusik);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Bücher": {
                int f = 0;
                ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY ID DESC");
                HashMap<Integer, Buch> newslistbuch = new HashMap<>();
                try {
                    while (rs.next() && f < 10) {
                        if (rs.getString("Typ").equals("Bücher") && rs.getString("Link") == null) {
                            Buch buch = new Buch(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Genre"), rs.getString("Standort"), rs.getString("Autor"),
                                    rs.getString("Zusatzinformationen"), rs.getString("Franchise"), rs.getString("Altersgruppe"),
                                    rs.getString("Link"));

                            newslistbuch.put(f, buch);
                            f++;
                        }
                    }
                    System.out.println(newslistbuch);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Zeitschriften": {
                int f = 0;
                ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Bücher ORDER BY ID DESC");
                HashMap<Integer, Zeitschrift> newslistzeitschrift = new HashMap<>();
                try {
                    while (rs.next() && f < 10) {
                        if (rs.getString("Typ").equals("Zeitschriften") && rs.getString("Link") == null) {
                            Zeitschrift zeitschrift = new Zeitschrift(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Herausgeber"), rs.getString("Ausgabe"), rs.getString("Zusatzinformationen"),
                                    rs.getString("Genre"), rs.getString("Standort"), rs.getString("Link"));

                            newslistzeitschrift.put(f, zeitschrift);
                            f++;
                        }
                    }
                    System.out.println(newslistzeitschrift);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Spiele": {
                int f = 0;
                ResultSet rs = Main.db.dbAbfrage("SELECT * FROM Spiele ORDER BY ID DESC");
                HashMap<Integer, Spiel> newslistzeitspiel = new HashMap<>();
                try {
                    while (rs.next() && f < 10) {
                        if (rs.getString("Link") == null) {
                            Spiel spiel = new Spiel(rs.getInt("ID"), rs.getString("Titel"), rs.getString("Untertitel"),
                                    rs.getString("Altersgruppe"), rs.getString("Zusatzinformationen"), rs.getString("Standort"),
                                    rs.getString("Franchise"), rs.getString("Plattform"), rs.getString("Link"));

                            newslistzeitspiel.put(f, spiel);
                            f++;
                        }
                    }
                    System.out.println(newslistzeitspiel);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
