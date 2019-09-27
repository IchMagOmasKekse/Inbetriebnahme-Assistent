package me.xxfreakdevxx.de.program.components;

import java.awt.Color;
import java.awt.Font;
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
	public String text = "";
	public GAction click_action = null;
	public GAction hover_action = null;
	public int max_characters = -1;
	public Font text_font = new Font("Tahoma", Font.PLAIN, 16);
	public boolean isSelected = false;
	public Color selected_color = new Color(0f,0.2f,1f,0.3f);
	
	/* Colors */
	private Color background = Color.LIGHT_GRAY;
	private Color background_backup = Color.LIGHT_GRAY;
	public Color highlight = new Color(220,220,220);
	
	/* Properties */
	public boolean isEnabled = true;
	public boolean isVisible = true;
	public boolean drawBox = false;
	
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
	
	public void setBackground(Color color) {
		this.background = color;
		this.background_backup = color;
	}
	
	public Color getBackground() {
		return background;
	}
	public Color getBackgroundBackup() {
		return background_backup;
	}
	public void useBackgroundBackup() {
		this.background = background_backup;
	}
	public void useHighlight() {
		this.background = highlight;
	}
	public void runClickAction() {
		if(click_action != null) {
			click_action.cancel();
			click_action.run();
		}
	}
	public void runHoverAction() {
		if(hover_action != null) hover_action.run();
	}
	
}
