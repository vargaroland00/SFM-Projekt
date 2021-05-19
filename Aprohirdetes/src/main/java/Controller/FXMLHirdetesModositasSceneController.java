package Controller;

import Model.Hirdetesek;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    
    void atiranyitasFoablak()
    {
        try {
        Parent mainSceneRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLMainScene.fxml"));
        
        Scene hirdetesScene = hirdetesFeladasaButton.getScene();
        Window window = hirdetesScene.getWindow();
        
        Stage MainStage = (Stage) window;
        hirdetesFeladasaButton.getScene().setRoot(mainSceneRoot);
        
        MainStage.setTitle("Hirdetés feladás");
        MainStage.show();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void onMegsemButton() {
        atiranyitasFoablak();
    }
}
