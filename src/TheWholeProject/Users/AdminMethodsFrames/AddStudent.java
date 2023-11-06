package TheWholeProject.Users.AdminMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import javax.swing.*;
import java.awt.*;

public class AddStudent extends JFrame implements FindServices {
    private JTextField firstNameField, lastNameField, usernameField, passwordField, universityField, facultyField, academicField;
private DataBase dataBase;
    public AddStudent(DataBase dataBase) {
        this.dataBase= dataBase;
        // Set the frame properties
        setTitle("Add Student");
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Create and add the text fields to the frame
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JTextField(20);
        universityField = new JTextField(20);
        facultyField = new JTextField(20);
        academicField = new JTextField(20);
        JLabel label = new JLabel("Enter student information:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("First Name: "));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name: "));
        panel.add(lastNameField);
        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        panel.add(new JLabel("University: "));
        panel.add(universityField);
        panel.add(new JLabel("Faculty: "));
        panel.add(facultyField);
        panel.add(new JLabel("Academic Department: "));
        panel.add(academicField);
        add(panel, BorderLayout.CENTER);

        // Create and add the submit button to the frame
        JButton submitButton = new JButton("Submit");
        add(submitButton, BorderLayout.SOUTH);
        submitButton.addActionListener(e -> {
            try {
                submit();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        setVisible(true);
    }
    private void submit() {
        find_student();
    }

    @Override
    public void find_professor() {

    }

    @Override
    public void find_student() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String university = universityField.getText();
        String faculty = facultyField.getText();
        String academic = academicField.getText();
        if (firstName.isBlank()
                || lastName.isBlank()
                || username.isBlank()
                || password.isBlank()
                || university.isBlank()
                || faculty.isBlank()
                || academic.isBlank()
        ) {
            JOptionPane.showMessageDialog(this, "No field should be empty!");
            dispose();
        } else {
            boolean foundU = false;
            boolean foundF = false;
            boolean foundA = false;
            Student student = new Student(firstName, lastName, username, password);
            for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
                if (dataBase.getUniversity_list().get(i).getName().equals(university)) {
                    foundU = true;
                    for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(faculty)) {
                            foundF = true;
                            for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++){
                                if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName().equals(academic)){
                                    foundA = true;
                                    dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).addStudent(student);
                                    JOptionPane.showMessageDialog(this, "Student has been added successfully!");
                                    dataBase.saveData();
                                    dispose();
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
        }
    }
}
