/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import geom.GeometryMath;
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
 * of the code is based off of. Given their help, I have decided to credit them 
 * as a co-author for this class.
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
    protected static final Color BACKGROUND_GRADIENT_COLOR_2 = 
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
     * This is the main color that is used to draw the lines around and on 
     * Rambley the Raccoon. That is to say, this is the color which most of the 
     * lines for Rambley is comprised of.
     */
    public static final Color RAMBLEY_LINE_COLOR = new Color(0x624361);
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
     * This is the color for the lines around Rambley the Raccoon's paws. That 
     * is to say, this is the color for the lines around Rambley's hands and 
     * feet.
     */
    public static final Color RAMBLEY_PAW_LINE_COLOR = RAMBLEY_LINE_COLOR;
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
     * This is the color for the lines around Rambley the Raccoon's eyes.
     */
    public static final Color RAMBLEY_EYE_LINE_COLOR = Color.BLACK;
    /**
     * This is the color for Rambley the Raccoon's irises.
     */
    public static final Color RAMBLEY_IRIS_COLOR = new Color(0x883EC1);
    /**
     * This is the color for the lines around of Rambley the Raccoon's irises 
     * and pupils.
     */
    public static final Color RAMBLEY_IRIS_LINE_COLOR = Color.BLACK;//new Color(0x23163F);
    /**
     * This is the color for evil Rambley's irisis. Evil Rambley is a version of 
     * Rambley the Raccoon with red eyes that first appeared on thumbnails of 
     * videos from the YouTube channel GameTheory on the topic of Indigo Park.
     */
    public static final Color EVIL_RAMBLEY_IRIS_COLOR = Color.RED;
    /**
     * This is the color for the lines around evil Rambley's irises and pupils. 
     * Evil Rambley is a version of Rambley the Raccoon with red eyes that first 
     * appeared on thumbnails of videos from the YouTube channel GameTheory on 
     * the topic of Indigo Park.
     */
    public static final Color EVIL_RAMBLEY_IRIS_LINE_COLOR = 
            RAMBLEY_IRIS_LINE_COLOR;
    /**
     * This is the color for Rambley the Raccoon's pupils.
     */
    public static final Color RAMBLEY_PUPIL_COLOR = Color.WHITE;
    /**
     * This is the color for Rambley the Raccoon's nose.
     */
    public static final Color RAMBLEY_NOSE_COLOR = RAMBLEY_PAW_COLOR;
    /**
     * This is the color for the line around and on Rambley the Raccoon's nose.
     */
    public static final Color RAMBLEY_NOSE_LINE_COLOR = RAMBLEY_LINE_COLOR;
    /**
     * This is the color for the inside of Rambley the Raccoon's mouth.
     */
    public static final Color RAMBLEY_MOUTH_COLOR = new Color(0x3D0D2F);
    /**
     * This is the color for the lines around and in Rambley the Raccoon's 
     * mouth.
     */
    public static final Color RAMBLEY_MOUTH_LINE_COLOR = RAMBLEY_LINE_COLOR;
    /**
     * This is the color for Rambley the Raccoon's teeth.
     */
    public static final Color RAMBLEY_TEETH_COLOR = Color.WHITE;
    /**
     * This is the color for the lines around Rambley the Raccoon's teeth.
     */
    public static final Color RAMBLEY_TEETH_LINE_COLOR = RAMBLEY_MOUTH_LINE_COLOR;
    /**
     * This is the color for Rambley the Raccoon's tongue.
     */
    public static final Color RAMBLEY_TONGUE_COLOR = new Color(0x724794);
    /**
     * This is the color for the lines around and on Rambley the Raccoon's 
     * tongue.
     */
    public static final Color RAMBLEY_TONGUE_LINE_COLOR = RAMBLEY_TONGUE_COLOR;
    /**
     * This is the color for Rambley the Raccoon's red scarf.
     */
    public static final Color RAMBLEY_SCARF_COLOR = new Color(0xC64C57);
    /**
     * This is the color for the lines around and on Rambley the Raccoon's red 
     * scarf.
     */
    public static final Color RAMBLEY_SCARF_LINE_COLOR = new Color(0xA63442);
    /**
     * This is the color for Rambley the Raccoon's train conductor hat which he 
     * wears during the Rambley's Railroad ride.
     */
    public static final Color RAMBLEY_CONDUCTOR_HAT_COLOR = new Color(0x431188);
    /**
     * This is the color for the stripes on Rambley the Raccoon's train 
     * conductor hat.
     */
    public static final Color RAMBLEY_CONDUCTOR_HAT_STRIPE_COLOR = new Color(0xF3E5FE);
    /**
     * This is the color for the line around Rambley the Raccoon's train 
     * conductor hat.
     */
    public static final Color RAMBLEY_CONDUCTOR_HAT_LINE_COLOR = Color.BLACK;
    /**
     * This is the color for the outline that goes around Rambley the Raccoon.
     */
    public static final Color RAMBLEY_OUTLINE_COLOR = Color.WHITE;
    /**
     * This is the color for Rambley the Raccoon's drop shadow.
     */
    public static final Color RAMBLEY_DROP_SHADOW_COLOR = Color.BLACK;
    /**
     * This is the width at which Rambley is rendered at internally when without 
     * his scarf.
     * @todo Figure out a more controllable method for the internal rendering 
     * size, instead of having it change depending on whether Rambley has his 
     * scarf or not.
     * @see INTERNAL_RENDER_WIDTH_2
     * @see #getRambleyWidth
     */
    private static final double INTERNAL_RENDER_WIDTH_1 = 256;
    /**
     * This is the width at which Rambley is rendered at internally when he has 
     * his scarf.
     * @todo Figure out a more controllable method for the internal rendering 
     * size, instead of having it change depending on whether Rambley has his 
     * scarf or not.
     * @see INTERNAL_RENDER_WIDTH_1
     * @see #getRambleyWidth
     */
    private static final double INTERNAL_RENDER_WIDTH_2 = 320;
    /**
     * This is the height at which Rambley is rendered at internally when without 
     * his scarf.
     * @todo Figure out a more controllable method for the internal rendering 
     * size, instead of having it change depending on whether Rambley has his 
     * scarf or not.
     * @see INTERNAL_RENDER_HEIGHT_2
     * @see #getRambleyHeight() 
     */
    private static final double INTERNAL_RENDER_HEIGHT_1 = 256;
    /**
     * This is the height at which Rambley is rendered at internally when he has 
     * his scarf.
     * @todo Figure out a more controllable method for the internal rendering 
     * size, instead of having it change depending on whether Rambley has his 
     * scarf or not.
     * @see INTERNAL_RENDER_HEIGHT_1
     * @see #getRambleyHeight() 
     */
    private static final double INTERNAL_RENDER_HEIGHT_2 = 320;
    /**
     * The offset for the y-coordinate of the top center of Rambley's earless 
     * head when Rambley does not have his scarf.
     * @todo Figure out a more controllable method for determining Rambley's 
     * position in the image.
     * @see RAMBLEY_Y_OFFSET_2
     * @see #getRambleyY() 
     */
    private static final double RAMBLEY_Y_OFFSET_1 = 70;
    /**
     * The offset for the y-coordinate of the top center of Rambley's earless 
     * head when Rambley has his scarf.
     * @todo Figure out a more controllable method for determining Rambley's 
     * position in the image.
     * @see RAMBLEY_Y_OFFSET_1
     * @see #getRambleyY() 
     */
    private static final double RAMBLEY_Y_OFFSET_2 = 80;
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
     * This is the default spacing between the lines in the pixel grid. For the 
     * vertical lines, this is the horizontal spacing. For the horizontal lines, 
     * this is the vertical spacing.
     */
    protected static final double DEFAULT_PIXEL_GRID_LINE_SPACING = 5;
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
     * @see RAMBLEY_PUPIL_SIZE
     */
    protected static final double RAMBLEY_IRIS_SIZE = 24;
    /**
     * This contains half of the {@link RAMBLEY_IRIS_SIZE size} of Rambley's 
     * irises. This is used for calculating the location of Rambley's irises 
     * when using their center coordinates to position them.
     * @see RAMBLEY_IRIS_SIZE
     */
    private static final double RAMBLEY_IRIS_HALF_SIZE = RAMBLEY_IRIS_SIZE/2.0;
    /**
     * This is the width and height of Rambley's pupils.
     * @see RAMBLEY_IRIS_SIZE
     */
    protected static final double RAMBLEY_PUPIL_SIZE = RAMBLEY_IRIS_SIZE-14;
    /**
     * This contains half of the {@link RAMBLEY_PUPIL_SIZE size} of Rambley's 
     * pupils. Rambley's pupils are centered in Rambley's irises.
     * @see RAMBLEY_PUPIL_SIZE
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
     * This is the multiplier to use to convert coordinates in the coordinate 
     * system used by the equations provided by AnimalWave on Discord to the 
     * image coordinate system. 
     */
    private static final double RAMBLEY_ANIMALWAVE_MULTIPLIER = 42;
    /**
     * This is the height at which Rambley's ears are rendered at. This is not 
     * necessarily the final height of Rambley's ears, as the ears may be 
     * shorter or taller than this due to the nature of how the shape of the 
     * ears is created. This is also used to vertically flip the ears as 
     * otherwise they'd produce upside down ears. 
     */
    protected static final double RAMBLEY_EAR_HEIGHT = 
            1.8*RAMBLEY_ANIMALWAVE_MULTIPLIER;
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
     * @see RAMBLEY_ANIMALWAVE_MULTIPLIER
     * @see RAMBLEY_EAR_Y_OFFSET
     */
    private static double graphicsToEarEquY(double y){
        y = RAMBLEY_EAR_HEIGHT - y;
        y /= RAMBLEY_ANIMALWAVE_MULTIPLIER;
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
     * @see RAMBLEY_ANIMALWAVE_MULTIPLIER
     * @see RAMBLEY_EAR_X_OFFSET
     */
    private static double graphicsToEarEquX(double x){
        x /= RAMBLEY_ANIMALWAVE_MULTIPLIER;
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
     * @see RAMBLEY_ANIMALWAVE_MULTIPLIER
     * @see RAMBLEY_EAR_Y_OFFSET
     */
    private static double earEquToGraphicsY(double y){
        y -= RAMBLEY_EAR_Y_OFFSET;
        y *= RAMBLEY_ANIMALWAVE_MULTIPLIER;
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
     * @see RAMBLEY_ANIMALWAVE_MULTIPLIER
     * @see RAMBLEY_EAR_X_OFFSET
     */
    private static double earEquToGraphicsX(double x){
        return (RAMBLEY_EAR_X_OFFSET-x)*RAMBLEY_ANIMALWAVE_MULTIPLIER;
    }
    /**
     * This is the thickness of the outline around Rambley.
     */
    protected static final float RAMBLEY_OUTLINE_THICKNESS = 6.0f;
    /**
     * This is the x offset for Rambley's drop shadow.
     * @see RAMBLEY_DROP_SHADOW_Y_OFFSET
     */
    protected static final double RAMBLEY_DROP_SHADOW_X_OFFSET = 4.5;
    /**
     * This is the y offset for Rambley's drop shadow.
     * @see RAMBLEY_DROP_SHADOW_X_OFFSET
     */
    protected static final double RAMBLEY_DROP_SHADOW_Y_OFFSET = 6.0;
    /**
     * This is the width of Rambley's fangs at their base. It is worth 
     * mentioning that the base of Rambley's fangs are not visible, and are 
     * covered by the curve of his mouth.
     */
    protected static final double RAMBLEY_FANG_WIDTH = 8.0;
    /**
     * This is half the width of Rambley's fangs at their base. This is used to 
     * calculate the curve used to produce Rambley's fangs.
     * @see RAMBLEY_FANG_WIDTH
     */
    private static final double RAMBLEY_FANG_HALF_WIDTH =RAMBLEY_FANG_WIDTH/2.0;
    /**
     * This is the total height of Rambley's fangs. This includes the visible 
     * portion of Rambley's fangs and the portion of Rambley's fangs that is 
     * obscured by the top of Rambley's mouth.
     * @see RAMBLEY_FANG_VISIBLE_HEIGHT
     */
    protected static final double RAMBLEY_FANG_HEIGHT = 19.5;
    /**
     * This is the height of the visible portion of Rambley's fangs at their 
     * center.
     * @see RAMBLEY_FANG_HEIGHT
     */
    protected static final double RAMBLEY_FANG_VISIBLE_HEIGHT = 9.5;
    /**
     * This is the height of the portion of Rambley's fangs that is obscured by 
     * the top of Rambley's mouth.
     * @see RAMBLEY_FANG_HEIGHT
     * @see RAMBLEY_FANG_VISIBLE_HEIGHT
     */
    private static final double RAMBLEY_FANG_OBSCURED_HEIGHT = 
            RAMBLEY_FANG_HEIGHT - RAMBLEY_FANG_VISIBLE_HEIGHT;
    /**
     * This is the amount by which the upper portion of the neck portion of 
     * Rambley's scarf is shifted up from the bottom portion of the scarf.
     */
    private static final double RAMBLEY_NECK_SCARF_Y_OFFSET = 23;
    /**
     * This is the width of the neck portion of Rambley's scarf.
     * @see RAMBLEY_NECK_SCARF_HEIGHT
     */
    protected static final double RAMBLEY_NECK_SCARF_WIDTH = 80;
    /**
     * This is half the width of the neck portion of Rambley's scarf.
     * @see RAMBLEY_NECK_SCARF_WIDTH
     */
    private static final double RAMBLEY_NECK_SCARF_HALF_WIDTH = 
            RAMBLEY_NECK_SCARF_WIDTH/2.0;
    /**
     * This is the height of the neck portion of Rambley's scarf.
     * @see RAMBLEY_NECK_SCARF_WIDTH
     */
    protected static final double RAMBLEY_NECK_SCARF_HEIGHT = 56;
    /**
     * This is half the height of the neck portion of Rambley's scarf.
     * @see RAMBLEY_NECK_SCARF_HEIGHT
     */
    private static final double RAMBLEY_NECK_SCARF_HALF_HEIGHT = 
            RAMBLEY_NECK_SCARF_HEIGHT/2.0;
    
    private static final double RAMBLEY_SCARF_LOWER_END_CONST = 3.62;
    
    private static final double RAMBLEY_SCARF_LOWER_END_SQRT = Math.sqrt(6.5528);
    
    private static final double RAMBLEY_SCARF_LOWER_END_DENOM = 2.2;
    /**
     * This is the offset for the x-coordinates in the equations used to 
     * calculate the curves for the lower end of Rambley's scarf. This is 
     * effectively used to flip the scarf end horizontally since the equations 
     * used produce a horizontally flipped version of the scarf.
     */
    private static final double RAMBLEY_SCARF_LOWER_END_X_OFFSET = 
            (RAMBLEY_SCARF_LOWER_END_CONST + RAMBLEY_SCARF_LOWER_END_SQRT) / 
            RAMBLEY_SCARF_LOWER_END_DENOM;
    /**
     * This is the width of the lower end of Rambley's scarf.
     */
    protected static final double RAMBLEY_SCARF_LOWER_END_WIDTH = 
            (RAMBLEY_SCARF_LOWER_END_X_OFFSET - 
            ((RAMBLEY_SCARF_LOWER_END_CONST - RAMBLEY_SCARF_LOWER_END_SQRT) / 
            RAMBLEY_SCARF_LOWER_END_DENOM)) * RAMBLEY_ANIMALWAVE_MULTIPLIER;
    /**
     * This converts the given x-coordinate in the image coordinate system to a 
     * X-coordinate in the coordinate system used by the equations used to 
     * calculate the curves that make up the lower end of Rambley's scarf. 
     * @param x The x-coordinate in the image coordinate system to convert.
     * @return The x-coordinate in the lower scarf end equation coordinate 
     * system.
     * @see #lowScarfEquToGraphicsY 
     * @see RAMBLEY_ANIMALWAVE_MULTIPLIER
     * @see RAMBLEY_SCARF_LOWER_END_X_OFFSET
     */
    private static double graphicsToLowScarfEquX(double x){
        x /= RAMBLEY_ANIMALWAVE_MULTIPLIER;
        return RAMBLEY_SCARF_LOWER_END_X_OFFSET - x;
    }
    /**
     * This converts the given y-coordinate in the coordinate system used by the 
     * equations used to calculate the curves that make up the lower end of 
     * Rambley's scarf to a y-coordinate in the image coordinate system.
     * @param y The y-coordinate in the lower scarf end equation coordinate 
     * system to convert.
     * @return The y-coordinate in the image coordinate system.
     * @see #graphicsToLowScarfEquX 
     * @see RAMBLEY_ANIMALWAVE_MULTIPLIER
     */
    private static double lowScarfEquToGraphicsY(double y){
        return y * RAMBLEY_ANIMALWAVE_MULTIPLIER;
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
     * This is the flag for whether the outline around Rambley will be painted.
     */
    public static final int PAINT_RAMBLEY_OUTLINE_FLAG =    0x00000004;
    /**
     * This is the flag for whether Rambley's shadow will be painted.
     */
    public static final int PAINT_RAMBLEY_SHADOW_FLAG =     0x00000008;
    /**
     * This is the flag for ignoring Rambley's aspect ratio when rendering 
     * Rambley.
     */
    public static final int IGNORE_ASPECT_RATIO_FLAG =      0x00000010;
    /**
     * This is the flag for whether evil Rambley will be painted instead of 
     * normal Rambley. Evil Rambley is a version of Rambley the Raccoon with red 
     * eyes that first appeared on thumbnails of videos from the YouTube channel 
     * GameTheory on the topic of Indigo Park.
     */
    public static final int EVIL_RAMBLEY_FLAG =             0x00000020;
    /**
     * This is the flag for drawing the background polka dots as circles instead 
     * of diamonds.
     */
    public static final int CIRCULAR_BACKGROUND_DOTS_FLAG = 0x00000040;
    /**
     * This is the flag which controls which side certain elements of Rambley 
     * will appear on. This includes which side of Rambley's face his fang will 
     * show on when his mouth is open, and which side the knot on Rambley's 
     * scarf will appear on.
     */
    public static final int RAMBLEY_FLIPPED_FLAG =          0x00000080;
    /**
     * This is the flag which controls whether Rambley's jaw is closed when his 
     * mouth is open.
     */
    public static final int RAMBLEY_JAW_CLOSED_FLAG =       0x00000100;
    /**
     * This is the flag for whether Rambley's scarf will be painted.
     */
    public static final int PAINT_RAMBLEY_SCARF_FLAG =      0x00000200;
    /**
     * This stores the flags that are set initially when a RambleyPainter is 
     * first constructed.
     */
    private static final int DEFAULT_FLAG_SETTINGS = PAINT_BACKGROUND_FLAG | 
            PAINT_PIXEL_GRID_FLAG | PAINT_RAMBLEY_OUTLINE_FLAG | 
            PAINT_RAMBLEY_SHADOW_FLAG | PAINT_RAMBLEY_SCARF_FLAG;
    /**
     * This stores the maximum value a {@code RambleyPainter}'s flags can be and 
     * still be considered valid.
     * @see PAINT_BACKGROUND_FLAG
     * @see PAINT_PIXEL_GRID_FLAG
     * @see PAINT_RAMBLEY_OUTLINE_FLAG
     * @see PAINT_RAMBLEY_SHADOW_FLAG
     * @see IGNORE_ASPECT_RATIO_FLAG
     * @see EVIL_RAMBLEY_FLAG
     * @see CIRCULAR_BACKGROUND_DOTS_FLAG
     * @see RAMBLEY_FLIPPED_FLAG
     * @see RAMBLEY_JAW_CLOSED_FLAG
     * @see PAINT_RAMBLEY_SCARF_FLAG
     */
    public static final int MAXIMUM_VALID_FLAGS = PAINT_BACKGROUND_FLAG | 
            PAINT_PIXEL_GRID_FLAG | PAINT_RAMBLEY_OUTLINE_FLAG | 
            PAINT_RAMBLEY_SHADOW_FLAG | IGNORE_ASPECT_RATIO_FLAG | 
            EVIL_RAMBLEY_FLAG | CIRCULAR_BACKGROUND_DOTS_FLAG | 
            RAMBLEY_FLIPPED_FLAG | RAMBLEY_JAW_CLOSED_FLAG | 
            PAINT_RAMBLEY_SCARF_FLAG;
    /**
     * This identifies that a change has been made to whether the background 
     * should be painted.
     * @see PAINT_BACKGROUND_FLAG
     */
    public static final String BACKGROUND_PAINTED_PROPERTY_CHANGED = 
            "BackgroundPaintedPropertyChanged";
    /**
     * This identifies that a change has been made to whether the pixel grid 
     * should be painted.
     * @see PAINT_PIXEL_GRID_FLAG
     */
    public static final String PIXEL_GRID_PAINTED_PROPERTY_CHANGED = 
            "PixelGridPaintedPropertyChanged";
    /**
     * This identifies that a change has been made to whether Rambley's outline 
     * and shadow should be painted.
     * @see PAINT_RAMBLEY_OUTLINE_FLAG
     */
    public static final String RAMBLEY_OUTLINE_PAINTED_PROPERTY_CHANGED = 
            "RambleyBorderPaintedPropertyChanged";
    /**
     * This identifies that a change has been made to whether Rambley's shadow 
     * should be painted.
     * @see PAINT_RAMBLEY_SHADOW_FLAG
     */
    public static final String RAMBLEY_SHADOW_PAINTED_PROPERTY_CHANGED = 
            "RambleyShadowPaintedPropertyChanged";
    /**
     * This identifies that a change has been made to whether the aspect ratio 
     * for Rambley will be ignored.
     * @see IGNORE_ASPECT_RATIO_FLAG
     */
    public static final String IGNORE_ASPECT_RATIO_PROPERTY_CHANGED = 
            "IgnoreAspectRatioPropertyChanged";
    /**
     * This identifies that a change has been made to whether Rambley is evil or 
     * not.
     * @see EVIL_RAMBLEY_FLAG
     */
    public static final String EVIL_RAMBLEY_PROPERTY_CHANGED = 
            "EvilRambleyPropertyChanged";
    /**
     * This identifies that a change has been made to whether the background 
     * polka dots are circular or diamonds.
     * @see CIRCULAR_BACKGROUND_DOTS_FLAG
     */
    public static final String CIRCULAR_BACKGROUND_DOTS_PROPERTY_CHANGED = 
            "CircularDotsPropertyChanged";
    /**
     * This identifies that a change has been made to which side of Rambley 
     * certain elements will appear on.
     * @see RAMBLEY_FLIPPED_FLAG
     */
    public static final String RAMBLEY_FLIPPED_PROPERTY_CHANGED = 
            "RambleyFlippedPropertyChanged";
    /**
     * This identifies that a change has been made to whether Rambley's jaw is 
     * closed when his mouth is open.
     * @see RAMBLEY_JAW_CLOSED_FLAG
     */
    public static final String RAMBLEY_JAW_CLOSED_PROPERTY_CHANGED = 
            "RambleyJawClosedPropertyChanged";
    /**
     * This identifies that a change has been made to whether Rambley's scarf 
     * should be painted.
     * @see PAINT_RAMBLEY_SCARF_FLAG
     */
    public static final String RAMBLEY_SCARF_PAINTED_PROPERTY_CHANGED = 
            "RambleyScarfPaintedPropertyChanged";
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
        nameMap.put(PAINT_RAMBLEY_OUTLINE_FLAG, 
                RAMBLEY_OUTLINE_PAINTED_PROPERTY_CHANGED);
        nameMap.put(PAINT_RAMBLEY_SHADOW_FLAG, 
                RAMBLEY_SHADOW_PAINTED_PROPERTY_CHANGED);
        nameMap.put(IGNORE_ASPECT_RATIO_FLAG, 
                IGNORE_ASPECT_RATIO_PROPERTY_CHANGED);
        nameMap.put(EVIL_RAMBLEY_FLAG, EVIL_RAMBLEY_PROPERTY_CHANGED);
        nameMap.put(CIRCULAR_BACKGROUND_DOTS_FLAG, 
                CIRCULAR_BACKGROUND_DOTS_PROPERTY_CHANGED);
        nameMap.put(RAMBLEY_FLIPPED_FLAG, RAMBLEY_FLIPPED_PROPERTY_CHANGED);
        nameMap.put(RAMBLEY_JAW_CLOSED_FLAG, 
                RAMBLEY_JAW_CLOSED_PROPERTY_CHANGED);
        nameMap.put(PAINT_RAMBLEY_SCARF_FLAG, 
                RAMBLEY_SCARF_PAINTED_PROPERTY_CHANGED);
        
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
    /**
     * This identifies that a change has been made to the thickness of the lines 
     * in the pixel grid.
     */
    public static final String PIXEL_GRID_LINE_THICKNESS_PROPERTY_CHANGED = 
            "PixelGridThicknessPropertyChanged"; 
    /**
     * 
     */
    public static final String RAMBLEY_RIGHT_EYE_X_PROPERTY_CHANGED = 
            "RambleyRightEyeXPropertyChanged";
    /**
     * 
     */
    public static final String RAMBLEY_RIGHT_EYE_Y_PROPERTY_CHANGED = 
            "RambleyRightEyeYPropertyChanged";
    /**
     * 
     */
    public static final String RAMBLEY_LEFT_EYE_X_PROPERTY_CHANGED = 
            "RambleyLeftEyeXPropertyChanged";
    /**
     * 
     */
    public static final String RAMBLEY_LEFT_EYE_Y_PROPERTY_CHANGED = 
            "RambleyLeftEyeYPropertyChanged";
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
    
    /*                      Settings and Listeners
    
    These are fields that relate to controlling the settings for the painter and 
    for listening to this painter.
    */
    
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
    private double gridSpacing;
    /**
     * This is the thickness of the lines in the pixel grid.
     */
    private float gridThickness;
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
    
    /*          Commonly used objects for the rendering process
    
    These are fields that don't change once initialized and are used each time 
    Rambley is painted.
    */
    
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
     * A BasicStroke object used to render most of the lines of Rambley. This 
     * is initially null and is initialized the first time it is used.
     */
    private BasicStroke outlineStroke = null;
    /**
     * A BasicStroke object used to render the outline around Rambley. This is 
     * initially null and is initialized the first time it is used.
     */
    private BasicStroke borderStroke = null;
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
    
    /*                      Dedicated scratch objects
    
    These fields are scratch objects used for doing or rendering specific 
    things. These scratch objects have an intended use, and should not be used 
    for much else, as this could cause issues with code that assumes something 
    of these objects, and that assumption turns out to be wrong.
    */
    
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
     * An Ellipse2D object used to create the top of Rambley's head. This is 
     * initially null and is initialized the first time it is used.
     */
    private Ellipse2D headEllipse = null;
    /**
     * A QuadCurve2D object used for generating Rambley's mouth. This is 
     * initialized the first time it is used.
     */
    private QuadCurve2D mouthCurve1 = null;
    /**
     * A second QuadCurve2D object used for generating Rambley's mouth. This is 
     * initialized the first time it is used.
     */
    private QuadCurve2D mouthCurve2 = null;
    /**
     * A Path2D object used to render the path of Rambley's mouth. This is 
     * initially null and is initialized the first time it is used.
     */
    private Path2D mouthPath = null;
    /**
     * A CubicCurve2D object used for generating the details on the neck portion 
     * of Rambley's scarf. This is initially null and is initialized the first 
     * time it is used.
     */
    private CubicCurve2D scarfCurve1 = null;
    /**
     * This is a scratch AffineTransform used to flip shapes horizontally. This 
     * is initially null and is initialized the first time it is used.
     */
    private AffineTransform horizTx = null;
    /**
     * This is the AffineTransform used to scale down the outer part of 
     * Rambley's ears to get the inner part of his ears. This is initially null 
     * and is initialized the first time it is used.
     */
    private AffineTransform inEarTx = null;
    
    /*                          Generic scratch objects
    
    These fields are scratch objects that are effectively free to use whenever 
    and for whatever purpose, and are here to reduce the amount of objects that 
    are created during the process of rendering Rambley. These scratch objects 
    should not be assumed to be in a given state, or even initialized.
    */
    
    /**
     * A scratch Rectangle2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Rectangle2D rect = null;
    /**
     * A scratch Ellipse2D object used for rendering the Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Ellipse2D ellipse1 = null;
    /**
     * A second scratch Ellipse2D object used for rendering the Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Ellipse2D ellipse2 = null;
    /**
     * A third scratch Ellipse2D object used for rendering the Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Ellipse2D ellipse3 = null;
    /**
     * A scratch Path2D object used for rendering Rambley. This is initialized 
     * the first time it is used. This scratch object may change at any time 
     * during the rendering process, and should not be assumed to be in a known 
     * state before being used.
     */
    private Path2D path = null;
    /**
     * A scratch Point2D object used for rendering Rambley. This is initialized 
     * the first time it is used. This scratch object may change at any time 
     * during the rendering process, and should not be assumed to be in a known 
     * state before being used.
     */
    private Point2D point1 = null;
    /**
     * A second scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Point2D point2 = null;
    /**
     * A third scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Point2D point3 = null;
    /**
     * A fourth scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Point2D point4 = null;
    /**
     * A fifth scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Point2D point5 = null;
    /**
     * A sixth scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Point2D point6 = null;
    /**
     * A seventh scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Point2D point7 = null;
    /**
     * An eighth scratch Point2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private Point2D point8 = null;
    /**
     * A scratch QuadCurve2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private QuadCurve2D quadCurve1 = null;
    /**
     * A second scratch QuadCurve2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private QuadCurve2D quadCurve2 = null;
    /**
     * A scratch CubicCurve2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private CubicCurve2D cubicCurve1 = null;
    /**
     * A second scratch CubicCurve2D object used for rendering Rambley. This is 
     * initialized the first time it is used. This scratch object may change at 
     * any time during the rendering process, and should not be assumed to be in 
     * a known state before being used.
     */
    private CubicCurve2D cubicCurve2 = null;
    /**
     * This is a scratch AffineTransform used to transform shapes. This is 
     * initially null and is initialized the first time it is used. This scratch 
     * object may change at any time during the rendering process, and should 
     * not be assumed to be in a known state before being used.
     */
    private AffineTransform afTx = null;
    
    
    
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
        gridSpacing = DEFAULT_PIXEL_GRID_LINE_SPACING;
        gridThickness = 1.0f;
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
     * @param flags The flags for this {@code RambleyPainter} (must be a 
     * positive integer between 0 and {@link #MAXIMUM_VALID_FLAGS}, inclusive).
     * @throws IllegalArgumentException If the given flags are negative or 
     * greater than {@code MAXIMUM_VALID_FLAGS}.
     */
    public RambleyPainter(int flags){
        this();
        RambleyPainter.this.setFlags(flags);
    }
    /**
     * This checks to see if the given flags are valid, and if not, throws an 
     * IllegalArgumentException.
     * @param flags The flags to check.
     * @throws IllegalArgumentException If the flags is negative or greater than 
     * {@link MAXIMUM_VALID_FLAGS}.
     * @see #MAXIMUM_VALID_FLAGS
     * @see #setFlags
     */
    protected void checkFlags(int flags){
            // If any of the flags are invalid
        if ((flags & MAXIMUM_VALID_FLAGS) != flags)
            throw new IllegalArgumentException("Invalid flags: " + flags);
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
     * @todo Add references to other related methods.
     * 
     * @param flags The flags for this painter (must be a positive integer 
     * between 0 and {@link #MAXIMUM_VALID_FLAGS}, inclusive).
     * @return This {@code RambleyPainter}.
     * @throws IllegalArgumentException If the given flags are negative or 
     * greater than {@code MAXIMUM_VALID_FLAGS}.
     */
    public RambleyPainter setFlags(int flags){
            // If the flags would change
        if (flags != this.flags){
                // Check the flags 
            checkFlags(flags);
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
                if (Integer.highestOneBit(flag)>=Integer.highestOneBit(changed))
                    break;
            }
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
     * @todo Add references to other related methods.
     * 
     * @param flag The flag to be set or cleared based off {@code value}.
     * @param value Whether the flag should be set or cleared.
     * @return This {@code RambleyPainter}.
     * @throws IllegalArgumentException If the flags would exceed {@link 
     * MAXIMUM_VALID_FLAGS} because of setting the given flag.
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
     * @todo Add references to other related methods.
     * 
     * @param flag The flag to be toggled.
     * @return This {@code RambleyPainter}.
     * @throws IllegalArgumentException If the flags would exceed {@link 
     * MAXIMUM_VALID_FLAGS} because of setting the given flag.
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
     * This returns whether the outline around Rambley will be painted by this 
     * {@code RambleyPainter}. The default value for this is {@code true}.
     * @return Whether the outline around Rambley will be painted.
     * @see PAINT_RAMBLEY_OUTLINE_FLAG
     * @see #getFlag 
     * @see setRambleyOutlinePainted
     */
    public boolean isRambleyOutlinePainted(){
        return getFlag(PAINT_RAMBLEY_OUTLINE_FLAG);
    }
    /**
     * This sets whether the outline around Rambley will be painted by this 
     * {@code RambleyPainter}. The default value for this is {@code true}.
     * @param enabled Whether the outline around Rambley should be painted.
     * @return This {@code RambleyPainter}.
     * @see PAINT_RAMBLEY_OUTLINE_FLAG
     * @see #setFlag 
     * @see isRambleyOutlinePainted
     */
    public RambleyPainter setRambleyOutlinePainted(boolean enabled){
        return setFlag(PAINT_RAMBLEY_OUTLINE_FLAG,enabled);
    }
    /**
     * This returns whether Rambley's shadow will be painted by this {@code 
     * RambleyPainter}. The default value for this is {@code true}.
     * @return Whether Rambley's shadow will be painted.
     * @see PAINT_RAMBLEY_SHADOW_FLAG
     * @see #getFlag 
     * @see setRambleyShadowPainted
     */
    public boolean isRambleyShadowPainted(){
        return getFlag(PAINT_RAMBLEY_SHADOW_FLAG);
    }
    /**
     * This sets whether Rambley's shadow will be painted by this {@code 
     * RambleyPainter}. The default value for this is {@code true}.
     * @param enabled Whether Rambley's shadow should be painted.
     * @return This {@code RambleyPainter}.
     * @see PAINT_RAMBLEY_SHADOW_FLAG
     * @see #setFlag 
     * @see isRambleyShadowPainted
     */
    public RambleyPainter setRambleyShadowPainted(boolean enabled){
        return setFlag(PAINT_RAMBLEY_SHADOW_FLAG,enabled);
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
     * This returns whether certain elements of Rambley will appear on the 
     * opposite side they typically appear on. When this is {@code true}, 
     * Rambley's fang will show on the left side of his mouth and the knot on 
     * Rambley's scarf will appear on Rambley's left. When this is {@code 
     * false}, Rambley's fang will show on the right side of his mouth and the 
     * knot on Rambley's scarf will appear on Rambley's right. The default value 
     * for this is {@code false}.
     * 
     * @todo Add references to other related methods. State which elements are 
     * effected as they are added, and which sides they appear on for which 
     * values.
     * 
     * @return {@true} if Rambley's sides are flipped, {@code false} otherwise.
     * @see RAMBLEY_FLIPPED_FLAG
     * @see #getFlag 
     * @see setRambleyFlipped
     */
    public boolean isRambleyFlipped(){
        return getFlag(RAMBLEY_FLIPPED_FLAG);
    }
    /**
     * This sets whether certain elements of Rambley will appear on the opposite 
     * side they typically appear on. When this is {@code true}, 
     * Rambley's fang will show on the left side of his mouth and the knot on 
     * Rambley's scarf will appear on Rambley's left. When this is {@code 
     * false}, Rambley's fang will show on the right side of his mouth and the 
     * knot on Rambley's scarf will appear on Rambley's right. The default value 
     * for this is {@code false}.
     * 
     * @todo Add references to other related methods. State which elements are 
     * effected as they are added, and which sides they appear on for which 
     * values.
     * 
     * @param value {@true} if Rambley's sides should be flipped, {@code false} 
     * if Rambley's sides should not be flipped.
     * @return This {@code RambleyPainter}.
     * @see RAMBLEY_FLIPPED_FLAG
     * @see setFlag 
     * @see isRambleyFlipped
     */
    public RambleyPainter setRambleyFlipped(boolean value){
        return setFlag(RAMBLEY_FLIPPED_FLAG,value);
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
     * This returns whether Rambley's scarf will be painted by this {@code 
     * RambleyPainter}. The default value for this is {@code true}.
     * 
     * @todo Add references to other related methods.
     * 
     * @return Whether Rambley's scarf will be painted.
     * @see #PAINT_RAMBLEY_SCARF_FLAG
     * @see #getFlag 
     * @see #setRambleyScarfPainted 
     */
    public boolean isRambleyScarfPainted(){
        return getFlag(PAINT_RAMBLEY_SCARF_FLAG);
    }
    /**
     * This sets whether Rambley's scarf will be painted by this {@code 
     * RambleyPainter}. The default value for this is {@code true}.
     * 
     * @todo Add references to other related methods.
     * 
     * @param enabled Whether Rambley's scarf should be painted.
     * @return This {@code RambleyPainter}.
     * @see #PAINT_RAMBLEY_SCARF_FLAG
     * @see #setFlag 
     * @see #isRambleyScarfPainted 
     */
    public RambleyPainter setRambleyScarfPainted(boolean enabled){
        return setFlag(PAINT_RAMBLEY_SCARF_FLAG,enabled);
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
     * @return The size of the background polka dots.
     * @see #setBackgroundDotSize 
     * @see #getBackgroundDotSpacing 
     * @see #setBackgroundDotSpacing 
     * @see #isBackgroundPainted 
     * @see #setBackgroundPainted 
     * @see #getCircularBackgroundDots 
     * @see #setCircularBackgroundDots 
     */
    public double getBackgroundDotSize(){
        return dotSize;
    }
    /**
     * This sets the width and height used for the background polka dots. 
     * @param size The size for the background polka dots. 
     * @return This {@code RambleyPainter}.
     * @see #getBackgroundDotSize 
     * @see #getBackgroundDotSpacing 
     * @see #setBackgroundDotSpacing 
     * @see #isBackgroundPainted 
     * @see #setBackgroundPainted 
     * @see #getCircularBackgroundDots 
     * @see #setCircularBackgroundDots 
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
     * @todo Rework the documentation for this method.
     * 
     * @return The diagonal spacing between the background polka dots.
     * @see #getBackgroundDotSize 
     * @see #setBackgroundDotSize 
     * @see #setBackgroundDotSpacing 
     * @see #isBackgroundPainted 
     * @see #setBackgroundPainted 
     * @see #getCircularBackgroundDots 
     * @see #setCircularBackgroundDots 
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
     * @todo Rework the documentation for this method.
     * 
     * @param spacing The diagonal spacing between the background polka dots.
     * @return This {@code RambleyPainter}.
     * @see #getBackgroundDotSize 
     * @see #setBackgroundDotSize 
     * @see #getBackgroundDotSpacing 
     * @see #isBackgroundPainted 
     * @see #setBackgroundPainted 
     * @see #getCircularBackgroundDots 
     * @see #setCircularBackgroundDots 
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
     * @return The spacing between the lines in the pixel grid.
     * @see #setPixelGridLineSpacing 
     * @see #getPixelGridLineThickness 
     * @see #setPixelGridLineThickness 
     * @see #isPixelGridPainted 
     * @see #setPixelGridPainted 
     */
    public double getPixelGridLineSpacing(){
        return gridSpacing;
    }
    /**
     * This sets the spacing between the lines in the pixel grid. For the 
     * vertical lines, this is the horizontal spacing. For the horizontal lines, 
     * this is the vertical spacing.
     * @param spacing The spacing between the lines in the pixel grid.
     * @return This {@code RambleyPainter}.
     * @see #getPixelGridLineSpacing 
     * @see #getPixelGridLineThickness 
     * @see #setPixelGridLineThickness 
     * @see #isPixelGridPainted 
     * @see #setPixelGridPainted 
     */
    public RambleyPainter setPixelGridLineSpacing(double spacing){
            // If the new spacing is different from the old spacing
        if (spacing != gridSpacing){
                // Get the old line spacing
            double old = gridSpacing;
            gridSpacing = spacing;
            firePropertyChange(PIXEL_GRID_LINE_SPACING_PROPERTY_CHANGED,old,spacing);
        }
        return this;
    }
    /**
     * This returns the thickness of the lines in the pixel grid.
     * @return The thickness for the lines in the pixel grid.
     * @see #setPixelGridLineThickness 
     * @see #getPixelGridLineSpacing 
     * @see #setPixelGridLineSpacing 
     * @see #isPixelGridPainted 
     * @see #setPixelGridPainted 
     */
    public float getPixelGridLineThickness(){
        return gridThickness;
    }
    /**
     * This sets the thickness of the lines in the pixel grid.
     * @param thickness The thickness for the lines in the pixel grid.
     * @return This {@code RambleyPainter}.
     * @throws IllegalArgumentException If the given line thickness is negative.
     * @see #getPixelGridLineThickness 
     * @see #getPixelGridLineSpacing 
     * @see #getPixelGridLineSpacing 
     * @see #isPixelGridPainted 
     * @see #setPixelGridPainted 
     */
    public RambleyPainter setPixelGridLineThickness(float thickness){
        if (thickness < 0.0f)
            throw new IllegalArgumentException("Pixel Grid line thickness must "
                    + "be greater than or equal to zero ("+thickness+ " < 0)");
            // If the new thickness is different from the old thickness
        if (thickness != gridThickness){
                // Get the old line thickness
            float old = gridThickness;
            gridThickness = thickness;
            firePropertyChange(PIXEL_GRID_LINE_THICKNESS_PROPERTY_CHANGED,old,thickness);
        }
        return this;
    }
    /**
     * This checks the given value to see if it is different from the old value 
     * and is between 0 and 1
     * 
     * @todo Rework the documentation for this method.
     * 
     * @param value The new value
     * @param oldValue The old value
     * @param name The name of the value
     * @return Whether the value has changed.
     * @IllegalArgumentException If the given new value is either less than zero 
     * or greater than one.
     */
    private boolean checkControlDouble(double value,double oldValue,String name){
            // If the old and new values are the same
        if (value == oldValue)
            return false;
            // If the value is less than 0 or greater than 1
        if (value < 0.0 || value > 1.0)
            throw new IllegalArgumentException(name+" must be between 0 and 1, "
                    + "inclusive (0.0 <= "+value+" <= 1.0)");
        return true;
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
     * @IllegalArgumentException If either the right x or y are either less than 
     * zero or greater than one.
     */
    public RambleyPainter setRambleyRightEye(double x, double y){
            // If either the x position or the y position has changed
        if (checkControlDouble(x,eyeRightX,"Right eye x value") || 
                checkControlDouble(y,eyeRightY,"Right eye y value")){
                // Get the old value for the x
            double oldX = eyeRightX;
                // Get the old value for the y
            double oldY = eyeRightY;
            eyeRightX = x;
            eyeRightY = y;
                // If the x position changed
            if (oldX != x)
                    // Fire a property change event for the x position
                firePropertyChange(RAMBLEY_RIGHT_EYE_X_PROPERTY_CHANGED,oldX,x);
                // If the y position changed
            if (oldY != y)
                    // Fire a property change event for the y position
                firePropertyChange(RAMBLEY_RIGHT_EYE_Y_PROPERTY_CHANGED,oldY,y);
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
     * @IllegalArgumentException If either the left x or y are either less than 
     * zero or greater than one.
     */
    public RambleyPainter setRambleyLeftEye(double x, double y){
            // If either the x position or the y position has changed
        if (checkControlDouble(x,eyeLeftX,"Left eye x value") || 
                checkControlDouble(y,eyeLeftY,"Left eye y value")){
                // Get the old value for the x
            double oldX = eyeLeftX;
                // Get the old value for the y
            double oldY = eyeLeftY;
            eyeLeftX = x;
            eyeLeftY = y;
                // If the x position changed
            if (oldX != x)
                    // Fire a property change event for the x position
                firePropertyChange(RAMBLEY_LEFT_EYE_X_PROPERTY_CHANGED,oldX,x);
                // If the y position changed
            if (oldY != y)
                    // Fire a property change event for the y position
                firePropertyChange(RAMBLEY_LEFT_EYE_Y_PROPERTY_CHANGED,oldY,y);
        }
        return this;
    }
    /**
     * 
     * @param x
     * @param y
     * @return 
     * @IllegalArgumentException If either the x or y are either less than zero 
     * or greater than one.
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
     * @IllegalArgumentException If the given height is either less than zero or 
     * greater than one.
     */
    public RambleyPainter setRambleyOpenMouthWidth(double width){
            // If the width value would change
        if (checkControlDouble(width,mouthOpenWidth,"Open mouth width")){
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
     * @IllegalArgumentException If the given height is either less than zero or 
     * greater than one.
     */
    public RambleyPainter setRambleyOpenMouthHeight(double height){
            // If the height value would change
        if (checkControlDouble(height,mouthOpenHeight,"Open mouth height")){
                // Get the old height value
            double old = mouthOpenHeight;
            mouthOpenHeight = height;
            firePropertyChange(RAMBLEY_OPEN_MOUTH_HEIGHT_PROPERTY_CHANGED,old,height);
        }
        return this;
    }
    /**
     * This returns whether Rambley's mouth is open.
     * 
     * @todo Rework the documentation for this method.
     * 
     * @return Whether Rambley's mouth is open.
     */
    public boolean isRambleyMouthOpen(){
        return getRambleyOpenMouthHeight() > 0&&getRambleyOpenMouthWidth() > 0;
    }
    
    
    
    /**
     * This returns an AffineTransform object that flips shapes horizontally and 
     * translates it by {@code dx}.
     * @param dx The dx value by which to translate stuff, relative to the 
     * original coordinate space.
     * @param tx An AffineTransform to store the results in, or null.
     * @return An AffineTransform used to flip things horizontally.
     * @see #getHorizontalFlipTransform(double, Shape, AffineTransform) 
     * @see #getHorizontalMirrorTransform 
     * @see #createHorizontallyMirroredArea 
     * @see #mirrorPathHorizontally 
     * @see #flipPathHorizontally 
     */
    protected AffineTransform getHorizontalFlipTransform(double dx, 
            AffineTransform tx){
            // If the given AffineTransform is null
        if (tx == null)
                // Get a transform that will flip things horizontally
            tx = AffineTransform.getScaleInstance(-1, 1);
        else    // Set the transform to one that will flip things horizontally
            tx.setToScale(-1, 1);
            // Translate everything by dx
        tx.translate(-dx, 0);
        return tx;
    }
    /**
     * This returns an AffineTransform object that flips shapes horizontally and 
     * translates it by {@code dx} plus the maximum x-coordinate of the given 
     * shape. If the given shape is null, then this is equivalent to calling 
     * {@link #getHorizontalFlipTransform(double, AffineTransform) 
     * getHorizontalFlipTransform(dx, tx)}. If the given shape is not null, then 
     * this is equivalent to calling {@link getHorizontalFlipTransform(double, 
     * AffineTransform) getHorizontalFlipTransform(dx + 
     * shape.getBounds2D().getMaxX(), tx)}. 
     * @param dx The dx value by which to translate stuff, relative to the left 
     * side of the image.
     * @param shape The shape for which to get the x-coordinate to use to shift 
     * stuff back into the image, or null.
     * @param tx An AffineTransform to store the results in, or null.
     * @return An AffineTransform used to flip things horizontally.
     * @see #getHorizontalFlipTransform(double, AffineTransform) 
     * @see #getHorizontalMirrorTransform 
     * @see #createHorizontallyMirroredArea 
     * @see #mirrorPathHorizontally 
     * @see #flipPathHorizontally 
     */
    protected AffineTransform getHorizontalFlipTransform(double dx,Shape shape, 
            AffineTransform tx){
            // If the given shape is null
        if (shape == null)
                // Return a regular horizontal flip transform
            return getHorizontalFlipTransform(dx,tx);
            // Return a horizontal flip transform that translates shapes by the 
            // given shape's maximum x-coordinate to put it back on the image, 
            // and then translated again by the given x offset
        return getHorizontalFlipTransform(dx+getBoundsOfShape(shape).getMaxX(),
                tx);
    }
    /**
     * This returns an AffineTransform object that flips shapes horizontally and 
     * translates it in such a way to appear as though it was mirrored over the 
     * vertical line at the given x-coordinate. If the given shape is null, then 
     * this is equivalent to calling {@link #getHorizontalFlipTransform(double, 
     * AffineTransform) getHorizontalFlipTransform(x, tx)}.
     * @param x The x-coordinate of the line to mirror shapes over.
     * @param shape The shape to use to calculate the x component of the 
     * translation.
     * @param tx An AffineTransform to store the results in, or null.
     * @return An AffineTransform used to mirror things horizontally over the 
     * vertical line at the given x-coordinate.
     * @see #getHorizontalFlipTransform(double, AffineTransform) 
     * @see #getHorizontalFlipTransform(double, Shape, AffineTransform) 
     * @see #createHorizontallyMirroredArea 
     * @see #mirrorPathHorizontally 
     * @see #flipPathHorizontally 
     */
    protected AffineTransform getHorizontalMirrorTransform(double x,Shape shape,
            AffineTransform tx){
            // If the given shape is null
        if (shape == null)
            return getHorizontalFlipTransform(x,tx);
            // Get the bounds of the shape
        RectangularShape bounds = getBoundsOfShape(shape);
            // Return a horizontal flip transform that translates shapes by the 
            // given shape's maximum x-coordinate to put it back on the image, 
            // and then translate it again in a way that appears as if the shape 
            // was mirrored over the vertical line at the given x-coordinate
        return getHorizontalFlipTransform(bounds.getMaxX()+(x-bounds.getMaxX())*2,
                bounds,tx);
    }
    /**
     * This mirrors the given area horizontally over the vertical line at the 
     * given x-coordinate.
     * @param area The area to mirror.
     * @param x The x-coordinate of the vertical line to flip the mirror over.
     * @return The horizontally mirrored area.
     * @see #getHorizontalFlipTransform(double, AffineTransform) 
     * @see #getHorizontalFlipTransform(double, Shape, AffineTransform) 
     * @see #getHorizontalMirrorTransform 
     * @see #mirrorPathHorizontally 
     * @see #flipPathHorizontally 
     * @see Area#createTransformedArea 
     */
    protected Area createHorizontallyMirroredArea(Area area, double x){
            // Get an AffineTransform to flip the area horizontally and mirror 
            // it over the vertical line at the given x coordinate
        horizTx = getHorizontalMirrorTransform(x,area,horizTx);
            // Mirror the area horizontally
        return area.createTransformedArea(horizTx);
    }
    /**
     * This flips the given path horizontally over the vertical line at the 
     * given x-coordinate and adds the flipped path back to the given path.
     * @param path The Path2D object to flip horizontally and add to.
     * @param x The x-coordinate of the vertical line to flip the path over.
     * @return The given Path2D object, now with the horizontally flipped 
     * version of it added to it.
     * @see #getHorizontalFlipTransform(double, AffineTransform) 
     * @see #getHorizontalFlipTransform(double, Shape, AffineTransform) 
     * @see #getHorizontalMirrorTransform 
     * @see #createHorizontallyMirroredArea 
     * @see #flipPathHorizontally 
     * @see Path2D#createTransformedShape 
     */
    protected Path2D mirrorPathHorizontally(Path2D path, double x){
            // Get an AffineTransform to flip the path horizontally and mirror 
            // it over the vertical line at the given x coordinate
        horizTx = getHorizontalMirrorTransform(x,path,horizTx);
            // Mirror the path horizontally and add it to the original path
        path.append(path.createTransformedShape(horizTx), false);
        return path;
    }
    /**
     * This flips the given path horizontally over the vertical line at the 
     * given x-coordinate and stores the resulting path back into the given 
     * path, overwriting the original.
     * @param path The Path2D object to flip horizontally.
     * @param x The x-coordinate of the vertical line to flip the path over.
     * @return The given Path2D object, now flipped horizontally.
     * @see #getHorizontalFlipTransform(double, AffineTransform) 
     * @see #getHorizontalFlipTransform(double, Shape, AffineTransform) 
     * @see #getHorizontalMirrorTransform 
     * @see #createHorizontallyMirroredArea 
     * @see #mirrorPathHorizontally 
     * @see Path2D#createTransformedShape 
     */
    protected Path2D flipPathHorizontally(Path2D path, double x){
            // Get an AffineTransform to flip the path horizontally and mirror 
            // it over the vertical line at the given x coordinate
        horizTx = getHorizontalMirrorTransform(x,path,horizTx);
            // Mirror the path horizontally
        Shape flipped = path.createTransformedShape(horizTx);
            // Reset the path
        path.reset();
            // Add the mirrored shape to the original path
        path.append(flipped, false);
        return path;
    }
    /**
     * This returns an AffineTransform object that scales shapes by the given 
     * {@code scale} value and positions it in the center of the given shape 
     * object. 
     * @param shape The shape to be scaled.
     * @param scale The scale factor for the transform. 
     * @param tx An AffineTransform to store the results in, or null.
     * @return An AffineTransform used to scale and position shapes.
     */
    protected AffineTransform getCenteredScaleTransform(Shape shape, 
            double scale, AffineTransform tx){
            // Get the bounds of the given shape
        RectangularShape bounds = getBoundsOfShape(shape);
            // Get the inverse of the given scale.
        double scaleInv = 1/scale;
            // If the given AffineTransform is null
        if (tx == null)
                // Create a scale transform to scale stuff
            tx = AffineTransform.getScaleInstance(scale, scale);
        else    // Set the transform to a scale transform to scale stuff
            tx.setToScale(scale, scale);
            // Translate the transform to be at the origin
        tx.translate(-bounds.getMinX(), -bounds.getMinY());
            // Translate the transform to be at the center of the outer portion 
            // of the shape (accounting for the earler scale transform)
        tx.translate(bounds.getCenterX()*scaleInv,bounds.getCenterY()*scaleInv);
            // Translate the transform left by half the shape's width, and up by 
            // half the shape's height. When combined with the earlier scale 
            // transform and translations, this will result in the scaled shape 
            // being centered within the given shape
        tx.translate(-bounds.getWidth()/2, -bounds.getHeight()/2);
        return tx;
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
        pC = GeometryMath.getQuadBezierControlPoint(p0,p1,p2,pC);
            // Add the curve to the path
        path.quadTo(pC.getX(), pC.getY(), p2.getX(), p2.getY());
        return pC;
    }
    /**
     * This returns the RectangularShape that represents the bounds of the given 
     * shape. This method is mainly to help avoid the creation of unnecessary 
     * Rectangle2D objects when getting the bounds of a shape in order to use 
     * RectangularShape methods on a given shape that may or may not already be 
     * a RectangularShape. 
     * @param shape The shape to get the bounds of.
     * @return The bounds of the given shape, or null if the shape is null. If 
     * the given shape is a RectangularShape, then it is returned instead.
     */
    private static RectangularShape getBoundsOfShape(Shape shape){
            // If the given shape is a RectangularShape
        if (shape instanceof RectangularShape)
            return (RectangularShape) shape;
            // If the shape is null
        else if (shape == null)
            return null;
        return shape.getBounds2D();
    }
    /**
     * {@inheritDoc }
     * 
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
        if (isBackgroundPainted())
                // Paint the background
            paintBackground(g,0,0,width,height);
            // Paint Rambley
        paintRambley(g,0,0,width,height);
            // If the pixel grid effect is to be painted
        if (isPixelGridPainted())
                // Paint the pixel grid effect
            paintPixelGrid(g,0,0,width,height);
            // Dispose of the copy of the graphics context
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
     * @see #paintBackground 
     * @see #paintBackgroundDots 
     * @see #paintRambley 
     * @see #paintPixelGrid 
     * @see #paintPixelGrid 
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
     * This returns the gradient to use to paint the background gradient. The 
     * gradient goes from {@link BACKGROUND_GRADIENT_COLOR} at the top to {@link 
     * BACKGROUND_GRADIENT_COLOR_2} at the bottom throughout the area to fill.
     * @param x The x-coordinate of the top-left corner of the area to fill.
     * @param y The y-coordinate of the top-left corner of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     * @return The gradient to use to paint the background gradient.
     * @see BACKGROUND_GRADIENT_COLOR
     * @see BACKGROUND_GRADIENT_COLOR_2
     * @see #paintBackground 
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
     * This is used to calculate the offset for the background polka dots using 
     * the given size value.
     * @param size The value to use to get the offset.
     * @return The offset for the background polka dots.
     * @see getBackgroundDotOffsetX
     * @see getBackgroundDotOffsetY
     * @see getBackgroundDotSpacing
     */
    private double getBackgroundDotOffset(double size){
        return (size%getBackgroundDotSpacing())/2.0;
    }
    /**
     * This returns the x offset to use for the background polka dots. 
     * 
     * @implSpec The default implementation is equivalent to {@code (width % }
     * {@link getBackgroundDotSpacing() getBackgroundDotSpacing()}{@code )/2.0}.
     * 
     * @param width The width of the area to fill with the background.
     * @return The offset for the x-coordinate of the background polka dots.
     * @see #getBackgroundDotOffsetY 
     * @see #getBackgroundDotDiamond 
     * @see #getBackgroundDot 
     * @see #paintBackgroundDots
     * @see getBackgroundDotSpacing
     * @see setBackgroundDotSpacing
     */
    protected double getBackgroundDotOffsetX(double width){
        return getBackgroundDotOffset(width);
    }
    /**
     * This returns the y offset to use for the background polka dots. 
     * 
     * @implSpec The default implementation is equivalent to {@code (height % }
     * {@link getBackgroundDotSpacing() getBackgroundDotSpacing()}{@code )/2.0}.
     * 
     * @param height The height of the area to fill with the background.
     * @return The offset for the y-coordinate of the background polka dots.
     * @see #getBackgroundDotOffsetX 
     * @see #getBackgroundDotDiamond 
     * @see #getBackgroundDot 
     * @see #paintBackgroundDots 
     * @see getBackgroundDotSpacing
     * @see setBackgroundDotSpacing
     */
    protected double getBackgroundDotOffsetY(double height){
        return getBackgroundDotOffset(height);
    }
    /**
     * This creates and returns the shape to use to draw a diamond shaped 
     * background polka dot, using the given {@code bounds} to determine the 
     * size and position of the polka dot. 
     * @param bounds A rectangle outlining the bounds for the background polka 
     * dot to return.
     * @param path A Path2D object to store the results in, or null.
     * @return The diamond shape object to use to draw a background polka dot.
     * @see #getBackgroundDot 
     * @see #getCircularBackgroundDots
     * @see #setCircularBackgroundDots 
     * @see #paintBackgroundDots 
     */
    protected Path2D getBackgroundDotDiamond(RectangularShape bounds,Path2D path){
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
     * This creates and returns the shape to use to draw a background polka dot 
     * centered at the given x and y coordinates. The background polka dot will 
     * be the width and height set for the {@link #getBackgroundDotSize() 
     * background polka dot size}. If the background polka dots are {@link 
     * #getCircularBackgroundDots circular}, then the shape returned will be a 
     * circle. Otherwise, the shape returned will be a diamond.
     * @param x The x-coordinate for the center of the background polka dot.
     * @param y The y-coordinate for the center of the background polka dot.
     * @param path A Path2D object to store the results in, or null.
     * @param ellipse An Ellipse2D object to store the results in, or null.
     * @return The shape object to use to draw a background polka dot.
     * @see #getBackgroundDotDiamond 
     * @see #getCircularBackgroundDots
     * @see #setCircularBackgroundDots 
     * @see #getBackgroundDotSize 
     * @see #setBackgroundDotSize 
     * @see #paintBackgroundDots 
     */
    protected Shape getBackgroundDot(double x, double y, Path2D path, 
            Ellipse2D ellipse){
            // If the given Ellipse2D object is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // Set the frame of the ellipse from the center to be the size of 
            // a background polka dot.
        ellipse.setFrameFromCenter(x, y, x-getBackgroundDotSize()/2.0, 
                y-getBackgroundDotSize()/2.0);
            // If the background dots should be circular
        if (getCircularBackgroundDots())
            return ellipse;
        return getBackgroundDotDiamond(ellipse,path);
    }
    /**
     * This is used to render the background. The area is first filled with a 
     * solid {@link BACKGROUND_COLOR light blue} color. After that, the 
     * background polka dots are drawn using the {@link paintBackgroundDots 
     * paintBackgroundDots} method. Finally, the {@link #getBackgroundGradient 
     * background gradient} is drawn over everything in the area. This renders 
     * to a copy of the given graphics context, so as to protect the rest of the 
     * paint code from changes made to the graphics context while rendering the 
     * background.
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill.
     * @param y The y-coordinate of the top-left corner of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     * @see #paint 
     * @see #paintBackgroundDots 
     * @see BACKGROUND_COLOR
     * @see #BACKGROUND_DOT_COLOR
     * @see #BACKGROUND_GRADIENT_COLOR
     * @see #getBackgroundGradient 
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
            // Dispose of the copy of the graphics context
        g.dispose();
    }
    /**
     * This is used to render the background polka dots. The background polka 
     * dots will be drawn in a {@link BACKGROUND_DOT_COLOR dark blue} color. 
     * Each polka dot will be {@link getBackgroundDot getBackgroundDot()} in 
     * width and height, and will be spaced diagonally by {@link 
     * #getBackgroundDotSpacing getBackgroundDotSpacing}. The polka dots will be 
     * either circular or diamond-shaped, depending on whether {@link 
     * #getCircularBackgroundDots getCircularBackgroundDots} is set to {@code 
     * true} or not. Each polka dot is generated using the {@link 
     * #getBackgroundDot getBackgroundDot} method. The polka dots will be offset 
     * horizontally by {@link #getBackgroundDotOffsetX getBackgroundDotOffsetX} 
     * and vertically by {@link #getBackgroundDotOffsetY 
     * getBackgroundDotOffsetY}. This renders to a copy of the given graphics 
     * context, so as to protect the rest of the paint code from changes made to 
     * the graphics context while rendering the background polka dots.
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill.
     * @param y The y-coordinate of the top-left corner of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     * @see #paint 
     * @see #paintBackground 
     * @see BACKGROUND_DOT_COLOR
     * @see #getBackgroundDot 
     * @see #getBackgroundDotOffsetX 
     * @see #getBackgroundDotOffsetY 
     * @see #getBackgroundDotSize 
     * @see #getBackgroundDotSpacing 
     * @see #getCircularBackgroundDots 
     * @see #setCircularBackgroundDots 
     * @see #isBackgroundPainted 
     * @see #setBackgroundPainted 
     */
    protected void paintBackgroundDots(Graphics2D g, int x, int y, int w, int h){
            // Create a copy of the given graphics context over the given area
        g = (Graphics2D) g.create(x, y, w, h);
            // Set the color to the background polka dot color
        g.setColor(BACKGROUND_DOT_COLOR);
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
                g.fill(getBackgroundDot(xDot+x1,yDot,path,ellipse1));
            }
        }   // Dispose of the copy of the graphics context
        g.dispose();
    }
    
    
    
    /**
     * This is used to calculate the offset for the pixel grid effect using the 
     * given size value.
     * @param size The value to use to get the offset.
     * @return The offset for the pixel grid effect.
     * @see getPixelGridOffsetX
     * @see getPixelGridOffsetY
     * @see getPixelGridLineSpacing
     */
    private double getPixelGridOffset(double size){
        return ((size-1)%getPixelGridLineSpacing())/2.0;
    }
    /**
     * This returns the x offset to use for the horizontal lines of the pixel 
     * grid effect.
     * 
     * @implSpec The default implementation is equivalent to {@code ((width-1) 
     * %} {@link getPixelGridLineSpacing() 
     * getPixelGridLineSpacing()}{@code )/2.0}.
     * 
     * @param width The width of the area to fill with the pixel grid effect.
     * @return The offset for the x-coordinate of the pixel grid effect.
     * @see #getPixelGridOffsetY
     * @see #getPixelGrid 
     * @see #paintPixelGrid(Graphics2D, int, int, int, int, Shape) 
     * @see #paintPixelGrid(Graphics2D, int, int, int, int) 
     * @see getPixelGridLineSpacing
     * @see setPixelGridLineSpacing
     */
    protected double getPixelGridOffsetX(double width){
        return getPixelGridOffset(width);
    }
    /**
     * This returns the y offset to use for the vertical lines of the pixel grid 
     * effect. 
     * 
     * @implSpec The default implementation is equivalent to {@code ((height-1) 
     * %} {@link getPixelGridLineSpacing() 
     * getPixelGridLineSpacing()}{@code )/2.0}.
     * 
     * @param height The height of the area to fill with the pixel grid effect.
     * @return The offset for the y-coordinate of the pixel grid effect.
     * @see #getPixelGridOffsetY
     * @see #getPixelGrid 
     * @see #paintPixelGrid(Graphics2D, int, int, int, int, Shape) 
     * @see #paintPixelGrid(Graphics2D, int, int, int, int) 
     * @see getPixelGridLineSpacing
     * @see setPixelGridLineSpacing
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
     * vertical lines are spaced out by {@link getPixelGridLineSpacing 
     * getPixelGridLineSpacing}.
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
     * @see getPixelGridLineSpacing
     * @see setPixelGridLineSpacing
     * @see #paintPixelGrid(Graphics2D, int, int, int, int, Shape) 
     * @see #paintPixelGrid(Graphics2D, int, int, int, int) 
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
        for (double y1 = getPixelGridOffsetY(h); y1 <= h; 
                y1+=getPixelGridLineSpacing()){
            path.moveTo(x, y1+y);
            path.lineTo(x2, y1+y);
        }   // Go through and generate the horizontal lines, starting at the 
            // offset for the x-coordinate of the pixel grid and spacing them 
            // out by the pixel grid spacing
        for (double x1 = getPixelGridOffsetX(w); x1 <= w; 
                x1+=getPixelGridLineSpacing()){
            path.moveTo(x1+x, y);
            path.lineTo(x1+x, y2);
        }
        return path;
    }
    /**
     * This is used to render the pixel grid effect over the area. The pixel 
     * grid effect is drawn without antialiasing and in a {@link 
     * PIXEL_GRID_COLOR transparent black} color and will have a line thickness 
     * of {@link #getPixelGridLineThickness getPixelGridLineThickness}. The 
     * pixel grid effect is rendered as a grid of horizontal and vertical lines 
     * that cover the area, with the spacing between the lines being {@link 
     * getPixelGridLineSpacing getPixelGridLineSpacing}. The horizontal lines 
     * are offset by {@link #getPixelGridOffsetX getPixelGridOffsetX} and 
     * vertical lines are offset by {@link #getPixelGridOffsetY 
     * getPixelGridOffsetY}. The path used to draw the pixel grid effect is 
     * generated by the {@link #getPixelGrid getPixelGrid} method. If a non-null 
     * {@code mask} is provided, then the pixel grid will only be rendered 
     * within the given mask. This renders to a copy of the given graphics 
     * context, so as to protect the rest of the paint code from changes made to 
     * the graphics context while rendering the pixel grid effect.
     * 
     * @todo Test the functionality to mask the pixel grid effect using the 
     * given mask.
     * 
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param y The y-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param w The width of the area to fill with the pixel grid effect.
     * @param h The height of the area to fill with the pixel grid effect.
     * @param mask An optional mask for the pixel grid effect, or null.
     * @see #paint 
     * @see #paintRambley 
     * @see #paintPixelGrid(Graphics2D, int, int, int, int) 
     * @see PIXEL_GRID_COLOR
     * @see #getPixelGrid 
     * @see #getPixelGridLineSpacing 
     * @see #setPixelGridLineSpacing 
     * @see #getPixelGridLineThickness 
     * @see #setPixelGridLineThickness 
     * @see #getPixelGridOffsetX 
     * @see #getPixelGridOffsetY 
     */
    protected void paintPixelGrid(Graphics2D g,int x,int y,int w,int h,Shape mask){
            // Create a copy of the given graphics context over the given area
        g = (Graphics2D) g.create(x, y, w, h);
            // Set the color to the pixel grid color
        g.setColor(PIXEL_GRID_COLOR);
            // Set the stroke to use to draw the pixel grid to use the set line 
            // thickness
        g.setStroke(new BasicStroke(getPixelGridLineThickness()));
            // Turn off antialiasing for the pixel grid
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
            // If a mask has been provided for the pixel grid
        if (mask != null)
                // Clip the graphics context to only render within the given 
            g.clip(mask);   // mask
            // Generate the pixel grid
        pixelGrid = getPixelGrid(0,0,w,h,pixelGrid);
            // Render the pixel grid
        g.draw(pixelGrid);
            // Dispose of the copy of the graphics context
        g.dispose();
    }
    /**
     * This is used to render the pixel grid effect over the area. The pixel 
     * grid effect is drawn without antialiasing and in a {@link 
     * PIXEL_GRID_COLOR transparent black} color. The pixel grid effect is 
     * rendered as a grid of horizontal and vertical lines that cover the area, 
     * with the spacing between the lines being {@link getPixelGridLineSpacing 
     * getPixelGridLineSpacing}. The horizontal lines are offset by {@link 
     * #getPixelGridOffsetX getPixelGridOffsetX} and vertical lines are offset 
     * by {@link #getPixelGridOffsetY getPixelGridOffsetY}. The path used to 
     * draw the pixel grid effect is generated by the {@link #getPixelGrid 
     * getPixelGrid} methodand will have a line thickness 
     * of {@link #getPixelGridLineThickness getPixelGridLineThickness}. The 
     * pixel grid effect is rendered as a grid of horizontal and vertical lines 
     * that cover the area, with the spacing between the lines being {@link 
     * getPixelGridLineSpacing getPixelGridLineSpacing}. The horizontal lines 
     * are offset by {@link #getPixelGridOffsetX getPixelGridOffsetX} and 
     * vertical lines are offset by {@link #getPixelGridOffsetY 
     * getPixelGridOffsetY}. The path used to draw the pixel grid effect is 
     * generated by the {@link #getPixelGrid getPixelGrid} method. This renders 
     * to a copy of the given graphics context, so as to protect the rest of the 
     * paint code from changes made to the graphics context while rendering the 
     * pixel grid effect. <p>
     * 
     * This version of the method does not take in an optional mask for the 
     * pixel grid effect. This is equivalent to calling {@link 
     * paintPixelGrid(Graphics2D, int, int, int, int, Shape) paintPixelGrid(g, 
     * x, y, w, h, null)}.
     * 
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param y The y-coordinate of the top-left corner of the area to fill with 
     * the pixel grid effect.
     * @param w The width of the area to fill with the pixel grid effect.
     * @param h The height of the area to fill with the pixel grid effect.
     * @see #paint 
     * @see #paintRambley 
     * @see #paintPixelGrid(Graphics2D, int, int, int, int, Shape) 
     * @see PIXEL_GRID_COLOR
     * @see #getPixelGrid 
     * @see #getPixelGridLineSpacing 
     * @see #setPixelGridLineSpacing 
     * @see #getPixelGridLineThickness 
     * @see #setPixelGridLineThickness 
     * @see #getPixelGridOffsetX 
     * @see #getPixelGridOffsetY 
     */
    protected void paintPixelGrid(Graphics2D g, int x, int y, int w, int h){
        paintPixelGrid(g,x,y,w,h,null);
    }
    
    
    
    /**
     * This constructs a BasicStroke object to use when rendering Rambley. The 
     * BasicStroke will have the given line width, the ends of the lines will be 
     * {@link BasicStroke#CAP_ROUND rounded}, and points where paths meet will 
     * be {@link BasicStroke#JOIN_ROUND rounded}.
     * @param width The line width for the stroke.
     * @return A BasicStroke with the given line width.
     * @throws IllegalArgumentException If the given line width is negative.
     * @see BasicStroke#BasicStroke(float, int, int) 
     * @see BasicStroke#CAP_ROUND
     * @see BasicStroke#JOIN_ROUND
     * @see #getRambleyNormalStroke() 
     * @see #getRambleyDetailStroke() 
     * @see #getRambleyLineStroke() 
     */
    protected BasicStroke getRambleyStroke(float width){
        return new BasicStroke(width,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    }
    /**
     * This returns a BasicStroke object to use for rendering most of Rambley. 
     * This stroke is mainly used when filling in shapes. This stroke has a line 
     * width of 1.0, the ends of the lines will be {@link BasicStroke#CAP_ROUND 
     * rounded}, and points where paths meet will be {@link 
     * BasicStroke#JOIN_ROUND rounded}.
     * @return The normal stroke used for drawing Rambley.
     * @see #getRambleyStroke
     * @see getRambleyDetailStroke
     * @see #getRambleyLineStroke
     * @see #getRambleyOutlineStroke 
     * @see BasicStroke#CAP_ROUND
     * @see BasicStroke#JOIN_ROUND
     */
    protected BasicStroke getRambleyNormalStroke(){
            // If the normal stroke for Rambley has not been initialized yet
        if (normalStroke == null)
            normalStroke = getRambleyStroke(1.0f);
        return normalStroke;
    }
    /**
     * This returns a BasicStroke object to use for rendering the details and 
     * finer outlines for Rambley. This stroke has a line width of 2.0, the ends 
     * of the lines will be {@link BasicStroke#CAP_ROUND rounded}, and points 
     * where paths meet will be {@link BasicStroke#JOIN_ROUND rounded}.
     * @return The details stroke used for drawing Rambley.
     * @see #getRambleyStroke
     * @see getRambleyNormalStroke
     * @see #getRambleyLineStroke
     * @see #getRambleyOutlineStroke 
     * @see BasicStroke#CAP_ROUND
     * @see BasicStroke#JOIN_ROUND
     */
    protected BasicStroke getRambleyDetailStroke(){
            // If the detail stroke for Rambley has not been initialized yet
        if (detailStroke == null)
            detailStroke = getRambleyStroke(2.0f);
        return detailStroke;
    }
    /**
     * This returns a BasicStroke object to use for rendering most of the lines 
     * for Rambley. This stroke has a line width of 3.0, the ends of the lines 
     * will be {@link BasicStroke#CAP_ROUND rounded}, and points where paths 
     * meet will be {@link BasicStroke#JOIN_ROUND rounded}.
     * @return The line stroke used for drawing Rambley.
     * @see #getRambleyStroke
     * @see getRambleyNormalStroke
     * @see getRambleyDetailStroke
     * @see #getRambleyOutlineStroke 
     * @see BasicStroke#CAP_ROUND
     * @see BasicStroke#JOIN_ROUND
     */
    protected BasicStroke getRambleyLineStroke(){
            // If the line stroke for Rambley has not been initialized yet
        if (outlineStroke == null)
            outlineStroke = getRambleyStroke(3.0f);
        return outlineStroke;
    }
    /**
     * This returns the x-coordinate for the top center of Rambley's head 
     * without his ears.
     * @todo Figure out a more controllable method for determining Rambley's 
     * position in the image.
     * @return The offset for the x-coordinate of the top center of Rambley's 
     * head.
     * @see #getRambleyY 
     * @see #getRambleyWidth 
     * @see #getRambleyHeight 
     * @see #paintRambley 
     * @see #getRambleyEarlessHead
     */
    protected double getRambleyX(){
        return getRambleyWidth()/2.0;
    }
    /**
     * This returns the y-coordinate for the top center of Rambley's head 
     * without his ears.
     * @todo Figure out a more controllable method for determining Rambley's 
     * position in the image.
     * @return The offset for the y-coordinate of the top center of Rambley's 
     * head.
     * @see #getRambleyX
     * @see #getRambleyWidth 
     * @see #getRambleyHeight 
     * @see #paintRambley 
     * @see #getRambleyEarlessHead
     */
    protected double getRambleyY(){
            // If Rambley's scarf is painted
        if (isRambleyScarfPainted())
            return RAMBLEY_Y_OFFSET_2;
        return RAMBLEY_Y_OFFSET_1;
    }
    /**
     * This is the width at which Rambley is rendered at internally. Rambley is 
     * scaled up or down to fill the area provided to the {@link #paint paint} 
     * method of {@code RambleyPainter}.
     * @todo Figure out a more controllable method for the internal rendering 
     * size, instead of having it change depending on whether Rambley has his 
     * scarf or not.
     * @return The internal rendering width of Rambley.
     * @see #getRambleyX
     * @see #getRambleyY 
     * @see #getRambleyHeight 
     * @see #paintRambley 
     */
    protected double getRambleyWidth(){
            // If Rambley's scarf is painted
        if (isRambleyScarfPainted())
            return INTERNAL_RENDER_WIDTH_2;
        return INTERNAL_RENDER_WIDTH_1;
    }
    /**
     * This is the height at which Rambley is rendered at internally. Rambley is 
     * scaled up or down to fill the area provided to the {@link #paint paint} 
     * method of {@code RambleyPainter}.
     * @todo Figure out a more controllable method for the internal rendering 
     * size, instead of having it change depending on whether Rambley has his 
     * scarf or not.
     * @return The internal rendering height of Rambley.
     * @see #getRambleyX
     * @see #getRambleyY 
     * @see #getRambleyWidth 
     * @see #paintRambley 
     */
    protected double getRambleyHeight(){
            // If Rambley's scarf is painted
        if (isRambleyScarfPainted())
            return INTERNAL_RENDER_HEIGHT_2;
        return INTERNAL_RENDER_HEIGHT_1;
    }
    /**
     * This creates and returns an Area that forms the base shape of Rambley's 
     * head without his ears.
     * @param x The x-coordinate of the top center of Rambley's head without his 
     * ears.
     * @param y The y-coordinate of the top center of Rambley's head without his 
     * ears.
     * @param rect A Rectangle2D object to use to store the mask for the lower 
     * half of the cheeks, or null.
     * @param path A Path2D object to use to store the mask for the cheeks, or 
     * null.
     * @param ellipse1 An Ellipse2D object to use to store the top of Rambley's 
     * head, or null.
     * @param ellipse2 An Ellipse2D object to use to store Rambley's cheeks, or 
     * null.
     * @return The area of Rambley's head without his ears.
     * @see #paintRambley 
     * @see #getRambleyX 
     * @see #getRambleyY
     * @see RAMBLEY_CHEEK_TRIANGLE_HEIGHT
     * @see #getRambleyEar 
     */
    private Area getRambleyEarlessHead(double x, double y, Rectangle2D rect, 
            Path2D path, Ellipse2D ellipse1, Ellipse2D ellipse2){
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
            // Set the frame of the rectangle to be centered at the given 
            // x-coordinate, and 130 pixels below the given y-coordinate. The 
            // top-left corner of the rectangle will be located 100 pixels to 
            // the left of the given x-coordinate and 84 pixels below the given 
            // y-coordinate The rectangle will be 200 x 92.
            // This will form the lower half of the mask for the cheeks
        rect.setFrameFromCenter(x, y+130, x+100, y+84);
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
            // horizontally centered ellipse with the given y-coordinate, and 
            // that is 152 x 176. This will form the top of Rambley's head
        ellipse1.setFrameFromCenter(rect.getCenterX(), y+88, 
                rect.getMinX()+24, y);
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
            // Get the amount by which the head shape will need to be shifted 
            // in order to center it in the x axis
        double xOff = rect.getCenterX() - headShape.getBounds2D().getCenterX();
            // If the generic AffineTransform object has not been initialized 
        if (afTx == null)   // yet
                // Get a translation transform to center the head shape
            afTx = AffineTransform.getTranslateInstance(xOff, 0);
        else    // Set the transform to center the head shape
            afTx.setToTranslation(xOff, 0);
            // Transform the head shape in order to center it
        headShape.transform(afTx);
        return headShape;
    }
    /**
     * 
     * {@code y = ln(x)/10ln(2) + 2.3}
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
     * {@code x = 2^(10y-23)}
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
     * {@code y = -(ln(1.5-x)/ln(2) - 5.2) / 8}
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
     * {@code y = -(ln(1.5-x)/ln(2) - 5.2) / 8}
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
     * {@code x = -2^(-8y+5.2) + 1.5}
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
     * {@code y = (0.01/(x-1.5)) + 2.4}
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
     * {@code x = (0.01/(y-2.4)) + 1.5}
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
     * This calculatess and returns the point at which the upper curve and tip 
     * curve used to form Rambley's right ear intersect.
     * @param x The x-coordinate for the top-left corner of the ear.
     * @param y The y-coordinate for the top-left corner of the ear.
     * @param point A Point2D object to store the results in, or null.
     * @return The point at which the upper curve and tip curve of Rambley's 
     * right ear intersect.
     * @see #getRambleyEar 
     * @see GeometryMath#getLineIntersection(double, double, 
     * DoubleUnaryOperator, DoubleUnaryOperator, Point2D) 
     * @see getRambleyUpperEarEquation 
     * @see getRambleyEarTipEquation 
     * @see #getRambleyUpperEarX 
     * @see #getRambleyUpperEarY 
     * @see #getRambleyEarTipX 
     * @see #getRambleyEarTipY 
     */
    protected Point2D getRambleyEarUpperTip(double x, double y, Point2D point){
            // Get the point of intersection. They intersect somewhere between 
            // x-coordinates 9 and 10.5
        point = GeometryMath.getLineIntersection(9,10.5,
                getRambleyUpperEarEquation(),getRambleyEarTipEquation(),point);
            // Offset the point of intersection by the given x and y coordinates
        point.setLocation(x+point.getX(), y+point.getY());
        return point;
    }
    /**
     * This calculatess and returns the point at which the lower curve and tip 
     * curve used to form Rambley's right ear intersect.
     * @param x The x-coordinate for the top-left corner of the ear.
     * @param y The y-coordinate for the top-left corner of the ear.
     * @param point A Point2D object to store the results in, or null.
     * @return The point at which the lower curve and tip curve of Rambley's 
     * right ear intersect.
     * @see #getRambleyEar 
     * @see GeometryMath#getLineIntersection(double, double, 
     * DoubleUnaryOperator, DoubleUnaryOperator, Point2D) 
     * @see getRambleyEarTipEquation 
     * @see getRambleyLowerEarEquation 
     * @see #getRambleyLowerEarX 
     * @see #getRambleyLowerEarY 
     * @see #getRambleyEarTipX 
     * @see #getRambleyEarTipY 
     */
    protected Point2D getRambleyEarLowerTip(double x, double y, Point2D point){
            // Get the point of intersection They intersect somewhere between 
            // the end of the tip curve and an x-coordinate of 1
        point = GeometryMath.getLineIntersection(
                getRambleyEarTipX(RAMBLEY_EAR_HEIGHT),1,
                getRambleyEarTipEquation(),getRambleyLowerEarEquation(),point);
            // Offset the point of intersection by the given x and y coordinates
        point.setLocation(x+point.getX(), y+point.getY());
        return point;
    }
    /**
     * This creates and returns an Area that forms the shape of Rambley's right 
     * ear. This method uses all 8 scratch Point2D objects.
     * @param x The x-coordinate for the top-left corner of the ear.
     * @param y The y-coordinate for the top-left corner of the ear.
     * @param path A Path2D object to use to construct the ear, or null.
     * @return The area of Rambley's right ear.
     * @see #paintRambley 
     * @see #getRambleyEarlessHead
     * @see #getRambleyInnerEar 
     * @see getRambleyUpperEarEquation 
     * @see getRambleyEarTipEquation 
     * @see getRambleyLowerEarEquation 
     * @see #getRambleyUpperEarX 
     * @see #getRambleyUpperEarY 
     * @see #getRambleyEarTipX 
     * @see #getRambleyEarTipY 
     * @see #getRambleyLowerEarX 
     * @see #getRambleyLowerEarY 
     * @see #getRambleyEarUpperTip
     * @see #getRambleyEarLowerTip
     * @see #RAMBLEY_EAR_HEIGHT
     * @see #RAMBLEY_EAR_TIP_ROUNDING
     */
    private Area getRambleyEar(double x, double y, Path2D path){
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
     * based off the Area of the given ear and the {@link 
     * #RAMBLEY_INNER_EAR_SCALE scale factor for the inner ear}. The inner ear 
     * will be centered within the given Area for the outer ear. The given Area 
     * for the head is used to subtract the head shape from the inner ear.
     * @param ear The Area representing the outer ear to derive the inner ear 
     * from.
     * @param head The Area for the shape of Rambley's head to use to subtract 
     * from the inner ear, or null.
     * @return The Area for the inner portion of Rambley's ear.
     * @see #paintRambley 
     * @see #getRambleyEarlessHead
     * @see #getRambleyEar 
     * @see #getCenteredScaleTransform 
     * @see RAMBLEY_INNER_EAR_SCALE
     */
    protected Area getRambleyInnerEar(Area ear, Area head){
            // Get the AffineTransform to scale the outer ear to get the inner 
            // ear and center the inner ear in the outer ear
        inEarTx = getCenteredScaleTransform(ear,RAMBLEY_INNER_EAR_SCALE,inEarTx);
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
     * @see #paintRambley 
     * @see #getRambleyEarlessHead
     * @see #getRambleyEar 
     */
    private Area getRambleyMaskFaceMarkings(RectangularShape headBounds, 
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
        Area temp = createHorizontallyMirroredArea(mask,ellipse1.getCenterX());
            // Get the bounds of the horizontally flipped version of the second 
            // ellipse, so that we can get some location data from it
        RectangularShape tempBounds = getBoundsOfShape(temp);
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
     * @param headBounds The bounds of Rambley's head, or null if {@code head} 
     * is not null.
     * @param head The Area for the shape of Rambley's head to use to ensure the 
     * snout area lies within the head shape, or null.
     * @param ellipse An Ellipse2D object to use to calculate the snout area, 
     * or null.
     * @param path A Path2D object to use to form the mask for the snout, or 
     * null.
     * @param point1 A Point2D object to store where the tapering curve should 
     * start on Rambley's snout, or null.
     * @param point2 A Point2D object to use to calculate some points used for 
     * Rambley's snout, or null.
     * @param quadCurve A QuadCurve2D object used to calculate the tampering 
     * curve for Rambley's snout, or null.
     * @return The area around Rambley's nose and mouth.
     * @see #paintRambley 
     * @see #paintRambleySnout 
     * @see #getRambleyEarlessHead
     * @see #getRambleyEar 
     */
    private Area getRambleySnout(RectangularShape headBounds, Area head, 
            Ellipse2D ellipse, Path2D path, Point2D point1, Point2D point2, 
            QuadCurve2D quadCurve){
            // If the ellipse is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // If the first of the two given Point2D objects is null
        if (point1 == null)
            point1 = new Point2D.Double();
            // If the second of the two given Point2D objects is null
        if (point2 == null)
            point2 = new Point2D.Double();
            // If the second given QuadCurve2D object is null
        if (quadCurve == null)
            quadCurve = new QuadCurve2D.Double();
            // If the bounds for the head are null but the area for the head is 
        if (headBounds == null && head != null)     // not
                // Get the bounds for the head
            headBounds = head.getBounds2D();
            // If the head bounds are null
        if (headBounds == null)
            throw new NullPointerException();
            // Set the ellipse's frame from the center so that it is 
            // horizontally centered in the head, is at the bottom of the head, 
            // and is around 72 x 56. This forms the snout area.
        ellipse.setFrameFromCenter(
                headBounds.getCenterX(), headBounds.getMaxY()-28, 
                headBounds.getMinX()+63, headBounds.getMaxY());
            // Get the points where the ellipse intersects the horizontal line 
            // that is two pixels above its center
        GeometryMath.getEllipseX(ellipse,ellipse.getCenterY()-3,point1,point2);
            // Set the curve to start at the left point of the intersection and 
            // end at the top center of the ellipse. Use a control point that is 
            // 1/10th of the way to the left there, and 5/6ths of the way up.
        quadCurve.setCurve(point1.getX(), point1.getY(), 
                (point1.getX()*9+ellipse.getCenterX())/10, 
                (point1.getY()+ellipse.getMinY()*5)/6, 
                ellipse.getCenterX(), ellipse.getMinY());
            // Start the path at the left-most point of the ellipse at the 
            // y-coordinate of where the curve starts
        path.moveTo(ellipse.getMinX(), quadCurve.getY1());
            // Add the tampering curve to the path
        path.append(quadCurve, true);
            // Draw a vertical line down to the bottom of the ellipse
        path.lineTo(ellipse.getCenterX(), ellipse.getMaxY());
            // Draw a horizontal line to the left side of the ellipse
        path.lineTo(ellipse.getMinX(), ellipse.getMaxY());
            // Close the path to complete the right side of the mask
        path.closePath();
            // Flip the path (which holds the right side of the mask) 
            // horizontally to form the left side of the mask and then add the 
            // left side of the mask to the path.
        path = mirrorPathHorizontally(path,ellipse.getCenterX());
            // Create an area for the snout area
        Area mouthArea = new Area(ellipse);
            // Mask the snout area using the mask
        mouthArea.intersect(new Area(path));
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
     * @param headBounds The bounds of Rambley's head.
     * @param ellipse An Ellipse2D object to use to calculate the eyebrow, or 
     * null.
     * @return The area that forms Rambley's right eyebrow.
     * @see #paintRambley 
     * @see #paintRambleyEye(Graphics2D, Shape, Ellipse2D, Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Ellipse2D, 
     * Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Rectangle2D, 
     * double, double, Ellipse2D, Ellipse2D) 
     * @see #getRambleyEarlessHead
     * @see #getRambleyEar 
     * @see #getRambleyEyeMarkings
     * @see #getRambleyEyeShape
     */
    private Area getRambleyEyebrow(RectangularShape headBounds, 
            Ellipse2D ellipse){
            // If the ellipse is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // Set the ellipse's frame from the center so that it is around 49 
            // pixels to the left of center, 20 pixels lower than the top of the 
            // head (without ears), and is 40 x 40. This forms the eyebrow area, 
            // and is intended to be covered up by the markings around the eyes 
            // and the eyes themselves.
        ellipse.setFrameFromCenter(
                headBounds.getCenterX()-29, headBounds.getMaxY()-94, 
                headBounds.getCenterX()-9, headBounds.getMaxY()-74);
        return new Area(ellipse);
    }
    /**
     * This creates and returns an Area that forms the shape of the markings 
     * around Rambley's right eye.
     * @param headBounds The bounds of Rambley's head.
     * @param ellipse An Ellipse2D object to use to calculate the top-right 
     * portion of the markings, or null.
     * @param path A Path2D object to use to form the portion of the markings 
     * that will not be formed by {@code ellipse}, or null.
     * @param point1 A Point2D object to use to calculate the points where the 
     * eyebrow ellipse intersects with the top-right portion of the eye 
     * markings, or null.
     * @param point2 A disposable Point2D object to use to calculate the points 
     * where the eyebrow ellipse intersects with the top-right portion of the 
     * eye markings, or null.
     * @param quadCurve A QuadCurve2D object to store the bottom-left quadratic 
     * bezier curve, or null.
     * @return The area that forms the markings around Rambley's right eye.
     * @see #paintRambley 
     * @see #paintRambleyEye(Graphics2D, Shape, Ellipse2D, Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Ellipse2D, 
     * Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Rectangle2D, 
     * double, double, Ellipse2D, Ellipse2D) 
     * @see #getRambleyEarlessHead
     * @see #getRambleyEar 
     * @see #getRambleyEyebrow 
     * @see #getRambleyEyeShape
     */
    private Area getRambleyEyeMarkings(RectangularShape headBounds, 
            Ellipse2D ellipse, Path2D path, Point2D point1, Point2D point2, 
            QuadCurve2D quadCurve){
            // If the given Ellipse2D object is null
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // If the first of the two given Point2D objects is null
        if (point1 == null)
            point1 = new Point2D.Double();
            // If the second of the two given Point2D objects is null
        if (point2 == null)
            point2 = new Point2D.Double();
            // If the given QuadCurve2D object is null
        if (quadCurve == null)
            quadCurve = new QuadCurve2D.Double();
            // Set the ellipse's frame from the center so that it is around 30.5 
            // pixels to the left of center, 24 pixels lower than the top of the 
            // head (without ears), and is 43 x 48. This forms the top-right 
            // part of the markings.
        ellipse.setFrameFromCenter(
                headBounds.getCenterX()-30.5, headBounds.getMaxY()-86, 
                headBounds.getCenterX()-9, headBounds.getMaxY()-62);
            // Get the points on the ellipse that are 4 pixels to the right of 
            // the left side of the ellipse. The top-most one (point1) will be 
            // used to transition between the ellipse and the path.
        GeometryMath.getEllipseY(ellipse,ellipse.getMinX()+4,point1,point2);
            // Start the path at the left-most point of intersection
        path.moveTo(point1.getX(), point1.getY());
            // Set the bottom-left quadratic bezier curve. 
            // Start at where the path should stop going to the left.
            // Use a control point that is 2 pixels to the left of the ellipse, 
            // and that is 14 pixels below the ellipse.
            // End the curve in the horizontal center of the ellipse, and 14 
            // pixels below the ellipse
        quadCurve.setCurve(ellipse.getMinX()-8, ellipse.getMaxY()-10, 
                ellipse.getMinX()+2, ellipse.getMaxY()+14, 
                ellipse.getCenterX(), ellipse.getMaxY()+14);
            // Add a bezier curve from point1 on the ellipse to the start of the 
            // bottom-left curve. In order to curve correctly, use a control 
            // point that is 2/3 left of the way to the start of the curve, and 
            // 1/3 of the way down to the start of the curve and shifted down by 
            // 5 pixels
        path.quadTo((point1.getX()+(quadCurve.getX1()*2))/3, 
                ((quadCurve.getY1()+(point1.getY()*2))/3)+5, 
                quadCurve.getX1(), quadCurve.getY1());
            // Add the bottom-left quadratic bezier curve to the path
        path.append(quadCurve, true);
            // Add a bezier control point from the end of the bottom-left curve 
            // to 4 pixels before the right edge of the ellipse and 5 pixels up. 
            // Use the control point located at the x-coordinate between the end 
            // of the bottom-left curve and the end of this curve and at the 
            // y-coordinate of the end of the bottom-left curve.
        path.quadTo((quadCurve.getX2()+ellipse.getMaxX()-4)/2,quadCurve.getY2(), 
                ellipse.getMaxX()-4, quadCurve.getY2()-5);
            // Add a bezier control point from the end of the previous curve to 
            // the right-center of the ellipse. Use the ellipse's right-most 
            // x-coordinate and the y-coordinate that is 1/3 of the way to the 
            // center of the ellipse.
        path.quadTo(ellipse.getMaxX(), 
                ((quadCurve.getY2()-5)*2+ellipse.getCenterY())/3, 
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
     * eye. This uses the ellipse and quadratic curve given to {@link 
     * #getRambleyEyeMarkings getRambleyEyeMarkings} method 
     * ({@code eyeMarkEllipse} and {@code eyeMarkCurve}) to position and control 
     * the shape of the eye.
     * @param headBounds The bounds of Rambley's head to position and control 
     * the form of the shape.
     * @param eyeBrow An Ellipse2D object with the ellipse used to form 
     * Rambley's right eyebrow.
     * @param eyeMarkEllipse An Ellipse2D object with the ellipse used to form 
     * the markings around Rambley's right eye.
     * @param eyeMarkCurve A QuadCurve2D object with the bottom-left quadratic 
     * bezier curve of the eye markings (cannot be null).
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
     * @see #paintRambley 
     * @see #paintRambleyEye(Graphics2D, Shape, Ellipse2D, Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Ellipse2D, 
     * Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Rectangle2D, 
     * double, double, Ellipse2D, Ellipse2D) 
     * @see #getRambleyEarlessHead
     * @see #getRambleyEar 
     * @see #getRambleyEyebrow 
     * @see #getRambleyEyeMarkings
     */
    private Area getRambleyEyeShape(RectangularShape headBounds, 
            Ellipse2D eyeMarkEllipse,QuadCurve2D eyeMarkCurve,Ellipse2D ellipse, 
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
            // side lining up with the eye markings ellipse, 12 pixels lower 
            // than the eye marks ellipse, and is 32 x 47. This forms the 
            // top-right part of the eye.
        ellipse.setFrameFromCenter(
                eyeMarkEllipse.getCenterX()+5.5,eyeMarkEllipse.getCenterY()+11.5, 
                eyeMarkEllipse.getMaxX(), eyeMarkEllipse.getMinY()+12);
            // Set the frame of the rectangular shape from its center, with its 
            // right side lining up with the ellipse, 14 pixels lower than the 
            // eye marks ellipse, and is 39 x 48. This is used as a reference 
            // for the curves that form the eye.
        rect.setFrameFromCenter(
                eyeMarkEllipse.getCenterX()+2, eyeMarkEllipse.getCenterY()+14, 
                ellipse.getMaxX(), eyeMarkEllipse.getMinY()+14);
            // Start the path at the top-center of the ellipse
        path.moveTo(ellipse.getCenterX(), ellipse.getMinY());
            // Draw a horizontal line that stops halfway between the center of 
            // the ellipse and the center of the rectangular shape
        path.lineTo((ellipse.getCenterX()+rect.getCenterX())/2, ellipse.getMinY());
            // This will get the point on the bottom-left bezier curve for the 
            // eye markings. This should be around 4 pixels to the left of the 
            // rectangular shape.
        point1 = GeometryMath.getQuadBezierPointForX(eyeMarkCurve,
                rect.getMinX()-4,point1);
            // Add a quadratic bezier curve from the previous point to point1, 
            // using a control point that is at the point formed by the 
            // left-most x-coordinate of the rectangular shape and the top 
            // y-coordinate of the ellipse.
        path.quadTo(rect.getMinX(),ellipse.getMinY(),point1.getX(),point1.getY());
            // Add a quadratic bezier curve from point1 to the bottom-center of 
            // the rectangular shape, using a control point that is 2 pixels to 
            // the right of the eye marks ellipse, and that is halfway between 
            // the bottoms of the rectangular shape and the eye ellipse.
        path.quadTo(eyeMarkEllipse.getMinX()+2, (ellipse.getMaxY()+rect.getMaxY())/2, 
                rect.getCenterX(), rect.getMaxY());
            // Get the x-coordinate to use for the control point of the next 
            // quadratic bezier curve. This is 14 pixels left of the center of 
        double x = headBounds.getCenterX()-14;  // the face
            // Add a quadratic bezier curve from the previous point to 2/3rds of 
            // the way between the control point of this curve and the 
            // right-most point of the ellipse, and at the bottom of the 
            // ellipse. The control point is 14 pixels to the left of the center 
            // of the face and is at the bottom of the rectangular shape.
        path.quadTo(x,rect.getMaxY(),(x+(ellipse.getMaxX()*2))/3,ellipse.getMaxY());
            // Calculate the points where the ellipse intersects the horizontal 
            // line one pixel above the center of the ellipse
        GeometryMath.getEllipseX(ellipse,ellipse.getCenterY()-1,point3,point2);
            // Add a quadratic bezier curve from the previous point to point2 
            // (the right-most intersection point), and using a control point 
            // that is 1 pixel to the right of the rectangular shape, and is 
            // 2/3rds of the way from the center of the rectangular shape to the  
            // bottom of the rectangular shape.
        path.quadTo(ellipse.getMaxX()+1,(rect.getMaxY()+(rect.getCenterY()*2))/3, 
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
     * @param x The x-coordinate of the center of Rambley's eye.
     * @param y The y-coordinate of the center of Rambley's eye.
     * @param iris An Ellipse2D object to store Rambley's iris in.
     * @param pupil An Ellipse2D object to store Rambley's pupil in.
     * @see #paintRambleyEye(Graphics2D, Shape, Ellipse2D, Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Ellipse2D, 
     * Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Rectangle2D, 
     * double, double, Ellipse2D, Ellipse2D) 
     * @see #RAMBLEY_IRIS_SIZE
     * @see #RAMBLEY_PUPIL_SIZE
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
     * This is used to render Rambley's eye using the given eye white, iris, and 
     * pupil.
     * @param g The graphics context to render to.
     * @param eyeWhite The shape to use to paint the eye white for Rambley's 
     * eye.
     * @param iris The Ellipse2D object representing Rambley's iris.
     * @param pupil The Ellipse2D object representing Rambley's pupil.
     * @see #paintRambley 
     * @see #paintRambleyEyes
     * @see #setRambleyEyeLocation 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Ellipse2D, 
     * Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Rectangle2D, 
     * double, double, Ellipse2D, Ellipse2D) 
     * @see #getRambleyEyeShape
     * @see #RAMBLEY_EYE_WHITE_COLOR
     * @see #RAMBLEY_IRIS_COLOR
     * @see #EVIL_RAMBLEY_IRIS_COLOR
     * @see #RAMBLEY_PUPIL_COLOR
     * @see #RAMBLEY_IRIS_LINE_COLOR
     * @see #EVIL_RAMBLEY_IRIS_LINE_COLOR
     * @see #RAMBLEY_EYE_LINE_COLOR
     * @see #RAMBLEY_IRIS_SIZE
     * @see #RAMBLEY_PUPIL_SIZE
     * @see #getRambleyDetailStroke 
     * @see #getRambleyLineStroke 
     * @see #isRambleyEvil 
     * @see #setRambleyEvil 
     */
    protected void paintRambleyEye(Graphics2D g,Shape eyeWhite,Ellipse2D iris,
            Ellipse2D pupil){
            // Create a copy of the given graphics context to render the eye
        Graphics2D gEye = (Graphics2D) g.create();
            // Clip the copy to the shape of the eye white
        gEye.clip(eyeWhite);
            // Fill the eye white
        gEye.setColor(RAMBLEY_EYE_WHITE_COLOR);
        gEye.fill(eyeWhite);
            // Set the color for Rambley's iris. If Rambley is evil, use the 
            // evil Rambley iris color. Otherwise, use the normal Rambley iris 
            // color
        gEye.setColor((isRambleyEvil())?EVIL_RAMBLEY_IRIS_COLOR:RAMBLEY_IRIS_COLOR);
            // Fill Rambley's iris
        gEye.fill(iris);
            // Fill Rambley's pupil
        gEye.setColor(RAMBLEY_PUPIL_COLOR);
        gEye.fill(pupil);
            // Set the color for the outline of Rambley's iris. If Rambley is 
            // evil, use the evil Rambley iris outline color. Otherwise, use the 
            // normal Rambley iris outline color
        gEye.setColor((isRambleyEvil())?EVIL_RAMBLEY_IRIS_LINE_COLOR:
                RAMBLEY_IRIS_LINE_COLOR);
            // Set the stroke to use to the detail stroke
        gEye.setStroke(getRambleyDetailStroke());
            // Draw the outline of the iris
        gEye.draw(iris);
            // Draw the outline of the pupil
        gEye.draw(pupil);
            // Dispose of the eye graphics context
        gEye.dispose();
            // Create another copy of the given graphics context
        g = (Graphics2D) g.create();
            // Set the stroke to use to the outline stroke
        g.setStroke(getRambleyLineStroke());
            // Draw the outline for Rambley's eye
        g.setColor(RAMBLEY_EYE_LINE_COLOR);
        g.draw(eyeWhite);
            // Dispose of the copy of the graphics context
        g.dispose();
    }
    /**
     * This sets the location for the {@code iris} and {@code pupil} ellipses 
     * for Rambley's eyes to the given center x and y coordinates, and then 
     * renders Rambley's eye Rambley's eye using the given eye white, iris, and 
     * pupil. This is equivalent to calling {@link #setRambleyEyeLocation 
     * setRambleyEyeLocation} before calling {@link #paintRambleyEye(Graphics2D, 
     * Shape, Ellipse2D, Ellipse2D) paintRambleyEye}.
     * @param g The graphics context to render to.
     * @param eyeWhite The shape to use to paint the eye white for Rambley's 
     * eye.
     * @param x The x-coordinate of the center of Rambley's iris and pupil.
     * @param y The y-coordinate of the center of Rambley's iris and pupil.
     * @param iris The Ellipse2D object representing Rambley's iris, or null.
     * @param pupil The Ellipse2D object representing Rambley's pupil, or null.
     * @see #paintRambley 
     * @see #paintRambleyEyes
     * @see #setRambleyEyeLocation 
     * @see #paintRambleyEye(Graphics2D, Shape, Ellipse2D, Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Rectangle2D, 
     * double, double, Ellipse2D, Ellipse2D) 
     * @see #getRambleyEyeShape
     * @see #RAMBLEY_EYE_WHITE_COLOR
     * @see #RAMBLEY_IRIS_COLOR
     * @see #EVIL_RAMBLEY_IRIS_COLOR
     * @see #RAMBLEY_PUPIL_COLOR
     * @see #RAMBLEY_IRIS_LINE_COLOR
     * @see #EVIL_RAMBLEY_IRIS_LINE_COLOR
     * @see #RAMBLEY_EYE_LINE_COLOR
     * @see #RAMBLEY_IRIS_SIZE
     * @see #RAMBLEY_PUPIL_SIZE
     * @see #getRambleyDetailStroke 
     * @see #getRambleyLineStroke 
     * @see #isRambleyEvil 
     * @see #setRambleyEvil 
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
     * @see #paintRambley 
     * @see #paintRambleyEyes
     * @see #setRambleyEyeLocation 
     * @see #paintRambleyEye(Graphics2D, Shape, Ellipse2D, Ellipse2D) 
     * @see #paintRambleyEye(Graphics2D, Shape, double, double, Ellipse2D, 
     * Ellipse2D) 
     * @see #getRambleyEyeShape
     * @see #RAMBLEY_EYE_WHITE_COLOR
     * @see #RAMBLEY_IRIS_COLOR
     * @see #EVIL_RAMBLEY_IRIS_COLOR
     * @see #RAMBLEY_PUPIL_COLOR
     * @see #RAMBLEY_IRIS_LINE_COLOR
     * @see #EVIL_RAMBLEY_IRIS_LINE_COLOR
     * @see #RAMBLEY_EYE_LINE_COLOR
     * @see #RAMBLEY_IRIS_SIZE
     * @see #RAMBLEY_PUPIL_SIZE
     * @see #getRambleyDetailStroke 
     * @see #getRambleyLineStroke 
     * @see #isRambleyEvil 
     * @see #setRambleyEvil 
     */
    private void paintRambleyEye(Graphics2D g,Shape eyeWhite,double x,
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
     * This uses the ellipse given to the {@link #getRambleySnout 
     * getRambleySnout} method ({@code snout}) to position and control the form 
     * of the shape.
     * @param snout An Ellipse2D object with the ellipse used to form Rambley's 
     * snout (cannot be null).
     * @param rect A Rectangle2D object to store the bounds of the nose, or 
     * null.
     * @param ellipse An Ellipse2D object to store the ellipse that forms the 
     * top of the nose, or null.
     * @param path A Path2D object to store the bottom of the nose, or null.
     * @return The area that forms Rambley's nose.
     * @see #paintRambley 
     * @see #getRambleySnout 
     * @see #getRambleyMouthCurve
     * @see #getRambleyOpenMouth
     */
    private Area getRambleyNose(Ellipse2D snout, Rectangle2D rect, 
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
     * ellipse given to the {@link #getRambleySnout getRambleySnout} method 
     * ({@code snout}) to position the outer edges of the mouth.
     * 
     * @todo Add references to other related methods.
     * 
     * @param snout An Ellipse2D object with the ellipse used to form Rambley's 
     * snout (cannot be null).
     * @param point The Point2D object with the center point of the mouth curve 
     * (cannot be null). This should be the bottom center point of the nose.
     * @param quadCurve1 A QuadCurve2D object to store the first half of the 
     * curve on the right side of the mouth, or null. This will be the curve 
     * from {@code point} to the lowest point on the right side of the curve of 
     * the mouth.
     * @param quadCurve2 A QuadCurve2D object to store the second half of the 
     * curve on the right side of the mouth, or null. This will be the curve 
     * from the lowest point on the right side of the curve of the mouth to the 
     * end point for the the right side of the curve of the mouth.
     * @param point1 A Point2D object to use to calculate points on the right 
     * side of the curve of the mouth, or null.
     * @param point2 A Point2D object to use to calculate points on the right 
     * side of the curve of the mouth, or null.
     * @param path A Path2D object to store the results in, or null.
     * @return A Path2D object with the mouth curve.
     * @see #paintRambley 
     * @see #paintRambleySnout 
     * @see #getRambleySnout 
     * @see #getRambleyNose
     * @see #getRambleyOpenMouth
     * @see #getRambleyTongue
     * @see #getRambleyFangCurve 
     * @see #getRambleyFangs
     * @see #getRambleyClosedTeeth
     */
    private Path2D getRambleyMouthCurve(Ellipse2D snout, Point2D point, 
            QuadCurve2D quadCurve1, QuadCurve2D quadCurve2, Point2D point1, 
            Point2D point2, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // If the first given QuadCurve2D object is null
        if (quadCurve1 == null)
            quadCurve1 = new QuadCurve2D.Double();
            // If the second given QuadCurve2D object is null
        if (quadCurve2 == null)
            quadCurve2 = new QuadCurve2D.Double();
            // If the first of the two given scratch Point2D objects is null
        if (point1 == null)
            point1 = new Point2D.Double();
            // If the second of the two given scratch Point2D objects is null
        if (point2 == null)
            point2 = new Point2D.Double();
        
            // Form the right curve of Rambley's mouth
            
            // Calculate the points where the snout intersects the horizontal 
            // line 2.5 pixels above the first point.
        GeometryMath.getEllipseX(snout,point.getY()-2.5,point2,point1);
            // Shift the left-most point 7.5 pixels to the right. This will be 
            // the end point of the right side of the mouth curve.
        point2.setLocation(point2.getX()+7.5, point2.getY());
            // This is the amount to add to the y-coordinate for the mid-point 
            // to get the lowest point on the curve. When Rambley's mouth is 
            // closed, the point is 9 pixels below the first point
        double yOff = 9;
            // If Rambley's mouth is open
        if (isRambleyMouthOpen())
                // Raise the lowest point by up to 3 pixels. If the height of 
                // the open mouth is less than 25%, then raise it by less to 
                // transition between raised and lowered
            yOff -= 3*Math.min(1.0, getRambleyOpenMouthHeight()*4);
            // Set the second point to be in between the starting point and 
            // end point, and below the first point. This will be the mid-point 
            // of the right side of the mouth curve
        point1.setLocation((point.getX()+point2.getX())/2, point.getY()+yOff);
            // Set the first quadratic bezier curve to start at the given point 
            // and end at point1. Set the control point for the curve to be 
            // halfway between the starting point and mid-point of the curve, 
            // with the y-coordinate of the mid-point.
        quadCurve1.setCurve(point.getX(), point.getY(), 
                (point.getX()+point1.getX())/2,point1.getY(), 
                point1.getX(), point1.getY());
            // Set the second quadratic bezier cuve to start at the end of the 
            // first curve and end at point2. Set the control point for the 
            // curve to be halfway between the end of the first curve and the 
            // end of the mouth and at the y-coordinate of the first curve's 
            // control point.
        quadCurve2.setCurve(quadCurve1.getX2(), quadCurve1.getY2(), 
                (point2.getX()+quadCurve1.getX2())/2,quadCurve1.getCtrlY(), 
                point2.getX(), point2.getY());
            // Add the first quadratic bezier curve to the path
        path.append(quadCurve1, false);
            // Add the second quadratic bezier curve to the path
        path.append(quadCurve2, true);
        
            // Form the line at the right edge of his mouth
        
            // Move to three pixels to the right and two pixels up from the end 
            // of the curve
        path.moveTo(quadCurve2.getX2()+3, quadCurve2.getY2()-2);
            // Move the second point to 2.5 pixels to the left and 3.5 pixels 
            // below the end of the curve
        point2.setLocation(quadCurve2.getX2()-2.5, quadCurve2.getY2()+3.5);
            // Get the control point for the curve at the edge of his mouth
        point1 = GeometryMath.getQuadBezierControlPoint(path.getCurrentPoint(),
                quadCurve2.getP2(),point2,point1);
            // Draw a quadratic bezier curve to the end of the edge curve, using 
            // the calculated control point in point1
        path.quadTo(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            // Flip the path (which holds the right side of the mouth) 
            // horizontally to form the left side of the mouth and then add the 
            // left side of the mouth to the path.
        path = mirrorPathHorizontally(path,point.getX());
        return path;
    }
    /**
     * This creates and returns the Area that forms Rambley's open mouth. This 
     * uses the ellipse given to the {@link #getRambleySnout getRambleySnout} 
     * method ({@code snout}) to control the maximum height of the mouth. This 
     * also uses the path returned by and the quadratic curves given to the 
     * {@link #getRambleyMouthCurve getRambleyMouthCurve} method, those being 
     * {@code mouthCurve} (the path), {@code mouthQuad1}, and {@code 
     * mouthQuad2} to control and position the mouth.
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
     * @param mouthQuad1 The QuadCurve2D object with the first half of the right 
     * side of the mouth curve (cannot be null). This is the curve from the 
     * center of the mouth curve to the lowest point of the right side of the 
     * mouth curve.
     * @param mouthQuad2 The QuadCurve2D object with the first second of the 
     * right side of the mouth curve (cannot be null). This is the curve from 
     * the lowest point of the right side of the mouth curve to the end of the 
     * right side of the mouth curve.
     * @param point1 A Point2D object to use to calculate the point on the right 
     * side of the mouth curve to start the path that forms the open mouth 
     * shape, or null.
     * @param point2 A Point2D object to use to calculate some points used to 
     * create the open mouth, or null.
     * @param quadCurve A QuadCurve2D object to use to store the curve for the 
     * right side of the open mouth, or null.
     * @param rect A Rectangle2D object to store the bounds of the path used to 
     * create the open mouth, or null.
     * @param path A Path2D object to use to calculate the path for the open 
     * mouth, or null.
     * @return The area that forms Rambley's open mouth.
     * @see #paintRambley 
     * @see #paintRambleySnout 
     * @see #getRambleySnout 
     * @see #getRambleyNose
     * @see #getRambleyMouthCurve
     * @see #getRambleyTongue
     * @see #getRambleyFangCurve 
     * @see #getRambleyFangs
     * @see #getRambleyClosedTeeth
     * @see #getRambleyOpenMouthWidth 
     * @see #setRambleyOpenMouthWidth 
     * @see #getRambleyOpenMouthHeight
     * @see #setRambleyOpenMouthHeight
     */
    private Area getRambleyOpenMouth(Path2D mouthCurve, double mouthWidth, 
            double mouthHeight, Ellipse2D snout,QuadCurve2D mouthQuad1, 
            QuadCurve2D mouthQuad2, Point2D point1, Point2D point2, 
            QuadCurve2D quadCurve, Rectangle2D rect, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
           // If the given Rectangle2D object is null
        if (rect == null)
            rect = new Rectangle2D.Double();
            // If the given scratch Point2D object is null
        if (point1 == null)
            point1 = new Point2D.Double();
            // If the given QuadCurve2D object is null
        if (quadCurve == null)
            quadCurve = new QuadCurve2D.Double();
            // Set the frame of the rectangle from the center to have the center 
            // at the mid point of the mouth curve. It will be as wide as the 
            // mouth curve and the maximum y-coordinate will be 3.5 pixels above 
            // the bottom of the snout ellipse. The lower half of this forms the 
            // maximum bounds of the open mouth
        rect.setFrameFromCenter(mouthQuad1.getX1(), mouthQuad1.getY1(), 
                mouthQuad2.getX2(), snout.getMaxY()-3.5);
            // Set the frame of the rectangle again from the center with the 
            // same center as before, but now set the x and y coordinates based 
            // off the given width and height for the mouth. 
        rect.setFrameFromCenter(rect.getCenterX(),rect.getCenterY(),
                rect.getMinX()+((1-mouthWidth)*(rect.getWidth()/2.0)),
                rect.getMinY()+((1-mouthHeight)*(rect.getHeight()/2.0)));
            // If the x-coordinate of the rectangle is at or beyond the point 
            // between the two curves that form the right side of the mouth 
        if (rect.getMinX() <= mouthQuad1.getX2())   // curve
                // Add the first part of the curve to the path
            path.append(mouthQuad1, false);
            // If the x-coordinate of the rectangle is at or beyond the 
            // left-most point on the mouth curve
        if (rect.getMinX() <= mouthQuad2.getX2()){
            point1.setLocation(mouthQuad2.getX2(),mouthQuad2.getY2());
                // Add the second part of the curve to the path
            path.append(mouthQuad2, true);
        }   // If the x-coordinate of the rectangle is at the point between the 
            // two curves that form the right side of the mouth curve
        else if (rect.getMinX() == mouthQuad1.getX2()){
            point1.setLocation(mouthQuad1.getX2(), mouthQuad1.getY2());
        }   // If the x-coordinate of the rectangle is at or beyond the center 
            // of the mouth curve
        else if (rect.getMinX() >= mouthQuad1.getX1()){
            point1.setLocation(mouthQuad1.getX1(), mouthQuad1.getY1());
                // Move the path to the current point
            path.moveTo(point1.getX(),point1.getY());
        } else {// Get if the x-coordinate for the rectangle lies on the 
                // left-most curve on the right side of the mouth. That is to 
                // say, if it lies on the second mouth curve
            boolean useCurve2 = rect.getMinX() >= mouthQuad2.getX2() && 
                    rect.getMinX() <= mouthQuad2.getX1();
                // This is the curve to get a point on. If the x-coordinate of 
                // the rectangle lies on the left-most curve on the right side 
                // of the mouth, then use the second mouth curve. Otherwise, use 
                // the first mouth curve
            QuadCurve2D curve = (useCurve2) ? mouthQuad2 : mouthQuad1;
                // Get the point on the quadratic bezier curve at the 
                // x-coordinate of the rectangle on the quadratic bezier curve 
                // that forms the mouth curve
            point1 = GeometryMath.getQuadBezierPointForX(curve,rect.getMinX(),
                    point1);
                // Get the point on the quadratic bezier curve in between the 
                // start of the curve and point1
            point2 = GeometryMath.getQuadBezierPointForX(curve, 
                    (point1.getX()+curve.getX1())/2.0, point2);
                // Get the control point for the segment of the mouth curve that 
                // ends at point1
            point2 = GeometryMath.getQuadBezierControlPoint(
                    curve.getX1(),curve.getY1(),point2.getX(),point2.getY(),
                    point1.getX(),point1.getY(),point2);
                // If this is not using the second mouth curve
            if (!useCurve2)
                    // Move to the start of the mouth curve
                path.moveTo(curve.getX1(),curve.getY1());
                // Add the segment of the mouth curve up until point1
            path.quadTo(point2.getX(),point2.getY(),point1.getX(),point1.getY());
        }   // Set the quadratic bezier curve for the open mouth curve to start 
            // at the point on the closed mouth curve and end at the bottom 
            // center of the open mouth bounds. Use a control point that is 
            // 1/3rd of the way left from the starting point to the end point, 
            // and at the bottom of the curve
        quadCurve.setCurve(point1.getX(), point1.getY(), 
                (point1.getX()*2+rect.getCenterX())/3.0, rect.getMaxY(), 
                rect.getCenterX(), rect.getMaxY());
            // Add the quadratic bezier curve to the path
        path.append(quadCurve, true);
            // Close the path
        path.closePath();
            // Flip the path (which holds the right side of the open mouth) 
            // horizontally to form the left side of the open mouth and then add 
            // the left side of the open mouth to the path.
        path = mirrorPathHorizontally(path,quadCurve.getX2());
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
     * the shape of the mouth returned by the {@link #getRambleyOpenMouth
     * getRambleyOpenMouth} method ({@code openMouth}) to ensure that the tongue 
     * does not escape the mouth. 
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
     * @see #paintRambley 
     * @see #paintRambleySnout 
     * @see #getRambleyNose
     * @see #getRambleyMouthCurve
     * @see #getRambleyOpenMouth
     * @see #getRambleyFangs
     * @see #getRambleyClosedTeeth
     * @see #getRambleyOpenMouthWidth 
     * @see #setRambleyOpenMouthWidth 
     * @see #getRambleyOpenMouthHeight
     * @see #setRambleyOpenMouthHeight
     */
    private Area getRambleyTongue(double xC,double x,double y,double mouthWidth, 
            double mouthHeight, Area openMouth, Ellipse2D ellipse){
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
     * This calculates and returns the quadratic bezier curve that forms half of 
     * Rambley's fang located at the given top center coordinate of the visible 
     * portion of the fang. The portion of the fang above the given y-coordinate 
     * is intended to be obscured by the top of the mouth, while the portion of 
     * the fang below the given y-coordinate is intended to be visible in the 
     * open mouth. <p>
     * 
     * Thank you AnimalWave on Discord for the equations on which the fang's 
     * size was based off of.
     * 
     * @todo Add references to other related methods. 
     * 
     * @param x The x-coordinate of the center of the fang.
     * @param y The y-coordinate of the top of the visible portion of the fang.
     * @param mouthWidth A value between 0.0 and 1.0, inclusive, used to control 
     * the width of Rambley's open mouth.
     * @param mouthHeight A value between 0.0 and 1.0, inclusive, used to 
     * control the height of Rambley's open mouth.
     * @param flipped {@code true} to get the curve for the (screen) right side 
     * of the fang, {@code false} to get the curve for the (screen) left side of 
     * the fang.
     * @param curve The QuadCurve2D object to store the results in, or null.
     * @return A QuadCurve2D object with the curve that forms half of Rambley's 
     * fang.
     * @see #paintRambley 
     * @see #paintRambleySnout 
     * @see #getRambleyMouthCurve
     * @see #getRambleyOpenMouth
     * @see #getRambleyFangs
     * @see #getRambleyClosedTeeth
     * @see #getRambleyOpenMouthWidth 
     * @see #setRambleyOpenMouthWidth 
     * @see #getRambleyOpenMouthHeight
     * @see #setRambleyOpenMouthHeight
     * @see #isRambleyEvil 
     * @see #setRambleyEvil 
     * @see #isRambleyFlipped 
     * @see #setRambleyFlipped 
     * @see #RAMBLEY_FANG_WIDTH
     * @see #RAMBLEY_FANG_HEIGHT
     * @see RAMBLEY_FANG_VISIBLE_HEIGHT
     */
    protected QuadCurve2D getRambleyFangCurve(double x, double y, 
            double mouthWidth, double mouthHeight, boolean flipped, 
            QuadCurve2D curve){
            // If the given QuadCurve2D object is null
        if (curve == null)
            curve = new QuadCurve2D.Double();
            // This gets the x-coordinate for the starting point of the curve. 
            // If we are getting the left side of the fang, subtract half the 
            // fang's width from the given x-coordinate to get the top-left 
            // corner. If we are getting the right side of the fang, add half of 
            // the fang's width to the given x-coordinate to get the top-right 
            // corner. 
        double x1 = x+(flipped?RAMBLEY_FANG_HALF_WIDTH:-RAMBLEY_FANG_HALF_WIDTH);
            // This gets the y-coordinate of the bottom of the fang. 
        double y2 = y+RAMBLEY_FANG_VISIBLE_HEIGHT;
            // Set the quadratic bezier curve to start at the top left/right of 
            // the fang, ending at the bottom center of the fang, and use a
            // control point in the center bottom of the curve
        curve.setCurve(x1, y-RAMBLEY_FANG_OBSCURED_HEIGHT,(x1+x)/2.0,y2,x,y2);
        return curve;
    }
    /**
     * This creates and returns the Area that forms Rambley's fangs. This uses 
     * the two quadratic bezier curves that form the right side of Rambley's 
     * mouth, as calculated by the {@link #getRambleyMouthCurve 
     * getRambleyMouthCurve} method (these being the curves {@code mouthQuad1} 
     * and {@code mouthQuad2}), to control the size and position of Rambley's 
     * right fang. The curve that forms half of Rambley's right fang are 
     * calculated using the {@link #getRambleyFangCurve getRambleyFangCurve} 
     * method, with the top center of the visible portion of the right fang 
     * being located around the lowest point on the right side of the mouth 
     * curve. If either Rambley is {@link #isRambleyEvil evil} or Rambley's fang 
     * is to be on the {@link #isRambleyFlipped left side of his face}, then 
     * his right fang will be  mirrored to produce his left fang. If Rambley is 
     * evil, then the left fang will be added to the right fang to give Rambley 
     * fangs on either side of his face. If Rambley is not evil, but his fang is 
     * to be on his left, then the left fang will be returned instead of the 
     * right fang. Finally, this uses the shape of the mouth returned by the 
     * {@link #getRambleyOpenMouth getRambleyOpenMouth} method ({@code 
     * openMouth}) to ensure that the fang(s) do not escape the mouth.
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
     * @param mouthQuad1 The QuadCurve2D object with the first half of the right 
     * side of the mouth curve (cannot be null). This is the curve from the 
     * center of the mouth curve to the lowest point of the right side of the 
     * mouth curve.
     * @param mouthQuad2 The QuadCurve2D object with the first second of the 
     * right side of the mouth curve (cannot be null). This is the curve from 
     * the lowest point of the right side of the mouth curve to the end of the 
     * right side of the mouth curve.
     * @param openMouth The area that forms Rambley's open mouth.
     * @param quadCurve A QuadCurve2D object to use to store the right half of 
     * the curve that forms the fang, or null.
     * @param path A Path2D object to use to calculate the path for the fang, or 
     * null.
     * @return The area that forms Rambley's fang(s).
     * @see #paintRambley 
     * @see #paintRambleySnout 
     * @see #getRambleyNose
     * @see #getRambleyMouthCurve
     * @see #getRambleyOpenMouth
     * @see #getRambleyTongue
     * @see #getRambleyFangCurve 
     * @see #getRambleyClosedTeeth
     * @see #getRambleyOpenMouthWidth 
     * @see #setRambleyOpenMouthWidth 
     * @see #getRambleyOpenMouthHeight
     * @see #setRambleyOpenMouthHeight
     * @see #isRambleyEvil 
     * @see #setRambleyEvil 
     * @see #isRambleyFlipped 
     * @see #setRambleyFlipped 
     * @see #RAMBLEY_FANG_WIDTH
     * @see #RAMBLEY_FANG_HEIGHT
     * @see RAMBLEY_FANG_VISIBLE_HEIGHT
     */
    private Area getRambleyFangs(double mouthWidth, double mouthHeight, 
            QuadCurve2D mouthQuad1, QuadCurve2D mouthQuad2, Area openMouth, 
            QuadCurve2D quadCurve, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // Calculate the points for the curve that forms Rambley's right 
            // fang. Rambley's right fang is 1.5 pixels to the left of the 
            // bottom point of the mouth curve
        quadCurve = getRambleyFangCurve(mouthQuad2.getX1()-1.5, 
                mouthQuad2.getY1(),mouthWidth,mouthHeight,false,quadCurve);
            // Start the path at the top center of the fang
        path.moveTo(quadCurve.getX2(), quadCurve.getY1());
            // Add the quadratic bezier curve to the path
        path.append(quadCurve, true);
            // Close the path
        path.closePath();
            // Flip the path (which holds the left part of the fang) 
            // horizontally to form the right part of the fang and then add the 
            // right part of the fang to the path.
        path = mirrorPathHorizontally(path,quadCurve.getX2());
            // If Rambley is evil
        if (isRambleyEvil())
                // Add a left fang to the path of the right fang
            path = mirrorPathHorizontally(path,mouthQuad1.getX1());
            // Create the area for the fang
        Area fang = new Area(path);
            // If Rambley is not evil and Rambley's fang should be on the left
        if (!isRambleyEvil() && isRambleyFlipped()){
                // Get an AffineTransform to flip the area horizontally and 
                // mirror it over the vertical line at the center of the mouth
            afTx = getHorizontalMirrorTransform(mouthQuad1.getX1(),fang,afTx);
                // Flip the fang to get the left fang
            fang.transform(afTx);
        }
            // Remove all but what lies within Rambley's mouth from the fang(s)
        fang.intersect(openMouth);
        return fang;
    }
    /**
     * This creates and returns the path to use to draw the line that separates 
     * the top and bottom of Rambley's jaw. 
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
     * @param mouthQuad1 The QuadCurve2D object with the first half of the right 
     * side of the mouth curve (cannot be null). This is the curve from the 
     * center of the mouth curve to the lowest point of the right side of the 
     * mouth curve.
     * @param mouthQuad2 The QuadCurve2D object with the first second of the 
     * right side of the mouth curve (cannot be null). This is the curve from 
     * the lowest point of the right side of the mouth curve to the end of the 
     * right side of the mouth curve.
     * @param mouthCurve The Path2D object that forms the mouth curve (cannot be 
     * null).
     * @param openQuad The QuadCurve2D object with the curve that forms half of 
     * the shape of Rambley's open mouth (cannot be null).
     * @param mouthOpen The area that forms Rambley's open mouth.
     * @param point A Point2D object to use to calculate some points used for 
     * the line, or null.
     * @param quadCurve A QuadCurve2D object to use to create the line segment 
     * of the right fang curve on the line, or null.
     * @param path A Path2D object to store the results in, or null.
     * @return A Path2D object to use to draw the line separating the upper and 
     * lower halves of Rambley's jaw.
     */
    private Path2D getRambleyClosedTeeth(double mouthWidth, double mouthHeight, 
            QuadCurve2D mouthQuad1, QuadCurve2D mouthQuad2, Path2D mouthCurve, 
            QuadCurve2D openQuad, Area mouthOpen, Point2D point, 
            QuadCurve2D quadCurve, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // This contains the y-coordinate for the top of the line. The line 
            // will be placed about halfway between the top and bottom of the 
            // open mouth. 
        double y = mouthQuad2.getY2()+(openQuad.getY2()-mouthQuad2.getY2())/2.0
                -mouthHeight;
            // Get the curve for the (screen) left side of the right fang. Put 
            // it 1 pixel to the right of the lowest point of the top mouth 
            // curve, and have the bottom of the right fang be 6 pixels below 
            // the line
        quadCurve = getRambleyFangCurve(mouthQuad2.getX1()+1,
                y-RAMBLEY_FANG_VISIBLE_HEIGHT+6,mouthWidth,mouthHeight,false,
                quadCurve);
            // Calculate the starting point on the curve for Rambley's fang 
            // that is at the top of the curve on the teeth line
        point = GeometryMath.getQuadBezierPointForY(quadCurve, y, point);
            // Calculate the segment of the curve for Rambley's fang that will 
            // appear on the teeth line
        quadCurve = GeometryMath.getQuadBezierCurveSegment(
                point.getX(),point.getY(),quadCurve.getX2(),quadCurve.getY2(), 
                quadCurve, quadCurve);
            // Append the segment of the fang curve to the path
        path.append(quadCurve, false);
            // Mirror the path horizontally to get the other side of the fang
        path = mirrorPathHorizontally(path,quadCurve.getX2());
            // Get the bounds of the fang
        Rectangle2D fangBounds = path.getBounds2D();
            // Move the path to the left-most point on the line, which is as the 
            // right side of the mouth curve
        path.moveTo(mouthQuad2.getX2(), y);
            // Draw a line to the start of the fang
        path.lineTo(fangBounds.getMinX(), y);
            // Move the path to the end of the fang
        path.moveTo(fangBounds.getMaxX(), y);
            // Draw a line to the center of the mouth
        path.lineTo(mouthQuad1.getX1(), y);
            // If Rambley is evil
        if (isRambleyEvil())
                // Mirror the path to form the left half which also has a fang
            return mirrorPathHorizontally(path,mouthQuad1.getX1());
            // Draw a line to 5 pixels to the right of the center of the mouth
        path.lineTo(mouthQuad1.getX1()+5, y);
            // If Rambley's fang is to be on his left
        if (isRambleyFlipped()){
                // Get an AffineTransform to flip the path horizontally and 
                // mirror it over the vertical line at the center of the mouth 
                // to flip it horizontally in place
            afTx = getHorizontalMirrorTransform(mouthQuad1.getX1(),path,afTx);
                // Create a shape that is the mirrored image of the path in the 
                // same location as the path
            Shape temp = path.createTransformedShape(afTx);
                // Reset the path
            path.reset();
                // Add the mirrored path to the path to make it contain the 
                // horizontally flipped version of the path
            path.append(temp, false);
        }
        return path;
    }
    /**
     * This creates and returns the Area that forms Rambley's scarf without the 
     * knot at the back. That is to say, this returns the area that forms the 
     * neck portion of Rambley's scarf.
     * 
     * @todo Add references to other related methods. 
     * 
     * @param x The x-coordinate of the bottom center of the scarf.
     * @param y The y-coordinate of the bottom center of the scarf.
     * @param cubicCurve1 A CubicCurve2D object to store the bottom-left curve 
     * of the top portion of the scarf, or null.
     * @param cubicCurve2 A CubicCurve2D object to store the top-left curve of 
     * the top portion of the scarf, or null.
     * @param path A Path2D object to use to form the top portion of the scarf, 
     * or null. 
     * @return The area that forms the shape of Rambley's scarf without the 
     * knot.
     */
    private Area getRambleyNeckScarf(double x, double y, 
            CubicCurve2D cubicCurve1, CubicCurve2D cubicCurve2, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // If the first given CubicCurve2D object is null
        if (cubicCurve1 == null)
            cubicCurve1 = new CubicCurve2D.Double();
            // If the second given CubicCurve2D object is null
        if (cubicCurve2 == null)
            cubicCurve2 = new CubicCurve2D.Double();
            // Shift it up to get the bottom of the top part of the scarf 
        y -= RAMBLEY_NECK_SCARF_Y_OFFSET;
            // This gets the left-most x-coordinate for the scarf
        double x1 = x-RAMBLEY_NECK_SCARF_HALF_WIDTH;
            // This gets the center y-coordinate for the top part of the scarf
        double y1 = y-RAMBLEY_NECK_SCARF_HALF_HEIGHT;
            // Set the first curve of the top part to be from the center left of 
            // the top of the scarf to the bottom center of the top of the 
            // scarf. The first control coordinate is at 7.5 pixels below the 
            // start of the curve. The second control point is 15 pixels to the 
            // left of the end of the curve. This forms the bottom-left curve 
            // used for the top part of the scarf
        cubicCurve1.setCurve(x1, y1, x1, y1+7.5, x-15, y, x, y);
            // Set the second curve to be a vertically flipped version of the 
            // first curve that starts at the top center of the top part of the 
            // scarf and ends where the first curve starts. This forms the 
            // top-left curve for the top part of the scarf
        cubicCurve2.setCurve(
                cubicCurve1.getX2(), y1-(cubicCurve1.getY2()-y1), 
                cubicCurve1.getCtrlX2(), y1-(cubicCurve1.getCtrlY2()-y1), 
                cubicCurve1.getCtrlX1(), y1-(cubicCurve1.getCtrlY1()-y1), 
                cubicCurve1.getX1(), cubicCurve1.getY1());
            // Append the top-left curve to the path
        path.append(cubicCurve2, false);
            // Append the bottom-left curve to the path
        path.append(cubicCurve1, true);
            // Close the path
        path.closePath();
            // Mirror the path horizontally to get the other side of the top of 
            // the scarf
        path = mirrorPathHorizontally(path,x);
            // Create an area with the path to get the neck portion of the scarf
        Area scarf = new Area(path);
            // If the scratch AffineTransform is null
        if (afTx == null)
                // Create a translation transform to move the top of the scarf 
                // down to form the bottom of the scarf
            afTx = AffineTransform.getTranslateInstance(0, RAMBLEY_NECK_SCARF_Y_OFFSET);
        else    // Set the transform to be a translation transform to move the 
                // top of the scarf down to form the bottom of the scarf
            afTx.setToTranslation(0, RAMBLEY_NECK_SCARF_Y_OFFSET);
            // Add a version of the scarf area that has been translated 
            // downwards to form the bottom part of the scarf
        scarf.add(scarf.createTransformedArea(afTx));
        return scarf;
    }
    /**
     * This creates and returns the path to use to draw the details for 
     * Rambley's scarf without the knot at the back. That is to say, this 
     * returns the details for the neck portion of Rambley's scarf. This uses 
     * the first curve passed to the {@link #getRambleyNeckScarf 
     * getRambleyNeckScarf} method ({@code scarfCurve}) to form the details for 
     * the scarf.
     * 
     * @todo Add references to other related methods. 
     * 
     * @param scarfCurve The CubicCurve2D object with the bottom-left curve of 
     * the top portion of the scarf (cannot be null).
     * @param cubicCurve1 A CubicCurve2D object to use to split a CubicCurve2D 
     * object, or null.
     * @param cubicCurve2 A second CubicCurve2D object to use to split a 
     * CubicCurve2D object, or null.
     * @param path A Path2D object to store the results in, or null.
     * @return A Path2D object to use to draw the details for Rambley's scarf 
     * without the knot.
     */
    private Path2D getRambleyNeckScarfDetail(CubicCurve2D scarfCurve, 
            CubicCurve2D cubicCurve1, CubicCurve2D cubicCurve2, Path2D path){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
            // If the first given CubicCurve2D object is null
        if (cubicCurve1 == null)
            cubicCurve1 = new CubicCurve2D.Double();
            // If the second given CubicCurve2D object is null
        if (cubicCurve2 == null)
            cubicCurve2 = new CubicCurve2D.Double();
            // Divide the curve
        scarfCurve.subdivide(cubicCurve1, cubicCurve2);
            // Add the left side of the curve to the path
        path.append(cubicCurve1, false);
            // Divide the right side of the curve
        cubicCurve2.subdivide(cubicCurve1, cubicCurve2);
            // Divide the left side of the right side of the curve
        cubicCurve1.subdivide(cubicCurve1, cubicCurve2);
            // Add the left side of the curve to the path
        path.append(cubicCurve1, true);
            // Divide the right side of the curve
        cubicCurve2.subdivide(cubicCurve1, cubicCurve2);
            // Add the left side of the curve to the path
        path.append(cubicCurve1, true);
            // Add a line that goes down and to the left
        path.lineTo(cubicCurve1.getX2()-7, cubicCurve1.getY2()+4);
            // Mirror the path to get the other side
        path = mirrorPathHorizontally(path,scarfCurve.getX2());
        return path;
    }
    /**
     * 
     * 
     * {@code y = 0.7 * (x-1.5)^2}
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param x
     * @return 
     */
    private double getRambleyLowerScarfTopY(double x){
            // Convert the x-coordinate to the equation coord. system
        x = graphicsToLowScarfEquX(x);
            // Calculate y = 0.7 * (x-1.5)^2
        double y = 0.7*Math.pow(x-1.5, 2);
            // Convert the y-coordinate to the graphics coord. system
        return lowScarfEquToGraphicsY(y);
    }
    /**
     * 
     * {@code y = -0.4 * (x-1.9)^2 + 1.53}
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param x
     * @return 
     */
    private double getRambleyLowerScarfBottomY(double x){
            // Convert the x-coordinate to the equation coord. system
        x = graphicsToLowScarfEquX(x);
            // Calculate y = -0.4 * (x-1.9)^2 + 1.53
        double y = -0.4*Math.pow(x-1.9, 2)+1.53;
            // Convert the y-coordinate to the graphics coord. system
        return lowScarfEquToGraphicsY(y);
    }
    /**
     * 
     * @param x
     * @param y
     * @param path 
     * @param point1 
     * @param point2 
     * @param point3 
     * @param point4 
     * @return 
     */
    private Area getRambleyLowerScarfEnd(double x, double y, Path2D path, 
            Point2D point1, Point2D point2, Point2D point3, Point2D point4){
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
        if (point3 == null)
            point3 = new Point2D.Double();
            // If the fourth of the four given scratch Point2D objects is null
        if (point4 == null)
            point4 = new Point2D.Double();
            // Start at the left-most point of the end of the scarf
        point1.setLocation(x,y);
            // Move the path to the starting point
        path.moveTo(point1.getX(), point1.getY());
            // Offset the x-coordinate to get the left-most side of the scarf 
            // end
        x -= RAMBLEY_SCARF_LOWER_END_WIDTH;
            // Offset the y-coordinate to get the top of the end of the scarf 
        y -= getRambleyLowerScarfTopY(RAMBLEY_SCARF_LOWER_END_WIDTH);
            // Calculate the offset for the x-coordinate when 53.5% of the way 
        double tempX = RAMBLEY_SCARF_LOWER_END_WIDTH * 0.535;   // to the right
            // Get the point on the upper curve when 53.5% of the way right
        point2.setLocation(x+tempX,y+getRambleyLowerScarfTopY(tempX));
            // Get the point at the end of the upper curve
        point3.setLocation(x, y+getRambleyLowerScarfTopY(0));
            // Calculate the quadratic bezier curve that passes through points 
            // point1, point2, and point3, and add that curve to the path
        point4 = addQuadBezierCurve(point1,point2,point3,point4,path);
            // Calculate the offset for the x-coordinate when 47% of the way 
        tempX = RAMBLEY_SCARF_LOWER_END_WIDTH * 0.47;    // to the right
            // Get the point on the lower curve when 47% of the way right
        point2.setLocation(x+tempX,y+getRambleyLowerScarfBottomY(tempX));
            // Calculate the quadratic bezier curve that passes through points 
            // point3, point2, and point1, and add that curve to the path
        addQuadBezierCurve(point3,point2,point1,point4,path);
            // Close the path
        path.closePath();
            // Create and return an area with the scarf end
        return new Area(path);
    }
    
    private Path2D getRambleyLowerScarfEndDetail(Area scarfEnd, Path2D path, 
            QuadCurve2D quadCurve, Point2D point){
            // If the given Path2D object is null
        if (path == null)
            path = new Path2D.Double();
        else    // Reset the given Path2D object
            path.reset();
        RectangularShape bounds = getBoundsOfShape(scarfEnd);
        
        double d1 = getTestDouble1();
        double d2 = getTestDouble2();
        
        double x0, x2;
        double x1 = RAMBLEY_SCARF_LOWER_END_WIDTH * (getTestDouble3()/100);
        double y = bounds.getMinY();
        double y0 = y + getRambleyLowerScarfTopY(RAMBLEY_SCARF_LOWER_END_WIDTH);
        double y2 = y+getRambleyLowerScarfTopY(0);
        double y1 = y+(getRambleyLowerScarfTopY(x1)*d1+getRambleyLowerScarfBottomY(x1)*d2)/(d1+d2);
            // If Rambley is flipped
        if (isRambleyFlipped()){
            x2 = bounds.getMaxX();
            x0 = bounds.getMinX();
            x1 = -x1;
        } else {
            x2 = bounds.getMinX();
            x0 = bounds.getMaxX();
        }
        x1 += x2;
        quadCurve = GeometryMath.getQuadBezierCurve(x0, y0, x1, y1, x2, y2, quadCurve);
        if (getABTesting()){
            point = GeometryMath.getQuadBezierPoint(quadCurve, getTestDouble4()/100, point);
            quadCurve = GeometryMath.getQuadBezierCurveSegment(quadCurve.getX1(), quadCurve.getY1(), 
                    point.getX(), point.getY(), quadCurve, quadCurve);
        }
        path.append(quadCurve, false);
        return path;
    }
    
    
    
    /**
     * This is used to render Rambley the Raccoon, his outline, his shadow, and 
     * his accessories. 
     * 
     * This renders to a copy of the given graphics context, so as to 
     * protect the rest of the paint code from changes made to the graphics 
     * context while rendering Rambley.
     * 
     * @todo Add references to related methods. Describe how Rambley is 
     * rendered, mentioning the methods this method delegates tasks to.
     * 
     * @param g The graphics context to render to.
     * @param x The x-coordinate of the top-left corner of the area to fill.
     * @param y The y-coordinate of the top-left corner of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     * @return The shape of Rambley's body.
     * @see #paint 
     * @see #paintBackground 
     * @see #paintPixelGrid(Graphics2D, int, int, int, int, Shape) 
     * @see #paintPixelGrid(Graphics2D, int, int, int, int) 
     * @see #paintRambleyOutlineAndShadow 
     * @see #paintRambleyHead
     * @see #paintRambleyNeckScarf 
     */
    protected Shape paintRambley(Graphics2D g, int x, int y, int w, int h){
            // Create a copy of the given graphics context limited to the given 
            // area to fill
        g = (Graphics2D) g.create(x,y,w,h);
            // Get the scale factor for the x axis
        double scaleX = w/getRambleyWidth();
            // Get the scale factor for the y axis
        double scaleY = h/getRambleyHeight();
            // If we are to ignore Rambley's aspect ratio
        if (isAspectRatioIgnored()){
                // Scale it to fit the internal rendering size
            g.scale(scaleX, scaleY);
        } else {// Get the smaller of the two scale factors (this will be what 
                // we use to scale the x and y coordinates)
            double scale = Math.min(scaleX, scaleY);
                // Get the width of the area that will actually be rendered
            double width = Math.min(w, w*(scaleY/scaleX));
                // Get the height of the area that will actually be rendered
            double height = Math.min(h, h*(scaleX/scaleY));
                // Translate to center the image that will be rendered
            g.translate((w-width)/2.0, (h-height)/2.0);
                // Scale it, preserving the aspect ratio
            g.scale(scale,scale);
        }   // If the Rectangle2D scratch object has not been initialized yet
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
            // If the first QuadCurve2D scratch object has not been initialized 
        if (quadCurve1 == null)     // yet
            quadCurve1 = new QuadCurve2D.Double();
            // If the second QuadCurve2D scratch object has not been initialized 
        if (quadCurve2 == null)     // yet
            quadCurve2 = new QuadCurve2D.Double();
            // If the first CubicCurve2D scratch object has not been 
        if (cubicCurve1 == null)     // initialized yet
            cubicCurve1 = new CubicCurve2D.Double();
            // If the second CubicCurve2D scratch object has not been 
        if (cubicCurve2 == null)     // initialized yet
            cubicCurve2 = new CubicCurve2D.Double();
            // If the head Ellipse2D scratch object has not been initialized yet
        if (headEllipse == null)
            headEllipse = new Ellipse2D.Double();
            // If the CubicCurve2D object for the scarf has not been initialized 
        if (scarfCurve1 == null)     // yet
            scarfCurve1 = new CubicCurve2D.Double();
        
            // Create the shape for Rambley's head (without his ears for now)
        Area headShape = getRambleyEarlessHead(getRambleyX(),getRambleyY(),
                rect,path,headEllipse,ellipse2);
            // Get the bounds for the head, so that we can base the facial 
            // features off it
        Rectangle2D headBounds = headShape.getBounds2D();
            // Get the area for Rambley's right ear
        Area earR = getRambleyEar(headBounds.getCenterX()-84,headBounds.getMinY()-31.5,path);
            // Flip the area for Rambley's right ear to get his left ear
        Area earL = createHorizontallyMirroredArea(earR,headBounds.getCenterX());
            // Get the area for the inner portion of Rambley's right ear
        Area earInR = getRambleyInnerEar(earR,headShape);
            // Get the area for the inner portion of Rambley's left ear
        Area earInL = getRambleyInnerEar(earL,headShape);
            // Add Rambley's right ear to the shape of his head
        headShape.add(earR);
            // Add Rambley's left ear to the shape of his head.
        headShape.add(earL);
            // Create the front (neck) part of Rambley's scarf. If Rambley is 
            // flipped, the scarf will be offset two pixels to the right. 
            // Otherwise, the scarf will be offset two pixels to the left
        Area scarf1 = getRambleyNeckScarf(
                headBounds.getCenterX()+(isRambleyFlipped()?2:-2),
                headEllipse.getMaxY()+6,scarfCurve1,cubicCurve1,path);
        
        
        double scarfKnotX = getTestDouble9() + scarfCurve1.getX1();
        double scarfKnotY = getTestDouble10() + scarfCurve1.getY1();
            // TODO: Once the rest of the scarf stuff is in place, mess around 
            // with the position to get the best one relative to the scarf
            // This gets the knot part of Rambley's scarf.
        Area scarf2 = null;
            // This gets the upper end of Rambley's scarf
        Area scarf3 = null;
            // This gets the details for the upper end of Rambley's scarf
        Path2D scarf4 = null;
            // Create the lower end of Rambley's scarf
        Area scarf5 = getRambleyLowerScarfEnd(scarfKnotX,scarfKnotY,path,point1,
                point2,point3,point4);
        
            // If Rambley is flipped
        if (isRambleyFlipped()){
                // Flip the lower end of Rambley's scarf
            scarf5.transform(getHorizontalMirrorTransform(scarfCurve1.getX2(),
                    scarf5,horizTx));
                // Flip the details of the lower end of Rambley's scarf
//            scarf6 = flipPathHorizontally(scarf6,scarfCurve1.getX2());
        }

            // This is an area that contains the entire shape of Rambley's 
            // outline. Start this area with the shape of his head.
        Area rambleyShape = new Area(headShape);
            // If Rambley's scarf is to be painted
        if (isRambleyScarfPainted()){
                // Add the neck part of Rambley's scarf to the area
            rambleyShape.add(scarf1);
                // Add the knot of Rambley's scarf to the area
                // Temporary null check
                if (scarf2 != null)
                    rambleyShape.add(scarf2);
                // Add the upper end of Rambley's scarf to the area
                // Temporary null check
                if (scarf3 != null)
                    rambleyShape.add(scarf3);
                // Add the lower end of Rambley's scarf to the area
                // Temporary null check
                if (scarf5 != null)
                    rambleyShape.add(scarf5);
        }
        
            // Code for adding other parts of Rambley's outline goes here
        
            // Set the stroke to use to the normal stroke
        g.setStroke(getRambleyNormalStroke());
            
            // DEBUG: If we are showing the lines that make up Rambley 
        if (getShowsLines()){
            GeometryMath.printShape("headBounds",headBounds);
            GeometryMath.printShape("headShape",headShape);
            GeometryMath.printShape("earR", earR);
            GeometryMath.printShape("rambleyShape",rambleyShape);
            GeometryMath.printShape("headEllipse",headEllipse);
            GeometryMath.printCubicCurve("scarfCurve1", scarfCurve1);
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
//                    RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setColor(RAMBLEY_LINE_COLOR);
            g.draw(rambleyShape);
            g.setColor(Color.ORANGE);
            g.draw(headBounds);
            g.setColor(Color.BLUE);
            g.draw(path);
            g.setColor(Color.GREEN);
            g.draw(scarf5);
        } else {    // DEBUG: If we are not showing the lines that make up Rambley 
                // Render Rambley's outline and shadow.
            paintRambleyOutlineAndShadow(g,rambleyShape);
                // If Rambley's scarf is to be painted
            if (isRambleyScarfPainted())
                    // Draw the back part of Rambley's scarf here
                paintRambleyScarfBack(g,scarf2,scarf3,scarf4,scarf5);
            
                // Draw Rambley's body here. 
            
                // If Rambley's scarf is to be painted
            if (isRambleyScarfPainted())
                    // Draw the front part of Rambley's scarf
                paintRambleyNeckScarf(g,scarf1,scarfCurve1);
                // Draw Rambley's head and face
            paintRambleyHead(g,headBounds,headShape,earR,earL,earInR,earInL);
            
            // Draw Rambley's conductor hat here
            
            // Draw any parts that may cover Rambley's face here
            
        }
            // Dispose of the copy of the graphics context
        g.dispose();
        return rambleyShape;
    }
    /**
     * This is used to render Rambley the Raccoon's head and face. 
     * 
     * This renders to a copy of the given graphics context, so as to 
     * protect the rest of the paint code from changes made to the graphics 
     * context while rendering Rambley's head.
     * 
     * @todo Add references to related methods. Describe how Rambley's head is 
     * rendered, mentioning the methods this method delegates tasks to.
     * 
     * @param g The graphics context to render to.
     * @param headBounds The bounds of Rambley's head, or null.
     * @param headShape The shape of Rambley's head.
     * @param earR The shape of Rambley's right ear.
     * @param earL The shape of Rambley's left ear.
     * @param earInR The shape of the inner portion of Rambley's right ear.
     * @param earInL The shape of the inner portion of Rambley's left ear.
     * @see #paintRambley 
     * @see #paintRambleyEyes
     * @see #paintRambleySnout 
     * @see #getRambleyEarlessHead
     * @see #getRambleyEar 
     * @see #getRambleyInnerEar 
     * @see getRambleyMaskFaceMarkings
     * @see #getRambleyNormalStroke 
     * @see #getRambleyDetailStroke 
     * @see #getRambleyLineStroke 
     * @see RAMBLEY_MAIN_BODY_COLOR
     * @see RAMBLEY_FACE_MARKINGS_COLOR
     * @see RAMBLEY_SECONDARY_BODY_COLOR
     * @see RAMBLEY_LINE_COLOR
     */
    protected void paintRambleyHead(Graphics2D g, RectangularShape headBounds, 
            Area headShape, Area earR, Area earL, Area earInR, Area earInL){
            // If the given bounds for the head is null
        if (headBounds == null)
            headBounds = getBoundsOfShape(headShape);
            // Create a copy of the graphics context
        g = (Graphics2D) g.create();
            // Fill the shape of Rambley's head
        g.setColor(RAMBLEY_MAIN_BODY_COLOR);
        g.fill(headShape);
            // Create and fill the shape of Rambley's mask-like facial markings
        g.setColor(RAMBLEY_FACE_MARKINGS_COLOR);
        g.fill(getRambleyMaskFaceMarkings(headBounds,ellipse1,ellipse2,ellipse3,
                rect));
            // Set the color to use to Rambley's secondary body color
        g.setColor(RAMBLEY_SECONDARY_BODY_COLOR);
            // Fill in the inner portion of Rambley's ears
        g.fill(earInR);
        g.fill(earInL);
            // Paint Rambley's snout, nose, and mouth
        paintRambleySnout(g,headBounds,headShape);
            // Paint Rambley's eyes 
        paintRambleyEyes(g,headBounds,headShape);
            // Set the color to Rambley's main line color
        g.setColor(RAMBLEY_LINE_COLOR);
            // Set the stroke to Rambley's line stroke
        g.setStroke(getRambleyLineStroke());
            // Draw the outline for Rambley's head
        g.draw(headShape);
            // Set the stroke to Rambley's detail stroke
        g.setStroke(getRambleyDetailStroke());
            // Draw the outline for Rambley's inner right ear
        g.draw(earInR);
            // Draw the outline for Rambley's inner left ear
        g.draw(earInL);
            // Dispose of the copy of the graphics context
        g.dispose();
    }
    /**
     * This is used to render Rambley the Raccoon's eyes. 
     * 
     * This renders to a copy of the given graphics context, so as to 
     * protect the rest of the paint code from changes made to the graphics 
     * context while rendering Rambley's eyes.
     * 
     * @todo Add references to related methods. Describe how Rambley's eyes are 
     * rendered, mentioning the methods this method delegates tasks to.
     * 
     * @param g The graphics context to render to.
     * @param headBounds The bounds of Rambley's head, or null.
     * @param headShape The shape of Rambley's head (cannot be null).
     */
    protected void paintRambleyEyes(Graphics2D g, RectangularShape headBounds, 
            Area headShape){
            // If the iris Ellipse2D object has not been initialized yet
        if (iris == null)
            iris = new Ellipse2D.Double();
            // If the pupil Ellipse2D object has not been initialized yet
        if (pupil == null)
            pupil = new Ellipse2D.Double();
            // If the given bounds for the head is null
        if (headBounds == null)
            headBounds = getBoundsOfShape(headShape);
            // Create Rambley's right eyebrow (this will intersect with the 
            // other eye markings)
        Area eyeBrowR = getRambleyEyebrow(headBounds,ellipse2);
            // Flip to form the Left eyebrow (this will intersect with the 
            // other eye markings)
        Area eyeBrowL = createHorizontallyMirroredArea(eyeBrowR,
                headBounds.getCenterX());
            // Create the area around Rambley's right eye
        Area eyeSurroundR = getRambleyEyeMarkings(headBounds,ellipse3,path,
                point1,point2,quadCurve1);
            // Flip to form the area around Rambley's left eye
        Area eyeSurroundL = createHorizontallyMirroredArea(eyeSurroundR,
                headBounds.getCenterX());
            // Create the shape of Rambley's right eye
        Area eyeWhiteR = getRambleyEyeShape(headBounds,ellipse3,quadCurve1,
                ellipse1,rect,path,point4,point5,point6);
            // Flip to form the shape of Rambley's left eye
        Area eyeWhiteL = createHorizontallyMirroredArea(eyeWhiteR,
                headBounds.getCenterX());
            // Create a copy of the graphics context
        g = (Graphics2D) g.create();
            // Set the color to Rambley's eye brow color
        g.setColor(RAMBLEY_EYEBROW_COLOR);
            // Fill in Rambley's eyebrows
        g.fill(eyeBrowR);
        g.fill(eyeBrowL);
            // Set the color to use to Rambley's secondary body color
        g.setColor(RAMBLEY_SECONDARY_BODY_COLOR);
            // Fill in the area arround Rambley's eyes
        g.fill(eyeSurroundR);
        g.fill(eyeSurroundL);
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
            // Dispose of the copy of the graphics context
        g.dispose();
    }
    /**
     * This is used to render Rambley the Raccoon's snout, nose, and mouth. This 
     * uses the shape and bounds of the head to control the position of the 
     * snout.
     * 
     * This renders to a copy of the given graphics context, so as to 
     * protect the rest of the paint code from changes made to the graphics 
     * context while rendering Rambley's snout.
     * 
     * @todo Add references to related methods. Describe how Rambley's snout is 
     * rendered, mentioning the methods this method delegates tasks to.
     * 
     * @param g The graphics context to render to.
     * @param headBounds The bounds of Rambley's head, or null.
     * @param headShape The shape of Rambley's head (cannot be null).
     * @see #paintRambley 
     * @see #paintRambleyHead 
     * @see #paintRambleyEyes
     * @see getRambleySnout
     * @see getRambleyNose
     * @see #getRambleyMouthCurve
     * @see #getRambleyOpenMouth
     * @see #getRambleyTongue 
     * 
     * @see #getRambleyNormalStroke 
     * @see #getRambleyDetailStroke 
     * @see #getRambleyLineStroke 
     */
    protected void paintRambleySnout(Graphics2D g, RectangularShape headBounds, 
            Area headShape){
            // If the first mouth QuadCurve2D scratch object has not been 
        if (mouthCurve1 == null)    // initialized yet
            mouthCurve1 = new QuadCurve2D.Double();
            // If the second mouth QuadCurve2D scratch object has not been  
        if (mouthCurve2 == null)     // initialized yet
            mouthCurve2 = new QuadCurve2D.Double();
            // Create the area around Rambley's nose and mouth
        Area snoutArea = getRambleySnout(headBounds,headShape,ellipse2,path,
                point1,point2,quadCurve1);
            // Create the shape of Rambley's nose
        Area nose = getRambleyNose(ellipse2,rect,ellipse1,path);
            // Set the location of the point to the bottom center of the nose
        point1.setLocation(rect.getCenterX(),rect.getMaxY());
            // Get the curve for Rambley's mouth, using the bottom center of the 
            // nose to position the mouth.
        mouthPath = getRambleyMouthCurve(ellipse2,point1,mouthCurve1,
                mouthCurve2,point2,point3,mouthPath);
            // Create a copy of the graphics context
        g = (Graphics2D) g.create();
            // Set the color to use to Rambley's secondary body color
        g.setColor(RAMBLEY_SECONDARY_BODY_COLOR);
            // Fill in Rambley's snout area
        g.fill(snoutArea);
            // If Rambley's open mouth is open
        if (isRambleyMouthOpen()){
                // Get how wide the mouth should be
            double mouthWidth = getRambleyOpenMouthWidth();
                // Get the height of the open mouth
            double mouthHeight = getRambleyOpenMouthHeight();
                // Create the shape of Rambley's mouth when open
            Area openMouth = getRambleyOpenMouth(mouthPath, mouthWidth, 
                    mouthHeight,ellipse2,mouthCurve1,mouthCurve2,point4,point5,
                    quadCurve2,rect,path);
                // Set the color to use for drawing the area for Rambley's open
                // mouth. If Rambley's jaw is closed, use Rambley's teeth color 
                // since we can only see Rambley's teeth. Otherwise, use the 
                // color of the inside of Rambley's mouth
            g.setColor(isRambleyJawClosed()?RAMBLEY_TEETH_COLOR:RAMBLEY_MOUTH_COLOR);
                // Fill in the area for Rambley's open mouth
            g.fill(openMouth);
                // Create a copy of the graphics context to draw the inner mouth
            Graphics2D gMouth = (Graphics2D) g.create();
                // Clip the graphics context to the open mouth
            gMouth.clip(openMouth);
                // If Rambley's jaw is closed
            if (isRambleyJawClosed()){
                    // Create the path to use to draw the line that separates 
                    // the upper and lower teeth.
                path = getRambleyClosedTeeth(mouthWidth, mouthHeight, 
                        mouthCurve1,mouthCurve2,mouthPath,quadCurve2,openMouth,
                        point1,quadCurve1,path);
                    // Draw the line that separates the top and bottom of 
                    // Rambley's jaw
                gMouth.setColor(RAMBLEY_TEETH_LINE_COLOR);
                gMouth.draw(path);
            } else {
                    // Create the shape of Rambley's tongue
                Area tongue = getRambleyTongue(rect.getCenterX(),
                        mouthCurve2.getX2(),rect.getMaxY(),mouthWidth, 
                        mouthHeight, openMouth,ellipse1);
                    // Create the shape of Rambley's right fang
                Area fang = getRambleyFangs(mouthWidth, mouthHeight, 
                        mouthCurve1,mouthCurve2,openMouth,quadCurve1,path);
                    // Fill in Rambley's tongue
                gMouth.setColor(RAMBLEY_TONGUE_COLOR);
                gMouth.fill(tongue);
                    // Draw the outline for Rambley's tongue
                gMouth.setColor(RAMBLEY_TONGUE_LINE_COLOR);
                gMouth.draw(tongue);
                    // Fill in Rambley's fang(s)
                gMouth.setColor(RAMBLEY_TEETH_COLOR);
                gMouth.fill(fang);
                    // Draw the outline for Rambley's fang(s)
                gMouth.setColor(RAMBLEY_TEETH_LINE_COLOR);
                gMouth.draw(fang);
            }   // Dispose of the mouth graphics context
            gMouth.dispose();
                // Set the stroke to Rambley's detail stroke
            g.setStroke(getRambleyDetailStroke());
                // Draw Rambley's mouth
            g.setColor(RAMBLEY_MOUTH_LINE_COLOR);
            g.draw(openMouth);
        }   // Set the stroke to Rambley's detail stroke
        g.setStroke(getRambleyDetailStroke());
            // Draw Rambley's mouth
        g.setColor(RAMBLEY_MOUTH_LINE_COLOR);
        g.draw(mouthPath);
            // Set the stroke to Rambley's normal stroke
        g.setStroke(getRambleyNormalStroke());
            // Draw Rambley's nose
        g.setColor(RAMBLEY_NOSE_COLOR);
        g.fill(nose);
            // Set the stroke to Rambley's detail stroke
        g.setStroke(getRambleyDetailStroke());
            // Set the color to the line color for Rambley's nose
        g.setColor(RAMBLEY_NOSE_LINE_COLOR);
            // Draw the outline for Rambley's nose
        g.draw(nose);
            // Dispose of the copy of the graphics context
        g.dispose();
    }
    /**
     * This is used to render 
     * @param g The graphics context to render to.
     * @param scarfArea
     * @param scarfCurve 
     */
    protected void paintRambleyNeckScarf(Graphics2D g, Area scarfArea, 
            CubicCurve2D scarfCurve){
            // Calculate the path containing the details of the scarf
        path = getRambleyNeckScarfDetail(scarfCurve,cubicCurve1,cubicCurve2,
                path);
            // Create a copy of the graphics context to draw the scarf
        g = (Graphics2D) g.create();
            // Set the color to be the color for Rambley's scarf
        g.setColor(RAMBLEY_SCARF_COLOR);
            // Fill in the neck of Rambley's scarf
        g.fill(scarfArea);
            // Set the color to be the color for the outline of Rambley's scarf
        g.setColor(RAMBLEY_SCARF_LINE_COLOR);
            // Set the stroke to the detail stroke
        g.setStroke(getRambleyDetailStroke());
            // Draw the path containing the details of the scarf
        g.draw(path);
            // Set the stroke to the line stroke
        g.setStroke(getRambleyLineStroke());
            // Draw the outline of the scarf
        g.draw(scarfArea);
            // Dispose of the scarf graphics context
        g.dispose();
    }
    
    protected void paintRambleyScarfEnd(Graphics2D g, Area scarfEnd, 
            Path2D scarfEndDetail){
        // Temporary null check
        if (scarfEnd != null){
                // Create a copy of the graphics context to draw the scarf
            g = (Graphics2D) g.create();
                // Set the color to be the color for Rambley's scarf
            g.setColor(RAMBLEY_SCARF_COLOR);
                // Fill the area for the current end of Rambley's scarf
            g.fill(scarfEnd);
                // Set the color to be the color for the outline of Rambley's scarf
            g.setColor(RAMBLEY_SCARF_LINE_COLOR);
            // Temporary null check
            if (scarfEndDetail != null){
                    // Set the stroke to the detail stroke
                g.setStroke(getRambleyDetailStroke());
                    // Draw the details for the current end of Rambley's scarf
                g.draw(scarfEndDetail);
            }
                // Set the stroke to the line stroke
            g.setStroke(getRambleyLineStroke());
                // Draw the outline for current end of Rambley's scarf
            g.draw(scarfEnd);
                // Dispose of the scarf graphics context
            g.dispose();
        }
    }
    
    protected void paintRambleyScarfBack(Graphics2D g, Area scarfKnot, 
            Area scarfEndUpper, Path2D scarfEndUpperDetail, 
            Area scarfEndLower){
            // Create the details for the lower end of Rambley's scarf
        path = getRambleyLowerScarfEndDetail(scarfEndLower,path,quadCurve1,point1);
            // Paint the lower end of Rambley's scarf
        paintRambleyScarfEnd(g,scarfEndLower,path);
            // Paint the upper end of Rambley's scarf
        paintRambleyScarfEnd(g,scarfEndUpper,scarfEndUpperDetail);
        // Temporary null check
        if (scarfKnot != null){
                // Create a copy of the graphics context to draw the scarf knot
            g = (Graphics2D) g.create();
                // Set the color to be the color for Rambley's scarf
            g.setColor(RAMBLEY_SCARF_COLOR);
                // Fill the area for the knot of Rambley's scarf
            g.fill(scarfKnot);
                // Set the color to be the color for the outline of Rambley's scarf
            g.setColor(RAMBLEY_SCARF_LINE_COLOR);
                // Set the stroke to the line stroke
            g.setStroke(getRambleyLineStroke());
                // Draw the outline for knot of Rambley's scarf
            g.draw(scarfKnot);
                // Dispose of the scarf graphics context
            g.dispose();
        }
    }
    
    
    
    
    
    /**
     * This returns a BasicStroke object to use for rendering the border around 
     * Rambley. This stroke has a line width of 2 * {@link 
     * RAMBLEY_OUTLINE_THICKNESS} (in order to have the visible section to be 
     * {@code RAMBLEY_OUTLINE_THICKNESS}), the ends of the lines will be {@link 
     * BasicStroke#CAP_ROUND rounded}, and points where paths meet will be 
     * {@link BasicStroke#JOIN_ROUND rounded}.
     * @return The border stroke used for drawing the border around Rambley.
     * @see #getRambleyStroke
     * @see getRambleyNormalStroke
     * @see getRambleyDetailStroke
     * @see getRambleyLineStroke
     * @see RAMBLEY_OUTLINE_THICKNESS
     * @see BasicStroke#CAP_ROUND
     * @see BasicStroke#JOIN_ROUND
     */
    protected BasicStroke getRambleyOutlineStroke(){
            // If the border stroke for Rambley has not been initialized yet
        if (borderStroke == null)
            borderStroke = getRambleyStroke(RAMBLEY_OUTLINE_THICKNESS*2);
        return borderStroke;
    }
    /**
     * This returns an AffineTransform object that translates shapes to become a 
     * drop shadow.
     * @param tx An AffineTransform to store the results in, or null.
     * @return An AffineTransform used to turn shapes into drop shadows.
     * @see #paintRambleyOutlineAndShadow 
     * @see #paintRambley
     * @see getDropShadow
     * @see RAMBLEY_DROP_SHADOW_X_OFFSET
     * @see RAMBLEY_DROP_SHADOW_Y_OFFSET
     * @see #isRambleyShadowPainted 
     * @see #setRambleyShadowPainted 
     */
    protected AffineTransform getDropShadowTransform(AffineTransform tx){
            // If the given AffineTransform is null
        if (tx == null)
                // Return an AffineTransform to translate shapes 
            return AffineTransform.getTranslateInstance(
                    RAMBLEY_DROP_SHADOW_X_OFFSET, RAMBLEY_DROP_SHADOW_Y_OFFSET);
            // Set the AffineTransform to one that translate shapes 
        tx.setToTranslation(RAMBLEY_DROP_SHADOW_X_OFFSET, 
                RAMBLEY_DROP_SHADOW_Y_OFFSET);
        return tx;
    }
    /**
     * This creates and returns an Area that serves as a drop shadow of the 
     * given area.
     * @param area The area to get the drop shadow of.
     * @return An area that represents the drop shadow of the given area.
     * @see #paintRambleyOutlineAndShadow 
     * @see #paintRambley
     * @see #getDropShadowTransform 
     * @see RAMBLEY_DROP_SHADOW_X_OFFSET
     * @see RAMBLEY_DROP_SHADOW_Y_OFFSET
     * @see #isRambleyShadowPainted 
     * @see #setRambleyShadowPainted 
     */
    protected Area getDropShadow(Area area){
            // Get the AffineTransform for creating the drop shadow
        afTx = getDropShadowTransform(afTx);
            // Transform the area to get the drop shadow
        return area.createTransformedArea(afTx);
    }
    /**
     * This is used to render the outline around Rambley the Raccoon and his 
     * drop shadow. If {@link #isRambleyShadowPainted Rambley's shadow is 
     * painted}, then this will first draw the drop shadow using the given 
     * {@code rambleyShape} shifted down and in the {@link 
     * RAMBLEY_DROP_SHADOW_COLOR color for the shadow}. If {@link 
     * #isRambleyOutlinePainted Rambley's outline is painted}, then this will 
     * draw the outline of the given {@code rambleyShape} with a thick line 
     * width in the {@link RAMBLEY_OUTLINE_COLOR outline color} to draw the 
     * outline. This is expected to be rendered before Rambley is rendered. This 
     * renders to a copy of the given graphics context, so as to protect the 
     * rest of the paint code from changes made to the graphics context while 
     * rendering the outline and shadow.
     * @param g The graphics context to render to.
     * @param rambleyShape The Area that forms the outline of Rambley.
     * @see #paintRambley
     * @see #getDropShadow 
     * @see #getDropShadowTransform 
     * @see RAMBLEY_DROP_SHADOW_COLOR
     * @see RAMBLEY_OUTLINE_COLOR
     * @see RAMBLEY_DROP_SHADOW_X_OFFSET
     * @see RAMBLEY_DROP_SHADOW_Y_OFFSET
     * @see getRambleyOutlineStroke 
     * @see #isRambleyOutlinePainted 
     * @see #setRambleyOutlinePainted 
     * @see #isRambleyShadowPainted 
     * @see #setRambleyShadowPainted 
     */
    protected void paintRambleyOutlineAndShadow(Graphics2D g,Area rambleyShape){
            // If neither Rambley's outline nor shadow will be painted
        if (!isRambleyOutlinePainted() && !isRambleyShadowPainted())
            return;
            // Create a copy of the given graphics context
        g = (Graphics2D) g.create();
            // If Rambley's outline should be painted
        if (isRambleyOutlinePainted())
                // Set the stroke to use to the outline stroke
            g.setStroke(getRambleyOutlineStroke());
            // If Rambley's shadow should be painted
        if (isRambleyShadowPainted()){
                // Get the area to use to draw Rambley's drop shadow
            Area shadow = getDropShadow(rambleyShape);
                // Fill the area for Rambley's drop shadow
            g.setColor(RAMBLEY_DROP_SHADOW_COLOR);
            g.fill(shadow);
                // Draw the drop shadow so as to match the thickness of the border
            g.draw(shadow);
        }   // If Rambley's outline should be painted
        if (isRambleyOutlinePainted()){
                // Fill the area for the outline around rambley
            g.setColor(RAMBLEY_OUTLINE_COLOR);
            g.draw(rambleyShape);
        }   // Dispose of the copy of the graphics context
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
                ",gridSpacing="+getPixelGridLineSpacing()+
                ",gridThickness="+getPixelGridLineThickness()+
                ",rightEye=("+getRambleyRightEyeX()+","+getRambleyRightEyeY()+")"+
                ",leftEye=("+getRambleyLeftEyeX()+","+getRambleyLeftEyeY()+")"+
                ",mouthOpen="+getRambleyOpenMouthWidth()+"x"+
                    getRambleyOpenMouthHeight();
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
    
    
    
        // Some debug settings that will be removed when finished
    /**
     * This returns whether the debug lines should be shown instead of Rambley. 
     * This is a debug value and should not be relied upon except during 
     * debugging, and will be removed at a later date.
     * @return Whether lines should be shown instead of Rambley.
     */
    boolean getShowsLines(){
        return false;
    }
    /**
     * This returns whether to draw an alternate version of something. What 
     * exactly this changes will depend on what is currently being tested. This 
     * is a debug value and should not be relied upon except during debugging, 
     * and will be removed at a later date.
     * @return Whether to draw an alternate version of something being tested.
     */
    boolean getABTesting(){
        return false;
    }
    /**
     * This is the first test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble1(){
        return 0.0;
    }
    /**
     * This is the second test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble2(){
        return 0.0;
    }
    /**
     * This is the third test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble3(){
        return 0.0;
    }
    /**
     * This is the fourth test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble4(){
        return 0.0;
    }
    /**
     * This is the fifth test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble5(){
        return 0.0;
    }
    /**
     * This is the sixth test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble6(){
        return 0.0;
    }
    /**
     * This is the seventh test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble7(){
        return 0.0;
    }
    /**
     * This is the eigth test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble8(){
        return 0.0;
    }
    /**
     * This is the ninth test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble9(){
        return 0.0;
    }
    /**
     * This is the tenth test double value used to dial in a value to use for 
     * drawing Rambley. What exactly this value is used depends on what is 
     * currently being worked on. This is a debug value and should not be relied 
     * upon except during debugging, and will be removed at a later date.
     * @return A double value used for testing.
     */
    double getTestDouble10(){
        return 0.0;
    }
    /**
     * This calculates a value to use to derive a control point for a curve for 
     * testing purposes. This is a debug method and should not be relied upon 
     * upon except during debugging, and will be removed at a later date.
     * @param x1 The start of the curve.
     * @param x2 The end of the curve.
     * @param d1 How much the start of the curve should impact the control 
     * point.
     * @param d2 How much the end of the curve should impact the control 
     * point.
     * @return A value to use for the control point of a curve.
     */
    double getTestCtrlValue(double x1, double x2, double d1, double d2){
            // If the control values are both zero (prevents a divide by zero)
        if (d1 == 0 && d2 == 0)
            return 0;
        return (x1*d1+x2*d2)/(d1+d2);
    }
    /**
     * This calculates a value to use as the x coordinate of the control point 
     * of a curve. The value will be between {@code x1} and {@code x2}, and 
     * which side will be favored is controlled using the first and second test 
     * doubles.
     * @param x1 The x-coordinate of the start of the curve.
     * @param x2 The x-coordinate of the end of the curve.
     * @return The x-coordinate for the control point of the curve.
     */
    double getTestCtrlX(double x1, double x2){
        return getTestCtrlValue(x1,x2,getTestDouble1(),getTestDouble2());
    }
    /**
     * This calculates a value to use as the y coordinate of the control point 
     * of a curve. The value will be between {@code y1} and {@code y2}, and 
     * which side will be favored is controlled using the third and fourth test 
     * doubles.
     * @param y1 The y-coordinate of the start of the curve.
     * @param y2 The y-coordinate of the end of the curve.
     * @return The y-coordinate for the control point of the curve.
     */
    double getTestCtrlY(double y1, double y2){
        return getTestCtrlValue(y1,y2,getTestDouble3(),getTestDouble4());
    }
}
