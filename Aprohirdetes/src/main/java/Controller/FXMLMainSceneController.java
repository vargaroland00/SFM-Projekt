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
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.Hyperlink;
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
    private Button fooldalButton;

    @FXML
    private Button hirdeteseimButton;

    @FXML
    private Button beallitasokButton;
    
    @FXML
    private Button kijelentkezesButton;
    
    @FXML
    private TableView<Hirdetesek> hirdetesekTable;
     
    @FXML
    private TableColumn<Hirdetesek, String> nevHirdetesekColumn;
    
    @FXML
    private TableColumn<Hirdetesek, String> arHirdetesekColumn;

    @FXML
    private TableColumn<Hirdetesek, String> helyHirdetesekColumn;

    @FXML
    private TableColumn<Hirdetesek, Hirdetesek> megtekintesHirdetesekColumn;
    
    @FXML
    void onKilepesButton() {
        Platform.exit();
    }
    
    @FXML
    void onHirdetesFeladasaButton(){
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
    
    @FXML
    void onBeallitasokButton() 
    {
        try 
        {
            Parent beallitasokRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLBeallitasokScene.fxml"));

            Scene mainScene = beallitasokButton.getScene();
            Window window = mainScene.getWindow();

            Stage BeallitasokStage = (Stage) window;
            beallitasokButton.getScene().setRoot(beallitasokRoot);

            BeallitasokStage.setTitle("Bejelentkezés/Regisztráció");
            BeallitasokStage.show();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void onFooldalButton() {

    }
    
    @FXML
    void onHirdeteseimButton() {
        try 
        {
            Parent sajatHirdeteseimRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLSajatHirdeteseimScene.fxml"));

            Scene mainScene = hirdeteseimButton.getScene();
            Window window = mainScene.getWindow();

            Stage sajatHirdeteseimStage = (Stage) window;
            hirdeteseimButton.getScene().setRoot(sajatHirdeteseimRoot);

            sajatHirdeteseimStage.setTitle("Saját hirdetéseim");
            sajatHirdeteseimStage.show();
        } 
        catch (IOException ex) 
        {
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
                    udvozloLabel.setText("Üdvözlöm, " + felhasznalo.getNev() + "!");
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
            
            megtekintesHirdetesekColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            
            megtekintesHirdetesekColumn.setCellFactory(param -> new TableCell<Hirdetesek, Hirdetesek>() 
            {
                private final Button button = new Button("Megtekintés");
                                   
                @Override
                protected void updateItem(Hirdetesek hirdetes, boolean empty) {
                    super.updateItem(hirdetes, empty);

                    if (hirdetes == null)
                    {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(button);
                    button.setOnAction(
                        event -> {FXMLHirdetesSceneController.aktualisHirdetes = hirdetes;
                                    atiranyitasFoablak();}
       
                    );
                }
            });
            
            hirdetesekTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            hirdetesekTable.setItems(hirdetesekTableData);
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.toString());
        }
    }
    
 
    void atiranyitasFoablak()
    {
        try {
        Parent mainSceneRoot = FXMLLoader.load(getClass().getResource("/FXML/FXMLHirdetesScene.fxml"));
        
        Scene Fooldal = fooldalButton.getScene();
        Window window = Fooldal.getWindow();
        
        Stage MainStage = (Stage) window;
        fooldalButton.getScene().setRoot(mainSceneRoot);
        
        MainStage.setTitle("Megtekintés");
        MainStage.show();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void onKijelentkezesButton() 
    {
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
    
}
