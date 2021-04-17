package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLHirdetesFeladasSceneController implements Initializable {

    @FXML
    private TextField hirdetesneveTextField;

    @FXML
    private TextArea leirasTextArea;

    @FXML
    private TextField varosTextField;

    @FXML
    private CheckBox csomagkuldesCheckBox;

    @FXML
    private TextField arTextField;

    @FXML
    void onHirdetesFeladasaButton(ActionEvent event) {

    }

    @FXML
    void onMegsemButton(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
