/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import java.awt.*;
import swing.ListenedPainter;

/**
 *
 * @author WALLE1Doctor1Who
 */
public class BackgroundPainter extends ListenedPainter<Component>{
    
    
    
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
     * This identifies that a change has been made to the width and height of 
     * the background polka dots.
     */
    public static final String BACKGROUND_DOT_SIZE_PROPERTY_CHANGED = 
            "BackgroundDotSizePropertyChanged"; 
    /**
     * This identifies that a change has been made to the spacing of the 
     * background polka dots.
     */
    public static final String BACKGROUND_DOT_SPACING_PROPERTY_CHANGED = 
            "BackgroundDotSpacingPropertyChanged"; 
    
    
    
    /**
     * The width and height of the background polka dots.
     */
    private double dotSize;
    /**
     * This is the diagonal spacing between the centers of the background polka 
     * dots. That is to say, the center of each background polka dot is {@code 
     * dotSpacing} pixels to the left and {@code dotSpacing} pixels below the 
     * center of another background polka dot.
     */
    private double dotSpacing;
    
    private Color color;
    private Color dotColor;
    private Color gradientColor;
    private Color gradientColor2;

    @Override
    public void paint(Graphics2D g, Component object, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
//    /**
//     * This is used to render the background polka dots. The background polka 
//     * dots will be drawn in a {@link BACKGROUND_DOT_COLOR dark blue} color. 
//     * Each polka dot will be {@link getBackgroundDot getBackgroundDot()} in 
//     * width and height, and will be spaced diagonally by {@link 
//     * #getBackgroundDotSpacing getBackgroundDotSpacing}. The polka dots will be 
//     * either circular or rhombus-shaped, depending on whether {@link 
//     * #getCircularBackgroundDots getCircularBackgroundDots} is set to {@code 
//     * true} or not. Each polka dot is generated using the {@link 
//     * #getBackgroundDot getBackgroundDot} method. The polka dots will be offset 
//     * horizontally by {@link #getBackgroundDotOffsetX getBackgroundDotOffsetX} 
//     * and vertically by {@link #getBackgroundDotOffsetY 
//     * getBackgroundDotOffsetY}. This renders to a copy of the given graphics 
//     * context, so as to protect the rest of the paint code from changes made to 
//     * the graphics context while rendering the background polka dots.
//     * @param g The graphics context to render to.
//     * @param x The x-coordinate of the top-left corner of the area to fill.
//     * @param y The y-coordinate of the top-left corner of the area to fill.
//     * @param w The width of the area to fill.
//     * @param h The height of the area to fill.
//     * @see #paint 
//     * @see #paintBackground 
//     * @see BACKGROUND_DOT_COLOR
//     * @see #getBackgroundDot 
//     * @see #getBackgroundDotOffsetX 
//     * @see #getBackgroundDotOffsetY 
//     * @see #getBackgroundDotSize 
//     * @see #getBackgroundDotSpacing 
//     * @see #getCircularBackgroundDots 
//     * @see #setCircularBackgroundDots 
//     * @see #isBackgroundPainted 
//     * @see #setBackgroundPainted 
//     */
//    protected void paintBackgroundDots(Graphics2D g, int x, int y, int w, int h){
//            // Create a copy of the given graphics context over the given area
//        g = (Graphics2D) g.create(x, y, w, h);
//            // Set the color to the background polka dot color
//        g.setColor(dotColor);
//            // If the background scratch Ellipse2D object has not been 
//        if (bgEllipse == null)   // initialized yet
//            bgEllipse = new Ellipse2D.Double();
//            // If the background scratch Rhombus2D object has not been 
//        if (bgRhombus == null)  // initialized yet
//            bgRhombus = new Rhombus2D.Double();
//            // Get the x offset for the background polka dots
//        double x1 = getBackgroundDotOffsetX(w);
//            // Get the y offset for the background polka dots
//        double y1 = getBackgroundDotOffsetY(h);
//            // Go through the multipliers for the y-coordinates for the centers 
//            // of the background polka dots (to create the polka dot pattern, 
//            // we need to know what row number we are on, so we can offset the 
//            // x-coordinates accordingly)
//        for (int i = 0; (i * getBackgroundDotSpacing()) <= h; i++){
//                // Get the y-coordinate for the centers of the polka dots on 
//                // this row
//            double yDot = (i * getBackgroundDotSpacing())+y1;
//                // Go through the x-coordinates for the centers of the 
//                // background polka dots (polka dots on odd rows are offset 
//                // compared to the polka dots on even rows)
//            for (double xDot = getBackgroundDotSpacing() * (i % 2); xDot <= w; 
//                    xDot+=getBackgroundDotSpacing()+getBackgroundDotSpacing()){
//                    // Fill the current background polka dot
//                g.fill(getBackgroundDot(xDot+x1,yDot));
//            }
//        }   // Dispose of the copy of the graphics context
//        g.dispose();
//    }
    /**
     * This resets this BackgroundPainter, setting it's values to their 
     * defaults.
     * @return This {@code BackgroundPainter}.
     */
    public BackgroundPainter reset(){
        // TODO: Reset the fields to their default values.
        return this;
    }
}
