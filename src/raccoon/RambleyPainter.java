/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import java.awt.*;
import java.awt.geom.*;
import java.beans.*;
import java.util.EventListener;
import java.util.HashMap;
import java.util.function.DoubleUnaryOperator;
import javax.swing.*;
import javax.swing.event.*;

/*
I'm thinking that RambleyIcon should be 256x256, with Rambley himself being 
about 240 pixels tall with an 8 pixel gap above and below him. The background 
should be the one from his screens, and it should be toggleable. The pixel 
effect should be toggleable. The icon MAY have different states that Rambley 
can be in.
*/

/**
 * This is a Painter that paints Rambley the Raccoon from Indigo Park. 
 * 
 * Special thanks to AnimalWave on Discord for help with the equations that the 
 * code is based off of.
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
     * This is the default resolution for the {@link #getLineIntersection(
     * double, double, double, double, DoubleUnaryOperator, DoubleUnaryOperator, 
     * Point2D) getLineIntersection} method when a resolution is not specified.
     * @see getLineIntersection(double, double, double, double, 
     * DoubleUnaryOperator, DoubleUnaryOperator, int, Point2D)
     * @see getLineIntersection(double, double, double, double, 
     * DoubleUnaryOperator, DoubleUnaryOperator, Point2D)
     */
    protected static final int DEFAULT_LINE_INTERSECTION_RESOLUTION = 25;
    /**
     * This is the width and height of the background polka dots.
     */
    protected static final double BACKGROUND_DOT_SIZE = 8;
    /**
     * This contains half of the {@link BACKGROUND_DOT_SIZE size} of the 
     * background polka dots. This is used for calculating the location of the 
     * background polka dots when using their center coordinates to position 
     * them.
     */
    private static final double BACKGROUND_DOT_HALF_SIZE = 
            BACKGROUND_DOT_SIZE/2.0;
    /**
     * This is the diagonal spacing between the centers of the background polka 
     * dots. That is to say, the center of each background polka dot is {@code 
     * BACKGROUND_DOT_SPACING} pixels to the left and {@code 
     * BACKGROUND_DOT_SPACING} pixels below the center of another background 
     * polka dot.
     * 
     * @todo Rework the documentation for this constant.
     */
    protected static final double BACKGROUND_DOT_SPACING = 12;
    /**
     * This is the spacing between the lines in the pixel grid. For the vertical 
     * lines, this is the horizontal spacing. For the horizontal lines, this is 
     * the vertical spacing.
     */
    protected static final double PIXEL_GRID_SPACING = 5;
    /**
     * The offset for the x-coordinate of the top-left corner of Rambley.
     */
    protected static final double RAMBLEY_X_OFFSET = 27;
    /**
     * The offset for the y-coordinate of the top-left corner of Rambley.
     */
    protected static final double RAMBLEY_Y_OFFSET = 60;
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
     * 
     */
    private static final double RAMBLEY_EAR_X_OFFSET = 1.5;
    
    private static final double RAMBLEY_EAR_Y_OFFSET = getRambleyEarOffset(0);
    
    private static final double RAMBLEY_EAR_MULTIPLIER = 42;
    
    private static final double RAMBLEY_EAR_HEIGHT = 1.8 * RAMBLEY_EAR_MULTIPLIER;
    
    private static final double RAMBLEY_EAR_TIP_Y_OFFSET = 1.0;
    
    private static final double RAMBLEY_EAR_TIP_ROUNDING = 2.0;
    /**
     * This the value to use when scaling the shapes that make up Rambley's ears 
     * in order to get the inner portion of his ears.
     */
    private static final double RAMBLEY_INNER_EAR_SCALE = 2/3.0;
    /**
     * This converts the given y-coordinate in the graphics coordinate system 
     * @param y
     * @return 
     */
    private static double graphicsToEarEquY(double y){
        y = RAMBLEY_EAR_HEIGHT - y;
        y /= RAMBLEY_EAR_MULTIPLIER;
        return y + RAMBLEY_EAR_Y_OFFSET;
    }
    
    private static double graphicsToEarEquX(double x){
        x /= RAMBLEY_EAR_MULTIPLIER;
        return RAMBLEY_EAR_X_OFFSET-x;
    }
    
    private static double earEquToGraphicsY(double y){
        y -= RAMBLEY_EAR_Y_OFFSET;
        y *= RAMBLEY_EAR_MULTIPLIER;
        return (RAMBLEY_EAR_HEIGHT-y);
    }
    
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
     * This stores the flags that are set initially when a RambleyPainter is 
     * first constructed.
     */
    private static final int DEFAULT_FLAG_SETTINGS = PAINT_BACKGROUND_FLAG | 
            PAINT_PIXEL_GRID_FLAG | PAINT_BORDER_AND_SHADOW_FLAG;
    // Insert property names for the properties represented by the flags here
//    /**
//     * This identifies that a change has been made to whether the background 
//     * should be painted.
//     */
//    public static final String BACKGROUND_PAINTED_PROPERTY_CHANGED = 
//            "BackgroundPaintedPropertyChanged";
    
    
    
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
     * The x component for the location of the center of Rambley's right iris and 
     * pupil. 0.5 is center, 0.0 is the left-most bounds for Rambley's right eye 
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
     * pupil. 0.5 is center, 0.0 is the left-most bounds for Rambley's left eye 
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
    private Ellipse2D ellipse = null;
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
    
    
    
    public RambleyPainter(){
        flags = DEFAULT_FLAG_SETTINGS;
        eyeRightX = eyeRightY = eyeLeftX = eyeLeftY = 0.5;
        changeSupport = new PropertyChangeSupport(this);
    }
    
    public RambleyPainter(int flags){
        this();
        RambleyPainter.this.setFlags(flags);
    }
    /**
     * This returns an integer storing the flags used to store the settings for 
     * this painter and control its state.
     * @return An integer containing the flags for this painter.
     * @see #getFlag
     * @see #setFlag
     * @see #toggleFlag
     */
    public int getFlags(){
        return flags;
    }
    /**
     * 
     * @param flags
     * @return This {@code RambleyPainter}.
     */
    public RambleyPainter setFlags(int flags){
            // If the flags would change
        if (flags != this.flags){
            this.flags = flags;
            fireStateChanged();
        }
        return this;
    }
    /**
     * This returns whether the given flag is set for this painter.
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
     * of the one seen in Indigo Park will be painted behind Rambley. The 
     * background will consist of {@link BACKGROUND_DOT_COLOR dark blue} 
     * diamond-shaped polka dots over a gradient going from {@link 
     * BACKGROUND_GRADIENT_COLOR dark blue} at the top to {@link 
     * BACKGROUND_COLOR light blue} at the bottom. The default value for this is 
     * {@code true}.
     * 
     * @todo Add references to other related methods.
     * 
     * @return Whether the background will be painted.
     */
    public boolean isBackgroundPainted(){
        return getFlag(PAINT_BACKGROUND_FLAG);
    }
    public RambleyPainter setBackgroundPainted(boolean enabled){
        return setFlag(PAINT_BACKGROUND_FLAG,enabled);
    }
    
    public boolean isPixelGridPainted(){
        return getFlag(PAINT_PIXEL_GRID_FLAG);
    }
    
    public RambleyPainter setPixelGridPainted(boolean enabled){
        return setFlag(PAINT_PIXEL_GRID_FLAG,enabled);
    }
    
    public boolean isRambleyEvil(){
        return getFlag(EVIL_RAMBLEY_FLAG);
    }
    
    public RambleyPainter setRambleyEvil(boolean enabled){
        return setFlag(EVIL_RAMBLEY_FLAG,enabled);
    }
    
    public boolean isAspectRatioIgnored(){
        return getFlag(IGNORE_ASPECT_RATIO_FLAG);
    }
    
    public RambleyPainter setAspectRatioIgnored(boolean enabled){
        return setFlag(IGNORE_ASPECT_RATIO_FLAG,enabled);
    }
    
        // Will be public when name is finalized
    protected boolean isBorderAndShadowPainted(){
        return getFlag(PAINT_BORDER_AND_SHADOW_FLAG);
    }
    
    protected RambleyPainter setBorderAndShadowPainted(boolean enabled){
        return setFlag(PAINT_BORDER_AND_SHADOW_FLAG,enabled);
    }
    
    
    
    /**
     * This returns the gradient to use to paint the background gradient.
     * @param x
     * @param y
     * @param w
     * @param h
     * @return 
     */
    protected Paint getBackgroundGradient(double x, double y, double w, double h){
        float x1 = (float)((w / 2.0)+x);
//            return new LinearGradientPaint(x1,y,x1,y+h-1,
//                    new float[]{0.0f,0.1f},
//                    new Color[]{BACKGROUND_GRADIENT_TOP_COLOR,
//                        BACKGROUND_GRADIENT_BOTTOM_COLOR});
        return new GradientPaint(x1,(float)y,BACKGROUND_GRADIENT_COLOR,
                x1,(float)(y+h-1),BACKGROUND_GRADIENT_COLOR_2);
    }
    
    protected double getBackgroundDotOffset(double size){
        return (size%BACKGROUND_DOT_SPACING)/2.0;
    }
    
    protected double getBackgroundDotOffsetX(double width){
        return getBackgroundDotOffset(width);
    }
    
    protected double getBackgroundDotOffsetY(double height){
        return getBackgroundDotOffset(height);
    }
    
    protected double getPixelGridOffset(double size){
        return ((size-1)%PIXEL_GRID_SPACING)/2.0;
    }
    
    protected Path2D getPixelGrid(double x, double y, double w, double h, Path2D path){
        if (path == null)
            path = new Path2D.Double();
        else
            path.reset();
        double x2 = x+w;
        double y2 = y+h;
        for (double y1 = getPixelGridOffset(h); y1 <= h; y1+=PIXEL_GRID_SPACING){
            path.moveTo(x, y1+y);
            path.lineTo(x2, y1+y);
        }
        for (double x1 = getPixelGridOffset(w); x1 <= w; x1+=PIXEL_GRID_SPACING){
            path.moveTo(x1+x, y);
            path.lineTo(x1+x, y2);
        }
        return path;
    }
    
    private Path2D getPixelGrid(double x, double y, double w, double h){
        if (pixelGrid == null)
            pixelGrid = new Path2D.Double();
        return getPixelGrid(x,y,w,h,pixelGrid);
    }
    /**
     * This constructs a stroke to use when rendering Rambley
     * @param width The line width
     * @return 
     */
    protected BasicStroke getRambleyStroke(float width){
        return new BasicStroke(width,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    }
    
    protected BasicStroke getRambleyNormalStroke(){
        if (normalStroke == null)
            normalStroke = getRambleyStroke(1.0f);
        return normalStroke;
    }
    
    protected BasicStroke getRambleyDetailStroke(){
        if (detailStroke == null)
            detailStroke = getRambleyStroke(2.0f);
        return detailStroke;
    }
    
    protected BasicStroke getRambleyOutlineStroke(){
        if (outlineStroke == null)
            outlineStroke = getRambleyStroke(3.0f);
        return outlineStroke;
    }
    
    

    @Override
    public void paint(Graphics2D g, Component c, int width, int height) {
        if (g == null || width <= 0 || height <= 0)
            return;
        g = configureGraphics((Graphics2D)g.create());
        Graphics2D tempG = (Graphics2D) g.create();
        if (isBackgroundPainted()){
            paintBackground(tempG,0,0,width,height);
            tempG.dispose();
            tempG = (Graphics2D) g.create();
        }
        paintRambley(tempG,0,0,width,height);
        tempG.dispose();
        if (isPixelGridPainted()){
            tempG = (Graphics2D) g.create();
            paintPixelGrid(tempG,0,0,width,height);
            tempG.dispose();
        }
    }
    
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
    
    protected Path2D getBackgroundDot(RectangularShape rect, Path2D path){
        if (path == null)
            path = new Path2D.Double();
        else
            path.reset();
        path.moveTo(rect.getCenterX(), rect.getMinY());
        path.lineTo(rect.getMinX(), rect.getCenterY());
        path.lineTo(rect.getCenterX(), rect.getMaxY());
        path.lineTo(rect.getMaxX(), rect.getCenterY());
        path.closePath();
        return path;
    }
    
    protected Path2D getBackgroundDot(double x, double y, Path2D path, Rectangle2D rect){
        if (rect == null)
            rect = new Rectangle2D.Double();
        rect.setFrameFromCenter(x, y, x-BACKGROUND_DOT_HALF_SIZE, y-BACKGROUND_DOT_HALF_SIZE);
        return getBackgroundDot(rect,path);
    }
    
    protected void paintBackground(Graphics2D g, int x, int y, int w, int h){
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(x, y, w, h);
        Graphics2D tempG = (Graphics2D) g.create();
        paintBackgroundDots(tempG,x,y,w,h);
        tempG.dispose();
        g.setPaint(getBackgroundGradient(x,y,w,h));
        g.fillRect(x, y, w, h);
    }
    
    protected void paintBackgroundDots(Graphics2D g, int x, int y, int w, int h){
        g.clipRect(x, y, w, h);
        g.setColor(BACKGROUND_DOT_COLOR);
        if (rect == null)
            rect = new Rectangle2D.Double();
        double x1 = getBackgroundDotOffsetX(w)+x;
        double y1 = getBackgroundDotOffsetY(h)+y;
        for (int i = 0; (i * BACKGROUND_DOT_SPACING) <= h; i++){
            double yDot = (i * BACKGROUND_DOT_SPACING)+y1;
            for (double xDot = BACKGROUND_DOT_SPACING * (i % 2); xDot <= w; 
                    xDot+=BACKGROUND_DOT_SPACING+BACKGROUND_DOT_SPACING){
                path = getBackgroundDot(xDot+x1,yDot,path,rect);
                g.fill(path);
            }
        }
    }
    
    protected void paintPixelGrid(Graphics2D g, int x, int y, int w, int h, Shape mask){
        g.clipRect(x, y, w, h);
        g.setColor(PIXEL_GRID_COLOR);
            // Turn off antialiasing for the pixel grid
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
//        if (mask != null)
//            g.clip(mask);
        g.draw(getPixelGrid(0,0,w,h));
    }
    
    protected void paintPixelGrid(Graphics2D g, int x, int y, int w, int h){
        paintPixelGrid(g,x,y,w,h,null);
    }
    /**
     * 
     * @param x The x-coordinate of the center of Rambley's eye.
     * @param y The y-coordinate of the center of Rambley's eye.
     * @param iris An Ellipse2D object to get the iris
     * @param pupil An Ellipse2D object to get the pupil
     */
    protected void setRambleyEyeLocation(double x, double y, Ellipse2D iris, 
            Ellipse2D pupil){
        iris.setFrameFromCenter(x,y,
                x+RAMBLEY_IRIS_HALF_SIZE,y+RAMBLEY_IRIS_HALF_SIZE);
        x = iris.getCenterX();
        y = iris.getCenterY();
        pupil.setFrameFromCenter(x, y, 
                x+RAMBLEY_PUPIL_HALF_SIZE, y+RAMBLEY_PUPIL_HALF_SIZE);
    }
    /**
     * 
     * @param g
     * @param eyeWhite The shape to use to paint the eye white
     * @param iris The iris 
     * @param pupil The pupil
     */
    protected void paintRambleyEye(Graphics2D g,Shape eyeWhite,Ellipse2D iris,Ellipse2D pupil){
        Graphics2D tempG = (Graphics2D) g.create();
        tempG.clip(eyeWhite);
        tempG.setColor(RAMBLEY_EYE_WHITE_COLOR);
        tempG.fill(eyeWhite);
        tempG.setColor((isRambleyEvil())?EVIL_RAMBLEY_IRIS_COLOR:RAMBLEY_IRIS_COLOR);
        tempG.fill(iris);
        tempG.setColor(RAMBLEY_PUPIL_COLOR);
        tempG.fill(pupil);
        tempG.setColor((isRambleyEvil())?EVIL_RAMBLEY_IRIS_OUTLINE_COLOR:
                RAMBLEY_IRIS_OUTLINE_COLOR);
        tempG.setStroke(getRambleyDetailStroke());
        tempG.draw(iris);
        tempG.draw(pupil);
        tempG.dispose();
        Stroke s = g.getStroke();
        g.setStroke(getRambleyOutlineStroke());
        g.setColor(RAMBLEY_EYE_OUTLINE_COLOR);
        g.draw(eyeWhite);
        g.setStroke(s);
    }
    /**
     * 
     * @param g
     * @param eyeWhite The shape to use to paint the eye white
     * @param x The x-coordinate of the center of Rambley's iris and pupil.
     * @param y The y-coordinate of the center of Rambley's iris and pupil.
     * @param iris The iris
     * @param pupil The pupil
     */
    protected void paintRambleyEye(Graphics2D g,Shape eyeWhite,double x, 
            double y,Ellipse2D iris,Ellipse2D pupil){
        if (iris == null)
            iris = new Ellipse2D.Double();
        if (pupil == null)
            pupil = new Ellipse2D.Double();
        setRambleyEyeLocation(x,y,iris,pupil);
        paintRambleyEye(g,eyeWhite,iris,pupil);
    }
    
    private void paintRambleyEye(Graphics2D g,Shape eyeWhite,double x, double y){
        if (iris == null)
            iris = new Ellipse2D.Double();
        if (pupil == null)
            pupil = new Ellipse2D.Double();
        paintRambleyEye(g,eyeWhite,x,y,iris,pupil);
    }
    
    private static double getRambleyEarOffset(double x){
        return -((Math.log(1.5-x)/Math.log(2))-5.2)/8;
    }
    
    protected double getRambleyUpperEarY(double x){
        x = graphicsToEarEquX(x);
        if (x <= 0)
            return earEquToGraphicsY(0);
        return earEquToGraphicsY((Math.log(x)/(10*Math.log(2)))+2.3);
    }
    /**
     * 2^(10y-23)
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param y
     * @return 
     */
    protected double getRambleyUpperEarX(double y){
        y = graphicsToEarEquY(y);
        return earEquToGraphicsX(Math.pow(2, 10*y-23));
    }
    
    protected double getRambleyLowerEarY(double x){
         x = graphicsToEarEquX(x);
        return earEquToGraphicsY(getRambleyEarOffset(x));
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
        y = graphicsToEarEquY(y);
        return earEquToGraphicsX(-Math.pow(2, -8*y+5.2)+1.5);
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
        y -= RAMBLEY_EAR_TIP_Y_OFFSET;
        y = graphicsToEarEquY(y) - 2.4;
        if (y >= 0)
            return earEquToGraphicsX(0);
        return earEquToGraphicsX(Math.max((0.01/y)+1.5, 0));
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
        x = graphicsToEarEquX(x)-1.5;
        double y = (x >= 0) ? 0 : (0.01/x);
        return Math.min(earEquToGraphicsY(y + 2.4) + RAMBLEY_EAR_TIP_Y_OFFSET, 
                RAMBLEY_EAR_HEIGHT);
    }
    
    private DoubleUnaryOperator getRambleyUpperEarEquation(){
        if (earEquationU == null)
            earEquationU = (double operand) -> getRambleyUpperEarY(operand);
        return earEquationU;
    }
    
    private DoubleUnaryOperator getRambleyLowerEarEquation(){
        if (earEquationL == null)
            earEquationL = (double operand) -> getRambleyLowerEarY(operand);
        return earEquationL;
    }
    
    private DoubleUnaryOperator getRambleyEarTipEquation(){
        if (earEquationT == null)
            earEquationT = (double operand) -> getRambleyEarTipY(operand);
        return earEquationT;
    }
    
    protected Point2D getRambleyEarUpperTip(double x, double y, Point2D point){
        return getLineIntersection(x,y,9,10.5,
                getRambleyUpperEarEquation(),getRambleyEarTipEquation(),point);
    }
    
    protected Point2D getRambleyEarLowerTip(double x, double y, Point2D point){
        return getLineIntersection(x,y,getRambleyEarTipX(RAMBLEY_EAR_HEIGHT),1,
                getRambleyEarTipEquation(),getRambleyLowerEarEquation(),point);
    }
    
    private void addQuadBezierCurve(Point2D p0, Point2D p1, Point2D p2, Point2D pC, Path2D path){
        pC = getQuadBezierControlPoint(p0,p1,p2,pC);
        path.quadTo(pC.getX(), pC.getY(), p2.getX(), p2.getY());
    }
    
    protected Area getRambleyEar(double x, double y, Path2D path){
        if (path == null)
            path = new Path2D.Double();
        else
            path.reset();
        double y1 = RAMBLEY_EAR_HEIGHT+y;
        double x1 = Math.max(getRambleyUpperEarX(RAMBLEY_EAR_HEIGHT), 
                getRambleyLowerEarX(RAMBLEY_EAR_HEIGHT))+x;
        
            // The point of intersection between the upper portion of the ear 
            // and the tip of the ear
        point7 = getRambleyEarUpperTip(x,y,point7);
            // The point of intersection between the tip of the ear and the 
            // lower portion of the ear
        point8 = getRambleyEarLowerTip(x,y,point8);
        
            // Upper component of the ear
        point1.setLocation(x1,y+RAMBLEY_EAR_HEIGHT/2.0);
        path.moveTo(point1.getX(), point1.getY());
        
        double tempY = RAMBLEY_EAR_HEIGHT * 0.26;
        point2.setLocation(getRambleyUpperEarX(tempY)+x,tempY+y);
        tempY = RAMBLEY_EAR_HEIGHT * 0.17;
        point3.setLocation(getRambleyUpperEarX(tempY)+x,tempY+y);
        addQuadBezierCurve(point1,point2,point3,point4,path);
        tempY = RAMBLEY_EAR_HEIGHT * 0.1;
        point2.setLocation(getRambleyUpperEarX(tempY)+x, tempY+y);
        double tempX = point7.getX()+RAMBLEY_EAR_TIP_ROUNDING;
        point5.setLocation(tempX, getRambleyUpperEarY(tempX-x)+y);
        addQuadBezierCurve(point3,point2,point5,point4,path);
        
            // Curve to smooth the transition between the upper portion and the 
            // tip of the ear
        tempX = point7.getX()-RAMBLEY_EAR_TIP_ROUNDING;
        point6.setLocation(tempX, getRambleyEarTipY(tempX-x)+y);
        path.quadTo(point7.getX(), point7.getY(), point6.getX(), point6.getY());
        
            // Tip of the ear
        double dxTip = Math.abs(point8.getX()-point6.getX());
        tempX = dxTip - (dxTip * 0.4);
        point1.setLocation(tempX+x, y+getRambleyEarTipY(tempX));
        tempX = dxTip - (dxTip * 0.75);
        point2.setLocation(tempX+x, y+getRambleyEarTipY(tempX));
        addQuadBezierCurve(point6,point1,point2,point4,path);
        tempX = dxTip - (dxTip * 0.9);
        point1.setLocation(tempX+x, y+getRambleyEarTipY(tempX));
        addQuadBezierCurve(point2,point1,point8,point4,path);
        
            // Lower component of the ear
        tempY = RAMBLEY_EAR_HEIGHT*0.76;
        point1.setLocation(getRambleyLowerEarX(tempY)+x,tempY+y);
        tempY = RAMBLEY_EAR_HEIGHT*0.88;
        point2.setLocation(getRambleyLowerEarX(tempY)+x,tempY+y);
        addQuadBezierCurve(point8,point1,point2,point4,path);
        tempY = RAMBLEY_EAR_HEIGHT*0.93;
        point3.setLocation(getRambleyLowerEarX(tempY)+x,tempY+y);
        point1.setLocation(x1,y1);
        addQuadBezierCurve(point2,point3,point1,point4,path);
        path.closePath();
        
        return new Area(path);
    }
    
    protected Area getRambleyInnerEar(Area ear, double scale, Area head){
        double scaleInv = 1/scale;
        Rectangle2D temp = ear.getBounds2D();
        AffineTransform inEarTx = AffineTransform.getScaleInstance(scale, scale);
        inEarTx.translate(-temp.getMinX(), -temp.getMinY());
        inEarTx.translate(temp.getCenterX()*scaleInv, temp.getCenterY()*scaleInv);
        inEarTx.translate(-temp.getWidth()/2, -temp.getHeight()/2);
        Area earIn = ear.createTransformedArea(inEarTx);
        earIn.subtract(head);
        return earIn;
    }
    
    protected void paintRambley(Graphics2D g, int x, int y, int w, int h){
        double scaleX = w/INTERNAL_RENDER_WIDTH;
        double scaleY = h/INTERNAL_RENDER_HEIGHT;
        if (isAspectRatioIgnored()){
            g.translate(x, y);
            g.scale(scaleX, scaleY);
        } else {
            double scale = Math.min(scaleX, scaleY);
            double width = Math.min(w, w*(scaleY/scaleX));
            double height = Math.min(h, h*(scaleX/scaleY));
            g.translate((w-width)/2.0+x, (h-height)/2.0+y);
            g.scale(scale,scale);
        }
        g.setStroke(getRambleyNormalStroke());
        AffineTransform horizFlip = AffineTransform.getScaleInstance(-1, 1);
        horizFlip.translate(-(INTERNAL_RENDER_HEIGHT-2), 0);
        if (rect == null)
            rect = new Rectangle2D.Double();
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
        if (path == null)
            path = new Path2D.Double();
        else
            path.reset();
        if (point1 == null)
            point1 = new Point2D.Double();
        if (point2 == null)
            point2 = new Point2D.Double();
        if (point3 == null)
            point3 = new Point2D.Double();
        if (point4 == null)
            point4 = new Point2D.Double();
        if (point5 == null)
            point5 = new Point2D.Double();
        if (point6 == null)
            point6 = new Point2D.Double();
        if (point7 == null)
            point7 = new Point2D.Double();
        if (point8 == null)
            point8 = new Point2D.Double();
        
            // Create the shape for Rambley's head
        
            // Might also be useable for the location of the nose
        Rectangle2D head1 = new Rectangle2D.Double(RAMBLEY_X_OFFSET, RAMBLEY_Y_OFFSET+84, 200, 92);
        Path2D head1a = new Path2D.Double(head1);
        head1a.lineTo(head1.getMinX(), head1.getMinY());
        double a1 = Math.toRadians(26.57);
        head1a.lineTo(head1.getCenterX(), head1.getMinY()-(Math.tan(a1)*100));
        head1a.lineTo(head1.getMaxX(), head1.getMinY());
        head1a.closePath();
            // Might also be usable for the location of his scarf
        Ellipse2D head2 = new Ellipse2D.Double(head1.getMinX()+24, RAMBLEY_Y_OFFSET, 152, 176);
            // Create the area for the upper part of his head
        Area temp = new Area(head2);
        temp.subtract(new Area(head1));
            // The cheek area
        ellipse.setFrameFromCenter(head1.getCenterX(), head2.getCenterY()-12, 
                head1.getMinX(), head2.getMinY()+18);
            // Create the shape of Rambley's head
        Area headShape = new Area(ellipse);
        headShape.intersect(new Area(head1a));
        headShape.add(temp);
        
            // Get the bounds for the head, so that we can base the facial 
            // features off it
        Rectangle2D headBounds = headShape.getBounds2D();
        
        Path2D earPath = new Path2D.Double();
        
        Area earR = getRambleyEar(headBounds.getCenterX()-84,head2.getMinY()-31.5,earPath);
        Area earL = earR.createTransformedArea(horizFlip);
        
        Area earInR = getRambleyInnerEar(earR,RAMBLEY_INNER_EAR_SCALE,headShape);
        Area earInL = getRambleyInnerEar(earL,RAMBLEY_INNER_EAR_SCALE,headShape);
        
        headShape.add(earR);
        headShape.add(earL);
        
        Path2D headOutline = new Path2D.Double(headShape);
        
//        g.translate((getIconWidth()-headBounds.getWidth())/2.0, 
//                (getIconHeight()-headBounds.getHeight())/2.0);
//            // If I go for a different translation, then this will need to be 
//            // altered to get the location right. This needs to be the width of 
//            // the painted area, but negative
//        horizFlip.translate(-(headBounds.getWidth()+2), 0);
        
        if (!getShowsLines()){
            g.setColor(RAMBLEY_MAIN_BODY_COLOR);
            g.fill(headShape);
            
        } else {
            System.out.println("Head: " + headBounds);
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
//                    RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setColor(Color.BLACK);
            g.draw(head1a);
            g.setColor(Color.DARK_GRAY);
            g.draw(head2);
            g.setColor(Color.MAGENTA);
            g.draw(earPath);
            g.setColor(Color.GRAY);
            g.draw(ellipse);
            g.draw(earInR);
            g.draw(earInL);
            g.setColor(Color.WHITE);
            g.draw(earR);
            g.draw(earL);
            g.setColor(Color.RED);
            g.draw(headShape);
            g.setColor(Color.ORANGE);
            g.draw(headBounds);
        }
        
            // Create shape for the face markings around his eyes
        
            // Used for many of the calculations for the markings
        Ellipse2D head3 = new Ellipse2D.Double();
        head3.setFrameFromCenter(headBounds.getCenterX(), headBounds.getMinY()+89, 
                headBounds.getMinX()+34, headBounds.getMinY()+58);
            // Might be used later for calculating eye location
        Ellipse2D head4 = new Ellipse2D.Double(head3.getMinX(),head3.getMinY()+17,56,32);
        temp = new Area(head4);
        Area temp2 = temp.createTransformedArea(horizFlip);
        Rectangle2D temp3 = temp2.getBounds2D();
            // Might be usable for the mouth area location, and for the eye location
        Rectangle2D head5 = new Rectangle2D.Double();
        head5.setFrameFromDiagonal(head4.getCenterX(), head4.getMinY(), 
                temp3.getCenterX(), temp3.getMaxY());
        temp.add(temp2);
        temp.add(new Area(head5));
        rect.setFrameFromDiagonal(head3.getMinX(), head3.getMinY(), 
                head3.getMaxX(), head4.getCenterY());
        temp.add(new Area(rect));
        Area faceMarkings = new Area(head3);
        faceMarkings.intersect(temp);
            // Possible Eyebrow mask?
        Ellipse2D head6 = new Ellipse2D.Double();
        double head6Y = Math.ceil(head1a.getBounds2D().getMinY());
        head6.setFrameFromCenter(head3.getCenterX(), head6Y+18, 
                head3.getCenterX()+14, head6Y);
        Area head6a = new Area(head6);
        faceMarkings.subtract(head6a);
        
        if (!getShowsLines()){
            g.setColor(RAMBLEY_FACE_MARKINGS_COLOR);
            g.fill(faceMarkings);
        } else {
            g.setColor(Color.WHITE);
            g.draw(head3);
            g.setColor(Color.YELLOW);
            g.draw(head4);
            g.draw(temp2);
            g.setColor(Color.CYAN);
            g.draw(head5);
            g.setColor(Color.BLUE);
            g.draw(rect);
            g.setColor(Color.GREEN);
            g.draw(head6);
            g.setColor(Color.MAGENTA);
            g.draw(faceMarkings);
        }
        
            // Create Rambley's eyebrows (will intersect with the other eye 
            // markings)
        
            // Ellipse to form the right eyebrow (may be used to calculate the 
            // location for the eyes)
        Ellipse2D eye1 = new Ellipse2D.Double();
        eye1.setFrameFromCenter(headBounds.getCenterX()-29, headBounds.getMinY()+40, 
                headBounds.getCenterX()-9, headBounds.getMinY()+20);
            // Right eyebrow area
        Area eyeBrowR = new Area(eye1);
            // Flip to form the Left eyebrow
        Area eyeBrowL = eyeBrowR.createTransformedArea(horizFlip);
        
        if (!getShowsLines()){
            g.setColor(RAMBLEY_EYEBROW_COLOR);
            g.fill(eyeBrowR);
            g.fill(eyeBrowL);
        } else {
            g.setColor(Color.YELLOW);
            g.draw(eye1);
            g.setColor(Color.PINK);
            g.draw(eyeBrowR);
            g.draw(eyeBrowL);
        }
            
            // May be used for calculations regarding the location of the mouth and nose
        Ellipse2D head7 = new Ellipse2D.Double();
        head7.setFrameFromCenter(headBounds.getCenterX(), headBounds.getMinY()+106, 
                headBounds.getMinX()+63, headBounds.getMinY()+78);
        Area mouthArea = new Area(head7);
        mouthArea.intersect(headShape);
        
            // Create the area around Rambley's eyes
            
            // Form the right eye area
        Ellipse2D eye2 = new Ellipse2D.Double();
        eye2.setFrameFromCenter(eye1.getCenterX()-1.5, eye1.getCenterY()+8, 
                eye1.getMaxX(), eye1.getMinY()+4);
        Ellipse2D eye4 = new Ellipse2D.Double();
        eye4.setFrameFromCenter(eye1.getCenterX()+4, eye2.getCenterY()+11.5, 
                eye1.getMinX()+8, eye2.getMinY()+12);
        Ellipse2D eye5 = new Ellipse2D.Double();
        eye5.setFrameFromCenter(eye1.getCenterX()+0.5, eye4.getCenterY()+2.5, 
                eye4.getMaxX(), eye4.getMinY()+2);
        getCircleIntersections(eye2,eye1,point2,point1);
        Path2D eye3 = new Path2D.Double();
        eye3.moveTo(point2.getX(), point2.getY());
        point1.setLocation(eye2.getMinX()-8, eye1.getMaxY()+2);
        eye3.quadTo((point2.getX()+(point1.getX()*2))/3, 
                ((point1.getY()+(point2.getY()*2))/3)+5, 
                point1.getX(), point1.getY());
        getCircleIntersections(head7,head1.getMinX(),head1.getMinY(),
                head1.getMaxX(),head1.getMinY(),point2,point3);
        point2.setLocation(eye2.getCenterX(), eye5.getMaxY());
        point3.setLocation(eye2.getMinX()+2, eye5.getMaxY());
        eye3.quadTo(point3.getX(), point3.getY(), point2.getX(), point2.getY());
        eye3.quadTo(eye2.getMaxX(), head7.getMinY(),eye2.getMaxX(), eye2.getCenterY());
        eye3.closePath();
        
            // Right eye surround
        Area eyeSurroundR = new Area(eye2);
        eyeSurroundR.add(new Area(eye3));
            // Left eye surround
        Area eyeSurroundL = eyeSurroundR.createTransformedArea(horizFlip);
        
        if (!getShowsLines()){
            g.setColor(RAMBLEY_SECONDARY_BODY_COLOR);
            g.fill(mouthArea);
            g.fill(eyeSurroundR);
            g.fill(eyeSurroundL);
            g.fill(earInR);
            g.fill(earInL);
        } else {
            g.setColor(Color.GREEN);
            g.draw(mouthArea);
            g.setColor(Color.CYAN);
            g.draw(eye2);
            g.setColor(Color.RED);
            g.draw(eye3);
            g.setColor(Color.ORANGE);
            g.draw(eyeSurroundR);
            g.draw(eyeSurroundL);
        }
        
        Path2D eye6 = new Path2D.Double();
        eye6.moveTo(eye4.getCenterX(), eye4.getMinY());
        eye6.lineTo((eye4.getCenterX()+eye5.getCenterX())/2, eye4.getMinY());
        double[] tArr = getQuadBezierT(point1.getX(),point3.getX(),point2.getX(),eye5.getMinX()-4);
        double t = 1;
        for (double tempT : tArr)
            t = Math.min(tempT, t);
        point4 = getQuadBezierPoint(point1,point3,point2,t, point4);
        eye6.quadTo(eye5.getMinX(),eye4.getMinY(),point4.getX(),point4.getY());
        eye6.quadTo(eye2.getMinX()+5, (eye4.getMaxY()+eye5.getMaxY())/2, 
                eye5.getCenterX(), eye5.getMaxY());
        eye6.quadTo(head6.getMinX(), eye5.getMaxY(), 
                (head6.getMinX()+(eye4.getMaxX()*2))/3, eye4.getMaxY());
        getCircleIntersections(eye4,head1.getMinX(),eye4.getCenterY()-1,
                head1.getMaxX(),eye4.getCenterY()-1,point2,point3);
        eye6.quadTo(eye4.getMaxX()+1, (eye5.getMaxY()+(eye5.getCenterY()*2))/3, 
                point3.getX(), point3.getY());
        eye6.closePath();
        
        Area eyeWhiteR = new Area(eye4);
        eyeWhiteR.add(new Area(eye6));
        Area eyeWhiteL = eyeWhiteR.createTransformedArea(horizFlip);
        
        if (!getShowsLines()){
            g.setColor(RAMBLEY_EYE_WHITE_COLOR);
            double pupilX = headBounds.getCenterX()-25;
            double pupilY = eyeWhiteR.getBounds2D().getCenterY();
            paintRambleyEye(g,eyeWhiteR,pupilX,pupilY);
            paintRambleyEye(g,eyeWhiteL,pupilX+50,pupilY);
        } else {
            g.setColor(Color.YELLOW);
            g.draw(eye5);
            g.setColor(RAMBLEY_MAIN_BODY_COLOR);
            g.draw(eye4);
            g.setColor(RAMBLEY_SCARF_COLOR);
            g.draw(eye6);
            g.setColor(RAMBLEY_IRIS_COLOR);
            g.draw(eyeWhiteR);
            g.draw(eyeWhiteL);
        }
        
        Rectangle2D nose1 = new Rectangle2D.Double();
        nose1.setFrameFromCenter(headBounds.getCenterX(), head1.getMinY()+7, 
                headBounds.getCenterX()-9, head1.getMinY());
        Ellipse2D nose3 = new Ellipse2D.Double();
        nose3.setFrameFromCenter(nose1.getCenterX(), head7.getMinY()+8.5, 
                nose1.getMinX()+2, nose1.getMaxY());
        Ellipse2D nose4 = new Ellipse2D.Double();
        nose4.setFrameFromCenter(nose1.getCenterX(), (nose3.getCenterY()+2), 
                nose1.getMinX(), nose1.getMinY());
        Path2D nose5 = new Path2D.Double();
        nose5.moveTo(nose4.getMinX(), nose4.getCenterY());
        nose5.curveTo(nose1.getMinX(),nose1.getCenterY(),
                (nose1.getMinX()+nose1.getCenterX())/2-1, nose1.getMaxY(), 
                nose3.getCenterX(), nose3.getMaxY());
        nose5.lineTo(nose1.getCenterX(), nose4.getCenterY());
        nose5.closePath();
        Area nose = new Area(nose3);
        rect.setFrameFromDiagonal(nose1.getMinX(), nose3.getMinX(), 
                nose1.getMaxX(), nose4.getCenterY());
        nose.subtract(new Area(rect));
        nose.add(new Area(nose4));
        temp = new Area(nose5);
        nose.add(temp);
        nose.add(temp.createTransformedArea(horizFlip));
        
        Path2D mouth = new Path2D.Double();
        point1.setLocation(nose1.getCenterX(), nose1.getMaxY());
        mouth.moveTo(point1.getX(), point1.getY());
        double mouth2 = (nose4.getMaxY()+point1.getY())/2;
        getCircleIntersections(head7,head1.getMinX(),mouth2,
                head1.getMaxX(),mouth2,point3,point2);
        point3.setLocation(point3.getX()+7.5, mouth2);
        point2.setLocation((point1.getX()+point3.getX())/2, head4.getMaxY());
        mouth.quadTo((point1.getX()+point2.getX())/2,point2.getY(),
                point2.getX(), point2.getY());
        mouth.quadTo((point3.getX()+point2.getX())/2,point2.getY(),
                point3.getX(), point3.getY());
        mouth.moveTo(point3.getX()+2, point3.getY()-2);
        mouth.lineTo(point3.getX()-2.5, point3.getY()+2.5);
        Shape mouth1 = mouth.createTransformedShape(horizFlip);
        mouth.append(mouth1, false);
        
        if (!getShowsLines()){
            g.setStroke(getRambleyDetailStroke());
            g.setColor(RAMBLEY_MOUTH_OUTLINE_COLOR);
            g.draw(mouth);
            g.setStroke(getRambleyNormalStroke());
            g.setColor(RAMBLEY_NOSE_COLOR);
            g.fill(nose);
        } else {
            g.setColor(Color.RED);
            g.draw(nose1);
            g.setColor(Color.CYAN);
            g.draw(nose5);
            g.setColor(Color.PINK);
            g.draw(nose3);
            g.draw(nose4);
            g.setColor(Color.MAGENTA);
            g.draw(nose);
            g.setColor(Color.BLUE);
            g.draw(mouth);
        }
        
        if (!getShowsLines()){
            g.setColor(RAMBLEY_OUTLINE_COLOR);
            g.setStroke(getRambleyOutlineStroke());
            g.draw(headOutline);
            g.setStroke(getRambleyDetailStroke());
            g.setColor(RAMBLEY_NOSE_OUTLINE_COLOR);
            g.draw(nose);
            g.draw(earInR);
            g.draw(earInL);
            
            g.setStroke(getRambleyNormalStroke());
        }
        
        
        
    }
    /**
     * This returns a String representation of this {@code RambleyIcon}. 
     * This method is primarily intended to be used only for debugging purposes, 
     * and the content and format of the returned String may vary between 
     * implementations.
     * @return A String representation of this {@code RambleyIcon}.
     */
    protected String paramString(){
        return "paintBackground="+isBackgroundPainted()+
                ",pixelGridPainted="+isPixelGridPainted();
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
     * This function gets the radius of the circle represented by the given 
     * ellipse. If the given ellipse is not a circle (i.e. the width and height 
     * are not the same), then this will take the average of the width and 
     * height of the given ellipse, and treat that as the diameter, dividing it 
     * by two to get the radius. While non-circle ellipses do not actually have 
     * a proper radius, this will at least give an approximate radius.
     * @param c The circle to get the radius of.
     * @return The radius of the given circle.
     */
    protected static double getRadius(Ellipse2D c){
        return (c.getWidth() + c.getHeight()) / 4.0;
    }
    /**
     * This calculates the two points of intersection for two circles.
     * 
     * This uses the algorithm described by Paul Bourke 
     * (<a href="https://paulbourke.net/geometry/circlesphere/">Circles and 
     * spheres</a>)
     * 
     * 
     * 
     * @param c0 
     * @param c1
     * @param p0 The first point of intersection 
     * @param p1 The second point of intersection 
     * @return {@code true} if the two circles intersect, {@code false} if 
     * either the circles are separate, one circle is fully contained within the 
     * other, or the circles are coincident and there are an infinite number of 
     * solutions.
     */
    protected static boolean getCircleIntersections(Ellipse2D c0, Ellipse2D c1, 
            Point2D p0, Point2D p1){
            // Store the location of the center of the first circle in the 
            // first point
        p0.setLocation(c0.getCenterX(), c0.getCenterY());
            // Store the location of the center of the second circle in the 
            // second point
        p1.setLocation(c1.getCenterX(), c1.getCenterY());
            // Get the radius of the first circle
        double r0 = getRadius(c0);
            // Get the radius of the second circle
        double r1 = getRadius(c1);
            // Get the distance between the centers of the two circles
        double d = p0.distance(p1);
            // If the distance is greater than the sum of the radiuses (the 
            // circles are separate), the distance is smaller than the 
            // difference between the radiuses (one circle is contained within 
            // the other), or the distance is zero and the radiuses are the 
            // same (the circles are coincident, and thus intersect everywhere)
        if (d > (r0 + r1) || d < Math.abs(r0 - r1) || (d == 0 && r0 == r1)){
                // Set the points to return to NaN
            p0.setLocation(Double.NaN, Double.NaN);
            p1.setLocation(p0);
            return false;
        }
        /*
        Let P3 be one of the two points of intersection, and P2 be the point 
        where the line between the centers of the circles and the line between 
        the two points of intersection cross.
        
        Consider the two right triangles P0P2P3 and P1P2P3, we can write 
        a^2+h^2=r0^2 and b^2+h^2=r1^2, with a being the length of triangle 
        P0P2P3 (the distance between P0 and P2), b being the length of triangle 
        P1P2P3 (the distance between P1 and P2), and h being the shared height 
        of the triangles (the distance between P2 and P3). As such, d = a + b.
        */
            // Get the radius of the first circle, squared
        double r0s = Math.pow(r0, 2);
            // Solve for a in the equation d = a + b, plugging in b^2 = r1^2-h^2
        double a = (r0s - Math.pow(r1, 2) + p0.distanceSq(p1)) / (2 * d);
            // Solve for h, using the equation h^2 = r0^2-a^2
        double h = Math.sqrt(r0s - Math.pow(a, 2));
            // Get the difference between the x-coordinates of the centers
        double dx = p1.getX() - p0.getX();
            // Get the difference between the y-coordinates of the centers
        double dy = p1.getY() - p0.getY();
            // Calculate the x-coordinate for P2
        double x2 = p0.getX() + (a * dx) / d;
            // Calculate the y-coordinate for P2
        double y2 = p0.getY() + (a * dy) / d;
            // Get the offset for the x-coordinate for the points of intersection 
        double rx = (h * dy) / d;
            // Get the offset for the y-coordinate for the points of intersection 
        double ry = (h * dx) / d;
            // Get the first point of intersection
        p0.setLocation(x2 + rx, y2 - ry);
            // Get the second point of intersection
        p1.setLocation(x2 - rx, y2 + ry);
        return true;
    }
    
    private static double[] solveQuadraticEquation(double a, double b, double c){
            // This will get the roots of the quadratic equation
        double[] roots = new double[2];
            // Solve the quadratic equation and get the number of roots
        int rootNum = QuadCurve2D.solveQuadratic(new double[]{c,b,a}, roots);
            // If there are no roots or the quadratic equation was a constant
        if (rootNum <= 0)
            return null;
            // If there was only 1 root
        else if (rootNum == 1)
                // Use the same root for both
            roots[1] = roots[0];
        else {
                // Temporarily store the first root.
            double temp = roots[0];
                // Make sure the first root is the lower of the two
            roots[0] = Math.min(roots[0], roots[1]);
                // Make sure the second root is the higher of the two
            roots[1] = Math.max(temp, roots[1]);
        }
        return roots;
    }
    /**
     * This gets the points at which the given line intersects with the given 
     * ellipse.
     * @param e
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param point1 The first point of intersection 
     * @param point2 The second point of intersection 
     * @return 
     */
    protected static boolean getCircleIntersections(Ellipse2D e, double x1, double y1, 
            double x2, double y2, Point2D point1, Point2D point2){
            // Function breaks for vertical lines
        if (x1 == x2){
                // If the line is a point
            if (y1 == y2){
                    // Set the points to return to NaN
                point1.setLocation(Double.NaN, Double.NaN);
                point2.setLocation(point1);
                return false;
            }
                // Rotate everything by 90 degrees.
                // Bam! The vertical line is now a horizontal line
            e.setFrame(e.getY(),e.getX(),e.getHeight(),e.getWidth());
                // Calculate the points of intersection for the horizontal line
            boolean value = getCircleIntersections(e,y1,x1,y2,x2,point1,point2);
                // Rotate everything back
            point1.setLocation(point1.getY(), point1.getX());
            point2.setLocation(point2.getY(), point2.getX());
            e.setFrame(e.getY(),e.getX(),e.getHeight(),e.getWidth());
            return value;
        }   // Get the slope of the line
        double k = (y2 - y1) / (x2 - x1);
            // Get the coefficient for the line
        double m = y2 - k * x2;
            // Get half of the ellipse's width, squared
        double w = Math.pow(e.getWidth()/2, 2);
            // Get half of the ellipse's height, squared
        double h = Math.pow(e.getHeight()/2, 2);
            // Get the a coefficient for the quadratic equation
        double a = 1/w + Math.pow(k, 2)/h;
            // Get the b coefficient for the quadratic equation
        double b = (2*k*(m-e.getCenterY()))/h - (2*e.getCenterX())/w;
            // Get the c coefficient for the quadratic equation
        double c = Math.pow(m-e.getCenterY(), 2)/h - 1 + Math.pow(e.getCenterX(), 2)/w;
            // Get the roots of the quadratic equation
        double[] roots = solveQuadraticEquation(a,b,c);
            // If there are no roots or the quadratic equation was a constant
        if (roots == null){
                // Set the points to return to NaN
            point1.setLocation(Double.NaN, Double.NaN);
            point2.setLocation(point1);
            return false;
        }
        point1.setLocation(roots[0], k*roots[0]+m);
        point2.setLocation(roots[1], k*roots[1]+m);
        return true;
    }
    
    protected static void getCircleIntersections(Ellipse2D e, Point2D p1, 
            Point2D p2, Point2D point1, Point2D point2){
        getCircleIntersections(e,p1.getX(),p1.getY(),p2.getX(),p2.getY(),
                point1,point2);
    }
    /**
     * 
     * @param p0 The starting point of the curve
     * @param p1 The control point for the curve.
     * @param p2 The end point of the curve
     * @param t
     * @return 
     */
    protected static double getQuadBezierPoint(double p0, double p1, double p2, double t){
        return (Math.pow(1-t, 2) * p0) + (2 * t * (1-t)*p1) + (Math.pow(t, 2)*p2);
    }
    /**
     * 
     * @param p0 The starting point of the curve
     * @param p1 The control point for the curve.
     * @param p2 The end point of the curve
     * @param t
     * @param point
     * @return 
     */
    protected static Point2D getQuadBezierPoint(Point2D p0, Point2D p1, 
            Point2D p2, double t, Point2D point){
        if (point == null)
            point = new Point2D.Double();
        point.setLocation(getQuadBezierPoint(p0.getX(),p1.getX(),p2.getX(),t),
                getQuadBezierPoint(p0.getY(),p1.getY(),p2.getY(),t));
        return point;
    }
    /**
     * 
     * @param p0 The starting point of the curve
     * @param p1 The control point for the curve
     * @param p2 The end point of the curve
     * @param p The point to get the value of t for
     * @return 
     */
    protected static double[] getQuadBezierT(double p0, double p1, double p2, double p){
            // Get the a coefficient for the quadratic equation
        double a = p0 - (2 * p1) + p2;
            // Get the b coefficient for the quadratic equation
        double b = (-2 * p0) + (2 * p1);
            // Get the c coefficient for the quadratic equation
        double c = p0 - p;
            // Get the roots of the quadratic equation
        double[] roots = solveQuadraticEquation(a,b,c);
            // If there are any roots from the quadratic equation
        if (roots != null){
                // Get the first root
            double r0 = roots[0];
                // Get the second root
            double r1 = roots[1];
                // If both roots are between 0 and 1, inclusive
            if (r0 >= 0 && r0 <= 1 && r1 >= 0 && r1 <= 1){
                    // If both roots are the same
                if (r0 == r1)
                    return new double[]{r0};
                return roots;
                // If only the first root is between 0 and 1, inclusive
            } else if (r0 >= 0 && r0 <= 1){
                return new double[]{r0};
                // If only the second root is between 0 and 1, inclusive
            } else if (r1 >= 0 && r1 <= 1){
                return new double[]{r1};
            }
        }
        return new double[]{};
    }
    /**
     * 
     * https://microbians.com/math/Gabriel_Suchowolski_Quadratic_bezier_through_three_points_and_the_-equivalent_quadratic_bezier_(theorem)-.pdf
     * 
     * @param p0 The starting point of the curve
     * @param p1 The point on the curve to pass through
     * @param p2 The end point of the curve
     * @param point 
     * @return 
     */
    protected static Point2D getQuadBezierControlPoint(Point2D p0, Point2D p1, 
            Point2D p2, Point2D point){
        if (point == null)
            point = new Point2D.Double();
        double tx1 = p0.getX() - p1.getX();
        double ty1 = p0.getY() - p1.getY();
        double tx2 = p2.getX() - p1.getX();
        double ty2 = p2.getY() - p1.getY();
        double d1 = p0.distance(p1);
        double d2 = p2.distance(p1);
        double d3 = Math.sqrt(d1*d2);
        point.setLocation(p1.getX()-(d3*(tx1/d1+tx2/d2))/2,
                p1.getY()-(d3*(ty1/d1+ty2/d2))/2);
        return point;
    }
    
    protected static void getCubicBezierControlPoints(Point2D p0, Point2D p1, 
            Point2D p2, Point2D p3, Point2D controlP1, Point2D controlP2){
//        double dx = p3.getX() - p0.getX();
        double y1 = (-5*p0.getY()+18*p1.getY()-9*p2.getY()+2*p3.getY())/6;
        double y2 = (2*p0.getY()-9*p1.getY()+18*p2.getY()-5*p3.getY())/6;
        
        controlP1.setLocation(p1.getX(),y1);
        controlP2.setLocation(p2.getX(),y2);
    }
    /**
     * https://www.codeproject.com/Articles/31859/Draw-a-Smooth-Curve-through-a-Set-of-2D-Points-wit
     * @param knots
     * @param ctrlPts1
     * @param ctrlPts2
     */
    protected static void getCubicBezierSplineControlPoints(java.util.List<Point2D> knots,
            java.util.List<Point2D> ctrlPts1, java.util.List<Point2D> ctrlPts2){
        if (knots == null || knots.size() < 2)
            throw new IllegalArgumentException("There must be at least 2 knots");
        ctrlPts1.clear();
        ctrlPts2.clear();
        int n = knots.size()-1;
            // Only 2 points, straight line.
        if (n == 1){
                // 3P1 = 2P0 + P3
            Point2D ctrlPt1 = new Point2D.Double(
                    (2*knots.get(0).getX()+knots.get(1).getX())/3,
                    (2*knots.get(0).getY()+knots.get(1).getY())/3);
            ctrlPts1.add(ctrlPt1);
                // P2 = 2P1 - P0
            ctrlPts2.add(new Point2D.Double(
                    2*ctrlPt1.getX()-knots.get(0).getX(),
                    2*ctrlPt1.getY()-knots.get(0).getY()));
            return;
        }
        
        double[][] coords = getSplineFirstCtrlPoints(knots,n);
        
            // Populate the control point arrays
        for (int i = 0; i < n; i++){
                // First control point
            ctrlPts1.add(new Point2D.Double(coords[0][i],coords[1][i]));
                // Second control point
            double x, y;
            if (i < n-1){
                x = 2*knots.get(i+1).getX()-coords[0][i+1];
                y = 2*knots.get(i+1).getY()-coords[1][i+1];
            } else {
                x = (knots.get(n).getX()+coords[0][n-1])/2;
                y = (knots.get(n).getY()+coords[1][n-1])/2;
            }
             ctrlPts2.add(new Point2D.Double(x,y));
        }
    }
    
    private static double[][] getSplineFirstCtrlPoints(java.util.List<Point2D> knots, int n){
            // Right hand side vector
        double[][] rhs = new double[2][n];
        
            // Set right hand side values
        rhs[0][0] = knots.get(0).getX()+2*knots.get(1).getX();
        rhs[1][0] = knots.get(0).getY()+2*knots.get(1).getY();
        for (int i = 1; i < n-1; i++){
            rhs[0][i] = 4*knots.get(i).getX()+2*knots.get(i+1).getX();
            rhs[1][i] = 4*knots.get(i).getY()+2*knots.get(i+1).getY();
        }
        rhs[0][n-1] = (8*knots.get(n-1).getX()+knots.get(n).getX())/2.0;
        rhs[1][n-1] = (8*knots.get(n-1).getY()+knots.get(n).getY())/2.0;
        
            // Solve a tridiagonal system for the coordinates of the first 
            // Bezier control points
        
            // Temporary workspace 
        double[] tmp = new double[n];
        double b = 2.0;
            // The coordinates
        double[][] p = new double[2][n];
        p[0][0] = rhs[0][0] / b;
        p[1][0] = rhs[1][0] / b;
            // Decomposition and forward substitution
        for (int i = 1; i < n; i++){
            tmp[i] = 1 / b;
            b = ((i < n-1)?4.0:3.5)-tmp[i];
            p[0][i] = (rhs[0][i] - p[0][i-1]) / b;
            p[1][i] = (rhs[1][i] - p[1][i-1]) / b;
        }
            // Backsubstitution
        for (double[] coords : p){
            for (int i = 1; i < n; i++){
                coords[n-i-1] -= tmp[n-i] * coords[n-i];
            }
        }
        return p;
    }
    
    private static void getIntersectingLine(double x, double y, Line2D line1, 
            Line2D line2, DoubleUnaryOperator getY){
        double x1 = (line1.getX1()+line1.getX2()) / 2.0;
        double y1 = getY.applyAsDouble(x1 - x) + y;
        Line2D l1 = new Line2D.Double(line1.getX1(), line1.getY1(), x1, y1);
        Line2D l2 = new Line2D.Double(x1, y1, line1.getX2(), line1.getY2());
        if (l1.intersectsLine(line2))
            line1.setLine(l1);
        else
            line1.setLine(l2);
    }
    
    protected static Point2D getLineIntersection(double x, double y, double x1, 
            double x2, DoubleUnaryOperator getY1, DoubleUnaryOperator getY2, 
            int resolution, Point2D point){
        if (point == null)
            point = new Point2D.Double();
        Line2D line1 = new Line2D.Double(x1+x, getY1.applyAsDouble(x1)+y, 
                x2+x, getY1.applyAsDouble(x2)+y);
        Line2D line2 = new Line2D.Double(x1+x, getY2.applyAsDouble(x1)+y, 
                x2+x, getY2.applyAsDouble(x2)+y);
        for (int i = 0; i < resolution; i++){
            if (line1.getP1().distance(line1.getP2()) >= line2.getP1().distance(line2.getP2()))
                getIntersectingLine(x,y,line1,line2,getY1);
             else 
                getIntersectingLine(x,y,line2,line1,getY2);
        }
        double tempX = (line1.getX1()+line1.getX2()+line2.getX1()+line2.getX2())/4.0;
        double temp = tempX - x;
        point.setLocation(tempX, (getY1.applyAsDouble(temp)+getY2.applyAsDouble(temp))/2.0+y);
        return point;
    }
    
    protected static Point2D getLineIntersection(double x, double y, double x1, 
            double x2, DoubleUnaryOperator getY1, DoubleUnaryOperator getY2, 
            Point2D point){
        return getLineIntersection(x,y,x1,x2,getY1,getY2,
                DEFAULT_LINE_INTERSECTION_RESOLUTION,point);
    }
    
    
    
    
    
    
        // Some debug flags and settings that will be removed when finished
    protected static final int A_B_TESTING_FLAG = 0x40000000;
    
    protected static final int SHOW_LINES_FLAG = 0x80000000;
    
    boolean getShowsLines(){
        return getFlag(SHOW_LINES_FLAG);
    }
    
    void setShowsLines(boolean value){
        setFlag(SHOW_LINES_FLAG,value);
    }
    
    boolean getABTesting(){
        return getFlag(A_B_TESTING_FLAG);
    }
    
    void setABTesting(boolean value){
        setFlag(A_B_TESTING_FLAG,value);
    }
    
    private double testDouble1 = 0.26;
    private double testDouble2 = 0.5;
    private double testDouble3 = 0.85;
    private double testDouble4 = 1.0;
    private double testDouble5 = 0.50;
    private double testDouble6 = 2;
    
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
}
