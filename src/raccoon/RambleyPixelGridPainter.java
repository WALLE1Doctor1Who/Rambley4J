/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author Milo Steier
 */
public class RambleyPixelGridPainter implements Painter<Shape>{
    /**
     * 
     */
    public static final Color PIXEL_GRID_COLOR = new Color(0x60000000,true);
    
    protected static final int PIXEL_GRID_SPACING = 5;
    
    private Path2D pixelGrid = null;
    
    protected double getPixelGridOffset(double iconSize){
        return ((iconSize-1)%PIXEL_GRID_SPACING)/2.0;
    }
    
    protected Path2D getPixelGrid(double x, double y, double w, double h){
        if (pixelGrid == null)
            pixelGrid = new Path2D.Double();
        else
            pixelGrid.reset();
        double x2 = x+w;
        double y2 = y+h;
        for (double y1 = getPixelGridOffset(h); y1 <= h; y1+=PIXEL_GRID_SPACING){
            pixelGrid.moveTo(x, y1+y);
            pixelGrid.lineTo(x2, y1+y);
        }
        for (double x1 = getPixelGridOffset(w); x1 <= w; x1+=PIXEL_GRID_SPACING){
            pixelGrid.moveTo(x1+x, y);
            pixelGrid.lineTo(x1+x, y2);
        }
        return pixelGrid;
    }
    
    protected void paintPixelGrid(Graphics2D g, int x, int y, int w, int h, Shape mask){
        g.clipRect(x, y, w, h);
        g.setColor(PIXEL_GRID_COLOR);
            // Turn off gntialiasing for the pixel grid
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
//        if (mask != null)
//            g.clip(mask);
        g.draw(getPixelGrid(0,0,w,h));
    }
    
    protected void paintPixelGrid(Graphics2D g, int x, int y, int w, int h){
        paintPixelGrid(g,x,y,w,h,null);
    }

    @Override
    public void paint(Graphics2D g, Shape mask, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
