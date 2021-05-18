package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FXMLLoginSceneController implements Initializable 
{
    @FXML
    private TextField emailBejelentkezesTextbox;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField emailRegisztracioTextbox;

    @FXML
    private TextField nevTextbox;

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
    private PasswordField jelszoBejelentkezesTextbox;

    @FXML
    private PasswordField jelszoRegisztracioTextbox;

    @FXML
    private PasswordField megerositoJelszoTextbox;

    @FXML
    private ChoiceBox<String> jogosultsagDropdown;

    @FXML
    private TextField telefonszamTextbox;

    @FXML
    private Label telefonszamRegisztracioWarningLabel;
    
    private ObservableList<String> jogosultsagList = FXCollections.observableArrayList("Felhasznalo", "Admin");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        jogosultsagDropdown.setValue(jogosultsagList.get(0));
        jogosultsagDropdown.setItems(jogosultsagList);
        
        bejelentkezesWarningInitialize();
        regisztracioWarningInitialize();
    }
    
    private void bejelentkezesWarningInitialize() 
    {
        emailBejelentkezesWarningLabel.setVisible(false);
        jelszoBejelentkezesWarningLabel.setVisible(false);
    }
    
    private void regisztracioWarningInitialize()
    {
        emailRegisztracioWarningLabel.setVisible(false);
        jelszoRegisztracioWarningLabel.setVisible(false);
        megerositojelszoRegisztracioWarningLabel.setVisible(false);
        nevRegisztracioWarningLabel.setVisible(false);
        telefonszamRegisztracioWarningLabel.setVisible(false);
    }
    
    @FXML
    private void onRegisztracioButton() 
    {
        
    }
    
    @FXML
    private void onLoginButton() 
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
    
    private boolean bejelentkezesKitoltesEllenorzes() 
    {
        boolean mindenHelyesenKitoltve = true;
        
        String emailRegex = "/^(([^<>()[\\]\\\\.,;:\\s@\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(emailBejelentkezesTextbox.getText());
        
        if (emailBejelentkezesTextbox.getText().isBlank())
        {
            emailBejelentkezesWarningLabel.setVisible(true);
            emailBejelentkezesWarningLabel.setText("Nem adta meg az email címet!");
            mindenHelyesenKitoltve = false;
        }
        else if (!emailMatcher.matches())
        {
            emailBejelentkezesWarningLabel.setVisible(true);
            emailBejelentkezesWarningLabel.setText("Hibás email cím formátum!");
            mindenHelyesenKitoltve = false;
        }
        
        if (jelszoBejelentkezesTextbox.getText().isBlank())
        {
            jelszoBejelentkezesWarningLabel.setVisible(true);
            jelszoBejelentkezesWarningLabel.setText("Nem adta meg a jelszót!");
            mindenHelyesenKitoltve = false;
        }
        
        return mindenHelyesenKitoltve;
    }
    
    private boolean regisztracioKitoltesEllenorzes() 
    {
        boolean mindenHelyesenKitoltve = true;
        
        String emailRegex = "/^(([^<>()[\\]\\\\.,;:\\s@\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(emailBejelentkezesTextbox.getText());
        
        if (emailRegisztracioTextbox.getText().isBlank())
        {
            emailRegisztracioWarningLabel.setVisible(true);
            emailRegisztracioWarningLabel.setText("Nem adta meg az email címet!");
            mindenHelyesenKitoltve = false;
        }
        else if (!emailMatcher.matches())
        {
            emailRegisztracioWarningLabel.setVisible(true);
            emailRegisztracioWarningLabel.setText("Hibás email cím formátum!");
            mindenHelyesenKitoltve = false;
        }
        
        if (jelszoRegisztracioTextbox.getText().isBlank())
        {
            jelszoRegisztracioWarningLabel.setVisible(true);
            jelszoRegisztracioWarningLabel.setText("Nem adta meg a jelszót!");
            mindenHelyesenKitoltve = false;
        }
        
        if (megerositoJelszoTextbox.getText().equals(jelszoRegisztracioTextbox.getText()))
        {
            megerositojelszoRegisztracioWarningLabel.setVisible(true);
            megerositojelszoRegisztracioWarningLabel.setText("A jelszavaknak meg kell egyezniük!");
            mindenHelyesenKitoltve = false;
        }
        
        if (nevTextbox.getText().isBlank())
        {
            nevRegisztracioWarningLabel.setVisible(true);
            nevRegisztracioWarningLabel.setText("Nem adta meg a nevét!");
            mindenHelyesenKitoltve = false;
        }
        
        String telefonszamRegex = "^(20|30|70){1}[0-9]{7}$";
        Pattern telefonszamPattern = Pattern.compile(telefonszamRegex);
        Matcher telefonszamMatcher = telefonszamPattern.matcher(telefonszamTextbox.getText());
        
        if (telefonszamTextbox.getText().isBlank())
        {
            telefonszamRegisztracioWarningLabel.setVisible(true);
            telefonszamRegisztracioWarningLabel.setText("Nem adta meg a telefonszámát!");
            mindenHelyesenKitoltve = false;
        }
        else if (!telefonszamMatcher.matches())
        {
            telefonszamRegisztracioWarningLabel.setVisible(true);
            telefonszamRegisztracioWarningLabel.setText("Hibás telefonszám formátum!");
            mindenHelyesenKitoltve = false;
        }
        
        return mindenHelyesenKitoltve;
    }
    
    @FXML
    private void onKilepesButton() {
        Platform.exit();
    }
}
