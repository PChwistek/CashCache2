/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.io.IOException;
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
public class BudgetOverview {
    
    private Paycheck thePaycheck;
    private CategoryList theCategoryList;
    private LocalDate theDate;
    private String theSavingsAccountType; 
    private ArrayList<ExpenseEvent> theExpenseList = new ArrayList();
    private ArrayList<ExpenseEvent> thePaydays = new ArrayList();
    private PersistentDataController theDataCntl = new PersistentDataController();
    
    public BudgetOverview(CategoryList aCategoryList, Paycheck aPaycheck){
        thePaycheck = aPaycheck;
        theCategoryList = aCategoryList;
    }
    
    public void saveBudgetOverview(){
        theDataCntl.exportList(this);
    }
    
    public void addToExpenseList(ExpenseEvent anExpenseEvent){
        getTheExpenseList().add(anExpenseEvent);
        saveBudgetOverview();
    }
    
    public void removeFromExpenseList(ExpenseEvent anExpenseEvent){
        
        for(int i = 0; i < theExpenseList.size(); i++){
            if(theExpenseList.get(i).equals(anExpenseEvent)){
                theExpenseList.remove(i);
            }
        }
        saveBudgetOverview();
        
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
        saveBudgetOverview();

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
        saveBudgetOverview();
    }

   
    /**
     * @param theListOfCategories the theListOfCategories to set
     */
    public void setTheListOfCategories(ObservableList<Category> theListOfCategories) {
        theCategoryList.setTheCategoryList(theListOfCategories);
        saveBudgetOverview();
    }
    
    public void setDOB(LocalDate aDate){
        this.theDate = aDate;
        saveBudgetOverview();
    }
    
    public LocalDate getDOB(){
        return this.theDate;
    }
    
    public void setAccountType(String anAccountType){
        theSavingsAccountType = anAccountType;
        saveBudgetOverview();
    }
    
    public String getAccountType(){
        return this.theSavingsAccountType;
        
    }
    
    public void setLastPayDay(LocalDate aDate){
        this.theDate = aDate;
        saveBudgetOverview();
    }
    
    public LocalDate getLastPayDay(){
        return this.theDate;
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
        saveBudgetOverview();
    }
    
}
