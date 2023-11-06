package TheWholeProject.Users.ChairmanMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.Lessons.Lesson;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ShowCourses extends JFrame {

    public ShowCourses(ArrayList<Lesson> lessons){
        setTitle("Show Courses");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        Object[] columnNames = {"Name", "Code", "Number of Units", "Capacity"};

        String[][] data = new String[lessons.size()][4];
        for (int i = 0 ; i < lessons.size() ; i++){
            data[i][0] = lessons.get(i).getName();
            data[i][1] = lessons.get(i).getCode();
            data[i][2] = String.valueOf(lessons.get(i).getNum_of_units());
            data[i][3] = String.valueOf(lessons.get(i).getCapacity());
        }


        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel();
        panel.add(scrollPane);
        add(panel);


    }
}
