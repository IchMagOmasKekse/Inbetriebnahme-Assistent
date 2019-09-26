package me.xxfreakdevxx.de.program.components.menus;

import me.xxfreakdevxx.de.program.components.GButton;
import me.xxfreakdevxx.de.program.components.GCanvas;
import me.xxfreakdevxx.de.program.components.GLabel;

public class OperationMenu extends GCanvas {
	
	public OperationMenu() {
		addComponent(new GLabel(20, 50, 18, "Operation auswälen"));
		GButton btn = new GButton(20, 60, 150, 40, "Erst Inbetriebnahme");
		addComponent(btn);
		addComponent(new GButton(20, 110, 150, 40, "Inbetriebname"));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		updateDimensions();
	}
	
}
