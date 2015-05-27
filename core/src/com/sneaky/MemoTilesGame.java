package com.sneaky;

import com.badlogic.gdx.Game;
import com.sneaky.screen.GameScreen;

/**
 * @author Kristaps Kohs
 */
public class MemoTilesGame extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());            
    }
}
