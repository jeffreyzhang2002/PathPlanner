package com.pathplanner.world.actor.gridActor.obstacles;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.gridActor.GridActor;

/**
 * This class is a representation of a single obstacles that can be placed inside a Grid environment. The position
 * stored within this obstacles must have Integer precision and can not be null. All GridObstacle have isSolid and isStatic set to true
 * @author Jeffrey Zhang
 * @version 0.0
 * @since 10/12/2020
 */
public class GridObstacle extends GridActor
{
    /**
     * Creates an instance of a GridObstacle at the given position. The position can not be null and must have Integer precision
     * @param position position of the GridObstacle
     * @throws IllegalArgumentException if position is null
     */
    public GridObstacle(Point2D<Integer> position)
    { super(position, true, true); }
}