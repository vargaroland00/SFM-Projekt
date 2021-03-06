/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.Felhasznalok;
import Model.Hirdetesek;
import aprohirdetes.JPAFelhasznalokDAO;
import aprohirdetes.JPAHirdetesekDAO;
import Model.Hirdetesek;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author Omen
 */
public class FXMLHirdetesSceneController implements Initializable {
    
    public static Hirdetesek aktualisHirdetes;

    @FXML
    private Button megsemButton;

    @FXML
    private Button megvasarolButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label leirasLabel;

    @FXML
    private Label varosLabel;

    @FXML
    private Label arLabel;

    @FXML
    private Label kuldesLabel;

    @FXML
    private void LoadHirdetes()
    {
        nameLabel.setText(aktualisHirdetes.getNev());
        leirasLabel.setText(aktualisHirdetes.getLeiras());
        varosLabel.setText(aktualisHirdetes.getHely());
        arLabel.setText(""+aktualisHirdetes.getAr());
        kuldesLabel.setText(aktualisHirdetes.getCsomagkuldes().toString());     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadHirdetes();
    }    
    
     @FXML
    private void onMegvasarolButton() {
        try 
        {
            Parent mainMegvasarol = FXMLLoader.load(getClass().getResource("/FXML/FXMLSikeresVasarlas.fxml"));

            Scene HirdetesScene = megvasarolButton.getScene();
            Window window = HirdetesScene.getWindow();
            
            Stage mainStage = (Stage) window;
            megvasarolButton.getScene().setRoot(mainMegvasarol);
            
            mainStage.setTitle("Apr??hirdet??s");
            mainStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    
     @FXML
    private void onMegsemButton() {
        atiranyitasFoablak();
    }

 void atiranyitasFoablak()
    {
        try {
        Parent mainSceneRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLMainScene.fxml"));
        
        Scene mainScene = megsemButton.getScene();
        Window window = mainScene.getWindow();
        
        Stage MainStage = (Stage) window;
        megsemButton.getScene().setRoot(mainSceneRoot);
        
        MainStage.setTitle("Hirdet??s felad??s");
        MainStage.show();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
