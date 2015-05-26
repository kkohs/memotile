package com.sneaky.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Kristaps Kohs
 */
public class PositionComponent extends Component {
    private Vector2 position = new Vector2();

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(final Vector2 position) {
        this.position.set(position);
    }
    
    public void setX(final float x) {
        this.position.x = x;
    }    
    
    public void setY(final float y) {
        this.position.y = y;
    }
    
    public float getX() {
        return position.x;
    }   
    public float getY() {
        return position.y;
    }
}
