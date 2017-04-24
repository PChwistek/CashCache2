/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;


/**
 * FXML Controller class
 *
 * @author Phil
 * 
 * This class is instantiated when the Market button is pressed. 
 * It calculates how much retirement savings from this month would be worth in the future.
 * It also allows the user to add, delete, and view stocks/stock information. 
 * 
 * 
 * 
 */
public class MarketUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private BudgetOverviewUIController theBudgetOverviewUICntl;
    
    @FXML TableView<StockItem> theStockTable;
    @FXML TableColumn<StockItem, String> tickerColumn;
    @FXML TableColumn<StockItem, String> currentPrice;
    @FXML TableColumn<StockItem, String> openingPrice;
    @FXML TableColumn<StockItem, String> percentageChange;
    @FXML Label amountInSavings;
    @FXML Label numberOfYears;
    @FXML Label futureAmount;
    
    private BudgetOverview theBudgetOverview;
    private ArrayList<StockItem> stockList = new ArrayList();
 
   // private Stock[] stockList = new Stock[20];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }    
    
    public void setBudgetOverviewCntl(BudgetOverviewUIController theCntl){
        theBudgetOverviewUICntl = theCntl;
        theBudgetOverview = theBudgetOverviewUICntl.getTheBudgetOverview();
        stockList = theBudgetOverview.getTheStockList();
        initTable();
        initRightSide();
        if(stockList.size() > 0){
            handleRefreshStockList();
        }
        
        
    }
    
    public void initTable(){
          ObservableList<StockItem> stockTableList = FXCollections.observableArrayList(stockList);
          theStockTable.setPlaceholder(new Label("Add Stock Symbols"));
          theStockTable.setItems(stockTableList);
          theStockTable.setEditable(true);
          tickerColumn.setEditable(true);

          
          tickerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSymbol()));
          currentPrice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrice()));
          openingPrice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpen()));
          percentageChange.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getChange()));

          tickerColumn.prefWidthProperty().bind(theStockTable.widthProperty().divide(4)); tickerColumn.setText("Symbol");
          currentPrice.prefWidthProperty().bind(theStockTable.widthProperty().divide(4)); currentPrice.setText("Last");
          openingPrice.prefWidthProperty().bind(theStockTable.widthProperty().divide(4)); openingPrice.setText("Open");
          percentageChange.prefWidthProperty().bind(theStockTable.widthProperty().divide(4)); percentageChange.setText("Change");
          
          
    }
    
    
    @FXML private void handleAddStock(){
        try{
            TextInputDialog dialog = new TextInputDialog("Add Stock");
            dialog.setTitle("Add Stock");
            dialog.setHeaderText("Add a stock price to the list");
            dialog.setContentText("Please Enter a stock ticker symbol");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                stockList.add(new StockItem(result.get()));
            }
            theBudgetOverview.setTheStockList(stockList);
            initTable();
            //colorizeTable();
        }catch(Exception e){
            
        }
    }
    
    @FXML private void handleDeleteStock(){
        stockList.remove(theStockTable.getSelectionModel().getSelectedIndex());
        theBudgetOverview.setTheStockList(stockList);
        initTable();
    }
    
    @FXML private void handleRefreshStockList(){
        for(int i = 0; i < stockList.size(); i++){
            stockList.set(i, new StockItem(stockList.get(i).getSymbol()));
        }
        theBudgetOverview.setTheStockList(stockList);
        initTable();
    }
    
    
    public void colorizeTable(){
          percentageChange.setCellFactory(column -> {
            return new TableCell<StockItem, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        
                        TableRow<StockItem> currentRow = getTableRow();
                        
                        if(item.compareTo("0") > 0){
                            currentRow.setStyle("-fx-text-color: rgba(137, 158, 96, 0.7);");                            
                        }
                        
                    }
                };
            });
    }
    
    
    public void initRightSide(){
        ArrayList<Integer> years = new ArrayList();
        ArrayList<Category> categories = theBudgetOverview.getTheCategoryList().getTheListofCategories();
        double savings = 0.0;
        LocalDate startAge = theBudgetOverview.getDOB();
        //System.out.println(startAge.getYear());
        for(int i =0; i < 100; i++){
                years.add(i);
            }
        
        ObservableList yearsObservable = FXCollections.observableArrayList(years);
        numberOfYears.setText("" + yearsObservable.get(65 - (LocalDate.now().getYear() - startAge.getYear())));
        
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).isIsRetirement()== true){
                savings += categories.get(i).getAllowanceProperty().getValue();
                System.out.print(savings);
            }
        }
        
        amountInSavings.setText("$" + savings);
        
        double future = 0;
        
        for(int i = 0; i < Integer.parseInt(numberOfYears.getText()); i++){
            savings += savings * .05;
        }
        
        futureAmount.setText("$" + Math.round(savings));
    }
    
}