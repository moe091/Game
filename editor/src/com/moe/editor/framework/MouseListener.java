package com.moe.editor.framework;

import com.moe.editor.Controller;

public interface MouseListener {

	public void startDrag(float x, float y);
	public void drag(float deltaX, float deltaY);
	
	
}
