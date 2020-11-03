package com.pathplanner.world.environment;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.Actor;

import java.util.Collection;
import java.util.Set;

/**
 * Environment is a fully enclosed abstract class that acts as a framework for storing actors. Environments are used
 * with pathfinders to generate the optimal path from a starting point to an ending point while avoiding all obstacles
 * and barriers on the Environment. This class must be overridden to be instantiated and used in a pathfinder
 * @author Jeffrey
 * @since 10/17/2020
 * @version 1
 * @param <E> An Environment can only contain Barriers and its subclasses
 */
public abstract class Environment<E extends Actor>
{
    private int rows, cols;

    /**
     * Initializes Environment with the given number of rows and columns. Rows and columns must be postive integer number not including 0
     * @param rows the number of rows this world has
     * @param cols the number of columns this world has
     * @throws IllegalArgumentException if rows or columns are less than or equal to 0
     */
    public Environment(int rows, int cols)
    {
        if(rows <= 0 || cols <= 0)
            throw new IllegalArgumentException("rows and cols must be at least 1");
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Removes the given actor from the Environment if the actors exists inside the Environment. Does nothing if the actor is not within the Environment
     * @param actor the actor that will be removed
     * @return true if the actor was removed false if the actor does not exist and can not be removed
     */
    public abstract boolean removeActor(E actor);

    /**
     * Gets a set of all actors currently inside the Environment
     * @return set of all actors inside the world
     */
    public abstract Set<E> getActors();

    /**
     * Removes all actors currently inside the Environment
     */
    public abstract void clearActors();

    /**
     * Adds an actor into the world. An actor can only be added if the actor's position is a valid position in the Environment and it does not share the same position
     * with a solid actor
     * @param actor
     * @throws IllegalArgumentException if the actor's position is not a valid position on the world
     * @throws IllegalStateException if the actor shares a position with another solid actor
     */
    public abstract void addActor(E actor);

    public void addAllActor(Collection<E> actors)
    {
        for(E actor : actors)
            addActor(actor);
    }
    
    public abstract boolean contains(E actor);

    /**
     * Checks whether the given point is a valid position in the world. A point is considered a valid point if the x value is between [0;rows) and the y value is between
     * [0;cols)
     * @param point the point that will be checked
     * @return true if the point is valid false otherwise
     */
    public boolean isValidPosition(Point2D point)
    { return point.getX().intValue() >= 0 && point.getY().intValue() < rows && point.getY().intValue() >= 0 && point.getY().intValue() < cols; }

    /**
     * Checks whether the give point is on the edge of the world. A point is considered to be on the edge if the x value is equals to 0 or rows - 1 and the y value is
     * equal to 0 or colds - 1
     * @param point the point that will be checked
     * @return true if the point is on the edge false otherwise
     */
    public boolean isEdgePosition(Point2D point)
    { return point.getX().intValue() == 0 && point.getY().intValue() == rows - 1 && point.getY().intValue() == 0 && point.getY().intValue() == cols - 1; }

    /**
     * gets the number of actors total on the world
     * @return the total number of actor on the world
     */
    public int getActorCount()
    { return getActors().size(); }

    /**
     * gets the number of rows on the world
     * @return the number of rows on the world
     */
    public final int getRows()
    { return rows; }

    /**
     * gets the number of columns of the world
     * @return the number of columns on the world
     */
    public final int getCols()
    { return cols; }

    /**
     * gets the string representation of the world
     * @return string representation of the world
     */
    public String toString()
    { return getActors().toString(); }
}