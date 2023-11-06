package TheWholeProject.Users.AdminMethodsFrames;

import TheWholeProject.AdministrativeParts.University;
import TheWholeProject.Data.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUniversity extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private DataBase dataBase;


    public DeleteUniversity(DataBase dataBase) {
        this.dataBase = dataBase;
        // Set frame properties
        setTitle("Delete University");
        setSize(550, 150);
        setLocationRelativeTo(null);

        // Create components
        label = new JLabel("Enter the name of the university you want to delete:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        textField = new JTextField(30);
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        button = new JButton("Delete");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(this);

        // Add components to frame
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        this.add(panel);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean found = false;
        for(University university : dataBase.getUniversity_list()){
            if(university.getName().equals(textField.getText())){
                dataBase.getUniversity_list().remove(university);
                JOptionPane.showMessageDialog(this, "University has been deleted successfully!");
                dataBase.saveData();
                dispose();
                found = true;
                break;
            }
        }
        if (!found){
            JOptionPane.showMessageDialog(this, "University not found!");
            dispose();
        }
    }
}
