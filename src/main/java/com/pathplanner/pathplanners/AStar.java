package com.pathplanner.pathplanners;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.environment.Grid;
import java.util.*;


public class AStar extends PathFinder<Grid>
{
    PriorityQueue<PathFinderData> openSet;
    HashSet<Point2D<Integer>> closedSet;
    HashMap<Point2D<Integer>, Point2D<Integer>> pointConnectionLibrary = new HashMap<>();

    public AStar(Grid grid)
    { super(grid); }

    public AStar(Grid grid, Point2D<Integer> start, Point2D<Integer> end)
    { super(grid, start, end); }

    public AStar(Grid grid, Point2D<Integer>... point2DS)
    { super(grid, point2DS); }

    public ArrayList<Point2D> generatePathSegment (Point2D start, Point2D end, boolean containCorners)
    {
        openSet   = new PriorityQueue<>();
        closedSet = new HashSet<>();
        pointConnectionLibrary = new HashMap<>();

        PathFinderData startPoint = new PathFinderData(new Point2D<Integer>((int)start.getX(), (int)start.getY()), 0, heuristic(start, end));
        openSet.add(startPoint);

        while(!openSet.isEmpty())
        {
            PathFinderData current = openSet.poll();
            closedSet.add((Point2D<Integer>) current.point);

            if(current.point.equals(end))
                return reconstructPath((Point2D<Integer>) current.point);

            Set<Point2D<Integer>> neighbors = ((Grid) super.getEnvironment()).getEmptyNeighboringPositions((Point2D<Integer>) current.point, containCorners);

            for(Point2D<Integer> neighborPoint : neighbors)
            {
                if(!closedSet.contains(neighborPoint))
                {
                    double newGScore = current.gScore + heuristic(current.point, neighborPoint);
                    PathFinderData currentKeyPointData = getProcessedPointData(neighborPoint);

                    if(currentKeyPointData == null || newGScore < currentKeyPointData.gScore) {
                        openSet.add(new PathFinderData(neighborPoint, newGScore, heuristic(neighborPoint, end)));
                        pointConnectionLibrary.put(neighborPoint, (Point2D<Integer>)current.point);
                    }
                }
            }
        }
        return null;
    }

    private ArrayList<Point2D> reconstructPath(Point2D<Integer> end)
    {
        Point2D<Integer> current = end;
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

    private PathFinderData getProcessedPointData(Point2D point)
    {
        for(PathFinderData group : openSet)
            if(group.point.equals(point))
                return group;
        return null;
    }

    private double heuristic(Point2D a, Point2D b)
    { return a.distanceSq(b); }
}
