package Controller;

import Model.Hirdetesek;
import aprohirdetes.JPAHirdetesekDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

public class FXMLHirdetesFeladasSceneController implements Initializable 
{
    int kattintasokSzama = 1;
    
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
    
    @FXML
    void onHirdetesFeladasaButton() 
    {
        boolean mindenHelyesenKitoltve = true; //ha valami nincs helyesen kitoltve, akkor falsera vált
        
        mindenHelyesenKitoltve = kitoltesEllenorzes(mindenHelyesenKitoltve);
        
        if (mindenHelyesenKitoltve == true) //értékek beállítása
        {
            Hirdetesek hirdetes = new Hirdetesek();
            //leirasTextArea.getText().replaceAll("\n", System.getProperty("line.separator"));
            hirdetes.setNev(hirdetesneveTextField.getText());
            hirdetes.setLeiras(leirasTextArea.getText());
            hirdetes.setHely(varosTextField.getText());
            hirdetes.setDate(LocalDate.now());
            hirdetes.setAr(Integer.parseInt(arTextField.getText()));
                
            if (csomagkuldesCheckBox.isSelected())
            {
                hirdetes.setCsomagkuldes(Hirdetesek.Csomagkuldes.IGEN);
            }
            else {
                hirdetes.setCsomagkuldes(Hirdetesek.Csomagkuldes.NEM);
            }
            
            try (JPAHirdetesekDAO hDAO = new JPAHirdetesekDAO();) //try-with resources
            {
                hDAO.saveHirdetes(hirdetes);
                
                atiranyitasFoablak();
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }
        }
        else {
            mindenHelyesenKitoltve = true;
        }
    }

    @FXML
    void onMegsemButton() {
        atiranyitasFoablak();
    }
    
    private boolean kitoltesEllenorzes(boolean mindenHelyesenKitoltve) 
    {
        if (hirdetesneveTextField.getText().isBlank())
        {
            hirdetesnevWarningLabel.setVisible(true);
            mindenHelyesenKitoltve = false;
        }
        else {
            hirdetesnevWarningLabel.setVisible(false);
        }
        
        if (leirasTextArea.getText().isBlank())
        {
            leirasWarningLabel.setVisible(true);
            mindenHelyesenKitoltve = false;
        }
        else {
            leirasWarningLabel.setVisible(false);
        }
        
        String varosPattern = "^[a-z,A-z]{2,}";
        if (!varosTextField.getText().matches(varosPattern))
        {
            varosWarningLabel.setVisible(true);
            mindenHelyesenKitoltve = false;
        }
        else {
            varosWarningLabel.setVisible(false);
        }
        
        String arPattern = "^[1-9]{1}[0-9]{0,}";
        if (!arTextField.getText().matches(arPattern))
        {
            arWarningLabel.setVisible(true);
            mindenHelyesenKitoltve = false;
        }
        else {
            arWarningLabel.setVisible(false);
        }
        
        return mindenHelyesenKitoltve;
    }
    
    void atiranyitasFoablak()
    {
        try {
        Parent hirdetesFeladasRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLMainScene.fxml"));
        
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hirdetesnevWarningLabel.setVisible(false);
        leirasWarningLabel.setVisible(false);
        varosWarningLabel.setVisible(false);
        arWarningLabel.setVisible(false);
    }    
}
