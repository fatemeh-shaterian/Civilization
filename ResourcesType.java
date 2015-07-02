package com.arc;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public enum  ResourcesType
{

    Horse("HOrse","AP/Resources/Horse/horse3.png"),
    Iron("Iron","AP/Resources/Iron/iron.png"),
    Mine("Mine","AP/Resources/Mine/mine2.png");


    private BufferedImage img;
    private String resourcesName;

    public String getTileName()
    {
        return resourcesName;
    }

    public BufferedImage getImg()
    {
        return img;
    }

    ResourcesType(String resourcesName,String imgPath)
    {
        this.resourcesName = resourcesName;
        try
        {
            img = ImageIO.read(new File(Main.class.getResource(imgPath).getPath()));
            System.out.println(new File(Main.class.getResource(imgPath).getPath())+"   "+imgPath+"    "+resourcesName);
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.out.println(Main.class.getResource(imgPath).getPath());
        }
    }
}
