package Controller;

import Model.Hirdetesek;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class FXMLMainSceneController implements Initializable {
    
    @FXML
    private Button hirdetesFeladasaButton;
    
    @FXML
    private Button fooldalButton;

    @FXML
    private Button hirdeteseimButton;

    @FXML
    private Button beallitasokButton;
    
    @FXML
    void onKilepesButton() {
        Platform.exit();
    }
    
    @FXML
    void onHirdetesFeladasaButton(ActionEvent event) {
        try {
        Parent hirdetesFeladasRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLHirdetesFeladasScene.fxml"));
        
        Scene elozoScene = hirdetesFeladasaButton.getScene();
        Window window = elozoScene.getWindow();
        Stage hirdetesFeladasStage = (Stage) window;
        hirdetesFeladasaButton.getScene().setRoot(hirdetesFeladasRoot);
        hirdetesFeladasStage.setTitle("Hirdetés feladás");
        hirdetesFeladasStage.show();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    void onBeallitasokButton() 
    {
        try 
        {
            Parent beallitasokRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLBeallitasokScene.fxml"));

            Scene mainScene = beallitasokButton.getScene();
            Window window = mainScene.getWindow();

            Stage BeallitasokStage = (Stage) window;
            beallitasokButton.getScene().setRoot(beallitasokRoot);

            BeallitasokStage.setTitle("Bejelentkezés/Regisztráció");
            BeallitasokStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void onFooldalButton() {

    }
    
    @FXML
    void onHirdeteseimButton() {
        try 
        {
            Parent sajatHirdeteseimRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLSajatHirdeteseimScene.fxml"));

            Scene mainScene = hirdeteseimButton.getScene();
            Window window = mainScene.getWindow();

            Stage sajatHirdeteseimStage = (Stage) window;
            hirdeteseimButton.getScene().setRoot(sajatHirdeteseimRoot);

            sajatHirdeteseimStage.setTitle("Saját hirdetéseim");
            sajatHirdeteseimStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
