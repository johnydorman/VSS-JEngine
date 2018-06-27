package uk.johndorman.core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import uk.johndorman.lib.Vector2;
import uk.johndorman.main.Game;

public abstract class CollidableObject extends GameObject {
	
	List<CollidableObject> collisions;
	
	Rectangle collider;
	int width, height;

	public CollidableObject(int id) {
		super(id);
		width = (int) (position.x * scale.x);
		height = (int) (position.y * scale.y);
		
		collider.setBounds(position.getRoundedX(), position.getRoundedY(), width, height);
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
		MovementTick();
	}
	
	private void MovementTick() {
		Vector2 nextPosition = position.add(velocity);
		Rectangle nextCollider = new Rectangle(nextPosition.getRoundedX(), nextPosition.getRoundedY(), width, height);
		calculuateCollisions(nextCollider);
		
		if(collisions.isEmpty()) {
			/*
			 * Update ColliderBounds
			 */
			collider = nextCollider;
			
			/*
			 * Update Position
			 */
			position = nextPosition;
		} else {
			Vector2 movementClamp = new Vector2(nextPosition.x, nextPosition.y);
			for(CollidableObject obj : collisions) {
				movementClamp = updateVectors(obj, movementClamp);
				onCollisionEnter(obj);
			}
			
			//once collisions are solved
			position = movementClamp;
			collider.setLocation(position.getRoundedX(), position.getRoundedY());
		}
	}
	
	private Vector2 updateVectors(CollidableObject obj, Vector2 movementClamp) {
		if(obj.collider.getMaxX() < collider.getMaxX()) {
			movementClamp.x = (float) obj.collider.getMaxX();
		}
		return movementClamp;
	}

	private void calculuateCollisions(Rectangle nextPosition) {
		collisions.clear();
		/*
		for(CollidableObject obj : Game.getInstance().getController().getcurrentLevel().getObjectsOfType(this.getClass().getName())){
			if(nextPosition.intersects(obj.collider)){
				collisions.add(obj);
			}
		}
		*/
	}

	public abstract void Update();
	
	/**
	 * Render Update
	 * @param g
	 */
	public abstract void render(Graphics g);
	
	/**
	 * 
	 */
	public abstract void onCollisionEnter(CollidableObject other);

}
