package pkg261cashcache;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class PersistentDataController {
    
    private final Gson gson = new GsonBuilder().create();
    private BudgetOverview importedBudget;
    //private String[] importedProperties = SerializedCollectionController.TheSerializedCollectionController.properties();
    
    public void exportList(BudgetOverview budget) {
        String json = gson.toJson(budget);
        try{
            FileWriter writer = new FileWriter("data.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BudgetOverview importList() {
        
        try {
            FileReader reader = new FileReader("data.json");
            JsonReader jsonReader = new JsonReader(reader);
            importedBudget = gson.fromJson(jsonReader, BudgetOverview.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return importedBudget;
        
    }
    
}
