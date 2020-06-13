package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Hoerspiel extends Medium
{
    private String folge;
    private String altersgruppe;
    public Hoerspiel(int ID,
                    String titel,
                    String untertitel,
                    String folge,
                    String zusatzinformationen,
                    String altersgruppe,
                    String standort,
                    String link)
    {
        super(ID, titel, untertitel, zusatzinformationen, standort, link);
        this.folge = folge;
        this.altersgruppe = altersgruppe;
    }
    
    @Override
    public LinkedHashMap<String, String> dbSchluesselWerte()
    {
        LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
        lhm.put("Typ", "Hörspiele");
        lhm.put("Folge", getFolge());
        lhm.put("Altersgruppe", getAltersgruppe());
        return lhm;
    }

    public String getFolge()
    {
        return folge;
    }

    public void setFolge(String folge)
    {
        this.folge = folge;
    }

    public String getAltersgruppe()
    {
        return altersgruppe;
    }

    public void setAltersgruppe(String altersgruppe)
    {
        this.altersgruppe = altersgruppe;
    }
    @Override
    public String getTabellenTitel()
    {
        return "Hörspiele";
    }
}
