package br.com.returnvoid.returnengine.model;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/*
 * Classe abstrata para criação de entidades
 * Possui basicamente as informações das posições, velocidade, dimensões e 
 * estado da entidade. 
 * 
 * Subclasses devem implementar métodos responsáveis por verificar colisão
 * e desenhar
 */
public abstract class Entity {
	private int state;
	public double x, y;
	public double vx, vy;
	private Dimension dimension;
	
	public abstract boolean checkColision(Entity entity);
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Point getCoordinate() {
		return new Point((int) Math.round(x), (int) Math.round(y));
	}
	public void setCoordinate(Point coordinate) {
		this.x = coordinate.x;
		this.y = coordinate.y;
	}

	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int)dimension.getWidth(), (int)dimension.getHeight());
	}	
	
	public abstract void paint(Graphics2D g);
}
