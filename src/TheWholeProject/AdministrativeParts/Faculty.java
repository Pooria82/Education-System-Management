package TheWholeProject.AdministrativeParts;

import java.io.Serializable;
import java.util.ArrayList;

public class Faculty implements Serializable {
    private String name;
    private String boss_name;
    public ArrayList<AcademicDepartment> disciplines_list = new ArrayList<>();

    public Faculty(String name, String boss_name) {
        this.name = name;
        this.boss_name = boss_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoss_name() {
        return boss_name;
    }

    public void setBoss_name(String boss_name) {
        this.boss_name = boss_name;
    }

    public ArrayList<AcademicDepartment> getDisciplines_list() {
        return disciplines_list;
    }
    public void addDisciplines(AcademicDepartment academicDepartment){
        this.disciplines_list.add(academicDepartment);
    }
}
