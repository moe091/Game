package com.moe.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class View {
private Model model;
private SpriteBatch batch;
private OrthographicCamera camera;
private Controller controller;
private Editor editor;
private ObjectList objList;


	public View(Model model, OrthographicCamera camera) {
		this.model = model;
		this.camera = camera;
		editor = new Editor(this, model);
		batch = new SpriteBatch();
		this.controller = new Controller(this, camera);
		objList = new ObjectList(this, model, editor, 730, 50);
	}
	
	public void render(float delta) {
		//have the controller update the camera and it's current state based on input, then update camera
		//any objects created and stuff like that will be done before these methods are called, as they will happen
		//in the controllers update method
		controller.update(delta);
		camera.update();

		//setup the batch and draw all the objects in the world
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int i = model.getNumObjects() - 1; i >= 0; i--) {
			model.getObject(i).getSprite().draw(batch);
		}
		batch.end();
		
		//draw the object list bar on top of everything else
		objList.draw();
	}
	
	

	/*
	 * used to handle click events coming from the controller class. View mediates between controller and model so input events will pass through 
	 * the view class in order to keep the control decoupled from the model
	 */
	public void click(Vector3 vec) {
		if (!objList.checkClick(vec.x, vec.y)) {
			camera.unproject(vec);
			editor.createObject(vec);
		}
	}

	public void openProperties() {
		editor.openProperties();
		
	}

	public void altClick(Vector3 vec) {
		System.out.println("x = " + vec.x + ", y = " + vec.y);
		camera.unproject(vec);
		System.out.println("x = " + vec.x + ", y = " + vec.y);
		
		for (int i = 0; i < model.getNumObjects(); i++) {
			if (model.isTouching(i, vec)) {
				editor.setCurObject(model.getObject(i).getBuilder(), true);
				
			}
		}
		
	}

	public void drag(float x, float y) {
		if (editor.isObjectSelected()) {
			
		}
		
	}
}
