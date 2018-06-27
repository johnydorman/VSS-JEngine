package uk.johndorman.world;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

import uk.johndorman.core.GameObject;
import uk.johndorman.enums.RenderType;

public class Level {
	
	int id;
	
	/*
	 * Default List
	 */
	List<GameObject> gameObjects;
	
	/*
	 * Render Lookup
	 */
	EnumMap<RenderType, List<GameObject>> objectsByRenderType;
	
	/*
	 * Type Lookup
	 */
	HashMap<String, List<GameObject>> objectsByClassname;
	
	public Level(int id) {
		this.id = id;
		
		this.gameObjects = new ArrayList<GameObject>();
		this.objectsByRenderType = new EnumMap<RenderType, List<GameObject>>(RenderType.class);
		this.objectsByClassname = new HashMap<String, List<GameObject>>();	
	}

	public List<GameObject> getObjectsOfType(RenderType type) {
		return objectsByRenderType.get(type);
	}
	
	//TODO: use an enumeration?
	public List<GameObject> getObjectsOfType(String classname) {
		return objectsByClassname.get(classname);
	}

	public List<GameObject> getObjects() {
		return gameObjects;
	}

}
