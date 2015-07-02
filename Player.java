package com.arc;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/26/14
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player
{
    protected static int turnCounter;
    protected int iD;
    protected String color;
    public int construction;
    //protected ArrayList<City> cities = new ArrayList<City>(10);
    protected ArrayList<Technology>technologies = new ArrayList<Technology>(10);
    protected Troop[] firstTroop = new Troop[3];
    public static int[] mapNumber = {Settler.mapNumber,Worker.mapNumber,SwordMan.mapNumber};
    protected int allFoodNeed;
    protected int foodAmount ;
    protected int unemployedPopulation;
    protected int trainSpeed; //the effect of technology on train speed or speed of build troops
    protected int constructionSpeed; //the effect of technology on construction speed
    protected ArrayList<Technology>researchedTec = new ArrayList<Technology>();
    protected ArrayList<Technology> canResearchTec = new ArrayList<Technology>();
    public String WorkerPath;
    public String SwordManPath;
    public String ArcheryPath;
    public String SpearManPath;
    public String HoursManPath;
    private int firstWarriorX;
    private int firstWarriorY;
    private int firstWorkerX;
    private int firstWorkerY;
    public int lackOfFood;
    public int lackAmount;
    public int plentyOfFood;
    public int plentyAmount;
    public boolean hasGranary = false;
    //public boolean[] btnNumber;


    public Player(int iD,String color,int [][]whichIsOnPlan) throws IOException
    {
        //btnNumber =new boolean[13];//number of Btn
        //for (int i = 0;i<btnNumber.length;i++)
        //    btnNumber[i] = true;
        this.iD = iD;
        this.color = color;
        this.construction = 1000;
        constructionSpeed = 1;
        foodAmount = 100000;
        unemployedPopulation = 10;
        WorkerPath = String.format("src/com/arc/AP/worker/"+color+".png");
        SwordManPath = String.format("src/com/arc/AP/Swordsman/"+color+".png");
        ArcheryPath = String.format("src/com/arc/AP/Archery"+color+".png");
        SpearManPath = String.format("src/com/arc/AP/Spearman"+color+".png");
        HoursManPath = String.format("src/com/arc/AP/Horseman"+color+".png");
        firstTroop[0] = new Settler("src/com/arc/AP/settler/settler.png",iD,0,whichIsOnPlan);
        firstWorkerX = firstTroop[0].x-1;
        firstWorkerY = firstTroop[0].y+1;
        firstWarriorX = firstTroop[0].x+1;
        firstWarriorY = firstTroop[0].y+1;
        while(whichIsOnPlan[firstWorkerX][firstWorkerY]!=1)
       {
            firstWorkerY+=1;
       }
        while(whichIsOnPlan[firstWarriorX][firstWarriorY]!=1)
        {
            firstWarriorY+=1;
        }

        firstTroop[1] = new Worker(WorkerPath,firstWorkerX,firstWorkerY,iD,0);
        firstTroop[2] = new SwordMan(SwordManPath,firstWarriorX,firstWarriorY,iD,0);
        System.out.println("first location x = "+firstWarriorX+" y = "+firstWarriorY);
        unemployedPopulation = 10;
        canResearchTec.add(new Agriculture());
        canResearchTec.add(new Mining());
        canResearchTec.add(new Masonry());
        canResearchTec.add(new WarriorCode());
        canResearchTec.add(new Writing());


       // cities.troops.add(new Settler("src/com/arc/AP/settler/settler.png"));
    }

    //methods

    public void calculateTrainSpeed(ArrayList<BuildingInCity> buildingInCities)
    {
        int numberOfLibrary = 0;
        for(int i = 0;i<buildingInCities.size();i++)
        {
            if(buildingInCities.get(i) instanceof Library)
            {
                if(((Library)buildingInCities.get(i)).hasWorker == true)
                    numberOfLibrary++;
            }
        }

        trainSpeed = 5+10*numberOfLibrary;
    }
     //list of technology
    public void calculateFoodAmount(ArrayList<MapElement> buildings)
    {
        int zarib = 20;
        int numberOfFarm = 0;
        for (int i = 0;i<buildings.size();i++)
        {
            if(buildings.get(i) instanceof Farm)
                if((((Farm)buildings.get(i)).owner == iD)&&(((Farm)buildings.get(i)).hasWorker))
                {   if(((Farm)buildings.get(i)).hasWorker == true)
                        numberOfFarm++;
                }

            if((buildings.get(i) instanceof Granary)&&(((Granary)buildings.get(i)).hasWorker == true))
                foodAmount += ((Granary)buildings.get(i)).getFoodAmount();
        }
        for(int t= 0;t<researchedTec.size();t++)
            if(researchedTec.get(t).number == 1)
                zarib+=10;
        foodAmount += numberOfFarm*zarib+trainSpeed/2;
    }

    public void calculateFoodNeed(ArrayList<MapElement> troops,ArrayList<MapElement>building,ArrayList<BuildingInCity>buildingInCities)
    {
        for (int i = 0;i<troops.size();i++)
        {
            if (((Troop)troops.get(i)).owner == iD)
                allFoodNeed += ((Troop)troops.get(i)).getFoodPerSet();
        }
        for (int i = 0;i<building.size();i++)
        {
            if (((Building)building.get(i)).hasWorker)
                allFoodNeed+=2;
        }
        for(int i = 0;i<buildingInCities.size();i++)
        {
            if(((BuildingInCity)buildingInCities.get(i)).hasWorker)
                allFoodNeed+=2;
        }
        allFoodNeed += 2*unemployedPopulation;
    }
    public void calculateConstruction(ArrayList<MapElement> buildings)
    {
        int zarib = 1;
        int numberOfMine = 0;

        for(int i = 0;i<buildings.size();i++)
        {
            if ((buildings.get(i) instanceof Mine)&&(((Mine)buildings.get(i)).owner == iD))
            {
                numberOfMine++;
            }
            construction += numberOfMine*20*constructionSpeed;
        }
        for(int i = 0;i<researchedTec.size();i++)
        {
            if(researchedTec.get(i).number == 2)
                zarib = 2;
            if(researchedTec.get(i).number == 3)
                zarib = 3;
        }
    }

    public void increasePopulation(ArrayList<BuildingInCity>buildingInCities,ArrayList<MapElement>resources)
    {   plentyOfFood = 0;
        int counter = 0;
        int increasedPopulation = plentyAmount/10;
        if(increasedPopulation>0)
        {
            while((increasedPopulation>0)&&(counter<resources.size())&&(((Building)resources.get(counter)).owner == iD))
            {
                System.out.println("a");
                if(resources.get(counter) instanceof Farm && (((Building)resources.get(counter)).hasWorker == false))
                {
                    ((Building)resources.get(counter)).hasWorker = true;

                    increasedPopulation--;
                }
                counter++;
            }
            counter = 0;
            while((increasedPopulation>0)&&(counter<resources.size())&&(((Building)resources.get(counter)).owner == iD))
            {   System.out.println("b");
                if(resources.get(counter) instanceof Mine && (((Building)resources.get(counter)).hasWorker == false))
                {
                    ((Building)resources.get(counter)).hasWorker = true;

                    increasedPopulation--;
                }
                counter++;
            }
            counter = 0;
            while((increasedPopulation>0)&&(counter<resources.size())&&(((Building)resources.get(counter)).owner == iD))
            {
                System.out.println("c");
                if(resources.get(counter) instanceof Granary && (((Building)resources.get(counter)).hasWorker == false))
                {
                    ((Building)resources.get(counter)).hasWorker = true;

                    increasedPopulation--;
                }
                counter++;
            }
            counter = 0;
            while((increasedPopulation>0)&&(counter<resources.size())&&(((Building)resources.get(counter)).owner == iD))
            {
                System.out.println("d");
                if(resources.get(counter) instanceof Barracks && (((Building)resources.get(counter)).hasWorker == false))
                {
                    ((Building)resources.get(counter)).hasWorker = true;

                    increasedPopulation--;
                }
                counter++;
            }
            counter = 0;
            while((increasedPopulation>0)&&(counter<buildingInCities.size()))
            {
                System.out.println("e");
                if(buildingInCities.get(counter).owner == iD)
                {
                    if(buildingInCities.get(counter).hasWorker == false)
                    {
                    buildingInCities.get(counter).hasWorker = true;
                    increasedPopulation--;

                    }

                }
                counter++;
            }

        }
        if(increasedPopulation>0)
            unemployedPopulation += (plentyAmount)/10;
    }
    public void decreasePopulation(ArrayList<BuildingInCity>buildingInCities,ArrayList<MapElement>resources,ArrayList<MapElement>troop)
    {
        int victim = (lackAmount)/70;
        lackAmount = 0;
        allFoodNeed = 0;
        System.out.println("victim: "+victim);
        if(victim>0)
        {
            if((unemployedPopulation>0))
            {
                if(unemployedPopulation-victim>0)
                {
                    unemployedPopulation-=victim;
                    victim =0;
                }
                else
                {
                    victim-=unemployedPopulation;
                    unemployedPopulation = 0;
                }
            }
            //koshtan
            if((victim>0))
            {
                int counter = 0;
                while((victim>0)&&(counter<buildingInCities.size()))
                {
                    System.out.println("f");
                    if(buildingInCities.get(counter).owner == iD)
                    {
                        if(buildingInCities.get(counter).hasWorker)
                        {
                        buildingInCities.get(counter).hasWorker = false;
                        victim--;

                        }
                    }
                    counter++;
                }
            }
            if (victim>0)
            {
                int counter = 0;
                while((victim>0)&&(counter<resources.size()))
                {
                    System.out.println("g");
                    if((resources.get(counter) instanceof Barracks)&&(((Building)resources.get(counter)).owner == iD)&&(((Building)resources.get(counter)).hasWorker))
                    {
                        ((Building)resources.get(counter)).hasWorker = false;

                        victim--;
                    }
                    counter++;
                }
                counter = 0;
                while((victim>0)&&(counter<resources.size()))
                {
                    System.out.println("h");
                    if((resources.get(counter) instanceof Granary)&&(((Building)resources.get(counter)).owner == iD)&&(((Building)resources.get(counter)).hasWorker))
                    {
                        System.out.println("o");
                        ((Building)resources.get(counter)).hasWorker = false;

                        victim--;
                    }
                    counter++;
                }
                counter = 0;
                while((victim>0)&&(counter<resources.size())&&(((Building)resources.get(counter)).owner == iD)&&(((Building)resources.get(counter)).hasWorker))
                {
                    System.out.println("i");
                    if(resources.get(counter) instanceof Mine)
                    {
                        ((Building)resources.get(counter)).hasWorker = false;

                        victim--;
                    }
                    counter++;
                }
                counter = 0;
                while((victim>0)&&(counter<resources.size())&&(((Building)resources.get(counter)).owner == iD)&&(((Building)resources.get(counter)).hasWorker))
                {
                    System.out.println("j");
                    if(resources.get(counter) instanceof Farm)
                    {
                        ((Building)resources.get(counter)).hasWorker = false;

                        victim--;
                    }
                    counter++;
                }
                counter = 0;
                while ((victim>0)&&(counter<troop.size()))
                {
                    if (((Troop)troop.get(counter)).owner == iD)
                    {
                        troop.remove(counter);
                        victim--;
                        counter--;
                    }
                    counter++;
                }

                System.out.println("every one died\nyou lose player"+iD);
            }
        }
    }


    public void manageFood(ArrayList<BuildingInCity>buildingInCities,ArrayList<MapElement>resources)
    {
       // boolean hasGranary = false;
        if(foodAmount-allFoodNeed>0)
        {
            plentyAmount = foodAmount-allFoodNeed;
            if(lackOfFood>0)
                lackOfFood--;
            else
            {
                plentyOfFood++;
            }
            foodAmount -= allFoodNeed;
            allFoodNeed = 0;
            for(int i = 0;i<resources.size();i++)
            {
                if (resources.get(i) instanceof Granary)
                {
                    if ((((Granary)resources.get(i)).owner == iD)&&(foodAmount+((Granary)resources.get(i)).getFoodAmount()<400))
                    {
                       // hasGranary = true;
                        ((Granary)resources.get(i)).setFoodAmount(((Granary)resources.get(i)).getFoodAmount()+foodAmount);
                        foodAmount = 0;
                    }
                    if ((((Granary)resources.get(i)).owner == iD)&&(foodAmount+((Granary)resources.get(i)).getFoodAmount()>=400))
                    {
                       // hasGranary = true;
                        foodAmount -= 400-((Granary)resources.get(i)).getFoodAmount();
                        ((Granary)resources.get(i)).setFoodAmount(400);
                    }
                }
            }
            if(hasGranary == false)
                foodAmount = 0;
        }
        if (getFoodAmount()-allFoodNeed<0)
        {
            lackOfFood++;
            allFoodNeed -= foodAmount;
            foodAmount = 0;
            lackAmount = allFoodNeed;
        }
    }

//setter&getter

    public static int getTurnCounter()
    {
        return turnCounter;
    }

    public static void setTurnCounter(int turnCounter) {
        Player.turnCounter = turnCounter;
    }

    public ArrayList<Technology> getCanResearchTec() {
        return canResearchTec;
    }

    public void setCanResearchTec(ArrayList<Technology> canResearchTec) {
        this.canResearchTec = canResearchTec;
    }

    public ArrayList<Technology> getResearchedTec() {

        return researchedTec;
    }

    public void setResearchedTec(ArrayList<Technology> researchedTec) {
        this.researchedTec = researchedTec;
    }

    public int getTrainSpeed() {

        return trainSpeed;
    }

    public void setTrainSpeed(int trainSpeed) {
        this.trainSpeed = trainSpeed;
    }

    public int getUnemployedPopulation() {
        return unemployedPopulation;
    }

    public void setUnemployedPopulation(int unemployedPopulation) {
        this.unemployedPopulation = unemployedPopulation;
    }

    public int getFoodAmount()
    {
            return foodAmount;
    }

    public void setFoodAmount(int foodAmount)
    {
        this.foodAmount = foodAmount;
    }

    public int getAllFoodNeed()
    {
        return allFoodNeed;
    }

    public void setAllFoodNeed(int allFoodNeed) {
        this.allFoodNeed = allFoodNeed;
    }

    public void setFirstTroop(Troop[] firstTroop) {

        this.firstTroop = firstTroop;
    }

    public void setTechnologies(ArrayList<Technology> technologies) {

        this.technologies = technologies;
    }

    public void setConstruction(int construction) {

        this.construction = construction;
    }

    public int getiD() {

        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Troop[] getFirstTroop() {
        return firstTroop;
    }

    public ArrayList<Technology> getTechnologies() {

        return technologies;
    }
}
