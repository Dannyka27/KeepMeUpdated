package datenhaltung;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import MainWindow.mediaPanes.Medium;

public class Datenbank
{
    private Connection conn;

    public Datenbank(File pfad)
    {
        this(pfad.getAbsolutePath());
    }

    public Datenbank(String pfad)
    {
        try
        {
            // UCanAccess als Datenbank Treiber laden
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // Verbindung zur Datenbank Datei herstellen
            conn = DriverManager.getConnection("jdbc:ucanaccess://" + pfad);

            System.out.println("Verbindung zur Datenbank hergestellt!");

        } catch (ClassNotFoundException e)
        {
            System.err.println("Konnte UCanAccess nicht laden!");
            e.printStackTrace();
        } catch (SQLException e)
        {
            System.err.println("Fehler beim Öffnen der Access Datei");
            e.printStackTrace();
        }
    }

    public String[] holeSpalte(String tabellenname, String spaltenname)
    {
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = st.executeQuery("SELECT * FROM " + tabellenname + " ORDER BY Titel ASC"); //ASC = Sortieren von A-Z

            rs.last();
            int numRows = rs.getRow();
            rs.first();

            String[] contents = new String[numRows];
            int i=0;

            while (rs.next())
            {
                contents[i++] = rs.getString(spaltenname);
            }

            return contents;
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public boolean dbAusfuehren(String query)
    {
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            return st.execute(query);
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public ResultSet dbAbfrage(String query)
    {
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public void druckeTabellenTitel(String tabellenname)
    {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + tabellenname + " ORDER BY Titel ASC"); //ASC = Sortieren von A-Z

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            for (int i = 1; i <= columnsNumber; i++)
            {
                if (i > 1)
                    System.out.print(",  ");
                System.out.print(rsmd.getColumnName(i) + "(" + rsmd.getColumnClassName(i) + ")");
            }

            System.out.println();
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public void druckeTabelle(String tabellenname)
    {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + tabellenname + " ORDER BY Titel ASC"); //ASC = Sortieren von A-Z, DESC sortieren von Z-A
            System.out.println(rs.toString());

            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("querying SELECT * FROM " + tabellenname);
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next())
            {
                for (int i = 1; i <= columnsNumber; i++)
                {
                    if (i > 1)
                        System.out.print(",  ");
                    System.out.printf("%-20s", rs.getString(i));
                    //System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public void mediumSpeichern(Medium m, String tabellenname)
    {
    	Map<String, String> sw = m.dbSchluesselWerte();
    	
    	String schluessel = "";
    	String werte = "";
    	
    	for(Map.Entry<String, String> e : sw.entrySet())
    	{
    		if(schluessel.length() > 0)
    			schluessel += ",";
    		if(werte.length() > 0)
    			werte += ",";
    		
    		schluessel += e.getKey();
    		werte += e.getValue();
    	}
    }
}