package com.arc;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/26/14
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Technology
{
    //can use
    //meghdar elme morde niaz   ???
    //zamane lazem baraye sakht ???
    //sood
    protected int number;
    protected String name;

    public static void whichTecCanResearch(ArrayList<Technology> researchedTec,ArrayList<Technology> canResearchTec)
    {
        int stableCounter = 0;
        int ironBronzeCounter = 0;
        int archeryCounter = 0;
        int libraryCounter = 0;
        int horseCounter = 0;
        int spearCounter = 0;
        for(int i = 0;i<researchedTec.size();i++)
        {
            for (int j = 0;j<canResearchTec.size();j++)
            {
                if (researchedTec.get(i).number == canResearchTec.get(j).number)
                {
                    canResearchTec.remove(j);
                    j--;
                }
            }
            if((researchedTec.get(i).number == 1)||(researchedTec.get(i).number == 3))
                stableCounter++;
            if((researchedTec.get(i).number == 6)||(researchedTec.get(i).number == 7))
                horseCounter++;
            if(researchedTec.get(i).number == 2)
                ironBronzeCounter++;
            if(researchedTec.get(i).number == 4)
                archeryCounter++;
            if(researchedTec.get(i).number == 5)
                libraryCounter++;
            if((researchedTec.get(i).number == 8)||(researchedTec.get(i).number == 3)||(researchedTec.get(i).number == 4))
                spearCounter++;
            if(stableCounter == 2)
                canResearchTec.add( new Stable());
            if (ironBronzeCounter == 1)
            {
                canResearchTec.add(new BronzeWorking());
                canResearchTec.add(new IronWorking());
            }
            if(archeryCounter == 1)
                canResearchTec.add(new ArcheryTec());
            if (libraryCounter == 1)
                canResearchTec.add(new LibraryTec());
            if (horseCounter == 2)
                canResearchTec.add(new HorseManTec());
            if (spearCounter == 3)
                canResearchTec.add(new SpearManTec());
        }


    }




}
