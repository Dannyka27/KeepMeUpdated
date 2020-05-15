package sample;

import java.util.Arrays;

public class DatenbankTest
{
	public static Datenbank db = new Datenbank("C:\\Users\\clair\\OneDrive\\FH\\Semester 2\\Informatik 2\\Praktikum\\Medien.accdb");
	
	public static void testeDatenbank()
	{		
		//db.dbAusführen("UPDATE Filme SET Titel = 'In Time' WHERE ID = 236");
		//db.dbAusführen("UPDATE Filme SET Medium = 'Bluray' WHERE ID IN(3,22,41,52,66,84,108,152,166,189,213,236,289,308,315,323,342,358,369,373,388,390,393,401,402,426,427,439,441,443)");
		
		//System.out.println(Arrays.toString(db.holeSpalte("Filme", "Medium")));
		
		db.druckeTabellenTitel("Filme");
		db.druckeTabelle("Filme");
	}
}
