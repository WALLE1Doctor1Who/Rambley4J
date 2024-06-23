/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import icons.Icon2D;
import java.awt.*;

/**
 *
 * @author Milo Steier
 */
public class RambleyIcon extends RambleyPainter implements Icon2D {
    
    public RambleyIcon(){
        
    }
    
    @Override
    public void paintIcon2D(Component c, Graphics2D g, int x, int y) {
        g.translate(x, y);
        paint(g, c, getIconWidth(), getIconHeight());
    }
    @Override
    public int getIconWidth() {
        return INTERNAL_RENDER_WIDTH;
    }

    @Override
    public int getIconHeight() {
        return INTERNAL_RENDER_HEIGHT;
    }
    /**
     * This returns a String representation of this {@code RambleyIcon}. 
     * This method is primarily intended to be used only for debugging purposes, 
     * and the content and format of the returned String may vary between 
     * implementations.
     * @return A String representation of this {@code RambleyIcon}.
     */
    @Override
    protected String paramString(){
        return getIconWidth() + "x" + getIconHeight()+
                ","+super.paramString();
    }
}
