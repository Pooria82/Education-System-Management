package TheWholeProject.Frames;


import TheWholeProject.Data.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenu extends JFrame implements ActionListener{
    private JButton loginButton, signUpButton;
    private DataBase dataBase;
    public MyMenu(DataBase dataBase) {
        this.dataBase = dataBase;
        setTitle("Login");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to Education System Management");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);

        loginButton = new JButton("Log In");
        loginButton.setFocusable(false);
        loginButton.setPreferredSize(new Dimension(150, 50));
        loginButton.setFont(new Font("Serif", Font.BOLD, 18));
        loginButton.addActionListener(this);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFocusable(false);
        signUpButton.setPreferredSize(new Dimension(150, 50));
        signUpButton.setFont(new Font("Serif", Font.BOLD, 18));
        signUpButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        setLayout(new BorderLayout());
        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signUpButton) {
            SignUpFrame signUpFrame = new SignUpFrame(this.dataBase);
        } else if (e.getSource() == loginButton) {
            LogInPanel logInPanel = new LogInPanel(this.dataBase);
        }
    }
}
