package main;

import java.awt.Color;

import processing.core.PGraphics;
import processing.core.PVector;

public class Trigger extends Environment{

	Box door;
	
	public Trigger(PVector pos, int _width, int _height) {
		super(pos, _width, _height);
		color = new Color(255, 255, 0).getRGB();
	}

	@Override
	public void draw(PGraphics g) {		
		g.stroke(color, 255);
		g.fill(0, 0, 0, 0);
		g.pushMatrix();
		g.translate(center.x, center.y);
		g.rotate((float)(Math.PI / 4));
		g.rect(-width.x / 2, -height.y / 2, width.x, height.y);	
		g.popMatrix();
	}

	@Override
	public void action() {
		
	}

}
