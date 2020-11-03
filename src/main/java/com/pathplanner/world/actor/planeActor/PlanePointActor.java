package com.pathplanner.world.actor.planeActor;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.properties.Properties;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlanePointActor extends PlaneActor
{
    private Point2D<Double> point;

    public PlanePointActor(Point2D<Double> point)
    {
        if(point == null)
            throw new IllegalArgumentException("Non null argument expected");
        this.point = point;
    }

    public PlanePointActor(Point2D<Double> point, Properties properties)
    {
        super(properties);
        if(point == null)
            throw new IllegalArgumentException("Non null argument expected");
        this.point = point;
    }

    public PlanePointActor(Double x, Double y)
    { this.point = new Point2D(x,y); }

    public PlanePointActor(Double x, Double y, Properties properties)
    {
        super(properties);
        this.point = new Point2D(x,y);
    }

    @Override
    public List<Point2D<Double>> getVertexPoints() {
        List<Point2D<Double>> list = new ArrayList<>(1);
        list.add(point);
        return list;
    }

    @Override
    public Set<Point2D<Double>> getBoundingPoints(double mag)
    {
        Set<Point2D<Double>> set = new HashSet<>(4);

        Point2D<Double> p1 = new Point2D<>(point.getX() + mag, point.getY());
        Point2D<Double> p2 = new Point2D<>(point.getX() - mag, point.getY());
        Point2D<Double> p3 = new Point2D<>(point.getX(), point.getY() + mag);
        Point2D<Double> p4 = new Point2D<>(point.getX(), point.getY() - mag);

        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);

        return set;
    }

    @Override
    public Point2D<Double> getPosition()
    { return point; }
}
