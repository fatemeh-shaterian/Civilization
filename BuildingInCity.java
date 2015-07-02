package com.arc;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 4/17/14
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildingInCity
{
    protected int owner;
    protected int cityName;
    protected int constructionBtn;
    protected int constructionNeed;
    protected int defence;
    protected int Health;
    protected int timeOfBuild;
    protected int howLongBuild;
    protected int mapNumber;
    protected boolean canBuilt;
    public boolean hasWorker;

    public BuildingInCity(int owner,int cityName)
    {
        this.owner = owner;
        this.cityName = cityName;
        hasWorker = true;
    }

    public int getConstructionBtn() {
        return constructionBtn;
    }

    public void setConstructionBtn(int constructionBtn) {
        this.constructionBtn = constructionBtn;
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

