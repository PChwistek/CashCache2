/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Phil
 */
public class CreateCategoryUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private BudgetOverviewUIController theBudgetOverviewCntl;
    @FXML private Button cancelButton;
    @FXML private Button createButton;
    @FXML private TextField categoryName;
    @FXML private ComboBox categoryTypeDropDown;
    @FXML private ComboBox retirementBox;
    @FXML private Label retirement;
    @FXML private Label title;

    ObservableList<String> categoryTypes = FXCollections.observableArrayList("Fixed Cost", "Flexible Spending", "Savings");
    ObservableList<String> retirementItems = FXCollections.observableArrayList("No", "Yes");


    public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewCntl = aBudgetOverviewCntl;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryTypeDropDown.setItems(categoryTypes);
        retirementBox.setItems(retirementItems);
    }
    
    @FXML
    private void handleCategoryTypeDrop(){
        if(categoryTypeDropDown.getValue().equals(categoryTypes.get(2))){
            retirementBox.setDisable(false); //starts out disabled
            retirement.setDisable(false);
        } else {
            retirementBox.setDisable(true); //starts out disabled
            retirement.setDisable(true);
        }
    }
    
    @FXML
    private void handleCreate(){
        try{
            String name = null;
            if(categoryName.getText().equals("") == false){
                 name = categoryName.getText();
            } else {
                throw new Exception();
            }
            String type = categoryTypeDropDown.getValue().toString();
            Category aCategory = new Category(0, name, type);
            theBudgetOverviewCntl.createCategory(aCategory);
        }catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Input");
            alert.setHeaderText("Please enter all fields");
            alert.setContentText("Make sure to properly enter all your information");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleCancelButton(){
        theBudgetOverviewCntl.closeRightPanel();
    }
    
   
}
