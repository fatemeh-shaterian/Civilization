package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/23/14
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mine extends Building
{
    public static int mapNumber = 11;
    public static int constructionNeed = 80;
    public static int defence = 60;
    //public static int timeOfBuild = 5;

    public Mine(String path, int x, int y,int owner,int cityName) throws IOException {
        super(path,x,y,owner,cityName);
        super.mapNumber = 11;
        timeOfBuild = 5;
        constructionBtn = 1;
    }
}

