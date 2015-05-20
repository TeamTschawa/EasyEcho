package main;

import java.util.ArrayList;

import processing.core.*;

@SuppressWarnings("serial")
public class Window extends PApplet {

	private PGraphics graphics;
	
	private ArrayList<Noise> noises;
	public ArrayList<Box> walls;
	private boolean debug;

	public void setup() {
		size(500, 500);

		graphics = this.createGraphics(this.width, this.height);
		noises = new ArrayList<Noise>();
		walls = new ArrayList<Box>();
		debug = true;
		Box b = new Box(new PVector(0, 0), 50, this.height);
		walls.add(b);
	}

	public void draw() {
		graphics.beginDraw();
		// settings
		graphics.colorMode(this.HSB);
		graphics.ellipseMode(this.CENTER);
		graphics.noStroke();
		graphics.background(0, 0, 0, 6);
		// render Walls
		if (debug) {
			for (Box b : walls) {
				b.draw(graphics);
			}
		}
		// render noises
		Noise[] iterator = new Noise[noises.size()];
		for (Noise n : noises.toArray(iterator)) {
			n.draw(graphics);
			if (n.transparency <= 0) {

				noises.remove(n);
			}
		}

		graphics.endDraw();
		this.image(graphics, 0, 0);
	}
	
	public void keyReleased(){
		if(key == 'o'){
			debug = !debug;
		}
	}

	public void mousePressed() {
		Noise n = new Noise(new PVector(mouseX, mouseY), (int) random(15, 60), this);
		noises.add(n);
	}

}
