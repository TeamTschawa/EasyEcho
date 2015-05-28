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
		this.width = new PVector(_width, 0);
		this.height = new PVector(0, _height);
		this.center = new PVector(position.x + width.x / 2, position.y + height.y / 2);
		this.angle = _angle;
	}

	public void draw(PGraphics g) {
		g.stroke(127,255,255,255);
		g.fill(0, 0, 0, 0);
		g.pushMatrix();
		g.translate(center.x, center.y);
		g.rotate(angle);
		g.rect(-width.x / 2, -height.y / 2, width.x, height.y);	
		g.popMatrix();
	}

	public void collideWithRay(Ray r) {
		PVector realRayPos = PVector.add(r.position, r.noise.position);
		if (this.contains(realRayPos)) {
			PVector rotatedHeight = height.get();
			PVector rotatedWidth = width.get();
			rotatedHeight.rotate(angle);
			rotatedWidth.rotate(angle);
			PVector centerA = PVector.sub(position, center);
			PVector centerB = PVector.sub(PVector.add(position, width), center);
			PVector centerC = PVector.sub(PVector.add(PVector.add(position, height), width), center);
			PVector centerD = PVector.sub(PVector.add(position, height), center);
			centerA.rotate(angle);
			centerB.rotate(angle);
			centerC.rotate(angle);
			centerD.rotate(angle);
			
			if (triangleContainsPoint(realRayPos, center, centerA, centerB)) 
			{
				rotatedWidth.rotate((float) Math.PI);
				reflectRay(r, rotatedWidth);
			} 
			else if (triangleContainsPoint(realRayPos, center, centerB, centerC))
			{
				rotatedHeight.rotate((float) Math.PI);
				reflectRay(r, rotatedHeight);
			} 
			else if (triangleContainsPoint(realRayPos, center, centerC, centerD))
			{
				reflectRay(r, rotatedWidth);
			}
			else if (triangleContainsPoint(realRayPos, center, centerD, centerA))
			{
				reflectRay(r, rotatedHeight);
			}
		}
	}
	
	public void reflectRay(Ray r, PVector a){
		PVector lot = a.get();
		lot.rotate((float)(Math.PI / 2f));
		r.velocity.rotate(PVector.angleBetween(r.velocity, a) * 2f);
	}

	public boolean triangleContainsPoint(PVector p, PVector base, PVector w, PVector h) {
		float t = -(base.x * w.y - base.y * w.x + w.x * p.y - w.y * p.x) / (h.x * w.y - h.y * w.x);
		float s = -(h.x * t + base.x - p.x) / w.x;
		float sum = t + s;
		return (sum >= 0 && sum <= 1 && t >= 0 && s >= 0);
	}
	
	public boolean parallelogrammContainsPoint(PVector p, PVector base, PVector w, PVector h){
		float t = -(base.x * w.y - base.y * w.x + w.x * p.y - w.y * p.x) / (h.x * w.y - h.y * w.x);
		float s = -(h.x * t + base.x - p.x) / w.x;
		return (t >= 0 && t <= 1 && s >= 0 && s <= 1);
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
