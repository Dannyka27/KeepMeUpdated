package Hinzufuegen;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.*;

public class ControllerFranchise extends ControllerAlter{
    @FXML
    protected TextField hFranchiseTextField;

    void initialize()
    {
        super.initialize();
    }
    public void promptFranchise(String franchise)
    {
        if(franchise != null)
        {hFranchiseTextField.setPromptText(franchise);}
    }
}
