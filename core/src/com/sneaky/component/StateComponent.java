package com.sneaky.component;

import com.badlogic.ashley.core.Component;

/**
 * @author Kristaps Kohs
 */
public class StateComponent extends Component {
    private Integer index;
    private State state = State.INVALID;
    private boolean initialized;

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(final Integer index) {
        this.index = index;
    }


    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(final boolean initialized) {
        this.initialized = initialized;
    }

    public enum State {VALID, INVALID}
}
