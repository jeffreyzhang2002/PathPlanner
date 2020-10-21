package com.pathplanner.geometry;

/**
 * The Point2D defines a point represented as (x,y) coordinate on a 2D plane. The specificity of the point is determined
 * by the Generic E. X and Y coordinate are non-null values and cannot be NaN.
 * @author Jeffrey Zhang
 * @see Number
 * @version 0.0
 * @since 10/12/2020
 * @param <E> the specificity of the point. Can be Integer, Double, Float, etc or any class that extends java.lang.Number
 */
public class Point2D<E extends Number> implements Cloneable
{
    private E x, y;

    /**
     * Constructs a Point2D at the specified x and y coordinates
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point2D(E x, E y)
    { setLocation(x, y); }

    /**
     * Constructs a Point2D at the specified Point2D.
     * @param p the point to copy
     */
    public Point2D(Point2D<E> p)
    { setLocation(p); }

    /**
     * Convenience method to get the X coordinate of the point. Analogous to the to the {@link #getX()} method.
     * @return The X coordinate
     */
    public E X()
    { return x; }

    /**
     * Convenience method to get the Y coordinate of the point. Analogous to the to the {@Link #getY()} method.
     * @return The Y coordinate
     */
    public E Y()
    { return y; }

    /**
     * Get the X coordinate E in precision.
     * @return the X coordinate
     */
    public E getX()
    { return x; }

    /**
     * Get the Y coordinate in E precision.
     * @return the Y Coordinate
     */
    public E getY()
    { return y; }

    /**
     * Sets the X coordinate of the point to the new X coordinate with precision E.
     * @param x the new X coordinate
     * @throws IllegalArgumentException if x is null or NaN
     * @see IllegalArgumentException
     */
    public void setX(E x)
    {
        if(x == null || Double.isNaN(x.doubleValue()))
            throw new IllegalArgumentException("non-null value expected");
        this.x = x;
    }

    /**
     * Sets the Y coordinate of the point to the new Y coordinate with precision E.
     * @param y the new Y coordinate
     * @throws IllegalArgumentException if y is null or NaN
     * @see IllegalArgumentException
     */
    public void setY(E y)
    {
        if(y == null || Double.isNaN(y.doubleValue()))
            throw new IllegalArgumentException("non-null value expected");
        this.y = y;
    }

    /**
     * Sets the location of this point to the new X and Y coordinates. Both X and Y must have precision E.
     * @param x the new X coordinate
     * @param y the new Y coordinate
     * @throws IllegalArgumentException if x or y is null or NaN
     * @see IllegalArgumentException
     */
    public void setLocation(E x, E y)
    {
        setX(x);
        setY(y);
    }

    /**
     * Sets the location of this point to the new point. The new point must have the same precision as the current point.
     * @param p the new Point
     */
    public void setLocation(Point2D<E> p)
    {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Returns the distance between the current point and a second point p.
     * @param p the second point
     * @return the distance between the current point and the second point
     */
    public double distance(Point2D p)
    { return distance(this, p); }

    /**
     * Returns the distance squared between the current point and a second point p.
     * @param p the second point
     * @return the squared distance between the current point and the second point
     */
    public double distanceSq(Point2D p)
    { return distanceSq(this, p); }

    /**
     * Compares the two points for equality. Returns true if they have the same x and y coordinate no matter what
     * the precision they are stored in
     * @param o the point to compare
     * @return true if they are equal
     */
    public boolean equals(Object o)
    {
        return(o instanceof Point2D && ((Point2D) o).X().doubleValue() == this.X().doubleValue()
            && ((Point2D) o).Y().doubleValue() == this.Y().doubleValue());
    }

    /**
     * Returns the hashcode for this point using the same hashing formula found in java.awt.geom.Point2D.
     * @return the Hashcode of the current point.
     */
    public int hashCode()
    {
        long l = java.lang.Double.doubleToLongBits(Y().doubleValue());
        l = l * 31 ^ java.lang.Double.doubleToLongBits(X().doubleValue());
        return (int) ((l >> 32) ^ l);
    }

    /**
     * Returns a string representation of Point2D in the format: (x,y).
     * @return a String representation of this object
     */
    public String toString()
    { return "(" + x.toString() + "," + y.toString() + ")"; }

    /**
     * Returns the distance between Point a and Point b no matter what precision each Point is in
     * @param a the first point
     * @param b the second point
     * @return the distance between point a and b
     */
    public static double distance(Point2D a, Point2D b)
    { return Math.hypot(a.x.doubleValue() - b.x.doubleValue(), a.y.doubleValue() - b.y.doubleValue()); }

    /**
     * Returns the squared distance between Point a and Point b no matter what precision each Point is in
     * @param a the first point
     * @param b the seond point
     * @return the squared distance between point a and b
     */
    public static double distanceSq(Point2D a, Point2D b)
    { return Math.pow(a.x.doubleValue() - b.x.doubleValue(), 2) + Math.pow(a.y.doubleValue() - b.y.doubleValue(), 2); }
}