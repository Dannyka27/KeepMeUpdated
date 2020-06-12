package datenhaltung;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * Lade und Persistiere Medienobjekte in einer Microsoft Acces Datenbank via SQL
 * @author Anika
 */
public class Datenbank
{
	/** Verbindung zur Datenbank */
	private Connection conn;

	/**
	 * Verbindet sich mit der Datenbank am angegebenen Pfad.
	 * @param pfad Der Pfad auf der Festplatte oder vlt. auch Server?
	 */
	public Datenbank(File pfad)
	{
		this(pfad.getAbsolutePath());
	}

	/**
	 * Verbindet sich mit der Datenbank am angegebenen Pfad.
	 * @param pfad Der Pfad auf der Festplatte oder vlt. auch Server?
	 */
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

	/**
	 * Hole eine einzelne Spalte aus der Datenbank als String Array. Eher unschön, empfehle die Nutzung von {@link #dbAbfrage(String)}. 
	 * @param tabellenname Der Name der Tabelle in der SQL-Datenbank
	 * @param spaltenname Der Name der Spalte in der Tabelle
	 * @return Ein String Array mit allen Werten.
	 */
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

	/**
	 * Führe den angegebenen SQL Befehl aus. Hauptsächlich für entwicklungszwecke gedacht
	 * @param query Der SQL-Query String
	 * @return Bei Erfolg gibt die Datenbank true zurück, false sonst (siehe Java SQL Documentation)
	 */
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

	/**
	 * Momentan die Methode, um Medien aus der Datenbank auszulesen.
	 * @param query Der SQL-Query String.
	 * @return Das Ergebniss der Abfrage als Tabelle, um z.B. alle Medien mit einem Iterator auszulesen. 
	 */
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

	/**
	 * Gibt sämtliche Titel der Tabellenzeilen mitsamt Datentyp aus.
	 * @param tabellenname Der Name der Tabelle in der Datenbank
	 */
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

	/**
	 * Gibt die gesamte Tabelle auf der Konsole aus, eher unübersichtlich...
	 * @param tabellenname Der Name der Tabelle, die ausgegeben werden soll.
	 */
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
	 * Speichert die Schlüssel-Werte Paare die von der Klasse Medium und den Kindklassen generiert werden in die Datenbank.
	 * 
	 * Da sich der Befehl um eine Zeile zu bearbeiten von dem Befehl eine Zeile hinzufügen unterschreidet, wird geguckt ob
	 * das Medium eine ID hat die größer ist als Null, wenn ja wird davon ausgegangen, dass für das Medium bereits ein Eintrag
	 * existiert und die Änderungen an dem Medium werden in die Datenbank geschrieben. Wenn nein wird ein Neuer Eintrag in der
	 * Datenbank erzeugt.
	 * 
	 * Beim Abspeichern werden leere Strings oder Null behandelt als seien sie nicht vorhanden, sprich leere Strings
	 * werden nicht in die Datenbank gespeichert und überschreiben somit keine bereits vorhandenen Daten oder Null.
	 * @param m Das Medium, das in die Datenbank geschrieben werden soll. Die Schlüssel-Werte Pare generiert die Klasse mit dem Aufruf von {@link MainWindow.mediaPanes.Medium#dbSchluesselWerte()} generiert.
	 * @param tabellenname Der Name der Datenbank Tabelle, in die gespeichert werden soll. Die Methode {@link MainWindow.mediaPanes.Medium#getTabellenTitel()} liefert das gewünschte.
	 * @return Die Anzahl an Einträgen, die geändert wurde. Dieser sollte immer 1 betragen wenn alles glatt gegangen ist, bei anderen Zahlen ist ein Fehler aufgetreten. 
	 */
	public int mediumSpeichern(DatenbankEintrag m, String tabellenname)
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

			//Überspringe leere Schlüssel/Werte
			if (e.getKey().length() < 1 || e.getValue().length() < 1)
				continue;

			//Generiere String für Datenbankaufruf
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
			//Prüfe ob ID größer ist als 0, wenn ja Update Datenbankeintrag
			if (id != null && Integer.parseInt(id) > 0)
			{
				Statement statement = conn.createStatement();
				updated = statement.executeUpdate(
						String.format("UPDATE %s SET %s WHERE ID = %s", tabellenname, paareU, id)
				);
				
				System.out.printf("%d Datenbankeintrag bearbeitet.%n", updated);
			} 
			//Wenn Nein, füge neuen Eintrag ein.
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
		return updated;
	}

	/**
	 * Löscht einen Eintrag aus der Datenbank. Hierfür wird die ID vom Medium heran gezogen.
	 * @param de Das Medium, das aus der Datenbank gelöscht werden soll. 
	 * @param tabellenname Der Name der Datenbank Tabelle, in die gespeichert werden soll. Die Methode {@link MainWindow.mediaPanes.Medium#getTabellenTitel()} liefert das gewünschte.
	 * @return Die Anzahl an Einträgen, die gelöscht wurden. Dieser sollte immer 1 betragen wenn alles glatt gegangen ist, bei anderen Zahlen ist ein Fehler aufgetreten.
	 */
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