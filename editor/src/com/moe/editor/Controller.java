package com.moe.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public abstract class Controller {
final int LEFT = Keys.A;
final int RIGHT = Keys.D;
final int UP = Keys.W;
final int DOWN = Keys.S;
final int ZOOMOUT = Keys.R;
final int ZOOMIN = Keys.F;
final int SPEED = Keys.SHIFT_LEFT;
final int ALT = Keys.ALT_LEFT;
final int PROPERTIES = Keys.T;
final int PLAY = Keys.P;

final float regSpeed = 5;
final float fastSpeed = 25;

public boolean left = false;
public boolean right = false;
public boolean up = false;
public boolean down = false;
public boolean play = false;
public boolean shift = false;


static Vector3 mouseVec;
float[] downx, downy;
float[] curx, cury;
float[] deltax, deltay;
float[] timeDown;
boolean[] isDown;
boolean[] downEvent;
boolean[] upEvent;


Vector3 vec;
View view;
OrthographicCamera camera;
float speed = 5;

	public Controller(View view, OrthographicCamera camera) {
		this.view = view;
		this.camera = camera;
		
		mouseVec = new Vector3();
		
		vec = new Vector3();
		
		downx = new float[2];
		downy = new float[2];
		curx = new float[2];
		cury = new float[2];
		deltax = new float[2];
		deltay = new float[2];
		timeDown = new float[2];
		isDown = new boolean[2];
		downEvent = new boolean[2];
		upEvent = new boolean[2];
	}
	
	public void update(float delta) {
		play = false;
		if (Gdx.input.isKeyPressed(PLAY)) {
			play = true;
		}
		
		//set speed, depending on whether the SPEED key is held
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			shift = true;
		else
			shift = false;
		if (Gdx.input.isKeyPressed(SPEED)) {
			speed = fastSpeed;
		} else {
			speed = regSpeed;
		}
		
		//check for movement keys and translate/zoom camera based on which ones are pressed
		if (Gdx.input.isKeyPressed(LEFT))
			left = true;
		else
			left = false;
		
		if (Gdx.input.isKeyPressed(RIGHT))
			right = true;
		else
			right = false;
		
		if (Gdx.input.isKeyPressed(UP))
			up = true;
		else
			up = false;
		
		if (Gdx.input.isKeyPressed(DOWN))
			down = true;
		else
			down = false;
		
		
		for (int i = 0; i < 2; i++) {
			downEvent[i] = false;
			upEvent[i] = false;
			if (Gdx.input.isTouched(i))	 {
				if (isDown[i]) {
					//Touch Drag event
					curx[i] = Gdx.input.getX(i);
					cury[i] = Gdx.input.getY(i);
					
					deltax[i] = curx[i] - downx[i];
					deltay[i] = cury[i] - downy[i];
					
					timeDown[i]+= delta;
				} else {
					//Touch Down event
					isDown[i] = true;
					downEvent[i] = true;
					timeDown[i] = 0;
					
					downx[i] = Gdx.input.getX(i);
					downy[i] = Gdx.input.getY(i);
					
					curx[i] = Gdx.input.getX(i);
					cury[i] = Gdx.input.getY(i);
				}
			} else {
				if (isDown[i]) { //UP EVENT
					upEvent(false);
					upEvent[i] = true;
					isDown[i] = false;
				} else {
					//Nothing happening
				}
			}
			
			if (downEvent[i]) {
				if (Gdx.input.isKeyPressed(ALT)) {
					downEvent(true);
				} else {
					downEvent(false);
				}
			} else if (isDown[i]) {
				dragEvent(false);
			}
			
			mouseVec.set(curx[0], cury[0], 0);
			camera.project(mouseVec);
		
			
		
		}
		

	}
	/*
	 * TOOLTIP
	 * downx and downy for coords
	 */
	public abstract void downEvent(boolean alt);
	
	//deltax and deltay for drag distance. curx/cury for current coords. need to ad shit to keep track of drag distance from last frame only
	public abstract void dragEvent(boolean alt);
	
	// curx/cury for coords
	public abstract void upEvent(boolean alt);
}
