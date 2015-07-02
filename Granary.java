package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 4/16/14
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Granary extends Building
{
    public static int mapNumber = 12;
    public static int constructionNeed = 100;
    public static int defence = 40;
    private int foodAmount;
    //public static int timeOfBuild = 2;
    public Granary(String path, int x, int y,int owner,int cityName) throws IOException
    {
        super(path, x, y, owner,cityName);
        timeOfBuild = 2;
        constructionBtn = 3;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }
}
