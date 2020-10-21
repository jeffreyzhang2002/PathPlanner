package com.pathplanner.world.actor;

import com.pathplanner.geometry.Point2D;

/**
 * This class represents a single actor that can be placed inside an environment. An actor stores its own position in (x,y) coordinates which must always
 * be inside of the environment at all times. The final boolean isSolid define whether this actor can share its position with another actor. Likewise the
 * final boolean isStatic defines whether or not this actor can change its position. By default both isSolid and isStatic is true and con be overridden
 * in the Constructor. After an actor is placed into an environment the method setPosition should not be run unless through the environment. Please
 * note that an actor should have no knowledge of the environment so collision and placement will be handled in the environment classes.
 * @author Jeffrey Zhang
 * @version 0.0
 * @since 10/12/2020
 * @param <E> defines the specificity of the actors position. E must be a subclass of the Number class
 */
public abstract class Actor<E extends Number>
{
    private Point2D<E> position;

    /**
     * Defines whether or not this actor can share its position with another actor. True if the actor can not share it position false otherwise
     */
    public final boolean isSolid;

    /**
     * Defines whether or not this actor can change its position after it is initialized. True if it can't change position false otherwise
     */
    public final boolean isStatic;

    /**
     * Creates an actor at the given position. This constructor will set the isSolid and isStatic to be true by default
     * @param position the position this actor will be placed at
     * @throws IllegalArgumentException if position is null
     * @see IllegalArgumentException
     */
    public Actor(Point2D position)
    {
        setPosition(position);
        isSolid = true;
        isStatic = true;
    }

    /**
     * Creates an actor at the give position. The parameter isSolid and isStatic can also be defined
     * @param position the position of the actor
     * @param isSolid whether or not the actor can share its position
     * @param isStatic whether or not the actor can change its position
     */
    public Actor(Point2D position, boolean isSolid, boolean isStatic)
    {
        setPosition(position);
        this.isSolid = isSolid;
        this.isStatic = isStatic;
    }

    /**
     * Creates an actor at the position (0,0). This constructor will set the isSolid and isStatic field to be true bt default
     */
    public Actor()
    {
        this.position = new Point2D(0, 0);
        isSolid = true;
        isStatic = true;
    }

    /**
     * Returns the current position of the actor with precision defined by E.
     * @return the current position of the actor
     */
    public Point2D<E> getPosition()
    { return position; }

    /**
     * Sets the current position of the actor to the new position if isStatic if false. If isStatic is true, running this method will do nothing
     * @param position the new position of the obstacles
     * @throws IllegalArgumentException if the new position is null
     * @see IllegalArgumentException
     */
    public void setPosition(Point2D<E> position)
    {
        if(isStatic)
            return;
        else if(position == null)
            throw new IllegalArgumentException("non-null input expected");
        this.position = position;
    }

    /**
     * Gets the string representation of the actor in the format: Class Name: (x,y)
     * @return the string representation of the actor
     */
    public String toString()
    { return this.getClass().getSimpleName() + ": " + getPosition().toString(); }
}