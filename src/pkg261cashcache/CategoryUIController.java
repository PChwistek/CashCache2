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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Phil
 */
public class CategoryUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TableView<Category> categoryTable;
    
    @FXML private TableColumn<Category, String> firstTableColumn;
    
    @FXML private TableColumn<Category, Double> secondTableColumn;
    
    @FXML private Label incomeAmount;
    
    @FXML private Label fundsRemaining;
    
    private CategoryList theCategoryList;
    private ObservableList theListOfCategories;
    
    public CategoryUIController(){
        theCategoryList = new CategoryList(0.0);
        theListOfCategories = theCategoryList.getTheListofCategories();
        
    }
    
    private void initCategoryTable(){
        firstTableColumn.setText("Category Name");
        secondTableColumn.setText("Allowance");
        firstTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryTitle());
        secondTableColumn.setCellValueFactory(cellData -> cellData.getValue().getAllowanceProperty());
    }
     

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        incomeAmount.setText("$0");
        fundsRemaining.setText("$0");
        initCategoryTable();
    }
    
    public void updateCategoryUI(double anIncome){
        incomeAmount.setText("$" + anIncome);
        theCategoryList = new CategoryList(anIncome);
        theListOfCategories = theCategoryList.getTheListofCategories();
        categoryTable.setItems(theListOfCategories);
    }
    
    
}
