/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.xml.bind.ValidationException;
import model.entities.Department;
import model.entities.Seller;
import model.exceptions.ValException;
import model.services.DepartmentService;
import model.services.SellerService;

/**
 *
 * @author PC
 */
public class SellerFormController implements Initializable{

    private SellerService service;
    private Seller entity;
    
    private List<DataChangeListener> dataChangeListener = new ArrayList<>();
    
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
    
    public void subscribeDataChangeListener(DataChangeListener listener){
        dataChangeListener.add(listener);
    }
    
    @FXML
    public void onBtSaveAction(ActionEvent event){
        if(entity==null){
            throw new IllegalStateException("Entity was null");
        }
        if(service==null){
            throw new IllegalStateException("Service was null");
        }
        try{
          entity = getFormData();
          service.saveOrUpdate(entity);
          notifyDataChangeListeners();
          Utils.currentStage(event).close();
          
        }catch(ValException e){
            setErrorsMessages(e.getErros());
          
        }catch(DbException e){
            Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
        }
    }
    @FXML
    public void onBtCancelAction(ActionEvent event){
        Utils.currentStage(event).close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void initiliazeNodes(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }

    public void setSeller(Seller entity) {
        this.entity = entity;
        
    }
    public void setSellerService(SellerService service){
        this.service = service;
    }

    public void updateFormData(){
        if(entity==null){
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(entity.getName());
        
    }

    private Seller getFormData() {
       Seller obj = new Seller();
        ValException exception = new ValException("Validation error");
       obj.setId(Utils.tryParseToInt(txtId.getText()));
       
       if(txtName.getText()==null || txtName.equals("")){
           exception.addError("Name", "Field can't be empty");
       }
       obj.setName(txtName.getText());
       if(exception.getErros().size()>0){
           throw exception;
       }
       
       return obj;
    }

    private void notifyDataChangeListeners() {
        for(DataChangeListener listener : dataChangeListener){
            listener.onDataChanged();
        }
    }
    
    private void setErrorsMessages(Map<String, String> errors){
        Set<String> fields = errors.keySet();
        if(fields.contains("Name")){
            labelErrorName.setText(errors.get("Name"));
            
        }
    }

}