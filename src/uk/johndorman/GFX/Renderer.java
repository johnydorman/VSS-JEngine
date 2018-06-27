package uk.johndorman.GFX;

import java.awt.Color;
import java.awt.Graphics;

import uk.johndorman.main.Game;

public class Renderer {
	
	public void render(Graphics g){
		switch(Game.state){
		case GAME:
			Game.getInstance().getController().render(g);
			break;
		case MENU:
			g.setColor(Color.BLACK);
			g.drawString("MENU", 150, 150);
			break;
		case LOAD:
			g.setColor(Color.BLACK);
			g.drawString("LOADING...", 150, 150);
			break;
		default:
			g.setColor(Color.RED);
			g.drawString("UNKOWN GAMESTATE", 150, 150);
			break;
		}
	}
}