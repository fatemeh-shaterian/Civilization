package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/23/14
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Farm extends Building
{
    public static int mapNumber = 10;
    public static int constructionNeed = 40;
    public static int defence = 30;
    public static int timeOfBuild = 1;


    public Farm(String path, int x, int y,int owner,int cityName) throws IOException {
        super(path,x,y,owner,cityName);
        super.mapNumber = 10;
        constructionNeed = 40;
        defence = 30;
        timeOfBuild = 1;
    }
}