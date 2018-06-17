package uk.johndorman.main;

import java.awt.Canvas;

import org.lwjgl.openal.AL;

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
	
	private boolean running = false;
	private Thread thread;
	
	public static void main(String args[]){
		Window.initWindow(TITLE);
		Window.addGame(game);
		Window.createtWindow();
		game.start();
	}
	
	
	private void actionTick() {
		
	}
	

	private void renderTick() {
		
	}
	
	@Override
	public void run() {
		init();
		
		int frames = 0;
		int ticks = 0;
		long t = System.currentTimeMillis();
		/**
		 * Main Game Loop
		 */
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
				updateFPSCount(ticks, frames);
				t+=1000;
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}


	private void updateFPSCount(int ticks, int frames) {
		Window.setTitle(TITLE + "    " + ticks + " Ticks,     FPS: " + frames);
	}

	private void init() {
		/**
		 * Initialize the Timer
		 * sets up the tick rate
		 */
		timer = new Timer(60.0);
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
}
