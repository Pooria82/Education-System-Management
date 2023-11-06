package TheWholeProject.Users.UsersInfoAndFrame;

import TheWholeProject.AdministrativeParts.AcademicDepartment;
import TheWholeProject.AdministrativeParts.Faculty;
import TheWholeProject.AdministrativeParts.University;
import TheWholeProject.Data.DataBase;
import TheWholeProject.Users.ChairmanMethodsFrames.RemoveStudentFromCourse;
import TheWholeProject.Users.ProfessorMethodsFrames.ChangeStudentsGrades;
import TheWholeProject.Users.ProfessorMethodsFrames.RecordStudentsGrades;
import TheWholeProject.Users.ProfessorMethodsFrames.RemoveStudent_P;
import TheWholeProject.Users.ProfessorMethodsFrames.ViewStudentsReportCards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfessorFrame extends JFrame implements ActionListener {
    private JLabel firstNameLabel, lastNameLabel, usernameLabel, passwordLabel, coursesLabel;
    private JList coursesList;
    private JScrollPane coursesScrollPane;
    private JButton removeStudentButton, viewReportCardsButton, recordGradesButton, changeGradesButton;
    private DataBase dataBase;
    private String university_name;
    private String faculty_name;
    private String academicDepartment_name;
    private Professor professor;
    private AcademicDepartment academicDepartment_obj;

    public ProfessorFrame(Professor professor, DataBase dataBase) {
        this.dataBase = dataBase;
        this.professor = professor;
        setTitle("Professor");
        setSize(800, 600);
        setLocationRelativeTo(null);

        for(University university : dataBase.getUniversity_list()){
            for(Faculty faculty : university.getFaculty_list()){
                for(AcademicDepartment academicDepartment : faculty.disciplines_list){
                    for(Professor professor1 : academicDepartment.getProfessors_list()){
                        if(professor1.equals(professor)){
                            university_name = university.getName();
                            faculty_name = faculty.getName();
                            academicDepartment_name = academicDepartment.getName();
                            academicDepartment_obj = academicDepartment;
                        }
                    }
                }
            }
        }

        firstNameLabel = new JLabel("First Name: " + professor.getFirst_name());
        lastNameLabel = new JLabel("Last Name: " + professor.getLast_name());
        usernameLabel = new JLabel("Username: " + professor.getUser_name());
        passwordLabel = new JLabel("Password: " + professor.getPassword());
        coursesLabel = new JLabel("List of Courses:");

        String[] lessonsList = new String[professor.getLessons_list().size()];
        for (int i = 0; i < professor.getLessons_list().size(); i++) {
            lessonsList[i] = professor.getLessons_list().get(i).getName();
        }
        coursesList = new JList(lessonsList);
        coursesScrollPane = new JScrollPane(coursesList);

        removeStudentButton = new JButton("Remove Student");
        viewReportCardsButton = new JButton("View Students' Report Cards");
        recordGradesButton = new JButton("Record Students' Grades");
        changeGradesButton = new JButton("Change Students' Grades");

        setLayout(new GridLayout(10, 1));
        add(firstNameLabel);
        add(lastNameLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(coursesLabel);
        add(coursesScrollPane);
        add(removeStudentButton);
        add(viewReportCardsButton);
        add(recordGradesButton);
        add(changeGradesButton);

        removeStudentButton.addActionListener(this);
        viewReportCardsButton.addActionListener(this);
        recordGradesButton.addActionListener(this);
        changeGradesButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeStudentButton) {
            new RemoveStudent_P(dataBase , university_name , faculty_name , academicDepartment_name ,professor);
        } else if (e.getSource() == viewReportCardsButton) {
            new ViewStudentsReportCards(academicDepartment_obj.getStudents_list());
        } else if (e.getSource() == recordGradesButton) {
            new RecordStudentsGrades(dataBase , university_name , faculty_name , academicDepartment_name ,professor);
        } else if (e.getSource() == changeGradesButton) {
            new ChangeStudentsGrades(dataBase , university_name , faculty_name , academicDepartment_name ,professor);
        }
    }
}
