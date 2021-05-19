package Controller;

import static Controller.FXMLMainSceneController.bejelentkezoID;
import Model.Felhasznalok;
import aprohirdetes.JPAFelhasznalokDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.print.attribute.standard.Finishings;

public class FXMLBeallitasokSceneController implements Initializable
{

    @FXML
    private Button fooldalButton;

    @FXML
    private Button hirdeteseimButton;

    @FXML
    private Button beallitasokButton;

    @FXML
    private Label udvozloLabel;

    @FXML
    private Button kijelentkezesButton;
    
    @FXML
    private PasswordField jelszoTextbox;

    @FXML
    private PasswordField megerositoJelszoTextbox;

    @FXML
    private TextField nevTextbox;

    @FXML
    private TextField telefonszamTextbox;

    @FXML
    private Button adatokmodositasaButton;

    @FXML
    private Label jelszoWarningLabel;

    @FXML
    private Label megerositojelszoWarningLabel;

    @FXML
    private Label nevWarningLabel;

    @FXML
    private Label telefonszamWarningLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

    }
    
    private void warningInitialize() 
    {

    }
    
    private boolean helyesKitoltes()
    {

    }
    
    @FXML
    private void onAdatokmodositasaButton() 
    {
        
    }

    @FXML
    private void onBeallitasokButton() {

    }

    @FXML
    private void onFooldalButton() {
        
    }

    @FXML
    private void onHirdeteseimButton() {
        
    }

    @FXML
    private void onKijelentkezesButton() {

    }

    @FXML
    private void onKilepesButton() 
    {
        
    }

}
