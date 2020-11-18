package gui;

import gui.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
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
import model.services.SellerService;
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
        loidView("/gui/SellerList.fxml", (SellerListController controller ) -> {
            controller.setSellerService(new SellerService());
            controller.updateTableView();
        });
    }
     
    @FXML
    public void DepartmentAction(){
        loidView("/gui/DepartmentList.fxml", (DepartmentListController controller ) -> {
            controller.setDepartmentSertvice(new DepartmentService());
            controller.updateTableView();
        });
    }
    
     @FXML
    public void AboutAction(){
         loidView("/gui/About.fxml", x -> {});
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    private synchronized <T> void loidView(String absoluteName, Consumer<T> initilizeAction){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
        VBox newVBox  = loader.load();
        
        Scene mainScene = Main.getMainScene();
        VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
        
        Node mainMenu = mainVBox.getChildren().get(0);
        mainVBox.getChildren().clear();
        mainVBox.getChildren().add(mainMenu);
        mainVBox.getChildren().addAll(newVBox.getChildren());
        
        T controller = loader.getController();
        initilizeAction.accept(controller);
        
    }catch(IOException e){
        Alerts.showAlert("IOException", "Error loader view", e.getMessage(), AlertType.ERROR);
    }
    }
    
}
