package com.gui.render.world.actor;

import com.pathplanner.geometry.Line2D;
import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.planeActor.PlaneLineActor;
import com.pathplanner.world.actor.properties.Properties;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.util.List;

public class PlaneLineActorRenderer extends PlaneLineActor implements PlaneActorRenderable
{
    public PlaneLineActorRenderer(Line2D<Double> line, Properties properties)
    { super(line, properties); }

    @Override
    public void render(Pane pane) {
    }

    @Override
    public void renderActor(Pane pane)
    {
        List<Point2D<Double>> vertexPoints = super.getVertexPoints();
        Line l = new Line(vertexPoints.get(0).getX(), vertexPoints.get(0).getY(),
                vertexPoints.get(1).getX(), vertexPoints.get(1).getY());
        pane.getChildren().add(l);
    }

    @Override
    public void renderBoundingPoint(Pane pane)
    {
        List<Point2D<Double>> boundingPoints = super.getVertexPoints();
        Group group = new Group();
        for(Point2D<Double> point : boundingPoints)
            group.getChildren().add(new Ellipse(point.getX(), point.getY()));
        pane.getChildren().add(group);
    }

    @Override
    public void renderVertexPoints(Pane pane) {
        List<Point2D<Double>> vertexPoints = super.getVertexPoints();
        Group group = new Group();
        for(Point2D<Double> point : vertexPoints)
            group.getChildren().add(new Ellipse(point.getX(), point.getY()));
        pane.getChildren().add(group);
    }
}
