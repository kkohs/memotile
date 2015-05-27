package com.sneaky.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Kristaps Kohs
 */
public class BoundsComponent extends Component {
    private Rectangle rectangle;

    public BoundsComponent(final Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(final Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
