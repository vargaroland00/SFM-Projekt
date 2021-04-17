package Controller;

import Model.Hirdetesek;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FXMLMainSceneController implements Initializable {
    
    private Hirdetesek model;
    
    public void setModel(Hirdetesek model) {
        this.model = model;
    }
    
    @FXML
    void onKilepesButton(ActionEvent event) {
        Platform.exit();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
