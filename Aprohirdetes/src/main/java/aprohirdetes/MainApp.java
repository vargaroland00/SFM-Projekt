/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aprohirdetes;

import Controller.FXMLMainSceneController;
import Model.Hirdetesek;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXMLMainScene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Apróhirdetés");
        stage.setScene(scene);
        stage.show();
        
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
