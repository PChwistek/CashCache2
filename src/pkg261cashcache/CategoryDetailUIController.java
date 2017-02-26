/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
     */
    
    @FXML private Label theCategoryName;
    @FXML private Label theCategoryType;
    @FXML private TextField theCategoryAllowance;
    @FXML private Button saveButton;
    @FXML private Slider allowanceSlider;
    
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
        allowanceSlider.setMajorTickUnit(selectedCat.getAllowanceProperty().getValue());
    }
    
    public void setAllowance(){
        try{
            ObservableList<Category> theList = theBudgetOverviewCntl.getTheBudgetOverview().getTheListOfCategories();
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
    
    @FXML private void handleSave(){
        setAllowance();
        theBudgetOverviewCntl.updateCategoryUI();
    }
    
    @FXML private void handleSlider(){
        theCategoryAllowance.setText("" + Math.round(allowanceSlider.getValue()));
    }
    
    @FXML private void handleAllowanceTextField(){ // lots of defensive programming here
        try{
            if(Double.parseDouble(theCategoryAllowance.getText()) > 0 && Double.parseDouble(theCategoryAllowance.getText()) < allowanceSlider.getMax()){
                allowanceSlider.setValue(Double.parseDouble(theCategoryAllowance.getText()));
            } else if (Double.parseDouble(theCategoryAllowance.getText()) < 0){
                allowanceSlider.setValue(0);
                theCategoryAllowance.setText("" + 0);
            } else if(Double.parseDouble(theCategoryAllowance.getText()) > allowanceSlider.getMax()){
                allowanceSlider.setValue(allowanceSlider.getMax());
                theCategoryAllowance.setText("" + allowanceSlider.getMax());
            }

        } catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Input");
            alert.setHeaderText("Please make sure you entered a valid allowanmce");
            alert.setContentText("Make sure to properly enter all your information");
            alert.showAndWait();    
        }
    }
    
    private void handleUserAllocationChart(){
        
    }
    
    private void handleRecommendedChart(){
        
    }
}
