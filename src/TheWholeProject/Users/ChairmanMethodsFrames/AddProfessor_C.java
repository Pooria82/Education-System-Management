package TheWholeProject.Users.ChairmanMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;
import TheWholeProject.Users.UsersInfoAndFrame.Professor;

import javax.swing.*;
import java.awt.*;

public class AddProfessor_C extends JFrame implements FindServices {
    private JTextField firstNameField, lastNameField, usernameField, passwordField;
    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;

    public AddProfessor_C(DataBase dataBase, String university, String faculty, String academic_department) {
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        // Set the frame properties
        setTitle("Add Professor");
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Create and add the text fields to the frame
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JTextField(20);

        JLabel label = new JLabel("Enter Professor information:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("First Name: "));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name: "));
        panel.add(lastNameField);
        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        add(panel, BorderLayout.CENTER);

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
        find_professor();
    }

    private void addProfessor(Professor professor) {
        if (firstNameField.getText().isBlank()
                || lastNameField.getText().isBlank()
                || usernameField.getText().isBlank()
                || passwordField.getText().isBlank()
        ) {
            JOptionPane.showMessageDialog(this, "No field should be empty!");
            dispose();
        } else {
            boolean foundU = false;
            boolean foundF = false;
            boolean foundA = false;
            for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
                if (dataBase.getUniversity_list().get(i).getName().equals(university)) {
                    foundU = true;
                    for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(faculty)) {
                            foundF = true;
                            for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++) {
                                if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName().equals(academic_department)) {
                                    foundA = true;
                                    dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).addProfessor(professor);
                                    JOptionPane.showMessageDialog(this, "Professor has been added successfully!");
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

    @Override
    public void find_professor() {
        Professor professor = new Professor(firstNameField.getText(), lastNameField.getText(), usernameField.getText(), passwordField.getText());
        addProfessor(professor);
        this.dispose();
    }

    @Override
    public void find_student() {

    }
}
