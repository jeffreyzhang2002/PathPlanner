package com.pathplanner.world.environment;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.gridActor.GridActor;
import java.util.*;


public class Grid extends Environment<GridActor>
{
    private List<GridActor> actors;

    public Grid(int rows, int cols)
    {
        super(rows, cols);
        actors = new LinkedList<>();
    }

    public GridActor getActor(Point2D<Integer> point)
    {
        for(GridActor actor : actors)
            if(actor.getPosition().equals(point))
                return actor;
        return null;
    }

    public List<GridActor> getActors(Point2D<Integer> point)
    {
        List<GridActor> actorsAtPosition = new LinkedList<>();
        for(GridActor actor : actors)
            if(actor.getPosition().equals(point))
                actorsAtPosition.add(actor);
        return actorsAtPosition;
    }

    @Override
    public boolean contains(GridActor actor)
    { return actors.contains(actor); }

    @Override
    public void addActor(GridActor actor)
    {
        GridActor a = getActor(actor.getPosition());
        if(a == null)
            actors.add(actor);
        else if(!a.isSolid && !actor.isSolid)
            actors.add(actor);
    }

    @Override
    public void clearActors()
    { actors.clear(); }

    public Set<GridActor> getActors()
    { return new HashSet<GridActor>(actors); }


    public void removeActors(Point2D<Integer> point)
    {
        Iterator<GridActor> iterator = actors.iterator();
        while(iterator.hasNext())
        {
            if(iterator.next().getPosition().equals(point))
                iterator.remove();
        }
    }

    public boolean removeActor(GridActor actor)
    { return actors.remove(actor); }

    public Set<GridActor> getNeighboringActors(Point2D<Integer> point, boolean containCorners)
    {
        Set<Point2D<Integer>> neighbors = getNeighboringPositions(point, containCorners);
        Set<GridActor> occupoedNeighborActor = new HashSet<>();
        for(GridActor actor : actors)
            if(neighbors.contains(actor.getPosition()))
                occupoedNeighborActor.add(actor);
        return occupoedNeighborActor;
    }

    public Set<Point2D<Integer>> getEmptyNeighboringPositions(Point2D<Integer> point, boolean containCorners)
    {
        Set<Point2D<Integer>> neighbors = getNeighboringPositions(point, containCorners);
        Set<Point2D<Integer>> emptyNeighborPoint = new HashSet<>();
        for(Point2D<Integer> p : neighbors)
            if(getActor(p) == null)
                emptyNeighborPoint.add(p);
        return emptyNeighborPoint;
    }

    public Set<Point2D<Integer>> getOccupiedNeighboringPositions(Point2D<Integer> point, boolean containCorners)
    {
        Set<Point2D<Integer>> neighbors = getNeighboringPositions(point, containCorners);
        Set<Point2D<Integer>> occupoedNeighborPoint = new HashSet<>();
        for(GridActor actor : actors)
            if(neighbors.contains(actor.getPosition()))
                occupoedNeighborPoint.add(actor.getPosition());
        return occupoedNeighborPoint;
    }

    public Set<Point2D<Integer>> getNeighboringPositions(Point2D<Integer> point, boolean containCorners)
    {
        HashSet<Point2D<Integer>> neighboringObjects = new HashSet<>();

        if(isValidPosition(new Point2D<Integer>(point.X().intValue() - 1, point.Y().intValue())))
            neighboringObjects.add(new Point2D<Integer>(point.X().intValue() - 1, point.Y().intValue()));
        if(isValidPosition(new Point2D<Integer>(point.X().intValue() + 1, point.Y().intValue())))
            neighboringObjects.add(new Point2D<Integer>(point.X().intValue() + 1, point.Y().intValue()));
        if(isValidPosition(new Point2D<Integer>(point.X().intValue(), point.Y() - 1)))
            neighboringObjects.add(new Point2D<Integer>(point.X().intValue(), point.Y() - 1));
        if(isValidPosition(new Point2D<Integer>(point.X().intValue(), point.Y() + 1)))
            neighboringObjects.add(new Point2D<Integer>(point.X().intValue(), point.Y() + 1));

        if(!containCorners)
            return neighboringObjects;

        if(isValidPosition(new Point2D<Integer>(point.X().intValue() - 1, point.Y() - 1)))
            neighboringObjects.add(new Point2D<Integer>(point.X().intValue() - 1, point.Y() - 1));
        if(isValidPosition(new Point2D<Integer>(point.X().intValue() + 1, point.Y() - 1)))
            neighboringObjects.add(new Point2D<Integer>(point.X().intValue() + 1, point.Y() - 1));
        if(isValidPosition(new Point2D<Integer>(point.X().intValue() - 1, point.Y() + 1)))
            neighboringObjects.add(new Point2D<Integer>(point.X().intValue() - 1, point.Y() + 1));
        if(isValidPosition(new Point2D<Integer>(point.X().intValue() + 1, point.Y() + 1)))
            neighboringObjects.add(new Point2D<Integer>(point.X().intValue() + 1, point.Y() + 1));

        return neighboringObjects;
    }
}