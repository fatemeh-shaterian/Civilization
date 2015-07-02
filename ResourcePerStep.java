package com.arc;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/26/14
 * Time: 10:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourcePerStep extends MapElement
{
    public ResourcePerStep(BufferedImage image, int x, int y)
    {
        super(image, x, y);
    }

    public ResourcePerStep(String path, int x, int y) throws IOException
    {
        super(path, x, y);
    }
}
