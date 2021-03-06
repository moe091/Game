package com.moe.editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Properties extends JFrame {
	private Editor editor;
	
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtName;
	private JTextField txtBodyref;
	private JTextField txtDensity;
	private JTextField txtFriction;
	private JTextField txtRestitution;
	private JRadioButton rdbtnDynamic;
	private JRadioButton rdbtnStatic;
	private JRadioButton rdbtnKinematic;
	private JLabel lblScale;
	private JTextField txtScale;
	private JLabel lblRotation;
	private JTextField txtRotation;
	private JLabel lblRotspeed;
	private JTextField txtRotationSpeed;
	private JButton btnUpdate;
	private JLabel lblSelectedobject;
	private JButton btnUpdatebuilder;
	private JLabel lblSelection;
	private JRadioButton rdbtnEdit;
	private JRadioButton rdbtnCreate;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JButton btnDeleteObject;


	public Properties(Model model, Editor editor) {
		this.editor = editor;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		rdbtnDynamic = new JRadioButton("Dynamic");
		buttonGroup.add(rdbtnDynamic);
		contentPane.add(rdbtnDynamic, "2, 2, left, default");
		
		rdbtnStatic = new JRadioButton("Static");
		buttonGroup.add(rdbtnStatic);
		contentPane.add(rdbtnStatic, "4, 2");
		
		rdbtnKinematic = new JRadioButton("Kinematic");
		buttonGroup.add(rdbtnKinematic);
		contentPane.add(rdbtnKinematic, "6, 2, left, default");
		
		lblSelection = new JLabel("selection");
		contentPane.add(lblSelection, "8, 2");
		
		JLabel lblName = new JLabel("Name");
		contentPane.add(lblName, "2, 4, right, default");
		
		txtName = new JTextField();
		txtName.setText("name");
		contentPane.add(txtName, "4, 4, left, center");
		txtName.setColumns(10);
		
		JLabel lblBodyref = new JLabel("BodyRef");
		contentPane.add(lblBodyref, "6, 4, right, default");
		
		txtBodyref = new JTextField();
		txtBodyref.setText("bodyref");
		contentPane.add(txtBodyref, "8, 4, fill, default");
		txtBodyref.setColumns(10);
		
		JLabel lblDensity = new JLabel("Density");
		contentPane.add(lblDensity, "2, 6, right, default");
		
		txtDensity = new JTextField();
		txtDensity.setText("Density");
		contentPane.add(txtDensity, "4, 6, fill, default");
		txtDensity.setColumns(10);
		
		JLabel lblFriction = new JLabel("Friction");
		contentPane.add(lblFriction, "6, 6, right, default");
		
		txtFriction = new JTextField();
		txtFriction.setText("Friction");
		contentPane.add(txtFriction, "8, 6, fill, default");
		txtFriction.setColumns(10);
		
		JLabel lblRestitution = new JLabel("Restitution");
		contentPane.add(lblRestitution, "2, 8, right, default");
		
		txtRestitution = new JTextField();
		txtRestitution.setText("Restitution");
		contentPane.add(txtRestitution, "4, 8, fill, default");
		txtRestitution.setColumns(10);
		
		lblScale = new JLabel("Scale");
		contentPane.add(lblScale, "6, 8, right, default");
		
		txtScale = new JTextField();
		txtScale.setText("Scale");
		contentPane.add(txtScale, "8, 8, fill, default");
		txtScale.setColumns(10);
		
		lblRotation = new JLabel("Rotation");
		contentPane.add(lblRotation, "2, 10, right, default");
		
		txtRotation = new JTextField();
		txtRotation.setText("Rotation");
		contentPane.add(txtRotation, "4, 10, fill, default");
		txtRotation.setColumns(10);
		
		lblRotspeed = new JLabel("RotSpeed");
		contentPane.add(lblRotspeed, "6, 10, right, default");
		
		txtRotationSpeed = new JTextField();
		txtRotationSpeed.setText("Rot Speed");
		contentPane.add(txtRotationSpeed, "8, 10, fill, default");
		txtRotationSpeed.setColumns(10);
		
		rdbtnEdit = new JRadioButton("Edit");
		rdbtnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				editModeChange();
			}
		});
		buttonGroup_1.add(rdbtnEdit);
		contentPane.add(rdbtnEdit, "4, 12");
		
		rdbtnCreate = new JRadioButton("Create");
		rdbtnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				editModeChange();
			}
		});
		buttonGroup_1.add(rdbtnCreate);
		contentPane.add(rdbtnCreate, "6, 12");
		
		lblSelectedobject = new JLabel("selectedObject");
		contentPane.add(lblSelectedobject, "4, 14");
		
		btnUpdate = new JButton("updateObject");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				updateClicked();
			}
		});
		contentPane.add(btnUpdate, "4, 16");
		
		btnUpdatebuilder = new JButton("updateBuilder");
		btnUpdatebuilder.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				updateBuilderClick();
			}
		});
		contentPane.add(btnUpdatebuilder, "6, 16");
		
		btnDeleteObject = new JButton("Delete Object");
		btnDeleteObject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				deleteObject();
			}
		});
		contentPane.add(btnDeleteObject, "4, 18");
	}

	protected void deleteObject() {
		editor.getSelectedObject().delete();
		
	}

	protected void editModeChange() {
		if (rdbtnEdit.isSelected()) {
			editor.mode = editor.EDIT;
		} else if (rdbtnCreate.isSelected()) {
			editor.mode = editor.CREATE;
		}
		System.out.println("mode = " + editor.mode);
		
	}

	protected void updateBuilderClick() {
		editor.updateBuilderProps();
		
	}

	public float getFriction() {
		try {
			return Float.parseFloat(txtFriction.getText());
		} catch (NumberFormatException e) {
			System.out.println("Friction format error -Properties");
			return 0.5f;
		}
	}
	public float getDensity() {
		try {
			return Float.parseFloat(txtDensity.getText());
		} catch (NumberFormatException e) {
			System.out.println("Friction format error -Properties");
			return 0.5f;
		}
	}
	public float getRestitution() {
		try {
			return Float.parseFloat(txtRestitution.getText());
		} catch (NumberFormatException e) {
			System.out.println("Friction format error -Properties");
			return 0.5f;
		}
	}
	protected void updateClicked() {
		editor.updateProperties();
	}


	public BodyType getBodyType() {
		if (rdbtnDynamic.isSelected()) {
			return BodyType.DynamicBody;
		} else if (rdbtnKinematic.isSelected()) {
			return BodyType.KinematicBody;
		} else {
			return BodyType.StaticBody;
		}
	}


	public float getRotSpeed() {
		try {
			return Float.parseFloat(txtRotationSpeed.getText()) * MathUtils.degreesToRadians;
		} catch (NumberFormatException e) {
			return 0;
		}
	}


	public float getRotation() {
		try {
			return Float.parseFloat(txtRotation.getText()) * MathUtils.degreesToRadians;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public float getScale() {
		try {
			return Float.parseFloat(txtScale.getText());
		} catch (NumberFormatException e) {
			return 1;
		}
	}
	
	
	
	
	public void updatePropertiesWindow(GameObject.ObjectBuilder builder) {
		if (builder.getGameObject() != null) {
			this.rdbtnEdit.doClick();
			this.lblSelectedobject.setText(builder.name);
			editModeChange();
		}
		this.txtBodyref.setText(builder.getBodyRef());
		this.txtScale.setText(Float.toString(builder.getScale()));
		if (builder.getBodyType() == BodyType.DynamicBody) 
			this.rdbtnDynamic.doClick();
		else if (builder.getBodyType() == BodyType.StaticBody) {
			this.rdbtnStatic.doClick();
		} else if (builder.getBodyType() == BodyType.KinematicBody) {
			this.rdbtnKinematic.doClick();
		}
		this.txtName.setText(builder.name + " ");
		this.txtRestitution.setText(Float.toString(builder.getRestitution()));
		this.txtFriction.setText(Float.toString(builder.getFriction()));
		this.txtDensity.setText(Float.toString(builder.getDensity()));
		this.txtRotation.setText(Float.toString(builder.getRotation() * MathUtils.radiansToDegrees));
		
		if (editor.isObjectSelected())
			lblSelection.setText("Object");
		else
			lblSelection.setText("Builder");
		
		System.out.println("UPDATE SHIT " + builder.getScale());
	}

	public void setMode(int mode) {
		if (mode == editor.CREATE) {
			rdbtnCreate.doClick();
			this.lblSelectedobject.setText(editor.getBuilder().name);
			editModeChange();
		} else if (mode == editor.EDIT) {
			rdbtnEdit.doClick();
			this.lblSelectedobject.setText(editor.getSelectedObject().getBuilder().name);
			editModeChange();
		}
	}

	public int getMode() {
		if (rdbtnCreate.isSelected())
			return editor.CREATE;
		else
			return editor.EDIT;
	}

}
