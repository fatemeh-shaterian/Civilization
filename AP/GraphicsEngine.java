package com.arc.AP;

import com.arc.*;
import javax.swing.*;


public class GraphicsEngine {

    private JFrame baseFrame;
    private JFrame consFrame;

    private GamePanel gamePanel ;
    private ConstructionPanel constructionPanel ;

    public GraphicsEngine(Map map,IGameEngine ge) {
        baseFrame = new JFrame("Civilization");
        gamePanel = new GamePanel(map);
        gamePanel.ge=ge;
        baseFrame.setBounds(8, 150, 1360, 590);
        baseFrame.setResizable(false);
        baseFrame.setVisible(true);
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        baseFrame.add(gamePanel);

        consFrame = new JFrame("Construction");
        constructionPanel = new ConstructionPanel();
        constructionPanel.ge=ge;
        consFrame.setBounds(8,0,1360,150);
        consFrame.setResizable(false);
        consFrame.setVisible(true);
        consFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        consFrame.add(constructionPanel);
    }

    public void setResearchs(String names[])
    {
        constructionPanel.setResearchs(names);
    }

    public void UpdateScreen()
    {
        gamePanel.updatescreen();
    }
}
