/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Phil
 */
public class Paycheck {
    
    private LocalDate payDay;
    private double checkAmount;
    private int frequency;
    private LocalDate newPayDay;
    //date and time variable
    
    public Paycheck(LocalDate lastPayDay, double aCheckAmount){
        
        checkAmount = aCheckAmount;
        payDay = lastPayDay;
    }
    
    public Paycheck(double aCheckAmount){
        checkAmount = aCheckAmount;
        payDay = null;
    }
    
    public void nextPaycheck(LocalDate lastPayDay, int freqID) {
        if (freqID == 0) {
            newPayDay = lastPayDay.plusWeeks(1);
        } else if (freqID == 1) {
            newPayDay = lastPayDay.plusWeeks(2);
        } else if (freqID == 2) {
            newPayDay = lastPayDay.plusMonths(1);
        }
    }

    /**
     * @return the payDay
     */
    public LocalDate getPayDay() {
        return payDay;
    }

    /**
     * @param payDay the payDay to set
     */
    public void setPayDay(LocalDate payDay) {
        this.payDay = payDay;
    }

    /**
     * @return the checkAmount
     */
    public double getCheckAmount() {
        return checkAmount;
    }

    /**
     * @param checkAmount the checkAmount to set
     */
    public void setCheckAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
    }
    
    public void setFrequency(int freq) {
        this.frequency = freq;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
}
