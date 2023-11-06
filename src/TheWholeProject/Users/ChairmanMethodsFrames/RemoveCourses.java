package TheWholeProject.Users.ChairmanMethodsFrames;

        import TheWholeProject.Data.DataBase;
        import TheWholeProject.FindServices;
        import TheWholeProject.Lessons.Lesson;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class RemoveCourses extends JFrame implements ActionListener , FindServices {
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private DataBase dataBase;
    private String university;
    private String faculty;
    private String academic_department;
    private String lesson;

    public RemoveCourses(DataBase dataBase, String university, String faculty, String academic_department) {
        this.dataBase = dataBase;
        this.university = university;
        this.faculty = faculty;
        this.academic_department = academic_department;
        // Set frame properties
        setTitle("Remove Lesson");
        setSize(550, 150);
        setLocationRelativeTo(null);

        // Create components
        label = new JLabel("Enter the CODE of the Lesson you want to remove:");
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
        boolean foundS = false;
        boolean foundL = false;
        lesson = textField.getText();

        for (int i = 0; i < dataBase.getUniversity_list().size(); i++) {
            if (dataBase.getUniversity_list().get(i).getName().equals(university)) {
                foundU = true;
                for (int j = 0; j < dataBase.getUniversity_list().get(i).getFaculty_list().size(); j++) {
                    if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).getName().equals(faculty)) {
                        foundF = true;
                        for (int k = 0; k < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.size(); k++) {
                            if (dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getName().equals(academic_department)) {
                                foundA = true;
                                for (Lesson lesson1 : dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list()){
                                    if (lesson1.getCode().equals(lesson)){
                                        foundL = true;
                                        dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getLessons_list().remove(lesson1);
                                        break;
                                    }
                                }
                                for (int s = 0 ; s < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().size(); s++){
                                    for (Lesson lesson1 : dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(s).getCourses()){
                                        if (lesson1.getCode().equals(lesson)){
                                            foundL = true;
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getStudents_list().get(s).getCourses().remove(lesson1);
                                            break;
                                        }
                                    }
                                }
                                for (int p = 0 ; p < dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().size() ; p++){
                                    for (Lesson lesson1 : dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(p).getLessons_list()){
                                        if (lesson1.getCode().equals(lesson)){
                                            foundL = true;
                                            dataBase.getUniversity_list().get(i).getFaculty_list().get(j).disciplines_list.get(k).getProfessors_list().get(p).getLessons_list().remove(lesson1);
                                            JOptionPane.showMessageDialog(this, "Lesson has been deleted successfully!");
                                            dataBase.saveData();
                                            break;
                                        }
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
        } else if (!foundS) {
            JOptionPane.showMessageDialog(this, "Student not found!");
            dispose();
        } else if (!foundL) {
            JOptionPane.showMessageDialog(this, "Lesson Code not found!");
        }
    }

    @Override
    public void find_student() {

    }
}
