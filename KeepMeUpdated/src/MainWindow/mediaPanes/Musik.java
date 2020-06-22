package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Musik extends Medium
{
    private String genre;
    private String franchise;

    public Musik(int ID,
                 String titel,
                 String untertitel,
                 String genre,
                 String franchise,
                 String zusatzinformationen,
                 String standort,
                 String link)
    {
        super(ID, titel, untertitel, zusatzinformationen, standort, link);
        this.genre = genre;
        this.franchise = franchise;
    }

    @Override
    public LinkedHashMap<String, String> dbSchluesselWerte() {
        LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
        lhm.put("Typ", "Musik");
        lhm.put("Genre", getGenre());
        lhm.put("Franchise", getFranchise());
        return lhm;
    }

    /*----------------------------------------------------
    GETTER & SETTER*/
    @Override
    public String getTabellenTitel()
    {
        return "Hörspiele";
    }
    public String getFranchise()
    {
        return franchise;
    }
    public String getGenre()
    {
        return genre;
    }

    public void setFranchise(String franchise)
    {
        this.franchise = franchise;
    }
    public void setGenre(String genre)
    {
        this.genre = genre;
    }
}