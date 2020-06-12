package MainWindow;

import java.util.Arrays;

import datenhaltung.Datenbank;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final boolean DEBUG = true;
	
	public static Datenbank db = new Datenbank(
			"C:\\Users\\clair\\Nextcloud\\FH\\Semester 2\\Informatik 2\\Praktikum\\Medien.accdb");

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//db.druckeTabellenTitel("Filme");

		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		primaryStage.setTitle("Medienkatalog");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		System.out.println("Starte Programm mit Argumenten: " + Arrays.toString(args));
		launch(args);
	}
}
