package main;

import processing.core.PGraphics;
import processing.core.PVector;

public class Box {

	public PVector position;
	public PVector width;
	public PVector height;
	public PVector center;
	public float angle;

	public Box(PVector pos, int _width, int _height) {
		this.position = pos;
		this.width = new PVector(_width,0);
		this.height = new PVector(0,_height);
		this.center = new PVector(position.x + width.x / 2, position.y + height.y / 2);
		this.angle = 0;
	}
	
	public Box(PVector pos, int _width, int _height, float _angle){
		this.position = pos;
		this.width = new PVector(_width,0);
		this.height = new PVector(0,_height);
		this.center = new PVector(position.x + width.x / 2, position.y + height.y / 2);
		this.angle = _angle;
	}

	public void draw(PGraphics g) {
		g.stroke(127,255,255,255);
		g.fill(0, 0, 0, 0);
		g.rect(this.position.x, this.position.y, width.x, height.y);
	}

	public void collideWithRay(Ray r) {
		PVector realRayPos = PVector.add(r.position, r.noise.position);
		if (this.contains(realRayPos)) {
			
			PVector centerA = PVector.sub(position, center);
			centerA.rotate(angle);
			PVector rotatedWidth = width.get();
			rotatedWidth.rotate(angle);
			PVector rotatedHeight = height.get();
			rotatedHeight.rotate(angle);
			
			PVector A = PVector.add(centerA, center);
			PVector B = PVector.add(A, rotatedWidth);
			PVector C = PVector.add(A, rotatedWidth);
			C.add(rotatedHeight);
			PVector D = PVector.add(A, rotatedHeight);

			
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
	
	public void reflectRay(Ray r, PVector a, PVector b){
		PVector lot = new PVector(100, 0);
		lot.rotate(PVector.angleBetween(a, b) / 2 + a.heading());
		
	}

	public boolean triangleContainsPoint(PVector p, PVector base, PVector j, PVector g) {
		float r;
		float s;

		s = (-j.x * base.y + j.x * p.y + j.y * base.x - j.y * p.x) / (j.x * g.y - j.y * g.x);
		r = (-s * g.x - base.x + p.x) / j.x;
		float sum = s + r;
		return (0 <= sum && sum <= 1 && s >= 0 && r >= 0);
	}
	
	public boolean parallelogrammContainsPoint(PVector p, PVector base, PVector j, PVector g){
		float r;
		float s;

		s = (-j.x * base.y + j.x * p.y + j.y * base.x - j.y * p.x) / (j.x * g.y - j.y * g.x);
		r = (-s * g.x - base.x + p.x) / j.x;
		float sum = s + r;
		return (0 <= sum && sum <= 2 && s >= 0 && r >= 0);
	}
	
	

	public boolean contains(PVector vec) {
		PVector centerA = PVector.sub(position, center);
		centerA.rotate(angle);
		PVector rotatedWidth = width.get();
		rotatedWidth.rotate(angle);
		PVector rotatedHeight = height.get();
		rotatedHeight.rotate(angle);
		return parallelogrammContainsPoint(vec, PVector.add(centerA, center), rotatedWidth, rotatedHeight);
	}

}
