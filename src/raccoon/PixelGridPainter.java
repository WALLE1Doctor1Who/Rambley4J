/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import java.awt.*;
import java.awt.geom.Path2D;
import swing.ListenedPainter;

/**
 * This is a Painter that paints the pixel grid overlay over Rambley the 
 * Raccoon.
 * @author Milo Steier
 */
public class PixelGridPainter extends ListenedPainter<Component>{
    /**
     * This is the color used to render the pixel grid effect that goes over 
     * Rambley. This color is a translucent black.
     */
    public static final Color PIXEL_GRID_COLOR = new Color(0x60000000,true);
    /**
     * This is the default spacing between the lines in the pixel grid. For the 
     * vertical lines, this is the horizontal spacing. For the horizontal lines, 
     * this is the vertical spacing.
     */
    protected static final double DEFAULT_LINE_SPACING = 5;
    /**
     * This is the default line thickness for the pixel grid.
     */
    protected static final float DEFAULT_LINE_THICKNESS = 1f;
    /**
     * This identifies that a change has been made to the spacing of the lines 
     * in the pixel grid.
     */
    public static final String LINE_SPACING_PROPERTY_CHANGED = 
            "PixelGridSpacingPropertyChanged";
    /**
     * This identifies that a change has been made to the thickness of the lines 
     * in the pixel grid.
     */
    public static final String LINE_THICKNESS_PROPERTY_CHANGED = 
            "PixelGridThicknessPropertyChanged";
    /**
     * This is the spacing between the lines in the pixel grid. For the vertical 
     * lines, this is the horizontal spacing. For the horizontal lines, this is 
     * the vertical spacing.
     */
    private double gridSpacing;
    /**
     * This is the thickness of the lines in the pixel grid.
     */
    private float gridThickness;
    /**
     * A BasicStroke object used to draw the pixel grid. This is initially null 
     * and is initialized the first time it is used. This is also replaced 
     * whenever the pixel grid thickness changes.
     */
    private BasicStroke gridStroke = null;
    /**
     * A Path2D object used to render the pixel grid. This is initially null and
     * is initialized the first time it is used.
     */
    private Path2D pixelGrid = null;
    
    public PixelGridPainter(){
        gridSpacing = DEFAULT_LINE_SPACING;
        gridThickness = DEFAULT_LINE_THICKNESS;
    }
    
    /**
     * This returns the spacing between the lines in the pixel grid. For the 
     * vertical lines, this is the horizontal spacing. For the horizontal lines, 
     * this is the vertical spacing.
     * @return The spacing between the lines in the pixel grid.
     * @see #setLineSpacing 
     * @see #getLineThickness 
     * @see #setLineThickness 
     */
    public double getLineSpacing(){
        return gridSpacing;
    }
    /**
     * This sets the spacing between the lines in the pixel grid. For the 
     * vertical lines, this is the horizontal spacing. For the horizontal lines, 
     * this is the vertical spacing.
     * @param spacing The spacing between the lines in the pixel grid.
     * @return This {@code PixelGridPainter}.
     * @see #getLineSpacing 
     * @see #getLineThickness 
     * @see #setLineThickness 
     */
    public PixelGridPainter setLineSpacing(double spacing){
            // If the new spacing is different from the old spacing
        if (spacing != gridSpacing){
                // Get the old line spacing
            double old = gridSpacing;
            gridSpacing = spacing;
            firePropertyChange(LINE_SPACING_PROPERTY_CHANGED,old,spacing);
        }
        return this;
    }
    /**
     * This returns the thickness of the lines in the pixel grid.
     * @return The thickness for the lines in the pixel grid.
     * @see #setLineThickness 
     * @see #getLineSpacing 
     * @see #setLineSpacing 
     */
    public float getLineThickness(){
        return gridThickness;
    }
    /**
     * This sets the thickness of the lines in the pixel grid.
     * @param thickness The thickness for the lines in the pixel grid.
     * @return This {@code PixelGridPainter}.
     * @throws IllegalArgumentException If the given line thickness is negative.
     * @see #getLineThickness 
     * @see #getLineSpacing 
     * @see #getLineSpacing 
     */
    public PixelGridPainter setLineThickness(float thickness){
            // If the line thickness is less than zero
        if (thickness < 0.0f)
            throw new IllegalArgumentException("Pixel Grid line thickness must "
                    + "be greater than or equal to zero ("+thickness+ " < 0)");
            // If the new thickness is different from the old thickness
        if (thickness != gridThickness){
                // Get the old line thickness
            float old = gridThickness;
            gridThickness = thickness;
            firePropertyChange(LINE_THICKNESS_PROPERTY_CHANGED,old,thickness);
        }
        return this;
    }

    @Override
    public void paint(Graphics2D g, Component c, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    /**
     * This is used to calculate the offset for the pixel grid effect using the 
     * given size value.
     * @param size The value to use to get the offset.
     * @return The offset for the pixel grid effect.
     * @see getPixelGridOffsetX
     * @see getPixelGridOffsetY
     * @see getLineSpacing
     */
    private double getPixelGridOffset(double size){
        return ((size-1)%getLineSpacing())/2.0;
    }
    /**
     * This returns the x offset to use for the horizontal lines of the pixel 
     * grid effect.
     * 
     * @implSpec The default implementation is equivalent to {@code ((width-1) 
     * %} {@link getLineSpacing() getLineSpacing()}{@code )/2.0}.
     * 
     * @param width The width of the area to fill with the pixel grid effect.
     * @return The offset for the x-coordinate of the pixel grid effect.
     * @see #getPixelGridOffsetY
     * @see #getPixelGrid 
     * @see #paintPixelGrid
     * @see getLineSpacing
     * @see setLineSpacing
     */
    protected double getPixelGridOffsetX(double width){
        return getPixelGridOffset(width);
    }
    /**
     * This returns the y offset to use for the vertical lines of the pixel grid 
     * effect. 
     * 
     * @implSpec The default implementation is equivalent to {@code ((height-1) 
     * %} {@link getLineSpacing() getLineSpacing()}{@code )/2.0}.
     * 
     * @param height The height of the area to fill with the pixel grid effect.
     * @return The offset for the y-coordinate of the pixel grid effect.
     * @see #getPixelGridOffsetY
     * @see #getPixelGrid 
     * @see #paintPixelGrid
     * @see getLineSpacing
     * @see setLineSpacing
     */
    protected double getPixelGridOffsetY(double height){
        return getPixelGridOffset(height);
    }
    /**
     * This returns the Path2D object used to render the pixel grid effect. The 
     * pixel grid effect is a set of horizontal and vertical lines that span the 
     * whole image. The horizontal lines are offset by {@link 
     * #getPixelGridOffsetX getPixelGridOffsetX} and vertical lines are offset 
     * by {@link #getPixelGridOffsetY getPixelGridOffsetY}. The horizontal and 
     * vertical lines are spaced out by {@link getLineSpacing getLineSpacing}.
     * @param x The x-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param y The y-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param w The width of the area to fill with the pixel grid effect.
     * @param h The height of the area to fill with the pixel grid effect.
     * @param path A Path2D object to store the results in, or null.
     * @return The Path2D object to use to render the pixel grid effect.
     * @see getPixelGridOffsetX
     * @see getPixelGridOffsetY
     * @see getLineSpacing
     * @see setLineSpacing
     * @see #paintPixelGrid
     */
    protected Path2D getPixelGrid(double x, double y, double w, double h, 
            Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // Get the maximum x-coordinate for the pixel grid
        double x2 = x+w;
            // Get the maximum y-coordinate for the pixel grid
        double y2 = y+h;
            // Go through and generate the vertical lines, starting at the 
            // offset for the y-coordinate of the pixel grid and spacing them 
            // out by the pixel grid spacing
        for (double y1 = getPixelGridOffsetY(h); y1 <= h; y1+=getLineSpacing()){
            path.moveTo(x, y1+y);
            path.lineTo(x2, y1+y);
        }   // Go through and generate the horizontal lines, starting at the 
            // offset for the x-coordinate of the pixel grid and spacing them 
            // out by the pixel grid spacing
        for (double x1 = getPixelGridOffsetX(w); x1 <= w; x1+=getLineSpacing()){
            path.moveTo(x1+x, y);
            path.lineTo(x1+x, y2);
        }
        return path;
    }
    
    protected BasicStroke getPixelGridStroke(){
        if (gridStroke == null || gridStroke.getLineWidth()!=getLineThickness())
            gridStroke = new BasicStroke(getLineThickness());
        return gridStroke;
    }
    /**
     * This is used to render the pixel grid effect over the area. The pixel 
     * grid effect is drawn without antialiasing and in a {@link 
     * PIXEL_GRID_COLOR transparent black} color. The pixel grid effect is 
     * rendered as a grid of horizontal and vertical lines that cover the area, 
     * with the spacing between the lines being {@link getLineSpacing 
     * getLineSpacing}. The horizontal lines are offset by {@link 
     * #getPixelGridOffsetX getPixelGridOffsetX} and vertical lines are offset 
     * by {@link #getPixelGridOffsetY getPixelGridOffsetY}. The path used to 
     * draw the pixel grid effect is generated by the {@link #getPixelGrid 
     * getPixelGrid} method and will have a line thickness of {@link 
     * #getLineThickness getLineThickness}. The pixel grid effect is rendered as 
     * a grid of horizontal and vertical lines 
     * that cover the area, with the spacing between the lines being {@link 
     * getLineSpacing getLineSpacing}. The horizontal lines 
     * are offset by {@link #getPixelGridOffsetX getPixelGridOffsetX} and 
     * vertical lines are offset by {@link #getPixelGridOffsetY 
     * getPixelGridOffsetY}. The path used to draw the pixel grid effect is 
     * generated by the {@link #getPixelGrid getPixelGrid} method. This renders 
     * to a copy of the given graphics context, so as to protect the rest of the 
     * paint code from changes made to the graphics context while rendering the 
     * pixel grid effect.
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param y The y-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param w The width of the area to fill with the pixel grid effect.
     * @param h The height of the area to fill with the pixel grid effect.
     * @see #paint 
     * @see PIXEL_GRID_COLOR
     * @see #getPixelGrid 
     * @see #getLineSpacing 
     * @see #setLineSpacing 
     * @see #getLineThickness 
     * @see #setLineThickness 
     * @see #getPixelGridOffsetX 
     * @see #getPixelGridOffsetY 
     */
    protected void paintPixelGrid(Graphics2D g,int x,int y,int w,int h){
            // Create a copy of the given graphics context over the given area
        g = (Graphics2D) g.create(x, y, w, h);
            // Set the color to the pixel grid color
        g.setColor(PIXEL_GRID_COLOR);
            // Set the stroke to use to draw the pixel grid to use the set line 
            // thickness
        g.setStroke(getPixelGridStroke());
            // Turn off antialiasing for the pixel grid
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
            // Generate the pixel grid
        pixelGrid = getPixelGrid(0,0,w,h,pixelGrid);
            // Render the pixel grid
        g.draw(pixelGrid);
            // Dispose of the copy of the graphics context
        g.dispose();
    }
}
