package me.xxfreakdevxx.de.program.components;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import me.xxfreakdevxx.de.program.Assistent;

public class GFrame {
	
	/* Variables */
	public JFrame frame = null;
	public GCanvas canvas = null;
	public String title = "";
	public int width = 0;
	public int height = 0;
	
	public GFrame(int width, int height, String title, GCanvas canvas, Assistent game) {
		this.width = width;
		this.height = height;
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.add(game);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		this.canvas = canvas;
	}
	
	public void render(Graphics g) {
		if(canvas != null) canvas.render(g);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
}
