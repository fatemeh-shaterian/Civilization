package com.arc;

import java.io.IOException;
import java.util.ArrayList;

public class City extends MapElement
{
    public City(String path, int x, int y,int owner,int name) throws IOException
    {
        super(path, x, y);
        population = 1;
        this.owner = owner;
        this.name = name;
        cityCounter++;
        defence = 100;
        health = 200;
        cityChangeOwner = false;
        makeSettler = false;
        makeWorker = false;
    }
    protected int name;//the name of city
    protected int owner;//ID of the player that is owner of this city
    protected int radius;
    protected int population;
    protected int foodAmount;
    protected int foodNeed;
    protected int constructionSpeed;
    public static int cityCounter = 0;
    private int defence;
    private int health;
    private boolean cityChangeOwner;
    public boolean makeWorker;
    public boolean makeSettler;




    //methods

//Gostaresh shar???????????????????????


//?????
    void reasearch(Technology cityTechnology)
    {}

//????
    void construct(Building cityBuilding)
    {}
//????
    void train(Troop cityTroop)
    {}

    public static boolean canBuildCity(int tile)
    {
       if ((tile !=0)&&(tile != 2)&&(tile != 3)) //if not sea,lake & mountain
            return true;
       return false;
    }
    public void calculateFoodAmount(ArrayList<Building>buildings)

    {
        int numberOfFarm = 0;
        for (int i = 0;i<buildings.size();i++)
        {
            if(buildings.get(i) instanceof Farm)
            {
                numberOfFarm++;
            }
        }
//formul chek shavadd!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        foodAmount = numberOfFarm*20;
    }
    public void calculateFoodNeed(ArrayList<Troop>troops)
    {
        int troopFood = 0;
        for (int i = 0;i<troops.size();i++)
        {
            troopFood += troops.get(i).foodPerSet;
        }
        foodNeed = troopFood;
    }
    //tozih bishtar!!!!!!!!!!!!!!
    public void managePopulation()
    {}

    void attack(Troop cityTroop)
    {}
    void defend(Troop cityTroop)
    {}


    //setter&getter


    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getConstructionSpeed() {

        return constructionSpeed;
    }

    public void setConstructionSpeed(int constructionSpeed) {
        this.constructionSpeed = constructionSpeed;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getOwner() {
        return owner;
    }

    public int getRadius() {
        return radius;
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getName() {

        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
    public int getFoodNeed() {
        return foodNeed;
    }

    public void setFoodNeed(int foodNeed) {
        this.foodNeed = foodNeed;
    }

    public static int getCityCounter() {
        return cityCounter;
    }

    public static void setCityCounter(int cityCounter) {
        City.cityCounter = cityCounter;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefence() {

        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public boolean isCityChangeOwner() {
        return cityChangeOwner;
    }

    public void setCityChangeOwner(boolean cityChangeOwner) {
        this.cityChangeOwner = cityChangeOwner;
    }
}