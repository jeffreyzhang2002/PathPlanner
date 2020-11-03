package com.pathplanner.pathprocessing.pathFactory;

import com.pathplanner.pathprocessing.ParametricPath;
import com.pathplanner.pathprocessing.WayPointPath;

public class ParametricPathFactory<T extends Number>
{

    ParametricPath parametricPath;
    WayPointPath<T> path;

    public ParametricPathFactory(WayPointPath<T> path)
    {
        this.path = path;
        parametricPath = new ParametricPath(path);
    }
}
