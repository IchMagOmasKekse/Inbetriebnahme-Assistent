package me.xxfreakdevxx.de.program;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	public int x_con = 0;
	public int y_con = 0;
	public int x_uncon = 0;
	public int y_uncon = 0;
	
	private static MouseInput instance = null;
	public static MouseInput getInstance() {
		return instance;
	}
	
	public MouseInput() {
		instance = this;
	}
	
	public void mousePressed(MouseEvent e) {
		Camera camera = Assistent.getCamera();
		x_con = e.getX();
		y_con = e.getY();
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());		
		x_con = mx;
		y_con = my;
		Assistent.getInstance().frame.canvas.mousePressed(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Assistent.getInstance().frame.canvas.mouseMoved(e);
	}
}
