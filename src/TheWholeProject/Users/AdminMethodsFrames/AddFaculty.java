package TheWholeProject.Users.AdminMethodsFrames;

import TheWholeProject.AdministrativeParts.Faculty;
import TheWholeProject.AdministrativeParts.University;
import TheWholeProject.Data.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFaculty extends JFrame {
    private JLabel facultyNameLabel, facultyBossLabel, universityLabel;
    private JTextField facultyNameField, facultyBossField, universityField;
    private JButton submitButton;
    private DataBase dataBase;

    public AddFaculty(DataBase dataBase) {
        this.dataBase = dataBase;
        // Set frame properties
        setTitle("Add Faculty");
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Create components
        facultyNameLabel = new JLabel("Faculty Name:");
        facultyBossLabel = new JLabel("Faculty Boss Name:");
        universityLabel = new JLabel("University Name:");
        facultyNameField = new JTextField(20);
        facultyBossField = new JTextField(20);
        universityField = new JTextField(20);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            try {
                submit();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Add components to frame
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(facultyNameLabel);
        panel.add(facultyNameField);
        panel.add(facultyBossLabel);
        panel.add(facultyBossField);
        panel.add(universityLabel);
        panel.add(universityField);
        panel.add(new JLabel());
        panel.add(submitButton);
        add(panel);

        // Display the frame
        setVisible(true);
    }

    private void submit(){
        String facultyName = facultyNameField.getText();
        String facultyBossName = facultyBossField.getText();
        String universityName = universityField.getText();
        if (facultyName.isBlank() || facultyBossName.isBlank()) {
            JOptionPane.showMessageDialog(this, "No field should be empty!");
            dispose();
        } else {
            boolean found = false;
            Faculty faculty = new Faculty(facultyName, facultyBossName);
            for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
                if (dataBase.getUniversity_list().get(i).getName().equals(universityName)) {
                    dataBase.getUniversity_list().get(i).addFaculty(faculty);
                    JOptionPane.showMessageDialog(this, "Faculty has been added successfully!");
                    dataBase.saveData();
                    found = true;
                    dispose();
                }
            }
            if (!found){
                JOptionPane.showMessageDialog(this, "University not found!");
                dispose();
            }
        }
    }
}
