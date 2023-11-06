package TheWholeProject.Data;

import TheWholeProject.AdministrativeParts.University;
import TheWholeProject.FindServices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class DataBase implements Serializable {
    private ArrayList<University> university_list = new ArrayList<>();

    public ArrayList<University> getUniversity_list() {
        return university_list;
    }

    public void addUniversity(University university){
        this.university_list.add(university);
    }
    public void saveData(){
        String fileName= "Data Base.txt";
        File file = new File(fileName);
        try {
            if(file.createNewFile()){
                FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this);
                oos.close();
            }
            else {
                FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this);
                oos.close();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
