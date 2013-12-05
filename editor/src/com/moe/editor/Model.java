package com.moe.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

/*
 * Model holds the world and all the objects in it. It has an update function which can be called to update the world and objects
 * Model doesn't need to know about anything, the view can access it to know what and where to draw and the controller can access it to
 * update things and make changes based on input. 
 * 
 * This is the internal representation of the entire game world
 */
public class Model {
	//the physics world and loader(might move loader later)
	World world;
	Loader loader; //might move
	//the array that holds all the objects in the game
	private Array<GameObject> gameObjects = new Array<GameObject>();
	private Array<GameObject.ObjectBuilder> creationQueue = new Array<GameObject.ObjectBuilder>();
	
	//initialize world and loader
	public Model() {
		 loader = new Loader("Physics/bodies.json");
		 loader.loadBasicBodies();
		 world = new World(new Vector2(0, -5), true);
	}

	//loop through all objects and call their update methods, update box2dWorld
	public void update(float delta) {
		world.step(delta, 7, 3);
		for (int i = creationQueue.size - 1; i >= 0; i--) {
			gameObjects.add(creationQueue.get(i).build());
			creationQueue.removeIndex(i);
		}
		for (int i = 0; i < gameObjects.size; i++) {
			gameObjects.get(i).update(delta);
		}
	}
	public void tempSetup() {
	
	}
	
	
	// Some methods used to access and modify the gameObjects list, in the interest of OOP and encapsulation
	public void addObject(GameObject object) {
		gameObjects.add(object);
	}
	public int getNumObjects() {
		return gameObjects.size;
	}
	public GameObject getObject(int num) {
		return gameObjects.get(num);
	}
	
	
	public boolean isTouching(int num, Vector3 vec) {
		if (gameObjects.get(num).getSprite().getBoundingRectangle().contains(vec.x, vec.y))
			return true;
		else
			return false;
	}

	public void removeObject(GameObject gameObject) {
		world.destroyBody(gameObject.getBody());
		gameObjects.removeValue(gameObject, true);
		
	}

	public void queueObject(GameObject.ObjectBuilder build) {
		creationQueue.add(build);
		
	}
}
