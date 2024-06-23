/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import raccoon.*;
import icons.Icon2D;
import java.awt.*;
import java.awt.geom.*;
import java.util.EventListener;
import javax.swing.event.*;

/**
 *
 * @author Milo Steier
 */
public class RambleyIconOld implements Icon2D {
    /*
    I'm thinking that RambleyIcon should be 256x256, with Rambley himself being 
    about 240 pixels tall with an 8 pixel gap above and below him. The background 
    should be the one from his screens, and it should be toggleable. The pixel 
    effect should be toggleable. The icon MAY have different states that Rambley 
    can be in.
    */
    
    public static final Color BACKGROUND_COLOR = new Color(0x2CDFFF);
    
    public static final Color BACKGROUND_DOT_COLOR = new Color(0x1A73C9);
    
    public static final Color BACKGROUND_GRADIENT_COLOR = new Color(0x0068FF);
    
    protected static final int BACKGROUND_DOT_POINTS = 4;
    /**
     * 
     */
    public static final Color PIXEL_GRID_COLOR = new Color(0x60000000,true);
    
    
    
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
        
    
    
    
    
    public RambleyIconOld(){
        
    }
    
    private boolean paintBackground = false;
    
    private boolean paintGrid = false;
    
    protected EventListenerList listenerList = new EventListenerList();
    
    private Paint backgroundGradient = null;
    
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
    
    
    
    public boolean isBackgroundPainted(){
        return paintBackground;
    }
    
    public void setBackgroundPainted(boolean enabled){
        if (enabled == paintBackground)
            return;
        paintBackground = enabled;
        fireStateChanged();
    }
    
    public boolean isPixelGridPainted(){
        return paintGrid;
    }
    
    public void setPixelGridPainted(boolean enabled){
        if (enabled == paintGrid)
            return;
        paintGrid = enabled;
        fireStateChanged();
    }
    
    protected Paint getBackgroundGradient(){
        if (backgroundGradient == null){
            float x = (float)(getIconWidth() / 2.0);
//            backgroundGradient = new LinearGradientPaint(x,0,x,getIconHeight()-1,
//                    new float[]{0.0f,0.1f},
//                    new Color[]{BACKGROUND_GRADIENT_TOP_COLOR,
//                        BACKGROUND_GRADIENT_BOTTOM_COLOR});
            backgroundGradient = new GradientPaint(x,0,BACKGROUND_GRADIENT_COLOR,
                    x,getIconHeight()-1,
                    new Color(BACKGROUND_GRADIENT_COLOR.getRGB()&0x00FFFFFF,true));
        }
        return backgroundGradient;
    }
    
    protected int getBackgroundDotSize(){
        return 8;
    }
    
    protected int getBackgroundDotHalfSize(){
        return Math.floorDiv(getBackgroundDotSize(),2);
    }
    
    protected int getBackgroundDotSpacing(){
        return 12;
    }
    
    protected int getBackgroundDotOffset(int iconSize){
        return Math.floorDiv((iconSize%getBackgroundDotSpacing()), 2);
    }
    
    protected int getBackgroundDotOffsetX(){
        return getBackgroundDotOffset(getIconWidth());
    }
    
    protected int getBackgroundDotOffsetY(){
        return getBackgroundDotOffset(getIconHeight());
    }
    
    protected double getPixelGridSpacing(){
        return 5;
    }
    
    protected double getPixelGridOffset(int iconSize){
        return ((iconSize-1)%getPixelGridSpacing())/2.0;
    }
    
    protected Path2D getPixelGrid(){
        if (pixelGrid == null){
            pixelGrid = new Path2D.Double();
            for (double y = getPixelGridOffset(getIconHeight()); y <= getIconHeight(); 
                    y+=getPixelGridSpacing()){
                pixelGrid.moveTo(0, y);
                pixelGrid.lineTo(getIconWidth(), y);
            }
            for (double x = getPixelGridOffset(getIconWidth()); x <= getIconWidth(); 
                    x+=getPixelGridSpacing()){
                pixelGrid.moveTo(x, 0);
                pixelGrid.lineTo(x, getIconHeight());
            }
        }
        return pixelGrid;
    }

    @Override
    public void paintIcon2D(Component c, Graphics2D g, int x, int y) {
        int w = getIconWidth();
        int h = getIconHeight();
        g = configureGraphics(c,g);
        Graphics2D tempG = (Graphics2D) g.create();
        if (isBackgroundPainted()){
            paintBackground(c,tempG,x,y,w,h);
            tempG.dispose();
            tempG = (Graphics2D) g.create();
        }
        paintRambley(c,tempG,x,y,w,h);
        tempG.dispose();
        if (isPixelGridPainted()){
            tempG = (Graphics2D) g.create();
            paintPixelGrid(c,tempG,x,y,w,h);
            tempG.dispose();
        }
    }
    
    protected Graphics2D configureGraphics(Component c, Graphics2D g){
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
    
    protected void paintBackground(Component c, Graphics2D g, int x, int y, int w, int h){
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(x, y, w, h);
        Graphics2D tempG = (Graphics2D) g.create(x, y, w, h);
        tempG.setColor(BACKGROUND_DOT_COLOR);
        for (int i = 0; (i * getBackgroundDotSpacing()) <= h; i++){
            int yDot = (i * getBackgroundDotSpacing());
            for (int xDot = getBackgroundDotSpacing() * (i % 2); xDot <= w; 
                    xDot+=getBackgroundDotSpacing()+getBackgroundDotSpacing()){
                paintBackgroundDot(tempG,xDot,yDot);
            }
        }
        tempG.dispose();
        g.setPaint(getBackgroundGradient());
        g.fillRect(x, y, w, h);
    }
    
    protected void paintBackgroundDot(Graphics2D g, int x, int y){
        paintBackgroundDot(g,x,y,getBackgroundDotOffsetX(),getBackgroundDotOffsetY());
    }
    
    protected void paintBackgroundDot(Graphics2D g, int x, int y, int xOff, int yOff){
        int[] xPoints = new int[BACKGROUND_DOT_POINTS];
        int[] yPoints = new int[BACKGROUND_DOT_POINTS];
        xPoints[0] = xPoints[1] = xPoints[2] = x + xOff;
        yPoints[0] = yPoints[1] = yPoints[3] = y + yOff;
        xPoints[1] -= getBackgroundDotHalfSize();
        yPoints[0] -= getBackgroundDotHalfSize();
        xPoints[3] = xPoints[1] + getBackgroundDotSize();
        yPoints[2] = yPoints[0] + getBackgroundDotSize();
        g.fillPolygon(xPoints, yPoints, BACKGROUND_DOT_POINTS);
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
            double x2, double y2, Point2D p0, Point2D p1){
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
        p0.setLocation(q1, k*q1+m);
        p1.setLocation(q2, k*q2+m);
    }
    
    protected double getBezierPoint(double x0, double x1, double x2, double t){
        return (Math.pow(1-t, 2) * x0) + (2 * t * (1-t)*x1) + (Math.pow(t, 2)*x2);
    }
    
    protected Point2D getBezierPoint(Point2D p0, Point2D p1, Point2D p2, double t){
        return new Point2D.Double(getBezierPoint(p0.getX(),p1.getX(),p2.getX(),t),
                getBezierPoint(p0.getY(),p1.getY(),p2.getY(),t));
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
    
    private boolean debug = false;
    
    protected void setDebug(boolean value){
        debug = value;
        fireStateChanged();
    }
    
    protected boolean getDebug(){
        return debug;
    }
    
    protected boolean abTesting = false;
    
    
    
    protected void paintRambley(Component c, Graphics2D g, int x, int y, int w, int h){
        g.clipRect(x, y, w, h);
        double x1 = 27;
        double y1 = 60;
        g.setStroke(getRambleyNormalStroke());
        AffineTransform horizFlip = AffineTransform.getScaleInstance(-1, 1);
        horizFlip.translate(-(getIconWidth()-2), 0);
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
        
        
            // Create the shape for Rambley's head
        
            // Might also be useable for the location of the nose
        Rectangle2D head1 = new Rectangle2D.Double(x1, y1+84, 200, 92);
        Path2D head1a = new Path2D.Double(head1);
        head1a.lineTo(head1.getMinX(), head1.getMinY());
        head1a.lineTo(head1.getCenterX(), head1.getMinY()-(Math.tan(Math.toRadians(26.57))*100));
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
        
            // Ears might go here?
        
        Path2D headOutline = new Path2D.Double(headShape);
        
//        g.translate((getIconWidth()-headBounds.getWidth())/2.0, 
//                (getIconHeight()-headBounds.getHeight())/2.0);
//            // If I go for a different translation, then this will need to be 
//            // altered to get the location right. This needs to be the width of 
//            // the painted area, but negative
//        horizFlip.translate(-(headBounds.getWidth()+2), 0);
        
        if (!debug){
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
        
        if (!debug){
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
        
            // Ellipse to form the left eyebrow (may be used to calculate the 
            // location for the eyes)
        Ellipse2D eye1 = new Ellipse2D.Double();
        eye1.setFrameFromCenter(headBounds.getCenterX()-29, headBounds.getMinY()+40, 
                headBounds.getCenterX()-9, headBounds.getMinY()+20);
            // Left eyebrow area
        Area eyeBrowL = new Area(eye1);
            // Flip to form the right eyebrow
        Area eyeBrowR = eyeBrowL.createTransformedArea(horizFlip);
        
        if (!debug){
            g.setColor(RAMBLEY_EYEBROW_COLOR);
            g.fill(eyeBrowL);
            g.fill(eyeBrowR);
        } else {
            g.setColor(Color.YELLOW);
            g.draw(eye1);
            g.setColor(Color.PINK);
            g.draw(eyeBrowL);
            g.draw(eyeBrowR);
        }
            
            // May be used for calculations regarding the location of the mouth and nose
        Ellipse2D head7 = new Ellipse2D.Double();
        head7.setFrameFromCenter(headBounds.getCenterX(), headBounds.getMinY()+106, 
                headBounds.getMinX()+63, headBounds.getMinY()+78);
        Area mouthArea = new Area(head7);
        mouthArea.intersect(headShape);
        
            // Create the area around Rambley's eyes
            
            // Form the left eye area
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
        
            // Left eye surround
        Area eyeSurroundL = new Area(eye2);
        eyeSurroundL.add(new Area(eye3));
            // Right eye surround
        Area eyeSurroundR = eyeSurroundL.createTransformedArea(horizFlip);
        
        if (!debug){
            g.setColor(RAMBLEY_SECONDARY_BODY_COLOR);
            g.fill(mouthArea);
            g.fill(eyeSurroundL);
            g.fill(eyeSurroundR);
        } else {
            g.setColor(Color.GREEN);
            g.draw(mouthArea);
            g.setColor(Color.CYAN);
            g.draw(eye2);
            g.setColor(Color.RED);
            g.draw(eye3);
            g.setColor(Color.ORANGE);
            g.draw(eyeSurroundL);
            g.draw(eyeSurroundR);
        }
        
        Path2D eye6 = new Path2D.Double();
        eye6.moveTo(eye4.getCenterX(), eye4.getMinY());
        eye6.lineTo((eye4.getCenterX()+eye5.getCenterX())/2, eye4.getMinY());
        double[] tArr = getBezierT(point1.getX(),point3.getX(),point2.getX(),eye5.getMinX()-4);
        double t = 1;
        for (double tempT : tArr)
            t = Math.min(tempT, t);
        Point2D point4 = getBezierPoint(point1,point3,point2,t);
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
        
        Area eyeWhiteL = new Area(eye4);
        eyeWhiteL.add(new Area(eye6));
        Area eyeWhiteR = eyeWhiteL.createTransformedArea(horizFlip);
        
        if (!debug){
            g.setColor(RAMBLEY_EYE_WHITE_COLOR);
            double pupilX = headBounds.getCenterX()-25;
            double pupilY = eyeWhiteL.getBounds2D().getCenterY();
            paintRambleyEye(g,eyeWhiteL,pupilX,pupilY);
            paintRambleyEye(g,eyeWhiteR,pupilX+50,pupilY);
        } else {
            g.setColor(Color.YELLOW);
            g.draw(eye5);
            g.setColor(RAMBLEY_MAIN_BODY_COLOR);
            g.draw(eye4);
            g.setColor(RAMBLEY_SCARF_COLOR);
            g.draw(eye6);
            g.setColor(RAMBLEY_IRIS_COLOR);
            g.draw(eyeWhiteL);
            g.draw(eyeWhiteR);
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
        
        if (!debug){
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
        
        if (!debug){
            g.setColor(RAMBLEY_OUTLINE_COLOR);
            g.setStroke(getRambleyOutlineStroke());
            g.draw(headOutline);
            g.setStroke(getRambleyEyeStroke());
            g.setColor(RAMBLEY_NOSE_OUTLINE_COLOR);
            g.draw(nose);
            
            g.setStroke(getRambleyNormalStroke());
        }
        
        
        
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
        tempG.setColor(RAMBLEY_IRIS_COLOR);
        tempG.fill(iris);
        tempG.setColor(RAMBLEY_PUPIL_COLOR);
        tempG.fill(pupil);
        tempG.setColor(RAMBLEY_IRIS_OUTLINE_COLOR);
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
    
    protected void paintPixelGrid(Component c, Graphics2D g, int x, int y, int w, int h){
        g.clipRect(x, y, w, h);
        g.setColor(PIXEL_GRID_COLOR);
            // Turn off gntialiasing for the pixel grid
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
        g.draw(getPixelGrid());
    }
    
    
    
    

    @Override
    public int getIconWidth() {
        return 256;
    }

    @Override
    public int getIconHeight() {
        return 256;
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
        return getIconWidth() + "x" + getIconHeight() + 
                ",paintBackground="+isBackgroundPainted()+
                ",pixelGridPainted="+isPixelGridPainted();
    }
    @Override
    public String toString(){
        return getClass().getName()+"["+paramString()+"]";
    }
}
