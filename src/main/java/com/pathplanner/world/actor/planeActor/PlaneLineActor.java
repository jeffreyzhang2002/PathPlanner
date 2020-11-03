package com.pathplanner.world.actor.planeActor;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.geometry.Line2D;
import com.pathplanner.world.actor.properties.Properties;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A PlaneLineActor is subclass of the PlaneObstacle class. It is defined by two vertex points.
 * This type of barrier can be thought of as line segment connecting to two points. In addition 4 bounding points
 * are generated for each PlaneLineActor. 2 points are collinear to the line and 2 other points are perpendicular to the lines
 * and collinear to the midpoint on the line.
 * Both point must be within the bounds on the world it is placed into.
 * @author Jeffrey Zhang
 * @version 0.0
 * @since 10/19/2020
 */
public class PlaneLineActor extends PlaneActor {
    private Line2D<Double> line;

    public PlaneLineActor(Double x1, Double y1, Double x2, Double y2, Properties properties)
    {
        super(properties);
        line = new Line2D<>(x1, y1, x2, y2);
    }

    public PlaneLineActor(Double x1, Double y1, Double x2, Double y2)
    { line = new Line2D<>(x1, y1, x2, y2); }


    /**
     * Creates a LineBarrier with a given Line from the geometry package.
     * @param line a line that will represent the barrier
     * @see Line2D
     * @throws IllegalArgumentException if line is null
     */
    public PlaneLineActor(Line2D<Double> line, Properties properties)
    {
        super(properties);
        if(line == null)
            throw new IllegalArgumentException("Non null arguments expected");
        this.line = line;
    }

    /**
     * Creates a LineBarrier with a given Line from the geometry package.
     * @param line a line that will represent the barrier
     * @see Line2D
     * @throws IllegalArgumentException if line is null
     */
    public PlaneLineActor(Line2D<Double> line)
    {
        if(line == null)
            throw new IllegalArgumentException("Non null arguments expected");
        this.line = line;
    }

    public PlaneLineActor(Point2D<Double> p1, Point2D<Double> p2, Properties properties)
    {
        super(properties);
        this.line = new Line2D<>(p1, p2);
    }

    public PlaneLineActor(Point2D<Double> p1, Point2D<Double> p2)
    { this.line = new Line2D<>(p1, p2); }

    /**
     * This method gets the vertex points of the PlaneLineActor. The vertex points are defined as the two end points
     * of the Line.
     * @return ArrayList<Point2D> containing the bounding points.
     */
    @Override
    public List<Point2D<Double>> getVertexPoints()
    {
        ArrayList<Point2D<Double>> boundingPoints = new ArrayList<>(2);
        boundingPoints.add(line.getP1());
        boundingPoints.add(line.getP2());
        return boundingPoints;
    }
    
    /**
     * This method returns the bounding points of the PlaneLineActor. Bounding points are points that are a certain
     * magnitude away from the end points. For a PlaneLineActor, their are 4 bounding points. 2 are collinear with line segment
     * and a distance of magnitude away from the endpoints. And another 2 are perpendicular to the line
     * and collinear to the midpoint. Please note these points are not directly on the line segment
     * @param mag the magnitude of propagation or the distance away the propagated points are from end points
     * @return a Set containing all the propagated points of the LineBarrier
     */
    @Override
    public Set<Point2D<Double>> getBoundingPoints(double mag)
    {
        Set<Point2D<Double>> propagatedPoints = new HashSet<>();

        double angle = Math.atan2(line.getP2().getY().doubleValue() - line.getP1().getY().doubleValue(),
                line.getP2().getX().doubleValue() - line.getP1().getX().doubleValue());
        double anglePrime = Math.PI / 2 - angle;

        Point2D midpoint = new Point2D((line.getP1().getX().doubleValue() + line.getP2().getX().doubleValue())/2
                , (line.getP1().getY().doubleValue() + line.getP2().getY().doubleValue())/2);

        Point2D P1 = new Point2D(0.0,0.0), P2 = new Point2D(0.0,0.0),
                P3 = new Point2D(0.0, 0.0), P4 = new Point2D(0.0, 0.0);

        double xOffset = Math.abs(Math.cos(angle)) * mag;
        double xOffsetPrime = Math.abs(Math.cos(anglePrime)) * mag;
        double yOffset = Math.abs(Math.sin(angle)) * mag;
        double yOffsetPrime = Math.abs(Math.sin(anglePrime)) * mag;

        if (line.getP1().getX().doubleValue() < line.getP2().getX().doubleValue()) {
            P1.setX(line.getP1().getX().doubleValue() - xOffset);
            P2.setX(line.getP2().getX().doubleValue() + xOffset);
            P3.setX(midpoint.getX().doubleValue() + xOffsetPrime);
            P4.setX(midpoint.getX().doubleValue() - xOffsetPrime);
        } else {
            P1.setX(line.getP1().getX().doubleValue() + xOffset);
            P2.setX(line.getP2().getX().doubleValue() - xOffset);
            P3.setX(midpoint.getX().doubleValue() - xOffsetPrime);
            P4.setX(midpoint.getX().doubleValue() + xOffsetPrime);
        }

        if (line.getP1().getY().doubleValue() < line.getP2().getY().doubleValue()) {
            P1.setY(line.getP1().getY().doubleValue() - yOffset);
            P2.setY(line.getP2().getY().doubleValue() + yOffset);
            P3.setY(midpoint.getY().doubleValue() - yOffsetPrime);
            P4.setY(midpoint.getY().doubleValue() + yOffsetPrime);
        } else {
            P1.setY(line.getP1().getY().doubleValue() + yOffset);
            P2.setY(line.getP2().getY().doubleValue() - yOffset);
            P3.setY(midpoint.getY().doubleValue() + yOffsetPrime);
            P4.setY(midpoint.getY().doubleValue() - yOffsetPrime);
        }

        propagatedPoints.add(P1);
        propagatedPoints.add(P2);
        propagatedPoints.add(P3);
        propagatedPoints.add(P4);
        return propagatedPoints;
    }
}