package com.arc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by pouya on 3/14/14.
 */
public enum TileType {
    Mountain("Mountain","AP/MapTiles/mountain3.png"),
    Plain("Plain","AP/MapTiles/grass1.png"),
    Lake("Lake","AP/MapTiles/lake.png"),
    Sea("Sea","AP/MapTiles/sea2.png");
    private BufferedImage img;
    private String tileName;

    public String getTileName()
    {
        return tileName;
    }

    public BufferedImage getImg()
    {
        return img;
    }

    TileType(String tileName,String imgpath)
    {
        this.tileName = tileName;
        try
        {
            img = ImageIO.read(new File(Main.class.getResource(imgpath).getPath()));
            System.out.println(new File(Main.class.getResource(imgpath).getPath())+"   "+imgpath+"    "+tileName);
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.out.println(Main.class.getResource(imgpath).getPath());
        }
    }
}
