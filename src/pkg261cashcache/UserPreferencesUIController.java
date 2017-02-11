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
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        theBudgetOverviewCntl.closePreferences();
    }

    
    
}
