package com.sneaky.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sneaky.component.BoundsComponent;
import com.sneaky.component.ParticleComponent;
import com.sneaky.component.PositionComponent;
import com.sneaky.component.VisualComponent;

/**
 * @author Kristaps Kohs
 */
public class ParticleSystem extends IteratingSystem {
    private SpriteBatch batch;
    private final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<ParticleComponent> particleMapper = ComponentMapper.getFor(ParticleComponent.class);
    public ParticleSystem(){
        super(Family.all(ParticleComponent.class, PositionComponent.class).get(),2);
        this.batch = new SpriteBatch();
    }

    @Override
    public void update(final float deltaTime) {
        batch.begin();
        super.update(deltaTime);
         batch.end();
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
           PositionComponent positionComponent = pm.get(entity);
           ParticleComponent particleComponent = particleMapper.get(entity);
        if (particleComponent.getEffect() != null) {
            particleComponent.getEffect().setPosition(positionComponent.getX(), positionComponent.getY());
            particleComponent.getEffect().draw(batch, deltaTime);
        }
    }
}
