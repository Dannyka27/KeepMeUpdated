package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Zeitschrift extends Medium
{
    private String ausgabe;
    private String herausgeber;
    private String genre;

    public Zeitschrift(int ID,
                       String titel,
                       String untertitel,
                       String herausgeber,
                       String ausgabe,
                       String zusatzinformationen,
                       String genre,
                       String standort,
                       String link)
    {
        super(ID, titel, untertitel, zusatzinformationen, standort, link);
        this.ausgabe = ausgabe;
        this.herausgeber = herausgeber;
        this.genre = genre;
    }

    @Override
    public LinkedHashMap<String, String> dbSchluesselWerte() {
        LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
        lhm.put("Typ", "Zeitschriften");
        lhm.put("Ausgabe", getAusgabe());
        lhm.put("Herausgeber", getHerausgeber());
        lhm.put("Genre", getGenre());
        return lhm;
    }

    /*----------------------------------------------------
    GETTER & SETTER*/
    @Override
    public String getTabellenTitel()
    {
        return "Bücher";
    }
    public String getGenre()
    {
        return genre;
    }
    public String getAusgabe()
    {
        return ausgabe;
    }
    public String getHerausgeber()
    {
        return herausgeber;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    public void setAusgabe(String ausgabe)
    {
        this.ausgabe = ausgabe;
    }
    public void setHerausgeber(String herausgeber)
    {
        this.herausgeber = herausgeber;
    }
}