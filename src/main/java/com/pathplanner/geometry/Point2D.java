package com.pathplanner.geometry;

import java.io.Serializable;

/**
 * The Point2D class defines a point represented as (x,y) coordinate on a 2D plane. The precision of the point is determined
 * by the Generic T. X and Y coordinate are non-null values and cannot be NaN.
 * @author Jeffrey Zhang
 * @see Number
 * @version 0.0
 * @since 10/12/2020
 * @param <T> the precision of the point. Must be a subclass of java.lang.Number class
 * @see java.lang.Number
 */
public class Point2D<T extends Number> implements Cloneable, Serializable
{
    //The X and Y coordinates of the Point2D
    private T x, y;

    /**
     * Constructs a Point2D at the specified x and y coordinates
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point2D(T x, T y)
    { setLocation(x, y); }

    /**
     * Constructs a Point2D at the specified position given by Point2D p. The precision of value of p
     * remain the same but the values of x and y stored in the current point will match the preicison speicifed by T
     * in the parameter should not change.
     * @param p the point to copy
     */
    public Point2D(Point2D<T> p)
    { setLocation(p); }

    /**
     * Get the X coordinate T in precision.
     * @return the X coordinate
     */
    public T getX()
    { return x; }

    /**
     * Get the Y coordinate in T precision.
     * @return the Y Coordinate
     */
    public T getY()
    { return y; }

    /**
     * Sets the X coordinate of the point to the new X coordinate with precision T.
     * @param x the new X coordinate
     * @throws IllegalArgumentException if x is null or NaN
     * @see IllegalArgumentException
     */
    public void setX(T x)
    {
        if(x == null || Double.isNaN(x.doubleValue()))
            throw new IllegalArgumentException("X: " + x + " Null and NaN are invalid inputs");
        this.x = x;
    }

    /**
     * Sets the Y coordinate of the point to the new Y coordinate with precision T.
     * @param y the new Y coordinate
     * @throws IllegalArgumentException if y is null or NaN
     * @see IllegalArgumentException
     */
    public void setY(T y)
    {
        if(y == null || Double.isNaN(y.doubleValue()))
            throw new IllegalArgumentException("Y: " + y + " Null and NaN are invalid inputs");
        this.y = y;
    }

    /**
     * Sets the location of this point to the new X and Y coordinates. Both X and Y must have precision T.
     * @param x the new X coordinate
     * @param y the new Y coordinate
     * @throws IllegalArgumentException if x or y is null or NaN
     * @see IllegalArgumentException
     */
    public void setLocation(T x, T y)
    {
        setX(x);
        setY(y);
    }

    /**
     * Sets the location of this point to the new point. The new point must have the same precision as the current point.
     * @param p the new Point
     */
    public void setLocation(Point2D<T> p)
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
    @Override
    public boolean equals(Object o)
    { return(o instanceof Point2D && ((Point2D) o).x.doubleValue() == this.x.doubleValue()
            && ((Point2D) o).y.doubleValue() == this.y.doubleValue()); }

    /**
     * Returns the hashcode for this point using the same hashing formula found in java.awt.geom.Point2D.
     * @return the Hashcode of the current point.
     */
    @Override
    public int hashCode()
    {
        long l = java.lang.Double.doubleToLongBits(y.doubleValue());
        l = l * 31 ^ java.lang.Double.doubleToLongBits(x.doubleValue());
        return (int) ((l >> 32) ^ l);
    }

    /**
     * Returns a string representation of Point2D in the format: (x,y).
     * @return a String representation of this object
     */
    @Override
    public String toString()
    { return "(" + x.toString() + "," + y.toString() + ")"; }

    /**
     * Creates a field for field copy of the Point2D
     * @return Point2D
     */
    @Override
    public Point2D<T> clone()
    { return new Point2D(x,y); }

    /**
     * Returns the distance between Point a and Point b no matter what precision each Point is in
     * @param a the first point
     * @param b the second point
     * @return the distance between point a and b
     */
    public static double distance(Point2D a, Point2D b)
    { return Math.hypot(a.x.doubleValue() - b.x.doubleValue(), a.y.doubleValue() - b.y.doubleValue()); }

    /**
     * returns the squared distance between Point a and Point b no matter what precision each Point is in
     * @param a the first point
     * @param b the second point
     * @return the squared distance between point a and b
     */
    public static double distanceSq(Point2D a, Point2D b)
    { return Math.pow(a.x.doubleValue() - b.x.doubleValue(), 2) + Math.pow(a.y.doubleValue() - b.y.doubleValue(), 2); }

}