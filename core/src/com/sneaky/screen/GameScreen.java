package com.sneaky.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sneaky.component.BoundsComponent;
import com.sneaky.component.ParticleComponent;
import com.sneaky.component.PositionComponent;
import com.sneaky.component.VisualComponent;
import com.sneaky.system.ParticleSystem;
import com.sneaky.system.RenderSystem;
import com.sneaky.system.TouchSystem;

/**
 * @author Kristaps Kohs
 */
public class GameScreen extends ScreenAdapter {
    private Engine engine;
    private OrthographicCamera camera;
    private AssetManager assetManager;
    private ParticleEffectPool pool;

    @Override
    public void show() {
        super.show();
        assetManager = new AssetManager();
        assetManager.load("tile.png", Texture.class);
        assetManager.load("clik.p", ParticleEffect.class);
        assetManager.finishLoading();
        engine = new PooledEngine();
        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() /2 , Gdx.graphics.getWidth()  / 2, 0);
        camera.update();
        RenderSystem renderSystem = new RenderSystem(camera);
        
        pool = new ParticleEffectPool(assetManager.get("clik.p", ParticleEffect.class), 1,2);

for (int i = 0; i < 6; i++) {
    for (int j = 0 ; j < 8; j++) {
        Entity tile = new Entity();
        VisualComponent visualComponent 
                = new VisualComponent(new TextureRegion(assetManager.get("tile.png", Texture.class)));
        visualComponent.setDimension(new Vector2(32, 32));
        tile.add(visualComponent);
        PositionComponent positionComponent =new PositionComponent();
        positionComponent.setX(i * (32 + 10));
        positionComponent.setY(j * (32 + 10));
        tile.add(positionComponent);

        Rectangle rectangle = new Rectangle(positionComponent.getX(), positionComponent.getY(),
                visualComponent.getWidth(), visualComponent.getHeight());
        BoundsComponent boundsComponent = new BoundsComponent(rectangle);
        tile.add(boundsComponent);
        tile.add(new ParticleComponent());
        engine.addEntity(tile);
    }
}
        engine.addSystem(renderSystem);
        engine.addSystem(new TouchSystem(camera, pool));
        engine.addSystem(new ParticleSystem());

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(delta);
    }
}
