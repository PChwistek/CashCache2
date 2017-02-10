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
    @FXML 
    private Button thePreferencesButton;
    @FXML
    private AnchorPane categoryListPanel;
    
    private Stage secondaryStage;
    private AnchorPane userPreferencesUI;
    
    public BudgetOverviewUIController(){
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void handlePreferencesButton(){
        try{
            Parent root;
            secondaryStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("UserPreferencesUI.fxml"));
            secondaryStage.setScene(new Scene(root));
            secondaryStage.setTitle("User Preferences");
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.showAndWait();
            
        } catch (IOException e){
             e.printStackTrace();
        }
    }
    
    private void initCategoryListPanel(){
        
           
       
        
    }
}
