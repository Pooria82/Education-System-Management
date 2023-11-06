import TheWholeProject.AdministrativeParts.AcademicDepartment;
import TheWholeProject.AdministrativeParts.Faculty;
import TheWholeProject.AdministrativeParts.University;
import TheWholeProject.Data.DataBase;
import TheWholeProject.Frames.MyMenu;
import TheWholeProject.Users.UsersInfoAndFrame.Chairman;
import TheWholeProject.Users.UsersInfoAndFrame.Professor;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DataBase dataBase = loadDatabase();

        new MyMenu(dataBase);
    }

    private static DataBase loadDatabase() throws IOException, ClassNotFoundException {
        String fileName = "Data Base.txt";
        File file = new File(fileName);
        DataBase dataBase = new DataBase();
        if (file.exists()) {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            dataBase = (DataBase) ois.readObject();
            ois.close();
            return dataBase;
        } else {
            University university = new University("aaa", "aaa", "aaa");
            Faculty faculty = new Faculty("bbb", "bbb");
            Chairman chairman = new Chairman("chairman", "chairman", "chairman", "chairman");
            AcademicDepartment academicDepartment = new AcademicDepartment("ccc", chairman);
            Professor professor = new Professor("pro", "pro", "pro", "pro");
            Student student = new Student("student", "student", "student", "student");

            academicDepartment.addStudent((student));
            academicDepartment.addProfessor(professor);
            faculty.addDisciplines(academicDepartment);
            university.addFaculty(faculty);
            dataBase.addUniversity(university);
            dataBase.saveData();
            return new DataBase();
        }
    }
}