package br.com.returnvoid.pong.controller;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import br.com.returnvoid.pong.model.Ball;
import br.com.returnvoid.pong.model.Paddle;
import br.com.returnvoid.pong.model.Player;
import br.com.returnvoid.returnengine.controller.Game;

public class PongGame extends Game{
	//Ball ball;
	List<Ball> balls = new ArrayList<Ball>();
	Paddle paddle1, paddle2;
	Player player1, player2;
	public PongGame(JFrame window, Player player1, Player player2) {
		super(30, 30, window);
		this.player1 = player1;
		this.player2 = player2;
		balls.add(new Ball());
		//38 -> up
		//40 -> down
		//87 -> w
		//83 -> s
		paddle1 = new Paddle(38, 40, 100, 100);
		paddle2 = new Paddle(87, 83, 700, 100);
		
		window.addKeyListener(paddle1);
		window.addKeyListener(paddle2);
		
		window.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				balls.add(new Ball());
			}
		});
	}
	

	@Override
	protected void onRender(Graphics2D g) {
		// TODO Auto-generated method stub
		for(Ball ball: balls)
			ball.paint(g);
		paddle1.paint(g);
		paddle2.paint(g);
		//System.out.println("On Render");
	}
	
	Random random = new Random();
	@Override
	protected void onLoop() {
		for (Ball ball: balls) {
			ball.x += ball.vx;
			ball.y += ball.vy;
			
			paddle1.y += paddle1.vy;
			if(paddle1.y > this.window.getHeight()-paddle1.getBounds().getHeight() || paddle1.y < 30)
				paddle1.y -= paddle1.vy;
			
			paddle2.y += paddle2.vy;
			if(paddle2.y > this.window.getHeight()-paddle2.getBounds().getHeight() || paddle2.y < 30)
				paddle2.y -= paddle2.vy;		
			
			if(ball.checkColision(paddle1)) {
				ball.vx *= -1;
				if(ball.vy > 0 && paddle1.vy > 0)
					ball.vy += 0.1;
				
				if(ball.vy < 0 && paddle1.vy < 0)
					ball.vy -= 0.1;
			}
			if(ball.checkColision(paddle2)) {
				ball.vx *= -1;
				if(ball.vy > 0 && paddle2.vy > 0)
					ball.vy += 0.1;
				
				if(ball.vy < 0 && paddle2.vy < 0)
					ball.vy -= 0.1;
			}
			
			if(ball.y < 30) {
				ball.y = 30;
				ball.vy *= -1;
			}else if(ball.y > this.window.getHeight()) {
				ball.y = this.window.getHeight();
				ball.vy *= -1;
			}
			
			
			if(ball.x <= 0 || ball.x >=this.window.getWidth()) {
				ball.x = this.window.getWidth() / 2;
				ball.y = this.window.getHeight() / 2;
				
				ball.vx = (random.nextDouble() * 0.6) + 0.4 *(random.nextBoolean()? -1: 1);
				ball.vy = random.nextDouble()/2;
			}
		}
	}

}