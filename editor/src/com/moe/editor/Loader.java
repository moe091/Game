package com.moe.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class Loader {
BodyEditorLoader loader;
Array<String> bodies = new Array<String>();

	public Loader(String loc) {
		loader = new BodyEditorLoader(Gdx.files.internal(loc));
		loadBasicBodies();
	}

	
	//Create one of these for each set of bodies I want to use
	public void loadBasicBodies() {
		bodies.clear();
		bodies.add("ball");
		bodies.add("blockPlastic");
		bodies.add("lPlastic");
		bodies.add("square1");
		bodies.add("laser1");
	}
	
	public String getSpritePath(String name) {
		return loader.getImagePath(name).substring(3);
	}
	
	public Sprite getSprite(String name) {
		return new Sprite(new Texture(getSpritePath(name)));
	}
	
	public BodyEditorLoader get() {
		return loader;
	}
	
	public String getBodyName(int num) {
		if (num < bodies.size)
			return bodies.get(num);
		else
			return bodies.get(bodies.size - 1);
	}

}
