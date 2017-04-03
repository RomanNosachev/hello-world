package model;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public abstract class DynamicGameObject implements GameObject {
	protected Shape base;
	protected Shape simpleBase;

	protected float boundingRadius;
	protected float rotateAngle = 0;

	public Shape getSimpleBase()
	{
		return simpleBase;
	}
	
	public float getRotateAngle() 
	{
		return rotateAngle;
	}

	public void setRotateAngle(float rotateAngle) 
	{
		this.rotateAngle = rotateAngle;
	}

	public void setPosition(Vector2f pos) throws IllegalArgumentException
	{
		simpleBase.setLocation(simpleBase.getX() - (base.getX() - pos.x),
							   simpleBase.getY() - (base.getY() - pos.y));
		
		base.setLocation(pos.x, pos.y);
	}
	
	public void setPosition(float x, float y)
	{
		simpleBase.setLocation(simpleBase.getX() - (base.getX() - x),
							   simpleBase.getY() - (base.getY() - y));
		base.setLocation(x, y);
	}
	
	public void setPositionX(float x)
	{
		simpleBase.setX(simpleBase.getX() - (base.getX() - x));
		base.setX(x);
	}
	
	public void setPositionY(float y)
	{
		simpleBase.setY(simpleBase.getY() - (base.getY() - y));
		base.setY(y);
	}

	public void rotate(float angle)
	{
		rotateAngle += angle;
		base = new Polygon(base.transform(Transform.createRotateTransform(angle * (float)Math.PI / 180F, simpleBase.getCenterX(), simpleBase.getCenterY())).getPoints());
		simpleBase = new Polygon(simpleBase.transform(Transform.createRotateTransform(angle * (float)Math.PI / 180F, simpleBase.getCenterX(), simpleBase.getCenterY())).getPoints());

	}
	
	public void rotate(float angle, float x, float y)
	{
		rotateAngle += angle;
		base = new Polygon(base.transform(Transform.createRotateTransform(angle * (float)Math.PI / 180F, x, y)).getPoints());
		simpleBase = new Polygon(simpleBase.transform(Transform.createRotateTransform(angle * (float)Math.PI / 180F, x, y)).getPoints());
	}
	
	@Override
	public boolean collidesWith(GameObject object) throws IllegalArgumentException
	{
		return base.intersects(object.getBase());
	}
	
	@Override
	public boolean collidesWith(Shape s) throws IllegalArgumentException
	{
		return base.intersects(s);
	}
	
	@Override
	public boolean isContains(GameObject object) throws IllegalArgumentException 
	{
		return base.contains(object.getBase());
	}
	
	@Override
	public boolean isContains(Shape s) throws IllegalArgumentException
	{
		return base.contains(s);
	}
	
	public boolean boundingWith(Shape s) throws IllegalArgumentException
	{
		return getBoundingCircle().intersects(s) || getBoundingCircle().contains(s);
	}
	
	public boolean boundingWith(DynamicGameObject object) throws IllegalArgumentException
	{
		return getBoundingCircle().intersects(object.getBoundingCircle()) || getBoundingCircle().contains(object.getBoundingCircle());
	}

	public Shape getBase() 
	{
		return base;
	}
	
	public Shape getBoundingCircle()
	{
		return new Ellipse(base.getCenterX(), base.getCenterY(), boundingRadius, boundingRadius);
	}

	protected void setBase(Shape base) throws IllegalArgumentException
	{
		this.base = base;
		boundingRadius = base.getBoundingCircleRadius();
		simpleBase = new Rectangle(base.getX(), base.getY(), base.getWidth(), base.getHeight());
	}
	
	public float getBoundingCircleRadius()
	{
		return base.getBoundingCircleRadius();
	}

	public float getX()
	{
		return base.getX();
	}
	
	public float getY()
	{
		return base.getY();
	}
	
	public float getSimpleCenterX()
	{
		return simpleBase.getCenterX();
	}
	
	public float getSimpleCenterY()
	{
		return simpleBase.getCenterY();
	}
	
	public float getCenterX()
	{
		return base.getCenterX();
	}
	
	public float getCenterY()
	{
		return base.getCenterY();
	}
	
	public float getMinX()
	{
		return base.getMinX();
	}
	
	public float getMinY()
	{
		return base.getMinY();
	}
	
	public float getMaxX()
	{
		return base.getMaxX();
	}
	
	public float getMaxY()
	{
		return base.getMaxY();
	}

	public float getHeight()
	{
		return base.getHeight();
	}
	
	public float getWidth()
	{
		return base.getWidth();
	}
}
