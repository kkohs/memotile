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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sneaky.component.PositionComponent;
import com.sneaky.component.VisualComponent;
import com.sneaky.system.RenderSystem;

/**
 * @author Kristaps Kohs
 */
public class GameScreen extends ScreenAdapter {
    private Engine engine;
    private OrthographicCamera camera;
    private AssetManager assetManager;

    @Override
    public void show() {
        super.show();
        assetManager = new AssetManager();
        assetManager.load("badlogic.jpg", Texture.class);
        assetManager.finishLoading();
        engine = new PooledEngine();
        OrthographicCamera camera = new OrthographicCamera(640, 480);
        camera.position.set(320, 240, 0);
        camera.update();
        RenderSystem renderSystem = new RenderSystem(camera);


        Entity tile = new Entity();
        tile.add(new VisualComponent(new TextureRegion(assetManager.get("badlogic.jpg", Texture.class))));
        tile.add(new PositionComponent());
        engine.addEntity(tile);
        engine.addSystem(renderSystem);

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(delta);
    }
}
