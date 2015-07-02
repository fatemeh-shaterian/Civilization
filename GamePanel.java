package com.arc;

import javax.swing.*;
//import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener,MouseListener,MouseMotionListener
{

    public static Map plain;
    private int startX , startY , lastX , lastY , bound;
    private Timer timer;
///?????//
    public IGameEngine ge;

    public GamePanel(Map plain){

        startX = 0;
        startY = 0;
        bound=30;

        GamePanel.plain = plain;

        addMouseListener(this);
        addMouseMotionListener(this);

        timer=new Timer(200,this);
        timer.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ge.clickOnMap(startX+e.getX()/plain.getTileSize(),startY+e.getY()/plain.getTileSize());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        lastX = getWidth()-50 ;
        lastY = getHeight()-50;
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // System.out.println(e.getPoint());
        lastX = getWidth()-50 ;
        lastY = getHeight()-50;
        //System.out.println(new Point(getWidth(),getHeight()));
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastX=e.getX();
        lastY=e.getY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D panel = (Graphics2D)g;

        //Drawing Map Elements
//        System.out.println(this.getWidth()/plain.getTileSize());
        for(int i = 0 ; i <= this.getWidth()/plain.getTileSize() && i<plain.getWidth() ; i++)
        {
            for(int j = 0 ; j <=  this.getHeight()/plain.getTileSize() && j<plain.getHeight() ; j++)
            {
                switch (plain.Tiles[startX+i][startY+j])
                {
                    case 0:
                        panel.drawImage(TileType.Mountain.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);
                        break;
                    case 1:
                        panel.drawImage(TileType.Plain.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);

                        break;
                    case 2:
                        panel.drawImage(TileType.Sea.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);
                        break;
                    case 3:
                        panel.drawImage(TileType.Lake.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);
                        break;
                    case 4:
                        panel.drawImage(TileType.Plain.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);
                        panel.drawImage(ResourcesType.Horse.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);
                        break;
                    case 5:
                        panel.drawImage(TileType.Plain.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);
                        panel.drawImage(ResourcesType.Mine.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);
                        break;
                    case 6:

                        panel.drawImage(ResourcesType.Iron.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);
                        break;
                    case 7:
                        panel.drawImage(TileType.Plain.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);

                        break;
                    case 8:
                        panel.drawImage(TileType.Plain.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);

                        break;
                    case 9:
                        panel.drawImage(TileType.Plain.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);

                        break;
                    case 10:
                        panel.drawImage(TileType.Plain.getImg(),i*plain.getTileSize(),j*plain.getTileSize(),null);

                        break;


                }

            }
        }

        for (MapElement mE : plain.getCity())
        {
            panel.drawImage(mE.getImage(),(mE.getX()-startX)*plain.getTileSize(),(mE.getY()-startY)*plain.getTileSize(),null);
        }
        for (MapElement mE : plain.getResources())
        {
            panel.drawImage(mE.getImage(),(mE.getX()-startX)*plain.getTileSize(),(mE.getY()-startY)*plain.getTileSize(),null);
        }
        for (MapElement mE : plain.getTroop())
        {
            panel.drawImage(mE.getImage(),(mE.getX()-startX)*plain.getTileSize(),(mE.getY()-startY)*plain.getTileSize(),null);
        }
    }
    private boolean mouseIsOverDisplayPanel()
    {

        if(this.isShowing() && MouseInfo.getPointerInfo().getLocation().x >= this.getLocationOnScreen().x
                && MouseInfo.getPointerInfo().getLocation().x <= this.getLocationOnScreen().x + this.getWidth()
                && MouseInfo.getPointerInfo().getLocation().y >= this.getLocationOnScreen().y
                && MouseInfo.getPointerInfo().getLocation().y <= this.getLocationOnScreen().y + this.getHeight())
        {

            return true;

        }
        else
        {

            return false;

        }
    }

    public void updatescreen(){
        update(getGraphics());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (lastX < bound && startX > 0){
            startX--;
            update(getGraphics());
        }
        else if (lastX > this.getWidth()-bound && startX + this.getWidth()/plain.getTileSize()+1 < plain.getWidth() ){
            startX++;
            update(getGraphics());
        }
        if(lastY<bound && startY > 0){
            startY--;
            update(getGraphics());
        }
        else if (lastY>this.getHeight()-bound && startY + this.getHeight()/plain.getTileSize()+1 < plain.getHeight() ){
            startY++;
            update(getGraphics());
        }
    }
}
