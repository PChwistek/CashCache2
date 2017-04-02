/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Phil
 */



public class UserPreferences {

    private DoubleProperty savings;
    private DoubleProperty retirementSavings;
    private StringProperty retirementAccountType; //this should be an enumerator
    private IntegerProperty age;
    private Paycheck income;
    private DoubleProperty bills;
    
    public UserPreferences(){
        
    }
    
    public UserPreferences(Paycheck anIncome){
        this.income = anIncome;
    }
    
    /**
     * @return the savings
     */
    public DoubleProperty getSavings() {
        return savings;
    }

    /**
     * @param savings the savings to set
     */
    public void setSavings(DoubleProperty savings) {
        this.savings = savings;
    }

    /**
     * @return the retirementSavings
     */
    public DoubleProperty getRetirementSavings() {
        return retirementSavings;
    }

    /**
     * @param retirementSavings the retirementSavings to set
     */
    public void setRetirementSavings(DoubleProperty retirementSavings) {
        this.retirementSavings = retirementSavings;
    }

    /**
     * @return the retirementAccountType
     */
    public StringProperty getRetirementAccountType() {
        return retirementAccountType;
    }

    /**
     * @param retirementAccountType the retirementAccountType to set
     */
    public void setRetirementAccountType(StringProperty retirementAccountType) {
        this.retirementAccountType = retirementAccountType;
    }

    /**
     * @return the age
     */
    public IntegerProperty getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(IntegerProperty age) {
        this.age = age;
    }

    /**
     * @return the income
     */
    
    public Paycheck getIncome() {
        return income;
    }

    /**
     * @param income the income to set
     */
    public void setIncome(Paycheck income) {
        this.income = income;
    }

    /**
     * @return the bills
     */
    public DoubleProperty getBills() {
        return bills;
    }

    /**
     * @param bills the bills to set
     */
    public void setBills(DoubleProperty bills) {
        this.bills = bills;
    }
    
    
   
}
