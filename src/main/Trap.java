package main;

import java.awt.Color;

import processing.core.PGraphics;
import processing.core.PVector;

public class Trap extends Box {

	int color = new Color(255, 0, 0).getRGB();

	public Trap(PVector pos, int _width, int _height) {
		super(pos, _width, _height);
	}

	public void draw(PGraphics g) {
		g.stroke(0, 255, 255, 255);
		g.fill(0, 0, 0, 0);
		g.rect(this.position.x, this.position.y, widthBox, heightBox);
	}

}
