package com.sneaky.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;

/**
 * @author Kristaps Kohs
 */
public class ParticleComponent extends Component{
    private ParticleEffectPool.PooledEffect effect;

    public ParticleEffectPool.PooledEffect getEffect() {
        return effect;
    }

    public void setEffect(final ParticleEffectPool.PooledEffect effect) {
        this.effect = effect;
    }
}
