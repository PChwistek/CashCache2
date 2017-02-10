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
    
    private IntegerProperty idNum; // would this end up being static? 
    private double allowance;
    private StringProperty categoryTitle;
    
    public Category(double anAllowance, String aTitle){
        allowance = anAllowance;
        categoryTitle = new SimpleStringProperty(aTitle);
    }

    /**
     * @return the idNum
     */
    public IntegerProperty getIdNum() {
        return idNum;
    }

    /**
     * @param idNum the idNum to set
     */
    public void setIdNum(IntegerProperty idNum) {
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
    
}
