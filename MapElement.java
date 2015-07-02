package com.arc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class MapElement {
    protected int x,y;
    private BufferedImage image;

    public MapElement(BufferedImage image, int x, int y)
    {
        this.x = x ;
        this.y = y ;
        this.image = image ;
    }

    public MapElement(String path, int x, int y) throws IOException{
        this.x = x;
        this.y = y;

        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Image "+path+" not found.");
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}

