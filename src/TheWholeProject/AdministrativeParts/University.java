package TheWholeProject.AdministrativeParts;

import java.io.Serializable;
import java.util.ArrayList;

public class University implements Serializable {
    ArrayList<Faculty> faculty_list = new ArrayList<>();

    public ArrayList<Faculty> getFaculty_list() {
        return faculty_list;
    }
    public void addFaculty(Faculty faculty){
        this.faculty_list.add(faculty);
    }

    private String name;
    private String type;
    private String address;

    public University(String name, String type, String address) {
        this.name = name;
        this.type = type;
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
