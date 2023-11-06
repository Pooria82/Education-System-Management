package TheWholeProject.AdministrativeParts;

import TheWholeProject.Lessons.Lesson;
import TheWholeProject.Users.UsersInfoAndFrame.Chairman;
import TheWholeProject.Users.UsersInfoAndFrame.Professor;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class AcademicDepartment implements Serializable {
    private String name;
    private Chairman chairman_name;
    private ArrayList<Professor> professors_list = new ArrayList<>();
    private ArrayList<Student> students_list = new ArrayList<>();
    private ArrayList<Lesson> lessons_list = new ArrayList<>();

    public AcademicDepartment(String name, Chairman chairman_name) {
        this.name = name;
        this.chairman_name = chairman_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chairman getChairman_name() {
        return chairman_name;
    }

    public void setChairman_name(Chairman chairman_name) {
        this.chairman_name = chairman_name;
    }
    public void addProfessor(Professor professor){
        this.professors_list.add(professor);
    }

    public ArrayList<Professor> getProfessors_list() {
        return professors_list;
    }

    public ArrayList<Student> getStudents_list() {
        return students_list;
    }

    public void addStudent(Student student){
        this.students_list.add(student);
    }

    public ArrayList<Lesson> getLessons_list() {
        return lessons_list;
    }
    public void addLesson(Lesson lesson){
        this.lessons_list.add(lesson);
    }
}
