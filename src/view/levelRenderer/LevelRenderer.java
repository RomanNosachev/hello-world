package view.levelRenderer;

import java.util.LinkedList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import model.dynamicGameObject.DynamicGameObject;
import model.level.Level;
import view.dynamicRenderer.DynamicRenderer;
import view.fieldRenderer.FieldRenderer;
import view.shellRenderer.ShellRenderer;
import view.tankRenderer.TankRenderer;

public class LevelRenderer 
{    
    private Level level;
    
    private float infoStringHeight = 0;
    private float scale = 1;
    
    private FieldRenderer   fieldRenderer;
    private TankRenderer    tankRenderer;
    
    private LinkedList<DynamicRenderer> objectRenderers;
    private LinkedList<DynamicRenderer> enemyRenderers;
    
    public LevelRenderer()
    {
        level = new Level();
        fieldRenderer = new FieldRenderer();
        tankRenderer = new TankRenderer();
        tankRenderer.setShellRenderer(new ShellRenderer());
        objectRenderers = new LinkedList<>();
        enemyRenderers = new LinkedList<>();
    }
    
    public LevelRenderer(Level level)
    {
        this.level = level;
        fieldRenderer = new FieldRenderer();
        tankRenderer = new TankRenderer();
        tankRenderer.setShellRenderer(new ShellRenderer());
        objectRenderers = new LinkedList<>();
        enemyRenderers = new LinkedList<>();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        scale(g);
        g.translate(-level.getCameraX() + Display.getWidth() / 4, 
                -level.getCameraY() + Display.getHeight() - 200);
        fieldRenderer.render(gc, g);
        tankRenderer.render(gc, g);
        
        for (DynamicGameObject object : level.getObjects())
        {
            g.draw(object.getBase());
            
            for (DynamicRenderer renderer : objectRenderers)
            {                
                if (object.getClass() == renderer.getRenderingObjectClass())
                {
                    renderer.setRenderingObject(object);
                    renderer.render(gc, g);
                }
            }
        }
        
        for (DynamicGameObject enemy : level.getEnemies())
        {
            g.draw(enemy.getBase());
            
            for (DynamicRenderer renderer : enemyRenderers)
            {
                if (enemy.getClass() == renderer.getRenderingObjectClass())
                {
                    renderer.setRenderingObject(enemy);
                    renderer.render(gc, g);
                }
            }
        }
        
        drawInfo(g);
    }

    public void scale(Graphics g)
    {
        int dWheal = Mouse.getDWheel();
        
        if (dWheal > 0)
        {
            scale += 0.1;
        }
        else
        {
            if (dWheal < 0)
                scale -= 0.1; 
        }

        g.scale(scale, scale);
    }
    
    public void drawInfo(Graphics g)
    {
        drawShotInfo(g);
        drawHP(g);
    }
    
    public void drawShotInfo(Graphics g)
    {
        g.setColor(new Color(25, 0, 200));
        g.drawString("Speed: " + Float.toString(level.getShotStartSpeed(level.getShellCount() - 1)), 
                level.getCameraX() - 150, level.getCameraY() + infoStringHeight);    
        g.drawString("Angle: " + Float.toString(level.getShotStartAngle(level.getShellCount() - 1) % 180), 
                level.getCameraX(), level.getCameraY() + infoStringHeight);
    }
    
    public void drawHP(Graphics g)
    {
        if (level.isTankDamaged())
            g.setColor(Color.red);
        else
            g.setColor(new Color(25, 0, 200));
        
        String tankHP = String.format("HP: %.0f", level.getTankHitPoints());
        g.drawString(tankHP, level.getCameraX() + 450, level.getCameraY() + infoStringHeight);
    }
    
    public void setInfoStringHeight(float height)
    {
        infoStringHeight = height;
    }
    
    public void setLevel(Level level)
    {
        this.level = level;
    }
   
    public void setFieldRenderer(FieldRenderer fr)
    {
        fieldRenderer = fr;
    }
    
    public void setTankRenderer(TankRenderer tr)
    {
        tankRenderer = tr;
    }
    
    public void setShellRenderer(ShellRenderer sr)
    {
        tankRenderer.setShellRenderer(sr);
    }
    
    public void addObjectRenderer(DynamicRenderer renderer)
    {
        objectRenderers.add(renderer);
    }
    
    public void addEnemyRenederer(DynamicRenderer renderer)
    {
        enemyRenderers.add(renderer);
    }
}
