package keepMeUpdatedTools;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Zum Setzen des Icons des Programms
 * @author Hanna
 */

public class KeepMeUpdatedTools {

    /**
     * Eine Konstante, die den Pfad zu dem Icon angibt, das unser Programm Icon sein soll
     * Ursprünglich sollte "Icon.png" verwendet werden, allerdings war es zu detailliert und schwer zu erkennen.
     * Daher wurde "Icon2.png" verwendet. Da Milana sich Mühe damit gegeben hat und wir sie gerne beide zeigen
     * würden, finden Sie beide Icons links im Package keepMeUpdatedTools.
     */
    private static final String ICON_PFAD = "Icon2.png";
    /**
     * Die Methode, die das Icon setzt.
     * Wird überall, wo eine Stage angelegt wird aufgerufen mit "KeepMeUpdatedTools.stageIconSetzen([stage]);"
     * @param stage die Stage die gerade angelegt wird und das Icon erhalten soll.
     */
    public static void stageIconSetzen(Stage stage)
    {
    	try {
    		stage.getIcons().add(new Image(KeepMeUpdatedTools.class.getResourceAsStream(ICON_PFAD)));
    	}
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }

}
