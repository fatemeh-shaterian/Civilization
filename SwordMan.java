package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/26/14
 * Time: 7:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class SwordMan extends Warrior
{
    public static int mapNumber = 7;
    static int constructionNeed = 100;
    public static int [] swordManDefence = {0,10,10};
    public SwordMan(String path, int x, int y,int owner,int cityName) throws IOException {
        super(path, x, y,owner,cityName);
        super.constructionNeed = 100;
        movePerSet = 1;
        attack = 20;
        defence = swordManDefence[owner];
        health = 40;
        timeOfTrain = 3;
        foodPerSet = 8;
        number = 8;
        super.mapNumber = 7;
        constructionBtn = 8;
    }
}