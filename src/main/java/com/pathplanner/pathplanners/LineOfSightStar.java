package com.pathplanner.pathplanners;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.environment.Plane;
import java.util.*;

/**
 * This class is an implementation of the Line Of Sight algorithm which is based of the AStar Algorithm. This class runs
 * on a Plane Environment and will find the optimal path between a starting and an ending point. In addition, this class
 * overrides the abstract method _generatePath method. This method provides functionality for path optimization.
 * @version 2
 * @author Jeffrey
 * @sinve 10/19/2020
 */
public class LineOfSightStar extends PathFinder<Plane>
{
    private PriorityQueue<PathFinderData> openSet;
    private HashSet<Point2D> closedSet;
    private HashMap<Point2D, Point2D> pointConnectionLibrary;

    /**
     * Creates an instance of the LineOfSight algorithm. This constructor takes a plane as paramter and
     * will be the world the algorithm will run on.
     * @param plane the Environment the algorithm will run on.
     */
    public LineOfSightStar(Plane plane)
    { super(plane); }

    /**
     * Creates an instance of the LineOfSight algorithm with the given plane and the start/stop points. The start
     * and stop points may change if more points are added to the key stop points list.
     * @param plane the Environment the LineOfSight algorithm will run on
     * @param start the current starting point
     * @param end the current ending point
     */
    public LineOfSightStar(Plane plane, Point2D<Double> start, Point2D<Double> end)
    { super(plane, start, end); }

    /**
     * Creates an instance of the LineOfSightStar algorithm which will use the given plane as its world and an
     * array of points as the key stopping points for the algorithm.
     * @param plane the Plane the LineOfSight algorithm will run on
     * @param point2DS list of points that this pathfinder will stop at
     */
    public LineOfSightStar(Plane plane, Point2D<Double> ... point2DS)
    { super(plane, point2DS); }

    /**
     * This method generates the the optimal path from the given start point to the given end point.
     * This method does not generate the entire path including each stop point. Instead it acts as the base for
     * the generatePath method which will create the entire path.
     * @param start the start point of the segment
     * @param end the end point of the segment
     * @param containCorners this comes from overriding this method. Corners are meaningless for Line Of Sight AStar
     * @return the optimal path form the start to end point
     */
    public ArrayList<Point2D> generatePathSegment(Point2D start, Point2D end, boolean containCorners)
    {
        openSet = new PriorityQueue<>();
        closedSet = new HashSet<>();
        pointConnectionLibrary = new HashMap<>();

        Set<Point2D<Double>> connectionPoints = ((Plane)super.getEnvironment()).getBoundingPoints();
        connectionPoints.add(start);
        connectionPoints.add(end);

        PathFinderData startGroup = new PathFinderData(start, 0, heuristic(start,end));
        openSet.add(startGroup);

        while(!openSet.isEmpty())
        {
            PathFinderData current = openSet.poll();
            closedSet.add(current.point);

            if(current.point.equals(end))
                return reconstructPath(end);

            for(Point2D currentConnectionPoint : connectionPoints)
            {
                if(!closedSet.contains(currentConnectionPoint) && ((Plane)super.getEnvironment()).LineOfSight(current.point, currentConnectionPoint))
                {
                    double newGScore = current.gScore + heuristic(current.point, currentConnectionPoint);
                    PathFinderData currentKeyPointData = getProcessedPointData(currentConnectionPoint);

                    if(currentKeyPointData == null || newGScore < currentKeyPointData.gScore) {
                        openSet.add(new PathFinderData(currentConnectionPoint, newGScore, heuristic(currentConnectionPoint, end)));
                        pointConnectionLibrary.put(currentConnectionPoint, current.point);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Reconstructs the path for the _generatePathMethod
     * @param end the end point for path generation
     * @return the optimal path connecting these points
     */
    private ArrayList<Point2D> reconstructPath(Point2D<Double> end)
    {
        Point2D current = end;
        ArrayList<Point2D> path = new ArrayList<>();
        path.add(end);
        while(pointConnectionLibrary.get(current) != null)
        {
            current = pointConnectionLibrary.get(current);
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Checks if the openSet contain the given point and return the data associated with that point
     * @param point the point the method will search for
     * @return PathFinderData associated with the point. Null if the point is not in the openset
     */
    private PathFinderData getProcessedPointData(Point2D<Double> point)
    {
        for(PathFinderData group : openSet)
            if(group.point.equals(point))
                return group;
        return null;
    }

    /**
     * Cost of arriving at a certain point. This is set to be the distance squared formula. Please note that the
     * squared distance was chosen to save on computing power.
     * @param start first point
     * @param end second point
     * @return distance squared between them
     */
    private double heuristic(Point2D<Double> start, Point2D<Double> end)
    { return start.distance(end); }
}