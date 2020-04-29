
public class DatenbankTest
{
	public static void main(String[] args)
	{
		Datenbank db = new Datenbank("C:\\Users\\clair\\OneDrive\\FH\\Semester 2\\Informatik 2\\Praktikum\\Medien.accdb");
		db.druckeTabelle("Filme");
	}
}
