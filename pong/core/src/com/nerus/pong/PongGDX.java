package com.nerus.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PongGDX extends ApplicationAdapter {
	SpriteBatch batch;
	TextureAtlas atlas;
	Sprite ballSprite;
	Sprite paddleSprite;
	Pong pong;
	boolean paused = false;

	public PongGDX(Pong pong) {
	    super();
	    this.pong = pong;
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("pong.atlas");
		TextureAtlas.AtlasRegion region = atlas.findRegion("ball");
        ballSprite = new Sprite(region);
        region = atlas.findRegion("paddle");
        paddleSprite = new Sprite(region);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!paused) pong.update();

		batch.begin();
		ballSprite.setPosition(pong.getBallXPosition(), pong.getBallYPosition());
		ballSprite.draw(batch);
		paddleSprite.setPosition(pong.getPaddleXPosition(0), pong.getPaddleYPosition(0));
		paddleSprite.draw(batch);
		paddleSprite.setPosition(pong.getPaddleXPosition(1), pong.getPaddleYPosition(1));
		paddleSprite.draw(batch);
		batch.end();

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
		    Gdx.app.exit();
        }
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		atlas.dispose();
	}
}
