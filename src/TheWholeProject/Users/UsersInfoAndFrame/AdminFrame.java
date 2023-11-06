package TheWholeProject.Users.UsersInfoAndFrame;

import TheWholeProject.Data.DataBase;
import TheWholeProject.Users.AdminMethodsFrames.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame implements ActionListener {
    private JButton addUniversityButton, deleteUniversityButton;
    private JButton addFacultyButton, deleteFacultyButton;
    private JButton addAcademicDepartmentButton, deleteAcademicDepartmentButton;
    private JButton addStudentButton, deleteStudentButton;
    private JButton addProfessorButton, deleteProfessorButton;
    private JButton addLessonButton, deleteLessonButton;
    private JLabel titleLabel;
    private DataBase dataBase;

    public AdminFrame(DataBase dataBase) {
        this.dataBase = dataBase;
        setTitle("Education System Management");
        setSize(800, 800);
        setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        addUniversityButton = new JButton("Add University");
        deleteUniversityButton = new JButton("Delete University");
        addFacultyButton = new JButton("Add Faculty");
        deleteFacultyButton = new JButton("Delete Faculty");
        addAcademicDepartmentButton = new JButton("Add Academic Department");
        deleteAcademicDepartmentButton = new JButton("Delete Academic Department");
        addStudentButton = new JButton("Add Student");
        deleteStudentButton = new JButton("Delete Student");
        addProfessorButton = new JButton("Add Professor");
        deleteProfessorButton = new JButton("Delete Professor");
        addLessonButton = new JButton("Add Lesson");
        deleteLessonButton = new JButton("Delete Lesson");

        addUniversityButton.addActionListener(this);
        deleteUniversityButton.addActionListener(this);
        addFacultyButton.addActionListener(this);
        deleteFacultyButton.addActionListener(this);
        addAcademicDepartmentButton.addActionListener(this);
        deleteAcademicDepartmentButton.addActionListener(this);
        addStudentButton.addActionListener(this);
        deleteStudentButton.addActionListener(this);
        addProfessorButton.addActionListener(this);
        deleteProfessorButton.addActionListener(this);
        addLessonButton.addActionListener(this);
        deleteLessonButton.addActionListener(this);

        buttonPanel.add(addUniversityButton);
        buttonPanel.add(deleteUniversityButton);
        buttonPanel.add(addFacultyButton);
        buttonPanel.add(deleteFacultyButton);
        buttonPanel.add(addAcademicDepartmentButton);
        buttonPanel.add(deleteAcademicDepartmentButton);
        buttonPanel.add(addStudentButton);
        buttonPanel.add(deleteStudentButton);
        buttonPanel.add(addProfessorButton);
        buttonPanel.add(deleteProfessorButton);
        buttonPanel.add(addLessonButton);
        buttonPanel.add(deleteLessonButton);

        titleLabel = new JLabel("Welcome to the Admin page");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        add(contentPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addUniversityButton){
            AddUniversity adddUniversity = new AddUniversity(this.dataBase);
        } else if (e.getSource() == deleteUniversityButton) {
            DeleteUniversity deleteUniversity = new DeleteUniversity(this.dataBase);
        }else if (e.getSource() == addFacultyButton) {
            AddFaculty addFaculty = new AddFaculty(this.dataBase);
        }else if (e.getSource() == deleteFacultyButton) {

        }else if (e.getSource() == addAcademicDepartmentButton) {
            AddAcademicDepartment addAcademicDepartment = new AddAcademicDepartment(this.dataBase);
        }else if (e.getSource() == deleteAcademicDepartmentButton) {

        }else if (e.getSource() == addStudentButton) {
            AddStudent addStudent = new AddStudent(this.dataBase);

        }else if (e.getSource() == deleteStudentButton) {

        }else if (e.getSource() == addProfessorButton) {
            AddProfessor addProfessor = new AddProfessor(this.dataBase);

        }else if (e.getSource() == deleteProfessorButton) {

        }else if (e.getSource() == addLessonButton) {
            AddLesson addLesson = new AddLesson(this.dataBase);
        }else if (e.getSource() == deleteLessonButton) {

        }
    }
}
