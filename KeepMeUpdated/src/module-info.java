module KeepMeUpdated 
{
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.web;
	requires java.sql;
	
	//Dämlicherweise müssen hier alle Pakete aufgelistet werden, die JavaFX enthalten, sowohl in exports, als auch in opens...
	exports MainWindow;
	exports MainWindow.mediaPanes;
	exports Hinzufuegen;
	exports Suche;
	exports Wishlist;
	
	opens MainWindow;
	opens MainWindow.mediaPanes;
	opens Hinzufuegen;
	opens Suche;
	opens Wishlist;
}