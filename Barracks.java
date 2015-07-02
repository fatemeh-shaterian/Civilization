package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 4/16/14
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Barracks extends Building
{
    public static int mapNumber = 13;
    public static int constructionNeed = 200;
    public static int defence = 60;
    //public static int timeOfBuild = 2;
    public Barracks(String path, int x, int y, int owner,int cityName) throws IOException
    {
        super(path, x, y, owner,cityName);
        super.mapNumber = 13;
        timeOfBuild = 2;
        constructionBtn = 2;
    }
}
