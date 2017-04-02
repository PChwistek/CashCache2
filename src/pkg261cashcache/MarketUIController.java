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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

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
    
    @FXML TableView<Stock> theStockTable;
    @FXML TableColumn<Stock, String> tickerColumn;
    @FXML TableColumn<Stock, String> currentPrice;
    @FXML TableColumn<Stock, String> openingPrice;
    @FXML TableColumn<Stock, String> percentageChange;
    @FXML Label amountInSavings;
    @FXML TextField percentGrowth;
    @FXML ComboBox numberOfYears;
    @FXML Label futureAmount;
    
    private BudgetOverview theBudgetOverview;
    private ArrayList<Stock> stockList = new ArrayList();
 
   // private Stock[] stockList = new Stock[20];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }    
    
    public void setBudgetOverviewCntl(BudgetOverviewUIController theCntl){
        theBudgetOverviewUICntl = theCntl;
        theBudgetOverview = theBudgetOverviewUICntl.getTheBudgetOverview();
        //initRightSide();
        
        
    }
    
    public void initTable(){
          ObservableList<Stock> stockTableList = FXCollections.observableArrayList(stockList);
          theStockTable.setPlaceholder(new Label("Add Stock Symbols"));
          theStockTable.setItems(stockTableList);
          theStockTable.setEditable(true);
          tickerColumn.setEditable(true);

          
          tickerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSymbol()));
          currentPrice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuote().getPrice().toString()));
          openingPrice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuote().getOpen().toString()));
          percentageChange.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuote().getChange().toString()));

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
                stockList.add(YahooFinance.get(result.get()));
            }
            initTable();
        }catch(Exception e){
            
        }
    }
    
    @FXML private void handleDeleteStock(){
        stockList.remove(theStockTable.getSelectionModel().getSelectedIndex());
        initTable();
    }
    
    
    
    
    public void initRightSide(){
        ArrayList<Integer> years = new ArrayList();
        ArrayList<Category> categories = theBudgetOverview.getTheCategoryList().getTheListofCategories();
        double savings = 0.0;
        LocalDate startAge = theBudgetOverview.getDOB();
        System.out.println(startAge.getYear());
        for(int i =0; i < 100; i++){
                years.add(i);
            }
        
        ObservableList yearsObservable = FXCollections.observableArrayList(years);
        numberOfYears.setItems(yearsObservable);
        numberOfYears.setValue(yearsObservable.get(65 - (LocalDate.now().getYear() - startAge.getYear())));
        
        percentGrowth.setText(".05");
        
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).isIsRetirement()== true){
                savings += categories.get(i).getAllowanceProperty().getValue();
                System.out.print(savings);
            }
        }
        
        amountInSavings.setText("" + savings);
        
        double future = 0;
        
        for(int i = 0; i < Integer.parseInt(numberOfYears.getValue().toString()); i++){
            savings = savings * Double.parseDouble(percentGrowth.getText());
        }
        
        futureAmount.setText("" + savings);
    }
    
}