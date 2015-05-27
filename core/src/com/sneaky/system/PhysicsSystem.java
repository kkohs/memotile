package com.sneaky.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Kristaps Kohs
 */
public class PhysicsSystem extends EntitySystem {
    private final static int MAX_FPS = 30;
    public final static float TIME_STEP = 1f / MAX_FPS;
    private final static int VELOCITY_ITERS = 6;
    private final static int POSITION_ITERS = 2;
    private final World world;
    private Box2DDebugRenderer renderer;
    private final OrthographicCamera camera;
    private Matrix4 debugMatrix;
    private SpriteBatch batch;

    public PhysicsSystem(final World world, final OrthographicCamera camera) {
        super(0);
        this.world = world;
        this.camera = camera;
        renderer = new Box2DDebugRenderer();
        debugMatrix = new Matrix4(camera.combined);
        debugMatrix.scale(100, 100 ,1);
        batch = new SpriteBatch();
    }

    @Override
    public void update(final float deltaTime) {
        world.step(TIME_STEP, VELOCITY_ITERS, POSITION_ITERS);
  //      batch.begin();
        camera.update();
     //   renderer.render(world, camera.combined);
    //    batch.end();
    }
}
