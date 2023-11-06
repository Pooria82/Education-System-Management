package TheWholeProject.Users.UsersInfoAndFrame;

import TheWholeProject.Lessons.Lesson;
import TheWholeProject.Users.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Professor extends User implements Serializable {
    private ArrayList<Lesson> lessons_list = new ArrayList<>();

    public Professor(String first_name, String last_name, String user_name, String password) {
        super(first_name, last_name, user_name, password);
    }

    public ArrayList<Lesson> getLessons_list() {
        return lessons_list;
    }
    public void addLesson(Lesson lesson){
        this.lessons_list.add(lesson);
    }
}
