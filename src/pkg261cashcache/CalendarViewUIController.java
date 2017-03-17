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
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

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
    private TreeTableView fixedCostTable;
    //Columns
    @FXML private TreeTableColumn fixedCostTitleCol;
    @FXML private TreeTableColumn fixedCostAmountCol;
    
    @FXML
    private TreeTableView flexSpendingTable;
    //Columns
    @FXML private TreeTableColumn flexSpendingTitleCol;
    @FXML private TreeTableColumn flexSpendingAmountCol;
    
    @FXML
    private TreeTableView savingsTable;
    //Columns
    @FXML private TreeTableColumn savingsTitleCol;
    @FXML private TreeTableColumn savingsAmountCol;
    
    @FXML
    private BudgetOverviewUIController theBudgetOverviewUICntl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCategoryTables();
    }    
    
     public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewUICntl = aBudgetOverviewCntl;
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
    
    
}
