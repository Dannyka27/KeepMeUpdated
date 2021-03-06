package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Serie extends Medium
{
    private String season;
    private String altersgruppe;
    private String franchise;
    private String medium;

    public Serie(int ID,
                 String titel,
                 String untertitel,
                 String season,
                 String zusatzinformationen,
                 String altersgruppe,
                 String standort,
                 String franchise,
                 String medium,
                 String link)
    {
        super(ID, titel, untertitel, zusatzinformationen, standort, link);
        this.season = season;
        this.altersgruppe = altersgruppe;
        this.franchise = franchise;
        this.medium = medium;
    }

    @Override
    public LinkedHashMap<String, String> dbSchluesselWerte() {
        LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
        lhm.put("Typ", "Serien");
        lhm.put("Season", getSeason());
        lhm.put("Altersgruppe", getAltersgruppe());
        lhm.put("Franchise", getFranchise());
        lhm.put("Medium", getMedium());
        return lhm;
    }

    /*----------------------------------------------------
    GETTER & SETTER*/
    @Override
    public String getTabellenTitel()
    {
        return "Filme";
    }
    public String getMedium()
    {
        return medium;
    }
    public String getFranchise()
    {
        return franchise;
    }
    public String getAltersgruppe()
    {
        return altersgruppe;
    }
    public String getSeason()
    {
        return season;
    }

    public void setMedium(String medium)
    {
        this.medium = medium;
    }
    public void setFranchise(String franchise)
    {
        this.franchise = franchise;
    }
    public void setAltersgruppe(String altersgruppe)
    {
        this.altersgruppe = altersgruppe;
    }
    public void setSeason(String season)
    {
        this.season = season;
    }
}