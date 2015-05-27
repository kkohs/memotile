package com.sneaky.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sneaky.component.PositionComponent;
import com.sneaky.component.VisualComponent;

/**
 * @author Kristaps Kohs
 */
public class RenderSystem extends IteratingSystem {
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final ComponentMapper<VisualComponent> visualMapper = ComponentMapper.getFor(VisualComponent.class);
    private final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);

    public RenderSystem(final OrthographicCamera camera) {
        super(Family.all(VisualComponent.class, PositionComponent.class).get(), 1);
        this.camera = camera;
        this.batch = new SpriteBatch();
    }

    @Override
    public void update(final float deltaTime) {

        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final VisualComponent visualComponent = visualMapper.get(entity);
        final PositionComponent positionComponent = positionMapper.get(entity);
        batch.setColor(visualComponent.getColor());
        batch.draw(visualComponent.getTextureRegion(), positionComponent.getX(), positionComponent.getY(), 
                visualComponent.getWidth(), visualComponent.getHeight());
    }
}
