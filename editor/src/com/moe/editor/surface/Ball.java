package com.moe.editor.surface;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.moe.editor.GameObject;
import com.moe.editor.Model;

public class Ball extends GameObject {

	
	
	public static Ball getNewBall(Model model, float x, float y) {
		
		
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyDef.BodyType.DynamicBody;
		
		FixtureDef fd = new FixtureDef();
		fd.restitution = 0.75f;
		fd.density = 0.8f;
		fd.friction = 0.9f;
		
		CircleShape circle = new CircleShape();
		circle.setRadius(1.5f);
		
		fd.shape = circle;
		
		Body body = model.world.createBody(bd);
		Fixture fixture = body.createFixture(fd);
		
		return new Ball(model, body);
	}
	public Ball(Model model, Body body) {
		super(model, body);
	}
}
