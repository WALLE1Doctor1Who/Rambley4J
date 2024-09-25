/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import java.awt.*;
import java.awt.geom.RectangularShape;
import javax.swing.*;

/**
 * This is a Painter that paints the background behind Rambley the Raccoon.
 * @author WALLE1Doctor1Who
 */
public class RambleyBackgroundPainter implements Painter<RectangularShape>{
    /**
     * This is the main color for the background.
     */
    public static final Color BACKGROUND_COLOR = new Color(0x2CDFFF);
    /**
     * This is the color for the polka dots in the background.
     */
    public static final Color BACKGROUND_DOT_COLOR = new Color(0x1A73C9);
    /**
     * This is the color for the background gradient. The background gradient 
     * fades from this color to transparency.
     */
    public static final Color BACKGROUND_GRADIENT_COLOR = new Color(0x0068FF);
    /**
     * This is the color which the background gradient fades into. This is a 
     * transparent color.
     */
    protected static final Color BACKGROUND_GRADIENT_COLOR_2 = 
            new Color(BACKGROUND_GRADIENT_COLOR.getRGB()&0x00FFFFFF,true);
    /**
     * This is the default width and height of the background polka dots.
     */
    protected static final double DEFAULT_BACKGROUND_DOT_SIZE = 8.0;
    /**
     * This is the default diagonal spacing between the centers of the 
     * background polka dots. Refer to the documentation for the {@link 
     * #getBackgroundDotSpacing getBackgroundDotSpacing} method for a more in
     * depth description on the background polka dot spacing.
     */
    protected static final double DEFAULT_BACKGROUND_DOT_SPACING = 12.0;
    
    /**
     * This is the width and height of the background dots.
     */
    protected static final int BACKGROUND_DOT_SIZE = 8;
    
    protected static final int BACKGROUND_DOT_HALF_SIZE = 
            Math.floorDiv(BACKGROUND_DOT_SIZE,2);
    
    protected static final int BACKGROUND_DOT_SPACING = 12;
    
    protected static final int BACKGROUND_DOT_POINTS = 4;
    
    protected Paint getBackgroundGradient(double x, double y, double w, double h){
        float x1 = (float)((w / 2.0)+x);
//            return new LinearGradientPaint(x1,y,x1,y+h-1,
//                    new float[]{0.0f,0.1f},
//                    new Color[]{BACKGROUND_GRADIENT_TOP_COLOR,
//                        BACKGROUND_GRADIENT_BOTTOM_COLOR});
        return new GradientPaint(x1,(float)y,BACKGROUND_GRADIENT_COLOR,
                x1,(float)(y+h-1),
                new Color(BACKGROUND_GRADIENT_COLOR.getRGB()&0x00FFFFFF,true));
    }
    
    protected int getBackgroundDotOffset(int iconSize){
        return Math.floorDiv((iconSize%BACKGROUND_DOT_SPACING), 2);
    }
    
    protected int getBackgroundDotOffsetX(int width){
        return getBackgroundDotOffset(width);
    }
    
    protected int getBackgroundDotOffsetY(int height){
        return getBackgroundDotOffset(height);
    }
    
    protected void paintBackgroundDot(Graphics2D g, int x, int y, int xOff, int yOff){
        int[] xPoints = new int[BACKGROUND_DOT_POINTS];
        int[] yPoints = new int[BACKGROUND_DOT_POINTS];
        xPoints[0] = xPoints[1] = xPoints[2] = x + xOff;
        yPoints[0] = yPoints[1] = yPoints[3] = y + yOff;
        xPoints[1] -= BACKGROUND_DOT_HALF_SIZE;
        yPoints[0] -= BACKGROUND_DOT_HALF_SIZE;
        xPoints[3] = xPoints[1] + BACKGROUND_DOT_SIZE;
        yPoints[2] = yPoints[0] + BACKGROUND_DOT_SIZE;
        g.fillPolygon(xPoints, yPoints, BACKGROUND_DOT_POINTS);
    }
    
    protected void paintBackground(Graphics2D g, int x, int y, int w, int h){
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(x, y, w, h);
        Graphics2D tempG = (Graphics2D) g.create(x, y, w, h);
        tempG.setColor(BACKGROUND_DOT_COLOR);
        for (int i = 0; (i * BACKGROUND_DOT_SPACING) <= h; i++){
            int yDot = (i * BACKGROUND_DOT_SPACING);
            for (int xDot = BACKGROUND_DOT_SPACING * (i % 2); xDot <= w; 
                    xDot+=BACKGROUND_DOT_SPACING+BACKGROUND_DOT_SPACING){
                paintBackgroundDot(tempG,xDot,yDot,getBackgroundDotOffsetX(w),
                        getBackgroundDotOffsetY(h));
            }
        }
        tempG.dispose();
        g.setPaint(getBackgroundGradient(x,y,w,h));
        g.fillRect(x, y, w, h);
    }

    @Override
    public void paint(Graphics2D g, RectangularShape object, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
