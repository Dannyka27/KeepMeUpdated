package Hinzufuegen;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.*;

public class ControllerFranchise extends ControllerAlter{
    @FXML
    protected TextField hFranchiseTextField;

    //String getFranchise(){return hFranchiseTextField.getText();}  merken
    void initialize()
    {
        super.initialize();
        hFranchiseTextField.setOnKeyPressed(keyEvent ->
        {
            if(keyEvent.getCode() == KeyCode.ENTER)
            {
                //Platzhalter für Code, wenn Enter gedrueckt wird, schreibt er den Inhalt des TextFields in die Konsole
                System.out.println(hFranchiseTextField.getText());
            }
        }
        );
    }
}
