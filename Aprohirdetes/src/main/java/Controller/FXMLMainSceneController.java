package Controller;

import Model.Hirdetesek;
import aprohirdetes.JPAHirdetesekDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

public class FXMLMainSceneController implements Initializable {
    
    @FXML
    private Button hirdetesFeladasaButton;
    
    @FXML
    private TableView<Hirdetesek> hirdetesekTable;
     
    @FXML
    private TableColumn<Hirdetesek, String> nevHirdetesekColumn;

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
        
        Scene elozoScene = hirdetesFeladasaButton.getScene();
        Window window = elozoScene.getWindow();
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
        System.out.println("Inicializalas...");
        try (JPAHirdetesekDAO hDAO = new JPAHirdetesekDAO();)
        {
            List<Hirdetesek> hirdetesekDataQuery = hDAO.getHirdetesek();
            System.out.println("Lefutott...");
            
            ObservableList<Hirdetesek> hirdetesekTableData = FXCollections.observableArrayList();
            hirdetesekTableData.addAll(hirdetesekDataQuery);
            
            nevHirdetesekColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
            helyHirdetesekColumn.setCellValueFactory(new PropertyValueFactory<>("hely"));
            feladasidejeHirdetesekColumn.setCellValueFactory(new PropertyValueFactory<>("feladasideje"));
            
            /*nevHirdetesekColumn.setCellFactory(new Callback<TableColumn<Hirdetesek,String>, TableCell<Hirdetesek,String>>() {
			@Override
			public TableCell<Hirdetesek, String> call( TableColumn<Hirdetesek, String> param) {
				final TableCell<Hirdetesek, String> cell = new TableCell<Hirdetesek, String>() {
					private Text text;
					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						//if (!isEmpty()) {
							text = new Text(item.toString());
							text.setWrappingWidth(400); // Setting the wrapping width to the Text
							setGraphic(text);
						//}
					}
                                        
                                        
				};
				return cell;
			}
                        
                        /*@Override
                        public TableCell<Hirdetesek, String> call( TableColumn<Hirdetesek, String> param) {
                            TableCell<Hirdetesek, String> cell = new TableCell<>();
                            Text text = new Text();
                            cell.setGraphic(text);
                            //text.wrappingWidthProperty().bind(cell.widthProperty());
                            text.setWrappingWidth(400); // Setting the wrapping width to the Text
                            //text.textProperty().bind(cell.itemProperty());
                            return cell ;
                        }
                        });*/
            
            hirdetesekTable.setItems(hirdetesekTableData);
        } 
        catch (Exception ex) {
            
            System.out.println(ex.toString());
            
            System.out.println("Hiba...");
        }
        
    }    
}
