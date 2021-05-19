package Controller;

import Model.Hirdetesek;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLHirdetesModositasSceneController implements Initializable
{
    public static Hirdetesek aktualisHirdetes;
    
    @FXML
    private TextField hirdetesneveTextField;

    @FXML
    private Label hirdetesnevWarningLabel;

    @FXML
    private TextArea leirasTextArea;

    @FXML
    private Label leirasWarningLabel;

    @FXML
    private TextField varosTextField;

    @FXML
    private Label varosWarningLabel;

    @FXML
    private CheckBox csomagkuldesCheckBox;

    @FXML
    private TextField arTextField;

    @FXML
    private Label arWarningLabel;

    @FXML
    private Button hirdetesFeladasaButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
        
    }
    
    @FXML
    void onHirdetesFeladasaButton() {

    }

    @FXML
    void onMegsemButton() {

    }
}
