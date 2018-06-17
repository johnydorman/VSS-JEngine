package uk.johndorman.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * 
 * @author https://github.com/johnydorman/
 *
 */
public class Window {

	private static JFrame frame;
	
	public static void initWindow(String title){
		frame = new JFrame(title);
	}
	
	public static void addGame(Game game){
		frame.add(game);
	}
	
	public static void createtWindow(){
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		
		frame.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						Game.exit();
					}
				}
		);
		
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
	
	public static void setTitle(String title){
		frame.setTitle(title);
	}
}
