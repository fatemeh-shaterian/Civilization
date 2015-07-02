package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/29/14
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Archery extends Warrior
{
    public static int mapNumber =6;
    public static int constructionNeed = 80;
    public static int[] archeryDefence = {0,10,10};
    public Archery(String path, int x, int y,int owner,int name) throws IOException
    {
        super(path, x, y,owner,name);
        health = 30;

        defence = archeryDefence[owner];
        System.out.println(defence);

        attack = 15;
        super.constructionNeed = 80;
        movePerSet = 1;
        timeOfTrain = 3;
        foodPerSet = 6;
        number = 7;
        super.mapNumber = 6;
        constructionBtn = 7;
    }
}
