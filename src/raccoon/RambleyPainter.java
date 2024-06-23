/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import java.awt.*;
import java.awt.geom.*;
import java.util.EventListener;
import java.util.function.DoubleUnaryOperator;
import javax.swing.*;
import javax.swing.event.*;

/**
 * 
 * Special thanks to AnimalWave on Discord for help with the equations that the 
 * code is based off of.
 * 
 * @author Milo Steier
 * @author AnimalWave on Discord
 */
public class RambleyPainter implements Painter<Component>{
    /*
    I'm thinking that RambleyIcon should be 256x256, with Rambley himself being 
    about 240 pixels tall with an 8 pixel gap above and below him. The background 
    should be the one from his screens, and it should be toggleable. The pixel 
    effect should be toggleable. The icon MAY have different states that Rambley 
    can be in.
    */
    
    protected static final int INTERNAL_RENDER_WIDTH = 256;
    
    protected static final int INTERNAL_RENDER_HEIGHT = 256;
    
    public static final Color BACKGROUND_COLOR = new Color(0x2CDFFF);
    
    public static final Color BACKGROUND_DOT_COLOR = new Color(0x1A73C9);
    
    public static final Color BACKGROUND_GRADIENT_COLOR = new Color(0x0068FF);
    
    protected static final int BACKGROUND_DOT_SIZE = 8;
    
    protected static final int BACKGROUND_DOT_HALF_SIZE = 
            Math.floorDiv(BACKGROUND_DOT_SIZE,2);
    
    protected static final int BACKGROUND_DOT_SPACING = 12;
    
    protected static final int BACKGROUND_DOT_POINTS = 4;
    /**
     * 
     */
    public static final Color PIXEL_GRID_COLOR = new Color(0x60000000,true);
    
    protected static final int PIXEL_GRID_SPACING = 5;
    
    
    
    public static final Color RAMBLEY_MAIN_BODY_COLOR = new Color(0xA591AE);
    
    public static final Color RAMBLEY_SECONDARY_BODY_COLOR = new Color(0xEBDEE0);
    
    public static final Color RAMBLEY_STRIPE_COLOR = Color.BLACK;
    
    public static final Color RAMBLEY_FACE_MARKINGS_COLOR = RAMBLEY_STRIPE_COLOR;
    
    public static final Color RAMBLEY_OUTLINE_COLOR = new Color(0x624361);
    
    public static final Color RAMBLEY_PAW_COLOR = new Color(0x161311);
    
    public static final Color RAMBLEY_PAW_OUTLINE_COLOR = RAMBLEY_OUTLINE_COLOR;
    
    public static final Color RAMBLEY_NOSE_COLOR = RAMBLEY_PAW_COLOR;
    
    public static final Color RAMBLEY_NOSE_OUTLINE_COLOR = RAMBLEY_OUTLINE_COLOR;
    
    public static final Color RAMBLEY_EYE_WHITE_COLOR = Color.WHITE;
    
    public static final Color RAMBLEY_EYE_OUTLINE_COLOR = Color.BLACK;
    
    public static final Color RAMBLEY_IRIS_COLOR = new Color(0x883EC1);
    
    public static final Color RAMBLEY_IRIS_OUTLINE_COLOR = Color.BLACK;//new Color(0x23163F);
    
    public static final Color EVIL_RAMBLEY_IRIS_COLOR = Color.RED;
    
    public static final Color EVIL_RAMBLEY_IRIS_OUTLINE_COLOR = RAMBLEY_IRIS_OUTLINE_COLOR;
    
    public static final Color RAMBLEY_PUPIL_COLOR = Color.WHITE;
    
    public static final Color RAMBLEY_EYEBROW_COLOR = new Color(0x60325D);
    
    public static final Color RAMBLEY_MOUTH_COLOR = new Color(0x3D0D2F);
    
    public static final Color RAMBLEY_TEETH_COLOR = Color.WHITE;
    
    public static final Color RAMBLEY_MOUTH_OUTLINE_COLOR = RAMBLEY_OUTLINE_COLOR;
    
    public static final Color RAMBLEY_TONGUE_COLOR = new Color(0x724794);
    
    public static final Color RAMBLEY_TONGUE_OUTLINE_COLOR = RAMBLEY_TONGUE_COLOR;
    
    
    
    public static final Color RAMBLEY_SCARF_COLOR = new Color(0xC64C57);
    
    public static final Color RAMBLEY_SCARF_OUTLINE_COLOR = new Color(0xA63442);
    
    
    
    public static final Color RAMBLEY_CONDUCTOR_HAT_COLOR = new Color(0x431188);
    
    public static final Color RAMBLEY_CONDUCTOR_HAT_STRIPE_COLOR = new Color(0xF3E5FE);
    
    public static final Color RAMBLEY_CONDUCTOR_HAT_OUTLINE_COLOR = Color.BLACK;
    
    
    
    public static final Color RAMBLEY_BORDER_COLOR = Color.WHITE;
    
    public static final Color RAMBLEY_DROP_SHADOW_COLOR = Color.BLACK;
    
    
    
    public static final int PAINT_BACKGROUND_FLAG = 0x00000001;
    
    public static final int PAINT_PIXEL_GRID_FLAG = 0x00000002;
    
    public static final int EVIL_RAMBLEY_FLAG = 0x00000004;
    
    public static final int IGNORE_ASPECT_RATIO_FLAG = 0x00000008;
    
    
    
    
    
    
    public static final int A_B_TESTING_FLAG = 0x40000000;
    
    public static final int SHOW_LINES_FLAG = 0x80000000;
    
    
    
    
    private static final int DEFAULT_FLAG_SETTINGS = PAINT_BACKGROUND_FLAG;// | PAINT_PIXEL_GRID_FLAG;
    
    
    
    
    
    
    private int flags;
    
    protected EventListenerList listenerList = new EventListenerList();
    
    private Path2D pixelGrid = null;
    
    private BasicStroke normalStroke = null;
    
    private BasicStroke eyeStroke = null;
    
    private BasicStroke outlineStroke = null;
    
    private Ellipse2D iris = null;
    
    private Ellipse2D pupil = null;
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
    }
    /**
     * This returns an integer storing the flags used to store the settings for 
     * this snake and control its state.
     * @return An integer containing the flags for this snake.
     * @see #getFlag
     * @see #setFlag
     * @see #toggleFlag
     * @see #APPLE_CONSUMED_FLAG
     * @see #APPLE_CONSUMPTION_ENABLED_FLAG
     * @see #APPLE_GROWTH_ENABLED_FLAG
     * @see #WRAP_AROUND_ENABLED_FLAG
     * @see #PLAYER_TYPE_FLAG
     * @see #FLIPPED_FLAG
     * @see #CRASHED_FLAG
     * @see #DEFAULT_ACTION_ENABLED_FLAG
     * @see #SKIPS_REPEATED_ACTIONS_FLAG
     */
    public int getFlags(){
        return flags;
    }
    /**
     * This gets whether the given flag is set for this snake.
     * @param flag The flag to check for.
     * @return Whether the flag is set.
     * @see #getFlags
     * @see #setFlag
     * @see #toggleFlag
     * @see SnakeUtilities#getFlag 
     * @see #APPLE_CONSUMED_FLAG
     * @see #APPLE_CONSUMPTION_ENABLED_FLAG
     * @see #APPLE_GROWTH_ENABLED_FLAG
     * @see #WRAP_AROUND_ENABLED_FLAG
     * @see #PLAYER_TYPE_FLAG
     * @see #FLIPPED_FLAG
     * @see #CRASHED_FLAG
     * @see #DEFAULT_ACTION_ENABLED_FLAG
     * @see #SKIPS_REPEATED_ACTIONS_FLAG
     */
    public boolean getFlag(int flag){
        return (flags & flag) == flag;
    }
    /**
     * This sets whether the given flag is set for this snake based off the 
     * given value. This returns {@code true} if this snake changed as a result 
     * of the call, and {@code false} if no change is made.
     * @param flag The flag to be set or cleared based off {@code value}.
     * @param value Whether the flag should be set or cleared.
     * @return Whether this snake changed as a result of the call.
     * @see #getFlags 
     * @see #getFlag 
     * @see #toggleFlag 
     * @see SnakeUtilities#setFlag 
     * @see #APPLE_CONSUMED_FLAG
     * @see #APPLE_CONSUMPTION_ENABLED_FLAG
     * @see #APPLE_GROWTH_ENABLED_FLAG
     * @see #WRAP_AROUND_ENABLED_FLAG
     * @see #PLAYER_TYPE_FLAG
     * @see #FLIPPED_FLAG
     * @see #CRASHED_FLAG
     * @see #DEFAULT_ACTION_ENABLED_FLAG
     * @see #SKIPS_REPEATED_ACTIONS_FLAG
     */
    public boolean setFlag(int flag, boolean value){
        int old = flags;    // Get the old value for the flags
            // If the flag is to be set, OR the flags with the flag. Otherwise, 
            // AND the flags with the inverse of the flag.
        flags = (value) ? flags | flag : flags & ~flag;
        return fireFlagChange(old);
    }
    
    private boolean fireFlagChange(int old){
        boolean change = flags != old;
        if (change)
            fireStateChanged();
        return change;
    }
    /**
     * This toggles whether the given flag is set for this snake. This returns 
     * {@code true} if this snake changed as a result of the call, and {@code 
     * false} if no change is made. 
     * @param flag The flag to be toggled.
     * @return Whether this snake changed as a result of the call.
     * @see #getFlags 
     * @see #getFlag 
     * @see #setFlag 
     * @see SnakeUtilities#toggleFlag 
     * @see #APPLE_CONSUMED_FLAG
     * @see #APPLE_CONSUMPTION_ENABLED_FLAG
     * @see #APPLE_GROWTH_ENABLED_FLAG
     * @see #WRAP_AROUND_ENABLED_FLAG
     * @see #PLAYER_TYPE_FLAG
     * @see #FLIPPED_FLAG
     * @see #CRASHED_FLAG
     * @see #DEFAULT_ACTION_ENABLED_FLAG
     * @see #SKIPS_REPEATED_ACTIONS_FLAG
     */
    public boolean toggleFlag(int flag){
        int old = flags;    // Get the old value for the flags
        flags = flags ^ flag;
        return fireFlagChange(old);
    }
    
    
    
    protected boolean getShowsLines(){
        return getFlag(SHOW_LINES_FLAG);
    }
    
    protected void setShowsLines(boolean value){
        setFlag(SHOW_LINES_FLAG,value);
    }
    
    protected boolean getABTesting(){
        return getFlag(A_B_TESTING_FLAG);
    }
    
    protected void setABTesting(boolean value){
        setFlag(A_B_TESTING_FLAG,value);
    }
    
    
    
    public boolean isBackgroundPainted(){
        return getFlag(PAINT_BACKGROUND_FLAG);
    }
    
    public void setBackgroundPainted(boolean enabled){
        setFlag(PAINT_BACKGROUND_FLAG,enabled);
    }
    
    public boolean isPixelGridPainted(){
        return getFlag(PAINT_PIXEL_GRID_FLAG);
    }
    
    public void setPixelGridPainted(boolean enabled){
        setFlag(PAINT_PIXEL_GRID_FLAG,enabled);
    }
    
    public boolean isRambleyEvil(){
        return getFlag(EVIL_RAMBLEY_FLAG);
    }
    
    public void setRambleyEvil(boolean enabled){
        setFlag(EVIL_RAMBLEY_FLAG,enabled);
    }
    
    public boolean isAspectRatioIgnored(){
        return getFlag(IGNORE_ASPECT_RATIO_FLAG);
    }
    
    public void setAspectRatioIgnored(boolean enabled){
        setFlag(IGNORE_ASPECT_RATIO_FLAG,enabled);
    }
    
    
    
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
    
    protected BasicStroke getRambleyStroke(float width){
        return new BasicStroke(width,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    }
    
    protected BasicStroke getRambleyNormalStroke(){
        if (normalStroke == null)
            normalStroke = getRambleyStroke(1.0f);
        return normalStroke;
    }
    
    protected BasicStroke getRambleyEyeStroke(){
        if (eyeStroke == null)
            eyeStroke = getRambleyStroke(2.0f);
        return eyeStroke;
    }
    
    protected BasicStroke getRambleyOutlineStroke(){
        if (outlineStroke == null)
            outlineStroke = getRambleyStroke(3.0f);
        return outlineStroke;
    }
    
    protected double getRadius(Ellipse2D c){
        return (c.getWidth() + c.getHeight()) / 4.0;
    }
    
    protected boolean getCircleIntersections(Ellipse2D c0, Ellipse2D c1, Point2D p0, Point2D p1){
        p0.setLocation(c0.getCenterX(), c0.getCenterY());
        p1.setLocation(c1.getCenterX(), c1.getCenterY());
        double r0 = getRadius(c0);
        double r1 = getRadius(c1);
        double d = p0.distance(p1);
        if (d > (r0 + r1) || d < Math.abs(r0 - r1) || (d == 0 && r0 == r1)){
            p0.setLocation(-1, -1);
            p1.setLocation(p0);
            return false;
        }
        double r0s = Math.pow(r0, 2);
        double a = (r0s - Math.pow(r1, 2) + p0.distanceSq(p1)) / (2 * d);
        double h = Math.sqrt(r0s - Math.pow(a, 2));
        double dx = p1.getX() - p0.getX();
        double dy = p1.getY() - p0.getY();
        double x2 = p0.getX() + (a * dx) / d;
        double y2 = p0.getY() + (a * dy) / d;
        double rx = (h * dy) / d;
        double ry = (h * dx) / d;
        p0.setLocation(x2 + rx, y2 - ry);
        p1.setLocation(x2 - rx, y2 + ry);
        return true;
    }
    
    protected void getCircleIntersections(Ellipse2D e, double x1, double y1, 
            double x2, double y2, Point2D point1, Point2D point2){
            // Function breaks for vertical lines
        if (x1 == x2){
            if (y1 == y2)
                return;
                // Rotate everything by 90 degrees.
                // Bam! The vertical line is now a horizontal line
            e.setFrame(e.getY(),e.getX(),e.getHeight(),e.getWidth());
                // Calculate the points of intersection for the horizontal line
            getCircleIntersections(e,y1,x1,y2,x2,point1,point2);
                // Rotate everything back
            point1.setLocation(point1.getY(), point1.getX());
            point2.setLocation(point2.getY(), point2.getX());
            e.setFrame(e.getY(),e.getX(),e.getHeight(),e.getWidth());
            return;
        }
        double r1 = y2 - y1;
        double r2 = x2 - x1; 
        double k = r1 / r2;
        double m = y2 - k * x2;
        double a = Math.pow(e.getWidth()/2, 2);
        double b = Math.pow(e.getHeight()/2, 2);
        double A1 = 1/a + Math.pow(k, 2)/b;
        double B1 = (2*k*(m-e.getCenterY()))/b - (2*e.getCenterX())/a;
        double C1 = Math.pow(m-e.getCenterY(), 2)/b - 1 + Math.pow(e.getCenterX(), 2)/a;
        double D = Math.sqrt(Math.pow(B1, 2) - 4*A1*C1);
        A1 *= 2;
        double q1 = (-B1 - D)/A1;
        double q2 = (-B1 + D)/A1;
        point1.setLocation(q1, k*q1+m);
        point2.setLocation(q2, k*q2+m);
    }
    
    protected void getCircleIntersections(Ellipse2D e, Point2D p1, Point2D p2, 
            Point2D point1, Point2D point2){
        getCircleIntersections(e,p1.getX(),p1.getY(),p2.getX(),p2.getY(),point1,point2);
    }
    
    protected double getBezierPoint(double x0, double x1, double x2, double t){
        return (Math.pow(1-t, 2) * x0) + (2 * t * (1-t)*x1) + (Math.pow(t, 2)*x2);
    }
    
    protected Point2D getBezierPoint(Point2D p0, Point2D p1, Point2D p2, double t, Point2D point){
        if (point == null)
            point = new Point2D.Double();
        point.setLocation(getBezierPoint(p0.getX(),p1.getX(),p2.getX(),t),
                getBezierPoint(p0.getY(),p1.getY(),p2.getY(),t));
        return point;
    }
    
    protected Point2D getBezierPoint(Point2D p0, Point2D p1, Point2D p2, double t){
        return getBezierPoint(p0,p1,p2,t,null);
    }
    
    protected double[] getBezierT(double x0, double x1, double x2, double x){
        double a = x0 - (2 * x1) + x2;
        double b = (-2 * x0) + (2 * x1);
        double c = x0 - x;
        double d = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        double a2 = 2 * a;
        double t0 = (-b + d)/a2;
        double t1 = (-b - d)/a2;
        if (t0 >= 0 && t0 <= 1 && t1 >= 0 && t1 <= 1){
            return new double[]{t0,t1};
        } else if (t0 >= 0 && t0 <= 1){
            return new double[]{t0};
        } else if (t1 >= 0 && t1 <= 1){
            return new double[]{t1};
        }
        return new double[]{};
    }
    
    protected double getBezierControlPoint(double x0, double x1, double x2, double t){
        x0 = Math.pow(1-t, 2) * x0;
        x2 = Math.pow(t, 2)*x2;
        double b = 2 * t * (1-t);
        return (x1 - x0 - x2) / b;
    }
    
    protected Point2D getBezierControlPoint(Point2D p0, Point2D p1, Point2D p2, double t, Point2D point){
        if (point == null)
            point = new Point2D.Double();
        point.setLocation(getBezierControlPoint(p0.getX(),p1.getX(),p2.getX(),t),
                getBezierControlPoint(p0.getY(),p1.getY(),p2.getY(),t));
        return point;
    }

    @Override
    public void paint(Graphics2D g, Component c, int width, int height) {
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
        return g;
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
    
    protected void setRambleyEyeLocation(double x, double y, Ellipse2D iris, 
            Ellipse2D pupil){
        iris.setFrameFromCenter(x,y,x+12,y+12);
        pupil.setFrameFromCenter(iris.getCenterX(), iris.getCenterY(), 
                iris.getCenterX()+5, iris.getCenterY()+5);
    }
    
    protected void setRambleyEyeLocation(double x, double y){
        if (iris == null)
            iris = new Ellipse2D.Double();
        if (pupil == null)
            pupil = new Ellipse2D.Double();
        setRambleyEyeLocation(x,y,iris,pupil);
    }
    
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
        tempG.setStroke(getRambleyEyeStroke());
        tempG.draw(iris);
        tempG.draw(pupil);
        tempG.dispose();
        Stroke s = g.getStroke();
        g.setStroke(getRambleyOutlineStroke());
        g.setColor(RAMBLEY_EYE_OUTLINE_COLOR);
        g.draw(eyeWhite);
        g.setStroke(s);
    }
    
    protected void paintRambleyEye(Graphics2D g,Shape eyeWhite,double x, double y){
        setRambleyEyeLocation(x,y);
        paintRambleyEye(g,eyeWhite,iris,pupil);
    }
    
    private double getRambleyEarAdjustY1(double y){
        y = RAMBLEY_EAR_HEIGHT - y;
        y /= RAMBLEY_EAR_MULTIPLIER;
        return y + RAMBLEY_EAR_Y_OFFSET;
    }
    
    private double getRambleyEarAdjustY2(double y){
        y -= RAMBLEY_EAR_Y_OFFSET;
        y *= RAMBLEY_EAR_MULTIPLIER;
        return (RAMBLEY_EAR_HEIGHT-y);
    }
    
    private double getRambleyEarAdjustX1(double x){
        return (RAMBLEY_EAR_X_OFFSET-x)*RAMBLEY_EAR_MULTIPLIER;
    }
    
    private double getRambleyEarAdjustX2(double x){
        x /= RAMBLEY_EAR_MULTIPLIER;
        return RAMBLEY_EAR_X_OFFSET-x;
    }
    
    private static double getRambleyEarUpperY1(double x){
        return -((Math.log(1.5-x)/Math.log(2))-5.2)/8;
    }
    
    private double getRambleyEarUpperY(double x){
        x = getRambleyEarAdjustX2(x);
        return getRambleyEarAdjustY2(getRambleyEarUpperY1(x));
    }
    /**
     * 2^(10y-23)
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param y
     * @return 
     */
    private double getRambleyEarUpperX(double y){
        y = getRambleyEarAdjustY1(y);
        return getRambleyEarAdjustX1(Math.pow(2, 10*y-23));
    }
    
    private double getRambleyEarLowerY(double x){
        x = getRambleyEarAdjustX2(x);
        return getRambleyEarAdjustY2((Math.log(x)/(10*Math.log(2)))+2.3);
    }
    /**
     * -2^(-8y+5.2) + 1.5
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param y
     * @return 
     */
    private double getRambleyEarLowerX(double y){
        y = getRambleyEarAdjustY1(y);
        return getRambleyEarAdjustX1(-Math.pow(2, -8*y+5.2)+1.5);
    }
    /**
     * (0.01/(y-2.4)) + 1.5
     * 
     * Thank you AnimalWave on Discord
     * 
     * @param y
     * @return 
     */
    private double getRambleyEarTipX(double y){
        y -= getTestDouble6();
        y = getRambleyEarAdjustY1(y) - 2.4;
        if (y >= 0)
            return getRambleyEarAdjustX1(0);
        return getRambleyEarAdjustX1((0.01/y)+1.5);
    }
    
    private static final double RAMBLEY_EAR_X_OFFSET = 1.5;
    
    private static final double RAMBLEY_EAR_Y_OFFSET = getRambleyEarUpperY1(0);
    
    private static final double RAMBLEY_EAR_Y_OFFSET_2 = 1.5;
    
    private static final double RAMBLEY_EAR_MULTIPLIER = 42;
//    
//    private static final double RAMBLEY_EAR_WIDTH = RAMBLEY_EAR_X_OFFSET * RAMBLEY_EAR_MULTIPLIER;
//    
    private static final double RAMBLEY_EAR_HEIGHT = 1.8 * RAMBLEY_EAR_MULTIPLIER;
    
//    private static final double RAMBLEY_EAR_HEIGHT = 75;
//    
//    private static final double RAMBLEY_EAR_MULTIPLIER = RAMBLEY_EAR_HEIGHT / 1.2;
    
    
    private void addRambleyEarPoint(double y, Path2D upper, Path2D lower, Path2D tip, double xOff, double yOff){
        double y2 = y + yOff;
        if (upper != null){
            double upperX = getRambleyEarUpperX(y) + xOff;
            if (upper.getCurrentPoint() == null)
                upper.moveTo(upperX, y2);
            else
                upper.lineTo(upperX, y2);
        }
        if (lower != null){
            double lowerX = getRambleyEarLowerX(y) + xOff;
            if (lower.getCurrentPoint() == null)
                lower.moveTo(lowerX, y2);
            else
                lower.lineTo(lowerX, y2);
        }
        if (tip != null){
            double tipX = getRambleyEarTipX(y) + xOff;
            if (tip.getCurrentPoint() == null)
                tip.moveTo(tipX, y2);
            else
                tip.lineTo(tipX, y2);
        }
    }
    
    private double testDouble1 = 0.025;
    
    protected double getTestDouble1(){
        return testDouble1;
    }
    
    protected void setTestDouble1(double value){
        testDouble1 = value;
        fireStateChanged();
    }
    
    private double testDouble2 = 0.055;
    
    protected double getTestDouble2(){
        return testDouble2;
    }
    
    protected void setTestDouble2(double value){
        testDouble2 = value;
        fireStateChanged();
    }
    
    private double testDouble3 = 0.29;
    
    protected double getTestDouble3(){
        return testDouble3;
    }
    
    protected void setTestDouble3(double value){
        testDouble3 = value;
        fireStateChanged();
    }
    
    private double testDouble4 = 0.72;
    
    protected double getTestDouble4(){
        return testDouble4;
    }
    
    protected void setTestDouble4(double value){
        testDouble4 = value;
        fireStateChanged();
    }
    
    private double testDouble5 = 0.5;
    
    protected double getTestDouble5(){
        return testDouble5;
    }
    
    protected void setTestDouble5(double value){
        testDouble5 = value;
        fireStateChanged();
    }
    
    private double testDouble6 = 1;
    
    protected double getTestDouble6(){
        return testDouble6;
    }
    
    protected void setTestDouble6(double value){
        testDouble6 = value;
        fireStateChanged();
    }
    
    private Path2D getRambleyEarPath(double x, double y, double t1, double t2, 
            double t3, double t4, double t5, Path2D path, DoubleUnaryOperator getX){
        if (path == null)
            path = new Path2D.Double();
        else
            path.reset();
        point1.setLocation(getX.applyAsDouble(0)+x,y);
        double tempY = RAMBLEY_EAR_HEIGHT*t1;
        point2.setLocation(getX.applyAsDouble(tempY)+x, tempY+y);
        tempY = RAMBLEY_EAR_HEIGHT*t2;
        point3.setLocation(getX.applyAsDouble(tempY)+x, tempY+y);
        tempY = RAMBLEY_EAR_HEIGHT*t3;
        point4.setLocation(getX.applyAsDouble(tempY)+x, tempY+y);
        point5.setLocation(getX.applyAsDouble(RAMBLEY_EAR_HEIGHT)+x,RAMBLEY_EAR_HEIGHT+y);
        point6 = getBezierControlPoint(point1,point2,point3,t4,point6);
        point7 = getBezierControlPoint(point3,point4,point5,t5,point7);
        path.moveTo(point1.getX(),point1.getY());
        path.quadTo(point6.getX(),point6.getY(), point3.getX(),point3.getY());
        path.quadTo(point7.getX(), point7.getY(), point5.getX(),point5.getY());
        return path;
    }
    
    private Area getRambleyEar(double x, double y, Path2D upper, Path2D lower, Path2D tip){
        y -= RAMBLEY_EAR_Y_OFFSET_2;
        upper = getRambleyEarPath(x,y,0.08,0.22,0.35,0.49,1/3.0,upper, 
                (double operand) -> getRambleyEarUpperX(operand));
        lower = getRambleyEarPath(x,y,0.39,0.71,0.84,0.39,1/3.0,lower, 
                (double operand) -> getRambleyEarLowerX(operand));
        
        if (getABTesting()){
            tip = getRambleyEarPath(x,y,getTestDouble1(),getTestDouble2(),getTestDouble3(),
                    getTestDouble4(),getTestDouble5(),tip, 
                    (double operand) -> getRambleyEarTipX(operand));
        } else {
            if (tip == null)
                tip = new Path2D.Double();
            else
                tip.reset();
            for (double y2 = 0; y2 <= RAMBLEY_EAR_HEIGHT; y2+=0.5){
                addRambleyEarPoint(y2,null,null,tip, x, y);
            }
            addRambleyEarPoint(RAMBLEY_EAR_HEIGHT,null,null,tip, x, y);
        }
        
        Point2D upperP = upper.getCurrentPoint();
        Point2D lowerP = lower.getCurrentPoint();
        Point2D tipP = tip.getCurrentPoint();
        upper.lineTo(upperP.getX(), y+RAMBLEY_EAR_HEIGHT);
        upper.lineTo(x, y+RAMBLEY_EAR_HEIGHT);
        upper.closePath();
        x = Math.max(upperP.getX(), lowerP.getX());
        lower.lineTo(x, lowerP.getY());
        lower.lineTo(x, y);
        lower.closePath();
        tip.lineTo(x, tipP.getY());
        tip.lineTo(x, y);
        tip.closePath();
        
        Area ear = new Area(upper);
        ear.intersect(new Area(lower));
        ear.intersect(new Area(tip));
        
        return ear;
    }
    
    private Area getRambleyInnerEar(Area ear, double scale, Area head){
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
        double scaleX = ((double)w)/INTERNAL_RENDER_WIDTH;
        double scaleY = ((double)h)/INTERNAL_RENDER_HEIGHT;
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
        double x1 = 27;
        double y1 = 60;
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
        Rectangle2D head1 = new Rectangle2D.Double(x1, y1+84, 200, 92);
        Path2D head1a = new Path2D.Double(head1);
        head1a.lineTo(head1.getMinX(), head1.getMinY());
        double a1 = Math.toRadians(26.57);
        head1a.lineTo(head1.getCenterX(), head1.getMinY()-(Math.tan(a1)*100));
        head1a.lineTo(head1.getMaxX(), head1.getMinY());
        head1a.closePath();
            // Might also be usable for the location of his scarf
        Ellipse2D head2 = new Ellipse2D.Double(head1.getMinX()+24, y1, 152, 176);
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
        
        // Ear/body outline interaction should be more like the original
        
        Path2D earUpper = new Path2D.Double();
        Path2D earLower = new Path2D.Double();
        Path2D earTip = new Path2D.Double();
        double earX = headBounds.getCenterX()-84;
        double earY = head2.getMinY()-30;
        
        // Left Ear Calculations in desmos from AnimalWave:
        // Outer: x = -2^(-8y+5.2) + 2
        //        x = 2^(10y-23) + 0.5
        //        y = (0.01/(x-2)) + 2.4
        //        x = (0.01/(y-2.4)) + 2
        // Outer Bounds: (0.5, 0.5769), (1.9999, 2.3585)
        // Outer Top Curve: (1.9893, 1.4678), (1.8301, 2.3412)
        // Outer Attaches to Head: (0.5147, 1.3699, 0.7333)
        // Inner: x = (-2^(-10y+9.4) + 2)/1.15
        //        x = (2^(14y-30) + 0.5)*1.7
        //        y = (0.01/(x-1.8)) + 2.1
        // Inner Bounds: (0.85, 0.9368), (1.7388, 2.076)
        // Inner Top Curve: (1.5765, 2.0553), (1.7383, 1.938)
        // Inner Attaches to Head: (0.8528, 1.4808), (1.2365, 1.0191)
        
        getRambleyEarPath(earX,earY-RAMBLEY_EAR_Y_OFFSET_2,
                getTestDouble1(),getTestDouble2(),getTestDouble3(),
                getTestDouble4(),getTestDouble5(),path, 
                (double operand) -> getRambleyEarTipX(operand));
        System.out.println(point1 + " " + point2 + " " + point3);
        System.out.println(point3 + " " + point4 + " " + point5);
        System.out.println(point6 + " " + point7);
        
        Area earR = getRambleyEar(earX,earY,earUpper,earLower,earTip);
        Area earL = earR.createTransformedArea(horizFlip);
        
        double earScale = 2/3.0;
        Area earInR = getRambleyInnerEar(earR,earScale,headShape);
        Area earInL = getRambleyInnerEar(earL,earScale,headShape);
        
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
            g.setColor(Color.GRAY);
            g.draw(ellipse);
            g.draw(earInR);
            g.draw(earInL);
            g.setColor(Color.RED);
            g.draw(headShape);
            g.setColor(Color.ORANGE);
            g.draw(headBounds);
            g.setColor(Color.WHITE);
            g.draw(earR);
            g.draw(earL);
            g.setColor(Color.MAGENTA);
            g.draw(earUpper);
            g.setColor(Color.GREEN);
            g.draw(earLower);
            g.setColor(Color.BLUE);
            g.draw(earTip);
            if (!getABTesting()){
                g.setColor(RAMBLEY_CONDUCTOR_HAT_COLOR);
                g.draw(path);
            }
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
        double[] tArr = getBezierT(point1.getX(),point3.getX(),point2.getX(),eye5.getMinX()-4);
        double t = 1;
        for (double tempT : tArr)
            t = Math.min(tempT, t);
        point4 = getBezierPoint(point1,point3,point2,t, point4);
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
            g.setStroke(getRambleyEyeStroke());
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
            g.setStroke(getRambleyEyeStroke());
            g.setColor(RAMBLEY_NOSE_OUTLINE_COLOR);
            g.draw(nose);
            g.draw(earInR);
            g.draw(earInL);
            
            g.setStroke(getRambleyNormalStroke());
        }
        
        
        
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
    public <T extends EventListener> T[] getListeners(Class<T> listenerType){
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
}
