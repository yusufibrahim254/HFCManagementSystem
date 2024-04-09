package org.example;
import jdk.jfr.Percentage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    public GUI(){
        setTitle("HFC (Health and Fitness Club)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);

        JLabel welcomeLabel = new JLabel("Welcome to the Health and Fitness Club!");
        JLabel whatAreYouLabel = new JLabel("Which of these are you?");

        JButton memberButton = new JButton("Member");
        JButton trainerButton = new JButton("Trainer");
        JButton staffButton = new JButton("Staff");

        setLayout(new GridLayout(5,1));

        add(welcomeLabel);
        add(whatAreYouLabel);
        add(memberButton);
        add(trainerButton);
        add(staffButton);

        memberButton.addActionListener(new ActionListener() {
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
