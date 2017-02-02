/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Phil
 */
public class Paycheck {
    
    private DoubleProperty checkAmount;
    //date and time variable
    
    public Paycheck(String date, double aCheckAmount){
        
        checkAmount = new SimpleDoubleProperty(aCheckAmount);
        
    }
    
    
    
    
}
