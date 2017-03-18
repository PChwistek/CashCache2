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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


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
    @FXML private TableColumn fixedCostTitleCol;
    @FXML private TableColumn fixedCostAmountCol;
    
    @FXML
    private TableView flexSpendingTable;
    //Columns
    @FXML private TableColumn flexSpendingTitleCol;
    @FXML private TableColumn flexSpendingAmountCol;
    
    @FXML
    private TableView savingsTable;
    //Columns
    @FXML private TableColumn savingsTitleCol;
    @FXML private TableColumn savingsAmountCol;
    
    @FXML private Button fixedCostButton;
    @FXML private Button flexSpendingButton;
    @FXML private Button savingsButton;
    
    
    @FXML
    private BudgetOverviewUIController theBudgetOverviewUICntl;
    private BudgetOverview theBudgetOverview;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCategoryTables();
    }    
    
     public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewUICntl = aBudgetOverviewCntl;
        theBudgetOverview = theBudgetOverviewUICntl.getTheBudgetOverview();
        initCalendar();
    }  
    
    private void initCategoryTables(){
        
        //Fixed Cost Table resize
        fixedCostTitleCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(2)); fixedCostTitleCol.setText("Event Title");
        fixedCostAmountCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(2)); fixedCostAmountCol.setText("Amount Logged");
        //Flexible Cost Table resize
        flexSpendingTitleCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(2)); flexSpendingTitleCol.setText("Event Title");
        flexSpendingAmountCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(2)); flexSpendingAmountCol.setText("Amount Logged");
        //Savings Cost Table resize
        savingsTitleCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(2)); savingsTitleCol.setText("Event Title");
        savingsAmountCol.prefWidthProperty().bind(fixedCostTable.widthProperty().divide(2));savingsAmountCol.setText("Amount Logged");
    }
    
    private void initCalendar(){
        
  
    }
    
    
    
}
