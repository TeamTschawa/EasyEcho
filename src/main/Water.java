package main;

import java.awt.Color;

import processing.core.PGraphics;
import processing.core.PVector;

public class Water extends Environment{

	public Water(PVector pos, int _width, int _height) {
		super(pos, _width, _height);
		color = new Color(0, 0, 255).getRGB();
	}
	
	@Override
	public void draw(PGraphics g) {
		g.stroke(color, 255);
		g.fill(0, 0, 0, 0);
		g.rect(this.position.x, this.position.y, widthBox, heightBox);
		
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

}
