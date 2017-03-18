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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Phil
 */
public class CalendarViewUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private DatePicker calendar;
    
    @FXML
    private TableView fixedCostTable;
    //Columns
    @FXML private TableColumn fixedCostCategoryCol;
    @FXML private TableColumn fixedCostTitleCol;
    @FXML private TableColumn fixedCostAmountCol;
    
    @FXML
    private TableView flexSpendingTable;
    //Columns
    @FXML private TableColumn flexSpendingCategoryCol;
    @FXML private TableColumn flexSpendingTitleCol;
    @FXML private TableColumn flexSpendingAmountCol;
    
    @FXML
    private TableView savingsTable;
    //Columns
    @FXML private TableColumn savingsCategoryCol;
    @FXML private TableColumn savingsTitleCol;
    @FXML private TableColumn savingsAmountCol;
    
    @FXML private Button fixedCostButton;
    @FXML private Button flexSpendingButton;
    @FXML private Button savingsButton;
    private Stage secondaryStage;
    private CreateNewExpenseController theExpenseCntl;
    private enum WhichButton{
        FIXED, FLEX, SAVINGS
    }
    
    
    @FXML
    private BudgetOverviewUIController theBudgetOverviewUICntl;
    private BudgetOverview theBudgetOverview;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCategoryTables();
    }    
    
    @FXML
    private void handleFixedCostButton(){
       createWindow(WhichButton.FIXED);
    }
    
    @FXML
    private void handleFlexSpendingButton(){
        createWindow(WhichButton.FLEX);
    }
    
    @FXML 
    private void handleSavingsButton(){
        createWindow(WhichButton.SAVINGS);
    }
    
    private void createWindow(WhichButton which){
        String categoryType = "";
        try{
            switch(which){
                case FIXED:
                    categoryType = "Fixed Cost";
                    break;
                case FLEX:
                    categoryType = "Flexible Spending";
                    break;
                case SAVINGS:
                     categoryType = "Savings";
                    break;
                default:
                    break;
            }
            Parent root;
            secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CreateNewExpense.fxml"));
            root = loader.load();
            theExpenseCntl = loader.getController();
            secondaryStage.setScene(new Scene(root));
            secondaryStage.setTitle("Create an Expense ");
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.showAndWait(); 
            
        } catch (IOException e){
             e.printStackTrace();
        }
    }
    
     public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewUICntl = aBudgetOverviewCntl;
        theBudgetOverview = theBudgetOverviewUICntl.getTheBudgetOverview();
        initCalendar();
    }  
    
    private void initCategoryTables(){
        
        //Fixed Cost Table resize
        fixedCostCategoryCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(3)); fixedCostCategoryCol.setText("Category");
        fixedCostTitleCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(3)); fixedCostTitleCol.setText("Event Title");
        fixedCostAmountCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(3)); fixedCostAmountCol.setText("Amount Logged");
        //Flexible Cost Table resize
        flexSpendingCategoryCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(3)); flexSpendingCategoryCol.setText("Category");
        flexSpendingTitleCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(3)); flexSpendingTitleCol.setText("Event Title");
        flexSpendingAmountCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(3)); flexSpendingAmountCol.setText("Amount Logged");
        //Savings Cost Table resize
        savingsCategoryCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(3)); savingsCategoryCol.setText("Category");
        savingsTitleCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(3)); savingsTitleCol.setText("Event Title");
        savingsAmountCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(3));savingsAmountCol.setText("Amount Logged");
    }
    
    
    private void initCalendar(){
        
  
    }
    
    
    
}
