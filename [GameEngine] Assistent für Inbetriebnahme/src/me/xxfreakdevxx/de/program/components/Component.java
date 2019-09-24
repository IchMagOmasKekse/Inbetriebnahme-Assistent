package me.xxfreakdevxx.de.program.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import me.xxfreakdevxx.de.program.Assistent;

public abstract class Component {
	
	protected int id = 0;
	protected int x = 0;
	protected int y = 0;
	protected int width = 0;
	protected int height = 0;
	protected String text = "";
	
	/* Colors */
	public Color background = Color.LIGHT_GRAY;
	
	/* Properties */
	public boolean isEnabled = true;
	public boolean isVisible = true;
	public boolean drawBox = true;
	
	public Component(int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.id = Assistent.randomInteger(0, 10000);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void mouseMoved(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void enable() {
		this.isEnabled = true;
		this.isVisible = true;
	}
	
	public void disable() {
		this.isEnabled = false;
		this.isVisible = false;
	}
	
	public void show() {
		this.isVisible = true;
	}
	
	public void hide() {
		this.isVisible = false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
	
}
