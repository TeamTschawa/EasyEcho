package main;

import java.awt.Color;

import processing.core.PGraphics;
import processing.core.PVector;

public class Trap extends Environment {

	public Trap(PVector pos, int _width, int _height) {
		super(pos, _width, _height);
		color = new Color(255, 0, 0).getRGB();
	}

	public void draw(PGraphics g) {
		g.stroke(0, 255, 255, 255);
		g.fill(0, 0, 0, 0);
		g.rect(this.position.x, this.position.y, width.x, height.y);
	}

	public void action(){
		
	}
	
}
