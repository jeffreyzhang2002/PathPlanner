package com.pathplanner.pathprocessing.pathSegment;
import com.pathplanner.geometry.Point2D;
import com.pathplanner.geometry.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 * RouteSegment represents a single segment within the entire route. This class is abstract as there will be different type of segments that extends this class such as
 * LineRouteSegment and SplineRouteSegment, etc. Each segment is defined by predetermined number of constraint points. By default, a minimum and maximum of 2 constraint points
 * is allowed but this can be changed in subclasses by overriding the getMinConstraints() and getMaxConstraints() methods. Each segment point are generated between t = [0,1]
 */
public abstract class RouteSegment
{
    /**
     * List of constraints that define this segment. This List should not be accessible outside of this class. The size of this list will always between 0 and the value returned by getMaxConstraints().
     * Order also matters as the first point should be the start constraint and the last point should be the finishing constraint
     */
    private List<Point2D<Double>> constraints;

    /**
     * Boolean that stores weather or not this segment has been modified. Used to optimize constant calculations. After adding, removing, setting any constraints on the segmentConstraint List
     * this will be true.
     */
    private boolean modified = true;

    /**
     * Constructor for the RouteSegment. This method uses a List with predefined constraint points. This size of the list must be less than or equal to getMaxConstraints()) or
     * it will throw an IllegalArgumentException
     * @param constraints List of constraint point that define this segment of the route
     */
    public RouteSegment(List<Point2D<Double>> constraints)
    {
        if(constraints.size() > getMaxConstraints())
            throw new IllegalArgumentException("Given constraints Size: " + constraints.size() + " > Max Constraints: " + getMaxConstraints());
        this.constraints = constraints;
    }

    /**
     * Constructor for the RouteSegment. This method initialzies the constraints with an empty List
     */
    public RouteSegment()
    { constraints = new ArrayList<> (getMaxConstraints()); }

    /**
     * Returns the minimum number of points required to fully define this route segment. This value is defaulted at 2 and should not be less than 2
     * @return the minimum required constraint points. Defaults at 2
     */
    public int getMinConstraints()
    { return 2; }

    /**
     * Returns the maximum number of points that can be used to fully define this route segment. This value is defaulted at 2. If there is no maximum, this should return
     * maximum integer value
     * @return the maximum number of constraint points allowed.
     */
    public int getMaxConstraints()
    { return 2; }

    /**
     * Adds a constraining point at the given index. If no more constraining points can be added, the point will not be added onto the list and false will be returned
     * @param index index in which the constraining point should be added
     * @param constraintPoint The constraint point
     * @return true if the point was successfully added. false otherwise
     */
    public boolean addConstraint(int index, Point2D<Double> constraintPoint)
    {
        if (constraints.size() >= getMaxConstraints() || index >= getMaxConstraints() || index < 0)
            return false;

        modified = true;
        constraints.add(index, constraintPoint);
        return true;
    }

    /**
     * Adds a constraining point to the end of the constraints list. If no more constaining points can be added, the point will not be added into the list and false will be returned
     * @param constraintPoint the constraint point
     * @return true if the point was added false otherwise
     */
    public boolean addConstraint(Point2D<Double> constraintPoint)
    { return addConstraint(constraints.size(), constraintPoint); }

    /**
     * Gets the constraining point at the given index
     * @param index The index of the point you want to get
     * @return the constraining point stored at the index
     */
    public Point2D<Double> getConstraint(int index)
    { return constraints.get(index); }

    /**
     * Sets the value at the index to the give constraint point
     * @param index the index in which will be set
     * @param constraintPoint the constraining point
     * @return the value that was previously there
     */
    public Point2D<Double> setConstraint(int index, Point2D<Double> constraintPoint)
    {
        modified = true;
        return constraints.set(index, constraintPoint);
    }

    /**
     * Removes the value at the give index
     * @param index the index of the point that will be removed
     * @return the value that was removed
     */
    public Point2D<Double> removeConstraint(int index)
    {
        modified = true;
        return constraints.remove(index);
    }

    /**
     * Removed the the given constraint point from the list of constraints
     * @param constraintPoint the point that will be removed
     * @return true if it was removed, false otherwise
     */
    public boolean removeConstraint(Double constraintPoint)
    {
        modified = true;
        return constraints.remove(constraintPoint);
    }

    /**
     * Returns the number of constraints the segment currently has
     * @return the number of constraints
     */
    public int constraintCount()
    { return constraints.size(); }

    /**
     * Makes sure point generation is possible
     */
    private void prepareGeneration()
    {
        if(constraints.size() < getMinConstraints())
            throw new IllegalStateException("Constraint Size: " + constraints.size() + " < " + getMinConstraints());
        else if(modified) {
            this.recalculateSegment();
            modified = false;
        }
    }

    /**
     * Gets the point on the segment at the give t. t must be a value between 0 and 1
     * @param t the point you want to get at the give t
     * @return the Point at the time t
     */
    public Point2D getPoint(double t)
    {
        if(t < 0 && t > 1)
            throw new IllegalArgumentException("t must be between 0 and 1");
        prepareGeneration();
        return this._getPoint(t);
    }

    public List<Point2D> getPoints(int count)
    {
        if(count <= 0)
            throw new IllegalArgumentException(count + "<= 0");
        prepareGeneration();
        double res = 1 / count;
        ArrayList<Point2D> list = new ArrayList<>(((int) res) + 1);

        for(double t = 0; t < 1; t += res)
            list.add(_getPoint(t));
        return list;
    }

    /**
     * Gets the tangent line on the segment at the give t. t must be a value between 0 and 1
     * @param t the tangent you want to get at the give t
     * @return the Vector tangent at the time t
     */
    public Vector2D getTangent(double t)
    {
        if(t < 0 && t > 1)
            throw new IllegalArgumentException("t must be between 0 and 1");
        prepareGeneration();
        return this._getTangent(t);
    }

    public List<Vector2D> getTangents(int count)
    {
        if(count <= 0)
            throw new IllegalArgumentException(count + "<= 0");
        prepareGeneration();
        double res = 1 / count;
        ArrayList<Vector2D> list = new ArrayList<>(((int) res) + 1);

        for(double t = 0; t < 1; t += res)
            list.add(_getTangent(t));
        return list;
    }

    /**
     * Returns the total displacement from the start to the end of the segment. By default this is euclidean distance but can be overridden
     * @return the displacement from the start point to the end point
     */
    public double getDisplacement()
    { return constraints.get(0).distance(constraints.get(constraints.size()-1)); }

    /**
     * Abstract method that recalculates all of the constant that define this segment with the current constraint point
     */
    public abstract void recalculateSegment();

    /**
     * Abstract method that gets the point at the give t
     * @param t the time parameter
     * @return the point define at t
     */
    public abstract Point2D _getPoint(double t);

    /**
     * abstract method that gets the tangent vector at the give t
     * @param t the time parameter
     * @return the tangent line defined at t
     */
    public abstract Vector2D _getTangent(double t);

    /**
     * abstract method that gets the total distance (arclength) of the current path segment
     * @return the arc length of the segment
     */
    public abstract double getDistance();

    public String toString()
    { return constraints.toString(); }
}