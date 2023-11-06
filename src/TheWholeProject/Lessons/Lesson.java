package TheWholeProject.Lessons;

import TheWholeProject.Users.UsersInfoAndFrame.Professor;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class Lesson implements Serializable {
    private Professor professor;
    private String name;
    private String code;
    private int num_of_units;
    private int capacity;
    private double grade = -1;
    private ArrayList<Student> students_list = new ArrayList<>();
    public void remaining_capacity(){            //fix

    }

    public Lesson(Professor professor, String name, String code, int num_of_units, int capacity) {
        this.professor = professor;
        this.name = name;
        this.code = code;
        this.num_of_units = num_of_units;
        this.capacity = capacity;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNum_of_units() {
        return num_of_units;
    }

    public void setNum_of_units(int num_of_units) {
        this.num_of_units = num_of_units;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public ArrayList<Student> getStudents_list() {
        return students_list;
    }

    public void addStudents_list(Student student){
        this.students_list.add(student);
    }
}
