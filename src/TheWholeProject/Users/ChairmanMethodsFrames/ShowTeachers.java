package TheWholeProject.Users.ChairmanMethodsFrames;

import TheWholeProject.Data.DataBase;
import TheWholeProject.Users.UsersInfoAndFrame.Professor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ShowTeachers extends JFrame {

    public ShowTeachers(ArrayList<Professor> professors){

        setTitle("Show Professors");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        Object[] columnNames = {"Firstname", "Lastname", "Username"};


        String[][] data = new String[professors.size()][4];
        for (int i = 0 ; i < professors.size() ; i++){
            data[i][0] = professors.get(i).getFirst_name();
            data[i][1] = professors.get(i).getLast_name();
            data[i][2] = professors.get(i).getUser_name();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel();
        panel.add(scrollPane);
        add(panel);


    }
}
