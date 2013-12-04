package com.moe.editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

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

public class Properties extends JFrame {
	private Model model;
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


	public Properties(Model model, Editor editor) {
		this.model = model;
		this.editor = editor;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				updateClicked();
			}
		});
		contentPane.add(btnUpdate, "4, 20");
	}


	protected void updateClicked() {
		System.out.println("update clicked");
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
			return Integer.parseInt(txtRotationSpeed.getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}


	public float getRotation() {
		try {
			return Integer.parseInt(txtRotation.getText());
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

}
