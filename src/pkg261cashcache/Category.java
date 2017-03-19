/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Phil
 */
public class Category {
    
    private Integer idNum; // would this end up being static? 
    private static int idCount = 1;
    private double allowance;
    private StringProperty categoryTitle;
    private String categoryName;
    private String categoryType;
    
    public Category(double anAllowance, String aTitle, String aCategoryType){
        allowance = anAllowance;
        categoryName = aTitle;
        categoryTitle = new SimpleStringProperty(aTitle);
        categoryType = aCategoryType;
        idNum = idCount;
        idCount++;
    }

    /**
     * @return the idNum
     */
    public int getIdNum() {
        return idNum;
    }

    /**
     * @param idNum the idNum to set
     */
    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    /**
     * @return the allowance
     */
    public ObservableValue<Double> getAllowanceProperty() {
        ObservableValue allowanceProperty = new SimpleDoubleProperty(allowance);
        return  allowanceProperty;
    }

    /**
     * @param allowance the allowance to set
     */
    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    /**
     * @return the categoryTitle
     */
    public StringProperty getCategoryTitle() {
        return categoryTitle;
    }

    /**
     * @param categoryTitle the categoryTitle to set
     */
    public void setCategoryTitle(StringProperty categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    /**
     * @return the categoryType
     */
    public String getCategoryType() {
        return categoryType;
    }

    /**
     * @param categoryType the categoryType to set
     */
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
    
    public boolean isEqual(Category aCat){
        if(this.getIdNum() == aCat.getIdNum()){
            return true;
        } 
        return false;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
        
}
