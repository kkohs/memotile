package com.memotile.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sneaky.MemoTilesGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 320;
        config.height = 480;
        config.title = "TESTAPP";
        new LwjglApplication(new MemoTilesGame(), config);
    }
}
