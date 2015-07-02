package com.arc;

import com.arc.AP.GraphicsEngine;
import java.io.IOException;
import java.util.ArrayList;

public class

        GameEngine implements IGameEngine
{
    //Data Definition
    Map map ;
    GraphicsEngine graphicsEngine;
    private ArrayList<MapElement> city;
    private ArrayList<MapElement> troop;
    private ArrayList<MapElement> resources;
    private ArrayList<MapElement> loading;
    private ArrayList<BuildingInCity> loadingInCity;
    private ArrayList<BuildingInCity> buildingInCities;
    public int [][]whichIsOnPlan;
    private Player player1;
    private Player player2;
    private String[] canResearchTec;
    private Technology[] researchTec;
    private static int turnCounter;
    private int clickBuffer; //kid of a ting that clicked on it
    private int locationBufferX;
    private int locationBufferY;
    private int troopLocationX;
    private int troopLocationY;
    private int troopKid;
    private boolean troopIsSelected;

    private int fighterKind;
    private int fighterX;
    private int fighterY;
    private boolean wantFight;

    Warrior fighter ;
    Warrior fighted ;

    boolean a = true;

    private int hoursemanMoveCounter;

    public static int getTurnCounter()
    {
        return turnCounter;
    }

    public GameEngine() throws IOException
    {
        this.city = new ArrayList<MapElement>() ;
        this.troop = new ArrayList<MapElement>() ;
        this.resources = new ArrayList<MapElement>() ;
        this.loading = new ArrayList<MapElement>();
        this.loadingInCity = new ArrayList<BuildingInCity>();
        this.buildingInCities = new ArrayList<BuildingInCity>();
        this.map = new Map(city , troop , resources , 30 , 40 , 80) ;
        this.graphicsEngine= new GraphicsEngine(map , this) ;
        whichIsOnPlan = new int [map.Tiles.length][map.Tiles[0].length];
        for (int i = 0;i<whichIsOnPlan.length;i++)// make whichIsOnPlan (0123)
        {
            for (int j = 0;j<whichIsOnPlan[0].length;j++)
            {
                whichIsOnPlan[i][j] = map.Tiles[i][j];
                if((whichIsOnPlan[i][j]!=0)&&(whichIsOnPlan[i][j]!=2)&&(whichIsOnPlan[i][j]!=3))
                    whichIsOnPlan[i][j] = 1;
            }
        }

        player1 = new Player(1, "blue",whichIsOnPlan);
        for (int i = 0; i < 3; i++)
        {
            troop.add(player1.firstTroop[i]);
            whichIsOnPlan[player1.firstTroop[i].x][player1.firstTroop[i].y] = player1.firstTroop[i].mapNumber;
        }
        graphicsEngine.UpdateScreen();

        if(turnCounter == 0)//player1
        {
            Technology.whichTecCanResearch(player1.researchedTec,player1.canResearchTec);
            canResearchTec = new String[player1.canResearchTec.size()];
            for (int i = 0;i<player1.canResearchTec.size();i++)
            {
                canResearchTec[i] = player1.canResearchTec.get(i).name;
            }
        }
        graphicsEngine.setResearchs(canResearchTec) ;
    }

    @Override
    public void clickOnButton(int btn) throws IOException
    {
        System.out.println("Clicked on Button with Number : "+btn);
        int beginTurn = GameEngine.getTurnCounter();
        System.out.println(beginTurn);
        //make city by player1 whit settler
        if ((btn == 0)&&(turnCounter%2 == 0)&&(clickBuffer == Settler.mapNumber))
        {
            city.add(new City("src/com/arc/AP/City/small.png",locationBufferX,locationBufferY,1,City.cityCounter));
            player1.unemployedPopulation++;
            //shomareye shahr ha az 30 be bade
            whichIsOnPlan[player1.firstTroop[0].x][player1.firstTroop[0].y] = ((City)city.get(city.size()-1)).name+30;
            for(int i = 0;i<troop.size();i++)
            {
                if (troop.get(i) instanceof Settler)
                    if(((Settler) troop.get(i)).owner == 1)
                    {
                        troop.remove(i);
                    }
            }
            map.setTroop(troop);
        }
        //make city by player2 with settler
        if ((btn == 0)&&(turnCounter%2 == 1)&&(clickBuffer == Settler.mapNumber))
        {
            city.add(new City("src/com/arc/AP/City/small.png",player2.firstTroop[0].x,player2.firstTroop[0].y,2,City.cityCounter));
            whichIsOnPlan[player2.firstTroop[0].x][player2.firstTroop[0].y] = ((City)city.get(city.size()-1)).name+30;
            player2.unemployedPopulation++;
            for(int i = 0;i<troop.size();i++)
            {
                if (troop.get(i) instanceof Settler)
                    if(((Settler) troop.get(i)).owner == 2)
                    {
                        troop.remove(i);
                    }
            }
            map.setTroop(troop);
        }
        //add setter to selected city by player1 and 2
        if((btn == 11)&&(clickBuffer>9))
        {
            if(((player1.construction-(Settler.constructionNeed)>0)&&(turnCounter%2==0)&&(player1.unemployedPopulation>0))
                    ||((player2.construction-(Settler.constructionNeed)>0)&&(turnCounter%2==1))&&(player2.unemployedPopulation>0))
            {
                int j = 0;
                int settlerLocationX = locationBufferX+1;
                int settlerLocationY = locationBufferY;
                for(int i = 0;i<city.size();i++)
                    if((((City)city.get(i)).x == locationBufferX)&&(((City)city.get(i)).y == locationBufferY))
                        j = i;
                if ((((City)city.get(j)).owner == (turnCounter%2)+1)&&(((City)city.get(j)).makeSettler == false))
                {
                    while ((whichIsOnPlan[settlerLocationX][settlerLocationY]!=1) )
                        settlerLocationX+=1;
                    troop.add(new Settler("src/com/arc/AP/settler/settler.png",settlerLocationX,settlerLocationY,(turnCounter%2)+1,((City)city.get(j)).name));
                    ((City)city.get(j)).makeSettler = true;
                    if((turnCounter%2==0))//player1
                    {
                        player1.construction -= Settler.constructionNeed;
                        player1.unemployedPopulation--;
                    }
                    if(turnCounter%2==1)//player2
                    {
                        player2.construction -= Settler.constructionNeed;
                        player2.unemployedPopulation--;
                    }

                    whichIsOnPlan[troop.get(troop.size()-1).x][troop.get(troop.size()-1).y] = Settler.mapNumber;//4
                }
            }
            map.setTroop(troop);
        }
        //add worker to selected city by player1
        if((btn == 10)&&(clickBuffer>9)&&(turnCounter%2==0)&&(player1.construction-(Worker.constructionNeed)>0)&&(player1.unemployedPopulation>0))
        {
            int j = 0;
            int workerLocationX = locationBufferX-1;
            int workerLocationY = locationBufferY;
            for(int i = 0;i<city.size();i++)
                if((((City)city.get(i)).x == locationBufferX)&&(((City)city.get(i)).y == locationBufferY))
                    j = i;

            if((((City)city.get(j)).owner == 1)&&(((City)city.get(j)).makeWorker == false))
            {
                while ((whichIsOnPlan[workerLocationX][workerLocationY]!=1) )
                    workerLocationX-=1;
                ((City)city.get(j)).makeWorker = true;
                troop.add(new Worker(player1.WorkerPath,workerLocationX,workerLocationY,1,((City)city.get(j)).name));
                whichIsOnPlan[troop.get(troop.size()-1).x][troop.get(troop.size()-1).y] =Worker.mapNumber;//5
                player1.unemployedPopulation--;
                player1.construction -=Worker.constructionNeed;//80
            }

            map.setTroop(troop);
        }

        //add worker to selected city by player2
        if((btn == 10)&&(clickBuffer>9)&&(turnCounter%2==1)&&(player2.construction-(Worker.constructionNeed)>0)&&(player2.unemployedPopulation>0))
        {
            int j = 0;
            int workerLocationX = locationBufferX-1;
            int workerLocationY = locationBufferY;
            for(int i = 0;i<city.size();i++)
                if((((City)city.get(i)).x == locationBufferX)&&(((City)city.get(i)).y == locationBufferY))
                    j = i;
            if((((City)city.get(j)).owner == 2)&&(((City)city.get(j)).makeWorker == false))
            {
                while ((
                        whichIsOnPlan[workerLocationX][workerLocationY]!=1) )
                    workerLocationX-=1;
                ((City)city.get(j)).makeWorker = true;
                troop.add(new Worker(player2.WorkerPath,workerLocationX,workerLocationY,2,((City)city.get(j)).name));
                whichIsOnPlan[troop.get(troop.size()-1).x][troop.get(troop.size()-1).y] = Worker.mapNumber;
                player2.construction -= Worker.constructionNeed;
                player2.unemployedPopulation--;
            }


            map.setTroop(troop);
        }
        //spearman by city of player1
        if((btn == 9)&&(clickBuffer == 13)&&(turnCounter%2==0)&&((player1.construction-Spearman.constructionNeed)>0)&&(player1.unemployedPopulation>0))
        {
            int spearmanX;
            int spearmanY;
            int whichBarracks = -1;
            for (int i =0;i<resources.size();i++)
            {
                if((resources.get(i).x == locationBufferX)&&(resources.get(i).y == locationBufferY)&&(((Barracks)resources.get(i)).owner == 1))
                    whichBarracks = i;
            }
            //System.out.println(whichBarracks);
            if(whichBarracks!=-1)
            {
                spearmanX = locationBufferX;
                spearmanY = locationBufferY-1;
                while (whichIsOnPlan[spearmanX][spearmanY] != 1)
                {
                   spearmanY+=1;
                }
                loading.add(new Spearman("src\\com\\arc\\AP\\Spearman\\blue.png",spearmanX,spearmanY,1,((Barracks)resources.get(whichBarracks)).cityName));
                player1.construction-=Spearman.constructionNeed;
                player1.unemployedPopulation--;
                map.setTroop(troop);
            }
        }
        //spearman by city of player2
        if((btn == 9)&&(clickBuffer == 13)&&(turnCounter%2==1)&&((player2.construction-Spearman.constructionNeed)>0)&&(player2.unemployedPopulation>0))
        {
            int spearmanX;
            int spearmanY;
            int whichBarracks = -1;
            for (int i =0;i<resources.size();i++)
            {
                if((resources.get(i).x == locationBufferX)&&(resources.get(i).y == locationBufferY)&&(((Barracks)resources.get(i)).owner == 2))
                    whichBarracks = i;
            }
            if(whichBarracks != -1)
            {
                spearmanX = locationBufferX;
                spearmanY = locationBufferY-1;
                while (whichIsOnPlan[spearmanX][spearmanY] != 1)
                {
                    spearmanY+=1;
                }
                loading.add(new Spearman("src\\com\\arc\\AP\\Spearman\\red.png",spearmanX,spearmanY,2,((Barracks)resources.get(whichBarracks)).cityName));
                player2.construction-=Spearman.constructionNeed;
                player2.unemployedPopulation--;
            }
        }
        //horse man by city of player1
        if((btn == 6)&&(clickBuffer == 13)&&(turnCounter%2==0)&&((player1.construction-HorseMan.constructionNeed)>0)&&(player1.unemployedPopulation>0))
        {
            int horsemanX;
            int horsemanY;
            int whichBarracks = -1;
            boolean hasActiveStable = false;
            for (int i =0;i<resources.size();i++)
            {
                if((resources.get(i).x == locationBufferX)&&(resources.get(i).y == locationBufferY)&&(((Barracks)resources.get(i)).owner == 1))
                    whichBarracks = i;
            }
            for (int i = 0;i<buildingInCities.size();i++)
            {
                if(buildingInCities.get(i) instanceof StableB && (((StableB)buildingInCities.get(i)).owner == 1)&&(((StableB)buildingInCities.get(i)).hasWorker))
                {
                    hasActiveStable = true;
                }
            }
            if(hasActiveStable == true && whichBarracks!=-1)
            {
                horsemanX = locationBufferX;
                horsemanY = locationBufferY-1;
                while (whichIsOnPlan[horsemanX][horsemanY] != 1)
                {
                    horsemanY+=1;
                }
                loading.add(new HorseMan("src\\com\\arc\\AP\\Horseman\\blue.png",horsemanX,horsemanY,1,((Barracks)resources.get(whichBarracks)).cityName));
                player1.construction-=HorseMan.constructionNeed;
                player1.unemployedPopulation--;
            }
        }
        //horse man by city of player2
        if((btn == 6)&&(clickBuffer == 13)&&(turnCounter%2==1)&&((player2.construction-HorseMan.constructionNeed)>0)&&(player2.unemployedPopulation>0))
        {
            int horsemanX;
            int horsemanY;
            int whichBarracks = -1;
            boolean hasActiveStable = false;
            for (int i = 0;i<buildingInCities.size();i++)
            {
                if(buildingInCities.get(i) instanceof StableB && (((StableB)buildingInCities.get(i)).owner == 2)&&(((StableB)buildingInCities.get(i)).hasWorker))
                {
                    hasActiveStable = true;
                }
            }

            for (int i =0;i<resources.size();i++)
            {
                if((resources.get(i).x == locationBufferX)&&(resources.get(i).y == locationBufferY)&&(((Barracks)resources.get(i)).owner == 2))
                    whichBarracks = i;
            }
            horsemanX = locationBufferX;
            horsemanY = locationBufferY-1;
            while (whichIsOnPlan[horsemanX][horsemanY] != 1)
            {
                horsemanY-=1;
            }
            if(hasActiveStable&&whichBarracks != -1)
            {
                loading.add(new HorseMan("src\\com\\arc\\AP\\Horseman\\red.png",horsemanX,horsemanY,2,((Barracks)resources.get(whichBarracks)).cityName));
                player2.construction-=HorseMan.constructionNeed;
                player2.unemployedPopulation--;
            }
        }
        //archy man by city of player1
        if((btn == 7)&&(clickBuffer == 13)&&(turnCounter%2==0)&&((player1.construction-Archery.constructionNeed)>0)&&(player1.unemployedPopulation>0))
        {
            int archeryX;
            int archeryY;
            int whichBarracks = -1;
            for (int i =0;i<resources.size();i++)
            {
                if((resources.get(i).x == locationBufferX)&&(resources.get(i).y == locationBufferY)&&(((Barracks)resources.get(i)).owner == 1))
                    whichBarracks = i;
            }
            if(whichBarracks != -1)
            {
                archeryX = locationBufferX;
                archeryY = locationBufferY-1;
                while (whichIsOnPlan[archeryX][archeryY] != 1)
                {
                    archeryY-=1;
                }
                loading.add(new Archery("src\\com\\arc\\AP\\Archery\\blue.png",archeryX,archeryY,1,((Barracks)resources.get(whichBarracks)).cityName));
                player1.construction -= Archery.constructionNeed;
                player1.unemployedPopulation--;
            }
        }
        //archy by city of player2
        if((btn == 7)&&(clickBuffer == 13)&&(turnCounter%2==1)&&((player2.construction-Archery.constructionNeed)>0)&&(player2.unemployedPopulation>0))
        {
            int archeryX;
            int archeryY;
            int whichBarracks = -1;
            for (int i =0;i<resources.size();i++)
            {
                if((resources.get(i).x == locationBufferX)&&(resources.get(i).y == locationBufferY)&&(((Barracks)resources.get(i)).owner == 2))
                    whichBarracks = i;
            }
            if(whichBarracks != -1)
            {
                archeryX = locationBufferX;
                archeryY = locationBufferY-1;
                while (whichIsOnPlan[archeryX][archeryY] != 1)
                {
                    archeryY-=1;
                }
                loading.add(new Archery("src\\com\\arc\\AP\\Archery\\red.png",archeryX,archeryY,2,((Barracks)resources.get(whichBarracks)).cityName));
                player2.construction -= Archery.constructionNeed;
                player2.unemployedPopulation--;
            }
        }
        //swordMan by city of player1
        if((btn == 8)&&(clickBuffer == 13)&&(turnCounter%2==0)&&((player1.construction-SwordMan.constructionNeed)>0)&&(player1.unemployedPopulation>0))
        {
            int swordManX;
            int swordManY;
            int whichBarracks = -1;
            for (int i =0;i<resources.size();i++)
            {
                if((resources.get(i).x == locationBufferX)&&(resources.get(i).y == locationBufferY)&&(((Barracks)resources.get(i)).owner == 1))
                    whichBarracks= i;
            }
            if (whichBarracks != -1)
            {
                swordManX = locationBufferX;
                swordManY = locationBufferY-1;
                while (whichIsOnPlan[swordManX][swordManY] != 1)
                {
                    swordManY-=1;
                }
                loading.add(new SwordMan("src\\com\\arc\\AP\\Swordsman\\blue.png",swordManX,swordManY,1,((Barracks)resources.get(whichBarracks)).cityName));
                player1.construction -= SwordMan.constructionNeed;
                player1.unemployedPopulation--;
            }
        }
        //swordMan by city of player2
        if((btn == 8)&&(clickBuffer == 13)&&(turnCounter%2==1)&&((player2.construction-SwordMan.constructionNeed)>0)&&(player2.unemployedPopulation>0))
        {
            int swordManX;
            int swordManY;
            int whichBarracks = -1;
            for (int i =0;i<resources.size();i++)
            {
                if((resources.get(i).x == locationBufferX)&&(resources.get(i).y == locationBufferY)&&(((Barracks)resources.get(i)).owner == 2))
                    whichBarracks= i;
            }
            if (whichBarracks != -1)
            {
                swordManX = locationBufferX;
                swordManY = locationBufferY-1;
                while (whichIsOnPlan[swordManX][swordManY] != 1)
                {
                    swordManY-=1;
                }
                loading.add(new SwordMan("src\\com\\arc\\AP\\Swordsman\\red.png",swordManX,swordManY,2,((Barracks)resources.get(whichBarracks)).cityName));
                player2.construction -= SwordMan.constructionNeed;
                player2.unemployedPopulation--;
            }
        }


        //farm of player1
        if((clickBuffer == Worker.mapNumber)&&(btn == 12)&&(turnCounter%2==0)&&((player1.construction-Farm.constructionNeed)>0)&&(player1.unemployedPopulation>0))
        {
            int whichWorker = -1;
            int farmLocationX;
            int farmLocationY;
            for(int i = 0;i<troop.size();i++)
                if((troop.get(i) instanceof Worker)&&(((Troop)troop.get(i)).x == locationBufferX)&&(((Troop)troop.get(i)).y == locationBufferY)&&(((Troop)troop.get(i))).owner == 1&&((((Worker)troop.get(i))).hasWork == false))
                    whichWorker = i;
            if(whichWorker>-1)
            {
                farmLocationX = locationBufferX;
                farmLocationY = locationBufferY+1;
                if(whichIsOnPlan[farmLocationX][farmLocationY] == 1)
                {
                    resources.add(new Farm("src\\com\\arc\\AP\\Resources\\Farm\\farm.png",farmLocationX,farmLocationY,(turnCounter%2)+1,((Troop)troop.get(whichWorker)).cityName));
                    whichIsOnPlan[farmLocationX][farmLocationY] = Farm.mapNumber;
                    player1.construction-=Farm.constructionNeed;//40
                    player1.unemployedPopulation--;
                }
            }

            map.setResources(resources);

        }
        //farm of player2
        if((clickBuffer == Worker.mapNumber)&&(btn == 12)&&(turnCounter%2==1)&&((player2.construction-(Farm.constructionNeed))>0)&&(player2.unemployedPopulation>0))
        {
            int whichWorker = -1;
            int farmLocationX;
            int farmLocationY;
            for(int i = 0;i<troop.size();i++)
                if((troop.get(i) instanceof Worker)&&(((Troop)troop.get(i)).x == locationBufferX)&&(((Troop)troop.get(i)).y == locationBufferY)&&(((Troop)troop.get(i))).owner == 2&&((((Worker)troop.get(i))).hasWork == false))
                    whichWorker = i;
            if(whichWorker>-1)
            {
                farmLocationX = locationBufferX;
                farmLocationY = locationBufferY+1;
                if(whichIsOnPlan[farmLocationX][farmLocationY] == 1)
                {
                    resources.add(new Farm("src\\com\\arc\\AP\\Resources\\Farm\\farm.png",farmLocationX,farmLocationY,(turnCounter%2)+1,((Troop)troop.get(whichWorker)).cityName));
                    whichIsOnPlan[farmLocationX][farmLocationY] = Farm.mapNumber;
                    player2.construction-=Farm.constructionNeed;//40
                    player2.unemployedPopulation--;
                }
            }
            map.setResources(resources);
        }

        //make barracks by player1
        if((clickBuffer == Worker.mapNumber)&&(btn == 2)&&(turnCounter%2==0)&&((player1.construction-Barracks.constructionNeed)>0)&&(player1.unemployedPopulation>0))
        {
            int whichWorker = -1;
            int barracksLocationX;
            int barracksLocationY;
            for(int i = 0;i<troop.size();i++)
                if((troop.get(i) instanceof Worker)&&(((Troop)troop.get(i)).x == locationBufferX)&&(((Troop)troop.get(i)).y == locationBufferY)&&(((Troop)troop.get(i))).owner == 1 &&((((Worker)troop.get(i))).hasWork == false))
                    whichWorker = i;
            if(whichWorker>-1)
            {
                barracksLocationX = locationBufferX;
                barracksLocationY = locationBufferY+1;
                if(whichIsOnPlan[barracksLocationX][barracksLocationY] == 1)
                {
                    loading.add(new Barracks("src\\com\\arc\\AP\\Buildings\\Barracks\\logo.png", barracksLocationX, barracksLocationY, (turnCounter % 2) + 1, ((Troop) troop.get(whichWorker)).cityName));
                    player1.construction-= Barracks.constructionNeed;//200
                    player1.unemployedPopulation--;
                    ((Worker)troop.get(whichWorker)).timeOfWork = 2;
                    ((Worker)troop.get(whichWorker)).hasWork = true;
                }
            }
        }

        //make barracks by player2
        if((clickBuffer == Worker.mapNumber)&&(btn == 2)&&(turnCounter%2==1)&&((player2.construction-Barracks.constructionNeed)>0)&&(player2.unemployedPopulation>0))
        {
            int whichWorker = -1;
            int barracksLocationX;
            int barracksLocationY;
            for(int i = 0;i<troop.size();i++)
                if((troop.get(i) instanceof Worker)&&(((Troop)troop.get(i)).x == locationBufferX)&&(((Troop)troop.get(i)).y == locationBufferY)&&(((Troop)troop.get(i))).owner == (turnCounter % 2) + 1 &&((((Worker)troop.get(i))).hasWork == false))
                    whichWorker = i;
            if(whichWorker>-1)
            {
                barracksLocationX = locationBufferX;
                barracksLocationY = locationBufferY+1;
                if(whichIsOnPlan[barracksLocationX][barracksLocationY] == 1)
                {
                    loading.add(new Barracks("src\\com\\arc\\AP\\Buildings\\Barracks\\logo.png", barracksLocationX, barracksLocationY, (turnCounter % 2) + 1, ((Troop) troop.get(whichWorker)).cityName));
                    player2.construction-= Barracks.constructionNeed;//200
                    player2.unemployedPopulation--;
                    ((Worker)troop.get(whichWorker)).timeOfWork = 2;
                    ((Worker)troop.get(whichWorker)).hasWork = true;
                }
            }
        }

        //make mine by player 1
        if((clickBuffer == Worker.mapNumber)&&(btn == 1)&&(turnCounter%2==0)&&((player1.construction-Mine.constructionNeed)>0)&&(player1.unemployedPopulation>0))
        {
            int whichWorker = -1;
            int mineLocationX;
            int mineLocationY;
            for(int i = 0;i<troop.size();i++)
                if((troop.get(i) instanceof Worker)&&(((Troop)troop.get(i)).x == locationBufferX)&&(((Troop)troop.get(i)).y == locationBufferY)&&((((Troop)troop.get(i))).owner == 1)&&((((Worker)troop.get(i))).hasWork == false))
                    whichWorker = i;
            if(whichWorker>-1)
            {
                mineLocationX = locationBufferX;
                mineLocationY = locationBufferY+1;
                if(whichIsOnPlan[mineLocationX][mineLocationY] == 1)
                {
                    loading.add(new Mine("src\\com\\arc\\AP\\tech\\Mining.png", mineLocationX, mineLocationY, (turnCounter % 2) + 1, ((Troop) troop.get(whichWorker)).cityName));
                    //whichIsOnPlan[mineLocationX][mineLocationY] = Farm.mapNumber;
                    player1.construction-= Mine.constructionNeed;//40
                    player1.unemployedPopulation--;
                    ((Worker)troop.get(whichWorker)).timeOfWork = 5;
                    ((Worker)troop.get(whichWorker)).hasWork = true;
                }
            }
        }

        //make mine by player2
        if((clickBuffer == Worker.mapNumber)&&(btn == 1)&&(turnCounter%2==1)&&((player2.construction-Mine.constructionNeed)>0)&&(player2.unemployedPopulation>0))
        {
            int whichWorker = -1;
            int mineLocationX;
            int mineLocationY;
            for(int i = 0;i<troop.size();i++)
                if((troop.get(i) instanceof Worker)&&(((Troop)troop.get(i)).x == locationBufferX)&&(((Troop)troop.get(i)).y == locationBufferY)&&((((Troop)troop.get(i))).owner == 2)&&((((Worker)troop.get(i))).hasWork == false))
                    whichWorker = i;
            if(whichWorker>-1)
            {
                mineLocationX = locationBufferX;
                mineLocationY = locationBufferY+1;
                if(whichIsOnPlan[mineLocationX][mineLocationY] == 1)
                {
                    loading.add(new Mine("src\\com\\arc\\AP\\tech\\Mining.png", mineLocationX, mineLocationY, (turnCounter % 2) + 1, ((Troop) troop.get(whichWorker)).cityName));
                    //whichIsOnPlan[mineLocationX][mineLocationY] = Mine.mapNumber;
                    player2.construction-= Mine.constructionNeed;//40
                    player2.unemployedPopulation--;
                    ((Worker)troop.get(whichWorker)).timeOfWork = 5;
                    ((Worker)troop.get(whichWorker)).hasWork = true;
                }
            }
        }
        //make granary by player 1
        if((clickBuffer == Worker.mapNumber)&&(btn == 3)&&(turnCounter%2==0)&&((player1.construction-Granary.constructionNeed)>0)&&(player1.unemployedPopulation>0))
        {
            int whichWorker = -1;
            int granaryLocationX;
            int granaryLocationY;
            for(int i = 0;i<troop.size();i++)
                if((troop.get(i) instanceof Worker)&&(((Troop)troop.get(i)).x == locationBufferX)&&(((Troop)troop.get(i)).y == locationBufferY)&&(((Troop)troop.get(i))).owner == 1)
                    whichWorker = i;
            if(whichWorker>-1)
            {
                granaryLocationX = locationBufferX;
                granaryLocationY = locationBufferY+1;
                if(whichIsOnPlan[granaryLocationX][granaryLocationY] == 1)
                {
                    loading.add(new Granary("src\\com\\arc\\AP\\Buildings\\Granary\\logo.png",granaryLocationX,granaryLocationY,(turnCounter%2)+1,((Troop)troop.get(whichWorker)).cityName));
                    player1.construction-= Granary.constructionNeed;//40
                    player1.unemployedPopulation--;
                    player1.hasGranary=true;
                    ((Worker)troop.get(whichWorker)).timeOfWork = 2;
                    ((Worker)troop.get(whichWorker)).hasWork = true;
                }
            }
        }
        //make granary by player 2
        if((clickBuffer == Worker.mapNumber)&&(btn == 3)&&(turnCounter%2==1)&&((player2.construction-Granary.constructionNeed)>0)&&(player2.unemployedPopulation>0))
        {
            int whichWorker = -1;
            int granaryLocationX;
            int granaryLocationY;
            for(int i = 0;i<troop.size();i++)
                if((troop.get(i) instanceof Worker)&&(((Troop)troop.get(i)).x == locationBufferX)
                        &&(((Troop)troop.get(i)).y == locationBufferY)&&(((Troop)troop.get(i))).owner == 2)
                    whichWorker = i;
            if(whichWorker>-1)
            {
                granaryLocationX = locationBufferX;
                granaryLocationY = locationBufferY+1;
                if(whichIsOnPlan[granaryLocationX][granaryLocationY] == 1)
                {
                    loading.add(new Granary("src\\com\\arc\\AP\\Buildings\\Granary\\logo.png",granaryLocationX,granaryLocationY,(turnCounter%2)+1,((Troop)troop.get(whichWorker)).cityName));
                    player2.construction-= Granary.constructionNeed;//40
                    player2.unemployedPopulation--;
                    player2.hasGranary = true;
                    ((Worker)troop.get(whichWorker)).timeOfWork = 2;
                    ((Worker)troop.get(whichWorker)).hasWork = true;
                }
            }
        }

        //make library in city by player 1
        if((btn == 4)&&(clickBuffer>29)&&(player1.construction-Library.constructionNeed>0)&&(player1.unemployedPopulation>0))
        {
            int whichCity = -1;
            for(int i = 0;i<city.size();i++)
                if(((City)city.get(i)).name == clickBuffer-30)
                    whichCity = i;
            if(((City)city.get(whichCity)).owner == (turnCounter%2)+1)
            {
                loadingInCity.add(new Library(((City)city.get(whichCity)).owner,((City)city.get(whichCity)).name));
                player1.construction -= StableB.constructionNeed;
                player1.unemployedPopulation--;
            }
        }
        //make library in city by player 2
        if((btn == 4)&&(clickBuffer>29)&&(turnCounter%2==1)&&(player2.construction-Library.constructionNeed>0)&&(player2.unemployedPopulation>0))
        {
            int whichCity = -1;
            for(int i = 0;i<city.size();i++)
                if(((City)city.get(i)).name == clickBuffer-30)
                    whichCity = i;
            if(((City)city.get(whichCity)).owner == (turnCounter%2)+1)
            {
                loadingInCity.add(new Library(((City)city.get(whichCity)).owner,((City)city.get(whichCity)).name));
                player2.construction -= StableB.constructionNeed;
                player2.unemployedPopulation--;
            }

        }
        //make stable in city by player 1
        if((btn == 5)&&(clickBuffer>29)&&(turnCounter%2 == 0)&&(player1.construction-StableB.constructionNeed>0)&&(player1.unemployedPopulation>0))
        {
            int whichCity = -1;
            for(int i = 0;i<city.size();i++)
                if(((City)city.get(i)).name == clickBuffer-30)
                    whichCity = i;
            if(((City)city.get(whichCity)).owner == (turnCounter%2)+1)
            {
                loadingInCity.add(new StableB(((City)city.get(whichCity)).owner,((City)city.get(whichCity)).name));
                player1.construction-=StableB.constructionNeed;
                player1.unemployedPopulation--;
            }
        }
        //make stable in city by player 2
        if((btn == 5)&&(clickBuffer>29)&&(turnCounter%2 == 1)&&(player2.construction-StableB.constructionNeed>0)&&(player2.unemployedPopulation>0))
        {
            int whichCity = -1;
            for(int i = 0;i<city.size();i++)
                if(((City)city.get(i)).name == clickBuffer-30)
                    whichCity = i;
            if(((City)city.get(whichCity)).owner == (turnCounter%2)+1)
            {
                loadingInCity.add(new StableB(((City)city.get(whichCity)).owner,((City)city.get(whichCity)).name));
                player2.construction -= StableB.constructionNeed;
                player2.unemployedPopulation--;
            }
        }
        graphicsEngine.UpdateScreen();
    }

    @Override
    public void clickOnMap(int x, int y)
    {


        System.out.println("Clicked On ["+x+ "," +y+"]    ");
        System.out.println(whichIsOnPlan[x][y]);
        clickBuffer = whichIsOnPlan[x][y];
        locationBufferX = x;
        locationBufferY = y;
        if((clickBuffer>3)&&(clickBuffer<10))//it is a troop
        {
            troopKid = clickBuffer;
            troopLocationX = locationBufferX;
            troopLocationY = locationBufferY;
            troopIsSelected = true;
        }
        if((clickBuffer>5)&&(clickBuffer<10)&& a)
        {
            //System.out.println("c");
            fighterKind = clickBuffer;
            fighterX = locationBufferX;
            fighterY = locationBufferY;
            a = false;
        }

        //fight whit other warriors
        if((clickBuffer<10)&&(clickBuffer>5))
        {
            for(int i =0;i<troop.size();i++)
            {
                if (troop.get(i) instanceof Warrior && (((Warrior)troop.get(i)).orderedPerSet == false))
                {
                    if((locationBufferX == troop.get(i).x)&&(locationBufferY == troop.get(i).y)&&(((Warrior)troop.get(i)).owner==((turnCounter+1)%2)+1))
                    {
                        System.out.println("a");
                        fighted =(Warrior)troop.get(i);
                    }
                }
                if (troop.get(i) instanceof Warrior  && (((Warrior)troop.get(i)).orderedPerSet == false))
                {
                    if((fighterX == troop.get(i).x)&&(fighterY == troop.get(i).y)&&(((Warrior)troop.get(i)).owner==turnCounter%2+1))
                    {
                        System.out.println("b");
                        fighter =(Warrior)troop.get(i);
                        a = true;
                    }
                }
            }

            if((fighted!=null&&fighter!=null)&&(((fighter.mapNumber == 6)&&((fighter.x-fighted.x<3)&&(fighter.x-fighted.x>-3)&&(fighter.y-fighted.y<3)&&(fighter.y-fighted.y>-3)))
                    ||((fighter.mapNumber!=6)&&(fighter.x-fighted.x<2)&&(fighter.x-fighted.x>-2)&&(fighter.y-fighted.y<2)&&(fighter.y-fighted.y>-2))))
            {
                System.out.println("c");
                fighter.fight(fighted);
                fighter = fighted = null;
            }

            for(int i =0;i<troop.size();i++)
            {
                if (troop.get(i) instanceof Warrior)
                {
                    if(((Warrior)troop.get(i)).isLife == false)
                    {
                        whichIsOnPlan[troop.get(i).x][troop.get(i).y] = 1;
                        troop.remove(i);
                        i--;
                    }
                }
            }

            map.setTroop(troop);
            graphicsEngine.UpdateScreen();

        }
        if(clickBuffer>29)//fight whit city
        {
            //System.out.println("here...");
            int whichCity = -1;
           // int whichWarrior = -1;
            for (int i = 0;i<city.size();i++)
            {
               // System.out.println("((City)city.get(i)).name"+((City)city.get(i)).name);
                //System.out.println("clickBuffer"+clickBuffer);
                if(((City)city.get(i)).name+30 == clickBuffer)
                {
                   // System.out.println("a");
                    whichCity = i;
                }
            }
            for(int i = 0;i<troop.size();i++)
            {
                if (troop.get(i) instanceof Warrior  && (((Warrior)troop.get(i)).orderedPerSet == false))
                {
                   // System.out.println("b");
                    if((fighterX == troop.get(i).x)&&(fighterY == troop.get(i).y)&&(((Warrior)troop.get(i)).owner==turnCounter%2+1))
                    {
                        //System.out.println("c");
                        fighter =(Warrior)troop.get(i);
                        a = true;
                    }
                }
            }
            if((fighter!=null)&&(((fighter.mapNumber == 6)&&((fighter.x-city.get(whichCity).x<3)&&(fighter.x-city.get(whichCity).x>-3)&&(fighter.y-city.get(whichCity).y<3)&&(fighter.y-city.get(whichCity).y>-3)))
                    ||((fighter.mapNumber!=6)&&(fighter.x-city.get(whichCity).x<2)&&(fighter.x-city.get(whichCity).x>-2)&&(fighter.y-city.get(whichCity).y<2)&&(fighter.y-city.get(whichCity).y>-2))))
            {
                fighter.figh((City)city.get(whichCity));
                //System.out.println("d");
                //System.out.println(((City)city.get(whichCity)).isCityChangeOwner());
                //System.out.println(fighter.isLife);

            }
            for(int i = 0;i<city.size();i++)
            {
                if(((City)city.get(i)).isCityChangeOwner() == true)
                {
                    for(int j = 0;j<troop.size();j++)
                    {
                        if(((Troop)troop.get(j)).cityName == ((City)city.get(i)).name)
                        {
                            ((Troop)troop.get(j)).owner = ((((Troop)troop.get(j)).owner)%2)+1;
                        }
                    }
                    for (int j = 0;j<resources.size();j++)
                    {
                        if(((Building)resources.get(j)).cityName == ((City)city.get(i)).name)
                        {
                            ((Building)resources.get(j)).owner = ((((Building)resources.get(j)).owner)%2)+1;
                        }
                    }
                    for (int j = 0;j<buildingInCities.size();j++)
                    {
                        if(((BuildingInCity)buildingInCities.get(j)).cityName == ((City)city.get(i)).name)
                        {
                            ((BuildingInCity)buildingInCities.get(j)).owner = ((((BuildingInCity)buildingInCities.get(j)).owner)%2)+1;
                        }
                    }
                    ((City)city.get(i)).owner = (((City)city.get(i)).owner%2)+1;
                }
            }
            for(int i =0;i<troop.size();i++)
            {
                if (troop.get(i) instanceof Warrior)
                {
                    if(((Warrior)troop.get(i)).isLife == false)
                    {
                        whichIsOnPlan[troop.get(i).x][troop.get(i).y] = 1;
                        troop.remove(i);
                        i--;
                    }
                }
            }
            map.setTroop(troop);
            graphicsEngine.UpdateScreen();
        }



        if((clickBuffer == 1)&&(troopIsSelected))
        {
           // Troop selectedTroop = null;// baraye vaghti ke mikhaim bad az turn harekat kone...
            //if far is less than ....
            fighted = null;
            fighter = null;
            boolean hasWay = true;

            if(locationBufferY == troopLocationY)//dar ye khatan
            {
                hasWay = true;
                if(locationBufferX<troopLocationX)
                {
                    if((whichIsOnPlan[troopLocationX-1][locationBufferY]==0)||(whichIsOnPlan[troopLocationX-1][locationBufferY]==2)||(whichIsOnPlan[troopLocationX-1][locationBufferY]==3))
                    {
                        hasWay = false;
                    }
                }
                else
                {
                    if((whichIsOnPlan[troopLocationX+1][locationBufferY]==0)||(whichIsOnPlan[troopLocationX+1][locationBufferY]==2)||(whichIsOnPlan[troopLocationX+1][locationBufferY]==3))
                    {
                        hasWay = false;
                    }
                }
            }
            if(locationBufferX == troopLocationX)
            {
                if(locationBufferY<troopLocationY)
                {
                    if((whichIsOnPlan[locationBufferX][locationBufferY+1]==0)||(whichIsOnPlan[locationBufferX][locationBufferY+1]==2)||(whichIsOnPlan[locationBufferX][locationBufferY+1]==3))
                    {
                        hasWay = false;
                    }
                }
                else
                {
                    if(whichIsOnPlan[locationBufferX][locationBufferY-1]==0)
                    {
                        hasWay = false;
                    }
                }
            }
            for(int i = 0;i<troop.size();i++)
            {
                if((troop.get(i).x == troopLocationX)&&(troop.get(i).y == troopLocationY)&&(turnCounter%2+1 == ((Troop)troop.get(i)).owner)&&(!(troop.get(i) instanceof Worker)||(((Worker)troop.get(i)).hasWork == false))
                        &&(((Troop)troop.get(i)).orderedPerSet == false))
                {
                    if(((Troop)troop.get(i)).canMove(locationBufferX,locationBufferY)&&hasWay)
                    {
                        ((Troop)troop.get(i)).move(locationBufferX,locationBufferY);
                        whichIsOnPlan[troopLocationX][troopLocationY] = 1;
                        troopLocationX = 0;
                        troopLocationY = 0;
                        whichIsOnPlan[locationBufferX][locationBufferY] = troopKid;
                        if(((Troop)troop.get(i)).mapNumber !=9)
                            ((Troop)troop.get(i)).orderedPerSet = true;
                        if (((Troop)troop.get(i)).mapNumber == 9)
                        {
                            hoursemanMoveCounter++;
                            if ( hoursemanMoveCounter == 3)
                                ((Troop)troop.get(i)).orderedPerSet = true;

                        }
                        break;
                    }
                }
            }

           // selectedTroop.move(locationBufferX,locationBufferY);
            troopIsSelected = false;

        }
        graphicsEngine.UpdateScreen();
    }


    @Override
    public void clickOnResearch(String name)
    {
        System.out.println("Research On : "+name);
        if(turnCounter%2 == 0)//player1
        {
            for (int i = 0;i<player1.canResearchTec.size();i++)
            {
                if(name.equals(player1.canResearchTec.get(i).name))
                {
                    if (name.equals("warrior code"))
                    {
                        Archery.archeryDefence[1]+=10;
                        HorseMan.horsemanDefence[1]+=10;
                        Spearman.spearmanDefence[1]+=10;
                        SwordMan.swordManDefence[1]+=10;
                    }
                    if (name.equals("archery"))
                        Archery.archeryDefence[1]+=5;
                    if(name.equals("horseman"))
                        HorseMan.horsemanDefence[1]+=5;
                    if (name.equals("spearman"))
                        Spearman.spearmanDefence[1]+=5;
                    if (name.equals("iron working")||name.equals("bronze working"))
                        SwordMan.swordManDefence[1]+=2;
                    player1.researchedTec.add(player1.canResearchTec.get(i));
                }
            }
        }
        if(turnCounter%2 == 1)//player2
        {
            for (int i = 0;i<player2.canResearchTec.size();i++)
            {
                if(name.equals(player2.canResearchTec.get(i).name))
                {
                    if (name.equals("warrior code"))
                    {
                        Archery.archeryDefence[2]+=10;
                        HorseMan.horsemanDefence[2]+=10;
                        Spearman.spearmanDefence[2]+=10;
                        SwordMan.swordManDefence[2]+=10;
                    }
                    if (name.equals("archery"))
                        Archery.archeryDefence[2]+=5;
                    if (name.equals("horseman"))
                        HorseMan.horsemanDefence[2]+=5;
                    if (name.equals("spearman"))
                        Spearman.spearmanDefence[2]+=5;
                    if (name.equals("iron working")||name.equals("bronze working"))
                        SwordMan.swordManDefence[2]+=2;
                    player2.researchedTec.add(player2.canResearchTec.get(i));
                }
            }
        }
    }

    @Override
    public void turnChanged()
    {
       // graphicsEngine.UpdateScreen();
        turnCounter++;
        clickBuffer = -1;
        locationBufferY = -1;
        locationBufferX = -1;
        System.out.print("Turn Changed : ");
        System.out.println(GameEngine.getTurnCounter());

        if(turnCounter == 1)
        {
            player2 = null;
            try {
                player2 = new Player(2,"red",whichIsOnPlan);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            for(int i = 0;i<3;i++)
            {
                troop.add(player2.firstTroop[i]);
                whichIsOnPlan[player2.firstTroop[i].x][player2.firstTroop[i].y] = player2.firstTroop[i].mapNumber;
            }
        }
        if(turnCounter%2==0)//player1 technology
        {
            Technology.whichTecCanResearch(player1.researchedTec,player1.canResearchTec);
            canResearchTec = new String[player1.canResearchTec.size()];
            for (int i = 0;i<player1.canResearchTec.size();i++)
            {
                canResearchTec[i] = player1.canResearchTec.get(i).name;
            }
        }
        if(turnCounter%2==1)//player2 technology
        {
            Technology.whichTecCanResearch(player2.researchedTec,player2.canResearchTec);
            canResearchTec = new String[player2.canResearchTec.size()];
            for (int i = 0;i<player2.canResearchTec.size();i++)
            {
                canResearchTec[i] = player2.canResearchTec.get(i).name;
            }
        }
        graphicsEngine.setResearchs(canResearchTec) ;

        //construction troop & building

        for(int i = 0;i<city.size();i++)
        {
            ((City)city.get(i)).makeSettler = false;
            ((City)city.get(i)).makeWorker = false;
        }

        for(int i = 0;i<troop.size();i++)
        {
            if((troop.get(i) instanceof Worker)&&((Worker) troop.get(i)).hasWork)
            {
                ((Worker)troop.get(i)).workCounter++;
                System.out.println("work counter:"+((Worker)troop.get(i)).workCounter);
                if(((Worker)troop.get(i)).workCounter == ((Worker)troop.get(i)).timeOfWork)
                {   // System.out.println("eq");
                    ((Worker)troop.get(i)).hasWork = false;
                    ((Worker)troop.get(i)).workCounter = 0;
                }
            }
        }
        for(int i = 0;i<troop.size();i++)
            ((Troop)troop.get(i)).orderedPerSet = false;

        fighter = fighted = null;

        for (int i =0;i<loading.size();i++)
        {
            if(loading.get(i) instanceof Troop)
            {
                ((Troop)loading.get(i)).howLongTrained++;
            }
            if(loading.get(i)instanceof Building)
            {
                ((Building)loading.get(i)).howLongBuild++;
            }
        }
        for (int i =0;i<loading.size();i++)
        {
            if((loading.get(i) instanceof Troop))
            {
                if(((Troop)loading.get(i)).howLongTrained == ((Troop)loading.get(i)).timeOfTrain)
                {
                    troop.add(loading.get(i));
                    whichIsOnPlan[((Troop)loading.get(i)).x][((Troop)loading.get(i)).y] = ((Troop)loading.get(i)).mapNumber;
                    loading.remove(i);
                    i--;
                }
            }
        }
        for (int i =0;i<loading.size();i++)
        {
            if((loading.get(i) instanceof Building))
            {
                if(((Building)loading.get(i)).howLongBuild == ((Building)loading.get(i)).timeOfBuild)
                {
                    resources.add(loading.get(i));
                    whichIsOnPlan[((Building)loading.get(i)).x][((Building)loading.get(i)).y] = ((Building)loading.get(i)).mapNumber;
                    loading.remove(i);
                    i--;
                }
            }
        }
        for (int i = 0;i<loadingInCity.size();i++)
        {
            loadingInCity.get(i).howLongBuild++;
        }
        for(int i = 0;i<loadingInCity.size();i++)
            if(loadingInCity.get(i).howLongBuild == loadingInCity.get(i).timeOfBuild)
            {
                buildingInCities.add(loadingInCity.get(i));
                System.out.println("add building in city!" + buildingInCities.get(buildingInCities.size() - 1).mapNumber);
                loadingInCity.remove(i);
                i--;
            }

        map.setTroop(troop);
        map.setResources(resources);
        graphicsEngine.UpdateScreen();
        player1.calculateConstruction(resources);
        player1.calculateFoodAmount(resources);
        player1.calculateFoodNeed(troop,resources,buildingInCities);
        player1.calculateTrainSpeed(buildingInCities);
        player1.manageFood(buildingInCities,resources);
        if(player1.plentyOfFood == 3)//10% az ghazaye ezafe ra be jamiat ezafe mikone!!!
        {
            player1.increasePopulation(buildingInCities,resources);
            player1.plentyOfFood = 0;
        }
        if(player1.lackOfFood == 3)
        {
            player1.decreasePopulation(buildingInCities,resources,troop);
            player1.lackOfFood = 0;
            map.setTroop(troop);
            graphicsEngine.UpdateScreen();
        }
        player2.calculateConstruction(resources);
        player2.calculateFoodAmount(resources);
        player2.calculateFoodNeed(troop,resources,buildingInCities);
        player2.manageFood(buildingInCities,resources);

        if(player2.plentyOfFood == 3)//10% az ghazaye ezafe ra be jamiat ezafe mikone!!!
        {
            player2.increasePopulation(buildingInCities,resources);
            player2.plentyOfFood = 0;
        }
        if(player2.lackOfFood == 3)
        {
            player2.decreasePopulation(buildingInCities,resources,troop);
            map.setTroop(troop);
            graphicsEngine.UpdateScreen();
            player2.lackOfFood = 0;
        }



        System.out.println("player1 food amount"+player1.foodAmount);
        System.out.println("player1 food need"+player1.allFoodNeed);
        System.out.println("player1 construction"+player1.construction);
        System.out.println("player1 unemployed"+player1.unemployedPopulation);
        System.out.println("player1 lak"+player1.lackOfFood);
        System.out.println("player2 food amount"+player2.foodAmount);
        System.out.println("player2 food need"+player2.allFoodNeed);
        System.out.println("player2 construction"+player2.construction);
        System.out.println("player2 unemployed"+player2.unemployedPopulation);
        System.out.println("player2 lak"+player2.lackOfFood);

    }
}