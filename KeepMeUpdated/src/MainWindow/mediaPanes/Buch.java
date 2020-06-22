package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

public class Buch extends Medium
{
    private String genre;
    private String autor;
    private String franchise;
    private String altersgruppe;

    public Buch(int ID,
                String titel,
                String untertitel,
                String genre,
                String standort,
                String autor,
                String zusatzinformationen,
                String franchise,
                String altersgruppe,
                String link)
    {
        super(ID, titel, untertitel, zusatzinformationen, standort, link);
        this.genre = genre;
        this.autor = autor;
        this.franchise = franchise;
        this.altersgruppe = altersgruppe;
    }

    @Override
    public LinkedHashMap<String, String> dbSchluesselWerte() {
        LinkedHashMap<String, String> lhm = super.dbSchluesselWerte();
        lhm.put("Typ", "Bücher");
        lhm.put("Genre", getGenre());
        lhm.put("Autor", getAutor());
        lhm.put("Franchise", getFranchise());
        lhm.put("Altersgruppe", getAltersgruppe());
        return lhm;
    }

    /*----------------------------------------------------
    GETTER & SETTER*/
    @Override
    public String getTabellenTitel()
    {
        return "Bücher";
    }
    public String getFranchise()
    {
        return franchise;
    }
    public String getAltersgruppe()
    {
        return altersgruppe;
    }
    public String getGenre()
    {
        return genre;
    }
    public String getAutor()
    {
        return autor;
    }

    public void setFranchise(String franchise)
    {
        this.franchise = franchise;
    }
    public void setAltersgruppe(String altersgruppe)
    {
        this.altersgruppe = altersgruppe;
    }
    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    public void setAutor(String autor)
    {
        this.autor = autor;
    }
}