/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Phil
 */
public class UserPreferencesUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML TextField incomeTextField;    
    private BudgetOverviewUIController theBudgetOverviewCntl;
    private double monthlyIncome;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        incomeTextField.setText("" + monthlyIncome);
    }
    
    public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewCntl = aBudgetOverviewCntl;
    }
    
    @FXML
    public void handleCancel(){
        theBudgetOverviewCntl.closePreferences();
    }
    
    @FXML
    public void handleSave(){
        try{
            monthlyIncome = Double.parseDouble(incomeTextField.getText());
            theBudgetOverviewCntl.updateCategoryUI();
            theBudgetOverviewCntl.closePreferences();
        } catch (Exception e){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Incorrect Input");
            alert.setHeaderText("Income incorrectly inputted");
            alert.setContentText("Please input your income as a number");

            alert.showAndWait();
        }
    }
    
    public double getMonthlyIncome(){
        return monthlyIncome;
    }
    

    
    
}
