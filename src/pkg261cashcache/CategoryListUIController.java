/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
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
    private CategoryList theCategoryList;
    private ObservableList theListOfCategories;
    
    public CategoryListUIController(){
        theCategoryList = new CategoryList(0.0);
        theListOfCategories = theCategoryList.getTheListofCategories();
        
    }
    
    public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewUICntl = aBudgetOverviewCntl;
    }
    
    private void initCategoryTable(){
        firstTableColumn.setText("Category Name");
        secondTableColumn.setText("Allowance");
        categoryTable.setPlaceholder(new Label("Create a category"));
        firstTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryTitle());
        secondTableColumn.setCellValueFactory(cellData -> cellData.getValue().getAllowanceProperty());
    }
     

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        incomeAmount.setText("$0");
        fundsRemaining.setText("$0");
        initCategoryTable();
        
        categoryTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showCategoryDetails(newValue));
    }
    
    public void updateCategoryUI(double anIncome){
        incomeAmount.setText("$" + anIncome);
        theCategoryList = new CategoryList(anIncome);
        theListOfCategories = theCategoryList.getTheListofCategories();
        categoryTable.setItems(theListOfCategories);
    }
    
    public void createNewCategory(){
        categoryTable.getSelectionModel().clearSelection();
        theBudgetOverviewUICntl.setCreateNewCategoryUI();
    }
    
    public void showCategoryDetails(Category cat){
        theBudgetOverviewUICntl.setDetailViewCategoryUI();
    }
    
}