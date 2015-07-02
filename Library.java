package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 4/2/14
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Library extends BuildingInCity{

    public static int constructionNeed = 200;
    public static int defence = 20;
    public static int mapNumber = 15;

    public Library(int owner,int cityName) throws IOException {
        super(owner,cityName);
        super.constructionNeed = 200;
        timeOfBuild = 3;
        super.mapNumber = 15;
        constructionBtn = 4;

    }
}
