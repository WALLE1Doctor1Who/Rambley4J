/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raccoon;

import java.awt.Graphics2D;
import java.util.Objects;
import swing.JPaintedPanel;

/**
 *
 * @author Milo Steier
 */
public class JRambley extends JPaintedPanel{
    
    public static final String RAMBLEY_PROPERTY_CHANGED = 
            "RambleyPropertyChanged";
    
    
    
    protected RambleyPainter rambley = null;
    
    public JRambley(RambleyPainter rambley){
        super();
        JRambley.this.setRambley(rambley);
    }
    
    public JRambley(){
        this(new RambleyPainter());
    }
    
    
    
    public RambleyPainter getRambley(){
        return rambley;
    }
    
    public void setRambley(RambleyPainter rambley){
        Objects.requireNonNull(rambley);
        if (Objects.equals(rambley, this.rambley))
            return;
        this.rambley = rambley;
    }

    @Override
    public void paintComponent2D(Graphics2D g, int x, int y, int width, int height) {
        
    }
    
    
    
    
}
