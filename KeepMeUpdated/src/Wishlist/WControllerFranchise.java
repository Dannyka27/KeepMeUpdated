package Wishlist;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.*;

public class WControllerFranchise extends WControllerAlter{
    @FXML
	protected TextField wFranchiseTextField;

    //String getFranchise(){return hFranchiseTextField.getText();}  merken
    void initialize()
    {
        super.initialize();
        wFranchiseTextField.setOnKeyPressed(keyEvent ->
                {
                    if(keyEvent.getCode() == KeyCode.ENTER)
                    {
                        //Platzhalter für Code, wenn Enter gedrueckt wird, schreibt er den Inhalt des TextFields in die Konsole
                        System.out.println(wFranchiseTextField.getText());
                    }
                }
        );
    }
}
