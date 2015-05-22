package main;

import processing.core.PGraphics;
import processing.core.PVector;

public abstract class Environment extends Box {

	int color;
	
	public Environment(PVector pos, int _width, int _height) {
		super(pos, _width, _height);
	}

	public abstract void draw(PGraphics g);

	public abstract void action();
	
}
