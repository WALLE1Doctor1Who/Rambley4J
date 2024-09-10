/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geom;

import java.awt.Shape;
import java.awt.geom.*;
import java.util.*;
import java.util.function.DoubleUnaryOperator;

/**
 * This is a library for math related to geometry.
 * @author Milo Steier
 */
public final class GeometryMath {
    /**
     * This is the default resolution for the {@link #getLineIntersection(
     * double, double, double, double, DoubleUnaryOperator, DoubleUnaryOperator, 
     * Point2D) getLineIntersection} method when a resolution is not specified.
     * @see getLineIntersection(double, double, double, double, 
     * DoubleUnaryOperator, DoubleUnaryOperator, int, Point2D)
     * @see getLineIntersection(double, double, double, double, 
     * DoubleUnaryOperator, DoubleUnaryOperator, Point2D)
     */
    public static final int DEFAULT_LINE_INTERSECTION_RESOLUTION = 25;
    /**
     * This class cannot be constructed.
     */
    private GeometryMath() {}
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
    private static double getRadius(Ellipse2D c){
        return (c.getWidth() + c.getHeight()) / 4.0;
    }
    /**
     * This solves the quadratic equation for the given a, b, and c 
     * coefficients. For reminder, a quadratic equation is in the form {@code 
     * ax^2 + bx + c = 0}.
     * @param a The a coefficient in the quadratic equation.
     * @param b The b coefficient in the quadratic equation.
     * @param c The c coefficient in the quadratic equation.
     * @return An array with the two roots of the quadratic equation, sorted 
     * from smallest to largest. If there is only one root, then both locations 
     * in the array will be the same value. If there are no roots or the 
     * quadratic equation was a constant, then this returns null.
     * @see QuadCurve2D#solveQuadratic(double[], double[]) 
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
        else    // Sort the roots 
            Arrays.sort(roots, 0, roots.length);
        return roots;
    }
    /**
     * This calculates the slope for the line that passes through the points 
     * ({@code x1}, {@code y1}) and ({@code x2}, {@code y2}).
     * @param x1 The x-coordinate for the first point on the line.
     * @param y1 The y-coordinate for the first point on the line.
     * @param x2 The x-coordinate for the second point on the line.
     * @param y2 The y-coordinate for the second point on the line.
     * @return The slope of the given line.
     */
    public static double getLineSlope(double x1,double y1,double x2,double y2){
        return (y2 - y1) / (x2 - x1);
    }
    /**
     * This calculates the slope for the line that passes through the points 
     * {@code p1} and {@code p2}.
     * @param p1 The first point on the line.
     * @param p2 The second point on the line.
     * @return The slope of the given line.
     */
    public static double getLineSlope(Point2D p1, Point2D p2){
        return getLineSlope(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    }
    /**
     * This calculates the slope for the given line.
     * @param line The line to get the slope of.
     * @return The slope of the given line.
     */
    public static double getLineSlope(Line2D line){
        return getLineSlope(line.getX1(),line.getY1(),line.getX2(),line.getY2());
    }
    /**
     * This calculates the line coefficient for the line with a slope {@code m} 
     * and that passes through point ({@code x}, {@code y}).
     * @param m The slope of the line.
     * @param x The x-coordinate of a point on the line.
     * @param y The y-coordinate of a point on the line.
     * @return The coefficient for the given line.
     */
    public static double getLineCoefficient(double m, double x, double y){
        return y - m * x;
    }
    /**
     * This calculates the line coefficient for the line with a slope {@code m} 
     * and that passes through the given point.
     * @param m The slope of the line.
     * @param p A point on the line.
     * @return The coefficient for the given line.
     */
    public static double getLineCoefficient(double m, Point2D p){
        return getLineCoefficient(m,p.getX(),p.getY());
    }
    /**
     * This calculates the x-coordinates for points on the given ellipse for the 
     * given y-coordinate, and stores them in the given Point2D objects {@code 
     * p0} and {@code p1}. This is equivalent to determining the points where 
     * the ellipse intersects with a horizontal line at the given y-coordinate. 
     * If the given y-coordinate does not lie on the given ellipse, then the 
     * points will be set to ({@link Double#NaN Double.NaN}, {@link Double#NaN 
     * Double.NaN}) and this will return false.
     * @param ellipse The ellipse to calculate the points on
     * @param y The y-coordinate to get the points for
     * @param p0 The Point2D object that the left-most point will be stored in.
     * @param p1 The Point2D object that the right-most point will be stored in.
     * @return Whether the y-coordinate lies on the ellipse.
     * @see #getEllipseY 
     * @see #getCircleIntersections(Ellipse2D, double, double, double, double, 
     * Point2D, Point2D) 
     */
    public static boolean getEllipseX(Ellipse2D ellipse, double y,Point2D p0, 
            Point2D p1){
            // If the given y-coordinate is out of range of the ellipse
        if (y < ellipse.getMinY() || y > ellipse.getMaxY()){
                // Set the points to return to NaN
            p0.setLocation(Double.NaN, Double.NaN);
            p1.setLocation(p0);
            return false;
        }   // If the given y-coordinate is at the top or bottom bounds of the 
            // ellipse
        else if (y == ellipse.getMinY() || y == ellipse.getMaxY()){
                // The points will be at the vertical center of the ellipse
            p0.setLocation(ellipse.getCenterX(), y);
            p1.setLocation(p0);
            return true;
        }   // If the given y-coordinate is at the center of the ellipse
        else if (y == ellipse.getCenterY()){
                // The points will be at the top and bottom of the ellipse
            p0.setLocation(ellipse.getMinX(),y);
            p1.setLocation(ellipse.getMaxX(),y);
            return true;
        }   // Get the intersections of the horizontal line at the given 
            // y-coordinate
        return getCircleIntersections(ellipse,ellipse.getMinX(),y,
                ellipse.getMaxX(),y,p0,p1);
    }
    /**
     * This calculates the y-coordinates for points on the given ellipse for the 
     * given x-coordinate, and stores them in the given Point2D objects {@code 
     * p0} and {@code p1}. This is equivalent to determining the points where 
     * the ellipse intersects with a vertical line at the given x-coordinate. 
     * If the given x-coordinate does not lie on the given ellipse, then the 
     * points will be set to ({@link Double#NaN Double.NaN}, {@link Double#NaN 
     * Double.NaN}) and this will return false.
     * @param ellipse The ellipse to calculate the points on
     * @param x The x-coordinate to get the points for
     * @param p0 The Point2D object that the upper point will be stored in.
     * @param p1 The Point2D object that the lower point will be stored in.
     * @return Whether the x-coordinate lies on the ellipse.
     * @see #getEllipseX 
     * @see #getCircleIntersections(Ellipse2D, double, double, double, double, 
     * Point2D, Point2D) 
     */
    public static boolean getEllipseY(Ellipse2D ellipse, double x,Point2D p0, 
            Point2D p1){
            // If the given x-coordinate is out of range of the ellipse
        if (x < ellipse.getMinX() || x > ellipse.getMaxX()){
                // Set the points to return to NaN
            p0.setLocation(Double.NaN, Double.NaN);
            p1.setLocation(p0);
            return false;
        }   // If the given x-coordinate is at the left or right bounds of the 
            // ellipse
        else if (x == ellipse.getMinX() || x == ellipse.getMaxX()){
                // The points will be at the vertical center of the ellipse
            p0.setLocation(x, ellipse.getCenterY());
            p1.setLocation(p0);
            return true;
        }   // If the given x-coordinate is at the center of the ellipse
        else if (x == ellipse.getCenterX()){
                // The points will be at the top and bottom of the ellipse
            p0.setLocation(x, ellipse.getMinY());
            p1.setLocation(x, ellipse.getMaxY());
            return true;
        }   // Calculate half of the width of the ellipse
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
     * This calculates the two points of intersection for two circles.
     * 
     * This uses the algorithm described by Paul Bourke 
     * (<a href="https://paulbourke.net/geometry/circlesphere/">Circles and 
     * spheres</a>)
     * 
     * 
     * 
     * @param circle1 One of the two circles
     * @param circle2 The other circle
     * @param p0 The Point2D object that the first point of intersection will be 
     * stored in.
     * @param p1 The Point2D object that the second point of intersection will 
     * be stored in.
     * @return {@code true} if the two circles intersect, {@code false} if 
     * either the circles are separate, one circle is fully contained within the 
     * other, or the circles are coincident and there are an infinite number of 
     * solutions.
     */
    public static boolean getCircleIntersections(Ellipse2D circle1, 
            Ellipse2D circle2, Point2D p0, Point2D p1){
            // Store the location of the center of the first circle in the 
            // first point
        p0.setLocation(circle1.getCenterX(), circle1.getCenterY());
            // Store the location of the center of the second circle in the 
            // second point
        p1.setLocation(circle2.getCenterX(), circle2.getCenterY());
            // Get the radius of the first circle
        double r0 = getRadius(circle1);
            // Get the radius of the second circle
        double r1 = getRadius(circle2);
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
     * This gets the points at which the given line intersects with the given 
     * ellipse and stores them in the Point2D objects {@code point1} and {@code 
     * point2}. If the line does not intersect with the ellipse, then the points 
     * will be set to ({@link Double#NaN Double.NaN}, {@link Double#NaN 
     * Double.NaN}) and this will return false.
     * @param ellipse The ellipse to intersect with the line.
     * @param x1 The x-coordinate for the first point on the line.
     * @param y1 The y-coordinate for the first point on the line.
     * @param x2 The x-coordinate for the second point on the line.
     * @param y2 The y-coordinate for the second point on the line.
     * @param point1 The Point2D object that the first point of intersection 
     * will be stored in.
     * @param point2 The Point2D object that the second point of intersection 
     * will be stored in.
     * @return Whether the line intersects the ellipse.
     * @see #getCircleIntersections(Ellipse2D, Point2D, Point2D, Point2D, 
     * Point2D) 
     * @see #getCircleIntersections(Ellipse2D, Line2D, Point2D, Point2D) 
     * @see #getCircleIntersections(Ellipse2D, Ellipse2D, Point2D, Point2D) 
     * @see #getEllipseX 
     * @see #getEllipseY 
     */
    public static boolean getCircleIntersections(Ellipse2D ellipse, 
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
            return getEllipseY(ellipse,x1,point1,point2);
        }   // Get the slope of the line
        double k = getLineSlope(x1,y1,x2,y2);
            // Get the coefficient for the line
        double m = getLineCoefficient(k,x2,y2);
            // Get half of the ellipse's width, squared
        double w = Math.pow(ellipse.getWidth()/2, 2);
            // Get half of the ellipse's height, squared
        double h = Math.pow(ellipse.getHeight()/2, 2);
            // Get the a coefficient for the quadratic equation
        double a = 1/w + Math.pow(k, 2)/h;
            // Get the b coefficient for the quadratic equation
        double b = (2*k*(m-ellipse.getCenterY()))/h - (2*ellipse.getCenterX())/w;
            // Get the c coefficient for the quadratic equation
        double c = Math.pow(m-ellipse.getCenterY(), 2)/h - 1 + 
                Math.pow(ellipse.getCenterX(), 2)/w;
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
     * This gets the points at which the given line intersects with the given 
     * ellipse and stores them in the Point2D objects {@code point1} and {@code 
     * point2}. If the line does not intersect with the ellipse, then the return 
     * points will be set to ({@link Double#NaN Double.NaN}, {@link Double#NaN 
     * Double.NaN}) and this will return false.
     * @param ellipse The ellipse to intersect with the line.
     * @param p1 The first point on the line.
     * @param p2 The second point on the line.
     * @param point1 The Point2D object that the first point of intersection 
     * will be stored in.
     * @param point2 The Point2D object that the second point of intersection 
     * will  be stored in.
     * @return Whether the line intersects the ellipse.
     * @see #getCircleIntersections(Ellipse2D, double, double, double, double, 
     * Point2D, Point2D) 
     * @see #getCircleIntersections(Ellipse2D, Line2D, Point2D, Point2D) 
     * @see #getCircleIntersections(Ellipse2D, Ellipse2D, Point2D, Point2D) 
     * @see #getEllipseX 
     * @see #getEllipseY 
     */
    public static boolean getCircleIntersections(Ellipse2D ellipse, Point2D p1, 
            Point2D p2, Point2D point1, Point2D point2){
        return getCircleIntersections(ellipse,p1.getX(),p1.getY(),
                p2.getX(),p2.getY(),point1,point2);
    }
    /**
     * This gets the points at which the given line intersects with the given 
     * ellipse and stores them in the Point2D objects {@code point1} and {@code 
     * point2}. If the line does not intersect with the ellipse, then the points 
     * will be set to ({@link Double#NaN Double.NaN}, {@link Double#NaN 
     * Double.NaN}) and this will return false.
     * @param ellipse The ellipse to intersect with the line.
     * @param line The line to intersect with the ellipse.
     * @param point1 The Point2D object that the first point of intersection 
     * will be stored in.
     * @param point2 The Point2D object that the second point of intersection 
     * will be stored in.
     * @return Whether the line intersects the ellipse.
     * @see #getCircleIntersections(Ellipse2D, double, double, double, double, 
     * Point2D, Point2D) 
     * @see #getCircleIntersections(Ellipse2D, Point2D, Point2D, Point2D, 
     * Point2D) 
     * @see #getCircleIntersections(Ellipse2D, Ellipse2D, Point2D, Point2D) 
     * @see #getEllipseX 
     * @see #getEllipseY 
     */
    public static boolean getCircleIntersections(Ellipse2D ellipse, Line2D line, 
            Point2D point1, Point2D point2){
        return getCircleIntersections(ellipse,line.getX1(),line.getY1(),
                line.getX2(),line.getY2(),point1,point2);
    }
    /**
     * This is used to calculate a point on a quadratic Bezier curve 
     * @param p0 The starting point of the curve.
     * @param p1 The control point for the curve.
     * @param p2 The end point of the curve.
     * @param t The t value of the point to get on the curve.
     * @return The point on the curve at the given t.
     */
    private static double getQuadBezierPoint(double p0, double p1, double p2, 
            double t){
        return (Math.pow(1-t, 2) * p0)+(2 * t * (1-t)*p1)+(Math.pow(t, 2)*p2);
    }
    /**
     * This calculates and returns 
     * @param x1 The x-coordinate of the starting point of the curve.
     * @param y1 The y-coordinate of the starting point of the curve.
     * @param xC The x-coordinate of the control point for the curve.
     * @param yC The y-coordinate of the control point for the curve.
     * @param x2 The x-coordinate of the end point of the curve.
     * @param y2 The y-coordinate of the end point of the curve.
     * @param t A value between 0 and 1, inclusive, 
     * @param point A Point2D object to store the results in, or null.
     * @return The point on the quadratic Bezier curve that corresponds to the 
     * given value of {@code t}.
     */
    public static Point2D getQuadBezierPoint(double x1, double y1, double xC, 
            double yC, double x2, double y2, double t, Point2D point){
            // If the given Point2D object is null
        if (point == null)
            point = new Point2D.Double();
        point.setLocation(getQuadBezierPoint(x1,xC,x2,t),
                getQuadBezierPoint(y1,yC,y2,t));
        return point;
    }
    /**
     * 
     * @param p1 The starting point of the curve.
     * @param pC The control point for the curve.
     * @param p2 The end point of the curve.
     * @param t A value between 0 and 1, inclusive, 
     * @param point A Point2D object to store the results in, or null.
     * @return The point on the quadratic Bezier curve that corresponds to the 
     * given value of {@code t}.
     */
    public static Point2D getQuadBezierPoint(Point2D p1, Point2D pC, 
            Point2D p2, double t, Point2D point){
        return getQuadBezierPoint(p1.getX(),p1.getY(),pC.getX(),pC.getY(),
                p2.getX(),p2.getY(),t,point);
    }
    /**
     * 
     * @param curve The curve to get the point on.
     * @param t A value between 0 and 1, inclusive, 
     * @param point A Point2D object to store the results in, or null.
     * @return The point on the quadratic Bezier curve that corresponds to the 
     * given value of {@code t}.
     */
    public static Point2D getQuadBezierPoint(QuadCurve2D curve, double t, 
            Point2D point){
        return getQuadBezierPoint(curve.getX1(),curve.getY1(),curve.getCtrlX(),
                curve.getCtrlY(),curve.getX2(),curve.getY2(),t,point);
    }
    /**
     * 
     * @param p0 The starting point of the curve.
     * @param p1 The control point for the curve.
     * @param p2 The end point of the curve.
     * @param p The point to get the value of t for.
     * @return The t value for the point on the curve, or Double.NAN if not 
     * found on the curve
     */
    public static double getQuadBezierT(double p0,double p1,double p2,double p){
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
                    // Return the smaller of the two
                return Math.min(r0, r1);
                // If only the first root is between 0 and 1, inclusive
            } else if (r0 >= 0 && r0 <= 1){
                return r0;
                // If only the second root is between 0 and 1, inclusive
            } else if (r1 >= 0 && r1 <= 1){
                return r1;
            }
        }
        return Double.NaN;
    }
    /**
     * 
     * @param x1 The x-coordinate of the starting point of the curve.
     * @param y1 The y-coordinate of the starting point of the curve.
     * @param xC The x-coordinate of the control point for the curve.
     * @param yC The y-coordinate of the control point for the curve.
     * @param x2 The x-coordinate of the end point of the curve.
     * @param y2 The y-coordinate of the end point of the curve.
     * @param x The x-coordinate of the point to get.
     * @param point A Point2D object to store the results in, or null.
     * @return The point on the quadratic Bezier curve with the given 
     * x-coordinate, or null if the given x-coordinate does not lie on the 
     * curve.
     */
    public static Point2D getQuadBezierPointForX(double x1, double y1,double xC, 
            double yC, double x2, double y2, double x, Point2D point){
            // Get the t-value for the x-coordinate
        double t = getQuadBezierT(x1,xC,x2,x);
            // If the t value is not a number or out of range (i.e. the point is 
            // not on the curve
        if (t == Double.NaN || t < 0 || t > 1){
                // Set the point to return to NaN
            point.setLocation(Double.NaN, Double.NaN);
                // Return null
            return null;
        }
        return getQuadBezierPoint(x1,y1,xC,yC,x2,y2,t,point);
    }
    /**
     * 
     * @param p1 The starting point of the curve
     * @param pC The control point for the curve
     * @param p2 The end point of the curve.
     * @param x The x-coordinate of the point to get.
     * @param point A Point2D object to store the results in, or null.
     * @return The point on the quadratic Bezier curve with the given 
     * x-coordinate, or null if the given x-coordinate does not lie on the 
     * curve.
     */
    public static Point2D getQuadBezierPointForX(Point2D p1, Point2D pC, 
            Point2D p2, double x, Point2D point){
        return getQuadBezierPointForX(p1.getX(),p1.getY(),pC.getX(),pC.getY(),
                p2.getX(),p2.getY(),x,point);
    }
    /**
     * 
     * @param curve The curve to get the point on.
     * @param x The x-coordinate of the point to get.
     * @param point A Point2D object to store the results in, or null.
     * @return The point on the quadratic Bezier curve with the given 
     * x-coordinate, or null if the given x-coordinate does not lie on the 
     * curve.
     */
    public static Point2D getQuadBezierPointForX(QuadCurve2D curve,double x, 
            Point2D point){
        return getQuadBezierPointForX(curve.getX1(),curve.getY1(),
                curve.getCtrlX(),curve.getCtrlY(),curve.getX2(),curve.getY2(),x,
                point);
    }
    /**
     * 
     * @param x1 The x-coordinate of the starting point of the curve.
     * @param y1 The y-coordinate of the starting point of the curve.
     * @param xC The x-coordinate of the control point for the curve.
     * @param yC The y-coordinate of the control point for the curve.
     * @param x2 The x-coordinate of the end point of the curve.
     * @param y2 The y-coordinate of the end point of the curve.
     * @param y The y-coordinate of the point to get.
     * @param point A Point2D object to store the results in, or null.
     * @return The point on the quadratic Bezier curve with the given 
     * y-coordinate, or null if the given y-coordinate does not lie on the 
     * curve.
     */
    public static Point2D getQuadBezierPointForY(double x1, double y1,double xC, 
            double yC, double x2, double y2, double y, Point2D point){
            // Get the t-value for the y-coordinate
        double t = getQuadBezierT(y1,yC,y2,y);
            // If the t value is not a number or out of range (i.e. the point is 
            // not on the curve
        if (t == Double.NaN || t < 0 || t > 1){
                // Set the point to return to NaN
            point.setLocation(Double.NaN, Double.NaN);
                // Return null
            return null;
        }
        return getQuadBezierPoint(x1,y1,xC,yC,x2,y2,t,point);
    }
    /**
     * 
     * @param p1 The starting point of the curve
     * @param pC The control point for the curve
     * @param p2 The end point of the curve.
     * @param y The y-coordinate of the point to get.
     * @param point A Point2D object to store the results in, or null.
     * @return The point on the quadratic Bezier curve with the given 
     * y-coordinate, or null if the given y-coordinate does not lie on the 
     * curve.
     */
    public static Point2D getQuadBezierPointForY(Point2D p1, Point2D pC, 
            Point2D p2, double y, Point2D point){
        return getQuadBezierPointForY(p1.getX(),p1.getY(),pC.getX(),pC.getY(),
                p2.getX(),p2.getY(),y,point);
    }
    /**
     * 
     * @param curve The curve to get the point on.
     * @param y The y-coordinate of the point to get.
     * @param point A Point2D object to store the results in, or null.
     * @return The point on the quadratic Bezier curve with the given 
     * y-coordinate, or null if the given y-coordinate does not lie on the 
     * curve.
     */
    public static Point2D getQuadBezierPointForY(QuadCurve2D curve,double y, 
            Point2D point){
        return getQuadBezierPointForY(curve.getX1(),curve.getY1(),
                curve.getCtrlX(),curve.getCtrlY(),curve.getX2(),curve.getY2(),y,
                point);
    }
    /**
     * 
     * https://microbians.com/math/Gabriel_Suchowolski_Quadratic_bezier_through_three_points_and_the_-equivalent_quadratic_bezier_(theorem)-.pdf
     * 
     * @param x0 The x-coordinate of the starting point of the curve.
     * @param y0 The y-coordinate of the starting point of the curve.
     * @param x1 The x-coordinate of the point on the curve to pass through.
     * @param y1 The y-coordinate of the point on the curve to pass through.
     * @param x2 The x-coordinate of the end point of the curve.
     * @param y2 The y-coordinate of the end point of the curve.
     * @param point A Point2D object to store the results in, or null.
     * @return The control point for the quadratic Bezier curve that starts at 
     * point ({@code x0}, {@code y0}), passes through point ({@code x1}, {@code 
     * y1}), and ends at point ({@code x2}, {@code y2}).
     */
    public static Point2D getQuadBezierControlPoint(double x0, double y0, 
            double x1, double y1, double x2, double y2, Point2D point){
            // If the given Point2D object is null
        if (point == null)
            point = new Point2D.Double();
            // Get the difference between the first and second points' 
        double tx1 = x0 - x1;   // x-coordinates
            // Get the difference between the first and second points' 
        double ty1 = y0 - y1;   // y-coordinates
            // Get the difference between the second and third points' 
        double tx2 = x2 - x1;   // x-coordinates
            // Get the difference between the second and third points' 
        double ty2 = y2 - y1;   // y-coordinates
            // Get the distance between the first and second points
        double d1 = Math.sqrt(Math.pow(tx1, 2)+Math.pow(ty1, 2));
            // Get the distance between the second and third points
        double d2 = Math.sqrt(Math.pow(tx2, 2)+Math.pow(ty2, 2));
            // Multiply the distance between first and second points by the 
            // distance between the second and third points, and get the square 
        double d3 = Math.sqrt(d1*d2);   // root of the result
        point.setLocation(x1-(d3*(tx1/d1+tx2/d2))/2,y1-(d3*(ty1/d1+ty2/d2))/2);
        return point;
    }
    /**
     * 
     * https://microbians.com/math/Gabriel_Suchowolski_Quadratic_bezier_through_three_points_and_the_-equivalent_quadratic_bezier_(theorem)-.pdf
     * 
     * @param p0 The starting point of the curve.
     * @param p1 The point on the curve to pass through.
     * @param p2 The end point of the curve.
     * @param point A Point2D object to store the results in, or null.
     * @return The control point for the quadratic Bezier curve that starts at 
     * point {@code p0}, passes through point {@code p1}, and ends at point 
     * {@code p2}.
     */
    public static Point2D getQuadBezierControlPoint(Point2D p0, Point2D p1, 
            Point2D p2, Point2D point){
        return getQuadBezierControlPoint(p0.getX(),p0.getY(),
                p1.getX(),p1.getY(),p2.getX(),p2.getY(),point);
    }
    /**
     * 
     * https://microbians.com/math/Gabriel_Suchowolski_Quadratic_bezier_through_three_points_and_the_-equivalent_quadratic_bezier_(theorem)-.pdf
     * 
     * @param x0 The x-coordinate of the starting point of the curve.
     * @param y0 The y-coordinate of the starting point of the curve.
     * @param x1 The x-coordinate of the point on the curve to pass through.
     * @param y1 The y-coordinate of the point on the curve to pass through.
     * @param x2 The x-coordinate of the end point of the curve.
     * @param y2 The y-coordinate of the end point of the curve.
     * @param curve A QuadCurve2D object to store the results in, or null.
     * @return The QuadCurve2D object with the quadratic Bezier curve that 
     * starts at point ({@code x0}, {@code y0}), passes through point 
     * ({@code x1}, {@code y1}), and ends at point ({@code x2}, {@code y2}).
     */
    public static QuadCurve2D getQuadBezierCurve(double x0, double y0, 
            double x1, double y1, double x2, double y2, QuadCurve2D curve){
            // If the given QuadCurve2D object is null
        if (curve == null)
            curve = new QuadCurve2D.Double();
            // Get the control point for the curve
        Point2D point = getQuadBezierControlPoint(x0,y0,x1,y1,x2,y2,null);
            // Set the curve to be between the start and end points, along with 
            // the calculated control point
        curve.setCurve(x0,y0,point.getX(),point.getY(),x2,y2);
        return curve;
    }
    /**
     * 
     * https://microbians.com/math/Gabriel_Suchowolski_Quadratic_bezier_through_three_points_and_the_-equivalent_quadratic_bezier_(theorem)-.pdf
     * 
     * @param p0 The starting point of the curve.
     * @param p1 The point on the curve to pass through.
     * @param p2 The end point of the curve.
     * @param curve A QuadCurve2D object to store the results in, or null.
     * @return The QuadCurve2D object with the quadratic Bezier curve that 
     * starts at point {@code p0}, passes through point {@code p1}, and ends at 
     * point {@code p2}.
     */
    public static QuadCurve2D getQuadBezierCurve(Point2D p0, Point2D p1, 
            Point2D p2, QuadCurve2D curve){
        return getQuadBezierCurve(p0.getX(),p0.getY(),p1.getX(),p1.getY(),
                p2.getX(),p2.getY(),curve);
    }
    /**
     * 
     * @param x1 The x-coordinate of the starting point of the curve segment.
     * @param y1 The y-coordinate of the starting point of the curve segment.
     * @param x2 The x-coordinate of the end point of the curve segment.
     * @param y2 The y-coordinate of the end point of the curve segment.
     * @param src The original quadratic Bezier curve.
     * @param curve A QuadCurve2D object to store the results in, or null.
     * @return 
     */
    public static QuadCurve2D getQuadBezierCurveSegment(double x1, double y1, 
            double x2, double y2, QuadCurve2D src, QuadCurve2D curve){
            // This will get a point on the curve
        Point2D point = null;
            // If the difference between the x-coordinates is greater than or 
            // equal to the difference between the y-coordinates
        if (Math.abs(x2 - x1) >= Math.abs(y2 - y1))
                // Get the point on the curve in between the two x-coordinates
            point = getQuadBezierPointForX(src,(x1+x2)/2,point);
        else    // Get the point on the curve in between the two y-coordinates
            point = getQuadBezierPointForY(src,(y1+y2)/2,point);
            // Return the curve that starts at the given first point, ends at 
            // the second given point, and passes through the calculated point
        return getQuadBezierCurve(x1,y1,point.getX(),point.getY(),x2,y2,curve);
    }
    /**
     * 
     * @param p1 The starting point of the curve segment.
     * @param p2 The end point of the curve segment.
     * @param src The original quadratic Bezier curve.
     * @param curve A QuadCurve2D object to store the results in, or null.
     * @return 
     */
    public static QuadCurve2D getQuadBezierCurveSegment(Point2D p1, Point2D p2, 
            QuadCurve2D src, QuadCurve2D curve){
        return getQuadBezierCurveSegment(p1.getX(),p1.getY(),
                p2.getX(),p2.getY(),src,curve);
    }
    /**
     * 
     * https://web.archive.org/web/20131225210855/http://people.sc.fsu.edu/~jburkardt/html/bezier_interpolation.html
     * 
     * @param p0 The starting point of the curve.
     * @param p1 The first point on the curve to pass through
     * @param p2 The second point on the curve to pass through
     * @param p3 The end point of the curve.
     * @param controlP1 The Point2D object that the first control point will be 
     * stored in.
     * @param controlP2 The Point2D object that the second control point will be 
     * stored in.
     */
    public static void getCubicBezierControlPoints(Point2D p0, Point2D p1, 
            Point2D p2, Point2D p3, Point2D controlP1, Point2D controlP2){
            // Get the y-coordinate for the first control point
        double y1 = (-5*p0.getY()+18*p1.getY()-9*p2.getY()+2*p3.getY())/6;
            // Get the y-coordinate for the second control point
        double y2 = (2*p0.getY()-9*p1.getY()+18*p2.getY()-5*p3.getY())/6;
        controlP1.setLocation(p1.getX(),y1);
        controlP2.setLocation(p2.getX(),y2);
    }
    /**
     * 
     * https://web.archive.org/web/20131225210855/http://people.sc.fsu.edu/~jburkardt/html/bezier_interpolation.html
     * 
     * @param p0 The starting point of the curve.
     * @param p1 The first point on the curve to pass through
     * @param p2 The second point on the curve to pass through
     * @param p3 The end point of the curve.
     * @param curve A CubicCurve2D object to store the results in, or null.
     * @return 
     */
    public static CubicCurve2D getCubicBezierCurve(Point2D p0, Point2D p1, 
            Point2D p2, Point2D p3, CubicCurve2D curve){
            // If the given CubicCurve2D object is null
        if (curve == null)
            curve = new CubicCurve2D.Double();
            // This will get the first control point for the curve
        Point2D pC1 = new Point2D.Double();
            // This will get the second control point for the curve
        Point2D pC2 = new Point2D.Double();
            // Get the control points for the curve
        getCubicBezierControlPoints(p0,p1,p2,p3,pC1,pC2);
            // Set the curve to be between points p0 and p3, using the control 
            // points pC1 and pC2.
        curve.setCurve(p0, pC1, pC2, p3);
        return curve;
    }
    /**
     * https://www.codeproject.com/Articles/31859/Draw-a-Smooth-Curve-through-a-Set-of-2D-Points-wit
     * 
     * @todo Make this reuse any points in the two control point arrays
     * 
     * @param knots A list containing all the knots in the spline
     * @param ctrlPts1 A list to get the first control points
     * @param ctrlPts2 A list to get the second control points
     * @return The number of splines
     */
    public static int getCubicBezierSplineControlPoints(List<Point2D> knots,
            List<Point2D> ctrlPts1, List<Point2D> ctrlPts2){
            // If the knots list is null or there are less than two knots
        if (knots == null || knots.size() < 2)
            throw new IllegalArgumentException("There must be at least 2 knots");
            // The number of control points is the number of knots - 1
        int n = knots.size()-1;
            // Clear the first control points list
        ctrlPts1.clear();
            // Clear the second control points list
        ctrlPts2.clear();
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
            return n;
        }   // Right hand side vector
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
            // Value to use to divide the next right hand side coordinates
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
        }   // Go through the coordinate arrays
        for (double[] arr : coords){
                // Backsubstitution
            for (int i = 1; i < n; i++){
                arr[n-i-1] -= tmp[n-i] * arr[n-i];
            }
        }   // Populate the control point arrays
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
        return n;
    }
    /**
     * 
     * https://www.codeproject.com/Articles/31859/Draw-a-Smooth-Curve-through-a-Set-of-2D-Points-wit
     * 
     * @todo Make this reuse any curves that are already in the curves list
     * 
     * @param knots A list containing all the knots in the spline
     * @param curves A list to store the curves in the spline, or null
     * @return 
     */
    public static List<CubicCurve2D> getCubicBezierSplineCurves(
            List<Point2D> knots, List<CubicCurve2D> curves){
            // This will get the first control points
        ArrayList<Point2D> ctrlPts1 = new ArrayList<>();
            // This will get the second control points
        ArrayList<Point2D> ctrlPts2 = new ArrayList<>();
            // Calculate the first and second control points of the splines, and 
            // get the number of control points
        int n = getCubicBezierSplineControlPoints(knots,ctrlPts1,ctrlPts2);
            // If the given list to store the curves is null
        if (curves == null)
                curves = new ArrayList<>();
        else    // Clear the list
            curves.clear();
            // Go through the control points
        for (int i = 0; i < n; i++){
                // Create a cubic curve to store the current curve
            CubicCurve2D curve = new CubicCurve2D.Double();
                // Set the curve to be from the current knot to the next knot, 
                // using the current first and second control points
            curve.setCurve(knots.get(i), ctrlPts1.get(i), ctrlPts2.get(i), 
                    knots.get(i+1));
                // Add the curve to the list
            curves.add(curve);
        }
        return curves;
    }
    /**
     * This approximates the point where the two lines represented by the 
     * equations {@code getY1} and {@code getY2} intersect, using the given 
     * {@code x1} and {@code x2} x-coordinates for the upper and lower bounds of 
     * the range to check. This uses the {@code resolution} to determine how 
     * precise the point of intersection will be.
     * @param x1 The x-coordinate of the start points of the two line segments.
     * @param x2 The x-coordinate of the end points of the two line segments.
     * @param getY1 The equation for the first line.
     * @param getY2 The equation for the second line.
     * @param resolution The resolution for the point to return. This determines 
     * how precise the point of intersection will be. This must be a value 
     * greater than 0.
     * @param point A Point2D object to store the results in, or null.
     * @return A rough approximation of the point at which two lines intersect.
     * @throws IllegalArgumentException If the {@code resolution} is less than 
     * or equal to zero.
     * @throws NullPointerException If either {@code getY1} or {@code getY2} is 
     * null.
     * @see #getLineIntersection(double, double, DoubleUnaryOperator, 
     * DoubleUnaryOperator, Point2D) 
     */
    public static Point2D getLineIntersection(double x1, double x2, 
            DoubleUnaryOperator getY1, DoubleUnaryOperator getY2,int resolution,
            Point2D point){
            // If the resolution is less than or equal to zero
        if (resolution <= 0)
            throw new IllegalArgumentException("Line intersection resolution "
                    + "must be greater than 0 ("+resolution+" <= 0)");
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
        point.setLocation(tempX, 
                (getY1.applyAsDouble(tempX)+getY2.applyAsDouble(tempX))/2.0);
        return point;
    }
    /**
     * his approximates the point where the two lines represented by the 
     * equations {@code getY1} and {@code getY2} intersect, using the given 
     * {@code x1} and {@code x2} x-coordinates for the upper and lower bounds of 
     * the range to check. This uses a resolution of {@link 
     * DEFAULT_LINE_INTERSECTION_RESOLUTION} to determine how precise the point 
     * of intersection will be. This is equivalent to calling {@link 
     * #getLineIntersection(double, double, DoubleUnaryOperator, 
     * DoubleUnaryOperator, int, Point2D) getLineIntersection(x1, x2, getY1, 
     * getY2, DEFAULT_LINE_INTERSECTION_RESOLUTION, point)}.
     * @param x1 The x-coordinate of the start points of the two line segments.
     * @param x2 The x-coordinate of the end points of the two line segments.
     * @param getY1 The equation for the first line.
     * @param getY2 The equation for the second line.
     * @param point A Point2D object to store the results in, or null.
     * @return A rough approximation of the point at which two lines intersect.
     * @throws IllegalArgumentException If the {@code resolution} is less than 
     * or equal to zero.
     * @throws NullPointerException If either {@code getY1} or {@code getY2} is 
     * null.
     * @see #getLineIntersection(double, double, DoubleUnaryOperator, 
     * DoubleUnaryOperator, int, Point2D) 
     * @see #DEFAULT_LINE_INTERSECTION_RESOLUTION
     */
    public static Point2D getLineIntersection(double x1, double x2, 
            DoubleUnaryOperator getY1, DoubleUnaryOperator getY2,Point2D point){
        return getLineIntersection(x1,x2,getY1,getY2,
                DEFAULT_LINE_INTERSECTION_RESOLUTION,point);
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
     * 
     * @todo Test this method
     * 
     * @param x1 The x-coordinate of the start point of the first specified 
     * line segment.
     * @param y1 The y-coordinate of the start point of the first specified 
     * line segment.
     * @param x2 The x-coordinate of the end point of the first specified 
     * line segment.
     * @param y2 The y-coordinate of the end point of the first specified 
     * line segment.
     * @param x3 The x-coordinate of the start point of the second specified 
     * line segment.
     * @param y3 The y-coordinate of the start point of the second specified 
     * line segment.
     * @param x4 The x-coordinate of the end point of the second specified 
     * line segment.
     * @param y4 The y-coordinate of the end point of the second specified 
     * line segment.
     * @param point A Point2D object to store the results in, or null.
     * @return The point at which the two given lines intersect, or null if the 
     * two lines do not intersect.
     */
    public static Point2D getLineIntersection(double x1, double y1, double x2, 
            double y2, double x3, double y3, double x4,double y4,Point2D point){
            // If the two lines do not intersect
        if (!Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4)){
                // If the given Point2D object is not null
            if (point != null)
                        // Set the point to return to NaN
                    point.setLocation(Double.NaN, Double.NaN);
                    // Return null
                return null;
        }   // If the given Point2D object is null
        if (point == null)
            point = new Point2D.Double();
            // Get the difference between the x-coordinates of the first line 
        double dx1 = x1 - x2;   // segment
            // Get the difference between the x-coordinates of the second line 
        double dx2 = x3 - x4;   // segment
            // Get the difference between the y-coordinates of the first line 
        double dy1 = y1 - y2;   // segment
            // Get the difference between the y-coordinates of the second line 
        double dy2 = y3 - y4;   // segment
            
        double l1 = (x1 * y2) - (y1 * x2);
        double l2 = (x3 * y4) - (y3 * x4);
        double a = (dx1 * dy2) - (dy1 * dx2);
        point.setLocation(((l1*dx2)-(dx1*l2))/a, ((l1*dy2)-(dy1*l2))/a);
        return point;
    }
    /**
     * 
     * @param p1 The start point of the first specified line segment.
     * @param p2 The end point of the first specified line segment.
     * @param p3 The start point of the second specified line segment.
     * @param p4 The end point of the second specified line segment.
     * @param point A Point2D object to store the results in, or null.
     * @return The point at which the two given lines intersect, or null if the 
     * two lines do not intersect.
     */
    public static Point2D getLineIntersection(Point2D p1, Point2D p2, 
            Point2D p3, Point2D p4, Point2D point){
        return getLineIntersection(p1.getX(),p1.getY(),p2.getX(),p2.getY(),
                p3.getX(),p3.getY(),p4.getX(),p4.getY(),point);
    }
    /**
     * 
     * @param line1 The first specified line segment.
     * @param line2 The second specified line segment.
     * @param point A Point2D object to store the results in, or null.
     * @return The point at which the two given lines intersect, or null if the 
     * two lines do not intersect.
     */
    public static Point2D getLineIntersection(Line2D line1, Line2D line2, 
            Point2D point){
        return getLineIntersection(
                line1.getX1(),line1.getY1(),line1.getX2(),line1.getY2(),
                line2.getX1(),line2.getY1(),line2.getX2(),line2.getY2(),point);
    }
    
    
    
    /**
     * This returns a Map that maps the names of the segment types to the 
     * constants that represent those segment types.
     * @return An unmodifiable map of the names of the segment types.
     */
    private static Map<Integer, String> getSegTypes(){
            // A map to store the names of the segment types
        HashMap<Integer,String> segTypes = new HashMap<>();
        segTypes.put(PathIterator.SEG_MOVETO, "SEG_MOVETO");
        segTypes.put(PathIterator.SEG_LINETO, "SEG_LINETO");
        segTypes.put(PathIterator.SEG_QUADTO, "SEG_QUADTO");
        segTypes.put(PathIterator.SEG_CUBICTO,"SEG_CUBICTO");
        segTypes.put(PathIterator.SEG_CLOSE,  "SEG_CLOSE");
        return Collections.unmodifiableMap(segTypes);
    }
    /**
     * This is a map that maps the constants for the segment types in a {@code 
     * PathIterator} to the names of the constants.
     * @see PathIterator#SEG_MOVETO
     * @see PathIterator#SEG_LINETO
     * @see PathIterator#SEG_QUADTO
     * @see PathIterator#SEG_CUBICTO
     * @see PathIterator#SEG_CLOSE
     */
    public static final Map<Integer, String> SEGMENT_TYPE_NAMES = getSegTypes();
    /**
     * This prints the segment types and coordinates for those segments for the 
     * given path iterator. This is mainly to be used for debugging purposes.
     * @param pathItr The path iterator to print the segmants of
     */
    public static void printPathIterator(PathIterator pathItr){
            // An array of 6 doubles to get the coordinates
        double[] coords = new double[6];
            // While the path iterator is not done
        while(!pathItr.isDone()){
                // Get the type and coordinates for the segment
            int type = pathItr.currentSegment(coords);
                // This will be the format to use to format the coordinate pairs
            String format = ": ";
                // Determine what coordinates to include based off the type
            switch(type){
                    // If the path closes at this segment
                case(PathIterator.SEG_CLOSE):
                        // Use a blank string as the format to not include any 
                    format = "";    // coordinate pairs
                    break;
                    // Default any unknown segments types to show all three 
                default:    // coordinate pairs
                    // If the segment type is a cubic bezier curve
                case(PathIterator.SEG_CUBICTO):
                    format += "(%9.5f, %9.5f), ";
                    // If the segment type is a quadratic bezier curve
                case(PathIterator.SEG_QUADTO):
                    format += "(%9.5f, %9.5f), ";
                    // If the segment type is a line
                case(PathIterator.SEG_LINETO):
                    // If the segment is just moving the point
                case(PathIterator.SEG_MOVETO):
                    format += "(%9.5f, %9.5f)";
            }   // Print out the type and coordinate pairs
            System.out.printf("%11s"+format,
                        // Get the type name, defaulting to unknown for unknown 
                        // segment types
                    SEGMENT_TYPE_NAMES.getOrDefault(type, "UNKNOWN"),
                    coords[0],coords[1],coords[2],
                    coords[3],coords[4],coords[5]);
            System.out.println();
                // Advance the path iterator
            pathItr.next();
        }
    }
    /**
     * 
     * @param shape
     * @param tx 
     */
    public static void printPathIterator(Shape shape, AffineTransform tx){
        printPathIterator(shape.getPathIterator(tx));
    }
    /**
     * 
     * @param shape 
     */
    public static void printPathIterator(Shape shape){
        printPathIterator(shape,null);
    }
    /**
     * 
     * @param name The name of the shape, or null
     * @param shape The shape to print the bounds of
     */
    public static void printShape(String name, Shape shape){
            // Get the bounds of the shape
        Rectangle2D bounds = shape.getBounds2D();
            // If the given name of the shape is not null
        if (name != null)
            System.out.print(name+": ");
        System.out.println(bounds + ", Center=(" + 
                bounds.getCenterX() + ", " + bounds.getCenterY()+"), Max=("+
                bounds.getMaxX() + ", " + bounds.getMaxY()+")");
    }
    /**
     * 
     * @param name
     * @param curve 
     */
    public static void printQuadCurve(String name, QuadCurve2D curve){
            // If the given name of the curve is not null
        if (name != null)
            System.out.print(name+": ");
        System.out.println("P1=" + curve.getP1()+", CtrlPt="+curve.getCtrlPt()+
                ", P2="+curve.getP2());
    }
    /**
     * 
     * @param name
     * @param curve 
     */
    public static void printCubicCurve(String name, CubicCurve2D curve){
            // If the given name of the curve is not null
        if (name != null)
            System.out.print(name+": ");
        System.out.println("P1=" + curve.getP1()+
                ", CtrlP1="+curve.getCtrlP1()+
                ", CtrlP2="+curve.getCtrlP2()+
                ", P2="+curve.getP2());
    }
}
