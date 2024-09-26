/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Objects;
import swing.ListenedPainter;

/**
 * This is a Painter that paints the pixel grid overlay over Rambley the 
 * Raccoon.
 * @author WALLE1Doctor1Who
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
     * This is the width and height at which the pixel grid is drawn at 
     * internally.
     */
    private static final double INTERNAL_RENDER_SIZE = 256;
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
     * @throws IllegalArgumentException If the given line spacing is negative.
     * @see #getLineSpacing 
     * @see #getLineThickness 
     * @see #setLineThickness 
     */
    public PixelGridPainter setLineSpacing(double spacing){
            // If the new spacing is less than zero
        if (spacing < 0)
            throw new IllegalArgumentException("Pixel grid line spacing cannot "
                    + "be negative ("+spacing + " < 0)");
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
            throw new IllegalArgumentException("Pixel grid line thickness must "
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
    /**
     * 
     * @param size
     * @return 
     */
    protected double getScale(double size){
        return size / INTERNAL_RENDER_SIZE;
    }
    /**
     * 
     * @param width
     * @param height
     * @return 
     */
    protected double getScaledLineSpacing(double width, double height){
        return getLineSpacing() * getScale(Math.min(width, height));
    }
    
    protected float getScaledLineThickness(double width, double height){
        return (float) (getLineThickness() * getScale(Math.min(width, height)));
    }
    /**
     * This is used to calculate the offset for the pixel grid effect using the 
     * given size value.
     * @param spacing
     * @param size The value to use to get the offset.
     * @return The offset for the pixel grid effect.
     * @see getPixelGridOffsetX
     * @see getPixelGridOffsetY
     * @see getLineSpacing
     */
    private double getPixelGridOffset(double spacing, double size){
        return ((size-1)%spacing)/2.0;
    }
    /**
     * This returns the x offset to use for the horizontal lines of the pixel 
     * grid effect.
     * 
     * @implSpec The default implementation is equivalent to {@code ((width-1) 
     * %} {@link getLineSpacing() getLineSpacing()}{@code )/2.0}.
     * 
     * @param spacing
     * @param width The width of the area to fill with the pixel grid effect.
     * @return The offset for the x-coordinate of the pixel grid effect.
     * @see #getPixelGridOffsetY
     * @see #getPixelGrid 
     * @see #paintPixelGrid
     * @see getLineSpacing
     * @see setLineSpacing
     */
    protected double getPixelGridOffsetX(double spacing, double width){
        return getPixelGridOffset(spacing, width);
    }
    /**
     * This returns the y offset to use for the vertical lines of the pixel grid 
     * effect. 
     * 
     * @implSpec The default implementation is equivalent to {@code ((height-1) 
     * %} {@link getLineSpacing() getLineSpacing()}{@code )/2.0}.
     * 
     * @param spacing
     * @param height The height of the area to fill with the pixel grid effect.
     * @return The offset for the y-coordinate of the pixel grid effect.
     * @see #getPixelGridOffsetY
     * @see #getPixelGrid 
     * @see #paintPixelGrid
     * @see getLineSpacing
     * @see setLineSpacing
     */
    protected double getPixelGridOffsetY(double spacing, double height){
        return getPixelGridOffset(spacing, height);
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
            // Get the spacing for the lines.
        double spacing = getScaledLineSpacing(w,h);
            // Go through and generate the vertical lines, starting at the 
            // offset for the y-coordinate of the pixel grid and spacing them 
            // out by the pixel grid spacing
        for (double y1 = getPixelGridOffsetY(spacing,h); y1 <= h; y1+=spacing){
            path.moveTo(x, y1+y);
            path.lineTo(x2, y1+y);
        }   // Go through and generate the horizontal lines, starting at the 
            // offset for the x-coordinate of the pixel grid and spacing them 
            // out by the pixel grid spacing
        for (double x1 = getPixelGridOffsetX(spacing,w); x1 <= w; x1+=spacing){
            path.moveTo(x1+x, y);
            path.lineTo(x1+x, y2);
        }
        return path;
    }
    
    protected BasicStroke getPixelGridStroke(double width, double height){
        return new BasicStroke(getScaledLineThickness(width,height));
    }

    @Override
    public void paint(Graphics2D g, Component c, int width, int height) {
            // Check if the graphics context is null
        Objects.requireNonNull(g);
            // If either the width or height are less than or equal to zero 
            // (nothing would be rendered anyway)
        if (width <= 0 || height <= 0)
            return;
            // Create a copy of the given graphics context over the given area
        g = (Graphics2D) g.create(0, 0, width, height);
            // Configure the graphics context
        g = configureGraphics(g,c);
            // Paint the pixel grid
        paintPixelGrid(g,0,0,width,height);
            // Dispose of the copy of the graphics context
        g.dispose();
    }
    /**
     * This is used to configure the graphics context used to render the pixel 
     * grid. It's assumed that the returned graphics context is the same as the 
     * given graphics context, or at least that the returned graphics context 
     * references the given graphics context in some way. 
     * @param g The graphics context to render to.
     * @param c A {@code Component} to get useful properties for painting.
     * @return The given graphics context, now configured for rendering the 
     * pixel grid.
     * @see #paint 
     * @see #paintPixelGrid 
     */
    protected Graphics2D configureGraphics(Graphics2D g, Component c){
            // Turn off antialiasing for the pixel grid
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
            // Prioritize rendering quality over speed
        g.setRenderingHint(RenderingHints.KEY_RENDERING, 
                RenderingHints.VALUE_RENDER_QUALITY);
            // Prioritize quality over speed for alpha interpolation
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, 
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            // Enable dithering
        g.setRenderingHint(RenderingHints.KEY_DITHERING, 
                RenderingHints.VALUE_DITHER_ENABLE);
            // Prioritize color rendering quality over speed
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, 
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        return g;
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
     * generated by the {@link #getPixelGrid getPixelGrid} method.
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
            // Set the color to the pixel grid color
        g.setColor(PIXEL_GRID_COLOR);
            // If the line spacing is greater than zero
        if (getLineSpacing() > 0){
                // Set the stroke to use to draw the pixel grid to use the set line 
                // thickness
            g.setStroke(getPixelGridStroke(w,h));
                // Generate the pixel grid
            pixelGrid = getPixelGrid(0,0,w,h,pixelGrid);
                // Render the pixel grid
            g.draw(pixelGrid);
        } else {
                // Fill a rectangle with the pixel grid color, since there is 
                // literally no space between the lines. Draw it a little 
                // oversized as the clipping will keep it in range.
            g.fillRect(x, y, w+1, h+1);
        }
    }
    /**
     * {@inheritDoc }
     */
    @Override
    protected String paramString(){
        return "gridSpacing="+getLineSpacing()+
                ",gridThickness="+getLineThickness();
    }
    /**
     * This resets this PixelGridPainter, setting it's values to their defaults.
     * @return This {@code PixelGridPainter}.
     */
    public PixelGridPainter reset(){
        return setLineSpacing(DEFAULT_LINE_SPACING).
                setLineThickness(DEFAULT_LINE_THICKNESS);
    }
}
