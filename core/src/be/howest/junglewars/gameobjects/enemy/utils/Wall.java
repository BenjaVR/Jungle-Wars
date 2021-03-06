package be.howest.junglewars.gameobjects.enemy.utils;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;



public class Wall {
    private GameScreen g;
    private Vector2 start;
    private ArrayList<Brick> wall;
    private int length;
    private Vector2 end;
    private int curveX;
    private int curveY;
    public Wall(GameScreen g, Vector2 start,int length,int curveX,int curveY){
        this.g = g;
        this.start = start;
        this.length = length;
        this.end = end;
        this.curveX = curveX;
        this.curveY = curveY;
        wall = new ArrayList<>();


    }


    public void DrawWall(){
        for(int i = 0; i < length; i = i+1){
            Brick b = new Brick (g,25,25,start.x+curveX*i,start.y+curveY*i);
            wall.add(b);


        }
    }


    public void DrawWallHorz(){
        for(int i = 0; i < length; i = i + 25){
            Brick b = new Brick (g,25,25,start.x+i,start.y);
            wall.add(b);


        }
    }


    public void DrawWallVert(){
        for(int i = 0; i < length; i = i + 25){
            Brick b = new Brick (g,25,25,start.x,start.y+i);
            wall.add(b);


        }
    }


    public void DrawWallDia(){
        for(int i = 0; i < length; i = i + 5){
            Brick b = new Brick (g,10,5,start.x+i,start.y+i);
            wall.add(b);


        }
    }
    public ArrayList<Brick> returnWall(){
        return wall;
    }
}
