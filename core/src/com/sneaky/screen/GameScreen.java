package com.sneaky.screen;

import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sneaky.component.BodyComponent;
import com.sneaky.component.BoundsComponent;
import com.sneaky.component.LightComponent;
import com.sneaky.component.ParticleComponent;
import com.sneaky.component.PositionComponent;
import com.sneaky.component.StateComponent;
import com.sneaky.component.VisualComponent;
import com.sneaky.system.LightSystem;
import com.sneaky.system.PhysicsSystem;
import com.sneaky.system.RenderSystem;
import com.sneaky.system.StateSystem;
import com.sneaky.system.TouchSystem;

/**
 * @author Kristaps Kohs
 */
public class GameScreen extends ScreenAdapter {

    static final int RAYS_PER_BALL = 1000;
    static final int BALLSNUM = 5;
    static final float LIGHT_DISTANCE = 128 * 2;
    static final float RADIUS = 32f;
    
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int SPACING = 10;
    private Engine engine;
    private OrthographicCamera camera;
    private AssetManager assetManager;
    private ParticleEffectPool pool;
    private RayHandler rayHandler;

    private World world;
    @Override
    public void show() {
        super.show();
        assetManager = new AssetManager();
        assetManager.load("tile.png", Texture.class);
        assetManager.load("clik.p", ParticleEffect.class);
        assetManager.finishLoading();
        world = new World(new Vector2(), true);
        rayHandler = new RayHandler(world);
        engine = new PooledEngine();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        camera.update();
        RenderSystem renderSystem = new RenderSystem(camera);

        pool = new ParticleEffectPool(assetManager.get("clik.p", ParticleEffect.class), 1, 2);
        float sunDirection = MathUtils.random(0f, 360f);

        DirectionalLight sun = new DirectionalLight(
                rayHandler, 4 * RAYS_PER_BALL, Color.LIGHT_GRAY, sunDirection);
        int tileIndex = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Entity tile = new Entity();
                VisualComponent visualComponent
                        = new VisualComponent(new TextureRegion(assetManager.get("tile.png", Texture.class)));
                visualComponent.setDimension(new Vector2(WIDTH, HEIGHT));
                tile.add(visualComponent);
                PositionComponent positionComponent = new PositionComponent();
                positionComponent.setX(i * (WIDTH + SPACING) + Gdx.graphics.getWidth() / 2);
                positionComponent.setY(j * (HEIGHT + SPACING));
                tile.add(positionComponent);

                Rectangle rectangle = new Rectangle(positionComponent.getX(), positionComponent.getY(),
                        visualComponent.getWidth(), visualComponent.getHeight());
                BoundsComponent boundsComponent = new BoundsComponent(rectangle);
                tile.add(boundsComponent);
                tile.add(new ParticleComponent());

                StateComponent stateComponent = new StateComponent();
                stateComponent.setIndex(tileIndex++);
                tile.add(stateComponent);

                PolygonShape shape = new PolygonShape();
                shape.setAsBox(rectangle.getWidth() / 2 , rectangle.getHeight() / 2);

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                FixtureDef def = new FixtureDef();
                def.shape = shape;
                bodyDef.position.x = positionComponent.getX() + rectangle.getWidth()  /2 ;
                bodyDef.position.y = positionComponent.getY() + rectangle.getHeight()  /2;
                
             //   Body body = world.createBody(bodyDef);
             //   body.createFixture(def);
          //      body.getTransform().setPosition(positionComponent.getPosition());
                shape.dispose();

                BodyComponent bodyComponent = new BodyComponent();
          //      bodyComponent.setBody(body);
                tile.add(bodyComponent);

                LightComponent lightComponent = new LightComponent();
                PointLight light = new PointLight(
                        rayHandler, RAYS_PER_BALL, null, LIGHT_DISTANCE, 0f, 0f);
                light.setPosition(bodyDef.position.x, bodyDef.position.y);
              //  light.attachToBody(body, 0 , 0 );
                light.setColor(
                        MathUtils.random(),
                        MathUtils.random(),
                        MathUtils.random(),
                        1f);               
           //     light.setActive(false);
                lightComponent.setLight(light);
                 tile.add(lightComponent);
                engine.addEntity(tile);
            }
        }
        engine.addSystem(renderSystem);
        engine.addSystem(new TouchSystem(camera, pool));
       // engine.addSystem(new ParticleSystem());
        engine.addSystem(new StateSystem(tileIndex, 7));
        engine.addSystem(new PhysicsSystem(world, camera));
        engine.addSystem(new LightSystem(rayHandler, camera));

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(delta);
    }
}
