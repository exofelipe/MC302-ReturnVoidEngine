package br.com.returnvoid.pong.controller;

import java.awt.Graphics2D;

import javax.swing.JFrame;

import br.com.returnvoid.pong.model.Ball;
import br.com.returnvoid.pong.model.Paddle;
import br.com.returnvoid.returnengine.controller.Game;

public class PongGame extends Game{
	Ball ball;
	Paddle paddle1, paddle2;
	public PongGame(JFrame window) {
		super(30, 30, window);
		ball = new Ball();
		
		//38 -> up
		//40 -> down
		//87 -> w
		//83 -> s
				
		paddle1 = new Paddle(38, 40, 100, 100);
		paddle2 = new Paddle(87, 83, 500, 100);
		
		window.addKeyListener(paddle1);
		window.addKeyListener(paddle2);
	}

	@Override
	protected void onRender(Graphics2D g) {
		// TODO Auto-generated method stub
		ball.paint(g);
		paddle1.paint(g);
		//System.out.println("On Render");
	}
	int change = 0;
	@Override
	protected void onLoop() {
		// TODO Auto-generated method stub
		ball.getCoordinate().x += ball.getSpeed().x;
		ball.getCoordinate().y += ball.getSpeed().y;
		paddle1.getCoordinate().y += paddle1.getSpeed().y;
		
		
		if(ball.checkColision(paddle1)) {
			ball.getSpeed().x *= -1;
			change++;
			
			if(change==10) {
				change = 0;
				ball.getSpeed().y *= -1;
			}
		}
	}

}