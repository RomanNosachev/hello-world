package model.tank;

import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;

public class Cannon extends DynamicGameObject{
    private Vector2f rotationPoint;

    public Vector2f getRotationPoint()
    {
        return rotationPoint;
    }
    
    public float getRotationPointX()
    {
        return rotationPoint.x;
    }
    
    public void setRotationPointX(float x)
    {
        rotationPoint.x = x;
    }
    
    public void setRotationPointY(float y)
    {
        rotationPoint.y = y;
    }

    public float getRotationPointY()
    {
        return rotationPoint.y;
    }
    
    public void setRotationPoint(float[] rotationPointCoord)
    {
        if (rotationPointCoord.length == 2)
            rotationPoint.set(rotationPointCoord);
    }
    
    public void setRotationPoint(Vector2f rotationPoint)
    {
        this.rotationPoint = rotationPoint;
    }
}