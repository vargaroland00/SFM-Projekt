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
@FXML
    private TextField cimTextField;

 @FXML
    private Label cimWarningLabel;

@FXML
    private Button OKButton;

@FXML
    void onOKButton() {
    boolean mindenHelyesenKitoltve = true;

    mindenHelyesenKitoltve = kitoltesEllenorzes(mindenHelyesenKitoltve);
    if (mindenHelyesenKitoltve == true) //cím beállítása
        {
        atiranyitasFoablak();
    }
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
 private void cimWarningInitialize() 
    {
        cimWarningLabel.setVisible(false);
}
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        cimWarningInitialize();
    } 

  private boolean kitoltesEllenorzes(boolean mindenHelyesenKitoltve) 
    {
        if (cimTextField.getText().isBlank())
        {
            cimWarningLabel.setVisible(true);
            mindenHelyesenKitoltve = false;
        }
        else {
            cimWarningLabel.setVisible(false);
        }
}
}