package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 4/16/14
 * Time: 11:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class StableB extends BuildingInCity
{
    public static int mapNumber = 14;
    public static int constructionNeed = 400;
    public static int defence = 80;

    public StableB(int owner,int cityName) throws IOException
    {
        super(owner,cityName);
        super.constructionNeed = 400;
        timeOfBuild = 6;
        super.mapNumber = 14;

    }
}
