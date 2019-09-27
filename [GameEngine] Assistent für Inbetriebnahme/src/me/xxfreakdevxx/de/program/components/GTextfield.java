package me.xxfreakdevxx.de.program.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class GTextfield extends Component {
	
	public String password_char = "*";
	public String display_text = "";
	public String pseudo_text = "";
	public boolean isPasswordField = false;
	
	public GTextfield(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.max_characters = 18;
	}

	@Override
	public void tick() {
		if(isPasswordField) {
			this.display_text = "";
			for(int i = 0; i < this.text.length(); i++) {
				this.display_text += password_char;
			}
		}else display_text = this.text;
	}

	@Override
	public void render(Graphics g) {
		g.setFont(this.text_font);
		g.setColor(getBackground());
		g.fillRect(x, y, width, height);
		if(isSelected) {
			g.setColor(selected_color);
			g.fillRect(x, y, width, height);
		}
		g.setColor(Color.GRAY);
		g.drawRect(x, y, width, height);
		g.drawRect(x+1, y+1, width-2, height-2);
		g.setColor(Color.BLACK);
		if(display_text.equals("")==false)g.drawString(display_text, x+4, y+(height/2)+(g.getFont().getSize()/2)-1);			
		else g.drawString(pseudo_text, x+4, y+(height/2)+(g.getFont().getSize()/2)-1);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(getBounds().intersects(new Rectangle(e.getX(), e.getY(), 1, 1))) runClickAction();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(new Rectangle(e.getX(), e.getY(),1,1).intersects(getBounds())) useHighlight();
		else useBackgroundBackup();
	}
	
	public void addText(String text) {
		if(this.text.length() < max_characters) this.text += text;
	}
	public void removeLastCharakter() {
		if(!text.equals("")) text = text.substring(0, (text.length()-1));
	}

}
