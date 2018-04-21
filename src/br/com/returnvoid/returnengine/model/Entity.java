package br.com.returnvoid.returnengine.model;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import br.com.returnvoid.returnengine.view.Sprite;

public abstract class Entity {
	private int state;
	private Point coordinate;
	private Point speed;
	private Dimension dimension;
	private Sprite sprite;
	
	public abstract void updateLogic();
	public abstract void updateGraphic();
	public abstract boolean checkColision(Entity entity);
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Point getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	public Point getSpeed() {
		return speed;
	}
	public void setSpeed(Point speed) {
		this.speed = speed;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
	public abstract void paint(Graphics2D g);
}
