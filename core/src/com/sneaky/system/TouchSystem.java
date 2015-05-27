package com.sneaky.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector3;
import com.sneaky.component.BoundsComponent;
import com.sneaky.component.LightComponent;
import com.sneaky.component.ParticleComponent;
import com.sneaky.component.StateComponent;
import com.sneaky.component.VisualComponent;

import static com.sneaky.component.StateComponent.State.INVALID;
import static com.sneaky.component.StateComponent.State.VALID;

/**
 * @author Kristaps Kohs
 */
public class TouchSystem extends IteratingSystem {
    private final OrthographicCamera camera;
    private final Vector3 touchPoint = new Vector3();
    private final ComponentMapper<BoundsComponent> bm = ComponentMapper.getFor(BoundsComponent.class);
    private final ComponentMapper<VisualComponent> visualMapper = ComponentMapper.getFor(VisualComponent.class);
    private final ComponentMapper<ParticleComponent> pm = ComponentMapper.getFor(ParticleComponent.class);
    private final ComponentMapper<StateComponent> stateMapper = ComponentMapper.getFor(StateComponent.class);
    private final ComponentMapper<LightComponent> lightMapper = ComponentMapper.getFor(LightComponent.class);
    private final ParticleEffectPool pool;

    public TouchSystem(final OrthographicCamera camera, final ParticleEffectPool pool) {
        super(Family.all(BoundsComponent.class, VisualComponent.class, ParticleComponent.class, StateComponent.class, LightComponent.class).get(), 0);
        this.camera = camera;
        this.pool = pool;
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
        StateComponent stateComponent = stateMapper.get(entity);
        LightComponent lightComponent = lightMapper.get(entity);
        if (boundsComponent.getRectangle().contains(touchPoint.x, touchPoint.y)
                && INVALID.equals(stateComponent.getState())) {
            visualComponent.setColor(Color.RED);
            lightComponent.getLight().setActive(true);
        } else if (boundsComponent.getRectangle().contains(touchPoint.x, touchPoint.y)
                && VALID.equals(stateComponent.getState())) {
            visualComponent.setColor(Color.GREEN);
            lightComponent.getLight().setActive(true);
        } else {
           // visualComponent.setColor(Color.WHITE);
            lightComponent.getLight().setActive(false);
        }
    }
}
