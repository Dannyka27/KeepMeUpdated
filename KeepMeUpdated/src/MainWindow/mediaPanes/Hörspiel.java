package MainWindow.mediaPanes;

@SuppressWarnings("unused")
public class Hörspiel extends Medium
{
	private int folge;
	private String altersgruppe;
	public Hörspiel(int ID, 
			String titel, 
			String untertitel,
			int folge,
			String zusatzinformationen, 
			String altersgruppe,
			String standort, 
			String link)
	{
		super(ID, titel, untertitel, zusatzinformationen, standort, link);
		this.folge = folge;
		this.altersgruppe = altersgruppe;
		
		setzeInfos(String.format("Folge: %s%n" + "Altersgruppe: %s", folge, altersgruppe));
	}

}