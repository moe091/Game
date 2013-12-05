package com.moe.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;

public class Resources {
	public static Resources instance;
	ObjectMap<String, Texture> textures;
	
	private Resources() {
		textures = new ObjectMap<String, Texture>();
	}
	
	public static Resources getInstance() {
		if (instance != null) 
			instance = new Resources();
		return instance;
	}
	
	public Texture getTexture(String name) {
		if (textures.get(name) != null)
			return textures.get(name);
		else {
			loadTexture(name, name);
			return textures.get(name);
		}
	}
	
	public void loadTexture(String name, String texture) {
		textures.put(name, new Texture(Gdx.files.internal("Graphics/Test/" + texture + ".png")));

	}
	
}
