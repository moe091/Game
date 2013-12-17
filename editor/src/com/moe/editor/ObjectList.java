package com.moe.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.moe.editor.GameObject.ObjectBuilder;
import com.moe.editor.framework.RenderableWindow;

/*
 * The Panel that holds a selection of objects to choose from. Select an object and then click to create(alt click is used to select/drag)
 * 
 */
public class ObjectList implements RenderableWindow {
private final float objectHeight = 50;
private final float objectWidth = 50;
private final int gap = 10;
private final float width = 100;
private final float height = 400;

private View view;
private Loader loader;
private Model model;
private Editor editor;

private float x, y, x2, y2;
private float scrollY;
int selection = -1;

private SpriteBatch batch = new SpriteBatch();
private Array<ObjectButton> buttons = new Array<ObjectButton>();

private Texture back = new Texture(Gdx.files.internal("Graphics/Test/objectbg.png"));
private Texture sidebg = new Texture(Gdx.files.internal("Graphics/Test/sidebg.png"));
private Texture selected = new Texture(Gdx.files.internal("Graphics/Test/objectselected.png"));
private Texture highlight = new Texture(Gdx.files.internal("Graphics/Test/highlight.png"));

private GameObject.ObjectBuilder curObject;

	public ObjectList(View view, Model model, Editor editor, float x, float y) {
		this.view = view;
		this.loader = model.loader;
		this.model = model;
		this.editor = editor;
		
		for (int i = 0; i < loader.bodies.size; i++) {
			buttons.add(new ObjectButton(loader.bodies.get(i), new Texture(loader.getSpritePath(loader.getBodyName(i)))));
		}
		this.x = x;
		this.y = y;
		this.x2 = x + width;
		this.y2 = y + height;
		
		curObject = new GameObject.ObjectBuilder("ball", model);
		curObject.bodyType(BodyType.DynamicBody).scale(2.4f).restitution(1.85f);
	}
	
	/*
	 * This method is called from the click method when an object is clicked. the i parameter is the index of the object that is clicked and
	 * is used to determine which object is selected. the selected objects builder is set as the editors curObjects. When the user clicks on the map
	 * part of the editor it creates an object at that location from the curObject builder. Also, a hotkey can be pressed to open a properties window,
	 * allowing the user to change the properties(static/dynamic/kinematic, restitution, scale, rotation, rotation speed, etc) of the object to be created.
	 * If an already created object is selected the properties window will modify that object instead.
	 */
	private void select(int i) {
		if (i < buttons.size) {
			curObject = buttons.get(i).builder;
			selection = i;
		}
		
		editor.updatePropertiesWindow(curObject);
		
	}

	// This just draws the actual panel
	public void draw(SpriteBatch camBatch) {
		batch.begin();
		
		batch.draw(sidebg, x - 20, y - 25, width + 20, height + 50);
		for (int i = 0; i < buttons.size; i++) {
			if (selection != i)
				batch.draw(back, x - 9, (y + i * (objectHeight + gap)) - 9, 68, 68);
			else
				batch.draw(selected, x - 9, (y + i * (objectHeight + gap)) - 9, 68, 68);
			if (buttons.get(i).texture.getWidth() <= buttons.get(i).texture.getHeight())
				batch.draw(buttons.get(i).texture, x, y + i * (objectHeight + gap), objectWidth, objectHeight * ((float) buttons.get(i).texture.getWidth() / (float) buttons.get(i).texture.getHeight()));
			else 
				batch.draw(buttons.get(i).texture, x, y + i * (objectHeight + gap) + 15, objectWidth, objectHeight * ((float) buttons.get(i).texture.getHeight() / (float) buttons.get(i).texture.getWidth()));
		}
		if (editor.isObjectSelected()) {
			if (editor.getSelectedObject() != null) {
				batch.draw(highlight, editor.getSelectedObject().getSprite().getX(), editor.getSelectedObject().getSprite().getY(), editor.getSelectedObject().getSprite().getWidth(), editor.getSelectedObject().getSprite().getHeight());
				//System.out.println("SELECTED x=" + editor.getSelectedObject().getSprite().getX());
			}
		}
		batch.end();
	}
	
	public GameObject.ObjectBuilder getCurObject() {
		return curObject;
	}

	public void setCurObject(GameObject.ObjectBuilder curObject) {
		this.curObject = curObject;
	}

	/*
	 * Inner class to wrap an object builder and a texture for the buttons. it links the texture used for a button with a complete objectBuilder to create that object
	 */
	private class ObjectButton {
		Texture texture;
		String name;
		GameObject.ObjectBuilder builder;
		
		public ObjectButton(String name, Texture texture) {
			this.texture = texture;
			this.name = name;
			builder = new GameObject.ObjectBuilder(name, model)
					.bodyType(BodyType.StaticBody)
					.scale(3f);
		}
	}

	@Override
	public boolean click(float x, float y) {
		if (x > this.x && x < this.x2 && y > this.y && y < this.y2) {
			relativeClick(x - this.x, y - this.y);
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public void relativeClick(float x, float y) {
		y-= scrollY;
		y = this.height - y - 13;
		select((int) ((int) y / (objectHeight + gap)));
		
	}

}
