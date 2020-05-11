package sample;

import java.util.Arrays;

public class DatenbankTest
{
	public static Datenbank db = new Datenbank("C:\\Users\\clair\\OneDrive\\FH\\Semester 2\\Informatik 2\\Praktikum\\Medien.accdb");
	
	public static void testeDatenbank()
	{
		db.druckeTabellenTitel("Filme");
		System.out.println(Arrays.toString(db.holeSpalte("Filme", "Titel")));
	}
}
