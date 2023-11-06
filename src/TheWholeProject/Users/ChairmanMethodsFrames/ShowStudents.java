package TheWholeProject.Users.ChairmanMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.Users.UsersInfoAndFrame.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ShowStudents extends JFrame {

    public ShowStudents(ArrayList<Student> students) {
        setTitle("Show Students");
        setResizable(false);
        setVisible(true);

        Object[] columnNames = {"Firstname", "Lastname", "Username", "Passed Units", "Passed Semesters"};

        String[][] data = new String[students.size()][5];
        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getFirst_name();
            data[i][1] = students.get(i).getLast_name();
            data[i][2] = students.get(i).getUser_name();
            data[i][3] = String.valueOf(students.get(i).getPassed_units());
            data[i][4] = String.valueOf(students.get(i).getPassed_semesters());
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 500));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }
}
