package com.pathplanner.pathprocessing;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.geometry.Vector2D;

import java.util.List;

public abstract class Path
{
    protected List<Point2D> path;

    public Path(List<Point2D> path)
    { this.path = path; }

    public double distance() {
        double distance = 0;
        for(int i = 1; i < path.size(); i++)
            distance += path.get(i).distance(path.get(i-1));
        return distance;
    }

    public double displacement()
    { return path.get(0).distance(path.get(path.size()-1)); }

    public Point2D getPosition(int index)
    { return path.get(index); }

    public Vector2D getTangent(int index)
    {
        if(index == path.size() - 1)
            return new Vector2D(0,0);
        else
            return new Vector2D(path.get(index + 1).getX().doubleValue() - path.get(index).getX().doubleValue(),
                    path.get(index + 1).getY().doubleValue() - path.get(index).getY().doubleValue());
    }
}
