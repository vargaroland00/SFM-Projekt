package Controller;

import static Controller.FXMLMainSceneController.bejelentkezoID;
import Model.Felhasznalok;
import Model.Hirdetesek;
import aprohirdetes.JPAFelhasznalokDAO;
import aprohirdetes.JPAHirdetesekDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FXMLSajatHirdeteseimSceneController implements Initializable{

    @FXML
    private Button fooldalButton;

    @FXML
    private Button hirdeteseimButton;

    @FXML
    private Button beallitasokButton;

    @FXML
    private Label udvozloLabel;

    @FXML
    private Button kijelentkezesButton;
    
    @FXML
    private TableView<Hirdetesek> hirdeteseimTable;
    
    @FXML
    private TableColumn<Hirdetesek, String> nevHirdetesekColumn;
    
    @FXML
    private TableColumn<Hirdetesek, String> arHirdetesekColumn;

    @FXML
    private TableColumn<Hirdetesek, String> helyHirdetesekColumn;

    @FXML
    private TableColumn<Hirdetesek, String> feladasidejeHirdetesekColumn;
    
    @FXML
    private TableColumn<Hirdetesek, String> megvasarolvaHirdetesekColumn;
    
    @FXML
    private TableColumn<Hirdetesek, Hirdetesek> modositasHirdetesekColumn;
    
    @FXML
    private TableColumn<Hirdetesek, Hirdetesek> torlesHirdetesekColumn;
    
    @FXML
    private Button modositasButton;

    @FXML
    private Button torlesButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }
    
    private void torles(Hirdetesek hirdetes) 
    {
        
    }

    
    @FXML
    private void onBeallitasokButton() {
        
    }

    @FXML
    private void onFooldalButton() {
        try 
        {
            Parent mainFeladasRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLMainScene.fxml"));

            Scene loginScene = fooldalButton.getScene();
            Window window = loginScene.getWindow();
            
            Stage mainStage = (Stage) window;
            fooldalButton.getScene().setRoot(mainFeladasRoot);
            
            mainStage.setTitle("Apróhirdetés");
            mainStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onHirdeteseimButton() {

    }

    @FXML
    private void onKijelentkezesButton() {
        try 
        {
            bejelentkezoID = -1;
            Parent hirdetesFeladasRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLLoginScene.fxml"));

            Scene mainScene = kijelentkezesButton.getScene();
            Window window = mainScene.getWindow();

            Stage LoginStage = (Stage) window;
            kijelentkezesButton.getScene().setRoot(hirdetesFeladasRoot);

            LoginStage.setTitle("Bejelentkezés/Regisztráció");
            LoginStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onKilepesButton() {
        Platform.exit();    
    }

    @FXML
    void onModositasButton() {

    }

    @FXML
    void onTorlesButton() {

    }
}
