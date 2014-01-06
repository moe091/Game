package com.moe.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.moe.editor.framework.MouseListener;

public class PlayController extends Controller {
Array<MouseListener> mouseListeners = new Array<MouseListener>();


	public PlayController(View view, OrthographicCamera camera) {
		super(view, camera);
	}
	
	@Override
	public void update(float delta) {
		//set speed, depending on whether the SPEED key is held
		
		if (Gdx.input.isKeyPressed(PROPERTIES)) {
			view.openProperties();
		}
		
		
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
					System.out.println(curx[i] + ", " + cury[i]);
				}
			} else {
				if (isDown[i]) {
					upEvent[i] = true;
					isDown[i] = false;
				} else {
					//Nothing happening
				}
			}
			
			if (downEvent[i]) {
				if (Gdx.input.isKeyPressed(ALT)) {
					vec.set(downx[i], downy[i], 0);
					view.altClick(vec);
				} else {
					vec.set(downx[i], downy[i], 0);
					view.click(vec);
				}
			} else if (isDown[i]) {
				view.drag(deltax[i], deltay[i]);
			}
			
			mouseVec.set(curx[0], cury[0], 0);
			camera.project(mouseVec);
		
			
		
		}
		

	}

	@Override
	public void downEvent(boolean alt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragEvent(boolean alt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upEvent(boolean alt) {
		// TODO Auto-generated method stub
		
	}
}
