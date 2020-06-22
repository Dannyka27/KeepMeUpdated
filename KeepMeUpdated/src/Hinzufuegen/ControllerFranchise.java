package Hinzufuegen;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Oberklasse der .fxml Controller, die eine Altersangabe und eine
 * Franchiseangabe haben. (Hinzufuegen)
 * Erbt von ControllerAlter.
 * @author Hanna
 */

public class ControllerFranchise extends ControllerAlter{
    @FXML
    protected TextField hFranchiseTextField;

    void initialize() {
        super.initialize();
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptFranchise(String franchise) {
        if(franchise != null)
        {hFranchiseTextField.setPromptText(franchise);}
    }
}
