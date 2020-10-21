package com.pathplanner.pathplanners;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.environment.Environment;
import java.util.ArrayList;
import java.util.List;

public abstract class PathFinder<E extends Environment>
{
    private E environment;
    private List<Point2D> constraintPoints;
    private List<Point2D> path;

    public PathFinder(E environment)
    {
        if(environment == null)
            throw new IllegalArgumentException("Non-null arguments expected");

        this.environment = environment;
        constraintPoints = new ArrayList<>();
    }

    public PathFinder(E environment, Point2D<Double> start, Point2D end)
    {
        if(start == null || end == null || environment == null)
            throw new IllegalArgumentException("Non-null arguments expected");
        else if(!environment.isValidPosition(start) || !environment.isValidPosition(end))
            throw new IllegalArgumentException("invalid point position");

        this.environment = environment;
        constraintPoints = new ArrayList<>(2);
        constraintPoints.add(0,start);
        constraintPoints.add(1, end);
    }

    public PathFinder(E environment, Point2D ... points)
    {
        if(environment == null || points == null)
            throw new IllegalArgumentException("Non-null arguments expected");

        this.environment = environment;
        constraintPoints = new ArrayList<>();

        for(Point2D point : points)
        {
            if(point == null)
                throw new IllegalArgumentException("Non-null arguments expected");
            else if(!environment.isValidPosition(point))
                throw new IllegalArgumentException("invalid point position");
            constraintPoints.add(point);
        }
    }

    public void addConstraintPoint(Point2D point)
    {
        isValidPoint(point);
        constraintPoints.add(point);
    }

    public void addConstraintPoint(int index, Point2D point)
    {
        isValidPoint(point);
        constraintPoints.add(index, point);
    }

    public void setConstraintPoints(int index, Point2D point)
    {
        isValidPoint(point);
        constraintPoints.set(index, point);
    }

    public void removeConstraintPoint(Point2D point)
    { constraintPoints.remove(point); }

    public void removeConstraintPoints(int index)
    { constraintPoints.remove(index); }

    public void clearConstraintPoints()
    { constraintPoints.clear(); }

    public int getConstraintPointCount()
    { return constraintPoints.size(); }

    public Point2D getConstraintPoint(int index)
    { return constraintPoints.get(index); }

    public void clearPath()
    { path = null; }

    public List<Point2D> getPath()
    { return path; }

    public List<Point2D> getConstraintPoints()
    { return constraintPoints; }

    public boolean isValidPath()
    { return path != null && !path.isEmpty(); }

    public Environment getEnvironment()
    { return  environment; }

    public List<Point2D> generatePath(boolean containCorners)
    {
        path = new ArrayList<>();
        if(constraintPoints.size() > 1)
        {
            for(int i = 1; i < constraintPoints.size(); i++) {
                ArrayList<Point2D> pathPart = generatePathSegment(constraintPoints.get(i - 1), constraintPoints.get(i), containCorners);
                if(pathPart != null) {
                    pathPart.remove(pathPart.size() - 1);
                    path.addAll(pathPart);
                }
                else {
                    path = null;
                    return null;
                }
            }
            path.add(constraintPoints.get(constraintPoints.size()-1));
        }
        else if(constraintPoints.size() == 1)
            path.add(constraintPoints.get(0));
        else
            return null;
        return path;
    }

    public abstract ArrayList<Point2D> generatePathSegment(Point2D start, Point2D end, boolean containCorners);

    private void isValidPoint(Point2D point)
    {
        if(point == null)
            throw new IllegalArgumentException("Non-null argument expected");
        else if(!environment.isValidPosition(point))
            throw new IllegalArgumentException("Invalid Point position");
    }

    public String toString()
    { return constraintPoints.toString(); }
}