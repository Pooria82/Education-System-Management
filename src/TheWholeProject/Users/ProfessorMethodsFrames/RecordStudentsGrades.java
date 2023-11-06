package TheWholeProject.Users.ProfessorMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;
import TheWholeProject.Lessons.Lesson;
import TheWholeProject.Users.UsersInfoAndFrame.Professor;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import javax.swing.*;
import java.awt.*;

public class RecordStudentsGrades extends  JFrame implements FindServices {
    private JTextField usernameField;
    private JTextField courseNameField;
    private JTextField gradeField;
    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;
    private Professor professor;

    public RecordStudentsGrades(DataBase dataBase, String university, String faculty, String academic_department , Professor professor){
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        this.professor = professor;
        setTitle("Record Students Grades");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submitForm());

        add(usernameLabel);
        add(usernameField);
        add(courseNameLabel);
        add(courseNameField);
        add(gradeLabel);
        add(gradeField);
        add(new JLabel());
        add(submitButton);

        setVisible(true);
    }

    private void submitForm() {
       find_student();
    }

    @Override
    public void find_professor() {}

    @Override
    public void find_student() {
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        this.professor = professor;

        String studentUsername = usernameField.getText();
        String courseName = courseNameField.getText();
        double grade = Double.parseDouble(gradeField.getText());
        boolean foundU = false;
        boolean foundF = false;
        boolean foundA = false;
        boolean foundS = false;
        boolean foundP = false;
        boolean foundL = false;

        for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
            if (dataBase.getUniversity_list().get(i).getName().equals(university)) {
                foundU = true;
                for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(faculty)) {
                        foundF = true;
                        for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++) {
                            if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName().equals(academic_department)) {
                                Student student = null;
                                Lesson lesson = null;
                                foundA = true;
                                for (int m = 0 ; m < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().size() ; m++){
                                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(m).getUser_name().equals(professor.getUser_name())){
                                        foundP = true;
                                        for (int n  = 0;n < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(m).getLessons_list().size() ; n++){
                                            if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(m).getLessons_list().get(n).getName().equals(courseName)){
                                                foundL = true;
                                                lesson = dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(m).getLessons_list().get(n);
                                                for (int p = 0 ; p < lesson.getStudents_list().size() ; p++){
                                                    if (lesson.getStudents_list().get(p).getUser_name().equals(studentUsername)){
                                                        foundS = true;
                                                        student = lesson.getStudents_list().get(p);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                for (int l = 0 ; l < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().size() ; l++) {
                                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getUser_name().equals(studentUsername)){
                                        for(int m = 0 ; m < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getCourses().size(); m++){
                                            if(dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getCourses().get(m).getName().equals(courseName)){
                                                dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getCourses().get(m).setGrade(grade);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if(foundL && foundS){
                                    JOptionPane.showMessageDialog(this, "The grade of the student's course was added!");
                                    dataBase.saveData();
                                    dispose();
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
        else if (!foundL) {
            JOptionPane.showMessageDialog(this, "Lesson not found!");
            dispose();
        } else if (!foundP) {
            JOptionPane.showMessageDialog(this, "Professor not found!");
            dispose();
        }
    }
}
