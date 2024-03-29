package me.xxfreakdevxx.de.program;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import me.xxfreakdevxx.de.program.components.GTextfield;


public class KeyInput extends KeyAdapter {
	
	public List<String> cooldown_bypass = new LinkedList<String>();
	public ConcurrentLinkedQueue<Integer> pressed_keys = new ConcurrentLinkedQueue<Integer>();
	public KeyEvent event = null;
	
	public KeyInput() {
		addBypass("W", "A", "S", "D");
//		addBypass("Oben", "Links", "Unten", "Rechts");
		addBypass("Leertaste", "Umschalt", "Steuerung", "Escape");
	}
	public void addBypass(String... letter) {
		for(String s : letter) if(cooldown_bypass.contains(s) == false) cooldown_bypass.add(s);
	}
	
	private int ticked = 0;
	public void tick() {
		ticked++;
		if(ticked == 1) ticked = 0;
		else return;
		for(int key : pressed_keys) {
			switch(key) {
			case KeyEvent.VK_P:
				Assistent.getCamera().biggerOffset();
				break;
			case KeyEvent.VK_O:
				Assistent.getCamera().smallerOffset();
				break;
			case KeyEvent.VK_RIGHT:
				Assistent.getCamera().addX(-1);
				break;
			case KeyEvent.VK_LEFT:
				Assistent.getCamera().addX(1);
				break;
			case KeyEvent.VK_UP:
				Assistent.getCamera().addY(1);
				break;
			case KeyEvent.VK_DOWN:
				Assistent.getCamera().addY(-1);
				break;
			case KeyEvent.VK_PLUS:
				Assistent.fps_maximal++;
				break;
			case KeyEvent.VK_MINUS:
				Assistent.fps_maximal--;
				break;
			case KeyEvent.VK_L:
				release(key);
				break;
			}
			checkForTextfield(key);
		}
	}
	int height = 20;
	int space = 2;
	int amount = 1;
	public void render(Graphics g) {
		if(pressed_keys.isEmpty()) {
			g.drawString("Kein Cooldown registriert", 10, 10+space+height);
		}else {
			for(int key : pressed_keys) {
				g.drawString("Key: "+KeyEvent.getKeyText(key), 10, 10+((amount+1)*space)+(amount*height));
				amount++;
			}
		}
		amount=1;
	}
	public void press(int key) {
		if(pressed_keys.contains(key) == false) {
			pressed_keys.add(key);
		}
	}
	public void release(int key) {
		pressed_keys.remove(key);
	}
	
	//TODO: Get Interaction
	@Override
	public void keyPressed(KeyEvent e) {
		this.event = e;
		int key = e.getKeyCode();
		press(key);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		this.event = e;
		int key = e.getKeyCode();
		release(key);
	}
	
	private boolean shift = false;
	public void checkForTextfield(int key) {
		if(Assistent.frame.canvas.getSelectedComponent() instanceof GTextfield) {
			String s = KeyEvent.getKeyText(key);
			if(key == KeyEvent.VK_BACK_SPACE) {
				((GTextfield)Assistent.frame.canvas.getSelectedComponent()).removeLastCharakter();
				pressed_keys.remove(key);				
			}else if(key == KeyEvent.VK_SPACE) {
				((GTextfield)Assistent.frame.canvas.getSelectedComponent()).addText(" ");
				pressed_keys.remove(key);				
			}else {				
				if(s.length() == 1) {
					shift = event.isShiftDown();
					if(shift) s = s.toUpperCase();
					else s = s.toLowerCase();
					((GTextfield)Assistent.frame.canvas.getSelectedComponent()).addText(s);
					pressed_keys.remove(key);				
				}
			}
		}
	}
	
	
}
