package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FXMLLoginSceneController 
{
    @FXML
    private Button LoginButton;
    
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
}
