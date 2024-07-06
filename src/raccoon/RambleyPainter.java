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
import java.util.Objects;
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
     * 
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
     * This is the height of Rambley's ears.
     */
    private static final double RAMBLEY_EAR_HEIGHT = 1.8 * RAMBLEY_EAR_MULTIPLIER;
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
    private static final double RAMBLEY_EAR_TIP_ROUNDING = 2.0;
    /**
     * This is the scaling factor used when scaling the shapes that make up 
     * Rambley's ears in order to get the inner portion of his ears.
     */
    private static final double RAMBLEY_INNER_EAR_SCALE = 2/3.0;
    /**
     * This converts the given y-coordinate in the image coordinate system to a 
     * y-coordinate in the coordinate system used by the equations used to 
     * calculate the curves that make up Rambley's ears. 
     * @param y The y-coordinate 
     * @return 
     */
    private static double graphicsToEarEquY(double y){
        y = RAMBLEY_EAR_HEIGHT - y;
        y /= RAMBLEY_EAR_MULTIPLIER;
        return y + RAMBLEY_EAR_Y_OFFSET;
    }
    /**
     * 
     * @param x
     * @return 
     */
    private static double graphicsToEarEquX(double x){
        x /= RAMBLEY_EAR_MULTIPLIER;
        return RAMBLEY_EAR_X_OFFSET-x;
    }
    /**
     * 
     * @param y
     * @return 
     */
    private static double earEquToGraphicsY(double y){
        y -= RAMBLEY_EAR_Y_OFFSET;
        y *= RAMBLEY_EAR_MULTIPLIER;
        return (RAMBLEY_EAR_HEIGHT-y);
    }
    /**
     * 
     * @param x
     * @return 
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
     * of the one seen on Rambley's screens in Indigo Park will be painted 
     * behind Rambley. The background will consist of {@link 
     * BACKGROUND_DOT_COLOR dark blue} diamond-shaped polka dots over a gradient 
     * going from {@link BACKGROUND_GRADIENT_COLOR dark blue} at the top to 
     * {@link BACKGROUND_COLOR light blue} at the bottom. The default value for 
     * this is {@code true}.
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
    
    protected double getBackgroundDotOffset(double size){
        return (size%BACKGROUND_DOT_SPACING)/2.0;
    }
    
    protected double getBackgroundDotOffsetX(double width){
        return getBackgroundDotOffset(width);
    }
    
    protected double getBackgroundDotOffsetY(double height){
        return getBackgroundDotOffset(height);
    }
    /**
     * 
     * @param rect A rectangle outlining the bounds for the background polka 
     * dot to return.
     * @param path A Path2D object to store the results in, or null.
     * @return The Path2D object to use to draw a background polka dot.
     */
    protected Path2D getBackgroundDot(RectangularShape rect, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // Move to the top center point of the dot
        path.moveTo(rect.getCenterX(), rect.getMinY());
            // Line to the center left point
        path.lineTo(rect.getMinX(), rect.getCenterY());
            // Line to the bottom center point
        path.lineTo(rect.getCenterX(), rect.getMaxY());
            // Line to the center right point
        path.lineTo(rect.getMaxX(), rect.getCenterY());
            // Close the path
        path.closePath();
        return path;
    }
    /**
     * 
     * @param x The x-coordinate for the center of the background polka dot.
     * @param y The y-coordinate for the center of the background polka dot.
     * @param path A Path2D object to store the results in, or null.
     * @param rect A Rectangle2D object to temporarily store the bounds for the 
     * background polka dot in, or null.
     * @return The Path2D object to use to draw a background polka dot.
     */
    protected Path2D getBackgroundDot(double x, double y, Path2D path, Rectangle2D rect){
            // If the given Rectangle2D object is null
        if (rect == null)
            rect = new Rectangle2D.Double();
            // Set the frame of the rectangle from the center to be the size of 
            // a background polka dot.
        rect.setFrameFromCenter(x, y, x-BACKGROUND_DOT_HALF_SIZE, 
                y-BACKGROUND_DOT_HALF_SIZE);
        return getBackgroundDot(rect,path);
    }
    
    protected double getPixelGridOffset(double size){
        return ((size-1)%PIXEL_GRID_SPACING)/2.0;
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
        for (double y1 = getPixelGridOffset(h); y1 <= h; y1+=PIXEL_GRID_SPACING){
            path.moveTo(x, y1+y);
            path.lineTo(x2, y1+y);
        }
            // Go through and generate the horizontal lines, starting at the 
            // offset for the x-coordinate of the pixel grid and spacing them 
            // out by the pixel grid spacing
        for (double x1 = getPixelGridOffset(w); x1 <= w; x1+=PIXEL_GRID_SPACING){
            path.moveTo(x1+x, y);
            path.lineTo(x1+x, y2);
        }
        return path;
    }
    /**
     * This constructs a stroke to use when rendering Rambley
     * @param width The line width
     * @return 
     */
    protected BasicStroke getRambleyStroke(float width){
        return new BasicStroke(width,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    }
    /**
     * This returns a BasicStroke to use for rendering most of Rambley. This 
     * stroke is mainly used when filling in shapes. This stroke has a line 
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
     * This returns a BasicStroke to use for rendering the details and finer 
     * outlines for Rambley. This stroke has a line width of 2.0.
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
     * This returns a BasicStroke to use for rendering most of the outlines for 
     * Rambley. This stroke has a line width of 3.0.
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
     * This returns an AffineTransform to use to flip shapes horizontally when 
     * painting Rambley.
     * @return The AffineTransform used to flip things horizontally.
     */
    protected AffineTransform getRambleyHorizontalFlipTransform(){
            // If the horizontal flip transform has not been initialized yet
        if (horizFlip == null){
                // Get a transform that will flip things horizontally
            horizFlip = AffineTransform.getScaleInstance(-1, 1);
                // Translate the horizontal flip transform to put it back on the 
                // image
            horizFlip.translate(-INTERNAL_RENDER_HEIGHT, 0);
        }
        return horizFlip;
    }
    /**
     * This flips the given area horizontally.
     * @param area The area to flip.
     * @return The horizontally flipped area.
     * @see getRambleyHorizontalFlipTransform
     * @see Area#createTransformedArea
     */
    protected Area createHorizontallyFlippedArea(Area area){
        return area.createTransformedArea(getRambleyHorizontalFlipTransform());
    }
    
    

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
            // Get the x offset for the background polka dots
        double x1 = getBackgroundDotOffsetX(w);
            // Get the y offset for the background polka dots
        double y1 = getBackgroundDotOffsetY(h);
            // Go through the multipliers for the y-coordinates for the centers 
            // of the background polka dots (to create the polka dot pattern, 
            // we need to know what row number we are on, so we can offset the 
            // x-coordinates accordingly)
        for (int i = 0; (i * BACKGROUND_DOT_SPACING) <= h; i++){
                // Get the y-coordinate for the centers of the polka dots on 
                // this row
            double yDot = (i * BACKGROUND_DOT_SPACING)+y1;
                // Go through the x-coordinates for the centers of the 
                // background polka dots (polka dots on odd rows are offset 
                // compared to the polka dots on even rows)
            for (double xDot = BACKGROUND_DOT_SPACING * (i % 2); xDot <= w; 
                    xDot+=BACKGROUND_DOT_SPACING+BACKGROUND_DOT_SPACING){
                    // Get the background polka dot to render
                path = getBackgroundDot(xDot+x1,yDot,path,rect);
                    // Fill the current background polka dot
                g.fill(path);
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
     * @param headBounds The bounds of Rambley's head without his ears.
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
            // will be horizontally centered, located 58 pixels down from the 
            // top of the head, and will be around 130 x 62. This will make up 
            // the markings, and the remaining code will mask it into the right 
            // shape.
        ellipse1.setFrameFromCenter(
                headBounds.getCenterX(), headBounds.getMinY()+89, 
                headBounds.getMinX()+34, headBounds.getMinY()+58);
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
     * @param headBounds The bounds of Rambley's head without his ears.
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
     * @param eyeBrow An Ellipse2D object with the ellipse used to form 
     * Rambley's right eyebrow (cannot be null).
     * @param snout  An Ellipse2D object with the ellipse used to form 
     * Rambley's snout (cannot be null).
     * @param ellipse An Ellipse2D object to use to calculate the top-right 
     * portion of the markings, or null.
     * @param path A Path2D object to use to form the portion of the markings 
     * that will not be formed by {@code ellipse}, or null.
     * @param point1 A Point2D object to store the top-left most point (starting 
     * point) of the bottom-left curve, or null.
     * @param point2 A Point2D object to store the bottom-right most point (end 
     * point) of the bottom-left curve, or null.
     * @param point3 A Point2D object to store the control point of the 
     * bottom-left curve, or null.
     * @return The area that forms the markings around Rambley's right eye.
     */
    private Area createRambleyEyeMarkings(Ellipse2D eyeBrow, Ellipse2D snout, 
            Ellipse2D ellipse, Path2D path, Point2D point1, Point2D point2, 
            Point2D point3){
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
        if (point3 == null)
            point3 = new Point2D.Double();
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
        point3.setLocation(ellipse.getMinX()+2, point2.getY());
            // Add a bezier curve from point1 to point2, using point3 as the 
            // control point.
        path.quadTo(point3.getX(), point3.getY(), point2.getX(), point2.getY());
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
     * 
     * @param x The x-coordinate of the center of Rambley's eye.
     * @param y The y-coordinate of the center of Rambley's eye.
     * @param iris An Ellipse2D object to store Rambley's iris in.
     * @param pupil An Ellipse2D object to store Rambley's pupil in.
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
     * 
     * @param g The graphics context to render to.
     * @param eyeWhite The shape to use to paint the eye white for Rambley's 
     * eye.
     * @param iris The Ellipse2D object representing Rambley's iris.
     * @param pupil The Ellipse2D object representing Rambley's pupil.
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
     * 
     * @param g The graphics context to render to.
     * @param eyeWhite The shape to use to paint the eye white for Rambley's 
     * eye.
     * @param x The x-coordinate of the center of Rambley's iris and pupil.
     * @param y The y-coordinate of the center of Rambley's iris and pupil.
     * @param iris The Ellipse2D object representing Rambley's iris.
     * @param pupil The Ellipse2D object representing Rambley's pupil.
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
            // If the Ellipse2D scratch object has not been initialized yet
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
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
        
            // A rectangle to use to calculate the shape of Rambley's head
        Rectangle2D head1 = new Rectangle2D.Double();
            // An ellipse to use to calculate the upper part of Rambley's head
            // (Might also be usable for the location of his scarf)
        Ellipse2D head2 = new Ellipse2D.Double();
            // Create the shape for Rambley's head (without his ears for now)
        Area headShape = createRambleyHeadArea(head1,path,head2,ellipse);
            // Get the bounds for the head, so that we can base the facial 
            // features off it
        Rectangle2D headBounds = headShape.getBounds2D();
        
            // DEBUG: If we are showing the lines that make up Rambley
        if (getShowsLines()){
            printShape("headBounds",headBounds);
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
//                    RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setColor(Color.BLACK);
            g.draw(path);
            g.setColor(Color.DARK_GRAY);
            g.draw(head2);
            g.setColor(Color.GRAY);
            g.draw(ellipse);
        }
        
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
        
            // DEBUG: If we are showing the lines that make up Rambley 
        if (getShowsLines()){
            printShape("headShape",headShape);
            g.setColor(Color.MAGENTA);
            g.draw(path);
            g.setColor(Color.GRAY);
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
        
            // An ellipse to use to calculate the face markings areound 
            // Rambley's eyes.
        Ellipse2D head3 = new Ellipse2D.Double();
            // A second ellipse to use to calculate the face markings areound 
            // Rambley's eyes.
        Ellipse2D head4 = new Ellipse2D.Double();
            // Create the shape for the face markings around his eyes
        Area faceMarkings = createRambleyMaskFaceMarkings(headBounds,ellipse,
                head3,head4,rect);
            // An Ellipse2D to use to form the area around Rambley's snout.
        Ellipse2D head5 = new Ellipse2D.Double();
            // Create the area around Rambley's nose and mouth
        Area snoutArea = createRambleySnoutArea(headBounds,headShape,head5);
        
            // DEBUG: If we are showing the lines that make up Rambley 
        if (getShowsLines()){
            g.setColor(Color.WHITE);
            g.draw(ellipse);
            g.setColor(Color.YELLOW);
            g.draw(head3);
            g.setColor(Color.BLUE);
            g.draw(rect);
            g.setColor(Color.GREEN);
            g.draw(head4);
            g.setColor(Color.MAGENTA);
            g.draw(faceMarkings);
        }
        
            // An Ellipse to use to form the right eyebrow
        Ellipse2D eye1 = new Ellipse2D.Double();
            // Create Rambley's right eyebrow (this will intersect with the 
            // other eye markings)
        Area eyeBrowR = createRambleyEyebrow(headBounds,eye1);
            // Flip to form the Left eyebrow (this will intersect with the 
            // other eye markings)
        Area eyeBrowL = createHorizontallyFlippedArea(eyeBrowR);
            // An Ellipse to use to form Rambley's right eye
        Ellipse2D eye2 = new Ellipse2D.Double();
            // Create the area around Rambley's right eye
        Area eyeSurroundR = createRambleyEyeMarkings(eye1,head5,eye2,path,
                point1,point2,point3);
            // Flip to form the area around Rambley's left eye
        Area eyeSurroundL = createHorizontallyFlippedArea(eyeSurroundR);
        
            // DEBUG: If we are showing the lines that make up Rambley 
        if (getShowsLines()){
            g.setColor(Color.YELLOW);
            g.draw(eye1);
            g.setColor(Color.PINK);
            g.draw(eyeBrowR);
            g.draw(eyeBrowL);
        }
        
        Ellipse2D eye4 = new Ellipse2D.Double();
        eye4.setFrameFromCenter(eye1.getCenterX()+4, eye2.getCenterY()+11.5, 
                eye1.getMinX()+8, eye2.getMinY()+12);
        Ellipse2D eye5 = new Ellipse2D.Double();
        eye5.setFrameFromCenter(eye1.getCenterX()+0.5, eye4.getCenterY()+2.5, 
                eye4.getMaxX(), eye4.getMinY()+2);
        
            // DEBUG: If we are showing the lines that make up Rambley 
        if (getShowsLines()){
            g.setColor(Color.GREEN);
            g.draw(snoutArea);
            g.setColor(Color.CYAN);
            g.draw(eye2);
            g.setColor(Color.RED);
            g.draw(path);
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
        eye6.quadTo(head4.getMinX(), eye5.getMaxY(), 
                (head4.getMinX()+(eye4.getMaxX()*2))/3, eye4.getMaxY());
        getCircleIntersections(eye4,head1.getMinX(),eye4.getCenterY()-1,
                head1.getMaxX(),eye4.getCenterY()-1,point2,point3);
        eye6.quadTo(eye4.getMaxX()+1, (eye5.getMaxY()+(eye5.getCenterY()*2))/3, 
                point3.getX(), point3.getY());
        eye6.closePath();
        
        Area eyeWhiteR = new Area(eye4);
        eyeWhiteR.add(new Area(eye6));
        Area eyeWhiteL = createHorizontallyFlippedArea(eyeWhiteR);
        
            // DEBUG: If we are showing the lines that make up Rambley 
        if (getShowsLines()){
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
        nose3.setFrameFromCenter(nose1.getCenterX(), head5.getMinY()+8.5, 
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
        Area temp = new Area(nose5);
        nose.add(temp);
        nose.add(createHorizontallyFlippedArea(temp));
        
        Path2D mouth = new Path2D.Double();
        point1.setLocation(nose1.getCenterX(), nose1.getMaxY());
        mouth.moveTo(point1.getX(), point1.getY());
        double mouth2 = (nose4.getMaxY()+point1.getY())/2;
        getCircleIntersections(head5,head1.getMinX(),mouth2,
                head1.getMaxX(),mouth2,point3,point2);
        point3.setLocation(point3.getX()+7.5, mouth2);
        point2.setLocation((point1.getX()+point3.getX())/2, head3.getMaxY());
        mouth.quadTo((point1.getX()+point2.getX())/2,point2.getY(),
                point2.getX(), point2.getY());
        mouth.quadTo((point3.getX()+point2.getX())/2,point2.getY(),
                point3.getX(), point3.getY());
        mouth.moveTo(point3.getX()+2, point3.getY()-2);
        mouth.lineTo(point3.getX()-2.5, point3.getY()+2.5);
        Shape mouth1 = mouth.createTransformedShape(getRambleyHorizontalFlipTransform());
        mouth.append(mouth1, false);
        
            // DEBUG: If we are showing the lines that make up Rambley 
        if (getShowsLines()){
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
                // Calculate the x-coordinate for Rambley's right iris and pupil
            double pupilX = headBounds.getCenterX()-25;
                // Calculate the y-coordinate for Rambley's right iris and pupil
            double pupilY = eyeWhiteR.getBounds2D().getCenterY();
                // Draw Rambley's right eye
            paintRambleyEye(g,eyeWhiteR,pupilX,pupilY,iris,pupil);
                // Draw Rambley's left eye
            paintRambleyEye(g,eyeWhiteL,pupilX+50,pupilY,iris,pupil);
                // Set the stroke to Rambley's detail stroke
            g.setStroke(getRambleyDetailStroke());
                // Draw Rambley's mouth
            g.setColor(RAMBLEY_MOUTH_OUTLINE_COLOR);
            g.draw(mouth);
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
     * This calculates the y-coordinates for points on the given ellipse for the 
     * given x-coordinate
     * @param ellipse The ellipse to calculate the points on
     * @param x The x-coordinate to get the points for
     * @param p0 The Point2D object that the upper point will be stored in.
     * @param p1 The Point2D object that the lower point will be stored in.
     * @return Whether the x-coordinate lies on the ellipse.
     */
    protected static boolean getEllipseY(Ellipse2D ellipse, double x,Point2D p0, 
            Point2D p1){
            // If the given x-coordinate is out of range of the ellipse
        if (x < ellipse.getMinX() || x > ellipse.getMaxX()){
                // Set the points to return to NaN
            p0.setLocation(Double.NaN, Double.NaN);
            p1.setLocation(p0);
            return false;
        }   // If the given x-coordinate is right at the left or right bounds of 
            // the ellipse
        else if (x == ellipse.getMinX() || x == ellipse.getMaxX()){
                // The points will be at the vertical center of the ellipse
            p0.setLocation(x, ellipse.getCenterY());
            p1.setLocation(p0);
            return true;
        }   // If the given x-coordinate is right at the center of the ellipse
        else if (x == ellipse.getCenterX()){
                // The points will be at the top and bottom of the ellipse
            p0.setLocation(x, ellipse.getMinY());
            p1.setLocation(x, ellipse.getMaxY());
            return true;
        }
            // Calculate half of the width of the ellipse
        double a = ellipse.getWidth()/2.0;
            // Let b be half the height of the ellipse
            // Relative to the center of the ellipse, y = (b/a)*sqrt(a^2-x^2)
        double y = ((ellipse.getHeight()/2.0)/a)*
                Math.sqrt(Math.pow(a, 2)-Math.pow(x-ellipse.getCenterX(), 2));
            // Top point will be above the vertical center of the ellipse
        p0.setLocation(x, ellipse.getCenterY()-y);
            // Bottom point will be below the vertical center of the ellipse
        p1.setLocation(x, ellipse.getCenterY()+y);
        return true;
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
     * @param c0 One of the two circles
     * @param c1 The other circle
     * @param p0 The Point2D object that the first point of intersection will be 
     * stored in.
     * @param p1 The Point2D object that the second point of intersection will 
     * be stored in.
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
    /**
     * This solves the 
     * @param a
     * @param b
     * @param c
     * @return 
     */
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
     * @param x1 The x-coordinate for the first point on the line
     * @param y1 The y-coordinate for the first point on the line
     * @param x2 The x-coordinate for the second point on the line
     * @param y2 The y-coordinate for the second point on the line
     * @param point1 The Point2D object that the first point of intersection 
     * will be stored in.
     * @param point2 The Point2D object that the second point of intersection 
     * will be stored in.
     * @return Whether the line intersects the ellipse.
     */
    protected static boolean getCircleIntersections(Ellipse2D e, 
            double x1, double y1, double x2, double y2, Point2D point1, 
            Point2D point2){
            // Function breaks for vertical lines
        if (x1 == x2){
                // If the line is a point
            if (y1 == y2){
                    // Set the points to return to NaN
                point1.setLocation(Double.NaN, Double.NaN);
                point2.setLocation(point1);
                return false;
            }   // Get the points on the ellipse for the given x-coordinates
            return getEllipseY(e,x1,point1,point2);
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
    /**
     * 
     * @param e
     * @param p1 The first point on the line
     * @param p2 The second point on the line
     * @param point1 The Point2D object that the first point of intersection 
     * will be stored in.
     * @param point2 The Point2D object that the second point of intersection 
     * will  be stored in.
     * @return Whether the line intersects the ellipse.
     */
    protected static boolean getCircleIntersections(Ellipse2D e, Point2D p1, 
            Point2D p2, Point2D point1, Point2D point2){
        return getCircleIntersections(e,p1.getX(),p1.getY(),p2.getX(),p2.getY(),
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
     * @param point A Point2D object to store the results in, or null.
     * @return 
     */
    protected static Point2D getQuadBezierPoint(Point2D p0, Point2D p1, 
            Point2D p2, double t, Point2D point){
            // If the given Point2D object is null
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
     * @param point A Point2D object to store the results in, or null.
     * @return 
     */
    protected static Point2D getQuadBezierControlPoint(Point2D p0, Point2D p1, 
            Point2D p2, Point2D point){
            // If the given Point2D object is null
        if (point == null)
            point = new Point2D.Double();
            // Get the difference between the first and second points' 
        double tx1 = p0.getX() - p1.getX();     // x-coordinates
            // Get the difference between the first and second points' 
        double ty1 = p0.getY() - p1.getY();     // y-coordinates
            // Get the difference between the second and third points' 
        double tx2 = p2.getX() - p1.getX();     // x-coordinates
            // Get the difference between the second and third points' 
        double ty2 = p2.getY() - p1.getY();     // y-coordinates
            // Get the distance between the first and second points
        double d1 = p0.distance(p1);
            // Get the distance between the second and third points
        double d2 = p2.distance(p1);
            // Multiply the distance between first and second points by the 
            // distance between the second and third points, and get the square 
        double d3 = Math.sqrt(d1*d2);   // root of the result
        point.setLocation(p1.getX()-(d3*(tx1/d1+tx2/d2))/2,
                p1.getY()-(d3*(ty1/d1+ty2/d2))/2);
        return point;
    }
    /**
     * 
     * @param p0 The starting point of the curve
     * @param p1 The first point on the curve to pass through
     * @param p2 The second point on the curve to pass through
     * @param p3 The end point of the curve
     * @param controlP1 The Point2D object that the first control point will be 
     * stored in.
     * @param controlP2 The Point2D object that the second control point will be 
     * stored in.
     */
    protected static void getCubicBezierControlPoints(Point2D p0, Point2D p1, 
            Point2D p2, Point2D p3, Point2D controlP1, Point2D controlP2){
            // Get the y-coordinate for the first control point
        double y1 = (-5*p0.getY()+18*p1.getY()-9*p2.getY()+2*p3.getY())/6;
            // Get the y-coordinate for the second control point
        double y2 = (2*p0.getY()-9*p1.getY()+18*p2.getY()-5*p3.getY())/6;
        controlP1.setLocation(p1.getX(),y1);
        controlP2.setLocation(p2.getX(),y2);
    }
    /**
     * https://www.codeproject.com/Articles/31859/Draw-a-Smooth-Curve-through-a-Set-of-2D-Points-wit
     * @param knots A list containing all the knots in the spline
     * @param ctrlPts1 A list to get the first control points
     * @param ctrlPts2 A list to get the second control points
     */
    protected static void getCubicBezierSplineControlPoints(java.util.List<Point2D> knots,
            java.util.List<Point2D> ctrlPts1, java.util.List<Point2D> ctrlPts2){
            // If the knots list is null or there are less than two knots
        if (knots == null || knots.size() < 2)
            throw new IllegalArgumentException("There must be at least 2 knots");
            // Clear the first control points list
        ctrlPts1.clear();
            // Clear the second control points list
        ctrlPts2.clear();
            // The number of control points is the number of knots - 1
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
        
            // Right hand side vector
        double[][] rhs = new double[2][n];
        
            // Set right hand side values
        rhs[0][0] = knots.get(0).getX()+2*knots.get(1).getX();
        rhs[1][0] = knots.get(0).getY()+2*knots.get(1).getY();
            // Go through and calculate the right hand side values for the 
            // middle values
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
        double[][] coords = new double[2][n];
        coords[0][0] = rhs[0][0] / b;
        coords[1][0] = rhs[1][0] / b;
            // Decomposition and forward substitution
        for (int i = 1; i < n; i++){
            tmp[i] = 1 / b;
            b = ((i < n-1)?4.0:3.5)-tmp[i];
            coords[0][i] = (rhs[0][i] - coords[0][i-1]) / b;
            coords[1][i] = (rhs[1][i] - coords[1][i-1]) / b;
        }
            // Backsubstitution
        for (double[] arr : coords){
            for (int i = 1; i < n; i++){
                arr[n-i-1] -= tmp[n-i] * arr[n-i];
            }
        }
        
            // Populate the control point arrays
        for (int i = 0; i < n; i++){
                // First control point
            ctrlPts1.add(new Point2D.Double(coords[0][i],coords[1][i]));
                // Second control point
            double x, y;
                // If not the last set of control points
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
    /**
     * 
     * @param line1 The line to be checked and shortened.
     * @param line2 The line to intersect with {@code line1}
     * @param getY The equation for {@code line1}
     * @param l1 A scratch Line2D object to use to calculate one half of {@code 
     * line1}, or null.
     * @param l2 A scratch Line2D object to use to calculate the other half of 
     * {@code line1}, or null.
     */
    private static void getIntersectingLine(Line2D line1, Line2D line2, 
            DoubleUnaryOperator getY, Line2D l1, Line2D l2){
            // Get the center x-coordinate for line 1
        double x1 = (line1.getX1()+line1.getX2()) / 2.0;
            // Get the actual y-coordinate for the center of line 1
        double y1 = getY.applyAsDouble(x1);
            // If the first scratch Line2D object is null
        if (l1 == null)
            l1 = new Line2D.Double();
            // If the second scratch Line2D object is null
        if (l2 == null)
            l2 = new Line2D.Double();
            // Get the first half of line 1
        l1.setLine(line1.getX1(), line1.getY1(), x1, y1);
            // Get the second half of line 1
        l2.setLine(x1, y1, line1.getX2(), line1.getY2());
            // If the first half of line 1 intersects line 2
        if (l1.intersectsLine(line2))
                // Set line 1 to the first half
            line1.setLine(l1);
        else
                // Set line 1 to the second half
            line1.setLine(l2);
    }
    /**
     * 
     * @param x The x-coordinate to offset the point by.
     * @param y The y-coordinate to offset the point by.
     * @param x1 The first shared x-coordinate for the two lines.
     * @param x2 The second shared x-coordinate for the two lines.
     * @param getY1 The equation for the first line.
     * @param getY2 The equation for the second line.
     * @param resolution
     * @param point A Point2D object to store the results in, or null.
     * @return A rough approximation of the point at which two lines intersect
     */
    protected static Point2D getLineIntersection(double x, double y, double x1, 
            double x2, DoubleUnaryOperator getY1, DoubleUnaryOperator getY2, 
            int resolution, Point2D point){
            // If the given Point2D object is null
        if (point == null)
            point = new Point2D.Double();
            // Create a Line2D object to represent rough approximation of a 
            // segment of the line produced by getY1
        Line2D line1 = new Line2D.Double(x1, getY1.applyAsDouble(x1), 
                x2, getY1.applyAsDouble(x2));
            // Create a Line2D object to represent rough approximation of a 
            // segment of the line produced by getY2
        Line2D line2 = new Line2D.Double(x1, getY2.applyAsDouble(x1), 
                x2, getY2.applyAsDouble(x2));
            // Create a scratch Line2D object
        Line2D line3 = new Line2D.Double();
            // Create a second scratch Line2D object
        Line2D line4 = new Line2D.Double();
            // Run through the tests until we have reached the desired resolution
        for (int i = 0; i < resolution; i++){
                // If the length of line 1 is greater than or equal to the 
                // length of line 2
            if (line1.getP1().distance(line1.getP2()) >= line2.getP1().distance(line2.getP2()))
                    // Get the half of line 1 that intersects with line 2
                getIntersectingLine(line1,line2,getY1,line3,line4);
             else 
                    // Get the half of line 2 that intersects with line 1
                getIntersectingLine(line2,line1,getY2,line3,line4);
        }
            // Get the average of the x-coordinates for the lines
        double tempX = (line1.getX1()+line1.getX2()+line2.getX1()+line2.getX2())/4.0;
            // Set the point of intersection to be the average of the lines'
            // x-coordinates, and the average of the y-coordinates at the 
            // average of the x-coordinates.
        point.setLocation(tempX+x, 
                (getY1.applyAsDouble(tempX)+getY2.applyAsDouble(tempX))/2.0+y);
        return point;
    }
    /**
     * 
     * @param x The x-coordinate to offset the point by.
     * @param y The y-coordinate to offset the point by.
     * @param x1 The first shared x-coordinate for the two lines.
     * @param x2 The second shared x-coordinate for the two lines.
     * @param getY1 The equation for the first line.
     * @param getY2 The equation for the second line.
     * @param point A Point2D object to store the results in, or null.
     * @return 
     */
    protected static Point2D getLineIntersection(double x, double y, double x1, 
            double x2, DoubleUnaryOperator getY1, DoubleUnaryOperator getY2, 
            Point2D point){
        return getLineIntersection(x,y,x1,x2,getY1,getY2,
                DEFAULT_LINE_INTERSECTION_RESOLUTION,point);
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
    
    static void printShape(String shapeName, Shape shape){
        Rectangle2D bounds = shape.getBounds2D();
        System.out.println(shapeName+": " + bounds + ", Center=(" + 
                bounds.getCenterX() + ", " + bounds.getCenterY()+"), Max=("+
                bounds.getMaxX() + ", " + bounds.getMaxY()+")");
    }
}
