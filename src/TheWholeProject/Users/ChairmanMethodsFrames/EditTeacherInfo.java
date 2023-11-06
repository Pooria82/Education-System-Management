package TheWholeProject.Users.ChairmanMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTeacherInfo extends JFrame implements FindServices {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField currentProfessorUsernameField;
    private JCheckBox showPasswordField;
    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;

    public EditTeacherInfo(DataBase dataBase, String university, String faculty, String academic_department) {
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        setTitle("Edit Professor information");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(7, 2));
        JLabel currentProfessorUsernameLabel = new JLabel("Current Username:");
        currentProfessorUsernameField = new JTextField();
        JLabel firstNameLabel = new JLabel("New Firstname:");
        firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("New Lastname:");
        lastNameField = new JTextField();
        JLabel usernameLabel = new JLabel("New Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("New Password:");
        passwordField = new JPasswordField();
        showPasswordField = new JCheckBox("Show Password");

        fieldsPanel.add(currentProfessorUsernameLabel);
        fieldsPanel.add(currentProfessorUsernameField);
        fieldsPanel.add(firstNameLabel);
        fieldsPanel.add(firstNameField);
        fieldsPanel.add(lastNameLabel);
        fieldsPanel.add(lastNameField);
        fieldsPanel.add(usernameLabel);
        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordLabel);
        fieldsPanel.add(passwordField);

        showPasswordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordField.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('\u2022');
                }
            }
        });
        fieldsPanel.add(showPasswordField);

        panel.add(fieldsPanel, BorderLayout.CENTER);


        JButton submitButton = new JButton("SUBMIT");

        submitButton.addActionListener(e -> {
            try {
                submit();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(submitButton, BorderLayout.SOUTH);

        add(panel);
    }

    private void submit() {
        find_professor();
    }

    @Override
    public void find_professor() {
        if (firstNameField.getText().isBlank()
                || lastNameField.getText().isBlank()
                || usernameField.getText().isBlank()
                || passwordField.getText().isBlank()
                || currentProfessorUsernameField.getText().isBlank()
        ) {
            JOptionPane.showMessageDialog(this, "No field should be empty!");
            dispose();
        } else {
            String currentProfessorUsername = currentProfessorUsernameField.getText();
            String newFirstName = firstNameField.getText();
            String newLastName = lastNameField.getText();
            String newUsername = usernameField.getText();
            String newPassword = new String(passwordField.getPassword());
            boolean foundU = false;
            boolean foundF = false;
            boolean foundA = false;
            boolean foundP = false;
            for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
                if (dataBase.getUniversity_list().get(i).getName().equals(university)) {
                    foundU = true;
                    for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(faculty)) {
                            foundF = true;
                            for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++) {
                                if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName().equals(academic_department)) {
                                    foundA = true;
                                    for (int l = 0; l < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().size(); l++) {
                                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getUser_name().equals(currentProfessorUsername)){
                                            foundP = true;
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).setFirst_name(newFirstName);
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).setLast_name(newLastName);
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).setUser_name(newUsername);
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).setPassword(newPassword);
                                            break;
                                        }
                                    }
                                    JOptionPane.showMessageDialog(this, "Professor information has been edited successfully!");
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
            else if (!foundP) {
                JOptionPane.showMessageDialog(this, "Professor not found!");
                dispose();
            }
        }
    }

    @Override
    public void find_student() {

    }
}
