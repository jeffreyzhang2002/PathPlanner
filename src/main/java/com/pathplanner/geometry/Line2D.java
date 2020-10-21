package com.pathplanner.geometry;

public class Line2D<E extends Number>
{
    private Point2D<E> p1, p2;

    public Line2D(Point2D<E> p1, Point2D<E> p2)
    {
       setLine(p1,p2);
    }

    public Line2D(Line2D<E> line)
    {
        setLine(line);
    }

    public Point2D<E> getP1()
    { return p1; }

    public Point2D<E> p1()
    { return p1; }

    public Point2D<E> getP2()
    { return p2; }

    public Point2D<E> p2()
    { return p2; }

    public void setP1(Point2D<E> p1)
    {
        if(p1 == null)
            throw new IllegalArgumentException("non-null value expected for p1");
        this.p1 = p1;
    }

    public void setP2(Point2D<E> p2)
    {
        if(p2 == null)
            throw new IllegalArgumentException("non-null value expected for p2");
        this.p2 = p2;
    }

    public void setLine(Point2D<E> p1, Point2D<E> p2)
    {
        setP1(p2);
        setP2(p2);
    }

    public void setLine(Line2D<E> line)
    {
        p1 = line.p1();
        p2 = line.p2();
    }

    public boolean intersects(Line2D line)
    { return Line2D.linesIntersect(this,line); }

    public double ptSegDist(Point2D point)
    { return ptSegDist(this, point); }

    public double ptSegDistSq(Point2D point)
    { return ptSegDistSq(this, point); }

    private static double area(Point2D p1, Point2D p2, Point2D p3)
    {
         return (p2.X().doubleValue() - p1.X().doubleValue()) * (p3.Y().doubleValue() - p1.Y().doubleValue())
                 - (p3.X().doubleValue() - p1.X().doubleValue()) * (p2.Y().doubleValue() - p1.Y().doubleValue());
    }

    private static boolean between(Point2D p1, Point2D p2, Point2D p3)
    {
        if (p1.X().doubleValue() != p2.X().doubleValue())
            return (p1.X().doubleValue() <= p3.X().doubleValue() && p3.X().doubleValue() <= p2.X().doubleValue())
                    || (p1.X().doubleValue() >= p3.X().doubleValue() && p3.X().doubleValue() >= p2.X().doubleValue());
        else
            return (p1.Y().doubleValue() <= p3.Y().doubleValue() && p3.Y().doubleValue() <= p2.Y().doubleValue())
                    || (p1.Y().doubleValue() >= p3.Y().doubleValue() && p3.Y().doubleValue() >= p2.Y().doubleValue());
    }

    public static boolean linesIntersect(Line2D line1, Line2D line2)
    {
        Point2D p1 = line1.p1(), p2 = line1.p2(), p3 = line2.p1(), p4 = line2.p2();
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

    public static double ptSegDistSq(Line2D line, Point2D point)
    {
        Point2D p1 = line.p1(), p2 = line.p2();
        double pd2 = Math.pow((p1.X().doubleValue() - p2.getX().doubleValue()), 2) + Math.pow((p1.getY().doubleValue() - p2.getY().doubleValue()), 2);
        double x, y;

        if (pd2 == 0)
        {
            x = p1.X().doubleValue();
            y = p2.Y().doubleValue();
        }
        else
        {
            double u = ((point.X().doubleValue() - p1.X().doubleValue()) * (p2.X().doubleValue() - p1.X().doubleValue())
                    + (point.Y().doubleValue() - p1.Y().doubleValue()) * (p2.Y().doubleValue() - p1.Y().doubleValue())) / pd2;
            if (u < 0)
            {
                x = p1.X().doubleValue();
                y = p1.Y().doubleValue();
            }
            else if (u > 1.0)
            {
                x = p2.X().doubleValue();
                y = p2.Y().doubleValue();
            }
            else
            {
                x = p1.X().doubleValue() + u * (p2.X().doubleValue() - p1.X().doubleValue());
                y = p1.Y().doubleValue() + u * (p2.Y().doubleValue() - p1.Y().doubleValue());
            }
        }
        return Math.pow((x - point.X().doubleValue()),2) + Math.pow((y - point.Y().doubleValue()),2);
    }

    public static double ptSegDist(Line2D line, Point2D point)
    { return Math.sqrt(ptSegDistSq(line, point)); }

    public Point2D<Double> getMidPoint()
    {
        return new Point2D<Double>((p1.getX().doubleValue() + p2.getX().doubleValue())/2 , (p1.getY().doubleValue() + p2.getY().doubleValue())/2);
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof Line2D))
            return false;
        Line2D l = (Line2D) o;
        return l.p1().equals(p1) && l.p2().equals(p2);
    }

    public int hashCode()
    { return new Point2D(p1.hashCode(), p2.hashCode()).hashCode(); }

    public String toString()
    { return p1.toString() + " ->" + p2.toString(); }
}
