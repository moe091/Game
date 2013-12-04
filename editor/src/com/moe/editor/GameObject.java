package com.moe.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/*
 * GameObject holds a body and a sprite. It has an update function that's called after the physics world updates to synchronize the sprite with its body
 * GameObject is in charge of managing the sprite/animation and keeping it in synch with the box2d body
 * for now it'll be initialized just by the bodyRef so it can grab the body and the sprite location from bodyloader
 * 
 */
public class GameObject {
//BodyRef string is used to get the body, origin, and sprite info
private Body body;
private Sprite sprite;
private Vector2 origin;
private ObjectBuilder builder;

//PlayScreen and model are always passed into the constructor(or always part of the builder object)
PlayScreen screen;
Model model;

//Other attributes from builder
private float rotationSpeed;
private float rotation;

	private GameObject(ObjectBuilder builder) {
		//Mandatory
		this(builder.model, builder.bodyRef, builder.x, builder.y, builder.bodyType, builder.scale, builder);
		
		//extra
		this.builder = builder;
		this.setRotation(builder.rotation);
	}

	//Only really need builder as a parameter. this stuf is all still here cause I didn't rewrite the code yet
	public GameObject(Model model, String bodyRef, float x, float y, BodyType bodType, float scale, ObjectBuilder builder) {
		this.screen = screen;
		this.model = model;
		
		Body body;
		 // 1. Create a BodyDef, as usual.
	    BodyDef bd = new BodyDef();
	    bd.position.set(x, y);
	    bd.type = bodType;
	    // 2. Create a FixtureDef, as usual.
	    FixtureDef fd = new FixtureDef();
	    fd.density = 1;
	    fd.friction = 0.5f;
	    fd.restitution = 0.3f;
	 
	    // 3. Create a Body, as usual.
	    body = model.world.createBody(bd);
	    
	    // 4. Create the body fixture automatically by using the loader.
	    model.loader.get().attachFixture(body, bodyRef, fd, scale);
	    System.out.println(bodyRef);

	    this.sprite = new Sprite(new Texture(model.loader.getSpritePath(bodyRef)));
	    float ratio = sprite.getHeight() / sprite.getWidth();
	    sprite.setSize(scale, scale * ratio);
	    this.setBody(body);
	    origin = model.loader.get().getOrigin(bodyRef, 0);
	    this.builder = builder;
	    builder.setGameObject(this);
	}
	
	public void delete() {
		model.removeObject(this);
	}
	
	public void update(float delta) {
		//body.setAngularVelocity(rotationSpeed);
		synch();
	}
	
	private void synch() {
		sprite.setRotation(getBody().getTransform().getRotation() * MathUtils.radiansToDegrees);
		sprite.setPosition(getBody().getTransform().getPosition().x + origin.x, getBody().getTransform().getPosition().y + origin.y);
		sprite.setOrigin(origin.x, origin.y);
	}
	
	private void setRotation(float rotation) {
		this.rotation = rotation;
		//set the actual rotation of the body to rotation
		//synch
	}
	
	
public static class ObjectBuilder	 {
		private String bodyRef;
		private PlayScreen screen;
		private Model model;
		private BodyType bodyType = BodyType.DynamicBody;
		private float x, y;
		private float scale = 2;
		private float rotSpeed;
		private float rotation;
		private float restitution = 0.8f;
		private float density = 0.5f;
		private float friction = 0.5f;
		
		private GameObject gameObject;
		
		public ObjectBuilder(String bodyRef, Model model) {
			this.bodyRef = bodyRef;
			this.screen = screen;
			this.model = model;
		}
		public ObjectBuilder bodyType(BodyType bodyType) {
			this.bodyType = bodyType;
			return this;
		}
		public ObjectBuilder location(float x, float y) {
			this.x = x;
			this.y = y;
			return this;
		}
		public ObjectBuilder scale(float scale) {
			this.scale = scale;
			return this;
		}
		public ObjectBuilder rotation(float rotation) {
			this.rotation = rotation;
			return this;
		}
		public ObjectBuilder rotSpeed(float rotSpeed) {
			this.rotSpeed = rotSpeed;
			return this;
		}
		public GameObject build() {
			return new GameObject(this);
		}
		public ObjectBuilder friction(float friction) {
			this.friction = friction;
			return this;
		}
		public ObjectBuilder density(float density) {
			this.density = density;
			return this;
		}
		public ObjectBuilder restitution(float restutution) {
			this.restitution = restitution;
			return this;
		}
		
		public GameObject getGameObject() {
			return gameObject;
		}
		public void setGameObject(GameObject obj) {
			this.gameObject = obj;
		}
	}


public Sprite getSprite() {
	return sprite;
}
public ObjectBuilder getBuilder() {
	return builder;
}

public Body getBody() {
	return body;
}
public void setBody(Body body) {
	this.body = body;
}

	//DOWN HERE I"LL PUT ALL THE GAMEOBJECT METHODS FOR EDITING GAME OBJECTS
	//THE KEY IS TO USE METHODS TO MODIFY PROPERTIES AND HAVE THE METHODS MAKE SURE THE OBJECT AND IT'S BUILDER STAY COORDINATED

}













