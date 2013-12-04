package com.moe.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.moe.editor.GameObject.ObjectBuilder;

/*
 * The Panel that holds a selection of objects to choose from. Select an object and then click to create(alt click is used to select/drag)
 * 
 */
public class ObjectList {
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
	}
	
	/*
	 * Checks if a click is on the objectList panel. if it is it returns true and sends the click event to the click method
	 * if not it just returns falls with no side-effect
	 */
	public boolean checkClick(float x, float y) {
		y = Gdx.graphics.getHeight() - y;
		if (x > this.x && x < this.x2 && y > this.y && y < this.y2) {
			click(x - this.x, y - this.y);
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * This is the method that is handles a click on the actual panel. The x and y parameters are the local coordinates of the click
	 */
	private void click(float x, float y) {
		y-= scrollY;
		select((int) y / (50 + gap));
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
			editor.setCurObject(buttons.get(i).builder, false);
			selection = i;
		}
		
	}


	// This just draws the actual panel
	public void draw() {
		batch.begin();
		
		batch.draw(sidebg, x - 20, y - 100, width + 20, height + 200);
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
		batch.end();
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

}
