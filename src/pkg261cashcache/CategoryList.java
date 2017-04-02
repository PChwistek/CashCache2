/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Phil
 */
public class CategoryList {
    
    private ArrayList <Category> theCategoryList = new ArrayList();

    private Paycheck income;
    
    public CategoryList(Double anIncome){
        income = new Paycheck(LocalDate.now(), anIncome);
    }
    
    public void setTheCategoryList(ArrayList<Category> aCategoryList){
        theCategoryList = aCategoryList;
    }
    
    public ArrayList<Category> getTheListofCategories(){
        return theCategoryList;
    }
    
    public void populateDefaultCategoryList(){
        
        double fixedCostAllowance = getIncome().getCheckAmount() * .5;
        double savingsGoalAllowance = getIncome().getCheckAmount() * .2;
        double flexSpendingAllowance = getIncome().getCheckAmount() * .3;
        
        /*
        Category fixedCosts = new Category(fixedCostAllowance, "Fixed Costs");
        Category savingsGoals = new Category(savingsGoalAllowance, "Financial Goals");
        Category flexSpending = new Category(flexSpendingAllowance, "Flexible Spending");
        
        
        getTheCategoryList().add(fixedCosts);
        getTheCategoryList().add(savingsGoals);
        getTheCategoryList().add(flexSpending);
        */
        
    }

    /**
     * @return the income
     */
    public Paycheck getIncome() {
        return income;
    }

    /**
     * @param income the income to set
     */
    public void setIncome(Paycheck income) {
        this.income = income;
    }
    
    public boolean fixedCostCategories(){
        for(Category cat: theCategoryList){
            if(cat.getCategoryType().equals("Fixed Costs")){
                return true;
            }
        }
        return false;
    }
    
    public boolean flexSpendingCategories(){
        for(Category cat: theCategoryList){
            if(cat.getCategoryType().equals("Flexible Spending")){
                return true;
            }
        }
        return false;
    }
    
    
    public boolean savingsCategories(){
         for(Category cat: theCategoryList){
            if(cat.getCategoryType().equals("Savings")){
                return true;
            }
        }
        return false;
    }
    
    
    
}
