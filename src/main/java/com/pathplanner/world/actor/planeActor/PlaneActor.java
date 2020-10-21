package com.pathplanner.world.actor.planeActor;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.Actor;
import java.util.List;
import java.util.Set;

/**
 * A PlaneActor is a type of Actor that can only be placed inside a Plane environment. A PlaneActor is not specified
 * at a single point. Instead its is defined by an list of vertex points that define the shape of the actor.
 * In a PlaneActor, the isSolid field does not mean actors can not share a position inside it means the actor prevent line of sight.
 * Also each PlaneActor can generate a set of bounding points. Bounding points are offset from vertex points facing outwards.
 * By default bounding point are a magnitude of 1 away from vertex points.
 * Lastly all PlaneActors store there point in double precision.
 * @see com.pathplanner.world.environment.Plane
 * @see Actor
 * @author Jeffrey
 * @version 1
 * @since  5/5/2020
 */
public abstract class PlaneActor extends Actor<Double>
{

    /**
     * The magnitude of propagation away from the vertex points for the bounding points.
     * By default this is value is set to 1 but can be changed for each plane actor
     */
    public double boundingMagnitude = 1;

    /**
     * Constructor for creating an instance of a PlaneActor.
     * Initializes isSolid and isStatic to be true by default
     */
    public PlaneActor()
    { super(new Point2D(0.0,0.0), true, true);}

    /**
     * Constructor for creating an instance of a PlaneActor.
     * Sets isSolid and isStatic fields to be the specified values provided.
     * @param isSolid whether or not this PlaneActor prevents line of sight
     * @param isStatic whether or not the PlaneActor can change its position
     */
    public PlaneActor(boolean isSolid, boolean isStatic)
    { super(new Point2D<Double>(0.0,0.0), isSolid, isStatic); }

    /**
     * This method returns the centroid of the PlaneActor. Since a plane barrier is not defined by a single point but instead a
     * list of vertices that connect to form a shape, when this method is called it returns where the center should be.
     * Please note that if the PlaneBarrier is just set to be a single point, this method will return that point and likewise if
     * this instance only has 2 bounding points and form a line Segment, this method will return the midpoint
     * @return a Point2D containing the centroid of the PlaneBarrier
     */
    @Override
    public Point2D<Double> getPosition()
    {
        if(getVertexPoints().size() == 1)
            return getVertexPoints().get(0);
        else if(getVertexPoints().size() == 2)
            return new Point2D<Double>(getVertexPoints().get(0).getX().doubleValue()
                    + getVertexPoints().get(1).getX().doubleValue() / 2,
                    (getVertexPoints().get(0).getY().doubleValue()
                            + getVertexPoints().get(1).getY().doubleValue()) / 2);

        double xSum = 0, ySum = 0;
        double area = 0;
        for(int i = 0; i < getVertexPoints().size(); i++)
        {
            int j = (i == getVertexPoints().size() - 1) ? 0 : i + 1;
            xSum += (getVertexPoints().get(i).getX().doubleValue() + getVertexPoints().get(j).getX().doubleValue()) *
                        (getVertexPoints().get(i).getX().doubleValue() * getVertexPoints().get(j).getY().doubleValue() -
                            getVertexPoints().get(j).getX().doubleValue() * getVertexPoints().get(i).getY().doubleValue());
            ySum += (getVertexPoints().get(i).getY().doubleValue() + getVertexPoints().get(j).getY().doubleValue()) *
                        (getVertexPoints().get(i).getX().doubleValue() * getVertexPoints().get(j).getY().doubleValue() -
                            getVertexPoints().get(j).getX().doubleValue() * getVertexPoints().get(i).getY().doubleValue());
            area += (getVertexPoints().get(i).getX().doubleValue() * getVertexPoints().get(j).getY().doubleValue() -
                    getVertexPoints().get(j).getX().doubleValue() * getVertexPoints().get(i).getY().doubleValue());
        }
        area /= 2.0;
        return new Point2D((int) (xSum / (6 * area)), (int) (ySum / (6 * area)));
    }

    /**
     * This method sets the position of this plane actor to the give position. Since a plane barrier does not consist of a single point but instead
     * is defined by a list of veertex point, this method will set the centroid of the PlaneBarrier and then shift each of the
     * bounding points accordingly. This method will do nothing if isStatic is true
     * @param position the new centroid position of the actor
     * @throws IllegalArgumentException if position is null
     */
    @Override
    public void setPosition(Point2D position)
    {
        if(isStatic)
            return;
        else if(position == null)
            throw new IllegalArgumentException("Non null parameter expected");

        Point2D currentPos = getPosition();
        Point2D shift = new Point2D<Double>(currentPos.getX().doubleValue() - position.getX().doubleValue(),
                currentPos.getY().doubleValue() - position.getY().doubleValue());
        for(Point2D point: getVertexPoints())
            point.setLocation(shift.getX().doubleValue() + point.getX().doubleValue(),
                    shift.getY().doubleValue() + point.getY().doubleValue());
        super.setPosition(getPosition());
    }

    /**
     * Returns a list of Vertex points that bound the PlaneActor
     * @return an ArrayList containing the points the bound the shape
     */
    public abstract List<Point2D<Double>> getVertexPoints();

    /**
     * Bounding points are points that are propagated away from the vertex point. These points are offset from the
     * shape by a certain magnitude. For example the Boudning points of line segment are collinear with the lines but the are
     * a certain distance away from the end point. The position where Boudning points are placed will be used to generate
     * the shortest path and for finding points where shapes block direct line of sight.
     * @param mag the magnitude of propagation
     * @return a set containing the propagated points
     */
    public abstract Set<Point2D<Double>> getBoundingPoints(double mag);

    /**
     * Get the set of bounding points with the default prograction magneitude defined by the class
     * @return
     */
    public Set<Point2D<Double>> getBoundingPoints()
    { return getBoundingPoints(boundingMagnitude); }

    /**
     * Returns a string containing all the bounding points of the PlaneBarrier
     * @return a String representation of the PlaneBarrier
     */
    @Override
    public String toString()
    {
        String stringBuilder = "";
        for(Point2D point : getVertexPoints())
            stringBuilder += point.toString() + " --> ";
        return stringBuilder;
    }
}
