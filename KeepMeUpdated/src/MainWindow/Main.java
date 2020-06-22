package MainWindow;

import datenhaltung.Datenbank;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import keepMeUpdatedTools.KeepMeUpdatedTools;

public class Main extends Application {

    //Der Pfad müsste für Sie persönlich angepasst werden.
    public static Datenbank db = new Datenbank("C:\\Users\\clair\\Nextcloud\\FH\\Semester 2\\Informatik 2\\Praktikum\\Medien.accdb");

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Medienkatalog");
        KeepMeUpdatedTools.stageIconSetzen(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
