package com.moe.editor.framework;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface RenderableWindow {
	
	
	public void draw(SpriteBatch drawBatch);//Render
	
	public boolean click(float x, float y);//Click, not taking into account the offset of the renderableWindow
	
	public void relativeClick(float x, float y); // Click, x and y should be the coordinates relative to the window
	
	
}
