package com.arc;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fatemeh
 * Date: 3/26/14
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Warrior extends Troop
{
    // what is attack???
    protected int attack;
    protected boolean isLife;
    protected boolean canFight;

    public Warrior(String path, int x, int y,int owner,int cityName) throws IOException
    {
        super(path, x, y,owner,cityName);
        isLife = true;
        //maghadire avalie

    }

    public Warrior(BufferedImage image, int x, int y,int owner,int cityName)
    {
        super(image, x, y,owner,cityName);
    }



    //methods

    public void fight(Warrior opponent)
    {
        System.out.println("In fight:");
        this.health -= opponent.defence;
        opponent.health -= defence;
        if(this.health>opponent.health)
            opponent.isLife = false;
        else
            this.isLife = false;
        System.out.println("fighter health:"+this.health+this.isLife);
        System.out.println("fighted health:"+opponent.health+opponent.isLife);
    }
    public void figh(City city)
    {
        city.setDefence(city.getDefence()-health);
        this.health -= city.getDefence();
        if(this.health<=city.getDefence())
        {
            this.isLife = false;
        }
        else
        {
            city.setCityChangeOwner(true);
        }

    }



    //setter&getter




    public int getAttack() {

        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
