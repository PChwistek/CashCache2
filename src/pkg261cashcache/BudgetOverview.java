/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 *
 * @author Phil
 */
public class BudgetOverview implements Serializable{
    
    private Paycheck thePaycheck;
    private CategoryList theCategoryList;
    private LocalDate theDate;
    private LocalDate theLastPayDate;
    private LocalDate DOB;
    private String theSavingsAccountType; 
    private ArrayList<ExpenseEvent> theExpenseList = new ArrayList();
    private ArrayList<ExpenseEvent> thePaydays = new ArrayList();
    private ArrayList<StockItem> theStockList = new ArrayList();
    
    public BudgetOverview(CategoryList aCategoryList, Paycheck aPaycheck){
        thePaycheck = aPaycheck;
        theCategoryList = aCategoryList;
    }
    
    
    public void addToExpenseList(ExpenseEvent anExpenseEvent){
        getTheExpenseList().add(anExpenseEvent);
    }
    
    public void removeFromExpenseList(ExpenseEvent anExpenseEvent){
        
        for(int i = 0; i < theExpenseList.size(); i++){
            if(theExpenseList.get(i).equals(anExpenseEvent)){
                theExpenseList.remove(i);
            }
        }
        
    }
    
    public void generatePaydays(LocalDate firstDay, int frequency){
        
    }
    
    public ObservableList<ExpenseEvent> sortExpenseListByDate(LocalDate aDate, String categoryType){
        ObservableList<ExpenseEvent> selectedDayList = FXCollections.observableArrayList();
        for(ExpenseEvent event: getTheExpenseList()){
            if(event.getTheDate().isEqual(aDate) && event.getCategoryType().equals(categoryType)){
                selectedDayList.add(event);
            }
        }
        return selectedDayList;
    }
    
    

    /**
     * @return the thePaycheck
     */
    public Paycheck getThePaycheck() {
        return thePaycheck;
    }

    /**
     * @param thePaycheck the thePaycheck to set
     */
    public void setThePaycheck(Paycheck thePaycheck) {
        this.thePaycheck = thePaycheck;

    }

    /**
     * @return the theCategoryList
     */
    public CategoryList getTheCategoryList() {
        return theCategoryList;
    }

    /**
     * @param theCategoryList the theCategoryList to set
     */
    public void setTheCategoryList(CategoryList theCategoryList) {
        this.theCategoryList = theCategoryList;
    }

   
    /**
     * @param theListOfCategories the theListOfCategories to set
     */
    public void setTheListOfCategories(ArrayList<Category> theListOfCategories) {
        theCategoryList.setTheCategoryList(theListOfCategories);
    }
    
    public void setDOB(LocalDate aDate){
        this.setTheDate(aDate);
    }
    
    public LocalDate getDOB(){
        return this.getTheDate();
    }
    
    public void setAccountType(String anAccountType){
        theSavingsAccountType = anAccountType;
    }
    
    public String getAccountType(){
        return this.theSavingsAccountType;
        
    }
    
    /**
     * @return the theExpenseList
     */
    public ArrayList<ExpenseEvent> getTheExpenseList() {
        return theExpenseList;
    }

    /**
     * @param theExpenseList the theExpenseList to set
     */
    public void setTheExpenseList(ArrayList<ExpenseEvent> theExpenseList) {
        this.theExpenseList = theExpenseList;
    }

    /**
     * @return the theDate
     */
    public LocalDate getTheDate() {
        return theDate;
    }

    /**
     * @param theDate the theDate to set
     */
    public void setTheDate(LocalDate theDate) {
        this.theDate = theDate;
    }

    /**
     * @return the theLastPayDate
     */
    public LocalDate getTheLastPayDate() {
        return theLastPayDate;
    }

    /**
     * @param theLastPayDate the theLastPayDate to set
     */
    public void setTheLastPayDate(LocalDate theLastPayDate) {
        this.theLastPayDate = theLastPayDate;
    }

    /**
     * @return the theStockList
     */
    public ArrayList<StockItem> getTheStockList() {
        return theStockList;
    }

    /**
     * @param theStockList the theStockList to set
     */
    public void setTheStockList(ArrayList<StockItem> theStockList) {
        this.theStockList = theStockList;
    }
    
}
