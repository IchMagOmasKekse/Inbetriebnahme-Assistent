package me.xxfreakdevxx.de.program.components.menus;

import java.awt.Color;
import java.util.TimerTask;

import me.xxfreakdevxx.de.program.Assistent;
import me.xxfreakdevxx.de.program.components.GAction;
import me.xxfreakdevxx.de.program.components.GButton;
import me.xxfreakdevxx.de.program.components.GCanvas;
import me.xxfreakdevxx.de.program.components.GLabel;
import me.xxfreakdevxx.de.program.components.GTextfield;

public class LoginMenu extends GCanvas {
	
	private int page = 0;
	
	public LoginMenu() {
		setBackground(new Color(0.1f,0.1f,0.1f,0.2f));
	}
	private int page_backup = -9;
	@Override
	public void tick() {
		updateDimensions();
		if(page != page_backup) {
			switch(page) {
			case 0:
				components.clear();
				int btn_width = 120;
				int btn_height = 40;
				int x = (Assistent.windowWidth/2);
				int y = (Assistent.windowHeight/2)-100;
				
				
				GButton azubi_btn = new GButton(x-(btn_width/2), y-(btn_height /2), btn_width, btn_height, "A");
				GButton ausbilder_btn = new GButton(x-(btn_width/2), y-(btn_height /2)+btn_height+4, btn_width, btn_height, "B");
				
				azubi_btn.setBackground(new Color(0.1f,0.1f,0.1f,0.1f));
				azubi_btn.highlight = new Color(0.1f,0.1f,0.1f,0.6f);
				azubi_btn.click_action = new GAction(new TimerTask() {
					
					@Override
					public void run() {
//						Assistent.frame.canvas = new OperationMenu();
						nextPage();
						this.cancel();
					}
				}, 0, 1);
				
				ausbilder_btn.setBackground(new Color(0.1f,0.1f,0.1f,0.1f));
				ausbilder_btn.highlight = new Color(0.1f,0.1f,0.1f,0.6f);
				
				addComponent(new GLabel(x-60, y-(btn_height /2)-4, 20, "Was sind Sie?"));
				addComponent(azubi_btn);
				addComponent(ausbilder_btn);
				break;
			case 1:
				components.clear();
				btn_width = 240;
				btn_height = 40;
				x = (Assistent.windowWidth/2);
				y = (Assistent.windowHeight/2)-100;
				
				
				GTextfield vorname = new GTextfield(x-(btn_width/2), y-(btn_height /2), btn_width, btn_height);
				GTextfield nachname = new GTextfield(x-(btn_width/2), y-(btn_height /2)+btn_height+4, btn_width, btn_height);
				
				vorname.setBackground(new Color(0.1f,0.1f,0.1f,0.1f));
				vorname.highlight = new Color(0.1f,0.1f,0.1f,0.6f);
				vorname.max_characters = 36;
				vorname.pseudo_text = "Dienstausweis Nummer";
				vorname.click_action = new GAction(new TimerTask() {
					
					@Override
					public void run() {
						Assistent.frame.canvas.setSelectedComponent(vorname);
						vorname.click_action.cancel();
					}
				}, 0, 1);
				
				nachname.setBackground(new Color(0.1f,0.1f,0.1f,0.1f));
				nachname.highlight = new Color(0.1f,0.1f,0.1f,0.6f);
				nachname.isPasswordField = true;
				nachname.pseudo_text = "Optional";
				nachname.click_action = new GAction(new TimerTask() {
					
					@Override
					public void run() {
						Assistent.frame.canvas.setSelectedComponent(nachname);
						nachname.click_action.cancel();
					}
				}, 0, 1);
				
				addComponent(new GLabel(x-110, y-(btn_height /2)-4, 20, "Geben Sie Ihre Daten an"));
				addComponent(vorname);
				addComponent(nachname);
				break;
			}
			this.page_backup = page;
		}
	}
	
	public void nextPage() {
		page++;
	}
	
}
