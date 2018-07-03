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

/*
 * Implementação de raquete, subclasse de entidade
 * Note que essa entidade implementa KeyListener igual ao joystick,
 * mas apenas para facilitar, de forma que a pŕopria raquete
 * possa ser adicionada ao listener da janela
 */
public class Paddle extends Entity implements KeyListener{
	// Joystick que contém a relação de controles da entidade
	private Joystick joystick = new Joystick();
	private static final Dimension PADDLE_DIMENSION = new Dimension(10, 100);
	private static final double BASE_SPEED = 0.5; //Velocidade base que aumenta e diminui nos loops
	public Paddle(int buttonUp, int buttonDown, int x, int y) {
		this.setCoordinate(new Point(x, y));
		this.setDimension(PADDLE_DIMENSION);
		this.vx = 0;
		this.vy = 0;
		
		/*
		 *  Adicionando os botões cima e baixo ao joystick
		 *  Note que ao apertar uma tecla a velocidade "aumenta" em uma direção
		 *  e ao soltar essa tecla a velocidade "aumenta" na direção contrária
		 */
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
		if (this.vy < BASE_SPEED)
			this.vy += BASE_SPEED;
	}
	
	private void speedDown() {
		if (this.vy > BASE_SPEED * -1)
			this.vy -= BASE_SPEED;
	}
	
	// Verificando a colisão através da intersecção das fronteiras
	@Override
	public boolean checkColision(Entity entity) {
		return this.getBounds().intersects(entity.getBounds());
	}
	
	// Desenhando a entidade na tela (retângulo branco)
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
	    g.fillRect(this.getCoordinate().x, this.getCoordinate().y, 
	    		this.getDimension().width, this.getDimension().height);
	}
	
	// Delegando as funções do teclado para o joystick
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
