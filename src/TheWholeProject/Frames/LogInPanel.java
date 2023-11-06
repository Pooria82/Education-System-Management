package TheWholeProject.Frames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.Users.UsersInfoAndFrame.AdminFrame;
import TheWholeProject.Users.UsersInfoAndFrame.ChairmanFrame;
import TheWholeProject.Users.UsersInfoAndFrame.ProfessorFrame;
import TheWholeProject.Users.UsersInfoAndFrame.StudentFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanel extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckbox;
    private JButton loginButton;
    private DataBase dataBase;

    public LogInPanel(DataBase dataBase) {
        this.dataBase = dataBase;
        // Set up the frame
        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);


        // Add the username label and field
        JLabel usernameLabel = new JLabel("Username:");
        add(usernameLabel);
        usernameField = new JTextField();
        add(usernameField);

        // Add the password label, field, and show password checkbox
        JLabel passwordLabel = new JLabel("Password:");
        add(passwordLabel);
        passwordField = new JPasswordField();
        add(passwordField);
        showPasswordCheckbox = new JCheckBox("Show password");
        showPasswordCheckbox.addActionListener(this);
        add(new JLabel(""));
        add(showPasswordCheckbox);

        // Add the login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(new JLabel(""));
        add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            boolean found = false;
            for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
                for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                    for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++) {
                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getChairman_name().getUser_name().equals(usernameField.getText())) {
                            found = true;
                            if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getChairman_name().getPassword().equals(passwordField.getText())) {
                                new ChairmanFrame(dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getChairman_name(), this.dataBase);
                            } else {
                                JOptionPane.showMessageDialog(this, "Wrong Password. Please try again.");
                            }
                        }
                        for (int l = 0; l < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().size(); l++) {
                            if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(l).getUser_name().equals(usernameField.getText())) {
                                found = true;
                                if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(l).getPassword().equals(passwordField.getText())) {
                                    new ProfessorFrame(dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(l), this.dataBase);
                                } else {
                                    JOptionPane.showMessageDialog(this, "Wrong Password. Please try again.");
                                }
                            }
                        }
                        for (int l = 0; l < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().size(); l++) {
                            if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getUser_name().equals(usernameField.getText())) {
                                found = true;
                                if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l).getPassword().equals(passwordField.getText())) {
                                    new StudentFrame(dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(l));
                                } else {
                                    JOptionPane.showMessageDialog(this, "Wrong Password. Please try again.");
                                }
                            }
                        }

                    }
                }
            }
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Check the username and password here
            if (username.equals("admin") && password.equals("admin")) {
                new AdminFrame(this.dataBase);
                found = true;
            }
            if (!found){
                JOptionPane.showMessageDialog(this, "Login failed. Please try again.");
            }
        } else if (e.getSource() == showPasswordCheckbox) {
            if (showPasswordCheckbox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('\u2022');
            }

        }
    }
}
