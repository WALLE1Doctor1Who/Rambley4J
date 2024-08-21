/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geom;

import java.awt.geom.*;
import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * This is a shape that describes a rhombus that is defined by a framing 
 * rectangle. <p>
 * 
 * This class is only the abstract superclass for all objects which store a 2D 
 * rhombus, and the actual storage representation of the coordinates is left to 
 * the subclass.
 * @author Milo Steier
 */
public abstract class Rhombus2D extends RectangularShape {
    /**
     * This class defines a rhombus specified in {@code double} precision.
     */
    public static class Double extends Rhombus2D implements Serializable{
        /**
         * The x-coordinate of the upper-left corner of the framing rectangle 
         * of this {@code Rhombus2D}.
         */
        public double x;
        /**
         * The y-coordinate of the upper-left corner of the framing rectangle 
         * of this {@code Rhombus2D}.
         */
        public double y;
        /**
         * The overall width of this {@code Rhombus2D}.
         */
        public double width;
        /**
         * The overall height of this {@code Rhombus2D}.
         */
        public double height;
        /**
         * This constructs and initializes a new {@code Rhombus2D} with the 
         * specified location and size.
         * @param x The x-coordinate of the upper-left corner of the framing 
         * rectangle.
         * @param y The y-coordinate of the upper-left corner of the framing 
         * rectangle.
         * @param w The width of the framing rectangle.
         * @param h The height of the framing rectangle.
         */
        public Double(double x, double y, double w, double h){
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }
        /**
         * This constructs  a new {@code Rhombus2D} that is initialized to the 
         * location (0, 0) and size (0 x 0).
         */
        public Double(){
            this(0,0,0,0);
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public double getX() {
            return x;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public double getY() {
            return y;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public double getWidth() {
            return width;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public double getHeight() {
            return height;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public boolean isEmpty() {
            return width <= 0 || height <= 0;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public void setFrame(double x, double y, double w, double h) {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public Rectangle2D getBounds2D() {
            return new Rectangle2D.Double(x, y, width, height);
        }
        /**
         * The serial version ID for this class.
         */
        private static final long serialVersionUID = 7227783083171821977L;
    }
    /**
     * This class defines a rhombus specified in {@code float} precision.
     */
    public static class Float extends Rhombus2D implements Serializable{
        /**
         * The x-coordinate of the upper-left corner of the framing rectangle 
         * of this {@code Rhombus2D}.
         */
        public float x;
        /**
         * The y-coordinate of the upper-left corner of the framing rectangle 
         * of this {@code Rhombus2D}.
         */
        public float y;
        /**
         * The overall width of this {@code Rhombus2D}.
         */
        public float width;
        /**
         * The overall height of this {@code Rhombus2D}.
         */
        public float height;
        /**
         * This constructs and initializes a new {@code Rhombus2D} with the 
         * specified location and size.
         * @param x The x-coordinate of the upper-left corner of the framing 
         * rectangle.
         * @param y The y-coordinate of the upper-left corner of the framing 
         * rectangle.
         * @param w The width of the framing rectangle.
         * @param h The height of the framing rectangle.
         */
        public Float(float x, float y, float w, float h){
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }
        /**
         * This constructs  a new {@code Rhombus2D} that is initialized to the 
         * location (0, 0) and size (0 x 0).
         */
        public Float(){
            this(0,0,0,0);
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public double getX() {
            return x;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public double getY() {
            return y;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public double getWidth() {
            return width;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public double getHeight() {
            return height;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public boolean isEmpty() {
            return width <= 0 || height <= 0;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public void setFrame(double x, double y, double w, double h) {
            this.x = (float) x;
            this.y = (float) y;
            this.width = (float) w;
            this.height = (float) h;
        }
        /**
         * This sets the location and size of the framing rectangle of this 
         * {@code Shape} to the specified rectangular values.
         * @param x The x-coordinate of the upper-left corner of the specified 
         * rectangular shape.
         * @param y The y-coordinate of the upper-left corner of the specified 
         * rectangular shape.
         * @param w The width of the specified rectangular shape.
         * @param h The height of the specified rectangular shape.
         */
        public void setFrame(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public Rectangle2D getBounds2D() {
            return new Rectangle2D.Float(x, y, width, height);
        }
        /**
         * The serial version ID for this class.
         */
        private static final long serialVersionUID = 2665570261384514273L;
    }
    /**
     * A Rectangle2D object used in the process of checking if a given rectangle 
     * intersects this Rhombus. This is initialized the first time it is used.
     */
    private Rectangle2D intersectRect = null;
    /**
     * This is an abstract class that cannot be instantiated directly. Type 
     * specific implementation subclasses are available for instantiation and 
     * provide a number of formats for storing the information necessary to 
     * satisfy the various accessor methods below.
     * 
     * @see Rhombus2D.Float
     * @see Rhombus2D.Double
     */
    protected Rhombus2D(){ }
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean contains(double x, double y){
            // If this rhombus is empty
        if (isEmpty())
            return false;
            // Get half the width
        double a = getWidth()/2.0;
            // Get half the height
        double b = getHeight()/2.0;
            // The cartesian equation for a rhombus at the origin is 
            // |x/a| + |y/b| = 1, and anything less than or equal to 1 is within 
            // the rhombus. To shift the rhombus, the x and y of the center of 
            // the rhombus will need to be subtracted. To use the top-left 
            // corner of the rhombus as the reference, a and b will need to be 
            // subtracted from the x and y.
        return Math.abs((x-getX()-a)/a)+Math.abs((y-getY()-b)/b)<=1;
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean contains(double x, double y, double w, double h){
            // If either this rhombus or the given rectangle are empty
        if (isEmpty() || w <= 0 || h <= 0)
            return false;
        return contains(x,y)&&contains(x+w,y)&&contains(x,y+h)&&contains(x+w,y+h);
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean intersects(double x, double y, double w, double h){
            // If either this rhombus or the given rectangle are empty
        if (isEmpty() || w <= 0 || h <= 0)
            return false;
            // If the intersection Rectangle2D object has not been initialized 
            // yet
        if (intersectRect == null)
            intersectRect = new Rectangle2D.Double();
            // Set the intersection rectangle to the bounds of this rhombus
        intersectRect.setFrame(getX(), getY(), getWidth(), getHeight());
            // If the bounding rectangle for this rhombus does not intersect 
            // with the given rectangle
        if (!intersectRect.intersects(x, y, w, h))
            return false;
            // If any of the four corners of the rectangle are in the rhombus
        if (contains(x,y)||contains(x+w,y)||contains(x,y+h)||contains(x+w,y+h))
            return true;
        // At this point we know that the bounding rectangle intersects with the 
        // given rectangle, but none of the corners of the given rectangle lie 
        // within the rhombus. At least one line of the given rectangle must 
        // intersect with the rhombus, and for straight line to intersect with 
        // the rhombus without the start and end points being within the 
        // rhombus, the line will need to pass through the center lines of the 
        // rhombus 
            // Get the center x for this rhombus
        double xC = intersectRect.getCenterX();
            // Get the center y for this rhombus
        double yC = intersectRect.getCenterY();
            // Return whether either the parallel horizontal lines of the 
            // rectangle intersects the vertical center line of the rhombus or 
            // the either the parallel vertical lines of the rectangle 
            // intersects the horizontal center line of the rhombus
        return (x <= xC && xC <= x+w) || (y <= yC && yC <= y+h);
    }
    /**
     * This returns an iteration object that defines the boundary of this {@code 
     * Rhombus2D}. The iterator for this class is multi-threaded safe, which 
     * means that this {@code Rhombus2D} class guarantees that modifications to 
     * the geometry of this {@code Rhombus2D} object do not affect any 
     * iterations of that geometry that are already in process.
     * 
     * @param at An optional {@code AffineTransform} to be applied to the 
     * coordinates as they are returned in the iteration, or {@code null} if 
     * untransformed coordinates are desired.
     * @return The {@code PathIterator} object that returns the geometry of the 
     * outline of this {@code Rhombus2D}, one segment at a time.
     */
    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return new RhombusIterator(this,at);
    }
    /**
     * This returns whether the given object is a {@code Rhombus2D} object that 
     * is equal to this {@code Rhombus2D} object. That is to say, this returns 
     * whether the given object is a {@code Rhombus2D} object with the same 
     * location and size as this {@code Rhombus2D} object.
     * @param obj The object to compare to this {@code Rhombus2D} object.
     * @return Whether the given object is equal to this {@code Rhombus2D} 
     * object.
     */
    @Override
    public boolean equals(Object obj){
        if (obj == this)        // If the given object is this object
            return true;
            // If the given object is a Rhombus2D object
        else if (obj instanceof Rhombus2D){
                // Get the object as a Rhombus2D object
            Rhombus2D r = (Rhombus2D) obj;
            return this.getX() == r.getX() &&
                    this.getY() == r.getY() &&                     
                    this.getWidth() == r.getWidth() && 
                    this.getHeight() == r.getHeight();
        }
        return false;
    }
    /**
     * This returns the hashcode for this {@code Rhombus2D} object.
     * @return The hashcode for this {@code Rhombus2D} object.
     */
    @Override
    public int hashCode() {
        int hash = 7;   // This gets the hash code for this rhombus object
            // A for loop to go through the x, y, width, and height
        for (double value : new double[]{getX(),getY(),getWidth(),getHeight()}){
                // Get the bits for the current value
            long bits = java.lang.Double.doubleToLongBits(value);
            hash = 89 * hash + (int)(bits ^ (bits >>> 32));
        }
        return hash;
    }
    /**
     * This is a utility class to iterate over the path segments for a rhombus 
     * through the PathIterator interface.
     * @author Milo Steier
     */
    private static class RhombusIterator implements PathIterator{
        /**
         * The amount of points there are in a rhombus.
         */
        private static final int POINT_COUNT = 4;
        /**
         * This is the index for the closing segment.
         */
        private static final int CLOSE_SEGMENT_INDEX = POINT_COUNT + 1;
        /**
         * The location and size of the framing rectangle.
         */
        private final double x, y, w, h;
        /**
         * Half of the size of the framing rectangle.
         */
        private final double halfW, halfH;
        /**
         * The AffineTransform to apply to the coordinates if one is supplied.
         */
        private final AffineTransform aTx;
        /**
         * The current index for this iterator.
         */
        private int index = 0;
        /**
         * This constructs a RhombusIterator that will iterate through the 
         * bounds of the given rhombus.
         * @param r The rhombus to iterate through the path of.
         * @param at The affine transform for the points, or null.
         */
        private RhombusIterator(Rhombus2D r, AffineTransform at){
            this.x = r.getX();
            this.y = r.getY();
            this.w = r.getWidth();
            this.h = r.getHeight();
            this.halfW = w / 2.0;
            this.halfH = h / 2.0;
            this.aTx = at;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public int getWindingRule() {
            return PathIterator.WIND_NON_ZERO;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public boolean isDone() {
            return index > CLOSE_SEGMENT_INDEX;
        }
        /**
         * {@inheritDoc }
         */
        @Override
        public void next() {
            index ++;
        }
        /**
         * This checks if this path iterator is done and if so throws an {@code 
         * NoSuchElementException}.
         * @throws NoSuchElementException If this path iterator is done.
         */
        private void checkIfDone(){
                // If this path iterator is done
            if (isDone())
                throw new NoSuchElementException("Rhombus iterator is done");
        }
        @Override
        public int currentSegment(float[] coords) {
                // Check if there are no more segments to return
            checkIfDone();
                // If this has reached the closing segment
            if (index == CLOSE_SEGMENT_INDEX)
                return PathIterator.SEG_CLOSE;
                // Set the coordinates to be the top-left corner of the framing
                // rectangle
            coords[0] = (float) x;
            coords[1] = (float) y;
                // If the current segment's index is divisible by 2
            if (index % 2 == 0)
                coords[0] += (float) halfW;
            else
                coords[1] += (float) halfH;
                // Determine how to alter the points based off the current index
            switch(index){
                    // Bottom center point
                case(2):
                    coords[1] += (float) h;
                    break;
                    // Right center point
                case(3):
                    coords[0] += (float) w;
            }   // If there is an AffineTransform applied to this path
            if (aTx != null)
                    // Transform the first point
                aTx.transform(coords, 0, coords, 0, 1);
                // If this is the first segment, then move to this point. 
                // Otherwise, draw a line to this point
            return (index == 0) ? PathIterator.SEG_MOVETO : PathIterator.SEG_LINETO;
        }

        @Override
        public int currentSegment(double[] coords) {
                // Check if there are no more segments to return
            checkIfDone();
                // If this has reached the closing segment
            if (index == CLOSE_SEGMENT_INDEX)
                return PathIterator.SEG_CLOSE;
                // Set the coordinates to be the top-left corner of the framing
                // rectangle
            coords[0] = x;
            coords[1] = y;
                // If the current segment's index is divisible by 2
            if (index % 2 == 0)
                coords[0] += halfW;
            else
                coords[1] += halfH;
                // Determine how to alter the points based off the current index
            switch(index){
                    // Bottom center point
                case(2):
                    coords[1] += h;
                    break;
                    // Right center point
                case(3):
                    coords[0] += w;
            }   // If there is an AffineTransform applied to this path
            if (aTx != null)
                    // Transform the first point
                aTx.transform(coords, 0, coords, 0, 1);
                // If this is the first segment, then move to this point. 
                // Otherwise, draw a line to this point
            return (index == 0) ? PathIterator.SEG_MOVETO : PathIterator.SEG_LINETO;
        }
    }
}
