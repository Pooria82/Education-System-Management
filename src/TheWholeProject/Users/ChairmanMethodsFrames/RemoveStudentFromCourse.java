package TheWholeProject.Users.ChairmanMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;
import TheWholeProject.Lessons.Lesson;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import javax.swing.*;
import java.awt.*;

public class RemoveStudentFromCourse extends JFrame implements FindServices {
    private JTextField studentUsernameField, courseNameField;
    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;

    public RemoveStudentFromCourse(DataBase dataBase, String university, String faculty, String academic_department){
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
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

        JButton removeButton = new JButton("REMOVE");
        removeButton.setFont(new Font("Arial", Font.BOLD, 14));

        removeButton.addActionListener(e -> {
            try {
                Remove();
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
        add(removeButton, BorderLayout.SOUTH);
        pack();
    }

    private void Remove(){
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
                                        for(Lesson lesson1 : student.getCourses()){
                                            if(lesson1.getName().equals(courseName)){
                                                student.getCourses().remove(lesson1);
                                                break;
                                            }
                                        }
                                    }
                                }
                                for(int l = 0 ; l < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().size() ; l++){
                                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).getName().equals(courseName)){
                                        foundC = true;
                                        lesson = dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l);
                                        for(Student student1 : lesson.getStudents_list()){
                                            if(student1.getUser_name().equals(studentUsername)){
                                                lesson.getStudents_list().remove(student1);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if(foundC && foundS){
                                    student.addCourse(lesson);
                                    lesson.addStudents_list(student);
                                    JOptionPane.showMessageDialog(this, "Student has been added successfully!");
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
        else if (!foundC) {
            JOptionPane.showMessageDialog(this, "Lesson not found!");
            dispose();
        }
    }
}
