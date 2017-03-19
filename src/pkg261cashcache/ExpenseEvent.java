/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Philz zee Kill
 */
public class ExpenseEvent {
    
    private String category;
    private String eventTitle;
    private String categoryType;
    private LocalDate theDate;
    private StringProperty categoryProperty;
    private StringProperty eventTitleProperty;
    private double expenseAmount;
    
    public ExpenseEvent(String aCategory, String aCategoryType, String anEventTitle, double anExpenseAmount, LocalDate aDate){
        this.category = aCategory;
        this.categoryType = aCategoryType;
        this.eventTitle = anEventTitle;
        this.expenseAmount = anExpenseAmount;
        this.categoryProperty = new SimpleStringProperty(aCategory);
        this.eventTitleProperty = new SimpleStringProperty(anEventTitle);
        this.theDate= aDate;
    }
    
    public boolean equals(ExpenseEvent anotherEvent){
        if(this.category.equals(anotherEvent.getCategory())){
            if(this.categoryType.equals(anotherEvent.getCategoryType())){
                if(this.theDate.equals(anotherEvent.getTheDate())){
                    if(this.eventTitle.equals(anotherEvent.getEventTitle())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the eventTitle
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * @param eventTitle the eventTitle to set
     */
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    /**
     * @return the expenseAmount
     */
    public double getExpenseAmount() {
        return expenseAmount;
    }
    
    public ObservableValue getExpenseAmountProperty(){
        return new SimpleDoubleProperty(expenseAmount);
    }

    /**
     * @param expenseAmount the expenseAmount to set
     */
    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    /**
     * @return the categoryProperty
     */
    public StringProperty getCategoryProperty() {
        return categoryProperty;
    }

    /**
     * @param categoryProperty the categoryProperty to set
     */
    public void setCategoryProperty(StringProperty categoryProperty) {
        this.categoryProperty = categoryProperty;
    }

    /**
     * @return the eventTitleProperty
     */
    public StringProperty getEventTitleProperty() {
        return eventTitleProperty;
    }

    /**
     * @param eventTitleProperty the eventTitleProperty to set
     */
    public void setEventTitleProperty(StringProperty eventTitleProperty) {
        this.eventTitleProperty = eventTitleProperty;
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

    /**
     * @return the theDate
     */
    public LocalDate getTheDate() {
        return theDate;
    }

    /**
     * @param theDate the theDate to set
     */
    public void setTheDate(LocalDate theDate) {
        this.theDate = theDate;
    }
    
}
