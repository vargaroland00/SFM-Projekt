package Controller;

import Model.Felhasznalok;
import Model.Hirdetesek;
import aprohirdetes.JPAFelhasznalokDAO;
import aprohirdetes.JPAHirdetesekDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FXMLMainSceneController implements Initializable {
    
    public static int bejelentkezoID;
    
    @FXML
    private Label udvozloLabel;
    
    @FXML
    private Button hirdetesFeladasaButton;
    
    @FXML
    private TableView<Hirdetesek> hirdetesekTable;
     
    @FXML
    private TableColumn<Hirdetesek, String> nevHirdetesekColumn;
    
    @FXML
    private TableColumn<Hirdetesek, String> arHirdetesekColumn;

    @FXML
    private TableColumn<Hirdetesek, String> helyHirdetesekColumn;

    @FXML
    private TableColumn<Hirdetesek, String> feladasidejeHirdetesekColumn;
    
    @FXML
    void onKilepesButton() {
        Platform.exit();
    }
    
    @FXML
    void onHirdetesFeladasaButton(ActionEvent event) {
        try {
        Parent hirdetesFeladasRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLHirdetesFeladasScene.fxml"));
        
        Scene mainScene = hirdetesFeladasaButton.getScene();
        Window window = mainScene.getWindow();
        
        Stage hirdetesFeladasStage = (Stage) window;
        hirdetesFeladasaButton.getScene().setRoot(hirdetesFeladasRoot);
        
        hirdetesFeladasStage.setTitle("Hirdetés feladás");
        hirdetesFeladasStage.show();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try (JPAFelhasznalokDAO fDAO = new JPAFelhasznalokDAO();)
        {
            List<Felhasznalok> felhasznalokDataQuery = fDAO.getFelhasznalok();
            
            for (Felhasznalok felhasznalo : felhasznalokDataQuery) 
            {
                if (felhasznalo.getId() == bejelentkezoID)
                {
                    udvozloLabel.setText("Jó napot, " + felhasznalo.getNev() + "!");
                }
            }
        }
        catch (Exception ex) 
        {
            System.out.println(ex.toString());
        }
        
        try (JPAHirdetesekDAO hDAO = new JPAHirdetesekDAO();)
        {
            List<Hirdetesek> hirdetesekDataQuery = hDAO.getHirdetesek();
            
            ObservableList<Hirdetesek> hirdetesekTableData = FXCollections.observableArrayList();
            hirdetesekTableData.addAll(hirdetesekDataQuery);
            
            nevHirdetesekColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
            
            nevHirdetesekColumn.setCellFactory(tableColumn -> 
            {
                TableCell<Hirdetesek, String> tableCell = new TableCell<>();
                
                Text text = new Text();
                text.setTextAlignment(TextAlignment.CENTER);
                
                tableCell.setGraphic(text);
                tableCell.setPrefHeight(Control.USE_COMPUTED_SIZE);
                
                //text.wrappingWidthProperty().bind(nevHirdetesekColumn.widthProperty());
                text.setWrappingWidth(nevHirdetesekColumn.getWidth() - 20);
                
                text.textProperty().bind(tableCell.itemProperty());
                
                return tableCell;
            });
            
            arHirdetesekColumn.setCellValueFactory(new PropertyValueFactory<>("ar"));
            helyHirdetesekColumn.setCellValueFactory(new PropertyValueFactory<>("hely"));
            feladasidejeHirdetesekColumn.setCellValueFactory(new PropertyValueFactory<>("feladasideje"));
            
            hirdetesekTable.setItems(hirdetesekTableData);
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.toString());
        }
    }    
}
