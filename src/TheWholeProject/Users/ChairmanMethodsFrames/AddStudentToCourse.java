package TheWholeProject.Users.ChairmanMethodsFrames;


import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;
import TheWholeProject.Lessons.Lesson;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import javax.swing.*;
import java.awt.*;

/*
username
course
 */
public class AddStudentToCourse extends JFrame implements ControlUnits , FindServices {
    private JTextField studentUsernameField, courseNameField;
    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;

    public AddStudentToCourse(DataBase dataBase, String university, String faculty, String academic_department){
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        setTitle("Add Student To Course");
        setLocationRelativeTo(null);
        setVisible(true);
        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        JLabel label = new JLabel("Enter the name of the course and student to be added:");
        label.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel studentUsernameLabel = new JLabel("Student Username:");
        studentUsernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        studentUsernameField = new JTextField();
        studentUsernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        courseNameField = new JTextField();
        courseNameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton addButton = new JButton("ADD");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));

        addButton.addActionListener(e -> {
            try {
                ADD();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(label);
        panel.add(new JLabel()); // Empty cell for spacing
        panel.add(studentUsernameLabel);
        panel.add(studentUsernameField);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        add(panel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
        pack();
    }

    private void ADD(){
        find_student();
    }

    @Override
    public boolean control(Student student , Lesson lesson) {
        int numberOfUnits = lesson.getNum_of_units();
        for (Lesson lesson1 : student.getCourses()){
            numberOfUnits += lesson1.getNum_of_units();
        }
        if (numberOfUnits > 20){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void find_professor() {

    }

    @Override
    public void find_student() {
        String studentUsername = studentUsernameField.getText();
        String courseName = courseNameField.getText();
        boolean foundU = false;
        boolean foundF = false;
        boolean foundA = false;
        boolean foundS = false;
        boolean foundC = false;

        for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
            if (dataBase.getUniversity_list().get(i).getName().equals(university)) {
                foundU = true;
                for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(faculty)) {
                        foundF = true;
                        for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++) {
                            if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName().equals(academic_department)) {
                                foundA = true;
                                Student student = null;
                                Lesson lesson = null;
                                for (int l = 0 ; l < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().size() ; l++) {
                                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getUser_name().equals(studentUsername)){
                                        foundS = true;
                                        student = dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l);
                                    }
                                }
                                for(int l = 0 ; l < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().size() ; l++){
                                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).getName().equals(courseName)){
                                        foundC = true;
                                        lesson = dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l);
                                    }
                                }
                                if(foundC && foundS){
                                    if (control(student , lesson)){
                                        student.addCourse(lesson);
                                        lesson.addStudents_list(student);
                                        JOptionPane.showMessageDialog(this, "Student has been added successfully!");
                                        dataBase.saveData();
                                        dispose();
                                    }else {
                                        JOptionPane.showMessageDialog(this, "The number of student units is more than 20!");
                                    }
                                }

                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        if (!foundU) {
            JOptionPane.showMessageDialog(this, "University not found!");
            dispose();
        } else if (!foundF) {
            JOptionPane.showMessageDialog(this, "Faculty not found!");
            dispose();
        } else if (!foundA) {
            JOptionPane.showMessageDialog(this, "Academic Department not found!");
            dispose();
        }
        else if (!foundS) {
            JOptionPane.showMessageDialog(this, "Student not found!");
            dispose();
        }
        else if (!foundC) {
            JOptionPane.showMessageDialog(this, "Lesson not found!");
            dispose();
        }
    }
}
