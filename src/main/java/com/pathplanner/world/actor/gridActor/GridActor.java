package com.pathplanner.world.actor.gridActor;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.Actor;
import com.pathplanner.world.actor.properties.Properties;

/**
 * A GridActor is a type of Actor that can only be placed inside a Grid world. A GridActor is a subclass of
 * the Actor class and is only used as a helper class to specify what can be placed inside a Grid. The position
 * of a GridActor is stored in Integer precision.
 * @see com.pathplanner.world.environment.Grid
 * @author Jeffrey Zhang
 * @version 0.0
 * @since 10/13/2020
 */
public abstract class GridActor extends Actor<Integer>
{
    /**
     * Creates an instance of a GridActor at the given position. The position but be in Integer precision and can not be null
     * the fields isSolid and isStatic is default to true
     * @param position the position this GridActor will be located at
     * @throws IllegalArgumentException if position is null
     * @see IllegalArgumentException
     */
    public GridActor(Point2D<Integer> position)
    { super(position); }

    /**
     * Creates an instance of a GridActor at the given position. The position but be in Integer precision and can not be null
     * The isSolid and isStatic field are also set by the given parameters
     * @param position the position this GridActor will be located at
     * @param properties the properties of the GridActor
     */
    public GridActor(Point2D<Integer> position, Properties properties)
    { super(position, properties); }

    /**
     * Creates an obstacles at the position (0,0). The fields isSolid and isStatic is defaulted to true
     */
    public GridActor()
    { super(); }
}