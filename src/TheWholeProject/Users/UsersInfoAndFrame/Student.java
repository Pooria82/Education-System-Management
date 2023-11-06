package TheWholeProject.Users.UsersInfoAndFrame;

import TheWholeProject.Lessons.Lesson;
import TheWholeProject.Users.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable {

    private int passed_units;
    private int passed_semesters;
    private ArrayList<Lesson> courses = new ArrayList<>();

    public Student(String first_name, String last_name, String user_name, String password) {
        super(first_name, last_name, user_name, password);
    }

    public int getPassed_units() {
        return passed_units;
    }

    public void setPassed_units(int passed_units) {
        this.passed_units = passed_units;
    }

    public int getPassed_semesters() {
        return passed_semesters;
    }

    public void setPassed_semesters(int passed_semesters) {
        this.passed_semesters = passed_semesters;
    }

    public ArrayList<Lesson> getCourses() {
        return courses;
    }
    public void addCourse(Lesson lesson){
        this.courses.add(lesson);
    }
}
