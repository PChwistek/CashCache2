/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * This class is embedded in the BudgetOverviewUIController. 
 * It includes the table of categories, as well as the income remaining and monthly allowance calculations
 * 
 * 
 * @author Phil
 */
public class CategoryListUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TableView<Category> categoryTable;
    @FXML private TableColumn<Category, String> firstTableColumn;
    @FXML private TableColumn<Category, Double> secondTableColumn;
    @FXML private Label incomeAmount;
    @FXML private Label fundsRemaining;
    @FXML private Button addCategoryButton;
    
    private BudgetOverviewUIController theBudgetOverviewUICntl;
    private BudgetOverview theBudgetOverview;
    private CategoryList theCategoryList;
    private ObservableList<Category> theListOfCategories;
    
    public CategoryListUIController(){
    }
    
    public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewUICntl = aBudgetOverviewCntl;
        theBudgetOverview = aBudgetOverviewCntl.getTheBudgetOverview();
        if(theBudgetOverview.getTheCategoryList() != null){
            theListOfCategories = FXCollections.observableArrayList(theBudgetOverview.getTheCategoryList().getTheListofCategories());
        }
        
        theBudgetOverview = PersistentDataController.getSerializedDataCntl().getSerializedDataModel();
        if(theBudgetOverview.getThePaycheck().getCheckAmount() > 0.0){
            updateCategoryUI(getMonthlyIncome());
        } else {
            incomeAmount.setText("$0");
            fundsRemaining.setText("$0");
        }
    }
    
    private void initCategoryTable(){
        firstTableColumn.setText("Category Name");
        secondTableColumn.setText("Allowance");
        categoryTable.setPlaceholder(new Label("Create a category"));
        firstTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryTitle());
        secondTableColumn.setCellValueFactory(cellData -> cellData.getValue().getAllowanceProperty());
        
        firstTableColumn.prefWidthProperty().bind(categoryTable.widthProperty().divide(2));
        secondTableColumn.prefWidthProperty().bind(categoryTable.widthProperty().divide(2));
      
    }
     

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        initCategoryTable();
        categoryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCategoryDetails(newValue));
    }
    
    public void updateCategoryUI(double anIncome){
        incomeAmount.setText("$" + anIncome);
        this.theBudgetOverview = theBudgetOverviewUICntl.getTheBudgetOverview();
        categoryTable.setItems(FXCollections.observableArrayList(theBudgetOverview.getTheCategoryList().getTheListofCategories()));
        fundsRemaining.setText("$" + (theBudgetOverviewUICntl.calculateRemainingFunds()));
        categoryTable.refresh();   
        addCategoryButton.setDisable(false);
        
        
    }
    
    public void updateFundsRemaining(){
        fundsRemaining.setText("$" + (theBudgetOverviewUICntl.calculateRemainingFunds()));
    }
    
    public double calculateCurrentTotalAllocations(){
        double funds = 0;
        if(theListOfCategories != null){
            for(int i = 0; i < theListOfCategories.size(); i++){
                funds += theListOfCategories.get(i).getAllowanceProperty().getValue();
            }
        }
        return funds;
        
    }
    
    public void createNewCategory(){
        categoryTable.getSelectionModel().clearSelection();
        theBudgetOverviewUICntl.setCreateNewCategoryUI();
    }
    
    public void showCategoryDetails(Category aCat){
        if(aCat != null){
            theBudgetOverviewUICntl.setDetailViewCategoryUI(aCat);
        }
    }
    
    public void clearSelections(){
        categoryTable.getSelectionModel().clearSelection();
        categoryTable.refresh();
    }
    
    public Double getMonthlyIncome(){
        int aFrequency = 1;
        if(theBudgetOverview.getThePaycheck().getFrequency() == 0){
            aFrequency = 4;
        } else if (theBudgetOverview.getThePaycheck().getFrequency() == 1){
            aFrequency = 2;
        } else {
            aFrequency = 1;
        }
        return theBudgetOverview.getThePaycheck().getCheckAmount()* aFrequency;
    }
    
}
