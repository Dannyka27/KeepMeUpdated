package sample;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

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
			System.err.println("Fehler beim öffnen der Access Datei");
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
			ResultSet rs = st.executeQuery("SELECT * FROM " + tabellenname + " ORDER BY Titel ASC"); //ASC = Sortieren von A-Z
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
					String columnValue = rs.getString(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");
			}
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
}
