package com.moe.editor;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.moe.editor.GameObject.ObjectBuilder;

/*
 * controls editing game objects
 */

public class Editor {
	/*
	 * Either an object builder(new object from menu on the right) or an existing object(object already in the world
	 * that has been selected to be edited) can eb selected at once. If an existing object is selected that gameObjects
	 * object builder will be stored in curObject. otherwise the objectButton from the objectLists ObjectBuilder will be
	 * selected. on update, the curObject objectBuilder will be updates. If objectSelected = true that means an existing
	 * object is selected, and upon updating the object will be deleted and a new one will be created from the objectBuilder
	 */
	private boolean objectSelected = false;
	private int selIndex;
	private GameObject selectedObject;
	
	private View view;
	private Model model;
	private Properties properties;
	
	public Editor(View view, Model model) {
		this.view = view;
		this.model = model;
		model.setEditor(this);
	}
	
	
	//Creates the current Object. In the future the current object will be decided based on which one is selected in the editor.
	//the view class will call a method in the editor that returns the current object and creates that
	//for now it'll create whatever I hardcode into this method
	public void createObject(Vector3 vec, GameObject.ObjectBuilder builder) {
		model.queueObject(builder.location(vec.x, vec.y));
	}
	
	
	public void setObject(GameObject object) {
		selectedObject = object;
		if (selectedObject != null) {
			objectSelected = true;
			selIndex = model.getIndex(object);
			if (properties != null) {
				properties.updatePropertiesWindow(object.getBuilder());
				System.out.println("selection - " + object.getBuilder().getScale());
			}
		} else {
			objectSelected = false;
			selIndex = -1;
		}
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
	
	public GameObject getSelectedObject() {
		return selectedObject;
	}
	//Called from the properties editor, tells Editor to update the current object. Calls updateCurObject at appropriate time
	//depending on whether an objectBuilder or an existing object is selected.
	public void updateProperties() {
			Vector3 vec = new Vector3(selectedObject.getBody().getTransform().getPosition().x, selectedObject.getBody().getTransform().getPosition().y, 0);
			
			updateExistingObject(vec, selectedObject);
			properties.updatePropertiesWindow(selectedObject.getBuilder());

	}
	
	private void updateExistingObject(Vector3 vec, GameObject obj) {
		model.world.destroyBody(obj.getBody());
		GameObject.ObjectBuilder builder = obj.getBuilder();
		updateCurObject(builder);
		
		model.removeObject(obj);
		createObject(vec, builder);
		this.setObject(null);
	}


	//called from updateProperties(), actually updates the curObject ObjectBuilder.
	public void updateCurObject(GameObject.ObjectBuilder builder) {
		builder.bodyType(properties.getBodyType());
		builder.rotSpeed(properties.getRotSpeed());
		builder.rotation(properties.getRotation());
		builder.scale(properties.getScale());
		builder.friction(properties.getFriction());
		builder.restitution(properties.getRestitution());
		builder.density(properties.getDensity());
	}


	public void blankClick() {
		
		
	}


	public void updatePropertiesWindow(ObjectBuilder curObject) {
		if (properties != null)
			properties.updatePropertiesWindow(curObject);
		
	}


	public void updateBuilderProps() {
		updateCurObject(view.getSelectedBuilder());
		properties.updatePropertiesWindow(view.getSelectedBuilder());
		System.out.println("IM THE ELSE");
		
	}
}
