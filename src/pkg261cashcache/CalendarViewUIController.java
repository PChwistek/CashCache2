/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
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
    private TableView<ExpenseEvent> fixedCostTable;
    //Columns
    @FXML private TableColumn<ExpenseEvent, String> fixedCostCategoryCol;
    @FXML private TableColumn<ExpenseEvent, String> fixedCostTitleCol;
    @FXML private TableColumn<ExpenseEvent, Double> fixedCostAmountCol;
    
    //Columns
    @FXML private TableView<ExpenseEvent> flexibleCostTable;
    @FXML private TableColumn<ExpenseEvent, String> flexSpendingCategoryCol;
    @FXML private TableColumn<ExpenseEvent, String> flexSpendingTitleCol;
    @FXML private TableColumn<ExpenseEvent, Double> flexSpendingAmountCol;
    
    @FXML
    private TableView<ExpenseEvent> savingsTable;
    //Columns
    @FXML private TableColumn<ExpenseEvent, String> savingsCategoryCol;
    @FXML private TableColumn<ExpenseEvent, String> savingsTitleCol;
    @FXML private TableColumn<ExpenseEvent, Double> savingsAmountCol;
    
    @FXML private Button fixedCostButton;
    @FXML private Button flexSpendingButton;
    @FXML private Button savingsButton;
    @FXML private TitledPane theAccordian;
    
    private Stage secondaryStage;
    private CreateNewExpenseController theExpenseCntl;
    private ArrayList<ExpenseEvent> fixedCostEvents = new ArrayList();
    private ArrayList<ExpenseEvent> flexibleSpendingEvents = new ArrayList();
    private ArrayList<ExpenseEvent> savingsEvents = new ArrayList();
    private ObservableList<ExpenseEvent> fixedCostRefinedList;
    private ObservableList<ExpenseEvent> flexSpendingRefinedList;
    private ObservableList<ExpenseEvent> savingsRefinedList;

    private enum WhichButton{
        FIXED, FLEX, SAVINGS
    }
    
    
    @FXML
    private BudgetOverviewUIController theBudgetOverviewUICntl;
    private BudgetOverview theBudgetOverview;
    private LocalDate selectedDate = LocalDate.now();
    
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
                    categoryType = "Fixed Costs";
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
            theExpenseCntl.setCalendarCntl(this);
            theExpenseCntl.updateLabel(categoryType);
            secondaryStage.setScene(new Scene(root));
            secondaryStage.setTitle("Create an Expense ");
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.showAndWait(); 
            
        } catch (IOException e){
             e.printStackTrace();
        }
    }
    
     public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
         this.setTheBudgetOverviewUICntl(aBudgetOverviewCntl);
        theBudgetOverview = getTheBudgetOverviewUICntl().getTheBudgetOverview();
        initCalendar();
        
        fixedCostRefinedList = FXCollections.observableArrayList(fixedCostEvents);
        fixedCostTable.setItems(fixedCostRefinedList);
        
        flexSpendingRefinedList = FXCollections.observableArrayList(flexibleSpendingEvents);
        flexibleCostTable.setItems(flexSpendingRefinedList);
        
        savingsRefinedList = FXCollections.observableArrayList(savingsEvents);
        savingsTable.setItems(savingsRefinedList);
        
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
        
        
        //data
        fixedCostCategoryCol.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        fixedCostTitleCol.setCellValueFactory(cellData -> cellData.getValue().getEventTitleProperty());
        fixedCostAmountCol.setCellValueFactory(cellData -> cellData.getValue().getExpenseAmountProperty());
        
        flexSpendingCategoryCol.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        flexSpendingTitleCol.setCellValueFactory(cellData -> cellData.getValue().getEventTitleProperty());
        flexSpendingAmountCol.setCellValueFactory(cellData -> cellData.getValue().getExpenseAmountProperty());
        
        savingsCategoryCol.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        savingsTitleCol.setCellValueFactory(cellData -> cellData.getValue().getEventTitleProperty());
        savingsAmountCol.setCellValueFactory(cellData -> cellData.getValue().getExpenseAmountProperty());
        
    }
    
    
    private void initCalendar(){
        
  
    }
    
    public void handleCancelExpenseWindow(){
        secondaryStage.close();
    }
    
    public void handleCreateExpense(ExpenseEvent anExpenseEvent){
        
        
        if(anExpenseEvent.getCategoryType().equalsIgnoreCase("Fixed Costs")){
            fixedCostEvents.add(anExpenseEvent);
            fixedCostRefinedList = FXCollections.observableArrayList(fixedCostEvents);
            fixedCostTable.setItems(fixedCostRefinedList);
            fixedCostTable.refresh();
        } else if (anExpenseEvent.getCategoryType().equalsIgnoreCase("Flexible Spending")){
             flexibleSpendingEvents.add(anExpenseEvent);
             flexSpendingRefinedList = FXCollections.observableArrayList(flexibleSpendingEvents);
             flexibleCostTable.setItems(flexSpendingRefinedList);
             flexibleCostTable.refresh();
         } else if (anExpenseEvent.getCategoryType().equalsIgnoreCase("Savings")){
             savingsEvents.add(anExpenseEvent);
             savingsRefinedList = FXCollections.observableArrayList(savingsEvents);
             savingsTable.setItems(savingsRefinedList);
             savingsTable.refresh();
         }
              
    }

    /**
     * @return the theBudgetOverviewUICntl
     */
    public BudgetOverviewUIController getTheBudgetOverviewUICntl() {
        return theBudgetOverviewUICntl;
    }

    /**
     * @param theBudgetOverviewUICntl the theBudgetOverviewUICntl to set
     */
    public void setTheBudgetOverviewUICntl(BudgetOverviewUIController theBudgetOverviewUICntl) {
        this.theBudgetOverviewUICntl = theBudgetOverviewUICntl;
    }

    /**
     * @return the selectedDate
     */
    public LocalDate getSelectedDate() {
        if(calendar.getValue() != null){
            selectedDate = calendar.getValue();
        }
        return selectedDate;
    }

    /**
     * @param selectedDate the selectedDate to set
     */
    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }
    
    
    
}
