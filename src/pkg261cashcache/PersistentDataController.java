package pkg261cashcache;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class PersistentDataController {

    private final String THE_SERIALIZED_DATA_FILE_NAME = "serializedData.ser";
    private final String EXTERNAL_DATA_PATH = "";
    private BudgetOverview theSerializedDataModel = null;
    private static PersistentDataController thePersistantDataCntl;

    private PersistentDataController() {
        System.out.println("created");

        this.readSerializedDataFile();
        if (theSerializedDataModel == null) {
            theSerializedDataModel = new BudgetOverview(new CategoryList(0.0), new Paycheck(LocalDate.now(), 0));
            theSerializedDataModel.getThePaycheck().setFrequency(1);
            this.writeSerializedDataFile(theSerializedDataModel);
            this.readSerializedDataFile();
        }
    }

    public static PersistentDataController getSerializedDataCntl() {
        if (thePersistantDataCntl == null) {
            thePersistantDataCntl = new PersistentDataController();
        }
        return thePersistantDataCntl;
    }

    public void readSerializedDataFile() {
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {

            fis = new FileInputStream(THE_SERIALIZED_DATA_FILE_NAME);
            in = new ObjectInputStream(fis);
            theSerializedDataModel = (BudgetOverview) in.readObject();
            if (theSerializedDataModel == null) {
                System.out.println("File empty");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void writeSerializedDataFile(BudgetOverview theBudgetOverview) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(THE_SERIALIZED_DATA_FILE_NAME);
            out = new ObjectOutputStream(fos);
            out.writeObject(theBudgetOverview);
            out.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public BudgetOverview getSerializedDataModel() {
        return theSerializedDataModel;
    }

}
