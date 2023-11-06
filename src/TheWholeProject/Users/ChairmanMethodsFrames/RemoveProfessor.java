package TheWholeProject.Users.ChairmanMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.FindServices;
import TheWholeProject.Lessons.Lesson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveProfessor extends JFrame implements ActionListener , FindServices {
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;
    private String professor;

    public RemoveProfessor(DataBase dataBase, String university, String faculty, String academic_department) {
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        // Set frame properties
        setTitle("Remove Professor");
        setSize(550, 150);
        setLocationRelativeTo(null);

        // Create components
        label = new JLabel("Enter the name of the Professor you want to remove:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        textField = new JTextField(30);
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        button = new JButton("Remove");
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
       find_professor();
    }

    @Override
    public void find_professor() {
        boolean foundU = false;
        boolean foundF = false;
        boolean foundA = false;
        boolean foundP = false;
        professor = textField.getText();
        //Lesson lesson = new Lesson()
        for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
            if (dataBase.getUniversity_list().get(i).getName().equals(university)) {
                foundU = true;
                for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(faculty)) {
                        foundF = true;
                        for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++) {
                            if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName().equals(academic_department)) {
                                foundA = true;
                                for (int w = 0; w < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().size(); w++) {
                                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(w).getUser_name().equals(professor)) {
                                        foundP = true;
                                        for (int t = 0 ; t < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(w).getLessons_list().size() ; t++){
                                            for (int q = 0 ; q < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(w).getLessons_list().get(t).getStudents_list().size() ; q++){
                                                for (Lesson lesson : dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(q).getCourses()){
                                                    if (lesson.getProfessor().getUser_name().equals(professor)){
                                                        dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(q).getCourses().remove(lesson);
                                                    }
                                                }
                                            }
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(w).getLessons_list().remove(t);
                                        }
                                        dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().remove(w);
                                        JOptionPane.showMessageDialog(this, "Professor has been deleted successfully!");
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
        } else if (!foundP) {
            JOptionPane.showMessageDialog(this, "Professor not found!");
            dispose();
        }
    }

    @Override
    public void find_student() {

    }
}
