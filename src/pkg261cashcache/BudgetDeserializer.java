/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.lang.reflect.Type;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 *
 * @author Philz zee Kill
 */
public class BudgetDeserializer implements JsonDeserializer<BudgetOverview>{
    
    @Override
    public BudgetOverview deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
        throws JsonParseException {
        
        final BudgetOverview budget = new BudgetOverview();
        
        final JsonObject jsonObject = json.getAsJsonObject();
        
        final JsonElement jsonAccountType = jsonObject.get("theSavingsAccountType");
        final String accountType = jsonAccountType.getAsString();
        System.out.println(accountType);
        
        
        BudgetOverview importedBudgetOverview = new BudgetOverview();
        return importedBudgetOverview;
        }
    }
