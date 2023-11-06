package TheWholeProject.Users.AdminMethodsFrames;

import TheWholeProject.AdministrativeParts.AcademicDepartment;
import TheWholeProject.Data.DataBase;
import TheWholeProject.Users.UsersInfoAndFrame.Chairman;

import javax.swing.*;
import java.awt.*;

public class AddAcademicDepartment extends JFrame {
    private JLabel departmentNameLabel, chairmanFirstNameLabel, chairmanLastNameLabel, chairmanUsernameLabel, chairmanPasswordLabel, universityNameLabel, facultyNameLabel;
    private JTextField departmentNameField, chairmanFirstNameField, chairmanLastNameField, chairmanUsernameField, chairmanPasswordField, universityNameField, facultyNameField;
    private JButton submitButton;
    private DataBase dataBase;

    public AddAcademicDepartment(DataBase dataBase) {
        this.dataBase = dataBase;
        // Set frame properties
        setTitle("Add Academic Department");
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Create components
        departmentNameLabel = new JLabel("Academic Department Name:");
        chairmanFirstNameLabel = new JLabel("Chairman First Name:");
        chairmanLastNameLabel = new JLabel("Chairman Last Name:");
        chairmanUsernameLabel = new JLabel("Chairman Username:");
        chairmanPasswordLabel = new JLabel("Chairman Password:");
        universityNameLabel = new JLabel("University Name:");
        facultyNameLabel = new JLabel("Faculty Name:");
        departmentNameField = new JTextField(20);
        chairmanFirstNameField = new JTextField(20);
        chairmanLastNameField = new JTextField(20);
        chairmanUsernameField = new JTextField(20);
        chairmanPasswordField = new JTextField(20);
        universityNameField = new JTextField(20);
        facultyNameField = new JTextField(20);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            try {
                submit();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Add components to frame
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(departmentNameLabel);
        panel.add(departmentNameField);
        panel.add(chairmanFirstNameLabel);
        panel.add(chairmanFirstNameField);
        panel.add(chairmanLastNameLabel);
        panel.add(chairmanLastNameField);
        panel.add(chairmanUsernameLabel);
        panel.add(chairmanUsernameField);
        panel.add(chairmanPasswordLabel);
        panel.add(chairmanPasswordField);
        panel.add(universityNameLabel);
        panel.add(universityNameField);
        panel.add(facultyNameLabel);
        panel.add(facultyNameField);
        panel.add(new JLabel());
        panel.add(submitButton);
        add(panel);

        // Display the frame
        setVisible(true);
    }

    private void submit() {
        String academicDepartmentName = departmentNameField.getText();
        String chairmanFirstName = chairmanFirstNameField.getText();
        String chairmanLastName = chairmanLastNameField.getText();
        String chairmanUsername = chairmanUsernameField.getText();
        String chairmanPassword = chairmanPasswordField.getText();
        String universityName = universityNameField.getText();
        String facultyName = facultyNameField.getText();
        Chairman chairman = new Chairman(chairmanFirstName,chairmanLastName,chairmanUsername,chairmanPassword);
        if (academicDepartmentName.isBlank()
                || chairmanFirstName.isBlank()
                || chairmanLastName.isBlank()
                ||chairmanUsername.isBlank()
                || chairmanPassword.isBlank()
                || universityName.isBlank()
                || facultyName.isBlank())
        {
            JOptionPane.showMessageDialog(this, "No field should be empty!");
            dispose();
        } else {
            boolean foundU = false;
            boolean foundF = false;
            AcademicDepartment academicDepartment = new AcademicDepartment(academicDepartmentName, chairman);
            for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
                if (dataBase.getUniversity_list().get(i).getName().equals(universityName)) {
                    foundU = true;
                    for (int j = 0 ; j < dataBase.getUniversity_list().get(i).getFaculty_list().size() ; j++){
                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(facultyName)){
                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).addDisciplines(academicDepartment);
                            JOptionPane.showMessageDialog(this, "Academic Department has been added successfully!");
                            dataBase.saveData();
                            foundF = true;
                            dispose();
                            break;
                        }
                    }
                    break;
                }
            }
            if (!foundU){
                JOptionPane.showMessageDialog(this, "University not found!");
                dispose();
            } else if (!foundF) {
                JOptionPane.showMessageDialog(this, "Faculty not found!");
                dispose();
            }
        }
    }
}
