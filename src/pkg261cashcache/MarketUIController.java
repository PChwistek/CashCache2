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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Phil
 */
public class MarketUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private BudgetOverviewUIController theBudgetOverviewUICntl;
    
    @FXML TableView theStockTable;
    @FXML TableColumn tickerColumn;
    @FXML TableColumn currentPrice;
    @FXML TableColumn openingPrice;
    @FXML TableColumn percentageChange;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }    
    
    public void setBudgetOverviewCntl(BudgetOverviewUIController theCntl){
        theBudgetOverviewUICntl = theCntl;
    }
    
    public void initTable(){
          tickerColumn.prefWidthProperty().bind(theStockTable.widthProperty().divide(4)); tickerColumn.setText("Symbol");
          currentPrice.prefWidthProperty().bind(theStockTable.widthProperty().divide(4)); currentPrice.setText("Last");
          openingPrice.prefWidthProperty().bind(theStockTable.widthProperty().divide(4)); openingPrice.setText("Open");
          percentageChange.prefWidthProperty().bind(theStockTable.widthProperty().divide(4)); percentageChange.setText("%");
    }
    
}
