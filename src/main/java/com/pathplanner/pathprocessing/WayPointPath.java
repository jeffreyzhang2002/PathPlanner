package com.pathplanner.pathprocessing;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.pathprocessing.Path;

import java.util.List;

public class WayPointPath<T extends Number> extends Path<T>
{
    public WayPointPath(List<Point2D<T>> path)
    { super(path); }

    public void split()
    {
        for(int i= path.size() - 2; i > 0; i--)
        {
            Point2D p1 = path.get(i-1);
            Point2D p2 = path.get(i);
            path.add(i-1, new Point2D((p1.getX().doubleValue() + p2.getX().doubleValue())/2
                    ,(p1.getY().doubleValue() + p2.getY().doubleValue())/2 ));
        }
    }
}
