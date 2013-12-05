package com.moe.editor;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Editor {
	/*
	 * Either an object builder(new object from menu on the right) or an existing object(object already in the world
	 * that has been selected to be edited) can eb selected at once. If an existing object is selected that gameObjects
	 * object builder will be stored in curObject. otherwise the objectButton from the objectLists ObjectBuilder will be
	 * selected. on update, the curObject objectBuilder will be updates. If objectSelected = true that means an existing
	 * object is selected, and upon updating the object will be deleted and a new one will be created from the objectBuilder
	 */
	private boolean objectSelected = false;
	private GameObject.ObjectBuilder curObject;
	private GameObject selectedObject;
	
	private View view;
	private Model model;
	private Properties properties;
	
	public Editor(View view, Model model) {
		this.view = view;
		this.model = model;
		
		curObject = new GameObject.ObjectBuilder("ball", model);
		curObject.bodyType(BodyType.DynamicBody).scale(2.4f).restitution(1.85f);
	}
	
	
	//Creates the current Object. In the future the current object will be decided based on which one is selected in the editor.
	//the view class will call a method in the editor that returns the current object and creates that
	//for now it'll create whatever I hardcode into this method
	public void createObject(Vector3 vec) {
		model.queueObject(curObject.location(vec.x, vec.y));
	}
	
	/*
	 * this method is called to set the curObject object builder. the curObject builder is used to create an object when the user clicks the 
	 * screen
	 */
	public void setCurObject(GameObject.ObjectBuilder curObject, boolean objSelected) {
		this.curObject = curObject;
		this.objectSelected = objSelected;
	}
	public boolean isObjectSelected() {
		return objectSelected;
	}

	public void openProperties() {
		if (properties == null) {
			properties = new Properties(model, this);
			properties.setVisible(true);
		}
	}
	
	//Called from the properties editor, tells Editor to update the current object. Calls updateCurObject at appropriate time
	//depending on whether an objectBuilder or an existing object is selected.
	public void updateProperties() {
		System.out.println("update properties");
		if (objectSelected) {
			Vector3 vec = new Vector3(curObject.getGameObject().getBody().getTransform().getPosition().x, curObject.getGameObject().getBody().getTransform().getPosition().y, 0);
			curObject.getGameObject().delete();
			objectSelected = false;
			updateCurObject();
			createObject(vec);
		} else {
			updateCurObject();
		}
	}
	
	//called from updateProperties(), actually updates the curObject ObjectBuilder.
	public void updateCurObject() {
		System.out.println("updateCurObject()");
		curObject.bodyType(properties.getBodyType());
		curObject.rotSpeed(properties.getRotSpeed());
		curObject.rotation(properties.getRotation());
		curObject.scale(properties.getScale());
	}
}
