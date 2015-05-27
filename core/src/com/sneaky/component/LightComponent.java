package com.sneaky.component;

import box2dLight.Light;
import com.badlogic.ashley.core.Component;

/**
 * @author Kristaps Kohs
 */
public class LightComponent extends Component {
    private Light light;

    public Light getLight() {
        return light;
    }

    public void setLight(final Light light) {
        this.light = light;
    }
}
