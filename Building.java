package com.arc;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/26/14
 * Time: 8:52 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Building extends MapElement{
    protected int owner;
    protected int cityName;
    protected int constructionBtn;
    protected int constructionNeed;
    protected int defence;
    protected int Health;
    protected int timeOfBuild;
    protected int howLongBuild;
    protected int mapNumber;
    public boolean hasWorker;

    //pish niaz
    protected boolean canBuilt;

    public Building(String path, int x, int y,int owner,int cityName) throws IOException {
        super(path, x, y);
        this.owner = owner;
        hasWorker = true;
    }

    //maziat???


    //methods

    public static boolean isCanBuilt()
    {
        //is there any worker to do this??worker should do it????
        //where should it be build??????
        return true;
    }

    //setter & getter


    public void setCanBuilt(boolean canBuilt) {
        this.canBuilt = canBuilt;
    }

    public int getMapNumber() {

        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }


    public int getTimeOfBuild() {

        return timeOfBuild;
    }

    public void setTimeOfBuild(int timeOfBuild) {
        this.timeOfBuild = timeOfBuild;
    }

    public int getHealth() {

        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public int getDefence() {

        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getConstructionNeed() {

        return constructionNeed;
    }

    public void setConstructionNeed(int constructionNeed) {
        this.constructionNeed = constructionNeed;
    }

    public int getCityName() {

        return cityName;
    }

    public void setCityName(int cityName) {
        this.cityName = cityName;
    }

    public int getOwner() {

        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
