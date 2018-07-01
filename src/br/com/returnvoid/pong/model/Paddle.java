package br.com.returnvoid.pong.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.com.returnvoid.returnengine.controller.Joystick;
import br.com.returnvoid.returnengine.model.Entity;

public class Paddle extends Entity implements KeyListener{
	private Joystick joystick = new Joystick();
	public Paddle(int buttonUp, int buttonDown, int x, int y) {
		this.setCoordinate(new Point(x, y));
		this.setDimension(new Dimension(10, 100));
		this.vx = 0;
		this.vy = 0;
		
		this.joystick.addButton(buttonUp, new KeyListener() {
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
		
		this.joystick.addButton(buttonDown, new KeyListener() {
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
	}
	
	private void speedUp() {
		if (this.vy < 0.5)
			this.vy += 0.5;
	}
	
	private void speedDown() {
		if (this.vy > -0.5)
			this.vy -= 0.5;
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
