package main;

import processing.core.PGraphics;
import processing.core.PVector;

public class Ray {

	public PVector position;
	public PVector velocity;
	public Noise noise;

	public Ray(PVector pos, PVector vel, Noise noise) {
		this.position = pos;
		this.velocity = vel;
		this.noise = noise;
	}

	public void draw(PGraphics g) {
		g.noStroke();
		for (Box b : noise.window.walls) {
			b.collideWithRay(this);
		}
		for (Environment e : noise.window.environments) {
			if (e.contains(PVector.add(noise.position, this.position))) {
				g.fill(e.color, noise.transparency);
				break;
			} else {
				g.fill(0, 0, 255, noise.transparency);
			}
		}
		position.add(velocity);
		g.pushMatrix();
		g.translate(position.x, position.y);
		g.ellipse(0, 0, 5, 5);
		g.popMatrix();
	}

}
