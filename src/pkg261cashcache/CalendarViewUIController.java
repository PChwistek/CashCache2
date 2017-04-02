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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;


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
    @FXML private Button fixedCostDelete;
    @FXML private Button flexSpendingDelete;
    @FXML private Button savingsDelete;
    
    @FXML private Accordion theAccordian;
    @FXML private TitledPane theFixedCostPane;
    @FXML private TitledPane theFlexSpendingPane;
    @FXML private TitledPane theSavingsPane;
    
    
    @FXML private ProgressBar fixedCostBar;
    @FXML private ProgressBar flexSpendBar;
    @FXML private ProgressBar savingsBar;
    
    @FXML private Label fixedCostPercentage;
    @FXML private Label flexSpenPercentage;
    @FXML private Label savingsPercentage;
    
    
    private Stage secondaryStage;
    private CreateNewExpenseController theExpenseCntl;
    private ExpenseEvent currentExpenseEvent;
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
        fixedCostTable.setEditable(false);
        flexibleCostTable.setEditable(false);
        savingsTable.setEditable(false);
        fixedCostDelete.setDisable(true);
        flexSpendingDelete.setDisable(true);
        savingsDelete.setDisable(true);
        initCategoryTables();
        
        fixedCostTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showExpenseEvent(newValue));
        flexibleCostTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showExpenseEvent(newValue));
        savingsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showExpenseEvent(newValue));
            
  
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
    
    @FXML
    private void initAccordion(){
        if(calendar.getValue() != null){
              
           
           fixedCostRefinedList = theBudgetOverview.sortExpenseListByDate(calendar.getValue(), "Fixed Costs");
           flexSpendingRefinedList = theBudgetOverview.sortExpenseListByDate(calendar.getValue(), "Flexible Spending");
           savingsRefinedList = theBudgetOverview.sortExpenseListByDate(calendar.getValue(), "Savings");
           
           fixedCostTable.setItems(fixedCostRefinedList);
           flexibleCostTable.setItems(flexSpendingRefinedList);
           savingsTable.setItems(savingsRefinedList);
           
            theAccordian.setDisable(false);
            
           if(theBudgetOverview.getTheCategoryList().fixedCostCategories() == true){
               fixedCostButton.setDisable(false);
           }
           
           if(theBudgetOverview.getTheCategoryList().flexSpendingCategories() == true){
               flexSpendingButton.setDisable(false);
           }
           
           if(theBudgetOverview.getTheCategoryList().savingsCategories() == true){
               savingsButton.setDisable(false);
           }
           
           initProgressBars();

        }
    }
    
    public void initCalendar(){
        
        ArrayList<ExpenseEvent> theListOfEvents = theBudgetOverview.getTheExpenseList();
        ArrayList<LocalDate> thePaydays = theBudgetOverview.getThePaycheck().getTheListOfPaychecks();
        LocalDate payDay = theBudgetOverview.getTheLastPayDate();
        setSelectedDate(calendar.getValue());
        
        
         final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            
                            for(int i = 0; i < thePaydays.size(); i ++){
                                if(item.isEqual(thePaydays.get(i))){
                                    setStyle("-fx-background-color: rgba(137, 158, 96, 0.7);");
                                }
                            }      
                            
                            for(int i = 0; i < theListOfEvents.size(); i++){
                                if(item.isEqual(theListOfEvents.get(i).getTheDate())){
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                    }
                };
            }
        };
        calendar.setDayCellFactory(dayCellFactory);
    }
    
    public void initProgressBars(){
        ObservableList<Category> theListOfCategories = FXCollections.observableArrayList(theBudgetOverview.getTheCategoryList().getTheListofCategories());
        ArrayList<ExpenseEvent> theListOfExpenses = theBudgetOverview.getTheExpenseList();
        
        double fixedCostMax = 0;
        double flexCostMax = 0;
        double savingsMax = 0;
        
        double currentFixedExpense = 0;
        double currentFlexExpense = 0;
        double currentSavingsExpense = 0;
        
        for(Category cat: theListOfCategories){
            switch(cat.getCategoryType()){
                case "Fixed Costs":
                    fixedCostMax += cat.getAllowanceProperty().getValue();
                    break;
                case "Flexible Spending":
                    flexCostMax += cat.getAllowanceProperty().getValue();
                    break;
                case "Savings":
                    savingsMax += cat.getAllowanceProperty().getValue();
                    break;
                default:
                    break;
            }
        }
        
        for(ExpenseEvent event: theListOfExpenses){
             switch(event.getCategoryType()){
                case "Fixed Costs":
                    currentFixedExpense += event.getExpenseAmount();
                    break;
                case "Flexible Spending":
                    currentFlexExpense += event.getExpenseAmount();
                    break;
                case "Savings":
                    currentSavingsExpense += event.getExpenseAmount();
                    break;
                default:
                    break;
            }
        }
        
        fixedCostBar.setProgress(currentFixedExpense/fixedCostMax);
        flexSpendBar.setProgress(currentFlexExpense/flexCostMax);
        savingsBar.setProgress(currentSavingsExpense/savingsMax);
        
        fixedCostPercentage.setText( (Math.round(100 * (currentFixedExpense/fixedCostMax))) + "%");
        flexSpenPercentage.setText((Math.round(100 * (currentFlexExpense/flexCostMax))) + "%");
        savingsPercentage.setText((Math.round( 100 * (currentSavingsExpense/savingsMax))) + "%");
        
        
    }
    
    public void handleCancelExpenseWindow(){
        secondaryStage.close();
    }
    
    @FXML
    public void handleFlexDelete(){
        if(deleteConfirm() == true){
            theBudgetOverview.removeFromExpenseList(flexibleCostTable.getSelectionModel().getSelectedItem());
            try{
                updateTables();
                initProgressBars();
                initCalendar();
            } catch(NullPointerException e){
                
            }
        }
    } 
    
    @FXML
    public void handleFixedDelete(){
        if(deleteConfirm() == true){
            theBudgetOverview.removeFromExpenseList(fixedCostTable.getSelectionModel().getSelectedItem());
            try{
                updateTables();
                initProgressBars();
                initCalendar();
            } catch(NullPointerException e){
                
            }
        }
    }
    
    @FXML
    public void handleSavingsDelete(){
        if(deleteConfirm() == true){
            theBudgetOverview.removeFromExpenseList(savingsTable.getSelectionModel().getSelectedItem());
            try{
                updateTables();
                initProgressBars();
                initCalendar();
            }catch(NullPointerException e){
                
            }
        }
    }
    
    public boolean deleteConfirm(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete?");
        alert.setHeaderText("Are you sure you want to delete this item?");
        alert.setContentText("Once deleted, this data cannot be retrieved");
        ButtonType okButton = new ButtonType("OK",  ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        Optional<ButtonType> result = alert.showAndWait();  
        if(result.get() == okButton){
            return true;
        }
        return false;
    }
    private void updateTables(){
     
        fixedCostRefinedList = theBudgetOverview.sortExpenseListByDate(calendar.getValue(), "Fixed Costs");
        flexSpendingRefinedList = theBudgetOverview.sortExpenseListByDate(calendar.getValue(), "Flexible Spending");
        savingsRefinedList = theBudgetOverview.sortExpenseListByDate(calendar.getValue(), "Savings");
        fixedCostTable.setItems(fixedCostRefinedList);
        flexibleCostTable.setItems(flexSpendingRefinedList);
        savingsTable.setItems(savingsRefinedList);
        clearSelections();
    }
    
    
    public void clearSelections(){
        
        if(fixedCostTable.getSelectionModel()!= null){
            fixedCostTable.getSelectionModel().clearSelection();
        }
        if(flexibleCostTable.getSelectionModel() != null){
            flexibleCostTable.getSelectionModel().clearSelection();
        }
        
        if(savingsTable.getSelectionModel() != null){
            savingsTable.getSelectionModel().clearSelection();
        }
     
    }
    
    public void handleCreateExpense(ExpenseEvent anExpenseEvent){
         theBudgetOverview.addToExpenseList(anExpenseEvent);
         updateTables();
         initProgressBars();

    }
    
    public void showExpenseEvent(ExpenseEvent anEvent){
        
        if(anEvent != null)
            if(anEvent.getCategoryType().equals("Fixed Costs")){
                fixedCostDelete.setDisable(false);
            } else if(anEvent.getCategoryType().equals("Flexible Spending")){
                flexSpendingDelete.setDisable(false);
            } else if (anEvent.getCategoryType().equals("Savings")){
                savingsDelete.setDisable(false);
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
    
     /**
     * @return the currentExpenseEvent
     */
    public ExpenseEvent getCurrentExpenseEvent() {
        return currentExpenseEvent;
    }

    /**
     * @param currentExpenseEvent the currentExpenseEvent to set
     */
    public void setCurrentExpenseEvent(ExpenseEvent currentExpenseEvent) {
        this.currentExpenseEvent = currentExpenseEvent;
    }

    
}
