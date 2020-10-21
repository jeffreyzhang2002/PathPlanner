package com.pathplanner.pathplanners;

import com.pathplanner.geometry.Point2D;

/**
 * This class stores data for AStar based pathfinders.
 * @author Jeffrey
 * @since 6/4/2020
 * @version 2
 */
public class PathFinderData<E extends Number> implements Comparable<PathFinderData>
{
    public Point2D<E> point;
    public double gScore = Double.POSITIVE_INFINITY;
    public double fScore = Double.POSITIVE_INFINITY;

    /**
     * Creates an in instance of PathFinderData with the given point from LOSStar. In addition, gScore and fScore are initialized at
     * positive infinity.
     * @param point a point that is currently on the Plane Environment
     */
    public PathFinderData(Point2D<E> point)
    { this.point = point; }

    /**
     * Creates an instance of PathFinderData with the given point from LOSStar in addition to the initial g and f score.
     * @param point the points on the plane this class will hold data for.
     * @param gScore the gScore is the cumulative distance necessary to arrive at the certain point. Is should the previous gScore + distance betwen that point and the current point
     * @param fScore the future distance still needed to travel. Distance formula from the current point to the end point
     */
    public PathFinderData(Point2D<E> point, double gScore, double fScore)
    {
        this.point = point;
        this.gScore = gScore;
        this.fScore = fScore;
    }

    /**
     * Returns the score of the current PathFinderData. The total score is calculated by adding the gScore and the fScore
     * @return the sum of gScore and fScore.
     */
    public double getScore()
    { return gScore + fScore; }

    /**
     * Compares this LOSStar group to another LOSStar group. They compare the sum of gScore + fScore
     */
    public int compareTo(PathFinderData other)
    { return (int) (this.getScore() - (other.getScore())); }

    /**
     * Checks if the given object is equal to the current PathFinderData. They are equivalent if the points are equal
     */
    public boolean equals(Object o)
    { return (o instanceof PathFinderData) && ((PathFinderData) o).point.equals(this.point); }

    public int hashCode()
    { return point.hashCode(); }

    public String toString()
    { return point.toString() + "GScore: " + gScore + "FScore: " + fScore; }
}