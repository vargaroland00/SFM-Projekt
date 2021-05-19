package Controller;

import Model.Hirdetesek;
import aprohirdetes.JPAHirdetesekDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
        warningInitialize();
        adatokBetoltese();
    }
    
    private void warningInitialize()
    {
        hirdetesnevWarningLabel.setVisible(false);
        leirasWarningLabel.setVisible(false);
        varosWarningLabel.setVisible(false);
        arWarningLabel.setVisible(false);
    }
    
    private void adatokBetoltese()
    {
        hirdetesneveTextField.setText(aktualisHirdetes.getNev());
        leirasTextArea.setText(aktualisHirdetes.getLeiras());
        varosTextField.setText(aktualisHirdetes.getHely());
        arTextField.setText(Integer.toString(aktualisHirdetes.getAr()));
        
        if (aktualisHirdetes.getCsomagkuldes().toString().equals("IGEN"))
        {
            csomagkuldesCheckBox.setSelected(true);
        }
        else
        {
            csomagkuldesCheckBox.setSelected(false);
        }
    }
    
    private boolean kitoltesEllenorzes() 
    {
        boolean mindenHelyesenKitoltve = true;
        
        if (hirdetesneveTextField.getText().isBlank())
        {
            hirdetesnevWarningLabel.setVisible(true);
            mindenHelyesenKitoltve = false;
        }
        
        if (leirasTextArea.getText().isBlank())
        {
            leirasWarningLabel.setVisible(true);
            mindenHelyesenKitoltve = false;
        }

        if (varosTextField.getText().isBlank())
        {
            varosWarningLabel.setVisible(true);
            mindenHelyesenKitoltve = false;
        }
        
        String arPattern = "^[1-9]{1}[0-9]{0,}";
        if (!arTextField.getText().matches(arPattern))
        {
            arWarningLabel.setVisible(true);
            mindenHelyesenKitoltve = false;
        }
        
        return mindenHelyesenKitoltve;
    }
    
    @FXML
    private void onHirdetesFeladasaButton() 
    {
        if (kitoltesEllenorzes() == true)
        {
            try (JPAHirdetesekDAO hDAO = new JPAHirdetesekDAO();)
            {
                //java.lang.IllegalArgumentException: Removing a detached instance Model.Hirdetesek#77 -> ha nem így törlöm ki
                List<Hirdetesek> hirdetesekDataQuery = hDAO.getHirdetesek();

                for (Hirdetesek modositandoHirdetes : hirdetesekDataQuery) 
                {
                    if (modositandoHirdetes.getId() == aktualisHirdetes.getId())
                    {
                        modositandoHirdetes.setNev(hirdetesneveTextField.getText());
                        modositandoHirdetes.setLeiras(leirasTextArea.getText());
                        modositandoHirdetes.setHely(varosTextField.getText());
                        modositandoHirdetes.setAr(Integer.parseInt(arTextField.getText()));
                        
                        hDAO.updateHirdetes(modositandoHirdetes);
                        
                        atiranyitasFoablak();
                    }
                }
            }
            catch (Exception ex) 
            {
                System.out.println(ex.toString());
            }
        }
    }
    
    private void atiranyitasFoablak()
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
    private void onMegsemButton() {
        atiranyitasFoablak();
    }
}
