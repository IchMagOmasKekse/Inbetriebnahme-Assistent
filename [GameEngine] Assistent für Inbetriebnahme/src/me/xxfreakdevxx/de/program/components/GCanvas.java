package me.xxfreakdevxx.de.program.components;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import me.xxfreakdevxx.de.program.Assistent;

public class GCanvas extends Component {
	
	public HashMap<Integer, Component> components = new HashMap<Integer, Component>();
	
	public GCanvas() {
		super(0, 0, 0, 0);
		addComponent(new GLabel(20, 50, 18, "Prüfung auswählen"));
		addComponent(new GButton(20, 60, 150, 40, "Erst Inbetriebnahme"));
		addComponent(new GButton(20, 110, 150, 40, "Inbetriebname"));
	}
	
	public boolean addComponent(Component com) {
		if(components.containsKey(com.id)) {
			return false;
		}else components.put(com.id, com);
		return true;
	}
	public boolean removeComponent(Component com) {
		if(components.containsKey(com.id) == false) {
			return false;
		}else components.remove(com.id, com);
		return true;
	}
	
	@Override
	public void tick() {
		this.width = Assistent.windowWidth;
		this.height = Assistent.windowHeight;
		for(Component com : components.values()) {
			if(com.isEnabled == true) com.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		for(Component com : components.values()) {
			if(com.isVisible == true) com.render(g);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(Component com : components.values()) {
			if(com.isEnabled) com.mousePressed(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(Component com : components.values()) {
			if(com.isEnabled) com.mouseMoved(e);
		}
	}

}
