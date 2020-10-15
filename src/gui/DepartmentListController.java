package gui;

import gui.util.Alerts;
import gui.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;
import workshop.javafx.jdbc.Main;

/**
 *
 * @author Rafu
 */
public class DepartmentListController implements Initializable{
    private DepartmentService service;
    
    @FXML
    private TableView<Department> tableDepartment;
    @FXML
    private TableColumn<Department, Integer> tableCId;
    @FXML
    private TableColumn<Department, String> tableCName;
    @FXML
    private Button btNew;
    @FXML
    private ObservableList<Department> obsList;
    
    @FXML
    public void OnBtnewAction(ActionEvent event){
        Stage parentStage = Utils.currentStage(event);
        createDialogForm("/gui/DepartmentForm.fxml", parentStage);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
        
    }
    
    public void setDepartmentSertvice(DepartmentService service){
        this.service = service;
    }

    private void initializeNodes() {
       tableCId.setCellValueFactory(new PropertyValueFactory<>("Id"));
       tableCName.setCellValueFactory(new PropertyValueFactory<>("name"));
       
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableDepartment.prefHeightProperty().bind(stage.heightProperty());
    }
    
    public void updateTableView(){
        if (service==null){
            throw new IllegalStateException("Service was null");
        }
        List<Department> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableDepartment.setItems(obsList);
    }
    
    private void createDialogForm(String absoluteName, Stage parentStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter department data");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        }catch (IOException e){
            Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
        }
    }
    
}
