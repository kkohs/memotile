package com.sneaky.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * @author Kristaps Kohs
 */
public class VisualComponent extends Component implements Pool.Poolable {
    private TextureRegion textureRegion;
    private Vector2 dimension = new Vector2();
    private boolean visible;
    
    private Color color = Color.WHITE;

    public VisualComponent(final TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        this.dimension.set(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(final TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public Vector2 getDimension() {
        return dimension;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setDimension(final Vector2 dimension) {
        this.dimension.set(dimension);
    }

    public void setVisible(final boolean visible) {
        this.visible = visible;
    }

    public void setWidth(final float width) {
        this.dimension.x = width;
    }

    public void setHeight(final float height) {
        this.dimension.y = height;
    }

    public float getWidth() {
        return dimension.x;
    }
    public float getHeight() {
        return dimension.y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
    }

    @Override
    public void reset() {
        
    }
}
