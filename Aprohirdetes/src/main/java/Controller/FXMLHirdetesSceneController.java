/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        nameLabel.setText(model.getHirdetesek());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadHirdetes();
    }    
    
     @FXML
    private void onMegvasarolButton() {
        
    }
    
     @FXML
    private void onMegsemButton() {
        Platform.exit();
    }
}
