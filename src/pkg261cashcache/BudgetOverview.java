/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Phil
 */
public class BudgetOverview {
    
    private Paycheck thePaycheck;
    private CategoryList theCategoryList;
    private LocalDate theDate;
    private ObservableList<Category> theListOfCategories;
    private String theSavingsAccountType; 
    private ArrayList<ExpenseEvent> theExpenseList = new ArrayList();
    
    public BudgetOverview(CategoryList aCategoryList, Paycheck aPaycheck){
        thePaycheck = aPaycheck;
        theCategoryList = aCategoryList;
        theListOfCategories = aCategoryList.getTheListofCategories();     
    }
    
    public void addToExpenseList(ExpenseEvent anExpenseEvent){
        theExpenseList.add(anExpenseEvent);
    }
    
    public ArrayList<ExpenseEvent> sortExpenseListByDate(LocalDate aDate, String categoryType){
        ArrayList<ExpenseEvent> selectedDayList = new ArrayList();
        for(ExpenseEvent event: theExpenseList){
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
     * @return the theListOfCategories
     */
    public ObservableList<Category> getTheListOfCategories() {
        return theListOfCategories;
    }

    /**
     * @param theListOfCategories the theListOfCategories to set
     */
    public void setTheListOfCategories(ObservableList<Category> theListOfCategories) {
        this.theListOfCategories = theListOfCategories;
    }
    
    public void setDOB(LocalDate aDate){
        this.theDate = aDate;
    }
    
    public LocalDate getDOB(){
        return this.theDate;
    }
    
    public void setAccountType(String anAccountType){
        theSavingsAccountType = anAccountType;
    }
    
    public String getAccountType(){
        return this.theSavingsAccountType;
        
    }
    
    public void setLastPayDay(LocalDate aDate){
        this.theDate = aDate;
    }
    
    public LocalDate getLastPayDay(){
        return this.theDate;
    }
    
}
