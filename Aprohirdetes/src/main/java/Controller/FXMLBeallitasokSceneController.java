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
        warningInitialize();
        
        try (JPAFelhasznalokDAO fDAO = new JPAFelhasznalokDAO();)
        {
            List<Felhasznalok> felhasznalokDataQuery = fDAO.getFelhasznalok();
            
            for (Felhasznalok felhasznalo : felhasznalokDataQuery) 
            {
                if (felhasznalo.getId() == bejelentkezoID)
                {
                    udvozloLabel.setText("Üdvözlöm, " + felhasznalo.getNev() + "!");
                    
                    break;
                }
            }
        }
        catch (Exception ex) 
        {
            System.out.println(ex.toString());
        }
    }
    
    private void warningInitialize() 
    {
        jelszoWarningLabel.setVisible(false);
        megerositojelszoWarningLabel.setVisible(false);
        nevWarningLabel.setVisible(false);
        telefonszamWarningLabel.setVisible(false);
    }
    
    private boolean helyesKitoltes()
    {
        warningInitialize();
        
        boolean mindenHelyesenKitoltve = true;
        
        if (jelszoTextbox.getText().isBlank() == false)
        {
            if (!megerositoJelszoTextbox.getText().equals(jelszoTextbox.getText()))
            {
                megerositojelszoWarningLabel.setVisible(true);
                megerositojelszoWarningLabel.setText("A jelszavaknak meg kell egyezniük!");
                mindenHelyesenKitoltve = false;
            }
        }
        
        if (telefonszamTextbox.getText().isBlank() == false)
        {
            String telefonszamRegex = "^(20|30|70){1}[0-9]{7}$";
            if (!telefonszamTextbox.getText().matches(telefonszamRegex))
            {
                telefonszamWarningLabel.setVisible(true);
                telefonszamWarningLabel.setText("Hibás telefonszám formátum!");
                mindenHelyesenKitoltve = false;
            }
        }

        return mindenHelyesenKitoltve;
    }
    
    @FXML
    private void onAdatokmodositasaButton() 
    {
        if (helyesKitoltes())
        {
            try (JPAFelhasznalokDAO fDAO = new JPAFelhasznalokDAO();)
            {
                List<Felhasznalok> felhasznalokDataQuery = fDAO.getFelhasznalok();

                for (Felhasznalok felhasznalo : felhasznalokDataQuery) 
                {
                    if (felhasznalo.getId() == bejelentkezoID)
                    {
                        if (jelszoTextbox.getText().isBlank() == false)
                        {
                            felhasznalo.setSalt((PasswordHashing.generateSalt(jelszoTextbox.getText().length())).toString());
                            felhasznalo.setJelszo((PasswordHashing.hashPassword(jelszoTextbox.getText(), felhasznalo.getSalt())).get());
                        }
                        
                        if (nevTextbox.getText().isBlank() == false)
                        {
                            felhasznalo.setNev(nevTextbox.getText());
                        }
                        
                        if (telefonszamTextbox.getText().isBlank() == false)
                        {
                            felhasznalo.setTelefonszam(telefonszamTextbox.getText());
                        }

                        fDAO.updateFelhasznalo(felhasznalo);
                        
                        onFooldalButton();
                    }
                }
            }
            catch (Exception ex) 
            {
                System.out.println(ex.toString());
            }
        }
    }

    @FXML
    private void onBeallitasokButton() {

    }

    @FXML
    private void onFooldalButton() {
        try 
        {
            Parent mainFeladasRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLMainScene.fxml"));

            Scene loginScene = fooldalButton.getScene();
            Window window = loginScene.getWindow();
            
            Stage mainStage = (Stage) window;
            fooldalButton.getScene().setRoot(mainFeladasRoot);
            
            mainStage.setTitle("Apróhirdetés");
            mainStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onHirdeteseimButton() {
        try 
        {
            Parent sajatHirdeteseimRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLSajatHirdeteseimScene.fxml"));

            Scene mainScene = hirdeteseimButton.getScene();
            Window window = mainScene.getWindow();

            Stage sajatHirdeteseimStage = (Stage) window;
            hirdeteseimButton.getScene().setRoot(sajatHirdeteseimRoot);

            sajatHirdeteseimStage.setTitle("Bejelentkezés/Regisztráció");
            sajatHirdeteseimStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onKijelentkezesButton() {
        try 
        {
            bejelentkezoID = -1;
            Parent hirdetesFeladasRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLLoginScene.fxml"));

            Scene mainScene = kijelentkezesButton.getScene();
            Window window = mainScene.getWindow();

            Stage LoginStage = (Stage) window;
            kijelentkezesButton.getScene().setRoot(hirdetesFeladasRoot);

            LoginStage.setTitle("Bejelentkezés/Regisztráció");
            LoginStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onKilepesButton() 
    {
        Platform.exit();
    }

}
