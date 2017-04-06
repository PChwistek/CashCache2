/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Phil
 */
public class BudgetOverviewUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button thePreferencesButton;
    @FXML private Button theCalendarButton;
    @FXML private Button theMarketButton;
    @FXML private AnchorPane categoryListUI;
    @FXML private AnchorPane categoryDetailUIPanel;
    @FXML private AnchorPane logoPanel;
    @FXML private CategoryListUIController categoryListUIController;
    private CreateCategoryUIController createCatCntl;
    private CategoryDetailUIController catDetailCntl;
    private CalendarViewUIController calendarUICntl;
    private MarketUIController theMarketCntl;
    private Stage secondaryStage;
    private AnchorPane userPreferencesUI;
    private static BudgetOverview theBudgetOverview;
    private UserPreferencesUIController userPrefCntl;
    private PersistentDataController theDataCntl; 
    
    public BudgetOverviewUIController(){
                
        logoPanel = new AnchorPane();
        
        try{
            theDataCntl = PersistentDataController.getSerializedDataCntl();
            theBudgetOverview = theDataCntl.getSerializedDataModel();
        }catch(Exception e){
           e.printStackTrace();
        }
    
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getCategoryListUIController().setBudgetOverviewCntl(this);
          if(theBudgetOverview.getTheExpenseList().isEmpty() == false || theBudgetOverview.getTheCategoryList().getTheListofCategories().isEmpty() == false){
                theCalendarButton.setDisable(false);
          }
          
        logoPanel.getChildren().setAll(categoryDetailUIPanel.getChildren());
        categoryDetailUIPanel.getChildren().add(logoPanel);

    }    
    
    @FXML
    private void handlePreferencesButton(){
        try{
            Parent root;
            secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UserPreferencesUI.fxml"));
            root = loader.load();
            userPrefCntl = loader.getController();
            userPrefCntl.setBudgetOverviewCntl(this);
            secondaryStage.setScene(new Scene(root));
            secondaryStage.setTitle("User Preferences");
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.showAndWait(); 
            
        } catch (IOException e){
             e.printStackTrace();
        }
    }
    
    @FXML 
    private void handleMarketButton(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MarketUI.fxml"));
            categoryDetailUIPanel.getChildren().clear();
            categoryDetailUIPanel.getChildren().add(loader.load());
            theMarketCntl = loader.getController();
            theMarketCntl.setBudgetOverviewCntl(this);
            getCategoryListUIController().clearSelections();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML 
    private void handleCalendarButton(){
        
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CalendarViewUI.fxml"));
            categoryDetailUIPanel.getChildren().clear();
            categoryDetailUIPanel.getChildren().add(loader.load());
            calendarUICntl = loader.getController();
            calendarUICntl.setBudgetOverviewCntl(this);
            getCategoryListUIController().clearSelections();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void updateCategoryUI(){
        getCategoryListUIController().updateCategoryUI(this.getMonthlyIncome());
        
        
    }
    
    public Double getMonthlyIncome(){
        int aFrequency = 1;
        if(theBudgetOverview.getThePaycheck().getFrequency() == 0){
            aFrequency = 4;
        } else if (theBudgetOverview.getThePaycheck().getFrequency() == 1){
            aFrequency = 2;
        } else {
            aFrequency = 1;
        }
        return theBudgetOverview.getThePaycheck().getCheckAmount()* aFrequency;
    }
    
    public BudgetOverview getTheBudgetOverview(){
        return theBudgetOverview;
    }
    
    public void setMonthlyIncome(Double anIncome){
        theBudgetOverview.getThePaycheck().setCheckAmount(anIncome);
    }
    
    public void closePreferences(){
        secondaryStage.close();
    }
    
    public double calculateRemainingFunds(){
       double expenses = 0;
        for(int i = 0; i < theBudgetOverview.getTheCategoryList().getTheListofCategories().size(); i++){
            expenses += theBudgetOverview.getTheCategoryList().getTheListofCategories().get(i).getAllowanceProperty().getValue();
        }
        return getMonthlyIncome() - expenses;
    }


    public void setBudgetOverview(BudgetOverview aBudgetOverview){
        this.theBudgetOverview = aBudgetOverview;
        theDataCntl.writeSerializedDataFile(theBudgetOverview);
       
    }

    //RIGHT PANEL CODE ===============================================================================================================//
    public void setCreateNewCategoryUI(){
        try{      
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CreateCategoryUI.fxml"));
            categoryDetailUIPanel.getChildren().clear();
            categoryDetailUIPanel.getChildren().add(loader.load());
            createCatCntl = loader.getController();
            createCatCntl.setBudgetOverviewCntl(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void createCategory(Category aCategory){
        theBudgetOverview.getTheCategoryList().getTheListofCategories().add(aCategory);
        updateCategoryUI();
        theCalendarButton.setDisable(false);
        closeRightPanel();
    }
    
    public void closeRightPanel(){
        categoryDetailUIPanel.getChildren().clear();
        categoryDetailUIPanel.getChildren().add(logoPanel);
    }
    
    public void setDetailViewCategoryUI(Category aCat){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CategoryDetailUI.fxml"));
            categoryDetailUIPanel.getChildren().clear();
            categoryDetailUIPanel.getChildren().add(loader.load());
            catDetailCntl = loader.getController();            
            catDetailCntl.setBudgetOverviewCntl(this);
            catDetailCntl.setSelectedCategory(aCat);
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void deleteItem(Category cat){
        ArrayList<Category> theList = theBudgetOverview.getTheCategoryList().getTheListofCategories();
        int listSize = theList.size();
        for(int i = 0; i < listSize; i++){
            if(theList.get(i).isEqual(cat)){
                theList.remove(i);
            }
        }
        theBudgetOverview.setTheListOfCategories(theList);
        updateCategoryUI();
        closeRightPanel();
    }
    
    public void saveBudgetOverview(){
        theDataCntl.writeSerializedDataFile(theBudgetOverview);
    }

    /**
     * @return the categoryListUIController
     */
    public CategoryListUIController getCategoryListUIController() {
        return categoryListUIController;
    }

    /**
     * @param categoryListUIController the categoryListUIController to set
     */
    public void setCategoryListUIController(CategoryListUIController categoryListUIController) {
        this.categoryListUIController = categoryListUIController;
    }
    
    
    
}