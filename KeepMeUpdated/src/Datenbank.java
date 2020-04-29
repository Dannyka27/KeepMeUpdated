import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
			System.err.println("Fehler beim �ffnen der Access Datei");
			e.printStackTrace();
		}
	}

	public void druckeTabelle(String tabellenname) 
	{
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + tabellenname); // "SELECT * FROM " + tabellenname + " ORDER BY Titel ASC"
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