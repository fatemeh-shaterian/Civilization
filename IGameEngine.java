package com.arc;
import java.io.IOException;

/**
 * Created by pouya on 3/15/14.
 */
public interface IGameEngine
{
    public void clickOnButton(int btn)throws IOException;
    public void clickOnMap(int x,int y);
    public void clickOnResearch(String name);
    public void turnChanged();
}
