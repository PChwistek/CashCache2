/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Philz zee Kill
 */
public class CreateNewExpenseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private CalendarViewUIController theCalendarCntl;
    @FXML private Label theCategoryType;
    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox listOfRespectiveCategories;
    @FXML private TextField expenseTitle;
    @FXML private TextField expenseAmount;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    public void setCalendarCntl(CalendarViewUIController calendarCntl){
        this.theCalendarCntl = calendarCntl;
    }
    
    public void updateLabel(String type){
        theCategoryType.setText(type);
        populateComboBox();
    }
    
    private void populateComboBox(){
        ObservableList<Category> theListOfCategories = FXCollections.observableArrayList(theCalendarCntl.getTheBudgetOverviewUICntl().getTheBudgetOverview().getTheCategoryList().getTheListofCategories());
        
        ObservableList<String> refinedObservableList;
        ArrayList<String> refinedArrayList = new ArrayList();
        
        for(Category cat: theListOfCategories){
            System.out.println(cat.getCategoryType());
            if(cat.getCategoryType().equals(theCategoryType.getText())){
                refinedArrayList.add(cat.getCategoryTitle().getValue());
                System.out.println(cat.getCategoryTitle().getValue());
            }
        }
        refinedObservableList = FXCollections.observableArrayList(refinedArrayList);
        listOfRespectiveCategories.setItems(refinedObservableList);
    }
    
    @FXML
    private void handleCancelButton(){
        theCalendarCntl.handleCancelExpenseWindow();
    }
    
    @FXML
    private void handleCreateButton(){
        try{
            ExpenseEvent theExpenseEvent = new ExpenseEvent(listOfRespectiveCategories.getValue().toString(),theCategoryType.getText(), expenseTitle.getText(), Double.parseDouble(expenseAmount.getText()), theCalendarCntl.getSelectedDate());
            theCalendarCntl.handleCreateExpense(theExpenseEvent);
            handleCancelButton();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Input");
            alert.setHeaderText("Please enter all fields");
            alert.setContentText("Make sure to properly enter all your information");
            alert.showAndWait();
        }
    }
}
