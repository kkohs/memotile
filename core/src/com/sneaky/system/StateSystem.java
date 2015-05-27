package com.sneaky.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.sneaky.component.StateComponent;
import com.sneaky.component.VisualComponent;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author Kristaps Kohs
 */
public class StateSystem extends IntervalIteratingSystem {
    private final ComponentMapper<StateComponent> mapper = ComponentMapper.getFor(StateComponent.class);
    private final ComponentMapper<VisualComponent> visualMapper = ComponentMapper.getFor(VisualComponent.class);
    private final Queue<Integer> toInitialize = new LinkedList<Integer>();
    private Integer currentIndex;

    public StateSystem(final Integer totalTiles, final Integer toSelect) {
        super(Family.all(StateComponent.class, VisualComponent.class).get(), 1);
        final SecureRandom random = new SecureRandom();
        for (int i = 0; i < toSelect; i++) {
            while ( true) {
                int index = random.nextInt(totalTiles);
                if (!toInitialize.contains(index)) {
                    toInitialize.add(index);
                    break;
                }
            }
        }
    }


    @Override
    protected void updateInterval() {
        currentIndex = toInitialize.poll();
        if (currentIndex == null)  {
            setProcessing(false);
        }
            super.updateInterval();
        
    }

    @Override
    protected void processEntity(final Entity entity) {
        StateComponent component = mapper.get(entity);
        VisualComponent visualComponent = visualMapper.get(entity);
        if (component.isInitialized() || currentIndex == null) {
            visualComponent.setColor(Color.WHITE);
        } else 
        if (currentIndex.equals(component.getIndex())) {
                component.setInitialized(true);
                component.setState(StateComponent.State.VALID);
                visualComponent.setColor(Color.YELLOW);
           
        } 
    }
}
