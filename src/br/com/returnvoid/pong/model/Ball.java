package br.com.returnvoid.pong.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import br.com.returnvoid.returnengine.model.Entity;

public class Ball extends Entity{
	public Ball() {
		this.setCoordinate(new Point(200, 200));
		this.vx = 0.25;
		this.vy = -0.25;
		this.setDimension(new Dimension(10, 10));
	}
	//TODO tudo
	@Override
	public void updateLogic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGraphic() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
	    g.fillOval(this.getCoordinate().x, this.getCoordinate().y, 
	    		this.getDimension().width, this.getDimension().height);
	}


}
