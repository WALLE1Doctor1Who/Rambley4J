/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import static geom.GeometryMath.*;
import java.awt.*;
import java.awt.geom.*;
import java.beans.*;
import java.util.*;
import java.util.function.DoubleUnaryOperator;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This is a Painter that paints Rambley the Raccoon from Indigo Park. 
 * 
 * Special thanks to AnimalWave on Discord for help with the equations that some 
 * of the code is based off of.
 * 
 * @author Milo Steier
 * @author AnimalWave on Discord
 */
public class RambleyPainter implements Painter<Component>{
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
    public static final Color BACKGROUND_GRADIENT_COLOR_2 = 
            new Color(BACKGROUND_GRADIENT_COLOR.getRGB()&0x00FFFFFF,true);
    /**
     * This is the color used to render the pixel grid effect that goes over 
     * Rambley. This color is a translucent black.
     */
    public static final Color PIXEL_GRID_COLOR = new Color(0x60000000,true);
    /**
     * This is the main color of Rambley the Raccoon. That is to say, this is 
     * the color which most of Rambley's body is comprised of.
     */
    public static final Color RAMBLEY_MAIN_BODY_COLOR = new Color(0xA591AE);
    /**
     * This is the secondary color of Rambley the Raccoon. That is to say, this 
     * is the color for many of Rambley's patches of fur and markings.
     */
    public static final Color RAMBLEY_SECONDARY_BODY_COLOR = new Color(0xEBDEE0);
    /**
     * This is the main color that is used to outline Rambley the Raccoon. That 
     * is to say, this is the color which most of the outline for Rambley is 
     * comprised of.
     */
    public static final Color RAMBLEY_OUTLINE_COLOR = new Color(0x624361);
    /**
     * This is the color for the stripes on Rambley the Raccoon's tail.
     */
    public static final Color RAMBLEY_STRIPE_COLOR = Color.BLACK;
    /**
     * This is the color for Rambley the Raccoon's paws. That is to say, this is 
     * the color for Rambley's hands and feet.
     */
    public static final Color RAMBLEY_PAW_COLOR = new Color(0x161311);
    /**
     * This is the color for the outline of Rambley the Raccoon's paws. That is 
     * to say, this is the color for the outline of Rambley's hands and feet.
     */
    public static final Color RAMBLEY_PAW_OUTLINE_COLOR = RAMBLEY_OUTLINE_COLOR;
    /**
     * This is the color for the mask-like markings on Rambley the Raccoon's 
     * face.
     */
    public static final Color RAMBLEY_FACE_MARKINGS_COLOR = RAMBLEY_STRIPE_COLOR;
    /**
     * This is the color for Rambley the Raccoon's eyebrows.
     */
    public static final Color RAMBLEY_EYEBROW_COLOR = new Color(0x60325D);
    /**
     * This is the color for Rambley the Raccoon's eye whites.
     */
    public static final Color RAMBLEY_EYE_WHITE_COLOR = Color.WHITE;
    /**
     * This is the color for the outline of Rambley the Raccoon's eyes.
     */
    public static final Color RAMBLEY_EYE_OUTLINE_COLOR = Color.BLACK;
    /**
     * This is the color for Rambley the Raccoon's irises.
     */
    public static final Color RAMBLEY_IRIS_COLOR = new Color(0x883EC1);
    /**
     * This is the color for the outline of Rambley the Raccoon's irises and 
     * pupils.
     */
    public static final Color RAMBLEY_IRIS_OUTLINE_COLOR = Color.BLACK;//new Color(0x23163F);
    /**
     * This is the color for evil Rambley's irisis. Evil Rambley is a version of 
     * Rambley the Raccoon with red eyes that first appeared on thumbnails of 
     * videos from the YouTube channel GameTheory on the topic of Indigo Park.
     */
    public static final Color EVIL_RAMBLEY_IRIS_COLOR = Color.RED;
    /**
     * This is the color for the outline of evil Rambley's irises and pupils. 
     * Evil Rambley is a version of Rambley the Raccoon with red eyes that first 
     * appeared on thumbnails of videos from the YouTube channel GameTheory on 
     * the topic of Indigo Park.
     */
    public static final Color EVIL_RAMBLEY_IRIS_OUTLINE_COLOR = 
            RAMBLEY_IRIS_OUTLINE_COLOR;
    /**
     * This is the color for Rambley the Raccoon's pupils.
     */
    public static final Color RAMBLEY_PUPIL_COLOR = Color.WHITE;
    /**
     * This is the color for Rambley the Raccoon's nose.
     */
    public static final Color RAMBLEY_NOSE_COLOR = RAMBLEY_PAW_COLOR;
    /**
     * This is the color for the outline of Rambley the Raccoon's nose.
     */
    public static final Color RAMBLEY_NOSE_OUTLINE_COLOR = RAMBLEY_OUTLINE_COLOR;
    /**
     * This is the color for the inside of Rambley the Raccoon's mouth.
     */
    public static final Color RAMBLEY_MOUTH_COLOR = new Color(0x3D0D2F);
    /**
     * This is the color for the outline of Rambley the Raccoon's mouth.
     */
    public static final Color RAMBLEY_MOUTH_OUTLINE_COLOR = RAMBLEY_OUTLINE_COLOR;
    /**
     * This is the color for Rambley the Raccoon's teeth.
     */
    public static final Color RAMBLEY_TEETH_COLOR = Color.WHITE;
    /**
     * This is the color for the outline of Rambley the Raccoon's teeth.
     */
    public static final Color RAMBLEY_TEETH_OUTLINE_COLOR = RAMBLEY_MOUTH_OUTLINE_COLOR;
    /**
     * This is the color for Rambley the Raccoon's tongue.
     */
    public static final Color RAMBLEY_TONGUE_COLOR = new Color(0x724794);
    /**
     * This is the color for the outline of Rambley the Raccoon's tongue.
     */
    public static final Color RAMBLEY_TONGUE_OUTLINE_COLOR = RAMBLEY_TONGUE_COLOR;
    /**
     * This is the color for Rambley the Raccoon's red scarf.
     */
    public static final Color RAMBLEY_SCARF_COLOR = new Color(0xC64C57);
    /**
     * This is the color for the outline of Rambley the Raccoon's red scarf.
     */
    public static final Color RAMBLEY_SCARF_OUTLINE_COLOR = new Color(0xA63442);
    /**
     * This is the color for Rambley the Raccoon's train conductor hat which he 
     * wears during the Rambley's Railroad ride.
     */
    public static final Color RAMBLEY_CONDUCTOR_HAT_COLOR = new Color(0x431188);
    /**
     * This is the color for the stripes on Rambley the Raccoon's train 
     * conductor hat which he wears during the Rambley's Railroad ride.
     */
    public static final Color RAMBLEY_CONDUCTOR_HAT_STRIPE_COLOR = new Color(0xF3E5FE);
    /**
     * This is the color for the outline of Rambley the Raccoon's train 
     * conductor hat which he wears during the Rambley's Railroad ride.
     */
    public static final Color RAMBLEY_CONDUCTOR_HAT_OUTLINE_COLOR = Color.BLACK;
    /**
     * This is the color for the border that goes around Rambley the Raccoon.
     */
    public static final Color RAMBLEY_BORDER_COLOR = Color.WHITE;
    /**
     * This is the color for Rambley the Raccoon's drop shadow.
     */
    public static final Color RAMBLEY_DROP_SHADOW_COLOR = Color.BLACK;
    /**
     * This is the width at which Rambley is rendered at internally. Rambley is 
     * scaled up or down to fill the area provided to the {@link #paint paint} 
     * method of {@code RambleyPainter}.
     */
    protected static final double INTERNAL_RENDER_WIDTH = 256;
    /**
     * This is the height at which Rambley is rendered at internally. Rambley is 
     * scaled up or down to fill the area provided to the {@link #paint paint} 
     * method of {@code RambleyPainter}.
     */
    protected static final double INTERNAL_RENDER_HEIGHT = 256;
    /**
     * This is the default width and height of the background polka dots.
     */
    protected static final double DEFAULT_BACKGROUND_DOT_SIZE = 8.0;
    /**
     * This is the default diagonal spacing between the centers of the 
     * background polka dots.
     * 
     * @todo Rework the documentation for this constant.
     */
    protected static final double DEFAULT_BACKGROUND_DOT_SPACING = 12.0;
    /**
     * This is the default spacing between the lines in the pixel grid. For the 
     * vertical lines, this is the horizontal spacing. For the horizontal lines, 
     * this is the vertical spacing.
     */
    protected static final double DEFAULT_PIXEL_GRID_LINE_SPACING = 5;
    /**
     * The offset for the x-coordinate of the top-left corner of Rambley.
     */
    protected static final double RAMBLEY_X_OFFSET = 28;
    /**
     * The offset for the y-coordinate of the top-left corner of Rambley.
     */
    protected static final double RAMBLEY_Y_OFFSET = 60;
    /**
     * This is the angle of elevation for Rambley's cheeks.
     */
    protected static final double RAMBLEY_CHEEK_ANGLE = 26.57;
    /**
     * This is the height of the triangle that is used to create Rambley's 
     * cheeks. This is equal to {@code tan(}{@link RAMBLEY_CHEEK_ANGLE}{@code ) 
     * * 100}.
     */
    protected static final double RAMBLEY_CHEEK_TRIANGLE_HEIGHT = 
            Math.tan(Math.toRadians(RAMBLEY_CHEEK_ANGLE))*100;
    /**
     * This is the width and height of Rambley's irises.
     */
    protected static final double RAMBLEY_IRIS_SIZE = 24;
    /**
     * This contains half of the {@link RAMBLEY_IRIS_SIZE size} of Rambley's 
     * irises. This is used for calculating the location of Rambley's irises 
     * when using their center coordinates to position them.
     */
    private static final double RAMBLEY_IRIS_HALF_SIZE = RAMBLEY_IRIS_SIZE/2.0;
    /**
     * This is the width and height of Rambley's pupils.
     */
    protected static final double RAMBLEY_PUPIL_SIZE = RAMBLEY_IRIS_SIZE-14;
    /**
     * This contains half of the {@link RAMBLEY_PUPIL_SIZE size} of Rambley's 
     * pupils. Rambley's pupils are centered in Rambley's irises.
     */
    private static final double RAMBLEY_PUPIL_HALF_SIZE = RAMBLEY_PUPIL_SIZE/2.0;
    /**
     * This is the offset for the x-coordinates in the equations used to 
     * calculate the curves for Rambley's ears. This is effectively used to flip 
     * the ear horizontally since the equations produce the left ear while the 
     * program expects the right ear.
     */
    private static final double RAMBLEY_EAR_X_OFFSET = 1.5;
    /**
     * This is the offset for the y-coordinates in the equations used to 
     * calculate the curves for Rambley's ears. The ear equations are 
     * effectively shifted down by this value so that the lower curve passes 
     * through point (0,0) in the coordinate system used by the ear equations.
     */
    private static final double RAMBLEY_EAR_Y_OFFSET = getRambleyEarOffset(0);
    /**
     * This is the multiplier to use to convert coordinates in the ear equations 
     * coordinate system to the image coordinate system.
     */
    private static final double RAMBLEY_EAR_MULTIPLIER = 42;
    /**
     * This is the height at which Rambley's ears are rendered at. This is also 
     * used to vertically flip the ears as otherwise they'd produce upside down 
     * ears.
     */
    private static final double RAMBLEY_EAR_HEIGHT = 1.8*RAMBLEY_EAR_MULTIPLIER;
    /**
     * This is the offset for the y-coordinates when calculating the curve for 
     * the tip of Rambley's ears. The curve for the tip of Rambley's ears are 
     * shifted down by this amount in order to make the tips of Rambley's ears 
     * look better.
     */
    private static final double RAMBLEY_EAR_TIP_Y_OFFSET = 1.0;
    /**
     * This is the amount by which to backtrack on the curves that make up 
     * Rambley's ears in order to more smoothly transition between the upper 
     * curve to the tip curve, and from the tip curve to the lower curve.
     */
    protected static final double RAMBLEY_EAR_TIP_ROUNDING = 2.0;
    /**
     * This is the scaling factor used when scaling the shapes that make up 
     * Rambley's ears in order to get the inner portion of his ears.
     */
    protected static final double RAMBLEY_INNER_EAR_SCALE = 2/3.0;
    /**
     * This converts the given y-coordinate in the image coordinate system to a 
     * y-coordinate in the coordinate system used by the equations used to 
     * calculate the curves that make up Rambley's ears. 
     * @param y The y-coordinate in the image coordinate system to convert.
     * @return The y-coordinate in the ear equation coordinate system.
     * @see #earEquToGraphicsY 
     * @see #earEquToGraphicsX 
     * @see #graphicsToEarEquX 
     * @see RAMBLEY_EAR_HEIGHT
     * @see RAMBLEY_EAR_MULTIPLIER
     * @see RAMBLEY_EAR_Y_OFFSET
     */
    private static double graphicsToEarEquY(double y){
        y = RAMBLEY_EAR_HEIGHT - y;
        y /= RAMBLEY_EAR_MULTIPLIER;
        return y + RAMBLEY_EAR_Y_OFFSET;
    }
    /**
     * This converts the given x-coordinate in the image coordinate system to a 
     * X-coordinate in the coordinate system used by the equations used to 
     * calculate the curves that make up Rambley's ears. 
     * @param x The x-coordinate in the image coordinate system to convert.
     * @return The x-coordinate in the ear equation coordinate system.
     * @see #earEquToGraphicsX
     * @see #earEquToGraphicsY 
     * @see #graphicsToEarEquY 
     * @see RAMBLEY_EAR_HEIGHT
     * @see RAMBLEY_EAR_X_OFFSET
     */
    private static double graphicsToEarEquX(double x){
        x /= RAMBLEY_EAR_MULTIPLIER;
        return RAMBLEY_EAR_X_OFFSET-x;
    }
    /**
     * This converts the given y-coordinate in the coordinate system used by the 
     * equations used to calculate the curves that make up Rambley's ears to a 
     * y-coordinate in the image coordinate system.
     * @param y The y-coordinate in the ear equation coordinate system to 
     * convert.
     * @return The y-coordinate in the image coordinate system.
     * @see #graphicsToEarEquY 
     * @see #graphicsToEarEquX 
     * @see #earEquToGraphicsX 
     * @see RAMBLEY_EAR_HEIGHT
     * @see RAMBLEY_EAR_MULTIPLIER
     * @see RAMBLEY_EAR_Y_OFFSET
     */
    private static double earEquToGraphicsY(double y){
        y -= RAMBLEY_EAR_Y_OFFSET;
        y *= RAMBLEY_EAR_MULTIPLIER;
        return (RAMBLEY_EAR_HEIGHT-y);
    }
    /**
     * This converts the given y-coordinate in the coordinate system used by the 
     * equations used to calculate the curves that make up Rambley's ears to a 
     * y-coordinate in the image coordinate system.
     * @param y The y-coordinate in the ear equation coordinate system to 
     * convert.
     * @return The y-coordinate in the image coordinate system.
     * @see #graphicsToEarEquX 
     * @see #graphicsToEarEquY 
     * @see #earEquToGraphicsY 
     * @see RAMBLEY_EAR_HEIGHT
     * @see RAMBLEY_EAR_X_OFFSET
     */
    private static double earEquToGraphicsX(double x){
        return (RAMBLEY_EAR_X_OFFSET-x)*RAMBLEY_EAR_MULTIPLIER;
    }
    /**
     * This is the flag for whether the background will be painted.
     */
    public static final int PAINT_BACKGROUND_FLAG =         0x00000001;
    /**
     * This is the flag for whether the pixel grid effect will be painted.
     */
    public static final int PAINT_PIXEL_GRID_FLAG =         0x00000002;
    /**
     * This is the flag for whether the border around Rambley and Rambley's 
     * shadow will be painted.
     * 
     * @todo Finalize the name for this flag and make it public instead of 
     * protected.
     */
    protected static final int PAINT_BORDER_AND_SHADOW_FLAG =  0x00000004;
    /**
     * This is the flag for ignoring Rambley's aspect ratio when rendering 
     * Rambley.
     */
    public static final int IGNORE_ASPECT_RATIO_FLAG =      0x00000008;
    /**
     * This is the flag for whether evil Rambley will be painted instead of 
     * normal Rambley. Evil Rambley is a version of Rambley the Raccoon with red 
     * eyes that first appeared on thumbnails of videos from the YouTube channel 
     * GameTheory on the topic of Indigo Park.
     */
    public static final int EVIL_RAMBLEY_FLAG =             0x00000010;
    /**
     * This is the flag for drawing the background polka dots as circles instead 
     * of diamonds.
     */
    public static final int CIRCULAR_BACKGROUND_DOTS_FLAG = 0x00000020;
    /**
     * This is the flag which controls which side of Rambley's face his fang 
     * shows on when his mouth is open. This flag has no effect when Rambley's 
     * mouth is closed, and has no effect when Rambley is evil, since Evil 
     * Rambley shows both fangs. When set, Rambley's fang will show on the left 
     * side of his mouth instead of the right side.
     */
    public static final int RAMBLEY_FANG_SIDE_FLAG =        0x00000040;
    /**
     * This is the flag which controls whether Rambley's jaw is closed when his 
     * mouth is open.
     */
    public static final int RAMBLEY_JAW_CLOSED_FLAG =       0x00000080;
    /**
     * This stores the flags that are set initially when a RambleyPainter is 
     * first constructed.
     */
    private static final int DEFAULT_FLAG_SETTINGS = PAINT_BACKGROUND_FLAG | 
            PAINT_PIXEL_GRID_FLAG | PAINT_BORDER_AND_SHADOW_FLAG;
    // Maximum flag value goes here
    /**
     * This identifies that a change has been made to whether the background 
     * should be painted.
     */
    public static final String BACKGROUND_PAINTED_PROPERTY_CHANGED = 
            "BackgroundPaintedPropertyChanged";
    /**
     * This identifies that a change has been made to whether the pixel grid 
     * should be painted.
     */
    public static final String PIXEL_GRID_PAINTED_PROPERTY_CHANGED = 
            "PixelGridPaintedPropertyChanged";
    /**
     * This identifies that a change has been made to whether Rambley's border 
     * and shadow should be painted.
     * 
     * @todo Finalize the name for this flag.
     */
    public static final String BORDER_AND_SHADOW_PAINTED_PROPERTY_CHANGED = 
            "BorderShadowPaintedPropertyChanged";
    /**
     * This identifies that a change has been made to whether the aspect ratio 
     * for Rambley will be ignored.
     */
    public static final String IGNORE_ASPECT_RATIO_PROPERTY_CHANGED = 
            "IgnoreAspectRatioPropertyChanged";
    /**
     * This identifies that a change has been made to whether Rambley is evil or 
     * not.
     */
    public static final String EVIL_RAMBLEY_PROPERTY_CHANGED = 
            "EvilRambleyPropertyChanged";
    /**
     * This identifies that a change has been made to whether the background 
     * polka dots are circular or diamonds.
     */
    public static final String CIRCULAR_BACKGROUND_DOTS_PROPERTY_CHANGED = 
            "CircularDotsPropertyChanged";
    /**
     * This identifies that a change has been made to which side Rambley's fang 
     * will be on when his mouth is open.
     */
    public static final String RAMBLEY_FANG_SIDE_PROPERTY_CHANGED = 
            "RambleyFangSidePropertyChanged";
    /**
     * This identifies that a change has been made to whether Rambley's jaw is 
     * closed when his mouth is open.
     */
    public static final String RAMBLEY_JAW_CLOSED_PROPERTY_CHANGED = 
            "RambleyJawClosedPropertyChanged";
    // Any more flag property names go here
    /**
     * This generates a map that maps flags for controlling {@code 
     * RambleyPainter} to their respective property names. This map is not 
     * modifiable. 
     * @return A map that maps flags to property names.
     */
    private static NavigableMap<Integer, String> generateFlagNameMap(){
            // Create a map to map the flags to the names
        TreeMap<Integer, String> nameMap = new TreeMap<>();
        nameMap.put(PAINT_BACKGROUND_FLAG, BACKGROUND_PAINTED_PROPERTY_CHANGED);
        nameMap.put(PAINT_PIXEL_GRID_FLAG, PIXEL_GRID_PAINTED_PROPERTY_CHANGED);
        nameMap.put(PAINT_BORDER_AND_SHADOW_FLAG, 
                BORDER_AND_SHADOW_PAINTED_PROPERTY_CHANGED);
        nameMap.put(IGNORE_ASPECT_RATIO_FLAG, 
                IGNORE_ASPECT_RATIO_PROPERTY_CHANGED);
        nameMap.put(EVIL_RAMBLEY_FLAG, EVIL_RAMBLEY_PROPERTY_CHANGED);
        nameMap.put(CIRCULAR_BACKGROUND_DOTS_FLAG, 
                CIRCULAR_BACKGROUND_DOTS_PROPERTY_CHANGED);
        nameMap.put(RAMBLEY_FANG_SIDE_FLAG, RAMBLEY_FANG_SIDE_PROPERTY_CHANGED);
        nameMap.put(RAMBLEY_JAW_CLOSED_FLAG, 
                RAMBLEY_JAW_CLOSED_PROPERTY_CHANGED);
        
            // Return an unmodifiable verion of the map
        return Collections.unmodifiableNavigableMap(nameMap);
    }
    /**
     * This is a map that maps the flags for controlling {@code RambleyPainter} 
     * to their respective property names. If a flag does not appear in this 
     * map, then it is not considered a property of {@code RambleyPainter}.
     */
    public static final NavigableMap<Integer, String> FLAG_PROPERTY_NAMES_MAP = 
            generateFlagNameMap();
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
     * This identifies that a change has been made to the spacing of the lines 
     * in the pixel grid.
     */
    public static final String PIXEL_GRID_LINE_SPACING_PROPERTY_CHANGED = 
            "PixelGridSpacingPropertyChanged"; 
    
//    public static final String RAMBLEY_RIGHT_EYE_X_PROPERTY_CHANGED = 
//            "RambleyRightEyeXPropertyChanged";
//    
//    public static final String RAMBLEY_RIGHT_EYE_Y_PROPERTY_CHANGED = 
//            "RambleyRightEyeYPropertyChanged";
//    
//    public static final String RAMBLEY_LEFT_EYE_X_PROPERTY_CHANGED = 
//            "RambleyLeftEyeXPropertyChanged";
//    
//    public static final String RAMBLEY_LEFT_EYE_Y_PROPERTY_CHANGED = 
//            "RambleyLeftEyeYPropertyChanged";
    /**
     * This identifies that a change has been made to how wide Rambley's mouth 
     * is open.
     */
    public static final String RAMBLEY_OPEN_MOUTH_WIDTH_PROPERTY_CHANGED = 
            "RambleyOpenMouthWidthPropertyChanged";
    /**
     * This identifies that a change has been made to how far Rambley's mouth 
     * is open.
     */
    public static final String RAMBLEY_OPEN_MOUTH_HEIGHT_PROPERTY_CHANGED = 
            "RambleyOpenMouthHeightPropertyChanged";
    
    // Settings and listeners
    
    /**
     * This is an EventListenerList to store the listeners for this class.
     */
    protected EventListenerList listenerList = new EventListenerList();
    /**
     * This is the PropertyChangeSupport used to handle changes to the 
     * properties of this painter.
     */
    private final PropertyChangeSupport changeSupport;
    /**
     * This stores the flags used to store the settings for this painter.
     */
    private int flags;
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
    /**
     * This is the spacing between the lines in the pixel grid. For the vertical 
     * lines, this is the horizontal spacing. For the horizontal lines, this is 
     * the vertical spacing.
     */
    private double lineSpacing;
    /**
     * The x component for the location of the center of Rambley's right iris and 
     * pupil. 0.5 is the default position, 0.0 is the left-most bounds for Rambley's right eye 
     * (screen's left, Rambley's right), 1.0 is the right-most bounds for 
     * Rambley's right eye (screen's right, Rambley's left).
     */
    private double eyeRightX;
    /**
     * The y component for the location of the center of Rambley's right iris and 
     * pupil. 0.5 is center, 0.0 is the top-most bounds for Rambley's right eye, 
     * 1.0 is the bottom-most bounds for Rambley's right eye.
     */
    private double eyeRightY;
    /**
     * The x component for the location of the center of Rambley's left iris and 
     * pupil. 0.5 is the default position, 0.0 is the left-most bounds for Rambley's left eye 
     * (screen's left, Rambley's right), 1.0 is the right-most bounds for 
     * Rambley's left eye (screen's right, Rambley's left).
     */
    private double eyeLeftX;
    /**
     * The y component for the location of the center of Rambley's left iris and 
     * pupil. 0.5 is center, 0.0 is the top-most bounds for Rambley's left eye, 
     * 1.0 is the bottom-most bounds for Rambley's left eye.
     */
    private double eyeLeftY;
    /**
     * This is used to control how far Rambley's mouth is open. 0.0 to 1.0
     */
    private double mouthOpenWidth;
    /**
     * This is used to control how wide Rambley's mouth is open. 0.0 to 1.0
     */
    private double mouthOpenHeight;
    
    // Commonly used objects for the rendering process
    
    /**
     * A BasicStroke object used to render most of Rambley. This is initially 
     * null and is initialized the first time it is used.
     */
    private BasicStroke normalStroke = null;
    /**
     * A BasicStroke object used to render some of the details and finer 
     * outlines of Rambley. This is initially null and is initialized the first 
     * time it is used.
     */
    private BasicStroke detailStroke = null;
    /**
     * A BasicStroke object used to render most of the outlines of Rambley. This 
     * is initially null and is initialized the first time it is used.
     */
    private BasicStroke outlineStroke = null;
    /**
     * This is the function to get the y-coordinate for a given x-coordinate for 
     * a point on the line that produces the upper curve on Rambley's right ear. 
     * This is initially null and is initialized the first time it is used.
     */
    private DoubleUnaryOperator earEquationU = null;
    /**
     * This is the function to get the y-coordinate for a given x-coordinate for 
     * a point on the line that produces the lower curve on Rambley's right ear. 
     * This is initially null and is initialized the first time it is used.
     */
    private DoubleUnaryOperator earEquationL = null;
    /**
     * This is the function to get the y-coordinate for a given x-coordinate for 
     * a point on the line that produces the curve that forms the tip for 
     * Rambley's right ear. This is initially null and is initialized the first 
     * time it is used.
     */
    private DoubleUnaryOperator earEquationT = null;
    /**
     * This is an AffineTransform used to flip shapes horizontally. This is 
     * initially null and is initialized the first time it is used.
     */
    private AffineTransform horizFlip = null;
    
    // Dedicated scratch shape objects
    
    /**
     * A Path2D object used to render the pixel grid effect. This is initially 
     * null and is initialized the first time it is used.
     */
    private Path2D pixelGrid = null;
    /**
     * An Ellipse2D object used to render Rambley's irises. This is initially 
     * null and is initialized the first time it is used.
     */
    private Ellipse2D iris = null;
    /**
     * An Ellipse2D object used to render Rambley's pupils. This is initially 
     * null and is initialized the first time it is used.
     */
    private Ellipse2D pupil = null;
    /**
     * An Ellipse2D object used to render Rambley's snout. This is initially 
     * null and is initialized the first time it is used.
     */
    private Ellipse2D snout = null;
    /**
     * A Path2D object used to render the path of Rambley's mouth. This is 
     * initially null and is initialized the first time it is used.
     */
    private Path2D mouthPath = null;
    
    // Generic scratch shape objects
    
    /**
     * A scratch Rectangle2D object used for rendering Rambley. This is 
     * initialized the first time it is used.
     */
    private Rectangle2D rect = null;
    /**
     * A scratch Ellipse2D object used for rendering the Rambley. This is 
     * initialized the first time it is used.
     */
    private Ellipse2D ellipse1 = null;
    /**
     * A second scratch Ellipse2D object used for rendering the Rambley. This is 
     * initialized the first time it is used.
     */
    private Ellipse2D ellipse2 = null;
    /**
     * A third scratch Ellipse2D object used for rendering the Rambley. This is 
     * initialized the first time it is used.
     */
    private Ellipse2D ellipse3 = null;
    /**
     * A scratch Path2D object used for rendering Rambley. This is initialized 
     * the first time it is used.
     */
    private Path2D path = null;
    /**
     * A scratch Point2D object used for rendering Rambley. This is initialized 
     * the first time it is used.
     */
    private Point2D point1 = null;
    /**
     * A second scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used.
     */
    private Point2D point2 = null;
    /**
     * A third scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used.
     */
    private Point2D point3 = null;
    /**
     * A fourth scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used.
     */
    private Point2D point4 = null;
    /**
     * A fifth scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used.
     */
    private Point2D point5 = null;
    /**
     * A sixth scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used.
     */
    private Point2D point6 = null;
    /**
     * A seventh scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used.
     */
    private Point2D point7 = null;
    /**
     * A eighth scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used.
     */
    private Point2D point8 = null;
    
    
    
    /**
     * This constructs a {@code RambleyPainter} object with the default 
     * settings.
     * 
     * @todo Describe the default settings.
     */
    public RambleyPainter(){
        flags = DEFAULT_FLAG_SETTINGS;
        dotSize = DEFAULT_BACKGROUND_DOT_SIZE;
        dotSpacing = DEFAULT_BACKGROUND_DOT_SPACING;
        lineSpacing = DEFAULT_PIXEL_GRID_LINE_SPACING;
        eyeRightX = eyeRightY = eyeLeftX = eyeLeftY = 0.5;
        mouthOpenWidth = 1.0;
        mouthOpenHeight = 0.0;
        changeSupport = new PropertyChangeSupport(this);
    }
    /**
     * This constructs a {@code RambleyPainter} object with the given flags.
     * 
     * @todo Describe the default settings for this constructor, and describe 
     * the limit for the flags when added.
     * 
     * @param flags The flags for this {@code RambleyPainter}.
     */
    public RambleyPainter(int flags){
        this();
        RambleyPainter.this.setFlags(flags);
    }
    /**
     * This returns an integer storing the flags used to store the settings for 
     * this painter and control its state.
     * 
     * @todo Add references to other related methods.
     * 
     * @return An integer containing the flags for this painter.
     * @see #getFlag
     * @see #setFlag
     * @see #toggleFlag
     */
    public int getFlags(){
        return flags;
    }
    /**
     * This sets an integer storing the flags used to store the settings for 
     * this painter and control its state.
     * 
     * @todo Add references to other related methods. Add a limit for the flags, 
     * and add a description for the limits for the flags if and when that is 
     * added. Either remove the firing of a state change or mention it in the 
     * documentation.
     * 
     * @param flags The flags for this painter.
     * @return This {@code RambleyPainter}.
     */
    public RambleyPainter setFlags(int flags){
            // If the flags would change
        if (flags != this.flags){
                // This gets the flags that will be changed. The old and new 
                // flags are XOR'd to get the flags that will be changed.
            int changed = this.flags ^ flags;
                // Set the flags
            this.flags = flags;
                // Go through the flags that have a property name assigned to them
            for (Integer flag : FLAG_PROPERTY_NAMES_MAP.navigableKeySet()){
                    // If the flag is somehow null
                if (flag == null)
                    continue;   // Skip this flag
                    // If the current flag is one of the flags that changed
                if ((changed & flag) == flag)
                    firePropertyChange(FLAG_PROPERTY_NAMES_MAP.get(flag),
                            getFlag(flag));
                    // If this is the last bit that changed in the flags
                if (Integer.highestOneBit(flag) >= Integer.highestOneBit(changed))
                    break;
            }
                // Is a state change still necessary?
            fireStateChanged();
        }
        return this;
    }
    /**
     * This returns whether the given flag is set for this painter.
     * 
     * @todo Add references to other related methods.
     * 
     * @param flag The flag to check for.
     * @return Whether the given flag is set.
     * @see #getFlags
     * @see #setFlag
     * @see #toggleFlag
     */
    public boolean getFlag(int flag){
        return (flags & flag) == flag;
    }
    /**
     * This sets whether the given flag is set for this painter based off the 
     * given value.
     * 
     * @todo Add references to other related methods. Add a description for the 
     * limits for the flags if and when that is added.
     * 
     * @param flag The flag to be set or cleared based off {@code value}.
     * @param value Whether the flag should be set or cleared.
     * @return This {@code RambleyPainter}.
     * @see #getFlags 
     * @see #getFlag 
     * @see #toggleFlag 
     */
    public RambleyPainter setFlag(int flag, boolean value){
            // If the flag is to be set, OR the flags with the flag. Otherwise, 
            // AND the flags with the inverse of the flag.
        return setFlags((value) ? flags | flag : flags & ~flag);
    }
    /**
     * This toggles whether the given flag is set for this painter.
     * 
     * @todo Add references to other related methods. Add a description for the 
     * limits for the flags if and when that is added.
     * 
     * @param flag The flag to be toggled.
     * @return This {@code RambleyPainter}.
     * @see #getFlags 
     * @see #getFlag 
     * @see #setFlag 
     */
    public RambleyPainter toggleFlag(int flag){
        return setFlags(flags ^ flag);
    }
    /**
     * This returns whether the background will be painted by this {@code 
     * RambleyPainter}. If this is {@code true}, then a background reminiscent 
     * of the one seen on Rambley's screens in Indigo Park will be painted 
     * behind Rambley. The background will consist of {@link 
     * BACKGROUND_DOT_COLOR dark blue} diamond-shaped or circle-shaped polka 
     * dots over a gradient going from {@link BACKGROUND_GRADIENT_COLOR dark 
     * blue} at the top to {@link BACKGROUND_COLOR light blue} at the bottom. 
     * The default value for this is {@code true}.
     * 
     * @todo Add references to other related methods.
     * 
     * @return Whether the background will be painted.
     * @see #PAINT_BACKGROUND_FLAG
     * @see #getFlag 
     * @see #setBackgroundPainted
     */
    public boolean isBackgroundPainted(){
        return getFlag(PAINT_BACKGROUND_FLAG);
    }
    /**
     * This sets whether the background will be painted by this {@code 
     * RambleyPainter}. If this is {@code true}, then a background reminiscent 
     * of the one seen on Rambley's screens in Indigo Park will be painted 
     * behind Rambley. The default value for this is {@code true}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param enabled Whether the background should be painted.
     * @return This {@code RambleyPainter}.
     * @see #PAINT_BACKGROUND_FLAG
     * @see #setFlag 
     * @see #isBackgroundPainted
     */
    public RambleyPainter setBackgroundPainted(boolean enabled){
        return setFlag(PAINT_BACKGROUND_FLAG,enabled);
    }
    /**
     * This returns whether the pixel grid effect will be painted by this {@code 
     * RambleyPainter}. If this is {@code true}, then an overlay reminiscent of 
     * the pixel grid effect seen on Rambley's screens in Indigo Park will be 
     * painted over Rambley. The default value for this is {@code true}.
     * 
     * @todo Add references to other related methods.
     * 
     * @return Whether the pixel grid effect will be painted.
     * @see #PAINT_PIXEL_GRID_FLAG
     * @see #getFlag 
     * @see #setPixelGridPainted 
     */
    public boolean isPixelGridPainted(){
        return getFlag(PAINT_PIXEL_GRID_FLAG);
    }
    /**
     * This sets whether the pixel grid effect will be painted by this {@code 
     * RambleyPainter}. If this is {@code true}, then an overlay reminiscent of 
     * the pixel grid effect seen on Rambley's screens in Indigo Park will be 
     * painted over Rambley. The default value for this is {@code true}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param enabled Whether the pixel grid effect should be painted.
     * @return This {@code RambleyPainter}.
     * @see #PAINT_PIXEL_GRID_FLAG
     * @see #setFlag 
     * @see #isPixelGridPainted 
     */
    public RambleyPainter setPixelGridPainted(boolean enabled){
        return setFlag(PAINT_PIXEL_GRID_FLAG,enabled);
    }
    /**
     * This returns whether this {@code RambleyPainter} will paint evil Rambley 
     * instead of normal Rambley. Evil Rambley is a version of Rambley the 
     * Raccoon with red eyes that first appeared on thumbnails of videos from 
     * the YouTube channel GameTheory on the topic of Indigo Park. If this is 
     * {@code true}, then Rambley's irises will be red instead of indigo, and if 
     * his mouth is open, then he will have two fangs (one on either side of his 
     * mouth) instead of one. The default value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @return Whether this will paint evil Rambley ({@code true} for evil 
     * Rambley, {@code false} for normal Rambley).
     * @see #EVIL_RAMBLEY_FLAG
     * @see #getFlag 
     * @see #setRambleyEvil 
     */
    public boolean isRambleyEvil(){
        return getFlag(EVIL_RAMBLEY_FLAG);
    }
    /**
     * This sets whether this {@code RambleyPainter} will paint evil Rambley 
     * instead of normal Rambley. Evil Rambley is a version of Rambley the 
     * Raccoon with red eyes that first appeared on thumbnails of videos from 
     * the YouTube channel GameTheory on the topic of Indigo Park. If this is 
     * {@code true}, then Rambley's irises will be red instead of indigo, and if 
     * his mouth is open, then he will have two fangs (one on either side of his 
     * mouth) instead of one. The default value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param value Whether this should paint evil Rambley ({@code true} for 
     * evil Rambley, {@code false} for normal Rambley).
     * @return This {@code RambleyPainter}.
     * @see #EVIL_RAMBLEY_FLAG
     * @see #setFlag 
     * @see #isRambleyEvil 
     */
    public RambleyPainter setRambleyEvil(boolean value){
        return setFlag(EVIL_RAMBLEY_FLAG,value);
    }
    /**
     * This returns whether Rambley's aspect ratio will be ignored when this 
     * {@code RambleyPainter} is painting Rambley. If this is {@code true}, then 
     * Rambley will be stretched to fill the area to be painted by the {@link 
     * #paint paint} method. In other words, if this is {@code true}, then 
     * Rambley will be rendered in the aspect ratio of the area to be painted. 
     * If this is {@code false}, then Rambley will be rendered in the 
     * appropriate aspect ratio and centered within the area to be painted. The 
     * default value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @return Whether Rambley's aspect ratio will be ignored.
     * @see IGNORE_ASPECT_RATIO_FLAG
     * @see #getFlag 
     * @see #setAspectRatioIgnored
     */
    public boolean isAspectRatioIgnored(){
        return getFlag(IGNORE_ASPECT_RATIO_FLAG);
    }
    /**
     * This sets whether Rambley's aspect ratio will be ignored when this {@code 
     * RambleyPainter} is painting Rambley. If this is {@code true}, then 
     * Rambley will be stretched to fill the area to be painted by the {@link 
     * #paint paint} method. In other words, if this is {@code true}, then 
     * Rambley will be rendered in the aspect ratio of the area to be painted. 
     * If this is {@code false}, then Rambley will be rendered in the 
     * appropriate aspect ratio and centered within the area to be painted. The 
     * default value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param value Whether Rambley's aspect ratio should be ignored.
     * @return This {@code RambleyPainter}.
     * @see IGNORE_ASPECT_RATIO_FLAG
     * @see #setFlag 
     * @see #isAspectRatioIgnored
     */
    public RambleyPainter setAspectRatioIgnored(boolean value){
        return setFlag(IGNORE_ASPECT_RATIO_FLAG,value);
    }
    /**
     * This returns whether the border around Rambley and Rambley's shadow will 
     * be painted by this {@code RambleyPainter}. The default value for this is 
     * {@code true}.
     * 
     * @todo Finalize the name for the flag and make it public instead of 
     * protected. Add references to other related methods.
     * 
     * @return Whether the border around Rambley and Rambley's shadow will be 
     * painted.
     * @see PAINT_BORDER_AND_SHADOW_FLAG
     * @see #getFlag 
     * @see setBorderAndShadowPainted
     */
    protected boolean isBorderAndShadowPainted(){
        return getFlag(PAINT_BORDER_AND_SHADOW_FLAG);
    }
    /**
     * This sets whether the border around Rambley and Rambley's shadow will be 
     * painted by this {@code RambleyPainter}. The default value for this is 
     * {@code true}.
     * 
     * @todo Finalize the name for the flag and make it public instead of 
     * protected. Add references to other related methods.
     * 
     * @param enabled Whether the border around Rambley and Rambley's shadow 
     * should be painted.
     * @return This {@code RambleyPainter}.
     * @see PAINT_BORDER_AND_SHADOW_FLAG
     * @see #setFlag 
     * @see isBorderAndShadowPainted
     */
    protected RambleyPainter setBorderAndShadowPainted(boolean enabled){
        return setFlag(PAINT_BORDER_AND_SHADOW_FLAG,enabled);
    }
    /**
     * This returns whether the polka dots in the background will be circles or 
     * diamonds. If this is {@code true}, then the background polka dots will be 
     * circles. If this is {@code false} then the background polka dots will be 
     * diamonds. The default value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @return {@code true} if the background polka dots are circles, {@code 
     * false} if the background polka dots are diamonds.
     * @see CIRCULAR_BACKGROUND_DOTS_FLAG
     * @see #getFlag 
     * @see #setCircularBackgroundDots
     */
    public boolean getCircularBackgroundDots(){
        return getFlag(CIRCULAR_BACKGROUND_DOTS_FLAG);
    }
    /**
     * This sets whether the polka dots in the background will be circles or 
     * diamonds. If this is {@code true}, then the background polka dots will be 
     * circles. If this is {@code false} then the background polka dots will be 
     * diamonds. The default value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param value {@code true} if the background polka dots should be circles, 
     * {@code false} if the background polka dots should be diamonds.
     * @return This {@code RambleyPainter}.
     * @see CIRCULAR_BACKGROUND_DOTS_FLAG
     * @see #setFlag 
     * @see #getCircularBackgroundDots
     */
    public RambleyPainter setCircularBackgroundDots(boolean value){
        return setFlag(CIRCULAR_BACKGROUND_DOTS_FLAG,value);
    }
    /**
     * This returns whether Rambley's fang is on the left or right of his mouth. 
     * When this is {@code true}, Rambley's fang shows on the left side of his 
     * mouth. When this is {@code false}, Rambley's fang shows on the right side 
     * of his mouth. This has no effect when Rambley's mouth is closed, nor does 
     * it have any effect when Rambley is {@link #isRambleyEvil() evil}. The 
     * latter is because Evil Rambley has fangs on both sides of his mouth. The 
     * default value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @return {@true} if Rambley's fang is on the left side of his mouth, 
     * {@code false} if Rambley's fang is on the right side of his mouth.
     * @see RAMBLEY_FANG_SIDE_FLAG
     * @see #getFlag 
     * @see setRambleyFangOnLeft
     */
    public boolean isRambleyFangOnLeft(){
        return getFlag(RAMBLEY_FANG_SIDE_FLAG);
    }
    /**
     * This sets whether Rambley's fang is on the left or right of his mouth. 
     * When this is {@code true}, Rambley's fang shows on the left side of his 
     * mouth. When this is {@code false}, Rambley's fang shows on the right side 
     * of his mouth. This has no effect when Rambley's mouth is closed, nor does 
     * it have any effect when Rambley is {@link #isRambleyEvil() evil}. The 
     * latter is because Evil Rambley has fangs on both sides of his mouth. The 
     * default value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param value {@true} if Rambley's fang is on the left side of his mouth, 
     * {@code false} if Rambley's fang is on the right side of his mouth.
     * @return This {@code RambleyPainter}.
     * @see RAMBLEY_FANG_SIDE_FLAG
     * @see setFlag 
     * @see isRambleyFangOnLeft
     */
    public RambleyPainter setRambleyFangOnLeft(boolean value){
        return setFlag(RAMBLEY_FANG_SIDE_FLAG,value);
    }
    /**
     * This returns whether Rambley's jaw is closed when his mouth is opened. 
     * This does not have any effect when Rambley's mouth is closed. The default 
     * value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @return Whether Rambley's jaw is closed.
     * @see RAMBLEY_JAW_CLOSED_FLAG
     * @see #getFlag 
     * @see setRambleyJawClosed
     */
    public boolean isRambleyJawClosed(){
        return getFlag(RAMBLEY_JAW_CLOSED_FLAG);
    }
    /**
     * This sets whether Rambley's jaw is closed when his mouth is opened. This 
     * does not have any effect when Rambley's mouth is closed. The default 
     * value for this is {@code false}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param value Whether Rambley's jaw is closed.
     * @return This {@code RambleyPainter}.
     * @see RAMBLEY_JAW_CLOSED_FLAG
     * @see #setFlag 
     * @see isRambleyJawClosed
     */
    public RambleyPainter setRambleyJawClosed(boolean value){
        return setFlag(RAMBLEY_JAW_CLOSED_FLAG,value);
    }
    
    
    
    /**
     * This returns the width and height used for the background polka dots.
     * 
     * @todo Add references to other related methods.
     * 
     * @return The size of the background polka dots.
     */
    public double getBackgroundDotSize(){
        return dotSize;
    }
    /**
     * This sets the width and height used for the background polka dots. 
     * 
     * @todo Add references to other related methods.
     * 
     * @param size The size for the background polka dots. 
     * @return This {@code RambleyPainter}.
     */
    public RambleyPainter setBackgroundDotSize(double size){
            // If the new size is different from the old size
        if (size != dotSize){
                // Get the old size
            double old = dotSize;
            dotSize = size;
            firePropertyChange(BACKGROUND_DOT_SIZE_PROPERTY_CHANGED,old,size);
        }
        return this;
    }
    /**
     * This returns the diagonal spacing between the centers of the background 
     * polka dots. That is to say, the center of each background polka dot is 
     * {@code getBackgroundDotSpacing()} pixels to the left and {@code 
     * getBackgroundDotSpacing()} pixels below the center of the previous 
     * background polka dot.
     * 
     * @todo Rework the documentation for this method. Add references to other 
     * related methods.
     * 
     * @return The diagonal spacing between the background polka dots.
     */
    public double getBackgroundDotSpacing(){
        return dotSpacing;
    }
    /**
     * This sets the diagonal spacing between the centers of the background 
     * polka dots. That is to say, the center of each background polka dot will  
     * be {@code getBackgroundDotSpacing()} pixels to the left and {@code 
     * getBackgroundDotSpacing()} pixels below the center of the previous 
     * background polka dot.
     * 
     * @todo Rework the documentation for this method. Add references to other 
     * related methods.
     * 
     * @param spacing The diagonal spacing between the background polka dots.
     * @return This {@code RambleyPainter}.
     */
    public RambleyPainter setBackgroundDotSpacing(double spacing){
            // If the new spacing is different from the old spacing
        if (spacing != dotSpacing){
                // Get the old dot spacing
            double old = dotSpacing;
            dotSpacing = spacing;
            firePropertyChange(BACKGROUND_DOT_SPACING_PROPERTY_CHANGED,old,spacing);
        }
        return this;
    }
    /**
     * This returns the spacing between the lines in the pixel grid. For the 
     * vertical lines, this is the horizontal spacing. For the horizontal lines, 
     * this is the vertical spacing.
     * 
     * @todo Add references to other related methods.
     * 
     * @return The spacing between the lines in the pixel grid.
     */
    public double getPixelGridLineSpacing(){
        return lineSpacing;
    }
    /**
     * This sets the spacing between the lines in the pixel grid. For the 
     * vertical lines, this is the horizontal spacing. For the horizontal lines, 
     * this is the vertical spacing.
     * 
     * @todo Add references to other related methods.
     * 
     * @param spacing The spacing between the lines in the pixel grid.
     * @return This {@code RambleyPainter}.
     */
    public RambleyPainter setPixelGridLineSpacing(double spacing){
            // If the new spacing is different from the old spacing
        if (spacing != lineSpacing){
                // Get the old line spacing
            double old = lineSpacing;
            lineSpacing = spacing;
            firePropertyChange(PIXEL_GRID_LINE_SPACING_PROPERTY_CHANGED,old,spacing);
        }
        return this;
    }
    /**
     * 
     * @return 
     */
    public double getRambleyRightEyeX(){
        return eyeRightX;
    }
    /**
     * 
     * @return 
     */
    public double getRambleyRightEyeY(){
        return eyeRightY;
    }
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public RambleyPainter setRambleyRightEye(double x, double y){
            // If the x position or the y position has changed
        if (x != eyeRightX || y != eyeRightY){
            eyeRightX = x;
            eyeRightY = y;
                // Fire a change in the state
            fireStateChanged();
        }
        return this;
    }
    /**
     * 
     * @return 
     */
    public double getRambleyLeftEyeX(){
        return eyeLeftX;
    }
    /**
     * 
     * @return 
     */
    public double getRambleyLeftEyeY(){
        return eyeLeftY;
    }
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public RambleyPainter setRambleyLeftEye(double x, double y){
            // If the x position or the y position has changed
        if (x != eyeLeftX || y != eyeLeftY){
            eyeLeftX = x;
            eyeLeftY = y;
                // Fire a change in the state
            fireStateChanged();
        }
        return this;
    }
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public RambleyPainter setRambleyEyes(double x, double y){
            // Set the position for both the right and left eyes
        return setRambleyRightEye(x, y).setRambleyLeftEye(x, y);
    }
    /**
     * 
     * @return 
     */
    public double getRambleyOpenMouthWidth(){
        return mouthOpenWidth;
    }
    /**
     * 
     * @param width
     * @return 
     */
    public RambleyPainter setRambleyOpenMouthWidth(double width){
            // If the given width is less than zero or greater than 1
        if (width < 0.0 || width > 1.0)
            throw new IllegalArgumentException("Open mouth width must be "
                    + "between 0 and 1, inclusive (0.0 <= "+width+" <= 1.0)");
            // If the width value would change
        if (width != mouthOpenWidth){
                // Get the old width value
            double old = mouthOpenWidth;
            mouthOpenWidth = width;
            firePropertyChange(RAMBLEY_OPEN_MOUTH_WIDTH_PROPERTY_CHANGED,old,width);
        }
        return this;
    }
    /**
     * 
     * @return 
     */
    public double getRambleyOpenMouthHeight(){
        return mouthOpenHeight;
    }
    /**
     * 
     * @param height
     * @return 
     */
    public RambleyPainter setRambleyOpenMouthHeight(double height){
            // If the given height is less than zero or greater than 1
        if (height < 0.0 || height > 1.0)
            throw new IllegalArgumentException("Open mouth height must be "
                    + "between 0 and 1, inclusive (0.0 <= "+height+" <= 1.0)");
            // If the height value would change
        if (height != mouthOpenHeight){
                // Get the old height value
            double old = mouthOpenHeight;
            mouthOpenHeight = height;
            firePropertyChange(RAMBLEY_OPEN_MOUTH_HEIGHT_PROPERTY_CHANGED,old,height);
        }
        return this;
    }
    
    
    
    /**
     * This returns the gradient to use to paint the background gradient.
     * @param x The x-coordinate of the top-left corner of the area to fill.
     * @param y The y-coordinate of the top-left corner of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     * @return The gradient to use to paint the background gradient.
     */
    protected Paint getBackgroundGradient(double x,double y,double w,double h){
            // Get the center x-coordinate
        float x1 = (float)((w / 2.0)+x);
            // Create a vertical gradient that fades from the background 
            // gradient color to transparency over the area to fill
        return new GradientPaint(x1,(float)y,BACKGROUND_GRADIENT_COLOR,
                x1,(float)(y+h-1),BACKGROUND_GRADIENT_COLOR_2);
    }
    /**
     * 
     * @param size
     * @return 
     */
    protected double getBackgroundDotOffset(double size){
        return (size%getBackgroundDotSpacing())/2.0;
    }
    /**
     * 
     * @param width
     * @return 
     */
    protected double getBackgroundDotOffsetX(double width){
        return getBackgroundDotOffset(width);
    }
    /**
     * 
     * @param height
     * @return 
     */
    protected double getBackgroundDotOffsetY(double height){
        return getBackgroundDotOffset(height);
    }
    /**
     * 
     * @param bounds A rectangle outlining the bounds for the background polka 
     * dot to return.
     * @param path A Path2D object to store the results in, or null.
     * @param ellipse An Ellipse2D object to store the results in, or null.
     * @return The shape object to use to draw a background polka dot.
     */
    protected Shape getBackgroundDot(RectangularShape bounds, Path2D path, 
            Ellipse2D ellipse){
            // If the background dots should be circular
        if (getCircularBackgroundDots()){
                // If the given Ellipse2D object is null
            if (ellipse == null)
                ellipse = new Ellipse2D.Double();
                // Set the frame of the ellipse to be the frame of the rectangle
            ellipse.setFrame(bounds.getX(), bounds.getY(), bounds.getWidth(), 
                    bounds.getHeight());
                // Return the ellipse
            return ellipse;
        }
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // Move to the top center point of the dot
        path.moveTo(bounds.getCenterX(), bounds.getMinY());
            // Line to the center left point
        path.lineTo(bounds.getMinX(), bounds.getCenterY());
            // Line to the bottom center point
        path.lineTo(bounds.getCenterX(), bounds.getMaxY());
            // Line to the center right point
        path.lineTo(bounds.getMaxX(), bounds.getCenterY());
            // Close the path
        path.closePath();
        return path;
    }
    /**
     * 
     * @param x The x-coordinate for the center of the background polka dot.
     * @param y The y-coordinate for the center of the background polka dot.
     * @param path A Path2D object to store the results in, or null.
     * @param ellipse An Ellipse2D object to store the results in, or null.
     * @param rect A Rectangle2D object to temporarily store the bounds for the 
     * background polka dot in, or null.
     * @return The shape object to use to draw a background polka dot.
     */
    protected Shape getBackgroundDot(double x, double y, Path2D path, 
            Ellipse2D ellipse, Rectangle2D rect){
            // If the given Rectangle2D object is null
        if (rect == null)
            rect = new Rectangle2D.Double();
            // Set the frame of the rectangle from the center to be the size of 
            // a background polka dot.
        rect.setFrameFromCenter(x, y, x-getBackgroundDotSize()/2.0, 
                y-getBackgroundDotSize()/2.0);
        return getBackgroundDot(rect,path,ellipse);
    }
    /**
     * 
     * @param size
     * @return 
     */
    protected double getPixelGridOffset(double size){
        return ((size-1)%getPixelGridLineSpacing())/2.0;
    }
    /**
     * 
     * @param x The x-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param y The y-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param w The width of the area to fill with the pixel grid effect.
     * @param h The height of the area to fill with the pixel grid effect.
     * @param path A Path2D object to store the results in, or null.
     * @return The Path2D object to use to render the pixel grid effect.
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
        for (double y1 = getPixelGridOffset(h); y1 <= h; 
                y1+=getPixelGridLineSpacing()){
            path.moveTo(x, y1+y);
            path.lineTo(x2, y1+y);
        }
            // Go through and generate the horizontal lines, starting at the 
            // offset for the x-coordinate of the pixel grid and spacing them 
            // out by the pixel grid spacing
        for (double x1 = getPixelGridOffset(w); x1 <= w; 
                x1+=getPixelGridLineSpacing()){
            path.moveTo(x1+x, y);
            path.lineTo(x1+x, y2);
        }
        return path;
    }
    /**
     * This constructs a BasicStroke object to use when rendering Rambley
     * @param width The line width
     * @return 
     */
    protected BasicStroke getRambleyStroke(float width){
        return new BasicStroke(width,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    }
    /**
     * This returns a BasicStroke object to use for rendering most of Rambley. 
     * This stroke is mainly used when filling in shapes. This stroke has a line 
     * width of 1.0.
     * @return The normal stroke used for drawing Rambley.
     * @see #getRambleyStroke
     * @see getRambleyDetailStroke
     * @see getRambleyOutlineStroke
     */
    protected BasicStroke getRambleyNormalStroke(){
            // If the normal stroke for Rambley has not been initialized yet
        if (normalStroke == null)
            normalStroke = getRambleyStroke(1.0f);
        return normalStroke;
    }
    /**
     * This returns a BasicStroke object to use for rendering the details and 
     * finer outlines for Rambley. This stroke has a line width of 2.0.
     * @return The details stroke used for drawing Rambley.
     * @see #getRambleyStroke
     * @see getRambleyNormalStroke
     * @see getRambleyOutlineStroke
     */
    protected BasicStroke getRambleyDetailStroke(){
            // If the detail stroke for Rambley has not been initialized yet
        if (detailStroke == null)
            detailStroke = getRambleyStroke(2.0f);
        return detailStroke;
    }
    /**
     * This returns a BasicStroke object to use for rendering most of the 
     * outlines for Rambley. This stroke has a line width of 3.0.
     * @return The outline stroke used for drawing Rambley.
     * @see #getRambleyStroke
     * @see getRambleyNormalStroke
     * @see getRambleyDetailStroke
     */
    protected BasicStroke getRambleyOutlineStroke(){
            // If the outline stroke for Rambley has not been initialized yet
        if (outlineStroke == null)
            outlineStroke = getRambleyStroke(3.0f);
        return outlineStroke;
    }
    /**
     * This returns an AffineTransform object that flips shapes horizontally and 
     * translates it by {@code dx}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param dx The dx value by which to translate stuff, relative to the 
     * original coordinate space.
     * @return An AffineTransform used to flip things horizontally.
     */
    protected AffineTransform getHorizontalFlipTransform(double dx){
            // Get a transform that will flip things horizontally
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            // Translate everything by dx
        tx.translate(-dx, 0);
        return tx;
    }
    /**
     * This returns an AffineTransform to use to flip shapes horizontally when 
     * painting Rambley.
     * 
     * @todo Add references to other related methods.
     * 
     * @return The AffineTransform used to flip things horizontally.
     */
    protected AffineTransform getRambleyHorizontalFlipTransform(){
            // If the horizontal flip transform has not been initialized yet
        if (horizFlip == null){
                // Get a transform that will flip things horizontally
            horizFlip = getHorizontalFlipTransform(INTERNAL_RENDER_HEIGHT);
        }
        return horizFlip;
    }
    /**
     * This flips the given area horizontally.
     * 
     * @todo Add references to other related methods.
     * 
     * @param area The area to flip.
     * @return The horizontally flipped area.
     * @see getRambleyHorizontalFlipTransform
     * @see Area#createTransformedArea
     */
    protected Area createHorizontallyFlippedArea(Area area){
        return area.createTransformedArea(getRambleyHorizontalFlipTransform());
    }
    /**
     * This flips the given path horizontally over the vertical line at the 
     * given x-coordinate and adds the flipped path back to the given path.
     * 
     * @todo Add references to other related methods.
     * 
     * @param path The Path2D object to flip horizontally and add to.
     * @param x The x-coordinate of the vertical line to flip the path over.
     * @return The given Path2D object, now with the horizontally flipped 
     * version of it added to it.
     */
    protected Path2D mirrorPathHorizontally(Path2D path, double x){
            // Flip the path horizontally and translate it by twice the given 
            // x-coordinate in order to put where it where it should be when 
            // mirrored.
        path.append(path.createTransformedShape(getHorizontalFlipTransform(x*2)), 
                false);
        return path;
    }
    
    
    
    /**
     * 
     * @param g {@inheritDoc }
     * @param c
     * @param width {@inheritDoc }
     * @param height {@inheritDoc }
     */
    @Override
    public void paint(Graphics2D g, Component c, int width, int height) {
            // Check if the graphics context is null
        Objects.requireNonNull(g);
            // If either the width or height are less than or equal to zero 
            // (nothing would be rendered anyway)
        if (width <= 0 || height <= 0)
            return;
            // Create a copy of the given graphics context and configure it to 
            // render Rambley and the other stuff
        g = configureGraphics((Graphics2D)g.create());
            // If the background is to be painted
        if (isBackgroundPainted()){
                // Paint the background
            paintBackground(g,0,0,width,height);
        }
            // Paint Rambley
        paintRambley(g,0,0,width,height);
            // If the pixel grid effect is to be painted
        if (isPixelGridPainted()){
                // Paint the pixel grid effect
            paintPixelGrid(g,0,0,width,height);
        }
        g.dispose();
    }
    /**
     * This is used to configure the graphics context used to render Rambley. 
     * It's assumed that the returned graphics context is the same as the given 
     * graphics context, or at least that the returned graphics context 
     * references the given graphics context in some way. 
     * 
     * @todo Add references to other related methods.
     * 
     * @param g The graphics context to render to.
     * @return The given graphics context, now configured for rendering Rambley.
     * @see #paint 
     */
    protected Graphics2D configureGraphics(Graphics2D g){
            // Enable antialiasing
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
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
            // Set the stroke normalization to be pure, i.e. geometry should be 
            // left unmodified
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, 
                RenderingHints.VALUE_STROKE_PURE);
        return g;
    }
    /**
     * 
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill.
     * @param y The y-coordinate of the top-left corner of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     */
    protected void paintBackground(Graphics2D g, int x, int y, int w, int h){
            // Create a copy of the given graphics context
        g = (Graphics2D) g.create();
            // Start by rendering a solid color background
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(x, y, w, h);
            // Paint the background dots
        paintBackgroundDots(g,x,y,w,h);
            // Render the background gradient
        g.setPaint(getBackgroundGradient(x,y,w,h));
        g.fillRect(x, y, w, h);
        g.dispose();
    }
    /**
     * 
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill.
     * @param y The y-coordinate of the top-left corner of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     */
    protected void paintBackgroundDots(Graphics2D g, int x, int y, int w, int h){
            // Create a copy of the given graphics context over the given area
        g = (Graphics2D) g.create(x, y, w, h);
            // Set the color to the background polka dot color
        g.setColor(BACKGROUND_DOT_COLOR);
            // If the scratch Rectangle2D object has not been initialized yet
        if (rect == null)
            rect = new Rectangle2D.Double();
            // If the first Ellipse2D scratch object has not been initialized 
        if (ellipse1 == null)   // yet
            ellipse1 = new Ellipse2D.Double();
            // If the scratch Path2D object has not been initialized yet
        if (path == null)
            path = new Path2D.Double();
            // Get the x offset for the background polka dots
        double x1 = getBackgroundDotOffsetX(w);
            // Get the y offset for the background polka dots
        double y1 = getBackgroundDotOffsetY(h);
            // Go through the multipliers for the y-coordinates for the centers 
            // of the background polka dots (to create the polka dot pattern, 
            // we need to know what row number we are on, so we can offset the 
            // x-coordinates accordingly)
        for (int i = 0; (i * getBackgroundDotSpacing()) <= h; i++){
                // Get the y-coordinate for the centers of the polka dots on 
                // this row
            double yDot = (i * getBackgroundDotSpacing())+y1;
                // Go through the x-coordinates for the centers of the 
                // background polka dots (polka dots on odd rows are offset 
                // compared to the polka dots on even rows)
            for (double xDot = getBackgroundDotSpacing() * (i % 2); xDot <= w; 
                    xDot+=getBackgroundDotSpacing()+getBackgroundDotSpacing()){
                    // Fill the current background polka dot
                g.fill(getBackgroundDot(xDot+x1,yDot,path,ellipse1,rect));
            }
        }
        g.dispose();
    }
    /**
     * 
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param y The y-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param w The width of the area to fill with the pixel grid effect.
     * @param h The height of the area to fill with the pixel grid effect.
     * @param mask An optional mask for the pixel grid effect, or null.
     */
    protected void paintPixelGrid(Graphics2D g, int x, int y, int w, int h, Shape mask){
            // Create a copy of the given graphics context over the given area
        g = (Graphics2D) g.create(x, y, w, h);
            // Set the color to the pixel grid color
        g.setColor(PIXEL_GRID_COLOR);
            // Turn off antialiasing for the pixel grid
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
            // If a mask has been provided for the pixel grid
//        if (mask != null)
//                // Clip the graphics context to only render within the given mask
//            g.clip(mask);
            // Generate the pixel grid
        pixelGrid = getPixelGrid(0,0,w,h,pixelGrid);
            // Render the pixel grid
        g.draw(pixelGrid);
        g.dispose();
    }
    /**
     * 
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param y The y-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param w The width of the area to fill with the pixel grid effect.
     * @param h The height of the area to fill with the pixel grid effect.
     */
    protected void paintPixelGrid(Graphics2D g, int x, int y, int w, int h){
        paintPixelGrid(g,x,y,w,h,null);
    }
    /**
     * This creates and returns an Area that forms the base shape of Rambley's 
     * head without his ears.
     * 
     * @todo Add references to other related methods.
     * 
     * @param rect A Rectangle2D object to use to store the mask for the lower 
     * half of the cheeks, or null.
     * @param path A Path2D object to use to store the mask for the cheeks, or 
     * null.
     * @param ellipse1 An Ellipse2D object to use to store the top of Rambley's 
     * head, or null.
     * @param ellipse2 An Ellipse2D object to use to store Rambley's cheeks, or 
     * null.
     * @return The area of Rambley's head without his ears.
     */
    private Area createRambleyHeadArea(Rectangle2D rect, Path2D path, 
            Ellipse2D ellipse1, Ellipse2D ellipse2){
            // If the given Rectangle is null
        if (rect == null)
            rect = new Rectangle2D.Double();
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // If the first of the two ellipses are null
        if (ellipse1 == null)
            ellipse1 = new Ellipse2D.Double();
            // If the second of the two ellipses are null
        if (ellipse2 == null)
            ellipse2 = new Ellipse2D.Double();
            // Set the frame of the rectangle temporarily to be the entire width 
            // of the area being rendered, and with the right y-coordinate and 
            // height
        rect.setFrame(0, RAMBLEY_Y_OFFSET+84, INTERNAL_RENDER_WIDTH, 92);
            // Set the frame of the rectangle again, this time from the center, 
            // to set the x-coordinate and width. This produces a horizontally 
            // centered rectangle that is 84 pixels below the y offset with a 
            // width of 200 and a height of 92. This will form the lower half 
            // of the mask for the cheeks
        rect.setFrameFromCenter(rect.getCenterX(), rect.getCenterY(), 
                rect.getCenterX()-100, rect.getMinY());
            // Append the rectangle to the path
        path.append(rect, false);
            // Line to the top-left corner of the rectangle
        path.lineTo(rect.getMinX(), rect.getMinY());
            // Line to the center x, and to the top of the triangle that forms 
            // the cheeks
        path.lineTo(rect.getCenterX(), rect.getMinY()-RAMBLEY_CHEEK_TRIANGLE_HEIGHT);
            // Line to the top-right corner of the rectangle
        path.lineTo(rect.getMaxX(), rect.getMinY());
            // Close the path to form the mask for the cheeks
        path.closePath();
            // Set the frame of the first ellipse from the center to get a 
            // horizontally centered ellipse with a y-coordinate at the 
            // y-offset,and that is 152 x 176. This will form the top of 
            // Rambley's head
        ellipse1.setFrameFromCenter(rect.getCenterX(), RAMBLEY_Y_OFFSET+88, 
                rect.getMinX()+24, RAMBLEY_Y_OFFSET);
            // Set the frame of the second ellipse from the center to get a 
            // horizontally centered ellipse that is 18 pixels below the first 
            // ellipse, and that is 200 x 116. This will form Rambley's cheeks 
            // when masked using the path.
        ellipse2.setFrameFromCenter(rect.getCenterX(), ellipse1.getCenterY()-12, 
                rect.getMinX(), ellipse1.getMinY()+18);
            // Create the area for the upper part of Rambley head
        Area temp = new Area(ellipse1);
            // Remove the lower half of the ellipse, since it's not needed
        temp.subtract(new Area(rect));
            // Create the shape of Rambley's head, starting with the cheek area
        Area headShape = new Area(ellipse2);
            // Mask off the unused parts of the cheek area using the path
        headShape.intersect(new Area(path));
            // Add the upper part of his head to the lower part
        headShape.add(temp);
        return headShape;
    }
    /**
     * 
     * ln(x)/10ln(2) + 2.3
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param x
     * @return 
     */
    protected double getRambleyUpperEarY(double x){
            // Convert the x-coordinate into the ear equations coordinate system
        x = graphicsToEarEquX(x);
            // If the x-coordinate is less than or equal to zero
        if (x <= 0)
                // Return the graphics coordinate equivalent to zero
            return earEquToGraphicsY(0);
            // y = ln(x)/10ln(2) + 2.3
            // Convert the resulting y-coordinate into the graphics coordinate 
            // system
        return earEquToGraphicsY((Math.log(x)/(10*Math.log(2)))+2.3);
    }
    /**
     * This takes in a y-coordinate for a point on the line that forms the upper 
     * curve for Rambley's right ear, and returns the corresponding x-coordinate 
     * for that point. 
     * 
     * 2^(10y-23)
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param y
     * @return 
     */
    protected double getRambleyUpperEarX(double y){
            // First, convert the y-coordinate into the ear equations coordinate 
            // system
            // x = 2^(10y-23)
            // Convert the resulting x-coordinate into the graphics coordinate 
            // system
        return earEquToGraphicsX(Math.pow(2, 10*graphicsToEarEquY(y)-23));
    }
    /**
     * This returns the function to calculate the y-coordinate for a point on 
     * the line that produces the upper curve for Rambley's right ear. The 
     * function returned is effectively a {@code DoubleUnaryOperator} version of 
     * the {@link getRambleyUpperEarY getRambleyUpperEarY} method.
     * @return The {@link getRambleyUpperEarY getRambleyUpperEarY} method, as a 
     * {@code DoubleUnaryOperator}.
     * @see getRambleyUpperEarY
     * @see getRambleyUpperEarX
     */
    protected DoubleUnaryOperator getRambleyUpperEarEquation(){
            // If the upper ear function has not been initialized yet
        if (earEquationU == null)
            earEquationU = (double operand) -> getRambleyUpperEarY(operand);
        return earEquationU;
    }
    /**
     * -(ln(1.5-x)/ln(2) - 5.2) / 8
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param x
     * @return 
     */
    private static double getRambleyEarOffset(double x){
            // y = -(ln(1.5-x)/ln(2) - 5.2) / 8
        return -((Math.log(1.5-x)/Math.log(2))-5.2)/8;
    }
    /**
     * 
     * -(ln(1.5-x)/ln(2) - 5.2) / 8
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param x
     * @return 
     */
    protected double getRambleyLowerEarY(double x){
            // First, convert the x-coordinate into the ear equations coordinate 
            // system
            // y = -(ln(1.5-x)/ln(2) - 5.2) / 8
            // Convert the resulting y-coordinate into the graphics coordinate 
            // system
        return earEquToGraphicsY(getRambleyEarOffset(graphicsToEarEquX(x)));
    }
    /**
     * -2^(-8y+5.2) + 1.5
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param y
     * @return 
     */
    protected double getRambleyLowerEarX(double y){
            // First, convert the y-coordinate into the ear equations coordinate 
            // system
            // x = -2^(-8y+5.2) + 1.5
            // Convert the resulting x-coordinate into the graphics coordinate 
            // system
        return earEquToGraphicsX(-Math.pow(2, -8*graphicsToEarEquY(y)+5.2)+1.5);
    }
    /**
     * This returns the function to calculate the y-coordinate for a point on 
     * the line that produces the lower curve for Rambley's right ear. The 
     * function returned is effectively a {@code DoubleUnaryOperator} version of 
     * the {@link getRambleyLowerEarY getRambleyLowerEarY} method.
     * @return The {@link getRambleyLowerEarY getRambleyLowerEarY} method, as a 
     * {@code DoubleUnaryOperator}.
     * @see getRambleyLowerEarY
     * @see getRambleyLowerEarX
     */
    protected DoubleUnaryOperator getRambleyLowerEarEquation(){
            // If the lower ear function has not been initialized yet
        if (earEquationL == null)
            earEquationL = (double operand) -> getRambleyLowerEarY(operand);
        return earEquationL;
    }
    /**
     * (0.01/(x-1.5)) + 2.4
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param x
     * @return 
     */
    protected double getRambleyEarTipY(double x){
            // Convert the x-coordinate into the ear equations coordinate system
            // Subtract 1.5 from the resulting x-coordinate
        x = graphicsToEarEquX(x)-1.5;
            // If x is greater than or equal to zero, then y = 0. Otherwise, 
            // y = 0.01 / x
        double y = (x >= 0) ? 0 : (0.01/x);
            // y = y + 2.4
            // Convert the resulting y-coordinate into the graphics coordinate 
            // system
            // Offset the y-coordinate by the tip's y offset
            // Bound it by the ear's height
        return Math.min(earEquToGraphicsY(y + 2.4) + RAMBLEY_EAR_TIP_Y_OFFSET, 
                RAMBLEY_EAR_HEIGHT);
    }
    /**
     * (0.01/(y-2.4)) + 1.5
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param y
     * @return 
     */
    protected double getRambleyEarTipX(double y){
            // Offset the y-coordinate by the tip's y offset
            // Convert the y-coordinate into the ear equations coordinate system
            // Subtract 2.4 from the resulting y-coordinate
        y = graphicsToEarEquY(y - RAMBLEY_EAR_TIP_Y_OFFSET) - 2.4;
            // If the resulting y-coordinate is greater than or equal to zero
        if (y >= 0)
                // Return the graphics coordinate equivalent to zero
            return earEquToGraphicsX(0);
            // x = 0.01/y + 1.5
            // Convert the resulting x-coordinate into the graphics coordinate 
            // system
        return earEquToGraphicsX(Math.max((0.01/y)+1.5, 0));
    }
    /**
     * This returns the function to calculate the y-coordinate for a point on 
     * the line that produces the curve that forms the tip for Rambley's right 
     * ear. The function returned is effectively a {@code DoubleUnaryOperator} 
     * version of the {@link getRambleyEarTipY getRambleyEarTipY} method.
     * @return The {@link getRambleyEarTipY getRambleyEarTipY} method, as a 
     * {@code DoubleUnaryOperator}.
     * @see getRambleyEarTipY
     * @see getRambleyEarTipX
     */
    protected DoubleUnaryOperator getRambleyEarTipEquation(){
            // If the ear tip function has not been initialized yet
        if (earEquationT == null)
            earEquationT = (double operand) -> getRambleyEarTipY(operand);
        return earEquationT;
    }
    /**
     * 
     * @param x The x-coordinate for the top-left corner of the ear.
     * @param y The y-coordinate for the top-left corner of the ear.
     * @param point A Point2D object to store the results in, or null.
     * @return 
     */
    protected Point2D getRambleyEarUpperTip(double x, double y, Point2D point){
        return getLineIntersection(x,y,9,10.5,
                getRambleyUpperEarEquation(),getRambleyEarTipEquation(),point);
    }
    /**
     * 
     * @param x The x-coordinate for the top-left corner of the ear.
     * @param y The y-coordinate for the top-left corner of the ear.
     * @param point A Point2D object to store the results in, or null.
     * @return 
     */
    protected Point2D getRambleyEarLowerTip(double x, double y, Point2D point){
        return getLineIntersection(x,y,getRambleyEarTipX(RAMBLEY_EAR_HEIGHT),1,
                getRambleyEarTipEquation(),getRambleyLowerEarEquation(),point);
    }
    /**
     * This creates and returns an Area that forms the shape of Rambley's right 
     * ear.
     * 
     * @todo Add references to other related methods.
     * 
     * @param x The x-coordinate for the top-left corner of the ear.
     * @param y The y-coordinate for the top-left corner of the ear.
     * @param path A Path2D object to use to construct the ear, or null.
     * @return The area of Rambley's right ear.
     */
    protected Area getRambleyEar(double x, double y, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // Get the maxiumum y-coordinate for the ear
        double y1 = RAMBLEY_EAR_HEIGHT+y;
            // Get the maximum x-coordinate for the ear
        double x1 = Math.max(getRambleyUpperEarX(RAMBLEY_EAR_HEIGHT), 
                getRambleyLowerEarX(RAMBLEY_EAR_HEIGHT))+x;
            // Get the point of intersection between the upper portion of the 
            // ear and the tip of the ear
        point7 = getRambleyEarUpperTip(x,y,point7);
            // Get the point of intersection between the tip of the ear and the 
            // lower portion of the ear
        point8 = getRambleyEarLowerTip(x,y,point8);
        
            // We will need to add a curve later on in order to smoothly 
            // transition from the upper portion of the ear to the tip of the 
            // ear. As such, we need to calculate where the upper curve should 
            // stop and where the tip curve should start
        
            // Get the x-coordinate for the start of the curve that joins the 
            // upper portion and the tip (this is where the upper curve should 
            // stop)
        double tempX = point7.getX()+RAMBLEY_EAR_TIP_ROUNDING;
            // Get the point at which the upper curve stops and the transition 
            // curve starts
        point5.setLocation(tempX, getRambleyUpperEarY(tempX-x)+y);
            // Get the x-coordinate for the end of the curve that joins the 
            // upper portion and the tip (this is where the tip curve should 
            // start)
        tempX = point7.getX()-RAMBLEY_EAR_TIP_ROUNDING;
            // Get the point at which the transition curve stops and the tip 
            // curve starts
        point6.setLocation(tempX, getRambleyEarTipY(tempX-x)+y);
        
            // Create the upper portion of the ear
            
            // Start at the center right-most point of the ear
        point1.setLocation(x1,y+RAMBLEY_EAR_HEIGHT/2.0);
            // Move the path to the starting point
        path.moveTo(point1.getX(), point1.getY());
            // Calculate the offset for the y-coordinate when 26% of the way 
        double tempY = RAMBLEY_EAR_HEIGHT * 0.26;   // down
            // Get the point on the upper curve when 26% of the way down
        point2.setLocation(getRambleyUpperEarX(tempY)+x,tempY+y);
            // Calculate the offset for the y-coordinate when 17% of the way 
        tempY = RAMBLEY_EAR_HEIGHT * 0.17;  // down
            // Get the point on the upper curve when 17% of the way down
        point3.setLocation(getRambleyUpperEarX(tempY)+x,tempY+y);
            // Calculate the quadratic bezier curve that passes through points 
            // point1, point2, and point3, and add that curve to the path
        point4 = addQuadBezierCurve(point1,point2,point3,point4,path);
            // Calculate the offset for the y-coordinate when 10% of the way 
        tempY = RAMBLEY_EAR_HEIGHT * 0.10;  // down
            // Get the point on the upper curve when 10% of the way down
        point2.setLocation(getRambleyUpperEarX(tempY)+x, tempY+y);
            // Calculate the quadratic bezier curve that passes through points 
            // point3, point2, and point5, and add that curve to the path
            // (point3 is the end of the previous curve, and point5 is the stop 
            // of the upper curve and the start of the transition curve)
        point4 = addQuadBezierCurve(point3,point2,point5,point4,path);
        
            // Curve to smooth the transition between the upper portion and the 
            // tip of the ear, using the point of intersection as the control 
            // point
        path.quadTo(point7.getX(), point7.getY(), point6.getX(), point6.getY());
        
            // We will need to add a curve later on in order to smoothly 
            // transition from the tip of the ear to the lower portion of the 
            // ear. As such, we need to calculate where the tip curve should 
            // stop and where the lower curve should start
        
            // Get the y-coordinate for the start of the curve that joins the 
            // tip and the lower portion (this is where the tip curve should 
            // stop)
        tempY = point8.getY()-RAMBLEY_EAR_TIP_ROUNDING;
            // Get the point at which the tip curve stops and the transition 
            // curve starts
        point5.setLocation(getRambleyEarTipX(tempY-y)+x, tempY);
            // Get the y-coordinate for the end of the curve that joins the 
            // tip and the lower portion (this is where the lower curve should 
            // start)
        tempY = point8.getY()+RAMBLEY_EAR_TIP_ROUNDING;
            // Get the point at which the transition curve stops and the 
            // lower curve starts
        point7.setLocation(getRambleyLowerEarX(tempY-y)+x, tempY);
        
            // Create the tip of the ear
        
            // Calculate the range that the x-coordinates will cover
        double dxTip = Math.abs(point5.getX()-point6.getX());
            // Calculate the offset for the x-coordinate when 40% of the way to 
        tempX = dxTip - (dxTip * 0.40);     // the left
            // Get the point on the tip curve when 40% of the way to the left
        point1.setLocation(tempX+x, y+getRambleyEarTipY(tempX));
            // Calculate the offset for the x-coordinate when 75% of the way to 
        tempX = dxTip - (dxTip * 0.75);     // the left
            // Get the point on the tip curve when 75% of the way to the left
        point2.setLocation(tempX+x, y+getRambleyEarTipY(tempX));
            // Calculate the quadratic bezier curve that passes through points 
            // point6, point1, and point2, and add that curve to the path
            // (point6 is the end of the last transition curve)
        point4 = addQuadBezierCurve(point6,point1,point2,point4,path);
            // Calculate the offset for the x-coordinate when 90% of the way to 
        tempX = dxTip - (dxTip * 0.90);     // the left
            // Get the point on the tip curve when 90% of the way to the left
        point1.setLocation(tempX+x, y+getRambleyEarTipY(tempX));
            // Calculate the quadratic bezier curve that passes through points 
            // point2, point1, and point5, and add that curve to the path
            // (point2 is the end of the previous curve, and point5 is the stop 
            // of the tip curve and the start of the next transition curve)
        point4 = addQuadBezierCurve(point2,point1,point5,point4,path);
        
            // Curve to smooth the transition between the tip and the lower 
            // portion of the ear, using the point of intersection as the 
            // control point
        path.quadTo(point8.getX(), point8.getY(), point7.getX(), point7.getY());
        
            // Create the lower portion of the ear
            
            // Calculate the offset for the y-coordinate when 76% of the way 
        tempY = RAMBLEY_EAR_HEIGHT*0.76;  // down
            // Get the point on the lower curve when 76% of the way down
        point1.setLocation(getRambleyLowerEarX(tempY)+x,tempY+y);
            // Calculate the offset for the y-coordinate when 88% of the way 
        tempY = RAMBLEY_EAR_HEIGHT*0.88;  // down
            // Get the point on the lower curve when 88% of the way down
        point2.setLocation(getRambleyLowerEarX(tempY)+x,tempY+y);
            // Calculate the quadratic bezier curve that passes through points 
            // point7, point1, and point2, and add that curve to the path
            // (point7 is the end of the last transition curve)
        point4 = addQuadBezierCurve(point7,point1,point2,point4,path);
            // Calculate the offset for the y-coordinate when 93% of the way 
        tempY = RAMBLEY_EAR_HEIGHT*0.93;  // down
            // Get the point on the lower curve when 93% of the way down
        point3.setLocation(getRambleyLowerEarX(tempY)+x,tempY+y);
            // Set the location to the bottom-right corner
        point1.setLocation(x1,y1);
            // Calculate the quadratic bezier curve that passes through points 
            // point2, point3, and point1, and add that curve to the path
            // (point2 is the end of the previous curve, and point1 is the 
            // bottom-right corner, and the end of the lower curve)
        point4 = addQuadBezierCurve(point2,point3,point1,point4,path);
            // Close the path to complete the ear
        path.closePath();
        return new Area(path);
    }
    /**
     * This gets the Area to use to render the inner portion of Rambley's ear, 
     * based off the Area of the given ear and the given scale factor for the 
     * inner ear. The inner ear will be scaled by the given scale factor and 
     * centered within the given Area for the outer ear. The given Area for the 
     * head is used to subtract the head shape from the inner ear.
     * 
     * @todo Add references to other related methods.
     * 
     * @param ear The Area representing the outer ear to derive the inner ear 
     * from.
     * @param scale The scale factor for the inner ear. 
     * @param head The Area for the shape of Rambley's head to use to subtract 
     * from the inner ear, or null.
     * @return The Area for the inner portion of Rambley's ear.
     */
    protected Area getRambleyInnerEar(Area ear, double scale, Area head){
            // Get the inverse of the given scale.
        double scaleInv = 1/scale;
            // Get the bounds of the given ear
        Rectangle2D temp = ear.getBounds2D();
            // Create a scale transform to scale down the inner ear
        AffineTransform inEarTx = AffineTransform.getScaleInstance(scale, scale);
            // Translate the transform to be at the origin
        inEarTx.translate(-temp.getMinX(), -temp.getMinY());
            // Translate the transform to be at the center of the outer portion 
            // of the ear (accounting for the earler scale transform)
        inEarTx.translate(temp.getCenterX()*scaleInv, temp.getCenterY()*scaleInv);
            // Translate the transform left by half the ear's width, and up by 
            // half the ear's height. When combined with the earlier scale 
            // transform and translations, this will result in the inner ear 
            // being centered within the outer ear
        inEarTx.translate(-temp.getWidth()/2, -temp.getHeight()/2);
            // Transform the outer ear to get the inner ear
        Area earIn = ear.createTransformedArea(inEarTx);
            // If the area of the head was given
        if (head != null)
                // Remove the area of the head from the inner ear
            earIn.subtract(head);
        return earIn;
    }
    /**
     * This creates and returns an Area that forms the shape of the mask-like 
     * markings on Rambley's face.
     * 
     * @todo Add references to other related methods.
     * 
     * @param headBounds The bounds of Rambley's head.
     * @param ellipse1 An Ellipse2D object to use to store the general area for 
     * Rambley's facial markings, or null.
     * @param ellipse2 An Ellipse2D object to use to store the ellipse used to 
     * make the bottom left (Rambley's right) portion of Rambley's facial 
     * markings, or null.
     * @param ellipse3 An Ellipse2D object to use to store the notch in 
     * Rambley's facial markings in between his eyes, or null.
     * @param rect A Rectangle2D object to use to calculate the area for 
     * Rambley's facial markings, or null.
     * @return The area of Rambley's mask-like facial markings.
     */
    private Area createRambleyMaskFaceMarkings(RectangularShape headBounds, 
            Ellipse2D ellipse1, Ellipse2D ellipse2, Ellipse2D ellipse3, 
            Rectangle2D rect){
            // If the given Rectangle is null
        if (rect == null)
            rect = new Rectangle2D.Double();
            // If the first of the ellipses are null
        if (ellipse1 == null)
            ellipse1 = new Ellipse2D.Double();
            // If the second of the ellipses are null
        if (ellipse2 == null)
            ellipse2 = new Ellipse2D.Double();
           // If the third of the ellipses are null
        if (ellipse3 == null)
            ellipse3 = new Ellipse2D.Double();
            // Set the frame of the first ellipse from the center. This ellipse 
            // will be horizontally centered, located 14 pixels above the bottom 
            // of the head, and will be around 130 x 62. This will make up the 
            // markings, and the remaining code will mask it into the right 
            // shape.
        ellipse1.setFrameFromCenter(
                headBounds.getCenterX(), headBounds.getMaxY()-45, 
                headBounds.getMinX()+34, headBounds.getMaxY()-14);
            // Set the frame of the second ellipse to be all the way to the left 
            // of the first ellipse, 17 pixels down from the top of the first 
            // ellipse, and to be 56 x 32. This forms the left (Rambley's right) 
            // part of the mask for the bottom of the facial markings, with the 
            // right part of it being a horizontally flipped version of this 
            // ellipse 
        ellipse2.setFrame(ellipse1.getMinX(),ellipse1.getMinY()+17,56,32);
            // Create an area with the second ellipse, which will be used to 
            // create the mask for the facial markings
        Area mask = new Area(ellipse2);
            // Flip the second ellipse horizontally to form the right part of 
            // the bottom of the facial markings mask
        Area temp = createHorizontallyFlippedArea(mask);
            // Get the bounds of the horizontally flipped version of the second 
            // ellipse, so that we can get some location data from it
        Rectangle2D tempBounds = temp.getBounds2D();
            // Set the frame of the rectangle using a diagonal from the top 
            // center of the second ellipse to the bottom center of the 
            // horizontally flipped version of the second ellipse. This will be 
            // used to bridge the two together to complete the bottom part of 
            // the mask.
        rect.setFrameFromDiagonal(ellipse2.getCenterX(), ellipse2.getMinY(), 
                tempBounds.getCenterX(), tempBounds.getMaxY());
            // Add the flipped version of the second ellipse to the mask
        mask.add(temp);
            // Add the rectangle to the mask to complete the bottom part of the 
        mask.add(new Area(rect));   // mask
            // Set the frame of the rectangle again using a diagonal, this time 
            // from the top-left corner of the first ellipse, to the right of 
            // the first ellipse and the vertical center of the second ellipse. 
            // This will form the top portion of the mask.
        rect.setFrameFromDiagonal(ellipse1.getMinX(), ellipse1.getMinY(), 
                ellipse1.getMaxX(), ellipse2.getCenterY());
            // Add the rectangle to the mask to form the top part of the mask
        mask.add(new Area(rect));
            // Set the frame for the third ellipse from the center so as to be 
            // horizontally centered in the markings, with the top being 24 
            // pixels above the first ellipse, and should be 28 x 36. This will 
            // form the little notch between the eyes at the top of the markings
        ellipse3.setFrameFromCenter(ellipse1.getCenterX(), ellipse1.getMinY()-6, 
                ellipse1.getCenterX()+14, ellipse1.getMinY()-24);
            // Remove the third ellipse from the mask so that it gets removed 
            // from the facial markings
        mask.subtract(new Area(ellipse3));
            // Create the area that will get the facial markings using the first 
        Area markings = new Area(ellipse1);     // ellipse
            // Mask off the areas that are not a part of the facial markings.
        markings.intersect(mask);
        return markings;
    }
    /**
     * This creates and returns an Area that forms the shape of Rambley's snout 
     * area. That is to say, the area around Rambley's nose and mouth.
     * 
     * @todo Add references to other related methods.
     * 
     * @param headBounds The bounds of Rambley's head, or null if {@code head} 
     * is not null.
     * @param head The Area for the shape of Rambley's head to use to ensure the 
     * snout area lies within the head shape, or null.
     * @param ellipse An Ellipse2D object to use to calculate the snout area, 
     * or null.
     * @return The area around Rambley's nose and mouth.
     */
    private Area createRambleySnoutArea(RectangularShape headBounds, 
            Area head, Ellipse2D ellipse){
            // If the ellipse is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // If the bounds for the head are null but the area for the head is 
        if (headBounds == null && head != null)     // not
                // Get the bounds for the head
            headBounds = head.getBounds2D();
            // Make sure the head bounds are not null
        Objects.requireNonNull(headBounds);
            // Set the ellipse's frame from the center so that it is 
            // horizontally centered in the head, is at the bottom of the head, 
            // and is around 72 x 56. This forms the snout area.
        ellipse.setFrameFromCenter(
                headBounds.getCenterX(), headBounds.getMaxY()-28, 
                headBounds.getMinX()+63, headBounds.getMaxY());
            // Create an area for the snout area
        Area mouthArea = new Area(ellipse);
            // If the area of the head was given
        if (head != null)
                // Make sure the snout area is fully within the head area
            mouthArea.intersect(head);
        return mouthArea;
    }
    /**
     * This creates and returns an Area that forms the shape of Rambley's right 
     * eyebrow. This is flipped to produce the left eyebrow. The eyebrows are 
     * intended to be covered up by the markings around Rambley's eyes and the 
     * eyes themselves.
     * 
     * @todo Add references to other related methods.
     * 
     * @param headBounds The bounds of Rambley's head without his ears.
     * @param ellipse An Ellipse2D object to use to calculate the eyebrow, or 
     * null.
     * @return The area that forms Rambley's right eyebrow.
     */
    private Area createRambleyEyebrow(RectangularShape headBounds, 
            Ellipse2D ellipse){
            // If the ellipse is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // Set the ellipse's frame from the center so that it is around 49 
            // pixels to the left of center, 20 pixels lower than the top of the 
            // head, and is 40 x 40. This forms the eyebrow area, and is 
            // intended to be covered up by the markings around the eyes and the 
            // eyes themselves.
        ellipse.setFrameFromCenter(
                headBounds.getCenterX()-29, headBounds.getMinY()+40, 
                headBounds.getCenterX()-9, headBounds.getMinY()+20);
        return new Area(ellipse);
    }
    /**
     * This creates and returns an Area that forms the shape of the markings 
     * around Rambley's right eye. This uses the ellipse given to the {@link 
     * #createRambleySnoutArea createRambleySnoutArea} method ({@code snout}) 
     * and the ellipse given to the {@link #createRambleyEyebrow 
     * createRambleyEyebrow} method ({@code eyeBrow}) to position and control 
     * the form of the shape.
     * 
     * @todo Add references to other related methods.
     * 
     * @param eyeBrow An Ellipse2D object with the ellipse used to form 
     * Rambley's right eyebrow (cannot be null).
     * @param snout  An Ellipse2D object with the ellipse used to form Rambley's 
     * snout (cannot be null).
     * @param ellipse An Ellipse2D object to use to calculate the top-right 
     * portion of the markings, or null.
     * @param path A Path2D object to use to form the portion of the markings 
     * that will not be formed by {@code ellipse}, or null.
     * @param point1 A Point2D object to store the top-left most point (starting 
     * point) of the bottom-left curve, or null.
     * @param point2 A Point2D object to store the bottom-right most point (end 
     * point) of the bottom-left curve, or null.
     * @param pointC A Point2D object to store the control point of the 
     * bottom-left curve, or null.
     * @return The area that forms the markings around Rambley's right eye.
     */
    private Area createRambleyEyeMarkings(Ellipse2D eyeBrow, Ellipse2D snout, 
            Ellipse2D ellipse, Path2D path, Point2D point1, Point2D point2, 
            Point2D pointC){
            // If the given Ellipse2D object is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // If the first of the three given Point2D objects is null
        if (point1 == null)
            point1 = new Point2D.Double();
            // If the second of the three given Point2D objects is null
        if (point2 == null)
            point2 = new Point2D.Double();
            // If the third of the three given Point2D objects is null
        if (pointC == null)
            pointC = new Point2D.Double();
            // Set the frame for the ellipse from the center, with its right 
            // side aligned with Rambley's eyebrows, 4 pixels lower than the 
            // eyebrows, and it should be 43 x 48. This forms the top-right part 
            // of the markings.
        ellipse.setFrameFromCenter(
                eyeBrow.getCenterX()-1.5, eyeBrow.getCenterY()+8, 
                eyeBrow.getMaxX(), eyeBrow.getMinY()+4);
            // Get the points of intersection between the top-right part of the 
            // markings and the eyebrows. The left-most one (point2) will be 
            // used to transition between the ellipse and the path.
        getCircleIntersections(ellipse,eyeBrow,point2,point1);
            // Make sure to use a point of intersection is actually on the 
            // ellipse by calculating the point on the ellipse for the 
            // left-most point of intersection. The top-most one (point2) will 
            // be used to transition between the ellipse and the path.
        getEllipseY(ellipse,point2.getX(),point2,point1);
            // Start the path at the left-most point of intersection
        path.moveTo(point2.getX(), point2.getY());
            // Move the first point to where the path should stop going to the 
        point1.setLocation(ellipse.getMinX()-8, eyeBrow.getMaxY()+2);   // left
            // Add a bezier curve from point2 on the ellipse to point1. In order 
            // to curve correctly, use a control point that is 2/3 left of the 
            // way to point1, and 1/3 of the way down to point1 and shifted down 
            // by 5 pixels
        path.quadTo((point2.getX()+(point1.getX()*2))/3, 
                ((point1.getY()+(point2.getY()*2))/3)+5, 
                point1.getX(), point1.getY());
            // Get the location that the next curve will end, making it end in 
            // the horrizontal center of the ellipse, and 14 pixels below the 
            // ellipse
        point2.setLocation(ellipse.getCenterX(), ellipse.getMaxY()+14);
            // The control point is 2 pixels to the left of the ellipse, and at 
            // the same y-coordinate as the end of the curve (14 pixels below 
            // the ellipse)
        pointC.setLocation(ellipse.getMinX()+2, point2.getY());
            // Add a bezier curve from point1 to point2, using point3 as the 
            // control point.
        path.quadTo(pointC.getX(), pointC.getY(), point2.getX(), point2.getY());
            // Add a bezier control point from point2 to the right-center of the 
            // ellipse. Use the ellipse's right-most x-coordinate and the 
            // snout's y-coordinate as the control. This curve will mostly be 
            // covered up by the eyes.
        path.quadTo(ellipse.getMaxX(), snout.getMinY(), 
                ellipse.getMaxX(), ellipse.getCenterY());
            // Close the path
        path.closePath();
            // Create the area for the eye markings, starting with the ellipse
        Area eyeSurround = new Area(ellipse);
            // Add the shape formed by the path to the markings
        eyeSurround.add(new Area(path));
        return eyeSurround;
    }
    /**
     * This creates and returns an Area that forms the shape of Rambley's right 
     * eye. This uses the ellipse given to the {@link #createRambleyEyebrow 
     * createRambleyEyebrow} method ({@code eyeBrow}) and the ellipse and three 
     * points given to {@link #createRambleyEyeMarkings 
     * createRambleyEyeMarkings} method ({@code eyeMarkEllipse}, {@code 
     * eyeMarkP1}, {@code eyeMarkP2}, and {@code eyeMarkPC}).
     * 
     * @todo Add references to other related methods.
     * 
     * @param headBounds The bounds of Rambley's head to position and control 
     * the form of the shape.
     * @param eyeBrow An Ellipse2D object with the ellipse used to form 
     * Rambley's right eyebrow.
     * @param eyeMarkEllipse An Ellipse2D object with the ellipse used to form 
     * the markings around Rambley's right eye.
     * @param eyeMarkP1 The top-left most point (starting point) of the 
     * bottom-left curve of the eye markings, or null.
     * @param eyeMarkP2 The bottom-right most point (end point) of the 
     * bottom-left curve of the eye markings, or null.
     * @param eyeMarkPC The control point of the bottom-left curve of the eye 
     * markings, or null.
     * @param ellipse An Ellipse2D object to use to calculate the top-right 
     * portion of the eye, or null.
     * @param rect A rectangular shape to use to calculate a reference for the 
     * curves that form the eye not formed by {@code ellipse}, or null.
     * @param path A Path2D object to use to form the portion of the eye that 
     * will not be formed by {@code ellipse}, or null.
     * @param point1 A Point2D object to store the bottom-left point (end point) 
     * of the top-left curve for the eye, or null.
     * @param point2 A Point2D object to store the top-right point (end point) 
     * of the last curve for the eye, or null.
     * @param point3 A disposable Point2D object to use to calculate some 
     * points, or null.
     * @return The area that forms Rambley's right eye.
     */
    private Area createRambleyEyeShape(RectangularShape headBounds, 
            Ellipse2D eyeBrow, Ellipse2D eyeMarkEllipse, Point2D eyeMarkP1, 
            Point2D eyeMarkP2, Point2D eyeMarkPC, Ellipse2D ellipse, 
            RectangularShape rect, Path2D path, Point2D point1, Point2D point2, 
            Point2D point3){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
           // If the given RectangularShape object is null
        if (rect == null)
            rect = new Rectangle2D.Double();
            // If the given Ellipse2D object is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // If the first of the three given Point2D objects is null
        if (point1 == null)
            point1 = new Point2D.Double();
            // If the second of the three given Point2D objects is null
        if (point2 == null)
            point2 = new Point2D.Double();
            // If the third of the three given Point2D objects is null
        if (point3 == null)
            point3 = new Point2D.Double();
            // Set the frame from the ellipse from its center, with its right 
            // side lining up with the eyebrow ellipse, 12 pixels lower than 
            // the eye marks ellipse, and is 32 x 47. This forms the top-right 
            // part of the eye.
        ellipse.setFrameFromCenter(
                eyeBrow.getCenterX()+4, eyeMarkEllipse.getCenterY()+11.5, 
                eyeBrow.getMaxX(), eyeMarkEllipse.getMinY()+12);
            // Set the frame of the rectangular shape from its center, with its 
            // right side lining up with the eyebrow ellipse, 14 pixels lower 
            // than the eye marks ellipse, and is 39 x 48. This is used as a 
            // reference for the curves that form the eye.
        rect.setFrameFromCenter(
                eyeBrow.getCenterX()+0.5, eyeMarkEllipse.getCenterY()+14, 
                eyeBrow.getMaxX(), eyeMarkEllipse.getMinY()+14);
            // Start the path at the top-center of the ellipse
        path.moveTo(ellipse.getCenterX(), ellipse.getMinY());
            // Draw a horizontal line that stops halfway between the center of 
            // the ellipse and the center of the rectangular shape
        path.lineTo((ellipse.getCenterX()+rect.getCenterX())/2, ellipse.getMinY());
            // This will get the point on the bottom-left bezier curve for the 
            // eye markings. This should be around 4 pixels to the left of the 
            // rectangular shape.
        point1 = getQuadBezierPointForX(eyeMarkP1,eyeMarkPC,eyeMarkP2,
                rect.getMinX()-4,point1);
            // Add a quadratic bezier curve from the previous point to point1, 
            // using a control point that is at the point formed by the 
            // left-most x-coordinate of the rectangular shape and the top 
            // y-coordinate of the ellipse.
        path.quadTo(rect.getMinX(),ellipse.getMinY(),point1.getX(),point1.getY());
            // Add a quadratic bezier curve from point1 to the bottom-center of 
            // the rectangular shape, using a control point that is 5 pixels to 
            // the right of the eye marks ellipse, and that is halfway between 
            // the bottoms of the rectangular shape and the eye ellipse.
        path.quadTo(eyeMarkEllipse.getMinX()+5, (ellipse.getMaxY()+rect.getMaxY())/2, 
                rect.getCenterX(), rect.getMaxY());
            // Get the x-coordinate to use for the control point of the next 
            // quadratic bezier curve. This is 14 pixels left of the center of 
            // the face
        double x = headBounds.getCenterX()-14;
            // Add a quadratic bezier curve from the previous point to 2/3rds of 
            // the way between the control point of this curve and the 
            // right-most point of the ellipse, and at the bottom of the 
            // ellipse. The control point is 14 pixels to the left of the center 
            // of the face and is at the bottom of the rectangular shape.
        path.quadTo(x, rect.getMaxY(), (x+(ellipse.getMaxX()*2))/3, ellipse.getMaxY());
            // Calculate the points where the ellipse intersects the horizontal 
            // line one pixel above the center of the ellipse
        getEllipseX(ellipse,ellipse.getCenterY()-1,point3,point2);
            // Add a quadratic bezier curve from the previous point to point2 
            // (the right-most intersection point), and using a control point 
            // that is 1 pixel to the right of the rectangular shape, and is 
            // 2/3rds of the way from the center of the rectangular shape to the  
            // bottom of the rectangular shape.
        path.quadTo(ellipse.getMaxX()+1, (rect.getMaxY()+(rect.getCenterY()*2))/3, 
                point2.getX(), point2.getY());
            // Close the path, completing the curves
        path.closePath();
            // Create the area for the eye, starting with the ellipse
        Area eye = new Area(ellipse);
            // Add the shape formed by the path to the eye
        eye.add(new Area(path));
        return eye;
    }
    /**
     * This sets the location for the {@code iris} and {@code pupil} ellipses 
     * for Rambley's eyes to the given center x and y coordinates.
     * 
     * @todo Add references to other related methods.
     * 
     * @param x The x-coordinate of the center of Rambley's eye.
     * @param y The y-coordinate of the center of Rambley's eye.
     * @param iris An Ellipse2D object to store Rambley's iris in.
     * @param pupil An Ellipse2D object to store Rambley's pupil in.
     * @see #paintRambleyEye(Graphics2D, Shape, Ellipse2D, Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Ellipse2D, Ellipse2D) 
     */
    protected void setRambleyEyeLocation(double x, double y, Ellipse2D iris, 
            Ellipse2D pupil){
            // Set the frame of the iris ellipse from the center to be the 
            // correct size for Rambley's iris
        iris.setFrameFromCenter(x,y,
                x+RAMBLEY_IRIS_HALF_SIZE,y+RAMBLEY_IRIS_HALF_SIZE);
            // Ensure that the coordinates are the center of the iris
        x = iris.getCenterX();
        y = iris.getCenterY();
            // Set the frame of the pupil ellipse from the center to be the 
            // correct size for Rambley's pupil
        pupil.setFrameFromCenter(x, y, 
                x+RAMBLEY_PUPIL_HALF_SIZE, y+RAMBLEY_PUPIL_HALF_SIZE);
    }
    /**
     * This paints Rambley's eye using the given eye white, iris, and pupil.
     * 
     * @todo Add references to other related methods.
     * 
     * @param g The graphics context to render to.
     * @param eyeWhite The shape to use to paint the eye white for Rambley's 
     * eye.
     * @param iris The Ellipse2D object representing Rambley's iris.
     * @param pupil The Ellipse2D object representing Rambley's pupil.
     * @see #setRambleyEyeLocation(double, double, Ellipse2D, Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Ellipse2D, Ellipse2D) 
     */
    protected void paintRambleyEye(Graphics2D g,Shape eyeWhite,Ellipse2D iris,
            Ellipse2D pupil){
            // Create a copy of the given graphics context
        Graphics2D tempG = (Graphics2D) g.create();
            // Clip the copy to the shape of the eye white
        tempG.clip(eyeWhite);
            // Fill the eye white
        tempG.setColor(RAMBLEY_EYE_WHITE_COLOR);
        tempG.fill(eyeWhite);
            // Set the color for Rambley's iris. If Rambley is evil, use the 
            // evil Rambley iris color. Otherwise, use the normal Rambley iris 
            // color
        tempG.setColor((isRambleyEvil())?EVIL_RAMBLEY_IRIS_COLOR:RAMBLEY_IRIS_COLOR);
            // Fill Rambley's iris
        tempG.fill(iris);
            // Fill Rambley's pupil
        tempG.setColor(RAMBLEY_PUPIL_COLOR);
        tempG.fill(pupil);
            // Set the color for the outline of Rambley's iris. If Rambley is 
            // evil, use the evil Rambley iris outline color. Otherwise, use the 
            // normal Rambley iris outline color
        tempG.setColor((isRambleyEvil())?EVIL_RAMBLEY_IRIS_OUTLINE_COLOR:
                RAMBLEY_IRIS_OUTLINE_COLOR);
            // Set the stroke to use to the detail stroke
        tempG.setStroke(getRambleyDetailStroke());
            // Draw the outline of the iris
        tempG.draw(iris);
            // Draw the outline of the pupil
        tempG.draw(pupil);
        tempG.dispose();
            // Create another copy of the given graphics context
        g = (Graphics2D) g.create();
            // Set the stroke to use to the outline stroke
        g.setStroke(getRambleyOutlineStroke());
            // Draw the outline for Rambley's eye
        g.setColor(RAMBLEY_EYE_OUTLINE_COLOR);
        g.draw(eyeWhite);
        g.dispose();
    }
    /**
     * This sets the location for the {@code iris} and {@code pupil} ellipses 
     * for Rambley's eyes to the given center x and y coordinates, and then 
     * paints Rambley's eye Rambley's eye using the given eye white, iris, and 
     * pupil. This is equivalent to calling {@link #setRambleyEyeLocation 
     * setRambleyEyeLocation} before calling {@link #paintRambleyEye(Graphics2D, 
     * Shape, Ellipse2D, Ellipse2D) paintRambleyEye}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param g The graphics context to render to.
     * @param eyeWhite The shape to use to paint the eye white for Rambley's 
     * eye.
     * @param x The x-coordinate of the center of Rambley's iris and pupil.
     * @param y The y-coordinate of the center of Rambley's iris and pupil.
     * @param iris The Ellipse2D object representing Rambley's iris, or null.
     * @param pupil The Ellipse2D object representing Rambley's pupil, or null.
     * @see #setRambleyEyeLocation(double, double, Ellipse2D, Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, Ellipse2D, Ellipse2D) 
     */
    protected void paintRambleyEye(Graphics2D g,Shape eyeWhite,double x, 
            double y,Ellipse2D iris,Ellipse2D pupil){
            // If the given iris Ellipse2D object is null
        if (iris == null)
            iris = new Ellipse2D.Double();
            // If the given pupil Ellipse2D object is null
        if (pupil == null)
            pupil = new Ellipse2D.Double();
            // Set the location of Rambley's iris and pupil
        setRambleyEyeLocation(x,y,iris,pupil);
            // Paint Rambley's eye
        paintRambleyEye(g,eyeWhite,iris,pupil);
    }
    /**
     * 
     * @param g The graphics context to render to.
     * @param eyeWhite The shape to use to paint the eye white for Rambley's 
     * eye.
     * @param x 
     * @param y 
     * @param bounds The bounds for {@code eyeWhite}, or null. This is used to 
     * calculate the location for the eye relative to the eye white.
     * @param minXOff The offset for the minimum x-coordinate.
     * @param maxXOff The offset for the maximum x-coordinate.
     * @param iris The Ellipse2D object representing Rambley's iris, or null.
     * @param pupil The Ellipse2D object representing Rambley's pupil, or null.
     */
    protected void paintRambleyEye(Graphics2D g,Shape eyeWhite,double x,
            double y,Rectangle2D bounds,double minXOff,double maxXOff,
            Ellipse2D iris,Ellipse2D pupil){
            // If the given bounds for the eye is null
        if (bounds == null)
                // Get the bounds for the eye
            bounds = eyeWhite.getBounds2D();
            // Expand the bounds using the given minimum and maximum x offsets
        bounds.setFrameFromDiagonal(bounds.getMinX()+minXOff,bounds.getMinY(), 
                bounds.getMaxX()+maxXOff, bounds.getMaxY());
            // Expand the bounds by the size of the iris
        bounds.setFrameFromCenter(bounds.getCenterX(), bounds.getCenterY(), 
                bounds.getMinX()-RAMBLEY_IRIS_HALF_SIZE,
                bounds.getMinY()-RAMBLEY_IRIS_HALF_SIZE);
            // Calculate the location for the iris and pupil using the given x 
            // and y values to determine how far right and down, respectively, 
            // they are in the eye white
        paintRambleyEye(g,eyeWhite,bounds.getMinX()+(bounds.getWidth()*x),
                bounds.getMinY()+(bounds.getHeight()*y),iris,pupil);
    }
    /**
     * This creates and returns an Area that forms the shape of Rambley's nose. 
     * This uses the ellipse given to the {@link #createRambleySnoutArea 
     * createRambleySnoutArea} method ({@code snout}) to position and control 
     * the form of the shape.
     * 
     * @todo Add references to other related methods.
     * 
     * @param snout An Ellipse2D object with the ellipse used to form Rambley's 
     * snout (cannot be null).
     * @param rect A Rectangle2D object to store the bounds of the nose, or 
     * null.
     * @param ellipse An Ellipse2D object to store the ellipse that forms the 
     * top of the nose, or null.
     * @param path A Path2D object to store the bottom of the nose, or null.
     * @return The area that forms Rambley's nose.
     */
    private Area createRambleyNoseShape(Ellipse2D snout, Rectangle2D rect, 
            Ellipse2D ellipse, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
           // If the given Rectangle2D object is null
        if (rect == null)
            rect = new Rectangle2D.Double();
            // If the given Ellipse2D object is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // Set the frame of the rectangle from its center that is 
            // horizontally centered on the snout, 6 pixels below the top of the 
            // snout, and is around 18 x 14. This will form the bounds of the 
            // nose
        rect.setFrameFromCenter(snout.getCenterX(), snout.getMinY()+13, 
                snout.getCenterX()-9, snout.getMinY()+6);
            // Set the frame of the ellipse from its center with it's top-left 
            // corner at the top-left corner of the bounds, and that is 18 x 9. 
            // This will form the top of the nose
        ellipse.setFrameFromCenter(rect.getCenterX(), rect.getMinY()+4.5, 
                rect.getMinX(), rect.getMinY());
            // Start the path at the left center of the ellipse
        path.moveTo(ellipse.getMinX(), ellipse.getCenterY());
            // Add a cubic bezier curve to the center bottom of the bounds of 
            // the nose. The first control point is at the left center of the 
            // bounds. The second control point is halfway between the left and 
            // center of the bounds of the nose, and over to the left by one 
            // pixel, and is at the bottom of the bounds of the nose.
        path.curveTo(rect.getMinX(),rect.getCenterY(),
                (rect.getMinX()+rect.getCenterX())/2-1, rect.getMaxY(), 
                rect.getCenterX(), rect.getMaxY());
            // Add a line to the center of the ellipse (this is a vertical line 
            // to the top of the path.
        path.lineTo(rect.getCenterX(), ellipse.getCenterY());
            // Close the path
        path.closePath();
            // Flip the path horizontally to form the other half of the nose 
            // and then add it to the path to complete the bottom part of the 
        path = mirrorPathHorizontally(path,rect.getCenterX());  // nose
            // Create the area for the nose, starting with the top of the nose
        Area nose = new Area(ellipse);
            // Add the path representing the bottom of the nose
        nose.add(new Area(path));
        return nose;
    }
    /**
     * This creates the path to use for the curve of the mouth. This uses the 
     * ellipse given to the {@link #createRambleySnoutArea 
     * createRambleySnoutArea} method ({@code snout}) to position the outer 
     * edges of the mouth.
     * 
     * @todo Add references to other related methods.
     * 
     * @param snout An Ellipse2D object with the ellipse used to form Rambley's 
     * snout (cannot be null).
     * @param x The x-coordinate for the center of the mouth.
     * @param y The y-coordinate for the top of the center spike of the mouth. 
     * This should be y-coordinate for the bottom of the nose.
     * @param point1 A Point2D object to use to calculate the lowest point on 
     * the right side of the curve of the mouth, or null.
     * @param pointC1 A Point2D object to use to calculate the control point for 
     * the curve between the given {@code x} and {@code y} and {@code point1}, 
     * or null. 
     * @param point2 A Point2D object to use to calculate the end point for the 
     * the right side of the curve of the mouth, or null.
     * @param pointC2 A Point2D object to use to calculate the control point for 
     * the curve between {@code point1} and {@code point2}, or null. 
     * @param path A Path2D object to store the results in, or null.
     * @return A Path2D object with the mouth curve.
     */
    private Path2D createRambleyMouthCurve(Ellipse2D snout, double x, double y, 
            Point2D point1, Point2D pointC1, Point2D point2, Point2D pointC2, 
            Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // If the first of the four given scratch Point2D objects is null
        if (point1 == null)
            point1 = new Point2D.Double();
            // If the second of the four given scratch Point2D objects is null
        if (point2 == null)
            point2 = new Point2D.Double();
            // If the third of the four given scratch Point2D objects is null
        if (pointC1 == null)
            pointC1 = new Point2D.Double();
            // If the fourth of the four given scratch Point2D objects is null
        if (pointC2 == null)
            pointC2 = new Point2D.Double();
        
            // Form the right curve of Rambley's mouth
        
            // Move the path to the starting point.
        path.moveTo(x, y);
            // Calculate the points where the snout intersects the horizontal 
            // line 2.5 pixels above the first point.
            // (A variation with a slight smile may be possible by adding 2.5 
            // instead of subtracting)
        getEllipseX(snout,y-2.5,point2,point1);
            // Shift the left-most point 7.5 pixels to the right. This will be 
            // the end point of the right side of the mouth curve.
        point2.setLocation(point2.getX()+7.5, point2.getY());
            // Set the second point to be in between the starting point and 
            // end point,and 9 pixels below the first point. This will be the 
            // mid-point of the right side of the mouth curve
        point1.setLocation((x+point2.getX())/2, y+9);
            // Set the first control point to be halfway between the starting 
            // point and mid-point of the curve, with the y of the mid-point.
        pointC1.setLocation((x+point1.getX())/2,point1.getY());
            // Set the second control point to be halfway between the mid-point 
            // and end point of the curve, with the y of the mid-point.
        pointC2.setLocation((point2.getX()+point1.getX())/2,point1.getY());
            // Add a quadratic bezier curve between the starting point and 
            // mid-point, using the first control point.
        path.quadTo(pointC1.getX(),pointC1.getY(),point1.getX(), point1.getY());
            // Add a quadratic bezier curve between the mid-point and end point, 
            // using the first control point.
        path.quadTo(pointC2.getX(),pointC2.getY(),point2.getX(), point2.getY());
        
            // Form the line at the right edge of his mouth
        
            // Move to two pixels to the right and two pixels up from the end of 
            // the curve
        path.moveTo(point2.getX()+2, point2.getY()-2);
            // Draw a line that is 4.5 pixels to the left and 4.5 pixels up
        path.lineTo(point2.getX()-2.5, point2.getY()+2.5);
        
            // Flip the path (which holds the right side of the mouth) 
            // horizontally to form the left side of the mouth and then add the 
            // left side of the mouth to the path.
        path = mirrorPathHorizontally(path,x);
        return path;
    }
    /**
     * This creates the path to use for the curve of the mouth. This uses the 
     * ellipse given to the {@link #createRambleySnoutArea 
     * createRambleySnoutArea} method ({@code snout}) to position the outer 
     * edges of the mouth.
     * 
     * @todo Add references to other related methods.
     * 
     * @param snout An Ellipse2D object with the ellipse used to form Rambley's 
     * snout (cannot be null).
     * @param point The Point2D object with the center point of the mouth curve 
     * (cannot be null). This should be the bottom center point of the nose.
     * @param point1 A Point2D object to use to calculate the lowest point on 
     * the right side of the curve of the mouth, or null.
     * @param pointC1 A Point2D object to use to calculate the control point for 
     * the curve between {@code point} and {@code point1}, or null. 
     * @param point2 A Point2D object to use to calculate the end point for the 
     * the right side of the curve of the mouth, or null.
     * @param pointC2 A Point2D object to use to calculate the control point for 
     * the curve between {@code point1} and {@code point2}, or null. 
     * @param path A Path2D object to store the results in, or null.
     * @return A Path2D object with the mouth curve.
     */
    private Path2D createRambleyMouthCurve(Ellipse2D snout, Point2D point, 
            Point2D point1, Point2D pointC1, Point2D point2, Point2D pointC2, 
            Path2D path){
        return createRambleyMouthCurve(snout,point.getX(),point.getY(),point1,
                pointC1,point2,pointC2,path);
    }
    /**
     * This creates and returns the Area that forms Rambley's open mouth. This 
     * uses the ellipse given to the {@link #createRambleySnoutArea 
     * createRambleySnoutArea} method ({@code snout}) to control the maximum 
     * height of the mouth. This also uses the path returned by and the points 
     * given to the {@link #createRambleyMouthCurve(Ellipse2D, Point2D, Point2D, 
     * Point2D, Point2D, Point2D, Path2D) createRambleyMouthCurve} 
     * method, those being {@code mouthCurve} (the path), {@code midPoint}, 
     * {@code point1}, {@code pointC1}, {@code point2}, and {@code pointC2} to 
     * control and position the mouth.
     * 
     * @todo Add references to other related methods.
     * 
     * @param mouthCurve The Path2D object that forms the mouth curve (cannot be 
     * null).
     * @param mouthWidth A value between 0.0 and 1.0, inclusive, used to control 
     * the width of Rambley's open mouth.
     * @param mouthHeight A value between 0.0 and 1.0, inclusive, used to 
     * control the height of Rambley's open mouth.
     * @param snout An Ellipse2D object with the ellipse used to form Rambley's 
     * snout (cannot be null).
     * @param midPoint The Point2D object with the center point of the mouth 
     * curve (cannot be null).
     * @param point1 The Point2D object with the lowest point on the right side 
     * of the mouth curve (cannot be null).
     * @param pointC1 The Point2D object with the control point for the curve on 
     * the mouth between {@code midPoint} and {@code point1} (cannot be null). 
     * @param point2 The Point2D object with the end point for the the right 
     * side of the mouth curve (cannot be null).
     * @param pointC2 The Point2D object with the control point for the curve on 
     * the mouth between {@code point1} and {@code point2} (cannot be null). 
     * @param point A Point2D object to use to calculate the point on the right 
     * side of the mouth curve to start the path that forms the open mouth 
     * shape, or null.
     * @param rect A Rectangle2D object to store the bounds of the path used to 
     * create the open mouth, or null.
     * @param path A Path2D object to use to calculate the path for the open 
     * mouth, or null.
     * @return The area that forms Rambley's open mouth.
     */
    private Area createRambleyOpenMouthShape(Path2D mouthCurve,double mouthWidth, 
            double mouthHeight,Ellipse2D snout,Point2D midPoint,Point2D point1,
            Point2D pointC1, Point2D point2, Point2D pointC2, Point2D point, 
            Rectangle2D rect, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // If the given scratch Point2D objects is null
        if (point == null)
            point = new Point2D.Double();
           // If the given Rectangle2D object is null
        if (rect == null)
            rect = new Rectangle2D.Double();
            // Set the frame of the rectangle from the center to have the center 
            // at the mid point of the mouth curve. It will be as wide as the 
            // mouth curve and the maximum y-coordinate will be 3.5 pixels above 
            // the bottom of the snout ellipse. The lower half of this forms the 
            // maximum bounds of the open mouth
        rect.setFrameFromCenter(midPoint.getX(), midPoint.getY(), 
                point2.getX(), snout.getMaxY()-3.5);
            // Set the frame of the rectangle again from the center with the 
            // same center as before, but now set the x and y coordinates based 
            // off the given width and height for the mouth. 
        rect.setFrameFromCenter(rect.getCenterX(),rect.getCenterY(),
                rect.getMinX()+((1-mouthWidth)*(rect.getWidth()/2.0)),
                rect.getMinY()+((1-mouthHeight)*(rect.getHeight()/2.0)));
            // If the x-coordinate of the rectangle is at or beyond the 
            // left-most point on the mouth curve
        if (rect.getMinX() <= point2.getX())
            point.setLocation(point2);
            // If the x-coordinate of the rectangle is at the point between the 
            // two curves that form the right side of the mouth curve
        else if (rect.getMinX() == point1.getX())
            point.setLocation(point1);
            // If the x-coordinate of the rectangle is at or beyond the center 
            // of the mouth curve
        else if (rect.getMinX() >= midPoint.getX())
            point.setLocation(midPoint);
        else {  // These are the three points that form the quadratic bezier 
                // curve to get a point on. These are, in order, the starting 
                // point, the ending point, and the control point.
            Point2D p1, p2, pC;
                // If the x-coordinate for the rectangle lies on the left-most 
                // curve on the right side of the mouth. That is to say, if it 
                // is in between point1 and point2
            if (rect.getMinX()>=point2.getX()&&rect.getMinX()<=point1.getX()){
                    // Use point1 as the starting point
                p1 = point1;
                    // Use point2 as the ending point
                p2 = point2;
                    // Use pointC2 as the control point
                pC = pointC2;
            }
            else{   // Use midPoint as the starting point
                p1 = midPoint;
                    // Use point1 as the ending point
                p2 = point1;
                    // Use pointC1 as the control point
                pC = pointC1;
            }   // Get the point on the quadratic bezier curve at the 
                // x-coordinate of the rectangle on the quadratic bezier curve 
                // that forms the mouth curve
            point = getQuadBezierPointForX(p1,pC,p2,rect.getMinX(),point);
        }
            // Start the path at the point on the mouth curve
        path.moveTo(point.getX(), point.getY());
            // Add a quadratic bezier curve between the point on the mouth curve 
            // and the point in the middle of the mouth curve and at the bottom 
            // of the open mouth bounds. Use the the point between the starting 
            // point and the mid-point as the control x-coordinate, and the 
            // bottom of the open mouth bounds as the control y-coordinate
        path.quadTo((point.getX()+midPoint.getX())/2.0,rect.getMaxY(),
                midPoint.getX(), rect.getMaxY());
            // Draw a vertical line from the previous point to the mid-point on 
            // the mouth curve
        path.lineTo(midPoint.getX(), midPoint.getY());
            // Flip the path (which holds the right side of the open mouth) 
            // horizontally to form the left side of the open mouth and then add 
            // the left side of the open mouth to the path.
        path = mirrorPathHorizontally(path,midPoint.getX());
            // Add the mouth curve to the path to complete the shape
        path.append(mouthCurve, false);
            // Create an area with the path
        Area mouthArea = new Area(path);
            // Subtract the area formed by the mouth curve, since otherwise 
            // Rambley gets a sort of moustache due to how the path is 
            // interpretted
        mouthArea.subtract(new Area(mouthCurve));
        return mouthArea;
    }
    /**
     * This creates and returns the Area that forms Rambley's tongue. This uses 
     * the shape of the mouth returned by {@link createRambleyOpenMouthShape
     * createRambleyOpenMouthShape} method ({@code openMouth}) to ensure that 
     * the tongue does not escape the mouth.
    * 
     * @todo Add references to other related methods.
     * 
     * @param xC The center x-coordinate for the tongue.
     * @param x The x-coordiante for the top-left corner of the tongue.
     * @param y The y-coordinate of the bottom of the mouth.
     * @param mouthWidth A value between 0.0 and 1.0, inclusive, used to control 
     * the width of Rambley's open mouth.
     * @param mouthHeight A value between 0.0 and 1.0, inclusive, used to 
     * control the height of Rambley's open mouth.
     * @param openMouth The area that forms Rambley's open mouth.
     * @param ellipse An Ellipse2D object to use to calculate the tongue, or 
     * null.
     * @return An area that forms Rambley's tongue.
     */
    private Area createRambleyTongueShape(double xC, double x, double y, 
            double mouthWidth, double mouthHeight, Area openMouth, 
            Ellipse2D ellipse){
            // If the given Ellipse2D object is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // Shift the y-coordinate up by up to 11.75 pixels, depending on the 
            // open mouth's height
        y -= 11.75 * mouthHeight;
            // Set the given ellipse from its center to a circle that is at the 
            // given x-coordinate and shifted y-coordinate, and that has the 
            // given center x-coordinate
        ellipse.setFrameFromCenter(xC, y+(xC-x), x, y);
            // Create an area from the ellipse
        Area tongue = new Area(ellipse);
            // Remove all but what lies within the mouth
        tongue.intersect(openMouth);
        return tongue;
    }
    /**
     * This creates and returns the Area that forms Rambley's right fang. This 
     * uses three of the five points provided to the {@link 
     * #createRambleyMouthCurve(Ellipse2D, Point2D, Point2D, Point2D, Point2D, 
     * Point2D, Path2D) createRambleyMouthCurve} method ({@code pointM1}, {@code 
     * pointM2}, and {@code pointM3}, the three non-control points) to control 
     * the size and position of the fang.
     * 
     * @todo Add references to other related methods. Also, possibly make it so 
     * the fang's position is dependent on the width of the mouth (i.e. move the 
     * fang inwards when the mouth is thinner), though not completely to the 
     * point where the fang is visible when the mouth is thin.
     * 
     * @param mouthWidth A value between 0.0 and 1.0, inclusive, used to control 
     * the width of Rambley's open mouth.
     * @param mouthHeight A value between 0.0 and 1.0, inclusive, used to 
     * control the height of Rambley's open mouth.
     * @param pointM1 The Point2D object with the center point of the mouth 
     * curve (cannot be null).
     * @param pointM2 The Point2D object with the lowest point on the right side 
     * of the mouth curve (cannot be null).
     * @param pointM3 The Point2D object with the end point for the the right 
     * side of the mouth curve (cannot be null).
     * @param point1 A Point2D object to use to calculate the starting point for 
     * the curve that forms the fang, or null.
     * @param point2 A Point2D object to use to calculate the end point for the 
     * curve that forms the fang, or null.
     * @param pointC A Point2D object to use to calculate the control point for 
     * the curve that forms the fang, or null.
     * @param path A Path2D object to use to calculate the path for the fang, or 
     * null.
     * @return The area that forms Rambley's right fang.
     */
    private Area createRambleyFangShape(double mouthWidth, double mouthHeight, 
            Point2D pointM1, Point2D pointM2, Point2D pointM3, Point2D point1, 
            Point2D point2, Point2D pointC, Path2D path){
            // If the first of the two given scratch Point2D objects is null
        if (point1 == null)
            point1 = new Point2D.Double();
            // If the second of the two given scratch Point2D objects is null
        if (point2 == null)
            point2 = new Point2D.Double();
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // Set the location of the starting point of the curve to be 8.5 
            // pixels to the right of the left-most point on the mouth curve, 
            // and to have the left-most point's y-coordinate
        point1.setLocation(pointM3.getX()+8.5, pointM3.getY());
            // Set the location of the ending point to be 4 pixels to the right 
            // of the starting curve (the fang will be 8 pixels wide at its 
            // widest point), and 8 pixels below the starting point
        point2.setLocation(point1.getX()+4, pointM2.getY()+8);
            // Move the path to the starting point
        path.moveTo(point1.getX(), point1.getY());
            // Set the control point to be between the starting point and the 
            // end point, and at the bottom of the fang.
        pointC.setLocation((point1.getX()+point2.getX())/2.0, point2.getY());
            // Add a quadratic bezier curve between the starting point and the 
            // end point. Use the calculated control point
        path.quadTo(pointC.getX(), pointC.getY(), point2.getX(), point2.getY());
            // Draw a vertical line to back to the top
        path.lineTo(point2.getX(), point1.getY());
            // Close the path
        path.closePath();
            // Flip the path (which holds the left part of the fang) 
            // horizontally to form the right part of the fang and then add the 
            // right part of the fang to the path.
        path = mirrorPathHorizontally(path,point2.getX());
        return new Area(path);
    }
    /**
     * 
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill.
     * @param y The y-coordinate of the top-left corner of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     */
    protected void paintRambley(Graphics2D g, int x, int y, int w, int h){
            // Create a copy of the given graphics context
        g = (Graphics2D) g.create();
            // Get the scale factor for the x axis
        double scaleX = w/INTERNAL_RENDER_WIDTH;
            // Get the scale factor for the y axis
        double scaleY = h/INTERNAL_RENDER_HEIGHT;
            // If we are to ignore Rambley's aspect ratio
        if (isAspectRatioIgnored()){
                // Translate to the given x and y coordinates
            g.translate(x, y);
                // Scale it to fit the internal rendering size
            g.scale(scaleX, scaleY);
        } else {
                // Get the smaller of the two scale factors (this will be what 
                // we use to scale the x and y coordinates)
            double scale = Math.min(scaleX, scaleY);
                // Get the width of the area that will actually be rendered
            double width = Math.min(w, w*(scaleY/scaleX));
                // Get the height of the area that will actually be rendered
            double height = Math.min(h, h*(scaleX/scaleY));
                // Translate to center the image that will be rendered
            g.translate((w-width)/2.0+x, (h-height)/2.0+y);
                // Scale it, preserving the aspect ratio
            g.scale(scale,scale);
        }
            // Set the stroke to use to the normal stroke
        g.setStroke(getRambleyNormalStroke());
            // If the Rectangle2D scratch object has not been initialized yet
        if (rect == null)
            rect = new Rectangle2D.Double();
            // If the first Ellipse2D scratch object has not been initialized 
        if (ellipse1 == null)   // yet
            ellipse1 = new Ellipse2D.Double();
            // If the second Ellipse2D scratch object has not been initialized 
        if (ellipse2 == null)   // yet
            ellipse2 = new Ellipse2D.Double();
            // If the third Ellipse2D scratch object has not been initialized 
        if (ellipse3 == null)   // yet
            ellipse3 = new Ellipse2D.Double();
            // If the Path2D scratch object has not been initialized yet
        if (path == null)
            path = new Path2D.Double();
            // If the first Point2D scratch object has not been initialized yet
        if (point1 == null)
            point1 = new Point2D.Double();
            // If the second Point2D scratch object has not been initialized yet
        if (point2 == null)
            point2 = new Point2D.Double();
            // If the third Point2D scratch object has not been initialized yet
        if (point3 == null)
            point3 = new Point2D.Double();
            // If the fourth Point2D scratch object has not been initialized yet
        if (point4 == null)
            point4 = new Point2D.Double();
            // If the fifth Point2D scratch object has not been initialized yet
        if (point5 == null)
            point5 = new Point2D.Double();
            // If the sixth Point2D scratch object has not been initialized yet
        if (point6 == null)
            point6 = new Point2D.Double();
            // If the seventh Point2D scratch object has not been initialized 
        if (point7 == null)     // yet
            point7 = new Point2D.Double();
            // If the eighth Point2D scratch object has not been initialized yet
        if (point8 == null)
            point8 = new Point2D.Double();
            // If the iris Ellipse2D object has not been initialized yet
        if (iris == null)
            iris = new Ellipse2D.Double();
            // If the pupil Ellipse2D object has not been initialized yet
        if (pupil == null)
            pupil = new Ellipse2D.Double();
            // If the snout Ellipse2D object has not been initialized yet
        if (snout == null)
            snout = new Ellipse2D.Double();
        
            // Create the shape for Rambley's head (without his ears for now)
        Area headShape = createRambleyHeadArea(rect,path,ellipse1,ellipse2);
            // Get the bounds for the head, so that we can base the facial 
            // features off it
        Rectangle2D headBounds = headShape.getBounds2D();
            // Get the area for Rambley's right ear
        Area earR = getRambleyEar(headBounds.getCenterX()-84,headBounds.getMinY()-31.5,path);
            // Flip the area for Rambley's right ear to get his left ear
        Area earL = createHorizontallyFlippedArea(earR);
            // Get the area for the inner portion of Rambley's right ear
        Area earInR = getRambleyInnerEar(earR,RAMBLEY_INNER_EAR_SCALE,headShape);
            // Get the area for the inner portion of Rambley's left ear
        Area earInL = getRambleyInnerEar(earL,RAMBLEY_INNER_EAR_SCALE,headShape);
            // Add Rambley's right ear to the shape of his head
        headShape.add(earR);
            // Add Rambley's left ear to the shape of his head.
        headShape.add(earL);
            // Create the shape for the face markings around his eyes
        Area faceMarkings = createRambleyMaskFaceMarkings(headBounds,ellipse1,
                ellipse2,ellipse3,rect);
            // Create the area around Rambley's nose and mouth
        Area snoutArea = createRambleySnoutArea(headBounds,headShape,snout);
            // Create Rambley's right eyebrow (this will intersect with the 
            // other eye markings)
        Area eyeBrowR = createRambleyEyebrow(headBounds,ellipse2);
            // Flip to form the Left eyebrow (this will intersect with the 
            // other eye markings)
        Area eyeBrowL = createHorizontallyFlippedArea(eyeBrowR);
            // Create the area around Rambley's right eye
        Area eyeSurroundR = createRambleyEyeMarkings(ellipse2,snout,ellipse3,
                path,point1,point2,point3);
            // Flip to form the area around Rambley's left eye
        Area eyeSurroundL = createHorizontallyFlippedArea(eyeSurroundR);
            // Create the shape of Rambley's right eye
        Area eyeWhiteR = createRambleyEyeShape(headBounds,ellipse2,ellipse3,
                point1,point2,point3,ellipse1,rect,path,point4,point5,point6);
            // Flip to form the shape of Rambley's left eye
        Area eyeWhiteL = createHorizontallyFlippedArea(eyeWhiteR);
            // Create the shape of Rambley's nose
        Area nose = createRambleyNoseShape(snout,rect,ellipse1,path);
            // Set the location of the point to the bottom center of the nose
        point1.setLocation(rect.getCenterX(),rect.getMaxY());
            // Get the curve for Rambley's mouth, using the bottom center of the 
            // nose to position the mouth.
        mouthPath = createRambleyMouthCurve(snout,point1,point2,point3,point4,
                point5,mouthPath);
        
            // DEBUG: If we are showing the lines that make up Rambley 
        if (getShowsLines()){
            printShape("headBounds",headBounds);
            printShape("headShape",headShape);
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
//                    RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setColor(Color.RED);
            g.draw(headShape);
            g.setColor(Color.ORANGE);
            g.draw(headBounds);
            g.setColor(Color.GREEN);
            g.draw(snoutArea);
            g.setColor(Color.MAGENTA);
            g.draw(nose);
            g.setColor(Color.BLUE);
            g.draw(mouthPath);
        }
        
            // DEBUG: If we are not showing the lines that make up Rambley 
        if (!getShowsLines()){
                // Fill the shape of Rambley's head
            g.setColor(RAMBLEY_MAIN_BODY_COLOR);
            g.fill(headShape);
                // Fill the shape of Rambley's mask-like facial markings
            g.setColor(RAMBLEY_FACE_MARKINGS_COLOR);
            g.fill(faceMarkings);
                // Fill in Rambley's eyebrows
            g.setColor(RAMBLEY_EYEBROW_COLOR);
            g.fill(eyeBrowR);
            g.fill(eyeBrowL);
                // Set the color to use to Rambley's secondary body color
            g.setColor(RAMBLEY_SECONDARY_BODY_COLOR);
                // Fill in Rambley's snout area
            g.fill(snoutArea);
                // Fill in the area arround Rambley's eyes
            g.fill(eyeSurroundR);
            g.fill(eyeSurroundL);
                // Fill in the inner portion of Rambley's ears
            g.fill(earInR);
            g.fill(earInL);
                // Get the bounds for the right eye
            Rectangle2D eyeBounds = eyeWhiteR.getBounds2D();
                // Get the offset to use to shift the iris and pupil to their 
                // default positions
            double pupilX = ((headBounds.getCenterX()-25)-eyeBounds.getCenterX())*2;
                // Draw Rambley's right eye
            paintRambleyEye(g,eyeWhiteR,getRambleyRightEyeX(),getRambleyRightEyeY(),
                    eyeBounds,0,pupilX,iris,pupil);
                // Draw Rambley's left eye
            paintRambleyEye(g,eyeWhiteL,getRambleyLeftEyeX(),getRambleyLeftEyeY(),
                    eyeWhiteL.getBounds2D(),-pupilX,0,iris,pupil);
        }
        
            // If Rambley's open mouth's width and height are greater than zero
        if (getRambleyOpenMouthHeight() > 0 && getRambleyOpenMouthWidth() > 0){
                // Create the shape of Rambley's mouth when open
            Area openMouth = createRambleyOpenMouthShape(mouthPath, 
                    getRambleyOpenMouthWidth(), getRambleyOpenMouthHeight(), 
                    snout,point1,point2,point3,point4,point5,point6,rect,path);
                // Create the shape of Rambley's tongue
            Area tongue = createRambleyTongueShape(rect.getCenterX(),point4.getX(),
                    rect.getMaxY(),getRambleyOpenMouthWidth(), 
                    getRambleyOpenMouthHeight(),openMouth,ellipse1);
            
                // DEBUG: If we are showing the lines that make up Rambley 
            if (getShowsLines()){
                g.setColor(Color.LIGHT_GRAY);
                g.draw(path);
                g.setColor(RAMBLEY_MAIN_BODY_COLOR);
                g.draw(openMouth);
                g.setColor(Color.GREEN);
                g.draw(tongue);
            }
            
                // Create the shape of Rambley's right fang
            Area fang = createRambleyFangShape(getRambleyOpenMouthWidth(), 
                    getRambleyOpenMouthHeight(),point1,point2,point4,point7,
                    point8,point6,path);
                // If Rambley is evil
            if (isRambleyEvil())
                    // Add a left fang to the area of the right fang
                fang.add(createHorizontallyFlippedArea(fang));
                // If Rambley's fang should be on the left
            else if (isRambleyFangOnLeft())
                    // Flip the fang to get the left fang
                fang = createHorizontallyFlippedArea(fang);
                // Remove all but what lies within Rambley's mouth from the 
            fang.intersect(openMouth);      // fang(s)

                // DEBUG: If we are showing the lines that make up Rambley 
            if (getShowsLines()){
                printShape("fang",fang);
                g.setColor(Color.PINK);
                g.draw(path);
                g.setColor(Color.WHITE);
                g.draw(fang);
            }
            
                // DEBUG: If we are not showing the lines that make up Rambley 
            if (!getShowsLines()){
                    // Fill in the inside of Rambley's mouth
                g.setColor(RAMBLEY_MOUTH_COLOR);
                g.fill(openMouth);
                    // Fill in Rambley's tongue
                g.setColor(RAMBLEY_TONGUE_COLOR);
                g.fill(tongue);
                    // Draw the outline for Rambley's tongue
                g.setColor(RAMBLEY_TONGUE_OUTLINE_COLOR);
                g.draw(tongue);
                    // Fill in Rambley's fang(s)
                g.setColor(RAMBLEY_TEETH_COLOR);
                g.fill(fang);
                    // Draw the outline for Rambley's fang(s)
                g.setColor(RAMBLEY_TEETH_OUTLINE_COLOR);
                g.draw(fang);
                    // Set the stroke to Rambley's detail stroke
                g.setStroke(getRambleyDetailStroke());
                    // Draw Rambley's mouth
                g.setColor(RAMBLEY_MOUTH_OUTLINE_COLOR);
                g.draw(openMouth);
            }
        }
        
            // DEBUG: If we are not showing the lines that make up Rambley 
        if (!getShowsLines()){
                // Set the stroke to Rambley's detail stroke
            g.setStroke(getRambleyDetailStroke());
                // Draw Rambley's mouth
            g.setColor(RAMBLEY_MOUTH_OUTLINE_COLOR);
            g.draw(mouthPath);
                // Set the stroke to Rambley's normal stroke
            g.setStroke(getRambleyNormalStroke());
                // Draw Rambley's nose
            g.setColor(RAMBLEY_NOSE_COLOR);
            g.fill(nose);
                // Set the color to Rambley's main outline color
            g.setColor(RAMBLEY_OUTLINE_COLOR);
                // Set the stroke to Rambley's outline stroke
            g.setStroke(getRambleyOutlineStroke());
                // Draw the outline for Rambley's head
            g.draw(headShape);
                // Set the stroke to Rambley's detail stroke
            g.setStroke(getRambleyDetailStroke());
                // Draw the outline for Rambley's inner right ear
            g.draw(earInR);
                // Draw the outline for Rambley's inner left ear
            g.draw(earInL);
                // Set the color to the outline color for Rambley's nose
            g.setColor(RAMBLEY_NOSE_OUTLINE_COLOR);
                // Draw the outline for Rambley's nose
            g.draw(nose);
        }
        
        g.dispose();
    }
    /**
     * This returns a String representation of this {@code RambleyIcon}. 
     * This method is primarily intended to be used only for debugging purposes, 
     * and the content and format of the returned String may vary between 
     * implementations.
     * @return A String representation of this {@code RambleyIcon}.
     */
    protected String paramString(){
        return "flags="+getFlags()+
                ",dotSize="+getBackgroundDotSize()+
                ",dotSpacing="+getBackgroundDotSpacing()+
                ",lineSpacing="+getPixelGridLineSpacing()+
                ",rightEye=("+getRambleyRightEyeX()+","+getRambleyRightEyeY()+")"+
                ",leftEye=("+getRambleyLeftEyeX()+","+getRambleyLeftEyeY()+")"+
                ",mouthOpenHeight="+getRambleyOpenMouthHeight()+
                ",mouthOpenWidth="+getRambleyOpenMouthWidth();
    }
    @Override
    public String toString(){
        return getClass().getName()+"["+paramString()+"]";
    }
    /**
     * This returns an array of all the objects currently registered as 
     * <code><em>Foo</em>Listener</code>s on this icon. 
     * <code><em>Foo</em>Listener</code>s are registered via the 
     * <code>add<em>Foo</em>Listener</code> method. <p>
     * 
     * The listener type can be specified using a class literal, such as 
     * <code><em>Foo</em>Listener.class</code>. If no such listeners exist, then 
     * an empty array will be returned.
     * @param <T> The type of {@code EventListener} being requested.
     * @param listenerType The type of listeners being requested. This should 
     * be an interface that descends from {@code EventListener}.
     * @return An array of the objects registered as the given listener type on 
     * this icon, or an empty array if no such listeners have been added.
     */
    @SuppressWarnings("unchecked")
    public <T extends EventListener> T[] getListeners(Class<T> listenerType){
            // If we're getting the PropertyChangeListeners
        if (listenerType == PropertyChangeListener.class)
            return (T[])getPropertyChangeListeners();
        else
            return listenerList.getListeners(listenerType);
    }
    /**
     * This adds the given {@code ChangeListener} to this icon.
     * @param l The listener to add.
     * @see #removeChangeListener(ChangeListener) 
     * @see #getChangeListeners() 
     */
    public void addChangeListener(ChangeListener l){
        if (l != null)          // If the listener is not null
            listenerList.add(ChangeListener.class, l);
    }
    /**
     * This removes the given {@code ChangeListener} from this icon.
     * @param l The listener to remove.
     * @see #addChangeListener(ChangeListener) 
     * @see #getChangeListeners() 
     */
    public void removeChangeListener(ChangeListener l){
        listenerList.remove(ChangeListener.class, l);
    }
    /**
     * This returns an array containing all the {@code ChangeListener}s that 
     * have been added to this icon.
     * @return An array containing the {@code ChangeListener}s that have been 
     * added, or an empty array if none have been added.
     * @see #addChangeListener(ChangeListener) 
     * @see #removeChangeListener(ChangeListener) 
     */
    public ChangeListener[] getChangeListeners(){
        return listenerList.getListeners(ChangeListener.class);
    }
    /**
     * This is used to notify the {@code ChangeListener}s that the state of this  
     * icon has changed.
     */
    protected void fireStateChanged(){
            // This constructs the evet to fire
        ChangeEvent evt = new ChangeEvent(this);
            // A for loop to go through the change listeners
        for (ChangeListener l : listenerList.getListeners(ChangeListener.class)){
            if (l != null)  // If the listener is not null
                l.stateChanged(evt);
        }
    }
    /**
     * This adds a {@code PropertyChangeListener} to this painter. This listener 
     * is registered for all bound properties of this painter. 
     * @param l The listener to be added.
     * @see #addPropertyChangeListener(String, PropertyChangeListener) 
     * @see #removePropertyChangeListener(PropertyChangeListener) 
     * @see #getPropertyChangeListeners() 
     */
    public void addPropertyChangeListener(PropertyChangeListener l){
        changeSupport.addPropertyChangeListener(l);
    }
    /**
     * This adds a {@code PropertyChangeListener} to this painter that listens for 
     * a specific property.
     * @param propertyName The name of the property to listen for.
     * @param l The listener to be added.
     * @see #addPropertyChangeListener(PropertyChangeListener) 
     * @see #removePropertyChangeListener(String, PropertyChangeListener) 
     * @see #getPropertyChangeListeners(String) 
     */
    public void addPropertyChangeListener(String propertyName, 
            PropertyChangeListener l){
        changeSupport.addPropertyChangeListener(propertyName, l);
    }
    /**
     * This removes a {@code PropertyChangeListener} from this painter. This 
     * method should be used to remove {@code PropertyChangeListener}s that were 
     * registered for all bound properties of this painter. 
     * @param l The listener to be removed.
     * @see #addPropertyChangeListener(PropertyChangeListener) 
     * @see #removePropertyChangeListener(String, PropertyChangeListener) 
     * @see #getPropertyChangeListeners() 
     */
    public void removePropertyChangeListener(PropertyChangeListener l){
        changeSupport.removePropertyChangeListener(l);
    }
    /**
     * This removes a {@code PropertyChangeListener} to this painter that listens 
     * for a specific property. This method should be used to remove {@code 
     * PropertyChangeListener}s that were registered for a specific property
     * @param propertyName The name of the property.
     * @param l The listener to be removed.
     * @see #removePropertyChangeListener(PropertyChangeListener)
     * @see #addPropertyChangeListener(String, PropertyChangeListener) 
     * @see #getPropertyChangeListeners(String) 
     */
    public void removePropertyChangeListener(String propertyName, 
            PropertyChangeListener l){
        changeSupport.removePropertyChangeListener(propertyName, l);
    }
    /**
     * This returns an array of all {@code PropertyChangeListener}s that are 
     * registered on this painter.
     * @return An array of the {@code PropertyChangeListener}s that have been 
     * added, or an empty array if no listeners have been added.
     * @see #getPropertyChangeListeners(String) 
     * @see #addPropertyChangeListener(PropertyChangeListener) 
     * @see #removePropertyChangeListener(PropertyChangeListener) 
     */
    public PropertyChangeListener[] getPropertyChangeListeners(){
        return changeSupport.getPropertyChangeListeners();
    }
    /**
     * This returns an array of all {@code PropertyChangeListener}s that are 
     * registered on this painter for a specific property.
     * @param propertyName The name of the property.
     * @return An array of the {@code PropertyChangeListener}s that have been 
     * added for the specified property, or an empty array if no listeners have 
     * been added or the specified property is null.
     * @see #getPropertyChangeListeners() 
     * @see #addPropertyChangeListener(String, PropertyChangeListener) 
     * @see #removePropertyChangeListener(String, PropertyChangeListener) 
     */
    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName){
        return changeSupport.getPropertyChangeListeners(propertyName);
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name,
     * old value, and new value. This method is for {@code Object} properties.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, Object oldValue, 
            Object newValue){
            // If the PropertyChangeSupport has been initialized
        if (changeSupport != null)  
            changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name, 
     * old value, and new value. This method is for {@code boolean} properties.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, boolean oldValue, 
            boolean newValue){
            // If the PropertyChangeSupport has been initialized
        if (changeSupport != null)  
            changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name and 
     * new value. This method is for {@code boolean} properties and the old 
     * value is assumed to be the inverse of the new value.
     * @param propertyName The name of the property.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, boolean newValue){
        firePropertyChange(propertyName, !newValue, newValue);
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name, 
     * old value, and new value. This method is for character properties.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, char oldValue, 
            char newValue){
        firePropertyChange(propertyName,Character.valueOf(oldValue),
                Character.valueOf(newValue));
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name, 
     * old value, and new value. This method is for integer properties.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, int oldValue, 
            int newValue){
            // If the PropertyChangeSupport has been initialized
        if (changeSupport != null)  
            changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name, 
     * old value, and new value. This method is for byte properties.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, byte oldValue, 
            byte newValue){
        firePropertyChange(propertyName,Byte.valueOf(oldValue),
                Byte.valueOf(newValue));
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name, 
     * old value, and new value. This method is for short properties.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, short oldValue, 
            short newValue){
        firePropertyChange(propertyName,Short.valueOf(oldValue),
                Short.valueOf(newValue));
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name, 
     * old value, and new value. This method is for long properties.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, long oldValue, 
            long newValue){
        firePropertyChange(propertyName,Long.valueOf(oldValue),
                Long.valueOf(newValue));
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name, 
     * old value, and new value. This method is for float properties.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, float oldValue, 
            float newValue){
        firePropertyChange(propertyName,Float.valueOf(oldValue),
                Float.valueOf(newValue));
    }
    /**
     * This fires a {@code PropertyChangeEvent} with the given property name, 
     * old value, and new value. This method is for double properties.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    protected void firePropertyChange(String propertyName, double oldValue, 
            double newValue){
        firePropertyChange(propertyName,Double.valueOf(oldValue),
                Double.valueOf(newValue));
    }
    /**
     * This calculates the control point for a quadratic bezier curve that 
     * starts at {@code p0}, passes through {@code p1}, and ends at {@code p2}, 
     * and adds the resulting curve to the given path.
     * @param p0 The starting point of the curve.
     * @param p1 The point to pass through.
     * @param p2 The ending point of the curve.
     * @param pC A Point2D object to store the control point in, or null.
     * @param path The Path2D object to add the curve to.
     * @return  The control point for the curve.
     * @see getQuadBezierControlPoint
     */
    protected static Point2D addQuadBezierCurve(Point2D p0, Point2D p1, 
            Point2D p2, Point2D pC, Path2D path){
            // Get the control point for the curve
        pC = getQuadBezierControlPoint(p0,p1,p2,pC);
            // Add the curve to the path
        path.quadTo(pC.getX(), pC.getY(), p2.getX(), p2.getY());
        return pC;
    }
    
    
    
        // Some debug settings that will be removed when finished
    
    boolean getShowsLines(){
        return showLines;
    }
    
    void setShowsLines(boolean value){
        if (showLines == value)
            return;
        showLines = value;
        fireStateChanged();
    }
    
    boolean getABTesting(){
        return abTesting;
    }
    
    void setABTesting(boolean value){
        if (abTesting == value)
            return;
        abTesting = value;
        fireStateChanged();
    }
    
    private boolean showLines = false;
    private boolean abTesting = false;
    private double testDouble1 = 1.0;
    private double testDouble2 = 1.0;
    private double testDouble3 = 1.0;
    private double testDouble4 = 1.0;
    private double testDouble5 = 1.0;
    private double testDouble6 = 0.0;
    
    double getTestDouble1(){
        return testDouble1;
    }
    
    void setTestDouble1(double value){
        testDouble1 = value;
        fireStateChanged();
    }
    
    double getTestDouble2(){
        return testDouble2;
    }
    
    void setTestDouble2(double value){
        testDouble2 = value;
        fireStateChanged();
    }
    
    double getTestDouble3(){
        return testDouble3;
    }
    
    void setTestDouble3(double value){
        testDouble3 = value;
        fireStateChanged();
    }
    
    double getTestDouble4(){
        return testDouble4;
    }
    
    void setTestDouble4(double value){
        testDouble4 = value;
        fireStateChanged();
    }
    
    double getTestDouble5(){
        return testDouble5;
    }
    
    void setTestDouble5(double value){
        testDouble5 = value;
        fireStateChanged();
    }
    
    double getTestDouble6(){
        return testDouble6;
    }
    
    void setTestDouble6(double value){
        testDouble6 = value;
        fireStateChanged();
    }
    
    static void printPathIterator(PathIterator pathItr){
        HashMap<Integer,String> segTypes = new HashMap<>();
        segTypes.put(PathIterator.SEG_MOVETO, "SEG_MOVETO");
        segTypes.put(PathIterator.SEG_LINETO, "SEG_LINETO");
        segTypes.put(PathIterator.SEG_QUADTO, "SEG_QUADTO");
        segTypes.put(PathIterator.SEG_CUBICTO,"SEG_CUBICTO");
        segTypes.put(PathIterator.SEG_CLOSE,  "SEG_CLOSE");
        while(!pathItr.isDone()){
            double[] coords = new double[6];
            int type = pathItr.currentSegment(coords);
            System.out.printf("%11s: (%9.5f, %9.5f), (%9.5f, %9.5f), (%9.5f, %9.5f)%n",
                    segTypes.get(type),coords[0],coords[1],coords[2],coords[3],coords[4],coords[5]);
            pathItr.next();
        }
        System.out.println();
    }
    
    static void printPathIterator(Shape shape){
        printPathIterator(shape.getPathIterator(null));
    }
    
    static void printShape(String shapeName, Shape shape){
        Rectangle2D bounds = shape.getBounds2D();
        System.out.println(shapeName+": " + bounds + ", Center=(" + 
                bounds.getCenterX() + ", " + bounds.getCenterY()+"), Max=("+
                bounds.getMaxX() + ", " + bounds.getMaxY()+")");
    }
}
