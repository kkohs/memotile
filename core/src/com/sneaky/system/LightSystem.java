package com.sneaky.system;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Kristaps Kohs
 */
public class LightSystem extends EntitySystem {
    private final RayHandler rayHandler;
    private final OrthographicCamera camera;

    public LightSystem(final RayHandler handler, final OrthographicCamera camera) {
        super(3);
        this.camera = camera;
        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);
        rayHandler = handler;
        rayHandler.setAmbientLight(0f, 0f, 0f, 0.5f);
        rayHandler.setBlurNum(3);
    }

    @Override
    public void update(final float deltaTime) {
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
    }
}
