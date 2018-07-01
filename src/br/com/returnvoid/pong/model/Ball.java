package br.com.returnvoid.pong.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import br.com.returnvoid.returnengine.model.Entity;

public class Ball extends Entity{
	private Random random = new Random();
	public Ball() {
		this.setCoordinate(new Point(200, 200));
		this.randomSpeed();
		this.setDimension(new Dimension(10, 10));
	}
	public void randomSpeed() {
		this.vx = ((random.nextDouble() * 0.4) + 0.2 )*(random.nextBoolean()? -1: 1);
		this.vy = ((random.nextDouble() * 0.3) + 0.1 )*(random.nextBoolean()? -1: 1);
	}
	@Override
	public void updateLogic() {
	}

	@Override
	public void updateGraphic() {
	}

	@Override
	public boolean checkColision(Entity entity) {
		Rectangle r1 = this.getBounds();			
		Rectangle r2 = entity.getBounds();
			
		if(r1.intersects(r2)) {
			return true;
		}				
		return false;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
	    g.fillOval(this.getCoordinate().x, this.getCoordinate().y, 
	    		this.getDimension().width, this.getDimension().height);
	}


}
