package com.pathplanner.world.actor;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.properties.Properties;

/**
 * This class represents a single actor that can be placed inside an world. An actor stores its own position in (x,y) coordinates which must always
 * be inside of the world at all times. The final boolean isSolid define whether this actor can share its position with another actor. Likewise the
 * final boolean isStatic defines whether or not this actor can change its position. By default both isSolid and isStatic is true and con be overridden
 * in the Constructor. After an actor is placed into an world the method setPosition should not be run unless through the world. Please
 * note that an actor should have no knowledge of the world so collision and placement will be handled in the world classes.
 * @author Jeffrey Zhang
 * @version 0.0
 * @since 10/12/2020
 * @param <E> defines the specificity of the actors position. E must be a subclass of the Number class
 */
public abstract class Actor<E extends Number>
{
    private Point2D<E> position;
    private boolean placed;
    public final Properties properties;


    /**
     * Creates an actor at the given position. This constructor will set the properties to be the default properties;
     * @param position the position this actor will be placed at
     * @throws IllegalArgumentException if position is null
     * @see IllegalArgumentException
     */
    public Actor(Point2D position)
    {
        if(position == null)
            throw new IllegalArgumentException("Non null parameters expected");
        this.position = position;
        properties = new Properties();
    }

    /**
     * Creates an actor at the give position. The parameter isSolid and isStatic can also be defined
     * @param position the position of the actor
     * @param properties properties of the Actor
     */
    public Actor(Point2D position, Properties properties)
    {
        if(position == null || properties == null)
            throw new IllegalArgumentException("Non null parameters expected");
        this.position = position;
        this.properties = properties;
    }

    /**
     * Creates an actor at the position (0,0). This constructor will set the properties to be the default properties
     */
    public Actor()
    {
        this.position = new Point2D(0, 0);
        this.properties = new Properties();
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
        if(properties.isStatic())
            return;
        else if(position == null)
            throw new IllegalArgumentException("non-null input expected");
        this.position = position;
    }

    /**
     * Get the properties of the Actor
     * @return the properties of the Actor
     */
    public Properties getProperties()
    { return properties; }

    /**
     * Sets placed to be true. After this is turned to true, the actor can not be placed into another world
     */
    public void placed()
    { placed = true; }

    /**
     * Gets the string representation of the actor in the format: Class Name: (x,y)
     * @return the string representation of the actor
     */
    public String toString()
    { return this.getClass().getSimpleName() + ": " + getPosition().toString(); }
}