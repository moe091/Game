package com.moe.editor.surface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.moe.editor.Controller;
import com.moe.editor.Model;
import com.moe.editor.PlayController;
import com.moe.editor.View;

public class GameManager {
	public boolean playing = false;
	
	View view;
	Model model;
	Ball ball;
	OrthographicCamera camera;
	
	public GameManager(View view, Model model, OrthographicCamera camera) {
		this.view = view;
		this.model = model;
		this.camera = camera;
		
		
	}
	

	public void update(float delta) {
		camera.position.set(ball.getBody().getTransform().getPosition().x, ball.getBody().getPosition().y, 0);
		camera.update();
	}
	
	
	
	
	public void startPlaying(float x, float y) {
		if (!playing) {
			view.setController(new PlayController(view, camera));
			makeBall(x, y);
			playing = true;
		}
	}
	
	public void stopPlaying() {
		ball.delete();
		ball = null;
	}
	
	public void makeBall(float x, float y) {
		ball = Ball.getNewBall(model, x, y);
	}
}
