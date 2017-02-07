/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author mjg5683
 */
public class Date {
    
    private IntegerProperty year;
    private IntegerProperty month;
    private IntegerProperty day;
    
    public Date(int aYear, int aMonth, int aDay) {
        
        year = new SimpleIntegerProperty(aYear);
        month = new SimpleIntegerProperty(aMonth);
        day = new SimpleIntegerProperty(aDay);
        
    }

    /**
     * @return the year
     */
    public IntegerProperty getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(IntegerProperty year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public IntegerProperty getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(IntegerProperty month) {
        this.month = month;
    }

    /**
     * @return the day
     */
    public IntegerProperty getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(IntegerProperty day) {
        this.day = day;
    }
}
