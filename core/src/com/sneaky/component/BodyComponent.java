package com.sneaky.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * @author Kristaps Kohs
 */
public class BodyComponent extends Component {
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(final Body body) {
        this.body = body;
    }
}
