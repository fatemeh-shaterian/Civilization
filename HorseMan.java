package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/23/14
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class HorseMan extends Warrior
{
    public static int mapNumber=9;
    static int constructionNeed = 200;
    public static int[] horsemanDefence = {0,20,20};
    public HorseMan(String path, int x, int y,int owner,int cityName) throws IOException
    {
        super(path, x, y,owner,cityName);
        health = 60;
        defence = horsemanDefence[owner];
        attack = 25;
        super.constructionNeed = 200;
        movePerSet = 3;
        timeOfTrain = 4;
        foodPerSet = 12;
        super.mapNumber = 9;
        constructionBtn = 6;
        //pish niaz!!!!!!!!!!!!!!!
    }
}

