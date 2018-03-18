package com.nerus.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PongGDX extends ApplicationAdapter {
	SpriteBatch batch;
	TextureAtlas atlas;
	Sprite sprite;
	Pong pong;

	public PongGDX(Pong pong) {
	    super();
	    this.pong = pong;
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("pong.atlas");
		TextureAtlas.AtlasRegion region = atlas.findRegion("ball");
        sprite = new Sprite(region);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		pong.update();
		sprite.setPosition(pong.getBallXPosition(), pong.getBallYPosition());
		sprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		atlas.dispose();
	}
}
