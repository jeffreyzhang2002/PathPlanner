package com.pathplanner.world.environment;

import com.pathplanner.geometry.Line2D;
import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.planeActor.PlaneActor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Plane is special type of Environment that take PlaneActors. A Plane is specified by its length and width with the (0,0) origin at the top left.
 */
public class Plane extends Environment<PlaneActor>
{
    private Set<PlaneActor> actors;

    public Plane(int length, int width)
    {
        super(length, width);
        actors = new HashSet<>();
    }

    @Override
    public void addActor(PlaneActor actor) {
        if(!super.isValidPosition(actor.getPosition()))
            throw new IllegalArgumentException("Actor position out of range");
        actors.add(actor);
    }

    public boolean removeActor(PlaneActor actor)
    { return actors.remove(actor); }

    @Override
    public Set<PlaneActor> getActors()
    { return actors; }

    public void clearActors()
    { actors.clear(); }

    @Override
    public boolean contains(PlaneActor actor)
    { return actors.contains(actor); }

    public List<Point2D<Double>> getVertexPoints()
    {
        List<Point2D<Double>> actorVertex = new ArrayList<>(actors.size() * 2);
        for(PlaneActor actor : actors)
            actorVertex.addAll(actor.getVertexPoints());
        return actorVertex;
    }

    public Set<Point2D<Double>> getBoundingPoints(double mag)
    {
        Set<Point2D<Double>> actorBounds = new HashSet<>(actors.size() * 4);
        for(PlaneActor actor : actors)
            actorBounds.addAll(actor.getBoundingPoints(mag));
        return actorBounds;
    }

    public Set<Point2D<Double>> getBoundingPoints()
    {
        Set<Point2D<Double>> actorBounds = new HashSet<>(actors.size() * 4);
        for(PlaneActor actor : actors)
            actorBounds.addAll(actor.getBoundingPoints());
        return actorBounds;
    }

    public boolean LineOfSight(Point2D startPoint, Point2D endPoint)
    {
        Line2D<Double> intersectionLine = new Line2D(startPoint,endPoint);

        for(PlaneActor actor : actors)
        {
            if(!actor.isSolid)
                continue;

            List<Point2D<Double>> actorVertexPoints = actor.getVertexPoints();
            for(int i = 0; i < actorVertexPoints.size(); i++)
            {
                int j = (i == actorVertexPoints.size() - 1) ? 0 : i + 1;
                Line2D<Double> currentLine = new Line2D<Double>(actorVertexPoints.get(i), actorVertexPoints.get(j));
                if(currentLine.intersects(intersectionLine))
                    return false;
            }
        }
        return true;
    }
}