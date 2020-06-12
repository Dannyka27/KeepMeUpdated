package datenhaltung;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

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
		try
		{
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM " + tabellenname + " ORDER BY Titel ASC"); // ASC = Sortieren
																										// von A-Z

			rs.last();
			int numRows = rs.getRow();
			rs.first();

			String[] contents = new String[numRows];
			int i = 0;

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
		try
		{
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

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
		try
		{
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

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
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + tabellenname + " ORDER BY Titel ASC"); // ASC = Sortieren
																										// von A-Z

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
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + tabellenname + " ORDER BY Titel ASC"); // ASC = Sortieren
																										// von A-Z, DESC
																										// sortieren von
																										// Z-A
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
					// System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");
			}
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @param m
	 * @param tabellenname
	 */
	public void mediumSpeichern(DatenbankEintrag m, String tabellenname)
	{
		Map<String, String> sw = m.dbSchluesselWerte();

		String schluesselI = "";
		String werteI = "";

		String paareU = "";

		String id = sw.get("ID");

		for (Map.Entry<String, String> e : sw.entrySet())
		{
			if (e.getValue() == null)
			{
				System.err.println("Warnung: Ein Schlüssel beim Speichern ist null (" + e.getKey() + ")!");
				continue;
			}

			if (e.getKey().length() < 1 || e.getValue().length() < 1)
				continue;

			if (schluesselI.length() > 0 && werteI.length() > 0 && paareU.length() > 0)
			{
				schluesselI += ",";
				werteI += ",";
				paareU += ",";
			}

			schluesselI += e.getKey();
			werteI += '\'' + e.getValue() + '\'';
			paareU += e.getKey() + "='" + e.getValue() + '\'';
		}

		int updated = -1;

		try
		{
			if (id != null && Integer.parseInt(id) > 0)
			{
				Statement statement = conn.createStatement();
				updated = statement.executeUpdate(
						String.format("UPDATE %s SET %s WHERE ID = %s", tabellenname, paareU, id)
				);
				
				System.out.printf("%d Datenbankeintrag bearbeitet.%n", updated);
			} 
			else
			{
				Statement statement = conn.createStatement();
				updated = statement.executeUpdate(
						String.format("INSERT INTO %s(%s) VALUES (%s)", tabellenname, schluesselI, werteI)
				);
				
				System.out.printf("%d Datenbankeintrag eingefügt.%n", updated);
			}
			
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public int eintragLoeschen(DatenbankEintrag de, String tabellenname)
	{
		try
		{
			System.out.println("Lösche eintrag: " + de.toString());

			Statement statement = conn.createStatement();
			return statement
					.executeUpdate("DELETE FROM " + tabellenname + " WHERE ID=" + de.dbSchluesselWerte().get("ID"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}
}