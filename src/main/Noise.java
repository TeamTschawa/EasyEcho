package main;

import java.util.ArrayList;

import processing.core.PGraphics;
import processing.core.PVector;

public class Noise {

	public PVector position;
	public float birth;
	public float lifespan;
	public float transparency;
	public ArrayList<Ray> rays;
	Window window;

	public Noise(PVector pos, int volume, Window win) {
		window = win;
		this.position = pos;
		this.rays = new ArrayList<Ray>();
		// lifegedöns
		birth = window.millis();
		lifespan = volume * 50;
		// generate rays
		float angle = (2 * window.PI) / volume;
		PVector vel = new PVector(0, 2);
		vel.rotate(window.random(0, angle));
		for (int i = 0; i < volume; i++) {
			vel.rotate(angle);
			Ray r = new Ray(new PVector(0, 0), vel.get(), this);
			rays.add(r);
		}
	}

	public void draw(PGraphics g) {
		// calc transparency
		transparency = 255 - ((window.millis() - birth) / lifespan) * 255;
		// render rays
		g.pushMatrix();
		g.translate(position.x, position.y);
		g.fill(0, 0, 255, transparency);
		for (Ray r : rays) {
			r.draw(g);
		}
		g.popMatrix();
	}

}