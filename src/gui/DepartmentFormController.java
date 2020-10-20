/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.util.Constraints;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

/**
 *
 * @author PC
 */
public class DepartmentFormController implements Initializable{

    private Department entity;
    
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtId;
    @FXML
    private Label labelErrorName;
    
    @FXML
    private Button btSave;
    @FXML
    private Button btCancel;
    
    @FXML
    public void onBtSaveAction(){
            System.out.println("Save on");
    }
    @FXML
    public void onBtCancelAction(){
        System.out.println("Cancel on");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void initiliazeNodes(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }

    public void setDepartment(Department entity) {
        this.entity = entity;
        
    }

    public void updateFormData(){
        if(entity==null){
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(entity.getName());
        
    }

}
