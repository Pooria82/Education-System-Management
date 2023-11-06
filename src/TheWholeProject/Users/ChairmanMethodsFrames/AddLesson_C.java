package TheWholeProject.Users.ChairmanMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;
import TheWholeProject.Lessons.Lesson;
import TheWholeProject.Users.UsersInfoAndFrame.Professor;

import javax.swing.*;
import java.awt.*;

public class AddLesson_C extends JFrame implements FindServices {

    private JLabel lessonNameLabel, lessonCodeLabel, lessonUnitsLabel, lessonCapacityLabel, professorNameLabel, infoLabel;
    private JTextField lessonNameField, lessonCodeField, lessonUnitsField, lessonCapacityField, professorNameField;
    private JButton submitButton;
    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;
    private  Professor professor;

    public AddLesson_C(DataBase dataBase, String university, String faculty, String academic_department) {
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        // Set the window properties
        this.setTitle("Add Lesson");
        this.setSize(500, 500);
        this.setLayout(new GridLayout(10, 2));
        setLocationRelativeTo(null);

        // Create the labels
        lessonNameLabel = new JLabel("Lesson Name:");
        lessonCodeLabel = new JLabel("Lesson Code:");
        lessonUnitsLabel = new JLabel("Number of Lesson Units:");
        lessonCapacityLabel = new JLabel("Lesson Capacity:");
        professorNameLabel = new JLabel("Lesson's Professor:");
        infoLabel = new JLabel("Enter Lesson information:");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Create the text fields
        lessonNameField = new JTextField();
        lessonCodeField = new JTextField();
        lessonUnitsField = new JTextField();
        lessonCapacityField = new JTextField();
        professorNameField = new JTextField();

        // Create the submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                submit();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Add the components to the window
        this.add(infoLabel);
        this.add(new JLabel());
        this.add(lessonNameLabel);
        this.add(lessonNameField);
        this.add(lessonCodeLabel);
        this.add(lessonCodeField);
        this.add(lessonUnitsLabel);
        this.add(lessonUnitsField);
        this.add(lessonCapacityLabel);
        this.add(lessonCapacityField);
        this.add(professorNameLabel);
        this.add(professorNameField);
        this.add(new JLabel());
        this.add(submitButton);
        // Show the window
        this.setVisible(true);
    }

    private void submit() {
        find_professor();
    }
    private void addLesson(Lesson lesson){
        String lessonName = lessonNameField.getText();
        String lessonCode = lessonCodeField.getText();
        String professorName = professorNameField.getText();
        if (lessonName.isBlank()
                || lessonCode.isBlank()
                || lessonUnitsField.getText().isBlank()
                || lessonCapacityField.getText().isBlank()
                || professorName.isBlank())
        {
            JOptionPane.showMessageDialog(this, "No field should be empty!");
            dispose();
        }else {
            boolean foundU = false;
            boolean foundF = false;
            boolean foundA = false;
            boolean foundP = false;
            //Lesson lesson = new Lesson()
            for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
                if (dataBase.getUniversity_list().get(i).getName().equals(university)) {
                    foundU = true;
                    for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(faculty)) {
                            foundF = true;
                            for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++){
                                if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName().equals(academic_department)){
                                    foundA = true;
                                    for (int w = 0 ; w < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().size(); w++){
                                        if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(w).getUser_name().equals(professorName)){
                                            foundP = true;
                                            professor = dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(w);
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).addLesson(lesson);
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(w).addLesson(lesson);
                                            JOptionPane.showMessageDialog(this, "Lesson has been added successfully!");
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
            }else if (!foundP) {
                JOptionPane.showMessageDialog(this, "Professor not found!");
                dispose();
            }
        }
    }

    @Override
    public void find_professor() {
        String lessonName = lessonNameField.getText();
        String lessonCode = lessonCodeField.getText();
        int lessonUnits = Integer.parseInt(lessonUnitsField.getText());
        int lessonCapacity = Integer.parseInt(lessonCapacityField.getText());
        Lesson lesson = new Lesson(professor , lessonName ,lessonCode,lessonUnits,lessonCapacity);
        addLesson(lesson);
        this.dispose();

    }

    @Override
    public void find_student() {

    }
}
