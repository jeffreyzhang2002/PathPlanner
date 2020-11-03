package com.pathplanner.pathprocessing.pathSegment;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.geometry.Vector2D;

import java.util.List;

/**
 * A instance of a RouteSegment that connects the the start and end point
 */
public class RouteLineSegment extends RouteSegment
{
    private double x1, y1;

    public RouteLineSegment(Point2D startPoint, Point2D endPoint)
    {
        super();
        super.addConstraint(0,startPoint);
        super.addConstraint(1,endPoint);
    }

    public RouteLineSegment(List<Point2D> constraintPoint)
    { super(constraintPoint); }

    public RouteLineSegment()
    { super(); }

    public void recalculateSegment()
    {
        Point2D startPoint = super.getConstraint(0);
        Point2D endPoint = super.getConstraint(1);
        x1 = startPoint.getX().doubleValue() - endPoint.getX().doubleValue();
        y1 = startPoint.getY().doubleValue() - endPoint.getY().doubleValue();
    }

    public Point2D _getPoint(double t)
    { return new Point2D(super.getConstraint(0).getX().doubleValue() - x1 * t, super.getConstraint(0).getY().doubleValue() - y1 * t); }

    @Override
    public Vector2D _getTangent(double t) {
        return new Vector2D(y1, x1);
    }

    @Override
    public double getDistance()
    { return super.getDisplacement(); }
}
