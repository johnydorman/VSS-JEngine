package uk.johndorman.core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import uk.johndorman.GFX.Textures;
import uk.johndorman.enums.RenderType;
import uk.johndorman.lib.Vector2;

public abstract class GameObject {
	
	protected int object_id;
	protected boolean active;
	
	protected BufferedImage texture;
	protected RenderType renderType;
	
	protected Vector2 position, rotation, velocity, scale;

	
	/*
	 * Default Constructor
	 */
	public GameObject(int id) {
		object_id = id;
		active = false;
		
		position = new Vector2(0,0);
		rotation = new Vector2(0,0);
		velocity = new Vector2(0,0);
		scale = new Vector2(1,1);
		
		texture = Textures.DEFAULT_TEXTURE;
		renderType = RenderType.DEFAULT;
	}
	
	/**
	 * Set the Object's to Activity
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
		if(active)
			onActive();
	}
	
	/**
	 * Run on Object Activation
	 */
	public abstract void onActive();
	
	
	/**
	 * Action Update
	 */
	public void tick() {
		Update();
	}
	
	public abstract void Update();
	
	/**
	 * Render Update
	 * @param g
	 */
	public abstract void render(Graphics g);
}
