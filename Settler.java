package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/23/14
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Settler extends Troop
{
    public static int mapNumber = 4;
    static int constructionNeed = 70;
    public Settler(String path, int x, int y,int owner,int cityName) throws IOException
    {
        super(path, x, y,owner,cityName);
        health = 1;
        defence = 1;
        super.constructionNeed = 70;
        movePerSet = 1;
        timeOfTrain = 1;
        foodPerSet = 4;
        number = 11;
        super.mapNumber = 4;
    }

    public Settler(String path,int owner,int cityName,int[][]whichIsOnPlan) throws IOException
    {
        super(path,(int) (Math.random()*20),(int) (Math.random()*30),owner,cityName);
        int randX = x;
        int randY = y;
        //System.out.println(whichIsOnPlan[(int) (Math.random()*29)][(int) (Math.random()*38)]);
        while (whichIsOnPlan[x][y]!=1)
        {
           //System.out.println(whichIsOnPlan[(int) (Math.random()*29)][(int) (Math.random()*38)]);
            randX = (int) (Math.random()*29);
            randY = (int) (Math.random()*38);
        }

        x = randX;
        y = randY;

        health = 1;
        defence = 1;
        constructionNeed = 70;
        movePerSet = 1;
        timeOfTrain = 1;
        foodPerSet = 4;
        number = 11;
        super.mapNumber = 4;

    }


    public static boolean canMakeCity()
    {
        return true;
    }
    //make city

    //setter and getter


}
