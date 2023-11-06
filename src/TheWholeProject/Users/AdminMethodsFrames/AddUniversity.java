package TheWholeProject.Users.AdminMethodsFrames;

import TheWholeProject.AdministrativeParts.University;
import TheWholeProject.Data.DataBase;

import javax.swing.*;
import java.awt.*;

public class AddUniversity extends JFrame{
    private JPanel panel;
    private JTextField universityNameField, universityTypeField, universityAddressField;
    private DataBase dataBase;

    public AddUniversity(DataBase dataBase) {
        this.dataBase= dataBase;
        setTitle("Add University");
        setPreferredSize(new Dimension(400, 200));


        panel = new JPanel(new GridLayout(4, 2));

        JLabel universityNameLabel = new JLabel("University Name:");
        universityNameField = new JTextField();
        panel.add(universityNameLabel);
        panel.add(universityNameField);

        JLabel universityTypeLabel = new JLabel("University Type:");
        universityTypeField = new JTextField();
        panel.add(universityTypeLabel);
        panel.add(universityTypeField);

        JLabel universityAddressLabel = new JLabel("University Address:");
        universityAddressField = new JTextField();
        panel.add(universityAddressLabel);
        panel.add(universityAddressField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                submit();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(new JLabel());
        panel.add(submitButton);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void submit(){
        String universityName = universityNameField.getText();
        String universityType = universityTypeField.getText();
        String universityAddress = universityAddressField.getText();
        if (universityName.isBlank() || universityType.isBlank() || universityAddress.isBlank()){
            JOptionPane.showMessageDialog(this, "No field should be empty!");
            dispose();
        }
        else {
            University university = new University(universityName , universityType , universityAddress);
            boolean exist = false;
            for(University university1 : dataBase.getUniversity_list()){
                if(university1.getName().equals(universityName)){
                    exist = true;
                    break;
                }
            }
            if(exist){
                JOptionPane.showMessageDialog(this, "University already exist");
                dispose();
            } else {
                this.dataBase.addUniversity(university);
                JOptionPane.showMessageDialog(this, "University has been added successfully!");
                dataBase.saveData();
                dispose();
            }
        }

    }

}
