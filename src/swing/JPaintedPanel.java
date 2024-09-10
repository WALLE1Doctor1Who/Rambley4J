/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * This is a JPanel that can be used for custom painting using a Graphics2D 
 * graphics context. To use this class, override the {@link #paintComponent2D 
 * paintComponent2D} method in order to paint whatever you need.
 * @author Milo Steier
 */
public abstract class JPaintedPanel extends JPanel {
    /**
     * This constructs a JPaintedPanel with the specified layout manager and 
     * buffering strategy.
     * @param layout The layout manager to use.
     * @param isDoubleBuffered Whether this panel uses double-buffering to 
     * achieve fast, flicker-free updates at the expense of using additional 
     * memory space.
     */
    public JPaintedPanel(LayoutManager layout, boolean isDoubleBuffered){
        super(layout,isDoubleBuffered);
    }
    /**
     * This constructs a JPaintedPanel with the specified layout manager.
     * @param layout The layout manager to use.
     */
    public JPaintedPanel(LayoutManager layout){
        super(layout);
    }
    /**
     * This constructs a JPaintedPanel with a flow layout and the given 
     * buffering strategy.
     * @param isDoubleBuffered Whether this panel uses double-buffering to 
     * achieve fast, flicker-free updates at the expense of using additional 
     * memory space.
     */
    public JPaintedPanel(boolean isDoubleBuffered){
        super(isDoubleBuffered);
    }
    /**
     * This constructs a JPaintedPanel that is double buffered with a flow 
     * layout.
     */
    public JPaintedPanel(){
        super();
    }
    /**
     * This paints this component to the given graphics context. This will first 
     * call the UI delegate's paint method if the UI delegate is non-null. A 
     * copy of the {@code Graphics} object is passed to the UI delegate so as to 
     * protect the rest of the paint code from irrevocable changes (for example, 
     * {@code Graphics.translate}). After the UI delegate finishes, this will 
     * call the {@link #paintComponent2D paintComponent2D} method to render the 
     * rest of this component. A second copy of the {@code Graphics} object, now 
     * in the form of a {@code Graphics2D} object, is passed to the {@code 
     * paintComponent2D} method to again protect the rest of the paint code from 
     * irrevocable changes. If the {@code Graphics} object is not a {@code 
     * Graphics2D} object, then the {@code paintComponent2D} will paint to an 
     * image which is then painted by the {@code Graphics} object. <p>
     * 
     * If you override this in a subclass you should not make any permanent 
     * changes to the given {@code Graphics} object. You may find it easier to 
     * create and use a copy of the given {@code Graphics} object. Further, if 
     * you do not invoke super's implementation you must honor the opaque 
     * property (i.e. if this component is opaque then you must completely fill 
     * in the background with an opaque color), least risk the appearance of 
     * visual artifacts. <p>
     * 
     * The given {@code Graphics} object may have a transform other than the 
     * identity transform applied to it. If this is the case, then you might get 
     * unexpected results if you cumulatively apply another transform.
     * 
     * @param g {@inheritDoc }
     * @see #paint 
     * @see javax.swing.plaf.ComponentUI
     * @see paintComponent2D
     */
    @Override
    public void paintComponent(Graphics g){
            // Paint the component as normal
        super.paintComponent(g);
        int w = getWidth();             // Get the width of the component
        int h = getHeight();            // Get the height of the component
            // If the width or height are 0 or less or the graphics context 
            // is null
        if (w <= 0 || h <= 0 || g == null)
            return;
        g = g.create(0, 0, w, h);
            // An image to render to if the given graphics context is not a 
        BufferedImage img = null;       // Graphics2D object
        Graphics2D g2D; // This will get the Graphics2D object to render to.
            // If the graphics context is a Graphics2D object
        if (g instanceof Graphics2D)
            g2D = (Graphics2D) g;
        else if (g != null){            // If the graphics context is not null
                // Create the image to render to
            img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
                // Create a graphics context for the image
            g2D = img.createGraphics();
                // Configure the image graphics context to match the given 
                // graphics context
            g2D.setFont(g.getFont());
            g2D.setColor(g.getColor());
        }
        else                            //If the graphics conext is somehow null
            return;
            // Paint the contents of this component
        paintComponent2D(g2D, 0, 0, w, h);
        if (img != null){               // If this rendered to an image
            g2D.dispose();
            g.drawImage(img, 0, 0, w, h, this);
        }
        g.dispose();
    }
    /**
     * This paints this component to the given graphics context. This is called 
     * by {@link #paintComponent paintComponent} after the UI delegate has
     * finished painting what it needs to for this component. The graphics 
     * context passed to this method by {@code paintComponent} is always a 
     * scratch {@code Graphics2D} object.
     * @param g The {@code Graphics2D} object to render to.
     * @param x The x-coordinate of the top-left corner of the area to render 
     * to.
     * @param y The y-coordinate of the top-left corner of the area to render 
     * to.
     * @param width The width of the area to render to.
     * @param height The height of the area to render to.
     * @see #paintComponent(Graphics) 
     */
    public abstract void paintComponent2D(Graphics2D g, int x, int y, int width, 
            int height);
}
