package com.moe.editor.framework;

public interface DragListener {

	public void startDrag(float x, float y);
	public void drag(float deltaX, float deltaY);
}
