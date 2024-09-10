/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Milo Steier
 */
public class TwoAxisSlider extends JPanel{
    
    protected static final double SLIDER_KNOB_WIDTH = 17;
    
    protected static final double SLIDER_KNOB_HEIGHT = 17;
    
    protected static final BasicStroke DASH_STROKE = new BasicStroke(1,
            BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10,new float[]{2,5},
            0);
    
    private Rectangle2D rect = null;
    
    private Ellipse2D ellipse = null;
    
    private Line2D line = null;
    
    private Path2D path = null;
    
    
    
    private void configureSlider(JSlider slider, Handler handler){
        slider.setMajorTickSpacing(majorTickSpacing);
        slider.setMajorTickSpacing(minorTickSpacing);
        slider.setPaintTicks(paintTicks);
        slider.addChangeListener(handler);
    }
    
    private void initialize(){
        Handler handler = new Handler();
        
        xSlider = new JSlider();
        ySlider = new JSlider(JSlider.VERTICAL);
        ySlider.setInverted(true);
        
        displayPanel = new ControlDisplayPanel();
        
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.9;
        c.weighty = 0.9;
        add(displayPanel,c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        add(ySlider,c);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(xSlider,c);
        
        configureSlider(xSlider,handler);
        configureSlider(ySlider,handler);
    }
    
    public TwoAxisSlider(){
        super(new GridBagLayout());
        initialize();
    }
    
    @Override
    public void setEnabled(boolean enabled){
        try{
            xSlider.setEnabled(enabled);
            ySlider.setEnabled(enabled);
        } catch (NullPointerException ex) {}
        super.setEnabled(enabled);
    }
    
//    public BoundedRangeModel get
    
    public int getValueX(){
        return xSlider.getValue();
    }
    
    public void setValueX(int x){
        if (getValueX() == x)
            return;
        xSlider.setValue(x);
    }
    
    public int getMinimumX(){
        return xSlider.getMinimum();
    }
    
    public void setMinimumX(int min){
        if (getMinimumX() == min)
            return;
        xSlider.setMinimum(min);
    }
    
    public int getMaximumX(){
        return xSlider.getMaximum();
    }
    
    public void setMaximumX(int max){
        if (getMaximumX() == max)
            return;
        xSlider.setMaximum(max);
    }
    
    public int getValueY(){
        return ySlider.getValue();
    }
    
    public void setValueY(int y){
        if (getValueY() == y)
            return;
        ySlider.setValue(y);
    }
    
    public int getMinimumY(){
        return ySlider.getMinimum();
    }
    
    public void setMinimumY(int min){
        if (getMinimumY() == min)
            return;
        ySlider.setMinimum(min);
    }
    
    public int getMaximumY(){
        return ySlider.getMaximum();
    }
    
    public void setMaximumY(int max){
        if (getMaximumY() == max)
            return;
        ySlider.setMaximum(max);
    }
    
    public int getMajorTickSpacing(){
        return majorTickSpacing;
    }
    
    public void setMajorTickSpacing(int spacing){
        if (majorTickSpacing == spacing)
            return;
        majorTickSpacing = spacing;
        xSlider.setMajorTickSpacing(spacing);
        ySlider.setMajorTickSpacing(spacing);
        displayPanel.repaint();
    }
    
    public int getMinorTickSpacing(){
        return minorTickSpacing;
    }
    
    public void setMinorTickSpacing(int spacing){
        if (minorTickSpacing == spacing)
            return;
        minorTickSpacing = spacing;
        xSlider.setMinorTickSpacing(spacing);
        ySlider.setMinorTickSpacing(spacing);
        displayPanel.repaint();
    }
    
    public boolean getPaintTicks(){
        return paintTicks;
    }
    
    public void setPaintTicks(boolean value){
        if (value == paintTicks)
            return;
        paintTicks = value;
        xSlider.setPaintTicks(value);
        ySlider.setPaintTicks(value);
        displayPanel.repaint();
    }
    

    
    private double getSliderPosition(JSlider slider){
        double value = getRangePosition(slider.getModel());
        if (slider.getInverted() != (slider.getOrientation() == JSlider.VERTICAL))
            value = 1.0 - value;
        return value;
    }
    
    private double getRangePosition(BoundedRangeModel model){
            // The current value for the model, offset by the minimum value
        long value = model.getValue() - model.getMinimum();
            // The maximum value for the model, offset by the minimum value
        long max = model.getMaximum() - model.getMinimum();
        return value / ((double)max);
    }
    
    protected double getPositionX(){
        return getSliderPosition(xSlider);
    }
    
    protected double getPositionY(){
        return getSliderPosition(ySlider);
    }
    
    
    
    protected void paintCenterLines(Graphics2D g, Rectangle2D bounds, 
            RectangularShape shape){
        line.setLine(bounds.getMinX(), shape.getCenterY(), 
                bounds.getMaxX(), shape.getCenterY());
        g.draw(line);
        line.setLine(shape.getCenterX(), bounds.getMinY(), 
                shape.getCenterX(), bounds.getMaxY());
        g.draw(line);
    }
    
    protected void paintAxisTicks(Graphics2D g, Rectangle2D bounds, int spacing, double length){
        if (spacing <= 0 || length <= 0)
            return;
        double xSpacing = (spacing / ((double) (getMaximumX()-getMinimumX()))) * 
                bounds.getWidth();
        double ySpacing = (spacing / ((double) (getMaximumY()-getMinimumY()))) * 
                bounds.getHeight();
        length /= 2.0;
        for (double x = bounds.getMinX()+xSpacing; x < bounds.getMaxX(); x+= xSpacing){
            line.setLine(x, bounds.getCenterY()-length, x, bounds.getCenterY()+length);
            g.draw(line);
        }
        for (double y = bounds.getMinY()+ySpacing; y < bounds.getMaxY(); y+= ySpacing){
            line.setLine(bounds.getCenterX()-length, y, bounds.getCenterX()+length,y);
            g.draw(line);
        }
    }
    
    protected void paintSliderAxis(Graphics2D g, int x, int y, int w, int h){
        g.translate(x, y);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (rect == null)
            rect = new Rectangle2D.Double();
        if (ellipse == null)
            ellipse = new Ellipse2D.Double();
        if (line == null)
            line = new Line2D.Double();
        if (path == null)
            path = new Path2D.Double();
        else
            path.reset();
        
        rect.setFrame(SLIDER_KNOB_WIDTH/2.0, SLIDER_KNOB_HEIGHT/2.0, 
                w-SLIDER_KNOB_WIDTH, h-SLIDER_KNOB_HEIGHT);
        
        Color bg = isEnabled() ? Color.WHITE : Color.LIGHT_GRAY;
        
        g.setColor(bg.darker());
        g.fillRect(0, 0, w, h);
        
        path.moveTo(0, 0);
        path.lineTo(rect.getMinX(), rect.getMinY());
        path.lineTo(rect.getMaxX(), rect.getMaxY());
        path.lineTo(w, h);
        path.lineTo(0,h);
        path.closePath();
        g.setColor(g.getColor().darker());
        g.fill(path);
        
        g.setColor(bg);
        g.fill(rect);
        
        double xPos = getPositionX() * rect.getWidth();
        double yPos = getPositionY() * rect.getHeight();
        ellipse.setFrameFromCenter(xPos+rect.getMinX(),yPos+rect.getMinY(),xPos,yPos);
        
        g.setColor(Color.BLACK);
        paintCenterLines(g,rect,rect);
        if (paintTicks){
            paintAxisTicks(g,rect,majorTickSpacing,12);
            paintAxisTicks(g,rect,minorTickSpacing,8);
        }

        g.setColor(isEnabled()?Color.GRAY:Color.DARK_GRAY);
        g.setStroke(DASH_STROKE);
        paintCenterLines(g,rect,ellipse);
        
        g.setStroke(new BasicStroke());
        
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, w-1, h-1);
        g.draw(rect);
        line.setLine(0, 0, rect.getMinX(), rect.getMinY());
        g.draw(line);
        line.setLine(rect.getMaxX(), rect.getMaxY(), w, h);
        g.draw(line);

        g.setColor(Color.BLUE);
        if (!isEnabled())
            g.setColor(g.getColor().darker());
        g.fill(ellipse);
        ellipse.setFrameFromCenter(ellipse.getCenterX(), ellipse.getCenterY(), 
                ellipse.getMinX()+2, ellipse.getMinY()+2);
        g.setColor(new Color(0xB0B0FF));
        if (!isEnabled())
            g.setColor(g.getColor().darker());
        g.fill(ellipse);
        g.setColor(Color.BLACK);
        g.draw(ellipse);
    }
    
    
    
    
    
    
    private int majorTickSpacing = 10;
    private int minorTickSpacing = 5;
    private boolean paintTicks = true;
    private JSlider xSlider;
    private JSlider ySlider;
    private ControlDisplayPanel displayPanel;
    
    
    
    
    
    
    private class ControlDisplayPanel extends JPaintedPanel{
        
        protected ControlDisplayPanel(){
//            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
        
        @Override
        public void paintComponent2D(Graphics2D g, int x, int y, int width, int height){
            paintSliderAxis(g,x,y,width,height);
        }
    }
    
    private class Handler implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent evt) {
            displayPanel.repaint();
//            System.out.println("xSlider: " + getPositionX());
//            System.out.println("ySlider: " + getPositionY());
        }
        
    }
}
