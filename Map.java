package com.arc;

import java.io.*;
import java.util.ArrayList;

public class Map {

    private ArrayList<MapElement> city;
    private ArrayList<MapElement> troop;
    private ArrayList<MapElement> resources;
    private ArrayList<MapElement> building;
    public int[][] Tiles;
    private int width, height;
    private int tileSize;

    public Map(ArrayList<MapElement> city ,ArrayList<MapElement> troop , ArrayList<MapElement> resources ,int width , int height , int tileSize) throws IOException {
        this.city = city ;
        this.troop = troop ;
        this.resources = resources ;
        Tiles = new int[width][height];
        this.width = width ;
        this.height = height ;
        this.tileSize = tileSize ;   //by default in this project is 80
        //generate random Tiles
        for(int i = 0 ; i < width ; i++)
            for(int j = 0 ; j < height ; j++)
                Tiles[i][j]=(int)(Math.random()*11) ;
    }
    public int getTileSize() {
        return tileSize;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public ArrayList<MapElement> getCity() {
        return city;
    }
    public ArrayList<MapElement> getTroop() {
        return troop;
    }
    public ArrayList<MapElement> getResources() {
        return resources;
    }

    public ArrayList<MapElement> getBuilding() {
        return building;
    }

    public void setBuilding(ArrayList<MapElement> building) {
        this.building = building;
    }

    public void setResources(ArrayList<MapElement> resources) {
        this.resources = resources;
    }

    public void setTroop(ArrayList<MapElement> troop) {

        this.troop = troop;
    }

    public void setCity(ArrayList<MapElement> city) {

        this.city = city;
    }

    /*return 0:
                TileType is Mountain
        return 1:
                TileType is Plain
        return 2:
                TileType is Sea
        return 3:
                TileType is Lake
        */
    public int getTileType(int x , int y){
        return Tiles[x][y];
    }
}
