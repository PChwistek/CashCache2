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
    private StringProperty retirementAccountType; //this should be enumerator
    private IntegerProperty age;
    
    public UserPreferences(){
        
    }
   
}
