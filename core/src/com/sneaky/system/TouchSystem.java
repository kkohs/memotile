package com.sneaky.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sneaky.component.BoundsComponent;
import com.sneaky.component.PositionComponent;
import com.sneaky.component.VisualComponent;

/**
 * @author Kristaps Kohs
 */
public class TouchSystem extends IteratingSystem {
    private final OrthographicCamera camera;
    private final Vector3 touchPoint = new Vector3();
    private final ComponentMapper<BoundsComponent> bm = ComponentMapper.getFor(BoundsComponent.class);
    private final ComponentMapper<VisualComponent> visualMapper = ComponentMapper.getFor(VisualComponent.class);

    public TouchSystem(final OrthographicCamera camera) {
        super(Family.all(BoundsComponent.class, VisualComponent.class).get());
        this.camera = camera;
    }

    @Override
    public void update(final float deltaTime) {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            super.update(deltaTime);
        }

    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        BoundsComponent boundsComponent = bm.get(entity);
        VisualComponent visualComponent = visualMapper.get(entity);

        if (boundsComponent.getRectangle().contains(touchPoint.x, touchPoint.y)) {
            visualComponent.setColor(Color.RED);
        } else {
            visualComponent.setColor(Color.WHITE);
        }
    }
}
