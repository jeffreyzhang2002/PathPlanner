package com.gui.render.world.environment;

import com.gui.render.Renderable;
import com.pathplanner.geometry.Point2D;
import com.gui.factory.Instanceiable;
import com.pathplanner.world.actor.planeActor.PlaneActor;
import com.pathplanner.world.environment.Plane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import java.util.List;
import java.util.Set;

public class PlaneRender extends Plane implements EnvironmentRenderable, Instanceiable<Plane>
{

    boolean renderVertexPoints = true;
    boolean renderEdgeLines = true;
    boolean renderBoundingPoints = true;
    double pointRadius = 2;

    public PlaneRender(Plane p)
    { super(p); }

    public PlaneRender(int length, int width)
    { super(length, width); }

    @Override
    public void renderBackground(Pane pane) {

    }

    @Override
    public void renderActors(Pane pane)
    {
        Set<PlaneActor> actors = super.getActors();
        for(PlaneActor actor : actors)
        {
            List<Point2D<Double>> vertexPoints = actor.getVertexPoints();
            if((actor instanceof Renderable))
                ((Renderable) actor).render(pane);
            if(renderEdgeLines)
                drawEdgeLines(actor, pane);
            if(renderVertexPoints)
                drawVertexPoints(actor, pane);
            if(renderBoundingPoints)
                drawBoundingPoints(actor, pane);
        }
    }

    public void drawVertexPoints(PlaneActor actor, Pane pane)
    {
        List<Point2D<Double>> vertexPoints = actor.getVertexPoints();
        for(Point2D<Double> p : vertexPoints)
            pane.getChildren().add(new Ellipse(p.getX(), p.getY(), pointRadius, pointRadius));
    }

    public void drawEdgeLines(PlaneActor actor, Pane pane)
    {
        List<Point2D<Double>> vertexPoints = actor.getVertexPoints();
        if(vertexPoints.size() == 1)
            return;
        else if(vertexPoints.size() == 2)
            pane.getChildren().add(new Line(vertexPoints.get(0).getX(), vertexPoints.get(0).getY()
                    , vertexPoints.get(1).getX(), vertexPoints.get(1).getY()));
        else
        {
            Polygon polygon = new Polygon();
            for(int i = 0; i < vertexPoints.size(); i++)
            {
                polygon.getPoints().add(vertexPoints.get(i).getX());
                polygon.getPoints().add(vertexPoints.get(i).getY());
            }
            pane.getChildren().add(polygon);
        }
    }

    public void drawBoundingPoints(PlaneActor actor, Pane pane)
    {
        Set<Point2D<Double>> boundingPoints = actor.getBoundingPoints();
        for(Point2D<Double> p : boundingPoints)
            pane.getChildren().add(new Ellipse(p.getX(), p.getY(), pointRadius, pointRadius));
    }

    @Override
    public Class[] getParamsType()
    { return new Class[]{Integer.class, Integer.class}; }

    @Override
    public Plane constructor(Object[] param)
    { return new PlaneRender((Integer) param[0], (Integer) param[1]); }
}
