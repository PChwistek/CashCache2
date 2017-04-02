/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Phil
 */
public class Paycheck implements Serializable {
    
    private LocalDate payDay;
    private double checkAmount;
    private int frequency;
    private LocalDate newPayDay;
    private ArrayList<LocalDate> theListOfPaychecks = new ArrayList();
    //date and time variable
    
    public Paycheck(LocalDate lastPayDay, double aCheckAmount){
        
        checkAmount = aCheckAmount;
        payDay = lastPayDay;
    }
    
    
    public ArrayList<LocalDate> nextPaychecks(LocalDate lastPayDay, int freqID) {
        ArrayList<LocalDate> listOfDays = new ArrayList();
        listOfDays.add(lastPayDay);
        LocalDate toAdd = lastPayDay;
        int dateCounter = lastPayDay.getDayOfMonth();
        if (freqID == 0) {
           for(int i = 0; i < lastPayDay.getMonth().maxLength(); i += 7){
               listOfDays.add(lastPayDay.plusDays(i));
           }
            
        } else if (freqID == 1) {
            for(int i = 0; i < lastPayDay.getMonth().maxLength(); i+= 14){
                listOfDays.add(lastPayDay.plusDays(i));
            }
        } else if (freqID == 2) {
           listOfDays.add(lastPayDay.plusMonths(1));
        }  
        setTheListOfPaychecks(listOfDays);
        return listOfDays;
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

    /**
     * @return the newPayDay
     */
    public LocalDate getNewPayDay() {
        return newPayDay;
    }

    /**
     * @param newPayDay the newPayDay to set
     */
    public void setNewPayDay(LocalDate newPayDay) {
        this.newPayDay = newPayDay;
    }

    /**
     * @return the theListOfPaychecks
     */
    public ArrayList<LocalDate> getTheListOfPaychecks() {
        return theListOfPaychecks;
    }

    /**
     * @param theListOfPaychecks the theListOfPaychecks to set
     */
    public void setTheListOfPaychecks(ArrayList<LocalDate> theListOfPaychecks) {
        this.theListOfPaychecks = theListOfPaychecks;
    }
    
}
