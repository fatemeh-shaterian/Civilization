package com.arc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Taha on 3/11/14.
 */
public class ConstructionPanel extends JPanel implements ActionListener{
    private JButton btn[];
    private JComboBox comboBox;
    private JButton researchbtn;
    private JButton Turn ;
    private JButton Move;
    public IGameEngine ge;
    public ConstructionPanel() {

        //setBounds(0, 0, 600, 130);
        setLayout(null);

        btn = new JButton[14];
        String logos[]={"AP/Resources/Farm/logo.png",
                "AP/tech/Mining.png",
                "AP/Buildings/Barracks/logo.png",
                "AP/Buildings/Granary/logo.png",
                "AP/Buildings/Library/logo.png",
                "AP/Buildings/Stable/logo.png",
                "AP/Horseman/logo.png",
                "AP/Archery/logo.png",
                "AP/Swordsman/logo.png",
                "AP/tech/Spearman1.png",
                "AP/worker/logo.png",
                "AP/Settler/logo.png",
                "Ap/tech/Agriculture.png",


        };
        for(int i = 0 ; i < 7 ; i++)
        {
            btn[i]=new JButton();
            btn[i+6]=new JButton();
            btn[i].setBounds(i * 55 + 5, 5,50,50);
            btn[i+6].setBounds(i * 55 + 5,50 + 10,50,50);
            btn[i].addActionListener(this);
            btn[i+6].addActionListener(this);
            btn[i].setName(Integer.toString(i));
            btn[i+6].setName(Integer.toString(i+6));
            btn[i].setIcon(new ImageIcon(Main.class.getResource(logos[i])));
            btn[i+6].setIcon(new ImageIcon(Main.class.getResource(logos[i+6])));
            add(btn[i]);
            add(btn[i + 6]);
        }
        comboBox =new JComboBox();
        comboBox.setBounds(400,5,100,20);
        researchbtn = new JButton("Research");
        researchbtn.setBounds(400,30,100,30);
        researchbtn.setName("researchbtn");
        researchbtn.addActionListener(this);
        Turn = new JButton("turn");
        Turn.setBounds(580 , 10 , 100 , 100);
        Turn.setName("Turn");
        Turn.addActionListener(this);

        add(researchbtn);
        add(comboBox);
        add(Turn);

    }
    public void setResearchs(String names[])
    {
        comboBox.removeAllItems();
        for(String s : names)
            comboBox.addItem(s);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton)e.getSource()).getName()=="researchbtn")
            ge.clickOnResearch(comboBox.getSelectedItem().toString());
        else if(((JButton)e.getSource()).getName()=="Turn")
            ge.turnChanged();
        else
            try {
                ge.clickOnButton (Integer.parseInt(((JButton)e.getSource()).getName()));
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

    }
}