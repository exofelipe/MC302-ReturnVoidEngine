package br.com.returnvoid.pong.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;

import br.com.returnvoid.returnengine.model.Entity;

public class Ball extends Entity{
	public Ball() {
		this.setCoordinate(new Point(300, 300));
		this.setSpeed(new Point(5, -5));
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
		if(this.getBounds().intersects(entity.getBounds())) {
			return true;
		}				
		return false;
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
	    g.fillRect(this.getCoordinate().x, this.getCoordinate().y, 
	    		this.getDimension().width, this.getDimension().height);
	}


}
