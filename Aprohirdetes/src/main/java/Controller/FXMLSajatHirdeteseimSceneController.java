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
        try (JPAFelhasznalokDAO fDAO = new JPAFelhasznalokDAO();)
        {
            List<Felhasznalok> felhasznalokDataQuery = fDAO.getFelhasznalok();
            
            for (Felhasznalok felhasznalo : felhasznalokDataQuery) 
            {
                if (felhasznalo.getId() == bejelentkezoID)
                {
                    udvozloLabel.setText("Üdvözlöm, " + felhasznalo.getNev() + "!");
                    
                    break;
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
            
            ObservableList<Hirdetesek> hirdeteseimTableData = FXCollections.observableArrayList();
            
            for (Hirdetesek hirdetes : hirdetesekDataQuery) 
            {
                if (hirdetes.getElado() == bejelentkezoID)
                {
                    hirdeteseimTableData.add(hirdetes);
                }
            }
            
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
            
            modositasHirdetesekColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            
            modositasHirdetesekColumn.setCellFactory(param -> new TableCell<Hirdetesek, Hirdetesek>() 
            {
                private final Button button = new Button("Módosítás");
                                   
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
                        event -> System.out.println("Módosítás...")
                    );
                }
            });
            
            torlesHirdetesekColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            
            torlesHirdetesekColumn.setCellFactory(param -> new TableCell<Hirdetesek, Hirdetesek>() 
            {
                private final Button button = new Button("Törlés");
                                   
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
                            event -> 
                            {
                                hirdeteseimTableData.remove(hirdetes);
                                torles(hirdetes);
                            }
                    );
                }
            });
                
            hirdeteseimTable.setItems(hirdeteseimTableData);
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.toString());
        }
    }
    
    private void torles(Hirdetesek hirdetes) 
    {
        System.out.println("Hirdetes ID: " + hirdetes.getId() + "Hirdetes nev: " + hirdetes.getNev());
        
        try (JPAHirdetesekDAO hDAO = new JPAHirdetesekDAO();)
        {
            //java.lang.IllegalArgumentException: Removing a detached instance Model.Hirdetesek#77 -> ha nem így törlöm ki
            List<Hirdetesek> hirdetesekDataQuery = hDAO.getHirdetesek();
            
            for (Hirdetesek torlendoHirdetes : hirdetesekDataQuery) 
            {
                if (torlendoHirdetes.getId() == hirdetes.getId())
                {
                    hDAO.deleteHirdetes(torlendoHirdetes);
                }
            }
        }
        catch (Exception ex) 
        {
            System.out.println(ex.toString());
        }
    }

    
    @FXML
    private void onBeallitasokButton() {
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
