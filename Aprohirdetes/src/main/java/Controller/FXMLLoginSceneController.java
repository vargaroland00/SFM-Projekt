package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FXMLLoginSceneController implements Initializable 
{
    @FXML
    private Button LoginButton;

    @FXML
    private Button RegisztracioButton;

    @FXML
    private Label emailBejelentkezesWarningLabel;

    @FXML
    private Label jelszoBejelentkezesWarningLabel;

    @FXML
    private Label emailRegisztracioWarningLabel;

    @FXML
    private Label jelszoRegisztracioWarningLabel;

    @FXML
    private Label megerositojelszoRegisztracioWarningLabel;

    @FXML
    private Label nevRegisztracioWarningLabel;
    
    @FXML
    private ChoiceBox<String> jogosultsagDropdown;
    
    private ObservableList<String> jogosultsagList = FXCollections.observableArrayList("Felhasznalo", "Admin");
    
    @FXML
    private Label telefonszamRegisztracioWarningLabel;

    @FXML
    void onRegisztracioButton() 
    {
        
    }
    
    @FXML
    void onLoginButton() 
    {
        try 
        {
            Parent mainFeladasRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLMainScene.fxml"));

            Scene loginScene = LoginButton.getScene();
            Window window = loginScene.getWindow();
            
            Stage mainStage = (Stage) window;
            LoginButton.getScene().setRoot(mainFeladasRoot);
            
            mainStage.setTitle("Apróhirdetés");
            mainStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        jogosultsagDropdown.setValue(jogosultsagList.get(0));
        jogosultsagDropdown.setItems(jogosultsagList);
        
        warningInitialize();
    }
    
    public void warningInitialize() 
    {
        emailBejelentkezesWarningLabel.setVisible(false);
        jelszoBejelentkezesWarningLabel.setVisible(false);
        emailRegisztracioWarningLabel.setVisible(false);
        jelszoRegisztracioWarningLabel.setVisible(false);
        megerositojelszoRegisztracioWarningLabel.setVisible(false);
        nevRegisztracioWarningLabel.setVisible(false);
        telefonszamRegisztracioWarningLabel.setVisible(false);
    }
    
    @FXML
    void onKilepesButton() {
        Platform.exit();
    }
}
