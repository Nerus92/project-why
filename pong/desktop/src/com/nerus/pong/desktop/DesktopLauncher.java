package com.nerus.pong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nerus.pong.Pong;
import com.nerus.pong.PongGDX;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Pong pong = new Pong();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 512;
        config.height = 256;
		new LwjglApplication(new PongGDX(pong), config);
	}
}
