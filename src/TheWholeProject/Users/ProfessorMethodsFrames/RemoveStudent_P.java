package TheWholeProject.Users.ProfessorMethodsFrames;


import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;
import TheWholeProject.Lessons.Lesson;
import TheWholeProject.Users.ChairmanMethodsFrames.RemoveStudentFromCourse;
import TheWholeProject.Users.UsersInfoAndFrame.Professor;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import javax.swing.*;
import java.awt.*;

public class RemoveStudent_P extends JFrame implements FindServices {
    private JTextField studentUsernameField, courseNameField;

    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;
    private Professor professor;

    public RemoveStudent_P(DataBase dataBase, String university, String faculty, String academic_department , Professor professor) {
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        this.professor = professor;
        setTitle("Remove Student From Course");
        setLocationRelativeTo(null);
        setVisible(true);
        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        JLabel label = new JLabel("Enter the name of the Lesson and the username of the student you want to remove from the Lesson.");
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        JLabel studentUsernameLabel = new JLabel("Student Username:");
        studentUsernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        studentUsernameField = new JTextField();
        studentUsernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        courseNameField = new JTextField();
        courseNameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));

        deleteButton.addActionListener(e -> {
            try {
                Delete();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(label);
        panel.add(new JLabel());
        panel.add(studentUsernameLabel);
        panel.add(studentUsernameField);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        add(panel, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);
        pack();
        System.out.println(1);
    }

    private void Delete(){
        find_student();
    }

    @Override
    public void find_professor() {}

    @Override
    public void find_student() {
        String studentUsername = studentUsernameField.getText();
        String courseName = courseNameField.getText();
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
                                        for(Lesson lesson1 : dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getCourses()){
                                            if(lesson1.getName().equals(courseName)){
                                                dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getCourses().remove(lesson1);
                                                break;
                                            }
                                        }
                                    }
                                }
                                for(int l = 0 ; l < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().size() ; l++){
                                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).getName().equals(courseName)){
                                        for(Student student1 : dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).getStudents_list()){
                                            if(student1.getUser_name().equals(studentUsername)){
                                                dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).getStudents_list().remove(student1);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if(foundL && foundS){
                                    JOptionPane.showMessageDialog(this, "The student was successfully removed from the course!");
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
