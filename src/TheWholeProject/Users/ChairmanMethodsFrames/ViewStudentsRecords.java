package TheWholeProject.Users.ChairmanMethodsFrames;


import TheWholeProject.Users.StudentsMethodsFrames.ShowReportCard;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ViewStudentsRecords extends JFrame implements ActionListener {
    private ArrayList<Student> students;
    private JPanel panel = new JPanel();
    private JTextField username = new JTextField(20);
    private JButton show = new JButton("show");

    public ViewStudentsRecords(ArrayList<Student> students) {
        this.students = students;
        JLabel usernameLabel = new JLabel("Username: ");
        this.setTitle("Username Frame");
        this.setVisible(true);
        panel.setLayout(new FlowLayout());
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(show);
        show.addActionListener(this);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Student student : students) {
            if (student.getUser_name().equals(username.getText())) {
                new ShowReportCard(student.getCourses());
                break;
            }
        }
    }
}
