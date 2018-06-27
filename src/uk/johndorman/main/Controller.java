package uk.johndorman.main;

import java.awt.Graphics;

import uk.johndorman.core.GameObject;
import uk.johndorman.enums.RenderType;
import uk.johndorman.world.Level;

public class Controller {
	
	Level currentLevel;

	/**
	 * Render Objects starting at the back
	 * @param g
	 */
	public void render(Graphics g) {
		//Render Background
		for(GameObject obj : currentLevel.getObjectsOfType(RenderType.BACKGROUND)) {
			obj.render(g);
		}
		
		//Render Effects
		for(GameObject obj : currentLevel.getObjectsOfType(RenderType.EFFECT)) {
			obj.render(g);
		}
		
		//Render default
		for(GameObject obj : currentLevel.getObjectsOfType(RenderType.DEFAULT)) {
			obj.render(g);
		}
		
		//Render Player
		for(GameObject obj : currentLevel.getObjectsOfType(RenderType.PLAYER)) {
			obj.render(g);
		}
		
		//Render Hud
		for(GameObject obj : currentLevel.getObjectsOfType(RenderType.HUD)) {
			obj.render(g);
		}
		
		//Render UI
		for(GameObject obj : currentLevel.getObjectsOfType(RenderType.UI)) {
			obj.render(g);
		}
	}

	public void tick() {
		for(GameObject obj : currentLevel.getObjects()){
			obj.tick();
		}
		
		Game.camera.tick();
	}
	
	public Level getcurrentLevel() {
		return currentLevel;
	}

}
