package Wishlist;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Oberklasse der .fxml Controller, die eine Altersangabe und eine
 * Franchiseangabe haben. (Wishlist)
 * Erbt von WControllerAlter.
 * @author Hanna
 */

public class WControllerFranchise extends WControllerAlter{
    @FXML
    protected TextField wFranchiseTextField;

    void initialize() {
        super.initialize();
    }

    /*----------------------------------------------------
    PROMPT*/
    public void promptFranchise(String franchise) {
        if(franchise != null)
        {wFranchiseTextField.setPromptText(franchise);}
    }
}
