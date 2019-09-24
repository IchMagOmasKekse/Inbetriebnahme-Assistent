package me.xxfreakdevxx.de.program.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import me.xxfreakdevxx.de.program.Assistent;

public class GButton extends Component {

	public GButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height);
		this.text = text;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	int text_width = 0;
	@Override
	public void render(Graphics g) {
		text_width = Assistent.calculateStringWidth(g.getFont(), text);
		g.setColor(background);
		g.fillRect(x, y, width, height);
		g.setColor(Color.GRAY);
		g.drawRect(x, y, width, height);
		g.drawRect(x+1, y+1, width-2, height-2);
		
		if(text_width < width) {
			g.setColor(Color.BLACK);
			g.drawString(text, x+(width/2)-(text_width/2), y+(height/2)+(g.getFont().getSize()/2)-1);			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(new Rectangle(e.getX(), e.getY(),1,1).intersects(getBounds())) background = new Color(220,220,220);
		else background = Color.LIGHT_GRAY;
	}

}
