package com.arc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by pouya on 3/15/14.
 */
public enum UnitsImage {
    Archer("Archer","AP/Archery/archery-red.png","AP/Archery/archery-green.png","AP/Archery/archery-blue.png"),
    Horseman("Horseman","AP/Horseman/Horseman-red.png","AP/Horseman/Horseman-green.png","AP/Horseman/Horseman-blue.png"),
    Spearman("Spearman","AP/Spearman/Spearman-red.png","AP/Spearman/Spearman-green.png","AP/Spearman/Spearman-blue.png"),
    Swordsman("Swordsman","AP/Swordsman/swordsman-red.png","AP/Swordsman/swordsman-green.png","AP/Swordsman/swordsman-blue.png");
    //Swordsman("Swordsman","AP/Swordsman/swordsman-red.png","AP/Swordsman/swordsman-green.png","AP/Swordsman/swordsman-blue.png"),
    //  Swordsman("Swordsman","AP/Swordsman/swordsman-red.png","AP/Swordsman/swordsman-green.png","AP/Swordsman/swordsman-blue.png"),
    private BufferedImage imgred;
    private BufferedImage imggreen;
    private BufferedImage imgblue;
    private String unitName;

    public BufferedImage getImgblue() {
        return imgblue;
    }

    public BufferedImage getImgred() {
        return imgred;
    }

    public BufferedImage getImggreen() {
        return imggreen;
    }

    public String getUnitName() {
        return unitName;
    }

    UnitsImage(String tileName,String imgred , String imggreen,String imgblue) {
        this.unitName = unitName;
        try
        {

            this.imgred = ImageIO.read(new File(Main.class.getResource(imgred).getPath()));
            this.imggreen = ImageIO.read(new File(Main.class.getResource(imggreen).getPath()));
            this.imgblue = ImageIO.read(new File(Main.class.getResource(imgblue).getPath()));

        }catch (IOException e)
        {
            System.out.println(e);
            System.out.println(Main.class.getResource(imgred).getPath());
        }
    }
}
