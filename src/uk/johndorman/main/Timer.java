package uk.johndorman.main;

public class Timer {
	
	/**
	 * Time Since the last Tick update
	 */
	public double deltaTime;
	public double lastFrameTime;
	
	/**
	 * Used for tracking tick updates
	 * tick() on deltaTick >= 1
	 */
	public double deltaTick;

	
	private double ticksPerSecond;
	public Timer(double tickRate) {
		ticksPerSecond = tickRate;
		deltaTick =0;
		deltaTime =0;
	}
	
	public void updateDelta(long currentTime) {
		double delta = (currentTime - lastFrameTime) / 1000000000;
		deltaTime += delta;
		deltaTick += delta * ticksPerSecond;
		lastFrameTime = currentTime;
	}

	public void resetDelta() {
		deltaTime = 0;
		deltaTick--;
	}

}
