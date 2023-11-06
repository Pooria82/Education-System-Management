package TheWholeProject.Users.StudentsMethodsFrames;

import TheWholeProject.Lessons.Lesson;

import javax.swing.*;
import java.util.ArrayList;

public class ShowReportCard extends JFrame {
    public ShowReportCard(ArrayList<Lesson> lessons){
        this.setTitle("Report Card");
        String[] columns = {"Name", "Grade"};
        String[][] data = new String[lessons.size()][2];

        for(int i=0; i<lessons.size(); i++){
            data[i][0] = lessons.get(i).getName();
            data[i][1] = String.valueOf(lessons.get(i).getGrade());
        }
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
