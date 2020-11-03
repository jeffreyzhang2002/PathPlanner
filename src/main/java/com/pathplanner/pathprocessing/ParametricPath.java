package com.pathplanner.pathprocessing;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.geometry.Vector2D;
import com.pathplanner.pathprocessing.pathSegment.RouteLineSegment;
import com.pathplanner.pathprocessing.pathSegment.RouteSegment;

import java.util.ArrayList;
import java.util.List;

public class ParametricPath extends Path<Double>
{
    private List<RouteSegment> parametrization;

    public ParametricPath(List<Point2D<Double>> path, List<RouteSegment> parametrization)
    {
        super(path);
        this.parametrization = parametrization;
    }

    public ParametricPath(List<Point2D<Double>> path)
    {
        super(path);
        parametrization = new ArrayList<>(path.size() - 1);

        for(int i = 1; i < path.size(); i++)
            parametrization.add(new RouteLineSegment(path.get(i-1), path.get(i)));
    }

    public Point2D<Double> getPoint(double t)
    { return parametrization.get((int) t).getPoint(t - Math.floor(t)); }

    public Vector2D getTangent(double t)
    { return parametrization.get((int) t).getTangent(t - Math.floor(t)); }

    @Override
    public double distance()
    {
        double distance = 0;
        for(RouteSegment seg : parametrization)
            distance += seg.getDistance();
        return distance;
    }
}
