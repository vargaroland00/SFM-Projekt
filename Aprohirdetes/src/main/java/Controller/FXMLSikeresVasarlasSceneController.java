package Controller;

import Model.Hirdetesek;
import aprohirdetes.JPAHirdetesekDAO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

public class FXMLSikeresVasarlasSceneController
{
    @FXML
        private TextField cimTextField;

     @FXML
        private Label cimWarningLabel;

    @FXML
        private Button OKButton;

    @FXML
    void onOKButton() 
    {

        try (JPAHirdetesekDAO hDAO = new JPAHirdetesekDAO();)
        {
            //java.lang.IllegalArgumentException: Removing a detached instance Model.Hirdetesek#77 -> ha nem így törlöm ki
            List<Hirdetesek> hirdetesekDataQuery = hDAO.getHirdetesek();
            
            for (Hirdetesek megvasarolvaHirdetes : hirdetesekDataQuery) 
            {
                if (megvasarolvaHirdetes.getId() == FXMLHirdetesSceneController.aktualisHirdetes.getId())
                {                   
                    megvasarolvaHirdetes.setMegvasarolva(true);
                    
                    hDAO.updateHirdetes(megvasarolvaHirdetes);
                    
                    atiranyitasFoablak();
                }
            }
        }
        catch (Exception ex) 
        {
            System.out.println(ex.toString());
        }
    }
    
    void atiranyitasFoablak()
    {
        try {
        Parent mainSceneRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLMainScene.fxml"));
        
        Scene SikeresVasarlasScene = OKButton.getScene();
        Window window = SikeresVasarlasScene.getWindow();
        
        Stage MainStage = (Stage) window;
        OKButton.getScene().setRoot(mainSceneRoot);
        
        MainStage.setTitle("Sikeres Vásárlás5");
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
   
}