/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;
import java.util.ArrayList;

/**
 *
 * @author Phil
 */
public class CategoryList {
    
    ArrayList<Category> theCategoryList;
    
    public CategoryList(){
        theCategoryList = new ArrayList();
    }
    
    public void setTheCategoryList(ArrayList<Category> aCategoryList){
        theCategoryList = aCategoryList;
    }
    
    public ArrayList<Category> getTheCategoryList(){
        return theCategoryList;
    }
    
}
