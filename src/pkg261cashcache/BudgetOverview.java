/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.util.ArrayList;

/**
 *
 * @author Phil
 */
public class BudgetOverview {
    
    private Paycheck thePaycheck;
    private CategoryList theCategoryList;
    private ArrayList<Category> theListOfCategories;
    
    public BudgetOverview(CategoryList aCategoryList, Paycheck aPaycheck){
        thePaycheck = aPaycheck;
        theCategoryList = aCategoryList;
        theListOfCategories = aCategoryList.getTheCategoryList();     
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
    public ArrayList<Category> getTheListOfCategories() {
        return theListOfCategories;
    }

    /**
     * @param theListOfCategories the theListOfCategories to set
     */
    public void setTheListOfCategories(ArrayList<Category> theListOfCategories) {
        this.theListOfCategories = theListOfCategories;
    }
    
    
    
    
    
    
    
   
    
    
    
    
}
