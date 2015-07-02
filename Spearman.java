package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/23/14
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Spearman extends Warrior
{
    public static int mapNumber = 8;
    static int constructionNeed = 80;
    public static int [] spearmanDefence = {0,20,20};
    public Spearman(String path, int x, int y,int owner,int cityName) throws IOException
    {

        super(path, x, y,owner,cityName);
        health = 30;
        defence = spearmanDefence[owner];
        attack = 10;
        super.constructionNeed = 80;
        movePerSet = 1;
        //pish niaz
        timeOfTrain = 2;
        foodPerSet = 4;
        number = 9;
        super.mapNumber = 8;
        constructionBtn = 9;

    }



}
