package TheWholeProject.Users.UsersInfoAndFrame;

import TheWholeProject.AdministrativeParts.AcademicDepartment;
import TheWholeProject.AdministrativeParts.Faculty;
import TheWholeProject.AdministrativeParts.University;
import TheWholeProject.Data.DataBase;
import TheWholeProject.Users.ChairmanMethodsFrames.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChairmanFrame extends JFrame implements ActionListener {
    private JLabel firstNameLabel;
    private JLabel firstNameShowLabel;
    private JLabel lastNameLabel;
    private JLabel lastNameShowLabel;
    private JLabel userNameLabel;
    private JLabel userNameShowLabel;
    private JLabel passwordLabel;
    private JLabel passwordShowLabel;
    private JButton addTeacherButton;
    private JButton showTeachersButton;
    private JButton editTeacherInfoButton;
    private JButton removeTeacherButton;
    private JButton addCourseButton;
    private JButton showCoursesButton;
    private JButton editCoursesButton;
    private JButton deleteCoursesButton;
    private JButton addStudentButton;
    private JButton showStudentsButton;
    private JButton editStudentInfoButton;
    private JButton deleteStudentButton;
    private JButton addStudentToCourseButton;
    private JButton removeStudentFromCourseButton;
//    private JButton controlUnitsButton;
    private JButton viewStudentsRecordsButton;
    private DataBase dataBase;
    private String university_name;
    private String faculty_name;
    private String academicDepartment_name;
    private University university_obj;
    private Faculty faculty_obj;
    private AcademicDepartment academicDepartment_obj;

    public ChairmanFrame(Chairman chairman, DataBase dataBase) {
        for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
            for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++) {
                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getChairman_name().equals(chairman)) {
                        university_obj = dataBase.getUniversity_list().get(i);
                        university_name = dataBase.getUniversity_list().get(i).getName();
                        faculty_obj = dataBase.getUniversity_list().get(i).getFaculty_list().get(j);
                        faculty_name = dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName();
                        academicDepartment_obj = dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k);
                        academicDepartment_name = dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName();

                    }
                }
            }
        }
        setTitle("Chairman");

        setSize(800, 800);

        setLocationRelativeTo(null);
        this.dataBase = dataBase;

        firstNameLabel = new

                JLabel("First Name: " + chairman.getFirst_name());
        firstNameShowLabel = new

                JLabel();

        lastNameLabel = new

                JLabel("Last Name: " + chairman.getLast_name());
        lastNameShowLabel = new

                JLabel();

        userNameLabel = new

                JLabel("User Name: " + chairman.getUser_name());
        userNameShowLabel = new

                JLabel();

        passwordLabel = new

                JLabel("Password: " + chairman.getPassword());
        passwordShowLabel = new

                JLabel();

        addTeacherButton = new JButton("Add Professor");
        addTeacherButton.addActionListener(this);

        showTeachersButton = new JButton("Show Professor");
        showTeachersButton.addActionListener(this);

        editTeacherInfoButton = new JButton("Edit Professor Info");
        editTeacherInfoButton.addActionListener(this);

        removeTeacherButton = new JButton("Remove Professor");
        removeTeacherButton.addActionListener(this);

        addCourseButton = new JButton("Add Lesson");
        addCourseButton.addActionListener(this);

        showCoursesButton = new JButton("Show Lessons");
        showCoursesButton.addActionListener(this);

        editCoursesButton = new JButton("Edit Lessons");
        editCoursesButton.addActionListener(this);

        deleteCoursesButton = new JButton("Remove Lessons");
        deleteCoursesButton.addActionListener(this);

        addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(this);

        showStudentsButton = new JButton("Show Students");
        showStudentsButton.addActionListener(this);

        editStudentInfoButton = new JButton("Edit Student Info");
        editStudentInfoButton.addActionListener(this);

        deleteStudentButton = new JButton("Remove Student");
        deleteStudentButton.addActionListener(this);

        addStudentToCourseButton = new JButton("Add Student to Lesson");
        addStudentToCourseButton.addActionListener(this);

        removeStudentFromCourseButton = new JButton("Remove Student from Lesson");
        removeStudentFromCourseButton.addActionListener(this);

//        controlUnitsButton = new JButton("Control Units");
//        controlUnitsButton.addActionListener(this);

        viewStudentsRecordsButton = new JButton("View Students Records");
        viewStudentsRecordsButton.addActionListener(this);

        setLayout(new GridLayout(12, 2));

        add(firstNameLabel);

        add(firstNameShowLabel);

        add(lastNameLabel);

        add(lastNameShowLabel);

        add(userNameLabel);

        add(userNameShowLabel);

        add(passwordLabel);

        add(passwordShowLabel);

        add(addTeacherButton);

        add(showTeachersButton);

        add(editTeacherInfoButton);

        add(removeTeacherButton);

        add(addCourseButton);

        add(showCoursesButton);

        add(editCoursesButton);

        add(deleteCoursesButton);

        add(addStudentButton);

        add(showStudentsButton);

        add(editStudentInfoButton);

        add(deleteStudentButton);

        add(addStudentToCourseButton);

        add(removeStudentFromCourseButton);

//        add(controlUnitsButton);

        add(viewStudentsRecordsButton);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTeacherButton) {
            new AddProfessor_C(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == showTeachersButton) {
            new ShowTeachers(academicDepartment_obj.getProfessors_list());
        }
        else if (e.getSource() == editTeacherInfoButton) {
            new EditTeacherInfo(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == removeTeacherButton) {
            new RemoveProfessor(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == addCourseButton) {
            new AddLesson_C(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == showCoursesButton) {
            new ShowCourses(academicDepartment_obj.getLessons_list());
        }
        else if (e.getSource() == editCoursesButton) {
            new EditCourses(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == deleteCoursesButton) {
            new RemoveCourses(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == addStudentButton) {
            new AddStudent_C(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == showStudentsButton) {
            new ShowStudents(academicDepartment_obj.getStudents_list());
        }
        else if (e.getSource() == editStudentInfoButton) {
            new EditStudentInfo(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == deleteStudentButton) {
            new RemoveStudent(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == addStudentToCourseButton) {
            new AddStudentToCourse(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == removeStudentFromCourseButton) {
            new RemoveStudentFromCourse(dataBase, university_name, faculty_name, academicDepartment_name);
        }
        else if (e.getSource() == viewStudentsRecordsButton) {
            new ViewStudentsRecords(academicDepartment_obj.getStudents_list());
        }
    }
}