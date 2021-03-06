/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author mjg5683
 */
public class Main extends Application {
    
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private AnchorPane categoryListLayout;
    private PersistentDataController theDataCntl;
    private BudgetOverviewUIController theBudgetOverviewCntl;
    
    
    @Override
    public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("CashCache");
      this.primaryStage.setResizable(false);
      initRootLayout();
    }
    
    public void initRootLayout()  {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("BudgetOverviewUI.fxml"));
            rootLayout = loader.load();
            theBudgetOverviewCntl = loader.getController();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> { theBudgetOverviewCntl.saveBudgetOverview();});
            
        } catch (IOException e){
             e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
