/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Phil
 */
public class CategoryDetailUIController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     * Whenever a user clicks on a category from the table on the left, this controller is instantiated... 
     * It allows users to set an allowance and shows recommended allocations 
     * 
     */
    
    @FXML private Label theCategoryName;
    @FXML private Label theCategoryType;
    @FXML private TextField theCategoryAllowance;
    @FXML private Slider allowanceSlider;
    @FXML private Button deleteButton;
    @FXML private Button editButton;
    @FXML private PieChart recommended;
    @FXML private PieChart userAlloc;
    
    private ObservableList<Data> recommendedExpenseCat = FXCollections.observableArrayList();
    private ObservableList<Data> userExpenseCat = FXCollections.observableArrayList();
    private BudgetOverviewUIController theBudgetOverviewCntl;
    private Category selectedCat;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }   
    
    public void setSelectedCategory(Category aCategory){
        selectedCat = aCategory;
        theCategoryName.setText(selectedCat.getCategoryTitle().getValue());
        theCategoryType.setText(selectedCat.getCategoryType());
        theCategoryAllowance.setText("" + selectedCat.getAllowanceProperty().getValue());
        
        allowanceSlider.setMin(0);
        allowanceSlider.setMax(theBudgetOverviewCntl.calculateRemainingFunds());
        allowanceSlider.setValue(selectedCat.getAllowanceProperty().getValue());
       
        if (theBudgetOverviewCntl.calculateRemainingFunds() == 0){
            allowanceSlider.setMin(0);
            allowanceSlider.setMax(selectedCat.getAllowanceProperty().getValue());
            allowanceSlider.setValue(selectedCat.getAllowanceProperty().getValue());
        } else if (theBudgetOverviewCntl.calculateRemainingFunds() > 0){
            allowanceSlider.setMin(0);
            allowanceSlider.setMax(selectedCat.getAllowanceProperty().getValue() + theBudgetOverviewCntl.calculateRemainingFunds());
            allowanceSlider.setValue(selectedCat.getAllowanceProperty().getValue());
        } else if (theBudgetOverviewCntl.calculateRemainingFunds() < 0) {
        
        }
        
        setRecommendedChart();
        setUserAllocationChart();
    }
    
    public void setAllowance(){
        try{
            ObservableList<Category> theList = FXCollections.observableArrayList(theBudgetOverviewCntl.getTheBudgetOverview().getTheCategoryList().getTheListofCategories());
            int listSize = theList.size();
            
            for(int i = 0; i < listSize ; i++){
                if(theList.get(i).isEqual(selectedCat)){
                    theList.get(i).setAllowance(Double.parseDouble(theCategoryAllowance.getText()));
                }
            }
                    
        } catch (Exception e ){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Input");
            alert.setHeaderText("Please make sure you entered a valid allowanmce");
            alert.setContentText("Make sure to properly enter all your information");
            alert.showAndWait();          
        }
    }
    
    public void setBudgetOverviewCntl(BudgetOverviewUIController aBudgetOverviewCntl){
        this.theBudgetOverviewCntl = aBudgetOverviewCntl;
    }    
    
    @FXML private void handleSlider(){
        theCategoryAllowance.setText("" + Math.round(allowanceSlider.getValue()));
        setAllowance();
        setUserAllocationChart();
    }
    
    @FXML private void handleSliderUpdate(){
        theBudgetOverviewCntl.getCategoryListUIController().clearSelections();
        theBudgetOverviewCntl.getCategoryListUIController().updateFundsRemaining();
    }
    
    @FXML private void handleAllowanceTextField(){ // lots of defensive programming here
        try{
            if (Double.parseDouble(theCategoryAllowance.getText()) < theBudgetOverviewCntl.getMonthlyIncome()) {
                setAllowance();
                setUserAllocationChart();
                theBudgetOverviewCntl.updateCategoryUI();
                allowanceSlider.setValue(Double.parseDouble(theCategoryAllowance.getText()));
            } else {
                throw new Exception();
            }
        } catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Input");
            alert.setHeaderText("Please make sure you entered a valid allowance");
            alert.setContentText("Make sure to properly enter all your information");
            alert.showAndWait();    
        }
    }
    
    private void setUserAllocationChart(){ //user pie chart data
        ObservableList<Category> theListOfCategories = FXCollections.observableArrayList(theBudgetOverviewCntl.getTheBudgetOverview().getTheCategoryList().getTheListofCategories());
        double fixedCostAllowance = 0;
        double flexSpendingAllowance = 0;
        double savingsAllowance = 0;
        if(userExpenseCat.size() == 0){
            userExpenseCat.add(new Data("Fixed Costs", fixedCostAllowance));
            userExpenseCat.add(new Data("Flexible Spending", flexSpendingAllowance));
            userExpenseCat.add(new Data("Savings", savingsAllowance)); 
            userExpenseCat.add(new Data("Unused Funds", theBudgetOverviewCntl.calculateRemainingFunds()));
        }
        double total = theBudgetOverviewCntl.getMonthlyIncome();
        
        for(Category cat: theListOfCategories){
            switch(cat.getCategoryType()){
                case "Fixed Costs":
                    fixedCostAllowance += cat.getAllowanceProperty().getValue();
                    break;
                case "Flexible Spending":
                    flexSpendingAllowance += cat.getAllowanceProperty().getValue();
                    break;
                case "Savings":
                    savingsAllowance += cat.getAllowanceProperty().getValue();
                    break;
                default:
                    break;
            }      
        }
        
        fixedCostAllowance = fixedCostAllowance/total;
        flexSpendingAllowance = flexSpendingAllowance/total;
        savingsAllowance = savingsAllowance/total;
               
        
        userExpenseCat.get(0).setPieValue(fixedCostAllowance);
        userExpenseCat.get(1).setPieValue(flexSpendingAllowance);
        userExpenseCat.get(2).setPieValue(savingsAllowance);
        userExpenseCat.get(3).setPieValue(theBudgetOverviewCntl.calculateRemainingFunds()/total);
        userAlloc.setData(userExpenseCat);
        userAlloc.setLegendVisible(false);

    }
    
    private void setRecommendedChart(){ // rec chart data
        recommendedExpenseCat.add(new Data("Fixed Costs", 50));
        recommendedExpenseCat.add(new Data("Flexible Spending", 30));
        recommendedExpenseCat.add(new Data("Savings", 20));
        recommendedExpenseCat.add(new Data("Unused Funds", 0));
        recommended.setData(recommendedExpenseCat);
    }
    
    @FXML private void handleDeleteButton(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete?");
        alert.setHeaderText("Are you sure you want to delete this item?");
        alert.setContentText("Once deleted, this data cannot be retrieved");
        ButtonType okButton = new ButtonType("OK",  ButtonData.CANCEL_CLOSE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        Optional<ButtonType> result = alert.showAndWait();  
        if(result.get() == okButton){
            theBudgetOverviewCntl.deleteItem(selectedCat);
        }
    }
    


}
