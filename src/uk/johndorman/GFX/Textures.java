package uk.johndorman.GFX;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Textures {
	
	public static BufferedImage DEFAULT_TEXTURE; //TODO:
	
	private Map<String, BufferedImage> textureStore;
	
	public Textures() {
		textureStore = new HashMap<String, BufferedImage>();
	}
	
	public void addTexture(String key, BufferedImage tex) {
		textureStore.put(key, tex);
	}
	
	/**
	 * Returns a texture if cannot be found loads default texture
	 * @param key
	 * @return
	 */
	public BufferedImage getTexture(String key) {
		return textureStore.getOrDefault(key, DEFAULT_TEXTURE);
	}

}
