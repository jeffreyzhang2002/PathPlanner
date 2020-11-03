package com.gui.render.world.actor;

import com.gui.render.Renderable;
import javafx.scene.layout.Pane;

public interface PlaneActorRenderable extends Renderable
{
    void renderActor(Pane pane);

    void renderVertexPoints(Pane pane);

    void renderBoundingPoint(Pane pane);
}
