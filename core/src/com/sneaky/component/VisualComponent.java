package com.sneaky.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

/**
 * @author Kristaps Kohs
 */
public class VisualComponent extends Component implements Pool.Poolable {
    private TextureRegion textureRegion;

    public VisualComponent(final TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(final TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public void reset() {
        
    }
}
