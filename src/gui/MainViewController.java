package gui;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
        System.out.println("Department");
    }
    
     @FXML
    public void AboutAction(){
        System.out.println("About");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
}
