package com.arc;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/26/14
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Troop extends MapElement
{

    protected int owner;
    protected int cityName;
    public int constructionBtn;
    protected static int movePerSet;
    protected int constructionNeed;
    protected int mapNumber;
    protected int health;
    protected int defence;
    protected static int foodPerSet;
    protected int number;//the number of butten in constructor panel
    // pish niaz
    //protected boolean canTrain;
    protected int timeOfTrain;
    protected int howLongTrained;
    protected boolean Selected;
    protected boolean orderedPerSet = false;
    //method pish niaz




    public Troop(String path, int x, int y,int owner,int cityName) throws IOException
    {
        super(path, x, y);
        this.owner = owner;
        this.cityName = cityName;
    }

    public Troop(BufferedImage image, int x, int y,int owner,int cityName)
    {
        super(image, x, y);
        this.owner = owner;
        this.cityName = cityName;
    }

    public boolean canMove(int x,int y)
    {
        if ((this.y+3>y)&&(y>this.y-3)&&(this.x+3>x)&&(x>this.x-3))
            //if((this.x+1==0)&&(x != this.x+2))

            return true;
        return false;
    }
    public void move(int x,int y)
    {
        this.x = x;
        this.y = y;
    }





    //setter & getter


    public boolean isSelected() {
        return Selected;
    }

    public void setSelected(boolean selected) {
        Selected = selected;
    }

    public int getNumber()

    {
        return number;
    }

    public int getFoodPerSet() {

        return foodPerSet;
    }

    public void setFoodPerSet(int foodPerSet) {
        this.foodPerSet = foodPerSet;
    }


    public int getConstructionNeed() {
        return constructionNeed;
    }

    public int getTimeOfTrain() {
        return timeOfTrain;
    }

    public void setTimeOfTrain(int timeOfTrain) {
        this.timeOfTrain = timeOfTrain;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getDefence() {

        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHealth() {

        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMovePerSet() {

        return movePerSet;
    }

    public void setMovePerSet(int movePerSet) {
        this.movePerSet = movePerSet;
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
