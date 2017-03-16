/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.net.URL;
import static java.sql.Date.valueOf;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    
    //ObservableList<String> dobYearList = FXCollections.observableArrayList();
    
    ObservableList<String> retirementTypeList = FXCollections.observableArrayList("401(k)", "403(b)", "IRA", "Roth IRA", "Solo 401(k)", "None");
    
    @FXML private ComboBox paycheckFrequency;
    @FXML private ComboBox retirementType;
    @FXML private DatePicker dobPicker;
    @FXML private DatePicker lastCheckPicker;
    @FXML TextField incomeTextField;    
    
    private BudgetOverviewUIController theBudgetOverviewCntl;
    private Paycheck paycheck;
    private BudgetOverview theBudgetOverview;
    private double monthlyIncome = 0.0;
    private int frequency = 0;
    private String savingsType;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        incomeTextField.setText("" + monthlyIncome);
        paycheckFrequency.setValue(paycheckFrequencyList.get(1));
        paycheckFrequency.setItems(paycheckFrequencyList);
        retirementType.setValue("Account Type");
        retirementType.setItems(retirementTypeList);
        paycheck = new Paycheck(0);
        
    }
    
    public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewCntl = aBudgetOverviewCntl;
        this.theBudgetOverview = theBudgetOverviewCntl.getTheBudgetOverview();
        this.paycheck = theBudgetOverview.getThePaycheck();
        incomeTextField.setText("" + paycheck.getCheckAmount());
        paycheckFrequency.setValue(paycheckFrequencyList.get(theBudgetOverview.getThePaycheck().getFrequency()));
        dobPicker.setValue(theBudgetOverview.getDOB());
        lastCheckPicker.setValue(theBudgetOverview.getLastPayDay());
        retirementType.setValue(theBudgetOverview.getAccountType());
    }
    
    @FXML
    public void handleCancel(){
        theBudgetOverviewCntl.closePreferences();
    }
    
    @FXML
    public void handleSave(){
        try{
            if (paycheckFrequency.getValue().equals(paycheckFrequencyList.get(0))){
                frequency = 0;
            } else if (paycheckFrequency.getValue().equals(paycheckFrequencyList.get(2))){
                frequency = 2;
            } else if (paycheckFrequency.getValue().equals(paycheckFrequencyList.get(1))){
                frequency = 1;
            }
            
            
            if (retirementType.getValue().equals(retirementTypeList.get(0))){
                savingsType = "401(k)";
            } else if (retirementType.getValue().equals(retirementTypeList.get(1))){
                savingsType = "403(b)";
            } else if (retirementType.getValue().equals(retirementTypeList.get(2))){
                savingsType = "IRA";
            } else if (retirementType.getValue().equals(retirementTypeList.get(3))){
                savingsType = "Roth IRA";
            } else if (retirementType.getValue().equals(retirementTypeList.get(4))){
                savingsType = "Solo 401(k)";
            } else if (retirementType.getValue().equals(retirementTypeList.get(5))){
                savingsType = "None";
            }
            
            paycheck.setCheckAmount(Double.parseDouble(incomeTextField.getText()));
            paycheck.setFrequency(frequency);
            theBudgetOverview.setThePaycheck(paycheck);
            theBudgetOverview.setDOB(dobPicker.getValue());
            theBudgetOverview.setLastPayDay(lastCheckPicker.getValue());
            theBudgetOverview.setAccountType(savingsType);
            theBudgetOverviewCntl.setBudgetOverview(theBudgetOverview);
            theBudgetOverviewCntl.updateCategoryUI();
            theBudgetOverviewCntl.closeRightPanel();
            theBudgetOverviewCntl.closePreferences();
            
        } catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Incorrect Input");
            alert.setHeaderText("Please enter all fields");
            alert.setContentText("Make sure to properly enter all your information");
            alert.showAndWait();
        }
    }
    
    public double getMonthlyIncome(){
        return monthlyIncome;
    }
    
}

