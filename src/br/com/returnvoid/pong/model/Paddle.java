package br.com.returnvoid.pong.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import br.com.returnvoid.returnengine.controller.Joystick;
import br.com.returnvoid.returnengine.model.Entity;

public class Paddle extends Entity implements KeyListener{
	private Joystick joystick = new Joystick();
	public Paddle(int buttonUp, int buttonDown, int x, int y) {
		this.setCoordinate(new Point(x, y));
		this.setDimension(new Dimension(5, 100));
		this.setSpeed(new Point(0,0));
		
		
		this.joystick.addButton(buttonUp, new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {} // Do nothing
			@Override
			public void keyReleased(KeyEvent e) {
				Paddle.this.speedDown();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				Paddle.this.speedUp();
			}
		});
		
		this.joystick.addButton(buttonDown, new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {} // Do nothing
			@Override
			public void keyReleased(KeyEvent e) {
				Paddle.this.speedUp();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				Paddle.this.speedDown();
			}
		});
	}
	
	private void speedUp() {
		this.getSpeed().y += 10;
	}
	
	private void speedDown() {
		this.getSpeed().y -= 10;
	}
	
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
		g.setColor(Color.blue);
	    g.fillRect(this.getCoordinate().x, this.getCoordinate().y, 
	    		this.getDimension().width, this.getDimension().height);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		this.joystick.keyTyped(e);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		this.joystick.keyPressed(e);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		this.joystick.keyReleased(e);
	}

}
