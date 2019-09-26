package me.xxfreakdevxx.de.program.components;

import java.util.Timer;
import java.util.TimerTask;


public class GAction {
	private Timer timer = new Timer();
	private TimerTask task = null;
	private int delay = 0;
	private int period = 0;
	public GAction(TimerTask task, int delay, int period) {
		this.task = task;
		this.delay = delay;
		this.period = period;
	}
	
	public void run() {
		timer.schedule(task, (long)delay, period);
	}
	
	public void cancel() {
		
	}
	
}
