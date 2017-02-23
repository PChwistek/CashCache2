/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Phil
 */
public class BudgetOverviewUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button thePreferencesButton;
    @FXML private AnchorPane categoryListUI;
    @FXML private CategoryListUIController categoryListUIController;
    private Stage secondaryStage;
    private AnchorPane userPreferencesUI;
    private static BudgetOverview theBudgetOverview;
    private UserPreferencesUIController userPrefCntl;
    private double monthlyIncome;
    private int frequency = 1; //index in frequencyList
    
    
    public BudgetOverviewUIController(){
        monthlyIncome = 0.0;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    private void handlePreferencesButton(){
        try{
            Parent root;
            secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UserPreferencesUI.fxml"));
            root = loader.load();
            userPrefCntl = loader.getController();
            userPrefCntl.setBudgetOverviewCntl(this);
            secondaryStage.setScene(new Scene(root));
            secondaryStage.setTitle("User Preferences");
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.showAndWait(); 
            
        } catch (IOException e){
             e.printStackTrace();
        }
    }
    
    public void updateCategoryUI(){
        int aFrequency = 1;
        if(frequency == 0){
            aFrequency = 4;
        } else if (frequency == 1){
            aFrequency = 2;
        } else {
            aFrequency = 1;
        }
        categoryListUIController.updateCategoryUI(aFrequency * this.getMonthlyIncome());
    }
    
    public Double getMonthlyIncome(){
        return monthlyIncome;
    }
    
    public void setMonthlyIncome(Double anIncome){
        monthlyIncome = anIncome;
    }
    
    public void closePreferences(){
        secondaryStage.close();
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    
    
}

