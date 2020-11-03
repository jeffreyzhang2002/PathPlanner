package com.pathplanner.geometry;

import java.io.Serializable;

/**
 * The Line2D class defines a line segment represented as two coordinate P1 --> P2 on a 2D plane. The precision of the
 * points are determined by the Generic T. P1 and P2 coordinate are two non null points on the plane.
 * @param <T> the precision of the point. Must be a subclass of java.lang.Number class
 * @see java.lang.Number
 * @see Point2D
 */
public class Line2D<T extends Number> implements Cloneable, Serializable
{
    // the first and second points of a line segment. Line segments do not have a direction and points are arbitrary
    private Point2D<T> p1, p2;

    public Line2D(T x1, T y1, T x2, T y2)
    { setLine(x1, y1, x2, y2); }

    /**
     * Creates an instance of a Line2D with P1 and P2 and the given points. Subsequent changes to p1 and p2 will
     * have no impact on the Line2D
     * @param p1 the first point on the Line2D
     * @param p2 the second point on the Line2D
     * @throws NullPointerException if p1 or p2 is null
     */
    public Line2D(Point2D<T> p1, Point2D<T> p2)
    { setLine(p1,p2); }

    /**
     * Creates an instance of a Line2D with another Line with the same precision. Subsequent changes to line
     * will have no impact on the current class
     * @param line another Line
     * @throws NullPointerException if line is null
     */
    public Line2D(Line2D<T> line)
    { setLine(line); }

    /**
     * Gets the P1 point of the line with the same precision as the Line2D
     * @return P1
     */
    public Point2D<T> getP1()
    { return p1; }

    /**
     * Gets the P2 point of the line with the same precision as the Line2D
     * @return P2
     */
    public Point2D<T> getP2()
    { return p2; }

    /**
     * Sets the P1 point on the line to the give Point2D. The give Point must have the same precision as the Line
     * @param p1 the point P1 will be set to
     */
    public void setP1(Point2D<T> p1)
    {
        if(p1 == null)
            throw new IllegalArgumentException("P1: null P1 can not be null");
        this.p1 = p1.clone();
    }

    /**
     * Sets the P2 point on the line to the give Point2D. The give Point must have the same precision as the Line
     * @param p2 the point P1 will be set to
     */
    public void setP2(Point2D<T> p2)
    {
        if(p2 == null)
            throw new IllegalArgumentException("P2: null P2 can not be null");
        this.p2 = p2.clone();
    }

    /**
     * Sets the Line2D to have the points p1 and p2
     * @param p1 the p1 point of the line
     * @param p2 the p2 point of the line
     */
    public void setLine(Point2D<T> p1, Point2D<T> p2)
    {
        setP1(p1);
        setP2(p2);
    }

    /**
     * Sets the current Line2D to be equal to the other Line2D
     * @param line
     */
    public void setLine(Line2D<T> line)
    {
        if(line == null)
            throw new IllegalArgumentException("Line can not be null");
        p1 = line.p1.clone();
        p2 = line.p2.clone();
    }

    public void setLine(T x1, T y1, T x2, T y2)
    {
        p1 = new Point2D<>(x1, y1);
        p2 = new Point2D<>(x1, y2);
    }

    /**
     * Checks if the current Line intersects another Line.
     * @param line another Line
     * @return true if they intersect false otherwise
     */
    public boolean intersects(Line2D line)
    { return Line2D.linesIntersect(this,line); }

    /**
     * Gets the distance a point is from the Line2D
     * @param point a point away from the line segment
     * @return the distance a point is away from the Line2D
     */
    public double ptSegDist(Point2D point)
    { return ptSegDist(this, point); }

    /**
     * get the squared distance between a point and the line
     * @param point a point of the line
     * @return the squared distance between the point and line
     */
    public double ptSegDistSq(Point2D point)
    { return ptSegDistSq(this, point); }

    /**
     * Get the MidPoint of the line
     * @return the midpoint of the line
     */
    public Point2D<Double> getMidPoint()
    { return new Point2D<Double>((p1.getX().doubleValue() + p2.getX().doubleValue())/2 , (p1.getY().doubleValue() + p2.getY().doubleValue())/2); }

    /**
     * Find the point of intersection point between the current Line and another Line2D
     * @param line another Line
     * @return the intersection point
     */
    public Point2D<Double> intersectionPoint(Line2D line)
    { return intersectionPoint(this, line);}

    /**
     * Checks if the current line is equal to another Line. A line is equal if they have the same endpoints
     * @param o another Line
     * @return true if they are equal false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Line2D))
            return false;
        Line2D l = (Line2D) o;
        return l.p1.equals(p1) && l.p2.equals(p2);
    }

    /**
     * Generates the Hashcode of the line
     * @return the hascode
     */
    @Override
    public int hashCode()
    { return new Point2D(p1.hashCode(), p2.hashCode()).hashCode(); }

    /**
     * Creates a string representation of the Line2D. This displays the lines as p1 -> p2
     * @return the String representation of the line
     */
    @Override
    public String toString()
    { return p1.toString() + " -> " + p2.toString(); }

    /**
     * Creates a field for field clone of the line. Each Point2D within is also cloned
     * @return a clone of the Line
     */
    @Override
    public Line2D<T> clone()
    { return new Line2D(p1,p2); }

    private static double area(Point2D p1, Point2D p2, Point2D p3)
    {
        return (p2.getX().doubleValue() - p1.getX().doubleValue()) * (p3.getY().doubleValue() - p1.getY().doubleValue())
                - (p3.getX().doubleValue() - p1.getX().doubleValue()) * (p2.getY().doubleValue() - p1.getY().doubleValue());
    }

    private static boolean between(Point2D p1, Point2D p2, Point2D p3)
    {
        if (p1.getX().doubleValue() != p2.getX().doubleValue())
            return (p1.getX().doubleValue() <= p3.getX().doubleValue() && p3.getX().doubleValue() <= p2.getX().doubleValue())
                    || (p1.getX().doubleValue() >= p3.getX().doubleValue() && p3.getX().doubleValue() >= p2.getX().doubleValue());
        else
            return (p1.getY().doubleValue() <= p3.getY().doubleValue() && p3.getY().doubleValue() <= p2.getY().doubleValue())
                    || (p1.getY().doubleValue() >= p3.getY().doubleValue() && p3.getY().doubleValue() >= p2.getY().doubleValue());
    }

    /**
     * Checks whether Line1 intersects Line 2
     * @param line1 the first line
     * @param line2 the second line
     * @return true if they intersect false otherwise
     */
    public static boolean linesIntersect(Line2D line1, Line2D line2)
    {
        Point2D p1 = line1.p1, p2 = line1.p2, p3 = line2.p1, p4 = line2.p2;
        double a1, a2, a3, a4;

        if ((a1 = area(p1, p2, p3)) == 0.0)
            if (between(p1, p2, p3))
                return true;
            else
            if (area(p1, p2, p4) == 0.0)
                return between(p3, p4, p1) || between (p3, p4, p2);
            else
                return false;
        else if ((a2 = area(p1, p2, p4)) == 0.0)
            return between(p1, p2, p4);

        if ((a3 = area(p3, p4, p1)) == 0.0)
            if (between(p3, p4, p1))
                return true;
            else
            if (area(p3, p4, p2) == 0.0)
                return between(p1, p2, p3) || between (p1, p2, p4);
            else
                return false;
        else if ((a4 = area(p3, p4, p2)) == 0.0)
            return between(p3, p4, p2);
        else
            return ((a1 > 0.0) ^ (a2 > 0.0)) && ((a3 > 0.0) ^ (a4 > 0.0));
    }

    /**
     * Gets the squared distance between a point and a line
     * @param line a line segment
     * @param point a point of the line
     * @return the squared the distance
     */
    public static double ptSegDistSq(Line2D line, Point2D point)
    {
        Point2D p1 = line.p1, p2 = line.p2;
        double pd2 = Math.pow((p1.getX().doubleValue() - p2.getX().doubleValue()), 2) + Math.pow((p1.getY().doubleValue() - p2.getY().doubleValue()), 2);
        double x, y;

        if (pd2 == 0)
        {
            x = p1.getX().doubleValue();
            y = p2.getY().doubleValue();
        }
        else
        {
            double u = ((point.getX().doubleValue() - p1.getX().doubleValue()) * (p2.getX().doubleValue() - p1.getX().doubleValue())
                    + (point.getY().doubleValue() - p1.getY().doubleValue()) * (p2.getY().doubleValue() - p1.getY().doubleValue())) / pd2;
            if (u < 0)
            {
                x = p1.getX().doubleValue();
                y = p1.getY().doubleValue();
            }
            else if (u > 1.0)
            {
                x = p2.getX().doubleValue();
                y = p2.getY().doubleValue();
            }
            else
            {
                x = p1.getX().doubleValue() + u * (p2.getX().doubleValue() - p1.getX().doubleValue());
                y = p1.getY().doubleValue() + u * (p2.getY().doubleValue() - p1.getY().doubleValue());
            }
        }
        return Math.pow((x - point.getX().doubleValue()),2) + Math.pow((y - point.getY().doubleValue()),2);
    }

    public static double ptSegDist(Line2D line, Point2D point)
    { return Math.sqrt(ptSegDistSq(line, point)); }

    public static Point2D<Double> intersectionPoint(Line2D l1, Line2D l2)
    {
        return null;
        //TODO implememnt method
    }
}
