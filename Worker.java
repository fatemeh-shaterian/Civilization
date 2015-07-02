package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/26/14
 * Time: 7:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Worker extends Troop
{
    public static int mapNumber =5;
    static int constructionNeed = 80;
    protected boolean hasWork;
    protected int workCounter = 0;
    protected int timeOfWork;

    public Worker(String path, int x, int y,int owner,int cityName) throws IOException
    {
        super(path, x, y,owner,cityName);
        health = 1;
        defence = 1;
        super.constructionNeed = 80;
        movePerSet = 1;
        timeOfTrain = 1;
        foodPerSet =4;
        number = 11;
        hasWork = false;
        super.mapNumber = 5;
     }




    //setter&getter


    public boolean isHasWork() {
        return hasWork;
    }

    public void setHasWork(boolean hasWork) {
        this.hasWork = hasWork;
    }
}
