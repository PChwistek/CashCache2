/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Phil
 */
public class Category {
    
    private IntegerProperty idNum;
    private DoubleProperty allowance;
    private StringProperty categoryTitle;
    
    public Category(double anAllowance, String aTitle){
        allowance = new SimpleDoubleProperty(anAllowance);
        categoryTitle = new SimpleStringProperty(aTitle);
    }
    
}
