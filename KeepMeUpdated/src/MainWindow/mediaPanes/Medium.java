package MainWindow.mediaPanes;

import java.util.LinkedHashMap;

import datenhaltung.DatenbankEintrag;

/**
 * Oberklasse für alle Medienobjekte, namentlich: Film, Serie, Buch, Zeitschrift, Musik, Hoerspiel, Spiel
 * @author Anika
 */
public abstract class Medium implements DatenbankEintrag {

    /**
     * Einmaliger Schlüssel, der das Addressieren in der Datenbank erlaubt.
     * Sollte kleiner 0 sein, wenn das Objekt noch nicht in der Datenbank gespeichert ist.
     */
    protected int ID;
    protected String titel;
    protected String untertitel;
    protected String zusatzinformationen;
    protected String standort;
    protected String link;

    /**
     * Erstellt ein Medien Objekt
     * @param ID Die ID des Mediums in der Datenbank, oder eine Zahl kleiner 0 wenn dieses noch nicht existiert.
     * @param titel Der Titel des Mediums.
     * @param untertitel Der Untertitel des Mediums, darf leer gelassen werden.
     * @param zusatzinformationen Zusatzinformationen über das Medium, darf leer gelassen werden.
     * @param standort Der Standort des Mediums
     * @param link Der Link des Mediums, wenn es in der Wunschliste ist, oder leer/null wenn nicht.
     */
    public Medium(int ID, String titel, String untertitel, String zusatzinformationen, String standort, String link) {
        this.ID = ID;
        this.titel = titel;
        this.untertitel = untertitel;
        this.zusatzinformationen = zusatzinformationen;
        this.standort = standort;
        this.link = link;
    }

    /**
     * Mit dem Aufruf der Methode werden die benötigten Schlüssel-Werte zum Abspeichern in die Datenbank generiert.
     * @return Alle Schlüssel-Werte, kann auch leere Strings und Null enthalten, als einziges die ID wird evtl leer gelassen, wenn sie kleiner 1 ist. Das Filtern von leeren Einträgen geschieht in der Datenbank.
     */
    @Override
    public LinkedHashMap<String, String> dbSchluesselWerte() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        if(ID > 0)
            map.put("ID", "" + getID());

        map.put("Titel", getTitel());
        map.put("Untertitel", getUntertitel());
        map.put("Zusatzinformationen", getZusatzinformationen());
        map.put("Standort", getStandort());
        map.put("Link", getLink());

        return map;
    }

    @Override
    public String toString() {
        return String.format("%s %s: %s (%d)", getClass().getSimpleName(), getTitel(), getUntertitel(), getID());
    }

    /*----------------------------------------------------
    GETTER & SETTER*/

    /**
     * Gibt den Titel der Tabelle in der Datenbank an.
     * @return Den Namen der zugehörigen Tabelle in der Datenbank (z.B. Bücher), oder null für Medium selber.
     */
    public abstract String getTabellenTitel();
    public String getTitel()
    {
        return titel;
    }
    public String getUntertitel()
    {
        return untertitel;
    }
    public String getLink()
    {
        return link;
    }
    public String getZusatzinformationen()
    {
        return zusatzinformationen;
    }
    public String getStandort()
    {
        return standort;
    }

    public int getID()
    {
        return ID;
    }
    public void setTitel(String titel)
    {
        this.titel = titel;
    }
    public void setUntertitel(String untertitel)
    {
        this.untertitel = untertitel;
    }
    public void setLink(String link)
    {
        this.link = link;
    }
    public void setZusatzinformationen(String zusatzinformationen)
    {
        this.zusatzinformationen = zusatzinformationen;
    }
    public void setStandort(String standort)
    {
        this.standort = standort;
    }
}