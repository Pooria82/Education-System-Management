package TheWholeProject.Users.UsersInfoAndFrame;

import TheWholeProject.Users.StudentsMethodsFrames.ShowReportCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentFrame extends JFrame implements ActionListener {
    private JList<String> coursesList;
    private JButton reportCardButton;
    private Student student;

    public StudentFrame(Student student) {

        this.student = student;
        // Set up the frame
        setTitle("Student Information");
        setSize(400, 400);
        setLayout(new GridLayout(10,1));
        setLocationRelativeTo(null);


        // Add the first name label and field
        JLabel firstNameLabel = new JLabel("First name: " + student.getFirst_name());
        add(firstNameLabel);


        // Add the last name label and field
        JLabel lastNameLabel = new JLabel("Last name: " + student.getLast_name());
        add(lastNameLabel);


        // Add the username label and field
        JLabel usernameLabel = new JLabel("Username: " + student.getUser_name());
        add(usernameLabel);


        // Add the password label and field
        JLabel passwordLabel = new JLabel("Password: " + student.getPassword());
        add(passwordLabel);


        // Add the number of passed units label and field
        JLabel passedUnitsLabel = new JLabel("Number of passed units: " + student.getPassed_units());
        add(passedUnitsLabel);


        // Add the number of semesters passed label and field
        JLabel semestersPassedLabel = new JLabel("Number of semesters passed: " + student.getPassed_semesters());
        add(semestersPassedLabel);


        // Add the courses taken label and list
        JLabel coursesLabel = new JLabel("Courses taken:");
        add(coursesLabel);

        String[] lessonsList = new String[student.getCourses().size()];
        for (int i = 0 ; i < student.getCourses().size() ; i++) {
            lessonsList[i] = student.getCourses().get(i).getName();
        }
        coursesList = new JList(lessonsList);
        JScrollPane scrollPane = new JScrollPane(coursesList);
        add(scrollPane);

        // Add the report card button
        reportCardButton = new JButton("View report card");
        reportCardButton.addActionListener(this);
        add(new JLabel(""));
        add(reportCardButton);



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reportCardButton) {
            ShowReportCard showReportCard = new ShowReportCard(student.getCourses());
        }
    }

}
