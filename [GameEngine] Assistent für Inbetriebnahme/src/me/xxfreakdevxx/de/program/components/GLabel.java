package me.xxfreakdevxx.de.program.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import me.xxfreakdevxx.de.program.Assistent;

public class GLabel extends Component {
	
	public Font font = null;
	public boolean rainbow_color = false;
	
	
	public GLabel(int x, int y, int font_size, String text) {
		super(x, y, Assistent.calculateStringWidth(new Font("Arial", 0, font_size), text), font_size);
		this.font = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, font_size);
		this.text = text;
		this.background = Color.BLACK;
	}
	
	int r = 0;
	int g = 0;
	int b = 0;
	
	@Override
	public void tick() {
		if(rainbow_color) {
			if(r < 255) {
				r++;
			}else if(g < 255) {
				g++;
			}else if(b < 255) {
				b++;
			}else {
				r = 0;
				g = 0;
				b = 0;
			}
			this.background = new Color(r,g,b);
		}
	}
	
	private Font old_font = null;
	@Override
	public void render(Graphics g) {
		if(drawBox) {
			g.setColor(this.background);
			g.fillRect(x-1, y-height+3, width+2, height+1);
		}
		g.setColor(Color.black);
		old_font = g.getFont();
		g.setFont(font);
		g.drawString(text, x, y);
		g.setFont(old_font);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
