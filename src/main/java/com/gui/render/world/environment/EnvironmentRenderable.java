package com.gui.render.world.environment;

import com.gui.render.Renderable;
import javafx.scene.layout.Pane;

public interface EnvironmentRenderable extends Renderable
{

    @Override
    default void render(Pane pane) {
        renderBackground(pane);
        renderActors(pane);
    }

    void renderBackground(Pane pane);

    void renderActors(Pane pane);
}
