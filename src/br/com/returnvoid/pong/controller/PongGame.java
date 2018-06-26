package br.com.returnvoid.pong.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import br.com.returnvoid.pong.model.Ball;
import br.com.returnvoid.pong.model.Paddle;
import br.com.returnvoid.pong.model.Player;
import br.com.returnvoid.pong.view.EndScreen;
import br.com.returnvoid.returnengine.controller.Game;

public class PongGame extends Game{
	//Ball ball;
	List<Ball> balls = new ArrayList<Ball>();
	Paddle paddle1, paddle2;
	Player player1, player2;
	int p1;
	int p2;	
	
	public PongGame(JFrame window, Player player1, Player player2) {
		super(600, 30, window);
		this.player1 = player1;
		this.player2 = player2;
		Thread tr = new Thread(new Runnable() {
			
			public void run() {
				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				balls.add(new Ball());
			}
		});
		tr.start();
		
		//38 -> up
		//40 -> down
		//87 -> w
		//83 -> s
		paddle1 = new Paddle(38, 40, 100, 100);
		paddle2 = new Paddle(87, 83, 700, 100);
		p1 = 0;
		p2 = 0;
		window.addKeyListener(paddle1);
		window.addKeyListener(paddle2);
		
		
		player1.setPoints(0);
		player2.setPoints(0);
		
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
		
		Font s = new Font("Gamer", Font.BOLD, 150);
		g.setFont(s);
		g.setColor(Color.WHITE);
		
		String score = Integer.toString(player1.getPoints());
		
		g.drawString(score, 240, 125);
		score = Integer.toString(player2.getPoints());	 
		g.drawString(score, 520, 125);
		
		s = new Font("Gamer", Font.BOLD, 50);
		g.setFont(s);
		
		g.drawString(player1.getNome(),  75, 550);		
		g.drawString(player2.getNome(),  525, 550);
		
		if(player1.getPoints() == 10 || player2.getPoints() == 10) {
			this.stop();
			super.window.dispose();
			EndScreen endScreen = new EndScreen(player1, player2);
			endScreen.setSize(new Dimension(800, 600));
			endScreen.setVisible(true);
		}
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
			
				if(ball.x <= 0) {
					player2.setPoints(player2.getPoints() + 1);
				}
				if(ball.x >= this.window.getWidth()) {
					player1.setPoints(player1.getPoints() + 1);
				}
				
				ball.x = this.window.getWidth() / 2;
				ball.y = this.window.getHeight() / 2;
				
				ball.vx = (random.nextDouble() * 0.6) + 0.4 *(random.nextBoolean()? -1: 1);
				ball.vy = random.nextDouble()/2;
			}
			
		}
	}

}