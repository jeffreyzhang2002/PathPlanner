package com.pathplanner.world.actor.planeActor.obstacles;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.planeActor.PlaneActor;

/**
 * A PlaneObstacle is subclass of the PlaneActor class. All plane actors have isStatic and isSolid set to be true.
 * @see com.pathplanner.world.actor.planeActor.PlaneActor
 * @author Jeffrey
 * @version 1
 * @since  5/5/2020
 */
public abstract class PlaneObstacle extends PlaneActor
{
    /**
     * Creates a instance of a PlaneObstacle. The fields isStatic and isSolid is set to true
     */
    public PlaneObstacle()
    { super(true, true); }
}
