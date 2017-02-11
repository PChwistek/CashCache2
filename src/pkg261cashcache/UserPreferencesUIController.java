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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
    
    ObservableList<String> paycheckFrequencyList = FXCollections.observableArrayList("Weekly", "Bi-weekly", "Monthly");
    
    @FXML
    private ComboBox paycheckFrequency;
    
    @FXML TextField incomeTextField;    
    private BudgetOverviewUIController theBudgetOverviewCntl;
    private double monthlyIncome = 0.0;
    private int frequency = 0;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        incomeTextField.setText("" + monthlyIncome);
        paycheckFrequency.setValue(paycheckFrequencyList.get(1));
        paycheckFrequency.setItems(paycheckFrequencyList);
    }
    
    public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewCntl = aBudgetOverviewCntl;
        incomeTextField.setText("" + theBudgetOverviewCntl.getMonthlyIncome());
        paycheckFrequency.setValue(paycheckFrequencyList.get(theBudgetOverviewCntl.getFrequency()));
    }
    
    @FXML
    public void handleCancel(){
        theBudgetOverviewCntl.closePreferences();
    }
    
    @FXML
    public void handleSave(){
        try{
            if(paycheckFrequency.getValue().equals(paycheckFrequencyList.get(0))){
                frequency = 0;
            } else if (paycheckFrequency.getValue().equals(paycheckFrequencyList.get(2))){
                frequency = 2;
            } else {
                frequency = 1;
            }
            theBudgetOverviewCntl.setMonthlyIncome(Double.parseDouble(incomeTextField.getText()));
            theBudgetOverviewCntl.setFrequency(frequency);
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

