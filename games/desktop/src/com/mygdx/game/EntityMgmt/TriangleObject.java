package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

class TriangleObject extends Entity implements CreateShape{
    Vector2 point1 = new Vector2();
    Vector2 point2= new Vector2();
    Vector2 point3= new Vector2();

    TriangleObject()
    {

    }

    TriangleObject(Color colour, float x, float y, float speed)
    {
        super(x, y,colour, speed);
    }

    @Override
    void draw(ShapeRenderer shape) {
        point1 = new Vector2(-25+getX(),-25+getY());
        point2 = new Vector2(25+getX(),-25+getY());
        point3 = new Vector2(0+getX(),25+getY());
        shape.setColor(getColor());
        shape.triangle(point1.x,point1.y,point2.x,point2.y,point3.x,point3.y);
    }
    void update() {
        System.out.println("In triangle at " + getX() + "," + getY() + " position");
    }

    @Override
    public TriangleObject createShape(){
        return new TriangleObject(Color.RED, 50, 50, 100);
    }

    @Override
    public float getWidth() {
        return 50; 
    }

    @Override
    public float getHeight() {
        return 50; 
    }


}