package org.example;
import jdk.jfr.Percentage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    private JButton memberButton, trainerButton, staffButton;

    public GUI(){
        setTitle("HFC (Health and Fitness Club)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);

        JLabel welcomeLabel = new JLabel("Welcome to the Health and Fitness Club!");
        JLabel whatAreYouLabel = new JLabel("Which of these are you?");

        memberButton = new JButton("Member");
        trainerButton = new JButton("Trainer");
        staffButton = new JButton("Staff");

        setLayout(new GridLayout(5,1));

        add(welcomeLabel);
        add(whatAreYouLabel);
        add(memberButton);
        add(trainerButton);
        add(staffButton);

        memberButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){


            }
        });
        trainerButton.addActionListener(this);
        staffButton.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
