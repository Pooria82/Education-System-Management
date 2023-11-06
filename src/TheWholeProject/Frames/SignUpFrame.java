package TheWholeProject.Frames;

import TheWholeProject.Data.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpFrame extends JFrame implements ActionListener {

    private JPanel panel;
    private JTextField firstNameField, lastNameField, usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userRoleComboBox;
    private JTextField universityField, facultyField, academicDepartmentField;
    private DataBase dataBase;

    public SignUpFrame(DataBase dataBase) {
        this.dataBase = dataBase;
        setTitle("Sign Up");
        setPreferredSize(new Dimension(400, 400));

        panel = new JPanel(new GridLayout(10, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        panel.add(firstNameLabel);
        panel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        panel.add(lastNameLabel);
        panel.add(lastNameField);

        JLabel usernameLabel = new JLabel("User Name:");
        usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        JLabel universityLabel = new JLabel("University:");
        universityField = new JTextField();
        panel.add(universityLabel);
        panel.add(universityField);

        JLabel facultyLabel = new JLabel("Faculty:");
        facultyField = new JTextField();
        panel.add(facultyLabel);
        panel.add(facultyField);

        JLabel academicDepartmentLabel = new JLabel("Academic Department:");
        academicDepartmentField = new JTextField();
        panel.add(academicDepartmentLabel);
        panel.add(academicDepartmentField);

        JLabel userRoleLabel = new JLabel("User Role:");
        String[] userRoles = {"Chairman", "Professor", "Student"};
        userRoleComboBox = new JComboBox<>(userRoles);
        panel.add(userRoleLabel);
        panel.add(userRoleComboBox);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> signUp());
        panel.add(new JLabel());
        panel.add(signUpButton);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void signUp() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String university = universityField.getText();
        String faculty = facultyField.getText();
        String academicDepartment = academicDepartmentField.getText();
        String userRole = (String) userRoleComboBox.getSelectedItem();

        // TODO: Add sign up logic here

        JOptionPane.showMessageDialog(this, "You have signed up successfully!");
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
