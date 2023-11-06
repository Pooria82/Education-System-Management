package TheWholeProject.Users.UsersInfoAndFrame;

import TheWholeProject.Users.User;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(String first_name, String last_name, String user_name, String password) {
        super(first_name, last_name, user_name, password);
    }
}
