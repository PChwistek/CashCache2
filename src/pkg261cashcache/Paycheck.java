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
    
    private Date payDay;
    private DoubleProperty checkAmount;
    //date and time variable
    
    public Paycheck(int year, int month, int day, double aCheckAmount){
        
        checkAmount = new SimpleDoubleProperty(aCheckAmount);
        payDay = new Date(year, month, day);
    }
    
    public Paycheck(double aCheckAmount){
        checkAmount = new SimpleDoubleProperty(aCheckAmount);
        payDay = null;
    }

    /**
     * @return the payDay
     */
    public Date getPayDay() {
        return payDay;
    }

    /**
     * @param payDay the payDay to set
     */
    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }

    /**
     * @return the checkAmount
     */
    public DoubleProperty getCheckAmount() {
        return checkAmount;
    }

    /**
     * @param checkAmount the checkAmount to set
     */
    public void setCheckAmount(DoubleProperty checkAmount) {
        this.checkAmount = checkAmount;
    }
    
    
    
    
}
