package main;

import processing.core.PGraphics;
import processing.core.PVector;

public class Box {

	public PVector position;
	public int widthBox;
	public int heightBox;
	public PVector center;

	public Box(PVector pos, int _width, int _height) {
		this.position = pos;
		this.widthBox = _width;
		this.heightBox = _height;
		this.center = new PVector(position.x + widthBox / 2, position.y + heightBox / 2);
	}

	public void draw(PGraphics g) {
		g.stroke(127,255,255,255);
		g.fill(0, 0, 0, 0);
		g.rect(this.position.x, this.position.y, widthBox, heightBox);
	}

	public void collideWithRay(Ray r) {
		PVector realRayPos = PVector.add(r.position, r.noise.position);
		if (this.contains(realRayPos)) {
			PVector A = position;
			PVector B = new PVector(position.x + widthBox, position.y);
			PVector C = new PVector(position.x + widthBox, position.y + heightBox);
			PVector D = new PVector(position.x, position.y + heightBox);

			PVector centerA = PVector.sub(A, center);
			PVector centerB = PVector.sub(B, center);
			PVector centerC = PVector.sub(C, center);
			PVector centerD = PVector.sub(D, center);

			// println(centerA, centerB, centerC, centerD);

			if (triangleContainsPoint(realRayPos, this.center, centerA, centerB)) {
				if (r.velocity.y >= 0) {
					r.velocity.y = r.velocity.y * -1.0f;
				}
			} else if (triangleContainsPoint(realRayPos, this.center, centerB, centerC)) {
				if (r.velocity.x <= 0) {
					r.velocity.x = r.velocity.x * -1.0f;
				}
			} else if (triangleContainsPoint(realRayPos, this.center, centerC, centerD)) {
				if (r.velocity.y <= 0) {
					r.velocity.y = r.velocity.y * -1.0f;
				}
			} else if (triangleContainsPoint(realRayPos, this.center, centerD, centerA)) {
				if (r.velocity.x >= 0) {
					r.velocity.x = r.velocity.x * -1.0f;
				}
			}
		}
	}

	public boolean triangleContainsPoint(PVector p, PVector base, PVector j, PVector g) {
		float r;
		float s;

		s = (-j.x * base.y + j.x * p.y + j.y * base.x - j.y * p.x) / (j.x * g.y - j.y * g.x);
		r = (-s * g.x - base.x + p.x) / j.x;
		float sum = s + r;
		return (0 <= sum && sum <= 1 && s >= 0 && r >= 0);
	}

	public boolean contains(PVector vec) {
		return (vec.x >= position.x && vec.y >= position.y && vec.x < position.x + widthBox && vec.y < position.y + heightBox);
	}

}
