package me.xxfreakdevxx.de.program.components;

import java.util.Timer;
import java.util.TimerTask;


public class GAction {
	private Timer timer = new Timer();
	private TimerTask task = null;
	private TimerTask task_backup = null;
	private int delay = 0;
	private int period = 0;
	public boolean pause = false;
	public GAction(TimerTask task, int delay, int period) {
		this.task = task;
		this.task_backup = task;
		this.delay = delay;
		this.period = period;
	}
	
	public void run() {
		timer = new Timer();
		timer.schedule(task, delay, period);
	}
	
	public void cancel() {
		timer.cancel();
		task.cancel();
		this.task = task_backup;
	}
	
	public void pause() {
		this.pause = true;
	}
	
}
