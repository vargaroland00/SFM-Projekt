package aprohirdetes;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.h2.tools.Server;

public class MainApp extends Application{

    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXMLMainScene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Style/CSSMainScene.css");      
        
        stage.setTitle("Apróhirdetés");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    public static void main(String[] args) {
        try {
            startDatabase();
            System.out.println("Running...");
            launch(args);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
}
