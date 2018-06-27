package uk.johndorman.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import org.lwjgl.openal.AL;

import uk.johndorman.GFX.Renderer;
import uk.johndorman.enums.GameState;
import uk.johndorman.lib.Vector2;

/**
 * 
 * @author https://github.com/johnydorman/
 *
 */
public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -7629913836486954274L;
	
	public static final int WIDTH  = 1600; //32 640 // 1280
	public static final int HEIGHT = 900;
	public static final String TITLE = "VSS JEngine - Johnydorman";
	private static Game game = new Game();
	private static Timer timer;
	
	public static GameState state;
	public static Camera camera;
	
	private Controller controller;
	private Renderer renderer;

	
	private boolean running = false;
	private Thread thread;
	
	public static void main(String args[]){
		Window.initWindow(TITLE);
		Window.addGame(game);
		Window.createtWindow();
		game.start();
	}
	
	public static Game getInstance(){
		return game;
	}
	
	private void actionTick() {
		controller.tick();
		camera.tick();
	}
	

	private void renderTick() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(new Color(6, 0, 40));
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		g2d.translate(camera.position.x, camera.position.y);
		renderer.render(g);
		g2d.translate(-camera.position.x, -camera.position.y);
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		initialize();
		
		int frames = 0;
		int ticks = 0;
		long t = System.currentTimeMillis();
		
		/**
		 * Main Game Loop
		 */
		state = GameState.MENU;
		timer.lastFrameTime = System.nanoTime();
		while(running){
			timer.updateDelta(System.nanoTime());

			if(timer.deltaTick >= 1){
				actionTick();
				ticks++;
				timer.resetDelta();
			}
			
			renderTick();
			frames++;
			
			if(System.currentTimeMillis() - t >= 1000){
				t+=1000;
				updateFPSCount(ticks, frames);
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}


	private void updateFPSCount(int ticks, int frames) {
		Window.setTitle(TITLE + "    " + ticks + " Ticks,     FPS: " + frames);
	}

	private void initialize() {
		/**
		 * 
		 */
		renderer = new Renderer();
		
		/**
		 * Sets the State Whilst loading
		 */
		state = GameState.LOAD;
		
		/**
		 * Initialize the Timer
		 * sets up the tick rate
		 */
		timer = new Timer(60.0);
		
		/**
		 * 
		 */
		camera = new Camera(new Vector2(0,0));
		
		//TODO: Read Textures
		//TODO: Read Level Data
		//TODO: Read UI Data
	}

	private synchronized void start(){
		if(running)
			return;
		else
			running = true;
		thread = new Thread(this);
		thread.start();
	}
	private synchronized void stop(){
		if(!running)
			return;
		else
			running = false;	
		
		cleanUp();
		System.exit(1);
	}
	private void cleanUp(){
		AL.destroy();
	}
	public static void exit(){
		game.stop();
	}

	public Controller getController() {
		return controller;
	}
}
