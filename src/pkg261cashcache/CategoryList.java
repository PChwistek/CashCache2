/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Phil
 */
public class CategoryList {
    
    private ObservableList <Category> theCategoryList = FXCollections.observableArrayList();

    private Paycheck income;
    
    public CategoryList(Double anIncome){
        income = new Paycheck(anIncome);
        populateDefaultCategoryList();
    }
    
    public void setTheCategoryList(ObservableList<Category> aCategoryList){
        theCategoryList = aCategoryList;
    }
    
    public ObservableList<Category> getTheListofCategories(){
        return theCategoryList;
    }
    
    public void populateDefaultCategoryList(){
        
        double fixedCostAllowance = income.getCheckAmount() * .5;
        double savingsGoalAllowance = income.getCheckAmount() * .2;
        double flexSpendingAllowance = income.getCheckAmount() * .3;
        
        Category fixedCosts = new Category(fixedCostAllowance, "Fixed Costs");
        Category savingsGoals = new Category(savingsGoalAllowance, "Financial Goals");
        Category flexSpending = new Category(flexSpendingAllowance, "Flexible Spending");
        
        theCategoryList.add(fixedCosts);
        theCategoryList.add(savingsGoals);
        theCategoryList.add(flexSpending);
        
    }
    
}
