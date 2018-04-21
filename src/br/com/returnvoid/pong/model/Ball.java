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
		this.setSpeed(new Point(2, -2));
		this.setDimension(new Dimension(5, 5));
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
		// TODO Auto-generated method stub
		return new Random().nextInt(20)< 2;
		//return false;
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
	    g.fillRect(this.getCoordinate().x, this.getCoordinate().y, 
	    		this.getDimension().width, this.getDimension().height);
	}


}
