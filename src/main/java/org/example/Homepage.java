package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame{
    public Homepage(){
        setTitle("HFC (Health and Fitness Club)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,400);

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
                HFCMember member = new HFCMember();

            }
        });
        trainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("jaksdhlkajhdlkjahsdlkjash#EUNDC");
            }
        });
        staffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("JKBJKJKBJKJKJKBJKBJKBJBKJKBBJKBJK#EUNDC");
            }
        });

    }
}
