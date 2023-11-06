package TheWholeProject.Users.ChairmanMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCourses extends JFrame implements FindServices {
    private JTextField newNameField;
    private JTextField newCodeField;
    private JTextField newNumOfUnitsField;
    private JTextField newCapacityField;
    private JTextField currentCodeField;
    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;

    public EditCourses(DataBase dataBase, String university, String faculty, String academic_department) {
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        setTitle("Edit Student information");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(7, 2));
        JLabel currentCodeLabel = new JLabel("Current Lesson Code:");
        currentCodeField = new JTextField();
        JLabel NameLabel = new JLabel("New Lesson Name:");
        newNameField = new JTextField();
        JLabel codeLabel = new JLabel("New Lesson Code:");
        newCodeField = new JTextField();
        JLabel numOfUnitsLabel = new JLabel("New Lesson Number of Units:");
        newNumOfUnitsField = new JTextField();
        JLabel capacityLabel = new JLabel("New Lesson Capacity:");
        newCapacityField = new JPasswordField();

        fieldsPanel.add(currentCodeLabel);
        fieldsPanel.add(currentCodeField);
        fieldsPanel.add(NameLabel);
        fieldsPanel.add(newNameField);
        fieldsPanel.add(codeLabel);
        fieldsPanel.add(newCodeField);
        fieldsPanel.add(numOfUnitsLabel);
        fieldsPanel.add(newNumOfUnitsField);
        fieldsPanel.add(capacityLabel);
        fieldsPanel.add(newCapacityField);

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
        if (newNameField.getText().isBlank()
                || newCodeField.getText().isBlank()
                || newNumOfUnitsField.getText().isBlank()
                || newCapacityField.getText().isBlank()
                || currentCodeField.getText().isBlank()
        ) {
            JOptionPane.showMessageDialog(this, "No field should be empty!");
            dispose();
        } else {
            String currentCode = currentCodeField.getText();
            String newName = newNameField.getText();
            String newCode = newCodeField.getText();
            String newNumOfUnits = newNumOfUnitsField.getText();
            String newCapacity = newCapacityField.getText();
            boolean foundU = false;
            boolean foundF = false;
            boolean foundA = false;
            boolean foundL = false;
            for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
                if (dataBase.getUniversity_list().get(i).getName().equals(university)) {
                    foundU = true;
                    for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(faculty)) {
                            foundF = true;
                            for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++) {
                                if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName().equals(academic_department)) {
                                    foundA = true;
                                    for (int l = 0; l < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().size(); l++) {
                                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).getCode().equals(currentCode)){
                                            foundL = true;
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).setName(newName);
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).setCode(newCode);
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).setNum_of_units(Integer.parseInt(newNumOfUnits));
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().get(l).setCapacity(Integer.parseInt(newCapacity));
                                            break;
                                        }
                                    }
                                    JOptionPane.showMessageDialog(this, "Lesson information has been edited successfully!");
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
            else if (!foundL) {
                JOptionPane.showMessageDialog(this, "Lesson not found!");
                dispose();
            }
        }
    }

    @Override
    public void find_student() {

    }
}

