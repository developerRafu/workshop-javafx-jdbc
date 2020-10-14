package gui;

import gui.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import workshop.javafx.jdbc.Main;

/**
 *
 * @author Rafu
 */
public class MainViewController implements Initializable{
    @FXML
    private MenuItem Seller;
    @FXML
    private MenuItem Department;
    @FXML
    private MenuItem About;
    
    @FXML
    public void SellerAction(){
        System.out.println("Seller");
    }
     
    @FXML
    public void DepartmentAction(){
        loidView("/gui/DepartmentList.fxml");
    }
    
     @FXML
    public void AboutAction(){
         loidView("/gui/About.fxml");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    private synchronized void loidView(String absoluteName){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
        VBox newVBox  = loader.load();
        
        Scene mainScene = Main.getMainScene();
        VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
        
        Node mainMenu = mainVBox.getChildren().get(0);
        mainVBox.getChildren().clear();
        mainVBox.getChildren().add(mainMenu);
        mainVBox.getChildren().addAll(newVBox.getChildren());
        
    }catch(IOException e){
        Alerts.showAlert("IOException", "Error loader view", e.getMessage(), AlertType.ERROR);
    }
    }
    
    private synchronized void loidView2(String absoluteName){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
        VBox newVBox  = loader.load();
        
        Scene mainScene = Main.getMainScene();
        VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
        
        Node mainMenu = mainVBox.getChildren().get(0);
        mainVBox.getChildren().clear();
        mainVBox.getChildren().add(mainMenu);
        mainVBox.getChildren().addAll(newVBox.getChildren());
        
        DepartmentListController controller = loader.getController();
        controller.setDepartmentSertvice(new DepartmentService());
        controller.updateTableView();
        
    }catch(IOException e){
        Alerts.showAlert("IOException", "Error loader view", e.getMessage(), AlertType.ERROR);
    }
    }
    
}
