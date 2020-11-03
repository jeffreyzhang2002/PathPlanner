package com.pathplanner.world.actor.planeActor;

import com.pathplanner.geometry.Point2D;

import java.util.*;

public class PlanePolygonActor extends PlaneActor
{
    private List<Point2D<Double>> vertexPoints;

    public PlanePolygonActor(int vertex)
    {
        vertexPoints = new ArrayList<>(vertex);
        for(int i = 0; i < vertex; i++)
            vertexPoints.add(new Point2D<Double>(0.0,0.0));
    }

    public PlanePolygonActor(Point2D<Double> ... vertex)
    { vertexPoints = new ArrayList<>(Arrays.asList(vertex)); }

    public void setVertex(int index, Point2D<Double> vertex)
    { vertexPoints.set(index, vertex); }


    @Override
    public Set<Point2D<Double>> getBoundingPoints(double mag)
    {
        Set<Point2D<Double>> set = new HashSet<>();
        for(int i = 0; i < vertexPoints.size(); i++)
        {
            int j = (i == 0)? vertexPoints.size() - 2 : i - 1;
            int k = (i == vertexPoints.size() - 1)? 0 : i + 1;

            Point2D<Double> a = vertexPoints.get(i);
            Point2D<Double> b = vertexPoints.get(j);
            Point2D<Double> c = vertexPoints.get(k);

            double x = (b.getX() + c.getX() - a.getX() * 2) * -1;
            double y = (b.getY() + c.getY() - a.getY() * 2) * -1;

            double vMag = Math.hypot(x, y);

            Point2D<Double> p = new Point2D<>(x / vMag * mag, y / vMag * mag);
            set.add(p);
        }
        return set;
    }

    @Override
    public List<Point2D<Double>> getVertexPoints()
    { return vertexPoints; }
}
